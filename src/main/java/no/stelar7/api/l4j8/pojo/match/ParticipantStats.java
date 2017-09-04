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
        
        if (assists != that.assists)
        {
            return false;
        }
        if (champLevel != that.champLevel)
        {
            return false;
        }
        if (combatPlayerScore != that.combatPlayerScore)
        {
            return false;
        }
        if (damageSelfMitigated != that.damageSelfMitigated)
        {
            return false;
        }
        if (damageDealtToTurrets != that.damageDealtToTurrets)
        {
            return false;
        }
        if (damageDealtToObjectives != that.damageDealtToObjectives)
        {
            return false;
        }
        if (deaths != that.deaths)
        {
            return false;
        }
        if (doubleKills != that.doubleKills)
        {
            return false;
        }
        if (firstBloodAssist != that.firstBloodAssist)
        {
            return false;
        }
        if (firstBloodKill != that.firstBloodKill)
        {
            return false;
        }
        if (firstInhibitorAssist != that.firstInhibitorAssist)
        {
            return false;
        }
        if (firstInhibitorKill != that.firstInhibitorKill)
        {
            return false;
        }
        if (firstTowerAssist != that.firstTowerAssist)
        {
            return false;
        }
        if (firstTowerKill != that.firstTowerKill)
        {
            return false;
        }
        if (goldEarned != that.goldEarned)
        {
            return false;
        }
        if (goldSpent != that.goldSpent)
        {
            return false;
        }
        if (inhibitorKills != that.inhibitorKills)
        {
            return false;
        }
        if (item0 != that.item0)
        {
            return false;
        }
        if (item1 != that.item1)
        {
            return false;
        }
        if (item2 != that.item2)
        {
            return false;
        }
        if (item3 != that.item3)
        {
            return false;
        }
        if (item4 != that.item4)
        {
            return false;
        }
        if (item5 != that.item5)
        {
            return false;
        }
        if (item6 != that.item6)
        {
            return false;
        }
        if (killingSprees != that.killingSprees)
        {
            return false;
        }
        if (kills != that.kills)
        {
            return false;
        }
        if (largestCriticalStrike != that.largestCriticalStrike)
        {
            return false;
        }
        if (largestKillingSpree != that.largestKillingSpree)
        {
            return false;
        }
        if (largestMultiKill != that.largestMultiKill)
        {
            return false;
        }
        if (longestTimeSpentLiving != that.longestTimeSpentLiving)
        {
            return false;
        }
        if (magicDamageDealt != that.magicDamageDealt)
        {
            return false;
        }
        if (magicDamageDealtToChampions != that.magicDamageDealtToChampions)
        {
            return false;
        }
        if (magicalDamageTaken != that.magicalDamageTaken)
        {
            return false;
        }
        if (neutralMinionsKilled != that.neutralMinionsKilled)
        {
            return false;
        }
        if (neutralMinionsKilledEnemyJungle != that.neutralMinionsKilledEnemyJungle)
        {
            return false;
        }
        if (neutralMinionsKilledTeamJungle != that.neutralMinionsKilledTeamJungle)
        {
            return false;
        }
        if (nodeCapture != that.nodeCapture)
        {
            return false;
        }
        if (nodeCaptureAssist != that.nodeCaptureAssist)
        {
            return false;
        }
        if (nodeNeutralize != that.nodeNeutralize)
        {
            return false;
        }
        if (nodeNeutralizeAssist != that.nodeNeutralizeAssist)
        {
            return false;
        }
        if (objectivePlayerScore != that.objectivePlayerScore)
        {
            return false;
        }
        if (pentaKills != that.pentaKills)
        {
            return false;
        }
        if (physicalDamageDealt != that.physicalDamageDealt)
        {
            return false;
        }
        if (physicalDamageDealtToChampions != that.physicalDamageDealtToChampions)
        {
            return false;
        }
        if (physicalDamageTaken != that.physicalDamageTaken)
        {
            return false;
        }
        if (quadraKills != that.quadraKills)
        {
            return false;
        }
        if (sightWardsBoughtInGame != that.sightWardsBoughtInGame)
        {
            return false;
        }
        if (teamObjective != that.teamObjective)
        {
            return false;
        }
        if (timeCCingOthers != that.timeCCingOthers)
        {
            return false;
        }
        if (totalDamageDealt != that.totalDamageDealt)
        {
            return false;
        }
        if (totalDamageDealtToChampions != that.totalDamageDealtToChampions)
        {
            return false;
        }
        if (totalDamageTaken != that.totalDamageTaken)
        {
            return false;
        }
        if (totalHeal != that.totalHeal)
        {
            return false;
        }
        if (totalMinionsKilled != that.totalMinionsKilled)
        {
            return false;
        }
        if (totalPlayerScore != that.totalPlayerScore)
        {
            return false;
        }
        if (totalScoreRank != that.totalScoreRank)
        {
            return false;
        }
        if (totalTimeCrowdControlDealt != that.totalTimeCrowdControlDealt)
        {
            return false;
        }
        if (totalUnitsHealed != that.totalUnitsHealed)
        {
            return false;
        }
        if (turretKills != that.turretKills)
        {
            return false;
        }
        if (tripleKills != that.tripleKills)
        {
            return false;
        }
        if (trueDamageDealt != that.trueDamageDealt)
        {
            return false;
        }
        if (trueDamageDealtToChampions != that.trueDamageDealtToChampions)
        {
            return false;
        }
        if (trueDamageTaken != that.trueDamageTaken)
        {
            return false;
        }
        if (unrealKills != that.unrealKills)
        {
            return false;
        }
        if (visionScore != that.visionScore)
        {
            return false;
        }
        if (visionWardsBoughtInGame != that.visionWardsBoughtInGame)
        {
            return false;
        }
        if (wardsKilled != that.wardsKilled)
        {
            return false;
        }
        if (wardsPlaced != that.wardsPlaced)
        {
            return false;
        }
        return win == that.win;
    }
    
    @Override
    public int hashCode()
    {
        int result = (int) (assists ^ (assists >>> 32));
        result = 31 * result + (int) (champLevel ^ (champLevel >>> 32));
        result = 31 * result + (int) (combatPlayerScore ^ (combatPlayerScore >>> 32));
        result = 31 * result + (int) (damageSelfMitigated ^ (damageSelfMitigated >>> 32));
        result = 31 * result + (int) (damageDealtToTurrets ^ (damageDealtToTurrets >>> 32));
        result = 31 * result + (int) (damageDealtToObjectives ^ (damageDealtToObjectives >>> 32));
        result = 31 * result + (int) (deaths ^ (deaths >>> 32));
        result = 31 * result + (int) (doubleKills ^ (doubleKills >>> 32));
        result = 31 * result + (firstBloodAssist ? 1 : 0);
        result = 31 * result + (firstBloodKill ? 1 : 0);
        result = 31 * result + (firstInhibitorAssist ? 1 : 0);
        result = 31 * result + (firstInhibitorKill ? 1 : 0);
        result = 31 * result + (firstTowerAssist ? 1 : 0);
        result = 31 * result + (firstTowerKill ? 1 : 0);
        result = 31 * result + (int) (goldEarned ^ (goldEarned >>> 32));
        result = 31 * result + (int) (goldSpent ^ (goldSpent >>> 32));
        result = 31 * result + (int) (inhibitorKills ^ (inhibitorKills >>> 32));
        result = 31 * result + (int) (item0 ^ (item0 >>> 32));
        result = 31 * result + (int) (item1 ^ (item1 >>> 32));
        result = 31 * result + (int) (item2 ^ (item2 >>> 32));
        result = 31 * result + (int) (item3 ^ (item3 >>> 32));
        result = 31 * result + (int) (item4 ^ (item4 >>> 32));
        result = 31 * result + (int) (item5 ^ (item5 >>> 32));
        result = 31 * result + (int) (item6 ^ (item6 >>> 32));
        result = 31 * result + (int) (killingSprees ^ (killingSprees >>> 32));
        result = 31 * result + (int) (kills ^ (kills >>> 32));
        result = 31 * result + (int) (largestCriticalStrike ^ (largestCriticalStrike >>> 32));
        result = 31 * result + (int) (largestKillingSpree ^ (largestKillingSpree >>> 32));
        result = 31 * result + (int) (largestMultiKill ^ (largestMultiKill >>> 32));
        result = 31 * result + (int) (longestTimeSpentLiving ^ (longestTimeSpentLiving >>> 32));
        result = 31 * result + (int) (magicDamageDealt ^ (magicDamageDealt >>> 32));
        result = 31 * result + (int) (magicDamageDealtToChampions ^ (magicDamageDealtToChampions >>> 32));
        result = 31 * result + (int) (magicalDamageTaken ^ (magicalDamageTaken >>> 32));
        result = 31 * result + (int) (neutralMinionsKilled ^ (neutralMinionsKilled >>> 32));
        result = 31 * result + (int) (neutralMinionsKilledEnemyJungle ^ (neutralMinionsKilledEnemyJungle >>> 32));
        result = 31 * result + (int) (neutralMinionsKilledTeamJungle ^ (neutralMinionsKilledTeamJungle >>> 32));
        result = 31 * result + (int) (nodeCapture ^ (nodeCapture >>> 32));
        result = 31 * result + (int) (nodeCaptureAssist ^ (nodeCaptureAssist >>> 32));
        result = 31 * result + (int) (nodeNeutralize ^ (nodeNeutralize >>> 32));
        result = 31 * result + (int) (nodeNeutralizeAssist ^ (nodeNeutralizeAssist >>> 32));
        result = 31 * result + (int) (objectivePlayerScore ^ (objectivePlayerScore >>> 32));
        result = 31 * result + (int) (pentaKills ^ (pentaKills >>> 32));
        result = 31 * result + (int) (physicalDamageDealt ^ (physicalDamageDealt >>> 32));
        result = 31 * result + (int) (physicalDamageDealtToChampions ^ (physicalDamageDealtToChampions >>> 32));
        result = 31 * result + (int) (physicalDamageTaken ^ (physicalDamageTaken >>> 32));
        result = 31 * result + (int) (quadraKills ^ (quadraKills >>> 32));
        result = 31 * result + (int) (sightWardsBoughtInGame ^ (sightWardsBoughtInGame >>> 32));
        result = 31 * result + (int) (teamObjective ^ (teamObjective >>> 32));
        result = 31 * result + (int) (timeCCingOthers ^ (timeCCingOthers >>> 32));
        result = 31 * result + (int) (totalDamageDealt ^ (totalDamageDealt >>> 32));
        result = 31 * result + (int) (totalDamageDealtToChampions ^ (totalDamageDealtToChampions >>> 32));
        result = 31 * result + (int) (totalDamageTaken ^ (totalDamageTaken >>> 32));
        result = 31 * result + (int) (totalHeal ^ (totalHeal >>> 32));
        result = 31 * result + (int) (totalMinionsKilled ^ (totalMinionsKilled >>> 32));
        result = 31 * result + (int) (totalPlayerScore ^ (totalPlayerScore >>> 32));
        result = 31 * result + (int) (totalScoreRank ^ (totalScoreRank >>> 32));
        result = 31 * result + (int) (totalTimeCrowdControlDealt ^ (totalTimeCrowdControlDealt >>> 32));
        result = 31 * result + (int) (totalUnitsHealed ^ (totalUnitsHealed >>> 32));
        result = 31 * result + (int) (turretKills ^ (turretKills >>> 32));
        result = 31 * result + (int) (tripleKills ^ (tripleKills >>> 32));
        result = 31 * result + (int) (trueDamageDealt ^ (trueDamageDealt >>> 32));
        result = 31 * result + (int) (trueDamageDealtToChampions ^ (trueDamageDealtToChampions >>> 32));
        result = 31 * result + (int) (trueDamageTaken ^ (trueDamageTaken >>> 32));
        result = 31 * result + (int) (unrealKills ^ (unrealKills >>> 32));
        result = 31 * result + (int) (visionScore ^ (visionScore >>> 32));
        result = 31 * result + (int) (visionWardsBoughtInGame ^ (visionWardsBoughtInGame >>> 32));
        result = 31 * result + (int) (wardsKilled ^ (wardsKilled >>> 32));
        result = 31 * result + (int) (wardsPlaced ^ (wardsPlaced >>> 32));
        result = 31 * result + (win ? 1 : 0);
        return result;
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
