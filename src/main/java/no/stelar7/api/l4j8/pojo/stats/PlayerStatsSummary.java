package no.stelar7.api.l4j8.pojo.stats;

public class PlayerStatsSummary
{
    private AggregatedStats aggregatedStats;
    private Integer         losses;
    private Long            modifyDate;
    private String          playerStatSummaryType;
    private Integer         wins;
    
    
    
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
        final PlayerStatsSummary other = (PlayerStatsSummary) obj;
        if (this.aggregatedStats == null)
        {
            if (other.aggregatedStats != null)
            {
                return false;
            }
        } else if (!this.aggregatedStats.equals(other.aggregatedStats))
        {
            return false;
        }
        if (this.losses == null)
        {
            if (other.losses != null)
            {
                return false;
            }
        } else if (!this.losses.equals(other.losses))
        {
            return false;
        }
        if (this.modifyDate == null)
        {
            if (other.modifyDate != null)
            {
                return false;
            }
        } else if (!this.modifyDate.equals(other.modifyDate))
        {
            return false;
        }
        if (this.playerStatSummaryType == null)
        {
            if (other.playerStatSummaryType != null)
            {
                return false;
            }
        } else if (!this.playerStatSummaryType.equals(other.playerStatSummaryType))
        {
            return false;
        }
        if (this.wins == null)
        {
            if (other.wins != null)
            {
                return false;
            }
        } else if (!this.wins.equals(other.wins))
        {
            return false;
        }
        return true;
    }
    
    /**
     * Aggregated stats.
     *
     * @return the aggregated stats
     */
    public AggregatedStats getAggregatedStats()
    {
        return this.aggregatedStats;
    }
    
    /**
     * Number of losses for this queue type. Returned for ranked queue types only.
     *
     * @return the losses
     */
    public Integer getLosses()
    {
        return this.losses;
    }
    
    /**
     * Date stats were last modified specified as epoch milliseconds.
     *
     * @return the modify date
     */
    public Long getModifyDate()
    {
        return this.modifyDate;
    }
    
    /**
     * Gets the player stat summary type.
     *
     * @return the player stat summary type
     */
    public String getPlayerStatSummaryType()
    {
        return this.playerStatSummaryType;
    }
    
    /**
     * Gets the wins.
     *
     * @return the wins
     */
    public Integer getWins()
    {
        return this.wins;
    }
    
    @Override
    public int hashCode()
    {
        final int prime  = 31;
        int       result = 1;
        result = (prime * result) + ((this.aggregatedStats == null) ? 0 : this.aggregatedStats.hashCode());
        result = (prime * result) + ((this.losses == null) ? 0 : this.losses.hashCode());
        result = (prime * result) + ((this.modifyDate == null) ? 0 : this.modifyDate.hashCode());
        result = (prime * result) + ((this.playerStatSummaryType == null) ? 0 : this.playerStatSummaryType.hashCode());
        result = (prime * result) + ((this.wins == null) ? 0 : this.wins.hashCode());
        return result;
    }
    
    @Override
    public String toString()
    {
        return "PlayerStatsSummary{" +
               "aggregatedStats=" + aggregatedStats +
               ", losses=" + losses +
               ", modifyDate=" + modifyDate +
               ", playerStatSummaryType='" + playerStatSummaryType + '\'' +
               ", wins=" + wins +
               '}';
    }
}
