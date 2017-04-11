package no.stelar7.api.l4j8.pojo.team;

import java.time.*;

public class TeamMemberInfo
{
    private Long   inviteDate;
    private Long   joinDate;
    private Long   playerId;
    private String status;
    
    
    
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
        final TeamMemberInfo other = (TeamMemberInfo) obj;
        if (this.inviteDate == null)
        {
            if (other.inviteDate != null)
            {
                return false;
            }
        } else if (!this.inviteDate.equals(other.inviteDate))
        {
            return false;
        }
        if (this.joinDate == null)
        {
            if (other.joinDate != null)
            {
                return false;
            }
        } else if (!this.joinDate.equals(other.joinDate))
        {
            return false;
        }
        if (this.playerId == null)
        {
            if (other.playerId != null)
            {
                return false;
            }
        } else if (!this.playerId.equals(other.playerId))
        {
            return false;
        }
        if (this.status == null)
        {
            if (other.status != null)
            {
                return false;
            }
        } else if (!this.status.equals(other.status))
        {
            return false;
        }
        return true;
    }
    
    /**
     * Date that team member was invited to team specified as epoch milliseconds.
     *
     * @return the invite date
     */
    public Long getInviteDate()
    {
        return this.inviteDate;
    }
    
    /**
     * A ZonedDateTime of {@code getInviteDateAsDate()}
     * <p>
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
        return this.joinDate;
    }
    
    /**
     * A ZonedDateTime of {@code getInviteDateAsDate()}
     * <p>
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
        return this.playerId;
    }
    
    /**
     * Gets the status.
     *
     * @return the status
     */
    public String getStatus()
    {
        return this.status;
    }
    
    @Override
    public int hashCode()
    {
        final int prime  = 31;
        int       result = 1;
        result = (prime * result) + ((this.inviteDate == null) ? 0 : this.inviteDate.hashCode());
        result = (prime * result) + ((this.joinDate == null) ? 0 : this.joinDate.hashCode());
        result = (prime * result) + ((this.playerId == null) ? 0 : this.playerId.hashCode());
        result = (prime * result) + ((this.status == null) ? 0 : this.status.hashCode());
        return result;
    }
    
    @Override
    public String toString()
    {
        return "TeamMemberInfo{" +
               "inviteDate=" + inviteDate +
               ", joinDate=" + joinDate +
               ", playerId=" + playerId +
               ", status='" + status + '\'' +
               '}';
    }
}
