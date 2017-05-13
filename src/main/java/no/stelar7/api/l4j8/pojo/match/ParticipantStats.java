package no.stelar7.api.l4j8.pojo.match;

import java.util.*;
import java.util.logging.*;
import java.util.stream.*;

public class ParticipantStats
{
    
    private Long    assists;
    private Long    champLevel;
    private Long    combatPlayerScore;
    private Long    damageSelfMitigated;
    private Long    damageDealtToTurrets;
    private Long    damageDealtToObjectives;
    private Long    deaths;
    private Long    doubleKills;
    private Boolean firstBloodAssist;
    private Boolean firstBloodKill;
    private Boolean firstInhibitorAssist;
    private Boolean firstInhibitorKill;
    private Boolean firstTowerAssist;
    private Boolean firstTowerKill;
    private Long    goldEarned;
    private Long    goldSpent;
    private Long    inhibitorKills;
    private Long    item0;
    private Long    item1;
    private Long    item2;
    private Long    item3;
    private Long    item4;
    private Long    item5;
    private Long    item6;
    private Long    killingSprees;
    private Long    kills;
    private Long    largestCriticalStrike;
    private Long    largestKillingSpree;
    private Long    largestMultiKill;
    private Long    longestTimeSpentLiving;
    private Long    magicDamageDealt;
    private Long    magicDamageDealtToChampions;
    private Long    magicalDamageTaken;
    private Long    neutralMinionsKilled;
    private Long    neutralMinionsKilledEnemyJungle;
    private Long    neutralMinionsKilledTeamJungle;
    private Long    nodeCapture;
    private Long    nodeCaptureAssist;
    private Long    nodeNeutralize;
    private Long    nodeNeutralizeAssist;
    private Long    objectivePlayerScore;
    private Long    pentaKills;
    private Long    physicalDamageDealt;
    private Long    physicalDamageDealtToChampions;
    private Long    physicalDamageTaken;
    private Long    quadraKills;
    private Long    sightWardsBoughtInGame;
    private Long    teamObjective;
    private Long    timeCCingOthers;
    private Long    totalDamageDealt;
    private Long    totalDamageDealtToChampions;
    private Long    totalDamageTaken;
    private Long    totalHeal;
    private Long    totalMinionsKilled;
    private Long    totalPlayerScore;
    private Long    totalScoreRank;
    private Long    totalTimeCrowdControlDealt;
    private Long    totalUnitsHealed;
    private Long    turretKills;
    private Long    tripleKills;
    private Long    trueDamageDealt;
    private Long    trueDamageDealtToChampions;
    private Long    trueDamageTaken;
    private Long    unrealKills;
    private Long    visionScore;
    private Long    visionWardsBoughtInGame;
    private Long    wardsKilled;
    private Long    wardsPlaced;
    private Boolean win;
    
    
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
    public Long getAssists()
    {
        return this.assists;
    }
    
    /**
     * Gets the champ level.
     *
     * @return the champ level
     */
    public Long getChampLevel()
    {
        return this.champLevel;
    }
    
    /**
     * Gets the combat player score.
     *
     * @return the combat player score
     */
    public Long getCombatPlayerScore()
    {
        return this.combatPlayerScore;
    }
    
    /**
     * Gets the deaths.
     *
     * @return the deaths
     */
    public Long getDeaths()
    {
        return this.deaths;
    }
    
    /**
     * Gets the double kills.
     *
     * @return the double kills
     */
    public Long getDoubleKills()
    {
        return this.doubleKills;
    }
    
    /**
     * Gets the gold earned.
     *
     * @return the gold earned
     */
    public Long getGoldEarned()
    {
        return this.goldEarned;
    }
    
    /**
     * Gets the gold spent.
     *
     * @return the gold spent
     */
    public Long getGoldSpent()
    {
        return this.goldSpent;
    }
    
    /**
     * Gets the inhibitor kills.
     *
     * @return the inhibitor kills
     */
    public Long getInhibitorKills()
    {
        return this.inhibitorKills;
    }
    
    /**
     * Gets the item0.
     *
     * @return the item0
     */
    public Long getItem0()
    {
        return this.item0;
    }
    
    /**
     * Gets the item1.
     *
     * @return the item1
     */
    public Long getItem1()
    {
        return this.item1;
    }
    
    /**
     * Gets the item2.
     *
     * @return the item2
     */
    public Long getItem2()
    {
        return this.item2;
    }
    
    /**
     * Gets the item3.
     *
     * @return the item3
     */
    public Long getItem3()
    {
        return this.item3;
    }
    
    /**
     * Gets the item4.
     *
     * @return the item4
     */
    public Long getItem4()
    {
        return this.item4;
    }
    
    /**
     * Gets the item5.
     *
     * @return the item5
     */
    public Long getItem5()
    {
        return this.item5;
    }
    
    /**
     * Gets the item6.
     *
     * @return the item6
     */
    public Long getItem6()
    {
        return this.item6;
    }
    
    /**
     * Gets the killing sprees.
     *
     * @return the killing sprees
     */
    public Long getKillingSprees()
    {
        return this.killingSprees;
    }
    
    /**
     * Gets the kills.
     *
     * @return the kills
     */
    public Long getKills()
    {
        return this.kills;
    }
    
    /**
     * Gets the largest critical strike.
     *
     * @return the largest critical strike
     */
    public Long getLargestCriticalStrike()
    {
        return this.largestCriticalStrike;
    }
    
    /**
     * Gets the largest killing spree.
     *
     * @return the largest killing spree
     */
    public Long getLargestKillingSpree()
    {
        return this.largestKillingSpree;
    }
    
    /**
     * Gets the largest multi kill.
     *
     * @return the largest multi kill
     */
    public Long getLargestMultiKill()
    {
        return this.largestMultiKill;
    }
    
    /**
     * Gets the magic damage dealt.
     *
     * @return the magic damage dealt
     */
    public Long getMagicDamageDealt()
    {
        return this.magicDamageDealt;
    }
    
    /**
     * Gets the magic damage dealt to champions.
     *
     * @return the magic damage dealt to champions
     */
    public Long getMagicDamageDealtToChampions()
    {
        return this.magicDamageDealtToChampions;
    }
    
    /**
     * Gets the magic damage taken.
     *
     * @return the magic damage taken
     */
    public Long getMagicDamageTaken()
    {
        return this.magicalDamageTaken;
    }
    
    /**
     * Gets the minions killed.
     *
     * @return the minions killed
     */
    public Long getTotalMinionsKilled()
    {
        return this.totalMinionsKilled;
    }
    
    /**
     * Gets the neutral minions killed.
     *
     * @return the neutral minions killed
     */
    public Long getNeutralMinionsKilled()
    {
        return this.neutralMinionsKilled;
    }
    
    /**
     * Gets the neutral minions killed enemy jungle.
     *
     * @return the neutral minions killed enemy jungle
     */
    public Long getNeutralMinionsKilledEnemyJungle()
    {
        return this.neutralMinionsKilledEnemyJungle;
    }
    
    /**
     * Gets the neutral minions killed team jungle.
     *
     * @return the neutral minions killed team jungle
     */
    public Long getNeutralMinionsKilledTeamJungle()
    {
        return this.neutralMinionsKilledTeamJungle;
    }
    
    /**
     * Gets the node capture.
     *
     * @return the node capture
     */
    public Long getNodeCapture()
    {
        return this.nodeCapture;
    }
    
    /**
     * Gets the node capture assist.
     *
     * @return the node capture assist
     */
    public Long getNodeCaptureAssist()
    {
        return this.nodeCaptureAssist;
    }
    
    /**
     * Gets the node neutralize.
     *
     * @return the node neutralize
     */
    public Long getNodeNeutralize()
    {
        return this.nodeNeutralize;
    }
    
    /**
     * Gets the node neutralize assist.
     *
     * @return the node neutralize assist
     */
    public Long getNodeNeutralizeAssist()
    {
        return this.nodeNeutralizeAssist;
    }
    
    /**
     * Gets the objective player score.
     *
     * @return the objective player score
     */
    public Long getObjectivePlayerScore()
    {
        return this.objectivePlayerScore;
    }
    
    /**
     * Gets the penta kills.
     *
     * @return the penta kills
     */
    public Long getPentaKills()
    {
        return this.pentaKills;
    }
    
    /**
     * Gets the physical damage dealt.
     *
     * @return the physical damage dealt
     */
    public Long getPhysicalDamageDealt()
    {
        return this.physicalDamageDealt;
    }
    
    /**
     * Gets the physical damage dealt to champions.
     *
     * @return the physical damage dealt to champions
     */
    public Long getPhysicalDamageDealtToChampions()
    {
        return this.physicalDamageDealtToChampions;
    }
    
    /**
     * Gets the physical damage taken.
     *
     * @return the physical damage taken
     */
    public Long getPhysicalDamageTaken()
    {
        return this.physicalDamageTaken;
    }
    
    /**
     * Gets the quadra kills.
     *
     * @return the quadra kills
     */
    public Long getQuadraKills()
    {
        return this.quadraKills;
    }
    
    /**
     * Gets the sight wards bought in game.
     *
     * @return the sight wards bought in game
     */
    public Long getSightWardsBoughtInGame()
    {
        return this.sightWardsBoughtInGame;
    }
    
    /**
     * Gets the team objective.
     *
     * @return the team objective
     */
    public Long getTeamObjective()
    {
        return this.teamObjective;
    }
    
    /**
     * Gets the total damage dealt.
     *
     * @return the total damage dealt
     */
    public Long getTotalDamageDealt()
    {
        return this.totalDamageDealt;
    }
    
    /**
     * Gets the total damage dealt to champions.
     *
     * @return the total damage dealt to champions
     */
    public Long getTotalDamageDealtToChampions()
    {
        return this.totalDamageDealtToChampions;
    }
    
    /**
     * Gets the total damage taken.
     *
     * @return the total damage taken
     */
    public Long getTotalDamageTaken()
    {
        return this.totalDamageTaken;
    }
    
    /**
     * Gets the total heal.
     *
     * @return the total heal
     */
    public Long getTotalHeal()
    {
        return this.totalHeal;
    }
    
    /**
     * Gets the total player score.
     *
     * @return the total player score
     */
    public Long getTotalPlayerScore()
    {
        return this.totalPlayerScore;
    }
    
    /**
     * Gets the total score rank.
     *
     * @return the total score rank
     */
    public Long getTotalScoreRank()
    {
        return this.totalScoreRank;
    }
    
    /**
     * Gets the total time crowd control dealt.
     *
     * @return the total time crowd control dealt
     */
    public Long getTotalTimeCrowdControlDealt()
    {
        return this.totalTimeCrowdControlDealt;
    }
    
    /**
     * Gets the total units healed.
     *
     * @return the total units healed
     */
    public Long getTotalUnitsHealed()
    {
        return this.totalUnitsHealed;
    }
    
    /**
     * Gets the turret kills.
     *
     * @return the turret kills
     */
    public Long getTurretKills()
    {
        return this.turretKills;
    }
    
    /**
     * Gets the triple kills.
     *
     * @return the triple kills
     */
    public Long getTripleKills()
    {
        return this.tripleKills;
    }
    
    /**
     * Gets the true damage dealt.
     *
     * @return the true damage dealt
     */
    public Long getTrueDamageDealt()
    {
        return this.trueDamageDealt;
    }
    
    /**
     * Gets the true damage dealt to champions.
     *
     * @return the true damage dealt to champions
     */
    public Long getTrueDamageDealtToChampions()
    {
        return this.trueDamageDealtToChampions;
    }
    
    /**
     * Gets the true damage taken.
     *
     * @return the true damage taken
     */
    public Long getTrueDamageTaken()
    {
        return this.trueDamageTaken;
    }
    
    /**
     * Gets the unreal kills.
     *
     * @return the unreal kills
     */
    public Long getUnrealKills()
    {
        return this.unrealKills;
    }
    
    /**
     * Gets the vision wards bought in game.
     *
     * @return the vision wards bought in game
     */
    public Long getVisionWardsBoughtInGame()
    {
        return this.visionWardsBoughtInGame;
    }
    
    /**
     * Gets the wards killed.
     *
     * @return the wards killed
     */
    public Long getWardsKilled()
    {
        return this.wardsKilled;
    }
    
    /**
     * Gets the wards placed.
     *
     * @return the wards placed
     */
    public Long getWardsPlaced()
    {
        return this.wardsPlaced;
    }
    
    
    /**
     * Id first inhibitor kill.
     *
     * @return the boolean
     */
    public Boolean isFirstInhibitorKill()
    {
        return this.firstInhibitorKill;
    }
    
    /**
     * Checks if is first blood assist.
     *
     * @return the boolean
     */
    public Boolean isFirstBloodAssist()
    {
        return this.firstBloodAssist;
    }
    
    /**
     * Checks if is first blood kill.
     *
     * @return the boolean
     */
    public Boolean isFirstBloodKill()
    {
        return this.firstBloodKill;
    }
    
    /**
     * Checks if is first inhibitor assist.
     *
     * @return the boolean
     */
    public Boolean isFirstInhibitorAssist()
    {
        return this.firstInhibitorAssist;
    }
    
    /**
     * Checks if is first tower assist.
     *
     * @return the boolean
     */
    public Boolean isFirstTowerAssist()
    {
        return this.firstTowerAssist;
    }
    
    /**
     * Checks if is first tower kill.
     *
     * @return the boolean
     */
    public Boolean isFirstTowerKill()
    {
        return this.firstTowerKill;
    }
    
    /**
     * Checks if is win.
     *
     * @return the boolean
     */
    public Boolean isWinner()
    {
        return this.win;
    }
    
    public Long getDamageSelfMitigated()
    {
        return damageSelfMitigated;
    }
    
    public Long getDamageDealtToTurrets()
    {
        return damageDealtToTurrets;
    }
    
    public Long getDamageDealtToObjectives()
    {
        return damageDealtToObjectives;
    }
    
    public Long getLongestTimeSpentLiving()
    {
        return longestTimeSpentLiving;
    }
    
    public Long getTimeCCingOthers()
    {
        return timeCCingOthers;
    }
    
    public Long getVisionScore()
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
        
        if ((assists != null) ? !assists.equals(that.assists) : (that.assists != null))
        {
            return false;
        }
        if ((champLevel != null) ? !champLevel.equals(that.champLevel) : (that.champLevel != null))
        {
            return false;
        }
        if ((combatPlayerScore != null) ? !combatPlayerScore.equals(that.combatPlayerScore) : (that.combatPlayerScore != null))
        {
            return false;
        }
        if ((damageSelfMitigated != null) ? !damageSelfMitigated.equals(that.damageSelfMitigated) : (that.damageSelfMitigated != null))
        {
            return false;
        }
        if ((damageDealtToTurrets != null) ? !damageDealtToTurrets.equals(that.damageDealtToTurrets) : (that.damageDealtToTurrets != null))
        {
            return false;
        }
        if ((damageDealtToObjectives != null) ? !damageDealtToObjectives.equals(that.damageDealtToObjectives) : (that.damageDealtToObjectives != null))
        {
            return false;
        }
        if ((deaths != null) ? !deaths.equals(that.deaths) : (that.deaths != null))
        {
            return false;
        }
        if ((doubleKills != null) ? !doubleKills.equals(that.doubleKills) : (that.doubleKills != null))
        {
            return false;
        }
        if ((firstBloodAssist != null) ? !firstBloodAssist.equals(that.firstBloodAssist) : (that.firstBloodAssist != null))
        {
            return false;
        }
        if ((firstBloodKill != null) ? !firstBloodKill.equals(that.firstBloodKill) : (that.firstBloodKill != null))
        {
            return false;
        }
        if ((firstInhibitorAssist != null) ? !firstInhibitorAssist.equals(that.firstInhibitorAssist) : (that.firstInhibitorAssist != null))
        {
            return false;
        }
        if ((firstInhibitorKill != null) ? !firstInhibitorKill.equals(that.firstInhibitorKill) : (that.firstInhibitorKill != null))
        {
            return false;
        }
        if ((firstTowerAssist != null) ? !firstTowerAssist.equals(that.firstTowerAssist) : (that.firstTowerAssist != null))
        {
            return false;
        }
        if ((firstTowerKill != null) ? !firstTowerKill.equals(that.firstTowerKill) : (that.firstTowerKill != null))
        {
            return false;
        }
        if ((goldEarned != null) ? !goldEarned.equals(that.goldEarned) : (that.goldEarned != null))
        {
            return false;
        }
        if ((goldSpent != null) ? !goldSpent.equals(that.goldSpent) : (that.goldSpent != null))
        {
            return false;
        }
        if ((inhibitorKills != null) ? !inhibitorKills.equals(that.inhibitorKills) : (that.inhibitorKills != null))
        {
            return false;
        }
        if ((item0 != null) ? !item0.equals(that.item0) : (that.item0 != null))
        {
            return false;
        }
        if ((item1 != null) ? !item1.equals(that.item1) : (that.item1 != null))
        {
            return false;
        }
        if ((item2 != null) ? !item2.equals(that.item2) : (that.item2 != null))
        {
            return false;
        }
        if ((item3 != null) ? !item3.equals(that.item3) : (that.item3 != null))
        {
            return false;
        }
        if ((item4 != null) ? !item4.equals(that.item4) : (that.item4 != null))
        {
            return false;
        }
        if ((item5 != null) ? !item5.equals(that.item5) : (that.item5 != null))
        {
            return false;
        }
        if ((item6 != null) ? !item6.equals(that.item6) : (that.item6 != null))
        {
            return false;
        }
        if ((killingSprees != null) ? !killingSprees.equals(that.killingSprees) : (that.killingSprees != null))
        {
            return false;
        }
        if ((kills != null) ? !kills.equals(that.kills) : (that.kills != null))
        {
            return false;
        }
        if ((largestCriticalStrike != null) ? !largestCriticalStrike.equals(that.largestCriticalStrike) : (that.largestCriticalStrike != null))
        {
            return false;
        }
        if ((largestKillingSpree != null) ? !largestKillingSpree.equals(that.largestKillingSpree) : (that.largestKillingSpree != null))
        {
            return false;
        }
        if ((largestMultiKill != null) ? !largestMultiKill.equals(that.largestMultiKill) : (that.largestMultiKill != null))
        {
            return false;
        }
        if ((longestTimeSpentLiving != null) ? !longestTimeSpentLiving.equals(that.longestTimeSpentLiving) : (that.longestTimeSpentLiving != null))
        {
            return false;
        }
        if ((magicDamageDealt != null) ? !magicDamageDealt.equals(that.magicDamageDealt) : (that.magicDamageDealt != null))
        {
            return false;
        }
        if ((magicDamageDealtToChampions != null) ? !magicDamageDealtToChampions.equals(that.magicDamageDealtToChampions) : (that.magicDamageDealtToChampions != null))
        {
            return false;
        }
        if ((magicalDamageTaken != null) ? !magicalDamageTaken.equals(that.magicalDamageTaken) : (that.magicalDamageTaken != null))
        {
            return false;
        }
        if ((neutralMinionsKilled != null) ? !neutralMinionsKilled.equals(that.neutralMinionsKilled) : (that.neutralMinionsKilled != null))
        {
            return false;
        }
        if ((neutralMinionsKilledEnemyJungle != null) ? !neutralMinionsKilledEnemyJungle.equals(that.neutralMinionsKilledEnemyJungle) : (that.neutralMinionsKilledEnemyJungle != null))
        {
            return false;
        }
        if ((neutralMinionsKilledTeamJungle != null) ? !neutralMinionsKilledTeamJungle.equals(that.neutralMinionsKilledTeamJungle) : (that.neutralMinionsKilledTeamJungle != null))
        {
            return false;
        }
        if ((nodeCapture != null) ? !nodeCapture.equals(that.nodeCapture) : (that.nodeCapture != null))
        {
            return false;
        }
        if ((nodeCaptureAssist != null) ? !nodeCaptureAssist.equals(that.nodeCaptureAssist) : (that.nodeCaptureAssist != null))
        {
            return false;
        }
        if ((nodeNeutralize != null) ? !nodeNeutralize.equals(that.nodeNeutralize) : (that.nodeNeutralize != null))
        {
            return false;
        }
        if ((nodeNeutralizeAssist != null) ? !nodeNeutralizeAssist.equals(that.nodeNeutralizeAssist) : (that.nodeNeutralizeAssist != null))
        {
            return false;
        }
        if ((objectivePlayerScore != null) ? !objectivePlayerScore.equals(that.objectivePlayerScore) : (that.objectivePlayerScore != null))
        {
            return false;
        }
        if ((pentaKills != null) ? !pentaKills.equals(that.pentaKills) : (that.pentaKills != null))
        {
            return false;
        }
        if ((physicalDamageDealt != null) ? !physicalDamageDealt.equals(that.physicalDamageDealt) : (that.physicalDamageDealt != null))
        {
            return false;
        }
        if ((physicalDamageDealtToChampions != null) ? !physicalDamageDealtToChampions.equals(that.physicalDamageDealtToChampions) : (that.physicalDamageDealtToChampions != null))
        {
            return false;
        }
        if ((physicalDamageTaken != null) ? !physicalDamageTaken.equals(that.physicalDamageTaken) : (that.physicalDamageTaken != null))
        {
            return false;
        }
        if ((quadraKills != null) ? !quadraKills.equals(that.quadraKills) : (that.quadraKills != null))
        {
            return false;
        }
        if ((sightWardsBoughtInGame != null) ? !sightWardsBoughtInGame.equals(that.sightWardsBoughtInGame) : (that.sightWardsBoughtInGame != null))
        {
            return false;
        }
        if ((teamObjective != null) ? !teamObjective.equals(that.teamObjective) : (that.teamObjective != null))
        {
            return false;
        }
        if ((timeCCingOthers != null) ? !timeCCingOthers.equals(that.timeCCingOthers) : (that.timeCCingOthers != null))
        {
            return false;
        }
        if ((totalDamageDealt != null) ? !totalDamageDealt.equals(that.totalDamageDealt) : (that.totalDamageDealt != null))
        {
            return false;
        }
        if ((totalDamageDealtToChampions != null) ? !totalDamageDealtToChampions.equals(that.totalDamageDealtToChampions) : (that.totalDamageDealtToChampions != null))
        {
            return false;
        }
        if ((totalDamageTaken != null) ? !totalDamageTaken.equals(that.totalDamageTaken) : (that.totalDamageTaken != null))
        {
            return false;
        }
        if ((totalHeal != null) ? !totalHeal.equals(that.totalHeal) : (that.totalHeal != null))
        {
            return false;
        }
        if ((totalMinionsKilled != null) ? !totalMinionsKilled.equals(that.totalMinionsKilled) : (that.totalMinionsKilled != null))
        {
            return false;
        }
        if ((totalPlayerScore != null) ? !totalPlayerScore.equals(that.totalPlayerScore) : (that.totalPlayerScore != null))
        {
            return false;
        }
        if ((totalScoreRank != null) ? !totalScoreRank.equals(that.totalScoreRank) : (that.totalScoreRank != null))
        {
            return false;
        }
        if ((totalTimeCrowdControlDealt != null) ? !totalTimeCrowdControlDealt.equals(that.totalTimeCrowdControlDealt) : (that.totalTimeCrowdControlDealt != null))
        {
            return false;
        }
        if ((totalUnitsHealed != null) ? !totalUnitsHealed.equals(that.totalUnitsHealed) : (that.totalUnitsHealed != null))
        {
            return false;
        }
        if ((turretKills != null) ? !turretKills.equals(that.turretKills) : (that.turretKills != null))
        {
            return false;
        }
        if ((tripleKills != null) ? !tripleKills.equals(that.tripleKills) : (that.tripleKills != null))
        {
            return false;
        }
        if ((trueDamageDealt != null) ? !trueDamageDealt.equals(that.trueDamageDealt) : (that.trueDamageDealt != null))
        {
            return false;
        }
        if ((trueDamageDealtToChampions != null) ? !trueDamageDealtToChampions.equals(that.trueDamageDealtToChampions) : (that.trueDamageDealtToChampions != null))
        {
            return false;
        }
        if ((trueDamageTaken != null) ? !trueDamageTaken.equals(that.trueDamageTaken) : (that.trueDamageTaken != null))
        {
            return false;
        }
        if ((unrealKills != null) ? !unrealKills.equals(that.unrealKills) : (that.unrealKills != null))
        {
            return false;
        }
        if ((visionScore != null) ? !visionScore.equals(that.visionScore) : (that.visionScore != null))
        {
            return false;
        }
        if ((visionWardsBoughtInGame != null) ? !visionWardsBoughtInGame.equals(that.visionWardsBoughtInGame) : (that.visionWardsBoughtInGame != null))
        {
            return false;
        }
        if ((wardsKilled != null) ? !wardsKilled.equals(that.wardsKilled) : (that.wardsKilled != null))
        {
            return false;
        }
        if ((wardsPlaced != null) ? !wardsPlaced.equals(that.wardsPlaced) : (that.wardsPlaced != null))
        {
            return false;
        }
        return (win != null) ? win.equals(that.win) : (that.win == null);
    }
    
    @Override
    public int hashCode()
    {
        int result = assists != null ? assists.hashCode() : 0;
        result = 31 * result + (champLevel != null ? champLevel.hashCode() : 0);
        result = 31 * result + (combatPlayerScore != null ? combatPlayerScore.hashCode() : 0);
        result = 31 * result + (damageSelfMitigated != null ? damageSelfMitigated.hashCode() : 0);
        result = 31 * result + (damageDealtToTurrets != null ? damageDealtToTurrets.hashCode() : 0);
        result = 31 * result + (damageDealtToObjectives != null ? damageDealtToObjectives.hashCode() : 0);
        result = 31 * result + (deaths != null ? deaths.hashCode() : 0);
        result = 31 * result + (doubleKills != null ? doubleKills.hashCode() : 0);
        result = 31 * result + (firstBloodAssist != null ? firstBloodAssist.hashCode() : 0);
        result = 31 * result + (firstBloodKill != null ? firstBloodKill.hashCode() : 0);
        result = 31 * result + (firstInhibitorAssist != null ? firstInhibitorAssist.hashCode() : 0);
        result = 31 * result + (firstInhibitorKill != null ? firstInhibitorKill.hashCode() : 0);
        result = 31 * result + (firstTowerAssist != null ? firstTowerAssist.hashCode() : 0);
        result = 31 * result + (firstTowerKill != null ? firstTowerKill.hashCode() : 0);
        result = 31 * result + (goldEarned != null ? goldEarned.hashCode() : 0);
        result = 31 * result + (goldSpent != null ? goldSpent.hashCode() : 0);
        result = 31 * result + (inhibitorKills != null ? inhibitorKills.hashCode() : 0);
        result = 31 * result + (item0 != null ? item0.hashCode() : 0);
        result = 31 * result + (item1 != null ? item1.hashCode() : 0);
        result = 31 * result + (item2 != null ? item2.hashCode() : 0);
        result = 31 * result + (item3 != null ? item3.hashCode() : 0);
        result = 31 * result + (item4 != null ? item4.hashCode() : 0);
        result = 31 * result + (item5 != null ? item5.hashCode() : 0);
        result = 31 * result + (item6 != null ? item6.hashCode() : 0);
        result = 31 * result + (killingSprees != null ? killingSprees.hashCode() : 0);
        result = 31 * result + (kills != null ? kills.hashCode() : 0);
        result = 31 * result + (largestCriticalStrike != null ? largestCriticalStrike.hashCode() : 0);
        result = 31 * result + (largestKillingSpree != null ? largestKillingSpree.hashCode() : 0);
        result = 31 * result + (largestMultiKill != null ? largestMultiKill.hashCode() : 0);
        result = 31 * result + (longestTimeSpentLiving != null ? longestTimeSpentLiving.hashCode() : 0);
        result = 31 * result + (magicDamageDealt != null ? magicDamageDealt.hashCode() : 0);
        result = 31 * result + (magicDamageDealtToChampions != null ? magicDamageDealtToChampions.hashCode() : 0);
        result = 31 * result + (magicalDamageTaken != null ? magicalDamageTaken.hashCode() : 0);
        result = 31 * result + (neutralMinionsKilled != null ? neutralMinionsKilled.hashCode() : 0);
        result = 31 * result + (neutralMinionsKilledEnemyJungle != null ? neutralMinionsKilledEnemyJungle.hashCode() : 0);
        result = 31 * result + (neutralMinionsKilledTeamJungle != null ? neutralMinionsKilledTeamJungle.hashCode() : 0);
        result = 31 * result + (nodeCapture != null ? nodeCapture.hashCode() : 0);
        result = 31 * result + (nodeCaptureAssist != null ? nodeCaptureAssist.hashCode() : 0);
        result = 31 * result + (nodeNeutralize != null ? nodeNeutralize.hashCode() : 0);
        result = 31 * result + (nodeNeutralizeAssist != null ? nodeNeutralizeAssist.hashCode() : 0);
        result = 31 * result + (objectivePlayerScore != null ? objectivePlayerScore.hashCode() : 0);
        result = 31 * result + (pentaKills != null ? pentaKills.hashCode() : 0);
        result = 31 * result + (physicalDamageDealt != null ? physicalDamageDealt.hashCode() : 0);
        result = 31 * result + (physicalDamageDealtToChampions != null ? physicalDamageDealtToChampions.hashCode() : 0);
        result = 31 * result + (physicalDamageTaken != null ? physicalDamageTaken.hashCode() : 0);
        result = 31 * result + (quadraKills != null ? quadraKills.hashCode() : 0);
        result = 31 * result + (sightWardsBoughtInGame != null ? sightWardsBoughtInGame.hashCode() : 0);
        result = 31 * result + (teamObjective != null ? teamObjective.hashCode() : 0);
        result = 31 * result + (timeCCingOthers != null ? timeCCingOthers.hashCode() : 0);
        result = 31 * result + (totalDamageDealt != null ? totalDamageDealt.hashCode() : 0);
        result = 31 * result + (totalDamageDealtToChampions != null ? totalDamageDealtToChampions.hashCode() : 0);
        result = 31 * result + (totalDamageTaken != null ? totalDamageTaken.hashCode() : 0);
        result = 31 * result + (totalHeal != null ? totalHeal.hashCode() : 0);
        result = 31 * result + (totalMinionsKilled != null ? totalMinionsKilled.hashCode() : 0);
        result = 31 * result + (totalPlayerScore != null ? totalPlayerScore.hashCode() : 0);
        result = 31 * result + (totalScoreRank != null ? totalScoreRank.hashCode() : 0);
        result = 31 * result + (totalTimeCrowdControlDealt != null ? totalTimeCrowdControlDealt.hashCode() : 0);
        result = 31 * result + (totalUnitsHealed != null ? totalUnitsHealed.hashCode() : 0);
        result = 31 * result + (turretKills != null ? turretKills.hashCode() : 0);
        result = 31 * result + (tripleKills != null ? tripleKills.hashCode() : 0);
        result = 31 * result + (trueDamageDealt != null ? trueDamageDealt.hashCode() : 0);
        result = 31 * result + (trueDamageDealtToChampions != null ? trueDamageDealtToChampions.hashCode() : 0);
        result = 31 * result + (trueDamageTaken != null ? trueDamageTaken.hashCode() : 0);
        result = 31 * result + (unrealKills != null ? unrealKills.hashCode() : 0);
        result = 31 * result + (visionScore != null ? visionScore.hashCode() : 0);
        result = 31 * result + (visionWardsBoughtInGame != null ? visionWardsBoughtInGame.hashCode() : 0);
        result = 31 * result + (wardsKilled != null ? wardsKilled.hashCode() : 0);
        result = 31 * result + (wardsPlaced != null ? wardsPlaced.hashCode() : 0);
        result = 31 * result + (win != null ? win.hashCode() : 0);
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
