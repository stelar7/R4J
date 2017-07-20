package no.stelar7.api.l4j8.tests.crawler;

import javafx.util.Pair;
import no.stelar7.api.l4j8.basic.constants.api.Platform;
import no.stelar7.api.l4j8.basic.constants.types.TeamType;
import no.stelar7.api.l4j8.basic.exceptions.APIEnumNotUpToDateException;
import no.stelar7.api.l4j8.impl.L4J8;
import no.stelar7.api.l4j8.pojo.match.*;
import no.stelar7.api.l4j8.pojo.shared.BannedChampion;
import no.stelar7.api.l4j8.tests.SecretFile;

import java.sql.*;
import java.util.*;
import java.util.Map.Entry;
import java.util.concurrent.*;
import java.util.stream.Collectors;

@SuppressWarnings("unchecked")
public class MatchHistoryCrawler
{
    
    private PreparedStatement mmSelectStatement;
    private PreparedStatement mrSelectStatement;
    private PreparedStatement mmipInsertStatement;
    private PreparedStatement mrInsertStatement;
    private PreparedStatement mmpInsertStatement;
    private PreparedStatement mrpInsertStatement;
    private PreparedStatement matchInsertStatement;
    private PreparedStatement sumAll;
    private PreparedStatement matchCheck;
    private PreparedStatement sumCheck;
    private PreparedStatement sumInsertStatement;
    private PreparedStatement pidInsertStatement;
    private PreparedStatement tsInsertStatement;
    private PreparedStatement tbInsertStatement;
    private PreparedStatement pInsertStatement;
    private PreparedStatement mmInsertStatement;
    private PreparedStatement rInsertStatement;
    private PreparedStatement psInsertStatement;
    
    // TODO: make this use the new cache system, when its finished
    
    private MatchHistoryCrawler() throws SQLException, InterruptedException
    {
        setup();
        
        try (ResultSet rs = sumAll.executeQuery())
        {
            if (rs.next())
            {
                do
                {
                    long                       accountid    = rs.getLong("accountid");
                    Platform                   platformid   = Platform.getFromCode(rs.getString("platformid")).get();
                    List<Pair<Long, Platform>> filteredRefs = getAndFilter(accountid, platformid);
                    if (filteredRefs == null)
                    {
                        continue;
                    }
                    
                    storeMatches(filteredRefs);
                } while (rs.next());
            } else
            {
                // Seed
                List<Pair<Long, Platform>> filteredRefs = getAndFilter(22401330L, Platform.EUW1);
                storeMatches(filteredRefs);
            }
        }
    }
    
    private void storeMatches(List<Pair<Long, Platform>> filteredRefs) throws InterruptedException, SQLException
    {
        System.out.println("Starting download pool...");
        ExecutorService pool = Executors.newFixedThreadPool(1);
        pool.submit(() -> insertMatches(filteredRefs));
        pool.shutdown();
        pool.awaitTermination(Long.MAX_VALUE, TimeUnit.DAYS);
        sql.getConnection().commit();
    }
    
    private List<Pair<Long, Platform>> getAndFilter(Long accountid, Platform platformid) throws SQLException
    {
        System.out.format("%nLoading Accountid: %s Platform: %s%n", accountid, platformid);
        
        List<MatchReference>       refs        = getRankedGames(accountid, platformid);
        List<Pair<Long, Platform>> apiIds      = refs.stream().map(r -> new Pair<>(r.getGameId(), r.getPlatform())).collect(Collectors.toList());
        List<Pair<Long, Platform>> databaseIds = getGamesFromDatabase(accountid, platformid);
        apiIds.removeAll(databaseIds);
        
        System.out.format("%s already stored, leaving %s games%n", databaseIds.size(), apiIds.size());
        if (apiIds.isEmpty())
        {
            return null;
        }
        
        return apiIds;
    }
    
    private void setup() throws SQLException
    {
        sql.getConnection().setAutoCommit(false);
        
        mmSelectStatement = sql.getConnection().prepareStatement(MM_SELECT);
        mrSelectStatement = sql.getConnection().prepareStatement(MR_SELECT);
        sumAll = sql.getConnection().prepareStatement(S_ALL);
        sumCheck = sql.getConnection().prepareStatement(S_CHECK);
        matchCheck = sql.getConnection().prepareStatement(MATCH_CHECK);
        mmpInsertStatement = sql.getConnection().prepareStatement(MMP_INSERT, Statement.RETURN_GENERATED_KEYS);
        mrpInsertStatement = sql.getConnection().prepareStatement(MRP_INSERT, Statement.RETURN_GENERATED_KEYS);
        mmipInsertStatement = sql.getConnection().prepareStatement(MM_INSERT, Statement.RETURN_GENERATED_KEYS);
        mrInsertStatement = sql.getConnection().prepareStatement(MR_INSERT, Statement.RETURN_GENERATED_KEYS);
        sumInsertStatement = sql.getConnection().prepareStatement(S_INSERT, Statement.RETURN_GENERATED_KEYS);
        matchInsertStatement = sql.getConnection().prepareStatement(MATCH_INSERT, Statement.RETURN_GENERATED_KEYS);
        pidInsertStatement = sql.getConnection().prepareStatement(PID_INSERT, Statement.RETURN_GENERATED_KEYS);
        tsInsertStatement = sql.getConnection().prepareStatement(TS_INSERT, Statement.RETURN_GENERATED_KEYS);
        tbInsertStatement = sql.getConnection().prepareStatement(TB_INSERT, Statement.RETURN_GENERATED_KEYS);
        pInsertStatement = sql.getConnection().prepareStatement(P_INSERT, Statement.RETURN_GENERATED_KEYS);
        mmInsertStatement = sql.getConnection().prepareStatement(M_INSERT, Statement.RETURN_GENERATED_KEYS);
        rInsertStatement = sql.getConnection().prepareStatement(R_INSERT, Statement.RETURN_GENERATED_KEYS);
        psInsertStatement = sql.getConnection().prepareStatement(PS_INSERT, Statement.RETURN_GENERATED_KEYS);
    }
    
    private void insertMatches(List<Pair<Long, Platform>> dataset)
    {
        try
        {
            for (int i = 0; i < dataset.size(); i++)
            {
                Pair<Long, Platform> pair = dataset.get(i);
                
                System.out.format("Loading match %s/%s, Id: %s Platform: %s%n", i + 1, dataset.size(), pair.getKey(), pair.getValue());
                Match match = getMatch(pair.getKey(), pair.getValue());
                if (match == null)
                {
                    System.out.format("%nMatchid: %s not found!%n", pair.getValue());
                    continue;
                }
                
                
                long matchid = insertMatch(match);
                insertTeamData(matchid, match);
                
                for (Participant participant : match.getParticipants())
                {
                    Player p             = match.getParticipantIdentityFromParticipantId(participant.getParticipantId()).getPlayer();
                    long   summonerid    = insertSummoner(p);
                    long   participantid = insertParticipant(matchid, participant);
                    insertParticipantIdentity(participantid, summonerid);
                    insertMasteries(participantid, participant);
                    insertRunes(participantid, participant);
                    insertStats(participantid, participant);
                }
            }
        } catch (SQLException | InterruptedException e)
        {
            e.printStackTrace();
        }
    }
    
    private List<MatchReference> getRankedGames(Long accountid, Platform platformid)
    {
        return api.getMatchAPI().getMatchList(platformid, accountid, null, null, null, null, null, null, null);
    }
    
    private Match getMatch(Long key, Platform value) throws InterruptedException
    {
        Match match = api.getMatchAPI().getMatch(value, key, null);
        
        if (match == null)
        {
            Thread.sleep(5000);
            match = api.getMatchAPI().getMatch(value, key, null);
            if (match == null)
            {
                return null;
            }
        }
        return match;
    }
    
    private List<Pair<Long, Platform>> getGamesFromDatabase(Long accountid, Platform platformid) throws SQLException
    {
        List<Pair<Long, Platform>> data = new ArrayList<>();
        
        matchCheck.setLong(1, accountid);
        matchCheck.setString(2, platformid.name());
        
        try (ResultSet rs = matchCheck.executeQuery())
        {
            while (rs.next())
            {
                data.add(new Pair(rs.getLong("gameid"), Platform.getFromCode(rs.getString("platformid")).get()));
            }
            
            return data;
        }
    }
    
    
    private long insertSummoner(Player summoner) throws SQLException
    {
        sumCheck.setLong(1, summoner.getCurrentAccountId());
        sumCheck.setString(2, summoner.getCurrentPlatform().name());
        try (ResultSet result = sumCheck.executeQuery())
        {
            if (result.first())
            {
                return result.getLong("id");
            }
        }
        
        sumInsertStatement.setLong(1, summoner.getCurrentAccountId());
        sumInsertStatement.setLong(2, summoner.getSummonerId());
        sumInsertStatement.setString(3, summoner.getCurrentPlatform().name());
        sumInsertStatement.setString(4, summoner.getSummonerName());
        sumInsertStatement.setLong(5, summoner.getProfileIcon());
        sumInsertStatement.executeUpdate();
        
        try (ResultSet pid = sumInsertStatement.getGeneratedKeys())
        {
            if (!pid.next())
            {
                System.out.println("ERROR");
            }
            return pid.getLong(1);
        }
    }
    
    private void insertStats(long participantid, Participant participant) throws SQLException
    {
        
        psInsertStatement.setLong(1, participantid);
        psInsertStatement.setBoolean(2, participant.getStats().isWinner());
        psInsertStatement.setLong(3, participant.getStats().getItem0());
        psInsertStatement.setLong(4, participant.getStats().getItem1());
        psInsertStatement.setLong(5, participant.getStats().getItem2());
        psInsertStatement.setLong(6, participant.getStats().getItem3());
        psInsertStatement.setLong(7, participant.getStats().getItem4());
        psInsertStatement.setLong(8, participant.getStats().getItem5());
        psInsertStatement.setLong(9, participant.getStats().getItem6());
        psInsertStatement.setLong(10, participant.getStats().getKills());
        psInsertStatement.setLong(11, participant.getStats().getDeaths());
        psInsertStatement.setLong(12, participant.getStats().getAssists());
        psInsertStatement.setLong(13, participant.getStats().getLargestKillingSpree());
        psInsertStatement.setLong(14, participant.getStats().getLargestMultiKill());
        psInsertStatement.setLong(15, participant.getStats().getKillingSprees());
        psInsertStatement.setLong(16, participant.getStats().getlongestTimeSpentLiving());
        psInsertStatement.setLong(17, participant.getStats().getDoubleKills());
        psInsertStatement.setLong(18, participant.getStats().getTripleKills());
        psInsertStatement.setLong(19, participant.getStats().getQuadraKills());
        psInsertStatement.setLong(20, participant.getStats().getPentaKills());
        psInsertStatement.setLong(21, participant.getStats().getUnrealKills());
        psInsertStatement.setLong(22, participant.getStats().getTotalDamageDealt());
        psInsertStatement.setLong(23, participant.getStats().getMagicDamageDealt());
        psInsertStatement.setLong(24, participant.getStats().getPhysicalDamageDealt());
        psInsertStatement.setLong(25, participant.getStats().getTrueDamageDealt());
        psInsertStatement.setLong(26, participant.getStats().getLargestCriticalStrike());
        psInsertStatement.setLong(27, participant.getStats().getTotalDamageDealtToChampions());
        psInsertStatement.setLong(28, participant.getStats().getMagicDamageDealtToChampions());
        psInsertStatement.setLong(29, participant.getStats().getPhysicalDamageDealtToChampions());
        psInsertStatement.setLong(30, participant.getStats().getTrueDamageDealtToChampions());
        psInsertStatement.setLong(31, participant.getStats().getTotalHeal());
        psInsertStatement.setLong(32, participant.getStats().getTotalUnitsHealed());
        psInsertStatement.setLong(33, participant.getStats().getDamageSelfMitigated());
        psInsertStatement.setLong(34, participant.getStats().getDamageDealtToObjectives());
        psInsertStatement.setLong(35, participant.getStats().getDamageDealtToTurrets());
        psInsertStatement.setLong(36, participant.getStats().getVisionScore());
        psInsertStatement.setLong(37, participant.getStats().getTimeCCingOthers());
        psInsertStatement.setLong(38, participant.getStats().getTotalDamageTaken());
        psInsertStatement.setLong(39, participant.getStats().getMagicDamageTaken());
        psInsertStatement.setLong(40, participant.getStats().getPhysicalDamageTaken());
        psInsertStatement.setLong(41, participant.getStats().getTrueDamageTaken());
        psInsertStatement.setLong(42, participant.getStats().getGoldEarned());
        psInsertStatement.setLong(43, participant.getStats().getGoldSpent());
        psInsertStatement.setLong(44, participant.getStats().getTurretKills());
        psInsertStatement.setLong(45, participant.getStats().getInhibitorKills());
        psInsertStatement.setLong(46, participant.getStats().getTotalMinionsKilled());
        psInsertStatement.setLong(47, participant.getStats().getNeutralMinionsKilled());
        psInsertStatement.setLong(48, participant.getStats().getNeutralMinionsKilledTeamJungle());
        psInsertStatement.setLong(49, participant.getStats().getNeutralMinionsKilledEnemyJungle());
        psInsertStatement.setLong(50, participant.getStats().getTotalTimeCrowdControlDealt());
        psInsertStatement.setLong(51, participant.getStats().getChampLevel());
        psInsertStatement.setLong(52, participant.getStats().getVisionWardsBoughtInGame());
        psInsertStatement.setLong(53, participant.getStats().getWardsPlaced());
        psInsertStatement.setLong(54, participant.getStats().getWardsKilled());
        psInsertStatement.setBoolean(55, participant.getStats().isFirstBloodKill());
        psInsertStatement.executeUpdate();
        
    }
    
    private void insertRunes(long participantid, Participant participant) throws SQLException
    {
        int page = getGoodRunePage(participant.getRunes());
        if (page == -1)
        {
            page = createRunePage(participant.getRunes());
        }
        
        rInsertStatement.setLong(1, participantid);
        rInsertStatement.setLong(2, page);
        rInsertStatement.executeUpdate();
    }
    
    private void insertMasteries(long participantid, Participant participant) throws SQLException
    {
        int page = getGoodMasteryPage(participant.getMasteries());
        if (page == -1)
        {
            page = createMasteryPage(participant.getMasteries());
        }
        
        mmInsertStatement.setLong(1, participantid);
        mmInsertStatement.setInt(2, page);
        mmInsertStatement.executeUpdate();
    }
    
    private void insertParticipantIdentity(long participantid, long summoner) throws SQLException
    {
        pidInsertStatement.setLong(1, participantid);
        pidInsertStatement.setLong(2, summoner);
        pidInsertStatement.executeUpdate();
    }
    
    private long insertParticipant(long matchid, Participant participant) throws SQLException
    {
        pInsertStatement.setLong(1, matchid);
        pInsertStatement.setLong(2, participant.getParticipantId());
        pInsertStatement.setLong(3, participant.getChampion().getId());
        pInsertStatement.setLong(4, participant.getSpell1().getValue());
        pInsertStatement.setLong(5, participant.getSpell2().getValue());
        pInsertStatement.setString(6, participant.getTimeline().getRole().name());
        pInsertStatement.setString(7, participant.getTimeline().getLane().name());
        pInsertStatement.executeUpdate();
        
        try (ResultSet pid = pInsertStatement.getGeneratedKeys())
        {
            if (!pid.next())
            {
                System.out.println("ERROR");
            }
            return pid.getLong(1);
        }
    }
    
    private void insertTeamData(long matchid, Match match) throws SQLException
    {
        for (TeamType type : TeamType.values())
        {
            TeamStats team = match.getTeamStats(type);
            tsInsertStatement.setLong(1, matchid);
            tsInsertStatement.setLong(2, team.getTeamType().getValue());
            tsInsertStatement.setBoolean(3, team.isFirstBlood());
            tsInsertStatement.setBoolean(4, team.isFirstTower());
            tsInsertStatement.setBoolean(5, team.isFirstInhibitor());
            tsInsertStatement.setBoolean(6, team.isFirstBaron());
            tsInsertStatement.setBoolean(7, team.isFirstDragon());
            tsInsertStatement.setBoolean(8, team.isFirstRiftHerald());
            tsInsertStatement.setLong(9, team.getTowerKills());
            tsInsertStatement.setLong(10, team.getInhibitorKills());
            tsInsertStatement.setLong(11, team.getBaronKills());
            tsInsertStatement.setLong(12, team.getDragonKills());
            tsInsertStatement.setLong(13, team.getRiftHeraldKills());
            tsInsertStatement.executeUpdate();
            
            for (BannedChampion ban : team.getBans())
            {
                tbInsertStatement.setLong(1, matchid);
                tbInsertStatement.setLong(2, team.getTeamType().getValue());
                tbInsertStatement.setLong(3, ban.getChampion().getId());
                tbInsertStatement.setLong(4, ban.getPickTurn());
                tbInsertStatement.executeUpdate();
            }
        }
    }
    
    private long insertMatch(Match match) throws SQLException
    {
        matchInsertStatement.setLong(1, match.getMatchId());
        matchInsertStatement.setString(2, match.getPlatform().name());
        matchInsertStatement.setLong(3, match.getGameQueueType().getValue());
        matchInsertStatement.setLong(4, match.getSeason().getValue());
        matchInsertStatement.setLong(5, match.getMatchDuration().getSeconds());
        matchInsertStatement.setLong(6, match.getMatchCreationDate().toInstant().toEpochMilli());
        matchInsertStatement.setString(7, match.getMatchVersion());
        matchInsertStatement.executeUpdate();
        
        try (ResultSet mid = matchInsertStatement.getGeneratedKeys())
        {
            if (!mid.next())
            {
                System.out.println("ERROR");
            }
            return mid.getLong(1);
        }
    }
    
    private int createRunePage(List<MatchRune> runes) throws SQLException
    {
        mrpInsertStatement.executeUpdate();
        try (ResultSet rs = mrpInsertStatement.getGeneratedKeys())
        {
            if (!rs.next())
            {
                System.out.println("mrp error");
            }
            int page = rs.getInt(1);
            
            List<Pair<Integer, Integer>> data = new ArrayList<>();
            for (MatchRune rune : runes)
            {
                mrInsertStatement.setInt(1, page);
                mrInsertStatement.setInt(2, rune.getRuneId());
                mrInsertStatement.setInt(3, rune.getRank());
                mrInsertStatement.executeUpdate();
                
                data.add(new Pair<>(rune.getRuneId(), rune.getRank()));
            }
            
            databaseMasteries.put(page, data);
            return page;
        }
    }
    
    
    private int createMasteryPage(List<MatchMastery> masteries) throws SQLException
    {
        mmpInsertStatement.executeUpdate();
        try (ResultSet rs = mmpInsertStatement.getGeneratedKeys())
        {
            if (!rs.next())
            {
                System.out.println("mmp error");
            }
            int page = rs.getInt(1);
            
            List<Pair<Integer, Integer>> data = new ArrayList<>();
            for (MatchMastery mastery : masteries)
            {
                mmipInsertStatement.setInt(1, page);
                mmipInsertStatement.setInt(2, mastery.getMasteryId());
                mmipInsertStatement.setInt(3, mastery.getRank());
                mmipInsertStatement.executeUpdate();
                
                data.add(new Pair<>(mastery.getMasteryId(), mastery.getRank()));
            }
            
            databaseMasteries.put(page, data);
            
            return page;
        }
    }
    
    private Map<Integer, List<Pair<Integer, Integer>>> getAllRunepages() throws SQLException
    {
        try (ResultSet rlist = mrSelectStatement.executeQuery())
        {
            Map<Integer, List<Pair<Integer, Integer>>> localRunes = new HashMap<>();
            while (rlist.next())
            {
                int                          key   = rlist.getInt("page");
                List<Pair<Integer, Integer>> rdata = localRunes.getOrDefault(key, new ArrayList<>());
                rdata.add(new Pair(rlist.getInt("runeid"), rlist.getInt("rank")));
                localRunes.put(key, rdata);
            }
            return localRunes;
        }
    }
    
    private int getGoodRunePage(List<MatchRune> participantRunes) throws SQLException
    {
        if (databaseRunes == null)
        {
            databaseRunes = getAllRunepages();
        }
        
        List<Pair<Integer, Integer>> participantList = new ArrayList();
        for (MatchRune rune : participantRunes)
        {
            participantList.add(new Pair(rune.getRuneId(), rune.getRank()));
        }
        
        for (Entry<Integer, List<Pair<Integer, Integer>>> entry : databaseRunes.entrySet())
        {
            List<Pair<Integer, Integer>> idRankList = entry.getValue();
            
            if (idRankList.containsAll(participantList))
            {
                return entry.getKey();
            }
        }
        return -1;
    }
    
    private Map<Integer, List<Pair<Integer, Integer>>> getAllMasterypages() throws SQLException
    {
        try (ResultSet mlist = mmSelectStatement.executeQuery())
        {
            Map<Integer, List<Pair<Integer, Integer>>> localMasteries = new HashMap<>();
            while (mlist.next())
            {
                int                          key   = mlist.getInt("page");
                List<Pair<Integer, Integer>> mdata = localMasteries.getOrDefault(key, new ArrayList<>());
                mdata.add(new Pair(mlist.getInt("masteryid"), mlist.getInt("rank")));
                localMasteries.put(key, mdata);
            }
            return localMasteries;
        }
    }
    
    private int getGoodMasteryPage(List<MatchMastery> participantMasteries) throws SQLException
    {
        if (databaseMasteries == null)
        {
            databaseMasteries = getAllMasterypages();
        }
        
        List<Pair<Integer, Integer>> participantList = new ArrayList();
        for (MatchMastery mastery : participantMasteries)
        {
            participantList.add(new Pair(mastery.getMasteryId(), mastery.getRank()));
        }
        
        for (Entry<Integer, List<Pair<Integer, Integer>>> entry : databaseMasteries.entrySet())
        {
            List<Pair<Integer, Integer>> idRankList = entry.getValue();
            
            if (idRankList.containsAll(participantList))
            {
                return entry.getKey();
            }
        }
        return -1;
    }
    
    private static final L4J8  api = new L4J8(SecretFile.CREDS);
    private static final MySQL sql = new MySQL("localhost", "3306", "leagueoflegends", "root", "");
    
    
    private static final String MM_SELECT = "SELECT * FROM `leagueoflegends`.`matchmasteries_in_page`";
    private static final String MR_SELECT = "SELECT * FROM `leagueoflegends`.`matchrunes_in_page`";
    
    private static final String MM_INSERT = "INSERT INTO `leagueoflegends`.`matchmasteries_in_page`(`page`,`masteryid`,`rank`)VALUES(?,?,?);";
    private static final String MR_INSERT = "INSERT INTO `leagueoflegends`.`matchrunes_in_page`(`page`,`runeid`,`rank`)VALUES(?,?,?);";
    
    private static final String MRP_INSERT = "INSERT INTO `leagueoflegends`.`matchrunepage`(`id`)VALUES(NULL);";
    private static final String MMP_INSERT = "INSERT INTO `leagueoflegends`.`matchmasterypage`(`id`)VALUES(NULL)";
    
    private static final String S_ALL = "SELECT accountid, platformid FROM `leagueoflegends`.`summoners` ORDER BY FIELD(`platformid`, 'EUW1') DESC, accountid DESC";
    
    private static final String S_CHECK = "SELECT * FROM `leagueoflegends`.`summoners` WHERE `accountid` = ? AND `platformid` = ?";
    
    private static final String S_INSERT = "INSERT INTO `leagueoflegends`.`summoners`(`accountid`,`summonerid`,`platformid`,`name`,`profileicon`)VALUES(?,?,?,?,?);";
    
    private static final String MATCH_INSERT = "INSERT INTO `leagueoflegends`.`matches`(`gameid`,`platformid`,`queueid`,`seasonid`,`duration`,`creation`,`version`)VALUES(?,?,?,?,?,?,?);";
    private static final String PID_INSERT   = "INSERT INTO `leagueoflegends`.`participantidentity`(`participant`,`summoner`)VALUES(?,?);";
    
    private static final String TS_INSERT = "INSERT INTO `leagueoflegends`.`teamstats`(`match`,`teamid`,`firstblood`,`firsttower`,`firstinhibitor`," +
                                            "`firstbaron`,`firstdragon`,`firstriftherald`,`towerkills`,`inhibitorkills`,`baronkills`,`dragonkills`,`riftheraldkills`)" +
                                            "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?);";
    
    private static final String TB_INSERT = "INSERT INTO `leagueoflegends`.`teambans`(`match`,`teamid`,`championid`,`pickturn`)VALUES(?,?,?,?);";
    private static final String P_INSERT  = "INSERT INTO `leagueoflegends`.`participants`(`match`,`participantid`,`championid`,`spell1`,`spell2`,`role`,`lane`)VALUES(?,?,?,?,?,?,?);";
    private static final String M_INSERT  = "INSERT INTO `leagueoflegends`.`matchmasteries`(`participant`,`page`)VALUES(?,?);";
    private static final String R_INSERT  = "INSERT INTO `leagueoflegends`.`matchrunes`(`participant`,`page`)VALUES(?,?);";
    
    private static final String PS_INSERT = "INSERT INTO `leagueoflegends`.`participantstats`(`participant`,`win`," +
                                            "`item0`,`item1`,`item2`,`item3`,`item4`,`item5`,`item6`,`kills`,`deaths`,`assists`,`largestkillingspree`,`largestmultikill`," +
                                            "`killingsprees`,`longesttimespentliving`,`doublekills`,`triplekills`,`quadrakills`,`pentakills`,`unrealkills`,`totaldamagedealt`," +
                                            "`magicdamagedealt`,`physicaldamagedealt`,`truedamagedealt`,`largestcriticalstrike`,`totaldamagedealttochampions`," +
                                            "`magicdamagedealttochampions`,`physicaldamagedealttochampions`,`truedamagedealttochampions`,`totalheal`,`toalunitshealed`," +
                                            "`damageselfmitigated`,`damagedealttoobjectives`,`damagedealttoturrets`,`visionscore`,`timeccingothers`,`totaldamagetaken`," +
                                            "`magicaldamagetaken`,`physicaldamagetaken`,`truedamagetaken`,`goldearned`,`goldspent`,`turretkills`,`inhibitorkills`," +
                                            "`totalminionskilled`,`neutralminionskilled`,`neutralminionskilledteamjungle`,`neutralminionskilledenemyjungle`," +
                                            "`totaltimecrowdcontrolldealt`,`champlevel`,`visionwardsboughtingame`,`wardsplaced`,`wardskilled`," +
                                            "`firstbloodkill`)" +
                                            "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);";
    
    private static final String MATCH_CHECK = "SELECT `m`.`gameid`, `m`.`platformid` FROM `matches` AS `m` JOIN `participants` AS `p` ON `m`.`id` = `p`.`match` " +
                                              "JOIN `participantidentity` AS `pi` ON `p`.`id` = `pi`.`participant` JOIN `summoners` AS `s` ON `pi`.`summoner` = `s`.`id` " +
                                              "WHERE `s`.`accountid` = ? AND `s`.`platformid` LIKE ?";
    
    private static Map<Integer, List<Pair<Integer, Integer>>> databaseRunes;
    private static Map<Integer, List<Pair<Integer, Integer>>> databaseMasteries;
    
    public static void main(String[] args) throws SQLException, InterruptedException
    {
        for (int i = 0; i < Integer.MAX_VALUE; )
        {
            try
            {
                new MatchHistoryCrawler();
            } catch (APIEnumNotUpToDateException e)
            {
                e.printStackTrace();
            }
        }
    }
    
    static class MySQL
    {
        private   String     hostname   = "";
        private   String     portnmbr   = "";
        private   String     username   = "";
        private   String     password   = "";
        private   String     database   = "";
        protected Connection connection = null;
        
        public MySQL(final String hostname, final String portnmbr, final String database, final String username, final String password)
        {
            super();
            this.hostname = hostname;
            this.portnmbr = portnmbr;
            this.database = database;
            this.username = username;
            this.password = password;
            
            try
            {
                final String url = String.format("jdbc:mysql://%s:%s/%s?useUnicode=true&characterEncoding=UTF-8&useServerPrepStmts=false&rewriteBatchedStatements=true&serverTimezone=UTC", this.hostname, this.portnmbr, this.database);
                this.connection = DriverManager.getConnection(url, this.username, this.password);
            } catch (final SQLException e)
            {
                System.out.print("Could not connect to MySQL server! ");
                System.out.println(e.getMessage());
            }
        }
        
        public Connection getConnection()
        {
            return this.connection;
        }
        
    }
    
}
