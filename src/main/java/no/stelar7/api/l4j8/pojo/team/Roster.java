package no.stelar7.api.l4j8.pojo.team;

import java.util.List;

public class Roster
{
    private List<TeamMemberInfo> memberList;
    private Long                 ownerId;

    /**
     * Gets the member list.
     *
     * @return the member list
     */
    public List<TeamMemberInfo> getMemberList()
    {
        return memberList;
    }

    /**
     * Gets the owner id.
     *
     * @return the owner id
     */
    public Long getOwnerId()
    {
        return ownerId;
    }

}
