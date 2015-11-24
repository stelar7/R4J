package no.stelar7.api.l4j8.pojo.team;

import java.time.Instant;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;

public class MatchHistorySummary
{
    private Integer assists;
    private Integer deaths;
    private Integer kills;
    private Integer mapId;
    private Integer opposingTeamKills;
    private Long    date;
    private Boolean invalid;
    private String  gameMode;
    private Long    gameId;
    private String  opposingTeamName;
    private Boolean win;

    /**
     * Gets the assists.
     *
     * @return the assists
     */
    public Integer getAssists()
    {
        return assists;
    }

    /**
     * Gets the deaths.
     *
     * @return the deaths
     */
    public Integer getDeaths()
    {
        return deaths;
    }

    /**
     * Gets the kills.
     *
     * @return the kills
     */
    public Integer getKills()
    {
        return kills;
    }

    /**
     * Gets the map id.
     *
     * @return the map id
     */
    public Integer getMapId()
    {
        return mapId;
    }

    /**
     * Gets the opposing team kills.
     *
     * @return the opposing team kills
     */
    public Integer getOpposingTeamKills()
    {
        return opposingTeamKills;
    }

    /**
     * Date that match was completed specified as epoch milliseconds.
     *
     * @return the date
     */
    public Long getDate()
    {
        return date;
    }
    
    /**
     * A ZonedDateTime of {@code getDate()}
     *
     * * @return ZonedDateTime
     */
    public ZonedDateTime getDateAsDate()
    {
        return ZonedDateTime.ofInstant(Instant.ofEpochMilli(this.date), ZoneOffset.UTC);
    }

    /**
     * Gets the invalid.
     *
     * @return the invalid
     */
    public Boolean getInvalid()
    {
        return invalid;
    }

    /**
     * Gets the game mode.
     *
     * @return the game mode
     */
    public String getGameMode()
    {
        return gameMode;
    }

    /**
     * Gets the game id.
     *
     * @return the game id
     */
    public Long getGameId()
    {
        return gameId;
    }

    /**
     * Gets the opposing team name.
     *
     * @return the opposing team name
     */
    public String getOpposingTeamName()
    {
        return opposingTeamName;
    }

    /**
     * Gets the win.
     *
     * @return the win
     */
    public Boolean getWin()
    {
        return win;
    }

}
