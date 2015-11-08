package no.stelar7.api.l4j8.pojo.match;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

public class ParticipantStats
{
    private Long    assists;
    private Long    champLevel;
    private Long    combatPlayerScore;
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
    private Long    magicDamageDealt;
    private Long    magicDamageDealtToChampions;
    private Long    magicDamageTaken;
    private Long    minionsKilled;
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
    private Long    totalDamageDealt;
    private Long    totalDamageDealtToChampions;
    private Long    totalDamageTaken;
    private Long    totalHeal;
    private Long    totalPlayerScore;
    private Long    totalScoreRank;
    private Long    totalTimeCrowdControlDealt;
    private Long    totalUnitsHealed;
    private Long    towerKills;
    private Long    tripleKills;
    private Long    trueDamageDealt;
    private Long    trueDamageDealtToChampions;
    private Long    trueDamageTaken;
    private Long    unrealKills;
    private Long    visionWardsBoughtInGame;
    private Long    wardsKilled;
    private Long    wardsPlaced;
    private Boolean winner;

    /**
     * a {@code Map<String, String>} of all the fields in this class.
     *
     * @return the all stats
     */
    public Map<String, String> getAllStats()
    {
        final Map<String, String> map = new HashMap<>();
        Stream.of(this.getClass().getFields()).forEach(f -> {
            try
            {
                map.put(f.getName(), f.get(this).toString());
            } catch (final Exception e)
            {
                e.printStackTrace();
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
        return assists;
    }

    /**
     * Gets the champ level.
     *
     * @return the champ level
     */
    public Long getChampLevel()
    {
        return champLevel;
    }

    /**
     * Gets the combat player score.
     *
     * @return the combat player score
     */
    public Long getCombatPlayerScore()
    {
        return combatPlayerScore;
    }

    /**
     * Gets the deaths.
     *
     * @return the deaths
     */
    public Long getDeaths()
    {
        return deaths;
    }

    /**
     * Gets the double kills.
     *
     * @return the double kills
     */
    public Long getDoubleKills()
    {
        return doubleKills;
    }

    /**
     * Checks if is first blood assist.
     *
     * @return the boolean
     */
    public Boolean isFirstBloodAssist()
    {
        return firstBloodAssist;
    }

    /**
     * Checks if is first blood kill.
     *
     * @return the boolean
     */
    public Boolean isFirstBloodKill()
    {
        return firstBloodKill;
    }

    /**
     * Checks if is first inhibitor assist.
     *
     * @return the boolean
     */
    public Boolean isFirstInhibitorAssist()
    {
        return firstInhibitorAssist;
    }

    /**
     * Id first inhibitor kill.
     *
     * @return the boolean
     */
    public Boolean idFirstInhibitorKill()
    {
        return firstInhibitorKill;
    }

    /**
     * Checks if is first tower assist.
     *
     * @return the boolean
     */
    public Boolean isFirstTowerAssist()
    {
        return firstTowerAssist;
    }

    /**
     * Checks if is first tower kill.
     *
     * @return the boolean
     */
    public Boolean isFirstTowerKill()
    {
        return firstTowerKill;
    }

    /**
     * Gets the gold earned.
     *
     * @return the gold earned
     */
    public Long getGoldEarned()
    {
        return goldEarned;
    }

    /**
     * Gets the gold spent.
     *
     * @return the gold spent
     */
    public Long getGoldSpent()
    {
        return goldSpent;
    }

    /**
     * Gets the inhibitor kills.
     *
     * @return the inhibitor kills
     */
    public Long getInhibitorKills()
    {
        return inhibitorKills;
    }

    /**
     * Gets the item0.
     *
     * @return the item0
     */
    public Long getItem0()
    {
        return item0;
    }

    /**
     * Gets the item1.
     *
     * @return the item1
     */
    public Long getItem1()
    {
        return item1;
    }

    /**
     * Gets the item2.
     *
     * @return the item2
     */
    public Long getItem2()
    {
        return item2;
    }

    /**
     * Gets the item3.
     *
     * @return the item3
     */
    public Long getItem3()
    {
        return item3;
    }

    /**
     * Gets the item4.
     *
     * @return the item4
     */
    public Long getItem4()
    {
        return item4;
    }

    /**
     * Gets the item5.
     *
     * @return the item5
     */
    public Long getItem5()
    {
        return item5;
    }

    /**
     * Gets the item6.
     *
     * @return the item6
     */
    public Long getItem6()
    {
        return item6;
    }

    /**
     * Gets the killing sprees.
     *
     * @return the killing sprees
     */
    public Long getKillingSprees()
    {
        return killingSprees;
    }

    /**
     * Gets the kills.
     *
     * @return the kills
     */
    public Long getKills()
    {
        return kills;
    }

    /**
     * Gets the largest critical strike.
     *
     * @return the largest critical strike
     */
    public Long getLargestCriticalStrike()
    {
        return largestCriticalStrike;
    }

    /**
     * Gets the largest killing spree.
     *
     * @return the largest killing spree
     */
    public Long getLargestKillingSpree()
    {
        return largestKillingSpree;
    }

    /**
     * Gets the largest multi kill.
     *
     * @return the largest multi kill
     */
    public Long getLargestMultiKill()
    {
        return largestMultiKill;
    }

    /**
     * Gets the magic damage dealt.
     *
     * @return the magic damage dealt
     */
    public Long getMagicDamageDealt()
    {
        return magicDamageDealt;
    }

    /**
     * Gets the magic damage dealt to champions.
     *
     * @return the magic damage dealt to champions
     */
    public Long getMagicDamageDealtToChampions()
    {
        return magicDamageDealtToChampions;
    }

    /**
     * Gets the magic damage taken.
     *
     * @return the magic damage taken
     */
    public Long getMagicDamageTaken()
    {
        return magicDamageTaken;
    }

    /**
     * Gets the minions killed.
     *
     * @return the minions killed
     */
    public Long getMinionsKilled()
    {
        return minionsKilled;
    }

    /**
     * Gets the neutral minions killed.
     *
     * @return the neutral minions killed
     */
    public Long getNeutralMinionsKilled()
    {
        return neutralMinionsKilled;
    }

    /**
     * Gets the neutral minions killed enemy jungle.
     *
     * @return the neutral minions killed enemy jungle
     */
    public Long getNeutralMinionsKilledEnemyJungle()
    {
        return neutralMinionsKilledEnemyJungle;
    }

    /**
     * Gets the neutral minions killed team jungle.
     *
     * @return the neutral minions killed team jungle
     */
    public Long getNeutralMinionsKilledTeamJungle()
    {
        return neutralMinionsKilledTeamJungle;
    }

    /**
     * Gets the node capture.
     *
     * @return the node capture
     */
    public Long getNodeCapture()
    {
        return nodeCapture;
    }

    /**
     * Gets the node capture assist.
     *
     * @return the node capture assist
     */
    public Long getNodeCaptureAssist()
    {
        return nodeCaptureAssist;
    }

    /**
     * Gets the node neutralize.
     *
     * @return the node neutralize
     */
    public Long getNodeNeutralize()
    {
        return nodeNeutralize;
    }

    /**
     * Gets the node neutralize assist.
     *
     * @return the node neutralize assist
     */
    public Long getNodeNeutralizeAssist()
    {
        return nodeNeutralizeAssist;
    }

    /**
     * Gets the objective player score.
     *
     * @return the objective player score
     */
    public Long getObjectivePlayerScore()
    {
        return objectivePlayerScore;
    }

    /**
     * Gets the penta kills.
     *
     * @return the penta kills
     */
    public Long getPentaKills()
    {
        return pentaKills;
    }

    /**
     * Gets the physical damage dealt.
     *
     * @return the physical damage dealt
     */
    public Long getPhysicalDamageDealt()
    {
        return physicalDamageDealt;
    }

    /**
     * Gets the physical damage dealt to champions.
     *
     * @return the physical damage dealt to champions
     */
    public Long getPhysicalDamageDealtToChampions()
    {
        return physicalDamageDealtToChampions;
    }

    /**
     * Gets the physical damage taken.
     *
     * @return the physical damage taken
     */
    public Long getPhysicalDamageTaken()
    {
        return physicalDamageTaken;
    }

    /**
     * Gets the quadra kills.
     *
     * @return the quadra kills
     */
    public Long getQuadraKills()
    {
        return quadraKills;
    }

    /**
     * Gets the sight wards bought in game.
     *
     * @return the sight wards bought in game
     */
    public Long getSightWardsBoughtInGame()
    {
        return sightWardsBoughtInGame;
    }

    /**
     * Gets the team objective.
     *
     * @return the team objective
     */
    public Long getTeamObjective()
    {
        return teamObjective;
    }

    /**
     * Gets the total damage dealt.
     *
     * @return the total damage dealt
     */
    public Long getTotalDamageDealt()
    {
        return totalDamageDealt;
    }

    /**
     * Gets the total damage dealt to champions.
     *
     * @return the total damage dealt to champions
     */
    public Long getTotalDamageDealtToChampions()
    {
        return totalDamageDealtToChampions;
    }

    /**
     * Gets the total damage taken.
     *
     * @return the total damage taken
     */
    public Long getTotalDamageTaken()
    {
        return totalDamageTaken;
    }

    /**
     * Gets the total heal.
     *
     * @return the total heal
     */
    public Long getTotalHeal()
    {
        return totalHeal;
    }

    /**
     * Gets the total player score.
     *
     * @return the total player score
     */
    public Long getTotalPlayerScore()
    {
        return totalPlayerScore;
    }

    /**
     * Gets the total score rank.
     *
     * @return the total score rank
     */
    public Long getTotalScoreRank()
    {
        return totalScoreRank;
    }

    /**
     * Gets the total time crowd control dealt.
     *
     * @return the total time crowd control dealt
     */
    public Long getTotalTimeCrowdControlDealt()
    {
        return totalTimeCrowdControlDealt;
    }

    /**
     * Gets the total units healed.
     *
     * @return the total units healed
     */
    public Long getTotalUnitsHealed()
    {
        return totalUnitsHealed;
    }

    /**
     * Gets the tower kills.
     *
     * @return the tower kills
     */
    public Long getTowerKills()
    {
        return towerKills;
    }

    /**
     * Gets the triple kills.
     *
     * @return the triple kills
     */
    public Long getTripleKills()
    {
        return tripleKills;
    }

    /**
     * Gets the true damage dealt.
     *
     * @return the true damage dealt
     */
    public Long getTrueDamageDealt()
    {
        return trueDamageDealt;
    }

    /**
     * Gets the true damage dealt to champions.
     *
     * @return the true damage dealt to champions
     */
    public Long getTrueDamageDealtToChampions()
    {
        return trueDamageDealtToChampions;
    }

    /**
     * Gets the true damage taken.
     *
     * @return the true damage taken
     */
    public Long getTrueDamageTaken()
    {
        return trueDamageTaken;
    }

    /**
     * Gets the unreal kills.
     *
     * @return the unreal kills
     */
    public Long getUnrealKills()
    {
        return unrealKills;
    }

    /**
     * Gets the vision wards bought in game.
     *
     * @return the vision wards bought in game
     */
    public Long getVisionWardsBoughtInGame()
    {
        return visionWardsBoughtInGame;
    }

    /**
     * Gets the wards killed.
     *
     * @return the wards killed
     */
    public Long getWardsKilled()
    {
        return wardsKilled;
    }

    /**
     * Gets the wards placed.
     *
     * @return the wards placed
     */
    public Long getWardsPlaced()
    {
        return wardsPlaced;
    }

    /**
     * Checks if is winner.
     *
     * @return the boolean
     */
    public Boolean isWinner()
    {
        return winner;
    }

}
