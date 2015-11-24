package no.stelar7.api.l4j8.pojo.team;

public class TeamStatDetail
{
    private Integer averageGamesPlayed;
    private Integer losses;
    private String  teamStatType;
    private Integer wins;

    /**
     * Gets the average games played.
     *
     * @return the average games played
     */
    public Integer getAverageGamesPlayed()
    {
        return averageGamesPlayed;
    }

    /**
     * Gets the losses.
     *
     * @return the losses
     */
    public Integer getLosses()
    {
        return losses;
    }

    /**
     * Gets the team stat type.
     *
     * @return the team stat type
     */
    public String getTeamStatType()
    {
        return teamStatType;
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
