package no.stelar7.api.r4j.basic.cache.impl;

import no.stelar7.api.r4j.basic.cache.*;
import no.stelar7.api.r4j.basic.calling.DataCall;
import no.stelar7.api.r4j.basic.constants.api.URLEndpoint;
import no.stelar7.api.r4j.basic.exceptions.APIUnsupportedActionException;
import no.stelar7.api.r4j.basic.utils.*;
import no.stelar7.api.r4j.basic.utils.sql.*;
import no.stelar7.api.r4j.pojo.val.match.*;
import org.slf4j.*;

import java.lang.reflect.*;
import java.sql.*;
import java.util.*;

public class MySQLCacheProvider implements CacheProvider
{
    private static final Logger logger            = LoggerFactory.getLogger(MySQLCacheProvider.class);
    private static final String COLUMN_EXPIRES_AT = "expires_at";
    
    private long              timeToLive;
    private CacheLifetimeHint hints = CacheLifetimeHint.DEFAULTS;
    private MySQL             sql   = null;
    
    private final Map<String, ObjectMapper<?>> typeMaps = new HashMap<>();
    
    public MySQLCacheProvider(String host, int port, String username, String password)
    {
        sql = new MySQL(host, port, username, password);
        
        try
        {
            sql.open();
            try
            {
                
                sql.getConnection().createStatement().executeUpdate(String.format("CREATE DATABASE IF NOT EXISTS `%s` DEFAULT CHARACTER SET = utf8mb4 DEFAULT COLLATE = utf8mb4_unicode_ci", DataCall.getCredentials().getLoLAPIKey()));
                sql.getConnection().createStatement().executeUpdate(String.format("CREATE DATABASE IF NOT EXISTS `%s` DEFAULT CHARACTER SET = utf8mb4 DEFAULT COLLATE = utf8mb4_unicode_ci", DataCall.getCredentials().getTFTAPIKey()));
                sql.getConnection().createStatement().executeUpdate(String.format("CREATE DATABASE IF NOT EXISTS `%s` DEFAULT CHARACTER SET = utf8mb4 DEFAULT COLLATE = utf8mb4_unicode_ci", DataCall.getCredentials().getTournamentAPIKey()));
                sql.getConnection().createStatement().executeUpdate(String.format("CREATE DATABASE IF NOT EXISTS `%s` DEFAULT CHARACTER SET = utf8mb4 DEFAULT COLLATE = utf8mb4_unicode_ci", DataCall.getCredentials().getLORAPIKey()));
                sql.getConnection().createStatement().executeUpdate(String.format("CREATE DATABASE IF NOT EXISTS `%s` DEFAULT CHARACTER SET = utf8mb4 DEFAULT COLLATE = utf8mb4_unicode_ci", DataCall.getCredentials().getVALAPIKey()));
                logger.info("Created new databases!");
                createMissingTables();
            } catch (APIUnsupportedActionException e)
            {
                logger.error("You need to set a value for all the API keys to use the SQL cache.");
                System.exit(0);
            }
            
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
    }
    
    private void createMissingTables() throws SQLException
    {
        createValorantTables();
    }
    
    private void createValorantTables() throws SQLException
    {
        sql.getConnection().setCatalog(DataCall.getCredentials().getVALAPIKey());
        
        createTableForClass(Match.class);
        
        List<Pair<Class<?>, String>> foreigns = new ArrayList<>();
        foreigns.add(new Pair<>(Match.class, "id"));
        createTableForClass(MatchInfo.class);
        createTableForClassWithParents(Player.class, foreigns);
        createTableForClassWithParents(RoundResult.class, foreigns);
        createTableForClassWithParents(Team.class, foreigns);
        
        foreigns.clear();
        foreigns.add(new Pair<>(Match.class, "id"));
        foreigns.add(new Pair<>(Player.class, "puuid"));
        createTableForClassWithParents(PlayerTotalStats.class, foreigns);
    }
    
    private Map<String, String> getTypeMapForClass(Class<?> c)
    {
        try
        {
            Method typeMapMethod = null;
            for (Method method : c.getDeclaredMethods())
            {
                if (method.isAnnotationPresent(SQLTypeMap.class))
                {
                    typeMapMethod = method;
                    break;
                }
            }
            
            if (typeMapMethod == null)
            {
                throw new RuntimeException("Tried to fetch typemap for class: " + c + ", but none was present.");
            }
            
            typeMapMethod.setAccessible(true);
            Map<String, String> preTrans = (Map<String, String>) typeMapMethod.invoke(null);
            
            Map<String, String> translatedTypes = SQLDialect.MYSQL.translate(preTrans.values());
            preTrans.forEach((k, v) -> preTrans.put(k, translatedTypes.get(v)));
            
            return preTrans;
        } catch (IllegalAccessException | InvocationTargetException e)
        {
            e.printStackTrace();
        }
        
        throw new RuntimeException("Tried to fetch typemap for class: " + c + ", but none was present.");
    }
    
    private Map<String, String> getExtraMapForClass(Class<?> c)
    {
        try
        {
            Method extraMapMethod = null;
            for (Method method : c.getDeclaredMethods())
            {
                if (method.isAnnotationPresent(SQLExtraMap.class))
                {
                    extraMapMethod = method;
                    break;
                }
            }
            
            if (extraMapMethod == null)
            {
                throw new RuntimeException("Tried to fetch extramap for class: " + c + ", but none was present.");
            }
            
            extraMapMethod.setAccessible(true);
            Map<String, String> preTrans = (Map<String, String>) extraMapMethod.invoke(null);
            
            Map<String, String> translatedTypes = SQLDialect.MYSQL.translate(preTrans.values());
            preTrans.forEach((k, v) -> preTrans.put(k, translatedTypes.get(v)));
            
            return preTrans;
        } catch (IllegalAccessException | InvocationTargetException e)
        {
            e.printStackTrace();
        }
        
        throw new RuntimeException("Tried to fetch typemap for class: " + c + ", but none was present.");
    }
    
    private void createTableForClass(Class<?> clazz) throws SQLException
    {
        StringBuilder sb = new StringBuilder();
        sb.append("CREATE TABLE IF NOT EXISTS ");
        sb.append("`").append(clazz.getName()).append("` ");
        sb.append("(");
        sb.append(getColumnsForTable(clazz));
        sb.append(")");
        System.out.println(sb.toString());
        sql.getConnection().createStatement().execute(sb.toString());
    }
    
    
    private void createTableForClassWithParents(Class<?> clazz, List<Pair<Class<?>, String>> foreigns) throws SQLException
    {
        StringBuilder sb = new StringBuilder();
        sb.append("CREATE TABLE IF NOT EXISTS ");
        sb.append("`").append(clazz.getName()).append("` ");
        sb.append("(");
        StringJoiner sj = new StringJoiner(",");
        for (Pair<Class<?>, String> foreign : foreigns)
        {
            sj.add(getColumnInfoWithoutExtras(foreign.getKey(), foreign.getValue()));
        }
        sb.append(sj.toString());
        sb.append(",");
        sb.append(getColumnsForTable(clazz));
        sb.append(")");
        System.out.println(sb.toString());
        sql.getConnection().createStatement().execute(sb.toString());
    }
    
    private String getColumnsForTable(Class<?> clazz)
    {
        Map<String, String> typeMap  = getTypeMapForClass(clazz);
        Map<String, String> extraMap = getExtraMapForClass(clazz);
        ObjectMapper<?>     mapper   = typeMaps.computeIfAbsent(clazz.getCanonicalName(), k -> new ObjectMapper<>(clazz));
        StringJoiner        joiner   = new StringJoiner(",");
        Set<String>         columns  = mapper.getFieldNames();
        columns.addAll(typeMap.keySet());
        for (String fieldName : columns)
        {
            StringBuilder sb2 = new StringBuilder();
            sb2.append("`").append(fieldName).append("` ");
            sb2.append(typeMap.get(fieldName)).append(" ");
            sb2.append(extraMap.getOrDefault(fieldName, ""));
            joiner.add(sb2.toString());
        }
        
        return joiner.toString();
    }
    
    private String getColumnInfoWithoutExtras(Class<?> clazz, String column)
    {
        Map<String, String> typeMap  = getTypeMapForClass(clazz);
        Map<String, String> extraMap = getExtraMapForClass(clazz);
        ObjectMapper<?>     mapper   = typeMaps.get(clazz.getCanonicalName());
        
        StringBuilder sb2 = new StringBuilder();
        sb2.append("`").append(clazz.getSimpleName().toLowerCase()).append("_").append(column).append("` ");
        sb2.append(typeMap.get(column));
        
        return sb2.toString();
    }
    
    @Override
    public void store(URLEndpoint type, Map<String, Object> obj)
    {
        if (type == URLEndpoint.V1_VAL_MATCH_BY_ID)
        {
            Object          value  = obj.get("value");
            ObjectMapper<?> mapper = typeMaps.get(value.getClass().getCanonicalName());
            System.out.println();
        }
    }
    
    
    @Override
    public Optional<?> get(URLEndpoint type, Map<String, Object> data)
    {
        return Optional.empty();
    }
    
    
    @Override
    public void update(URLEndpoint type, Map<String, Object> obj)
    {
        // todo
    }
    
    @Override
    public void clear(URLEndpoint type, Map<String, Object> obj)
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
        return timeToLive == TTL_USE_HINTS ? hints.get(type) : timeToLive;
    }
    
    @Override
    public long getSize(URLEndpoint type, Map<String, Object> filter)
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
