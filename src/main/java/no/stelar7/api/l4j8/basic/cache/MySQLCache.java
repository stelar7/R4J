package no.stelar7.api.l4j8.basic.cache;

import java.sql.*;
import java.util.*;

public class MySQLCache extends SQLCache
{
    private static String host;
    private static int    port;
    private static String database;
    private static String username;
    private static String password;
    
    public static MySQLCache create(String host, int port, String database, String username, String password)
    {
        MySQLCache.host = host;
        MySQLCache.port = port;
        MySQLCache.database = database;
        MySQLCache.username = username;
        MySQLCache.password = password;
        return new MySQLCache();
    }
    
    @Override
    protected void setupConnection()
    {
        try
        {
            String       unicode        = "useUnicode=true";
            String       encoding       = "characterEncoding=UTF-8";
            String       serverPrep     = "useServerPrepStmts=false";
            String       rewriteBatch   = "rewriteBatchedStatements=true";
            String       serverTimezone = "serverTimezone=UTC";
            final String url            = String.format("jdbc:mysql://%s:%s/%s?%s&%s&%s&%s&%s", host, port, database, unicode, encoding, serverPrep, rewriteBatch, serverTimezone);
            this.connection = DriverManager.getConnection(url, username, password);
            this.connection.setAutoCommit(false);
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
    }
    
    @Override
    protected void createTables()
    {
        try
        {
            //<editor-fold desc="Match Insert">
            String matchTable = "CREATE TABLE IF NOT EXISTS `matches` (" +
                                "  `id` BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT," +
                                "  `gameCreation` BIGINT(20) NOT NULL," +
                                "  `gameDuration` INT(11) NOT NULL," +
                                "  `gameId` BIGINT(20) NOT NULL," +
                                "  `gameMode` VARCHAR(20) CHARACTER SET 'utf8' NOT NULL," +
                                "  `gameType` VARCHAR(20) CHARACTER SET 'utf8' NOT NULL," +
                                "  `gameVersion` VARCHAR(20) CHARACTER SET 'utf8' NOT NULL," +
                                "  `mapId` TINYINT(4) NOT NULL," +
                                "  `platformId` VARCHAR(5) CHARACTER SET 'utf8' NOT NULL," +
                                "  `queueId` SMALLINT(6) NOT NULL," +
                                "  `seasonId` TINYINT(4) NOT NULL," +
                                "  PRIMARY KEY (`id`)," +
                                "  UNIQUE INDEX `gp` (`gameid` ASC, `platformid` ASC)) " +
                                "ENGINE = InnoDB " +
                                "DEFAULT CHARACTER SET = utf8 " +
                                "COLLATE = utf8_swedish_ci;";
            
            connection.createStatement().executeUpdate(matchTable);
            
            String teamStatsTable = "CREATE TABLE IF NOT EXISTS `teamstats` ( " +
                                    "  `baronKills` TINYINT(4) NOT NULL, " +
                                    "  `dominionVictoryScore` SMALLINT(6) UNSIGNED NOT NULL, " +
                                    "  `dragonKills` TINYINT(4) NOT NULL, " +
                                    "  `firstBaron` TINYINT(1) NOT NULL, " +
                                    "  `firstBlood` TINYINT(1) NOT NULL, " +
                                    "  `firstDragon` TINYINT(1) NOT NULL, " +
                                    "  `firstInhibitor` TINYINT(1) NOT NULL, " +
                                    "  `firstRiftHerald` TINYINT(1) NOT NULL, " +
                                    "  `firstTower` TINYINT(1) NOT NULL, " +
                                    "  `inhibitorKills` TINYINT(4) NOT NULL, " +
                                    "  `matchId` BIGINT(20) UNSIGNED NOT NULL, " +
                                    "  `riftHeraldKills` TINYINT(4) NOT NULL, " +
                                    "  `teamId` TINYINT(3) UNSIGNED NOT NULL, " +
                                    "  `towerKills` TINYINT(4) NOT NULL, " +
                                    "  `vilemawKills` TINYINT(4) NOT NULL, " +
                                    "  `win` TINYINT(1) NOT NULL, " +
                                    "  PRIMARY KEY (`matchId`, `teamid`), " +
                                    "  CONSTRAINT `ts_m` " +
                                    "    FOREIGN KEY (`matchId`) " +
                                    "    REFERENCES `matches` (`id`) " +
                                    "    ON DELETE CASCADE " +
                                    "    ON UPDATE CASCADE) " +
                                    "ENGINE = InnoDB " +
                                    "DEFAULT CHARACTER SET = utf8 " +
                                    "COLLATE = utf8_swedish_ci;";
            
            connection.createStatement().executeUpdate(teamStatsTable);
            
            String teamBansTable = "CREATE TABLE IF NOT EXISTS `teambans` ( " +
                                   "  `matchId` BIGINT(20) UNSIGNED NOT NULL, " +
                                   "  `teamId` TINYINT(4) UNSIGNED NOT NULL, " +
                                   "  `championId` SMALLINT(6) NOT NULL, " +
                                   "  `pickTurn` TINYINT(4) NOT NULL, " +
                                   "  PRIMARY KEY (`matchId`, `teamId`, `pickTurn`), " +
                                   "  CONSTRAINT `tb_m` " +
                                   "    FOREIGN KEY (`matchId`) " +
                                   "    REFERENCES `matches` (`id`) " +
                                   "    ON DELETE CASCADE " +
                                   "    ON UPDATE CASCADE) " +
                                   "ENGINE = InnoDB " +
                                   "DEFAULT CHARACTER SET = utf8 " +
                                   "COLLATE = utf8_swedish_ci;";
            
            connection.createStatement().executeUpdate(teamBansTable);
            
            
            String summonersTable = "CREATE TABLE IF NOT EXISTS `summoners` ( " +
                                    "  `id` BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT, " +
                                    "  `accountId` INT(11) UNSIGNED NOT NULL, " +
                                    "  `currentAccountId` INT(11) UNSIGNED NOT NULL, " +
                                    "  `currentPlatformId` VARCHAR(5) CHARACTER SET 'utf8' NOT NULL, " +
                                    "  `matchHistoryUri` VARCHAR(45) CHARACTER SET 'utf8' NOT NULL, " +
                                    "  `platformId` VARCHAR(5) CHARACTER SET 'utf8' NOT NULL, " +
                                    "  `profileIcon` SMALLINT(6) UNSIGNED NOT NULL, " +
                                    "  `summonerId` INT(11) UNSIGNED NOT NULL, " +
                                    "  `summonerName` VARCHAR(45) CHARACTER SET 'utf8' NOT NULL, " +
                                    "  PRIMARY KEY (`id`), " +
                                    "  UNIQUE INDEX `acc_pla` (`accountId` ASC, `currentAccountId` ASC)) " +
                                    "ENGINE = InnoDB " +
                                    "DEFAULT CHARACTER SET = utf8 " +
                                    "COLLATE = utf8_swedish_ci;";
            
            connection.createStatement().executeUpdate(summonersTable);
            
            
            String participantTable = "CREATE TABLE IF NOT EXISTS `participants` ( " +
                                      "  `id` BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT, " +
                                      "  `championId` MEDIUMINT(9) NOT NULL, " +
                                      "  `highestAchievedSeasonTier` VARCHAR(15) NOT NULL, " +
                                      "  `lane` VARCHAR(15) NOT NULL, " +
                                      "  `matchId` BIGINT(20) UNSIGNED NOT NULL, " +
                                      "  `participantId` TINYINT(4) NOT NULL, " +
                                      "  `role` VARCHAR(15) NOT NULL, " +
                                      "  `spell1Id` TINYINT(4) NOT NULL, " +
                                      "  `spell2Id` TINYINT(4) NOT NULL, " +
                                      "  `teamId` TINYINT(4) UNSIGNED NOT NULL, " +
                                      "  PRIMARY KEY (`id`), " +
                                      "  INDEX `p_m_idx` (`matchId` ASC), " +
                                      "  CONSTRAINT `p_m` " +
                                      "    FOREIGN KEY (`matchId`) " +
                                      "    REFERENCES `matches` (`id`) " +
                                      "    ON DELETE CASCADE " +
                                      "    ON UPDATE CASCADE) " +
                                      "ENGINE = InnoDB " +
                                      "DEFAULT CHARACTER SET = utf8 " +
                                      "COLLATE = utf8_swedish_ci;";
            
            connection.createStatement().executeUpdate(participantTable);
            
            String participantTimelineTypeTable = "CREATE TABLE IF NOT EXISTS `participantTimelineType` ( " +
                                                  "  `id` TINYINT(4) UNSIGNED NOT NULL AUTO_INCREMENT, " +
                                                  "  `timelineType` VARCHAR(30) NOT NULL, " +
                                                  "  PRIMARY KEY (`id`,`timelineType`)) " +
                                                  "ENGINE = InnoDB " +
                                                  "DEFAULT CHARACTER SET = utf8 " +
                                                  "COLLATE = utf8_swedish_ci;";
            
            connection.createStatement().executeUpdate(participantTimelineTypeTable);
            
            String participantTimelineTimeTable = "CREATE TABLE IF NOT EXISTS `participantTimelineTime` ( " +
                                                  "  `id` TINYINT(4) UNSIGNED NOT NULL AUTO_INCREMENT, " +
                                                  "  `timelineTime` VARCHAR(15) NOT NULL, " +
                                                  "  PRIMARY KEY (`id`,`timelineTime`))  " +
                                                  "ENGINE = InnoDB " +
                                                  "DEFAULT CHARACTER SET = utf8 " +
                                                  "COLLATE = utf8_swedish_ci;";
            
            connection.createStatement().executeUpdate(participantTimelineTimeTable);
            
            String participantTimelineTable = "CREATE TABLE IF NOT EXISTS `participantTimeline` ( " +
                                              "  `participantId` BIGINT(20) UNSIGNED NOT NULL, " +
                                              "  `timelineType` TINYINT(4) UNSIGNED NOT NULL, " +
                                              "  `timelineTime` TINYINT(4) UNSIGNED NOT NULL, " +
                                              "  `timelineValue` FLOAT, " +
                                              "  PRIMARY KEY (`participantId`,`timelineType`,`timelineTime`), " +
                                              "  INDEX `p_t_idx` (`participantId` ASC), " +
                                              "  CONSTRAINT `p_t` " +
                                              "    FOREIGN KEY (`participantId`) " +
                                              "    REFERENCES `participants` (`id`) " +
                                              "    ON DELETE CASCADE " +
                                              "    ON UPDATE CASCADE, " +
                                              " CONSTRAINT `p_tt` " +
                                              "    FOREIGN KEY (`timelineType`) " +
                                              "    REFERENCES `participantTimelineType` (`id`) " +
                                              "    ON DELETE CASCADE " +
                                              "    ON UPDATE CASCADE, " +
                                              " CONSTRAINT `p_ttt` " +
                                              "    FOREIGN KEY (`timelineTime`) " +
                                              "    REFERENCES `participantTimelineTime` (`id`) " +
                                              "    ON DELETE CASCADE " +
                                              "    ON UPDATE CASCADE" +
                                              ") " +
                                              "ENGINE = InnoDB " +
                                              "DEFAULT CHARACTER SET = utf8 " +
                                              "COLLATE = utf8_swedish_ci;";
            
            connection.createStatement().executeUpdate(participantTimelineTable);
            
            
            String participantIdentityTable = "CREATE TABLE IF NOT EXISTS  `participantidentity` ( " +
                                              "  `participantId` BIGINT(20) UNSIGNED NOT NULL, " +
                                              "  `summonerId` BIGINT(20) UNSIGNED NOT NULL, " +
                                              "  PRIMARY KEY (`participantId`, `summonerId`), " +
                                              "  INDEX `pi_s_idx` (`summonerId` ASC, `participantId` ASC), " +
                                              "  CONSTRAINT `pi_p` " +
                                              "    FOREIGN KEY (`participantId`) " +
                                              "    REFERENCES  `participants` (`id`) " +
                                              "    ON DELETE CASCADE " +
                                              "    ON UPDATE CASCADE, " +
                                              "  CONSTRAINT `pi_s` " +
                                              "    FOREIGN KEY (`summonerId`) " +
                                              "    REFERENCES  `summoners` (`id`) " +
                                              "    ON DELETE CASCADE " +
                                              "    ON UPDATE CASCADE) " +
                                              "ENGINE = InnoDB " +
                                              "DEFAULT CHARACTER SET = utf8 " +
                                              "COLLATE = utf8_swedish_ci; ";
            
            connection.createStatement().executeUpdate(participantIdentityTable);
            
            String masteriesPageTable = "CREATE TABLE IF NOT EXISTS `matchmasterypage` (" +
                                        "  `id` INT(11) NOT NULL AUTO_INCREMENT," +
                                        "  PRIMARY KEY (`id`)" +
                                        ") ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=utf8 COLLATE=utf8_swedish_ci;";
            
            connection.createStatement().executeUpdate(masteriesPageTable);
            
            String masteriesTable = "CREATE TABLE IF NOT EXISTS `matchmasteries` (" +
                                    "  `participant` BIGINT(20) UNSIGNED NOT NULL," +
                                    "  `page` INT(11) NOT NULL," +
                                    "  PRIMARY KEY (`participant`,`page`)," +
                                    "  KEY `mm_mmp_idx` (`page`)," +
                                    "  CONSTRAINT `mm_mmp` FOREIGN KEY (`page`) REFERENCES `matchmasterypage` (`id`) ON DELETE CASCADE ON UPDATE CASCADE," +
                                    "  CONSTRAINT `mm_p` FOREIGN KEY (`participant`) REFERENCES `participants` (`id`) ON DELETE CASCADE ON UPDATE CASCADE" +
                                    ") ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_swedish_ci;";
            
            connection.createStatement().executeUpdate(masteriesTable);
            
            String masteriesInPageTable = "CREATE TABLE IF NOT EXISTS `matchmasteries_in_page` (" +
                                          "  `page` INT(11) NOT NULL," +
                                          "  `masteryid` SMALLINT(6) NOT NULL," +
                                          "  `rank` TINYINT(4) NOT NULL," +
                                          "  UNIQUE KEY `unique` (`masteryid`,`rank`,`page`)," +
                                          "  KEY `fk_index` (`page`)," +
                                          "  CONSTRAINT `mmip_mmp` FOREIGN KEY (`page`) REFERENCES `matchmasterypage` (`id`) ON DELETE CASCADE ON UPDATE CASCADE" +
                                          ") ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_swedish_ci;";
            
            connection.createStatement().executeUpdate(masteriesInPageTable);
            
            String runepageTable = "CREATE TABLE IF NOT EXISTS `matchrunepage` (" +
                                   "  `id` INT(11) NOT NULL AUTO_INCREMENT," +
                                   "  PRIMARY KEY (`id`)" +
                                   ") ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=utf8 COLLATE=utf8_swedish_ci;";
            
            connection.createStatement().executeUpdate(runepageTable);
            
            String runeTable = "CREATE TABLE IF NOT EXISTS `matchrunes` (" +
                               "  `participant` BIGINT(20) UNSIGNED NOT NULL," +
                               "  `page` INT(11) NOT NULL," +
                               "  PRIMARY KEY (`participant`,`page`)," +
                               "  KEY `mr_mrp_idx` (`page`)," +
                               "  CONSTRAINT `mr_mrp` FOREIGN KEY (`page`) REFERENCES `matchrunepage` (`id`) ON DELETE CASCADE ON UPDATE CASCADE," +
                               "  CONSTRAINT `mr_p` FOREIGN KEY (`participant`) REFERENCES `participants` (`id`) ON DELETE CASCADE ON UPDATE CASCADE" +
                               ") ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_swedish_ci;";
            
            
            connection.createStatement().executeUpdate(runeTable);
            
            String runesInPageTable = "CREATE TABLE IF NOT EXISTS `matchrunes_in_page` (" +
                                      "  `page` INT(11) NOT NULL," +
                                      "  `runeid` SMALLINT(6) NOT NULL," +
                                      "  `rank` TINYINT(4) NOT NULL," +
                                      "  UNIQUE KEY `unique` (`rank`,`page`,`runeid`)," +
                                      "  KEY `fk_index` (`page`)," +
                                      "  CONSTRAINT `mrip_mrp` FOREIGN KEY (`page`) REFERENCES `matchrunepage` (`id`) ON DELETE CASCADE ON UPDATE CASCADE" +
                                      ") ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_swedish_ci;";
            
            connection.createStatement().executeUpdate(runesInPageTable);
            
            String participantStats = "CREATE TABLE IF NOT EXISTS `participantstats` (" +
                                      "  `participant` BIGINT(20) UNSIGNED NOT NULL," +
                                      "  `win` TINYINT(1) DEFAULT NULL," +
                                      "  `item0` SMALLINT(6) DEFAULT NULL," +
                                      "  `item1` SMALLINT(6) DEFAULT NULL," +
                                      "  `item2` SMALLINT(6) DEFAULT NULL," +
                                      "  `item3` SMALLINT(6) DEFAULT NULL," +
                                      "  `item4` SMALLINT(6) DEFAULT NULL," +
                                      "  `item5` SMALLINT(6) DEFAULT NULL," +
                                      "  `item6` SMALLINT(6) DEFAULT NULL," +
                                      "  `kills` SMALLINT(6) DEFAULT NULL," +
                                      "  `deaths` SMALLINT(6) DEFAULT NULL," +
                                      "  `assists` SMALLINT(6) DEFAULT NULL," +
                                      "  `largestkillingspree` SMALLINT(6) DEFAULT NULL," +
                                      "  `largestmultikill` SMALLINT(6) DEFAULT NULL," +
                                      "  `killingsprees` SMALLINT(6) DEFAULT NULL," +
                                      "  `longesttimespentliving` SMALLINT(6) DEFAULT NULL," +
                                      "  `doublekills` SMALLINT(6) DEFAULT NULL," +
                                      "  `triplekills` SMALLINT(6) DEFAULT NULL," +
                                      "  `quadrakills` SMALLINT(6) DEFAULT NULL," +
                                      "  `pentakills` SMALLINT(6) DEFAULT NULL," +
                                      "  `unrealkills` SMALLINT(6) DEFAULT NULL," +
                                      "  `totaldamagedealt` INT(11) DEFAULT NULL," +
                                      "  `magicdamagedealt` INT(11) DEFAULT NULL," +
                                      "  `physicaldamagedealt` INT(11) DEFAULT NULL," +
                                      "  `truedamagedealt` INT(11) DEFAULT NULL," +
                                      "  `largestcriticalstrike` INT(11) DEFAULT NULL," +
                                      "  `totaldamagedealttochampions` INT(11) DEFAULT NULL," +
                                      "  `magicdamagedealttochampions` INT(11) DEFAULT NULL," +
                                      "  `physicaldamagedealttochampions` INT(11) DEFAULT NULL," +
                                      "  `truedamagedealttochampions` INT(11) DEFAULT NULL," +
                                      "  `totalheal` INT(11) DEFAULT NULL," +
                                      "  `totalunitshealed` TINYINT(4) DEFAULT NULL," +
                                      "  `damageselfmitigated` INT(11) DEFAULT NULL," +
                                      "  `damagedealttoobjectives` INT(11) DEFAULT NULL," +
                                      "  `damagedealttoturrets` INT(11) DEFAULT NULL," +
                                      "  `visionscore` INT(11) DEFAULT NULL," +
                                      "  `timeccingothers` INT(11) DEFAULT NULL," +
                                      "  `totaldamagetaken` INT(11) DEFAULT NULL," +
                                      "  `magicaldamagetaken` INT(11) DEFAULT NULL," +
                                      "  `physicaldamagetaken` INT(11) DEFAULT NULL," +
                                      "  `truedamagetaken` INT(11) DEFAULT NULL," +
                                      "  `goldearned` INT(11) DEFAULT NULL," +
                                      "  `goldspent` INT(11) DEFAULT NULL," +
                                      "  `turretkills` TINYINT(4) DEFAULT NULL," +
                                      "  `inhibitorkills` TINYINT(4) DEFAULT NULL," +
                                      "  `totalminionskilled` SMALLINT(6) DEFAULT NULL," +
                                      "  `neutralminionskilled` SMALLINT(6) DEFAULT NULL," +
                                      "  `neutralminionskilledteamjungle` SMALLINT(6) DEFAULT NULL," +
                                      "  `neutralminionskilledenemyjungle` SMALLINT(6) DEFAULT NULL," +
                                      "  `totalTimeCrowdControlDealt` SMALLINT(6) DEFAULT NULL," +
                                      "  `totalPlayerScore` SMALLINT(6) DEFAULT NULL," +
                                      "  `totalScoreRank` SMALLINT(6) DEFAULT NULL," +
                                      "  `champlevel` TINYINT(4) DEFAULT NULL," +
                                      "  `visionwardsboughtingame` TINYINT(4) UNSIGNED DEFAULT NULL," +
                                      "  `sightwardsboughtingame` TINYINT(4) UNSIGNED DEFAULT NULL," +
                                      "  `wardsplaced` SMALLINT(5) UNSIGNED DEFAULT NULL," +
                                      "  `wardskilled` TINYINT(4) UNSIGNED DEFAULT NULL," +
                                      "  `nodeNeutralizeAssist` TINYINT(4) UNSIGNED DEFAULT NULL," +
                                      "  `objectivePlayerScore` TINYINT(4) UNSIGNED DEFAULT NULL," +
                                      "  `combatPlayerScore` TINYINT(4) UNSIGNED DEFAULT NULL," +
                                      "  `nodeNeutralize` TINYINT(4) UNSIGNED DEFAULT NULL," +
                                      "  `nodeCapture` TINYINT(4) UNSIGNED DEFAULT NULL," +
                                      "  `nodeCaptureAssist` TINYINT(4) UNSIGNED DEFAULT NULL," +
                                      "  `teamObjective` TINYINT(4) UNSIGNED DEFAULT NULL," +
                                      "  `firstbloodkill` TINYINT(1) DEFAULT NULL," +
                                      "  `firstBloodAssist` TINYINT(1) DEFAULT NULL," +
                                      "  `firstTowerKill` TINYINT(1) DEFAULT NULL," +
                                      "  `firstTowerAssist` TINYINT(1) DEFAULT NULL," +
                                      "  `firstInhibitorKill` TINYINT(1) DEFAULT NULL," +
                                      "  `firstInhibitorAssist` TINYINT(1) DEFAULT NULL," +
                                      "  PRIMARY KEY (`participant`)," +
                                      "  CONSTRAINT `ps_p` FOREIGN KEY (`participant`) REFERENCES `participants` (`id`) ON DELETE CASCADE ON UPDATE CASCADE" +
                                      ") ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_swedish_ci;";
            
            connection.createStatement().executeUpdate(participantStats);
            
            //</editor-fold desc="Match Insert">
            
            connection.commit();
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
    }
    
    @Override
    protected String createInsertStatement(String table, Map<String, Object> data)
    {
        String statement = "INSERT INTO `%s` ( %s ) VALUES ( %s )";
        
        StringJoiner keys   = new StringJoiner("`,`", "`", "`");
        StringJoiner values = new StringJoiner(",");
        
        data.forEach((key, value) ->
                     {
                         keys.add(key);
                         if (value instanceof Boolean)
                         {
                             values.add(addSurroundingQuotes(Boolean.valueOf(value.toString()) ? "1" : "0"));
                         } else if (value instanceof String)
                         {
                             if ("win".equalsIgnoreCase(key))
                             {
                                 String kval = "Win".equalsIgnoreCase(value.toString()) ? "1" : "0";
                                 values.add(addSurroundingQuotes(kval));
                             } else
                             {
                                 values.add(addSurroundingQuotes(value.toString()));
                             }
                         } else if (value instanceof Number)
                         {
                             values.add(value.toString());
                         } else if (value == null)
                         {
                             values.add(null);
                         } else
                         {
                             values.add(addSurroundingQuotes(value.toString()));
                         }
                     });
        
        String returnData = String.format(statement, table, keys.toString(), values.toString());
        
        return returnData;
    }
    
    private String addSurroundingQuotes(String in)
    {
        return "\"" + in + "\"";
    }
    
}
