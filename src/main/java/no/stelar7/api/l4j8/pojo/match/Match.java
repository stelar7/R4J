package no.stelar7.api.l4j8.pojo.match;

import no.stelar7.api.l4j8.basic.constants.api.Platform;
import no.stelar7.api.l4j8.basic.constants.types.*;
import no.stelar7.api.l4j8.impl.MatchAPI;

import java.time.*;
import java.util.*;

public class Match
{
    private SeasonType                seasonId;
    private GameQueueType             queueId;
    private Long                      gameId;
    private List<ParticipantIdentity> participantIdentities;
    private String                    gameVersion;
    private Platform                  platformId;
    private GameModeType              gameMode;
    private MapType                   mapId;
    private GameType                  gameType;
    private List<TeamStats>           teams;
    private List<Participant>         participants;
    private Long                      gameCreation;
    private Long                      gameDuration;
    
    
    /**
     * a GameQueueType representing the GameQueueType.
     *
     * @return GameQueueType
     */
    public GameQueueType getGameQueueType()
    {
        return this.queueId;
    }
    
    /**
     * Match map ID
     *
     * @return Integer
     */
    public MapType getMap()
    {
        return this.mapId;
    }
    
    /**
     * A ZonedDateTime of {@code getMatchCreation()}
     *
     * @return ZonedDateTime
     */
    public ZonedDateTime getMatchCreationDate()
    {
        return ZonedDateTime.ofInstant(Instant.ofEpochMilli(this.gameCreation), ZoneOffset.UTC);
    }
    
    /**
     * Match duration
     *
     * @return Long
     */
    public Long getMatchDuration()
    {
        return this.gameDuration;
    }
    
    /**
     * ID of the match
     *
     * @return Long
     */
    public Long getMatchId()
    {
        return this.gameId;
    }
    
    /**
     * Match mode (Legal values: CLASSIC, ODIN, ARAM, TUTORIAL, ONEFORALL, ASCENSION, FIRSTBLOOD, KINGPORO)
     *
     * @return String
     */
    public GameModeType getMatchMode()
    {
        return this.gameMode;
    }
    
    /**
     * Match type (Legal values: CUSTOM_GAME, MATCHED_GAME, TUTORIAL_GAME)
     *
     * @return the match type
     */
    public GameType getMatchType()
    {
        return this.gameType;
    }
    
    /**
     * Match version
     *
     * @return String
     */
    public String getMatchVersion()
    {
        return this.gameVersion;
    }
    
    /**
     * Participant identity information
     *
     * @return {@code List<ParticipantIdentity>}
     */
    public List<ParticipantIdentity> getParticipantIdentities()
    {
        return this.participantIdentities;
    }
    
    /**
     * Participant information
     *
     * @return {@code List<Participant>}
     */
    public List<Participant> getParticipants()
    {
        return this.participants;
    }
    
    /**
     * Platform ID of the match
     *
     * @return String
     */
    public Platform getPlatform()
    {
        return this.platformId;
    }
    
    
    /**
     * SeasonType match was played (Legal values: PRESEASON3, SEASON3, PRESEASON2014, SEASON2014, PRESEASON2015, SEASON2015)
     *
     * @return String
     */
    public SeasonType getSeason()
    {
        return this.seasonId;
    }
    
    /**
     * TeamType information
     *
     * @return {@code List<TeamType>}
     */
    public List<TeamStats> getTeamStats()
    {
        return this.teams;
    }
    
    /**
     * Only avaliable for one year after the game has been played
     *
     * @return MatchTimeline
     */
    public Optional<MatchTimeline> getTimeline()
    {
        return Optional.ofNullable(MatchAPI.getInstance().getTimeline(platformId, gameId));
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
        
        Match match = (Match) o;
        
        if (seasonId != match.seasonId)
        {
            return false;
        }
        if (queueId != match.queueId)
        {
            return false;
        }
        if ((gameId != null) ? !gameId.equals(match.gameId) : (match.gameId != null))
        {
            return false;
        }
        if ((participantIdentities != null) ? !participantIdentities.equals(match.participantIdentities) : (match.participantIdentities != null))
        {
            return false;
        }
        if ((gameVersion != null) ? !gameVersion.equals(match.gameVersion) : (match.gameVersion != null))
        {
            return false;
        }
        if (platformId != match.platformId)
        {
            return false;
        }
        if (gameMode != match.gameMode)
        {
            return false;
        }
        if (mapId != match.mapId)
        {
            return false;
        }
        if (gameType != match.gameType)
        {
            return false;
        }
        if ((teams != null) ? !teams.equals(match.teams) : (match.teams != null))
        {
            return false;
        }
        if ((participants != null) ? !participants.equals(match.participants) : (match.participants != null))
        {
            return false;
        }
        if ((gameCreation != null) ? !gameCreation.equals(match.gameCreation) : (match.gameCreation != null))
        {
            return false;
        }
        return (gameDuration != null) ? gameDuration.equals(match.gameDuration) : (match.gameDuration == null);
    }
    
    @Override
    public int hashCode()
    {
        int result = seasonId != null ? seasonId.hashCode() : 0;
        result = 31 * result + (queueId != null ? queueId.hashCode() : 0);
        result = 31 * result + (gameId != null ? gameId.hashCode() : 0);
        result = 31 * result + (participantIdentities != null ? participantIdentities.hashCode() : 0);
        result = 31 * result + (gameVersion != null ? gameVersion.hashCode() : 0);
        result = 31 * result + (platformId != null ? platformId.hashCode() : 0);
        result = 31 * result + (gameMode != null ? gameMode.hashCode() : 0);
        result = 31 * result + (mapId != null ? mapId.hashCode() : 0);
        result = 31 * result + (gameType != null ? gameType.hashCode() : 0);
        result = 31 * result + (teams != null ? teams.hashCode() : 0);
        result = 31 * result + (participants != null ? participants.hashCode() : 0);
        result = 31 * result + (gameCreation != null ? gameCreation.hashCode() : 0);
        result = 31 * result + (gameDuration != null ? gameDuration.hashCode() : 0);
        return result;
    }
    
    @Override
    public String toString()
    {
        return "Match{" +
               "mapId=" + mapId +
               ", gameCreation=" + gameCreation +
               ", gameDuration=" + gameDuration +
               ", gameId=" + gameId +
               ", gameMode='" + gameMode + '\'' +
               ", gameType='" + gameType + '\'' +
               ", gameVersion='" + gameVersion + '\'' +
               ", participantIdentities=" + participantIdentities +
               ", participants=" + participants +
               ", platformId='" + platformId + '\'' +
               ", queueType='" + queueId + '\'' +
               ", season='" + seasonId + '\'' +
               ", teams=" + teams +
               '}';
    }
}
