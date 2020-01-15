package no.stelar7.api.r4j.pojo.lol.tournament;

import java.io.Serializable;
import java.util.List;

public class TournamentCallback implements Serializable
{
    private static final long serialVersionUID = 1224859743037788003L;
    
    private long                             startTime;
    private List<TournamentCallbackSummoner> winningTeam;
    private List<TournamentCallbackSummoner> losingTeam;
    private String                           shortCode;
    private String                           metaData;
    private long                             gameId;
    private String                           gameName;
    private String                           gameType;
    private int                              gameMap;
    private String                           gameMode;
    private String                           region;
    
    
    public long getGameId()
    {
        return this.gameId;
    }
    
    public int getGameMap()
    {
        return this.gameMap;
    }
    
    public String getGameMode()
    {
        return this.gameMode;
    }
    
    public String getGameName()
    {
        return this.gameName;
    }
    
    public String getGameType()
    {
        return this.gameType;
    }
    
    public List<TournamentCallbackSummoner> getLosingTeam()
    {
        return this.losingTeam;
    }
    
    public String getMetaData()
    {
        return this.metaData;
    }
    
    public String getRegion()
    {
        return this.region;
    }
    
    public String getShortCode()
    {
        return this.shortCode;
    }
    
    public long getStartTime()
    {
        return this.startTime;
    }
    
    public List<TournamentCallbackSummoner> getWinningTeam()
    {
        return this.winningTeam;
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
        
        TournamentCallback that = (TournamentCallback) o;
        
        if (startTime != that.startTime)
        {
            return false;
        }
        if (gameId != that.gameId)
        {
            return false;
        }
        if (gameMap != that.gameMap)
        {
            return false;
        }
        if ((winningTeam != null) ? !winningTeam.equals(that.winningTeam) : (that.winningTeam != null))
        {
            return false;
        }
        if ((losingTeam != null) ? !losingTeam.equals(that.losingTeam) : (that.losingTeam != null))
        {
            return false;
        }
        if ((shortCode != null) ? !shortCode.equals(that.shortCode) : (that.shortCode != null))
        {
            return false;
        }
        if ((metaData != null) ? !metaData.equals(that.metaData) : (that.metaData != null))
        {
            return false;
        }
        if ((gameName != null) ? !gameName.equals(that.gameName) : (that.gameName != null))
        {
            return false;
        }
        if ((gameType != null) ? !gameType.equals(that.gameType) : (that.gameType != null))
        {
            return false;
        }
        if ((gameMode != null) ? !gameMode.equals(that.gameMode) : (that.gameMode != null))
        {
            return false;
        }
        return (region != null) ? region.equals(that.region) : (that.region == null);
    }
    
    @Override
    public int hashCode()
    {
        int result = (int) (startTime ^ (startTime >>> 32));
        result = 31 * result + (winningTeam != null ? winningTeam.hashCode() : 0);
        result = 31 * result + (losingTeam != null ? losingTeam.hashCode() : 0);
        result = 31 * result + (shortCode != null ? shortCode.hashCode() : 0);
        result = 31 * result + (metaData != null ? metaData.hashCode() : 0);
        result = 31 * result + (int) (gameId ^ (gameId >>> 32));
        result = 31 * result + (gameName != null ? gameName.hashCode() : 0);
        result = 31 * result + (gameType != null ? gameType.hashCode() : 0);
        result = 31 * result + gameMap;
        result = 31 * result + (gameMode != null ? gameMode.hashCode() : 0);
        result = 31 * result + (region != null ? region.hashCode() : 0);
        return result;
    }
    
    @Override
    public String toString()
    {
        return "TournamentCallback{" +
               "startTime=" + startTime +
               ", winningTeam=" + winningTeam +
               ", losingTeam=" + losingTeam +
               ", shortCode='" + shortCode + '\'' +
               ", metaData='" + metaData + '\'' +
               ", gameId=" + gameId +
               ", gameName='" + gameName + '\'' +
               ", gameType='" + gameType + '\'' +
               ", gameMap=" + gameMap +
               ", gameMode='" + gameMode + '\'' +
               ", region='" + region + '\'' +
               '}';
    }
}
