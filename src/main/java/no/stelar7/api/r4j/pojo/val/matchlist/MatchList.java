package no.stelar7.api.r4j.pojo.val.matchlist;

import java.io.Serializable;
import java.util.*;

public class MatchList implements Serializable
{
    private static final long serialVersionUID = -6256637993112869814L;
    
    private String puuid;
    private List<MatchReference> history;
    
    public String getPuuid()
    {
        return puuid;
    }
    
    public List<MatchReference> getHistory()
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
