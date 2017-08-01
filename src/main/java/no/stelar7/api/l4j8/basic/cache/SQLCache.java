package no.stelar7.api.l4j8.basic.cache;

import javafx.util.Pair;
import no.stelar7.api.l4j8.basic.constants.api.*;
import no.stelar7.api.l4j8.basic.constants.types.TeamType;
import no.stelar7.api.l4j8.pojo.match.*;
import no.stelar7.api.l4j8.pojo.shared.BannedChampion;

import java.lang.reflect.*;
import java.sql.*;
import java.util.*;
import java.util.Map.Entry;

@SuppressWarnings("unchecked")
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
    
    private Map<String, Integer> getIdAndField(String table, String field) throws SQLException
    {
        try (ResultSet rlist = connection.createStatement().executeQuery("SELECT * FROM " + table))
        {
            Map<String, Integer> localData = new HashMap<>();
            while (rlist.next())
            {
                int    value = rlist.getInt("id");
                String key   = rlist.getString(field);
                localData.put(key, value);
            }
            return localData;
        }
    }
    
    private Map<Integer, List<Pair<Integer, Integer>>> getIdAndList(String table, String mapKey, String colA, String colB) throws SQLException
    {
        Map<Integer, List<Pair<Integer, Integer>>> localData = new HashMap<>();
        
        try (ResultSet mlist = connection.createStatement().executeQuery("select * from " + table))
        {
            while (mlist.next())
            {
                int                          key   = mlist.getInt(mapKey);
                List<Pair<Integer, Integer>> mdata = localData.getOrDefault(key, new ArrayList<>());
                mdata.add(new Pair(mlist.getInt(colA), mlist.getInt(colB)));
                localData.put(key, mdata);
            }
        }
        
        return localData;
    }
    
    private Integer insertAndReturnKey(String table, Map<String, Object> data)
    {
        try
        {
            Statement stmt      = connection.createStatement();
            String    statement = createInsertStatement(table, data);
            
            stmt.executeUpdate(statement, Statement.RETURN_GENERATED_KEYS);
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
    
    
    @SuppressWarnings("unchecked")
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
    
    @Override
    public Optional<Object> get(URLEndpoint returnType, Object... data)
    {
        if (returnType.equals(URLEndpoint.V3_MATCH))
        {
            if (data.length == 3)
            {
                return getMatch((Platform) data[0], (Long) data[1], (Long) data[2]);
            } else
            {
                return getMatch((Platform) data[0], (Long) data[1], null);
            }
        }
        
        return Optional.empty();
    }
    
    
    //<editor-fold desc="Match">
    private Optional<Object> getMatch(Platform server, long matchid, Long foraccount)
    {
        return Optional.empty();
    }
    
    private boolean hasMatch(Platform server, long matchid)
    {
        boolean found = false;
        
        try (ResultSet rs = connection.createStatement().executeQuery("select * from matches where `gameid` = " + matchid + " and `platformid` like \"" + server.toString() + "\""))
        {
            found = rs.next();
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
        
        return found;
    }
    
    private Map<String, Integer>                       databaseTimelineTime;
    private Map<String, Integer>                       databaseTimelineType;
    private Map<Integer, List<Pair<Integer, Integer>>> databaseRunes;
    private Map<Integer, List<Pair<Integer, Integer>>> databaseMasteries;
    
    private void storeMatch(Match match) throws SQLException
    {
        if (hasMatch(match.getPlatform(), match.getMatchId()))
        {
            return;
        }
        
        long matchId = insertMatch(match);
        insertMatchTeamData(matchId, match);
        for (Participant participant : match.getParticipants())
        {
            Player p = match.getParticipantIdentityFromParticipantId(participant.getParticipantId()).getPlayer();
            
            long sum = insertMatchSummoner(p);
            long par = insertMatchParticipant(matchId, participant);
            insertMatchParticipantIdentity(par, sum);
            insertMatchMasteries(par, participant);
            insertMatchRunes(par, participant);
            insertMatchStats(par, participant);
        }
        connection.commit();
    }
    
    protected void insertMatchStats(long par, Participant participant) throws SQLException
    {
        Map<String, Object> insertData = classFieldsToMap(participant.getStats());
        insertData.put("participant", par);
        connection.createStatement().executeUpdate(createInsertStatement("participantstats", insertData));
    }
    
    
    private int getPageMatch(List<?> data) throws SQLException
    {
        if (data.isEmpty())
        {
            return -1;
        }
        
        Map<Integer, List<Pair<Integer, Integer>>> mapToUse;
        
        if (data.get(0) instanceof MatchMastery)
        {
            if (databaseMasteries == null)
            {
                databaseMasteries = getIdAndList("matchmasteries_in_page", "page", "masteryid", "rank");
            }
            
            mapToUse = databaseMasteries;
        } else
        {
            
            if (databaseRunes == null)
            {
                databaseRunes = getIdAndList("matchrunes_in_page", "page", "runeid", "rank");
            }
            
            mapToUse = databaseRunes;
        }
        
        
        List<Pair<Integer, Integer>> participantList = new ArrayList();
        for (Object ob : data)
        {
            if (ob instanceof MatchMastery)
            {
                participantList.add(new Pair(((MatchMastery) ob).getMasteryId(), ((MatchMastery) ob).getRank()));
            }
            
            if (ob instanceof MatchRune)
            {
                participantList.add(new Pair(((MatchRune) ob).getRuneId(), ((MatchRune) ob).getRank()));
            }
        }
        
        for (Entry<Integer, List<Pair<Integer, Integer>>> entry : mapToUse.entrySet())
        {
            List<Pair<Integer, Integer>> idRankList = entry.getValue();
            
            if (idRankList.containsAll(participantList))
            {
                return entry.getKey();
            }
        }
        return -1;
    }
    
    private int createPage(List<?> data, String table, String table2, String field) throws SQLException
    {
        Map<String, Object> mapdata = new HashMap<>();
        mapdata.put("id", null);
        int page = insertAndReturnKey(table, mapdata);
        
        List<Pair<Integer, Integer>> container = new ArrayList<>();
        for (Object ob : data)
        {
            int id   = -1;
            int rank = -1;
            
            if (ob instanceof MatchMastery)
            {
                id = ((MatchMastery) ob).getMasteryId();
                rank = ((MatchMastery) ob).getRank();
            }
            if (ob instanceof MatchRune)
            {
                id = ((MatchRune) ob).getRuneId();
                rank = ((MatchRune) ob).getRank();
            }
            
            
            Map<String, Object> insData = new HashMap<>();
            insData.put("page", page);
            insData.put(field, id);
            insData.put("rank", rank);
            
            String statement = createInsertStatement(table2, insData);
            connection.createStatement().executeUpdate(statement);
            
            container.add(new Pair<>(id, rank));
        }
        databaseMasteries.put(page, container);
        
        return page;
    }
    
    private void insertMatchMasteries(long participantid, Participant participant) throws SQLException
    {
        int page = getPageMatch(participant.getMasteries());
        if (page == -1)
        {
            page = createPage(participant.getMasteries(), "matchmasterypage", "matchmasteries_in_page", "masteryid");
        }
        
        Map<String, Object> data = new HashMap<>();
        data.put("participant", participantid);
        data.put("page", page);
        connection.createStatement().executeUpdate(createInsertStatement("matchmasteries", data));
    }
    
    
    private void insertMatchRunes(long participantid, Participant participant) throws SQLException
    {
        int page = getPageMatch(participant.getRunes());
        if (page == -1)
        {
            page = createPage(participant.getRunes(), "matchrunepage", "matchrunes_in_page", "runeid");
        }
        
        Map<String, Object> data = new HashMap<>();
        data.put("participant", participantid);
        data.put("page", page);
        connection.createStatement().executeUpdate(createInsertStatement("matchrunes", data));
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
                Map<String, String> typeData = (Map<String, String>) entry.getValue();
                
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
    
    private int getTimelineTime(String fieldName) throws SQLException
    {
        if (databaseTimelineTime == null)
        {
            databaseTimelineTime = getIdAndField("participantTimelineTime", "timelineTime");
        }
        
        Map<String, Object> data = new HashMap<>();
        data.put("timelineTime", fieldName);
        
        return databaseTimelineTime.computeIfAbsent(fieldName, f -> insertAndReturnKey("participantTimelineTime", data));
    }
    
    private int getTimelineType(String fieldName) throws SQLException
    {
        if (databaseTimelineType == null)
        {
            databaseTimelineType = getIdAndField("participantTimelineType", "timelineType");
        }
        
        
        Map<String, Object> data = new HashMap<>();
        data.put("timelineType", fieldName);
        
        return databaseTimelineType.computeIfAbsent(fieldName, f -> insertAndReturnKey("participantTimelineType", data));
    }
    
    protected long insertMatchSummoner(Player player) throws SQLException
    {
        Map<String, Object> insertData = classFieldsToMap(player);
        return insertAndReturnKey("summoners", insertData);
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
        return insertAndReturnKey("matches", insertData);
    }
    //</editor-fold>
}
