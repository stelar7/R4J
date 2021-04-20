package no.stelar7.api.r4j.pojo.val.ranked;

import java.io.Serializable;
import java.util.Objects;

public class LeaderboardPlayer implements Serializable
{
    private static final long serialVersionUID = -7507695931213137340L;
    
    private String puuid;
    private String gameName;
    private String tagLine;
    private long   leaderboardRank;
    private long   rankedRating;
    private long   numberOfWins;
    
    public String getPuuid()
    {
        return puuid;
    }
    
    public String getGameName()
    {
        return gameName;
    }
    
    public String getTagLine()
    {
        return tagLine;
    }
    
    public long getLeaderboardRank()
    {
        return leaderboardRank;
    }
    
    public long getRankedRating()
    {
        return rankedRating;
    }
    
    public long getNumberOfWins()
    {
        return numberOfWins;
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
        LeaderboardPlayer that = (LeaderboardPlayer) o;
        return leaderboardRank == that.leaderboardRank && rankedRating == that.rankedRating && numberOfWins == that.numberOfWins && Objects.equals(puuid, that.puuid) && Objects.equals(gameName, that.gameName) && Objects.equals(tagLine, that.tagLine);
    }
    
    @Override
    public int hashCode()
    {
        return Objects.hash(puuid, gameName, tagLine, leaderboardRank, rankedRating, numberOfWins);
    }
    
    @Override
    public String toString()
    {
        return "LeaderboardPlayer{" +
               "puuid='" + puuid + '\'' +
               ", gameName='" + gameName + '\'' +
               ", tagLine='" + tagLine + '\'' +
               ", leaderboardRank=" + leaderboardRank +
               ", rankedRating=" + rankedRating +
               ", numberOfWins=" + numberOfWins +
               '}';
    }
}
