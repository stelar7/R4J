package no.stelar7.api.l4j8.pojo.currentgame;

import java.time.Instant;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.Collections;
import java.util.List;

import no.stelar7.api.l4j8.basic.APIObject;

public class CurrentGameInfo extends APIObject
{
    private List<BannedChampion>         bannedChampions;
    private Long                         gameId;
    private Long                         gameLength;
    private String                       gameMode;
    private Long                         gameQueueConfigId;
    private Long                         gameStartTime;
    private String                       gameType;
    private Long                         mapId;
    private Observer                     observers;
    private List<CurrentGameParticipant> participants;
    private String                       platformId;

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
     * The queue type
     *
     * @return Long
     */
    public Long getGameQueueConfigId()
    {
        return this.gameQueueConfigId;
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
    public List<CurrentGameParticipant> getParticipants()
    {
        return Collections.unmodifiableList(this.participants);
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
        return "CurrentGameInfo [\n\tbannedChampions=" + this.bannedChampions + ", \n\tgameId=" + this.gameId + ",\n\tgameLength=" + this.gameLength + ", \n\tgameMode=" + this.gameMode + ", \n\tgameQueueConfigId=" + this.gameQueueConfigId + ",\n\tgameStartTime=" + this.gameStartTime + ", \n\tgameType=" + this.gameType + ", \n\tmapId=" + this.mapId + ", \n\tobservers=" + this.observers + ", \n\tparticipants=" + this.participants + ", \n\tplatformId=" + this.platformId + "]";
    }
}
