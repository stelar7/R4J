package no.stelar7.api.l4j8.pojo.featuredgames;

import java.util.*;

import no.stelar7.api.l4j8.basic.constants.*;

public class FeaturedGameParticipant
{
    private Boolean bot;
    private Integer championId;
    private Long    profileIconId;
    private Long    spell1Id;
    private Long    spell2Id;
    private String  summonerName;
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
        final FeaturedGameParticipant other = (FeaturedGameParticipant) obj;
        if (this.bot == null)
        {
            if (other.bot != null)
            {
                return false;
            }
        } else if (!this.bot.equals(other.bot))
        {
            return false;
        }
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
        if (this.profileIconId == null)
        {
            if (other.profileIconId != null)
            {
                return false;
            }
        } else if (!this.profileIconId.equals(other.profileIconId))
        {
            return false;
        }
        if (this.spell1Id == null)
        {
            if (other.spell1Id != null)
            {
                return false;
            }
        } else if (!this.spell1Id.equals(other.spell1Id))
        {
            return false;
        }
        if (this.spell2Id == null)
        {
            if (other.spell2Id != null)
            {
                return false;
            }
        } else if (!this.spell2Id.equals(other.spell2Id))
        {
            return false;
        }
        if (this.summonerName == null)
        {
            if (other.summonerName != null)
            {
                return false;
            }
        } else if (!this.summonerName.equals(other.summonerName))
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
     * The ID of the champion played by this participant
     *
     * @return Long
     */
    public Integer getChampionId()
    {
        return this.championId;
    }

    /**
     * The ID of the profile icon used by this participant
     *
     * @return Long
     */
    public Long getProfileIconId()
    {
        return this.profileIconId;
    }

    /**
     * The ID of the first summoner spell used by this participant
     *
     * @return Long
     */
    public Long getSpell1Id()
    {
        return this.spell1Id;
    }

    /**
     * The ID of the second summoner spell used by this participant
     *
     * @return Long
     */
    public Long getSpell2Id()
    {
        return this.spell2Id;
    }

    /**
     * The summoner name of this participant
     *
     * @return String
     */
    public String getSummonerName()
    {
        return this.summonerName;
    }

    /**
     * a Team representing the team of the participant
     *
     * @return Team
     */
    public Optional<Team> getTeam()
    {
        return Team.getFromCode(this.teamId);
    }

    /**
     * The team ID of this participant, indicating the participant's team
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
        result = (prime * result) + ((this.bot == null) ? 0 : this.bot.hashCode());
        result = (prime * result) + ((this.championId == null) ? 0 : this.championId.hashCode());
        result = (prime * result) + ((this.profileIconId == null) ? 0 : this.profileIconId.hashCode());
        result = (prime * result) + ((this.spell1Id == null) ? 0 : this.spell1Id.hashCode());
        result = (prime * result) + ((this.spell2Id == null) ? 0 : this.spell2Id.hashCode());
        result = (prime * result) + ((this.summonerName == null) ? 0 : this.summonerName.hashCode());
        result = (prime * result) + ((this.teamId == null) ? 0 : this.teamId.hashCode());
        return result;
    }

    /**
     * Flag indicating whether or not this participant is a bot
     *
     * @return Boolean
     */
    public Boolean isBot()
    {
        return this.bot;
    }

    @Override
    public String toString()
    {
        return "FeaturedGameParticipant [bot=" + this.bot + ", championId=" + this.championId + ", profileIconId=" + this.profileIconId + ", spell1Id=" + this.spell1Id + ", spell2Id=" + this.spell2Id + ", summonerName=" + this.summonerName + ", teamId=" + this.teamId + "]";
    }

}
