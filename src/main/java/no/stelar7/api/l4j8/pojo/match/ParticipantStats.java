package no.stelar7.api.l4j8.pojo.match;

import java.io.Serializable;
import java.util.*;
import java.util.logging.*;
import java.util.stream.Stream;

public class ParticipantStats implements Serializable
{
    private static final long serialVersionUID = -2910190875533332744L;
    
    private long    assists;
    private long    champLevel;
    private long    combatPlayerScore;
    private long    damageSelfMitigated;
    private long    damageDealtToTurrets;
    private long    damageDealtToObjectives;
    private long    deaths;
    private long    doubleKills;
    private boolean firstBloodAssist;
    private boolean firstBloodKill;
    private boolean firstInhibitorAssist;
    private boolean firstInhibitorKill;
    private boolean firstTowerAssist;
    private boolean firstTowerKill;
    private long    goldEarned;
    private long    goldSpent;
    private long    inhibitorKills;
    private long    item0;
    private long    item1;
    private long    item2;
    private long    item3;
    private long    item4;
    private long    item5;
    private long    item6;
    private long    killingSprees;
    private long    kills;
    private long    largestCriticalStrike;
    private long    largestKillingSpree;
    private long    largestMultiKill;
    private long    longestTimeSpentLiving;
    private long    magicDamageDealt;
    private long    magicDamageDealtToChampions;
    private long    magicalDamageTaken;
    private long    neutralMinionsKilled;
    private long    neutralMinionsKilledEnemyJungle;
    private long    neutralMinionsKilledTeamJungle;
    private long    nodeCapture;
    private long    nodeCaptureAssist;
    private long    nodeNeutralize;
    private long    nodeNeutralizeAssist;
    private long    objectivePlayerScore;
    private long    pentaKills;
    private long    physicalDamageDealt;
    private long    physicalDamageDealtToChampions;
    private long    physicalDamageTaken;
    private long    playerScore0;
    private long    playerScore1;
    private long    playerScore2;
    private long    playerScore3;
    private long    playerScore4;
    private long    playerScore5;
    private long    playerScore6;
    private long    playerScore7;
    private long    playerScore8;
    private long    playerScore9;
    private long    quadraKills;
    private long    sightWardsBoughtInGame;
    private long    teamObjective;
    private long    timeCCingOthers;
    private long    totalDamageDealt;
    private long    totalDamageDealtToChampions;
    private long    totalDamageTaken;
    private long    totalHeal;
    private long    totalMinionsKilled;
    private long    totalPlayerScore;
    private long    totalScoreRank;
    private long    totalTimeCrowdControlDealt;
    private long    totalUnitsHealed;
    private long    turretKills;
    private long    tripleKills;
    private long    trueDamageDealt;
    private long    trueDamageDealtToChampions;
    private long    trueDamageTaken;
    private long    unrealKills;
    private long    visionScore;
    private long    visionWardsBoughtInGame;
    private long    wardsKilled;
    private long    wardsPlaced;
    private boolean win;
    
    
    /**
     * a {@code MapType<String, String>} of all the fields in this class.
     *
     * @return the all stats
     */
    public Map<String, String> getAllStats()
    {
        final Map<String, String> map = new HashMap<>();
        Stream.of(this.getClass().getFields()).forEach(f ->
                                                       {
                                                           try
                                                           {
                                                               map.put(f.getName(), f.get(this).toString());
                                                           } catch (final IllegalAccessException e)
                                                           {
                                                               Logger.getGlobal().log(Level.WARNING, e.getMessage(), e);
                                                           }
                                                       });
        return map;
    }
    
    /**
     * Gets the assists.
     *
     * @return the assists
     */
    public long getAssists()
    {
        return this.assists;
    }
    
    /**
     * Gets the champ level.
     *
     * @return the champ level
     */
    public long getChampLevel()
    {
        return this.champLevel;
    }
    
    /**
     * Gets the combat player score.
     *
     * @return the combat player score
     */
    
    public long getCombatPlayerScore()
    {
        return this.combatPlayerScore;
    }
    
    /**
     * Gets the deaths.
     *
     * @return the deaths
     */
    public long getDeaths()
    {
        return this.deaths;
    }
    
    /**
     * Gets the double kills.
     *
     * @return the double kills
     */
    public long getDoubleKills()
    {
        return this.doubleKills;
    }
    
    /**
     * Gets the gold earned.
     *
     * @return the gold earned
     */
    public long getGoldEarned()
    {
        return this.goldEarned;
    }
    
    /**
     * Gets the gold spent.
     *
     * @return the gold spent
     */
    public long getGoldSpent()
    {
        return this.goldSpent;
    }
    
    /**
     * Gets the inhibitor kills.
     *
     * @return the inhibitor kills
     */
    public long getInhibitorKills()
    {
        return this.inhibitorKills;
    }
    
    /**
     * Gets the item0.
     *
     * @return the item0
     */
    public long getItem0()
    {
        return this.item0;
    }
    
    /**
     * Gets the item1.
     *
     * @return the item1
     */
    public long getItem1()
    {
        return this.item1;
    }
    
    /**
     * Gets the item2.
     *
     * @return the item2
     */
    public long getItem2()
    {
        return this.item2;
    }
    
    /**
     * Gets the item3.
     *
     * @return the item3
     */
    public long getItem3()
    {
        return this.item3;
    }
    
    /**
     * Gets the item4.
     *
     * @return the item4
     */
    public long getItem4()
    {
        return this.item4;
    }
    
    /**
     * Gets the item5.
     *
     * @return the item5
     */
    public long getItem5()
    {
        return this.item5;
    }
    
    /**
     * Gets the item6.
     *
     * @return the item6
     */
    public long getItem6()
    {
        return this.item6;
    }
    
    /**
     * Gets the killing sprees.
     *
     * @return the killing sprees
     */
    public long getKillingSprees()
    {
        return this.killingSprees;
    }
    
    /**
     * Gets the kills.
     *
     * @return the kills
     */
    public long getKills()
    {
        return this.kills;
    }
    
    /**
     * Gets the largest critical strike.
     *
     * @return the largest critical strike
     */
    public long getLargestCriticalStrike()
    {
        return this.largestCriticalStrike;
    }
    
    /**
     * Gets the largest killing spree.
     *
     * @return the largest killing spree
     */
    public long getLargestKillingSpree()
    {
        return this.largestKillingSpree;
    }
    
    /**
     * Gets the largest multi kill.
     *
     * @return the largest multi kill
     */
    public long getLargestMultiKill()
    {
        return this.largestMultiKill;
    }
    
    /**
     * Gets the magic damage dealt.
     *
     * @return the magic damage dealt
     */
    public long getMagicDamageDealt()
    {
        return this.magicDamageDealt;
    }
    
    /**
     * Gets the magic damage dealt to champions.
     *
     * @return the magic damage dealt to champions
     */
    public long getMagicDamageDealtToChampions()
    {
        return this.magicDamageDealtToChampions;
    }
    
    /**
     * Gets the magic damage taken.
     *
     * @return the magic damage taken
     */
    public long getMagicDamageTaken()
    {
        return this.magicalDamageTaken;
    }
    
    /**
     * Gets the minions killed.
     *
     * @return the minions killed
     */
    public long getTotalMinionsKilled()
    {
        return this.totalMinionsKilled;
    }
    
    /**
     * Gets the neutral minions killed.
     *
     * @return the neutral minions killed
     */
    public long getNeutralMinionsKilled()
    {
        return this.neutralMinionsKilled;
    }
    
    /**
     * Gets the neutral minions killed enemy jungle.
     *
     * @return the neutral minions killed enemy jungle
     */
    public long getNeutralMinionsKilledEnemyJungle()
    {
        return this.neutralMinionsKilledEnemyJungle;
    }
    
    /**
     * Gets the neutral minions killed team jungle.
     *
     * @return the neutral minions killed team jungle
     */
    public long getNeutralMinionsKilledTeamJungle()
    {
        return this.neutralMinionsKilledTeamJungle;
    }
    
    /**
     * Gets the node capture.
     *
     * @return the node capture
     */
    public long getNodeCapture()
    {
        return this.nodeCapture;
    }
    
    /**
     * Gets the node capture assist.
     *
     * @return the node capture assist
     */
    public long getNodeCaptureAssist()
    {
        return this.nodeCaptureAssist;
    }
    
    /**
     * Gets the node neutralize.
     *
     * @return the node neutralize
     */
    public long getNodeNeutralize()
    {
        return this.nodeNeutralize;
    }
    
    /**
     * Gets the node neutralize assist.
     *
     * @return the node neutralize assist
     */
    public long getNodeNeutralizeAssist()
    {
        return this.nodeNeutralizeAssist;
    }
    
    /**
     * Gets the objective player score.
     *
     * @return the objective player score
     */
    public long getObjectivePlayerScore()
    {
        return this.objectivePlayerScore;
    }
    
    /**
     * Gets the penta kills.
     *
     * @return the penta kills
     */
    public long getPentaKills()
    {
        return this.pentaKills;
    }
    
    /**
     * Gets the physical damage dealt.
     *
     * @return the physical damage dealt
     */
    public long getPhysicalDamageDealt()
    {
        return this.physicalDamageDealt;
    }
    
    /**
     * Gets the physical damage dealt to champions.
     *
     * @return the physical damage dealt to champions
     */
    public long getPhysicalDamageDealtToChampions()
    {
        return this.physicalDamageDealtToChampions;
    }
    
    /**
     * Gets the physical damage taken.
     *
     * @return the physical damage taken
     */
    public long getPhysicalDamageTaken()
    {
        return this.physicalDamageTaken;
    }
    
    /**
     * Gets the quadra kills.
     *
     * @return the quadra kills
     */
    public long getQuadraKills()
    {
        return this.quadraKills;
    }
    
    /**
     * Gets the sight wards bought in game.
     *
     * @return the sight wards bought in game
     */
    public long getSightWardsBoughtInGame()
    {
        return this.sightWardsBoughtInGame;
    }
    
    /**
     * Gets the team objective.
     *
     * @return the team objective
     */
    public long getTeamObjective()
    {
        return this.teamObjective;
    }
    
    /**
     * Gets the total damage dealt.
     *
     * @return the total damage dealt
     */
    public long getTotalDamageDealt()
    {
        return this.totalDamageDealt;
    }
    
    /**
     * Gets the total damage dealt to champions.
     *
     * @return the total damage dealt to champions
     */
    public long getTotalDamageDealtToChampions()
    {
        return this.totalDamageDealtToChampions;
    }
    
    /**
     * Gets the total damage taken.
     *
     * @return the total damage taken
     */
    public long getTotalDamageTaken()
    {
        return this.totalDamageTaken;
    }
    
    /**
     * Gets the total heal.
     *
     * @return the total heal
     */
    public long getTotalHeal()
    {
        return this.totalHeal;
    }
    
    /**
     * Gets the total player score.
     *
     * @return the total player score
     */
    public long getTotalPlayerScore()
    {
        return this.totalPlayerScore;
    }
    
    /**
     * Gets the total score rank.
     *
     * @return the total score rank
     */
    public long getTotalScoreRank()
    {
        return this.totalScoreRank;
    }
    
    /**
     * Gets the total time crowd control dealt.
     *
     * @return the total time crowd control dealt
     */
    public long getTotalTimeCrowdControlDealt()
    {
        return this.totalTimeCrowdControlDealt;
    }
    
    /**
     * Gets the total units healed.
     *
     * @return the total units healed
     */
    public long getTotalUnitsHealed()
    {
        return this.totalUnitsHealed;
    }
    
    /**
     * Gets the turret kills.
     *
     * @return the turret kills
     */
    public long getTurretKills()
    {
        return this.turretKills;
    }
    
    /**
     * Gets the triple kills.
     *
     * @return the triple kills
     */
    public long getTripleKills()
    {
        return this.tripleKills;
    }
    
    /**
     * Gets the true damage dealt.
     *
     * @return the true damage dealt
     */
    public long getTrueDamageDealt()
    {
        return this.trueDamageDealt;
    }
    
    /**
     * Gets the true damage dealt to champions.
     *
     * @return the true damage dealt to champions
     */
    public long getTrueDamageDealtToChampions()
    {
        return this.trueDamageDealtToChampions;
    }
    
    /**
     * Gets the true damage taken.
     *
     * @return the true damage taken
     */
    public long getTrueDamageTaken()
    {
        return this.trueDamageTaken;
    }
    
    /**
     * Gets the unreal kills.
     *
     * @return the unreal kills
     */
    public long getUnrealKills()
    {
        return this.unrealKills;
    }
    
    /**
     * Gets the vision wards bought in game.
     *
     * @return the vision wards bought in game
     */
    public long getVisionWardsBoughtInGame()
    {
        return this.visionWardsBoughtInGame;
    }
    
    /**
     * Gets the wards killed.
     *
     * @return the wards killed
     */
    public long getWardsKilled()
    {
        return this.wardsKilled;
    }
    
    /**
     * Gets the wards placed.
     *
     * @return the wards placed
     */
    public long getWardsPlaced()
    {
        return this.wardsPlaced;
    }
    
    
    /**
     * Id first inhibitor kill.
     *
     * @return the boolean
     */
    public boolean isFirstInhibitorKill()
    {
        return this.firstInhibitorKill;
    }
    
    /**
     * Checks if is first blood assist.
     *
     * @return the boolean
     */
    public boolean isFirstBloodAssist()
    {
        return this.firstBloodAssist;
    }
    
    /**
     * Checks if is first blood kill.
     *
     * @return the boolean
     */
    public boolean isFirstBloodKill()
    {
        return this.firstBloodKill;
    }
    
    /**
     * Checks if is first inhibitor assist.
     *
     * @return the boolean
     */
    public boolean isFirstInhibitorAssist()
    {
        return this.firstInhibitorAssist;
    }
    
    /**
     * Checks if is first tower assist.
     *
     * @return the boolean
     */
    public boolean isFirstTowerAssist()
    {
        return this.firstTowerAssist;
    }
    
    /**
     * Checks if is first tower kill.
     *
     * @return the boolean
     */
    public boolean isFirstTowerKill()
    {
        return this.firstTowerKill;
    }
    
    /**
     * Checks if is win.
     *
     * @return the boolean
     */
    public boolean isWinner()
    {
        return this.win;
    }
    
    public long getDamageSelfMitigated()
    {
        return damageSelfMitigated;
    }
    
    public long getDamageDealtToTurrets()
    {
        return damageDealtToTurrets;
    }
    
    public long getDamageDealtToObjectives()
    {
        return damageDealtToObjectives;
    }
    
    public long getlongestTimeSpentLiving()
    {
        return longestTimeSpentLiving;
    }
    
    public long getTimeCCingOthers()
    {
        return timeCCingOthers;
    }
    
    public long getVisionScore()
    {
        return visionScore;
    }
    
    public long getPlayerScore0()
    {
        return playerScore0;
    }
    
    public long getPlayerScore1()
    {
        return playerScore1;
    }
    
    public long getPlayerScore2()
    {
        return playerScore2;
    }
    
    public long getPlayerScore3()
    {
        return playerScore3;
    }
    
    public long getPlayerScore4()
    {
        return playerScore4;
    }
    
    public long getPlayerScore5()
    {
        return playerScore5;
    }
    
    public long getPlayerScore6()
    {
        return playerScore6;
    }
    
    public long getPlayerScore7()
    {
        return playerScore7;
    }
    
    public long getPlayerScore8()
    {
        return playerScore8;
    }
    
    public long getPlayerScore9()
    {
        return playerScore9;
    }
    
    @Override
    public boolean equals(Object o)
    {
        if (this == o)
        {
            return true;
        }
        if (o == null || getClass() != o.getClass())
        {
            return false;
        }
        ParticipantStats that = (ParticipantStats) o;
        return assists == that.assists &&
               champLevel == that.champLevel &&
               combatPlayerScore == that.combatPlayerScore &&
               damageSelfMitigated == that.damageSelfMitigated &&
               damageDealtToTurrets == that.damageDealtToTurrets &&
               damageDealtToObjectives == that.damageDealtToObjectives &&
               deaths == that.deaths &&
               doubleKills == that.doubleKills &&
               firstBloodAssist == that.firstBloodAssist &&
               firstBloodKill == that.firstBloodKill &&
               firstInhibitorAssist == that.firstInhibitorAssist &&
               firstInhibitorKill == that.firstInhibitorKill &&
               firstTowerAssist == that.firstTowerAssist &&
               firstTowerKill == that.firstTowerKill &&
               goldEarned == that.goldEarned &&
               goldSpent == that.goldSpent &&
               inhibitorKills == that.inhibitorKills &&
               item0 == that.item0 &&
               item1 == that.item1 &&
               item2 == that.item2 &&
               item3 == that.item3 &&
               item4 == that.item4 &&
               item5 == that.item5 &&
               item6 == that.item6 &&
               killingSprees == that.killingSprees &&
               kills == that.kills &&
               largestCriticalStrike == that.largestCriticalStrike &&
               largestKillingSpree == that.largestKillingSpree &&
               largestMultiKill == that.largestMultiKill &&
               longestTimeSpentLiving == that.longestTimeSpentLiving &&
               magicDamageDealt == that.magicDamageDealt &&
               magicDamageDealtToChampions == that.magicDamageDealtToChampions &&
               magicalDamageTaken == that.magicalDamageTaken &&
               neutralMinionsKilled == that.neutralMinionsKilled &&
               neutralMinionsKilledEnemyJungle == that.neutralMinionsKilledEnemyJungle &&
               neutralMinionsKilledTeamJungle == that.neutralMinionsKilledTeamJungle &&
               nodeCapture == that.nodeCapture &&
               nodeCaptureAssist == that.nodeCaptureAssist &&
               nodeNeutralize == that.nodeNeutralize &&
               nodeNeutralizeAssist == that.nodeNeutralizeAssist &&
               objectivePlayerScore == that.objectivePlayerScore &&
               pentaKills == that.pentaKills &&
               physicalDamageDealt == that.physicalDamageDealt &&
               physicalDamageDealtToChampions == that.physicalDamageDealtToChampions &&
               physicalDamageTaken == that.physicalDamageTaken &&
               playerScore0 == that.playerScore0 &&
               playerScore1 == that.playerScore1 &&
               playerScore2 == that.playerScore2 &&
               playerScore3 == that.playerScore3 &&
               playerScore4 == that.playerScore4 &&
               playerScore5 == that.playerScore5 &&
               playerScore6 == that.playerScore6 &&
               playerScore7 == that.playerScore7 &&
               playerScore8 == that.playerScore8 &&
               playerScore9 == that.playerScore9 &&
               quadraKills == that.quadraKills &&
               sightWardsBoughtInGame == that.sightWardsBoughtInGame &&
               teamObjective == that.teamObjective &&
               timeCCingOthers == that.timeCCingOthers &&
               totalDamageDealt == that.totalDamageDealt &&
               totalDamageDealtToChampions == that.totalDamageDealtToChampions &&
               totalDamageTaken == that.totalDamageTaken &&
               totalHeal == that.totalHeal &&
               totalMinionsKilled == that.totalMinionsKilled &&
               totalPlayerScore == that.totalPlayerScore &&
               totalScoreRank == that.totalScoreRank &&
               totalTimeCrowdControlDealt == that.totalTimeCrowdControlDealt &&
               totalUnitsHealed == that.totalUnitsHealed &&
               turretKills == that.turretKills &&
               tripleKills == that.tripleKills &&
               trueDamageDealt == that.trueDamageDealt &&
               trueDamageDealtToChampions == that.trueDamageDealtToChampions &&
               trueDamageTaken == that.trueDamageTaken &&
               unrealKills == that.unrealKills &&
               visionScore == that.visionScore &&
               visionWardsBoughtInGame == that.visionWardsBoughtInGame &&
               wardsKilled == that.wardsKilled &&
               wardsPlaced == that.wardsPlaced &&
               win == that.win;
    }
    
    @Override
    public int hashCode()
    {
        return Objects.hash(assists, champLevel, combatPlayerScore, damageSelfMitigated, damageDealtToTurrets, damageDealtToObjectives, deaths, doubleKills, firstBloodAssist, firstBloodKill, firstInhibitorAssist, firstInhibitorKill,
                            firstTowerAssist, firstTowerKill, goldEarned, goldSpent, inhibitorKills, item0, item1, item2, item3, item4, item5, item6, killingSprees, kills, largestCriticalStrike, largestKillingSpree, largestMultiKill,
                            longestTimeSpentLiving, magicDamageDealt, magicDamageDealtToChampions, magicalDamageTaken, neutralMinionsKilled, neutralMinionsKilledEnemyJungle, neutralMinionsKilledTeamJungle, nodeCapture, nodeCaptureAssist,
                            nodeNeutralize, nodeNeutralizeAssist, objectivePlayerScore, pentaKills, physicalDamageDealt, physicalDamageDealtToChampions, physicalDamageTaken, playerScore0, playerScore1, playerScore2, playerScore3,
                            playerScore4, playerScore5, playerScore6, playerScore7, playerScore8, playerScore9, quadraKills, sightWardsBoughtInGame, teamObjective, timeCCingOthers, totalDamageDealt, totalDamageDealtToChampions,
                            totalDamageTaken, totalHeal, totalMinionsKilled, totalPlayerScore, totalScoreRank, totalTimeCrowdControlDealt, totalUnitsHealed, turretKills, tripleKills, trueDamageDealt, trueDamageDealtToChampions,
                            trueDamageTaken, unrealKills, visionScore, visionWardsBoughtInGame, wardsKilled, wardsPlaced, win);
    }
    
    @Override
    public String toString()
    {
        return "ParticipantStats{" +
               "assists=" + assists +
               ", champLevel=" + champLevel +
               ", combatPlayerScore=" + combatPlayerScore +
               ", damageSelfMitigated=" + damageSelfMitigated +
               ", damageDealtToTurrets=" + damageDealtToTurrets +
               ", damageDealtToObjectives=" + damageDealtToObjectives +
               ", deaths=" + deaths +
               ", doubleKills=" + doubleKills +
               ", firstBloodAssist=" + firstBloodAssist +
               ", firstBloodKill=" + firstBloodKill +
               ", firstInhibitorAssist=" + firstInhibitorAssist +
               ", firstInhibitorKill=" + firstInhibitorKill +
               ", firstTowerAssist=" + firstTowerAssist +
               ", firstTowerKill=" + firstTowerKill +
               ", goldEarned=" + goldEarned +
               ", goldSpent=" + goldSpent +
               ", inhibitorKills=" + inhibitorKills +
               ", item0=" + item0 +
               ", item1=" + item1 +
               ", item2=" + item2 +
               ", item3=" + item3 +
               ", item4=" + item4 +
               ", item5=" + item5 +
               ", item6=" + item6 +
               ", killingSprees=" + killingSprees +
               ", kills=" + kills +
               ", largestCriticalStrike=" + largestCriticalStrike +
               ", largestKillingSpree=" + largestKillingSpree +
               ", largestMultiKill=" + largestMultiKill +
               ", longestTimeSpentLiving=" + longestTimeSpentLiving +
               ", magicDamageDealt=" + magicDamageDealt +
               ", magicDamageDealtToChampions=" + magicDamageDealtToChampions +
               ", magicalDamageTaken=" + magicalDamageTaken +
               ", neutralMinionsKilled=" + neutralMinionsKilled +
               ", neutralMinionsKilledEnemyJungle=" + neutralMinionsKilledEnemyJungle +
               ", neutralMinionsKilledTeamJungle=" + neutralMinionsKilledTeamJungle +
               ", nodeCapture=" + nodeCapture +
               ", nodeCaptureAssist=" + nodeCaptureAssist +
               ", nodeNeutralize=" + nodeNeutralize +
               ", nodeNeutralizeAssist=" + nodeNeutralizeAssist +
               ", objectivePlayerScore=" + objectivePlayerScore +
               ", pentaKills=" + pentaKills +
               ", physicalDamageDealt=" + physicalDamageDealt +
               ", physicalDamageDealtToChampions=" + physicalDamageDealtToChampions +
               ", physicalDamageTaken=" + physicalDamageTaken +
               ", playerScore0=" + playerScore0 +
               ", playerScore1=" + playerScore1 +
               ", playerScore2=" + playerScore2 +
               ", playerScore3=" + playerScore3 +
               ", playerScore4=" + playerScore4 +
               ", playerScore5=" + playerScore5 +
               ", playerScore6=" + playerScore6 +
               ", playerScore7=" + playerScore7 +
               ", playerScore8=" + playerScore8 +
               ", playerScore9=" + playerScore9 +
               ", quadraKills=" + quadraKills +
               ", sightWardsBoughtInGame=" + sightWardsBoughtInGame +
               ", teamObjective=" + teamObjective +
               ", timeCCingOthers=" + timeCCingOthers +
               ", totalDamageDealt=" + totalDamageDealt +
               ", totalDamageDealtToChampions=" + totalDamageDealtToChampions +
               ", totalDamageTaken=" + totalDamageTaken +
               ", totalHeal=" + totalHeal +
               ", totalMinionsKilled=" + totalMinionsKilled +
               ", totalPlayerScore=" + totalPlayerScore +
               ", totalScoreRank=" + totalScoreRank +
               ", totalTimeCrowdControlDealt=" + totalTimeCrowdControlDealt +
               ", totalUnitsHealed=" + totalUnitsHealed +
               ", turretKills=" + turretKills +
               ", tripleKills=" + tripleKills +
               ", trueDamageDealt=" + trueDamageDealt +
               ", trueDamageDealtToChampions=" + trueDamageDealtToChampions +
               ", trueDamageTaken=" + trueDamageTaken +
               ", unrealKills=" + unrealKills +
               ", visionScore=" + visionScore +
               ", visionWardsBoughtInGame=" + visionWardsBoughtInGame +
               ", wardsKilled=" + wardsKilled +
               ", wardsPlaced=" + wardsPlaced +
               ", win=" + win +
               '}';
    }
}
