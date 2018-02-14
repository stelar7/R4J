package no.stelar7.api.l4j8.pojo.match;

import no.stelar7.api.l4j8.basic.constants.api.Platform;
import no.stelar7.api.l4j8.basic.constants.types.*;
import no.stelar7.api.l4j8.impl.builders.match.TimelineBuilder;

import java.io.Serializable;
import java.time.*;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.*;

public class Match implements Serializable
{
    private static final long serialVersionUID = -2199053222630714286L;
    
    private SeasonType                seasonId;
    private GameQueueType             queueId;
    private long                      gameId;
    private List<ParticipantIdentity> participantIdentities;
    private String                    gameVersion;
    private Platform                  platformId;
    private GameModeType              gameMode;
    private MapType                   mapId;
    private GameType                  gameType;
    private List<TeamStats>           teams;
    private List<Participant>         participants;
    private long                      gameCreation;
    private long                      gameDuration;
    
    
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
     * @return int
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
     * @return Duration
     */
    public Duration getMatchDuration()
    {
        return Duration.of(this.gameDuration, ChronoUnit.SECONDS);
    }
    
    /**
     * ID of the match
     *
     * @return long
     */
    public long getMatchId()
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
    
    public Participant getParticipantFromParticipantId(int participantId)
    {
        for (Participant participant : participants)
        {
            if (participant.getParticipantId() == participantId)
            {
                return participant;
            }
        }
        return null;
    }
    
    public List<Participant> getParticipants(TeamType team)
    {
        return getParticipants().stream().filter(p -> p.getTeam() == team).collect(Collectors.toList());
    }
    
    public ParticipantIdentity getParticipantIdentityFromParticipantId(int participantId)
    {
        for (ParticipantIdentity participant : participantIdentities)
        {
            if (participant.getParticipantId() == participantId)
            {
                return participant;
            }
        }
        return null;
    }
    
    /**
     * Returns the participantidentity that has this summonerid, or null if it cant be determined
     *
     * @param summonerId the id to check
     * @return ParticipantIdentity
     */
    public ParticipantIdentity getParticipantIdentityFromSummonerId(long summonerId)
    {
        for (ParticipantIdentity identity : participantIdentities)
        {
            if (identity.getPlayer().getSummonerId() == summonerId)
            {
                return identity;
            }
        }
        return null;
    }
    
    /**
     * Returns the participant that has this summonerid, or null if it cant be determined
     *
     * @param summonerId the id to check
     * @return Participant
     */
    public Participant getParticipantFromSummonerId(long summonerId)
    {
        ParticipantIdentity id = getParticipantIdentityFromSummonerId(summonerId);
        
        for (Participant participant : participants)
        {
            if (participant.getParticipantId() == id.getParticipantId())
            {
                return participant;
            }
        }
        
        return null;
    }
    
    public boolean didWin(Participant participant)
    {
        return getTeamStats(participant.getTeam()).get(0).isWinner();
    }
    
    /**
     * Returns your lane opponent based on role and lane, or null if it cant be determined
     *
     * @param self the participant to find the opponent of
     * @return ParticipantIdentity
     */
    public ParticipantIdentity getLaneOpponentIdentity(Participant self)
    {
        LaneType selfLane = self.getTimeline().getLane();
        RoleType selfRole = self.getTimeline().getRole();
        
        for (Participant participant : participants)
        {
            if (participant.getParticipantId() == self.getParticipantId())
            {
                continue;
            }
            
            LaneType otherLane = participant.getTimeline().getLane();
            RoleType otherRole = participant.getTimeline().getRole();
            
            if (selfLane == otherLane && selfRole == otherRole)
            {
                return getParticipantIdentityFromParticipantId(participant.getParticipantId());
            }
        }
        
        return null;
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
     * @param team the team to get info about
     * @return {@code TeamStats}
     */
    public List<TeamStats> getTeamStats(TeamType... team)
    {
        List<TeamType> types = Stream.of(team).collect(Collectors.toList());
        return this.teams.stream().filter(t -> types.contains(t.getTeamType())).collect(Collectors.toList());
    }
    
    /**
     * Only avaliable for one year after the game has been played
     *
     * @return MatchTimeline
     */
    public MatchTimeline getTimeline()
    {
        return new TimelineBuilder().withPlatform(platformId).withId(gameId).get();
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
        
        if (gameId != match.gameId)
        {
            return false;
        }
        if (gameCreation != match.gameCreation)
        {
            return false;
        }
        if (gameDuration != match.gameDuration)
        {
            return false;
        }
        if (seasonId != match.seasonId)
        {
            return false;
        }
        if (queueId != match.queueId)
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
        return (participants != null) ? participants.equals(match.participants) : (match.participants == null);
    }
    
    @Override
    public int hashCode()
    {
        int result = seasonId != null ? seasonId.hashCode() : 0;
        result = 31 * result + (queueId != null ? queueId.hashCode() : 0);
        result = 31 * result + (int) (gameId ^ (gameId >>> 32));
        result = 31 * result + (participantIdentities != null ? participantIdentities.hashCode() : 0);
        result = 31 * result + (gameVersion != null ? gameVersion.hashCode() : 0);
        result = 31 * result + (platformId != null ? platformId.hashCode() : 0);
        result = 31 * result + (gameMode != null ? gameMode.hashCode() : 0);
        result = 31 * result + (mapId != null ? mapId.hashCode() : 0);
        result = 31 * result + (gameType != null ? gameType.hashCode() : 0);
        result = 31 * result + (teams != null ? teams.hashCode() : 0);
        result = 31 * result + (participants != null ? participants.hashCode() : 0);
        result = 31 * result + (int) (gameCreation ^ (gameCreation >>> 32));
        result = 31 * result + (int) (gameDuration ^ (gameDuration >>> 32));
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
