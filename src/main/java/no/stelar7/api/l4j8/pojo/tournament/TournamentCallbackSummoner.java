package no.stelar7.api.l4j8.pojo.tournament;

public class TournamentCallbackSummoner
{
    String summonerName;
    Long   summonerId;

    @Override
    public String toString()
    {
        return "TournamentCallbackSummoner [summonerName=" + summonerName + ", summonerId=" + summonerId + "]";
    }

    public String getSummonerName()
    {
        return summonerName;
    }

    public Long getSummonerId()
    {
        return summonerId;
    }

    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((summonerId == null) ? 0 : summonerId.hashCode());
        result = prime * result + ((summonerName == null) ? 0 : summonerName.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        TournamentCallbackSummoner other = (TournamentCallbackSummoner) obj;
        if (summonerId == null)
        {
            if (other.summonerId != null)
                return false;
        } else if (!summonerId.equals(other.summonerId))
            return false;
        if (summonerName == null)
        {
            if (other.summonerName != null)
                return false;
        } else if (!summonerName.equals(other.summonerName))
            return false;
        return true;
    }
}
