package no.stelar7.api.l4j8.pojo.game;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

import no.stelar7.api.l4j8.basic.APIObject;

public class RawStats implements APIObject
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
     * a {@code Map<String, String>} of all the fields in this class
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
     * Amount of assists
     *
     * @return Integer
     */
    public Integer getAssists()
    {
        return this.assists;
    }

    /**
     * Number of enemy inhibitors killed.
     *
     * @return Integer
     */
    public Integer getBarracksKilled()
    {
        return this.barracksKilled;
    }

    /**
     * Amount of champions killed
     *
     * @return Integer
     */
    public Integer getChampionsKilled()
    {
        return this.championsKilled;
    }

    /**
     * Amount of points. (Dominion only)
     *
     * @return Integer
     */
    public Integer getCombatPlayerScore()
    {
        return this.combatPlayerScore;
    }

    /**
     * Amount of consumables purchased
     *
     * @return Integer
     */
    public Integer getConsumablesPurchased()
    {
        return this.consumablesPurchased;
    }

    /**
     * amount of
     *
     * @return Integer
     */
    public Integer getDamageDealtPlayer()
    {
        return this.damageDealtPlayer;
    }

    /**
     * @return Integer
     */
    public Integer getDoubleKills()
    {
        return this.doubleKills;
    }

    /**
     * @return Integer
     */
    public Integer getFirstBlood()
    {
        return this.firstBlood;
    }

    /**
     * @return Integer
     */
    public Integer getGold()
    {
        return this.gold;
    }

    /**
     * @return Integer
     */
    public Integer getGoldEarned()
    {
        return this.goldEarned;
    }

    /**
     * @return Integer
     */
    public Integer getGoldSpent()
    {
        return this.goldSpent;
    }

    /**
     * @return Integer
     */
    public Integer getItem0()
    {
        return this.item0;
    }

    /**
     * @return Integer
     */
    public Integer getItem1()
    {
        return this.item1;
    }

    /**
     * @return Integer
     */
    public Integer getItem2()
    {
        return this.item2;
    }

    /**
     * @return Integer
     */
    public Integer getItem3()
    {
        return this.item3;
    }

    /**
     * @return Integer
     */
    public Integer getItem4()
    {
        return this.item4;
    }

    /**
     * @return Integer
     */
    public Integer getItem5()
    {
        return this.item5;
    }

    /**
     * @return Integer
     */
    public Integer getItem6()
    {
        return this.item6;
    }

    /**
     * @return Integer
     */
    public Integer getItemsPurchased()
    {
        return this.itemsPurchased;
    }

    /**
     * @return Integer
     */
    public Integer getKillingSprees()
    {
        return this.killingSprees;
    }

    /**
     * @return Integer
     */
    public Integer getLargestCriticalStrike()
    {
        return this.largestCriticalStrike;
    }

    /**
     * @return Integer
     */
    public Integer getLargestKillingSpree()
    {
        return this.largestKillingSpree;
    }

    /**
     * @return Integer
     */
    public Integer getLargestMultiKill()
    {
        return this.largestMultiKill;
    }

    /**
     * @return Integer
     */
    public Integer getLegendaryItemsCreated()
    {
        return this.legendaryItemsCreated;
    }

    /**
     * @return Integer
     */
    public Integer getLevel()
    {
        return this.level;
    }

    /**
     * @return Integer
     */

    public Integer getMagicDamageDealtPlayer()
    {
        return this.magicDamageDealtPlayer;
    }

    /**
     * @return Integer
     */

    public Integer getMagicDamageDealtToChampions()
    {
        return this.magicDamageDealtToChampions;
    }

    /**
     * @return Integer
     */

    public Integer getMagicDamageTaken()
    {
        return this.magicDamageTaken;
    }

    /**
     * @return Integer
     */

    public Integer getMinionsDenied()
    {
        return this.minionsDenied;
    }

    /**
     * @return Integer
     */

    public Integer getMinionsKilled()
    {
        return this.minionsKilled;
    }

    /**
     * @return Integer
     */

    public Integer getNeutralMinionsKilled()
    {
        return this.neutralMinionsKilled;
    }

    /**
     * @return Integer
     */

    public Integer getNeutralMinionsKilledEnemyJungle()
    {
        return this.neutralMinionsKilledEnemyJungle;
    }

    /**
     * @return Integer
     */

    public Integer getNeutralMinionsKilledYourJungle()
    {
        return this.neutralMinionsKilledYourJungle;
    }

    /**
     * @return Integer
     */

    public Boolean getNexusKilled()
    {
        return this.nexusKilled;
    }

    /**
     * @return Integer
     */

    public Integer getNodeCapture()
    {
        return this.nodeCapture;
    }

    /**
     * @return Integer
     */

    public Integer getNodeCaptureAssist()
    {
        return this.nodeCaptureAssist;
    }

    /**
     * @return Integer
     */

    public Integer getNodeNeutralize()
    {
        return this.nodeNeutralize;
    }

    /**
     * @return Integer
     */

    public Integer getNodeNeutralizeAssist()
    {
        return this.nodeNeutralizeAssist;
    }

    /**
     * @return Integer
     */

    public Integer getNumDeaths()
    {
        return this.numDeaths;
    }

    /**
     * @return Integer
     */

    public Integer getNumItemsBought()
    {
        return this.numItemsBought;
    }

    /**
     * @return Integer
     */

    public Integer getObjectivePlayerScore()
    {
        return this.objectivePlayerScore;
    }

    /**
     * @return Integer
     */

    public Integer getPentaKills()
    {
        return this.pentaKills;
    }

    /**
     * @return Integer
     */

    public Integer getPhysicalDamageDealtPlayer()
    {
        return this.physicalDamageDealtPlayer;
    }

    /**
     * @return Integer
     */

    public Integer getPhysicalDamageDealtToChampions()
    {
        return this.physicalDamageDealtToChampions;
    }

    /**
     * @return Integer
     */

    public Integer getPhysicalDamageTaken()
    {
        return this.physicalDamageTaken;
    }

    /**
     * @return Integer
     */

    public Integer getPlayerPosition()
    {
        return this.playerPosition;
    }

    /**
     * @return Integer
     */

    public Integer getPlayerRole()
    {
        return this.playerRole;
    }

    /**
     * @return Integer
     */

    public Integer getQuadraKills()
    {
        return this.quadraKills;
    }

    /**
     * @return Integer
     */

    public Integer getSightWardsBought()
    {
        return this.sightWardsBought;
    }

    /**
     * @return Integer
     */

    public Integer getSpell1Cast()
    {
        return this.spell1Cast;
    }

    /**
     * @return Integer
     */

    public Integer getSpell2Cast()
    {
        return this.spell2Cast;
    }

    /**
     * @return Integer
     */

    public Integer getSpell3Cast()
    {
        return this.spell3Cast;
    }

    /**
     * @return Integer
     */

    public Integer getSpell4Cast()
    {
        return this.spell4Cast;
    }

    /**
     * @return Integer
     */

    public Integer getSummonSpell1Cast()
    {
        return this.summonSpell1Cast;
    }

    /**
     * @return Integer
     */

    public Integer getSummonSpell2Cast()
    {
        return this.summonSpell2Cast;
    }

    /**
     * @return Integer
     */

    public Integer getSuperMonsterKilled()
    {
        return this.superMonsterKilled;
    }

    /**
     * @return Integer
     */

    public Integer getTeam()
    {
        return this.team;
    }

    /**
     * @return Integer
     */
    public Integer getTeamObjective()
    {
        return this.teamObjective;
    }

    /**
     * @return Integer
     */
    public Integer getTimePlayed()
    {
        return this.timePlayed;
    }

    /**
     * @return Integer
     */
    public Integer getTotalDamageDealt()
    {
        return this.totalDamageDealt;
    }

    /**
     * @return Integer
     */
    public Integer getTotalDamageDealtToChampions()
    {
        return this.totalDamageDealtToChampions;
    }

    /**
     * @return Integer
     */
    public Integer getTotalDamageTaken()
    {
        return this.totalDamageTaken;
    }

    /**
     * @return Integer
     */
    public Integer getTotalHeal()
    {
        return this.totalHeal;
    }

    /**
     * @return Integer
     */
    public Integer getTotalPlayerScore()
    {
        return this.totalPlayerScore;
    }

    /**
     * @return Integer
     */
    public Integer getTotalScoreRank()
    {
        return this.totalScoreRank;
    }

    /**
     * @return Integer
     */
    public Integer getTotalTimeCrowdControlDealt()
    {
        return this.totalTimeCrowdControlDealt;
    }

    /**
     * @return Integer
     */
    public Integer getTotalUnitsHealed()
    {
        return this.totalUnitsHealed;
    }

    /**
     * @return Integer
     */
    public Integer getTripleKills()
    {
        return this.tripleKills;
    }

    /**
     * @return Integer
     */
    public Integer getTrueDamageDealtPlayer()
    {
        return this.trueDamageDealtPlayer;
    }

    /**
     * @return Integer
     */
    public Integer getTrueDamageDealtToChampions()
    {
        return this.trueDamageDealtToChampions;
    }

    /**
     * @return Integer
     */
    public Integer getTrueDamageTaken()
    {
        return this.trueDamageTaken;
    }

    /**
     * @return Integer
     */
    public Integer getTurretsKilled()
    {
        return this.turretsKilled;
    }

    /**
     * @return Integer
     */
    public Integer getUnrealKills()
    {
        return this.unrealKills;
    }

    /**
     * @return Integer
     */

    public Integer getVictoryPointTotal()
    {
        return this.victoryPointTotal;
    }

    /**
     * @return Integer
     */
    public Integer getVisionWardsBought()
    {
        return this.visionWardsBought;
    }

    /**
     * @return Integer
     */
    public Integer getWardKilled()
    {
        return this.wardKilled;
    }

    /**
     * @return Integer
     */
    public Integer getWardPlaced()
    {
        return this.wardPlaced;
    }

    /**
     * @return Integer
     */
    public Boolean getWin()
    {
        return this.win;
    }

    @Override
    public String toString()
    {
        return "RawStats [assists=" + this.assists + ", barracksKilled=" + this.barracksKilled + ", championsKilled=" + this.championsKilled + ", combatPlayerScore=" + this.combatPlayerScore + ", consumablesPurchased=" + this.consumablesPurchased + ", damageDealtPlayer=" + this.damageDealtPlayer + ", doubleKills=" + this.doubleKills + ", firstBlood=" + this.firstBlood + ", gold=" + this.gold + ", goldEarned=" + this.goldEarned + ", goldSpent=" + this.goldSpent + ", item0=" + this.item0 + ", item1=" + this.item1 + ", item2=" + this.item2 + ", item3=" + this.item3 + ", item4=" + this.item4 + ", item5=" + this.item5 + ", item6=" + this.item6 + ", itemsPurchased=" + this.itemsPurchased + ", killingSprees=" + this.killingSprees + ", largestCriticalStrike=" + this.largestCriticalStrike + ", largestKillingSpree=" + this.largestKillingSpree + ", largestMultiKill=" + this.largestMultiKill + ", legendaryItemsCreated=" + this.legendaryItemsCreated + ", level=" + this.level + ", magicDamageDealtPlayer=" + this.magicDamageDealtPlayer + ", magicDamageDealtToChampions=" + this.magicDamageDealtToChampions + ", magicDamageTaken=" + this.magicDamageTaken + ", minionsDenied=" + this.minionsDenied + ", minionsKilled=" + this.minionsKilled + ", neutralMinionsKilled=" + this.neutralMinionsKilled + ", neutralMinionsKilledEnemyJungle=" + this.neutralMinionsKilledEnemyJungle + ", neutralMinionsKilledYourJungle=" + this.neutralMinionsKilledYourJungle + ", nexusKilled=" + this.nexusKilled + ", nodeCapture=" + this.nodeCapture + ", nodeCaptureAssist=" + this.nodeCaptureAssist + ", nodeNeutralize="
                + this.nodeNeutralize + ", nodeNeutralizeAssist=" + this.nodeNeutralizeAssist + ", numDeaths=" + this.numDeaths + ", numItemsBought=" + this.numItemsBought + ", objectivePlayerScore=" + this.objectivePlayerScore + ", pentaKills=" + this.pentaKills + ", physicalDamageDealtPlayer=" + this.physicalDamageDealtPlayer + ", physicalDamageDealtToChampions=" + this.physicalDamageDealtToChampions + ", physicalDamageTaken=" + this.physicalDamageTaken + ", playerPosition=" + this.playerPosition + ", playerRole=" + this.playerRole + ", quadraKills=" + this.quadraKills + ", sightWardsBought=" + this.sightWardsBought + ", spell1Cast=" + this.spell1Cast + ", spell2Cast=" + this.spell2Cast + ", spell3Cast=" + this.spell3Cast + ", spell4Cast=" + this.spell4Cast + ", summonSpell1Cast=" + this.summonSpell1Cast + ", summonSpell2Cast=" + this.summonSpell2Cast + ", superMonsterKilled=" + this.superMonsterKilled + ", team=" + this.team + ", teamObjective=" + this.teamObjective + ", timePlayed=" + this.timePlayed + ", totalDamageDealt=" + this.totalDamageDealt + ", totalDamageDealtToChampions=" + this.totalDamageDealtToChampions + ", totalDamageTaken=" + this.totalDamageTaken + ", totalHeal=" + this.totalHeal + ", totalPlayerScore=" + this.totalPlayerScore + ", totalScoreRank=" + this.totalScoreRank + ", totalTimeCrowdControlDealt=" + this.totalTimeCrowdControlDealt + ", totalUnitsHealed=" + this.totalUnitsHealed + ", tripleKills=" + this.tripleKills + ", trueDamageDealtPlayer=" + this.trueDamageDealtPlayer + ", trueDamageDealtToChampions=" + this.trueDamageDealtToChampions
                + ", trueDamageTaken=" + this.trueDamageTaken + ", turretsKilled=" + this.turretsKilled + ", unrealKills=" + this.unrealKills + ", victoryPointTotal=" + this.victoryPointTotal + ", visionWardsBought=" + this.visionWardsBought + ", wardKilled=" + this.wardKilled + ", wardPlaced=" + this.wardPlaced + ", win=" + this.win + "]";
    }
}
