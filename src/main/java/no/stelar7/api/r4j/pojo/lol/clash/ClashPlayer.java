package no.stelar7.api.r4j.pojo.lol.clash;

import java.io.Serializable;
import java.util.Objects;


public class ClashPlayer implements Serializable
{
    private static final long serialVersionUID = 7753261596504782527L;
    private String        puuid;
    private String        teamId;
    private ClashPosition position;
    private ClashRole     role;
    
    public String getPuuid()
    {
        return puuid;
    }
    
    /**
     * Returns the id of the team, may be null if received through a team
     * @return The teamId of the team this player is in
     */
    @Nullable
    public String getTeamId()
    {
        return teamId;
    }
    
    public ClashPosition getPosition()
    {
        return position;
    }
    
    public ClashRole getRole()
    {
        return role;
    }
    
    @Override
    public boolean equals(Object o)
    {
        if (this == o)
        {
            return true;
        }
        if (o == null || getClass() != o.getClass())
        {
            return false;
        }
        ClashPlayer that = (ClashPlayer) o;
        return Objects.equals(puuid, that.puuid) &&
               Objects.equals(teamId, that.teamId) &&
               position == that.position &&
               role == that.role;
    }
    
    @Override
    public int hashCode()
    {
        return Objects.hash(puuid, teamId, position, role);
    }
    
    @Override
    public String toString()
    {
        return "ClashPlayer{" +
               "puuid='" + puuid + '\'' +
               ", teamId='" + teamId + '\'' +
               ", position=" + position +
               ", role=" + role +
               '}';
    }
}
