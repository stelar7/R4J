package no.stelar7.api.l4j8.pojo.tournament;

import java.util.List;

public class TournamentCallback
{
    Long                             startTime;
    List<TournamentCallbackSummoner> winningTeam;
    List<TournamentCallbackSummoner> losingTeam;
    String                           shortCode;
    String                           metaData;
    Long                             gameId;
    String                           gameName;
    String                           gameType;
    Integer                          gameMap;
    String                           gameMode;
    String                           region;

    public Long getStartTime()
    {
        return startTime;
    }

    public List<TournamentCallbackSummoner> getWinningTeam()
    {
        return winningTeam;
    }

    public List<TournamentCallbackSummoner> getLosingTeam()
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

    public Long getGameId()
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

    public Integer getGameMap()
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
    public String toString()
    {
        return "TournamentCallback [startTime=" + startTime + ", winningTeam=" + winningTeam + ", losingTeam=" + losingTeam + ", shortCode=" + shortCode + ", metaData=" + metaData + ", gameId=" + gameId + ", gameName=" + gameName + ", gameType=" + gameType + ", gameMap=" + gameMap + ", gameMode=" + gameMode + ", region=" + region + "]";
    }

    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((gameId == null) ? 0 : gameId.hashCode());
        result = prime * result + ((gameMap == null) ? 0 : gameMap.hashCode());
        result = prime * result + ((gameMode == null) ? 0 : gameMode.hashCode());
        result = prime * result + ((gameName == null) ? 0 : gameName.hashCode());
        result = prime * result + ((gameType == null) ? 0 : gameType.hashCode());
        result = prime * result + ((losingTeam == null) ? 0 : losingTeam.hashCode());
        result = prime * result + ((metaData == null) ? 0 : metaData.hashCode());
        result = prime * result + ((region == null) ? 0 : region.hashCode());
        result = prime * result + ((shortCode == null) ? 0 : shortCode.hashCode());
        result = prime * result + ((startTime == null) ? 0 : startTime.hashCode());
        result = prime * result + ((winningTeam == null) ? 0 : winningTeam.hashCode());
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
        TournamentCallback other = (TournamentCallback) obj;
        if (gameId == null)
        {
            if (other.gameId != null)
                return false;
        } else if (!gameId.equals(other.gameId))
            return false;
        if (gameMap == null)
        {
            if (other.gameMap != null)
                return false;
        } else if (!gameMap.equals(other.gameMap))
            return false;
        if (gameMode == null)
        {
            if (other.gameMode != null)
                return false;
        } else if (!gameMode.equals(other.gameMode))
            return false;
        if (gameName == null)
        {
            if (other.gameName != null)
                return false;
        } else if (!gameName.equals(other.gameName))
            return false;
        if (gameType == null)
        {
            if (other.gameType != null)
                return false;
        } else if (!gameType.equals(other.gameType))
            return false;
        if (losingTeam == null)
        {
            if (other.losingTeam != null)
                return false;
        } else if (!losingTeam.equals(other.losingTeam))
            return false;
        if (metaData == null)
        {
            if (other.metaData != null)
                return false;
        } else if (!metaData.equals(other.metaData))
            return false;
        if (region == null)
        {
            if (other.region != null)
                return false;
        } else if (!region.equals(other.region))
            return false;
        if (shortCode == null)
        {
            if (other.shortCode != null)
                return false;
        } else if (!shortCode.equals(other.shortCode))
            return false;
        if (startTime == null)
        {
            if (other.startTime != null)
                return false;
        } else if (!startTime.equals(other.startTime))
            return false;
        if (winningTeam == null)
        {
            if (other.winningTeam != null)
                return false;
        } else if (!winningTeam.equals(other.winningTeam))
            return false;
        return true;
    }
}
