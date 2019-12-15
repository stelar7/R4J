package no.stelar7.api.l4j8.basic.cache.impl;

import no.stelar7.api.l4j8.basic.cache.*;
import no.stelar7.api.l4j8.basic.calling.DataCall;
import no.stelar7.api.l4j8.basic.constants.api.*;
import no.stelar7.api.l4j8.basic.utils.sql.*;
import no.stelar7.api.l4j8.pojo.summoner.Summoner;
import org.slf4j.*;

import java.lang.reflect.*;
import java.sql.*;
import java.util.*;

public class MySQLCacheProvider implements CacheProvider
{
    
    private static final Logger logger = LoggerFactory.getLogger(MySQLCacheProvider.class);
    
    private       long              timeToLive;
    private       CacheLifetimeHint hints = CacheLifetimeHint.DEFAULTS;
    private final MySQL             sql;
    
    private static final String COLUMN_EXPIRES_AT = "expires_at";
    
    public MySQLCacheProvider(String host, int port, String username, String password)
    {
        sql = new MySQL(host, port, username, password);
        try
        {
            sql.open();
            sql.getConnection().createStatement().executeUpdate(String.format("CREATE DATABASE IF NOT EXISTS `%s` DEFAULT CHARACTER SET = utf8mb4 DEFAULT COLLATE = utf8mb4_unicode_ci", DataCall.getCredentials().getUniqueKeyCombination()));
            sql.getConnection().setCatalog(DataCall.getCredentials().getUniqueKeyCombination());
            
            logger.info("Created new database!");
            
            createMissingTables();
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
    }
    
    private Method getAnnotatedMethod(Class c)
    {
        for (Method method : c.getDeclaredMethods())
        {
            if (method.isAnnotationPresent(SQLTypeMap.class))
            {
                return method;
            }
        }
        
        throw new RuntimeException("Tried to fetch typemap for class: " + c + ", but none was present.");
    }
    
    private Map<String, String> getTypeMapForClass(Class c, Optional<?> invokee)
    {
        Method m = getAnnotatedMethod(c);
        try
        {
            Map<String, String> preTrans = (Map<String, String>) m.invoke(invokee.orElse(null));
            
            Map<String, String> translatedTypes = SQLDialect.MYSQL.translate(preTrans.values());
            preTrans.forEach((k, v) -> preTrans.put(k, translatedTypes.get(v)));
            
            return preTrans;
            
        } catch (IllegalAccessException | InvocationTargetException e)
        {
            e.printStackTrace();
        }
        
        throw new RuntimeException("Tried to fetch typemap for class: " + c + ", but none was present.");
    }
    
    private void createMissingTables() throws SQLException
    {
        createPlatformTable();
        createSummonerTable();
    }
    
    private void createPlatformTable() throws SQLException
    {
        Map<String, String> typeMap = getTypeMapForClass(Platform.class, Optional.of(Platform.UNKNOWN));
        StringBuilder       sb      = new StringBuilder();
        sb.append("CREATE TABLE IF NOT EXISTS `API_PLATFORM` ");
        sb.append("(");
        sb.append("`id` int primary key auto_increment,");
        sb.append("`name` varchar(10) not null unique");
        sb.append(")");
        sql.getConnection().createStatement().execute(sb.toString());
        
        sb = new StringBuilder();
        sb.append("CREATE TABLE IF NOT EXISTS `API_PLATFORM_KEYS` ");
        sb.append("(");
        sb.append("`id` int primary key auto_increment,");
        sb.append("`platform_key` int,");
        sb.append("`key_name` varchar(10) not null unique");
        sb.append(")");
        sql.getConnection().createStatement().execute(sb.toString());
        
        
        //TODO replace this with a select->insert instead of a insert-ignore, since that increments the AI key...
        PreparedStatement primary   = sql.getConnection().prepareStatement("INSERT IGNORE INTO `API_PLATFORM` VALUES (null, ?)", Statement.RETURN_GENERATED_KEYS);
        PreparedStatement secondary = sql.getConnection().prepareStatement("INSERT IGNORE INTO `API_PLATFORM_KEYS` VALUES (null, ?, ?)", Statement.RETURN_GENERATED_KEYS);
        for (Platform value : Platform.values())
        {
            primary.setString(1, value.name());
            primary.execute();
            
            ResultSet keys = primary.getGeneratedKeys();
            int       id;
            if (keys.next())
            {
                id = keys.getInt(1);
            } else
            {
                PreparedStatement fetch = sql.getConnection().prepareStatement("SELECT `id` from `API_PLATFORM` WHERE `name` = ?");
                fetch.setString(1, value.name());
                ResultSet rs = fetch.executeQuery();
                rs.next();
                id = rs.getInt("id");
            }
            
            for (String key : value.getKeys())
            {
                secondary.setInt(1, id);
                secondary.setString(2, key);
                secondary.execute();
            }
        }
    }
    
    private void createSummonerTable()
    {
        Map<String, String> typeMap = getTypeMapForClass(Summoner.class, Optional.empty());
        System.out.println(typeMap);
    }
    
    @Override
    public void store(URLEndpoint type, Object... obj)
    {
        // todo
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
        return timeToLive == TTL_USE_HINTS ? hints.get(type) : timeToLive;
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
