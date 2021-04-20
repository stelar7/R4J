package no.stelar7.api.r4j.pojo.val.ranked;

import java.io.Serializable;
import java.util.*;

public class Leaderboard implements Serializable
{
    private static final long serialVersionUID = -1887406144586619846L;
    
    private String                  shard;
    private String                  actId;
    private long                    totalPlayers;
    private List<LeaderboardPlayer> players;
    
    public String getShard()
    {
        return shard;
    }
    
    public String getActId()
    {
        return actId;
    }
    
    public long getTotalPlayers()
    {
        return totalPlayers;
    }
    
    public List<LeaderboardPlayer> getPlayers()
    {
        return players;
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
        Leaderboard that = (Leaderboard) o;
        return totalPlayers == that.totalPlayers && Objects.equals(shard, that.shard) && Objects.equals(actId, that.actId) && Objects.equals(players, that.players);
    }
    
    @Override
    public int hashCode()
    {
        return Objects.hash(shard, actId, totalPlayers, players);
    }
    
    @Override
    public String toString()
    {
        return "Leaderboard{" +
               "shard='" + shard + '\'' +
               ", actId='" + actId + '\'' +
               ", totalPlayers=" + totalPlayers +
               ", players=" + players +
               '}';
    }
}
