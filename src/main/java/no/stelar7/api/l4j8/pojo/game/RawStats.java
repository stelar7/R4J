package no.stelar7.api.l4j8.pojo.game;

import java.util.*;
import java.util.logging.*;
import java.util.stream.*;

public class RawStats
{
    
    public static final Logger LOGGER = Logger.getGlobal();
    
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
    private Integer totalDamageDealtToBuildings;
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
    
    @Override
    public boolean equals(final Object obj)
    {
        if (this == obj)
        {
            return true;
        }
        if (obj == null)
        {
            return false;
        }
        if (this.getClass() != obj.getClass())
        {
            return false;
        }
        final RawStats other = (RawStats) obj;
        if (this.assists == null)
        {
            if (other.assists != null)
            {
                return false;
            }
        } else if (!this.assists.equals(other.assists))
        {
            return false;
        }
        if (this.barracksKilled == null)
        {
            if (other.barracksKilled != null)
            {
                return false;
            }
        } else if (!this.barracksKilled.equals(other.barracksKilled))
        {
            return false;
        }
        if (this.championsKilled == null)
        {
            if (other.championsKilled != null)
            {
                return false;
            }
        } else if (!this.championsKilled.equals(other.championsKilled))
        {
            return false;
        }
        if (this.combatPlayerScore == null)
        {
            if (other.combatPlayerScore != null)
            {
                return false;
            }
        } else if (!this.combatPlayerScore.equals(other.combatPlayerScore))
        {
            return false;
        }
        if (this.consumablesPurchased == null)
        {
            if (other.consumablesPurchased != null)
            {
                return false;
            }
        } else if (!this.consumablesPurchased.equals(other.consumablesPurchased))
        {
            return false;
        }
        if (this.damageDealtPlayer == null)
        {
            if (other.damageDealtPlayer != null)
            {
                return false;
            }
        } else if (!this.damageDealtPlayer.equals(other.damageDealtPlayer))
        {
            return false;
        }
        if (this.doubleKills == null)
        {
            if (other.doubleKills != null)
            {
                return false;
            }
        } else if (!this.doubleKills.equals(other.doubleKills))
        {
            return false;
        }
        if (this.firstBlood == null)
        {
            if (other.firstBlood != null)
            {
                return false;
            }
        } else if (!this.firstBlood.equals(other.firstBlood))
        {
            return false;
        }
        if (this.gold == null)
        {
            if (other.gold != null)
            {
                return false;
            }
        } else if (!this.gold.equals(other.gold))
        {
            return false;
        }
        if (this.goldEarned == null)
        {
            if (other.goldEarned != null)
            {
                return false;
            }
        } else if (!this.goldEarned.equals(other.goldEarned))
        {
            return false;
        }
        if (this.goldSpent == null)
        {
            if (other.goldSpent != null)
            {
                return false;
            }
        } else if (!this.goldSpent.equals(other.goldSpent))
        {
            return false;
        }
        if (this.item0 == null)
        {
            if (other.item0 != null)
            {
                return false;
            }
        } else if (!this.item0.equals(other.item0))
        {
            return false;
        }
        if (this.item1 == null)
        {
            if (other.item1 != null)
            {
                return false;
            }
        } else if (!this.item1.equals(other.item1))
        {
            return false;
        }
        if (this.item2 == null)
        {
            if (other.item2 != null)
            {
                return false;
            }
        } else if (!this.item2.equals(other.item2))
        {
            return false;
        }
        if (this.item3 == null)
        {
            if (other.item3 != null)
            {
                return false;
            }
        } else if (!this.item3.equals(other.item3))
        {
            return false;
        }
        if (this.item4 == null)
        {
            if (other.item4 != null)
            {
                return false;
            }
        } else if (!this.item4.equals(other.item4))
        {
            return false;
        }
        if (this.item5 == null)
        {
            if (other.item5 != null)
            {
                return false;
            }
        } else if (!this.item5.equals(other.item5))
        {
            return false;
        }
        if (this.item6 == null)
        {
            if (other.item6 != null)
            {
                return false;
            }
        } else if (!this.item6.equals(other.item6))
        {
            return false;
        }
        if (this.itemsPurchased == null)
        {
            if (other.itemsPurchased != null)
            {
                return false;
            }
        } else if (!this.itemsPurchased.equals(other.itemsPurchased))
        {
            return false;
        }
        if (this.killingSprees == null)
        {
            if (other.killingSprees != null)
            {
                return false;
            }
        } else if (!this.killingSprees.equals(other.killingSprees))
        {
            return false;
        }
        if (this.largestCriticalStrike == null)
        {
            if (other.largestCriticalStrike != null)
            {
                return false;
            }
        } else if (!this.largestCriticalStrike.equals(other.largestCriticalStrike))
        {
            return false;
        }
        if (this.largestKillingSpree == null)
        {
            if (other.largestKillingSpree != null)
            {
                return false;
            }
        } else if (!this.largestKillingSpree.equals(other.largestKillingSpree))
        {
            return false;
        }
        if (this.largestMultiKill == null)
        {
            if (other.largestMultiKill != null)
            {
                return false;
            }
        } else if (!this.largestMultiKill.equals(other.largestMultiKill))
        {
            return false;
        }
        if (this.legendaryItemsCreated == null)
        {
            if (other.legendaryItemsCreated != null)
            {
                return false;
            }
        } else if (!this.legendaryItemsCreated.equals(other.legendaryItemsCreated))
        {
            return false;
        }
        if (this.level == null)
        {
            if (other.level != null)
            {
                return false;
            }
        } else if (!this.level.equals(other.level))
        {
            return false;
        }
        if (this.magicDamageDealtPlayer == null)
        {
            if (other.magicDamageDealtPlayer != null)
            {
                return false;
            }
        } else if (!this.magicDamageDealtPlayer.equals(other.magicDamageDealtPlayer))
        {
            return false;
        }
        if (this.magicDamageDealtToChampions == null)
        {
            if (other.magicDamageDealtToChampions != null)
            {
                return false;
            }
        } else if (!this.magicDamageDealtToChampions.equals(other.magicDamageDealtToChampions))
        {
            return false;
        }
        if (this.magicDamageTaken == null)
        {
            if (other.magicDamageTaken != null)
            {
                return false;
            }
        } else if (!this.magicDamageTaken.equals(other.magicDamageTaken))
        {
            return false;
        }
        if (this.minionsDenied == null)
        {
            if (other.minionsDenied != null)
            {
                return false;
            }
        } else if (!this.minionsDenied.equals(other.minionsDenied))
        {
            return false;
        }
        if (this.minionsKilled == null)
        {
            if (other.minionsKilled != null)
            {
                return false;
            }
        } else if (!this.minionsKilled.equals(other.minionsKilled))
        {
            return false;
        }
        if (this.neutralMinionsKilled == null)
        {
            if (other.neutralMinionsKilled != null)
            {
                return false;
            }
        } else if (!this.neutralMinionsKilled.equals(other.neutralMinionsKilled))
        {
            return false;
        }
        if (this.neutralMinionsKilledEnemyJungle == null)
        {
            if (other.neutralMinionsKilledEnemyJungle != null)
            {
                return false;
            }
        } else if (!this.neutralMinionsKilledEnemyJungle.equals(other.neutralMinionsKilledEnemyJungle))
        {
            return false;
        }
        if (this.neutralMinionsKilledYourJungle == null)
        {
            if (other.neutralMinionsKilledYourJungle != null)
            {
                return false;
            }
        } else if (!this.neutralMinionsKilledYourJungle.equals(other.neutralMinionsKilledYourJungle))
        {
            return false;
        }
        if (this.nexusKilled == null)
        {
            if (other.nexusKilled != null)
            {
                return false;
            }
        } else if (!this.nexusKilled.equals(other.nexusKilled))
        {
            return false;
        }
        if (this.nodeCapture == null)
        {
            if (other.nodeCapture != null)
            {
                return false;
            }
        } else if (!this.nodeCapture.equals(other.nodeCapture))
        {
            return false;
        }
        if (this.nodeCaptureAssist == null)
        {
            if (other.nodeCaptureAssist != null)
            {
                return false;
            }
        } else if (!this.nodeCaptureAssist.equals(other.nodeCaptureAssist))
        {
            return false;
        }
        if (this.nodeNeutralize == null)
        {
            if (other.nodeNeutralize != null)
            {
                return false;
            }
        } else if (!this.nodeNeutralize.equals(other.nodeNeutralize))
        {
            return false;
        }
        if (this.nodeNeutralizeAssist == null)
        {
            if (other.nodeNeutralizeAssist != null)
            {
                return false;
            }
        } else if (!this.nodeNeutralizeAssist.equals(other.nodeNeutralizeAssist))
        {
            return false;
        }
        if (this.numDeaths == null)
        {
            if (other.numDeaths != null)
            {
                return false;
            }
        } else if (!this.numDeaths.equals(other.numDeaths))
        {
            return false;
        }
        if (this.numItemsBought == null)
        {
            if (other.numItemsBought != null)
            {
                return false;
            }
        } else if (!this.numItemsBought.equals(other.numItemsBought))
        {
            return false;
        }
        if (this.objectivePlayerScore == null)
        {
            if (other.objectivePlayerScore != null)
            {
                return false;
            }
        } else if (!this.objectivePlayerScore.equals(other.objectivePlayerScore))
        {
            return false;
        }
        if (this.pentaKills == null)
        {
            if (other.pentaKills != null)
            {
                return false;
            }
        } else if (!this.pentaKills.equals(other.pentaKills))
        {
            return false;
        }
        if (this.physicalDamageDealtPlayer == null)
        {
            if (other.physicalDamageDealtPlayer != null)
            {
                return false;
            }
        } else if (!this.physicalDamageDealtPlayer.equals(other.physicalDamageDealtPlayer))
        {
            return false;
        }
        if (this.physicalDamageDealtToChampions == null)
        {
            if (other.physicalDamageDealtToChampions != null)
            {
                return false;
            }
        } else if (!this.physicalDamageDealtToChampions.equals(other.physicalDamageDealtToChampions))
        {
            return false;
        }
        if (this.physicalDamageTaken == null)
        {
            if (other.physicalDamageTaken != null)
            {
                return false;
            }
        } else if (!this.physicalDamageTaken.equals(other.physicalDamageTaken))
        {
            return false;
        }
        if (this.playerPosition == null)
        {
            if (other.playerPosition != null)
            {
                return false;
            }
        } else if (!this.playerPosition.equals(other.playerPosition))
        {
            return false;
        }
        if (this.playerRole == null)
        {
            if (other.playerRole != null)
            {
                return false;
            }
        } else if (!this.playerRole.equals(other.playerRole))
        {
            return false;
        }
        if (this.quadraKills == null)
        {
            if (other.quadraKills != null)
            {
                return false;
            }
        } else if (!this.quadraKills.equals(other.quadraKills))
        {
            return false;
        }
        if (this.sightWardsBought == null)
        {
            if (other.sightWardsBought != null)
            {
                return false;
            }
        } else if (!this.sightWardsBought.equals(other.sightWardsBought))
        {
            return false;
        }
        if (this.spell1Cast == null)
        {
            if (other.spell1Cast != null)
            {
                return false;
            }
        } else if (!this.spell1Cast.equals(other.spell1Cast))
        {
            return false;
        }
        if (this.spell2Cast == null)
        {
            if (other.spell2Cast != null)
            {
                return false;
            }
        } else if (!this.spell2Cast.equals(other.spell2Cast))
        {
            return false;
        }
        if (this.spell3Cast == null)
        {
            if (other.spell3Cast != null)
            {
                return false;
            }
        } else if (!this.spell3Cast.equals(other.spell3Cast))
        {
            return false;
        }
        if (this.spell4Cast == null)
        {
            if (other.spell4Cast != null)
            {
                return false;
            }
        } else if (!this.spell4Cast.equals(other.spell4Cast))
        {
            return false;
        }
        if (this.summonSpell1Cast == null)
        {
            if (other.summonSpell1Cast != null)
            {
                return false;
            }
        } else if (!this.summonSpell1Cast.equals(other.summonSpell1Cast))
        {
            return false;
        }
        if (this.summonSpell2Cast == null)
        {
            if (other.summonSpell2Cast != null)
            {
                return false;
            }
        } else if (!this.summonSpell2Cast.equals(other.summonSpell2Cast))
        {
            return false;
        }
        if (this.superMonsterKilled == null)
        {
            if (other.superMonsterKilled != null)
            {
                return false;
            }
        } else if (!this.superMonsterKilled.equals(other.superMonsterKilled))
        {
            return false;
        }
        if (this.team == null)
        {
            if (other.team != null)
            {
                return false;
            }
        } else if (!this.team.equals(other.team))
        {
            return false;
        }
        if (this.teamObjective == null)
        {
            if (other.teamObjective != null)
            {
                return false;
            }
        } else if (!this.teamObjective.equals(other.teamObjective))
        {
            return false;
        }
        if (this.timePlayed == null)
        {
            if (other.timePlayed != null)
            {
                return false;
            }
        } else if (!this.timePlayed.equals(other.timePlayed))
        {
            return false;
        }
        if (this.totalDamageDealt == null)
        {
            if (other.totalDamageDealt != null)
            {
                return false;
            }
        } else if (!this.totalDamageDealt.equals(other.totalDamageDealt))
        {
            return false;
        }
        if (this.totalDamageDealtToChampions == null)
        {
            if (other.totalDamageDealtToChampions != null)
            {
                return false;
            }
        } else if (!this.totalDamageDealtToChampions.equals(other.totalDamageDealtToChampions))
        {
            return false;
        }
        if (this.totalDamageTaken == null)
        {
            if (other.totalDamageTaken != null)
            {
                return false;
            }
        } else if (!this.totalDamageTaken.equals(other.totalDamageTaken))
        {
            return false;
        }
        if (this.totalHeal == null)
        {
            if (other.totalHeal != null)
            {
                return false;
            }
        } else if (!this.totalHeal.equals(other.totalHeal))
        {
            return false;
        }
        if (this.totalPlayerScore == null)
        {
            if (other.totalPlayerScore != null)
            {
                return false;
            }
        } else if (!this.totalPlayerScore.equals(other.totalPlayerScore))
        {
            return false;
        }
        if (this.totalScoreRank == null)
        {
            if (other.totalScoreRank != null)
            {
                return false;
            }
        } else if (!this.totalScoreRank.equals(other.totalScoreRank))
        {
            return false;
        }
        if (this.totalTimeCrowdControlDealt == null)
        {
            if (other.totalTimeCrowdControlDealt != null)
            {
                return false;
            }
        } else if (!this.totalTimeCrowdControlDealt.equals(other.totalTimeCrowdControlDealt))
        {
            return false;
        }
        if (this.totalUnitsHealed == null)
        {
            if (other.totalUnitsHealed != null)
            {
                return false;
            }
        } else if (!this.totalUnitsHealed.equals(other.totalUnitsHealed))
        {
            return false;
        }
        if (this.tripleKills == null)
        {
            if (other.tripleKills != null)
            {
                return false;
            }
        } else if (!this.tripleKills.equals(other.tripleKills))
        {
            return false;
        }
        if (this.trueDamageDealtPlayer == null)
        {
            if (other.trueDamageDealtPlayer != null)
            {
                return false;
            }
        } else if (!this.trueDamageDealtPlayer.equals(other.trueDamageDealtPlayer))
        {
            return false;
        }
        if (this.trueDamageDealtToChampions == null)
        {
            if (other.trueDamageDealtToChampions != null)
            {
                return false;
            }
        } else if (!this.trueDamageDealtToChampions.equals(other.trueDamageDealtToChampions))
        {
            return false;
        }
        if (this.trueDamageTaken == null)
        {
            if (other.trueDamageTaken != null)
            {
                return false;
            }
        } else if (!this.trueDamageTaken.equals(other.trueDamageTaken))
        {
            return false;
        }
        if (this.turretsKilled == null)
        {
            if (other.turretsKilled != null)
            {
                return false;
            }
        } else if (!this.turretsKilled.equals(other.turretsKilled))
        {
            return false;
        }
        if (this.unrealKills == null)
        {
            if (other.unrealKills != null)
            {
                return false;
            }
        } else if (!this.unrealKills.equals(other.unrealKills))
        {
            return false;
        }
        if (this.victoryPointTotal == null)
        {
            if (other.victoryPointTotal != null)
            {
                return false;
            }
        } else if (!this.victoryPointTotal.equals(other.victoryPointTotal))
        {
            return false;
        }
        if (this.visionWardsBought == null)
        {
            if (other.visionWardsBought != null)
            {
                return false;
            }
        } else if (!this.visionWardsBought.equals(other.visionWardsBought))
        {
            return false;
        }
        if (this.wardKilled == null)
        {
            if (other.wardKilled != null)
            {
                return false;
            }
        } else if (!this.wardKilled.equals(other.wardKilled))
        {
            return false;
        }
        if (this.wardPlaced == null)
        {
            if (other.wardPlaced != null)
            {
                return false;
            }
        } else if (!this.wardPlaced.equals(other.wardPlaced))
        {
            return false;
        }
        if (this.win == null)
        {
            if (other.win != null)
            {
                return false;
            }
        } else if (!this.win.equals(other.win))
        {
            return false;
        }
        return true;
    }
    
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
                                                               RawStats.LOGGER.log(Level.WARNING, e.getMessage(), e);
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
     * Gets total damage deals to buildings
     *
     * @return the boolean
     */
    public Integer getTotalDamageDealtToBuildings()
    {
        return this.totalDamageDealtToBuildings;
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
    
    @Override
    public int hashCode()
    {
        final int prime  = 31;
        int       result = 1;
        result = (prime * result) + ((this.assists == null) ? 0 : this.assists.hashCode());
        result = (prime * result) + ((this.barracksKilled == null) ? 0 : this.barracksKilled.hashCode());
        result = (prime * result) + ((this.championsKilled == null) ? 0 : this.championsKilled.hashCode());
        result = (prime * result) + ((this.combatPlayerScore == null) ? 0 : this.combatPlayerScore.hashCode());
        result = (prime * result) + ((this.consumablesPurchased == null) ? 0 : this.consumablesPurchased.hashCode());
        result = (prime * result) + ((this.damageDealtPlayer == null) ? 0 : this.damageDealtPlayer.hashCode());
        result = (prime * result) + ((this.doubleKills == null) ? 0 : this.doubleKills.hashCode());
        result = (prime * result) + ((this.firstBlood == null) ? 0 : this.firstBlood.hashCode());
        result = (prime * result) + ((this.gold == null) ? 0 : this.gold.hashCode());
        result = (prime * result) + ((this.goldEarned == null) ? 0 : this.goldEarned.hashCode());
        result = (prime * result) + ((this.goldSpent == null) ? 0 : this.goldSpent.hashCode());
        result = (prime * result) + ((this.item0 == null) ? 0 : this.item0.hashCode());
        result = (prime * result) + ((this.item1 == null) ? 0 : this.item1.hashCode());
        result = (prime * result) + ((this.item2 == null) ? 0 : this.item2.hashCode());
        result = (prime * result) + ((this.item3 == null) ? 0 : this.item3.hashCode());
        result = (prime * result) + ((this.item4 == null) ? 0 : this.item4.hashCode());
        result = (prime * result) + ((this.item5 == null) ? 0 : this.item5.hashCode());
        result = (prime * result) + ((this.item6 == null) ? 0 : this.item6.hashCode());
        result = (prime * result) + ((this.itemsPurchased == null) ? 0 : this.itemsPurchased.hashCode());
        result = (prime * result) + ((this.killingSprees == null) ? 0 : this.killingSprees.hashCode());
        result = (prime * result) + ((this.largestCriticalStrike == null) ? 0 : this.largestCriticalStrike.hashCode());
        result = (prime * result) + ((this.largestKillingSpree == null) ? 0 : this.largestKillingSpree.hashCode());
        result = (prime * result) + ((this.largestMultiKill == null) ? 0 : this.largestMultiKill.hashCode());
        result = (prime * result) + ((this.legendaryItemsCreated == null) ? 0 : this.legendaryItemsCreated.hashCode());
        result = (prime * result) + ((this.level == null) ? 0 : this.level.hashCode());
        result = (prime * result) + ((this.magicDamageDealtPlayer == null) ? 0 : this.magicDamageDealtPlayer.hashCode());
        result = (prime * result) + ((this.magicDamageDealtToChampions == null) ? 0 : this.magicDamageDealtToChampions.hashCode());
        result = (prime * result) + ((this.magicDamageTaken == null) ? 0 : this.magicDamageTaken.hashCode());
        result = (prime * result) + ((this.minionsDenied == null) ? 0 : this.minionsDenied.hashCode());
        result = (prime * result) + ((this.minionsKilled == null) ? 0 : this.minionsKilled.hashCode());
        result = (prime * result) + ((this.neutralMinionsKilled == null) ? 0 : this.neutralMinionsKilled.hashCode());
        result = (prime * result) + ((this.neutralMinionsKilledEnemyJungle == null) ? 0 : this.neutralMinionsKilledEnemyJungle.hashCode());
        result = (prime * result) + ((this.neutralMinionsKilledYourJungle == null) ? 0 : this.neutralMinionsKilledYourJungle.hashCode());
        result = (prime * result) + ((this.nexusKilled == null) ? 0 : this.nexusKilled.hashCode());
        result = (prime * result) + ((this.nodeCapture == null) ? 0 : this.nodeCapture.hashCode());
        result = (prime * result) + ((this.nodeCaptureAssist == null) ? 0 : this.nodeCaptureAssist.hashCode());
        result = (prime * result) + ((this.nodeNeutralize == null) ? 0 : this.nodeNeutralize.hashCode());
        result = (prime * result) + ((this.nodeNeutralizeAssist == null) ? 0 : this.nodeNeutralizeAssist.hashCode());
        result = (prime * result) + ((this.numDeaths == null) ? 0 : this.numDeaths.hashCode());
        result = (prime * result) + ((this.numItemsBought == null) ? 0 : this.numItemsBought.hashCode());
        result = (prime * result) + ((this.objectivePlayerScore == null) ? 0 : this.objectivePlayerScore.hashCode());
        result = (prime * result) + ((this.pentaKills == null) ? 0 : this.pentaKills.hashCode());
        result = (prime * result) + ((this.physicalDamageDealtPlayer == null) ? 0 : this.physicalDamageDealtPlayer.hashCode());
        result = (prime * result) + ((this.physicalDamageDealtToChampions == null) ? 0 : this.physicalDamageDealtToChampions.hashCode());
        result = (prime * result) + ((this.physicalDamageTaken == null) ? 0 : this.physicalDamageTaken.hashCode());
        result = (prime * result) + ((this.playerPosition == null) ? 0 : this.playerPosition.hashCode());
        result = (prime * result) + ((this.playerRole == null) ? 0 : this.playerRole.hashCode());
        result = (prime * result) + ((this.quadraKills == null) ? 0 : this.quadraKills.hashCode());
        result = (prime * result) + ((this.sightWardsBought == null) ? 0 : this.sightWardsBought.hashCode());
        result = (prime * result) + ((this.spell1Cast == null) ? 0 : this.spell1Cast.hashCode());
        result = (prime * result) + ((this.spell2Cast == null) ? 0 : this.spell2Cast.hashCode());
        result = (prime * result) + ((this.spell3Cast == null) ? 0 : this.spell3Cast.hashCode());
        result = (prime * result) + ((this.spell4Cast == null) ? 0 : this.spell4Cast.hashCode());
        result = (prime * result) + ((this.summonSpell1Cast == null) ? 0 : this.summonSpell1Cast.hashCode());
        result = (prime * result) + ((this.summonSpell2Cast == null) ? 0 : this.summonSpell2Cast.hashCode());
        result = (prime * result) + ((this.superMonsterKilled == null) ? 0 : this.superMonsterKilled.hashCode());
        result = (prime * result) + ((this.team == null) ? 0 : this.team.hashCode());
        result = (prime * result) + ((this.teamObjective == null) ? 0 : this.teamObjective.hashCode());
        result = (prime * result) + ((this.timePlayed == null) ? 0 : this.timePlayed.hashCode());
        result = (prime * result) + ((this.totalDamageDealt == null) ? 0 : this.totalDamageDealt.hashCode());
        result = (prime * result) + ((this.totalDamageDealtToChampions == null) ? 0 : this.totalDamageDealtToChampions.hashCode());
        result = (prime * result) + ((this.totalDamageTaken == null) ? 0 : this.totalDamageTaken.hashCode());
        result = (prime * result) + ((this.totalHeal == null) ? 0 : this.totalHeal.hashCode());
        result = (prime * result) + ((this.totalPlayerScore == null) ? 0 : this.totalPlayerScore.hashCode());
        result = (prime * result) + ((this.totalScoreRank == null) ? 0 : this.totalScoreRank.hashCode());
        result = (prime * result) + ((this.totalTimeCrowdControlDealt == null) ? 0 : this.totalTimeCrowdControlDealt.hashCode());
        result = (prime * result) + ((this.totalUnitsHealed == null) ? 0 : this.totalUnitsHealed.hashCode());
        result = (prime * result) + ((this.tripleKills == null) ? 0 : this.tripleKills.hashCode());
        result = (prime * result) + ((this.trueDamageDealtPlayer == null) ? 0 : this.trueDamageDealtPlayer.hashCode());
        result = (prime * result) + ((this.trueDamageDealtToChampions == null) ? 0 : this.trueDamageDealtToChampions.hashCode());
        result = (prime * result) + ((this.trueDamageTaken == null) ? 0 : this.trueDamageTaken.hashCode());
        result = (prime * result) + ((this.turretsKilled == null) ? 0 : this.turretsKilled.hashCode());
        result = (prime * result) + ((this.unrealKills == null) ? 0 : this.unrealKills.hashCode());
        result = (prime * result) + ((this.victoryPointTotal == null) ? 0 : this.victoryPointTotal.hashCode());
        result = (prime * result) + ((this.visionWardsBought == null) ? 0 : this.visionWardsBought.hashCode());
        result = (prime * result) + ((this.wardKilled == null) ? 0 : this.wardKilled.hashCode());
        result = (prime * result) + ((this.wardPlaced == null) ? 0 : this.wardPlaced.hashCode());
        result = (prime * result) + ((this.win == null) ? 0 : this.win.hashCode());
        return result;
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
     * Checks if is win.
     *
     * @return the boolean
     */
    public Boolean isWin()
    {
        return this.win;
    }
    
    @Override
    public String toString()
    {
        return "RawStats{" +
               "assists=" + assists +
               ", barracksKilled=" + barracksKilled +
               ", championsKilled=" + championsKilled +
               ", combatPlayerScore=" + combatPlayerScore +
               ", consumablesPurchased=" + consumablesPurchased +
               ", damageDealtPlayer=" + damageDealtPlayer +
               ", doubleKills=" + doubleKills +
               ", firstBlood=" + firstBlood +
               ", gold=" + gold +
               ", goldEarned=" + goldEarned +
               ", goldSpent=" + goldSpent +
               ", item0=" + item0 +
               ", item1=" + item1 +
               ", item2=" + item2 +
               ", item3=" + item3 +
               ", item4=" + item4 +
               ", item5=" + item5 +
               ", item6=" + item6 +
               ", itemsPurchased=" + itemsPurchased +
               ", killingSprees=" + killingSprees +
               ", largestCriticalStrike=" + largestCriticalStrike +
               ", largestKillingSpree=" + largestKillingSpree +
               ", largestMultiKill=" + largestMultiKill +
               ", legendaryItemsCreated=" + legendaryItemsCreated +
               ", level=" + level +
               ", magicDamageDealtPlayer=" + magicDamageDealtPlayer +
               ", magicDamageDealtToChampions=" + magicDamageDealtToChampions +
               ", magicDamageTaken=" + magicDamageTaken +
               ", minionsDenied=" + minionsDenied +
               ", minionsKilled=" + minionsKilled +
               ", neutralMinionsKilled=" + neutralMinionsKilled +
               ", neutralMinionsKilledEnemyJungle=" + neutralMinionsKilledEnemyJungle +
               ", neutralMinionsKilledYourJungle=" + neutralMinionsKilledYourJungle +
               ", nexusKilled=" + nexusKilled +
               ", nodeCapture=" + nodeCapture +
               ", nodeCaptureAssist=" + nodeCaptureAssist +
               ", nodeNeutralize=" + nodeNeutralize +
               ", nodeNeutralizeAssist=" + nodeNeutralizeAssist +
               ", numDeaths=" + numDeaths +
               ", numItemsBought=" + numItemsBought +
               ", objectivePlayerScore=" + objectivePlayerScore +
               ", pentaKills=" + pentaKills +
               ", physicalDamageDealtPlayer=" + physicalDamageDealtPlayer +
               ", physicalDamageDealtToChampions=" + physicalDamageDealtToChampions +
               ", physicalDamageTaken=" + physicalDamageTaken +
               ", playerPosition=" + playerPosition +
               ", playerRole=" + playerRole +
               ", quadraKills=" + quadraKills +
               ", sightWardsBought=" + sightWardsBought +
               ", spell1Cast=" + spell1Cast +
               ", spell2Cast=" + spell2Cast +
               ", spell3Cast=" + spell3Cast +
               ", spell4Cast=" + spell4Cast +
               ", summonSpell1Cast=" + summonSpell1Cast +
               ", summonSpell2Cast=" + summonSpell2Cast +
               ", superMonsterKilled=" + superMonsterKilled +
               ", team=" + team +
               ", teamObjective=" + teamObjective +
               ", timePlayed=" + timePlayed +
               ", totalDamageDealt=" + totalDamageDealt +
               ", totalDamageDealtToBuildings=" + totalDamageDealtToBuildings +
               ", totalDamageDealtToChampions=" + totalDamageDealtToChampions +
               ", totalDamageTaken=" + totalDamageTaken +
               ", totalHeal=" + totalHeal +
               ", totalPlayerScore=" + totalPlayerScore +
               ", totalScoreRank=" + totalScoreRank +
               ", totalTimeCrowdControlDealt=" + totalTimeCrowdControlDealt +
               ", totalUnitsHealed=" + totalUnitsHealed +
               ", tripleKills=" + tripleKills +
               ", trueDamageDealtPlayer=" + trueDamageDealtPlayer +
               ", trueDamageDealtToChampions=" + trueDamageDealtToChampions +
               ", trueDamageTaken=" + trueDamageTaken +
               ", turretsKilled=" + turretsKilled +
               ", unrealKills=" + unrealKills +
               ", victoryPointTotal=" + victoryPointTotal +
               ", visionWardsBought=" + visionWardsBought +
               ", wardKilled=" + wardKilled +
               ", wardPlaced=" + wardPlaced +
               ", win=" + win +
               '}';
    }
}
