package no.stelar7.api.r4j.pojo.lol.tournament;

import java.io.Serializable;
import java.util.*;

public class TournamentGame implements Serializable
{
    private static final long serialVersionUID = 1L;

    private long                       startTime;
    private List<TournamentTeamMember> winningTeam;
    private List<TournamentTeamMember> losingTeam;
    private String                     shortCode;
    private String                     metaData;
    private long                       gameId;
    private String                     gameName;
    private String                     gameType;
    private int                        gameMap;
    private String                     gameMode;
    private String                     region;

    public long getStartTime()
    {
        return startTime;
    }
    
    public List<TournamentTeamMember> getWinningTeam()
    {
        return winningTeam;
    }
    
    public List<TournamentTeamMember> getLosingTeam()
    {
        return losingTeam;
    }
    
    public String getShortCode()
    {
        return shortCode;
    }
    
    public String getMetaData()
    {
        return metaData;
    }
    
    public long getGameId()
    {
        return gameId;
    }
    
    public String getGameName()
    {
        return gameName;
    }
    
    public String getGameType()
    {
        return gameType;
    }
    
    public int getGameMap()
    {
        return gameMap;
    }
    
    public String getGameMode()
    {
        return gameMode;
    }
    
    public String getRegion()
    {
        return region;
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
        TournamentGame that = (TournamentGame) o;
        return startTime == that.startTime &&
               Objects.equals(winningTeam, that.winningTeam) &&
               Objects.equals(losingTeam, that.losingTeam) &&
               Objects.equals(shortCode, that.shortCode) &&
               Objects.equals(metaData, that.metaData) &&
               gameId == that.gameId &&
               Objects.equals(gameName, that.gameName) &&
               Objects.equals(gameType, that.gameType) &&
               gameMap == that.gameMap &&
               Objects.equals(gameMode, that.gameMode) &&
               Objects.equals(region, that.region);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(startTime, winningTeam, losingTeam, shortCode, metaData, gameId, gameName, gameType, gameMap, gameMode, region);
    }

    @Override
    public String toString()
    {
        return "TournamentGame{" +
               "startTime=" + startTime +
               ", winningTeam=" + winningTeam +
               ", losingTeam=" + losingTeam +
               ", shortCode=" + shortCode +
               ", metaData=" + metaData +
               ", gameId=" + gameId +
               ", gameName=" + gameName +
               ", gameType=" + gameType +
               ", gameMap=" + gameMap +
               ", gameMode=" + gameMode +
               ", region=" + region +
               '}';
    }
}
