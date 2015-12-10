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
        final ParticipantStats other = (ParticipantStats) obj;
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
        if (this.champLevel == null)
        {
            if (other.champLevel != null)
            {
                return false;
            }
        } else if (!this.champLevel.equals(other.champLevel))
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
        if (this.deaths == null)
        {
            if (other.deaths != null)
            {
                return false;
            }
        } else if (!this.deaths.equals(other.deaths))
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
        if (this.firstBloodAssist == null)
        {
            if (other.firstBloodAssist != null)
            {
                return false;
            }
        } else if (!this.firstBloodAssist.equals(other.firstBloodAssist))
        {
            return false;
        }
        if (this.firstBloodKill == null)
        {
            if (other.firstBloodKill != null)
            {
                return false;
            }
        } else if (!this.firstBloodKill.equals(other.firstBloodKill))
        {
            return false;
        }
        if (this.firstInhibitorAssist == null)
        {
            if (other.firstInhibitorAssist != null)
            {
                return false;
            }
        } else if (!this.firstInhibitorAssist.equals(other.firstInhibitorAssist))
        {
            return false;
        }
        if (this.firstInhibitorKill == null)
        {
            if (other.firstInhibitorKill != null)
            {
                return false;
            }
        } else if (!this.firstInhibitorKill.equals(other.firstInhibitorKill))
        {
            return false;
        }
        if (this.firstTowerAssist == null)
        {
            if (other.firstTowerAssist != null)
            {
                return false;
            }
        } else if (!this.firstTowerAssist.equals(other.firstTowerAssist))
        {
            return false;
        }
        if (this.firstTowerKill == null)
        {
            if (other.firstTowerKill != null)
            {
                return false;
            }
        } else if (!this.firstTowerKill.equals(other.firstTowerKill))
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
        if (this.inhibitorKills == null)
        {
            if (other.inhibitorKills != null)
            {
                return false;
            }
        } else if (!this.inhibitorKills.equals(other.inhibitorKills))
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
        if (this.kills == null)
        {
            if (other.kills != null)
            {
                return false;
            }
        } else if (!this.kills.equals(other.kills))
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
        if (this.magicDamageDealt == null)
        {
            if (other.magicDamageDealt != null)
            {
                return false;
            }
        } else if (!this.magicDamageDealt.equals(other.magicDamageDealt))
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
        if (this.neutralMinionsKilledTeamJungle == null)
        {
            if (other.neutralMinionsKilledTeamJungle != null)
            {
                return false;
            }
        } else if (!this.neutralMinionsKilledTeamJungle.equals(other.neutralMinionsKilledTeamJungle))
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
        if (this.physicalDamageDealt == null)
        {
            if (other.physicalDamageDealt != null)
            {
                return false;
            }
        } else if (!this.physicalDamageDealt.equals(other.physicalDamageDealt))
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
        if (this.sightWardsBoughtInGame == null)
        {
            if (other.sightWardsBoughtInGame != null)
            {
                return false;
            }
        } else if (!this.sightWardsBoughtInGame.equals(other.sightWardsBoughtInGame))
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
        if (this.towerKills == null)
        {
            if (other.towerKills != null)
            {
                return false;
            }
        } else if (!this.towerKills.equals(other.towerKills))
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
        if (this.trueDamageDealt == null)
        {
            if (other.trueDamageDealt != null)
            {
                return false;
            }
        } else if (!this.trueDamageDealt.equals(other.trueDamageDealt))
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
        if (this.visionWardsBoughtInGame == null)
        {
            if (other.visionWardsBoughtInGame != null)
            {
                return false;
            }
        } else if (!this.visionWardsBoughtInGame.equals(other.visionWardsBoughtInGame))
        {
            return false;
        }
        if (this.wardsKilled == null)
        {
            if (other.wardsKilled != null)
            {
                return false;
            }
        } else if (!this.wardsKilled.equals(other.wardsKilled))
        {
            return false;
        }
        if (this.wardsPlaced == null)
        {
            if (other.wardsPlaced != null)
            {
                return false;
            }
        } else if (!this.wardsPlaced.equals(other.wardsPlaced))
        {
            return false;
        }
        if (this.winner == null)
        {
            if (other.winner != null)
            {
                return false;
            }
        } else if (!this.winner.equals(other.winner))
        {
            return false;
        }
        return true;
    }

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
        return this.magicDamageTaken;
    }

    /**
     * Gets the minions killed.
     *
     * @return the minions killed
     */
    public Long getMinionsKilled()
    {
        return this.minionsKilled;
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
     * Gets the tower kills.
     *
     * @return the tower kills
     */
    public Long getTowerKills()
    {
        return this.towerKills;
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

    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = (prime * result) + ((this.assists == null) ? 0 : this.assists.hashCode());
        result = (prime * result) + ((this.champLevel == null) ? 0 : this.champLevel.hashCode());
        result = (prime * result) + ((this.combatPlayerScore == null) ? 0 : this.combatPlayerScore.hashCode());
        result = (prime * result) + ((this.deaths == null) ? 0 : this.deaths.hashCode());
        result = (prime * result) + ((this.doubleKills == null) ? 0 : this.doubleKills.hashCode());
        result = (prime * result) + ((this.firstBloodAssist == null) ? 0 : this.firstBloodAssist.hashCode());
        result = (prime * result) + ((this.firstBloodKill == null) ? 0 : this.firstBloodKill.hashCode());
        result = (prime * result) + ((this.firstInhibitorAssist == null) ? 0 : this.firstInhibitorAssist.hashCode());
        result = (prime * result) + ((this.firstInhibitorKill == null) ? 0 : this.firstInhibitorKill.hashCode());
        result = (prime * result) + ((this.firstTowerAssist == null) ? 0 : this.firstTowerAssist.hashCode());
        result = (prime * result) + ((this.firstTowerKill == null) ? 0 : this.firstTowerKill.hashCode());
        result = (prime * result) + ((this.goldEarned == null) ? 0 : this.goldEarned.hashCode());
        result = (prime * result) + ((this.goldSpent == null) ? 0 : this.goldSpent.hashCode());
        result = (prime * result) + ((this.inhibitorKills == null) ? 0 : this.inhibitorKills.hashCode());
        result = (prime * result) + ((this.item0 == null) ? 0 : this.item0.hashCode());
        result = (prime * result) + ((this.item1 == null) ? 0 : this.item1.hashCode());
        result = (prime * result) + ((this.item2 == null) ? 0 : this.item2.hashCode());
        result = (prime * result) + ((this.item3 == null) ? 0 : this.item3.hashCode());
        result = (prime * result) + ((this.item4 == null) ? 0 : this.item4.hashCode());
        result = (prime * result) + ((this.item5 == null) ? 0 : this.item5.hashCode());
        result = (prime * result) + ((this.item6 == null) ? 0 : this.item6.hashCode());
        result = (prime * result) + ((this.killingSprees == null) ? 0 : this.killingSprees.hashCode());
        result = (prime * result) + ((this.kills == null) ? 0 : this.kills.hashCode());
        result = (prime * result) + ((this.largestCriticalStrike == null) ? 0 : this.largestCriticalStrike.hashCode());
        result = (prime * result) + ((this.largestKillingSpree == null) ? 0 : this.largestKillingSpree.hashCode());
        result = (prime * result) + ((this.largestMultiKill == null) ? 0 : this.largestMultiKill.hashCode());
        result = (prime * result) + ((this.magicDamageDealt == null) ? 0 : this.magicDamageDealt.hashCode());
        result = (prime * result) + ((this.magicDamageDealtToChampions == null) ? 0 : this.magicDamageDealtToChampions.hashCode());
        result = (prime * result) + ((this.magicDamageTaken == null) ? 0 : this.magicDamageTaken.hashCode());
        result = (prime * result) + ((this.minionsKilled == null) ? 0 : this.minionsKilled.hashCode());
        result = (prime * result) + ((this.neutralMinionsKilled == null) ? 0 : this.neutralMinionsKilled.hashCode());
        result = (prime * result) + ((this.neutralMinionsKilledEnemyJungle == null) ? 0 : this.neutralMinionsKilledEnemyJungle.hashCode());
        result = (prime * result) + ((this.neutralMinionsKilledTeamJungle == null) ? 0 : this.neutralMinionsKilledTeamJungle.hashCode());
        result = (prime * result) + ((this.nodeCapture == null) ? 0 : this.nodeCapture.hashCode());
        result = (prime * result) + ((this.nodeCaptureAssist == null) ? 0 : this.nodeCaptureAssist.hashCode());
        result = (prime * result) + ((this.nodeNeutralize == null) ? 0 : this.nodeNeutralize.hashCode());
        result = (prime * result) + ((this.nodeNeutralizeAssist == null) ? 0 : this.nodeNeutralizeAssist.hashCode());
        result = (prime * result) + ((this.objectivePlayerScore == null) ? 0 : this.objectivePlayerScore.hashCode());
        result = (prime * result) + ((this.pentaKills == null) ? 0 : this.pentaKills.hashCode());
        result = (prime * result) + ((this.physicalDamageDealt == null) ? 0 : this.physicalDamageDealt.hashCode());
        result = (prime * result) + ((this.physicalDamageDealtToChampions == null) ? 0 : this.physicalDamageDealtToChampions.hashCode());
        result = (prime * result) + ((this.physicalDamageTaken == null) ? 0 : this.physicalDamageTaken.hashCode());
        result = (prime * result) + ((this.quadraKills == null) ? 0 : this.quadraKills.hashCode());
        result = (prime * result) + ((this.sightWardsBoughtInGame == null) ? 0 : this.sightWardsBoughtInGame.hashCode());
        result = (prime * result) + ((this.teamObjective == null) ? 0 : this.teamObjective.hashCode());
        result = (prime * result) + ((this.totalDamageDealt == null) ? 0 : this.totalDamageDealt.hashCode());
        result = (prime * result) + ((this.totalDamageDealtToChampions == null) ? 0 : this.totalDamageDealtToChampions.hashCode());
        result = (prime * result) + ((this.totalDamageTaken == null) ? 0 : this.totalDamageTaken.hashCode());
        result = (prime * result) + ((this.totalHeal == null) ? 0 : this.totalHeal.hashCode());
        result = (prime * result) + ((this.totalPlayerScore == null) ? 0 : this.totalPlayerScore.hashCode());
        result = (prime * result) + ((this.totalScoreRank == null) ? 0 : this.totalScoreRank.hashCode());
        result = (prime * result) + ((this.totalTimeCrowdControlDealt == null) ? 0 : this.totalTimeCrowdControlDealt.hashCode());
        result = (prime * result) + ((this.totalUnitsHealed == null) ? 0 : this.totalUnitsHealed.hashCode());
        result = (prime * result) + ((this.towerKills == null) ? 0 : this.towerKills.hashCode());
        result = (prime * result) + ((this.tripleKills == null) ? 0 : this.tripleKills.hashCode());
        result = (prime * result) + ((this.trueDamageDealt == null) ? 0 : this.trueDamageDealt.hashCode());
        result = (prime * result) + ((this.trueDamageDealtToChampions == null) ? 0 : this.trueDamageDealtToChampions.hashCode());
        result = (prime * result) + ((this.trueDamageTaken == null) ? 0 : this.trueDamageTaken.hashCode());
        result = (prime * result) + ((this.unrealKills == null) ? 0 : this.unrealKills.hashCode());
        result = (prime * result) + ((this.visionWardsBoughtInGame == null) ? 0 : this.visionWardsBoughtInGame.hashCode());
        result = (prime * result) + ((this.wardsKilled == null) ? 0 : this.wardsKilled.hashCode());
        result = (prime * result) + ((this.wardsPlaced == null) ? 0 : this.wardsPlaced.hashCode());
        result = (prime * result) + ((this.winner == null) ? 0 : this.winner.hashCode());
        return result;
    }

    /**
     * Id first inhibitor kill.
     *
     * @return the boolean
     */
    public Boolean idFirstInhibitorKill()
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
     * Checks if is winner.
     *
     * @return the boolean
     */
    public Boolean isWinner()
    {
        return this.winner;
    }

    @Override
    public String toString()
    {
        return "ParticipantStats [assists=" + assists + ", champLevel=" + champLevel + ", combatPlayerScore=" + combatPlayerScore + ", deaths=" + deaths + ", doubleKills=" + doubleKills + ", firstBloodAssist=" + firstBloodAssist + ", firstBloodKill=" + firstBloodKill + ", firstInhibitorAssist=" + firstInhibitorAssist + ", firstInhibitorKill=" + firstInhibitorKill + ", firstTowerAssist=" + firstTowerAssist + ", firstTowerKill=" + firstTowerKill + ", goldEarned=" + goldEarned + ", goldSpent=" + goldSpent + ", inhibitorKills=" + inhibitorKills + ", item0=" + item0 + ", item1=" + item1 + ", item2=" + item2 + ", item3=" + item3 + ", item4=" + item4 + ", item5=" + item5 + ", item6=" + item6 + ", killingSprees=" + killingSprees + ", kills=" + kills + ", largestCriticalStrike=" + largestCriticalStrike + ", largestKillingSpree=" + largestKillingSpree + ", largestMultiKill=" + largestMultiKill + ", magicDamageDealt=" + magicDamageDealt + ", magicDamageDealtToChampions=" + magicDamageDealtToChampions + ", magicDamageTaken=" + magicDamageTaken + ", minionsKilled=" + minionsKilled + ", neutralMinionsKilled=" + neutralMinionsKilled + ", neutralMinionsKilledEnemyJungle=" + neutralMinionsKilledEnemyJungle + ", neutralMinionsKilledTeamJungle=" + neutralMinionsKilledTeamJungle + ", nodeCapture=" + nodeCapture + ", nodeCaptureAssist=" + nodeCaptureAssist + ", nodeNeutralize=" + nodeNeutralize + ", nodeNeutralizeAssist=" + nodeNeutralizeAssist + ", objectivePlayerScore=" + objectivePlayerScore + ", pentaKills=" + pentaKills + ", physicalDamageDealt=" + physicalDamageDealt
                + ", physicalDamageDealtToChampions=" + physicalDamageDealtToChampions + ", physicalDamageTaken=" + physicalDamageTaken + ", quadraKills=" + quadraKills + ", sightWardsBoughtInGame=" + sightWardsBoughtInGame + ", teamObjective=" + teamObjective + ", totalDamageDealt=" + totalDamageDealt + ", totalDamageDealtToChampions=" + totalDamageDealtToChampions + ", totalDamageTaken=" + totalDamageTaken + ", totalHeal=" + totalHeal + ", totalPlayerScore=" + totalPlayerScore + ", totalScoreRank=" + totalScoreRank + ", totalTimeCrowdControlDealt=" + totalTimeCrowdControlDealt + ", totalUnitsHealed=" + totalUnitsHealed + ", towerKills=" + towerKills + ", tripleKills=" + tripleKills + ", trueDamageDealt=" + trueDamageDealt + ", trueDamageDealtToChampions=" + trueDamageDealtToChampions + ", trueDamageTaken=" + trueDamageTaken + ", unrealKills=" + unrealKills + ", visionWardsBoughtInGame=" + visionWardsBoughtInGame + ", wardsKilled=" + wardsKilled + ", wardsPlaced=" + wardsPlaced + ", winner=" + winner + "]";
    }

}
