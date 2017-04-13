package no.stelar7.api.l4j8.pojo.shared;

import no.stelar7.api.l4j8.basic.constants.types.*;

public class BannedChampion
{
    private ChampionType championId;
    private Integer      pickTurn;
    private TeamType     teamId;
    
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
        final BannedChampion other = (BannedChampion) obj;
        if (this.championId == null)
        {
            if (other.championId != null)
            {
                return false;
            }
        } else if (!this.championId.equals(other.championId))
        {
            return false;
        }
        if (this.pickTurn == null)
        {
            if (other.pickTurn != null)
            {
                return false;
            }
        } else if (!this.pickTurn.equals(other.pickTurn))
        {
            return false;
        }
        if (this.teamId == null)
        {
            if (other.teamId != null)
            {
                return false;
            }
        } else if (!this.teamId.equals(other.teamId))
        {
            return false;
        }
        return true;
    }
    
    
    /**
     * The ID of the banned champion
     *
     * @return Long
     */
    public ChampionType getChampion()
    {
        return this.championId;
    }
    
    /**
     * The turn during which the champion was banned
     *
     * @return Integer
     */
    public Integer getPickTurn()
    {
        return this.pickTurn;
    }
    
    
    /**
     * The ID of the team that banned the champion
     *
     * @return Long
     */
    public TeamType getTeam()
    {
        return this.teamId;
    }
    
    @Override
    public int hashCode()
    {
        final int prime  = 31;
        int       result = 1;
        result = (prime * result) + ((this.championId == null) ? 0 : this.championId.hashCode());
        result = (prime * result) + ((this.pickTurn == null) ? 0 : this.pickTurn.hashCode());
        result = (prime * result) + ((this.teamId == null) ? 0 : this.teamId.hashCode());
        return result;
    }
    
    @Override
    public String toString()
    {
        return "BannedChampion{" +
               "championId=" + championId +
               ", pickTurn=" + pickTurn +
               ", teamId=" + teamId +
               '}';
    }
}
