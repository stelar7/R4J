package no.stelar7.api.l4j8.basic.cache.impl;

import no.stelar7.api.l4j8.basic.cache.*;
import no.stelar7.api.l4j8.basic.constants.api.URLEndpoint;
import no.stelar7.api.l4j8.basic.utils.*;

import java.lang.reflect.*;
import java.sql.*;
import java.util.*;
import java.util.Map.Entry;

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
    
    Set<URLEndpoint> created = new HashSet<>();
    
    @Override
    public void store(URLEndpoint type, Object... obj)
    {
        int                 index = 0;
        Map<String, String> keys  = new HashMap<>();
        for (String key : getKeysForType(type))
        {
            keys.put(key, obj[index++].toString());
        }
        
        try
        {
            if (!created.contains(type))
            {
                createTableIfMissing(type, obj[0], keys.keySet());
                created.add(type);
            }
            
            storeParent(type, obj[0], keys);
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
    }
    
    
    @SuppressWarnings("unchecked")
    private void createTableIfMissing(URLEndpoint type, Object obj, Set<String> keys) throws SQLException
    {
        Field[]                    fields = obj.getClass().getDeclaredFields();
        Map<String, Object>        data   = new HashMap<>(fieldToSQLType(fields, obj.getClass()));
        List<Pair<String, String>> fks    = new ArrayList<>();
        
        StringBuilder sb = new StringBuilder("CREATE TABLE IF NOT EXISTS ");
        sb.append("`").append(type.name()).append("`");
        sb.append(" ( ");
        for (Entry<String, Object> entry : data.entrySet())
        {
            String k = entry.getKey();
            Object v = entry.getValue();
            
            sb.append("`").append(k).append("`").append(" ");
            if (v instanceof Map)
            {
                fks.add(new Pair(k, createSubTableIfMissing(type, k, (Map<String, Object>) v)));
                sb.append("BIGINT").append(", ");
            } else
            {
                sb.append(v).append(", ");
            }
        }
        
        sb.append("`").append(COLUMN_EXPIRES_AT).append("` ").append("DATETIME");
        
        if (keys != null && !keys.isEmpty())
        {
            sb.append(", PRIMARY KEY(");
            keys.forEach(k -> sb.append("`").append(k).append("`").append(", "));
            sb.reverse().deleteCharAt(0).deleteCharAt(0).reverse();
            sb.append(")");
        }
        
        if (!fks.isEmpty())
        {
            sb.append(", ");
            fks.forEach(t -> {
                sb.append("CONSTRAINT `").append(type.name()).append("-").append(t.getValue()).append("` FOREIGN KEY (").append("`").append(t.getKey()).append("`) ");
                sb.append("REFERENCES ").append("`").append(t.getValue()).append("`").append("(indexColumn) ");
                sb.append("ON UPDATE CASCADE ON DELETE CASCADE, ");
            });
            sb.reverse().deleteCharAt(0).deleteCharAt(0).reverse();
        }
        sb.append(")");
        
        sql.getConnection().createStatement().executeUpdate(sb.toString());
    }
    
    @SuppressWarnings("unchecked")
    private String createSubTableIfMissing(URLEndpoint type, String parent, Map<String, Object> data) throws SQLException
    {
        String                     tableName = type.name() + "-" + parent;
        List<Pair<String, String>> fks       = new ArrayList<>();
        
        StringBuilder sb = new StringBuilder("CREATE TABLE IF NOT EXISTS ");
        sb.append("`").append(tableName).append("`");
        sb.append(" (");
        sb.append("indexColumn BIGINT AUTO_INCREMENT PRIMARY KEY, ");
        for (Entry<String, Object> entry : data.entrySet())
        {
            String k = entry.getKey();
            Object v = entry.getValue();
            
            sb.append("`").append(k).append("`").append(" ");
            if (v instanceof Map)
            {
                fks.add(new Pair(k, createSubTableIfMissing(type, k, (Map<String, Object>) v)));
                sb.append("BIGINT").append(", ");
            } else
            {
                sb.append(v).append(", ");
            }
        }
        
        sb.append("`").append(COLUMN_EXPIRES_AT).append("` ").append("DATETIME");
        
        if (!fks.isEmpty())
        {
            sb.append(", ");
            fks.forEach(t -> {
                sb.append("CONSTRAINT `").append(tableName).append("-").append(t.getValue()).append("` FOREIGN KEY (").append("`").append(t.getKey()).append("`) ");
                sb.append("REFERENCES ").append("`").append(t.getValue()).append("`").append("(indexColumn) ");
                sb.append("ON UPDATE CASCADE ON DELETE CASCADE, ");
            });
            
            sb.reverse().deleteCharAt(0).deleteCharAt(0).reverse();
        }
        
        sb.append(")");
        
        sql.getConnection().createStatement().executeUpdate(sb.toString());
        
        return tableName;
    }
    
    private Map<String, Object> fieldToSQLType(Field[] fields, Class parent)
    {
        Map<String, Object> data = new HashMap<>();
        for (Field field : fields)
        {
            boolean isBasic = field.getType().isPrimitive() || field.getType() == String.class;
            boolean isList  = Collection.class.isAssignableFrom(field.getType());
            boolean isEnum  = field.getType().isEnum();
            
            String name = field.getName();
            if (name.equalsIgnoreCase("serialVersionUID"))
            {
                continue;
            }
            
            if (isList)
            {
                Field[] newFields = ((Class) ((ParameterizedType) field.getGenericType()).getActualTypeArguments()[0]).getDeclaredFields();
                data.put(name, fieldToSQLType(newFields, ((Class) ((ParameterizedType) field.getGenericType()).getActualTypeArguments()[0])));
            } else if (isBasic || isEnum)
            {
                if (field.getType() == int.class)
                {
                    data.put(name, "INTEGER");
                } else if (field.getType() == long.class)
                {
                    data.put(name, "BIGINT");
                } else if (field.getType() == String.class || field.getType().isEnum())
                {
                    data.put(name, "VARCHAR(60)");
                } else if (field.getType() == boolean.class)
                {
                    data.put(name, "TINYINT(1)");
                } else if (field.getType() == float.class)
                {
                    data.put(name, "FLOAT");
                } else if (field.getType() == Map.class)
                {
                    data.put(name, "BIGINT");
                } else
                {
                    throw new RuntimeException("Unhandled type! " + field.getType());
                }
            } else
            {
                Field[] newFields = field.getType().getDeclaredFields();
                data.put(name, fieldToSQLType(newFields, field.getType()));
            }
        }
        
        return data;
    }
    
    private void storeParent(URLEndpoint type, Object storeMe, Map<String, String> indexFields)
    {
        Map<String, Object> data = new HashMap<>();
        indexFields.forEach(data::putIfAbsent);
        
        Field[] fields = storeMe.getClass().getDeclaredFields();
        data.putAll(fieldToMap(fields, storeMe));
        
        
        /* TODO
        generate intermediate tables for childlists
        sql.insert(type, data);
        */
    }
    
    private Map<String, Object> storeChildList(Field selfField, Object selfValue)
    {
        Map<String, Object> data   = new HashMap<>();
        Field[]             fields = ((Class) ((ParameterizedType) selfField.getGenericType()).getActualTypeArguments()[0]).getDeclaredFields();
        Collection<?>       coll   = (Collection) selfValue;
        
        int outdex = 0;
        for (Object obj : coll)
        {
            data.put(String.valueOf(outdex++), fieldToMap(fields, obj));
        }
        
        return data;
    }
    
    private Map<String, Object> storeChild(Field selfField, Object selfValue)
    {
        Field[] fields = selfField.getType().getDeclaredFields();
        
        Map<String, Object> data = fieldToMap(fields, selfValue);
        
        return data;
    }
    
    private Map<String, Object> fieldToMap(Field[] fields, Object parent)
    {
        Map<String, Object> data = new HashMap<>();
        try
        {
            for (Field field : fields)
            {
                field.setAccessible(true);
                
                boolean isBasic = field.getType().isPrimitive() || field.getType() == String.class;
                boolean isList  = Collection.class.isAssignableFrom(field.getType());
                boolean isEnum  = field.getType().isEnum();
                
                String name  = field.getName();
                Object value = field.get(parent);
                
                if (name.equalsIgnoreCase("serialVersionUID"))
                {
                    continue;
                }
                
                if (value == null)
                {
                    data.put(name, null);
                } else if (isList)
                {
                    data.put(name, storeChildList(field, value));
                } else if (isBasic || isEnum)
                {
                    data.put(name, value);
                } else
                {
                    data.put(name, storeChild(field, value));
                }
            }
        } catch (IllegalAccessException e)
        {
            e.printStackTrace();
        }
        
        return data;
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
        sql.clearTable(type.name());
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
                stmt.setString(1, endpoint.name());
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
        try
        {
            try (ResultSet rs = sql.getConnection().createStatement().executeQuery("SELECT COUNT(*) FROM " + type.name()))
            {
                if (rs.next())
                {
                    return rs.getLong(1);
                }
            }
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
        return -1;
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
