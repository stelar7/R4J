package no.stelar7.api.l4j8.pojo.game;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

public class RawStats
{
    private Integer assists;
    private Integer barracksKilled;
    private Integer championsKilled;
    private Integer combatPlayerScore;
    private Integer consumablesPurchased;
    private Integer damageDealtPlayer;
    private Integer doubleKills;
    private Integer firstBlood;
    private Integer gold;
    private Integer goldEarned;
    private Integer goldSpent;
    private Integer item0;
    private Integer item1;
    private Integer item2;
    private Integer item3;
    private Integer item4;
    private Integer item5;
    private Integer item6;
    private Integer itemsPurchased;
    private Integer killingSprees;
    private Integer largestCriticalStrike;
    private Integer largestKillingSpree;
    private Integer largestMultiKill;
    private Integer legendaryItemsCreated;
    private Integer level;
    private Integer magicDamageDealtPlayer;
    private Integer magicDamageDealtToChampions;
    private Integer magicDamageTaken;
    private Integer minionsDenied;
    private Integer minionsKilled;
    private Integer neutralMinionsKilled;
    private Integer neutralMinionsKilledEnemyJungle;
    private Integer neutralMinionsKilledYourJungle;
    private Boolean nexusKilled;
    private Integer nodeCapture;
    private Integer nodeCaptureAssist;
    private Integer nodeNeutralize;
    private Integer nodeNeutralizeAssist;
    private Integer numDeaths;
    private Integer numItemsBought;
    private Integer objectivePlayerScore;
    private Integer pentaKills;
    private Integer physicalDamageDealtPlayer;
    private Integer physicalDamageDealtToChampions;
    private Integer physicalDamageTaken;
    private Integer playerPosition;
    private Integer playerRole;
    private Integer quadraKills;
    private Integer sightWardsBought;
    private Integer spell1Cast;
    private Integer spell2Cast;
    private Integer spell3Cast;
    private Integer spell4Cast;
    private Integer summonSpell1Cast;
    private Integer summonSpell2Cast;
    private Integer superMonsterKilled;
    private Integer team;
    private Integer teamObjective;
    private Integer timePlayed;
    private Integer totalDamageDealt;
    private Integer totalDamageDealtToChampions;
    private Integer totalDamageTaken;
    private Integer totalHeal;
    private Integer totalPlayerScore;
    private Integer totalScoreRank;
    private Integer totalTimeCrowdControlDealt;
    private Integer totalUnitsHealed;
    private Integer tripleKills;
    private Integer trueDamageDealtPlayer;
    private Integer trueDamageDealtToChampions;
    private Integer trueDamageTaken;
    private Integer turretsKilled;
    private Integer unrealKills;
    private Integer victoryPointTotal;
    private Integer visionWardsBought;
    private Integer wardKilled;
    private Integer wardPlaced;
    private Boolean win;

    /**
     * Amount of assists
     * 
     * @return Integer
     */
    public Integer getAssists()
    {
        return assists;
    }

    /**
     * Number of enemy inhibitors killed.
     * 
     * @return Integer
     */
    public Integer getBarracksKilled()
    {
        return barracksKilled;
    }

    /**
     * Amount of champions killed
     * 
     * @return Integer
     */
    public Integer getChampionsKilled()
    {
        return championsKilled;
    }

    /**
     * Amount of points. (Dominion only)
     * 
     * @return Integer
     */
    public Integer getCombatPlayerScore()
    {
        return combatPlayerScore;
    }

    /**
     * Amount of consumables purchased
     * 
     * @return Integer
     */
    public Integer getConsumablesPurchased()
    {
        return consumablesPurchased;
    }

    /**
     * amount of
     * 
     * @return Integer
     */
    public Integer getDamageDealtPlayer()
    {
        return damageDealtPlayer;
    }

    /**
     * @return Integer
     */
    public Integer getDoubleKills()
    {
        return doubleKills;
    }

    /**
     * @return Integer
     */
    public Integer getFirstBlood()
    {
        return firstBlood;
    }

    /**
     * @return Integer
     */
    public Integer getGold()
    {
        return gold;
    }

    /**
     * @return Integer
     */
    public Integer getGoldEarned()
    {
        return goldEarned;
    }

    /**
     * @return Integer
     */
    public Integer getGoldSpent()
    {
        return goldSpent;
    }

    /**
     * @return Integer
     */
    public Integer getItem0()
    {
        return item0;
    }

    /**
     * @return Integer
     */
    public Integer getItem1()
    {
        return item1;
    }

    /**
     * @return Integer
     */
    public Integer getItem2()
    {
        return item2;
    }

    /**
     * @return Integer
     */
    public Integer getItem3()
    {
        return item3;
    }

    /**
     * @return Integer
     */
    public Integer getItem4()
    {
        return item4;
    }

    /**
     * @return Integer
     */
    public Integer getItem5()
    {
        return item5;
    }

    /**
     * @return Integer
     */
    public Integer getItem6()
    {
        return item6;
    }

    /**
     * @return Integer
     */
    public Integer getItemsPurchased()
    {
        return itemsPurchased;
    }

    /**
     * @return Integer
     */
    public Integer getKillingSprees()
    {
        return killingSprees;
    }

    /**
     * @return Integer
     */
    public Integer getLargestCriticalStrike()
    {
        return largestCriticalStrike;
    }

    /**
     * @return Integer
     */
    public Integer getLargestKillingSpree()
    {
        return largestKillingSpree;
    }

    /**
     * @return Integer
     */
    public Integer getLargestMultiKill()
    {
        return largestMultiKill;
    }

    /**
     * @return Integer
     */
    public Integer getLegendaryItemsCreated()
    {
        return legendaryItemsCreated;
    }

    /**
     * @return Integer
     */
    public Integer getLevel()
    {
        return level;
    }

    /**
     * @return Integer
     */

    public Integer getMagicDamageDealtPlayer()
    {
        return magicDamageDealtPlayer;
    }

    /**
     * @return Integer
     */

    public Integer getMagicDamageDealtToChampions()
    {
        return magicDamageDealtToChampions;
    }

    /**
     * @return Integer
     */

    public Integer getMagicDamageTaken()
    {
        return magicDamageTaken;
    }

    /**
     * @return Integer
     */

    public Integer getMinionsDenied()
    {
        return minionsDenied;
    }

    /**
     * @return Integer
     */

    public Integer getMinionsKilled()
    {
        return minionsKilled;
    }

    /**
     * @return Integer
     */

    public Integer getNeutralMinionsKilled()
    {
        return neutralMinionsKilled;
    }

    /**
     * @return Integer
     */

    public Integer getNeutralMinionsKilledEnemyJungle()
    {
        return neutralMinionsKilledEnemyJungle;
    }

    /**
     * @return Integer
     */

    public Integer getNeutralMinionsKilledYourJungle()
    {
        return neutralMinionsKilledYourJungle;
    }

    /**
     * @return Integer
     */

    public Boolean getNexusKilled()
    {
        return nexusKilled;
    }

    /**
     * @return Integer
     */

    public Integer getNodeCapture()
    {
        return nodeCapture;
    }

    /**
     * @return Integer
     */

    public Integer getNodeCaptureAssist()
    {
        return nodeCaptureAssist;
    }

    /**
     * @return Integer
     */

    public Integer getNodeNeutralize()
    {
        return nodeNeutralize;
    }

    /**
     * @return Integer
     */

    public Integer getNodeNeutralizeAssist()
    {
        return nodeNeutralizeAssist;
    }

    /**
     * @return Integer
     */

    public Integer getNumDeaths()
    {
        return numDeaths;
    }

    /**
     * @return Integer
     */

    public Integer getNumItemsBought()
    {
        return numItemsBought;
    }

    /**
     * @return Integer
     */

    public Integer getObjectivePlayerScore()
    {
        return objectivePlayerScore;
    }

    /**
     * @return Integer
     */

    public Integer getPentaKills()
    {
        return pentaKills;
    }

    /**
     * @return Integer
     */

    public Integer getPhysicalDamageDealtPlayer()
    {
        return physicalDamageDealtPlayer;
    }

    /**
     * @return Integer
     */

    public Integer getPhysicalDamageDealtToChampions()
    {
        return physicalDamageDealtToChampions;
    }

    /**
     * @return Integer
     */

    public Integer getPhysicalDamageTaken()
    {
        return physicalDamageTaken;
    }

    /**
     * @return Integer
     */

    public Integer getPlayerPosition()
    {
        return playerPosition;
    }

    /**
     * @return Integer
     */

    public Integer getPlayerRole()
    {
        return playerRole;
    }

    /**
     * @return Integer
     */

    public Integer getQuadraKills()
    {
        return quadraKills;
    }

    /**
     * @return Integer
     */

    public Integer getSightWardsBought()
    {
        return sightWardsBought;
    }

    /**
     * @return Integer
     */

    public Integer getSpell1Cast()
    {
        return spell1Cast;
    }

    /**
     * @return Integer
     */

    public Integer getSpell2Cast()
    {
        return spell2Cast;
    }

    /**
     * @return Integer
     */

    public Integer getSpell3Cast()
    {
        return spell3Cast;
    }

    /**
     * @return Integer
     */

    public Integer getSpell4Cast()
    {
        return spell4Cast;
    }

    /**
     * @return Integer
     */

    public Integer getSummonSpell1Cast()
    {
        return summonSpell1Cast;
    }

    /**
     * @return Integer
     */

    public Integer getSummonSpell2Cast()
    {
        return summonSpell2Cast;
    }

    /**
     * @return Integer
     */

    public Integer getSuperMonsterKilled()
    {
        return superMonsterKilled;
    }

    /**
     * @return Integer
     */

    public Integer getTeam()
    {
        return team;
    }

    /**
     * @return Integer
     */
    public Integer getTeamObjective()
    {
        return teamObjective;
    }

    /**
     * @return Integer
     */
    public Integer getTimePlayed()
    {
        return timePlayed;
    }

    /**
     * @return Integer
     */
    public Integer getTotalDamageDealt()
    {
        return totalDamageDealt;
    }

    /**
     * @return Integer
     */
    public Integer getTotalDamageDealtToChampions()
    {
        return totalDamageDealtToChampions;
    }

    /**
     * @return Integer
     */
    public Integer getTotalDamageTaken()
    {
        return totalDamageTaken;
    }

    /**
     * @return Integer
     */
    public Integer getTotalHeal()
    {
        return totalHeal;
    }

    /**
     * @return Integer
     */
    public Integer getTotalPlayerScore()
    {
        return totalPlayerScore;
    }

    /**
     * @return Integer
     */
    public Integer getTotalScoreRank()
    {
        return totalScoreRank;
    }

    /**
     * @return Integer
     */
    public Integer getTotalTimeCrowdControlDealt()
    {
        return totalTimeCrowdControlDealt;
    }

    /**
     * @return Integer
     */
    public Integer getTotalUnitsHealed()
    {
        return totalUnitsHealed;
    }

    /**
     * @return Integer
     */
    public Integer getTripleKills()
    {
        return tripleKills;
    }

    /**
     * @return Integer
     */
    public Integer getTrueDamageDealtPlayer()
    {
        return trueDamageDealtPlayer;
    }

    /**
     * @return Integer
     */
    public Integer getTrueDamageDealtToChampions()
    {
        return trueDamageDealtToChampions;
    }

    /**
     * @return Integer
     */
    public Integer getTrueDamageTaken()
    {
        return trueDamageTaken;
    }

    /**
     * @return Integer
     */
    public Integer getTurretsKilled()
    {
        return turretsKilled;
    }

    /**
     * @return Integer
     */
    public Integer getUnrealKills()
    {
        return unrealKills;
    }

    /**
     * @return Integer
     */

    public Integer getVictoryPointTotal()
    {
        return victoryPointTotal;
    }

    /**
     * @return Integer
     */
    public Integer getVisionWardsBought()
    {
        return visionWardsBought;
    }

    /**
     * @return Integer
     */
    public Integer getWardKilled()
    {
        return wardKilled;
    }

    /**
     * @return Integer
     */
    public Integer getWardPlaced()
    {
        return wardPlaced;
    }

    /**
     * @return Integer
     */
    public Boolean getWin()
    {
        return win;
    }

    /**
     * a {@code Map<String, String>} of all the fields in this class
     */
    public Map<String, String> getAllStats()
    {
        Map<String, String> map = new HashMap<>();
        Stream.of(this.getClass().getFields()).forEach(f -> {
            try
            {
                map.put(f.getName(), f.get(this).toString());
            } catch (Exception e)
            {
                e.printStackTrace();
            }
        });
        return map;
    }

    @Override
    public String toString()
    {
        return "RawStats [assists=" + assists + ", barracksKilled=" + barracksKilled + ", championsKilled=" + championsKilled + ", combatPlayerScore=" + combatPlayerScore + ", consumablesPurchased=" + consumablesPurchased + ", damageDealtPlayer=" + damageDealtPlayer + ", doubleKills=" + doubleKills + ", firstBlood=" + firstBlood + ", gold=" + gold + ", goldEarned=" + goldEarned + ", goldSpent=" + goldSpent + ", item0=" + item0 + ", item1=" + item1 + ", item2=" + item2 + ", item3=" + item3 + ", item4=" + item4 + ", item5=" + item5 + ", item6=" + item6 + ", itemsPurchased=" + itemsPurchased + ", killingSprees=" + killingSprees + ", largestCriticalStrike=" + largestCriticalStrike + ", largestKillingSpree=" + largestKillingSpree + ", largestMultiKill=" + largestMultiKill + ", legendaryItemsCreated=" + legendaryItemsCreated + ", level=" + level + ", magicDamageDealtPlayer=" + magicDamageDealtPlayer + ", magicDamageDealtToChampions=" + magicDamageDealtToChampions + ", magicDamageTaken=" + magicDamageTaken + ", minionsDenied=" + minionsDenied + ", minionsKilled=" + minionsKilled + ", neutralMinionsKilled=" + neutralMinionsKilled + ", neutralMinionsKilledEnemyJungle=" + neutralMinionsKilledEnemyJungle + ", neutralMinionsKilledYourJungle=" + neutralMinionsKilledYourJungle + ", nexusKilled=" + nexusKilled + ", nodeCapture=" + nodeCapture + ", nodeCaptureAssist=" + nodeCaptureAssist + ", nodeNeutralize=" + nodeNeutralize + ", nodeNeutralizeAssist=" + nodeNeutralizeAssist + ", numDeaths=" + numDeaths + ", numItemsBought=" + numItemsBought + ", objectivePlayerScore="
                + objectivePlayerScore + ", pentaKills=" + pentaKills + ", physicalDamageDealtPlayer=" + physicalDamageDealtPlayer + ", physicalDamageDealtToChampions=" + physicalDamageDealtToChampions + ", physicalDamageTaken=" + physicalDamageTaken + ", playerPosition=" + playerPosition + ", playerRole=" + playerRole + ", quadraKills=" + quadraKills + ", sightWardsBought=" + sightWardsBought + ", spell1Cast=" + spell1Cast + ", spell2Cast=" + spell2Cast + ", spell3Cast=" + spell3Cast + ", spell4Cast=" + spell4Cast + ", summonSpell1Cast=" + summonSpell1Cast + ", summonSpell2Cast=" + summonSpell2Cast + ", superMonsterKilled=" + superMonsterKilled + ", team=" + team + ", teamObjective=" + teamObjective + ", timePlayed=" + timePlayed + ", totalDamageDealt=" + totalDamageDealt + ", totalDamageDealtToChampions=" + totalDamageDealtToChampions + ", totalDamageTaken=" + totalDamageTaken + ", totalHeal=" + totalHeal + ", totalPlayerScore=" + totalPlayerScore + ", totalScoreRank=" + totalScoreRank + ", totalTimeCrowdControlDealt=" + totalTimeCrowdControlDealt + ", totalUnitsHealed=" + totalUnitsHealed + ", tripleKills=" + tripleKills + ", trueDamageDealtPlayer=" + trueDamageDealtPlayer + ", trueDamageDealtToChampions=" + trueDamageDealtToChampions + ", trueDamageTaken=" + trueDamageTaken + ", turretsKilled=" + turretsKilled + ", unrealKills=" + unrealKills + ", victoryPointTotal=" + victoryPointTotal + ", visionWardsBought=" + visionWardsBought + ", wardKilled=" + wardKilled + ", wardPlaced=" + wardPlaced + ", win=" + win + "]";
    }
}
