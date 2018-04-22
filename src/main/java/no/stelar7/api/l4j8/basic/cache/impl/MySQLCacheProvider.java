package no.stelar7.api.l4j8.basic.cache.impl;

import no.stelar7.api.l4j8.basic.cache.*;
import no.stelar7.api.l4j8.basic.constants.api.URLEndpoint;
import no.stelar7.api.l4j8.basic.utils.MySQL;

import java.lang.reflect.Field;
import java.sql.*;
import java.util.*;

public class MySQLCacheProvider implements CacheProvider
{
    
    private       long              timeToLive;
    private       CacheLifetimeHint hints;
    private final MySQL             sql;
    
    private static final String COLUMN_EXPIRES_AT = "expires_at";
    
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
        int                 index = 0;
        Map<String, String> keys  = new HashMap<>();
        for (String key : getKeysForType(type))
        {
            keys.put(key, obj[index++].toString());
        }
        
        storeParent(obj[0], keys);
    }
    
    private void storeParent(Object storeMe, Map<String, String> indexFields)
    {
        // TODO
        try
        {
            for (Field field : storeMe.getClass().getDeclaredFields())
            {
                field.setAccessible(true);
                
                boolean isBasic = field.getType().isPrimitive() || field.getType() == String.class;
                boolean isList  = Collection.class.isAssignableFrom(field.getType());
                
                if (isBasic)
                {
                    System.out.printf("Storing basic field %s=%s%n", field.getName(), field.get(storeMe));
                } else if (isList)
                {
                    System.out.printf("Storing list type %s=%s%n", field.getName(), field.get(storeMe));
                } else
                {
                    System.out.printf("Storing custom type %s=%s%n", field.getName(), field.get(storeMe));
                }
            }
        } catch (IllegalAccessException e)
        {
            e.printStackTrace();
        }
        System.out.println();
    }
    
    @Override
    public Optional<?> get(URLEndpoint type, Object... data)
    {
        return Optional.empty();
    }
    
    
    @Override
    public void update(URLEndpoint type, Object... obj)
    {
        // todo
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
                stmt.setString(2, COLUMN_EXPIRES_AT);
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
    
    private String[] getKeysForType(URLEndpoint type)
    {
        switch (type)
        {
            case V3_MATCH:
            {
                return new String[]{"platformId", "gameId"};
            }
            case V3_TIMELINE:
            {
                return new String[]{"platformId", "gameId"};
            }
            case V3_CHAMPIONS_BY_ID:
            {
                return new String[]{"platformId", "id"};
            }
            case V3_CHAMPIONS:
            {
                return new String[]{"platformId", "freeToPlay"};
            }
            case V3_MASTERY_BY_ID:
            {
                return new String[]{"platformId", "playerId"};
            }
            case V3_MASTERY_BY_CHAMPION:
            {
                return new String[]{"platformId", "playerId", "championId"};
            }
            case V3_MASTERY_SCORE:
            {
                return new String[]{"platformId", "playerId"};
            }
            case V3_LEAGUE_CHALLENGER:
            {
                return new String[]{"platformId", "queue"};
            }
            case V3_LEAGUE_MASTER:
            {
                return new String[]{"platformId", "queue"};
            }
            case V3_LEAGUE:
            {
                return new String[]{"platformId", "leagueId"};
            }
            case V3_LEAGUE_ENTRY:
            {
                return new String[]{"platformId", "playerOrTeamId"};
            }
            case V3_SPECTATOR_CURRENT:
            {
                return new String[]{"platformId", "playerId"};
            }
            case V3_SPECTATOR_FEATURED:
            {
                return new String[]{"platformId"};
            }
            case V3_SUMMONER_BY_ACCOUNT:
            {
                return new String[]{"platformId", "accountId"};
            }
            case V3_SUMMONER_BY_ID:
            {
                return new String[]{"platformId", "id"};
            }
            case V3_SUMMONER_BY_NAME:
            {
                return new String[]{"platformId", "name"};
            }
            case V3_SHARD_STATUS:
            {
                return new String[]{"platformId"};
            }
            default:
            {
                throw new UnsupportedOperationException(type.toString() + " is not added to the cache");
            }
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
