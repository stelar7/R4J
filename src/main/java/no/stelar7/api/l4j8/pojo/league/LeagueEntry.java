package no.stelar7.api.l4j8.pojo.league;

public class LeagueEntry
{
    private String     division;
    private Boolean    isFreshBlood;
    private Boolean    isHotStreak;
    private Boolean    isInactive;
    private Boolean    isVeteran;
    private Integer    leaguePoints;
    private Integer    losses;
    private MiniSeries miniSeries;
    private String     playerOrTeamId;
    private String     playerOrTeamName;
    private Integer    wins;

    /**
     * The league division of the participant.
     *
     * @return the division
     */
    public String getDivision()
    {
        return division;
    }

    /**
     * Specifies if the participant is fresh blood.
     *
     * @return the checks if is fresh blood
     */
    public Boolean getIsFreshBlood()
    {
        return isFreshBlood;
    }

    /**
     * Specifies if the participant is on a hot streak.
     *
     * @return the checks if is hot streak
     */
    public Boolean getIsHotStreak()
    {
        return isHotStreak;
    }

    /**
     * Specifies if the participant is inactive.
     *
     * @return the checks if is inactive
     */
    public Boolean getIsInactive()
    {
        return isInactive;
    }

    /**
     * Specifies if the participant is a veteran
     *
     * @return the checks if is veteran
     */
    public Boolean getIsVeteran()
    {
        return isVeteran;
    }

    /**
     * The league points of the participant.
     *
     * @return the league points
     */
    public Integer getLeaguePoints()
    {
        return leaguePoints;
    }

    /**
     * The number of losses for the participant.
     * 
     * @return the losses
     */
    public Integer getLosses()
    {
        return losses;
    }

    /**
     * Mini series data for the participant. Only present if the participant is currently in a mini series.
     *
     * @return the mini series
     */
    public MiniSeries getMiniSeries()
    {
        return miniSeries;
    }

    /**
     * The ID of the participant (i.e., summoner or team) represented by this entry.
     *
     * @return the player or team id
     */
    public String getPlayerOrTeamId()
    {
        return playerOrTeamId;
    }

    /**
     * The name of the the participant (i.e., summoner or team) represented by this entry.
     *
     * @return the player or team name
     */
    public String getPlayerOrTeamName()
    {
        return playerOrTeamName;
    }

    /**
     * The number of wins for the participant.
     *
     * @return the wins
     */
    public Integer getWins()
    {
        return wins;
    }

}
