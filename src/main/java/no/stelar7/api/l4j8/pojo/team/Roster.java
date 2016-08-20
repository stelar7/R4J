package no.stelar7.api.l4j8.pojo.team;

import java.util.List;

public class Roster
{
    private List<TeamMemberInfo> memberList;
    private Long ownerId;

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
        final Roster other = (Roster) obj;
        if (this.memberList == null)
        {
            if (other.memberList != null)
            {
                return false;
            }
        } else if (!this.memberList.equals(other.memberList))
        {
            return false;
        }
        if (this.ownerId == null)
        {
            if (other.ownerId != null)
            {
                return false;
            }
        } else if (!this.ownerId.equals(other.ownerId))
        {
            return false;
        }
        return true;
    }

    /**
     * Gets the member list.
     *
     * @return the member list
     */
    public List<TeamMemberInfo> getMemberList()
    {
        return this.memberList;
    }

    /**
     * Gets the owner id.
     *
     * @return the owner id
     */
    public Long getOwnerId()
    {
        return this.ownerId;
    }

    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = (prime * result) + ((this.memberList == null) ? 0 : this.memberList.hashCode());
        result = (prime * result) + ((this.ownerId == null) ? 0 : this.ownerId.hashCode());
        return result;
    }

}
