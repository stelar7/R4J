package no.stelar7.api.l4j8.pojo.tournament;

public class TournamentCallbackSummoner
{
    private String summonerName;
    private Long   summonerId;
    
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
        final TournamentCallbackSummoner other = (TournamentCallbackSummoner) obj;
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
        return true;
    }
    
    public Long getSummonerId()
    {
        return this.summonerId;
    }
    
    public String getSummonerName()
    {
        return this.summonerName;
    }
    
    @Override
    public int hashCode()
    {
        final int prime  = 31;
        int       result = 1;
        result = (prime * result) + ((this.summonerId == null) ? 0 : this.summonerId.hashCode());
        result = (prime * result) + ((this.summonerName == null) ? 0 : this.summonerName.hashCode());
        return result;
    }
    
    @Override
    public String toString()
    {
        return "TournamentCallbackSummoner [summonerName=" + this.summonerName + ", summonerId=" + this.summonerId + "]";
    }
}
