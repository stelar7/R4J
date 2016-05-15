package no.stelar7.api.l4j8.pojo.tournament;

import java.util.*;

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

    @Override
    public boolean equals(final Object obj)
    {
        if (this == obj)
        {
            return true;
        }
        if (obj == null)
        {
            return false;
        }
        if (this.getClass() != obj.getClass())
        {
            return false;
        }
        final TournamentCallback other = (TournamentCallback) obj;
        if (this.gameId == null)
        {
            if (other.gameId != null)
            {
                return false;
            }
        } else if (!this.gameId.equals(other.gameId))
        {
            return false;
        }
        if (this.gameMap == null)
        {
            if (other.gameMap != null)
            {
                return false;
            }
        } else if (!this.gameMap.equals(other.gameMap))
        {
            return false;
        }
        if (this.gameMode == null)
        {
            if (other.gameMode != null)
            {
                return false;
            }
        } else if (!this.gameMode.equals(other.gameMode))
        {
            return false;
        }
        if (this.gameName == null)
        {
            if (other.gameName != null)
            {
                return false;
            }
        } else if (!this.gameName.equals(other.gameName))
        {
            return false;
        }
        if (this.gameType == null)
        {
            if (other.gameType != null)
            {
                return false;
            }
        } else if (!this.gameType.equals(other.gameType))
        {
            return false;
        }
        if (this.losingTeam == null)
        {
            if (other.losingTeam != null)
            {
                return false;
            }
        } else if (!this.losingTeam.equals(other.losingTeam))
        {
            return false;
        }
        if (this.metaData == null)
        {
            if (other.metaData != null)
            {
                return false;
            }
        } else if (!this.metaData.equals(other.metaData))
        {
            return false;
        }
        if (this.region == null)
        {
            if (other.region != null)
            {
                return false;
            }
        } else if (!this.region.equals(other.region))
        {
            return false;
        }
        if (this.shortCode == null)
        {
            if (other.shortCode != null)
            {
                return false;
            }
        } else if (!this.shortCode.equals(other.shortCode))
        {
            return false;
        }
        if (this.startTime == null)
        {
            if (other.startTime != null)
            {
                return false;
            }
        } else if (!this.startTime.equals(other.startTime))
        {
            return false;
        }
        if (this.winningTeam == null)
        {
            if (other.winningTeam != null)
            {
                return false;
            }
        } else if (!this.winningTeam.equals(other.winningTeam))
        {
            return false;
        }
        return true;
    }

    public Long getGameId()
    {
        return this.gameId;
    }

    public Integer getGameMap()
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

    public Long getStartTime()
    {
        return this.startTime;
    }

    public List<TournamentCallbackSummoner> getWinningTeam()
    {
        return this.winningTeam;
    }

    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = (prime * result) + ((this.gameId == null) ? 0 : this.gameId.hashCode());
        result = (prime * result) + ((this.gameMap == null) ? 0 : this.gameMap.hashCode());
        result = (prime * result) + ((this.gameMode == null) ? 0 : this.gameMode.hashCode());
        result = (prime * result) + ((this.gameName == null) ? 0 : this.gameName.hashCode());
        result = (prime * result) + ((this.gameType == null) ? 0 : this.gameType.hashCode());
        result = (prime * result) + ((this.losingTeam == null) ? 0 : this.losingTeam.hashCode());
        result = (prime * result) + ((this.metaData == null) ? 0 : this.metaData.hashCode());
        result = (prime * result) + ((this.region == null) ? 0 : this.region.hashCode());
        result = (prime * result) + ((this.shortCode == null) ? 0 : this.shortCode.hashCode());
        result = (prime * result) + ((this.startTime == null) ? 0 : this.startTime.hashCode());
        result = (prime * result) + ((this.winningTeam == null) ? 0 : this.winningTeam.hashCode());
        return result;
    }

    @Override
    public String toString()
    {
        return "TournamentCallback [startTime=" + this.startTime + ", winningTeam=" + this.winningTeam + ", losingTeam=" + this.losingTeam + ", shortCode=" + this.shortCode + ", metaData=" + this.metaData + ", gameId=" + this.gameId + ", gameName=" + this.gameName + ", gameType=" + this.gameType + ", gameMap=" + this.gameMap + ", gameMode=" + this.gameMode + ", region=" + this.region + "]";
    }
}
