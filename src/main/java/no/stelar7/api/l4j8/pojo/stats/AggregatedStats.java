package no.stelar7.api.l4j8.pojo.stats;

public class AggregatedStats
{
    private Integer averageAssists;
    private Integer averageChampionsKilled;
    private Integer averageCombatPlayerScore;
    private Integer averageNodeCapture;
    private Integer averageNodeCaptureAssist;
    private Integer averageNodeNeutralize;
    private Integer averageNodeNeutralizeAssist;
    private Integer averageNumDeaths;
    private Integer averageObjectivePlayerScore;
    private Integer averageTeamObjective;
    private Integer averageTotalPlayerScore;
    private Integer botGamesPlayed;
    private Integer killingSpree;
    private Integer maxAssists;
    private Integer maxChampionsKilled;
    private Integer maxCombatPlayerScore;
    private Integer maxLargestCriticalStrike;
    private Integer maxLargestKillingSpree;
    private Integer maxNodeCapture;
    private Integer maxNodeCaptureAssist;
    private Integer maxNodeNeutralize;
    private Integer maxNodeNeutralizeAssist;
    private Integer maxNumDeaths;
    private Integer maxObjectivePlayerScore;
    private Integer maxTeamObjective;
    private Integer maxTimePlayed;
    private Integer maxTimeSpentLiving;
    private Integer maxTotalPlayerScore;
    private Integer mostChampionKillsPerSession;
    private Integer mostSpellsCast;
    private Integer normalGamesPlayed;
    private Integer rankedPremadeGamesPlayed;
    private Integer rankedSoloGamesPlayed;
    private Integer totalAssists;
    private Integer totalChampionKills;
    private Integer totalDamageDealt;
    private Integer totalDamageTaken;
    private Integer totalDeathsPerSession;
    private Integer totalDoubleKills;
    private Integer totalFirstBlood;
    private Integer totalGoldEarned;
    private Integer totalHeal;
    private Integer totalMagicDamageDealt;
    private Integer totalMinionKills;
    private Integer totalNeutralMinionsKilled;
    private Integer totalNodeCapture;
    private Integer totalNodeNeutralize;
    private Integer totalPentaKills;
    private Integer totalPhysicalDamageDealt;
    private Integer totalQuadraKills;
    private Integer totalSessionsLost;
    private Integer totalSessionsPlayed;
    private Integer totalSessionsWon;
    private Integer totalTripleKills;
    private Integer totalTurretsKilled;
    private Integer totalUnrealKills;

    @SuppressWarnings("ConstantConditions")
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
        final AggregatedStats other = (AggregatedStats) obj;
        if (this.averageAssists == null)
        {
            if (other.averageAssists != null)
            {
                return false;
            }
        } else if (!this.averageAssists.equals(other.averageAssists))
        {
            return false;
        }
        if (this.averageChampionsKilled == null)
        {
            if (other.averageChampionsKilled != null)
            {
                return false;
            }
        } else if (!this.averageChampionsKilled.equals(other.averageChampionsKilled))
        {
            return false;
        }
        if (this.averageCombatPlayerScore == null)
        {
            if (other.averageCombatPlayerScore != null)
            {
                return false;
            }
        } else if (!this.averageCombatPlayerScore.equals(other.averageCombatPlayerScore))
        {
            return false;
        }
        if (this.averageNodeCapture == null)
        {
            if (other.averageNodeCapture != null)
            {
                return false;
            }
        } else if (!this.averageNodeCapture.equals(other.averageNodeCapture))
        {
            return false;
        }
        if (this.averageNodeCaptureAssist == null)
        {
            if (other.averageNodeCaptureAssist != null)
            {
                return false;
            }
        } else if (!this.averageNodeCaptureAssist.equals(other.averageNodeCaptureAssist))
        {
            return false;
        }
        if (this.averageNodeNeutralize == null)
        {
            if (other.averageNodeNeutralize != null)
            {
                return false;
            }
        } else if (!this.averageNodeNeutralize.equals(other.averageNodeNeutralize))
        {
            return false;
        }
        if (this.averageNodeNeutralizeAssist == null)
        {
            if (other.averageNodeNeutralizeAssist != null)
            {
                return false;
            }
        } else if (!this.averageNodeNeutralizeAssist.equals(other.averageNodeNeutralizeAssist))
        {
            return false;
        }
        if (this.averageNumDeaths == null)
        {
            if (other.averageNumDeaths != null)
            {
                return false;
            }
        } else if (!this.averageNumDeaths.equals(other.averageNumDeaths))
        {
            return false;
        }
        if (this.averageObjectivePlayerScore == null)
        {
            if (other.averageObjectivePlayerScore != null)
            {
                return false;
            }
        } else if (!this.averageObjectivePlayerScore.equals(other.averageObjectivePlayerScore))
        {
            return false;
        }
        if (this.averageTeamObjective == null)
        {
            if (other.averageTeamObjective != null)
            {
                return false;
            }
        } else if (!this.averageTeamObjective.equals(other.averageTeamObjective))
        {
            return false;
        }
        if (this.averageTotalPlayerScore == null)
        {
            if (other.averageTotalPlayerScore != null)
            {
                return false;
            }
        } else if (!this.averageTotalPlayerScore.equals(other.averageTotalPlayerScore))
        {
            return false;
        }
        if (this.botGamesPlayed == null)
        {
            if (other.botGamesPlayed != null)
            {
                return false;
            }
        } else if (!this.botGamesPlayed.equals(other.botGamesPlayed))
        {
            return false;
        }
        if (this.killingSpree == null)
        {
            if (other.killingSpree != null)
            {
                return false;
            }
        } else if (!this.killingSpree.equals(other.killingSpree))
        {
            return false;
        }
        if (this.maxAssists == null)
        {
            if (other.maxAssists != null)
            {
                return false;
            }
        } else if (!this.maxAssists.equals(other.maxAssists))
        {
            return false;
        }
        if (this.maxChampionsKilled == null)
        {
            if (other.maxChampionsKilled != null)
            {
                return false;
            }
        } else if (!this.maxChampionsKilled.equals(other.maxChampionsKilled))
        {
            return false;
        }
        if (this.maxCombatPlayerScore == null)
        {
            if (other.maxCombatPlayerScore != null)
            {
                return false;
            }
        } else if (!this.maxCombatPlayerScore.equals(other.maxCombatPlayerScore))
        {
            return false;
        }
        if (this.maxLargestCriticalStrike == null)
        {
            if (other.maxLargestCriticalStrike != null)
            {
                return false;
            }
        } else if (!this.maxLargestCriticalStrike.equals(other.maxLargestCriticalStrike))
        {
            return false;
        }
        if (this.maxLargestKillingSpree == null)
        {
            if (other.maxLargestKillingSpree != null)
            {
                return false;
            }
        } else if (!this.maxLargestKillingSpree.equals(other.maxLargestKillingSpree))
        {
            return false;
        }
        if (this.maxNodeCapture == null)
        {
            if (other.maxNodeCapture != null)
            {
                return false;
            }
        } else if (!this.maxNodeCapture.equals(other.maxNodeCapture))
        {
            return false;
        }
        if (this.maxNodeCaptureAssist == null)
        {
            if (other.maxNodeCaptureAssist != null)
            {
                return false;
            }
        } else if (!this.maxNodeCaptureAssist.equals(other.maxNodeCaptureAssist))
        {
            return false;
        }
        if (this.maxNodeNeutralize == null)
        {
            if (other.maxNodeNeutralize != null)
            {
                return false;
            }
        } else if (!this.maxNodeNeutralize.equals(other.maxNodeNeutralize))
        {
            return false;
        }
        if (this.maxNodeNeutralizeAssist == null)
        {
            if (other.maxNodeNeutralizeAssist != null)
            {
                return false;
            }
        } else if (!this.maxNodeNeutralizeAssist.equals(other.maxNodeNeutralizeAssist))
        {
            return false;
        }
        if (this.maxNumDeaths == null)
        {
            if (other.maxNumDeaths != null)
            {
                return false;
            }
        } else if (!this.maxNumDeaths.equals(other.maxNumDeaths))
        {
            return false;
        }
        if (this.maxObjectivePlayerScore == null)
        {
            if (other.maxObjectivePlayerScore != null)
            {
                return false;
            }
        } else if (!this.maxObjectivePlayerScore.equals(other.maxObjectivePlayerScore))
        {
            return false;
        }
        if (this.maxTeamObjective == null)
        {
            if (other.maxTeamObjective != null)
            {
                return false;
            }
        } else if (!this.maxTeamObjective.equals(other.maxTeamObjective))
        {
            return false;
        }
        if (this.maxTimePlayed == null)
        {
            if (other.maxTimePlayed != null)
            {
                return false;
            }
        } else if (!this.maxTimePlayed.equals(other.maxTimePlayed))
        {
            return false;
        }
        if (this.maxTimeSpentLiving == null)
        {
            if (other.maxTimeSpentLiving != null)
            {
                return false;
            }
        } else if (!this.maxTimeSpentLiving.equals(other.maxTimeSpentLiving))
        {
            return false;
        }
        if (this.maxTotalPlayerScore == null)
        {
            if (other.maxTotalPlayerScore != null)
            {
                return false;
            }
        } else if (!this.maxTotalPlayerScore.equals(other.maxTotalPlayerScore))
        {
            return false;
        }
        if (this.mostChampionKillsPerSession == null)
        {
            if (other.mostChampionKillsPerSession != null)
            {
                return false;
            }
        } else if (!this.mostChampionKillsPerSession.equals(other.mostChampionKillsPerSession))
        {
            return false;
        }
        if (this.mostSpellsCast == null)
        {
            if (other.mostSpellsCast != null)
            {
                return false;
            }
        } else if (!this.mostSpellsCast.equals(other.mostSpellsCast))
        {
            return false;
        }
        if (this.normalGamesPlayed == null)
        {
            if (other.normalGamesPlayed != null)
            {
                return false;
            }
        } else if (!this.normalGamesPlayed.equals(other.normalGamesPlayed))
        {
            return false;
        }
        if (this.rankedPremadeGamesPlayed == null)
        {
            if (other.rankedPremadeGamesPlayed != null)
            {
                return false;
            }
        } else if (!this.rankedPremadeGamesPlayed.equals(other.rankedPremadeGamesPlayed))
        {
            return false;
        }
        if (this.rankedSoloGamesPlayed == null)
        {
            if (other.rankedSoloGamesPlayed != null)
            {
                return false;
            }
        } else if (!this.rankedSoloGamesPlayed.equals(other.rankedSoloGamesPlayed))
        {
            return false;
        }
        if (this.totalAssists == null)
        {
            if (other.totalAssists != null)
            {
                return false;
            }
        } else if (!this.totalAssists.equals(other.totalAssists))
        {
            return false;
        }
        if (this.totalChampionKills == null)
        {
            if (other.totalChampionKills != null)
            {
                return false;
            }
        } else if (!this.totalChampionKills.equals(other.totalChampionKills))
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
        if (this.totalDeathsPerSession == null)
        {
            if (other.totalDeathsPerSession != null)
            {
                return false;
            }
        } else if (!this.totalDeathsPerSession.equals(other.totalDeathsPerSession))
        {
            return false;
        }
        if (this.totalDoubleKills == null)
        {
            if (other.totalDoubleKills != null)
            {
                return false;
            }
        } else if (!this.totalDoubleKills.equals(other.totalDoubleKills))
        {
            return false;
        }
        if (this.totalFirstBlood == null)
        {
            if (other.totalFirstBlood != null)
            {
                return false;
            }
        } else if (!this.totalFirstBlood.equals(other.totalFirstBlood))
        {
            return false;
        }
        if (this.totalGoldEarned == null)
        {
            if (other.totalGoldEarned != null)
            {
                return false;
            }
        } else if (!this.totalGoldEarned.equals(other.totalGoldEarned))
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
        if (this.totalMagicDamageDealt == null)
        {
            if (other.totalMagicDamageDealt != null)
            {
                return false;
            }
        } else if (!this.totalMagicDamageDealt.equals(other.totalMagicDamageDealt))
        {
            return false;
        }
        if (this.totalMinionKills == null)
        {
            if (other.totalMinionKills != null)
            {
                return false;
            }
        } else if (!this.totalMinionKills.equals(other.totalMinionKills))
        {
            return false;
        }
        if (this.totalNeutralMinionsKilled == null)
        {
            if (other.totalNeutralMinionsKilled != null)
            {
                return false;
            }
        } else if (!this.totalNeutralMinionsKilled.equals(other.totalNeutralMinionsKilled))
        {
            return false;
        }
        if (this.totalNodeCapture == null)
        {
            if (other.totalNodeCapture != null)
            {
                return false;
            }
        } else if (!this.totalNodeCapture.equals(other.totalNodeCapture))
        {
            return false;
        }
        if (this.totalNodeNeutralize == null)
        {
            if (other.totalNodeNeutralize != null)
            {
                return false;
            }
        } else if (!this.totalNodeNeutralize.equals(other.totalNodeNeutralize))
        {
            return false;
        }
        if (this.totalPentaKills == null)
        {
            if (other.totalPentaKills != null)
            {
                return false;
            }
        } else if (!this.totalPentaKills.equals(other.totalPentaKills))
        {
            return false;
        }
        if (this.totalPhysicalDamageDealt == null)
        {
            if (other.totalPhysicalDamageDealt != null)
            {
                return false;
            }
        } else if (!this.totalPhysicalDamageDealt.equals(other.totalPhysicalDamageDealt))
        {
            return false;
        }
        if (this.totalQuadraKills == null)
        {
            if (other.totalQuadraKills != null)
            {
                return false;
            }
        } else if (!this.totalQuadraKills.equals(other.totalQuadraKills))
        {
            return false;
        }
        if (this.totalSessionsLost == null)
        {
            if (other.totalSessionsLost != null)
            {
                return false;
            }
        } else if (!this.totalSessionsLost.equals(other.totalSessionsLost))
        {
            return false;
        }
        if (this.totalSessionsPlayed == null)
        {
            if (other.totalSessionsPlayed != null)
            {
                return false;
            }
        } else if (!this.totalSessionsPlayed.equals(other.totalSessionsPlayed))
        {
            return false;
        }
        if (this.totalSessionsWon == null)
        {
            if (other.totalSessionsWon != null)
            {
                return false;
            }
        } else if (!this.totalSessionsWon.equals(other.totalSessionsWon))
        {
            return false;
        }
        if (this.totalTripleKills == null)
        {
            if (other.totalTripleKills != null)
            {
                return false;
            }
        } else if (!this.totalTripleKills.equals(other.totalTripleKills))
        {
            return false;
        }
        if (this.totalTurretsKilled == null)
        {
            if (other.totalTurretsKilled != null)
            {
                return false;
            }
        } else if (!this.totalTurretsKilled.equals(other.totalTurretsKilled))
        {
            return false;
        }
        if (this.totalUnrealKills == null)
        {
            if (other.totalUnrealKills != null)
            {
                return false;
            }
        } else if (!this.totalUnrealKills.equals(other.totalUnrealKills))
        {
            return false;
        }
        return true;
    }

    /**
     * Gets the average assists. (Dominion only.)
     *
     * @return the average assists
     */
    public Integer getAverageAssists()
    {
        return this.averageAssists;
    }

    /**
     * Gets the average champions killed. (Dominion only.)
     *
     * @return the average champions killed
     */
    public Integer getAverageChampionsKilled()
    {
        return this.averageChampionsKilled;
    }

    /**
     * Gets the average combat player score.(Dominion only.)
     *
     * @return the average combat player score
     */
    public Integer getAverageCombatPlayerScore()
    {
        return this.averageCombatPlayerScore;
    }

    /**
     * Gets the average node capture.(Dominion only.)
     *
     * @return the average node capture
     */
    public Integer getAverageNodeCapture()
    {
        return this.averageNodeCapture;
    }

    /**
     * Gets the average node capture assist.(Dominion only.)
     *
     * @return the average node capture assist
     */
    public Integer getAverageNodeCaptureAssist()
    {
        return this.averageNodeCaptureAssist;
    }

    /**
     * Gets the average node neutralize.(Dominion only.)
     *
     * @return the average node neutralize
     */
    public Integer getAverageNodeNeutralize()
    {
        return this.averageNodeNeutralize;
    }

    /**
     * Gets the average node neutralize assist.(Dominion only.)
     *
     * @return the average node neutralize assist
     */
    public Integer getAverageNodeNeutralizeAssist()
    {
        return this.averageNodeNeutralizeAssist;
    }

    /**
     * Gets the average num deaths.(Dominion only.)
     *
     * @return the average num deaths
     */
    public Integer getAverageNumDeaths()
    {
        return this.averageNumDeaths;
    }

    /**
     * Gets the average objective player score.(Dominion only.)
     *
     * @return the average objective player score
     */
    public Integer getAverageObjectivePlayerScore()
    {
        return this.averageObjectivePlayerScore;
    }

    /**
     * Gets the average team objective.(Dominion only.)
     *
     * @return the average team objective
     */
    public Integer getAverageTeamObjective()
    {
        return this.averageTeamObjective;
    }

    /**
     * Gets the average total player score.(Dominion only.)
     *
     * @return the average total player score
     */
    public Integer getAverageTotalPlayerScore()
    {
        return this.averageTotalPlayerScore;
    }

    /**
     * Gets the bot games played.
     *
     * @return the bot games played
     */
    public Integer getBotGamesPlayed()
    {
        return this.botGamesPlayed;
    }

    /**
     * Gets the killing spree.
     *
     * @return the killing spree
     */
    public Integer getKillingSpree()
    {
        return this.killingSpree;
    }

    /**
     * Gets the max assists. (Dominion only.)
     *
     * @return the max assists
     */
    public Integer getMaxAssists()
    {
        return this.maxAssists;
    }

    /**
     * Gets the max champions killed.
     *
     * @return the max champions killed
     */
    public Integer getMaxChampionsKilled()
    {
        return this.maxChampionsKilled;
    }

    /**
     * Gets the max combat player score. (Dominion only.)
     *
     * @return the max combat player score
     */
    public Integer getMaxCombatPlayerScore()
    {
        return this.maxCombatPlayerScore;
    }

    /**
     * Gets the max largest critical strike.
     *
     * @return the max largest critical strike
     */
    public Integer getMaxLargestCriticalStrike()
    {
        return this.maxLargestCriticalStrike;
    }

    /**
     * Gets the max largest killing spree.
     *
     * @return the max largest killing spree
     */
    public Integer getMaxLargestKillingSpree()
    {
        return this.maxLargestKillingSpree;
    }

    /**
     * Gets the max node capture. (Dominion only.)
     *
     * @return the max node capture
     */
    public Integer getMaxNodeCapture()
    {
        return this.maxNodeCapture;
    }

    /**
     * Gets the max node capture assist. (Dominion only.)
     *
     * @return the max node capture assist
     */
    public Integer getMaxNodeCaptureAssist()
    {
        return this.maxNodeCaptureAssist;
    }

    /**
     * Gets the max node neutralize. (Dominion only.)
     *
     * @return the max node neutralize
     */
    public Integer getMaxNodeNeutralize()
    {
        return this.maxNodeNeutralize;
    }

    /**
     * Gets the max node neutralize assist. (Dominion only.)
     *
     * @return the max node neutralize assist
     */
    public Integer getMaxNodeNeutralizeAssist()
    {
        return this.maxNodeNeutralizeAssist;
    }

    /**
     * Gets the max num deaths. (Only returned for ranked statistics.)
     *
     * @return the max num deaths
     */
    public Integer getMaxNumDeaths()
    {
        return this.maxNumDeaths;
    }

    /**
     * Gets the max objective player score. (Dominion only.)
     *
     * @return the max objective player score
     */
    public Integer getMaxObjectivePlayerScore()
    {
        return this.maxObjectivePlayerScore;
    }

    /**
     * Gets the max team objective. (Dominion only.)
     *
     * @return the max team objective
     */
    public Integer getMaxTeamObjective()
    {
        return this.maxTeamObjective;
    }

    /**
     * Gets the max time played.
     *
     * @return the max time played
     */
    public Integer getMaxTimePlayed()
    {
        return this.maxTimePlayed;
    }

    /**
     * Gets the max time spent living.
     *
     * @return the max time spent living
     */
    public Integer getMaxTimeSpentLiving()
    {
        return this.maxTimeSpentLiving;
    }

    /**
     * Gets the max total player score. (Dominion only.)
     *
     * @return the max total player score
     */
    public Integer getMaxTotalPlayerScore()
    {
        return this.maxTotalPlayerScore;
    }

    /**
     * Gets the most champion kills per session.
     *
     * @return the most champion kills per session
     */
    public Integer getMostChampionKillsPerSession()
    {
        return this.mostChampionKillsPerSession;
    }

    /**
     * Gets the most spells cast.
     *
     * @return the most spells cast
     */
    public Integer getMostSpellsCast()
    {
        return this.mostSpellsCast;
    }

    /**
     * Gets the normal games played.
     *
     * @return the normal games played
     */
    public Integer getNormalGamesPlayed()
    {
        return this.normalGamesPlayed;
    }

    /**
     * Gets the ranked premade games played.
     *
     * @return the ranked premade games played
     */
    public Integer getRankedPremadeGamesPlayed()
    {
        return this.rankedPremadeGamesPlayed;
    }

    /**
     * Gets the ranked solo games played.
     *
     * @return the ranked solo games played
     */
    public Integer getRankedSoloGamesPlayed()
    {
        return this.rankedSoloGamesPlayed;
    }

    /**
     * Gets the total assists.
     *
     * @return the total assists
     */
    public Integer getTotalAssists()
    {
        return this.totalAssists;
    }

    /**
     * Gets the total champion kills.
     *
     * @return the total champion kills
     */
    public Integer getTotalChampionKills()
    {
        return this.totalChampionKills;
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
     * Gets the total damage taken.
     *
     * @return the total damage taken
     */
    public Integer getTotalDamageTaken()
    {
        return this.totalDamageTaken;
    }

    /**
     * Gets the total deaths per session. (Only returned for ranked statistics.)
     *
     * @return the total deaths per session
     */
    public Integer getTotalDeathsPerSession()
    {
        return this.totalDeathsPerSession;
    }

    /**
     * Gets the total double kills.
     *
     * @return the total double kills
     */
    public Integer getTotalDoubleKills()
    {
        return this.totalDoubleKills;
    }

    /**
     * Gets the total first blood.
     *
     * @return the total first blood
     */
    public Integer getTotalFirstBlood()
    {
        return this.totalFirstBlood;
    }

    /**
     * Gets the total gold earned.
     *
     * @return the total gold earned
     */
    public Integer getTotalGoldEarned()
    {
        return this.totalGoldEarned;
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
     * Gets the total magic damage dealt.
     *
     * @return the total magic damage dealt
     */
    public Integer getTotalMagicDamageDealt()
    {
        return this.totalMagicDamageDealt;
    }

    /**
     * Gets the total minion kills.
     *
     * @return the total minion kills
     */
    public Integer getTotalMinionKills()
    {
        return this.totalMinionKills;
    }

    /**
     * Gets the total neutral minions killed.
     *
     * @return the total neutral minions killed
     */
    public Integer getTotalNeutralMinionsKilled()
    {
        return this.totalNeutralMinionsKilled;
    }

    /**
     * Gets the total node capture. (Dominion only.)
     *
     * @return the total node capture
     */
    public Integer getTotalNodeCapture()
    {
        return this.totalNodeCapture;
    }

    /**
     * Gets the total node neutralize.(Dominion only.)
     *
     * @return the total node neutralize
     */
    public Integer getTotalNodeNeutralize()
    {
        return this.totalNodeNeutralize;
    }

    /**
     * Gets the total penta kills.
     *
     * @return the total penta kills
     */
    public Integer getTotalPentaKills()
    {
        return this.totalPentaKills;
    }

    /**
     * Gets the total physical damage dealt.
     *
     * @return the total physical damage dealt
     */
    public Integer getTotalPhysicalDamageDealt()
    {
        return this.totalPhysicalDamageDealt;
    }

    /**
     * Gets the total quadra kills.
     *
     * @return the total quadra kills
     */
    public Integer getTotalQuadraKills()
    {
        return this.totalQuadraKills;
    }

    /**
     * Gets the total sessions lost.
     *
     * @return the total sessions lost
     */
    public Integer getTotalSessionsLost()
    {
        return this.totalSessionsLost;
    }

    /**
     * Gets the total sessions played.
     *
     * @return the total sessions played
     */
    public Integer getTotalSessionsPlayed()
    {
        return this.totalSessionsPlayed;
    }

    /**
     * Gets the total sessions won.
     *
     * @return the total sessions won
     */
    public Integer getTotalSessionsWon()
    {
        return this.totalSessionsWon;
    }

    /**
     * Gets the total triple kills.
     *
     * @return the total triple kills
     */
    public Integer getTotalTripleKills()
    {
        return this.totalTripleKills;
    }

    /**
     * Gets the total turrets killed.
     *
     * @return the total turrets killed
     */
    public Integer getTotalTurretsKilled()
    {
        return this.totalTurretsKilled;
    }

    /**
     * Gets the total unreal kills.
     *
     * @return the total unreal kills
     */
    public Integer getTotalUnrealKills()
    {
        return this.totalUnrealKills;
    }

    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = (prime * result) + ((this.averageAssists == null) ? 0 : this.averageAssists.hashCode());
        result = (prime * result) + ((this.averageChampionsKilled == null) ? 0 : this.averageChampionsKilled.hashCode());
        result = (prime * result) + ((this.averageCombatPlayerScore == null) ? 0 : this.averageCombatPlayerScore.hashCode());
        result = (prime * result) + ((this.averageNodeCapture == null) ? 0 : this.averageNodeCapture.hashCode());
        result = (prime * result) + ((this.averageNodeCaptureAssist == null) ? 0 : this.averageNodeCaptureAssist.hashCode());
        result = (prime * result) + ((this.averageNodeNeutralize == null) ? 0 : this.averageNodeNeutralize.hashCode());
        result = (prime * result) + ((this.averageNodeNeutralizeAssist == null) ? 0 : this.averageNodeNeutralizeAssist.hashCode());
        result = (prime * result) + ((this.averageNumDeaths == null) ? 0 : this.averageNumDeaths.hashCode());
        result = (prime * result) + ((this.averageObjectivePlayerScore == null) ? 0 : this.averageObjectivePlayerScore.hashCode());
        result = (prime * result) + ((this.averageTeamObjective == null) ? 0 : this.averageTeamObjective.hashCode());
        result = (prime * result) + ((this.averageTotalPlayerScore == null) ? 0 : this.averageTotalPlayerScore.hashCode());
        result = (prime * result) + ((this.botGamesPlayed == null) ? 0 : this.botGamesPlayed.hashCode());
        result = (prime * result) + ((this.killingSpree == null) ? 0 : this.killingSpree.hashCode());
        result = (prime * result) + ((this.maxAssists == null) ? 0 : this.maxAssists.hashCode());
        result = (prime * result) + ((this.maxChampionsKilled == null) ? 0 : this.maxChampionsKilled.hashCode());
        result = (prime * result) + ((this.maxCombatPlayerScore == null) ? 0 : this.maxCombatPlayerScore.hashCode());
        result = (prime * result) + ((this.maxLargestCriticalStrike == null) ? 0 : this.maxLargestCriticalStrike.hashCode());
        result = (prime * result) + ((this.maxLargestKillingSpree == null) ? 0 : this.maxLargestKillingSpree.hashCode());
        result = (prime * result) + ((this.maxNodeCapture == null) ? 0 : this.maxNodeCapture.hashCode());
        result = (prime * result) + ((this.maxNodeCaptureAssist == null) ? 0 : this.maxNodeCaptureAssist.hashCode());
        result = (prime * result) + ((this.maxNodeNeutralize == null) ? 0 : this.maxNodeNeutralize.hashCode());
        result = (prime * result) + ((this.maxNodeNeutralizeAssist == null) ? 0 : this.maxNodeNeutralizeAssist.hashCode());
        result = (prime * result) + ((this.maxNumDeaths == null) ? 0 : this.maxNumDeaths.hashCode());
        result = (prime * result) + ((this.maxObjectivePlayerScore == null) ? 0 : this.maxObjectivePlayerScore.hashCode());
        result = (prime * result) + ((this.maxTeamObjective == null) ? 0 : this.maxTeamObjective.hashCode());
        result = (prime * result) + ((this.maxTimePlayed == null) ? 0 : this.maxTimePlayed.hashCode());
        result = (prime * result) + ((this.maxTimeSpentLiving == null) ? 0 : this.maxTimeSpentLiving.hashCode());
        result = (prime * result) + ((this.maxTotalPlayerScore == null) ? 0 : this.maxTotalPlayerScore.hashCode());
        result = (prime * result) + ((this.mostChampionKillsPerSession == null) ? 0 : this.mostChampionKillsPerSession.hashCode());
        result = (prime * result) + ((this.mostSpellsCast == null) ? 0 : this.mostSpellsCast.hashCode());
        result = (prime * result) + ((this.normalGamesPlayed == null) ? 0 : this.normalGamesPlayed.hashCode());
        result = (prime * result) + ((this.rankedPremadeGamesPlayed == null) ? 0 : this.rankedPremadeGamesPlayed.hashCode());
        result = (prime * result) + ((this.rankedSoloGamesPlayed == null) ? 0 : this.rankedSoloGamesPlayed.hashCode());
        result = (prime * result) + ((this.totalAssists == null) ? 0 : this.totalAssists.hashCode());
        result = (prime * result) + ((this.totalChampionKills == null) ? 0 : this.totalChampionKills.hashCode());
        result = (prime * result) + ((this.totalDamageDealt == null) ? 0 : this.totalDamageDealt.hashCode());
        result = (prime * result) + ((this.totalDamageTaken == null) ? 0 : this.totalDamageTaken.hashCode());
        result = (prime * result) + ((this.totalDeathsPerSession == null) ? 0 : this.totalDeathsPerSession.hashCode());
        result = (prime * result) + ((this.totalDoubleKills == null) ? 0 : this.totalDoubleKills.hashCode());
        result = (prime * result) + ((this.totalFirstBlood == null) ? 0 : this.totalFirstBlood.hashCode());
        result = (prime * result) + ((this.totalGoldEarned == null) ? 0 : this.totalGoldEarned.hashCode());
        result = (prime * result) + ((this.totalHeal == null) ? 0 : this.totalHeal.hashCode());
        result = (prime * result) + ((this.totalMagicDamageDealt == null) ? 0 : this.totalMagicDamageDealt.hashCode());
        result = (prime * result) + ((this.totalMinionKills == null) ? 0 : this.totalMinionKills.hashCode());
        result = (prime * result) + ((this.totalNeutralMinionsKilled == null) ? 0 : this.totalNeutralMinionsKilled.hashCode());
        result = (prime * result) + ((this.totalNodeCapture == null) ? 0 : this.totalNodeCapture.hashCode());
        result = (prime * result) + ((this.totalNodeNeutralize == null) ? 0 : this.totalNodeNeutralize.hashCode());
        result = (prime * result) + ((this.totalPentaKills == null) ? 0 : this.totalPentaKills.hashCode());
        result = (prime * result) + ((this.totalPhysicalDamageDealt == null) ? 0 : this.totalPhysicalDamageDealt.hashCode());
        result = (prime * result) + ((this.totalQuadraKills == null) ? 0 : this.totalQuadraKills.hashCode());
        result = (prime * result) + ((this.totalSessionsLost == null) ? 0 : this.totalSessionsLost.hashCode());
        result = (prime * result) + ((this.totalSessionsPlayed == null) ? 0 : this.totalSessionsPlayed.hashCode());
        result = (prime * result) + ((this.totalSessionsWon == null) ? 0 : this.totalSessionsWon.hashCode());
        result = (prime * result) + ((this.totalTripleKills == null) ? 0 : this.totalTripleKills.hashCode());
        result = (prime * result) + ((this.totalTurretsKilled == null) ? 0 : this.totalTurretsKilled.hashCode());
        result = (prime * result) + ((this.totalUnrealKills == null) ? 0 : this.totalUnrealKills.hashCode());
        return result;
    }

}
