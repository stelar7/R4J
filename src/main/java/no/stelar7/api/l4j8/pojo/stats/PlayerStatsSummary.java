package no.stelar7.api.l4j8.pojo.stats;

public class PlayerStatsSummary
{
    private AggregatedStats aggregatedStats;
    private Integer         losses;
    private Long            modifyDate;
    private String          playerStatSummaryType;
    private Integer         wins;

    /**
     * Aggregated stats.
     *
     * @return the aggregated stats
     */
    public AggregatedStats getAggregatedStats()
    {
        return aggregatedStats;
    }

    /**
     * Number of losses for this queue type. Returned for ranked queue types only.
     *
     * @return the losses
     */
    public Integer getLosses()
    {
        return losses;
    }

    /**
     * Date stats were last modified specified as epoch milliseconds.
     *
     * @return the modify date
     */
    public Long getModifyDate()
    {
        return modifyDate;
    }

    /**
     * Gets the player stat summary type.
     *
     * @return the player stat summary type
     */
    public String getPlayerStatSummaryType()
    {
        return playerStatSummaryType;
    }

    /**
     * Gets the wins.
     *
     * @return the wins
     */
    public Integer getWins()
    {
        return wins;
    }
}
