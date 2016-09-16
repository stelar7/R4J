package no.stelar7.api.l4j8.pojo.shared;

import no.stelar7.api.l4j8.basic.constants.*;
import no.stelar7.api.l4j8.basic.constants.Map;
import no.stelar7.api.l4j8.basic.constants.api.Platform;
import no.stelar7.api.l4j8.pojo.currentgame.CurrentGameParticipant;

import java.time.*;
import java.util.*;

@SuppressWarnings("MismatchedQueryAndUpdateOfCollection")
public class BaseGameInfo
{
    private List<BannedChampion>         bannedChampions;
    private Long                         gameId;
    private Long                         gameLength;
    private String                       gameMode;
    private Integer                      gameQueueConfigId;
    private Long                         gameStartTime;
    private String                       gameType;
    private Integer                      mapId;
    private Observer                     observers;
    private List<CurrentGameParticipant> participants;

    private String platformId;

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
        final BaseGameInfo other = (BaseGameInfo) obj;
        if (this.bannedChampions == null)
        {
            if (other.bannedChampions != null)
            {
                return false;
            }
        } else if (!this.bannedChampions.equals(other.bannedChampions))
        {
            return false;
        }
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
        if (this.gameLength == null)
        {
            if (other.gameLength != null)
            {
                return false;
            }
        } else if (!this.gameLength.equals(other.gameLength))
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
        if (this.gameQueueConfigId == null)
        {
            if (other.gameQueueConfigId != null)
            {
                return false;
            }
        } else if (!this.gameQueueConfigId.equals(other.gameQueueConfigId))
        {
            return false;
        }
        if (this.gameStartTime == null)
        {
            if (other.gameStartTime != null)
            {
                return false;
            }
        } else if (!this.gameStartTime.equals(other.gameStartTime))
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
        if (this.mapId == null)
        {
            if (other.mapId != null)
            {
                return false;
            }
        } else if (!this.mapId.equals(other.mapId))
        {
            return false;
        }
        if (this.observers == null)
        {
            if (other.observers != null)
            {
                return false;
            }
        } else if (!this.observers.equals(other.observers))
        {
            return false;
        }
        if (this.participants == null)
        {
            if (other.participants != null)
            {
                return false;
            }
        } else if (!this.participants.equals(other.participants))
        {
            return false;
        }
        if (this.platformId == null)
        {
            if (other.platformId != null)
            {
                return false;
            }
        } else if (!this.platformId.equals(other.platformId))
        {
            return false;
        }
        return true;
    }

    /**
     * Banned champion information
     *
     * @return {@code List<BannedChampion>}
     */
    public List<BannedChampion> getBannedChampions()
    {
        return this.bannedChampions == null ? Collections.emptyList() : Collections.unmodifiableList(this.bannedChampions);
    }

    /**
     * The ID of the game
     *
     * @return Long
     */
    public Long getGameId()
    {
        return this.gameId;
    }

    /**
     * The amount of time in seconds that has passed since the game started
     *
     * @return Long
     */
    public Long getGameLength()
    {
        return this.gameLength;
    }

    /**
     * a GameMode representing the gameMode.
     *
     * @return GameMode
     */
    public Optional<GameMode> getGameMode()
    {
        return GameMode.getFromCode(this.gameMode);
    }

    /**
     * The game mode
     *
     * @return String
     */
    public String getGameModeId()
    {
        return this.gameMode;
    }

    /**
     * The queue type
     *
     * @return Long
     */
    public Integer getGameQueueConfigId()
    {
        return this.gameQueueConfigId;
    }

    /**
     * a GameQueueType representing the GameQueueType.
     *
     * @return GameQueueType
     */
    public Optional<GameQueueType> getGameQueueType()
    {
        return GameQueueType.getFromCode(this.gameQueueConfigId);
    }

    /**
     * The game start time represented in epoch milliseconds
     *
     * @return Long
     */
    public Long getGameStartTime()
    {
        return this.gameStartTime;
    }

    /**
     * A ZonedDateTime of {@code getGameStartTime()}
     * <p>
     * * @return ZonedDateTime
     */
    public ZonedDateTime getGameStartTimeAsDate()
    {
        return ZonedDateTime.ofInstant(Instant.ofEpochMilli(this.gameStartTime), ZoneOffset.UTC);
    }

    /**
     * a GameType representing the GameType.
     *
     * @return GameType
     */
    public Optional<GameType> getGameType()
    {
        return GameType.getFromCode(this.gameType);
    }

    /**
     * The game type
     *
     * @return String
     */
    public String getGameTypeId()
    {
        return this.gameType;
    }

    /**
     * a Map representing the Map.
     *
     * @return Map
     */
    public Optional<Map> getMap()
    {
        return Map.getFromCode(this.mapId);
    }

    /**
     * The ID of the map
     *
     * @return Long
     */
    public Integer getMapId()
    {
        return this.mapId;
    }

    /**
     * The observer information
     *
     * @return Observer
     */
    public Observer getObservers()
    {
        return this.observers;
    }

    /**
     * The participant information
     *
     * @return {@code List<CurrentGameParticipant>}
     */
    public List<CurrentGameParticipant> getParticipants()
    {
        return this.participants == null ? Collections.emptyList() : Collections.unmodifiableList(this.participants);
    }

    /**
     * the platformId represented as a Platform
     *
     * @return Platform
     */
    public Optional<Platform> getPlatform()
    {
        return Platform.getFromCode(this.platformId);
    }

    /**
     * The ID of the platform on which the game belongs
     *
     * @return String
     */
    public String getPlatformId()
    {
        return this.platformId;
    }

    @Override
    public int hashCode()
    {
        final int prime  = 31;
        int       result = 1;
        result = (prime * result) + ((this.bannedChampions == null) ? 0 : this.bannedChampions.hashCode());
        result = (prime * result) + ((this.gameId == null) ? 0 : this.gameId.hashCode());
        result = (prime * result) + ((this.gameLength == null) ? 0 : this.gameLength.hashCode());
        result = (prime * result) + ((this.gameMode == null) ? 0 : this.gameMode.hashCode());
        result = (prime * result) + ((this.gameQueueConfigId == null) ? 0 : this.gameQueueConfigId.hashCode());
        result = (prime * result) + ((this.gameStartTime == null) ? 0 : this.gameStartTime.hashCode());
        result = (prime * result) + ((this.gameType == null) ? 0 : this.gameType.hashCode());
        result = (prime * result) + ((this.mapId == null) ? 0 : this.mapId.hashCode());
        result = (prime * result) + ((this.observers == null) ? 0 : this.observers.hashCode());
        result = (prime * result) + ((this.participants == null) ? 0 : this.participants.hashCode());
        result = (prime * result) + ((this.platformId == null) ? 0 : this.platformId.hashCode());
        return result;
    }

    @Override
    public String toString()
    {
        return "FeaturedGameInfo [bannedChampions=" + this.bannedChampions + ", gameId=" + this.gameId + ", gameLength=" + this.gameLength + ", gameMode=" + this.gameMode + ", gameQueueConfigId=" + this.gameQueueConfigId + ", gameStartTime=" + this.gameStartTime + ", gameType=" + this.gameType + ", mapId=" + this.mapId + ", observers=" + this.observers + ", participants=" + this.participants + ", platformId=" + this.platformId + "]";
    }
}
