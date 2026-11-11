package no.stelar7.api.r4j.basic.ratelimiting;

import javax.sql.DataSource;
import java.sql.*;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.LongSupplier;

/**
 * Keeps the shared rate limit counters in a SQL database, so every process using the same api key (on any machine) obeys the limits together.
 * <pre>{@code
 * // with a connection pool
 * SharedRateLimitStore store = new JdbcRateLimitStore(dataSource);
 *
 * // or without one
 * SharedRateLimitStore store = new JdbcRateLimitStore(() -> DriverManager.getConnection("jdbc:postgresql://localhost/r4j", "user", "password"));
 *
 * R4JContext.getDefault().setRateLimitStore(store);
 * }</pre>
 * It uses one small table (created on first use if it does not exist) and plain SQL, so it works with any database that has row locks and
 * transactions; it is tested with H2, MySQL and PostgreSQL. R4J does not ship a JDBC driver, use the one of your database.
 * <p>
 * Things to know, compared to {@link RedisRateLimitStore}:
 * <ul>
 * <li>Every call takes a connection and runs a short transaction, so use a connection pool. Redis is the faster choice when both are available.</li>
 * <li>Expiry is measured with the clock of the process, not of the database (there is no portable SQL for the current time in milliseconds),
 * so the clocks of the machines sharing a key should be synchronized (NTP). A difference shortens or lengthens a window by that much.</li>
 * </ul>
 * Operations on the same bucket are serialized with a lock row, operations on different buckets do not wait for each other.
 */
public class JdbcRateLimitStore implements SharedRateLimitStore
{
    /**
     * Gives a connection to the database. The store closes it when it is done with it (which returns it to the pool, if there is one).
     */
    @FunctionalInterface
    public interface ConnectionSupplier
    {
        Connection get() throws SQLException;
    }

    public static final String DEFAULT_TABLE = "r4j_rate_limit";

    private static final long PURGE_INTERVAL_MS = 60_000;
    private static final int  LOCK_ATTEMPTS     = 5;

    private static final class Row
    {
        private final String value;
        private final long   expiresAt;

        private Row(String value, long expiresAt)
        {
            this.value = value;
            this.expiresAt = expiresAt;
        }
    }

    private interface Transaction<T>
    {
        T run(Connection connection, long now) throws SQLException;
    }

    private final ConnectionSupplier connections;
    private final LongSupplier       clock;
    private final String             table;

    private final Set<String> knownLocks = ConcurrentHashMap.newKeySet();

    private volatile boolean tableChecked;
    private volatile long    lastPurge;

    /**
     * @param dataSource where to get connections from (ie. a connection pool)
     */
    public JdbcRateLimitStore(DataSource dataSource)
    {
        this(dataSource::getConnection);
    }

    /**
     * @param connections gives a connection to the database
     */
    public JdbcRateLimitStore(ConnectionSupplier connections)
    {
        this(connections, DEFAULT_TABLE, System::currentTimeMillis);
    }

    /**
     * @param connections gives a connection to the database
     * @param table       the name of the table to use (letters, digits and underscores, optionally prefixed with a schema)
     * @param clock       gives the current time in ms
     */
    public JdbcRateLimitStore(ConnectionSupplier connections, String table, LongSupplier clock)
    {
        if (!table.matches("[A-Za-z_][A-Za-z0-9_]*(\\.[A-Za-z_][A-Za-z0-9_]*)?"))
        {
            throw new IllegalArgumentException("Invalid table name: " + table);
        }

        this.connections = Objects.requireNonNull(connections);
        this.clock = Objects.requireNonNull(clock);
        this.table = table;
    }

    /**
     * Creates the table if it does not exist. This is done automatically on first use; call it yourself to fail early,
     * or create the table by hand if the database user of the application is not allowed to:
     * <pre>
     * CREATE TABLE r4j_rate_limit (
     *     entry_key   VARCHAR(255) NOT NULL PRIMARY KEY,
     *     entry_value VARCHAR(255) NOT NULL,
     *     expires_at  BIGINT       NOT NULL
     * )
     * </pre>
     */
    public void createTable()
    {
        try (Connection connection = connections.get())
        {
            connection.setAutoCommit(true);
            if (tableExists(connection))
            {
                tableChecked = true;
                return;
            }

            try (Statement statement = connection.createStatement())
            {
                statement.executeUpdate("CREATE TABLE " + table + " (entry_key VARCHAR(255) NOT NULL PRIMARY KEY, entry_value VARCHAR(255) NOT NULL, expires_at BIGINT NOT NULL)");
            } catch (SQLException e)
            {
                // another process may have created it at the same time
                if (!tableExists(connection))
                {
                    throw e;
                }
            }

            tableChecked = true;
        } catch (SQLException e)
        {
            throw new IllegalStateException("Unable to create the table " + table, e);
        }
    }

    private boolean tableExists(Connection connection)
    {
        try (Statement statement = connection.createStatement(); ResultSet ignored = statement.executeQuery("SELECT entry_key FROM " + table + " WHERE 1 = 0"))
        {
            return true;
        } catch (SQLException e)
        {
            return false;
        }
    }

    /**
     * Removes the entries that have expired. This is done automatically about once a minute; expired entries are never used, this only keeps the table small.
     */
    public void purgeExpired()
    {
        lastPurge = clock.getAsLong();
        try (Connection connection = connections.get())
        {
            connection.setAutoCommit(true);
            try (PreparedStatement statement = connection.prepareStatement("DELETE FROM " + table + " WHERE expires_at <> 0 AND expires_at <= ?"))
            {
                statement.setLong(1, clock.getAsLong());
                statement.executeUpdate();
            }
        } catch (SQLException e)
        {
            throw new IllegalStateException("Unable to remove expired entries from " + table, e);
        }
    }

    /**
     * The keys of a bucket look like "{bucket}:count:1000"; everything inside the braces is the bucket, and what is locked.
     */
    private static String lockName(String key)
    {
        int end = key.indexOf('}');
        return (key.startsWith("{") && end > 0 ? key.substring(0, end + 1) : key) + ":lock";
    }

    /**
     * Runs the work in a transaction that holds the lock of the bucket, so no other process changes the bucket at the same time.
     */
    private <T> T locked(String key, Transaction<T> work)
    {
        if (!tableChecked)
        {
            createTable();
        }

        long now = clock.getAsLong();
        if (now - lastPurge > PURGE_INTERVAL_MS)
        {
            try
            {
                purgeExpired();
            } catch (RuntimeException e)
            {
                // not important enough to fail the call
            }
        }

        String lock = lockName(key);
        try (Connection connection = connections.get())
        {
            for (int attempt = 0; attempt < LOCK_ATTEMPTS; attempt++)
            {
                if (!knownLocks.contains(lock))
                {
                    // The lock row has to exist before it can be locked. This is done outside of the transaction,
                    // as some databases abort the whole transaction when a statement fails (ie. because another process just inserted the row).
                    connection.setAutoCommit(true);
                    try (PreparedStatement insert = connection.prepareStatement("INSERT INTO " + table + " (entry_key, entry_value, expires_at) VALUES (?, '0', 0)"))
                    {
                        insert.setString(1, lock);
                        insert.executeUpdate();
                    } catch (SQLException e)
                    {
                        // it already exists
                    }
                }

                connection.setAutoCommit(false);
                try
                {
                    // updating the row locks it until the transaction ends
                    int locked;
                    try (PreparedStatement update = connection.prepareStatement("UPDATE " + table + " SET expires_at = 0 WHERE entry_key = ?"))
                    {
                        update.setString(1, lock);
                        locked = update.executeUpdate();
                    }

                    if (locked == 0)
                    {
                        // the lock row is gone (ie. the table was emptied), create it again
                        connection.rollback();
                        knownLocks.remove(lock);
                        continue;
                    }

                    knownLocks.add(lock);
                    T result = work.run(connection, now);
                    connection.commit();
                    return result;
                } catch (SQLException | RuntimeException e)
                {
                    try
                    {
                        connection.rollback();
                    } catch (SQLException ignored)
                    {
                        // the connection is broken, the original problem is reported
                    }
                    throw e;
                }
            }

            throw new IllegalStateException("Unable to lock " + lock + " in " + table);
        } catch (SQLException e)
        {
            throw new IllegalStateException("The rate limit store could not use the database", e);
        }
    }

    /**
     * Reads without taking the lock; what is read is a committed state
     */
    private <T> T unlocked(Transaction<T> work)
    {
        if (!tableChecked)
        {
            createTable();
        }

        try (Connection connection = connections.get())
        {
            connection.setAutoCommit(true);
            return work.run(connection, clock.getAsLong());
        } catch (SQLException e)
        {
            throw new IllegalStateException("The rate limit store could not use the database", e);
        }
    }

    /**
     * @return the entry, or null if it does not exist or has expired
     */
    private Row read(Connection connection, String key, long now) throws SQLException
    {
        try (PreparedStatement select = connection.prepareStatement("SELECT entry_value, expires_at FROM " + table + " WHERE entry_key = ?"))
        {
            select.setString(1, key);
            try (ResultSet result = select.executeQuery())
            {
                if (!result.next())
                {
                    return null;
                }

                Row row = new Row(result.getString(1), result.getLong(2));
                return row.expiresAt != 0 && row.expiresAt <= now ? null : row;
            }
        }
    }

    /**
     * Creates or replaces the entry. Only used while holding the lock of the bucket.
     */
    private void write(Connection connection, String key, String value, long expiresAt) throws SQLException
    {
        int updated;
        try (PreparedStatement update = connection.prepareStatement("UPDATE " + table + " SET entry_value = ?, expires_at = ? WHERE entry_key = ?"))
        {
            update.setString(1, value);
            update.setLong(2, expiresAt);
            update.setString(3, key);
            updated = update.executeUpdate();
        }

        if (updated == 0)
        {
            try (PreparedStatement insert = connection.prepareStatement("INSERT INTO " + table + " (entry_key, entry_value, expires_at) VALUES (?, ?, ?)"))
            {
                insert.setString(1, key);
                insert.setString(2, value);
                insert.setLong(3, expiresAt);
                insert.executeUpdate();
            }
        }
    }

    private static long remaining(Row row, long now)
    {
        return row == null || row.expiresAt == 0 ? 0 : Math.max(1, row.expiresAt - now);
    }

    @Override
    public long tryIncrement(String blockKey, List<String> counters, List<Long> maxima, List<Long> ttls)
    {
        return locked(blockKey, (connection, now) -> {
            Row block = read(connection, blockKey, now);
            if (block != null && block.expiresAt != 0)
            {
                return -remaining(block, now);
            }

            List<Row> rows = new ArrayList<>();
            long      wait = 0;
            for (int i = 0; i < counters.size(); i++)
            {
                Row row = read(connection, counters.get(i), now);
                rows.add(row);

                if (row != null && Long.parseLong(row.value) >= maxima.get(i))
                {
                    if (row.expiresAt == 0)
                    {
                        // a counter without an expiry would block forever
                        write(connection, counters.get(i), row.value, now + ttls.get(i));
                        wait = Math.max(wait, ttls.get(i));
                    } else
                    {
                        wait = Math.max(wait, remaining(row, now));
                    }
                }
            }

            if (wait > 0)
            {
                return wait;
            }

            for (int i = 0; i < counters.size(); i++)
            {
                Row row = rows.get(i);
                if (row == null)
                {
                    write(connection, counters.get(i), "1", now + ttls.get(i));
                } else
                {
                    write(connection, counters.get(i), String.valueOf(Long.parseLong(row.value) + 1), row.expiresAt);
                }
            }

            return 0L;
        });
    }

    @Override
    public void decrement(List<String> counters)
    {
        if (counters.isEmpty())
        {
            return;
        }

        locked(counters.get(0), (connection, now) -> {
            for (String counter : counters)
            {
                Row row = read(connection, counter, now);
                if (row != null && Long.parseLong(row.value) > 0)
                {
                    write(connection, counter, String.valueOf(Long.parseLong(row.value) - 1), row.expiresAt);
                }
            }
            return null;
        });
    }

    @Override
    public long raise(String counter, long value, long ttl)
    {
        return locked(counter, (connection, now) -> {
            Row row = read(connection, counter, now);
            if (row == null)
            {
                write(connection, counter, String.valueOf(value), now + ttl);
                return 0L;
            }

            long previous  = Long.parseLong(row.value);
            long expiresAt = row.expiresAt == 0 ? 0 : Math.max(row.expiresAt, now + ttl);
            if (previous < value || expiresAt != row.expiresAt)
            {
                write(connection, counter, String.valueOf(Math.max(previous, value)), expiresAt);
            }

            return previous;
        });
    }

    @Override
    public long getCounter(String counter)
    {
        String value = get(counter);
        return value == null ? 0 : Long.parseLong(value);
    }

    @Override
    public void put(String key, String value, long ttl)
    {
        locked(key, (connection, now) -> {
            write(connection, key, value, ttl > 0 ? now + ttl : 0);
            return null;
        });
    }

    @Override
    public String get(String key)
    {
        return unlocked((connection, now) -> {
            Row row = read(connection, key, now);
            return row == null ? null : row.value;
        });
    }

    @Override
    public long getTimeToLive(String key)
    {
        return unlocked((connection, now) -> remaining(read(connection, key, now), now));
    }
}
