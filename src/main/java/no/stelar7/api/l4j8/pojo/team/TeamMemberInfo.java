package no.stelar7.api.l4j8.pojo.team;

import java.time.Instant;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;

public class TeamMemberInfo
{
    private Long   inviteDate;
    private Long   joinDate;
    private Long   playerId;
    private String status;

    /**
     * Date that team member was invited to team specified as epoch milliseconds.
     *
     * @return the invite date
     */
    public Long getInviteDate()
    {
        return inviteDate;
    }

    /**
     * A ZonedDateTime of {@code getInviteDateAsDate()}
     *
     * * @return ZonedDateTime
     */
    public ZonedDateTime getInviteDateAsDate()
    {
        return ZonedDateTime.ofInstant(Instant.ofEpochMilli(this.inviteDate), ZoneOffset.UTC);
    }

    /**
     * Date that team member joined team specified as epoch milliseconds.
     *
     * @return the join date
     */
    public Long getJoinDate()
    {
        return joinDate;
    }

    /**
     * A ZonedDateTime of {@code getInviteDateAsDate()}
     *
     * * @return ZonedDateTime
     */
    public ZonedDateTime getJoinDateAsDate()
    {
        return ZonedDateTime.ofInstant(Instant.ofEpochMilli(this.joinDate), ZoneOffset.UTC);
    }

    /**
     * Gets the player id.
     *
     * @return the player id
     */
    public Long getPlayerId()
    {
        return playerId;
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

}
