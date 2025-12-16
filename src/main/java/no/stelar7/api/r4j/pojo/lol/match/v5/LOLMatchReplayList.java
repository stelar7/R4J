package no.stelar7.api.r4j.pojo.lol.match.v5;

import java.io.Serializable;
import java.util.*;

public class LOLMatchReplayList implements Serializable
{
    private static final long serialVersionUID = -1841657355311492853L;
    
    private int          total;
    private List<String> matchFileURLs;
    
    public int getTotal()
    {
        return total;
    }
    
    public List<String> getMatchFileURLs()
    {
        return matchFileURLs;
    }
    
    @Override
    public boolean equals(Object o)
    {
        if (o == null || getClass() != o.getClass())
        {
            return false;
        }
        LOLMatchReplayList that = (LOLMatchReplayList) o;
        return total == that.total && Objects.equals(matchFileURLs, that.matchFileURLs);
    }
    
    @Override
    public int hashCode()
    {
        return Objects.hash(total, matchFileURLs);
    }
    
    @Override
    public String toString()
    {
        return "LOLMatchReplayList{" +
               "total=" + total +
               ", matchFileURLs=" + matchFileURLs +
               '}';
    }
}
