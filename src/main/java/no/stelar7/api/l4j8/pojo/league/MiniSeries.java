package no.stelar7.api.l4j8.pojo.league;

public class MiniSeries
{
    private Integer losses;
    private String  progress;
    private Integer target;
    private Integer wins;

    /**
     * Number of current losses in the mini series.
     *
     * @return the losses
     */
    public Integer getLosses()
    {
        return losses;
    }

    /**
     * String showing the current, sequential mini series progress where 'W' represents a win, 'L' represents a loss, and 'N' represents a game that hasn't been played yet.
     *
     * @return the progress
     */
    public String getProgress()
    {
        return progress;
    }

    /**
     * Number of wins required for promotion.
     *
     * @return the target
     */
    public Integer getTarget()
    {
        return target;
    }

    /**
     * Number of current wins in the mini series.
     *
     * @return the wins
     */
    public Integer getWins()
    {
        return wins;
    }

}
