package no.stelar7.api.l4j8.pojo.shared;

import java.util.Optional;

import no.stelar7.api.l4j8.basic.APIObject;
import no.stelar7.api.l4j8.basic.constants.Champion;
import no.stelar7.api.l4j8.basic.constants.Team;

public class BannedChampion implements APIObject
{
    private Integer    championId;
    private Integer pickTurn;
    private Long    teamId;

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
     * The banned champion as a Champion
     *
     * @return Champion
     */
    public Optional<Champion> getChampion()
    {
        return Champion.getFromId(this.championId);
    }

    /**
     * The ID of the banned champion
     *
     * @return Long
     */
    public Integer getChampionId()
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
     * a Team representing the team that banned the champion
     *
     * @return Team
     */
    public Optional<Team> getTeam()
    {
        return Team.getFromCode(this.teamId);
    }

    /**
     * The ID of the team that banned the champion
     *
     * @return Long
     */
    public Long getTeamId()
    {
        return this.teamId;
    }

    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = (prime * result) + ((this.championId == null) ? 0 : this.championId.hashCode());
        result = (prime * result) + ((this.pickTurn == null) ? 0 : this.pickTurn.hashCode());
        result = (prime * result) + ((this.teamId == null) ? 0 : this.teamId.hashCode());
        return result;
    }

    @Override
    public String toString()
    {
        return "BannedChampion [championId=" + this.championId + ", pickTurn=" + this.pickTurn + ", teamId=" + this.teamId + "]";
    }

}
