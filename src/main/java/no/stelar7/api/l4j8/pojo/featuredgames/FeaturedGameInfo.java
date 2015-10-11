package no.stelar7.api.l4j8.pojo.featuredgames;

import java.time.Instant;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.Collections;
import java.util.List;

import no.stelar7.api.l4j8.basic.APIObject;
import no.stelar7.api.l4j8.basic.Platform;
import no.stelar7.api.l4j8.basic.constants.GameMode;
import no.stelar7.api.l4j8.basic.constants.GameQueueType;
import no.stelar7.api.l4j8.basic.constants.GameType;
import no.stelar7.api.l4j8.basic.constants.Map;
import no.stelar7.api.l4j8.pojo.shared.BannedChampion;
import no.stelar7.api.l4j8.pojo.shared.Observer;

public class FeaturedGameInfo implements APIObject
{
    public static Class<?> createFromString(final String json) throws Exception
    {
        // TODO
        return APIObject.createFromString(json);
    }

    private List<BannedChampion>          bannedChampions;
    private Long                          gameId;
    private Long                          gameLength;
    private String                        gameMode;
    private Long                          gameQueueConfigId;
    private Long                          gameStartTime;
    private String                        gameType;
    private Long                          mapId;
    private Observer                      observers;
    private List<FeaturedGameParticipant> participants;

    private String platformId;

    /**
     * Banned champion information
     *
     * @return {@code List<BannedChampion>}
     */
    public List<BannedChampion> getBannedChampions()
    {
        return Collections.unmodifiableList(this.bannedChampions);
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
     * The game mode
     *
     * @return String
     */
    public String getGameMode()
    {
        return this.gameMode;
    }

    /**
     * a GameMode representing the gameMode.
     *
     * @return GameMode
     */
    public GameMode getGameModeAsGameMode()
    {
        return GameMode.getFromCode(this.gameMode);
    }

    /**
     * The queue type
     *
     * @return Long
     */
    public Long getGameQueueConfigId()
    {
        return this.gameQueueConfigId;
    }

    /**
     * a GameQueueType representing the GameQueueType.
     *
     * @return GameQueueType
     */
    public GameQueueType getGameQueueTypeAsGameQueueType()
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
     * The game type
     *
     * @return String
     */
    public String getGameType()
    {
        return this.gameType;
    }

    /**
     * a GameType representing the GameType.
     *
     * @return GameType
     */
    public GameType getGameTypeAsGameType()
    {
        return GameType.getFromCode(this.gameType);
    }

    /**
     * a Map representing the Map.
     *
     * @return Map
     */
    public Map getMapAsMap()
    {
        return Map.getFromCode(this.mapId);
    }

    /**
     * The ID of the map
     *
     * @return Long
     */
    public Long getMapId()
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
    public List<FeaturedGameParticipant> getParticipants()
    {
        return Collections.unmodifiableList(this.participants);
    }

    /**
     * the platformId represented as a Platform
     *
     * @return Platform
     */
    public Platform getPlatform()
    {
        return Platform.getFromCode(this.platformId);
    }

    /**
     * The ID of the platform on which the game is being played
     *
     * @return String
     */
    public String getPlatformId()
    {
        return this.platformId;
    }

    /**
     * A ZonedDateTime of {@code getGameStartTime()}
     *
     * * @return ZonedDateTime
     */
    public ZonedDateTime getRevisionDateAsDate()
    {
        return ZonedDateTime.ofInstant(Instant.ofEpochMilli(this.gameStartTime), ZoneOffset.UTC);
    }

    @Override
    public String toString()
    {
        return "FeaturedGameInfo [bannedChampions=" + this.bannedChampions + ", gameId=" + this.gameId + ", gameLength=" + this.gameLength + ", gameMode=" + this.gameMode + ", gameQueueConfigId=" + this.gameQueueConfigId + ", gameStartTime=" + this.gameStartTime + ", gameType=" + this.gameType + ", mapId=" + this.mapId + ", observers=" + this.observers + ", participants=" + this.participants + ", platformId=" + this.platformId + "]";
    }
}
