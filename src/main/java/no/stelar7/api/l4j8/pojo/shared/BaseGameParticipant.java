package no.stelar7.api.l4j8.pojo.shared;

import no.stelar7.api.l4j8.basic.constants.types.*;

public class BaseGameParticipant
{
    private ChampionType      championId;
    private Long              profileIconId;
    private SummonerSpellType spell1Id;
    private SummonerSpellType spell2Id;
    private String            summonerName;
    private TeamType          teamId;
    
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
        final BaseGameParticipant other = (BaseGameParticipant) obj;
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
     * The ID of the champion played by this participant
     *
     * @return Long
     */
    protected ChampionType getChampion()
    {
        return this.championId;
    }
    
    /**
     * The ID of the profile icon used by this participant
     *
     * @return Long
     */
    protected Long getProfileIconId()
    {
        return this.profileIconId;
    }
    
    /**
     * The ID of the first summoner spell used by this participant
     *
     * @return Long
     */
    protected SummonerSpellType getSpell1()
    {
        return this.spell1Id;
    }
    
    /**
     * The ID of the second summoner spell used by this participant
     *
     * @return Long
     */
    protected SummonerSpellType getSpell2()
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
     * The team ID of this participant, indicating the participant's team
     *
     * @return Long
     */
    protected TeamType getTeam()
    {
        return this.teamId;
    }
    
    @Override
    public int hashCode()
    {
        final int prime  = 31;
        int       result = 1;
        result = (prime * result) + ((this.championId == null) ? 0 : this.championId.hashCode());
        result = (prime * result) + ((this.profileIconId == null) ? 0 : this.profileIconId.hashCode());
        result = (prime * result) + ((this.spell1Id == null) ? 0 : this.spell1Id.hashCode());
        result = (prime * result) + ((this.spell2Id == null) ? 0 : this.spell2Id.hashCode());
        result = (prime * result) + ((this.summonerName == null) ? 0 : this.summonerName.hashCode());
        result = (prime * result) + ((this.teamId == null) ? 0 : this.teamId.hashCode());
        return result;
    }
    
    @Override
    public String toString()
    {
        return "BaseGameParticipant{" +
               "championId=" + championId +
               ", profileIconId=" + profileIconId +
               ", spell1Id=" + spell1Id +
               ", spell2Id=" + spell2Id +
               ", summonerName='" + summonerName + '\'' +
               ", teamId=" + teamId +
               '}';
    }
}
