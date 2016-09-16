package no.stelar7.api.l4j8.pojo.game;

import no.stelar7.api.l4j8.basic.constants.Champion;
import no.stelar7.api.l4j8.basic.constants.Team;

import java.util.Optional;

public class Player
{
    private Integer championId;
    private Long    summonerId;
    private Integer teamId;

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
        final Player other = (Player) obj;
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
        if (this.summonerId == null)
        {
            if (other.summonerId != null)
            {
                return false;
            }
        } else if (!this.summonerId.equals(other.summonerId))
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
     * The champion as a Champion
     *
     * @return Champion
     */
    public Optional<Champion> getChampion()
    {
        return Champion.getFromId(this.championId);
    }

    /**
     * Champion id associated with player.
     *
     * @return Integer
     */
    public Integer getChampionId()
    {
        return this.championId;
    }

    /**
     * Summoner id associated with player.
     *
     * @return Long
     */
    public Long getSummonerId()
    {
        return this.summonerId;
    }

    /**
     * a Team representing the team associated with player.
     *
     * @return Team
     */
    public Optional<Team> getTeam()
    {
        return Team.getFromCode(this.teamId);
    }

    /**
     * Team id associated with player.
     *
     * @return Integer
     */
    public Integer getTeamId()
    {
        return this.teamId;
    }

    @Override
    public int hashCode()
    {
        final int prime  = 31;
        int       result = 1;
        result = (prime * result) + ((this.championId == null) ? 0 : this.championId.hashCode());
        result = (prime * result) + ((this.summonerId == null) ? 0 : this.summonerId.hashCode());
        result = (prime * result) + ((this.teamId == null) ? 0 : this.teamId.hashCode());
        return result;
    }

    @Override
    public String toString()
    {
        return "Player [championId=" + this.championId + ", summonerId=" + this.summonerId + ", teamId=" + this.teamId + "]";
    }
}
