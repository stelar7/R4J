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
import java.util.Map.Entry;

@SuppressWarnings("rawtypes")
public class MySQLCacheProvider implements CacheProvider
{
    private static final Logger logger             = LoggerFactory.getLogger(MySQLCacheProvider.class);
    private static final String COLUMN_INSERTED_AT = "inserted_at";
    
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
        createTableForClass(MatchInfo.class);
        createTableForClass(Player.class);
        createTableForClass(PlayerTotalStats.class);
        createTableForClass(RoundResult.class);
        createTableForClass(Team.class);
        createTableForClass(Location.class);
        createTableForClass(PlayerLocation.class);
        createTableForClass(PlayerRoundStats.class);
        createTableForClass(Economy.class);
        createTableForClass(Ability.class);
        createTableForClass(Damage.class);
        createTableForClass(FinishingDamage.class);
        createTableForClass(Kill.class);
        createTableForClass(StringFacade.class);
    }
    
    private Map<String, String> getTypeMapForClass(Class<?> c)
    {
        ObjectMapper<?>     mapper   = typeMaps.computeIfAbsent(c.getCanonicalName(), k -> new ObjectMapper<>(c));
        Map<String, String> preTrans = mapper.getUntranslatedTypeMap();
        
        Map<String, String> translatedTypes = SQLDialect.MYSQL.translate(preTrans.values());
        preTrans.forEach((k, v) -> preTrans.put(k, translatedTypes.get(v)));
        
        return preTrans;
    }
    
    private void createTableForClass(Class<?> clazz) throws SQLException
    {
        StringBuilder sb = new StringBuilder();
        sb.append("CREATE TABLE IF NOT EXISTS ");
        sb.append("`").append(clazz.getName()).append("` ");
        sb.append("(");
        sb.append(getColumnsForTable(clazz));
        sb.append(")");
        sql.getConnection().createStatement().execute(sb.toString());
    }
    
    private String getColumnsForTable(Class<?> clazz)
    {
        ObjectMapper<?> mapper = typeMaps.computeIfAbsent(clazz.getCanonicalName(), k -> new ObjectMapper<>(clazz));
        
        Map<String, String>   typeMap    = getTypeMapForClass(clazz);
        Map<String, String>   extraMap   = mapper.getExtraMap();
        Map<Class<?>, String> foreignMap = mapper.getForeignMap();
        
        Set<String> columns = mapper.getFieldNames();
        columns.addAll(typeMap.keySet());
        columns.addAll(extraMap.keySet());
        StringJoiner joiner = new StringJoiner(",");
        
        for (Entry<Class<?>, String> foreignKeys : foreignMap.entrySet())
        {
            StringBuilder sb2        = new StringBuilder();
            String        columnName = foreignKeys.getKey().getSimpleName().toLowerCase() + "_" + foreignKeys.getValue();
            
            sb2.append("`").append(columnName).append("` ");
            sb2.append(getTypeMapForClass(foreignKeys.getKey()).get(foreignKeys.getValue())).append(" ");
            joiner.add(sb2.toString());
        }
        
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
    
    private String insertGetId(Class<?> clazz, Object value, Map<String, Object> extras) throws SQLException, IllegalAccessException
    {
        ObjectMapper      mapper = typeMaps.computeIfAbsent(clazz.getCanonicalName(), k -> new ObjectMapper<>(clazz));
        String            query  = generateInsertQuery(clazz, value, extras, new ArrayList<>());
        PreparedStatement stmt   = this.sql.getConnection().prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        stmt.executeUpdate();
        
        List<Pair<Class<?>, String>> foreigns = new ArrayList<>();
        
        ResultSet rs = stmt.getGeneratedKeys();
        if (rs.next())
        {
            long id = rs.getLong(1);
            foreigns.add(new Pair<>(value.getClass(), String.valueOf(id)));
        }
        
        for (Object erased : mapper.getOneToOneFields().entrySet())
        {
            Entry<String, Field> entry = (Entry<String, Field>) erased;
            
            String childId = insertGetIdWithParent(entry.getValue().getType(), entry.getValue().get(value), foreigns);
        }
        
        for (Object erased : mapper.getOneToManyFields().entrySet())
        {
            Entry<String, Field> entry          = (Entry<String, Field>) erased;
            ParameterizedType    realTypeHolder = (ParameterizedType) entry.getValue().getGenericType();
            Class<?>             realType       = ((Class<?>) realTypeHolder.getActualTypeArguments()[0]);
            
            List<?> values = (List<?>) entry.getValue().get(value);
            for (Object child : values)
            {
                String childId = insertGetIdWithParent(realType, child, foreigns);
            }
        }
        
        for (Pair<Class<?>, String> foreign : foreigns)
        {
            if (foreign.getKey().equals(clazz))
            {
                return foreign.getValue();
            }
        }
        
        return "-1";
    }
    
    private String generateInsertQuery(Class<?> clazz, Object value, Map<String, Object> extras, List<Pair<Class<?>, String>> foreigns)
    {
        ObjectMapper          mapper     = typeMaps.computeIfAbsent(clazz.getCanonicalName(), k -> new ObjectMapper<>(clazz));
        Map<String, String>   typemap    = mapper.getUntranslatedTypeMap();
        Map<Class<?>, String> foreignMap = mapper.getForeignMap();
        
        Map<String, Object> unmapped = mapper.unmap(value);
        
        StringBuilder sb = new StringBuilder();
        sb.append("INSERT INTO `").append(clazz.getName()).append("` (");
        
        StringJoiner sj = new StringJoiner(",");
        for (String key : unmapped.keySet())
        {
            sj.add("`" + key + "`");
        }
        for (String key : extras.keySet())
        {
            sj.add("`" + key + "`");
        }
        for (Pair<Class<?>, String> foreign : foreigns)
        {
            String columnName = foreign.getKey().getSimpleName().toLowerCase() + "_" + foreignMap.get(foreign.getKey());
            sj.add("`" + columnName + "`");
        }
        sb.append(sj.toString());
        
        sb.append(") VALUES (");
        
        StringJoiner sj2 = new StringJoiner(",");
        for (String key : unmapped.keySet())
        {
            sj2.add(SQLDialect.MYSQL.convertForInsert(typemap.get(key), unmapped.get(key)));
        }
        for (String key : extras.keySet())
        {
            sj2.add(SQLDialect.MYSQL.convertForInsert(typemap.get(key), extras.get(key)));
        }
        for (Pair<Class<?>, String> foreign : foreigns)
        {
            ObjectMapper foreignMapper = typeMaps.computeIfAbsent(foreign.getKey().getCanonicalName(), k -> new ObjectMapper<>(foreign.getKey()));
            String       lookupKey     = foreignMap.get(foreign.getKey());
            String       mapToType     = (String) foreignMapper.getUntranslatedTypeMap().get(lookupKey);
            sj2.add(SQLDialect.MYSQL.convertForInsert(mapToType, foreign.getValue()));
        }
        sb.append(sj2.toString());
        
        sb.append(")");
        
        return sb.toString();
    }
    
    private String insertGetIdWithParent(Class<?> clazz, Object value, List<Pair<Class<?>, String>> foreigns) throws SQLException, IllegalAccessException
    {
        ObjectMapper        mapper = typeMaps.computeIfAbsent(clazz.getCanonicalName(), k -> new ObjectMapper<>(clazz));
        Map<String, Object> unmap  = mapper.unmap(value);
        
        String            query = generateInsertQuery(clazz, value, new HashMap<>(), foreigns);
        PreparedStatement stmt  = this.sql.getConnection().prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        stmt.executeUpdate();
        
        List<Pair<Class<?>, String>> localForeigns = new ArrayList<>(foreigns);
        
        Pair returnedKey = mapper.getReturnedKey(value);
        if (returnedKey != null)
        {
            localForeigns.add(returnedKey);
        }
        
        ResultSet rs = stmt.getGeneratedKeys();
        if (rs.next())
        {
            long id = rs.getLong(1);
            localForeigns.add(new Pair<>(value.getClass(), String.valueOf(id)));
        }
        
        for (Object erased : mapper.getOneToOneFields().entrySet())
        {
            Entry<String, Field> entry = (Entry<String, Field>) erased;
            
            String childId = insertGetIdWithParent(entry.getValue().getType(), entry.getValue().get(value), localForeigns);
        }
        
        for (Object erased : mapper.getOneToManyFields().entrySet())
        {
            Entry<String, Field> entry          = (Entry<String, Field>) erased;
            ParameterizedType    realTypeHolder = (ParameterizedType) entry.getValue().getGenericType();
            Class<?>             realType       = ((Class<?>) realTypeHolder.getActualTypeArguments()[0]);
            
            List<?> values = (List<?>) entry.getValue().get(value);
            if (values == null)
            {
                continue;
            }
            
            for (Object child : values)
            {
                Object realChild = child;
                if (realType.isAssignableFrom(String.class))
                {
                    realType = StringFacade.class;
                }
                
                if (realType.isAssignableFrom(StringFacade.class))
                {
                    realChild = new StringFacade((String) child);
                }
                
                String childId = insertGetIdWithParent(realType, realChild, localForeigns);
            }
        }
        
        for (Pair<Class<?>, String> foreign : foreigns)
        {
            if (foreign.getKey().equals(clazz))
            {
                return foreign.getValue();
            }
        }
        
        return "-1";
    }
    
    @Override
    public void store(URLEndpoint type, Map<String, Object> obj)
    {
        try
        {
            if (type == URLEndpoint.V1_VAL_MATCH_BY_ID)
            {
                Object              value  = obj.get("value");
                Map<String, Object> extras = new HashMap<>(obj);
                extras.remove("value");
                insertGetId(value.getClass(), value, extras);
            }
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    
    @Override
    public Optional<?> get(URLEndpoint type, Map<String, Object> data)
    {
        try
        {
            if (type == URLEndpoint.V1_VAL_MATCH_BY_ID)
            {
                String            query     = generateExistsQuery(type.getType(), data);
                PreparedStatement stmt      = this.sql.getConnection().prepareStatement(query);
                ResultSet         resultSet = stmt.executeQuery();
                if (resultSet.next() && resultSet.getBoolean(1))
                {
                    logger.info("Entry found in cache :)");
                    System.exit(0);
                }
            }
        } catch (Exception e)
        {
            e.printStackTrace();
        }
        
        return Optional.empty();
    }
    
    private String generateExistsQuery(Class<?> clazz, Map<String, Object> data)
    {
        ObjectMapper<?>     mapper  = typeMaps.computeIfAbsent(clazz.getCanonicalName(), k -> new ObjectMapper<>(clazz));
        Map<String, String> typemap = mapper.getUntranslatedTypeMap();
        
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT EXISTS(SELECT 1 FROM `").append(clazz.getName()).append("` WHERE ");
        StringJoiner sj = new StringJoiner(" AND ");
        for (Entry<String, Object> entry : data.entrySet())
        {
            sj.add("`" + entry.getKey() + "`" + "=" + SQLDialect.MYSQL.convertForInsert(typemap.get(entry.getKey()), entry.getValue()));
        }
        sb.append(sj);
        sb.append(")");
        
        return sb.toString();
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
                stmt.setString(2, COLUMN_INSERTED_AT);
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
