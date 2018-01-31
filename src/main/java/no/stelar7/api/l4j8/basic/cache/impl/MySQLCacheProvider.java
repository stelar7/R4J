package no.stelar7.api.l4j8.basic.cache.impl;

import no.stelar7.api.l4j8.basic.cache.*;
import no.stelar7.api.l4j8.basic.constants.api.URLEndpoint;
import no.stelar7.api.l4j8.basic.utils.MySQL;

import java.sql.*;
import java.util.*;

public class MySQLCacheProvider implements CacheProvider
{
    
    private       long              timeToLive;
    private       CacheLifetimeHint hints;
    private final MySQL             sql;
    
    private static final String EXPIRES_AT = "expires_at";
    
    public MySQLCacheProvider(String host, int port, String database, String username, String password)
    {
        sql = new MySQL(host, port, username, password);
        try
        {
            sql.open();
            sql.getConnection().createStatement().executeUpdate(String.format("CREATE DATABASE IF NOT EXISTS %s DEFAULT CHARACTER SET = utf8mb4 DEFAULT COLLATE = utf8mb4_unicode_ci", database));
            sql.getConnection().setCatalog(database);
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
    }
    
    
    @Override
    public void store(URLEndpoint type, Object... obj)
    {
        System.out.println(Arrays.toString(obj));
    }
    
    @Override
    public Optional<?> get(URLEndpoint type, Object... data)
    {
        return Optional.empty();
    }
    
    
    @Override
    public void update(URLEndpoint type, Object... obj)
    {
        String updateQuery = CacheDataKeys.createUpdateStatement(type, obj);
        
        try (PreparedStatement statement = sql.getConnection().prepareStatement(updateQuery))
        {
            statement.setString(1, type.toString());
            statement.setLong(2, System.currentTimeMillis() + getTimeToLive(type));
            statement.setString(3, EXPIRES_AT);
            
            statement.executeUpdate();
        } catch (final SQLException e)
        {
            e.printStackTrace();
        }
    }
    
    @Override
    public void clear(URLEndpoint type, Object... obj)
    {
        try
        {
            try (PreparedStatement statement = sql.getConnection().prepareStatement("DELETE FROM ?"))
            {
                statement.setString(1, type.toString());
                statement.executeUpdate();
            }
        } catch (final SQLException e)
        {
            e.printStackTrace();
        }
    }
    
    @Override
    public void clearOldCache()
    {
        if (timeToLive == CacheProvider.TTL_INFINITY)
        {
            return;
        }
        
        try (PreparedStatement stmt = sql.getConnection().prepareStatement("DELETE FROM ? WHERE ? < ?"))
        {
            for (URLEndpoint endpoint : URLEndpoint.values())
            {
                stmt.setString(1, endpoint.toString());
                stmt.setString(2, EXPIRES_AT);
                stmt.setLong(3, System.currentTimeMillis() + getTimeToLive(endpoint));
                stmt.executeUpdate();
            }
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
    }
    
    @Override
    public long getTimeToLive(URLEndpoint type)
    {
        if (timeToLive == CacheProvider.TTL_USE_HINTS)
        {
            return hints.get(type);
        }
        
        return timeToLive;
    }
    
    @Override
    public long getSize(URLEndpoint type)
    {
        try (PreparedStatement stmt = sql.getConnection().prepareStatement("SELECT COUNT(*) FROM ?"))
        {
            stmt.setString(1, type.toString());
            
            try (ResultSet rs = stmt.executeQuery())
            {
                rs.next();
                return rs.getLong(1);
            }
        } catch (SQLException e)
        {
            e.printStackTrace();
            return 0;
        }
    }
    
    @Override
    public void setTimeToLive(CacheLifetimeHint timeToLive)
    {
        this.hints = timeToLive;
    }
    
    @Override
    public void setTimeToLiveGlobal(long timeToLive)
    {
        this.timeToLive = timeToLive;
    }
    
}
