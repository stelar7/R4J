package no.stelar7.api.r4j.basic.cache.impl;

import no.stelar7.api.r4j.basic.APICredentials;
import no.stelar7.api.r4j.basic.cache.*;
import no.stelar7.api.r4j.basic.calling.R4JContext;
import no.stelar7.api.r4j.basic.constants.api.URLEndpoint;
import no.stelar7.api.r4j.basic.utils.Utils;
import org.slf4j.*;

import javax.sql.DataSource;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.sql.*;
import java.util.*;

/**
 * Caches the responses in a SQL database, so the cache can be shared by every process (on any machine) that can reach the database.
 * <pre>{@code
 * context.setCacheProvider(new JdbcCacheProvider(dataSource));
 *
 * // or without a connection pool
 * context.setCacheProvider(new JdbcCacheProvider(() -> DriverManager.getConnection("jdbc:postgresql://localhost/r4j", "user", "password")));
 * }</pre>
 * It uses one table (created on first use if it does not exist) and plain SQL, and is tested with H2, MySQL and PostgreSQL.
 * R4J does not ship a JDBC driver, use the one of your database.
 * <p>
 * The items are stored the same way as by {@link FileSystemCacheProvider}: as json where that is lossless, otherwise serialized.
 * A problem with the database never fails a call; it is logged, and the call is made as if nothing was cached.
 */
public class JdbcCacheProvider implements CacheProvider
{
    /**
     * Gives a connection to the database. The cache closes it when it is done with it (which returns it to the pool, if there is one).
     */
    @FunctionalInterface
    public interface ConnectionSupplier
    {
        Connection get() throws SQLException;
    }

    private interface Work<T>
    {
        T run(Connection connection) throws SQLException;
    }

    private static final Logger logger = LoggerFactory.getLogger(JdbcCacheProvider.class);

    public static final String DEFAULT_TABLE = "r4j_cache";

    /**
     * java serialized items are stored as text too, with this in front of their base64
     */
    private static final String SERIALIZED_PREFIX = "ser:";

    private static final int MAX_KEY_TEXT = 1000;

    private final ConnectionSupplier connections;
    private final String             table;

    private volatile R4JContext        context = R4JContext.getDefault();
    private volatile long              timeToLive;
    private volatile CacheLifetimeHint hints   = CacheLifetimeHint.DEFAULTS;
    private volatile boolean           tableChecked;

    /**
     * @param dataSource where to get connections from (ie. a connection pool)
     */
    public JdbcCacheProvider(DataSource dataSource)
    {
        this(dataSource::getConnection);
    }

    /**
     * @param connections gives a connection to the database
     */
    public JdbcCacheProvider(ConnectionSupplier connections)
    {
        this(connections, DEFAULT_TABLE, CacheProvider.TTL_USE_HINTS);
    }

    /**
     * @param connections gives a connection to the database
     * @param table       the name of the table to use (letters, digits and underscores, optionally prefixed with a schema)
     * @param ttl         how long (in ms) items are kept, or one of {@link CacheProvider#TTL_INFINITY}, {@link CacheProvider#TTL_USE_HINTS}
     */
    public JdbcCacheProvider(ConnectionSupplier connections, String table, long ttl)
    {
        if (!table.matches("[A-Za-z_][A-Za-z0-9_]*(\\.[A-Za-z_][A-Za-z0-9_]*)?"))
        {
            throw new IllegalArgumentException("Invalid table name: " + table);
        }

        this.connections = Objects.requireNonNull(connections);
        this.table = table;
        this.timeToLive = ttl;
    }

    /**
     * Creates the table if it does not exist. This is done automatically on first use; call it yourself to fail early,
     * or create the table by hand if the database user of the application is not allowed to
     * (use the largest text type of your database for the data column):
     * <pre>
     * CREATE TABLE r4j_cache (
     *     endpoint  VARCHAR(100)  NOT NULL,
     *     key_hash  VARCHAR(64)   NOT NULL,
     *     key_text  VARCHAR(1000) NOT NULL,
     *     data      LONGTEXT      NOT NULL,
     *     stored_at BIGINT        NOT NULL,
     *     PRIMARY KEY (endpoint, key_hash)
     * )
     * </pre>
     *
     * @throws IllegalStateException if the table does not exist and can not be created
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

            // the name of a text type without a size limit differs per database
            SQLException last = null;
            for (String textType : new String[]{"LONGTEXT", "TEXT", "CLOB"})
            {
                try (Statement statement = connection.createStatement())
                {
                    statement.executeUpdate("CREATE TABLE " + table + " (endpoint VARCHAR(100) NOT NULL, key_hash VARCHAR(64) NOT NULL, key_text VARCHAR(" + MAX_KEY_TEXT + ") NOT NULL, " +
                                            "data " + textType + " NOT NULL, stored_at BIGINT NOT NULL, PRIMARY KEY (endpoint, key_hash))");
                    tableChecked = true;
                    return;
                } catch (SQLException e)
                {
                    last = e;
                    // another process may have created it at the same time
                    if (tableExists(connection))
                    {
                        tableChecked = true;
                        return;
                    }
                }
            }

            throw last;
        } catch (SQLException e)
        {
            throw new IllegalStateException("Unable to create the table " + table, e);
        }
    }

    private boolean tableExists(Connection connection)
    {
        try (Statement statement = connection.createStatement(); ResultSet ignored = statement.executeQuery("SELECT endpoint FROM " + table + " WHERE 1 = 0"))
        {
            return true;
        } catch (SQLException e)
        {
            return false;
        }
    }

    /**
     * Runs the work with a connection; a problem with the database is logged and gives the fallback
     */
    private <T> T withConnection(String action, T fallback, Work<T> work)
    {
        try
        {
            if (!tableChecked)
            {
                createTable();
            }

            try (Connection connection = connections.get())
            {
                connection.setAutoCommit(true);
                return work.run(connection);
            }
        } catch (SQLException | RuntimeException e)
        {
            logger.warn("Unable to {} the database cache", action, e);
            return fallback;
        }
    }

    @Override
    public void setContext(R4JContext context)
    {
        this.context = context;
    }

    @Override
    public void setTimeToLiveGlobal(long timeToLive)
    {
        this.timeToLive = timeToLive;
    }

    @Override
    public void setTimeToLive(CacheLifetimeHint hints)
    {
        this.hints = hints;
    }

    @Override
    public long getTimeToLive(URLEndpoint type)
    {
        return timeToLive == TTL_USE_HINTS ? hints.get(type) : timeToLive;
    }

    /**
     * The values identifying the item; everything but the item itself, followed by the api key combination
     */
    private List<String> keyValues(Map<String, Object> obj, boolean withCredentials)
    {
        List<String> values = new ArrayList<>();
        obj.forEach((k, v) -> {
            if (!"value".equals(k))
            {
                values.add(Utils.normalizeString(String.valueOf(v)));
            }
        });

        if (withCredentials)
        {
            // so different api keys do not share data (ids are encrypted per key)
            APICredentials credentials = context.getCredentials();
            values.add(credentials == null ? "STATIC_DATA" : credentials.getUniqueKeyCombination());
        }

        return values;
    }

    private static String keyText(List<String> values)
    {
        String text = String.join("/", values);
        return text.length() > MAX_KEY_TEXT ? text.substring(0, MAX_KEY_TEXT) : text;
    }

    private static String keyHash(List<String> values)
    {
        try
        {
            byte[]        hash = MessageDigest.getInstance("SHA-256").digest(String.join("/", values).getBytes(StandardCharsets.UTF_8));
            StringBuilder sb   = new StringBuilder();
            for (byte b : hash)
            {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e)
        {
            throw new IllegalStateException("SHA-256 is not available", e);
        }
    }

    private void delete(Connection connection, URLEndpoint type, String hash) throws SQLException
    {
        try (PreparedStatement delete = connection.prepareStatement("DELETE FROM " + table + " WHERE endpoint = ? AND key_hash = ?"))
        {
            delete.setString(1, type.name());
            delete.setString(2, hash);
            delete.executeUpdate();
        }
    }

    @Override
    public void store(URLEndpoint type, Map<String, Object> obj)
    {
        if (!obj.containsKey("value"))
        {
            throw new IllegalArgumentException("Invalid cache insert!");
        }

        // if the object we are trying to store is not valid, dont store it.
        Object item = obj.get("value");
        if (item == null)
        {
            return;
        }

        List<String> values = keyValues(obj, true);
        withConnection("store data in", null, connection -> {
            String data;
            try
            {
                byte[] bytes = CacheSerializer.serialize(type, item);
                data = CacheSerializer.isJavaSerialization(bytes) ? SERIALIZED_PREFIX + Base64.getEncoder().encodeToString(bytes) : new String(bytes, StandardCharsets.UTF_8);
            } catch (java.io.IOException e)
            {
                throw new SQLException("The item can not be serialized", e);
            }

            String hash = keyHash(values);
            long   now  = System.currentTimeMillis();

            // update first, insert if there was nothing to update, and update again if someone else inserted in between
            for (int attempt = 0; attempt < 2; attempt++)
            {
                try (PreparedStatement update = connection.prepareStatement("UPDATE " + table + " SET data = ?, stored_at = ? WHERE endpoint = ? AND key_hash = ?"))
                {
                    update.setString(1, data);
                    update.setLong(2, now);
                    update.setString(3, type.name());
                    update.setString(4, hash);
                    if (update.executeUpdate() > 0)
                    {
                        return null;
                    }
                }

                try (PreparedStatement insert = connection.prepareStatement("INSERT INTO " + table + " (endpoint, key_hash, key_text, data, stored_at) VALUES (?, ?, ?, ?, ?)"))
                {
                    insert.setString(1, type.name());
                    insert.setString(2, hash);
                    insert.setString(3, keyText(values));
                    insert.setString(4, data);
                    insert.setLong(5, now);
                    insert.executeUpdate();
                    return null;
                } catch (SQLException e)
                {
                    if (attempt == 1)
                    {
                        throw e;
                    }
                }
            }

            return null;
        });
    }

    @Override
    public void update(URLEndpoint type, Map<String, Object> obj)
    {
        if (!obj.containsKey("value"))
        {
            throw new IllegalArgumentException("Invalid cache insert!");
        }

        if (obj.get("value") == null)
        {
            return;
        }

        String hash = keyHash(keyValues(obj, true));
        Boolean refreshed = withConnection("update data in", false, connection -> {
            try (PreparedStatement update = connection.prepareStatement("UPDATE " + table + " SET stored_at = ? WHERE endpoint = ? AND key_hash = ?"))
            {
                update.setLong(1, System.currentTimeMillis());
                update.setString(2, type.name());
                update.setString(3, hash);
                return update.executeUpdate() > 0;
            }
        });

        if (!refreshed)
        {
            store(type, obj);
        }
    }

    @Override
    public Optional<?> get(URLEndpoint type, Map<String, Object> obj)
    {
        String hash = keyHash(keyValues(obj, true));

        Object item = withConnection("read from", null, connection -> {
            String data;
            long   storedAt;
            try (PreparedStatement select = connection.prepareStatement("SELECT data, stored_at FROM " + table + " WHERE endpoint = ? AND key_hash = ?"))
            {
                select.setString(1, type.name());
                select.setString(2, hash);
                try (ResultSet result = select.executeQuery())
                {
                    if (!result.next())
                    {
                        return null;
                    }

                    data = result.getString(1);
                    storedAt = result.getLong(2);
                }
            }

            long ttl = getTimeToLive(type);
            if (ttl != TTL_INFINITY && System.currentTimeMillis() - storedAt > ttl)
            {
                logger.debug("Data in cache is outdated, deleting...");
                delete(connection, type, hash);
                return null;
            }

            try
            {
                byte[] bytes = data.startsWith(SERIALIZED_PREFIX) ? Base64.getDecoder().decode(data.substring(SERIALIZED_PREFIX.length())) : data.getBytes(StandardCharsets.UTF_8);
                return CacheSerializer.deserialize(type, bytes);
            } catch (java.io.IOException | ClassNotFoundException | RuntimeException e)
            {
                logger.info("Data in cache is from an older version of the library, removing it");
                delete(connection, type, hash);
                return null;
            }
        });

        if (item != null)
        {
            logger.debug("Loaded data from cache ({} {} {})", this.getClass().getName(), type, obj);
        }

        return Optional.ofNullable(item);
    }

    /**
     * The hashes of the items of the endpoint whose key starts with the values of the filter
     */
    private List<String> matching(Connection connection, URLEndpoint type, Map<String, Object> filter) throws SQLException
    {
        String       prefix = keyText(keyValues(filter, false));
        List<String> hashes = new ArrayList<>();

        try (PreparedStatement select = connection.prepareStatement("SELECT key_hash, key_text FROM " + table + " WHERE endpoint = ?"))
        {
            select.setString(1, type.name());
            try (ResultSet result = select.executeQuery())
            {
                while (result.next())
                {
                    String text = result.getString(2);
                    if (prefix.isEmpty() || text.equals(prefix) || text.startsWith(prefix + "/"))
                    {
                        hashes.add(result.getString(1));
                    }
                }
            }
        }

        return hashes;
    }

    @Override
    public void clear(URLEndpoint type, Map<String, Object> filter)
    {
        withConnection("clear", null, connection -> {
            for (String hash : matching(connection, type, filter))
            {
                delete(connection, type, hash);
            }
            return null;
        });
    }

    @Override
    public long getSize(URLEndpoint type, Map<String, Object> filter)
    {
        return withConnection("get the size of", -1L, connection -> (long) matching(connection, type, filter).size());
    }

    @Override
    public void clearOldCache()
    {
        if (timeToLive == TTL_INFINITY)
        {
            return;
        }

        withConnection("remove old data from", null, connection -> {
            List<String> endpoints = new ArrayList<>();
            try (Statement select = connection.createStatement(); ResultSet result = select.executeQuery("SELECT DISTINCT endpoint FROM " + table))
            {
                while (result.next())
                {
                    endpoints.add(result.getString(1));
                }
            }

            long now = System.currentTimeMillis();
            for (String endpoint : endpoints)
            {
                long ttl;
                try
                {
                    ttl = getTimeToLive(URLEndpoint.valueOf(endpoint));
                } catch (IllegalArgumentException e)
                {
                    // an endpoint that no longer exists
                    ttl = 0;
                }

                try (PreparedStatement delete = connection.prepareStatement("DELETE FROM " + table + " WHERE endpoint = ? AND stored_at < ?"))
                {
                    delete.setString(1, endpoint);
                    delete.setLong(2, now - ttl);
                    delete.executeUpdate();
                }
            }

            return null;
        });
    }
}
