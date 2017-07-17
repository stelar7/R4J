package no.stelar7.api.l4j8.basic.cache;

import no.stelar7.api.l4j8.basic.constants.api.URLEndpoint;
import no.stelar7.api.l4j8.basic.constants.types.TeamType;
import no.stelar7.api.l4j8.pojo.match.*;
import no.stelar7.api.l4j8.pojo.shared.BannedChampion;

import java.lang.reflect.*;
import java.sql.*;
import java.util.*;
import java.util.Map.Entry;

public abstract class SQLCache extends CacheProvider
{
    protected Connection connection = null;
    
    public SQLCache()
    {
        try
        {
            setupConnection();
            createTables();
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
    }
    
    protected abstract void setupConnection();
    
    protected abstract void createTables() throws SQLException;
    
    protected abstract String createInsertStatement(String table, Map<String, Object> values);
    
    private Map<String, Object> classFieldsToMap(Object obj)
    {
        try
        {
            Map<String, Object> values = new HashMap<>();
            
            for (Field f : obj.getClass().getDeclaredFields())
            {
                f.setAccessible(true);
                
                if (f.getType().isPrimitive())
                {
                    values.put(f.getName(), f.get(obj));
                    continue;
                }
                
                if (f.getType().isEnum())
                {
                    Enum   refl = Enum.valueOf((Class<Enum>) f.getType(), ((Enum) f.get(obj)).name());
                    Method m    = f.get(obj).getClass().getDeclaredMethod("getValue");
                    values.put(f.getName(), m.invoke(refl));
                    continue;
                }
                
                if (f.getType() == String.class)
                {
                    values.put(f.getName(), f.get(obj));
                }
            }
            
            return values;
            
        } catch (IllegalAccessException | NoSuchMethodException | InvocationTargetException e)
        {
            e.printStackTrace();
            return Collections.emptyMap();
        }
    }
    
    
    protected Map<String, Object> classMapToMap(Object obj)
    {
        try
        {
            Map<String, Object> values = new HashMap<>();
            
            for (Field f : obj.getClass().getDeclaredFields())
            {
                f.setAccessible(true);
                
                if (f.getType() == Map.class)
                {
                    values.put(f.getName(), f.get(obj));
                }
            }
            
            return values;
            
        } catch (IllegalAccessException e)
        {
            e.printStackTrace();
            return Collections.emptyMap();
        }
    }
    
    @Override
    public void store(URLEndpoint returnType, Object dtoobj)
    {
        try
        {
            if (returnType.equals(URLEndpoint.V3_MATCH))
            {
                storeMatch((Match) dtoobj);
            }
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
    }
    
    private void storeMatch(Match match) throws SQLException
    {
        long matchId = insertMatch(match);
        
        insertMatchTeamData(matchId, match);
        for (Participant participant : match.getParticipants())
        {
            Player p = match.getParticipantIdentityFromParticipantId(participant.getParticipantId()).getPlayer();
            
            long sum = insertMatchSummoner(p);
            long par = insertMatchParticipant(matchId, participant);
            insertMatchParticipantIdentity(par, sum);
            /*
            TODO:
            insertMatchMasteries(par, participant);
            insertMatchRunes(par, participant);
            insertMatchStats(par, participant);
            */
        }
    }
    
    private void insertMatchParticipantIdentity(long par, long sum) throws SQLException
    {
        Map<String, Object> data = new HashMap<>();
        data.put("participantId", par);
        data.put("summonerId", sum);
        
        String    statement = createInsertStatement("participantidentity", data);
        Statement stmt      = connection.createStatement();
        stmt.executeUpdate(statement);
    }
    
    protected long insertMatchParticipant(long matchId, Participant participant) throws SQLException
    {
        Map<String, Object> insertData = classFieldsToMap(participant);
        insertData.put("matchId", matchId);
        insertData.put("lane", participant.getTimeline().getLane());
        insertData.put("role", participant.getTimeline().getRole());
        
        String    statement = createInsertStatement("participants", insertData);
        Statement stmt      = connection.createStatement();
        stmt.executeUpdate(statement, Statement.RETURN_GENERATED_KEYS);
        try (ResultSet rs = stmt.getGeneratedKeys())
        {
            if (!rs.next())
            {
                return -1;
            }
            
            long participantid = rs.getLong(1);
            
            insertData = classMapToMap(participant.getTimeline());
            Map<String, Object> data = new HashMap<>();
            for (Entry<String, Object> entry : insertData.entrySet())
            {
                int                 typeKey  = getTimelineType(entry.getKey());
                Map<String, String> typeData = (Map) entry.getValue();
                
                for (Entry<String, String> timeData : typeData.entrySet())
                {
                    int    timeKey = getTimelineTime(timeData.getKey());
                    String value   = String.valueOf(timeData.getValue());
                    data.clear();
                    data.put("participantId", participantid);
                    data.put("timelineType", typeKey);
                    data.put("timelineTime", timeKey);
                    data.put("timelineValue", value);
                    
                    statement = createInsertStatement("participantTimeline", data);
                    stmt = connection.createStatement();
                    stmt.executeUpdate(statement);
                }
            }
            
            return participantid;
        }
    }
    
    private Map<String, Integer> timelineTime;
    private Map<String, Integer> timelineType;
    
    private Map<String, Integer> getAllTimelineTypes() throws SQLException
    {
        try (ResultSet rlist = connection.createStatement().executeQuery("SELECT * FROM participantTimelineType"))
        {
            Map<String, Integer> localData = new HashMap<>();
            while (rlist.next())
            {
                int    value = rlist.getInt("id");
                String key   = rlist.getString("timelineType");
                localData.put(key, value);
            }
            return localData;
        }
    }
    
    private Map<String, Integer> getAllTimelineTimes() throws SQLException
    {
        try (ResultSet rlist = connection.createStatement().executeQuery("SELECT * FROM participantTimelineTime"))
        {
            Map<String, Integer> localData = new HashMap<>();
            while (rlist.next())
            {
                int    value = rlist.getInt("id");
                String key   = rlist.getString("timelineTime");
                localData.put(key, value);
            }
            return localData;
        }
    }
    
    
    private int getTimelineTime(String fieldName) throws SQLException
    {
        if (timelineTime == null)
        {
            timelineTime = getAllTimelineTimes();
        }
        
        return timelineTime.computeIfAbsent(fieldName, this::createTimelineTime);
    }
    
    private Integer createTimelineTime(String s)
    {
        try
        {
            Statement stmt = connection.createStatement();
            stmt.executeUpdate("INSERT INTO participantTimelineTime (`timelineTime`) VALUES (\"" + s + "\")", Statement.RETURN_GENERATED_KEYS);
            try (ResultSet rs = stmt.getGeneratedKeys())
            {
                if (rs.next())
                {
                    return rs.getInt(1);
                }
            }
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
        return -1;
    }
    
    private int getTimelineType(String fieldName) throws SQLException
    {
        if (timelineType == null)
        {
            timelineType = getAllTimelineTypes();
        }
        
        return timelineType.computeIfAbsent(fieldName, this::createTimelineType);
    }
    
    private Integer createTimelineType(String s)
    {
        try
        {
            Statement stmt = connection.createStatement();
            stmt.executeUpdate("INSERT INTO participantTimelineType (`timelineType`) VALUES (\"" + s + "\")", Statement.RETURN_GENERATED_KEYS);
            try (ResultSet rs = stmt.getGeneratedKeys())
            {
                if (rs.next())
                {
                    return rs.getInt(1);
                }
            }
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
        return -1;
    }
    
    
    protected long insertMatchSummoner(Player player) throws SQLException
    {
        Map<String, Object> insertData = classFieldsToMap(player);
        String              statement  = createInsertStatement("summoners", insertData);
        
        Statement stmt = connection.createStatement();
        stmt.executeUpdate(statement, Statement.RETURN_GENERATED_KEYS);
        try (ResultSet rs = stmt.getGeneratedKeys())
        {
            if (!rs.next())
            {
                return -1;
            }
            
            return rs.getLong(1);
        }
        
    }
    
    
    protected void insertMatchTeamData(long matchId, Match match) throws SQLException
    {
        for (TeamType type : TeamType.values())
        {
            TeamStats           stats      = match.getTeamStats(type);
            Map<String, Object> insertData = classFieldsToMap(stats);
            insertData.put("matchId", matchId);
            
            String statement = createInsertStatement("teamstats", insertData);
            connection.createStatement().executeUpdate(statement);
            
            for (BannedChampion ban : stats.getBans())
            {
                insertData = classFieldsToMap(ban);
                insertData.put("matchId", matchId);
                insertData.put("teamId", stats.getTeamType().getValue());
                
                statement = createInsertStatement("teambans", insertData);
                connection.createStatement().executeUpdate(statement);
            }
        }
    }
    
    private long insertMatch(Match match) throws SQLException
    {
        Map<String, Object> insertData = classFieldsToMap(match);
        String              statement  = createInsertStatement("matches", insertData);
        
        Statement stmt = connection.createStatement();
        stmt.executeUpdate(statement, Statement.RETURN_GENERATED_KEYS);
        
        try (ResultSet rs = stmt.getGeneratedKeys())
        {
            if (!rs.next())
            {
                return -1;
            }
            
            return rs.getLong(1);
        }
    }
}
