package no.stelar7.api.l4j8.pojo.tournament;

import java.io.Serializable;

public class TournamentCallbackSummoner implements Serializable
{
    private static final long serialVersionUID = 1937499754440856714L;
    
    private String summonerName;
    private long   summonerId;
    
    
    public long getSummonerId()
    {
        return this.summonerId;
    }
    
    public String getSummonerName()
    {
        return this.summonerName;
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
        
        TournamentCallbackSummoner that = (TournamentCallbackSummoner) o;
        
        if (summonerId != that.summonerId)
        {
            return false;
        }
        return (summonerName != null) ? summonerName.equals(that.summonerName) : (that.summonerName == null);
    }
    
    @Override
    public int hashCode()
    {
        int result = summonerName != null ? summonerName.hashCode() : 0;
        result = 31 * result + (int) (summonerId ^ (summonerId >>> 32));
        return result;
    }
    
    @Override
    public String toString()
    {
        return "TournamentCallbackSummoner [summonerName=" + this.summonerName + ", summonerId=" + this.summonerId + "]";
    }
}
