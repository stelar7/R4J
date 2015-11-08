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
    public Integer getAssists()
    {
        return this.assists;
    }

    /**
     * Gets the barracks killed.
     *
     * @return the barracks killed
     */
    public Integer getBarracksKilled()
    {
        return this.barracksKilled;
    }

    /**
     * Gets the champions killed.
     *
     * @return the champions killed
     */
    public Integer getChampionsKilled()
    {
        return this.championsKilled;
    }

    /**
     * Gets the combat player score.
     *
     * @return the combat player score
     */
    public Integer getCombatPlayerScore()
    {
        return this.combatPlayerScore;
    }

    /**
     * Gets the consumables purchased.
     *
     * @return the consumables purchased
     */
    public Integer getConsumablesPurchased()
    {
        return this.consumablesPurchased;
    }

    /**
     * Gets the damage dealt player.
     *
     * @return the damage dealt player
     */
    public Integer getDamageDealtPlayer()
    {
        return this.damageDealtPlayer;
    }

    /**
     * Gets the double kills.
     *
     * @return the double kills
     */
    public Integer getDoubleKills()
    {
        return this.doubleKills;
    }

    /**
     * Gets the first blood.
     *
     * @return the first blood
     */
    public Integer getFirstBlood()
    {
        return this.firstBlood;
    }

    /**
     * Gets the gold.
     *
     * @return the gold
     */
    public Integer getGold()
    {
        return this.gold;
    }

    /**
     * Gets the gold earned.
     *
     * @return the gold earned
     */
    public Integer getGoldEarned()
    {
        return this.goldEarned;
    }

    /**
     * Gets the gold spent.
     *
     * @return the gold spent
     */
    public Integer getGoldSpent()
    {
        return this.goldSpent;
    }

    /**
     * Gets the item0.
     *
     * @return the item0
     */
    public Integer getItem0()
    {
        return this.item0;
    }

    /**
     * Gets the item1.
     *
     * @return the item1
     */
    public Integer getItem1()
    {
        return this.item1;
    }

    /**
     * Gets the item2.
     *
     * @return the item2
     */
    public Integer getItem2()
    {
        return this.item2;
    }

    /**
     * Gets the item3.
     *
     * @return the item3
     */
    public Integer getItem3()
    {
        return this.item3;
    }

    /**
     * Gets the item4.
     *
     * @return the item4
     */
    public Integer getItem4()
    {
        return this.item4;
    }

    /**
     * Gets the item5.
     *
     * @return the item5
     */
    public Integer getItem5()
    {
        return this.item5;
    }

    /**
     * Gets the item6.
     *
     * @return the item6
     */
    public Integer getItem6()
    {
        return this.item6;
    }

    /**
     * Gets the items purchased.
     *
     * @return the items purchased
     */
    public Integer getItemsPurchased()
    {
        return this.itemsPurchased;
    }

    /**
     * Gets the killing sprees.
     *
     * @return the killing sprees
     */
    public Integer getKillingSprees()
    {
        return this.killingSprees;
    }

    /**
     * Gets the largest critical strike.
     *
     * @return the largest critical strike
     */
    public Integer getLargestCriticalStrike()
    {
        return this.largestCriticalStrike;
    }

    /**
     * Gets the largest killing spree.
     *
     * @return the largest killing spree
     */
    public Integer getLargestKillingSpree()
    {
        return this.largestKillingSpree;
    }

    /**
     * Gets the largest multi kill.
     *
     * @return the largest multi kill
     */
    public Integer getLargestMultiKill()
    {
        return this.largestMultiKill;
    }

    /**
     * Gets the legendary items created.
     *
     * @return the legendary items created
     */
    public Integer getLegendaryItemsCreated()
    {
        return this.legendaryItemsCreated;
    }

    /**
     * Gets the level.
     *
     * @return the level
     */
    public Integer getLevel()
    {
        return this.level;
    }

    /**
     * Gets the magic damage dealt player.
     *
     * @return the magic damage dealt player
     */
    public Integer getMagicDamageDealtPlayer()
    {
        return this.magicDamageDealtPlayer;
    }

    /**
     * Gets the magic damage dealt to champions.
     *
     * @return the magic damage dealt to champions
     */
    public Integer getMagicDamageDealtToChampions()
    {
        return this.magicDamageDealtToChampions;
    }

    /**
     * Gets the magic damage taken.
     *
     * @return the magic damage taken
     */
    public Integer getMagicDamageTaken()
    {
        return this.magicDamageTaken;
    }

    /**
     * Gets the minions denied.
     *
     * @return the minions denied
     */
    public Integer getMinionsDenied()
    {
        return this.minionsDenied;
    }

    /**
     * Gets the minions killed.
     *
     * @return the minions killed
     */
    public Integer getMinionsKilled()
    {
        return this.minionsKilled;
    }

    /**
     * Gets the neutral minions killed.
     *
     * @return the neutral minions killed
     */
    public Integer getNeutralMinionsKilled()
    {
        return this.neutralMinionsKilled;
    }

    /**
     * Gets the neutral minions killed enemy jungle.
     *
     * @return the neutral minions killed enemy jungle
     */
    public Integer getNeutralMinionsKilledEnemyJungle()
    {
        return this.neutralMinionsKilledEnemyJungle;
    }

    /**
     * Gets the neutral minions killed your jungle.
     *
     * @return the neutral minions killed your jungle
     */
    public Integer getNeutralMinionsKilledYourJungle()
    {
        return this.neutralMinionsKilledYourJungle;
    }

    /**
     * Checks if is nexus killed.
     *
     * @return the boolean
     */
    public Boolean isNexusKilled()
    {
        return this.nexusKilled;
    }

    /**
     * Gets the node capture.
     *
     * @return the node capture
     */
    public Integer getNodeCapture()
    {
        return this.nodeCapture;
    }

    /**
     * Gets the node capture assist.
     *
     * @return the node capture assist
     */
    public Integer getNodeCaptureAssist()
    {
        return this.nodeCaptureAssist;
    }

    /**
     * Gets the node neutralize.
     *
     * @return the node neutralize
     */
    public Integer getNodeNeutralize()
    {
        return this.nodeNeutralize;
    }

    /**
     * Gets the node neutralize assist.
     *
     * @return the node neutralize assist
     */
    public Integer getNodeNeutralizeAssist()
    {
        return this.nodeNeutralizeAssist;
    }

    /**
     * Gets the num deaths.
     *
     * @return the num deaths
     */
    public Integer getNumDeaths()
    {
        return this.numDeaths;
    }

    /**
     * Gets the num items bought.
     *
     * @return the num items bought
     */
    public Integer getNumItemsBought()
    {
        return this.numItemsBought;
    }

    /**
     * Gets the objective player score.
     *
     * @return the objective player score
     */
    public Integer getObjectivePlayerScore()
    {
        return this.objectivePlayerScore;
    }

    /**
     * Gets the penta kills.
     *
     * @return the penta kills
     */
    public Integer getPentaKills()
    {
        return this.pentaKills;
    }

    /**
     * Gets the physical damage dealt player.
     *
     * @return the physical damage dealt player
     */
    public Integer getPhysicalDamageDealtPlayer()
    {
        return this.physicalDamageDealtPlayer;
    }

    /**
     * Gets the physical damage dealt to champions.
     *
     * @return the physical damage dealt to champions
     */
    public Integer getPhysicalDamageDealtToChampions()
    {
        return this.physicalDamageDealtToChampions;
    }

    /**
     * Gets the physical damage taken.
     *
     * @return the physical damage taken
     */
    public Integer getPhysicalDamageTaken()
    {
        return this.physicalDamageTaken;
    }

    /**
     * Gets the player position.
     *
     * @return the player position
     */
    public Integer getPlayerPosition()
    {
        return this.playerPosition;
    }

    /**
     * Gets the player role.
     *
     * @return the player role
     */
    public Integer getPlayerRole()
    {
        return this.playerRole;
    }

    /**
     * Gets the quadra kills.
     *
     * @return the quadra kills
     */
    public Integer getQuadraKills()
    {
        return this.quadraKills;
    }

    /**
     * Gets the sight wards bought.
     *
     * @return the sight wards bought
     */
    public Integer getSightWardsBought()
    {
        return this.sightWardsBought;
    }

    /**
     * Gets the spell1 cast.
     *
     * @return the spell1 cast
     */
    public Integer getSpell1Cast()
    {
        return this.spell1Cast;
    }

    /**
     * Gets the spell2 cast.
     *
     * @return the spell2 cast
     */
    public Integer getSpell2Cast()
    {
        return this.spell2Cast;
    }

    /**
     * Gets the spell3 cast.
     *
     * @return the spell3 cast
     */
    public Integer getSpell3Cast()
    {
        return this.spell3Cast;
    }

    /**
     * Gets the spell4 cast.
     *
     * @return the spell4 cast
     */
    public Integer getSpell4Cast()
    {
        return this.spell4Cast;
    }

    /**
     * Gets the summon spell1 cast.
     *
     * @return the summon spell1 cast
     */
    public Integer getSummonSpell1Cast()
    {
        return this.summonSpell1Cast;
    }

    /**
     * Gets the summon spell2 cast.
     *
     * @return the summon spell2 cast
     */
    public Integer getSummonSpell2Cast()
    {
        return this.summonSpell2Cast;
    }

    /**
     * Gets the super monster killed.
     *
     * @return the super monster killed
     */
    public Integer getSuperMonsterKilled()
    {
        return this.superMonsterKilled;
    }

    /**
     * Gets the team.
     *
     * @return the team
     */
    public Integer getTeam()
    {
        return this.team;
    }

    /**
     * Gets the team objective.
     *
     * @return the team objective
     */
    public Integer getTeamObjective()
    {
        return this.teamObjective;
    }

    /**
     * Gets the time played.
     *
     * @return the time played
     */
    public Integer getTimePlayed()
    {
        return this.timePlayed;
    }

    /**
     * Gets the total damage dealt.
     *
     * @return the total damage dealt
     */
    public Integer getTotalDamageDealt()
    {
        return this.totalDamageDealt;
    }

    /**
     * Gets the total damage dealt to champions.
     *
     * @return the total damage dealt to champions
     */
    public Integer getTotalDamageDealtToChampions()
    {
        return this.totalDamageDealtToChampions;
    }

    /**
     * Gets the total damage taken.
     *
     * @return the total damage taken
     */
    public Integer getTotalDamageTaken()
    {
        return this.totalDamageTaken;
    }

    /**
     * Gets the total heal.
     *
     * @return the total heal
     */
    public Integer getTotalHeal()
    {
        return this.totalHeal;
    }

    /**
     * Gets the total player score.
     *
     * @return the total player score
     */
    public Integer getTotalPlayerScore()
    {
        return this.totalPlayerScore;
    }

    /**
     * Gets the total score rank.
     *
     * @return the total score rank
     */
    public Integer getTotalScoreRank()
    {
        return this.totalScoreRank;
    }

    /**
     * Gets the total time crowd control dealt.
     *
     * @return the total time crowd control dealt
     */
    public Integer getTotalTimeCrowdControlDealt()
    {
        return this.totalTimeCrowdControlDealt;
    }

    /**
     * Gets the total units healed.
     *
     * @return the total units healed
     */
    public Integer getTotalUnitsHealed()
    {
        return this.totalUnitsHealed;
    }

    /**
     * Gets the triple kills.
     *
     * @return the triple kills
     */
    public Integer getTripleKills()
    {
        return this.tripleKills;
    }

    /**
     * Gets the true damage dealt player.
     *
     * @return the true damage dealt player
     */
    public Integer getTrueDamageDealtPlayer()
    {
        return this.trueDamageDealtPlayer;
    }

    /**
     * Gets the true damage dealt to champions.
     *
     * @return the true damage dealt to champions
     */
    public Integer getTrueDamageDealtToChampions()
    {
        return this.trueDamageDealtToChampions;
    }

    /**
     * Gets the true damage taken.
     *
     * @return the true damage taken
     */
    public Integer getTrueDamageTaken()
    {
        return this.trueDamageTaken;
    }

    /**
     * Gets the turrets killed.
     *
     * @return the turrets killed
     */
    public Integer getTurretsKilled()
    {
        return this.turretsKilled;
    }

    /**
     * Gets the unreal kills.
     *
     * @return the unreal kills
     */
    public Integer getUnrealKills()
    {
        return this.unrealKills;
    }

    /**
     * Gets the victory point total.
     *
     * @return the victory point total
     */
    public Integer getVictoryPointTotal()
    {
        return this.victoryPointTotal;
    }

    /**
     * Gets the vision wards bought.
     *
     * @return the vision wards bought
     */
    public Integer getVisionWardsBought()
    {
        return this.visionWardsBought;
    }

    /**
     * Gets the ward killed.
     *
     * @return the ward killed
     */
    public Integer getWardKilled()
    {
        return this.wardKilled;
    }

    /**
     * Gets the ward placed.
     *
     * @return the ward placed
     */
    public Integer getWardPlaced()
    {
        return this.wardPlaced;
    }

    /**
     * Checks if is win.
     *
     * @return the boolean
     */
    public Boolean isWin()
    {
        return this.win;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString()
    {
        return "RawStats [assists=" + this.assists + ", barracksKilled=" + this.barracksKilled + ", championsKilled=" + this.championsKilled + ", combatPlayerScore=" + this.combatPlayerScore + ", consumablesPurchased=" + this.consumablesPurchased + ", damageDealtPlayer=" + this.damageDealtPlayer + ", doubleKills=" + this.doubleKills + ", firstBlood=" + this.firstBlood + ", gold=" + this.gold + ", goldEarned=" + this.goldEarned + ", goldSpent=" + this.goldSpent + ", item0=" + this.item0 + ", item1=" + this.item1 + ", item2=" + this.item2 + ", item3=" + this.item3 + ", item4=" + this.item4 + ", item5=" + this.item5 + ", item6=" + this.item6 + ", itemsPurchased=" + this.itemsPurchased + ", killingSprees=" + this.killingSprees + ", largestCriticalStrike=" + this.largestCriticalStrike + ", largestKillingSpree=" + this.largestKillingSpree + ", largestMultiKill=" + this.largestMultiKill + ", legendaryItemsCreated=" + this.legendaryItemsCreated + ", level=" + this.level + ", magicDamageDealtPlayer=" + this.magicDamageDealtPlayer + ", magicDamageDealtToChampions=" + this.magicDamageDealtToChampions + ", magicDamageTaken=" + this.magicDamageTaken + ", minionsDenied=" + this.minionsDenied + ", minionsKilled=" + this.minionsKilled + ", neutralMinionsKilled=" + this.neutralMinionsKilled + ", neutralMinionsKilledEnemyJungle=" + this.neutralMinionsKilledEnemyJungle + ", neutralMinionsKilledYourJungle=" + this.neutralMinionsKilledYourJungle + ", nexusKilled=" + this.nexusKilled + ", nodeCapture=" + this.nodeCapture + ", nodeCaptureAssist=" + this.nodeCaptureAssist + ", nodeNeutralize="
                + this.nodeNeutralize + ", nodeNeutralizeAssist=" + this.nodeNeutralizeAssist + ", numDeaths=" + this.numDeaths + ", numItemsBought=" + this.numItemsBought + ", objectivePlayerScore=" + this.objectivePlayerScore + ", pentaKills=" + this.pentaKills + ", physicalDamageDealtPlayer=" + this.physicalDamageDealtPlayer + ", physicalDamageDealtToChampions=" + this.physicalDamageDealtToChampions + ", physicalDamageTaken=" + this.physicalDamageTaken + ", playerPosition=" + this.playerPosition + ", playerRole=" + this.playerRole + ", quadraKills=" + this.quadraKills + ", sightWardsBought=" + this.sightWardsBought + ", spell1Cast=" + this.spell1Cast + ", spell2Cast=" + this.spell2Cast + ", spell3Cast=" + this.spell3Cast + ", spell4Cast=" + this.spell4Cast + ", summonSpell1Cast=" + this.summonSpell1Cast + ", summonSpell2Cast=" + this.summonSpell2Cast + ", superMonsterKilled=" + this.superMonsterKilled + ", team=" + this.team + ", teamObjective=" + this.teamObjective + ", timePlayed=" + this.timePlayed + ", totalDamageDealt=" + this.totalDamageDealt + ", totalDamageDealtToChampions=" + this.totalDamageDealtToChampions + ", totalDamageTaken=" + this.totalDamageTaken + ", totalHeal=" + this.totalHeal + ", totalPlayerScore=" + this.totalPlayerScore + ", totalScoreRank=" + this.totalScoreRank + ", totalTimeCrowdControlDealt=" + this.totalTimeCrowdControlDealt + ", totalUnitsHealed=" + this.totalUnitsHealed + ", tripleKills=" + this.tripleKills + ", trueDamageDealtPlayer=" + this.trueDamageDealtPlayer + ", trueDamageDealtToChampions=" + this.trueDamageDealtToChampions
                + ", trueDamageTaken=" + this.trueDamageTaken + ", turretsKilled=" + this.turretsKilled + ", unrealKills=" + this.unrealKills + ", victoryPointTotal=" + this.victoryPointTotal + ", visionWardsBought=" + this.visionWardsBought + ", wardKilled=" + this.wardKilled + ", wardPlaced=" + this.wardPlaced + ", win=" + this.win + "]";
    }
}
