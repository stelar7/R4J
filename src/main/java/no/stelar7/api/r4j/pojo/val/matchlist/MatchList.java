package no.stelar7.api.r4j.pojo.val.matchlist;

import java.util.*;

public class MatchList
{
    private String               puuid;
    private List<MatchListMatch> history;
    
    public String getPuuid()
    {
        return puuid;
    }
    
    public List<MatchListMatch> getHistory()
    {
        return history;
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
        MatchList matchList = (MatchList) o;
        return Objects.equals(puuid, matchList.puuid) &&
               Objects.equals(history, matchList.history);
    }
    
    @Override
    public int hashCode()
    {
        return Objects.hash(puuid, history);
    }
    
    @Override
    public String toString()
    {
        return "MatchList{" +
               "puuid='" + puuid + '\'' +
               ", history=" + history +
               '}';
    }
}
