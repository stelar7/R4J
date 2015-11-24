package no.stelar7.api.l4j8.pojo.team;

import java.time.Instant;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.List;

import no.stelar7.api.l4j8.basic.APIObject;

public class Team implements APIObject
{
    private Long                      createDate;
    private String                    fullId;
    private Long                      lastGameDate;
    private Long                      lastJoinDate;
    private Long                      lastJoinedRankedTeamQueueDate;
    private List<MatchHistorySummary> matchHistory;
    private Long                      modifyDate;
    private String                    name;
    private Roster                    roster;
    private Long                      secondLastJoinDate;
    private String                    status;
    private String                    tag;
    private List<TeamStatDetail>      teamStatDetails;
    private Long                      thirdLastJoinDate;

    /**
     * Date that team was created specified as epoch milliseconds.
     *
     * @return the creates the date
     */
    public Long getCreateDate()
    {
        return createDate;
    }

    /**
     * A ZonedDateTime of {@code getCreateDate()}
     *
     * * @return ZonedDateTime
     */
    public ZonedDateTime getCreateDateAsDate()
    {
        return ZonedDateTime.ofInstant(Instant.ofEpochMilli(this.createDate), ZoneOffset.UTC);
    }

    /**
     * Gets the full id.
     *
     * @return the full id
     */
    public String getFullId()
    {
        return fullId;
    }

    /**
     * Date that last game played by team ended specified as epoch milliseconds.
     *
     * @return the last game date
     */
    public Long getLastGameDate()
    {
        return lastGameDate;
    }

    /**
     * A ZonedDateTime of {@code getLastGameDate()}
     *
     * * @return ZonedDateTime
     */
    public ZonedDateTime getLastGameDateAsDate()
    {
        return ZonedDateTime.ofInstant(Instant.ofEpochMilli(this.lastGameDate), ZoneOffset.UTC);
    }

    /**
     * Date that last member joined specified as epoch milliseconds.
     *
     * @return the last join date
     */
    public Long getLastJoinDate()
    {
        return lastJoinDate;
    }

    /**
     * Date that team last joined the ranked team queue specified as epoch milliseconds.
     *
     * @return the last joined ranked team queue date
     */
    public Long getLastJoinedRankedTeamQueueDate()
    {
        return lastJoinedRankedTeamQueueDate;
    }

    /**
     * A ZonedDateTime of {@code getLastJoinedRankedTeamQueueDate()}
     *
     * * @return ZonedDateTime
     */
    public ZonedDateTime getLastJoinedRankedTeamQueueDateAsDate()
    {
        return ZonedDateTime.ofInstant(Instant.ofEpochMilli(this.lastJoinedRankedTeamQueueDate), ZoneOffset.UTC);
    }

    /**
     * Gets the match history.
     *
     * @return the match history
     */
    public List<MatchHistorySummary> getMatchHistory()
    {
        return matchHistory;
    }

    /**
     * Date that team was last modified specified as epoch milliseconds.
     *
     * @return the modify date
     */
    public Long getModifyDate()
    {
        return modifyDate;
    }

    /**
     * A ZonedDateTime of {@code getModifyDate()}
     *
     * * @return ZonedDateTime
     */
    public ZonedDateTime getModifyDateAsDate()
    {
        return ZonedDateTime.ofInstant(Instant.ofEpochMilli(this.modifyDate), ZoneOffset.UTC);
    }

    /**
     * Gets the name.
     *
     * @return the name
     */
    public String getName()
    {
        return name;
    }

    /**
     * Gets the roster.
     *
     * @return the roster
     */
    public Roster getRoster()
    {
        return roster;
    }

    /**
     * Date that second to last member joined specified as epoch milliseconds.
     *
     * @return the second last join date
     */
    public Long getSecondLastJoinDate()
    {
        return secondLastJoinDate;
    }

    /**
     * A ZonedDateTime of {@code getSecondLastJoinDate()}
     *
     * * @return ZonedDateTime
     */
    public ZonedDateTime getSecondLastJoinDateAsDate()
    {
        return ZonedDateTime.ofInstant(Instant.ofEpochMilli(this.secondLastJoinDate), ZoneOffset.UTC);
    }

    /**
     * Gets the status.
     *
     * @return the status
     */
    public String getStatus()
    {
        return status;
    }

    /**
     * Gets the tag.
     *
     * @return the tag
     */
    public String getTag()
    {
        return tag;
    }

    /**
     * Gets the team stat details.
     *
     * @return the team stat details
     */
    public List<TeamStatDetail> getTeamStatDetails()
    {
        return teamStatDetails;
    }

    /**
     * Date that third to last member joined specified as epoch milliseconds.
     *
     * @return the third last join date
     */
    public Long getThirdLastJoinDate()
    {
        return thirdLastJoinDate;
    }

    /**
     * A ZonedDateTime of {@code getThirdLastJoinDate()}
     *
     * * @return ZonedDateTime
     */
    public ZonedDateTime getThirdLastJoinDateAsDate()
    {
        return ZonedDateTime.ofInstant(Instant.ofEpochMilli(this.thirdLastJoinDate), ZoneOffset.UTC);
    }

}
