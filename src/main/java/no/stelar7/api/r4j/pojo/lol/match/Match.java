package no.stelar7.api.r4j.pojo.lol.match;

import no.stelar7.api.r4j.basic.constants.api.regions.LeagueShard;
import no.stelar7.api.r4j.basic.constants.types.lol.*;
import no.stelar7.api.r4j.impl.lol.builders.match.TimelineBuilder;
import no.stelar7.api.r4j.impl.lol.raw.MatchAPI;
import no.stelar7.api.r4j.pojo.lol.summoner.Summoner;

import java.io.Serializable;
import java.time.*;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;

public class Match implements Serializable
{
    private static final long serialVersionUID = -2199053222630714286L;
    
    private SeasonType                seasonId;
    private GameQueueType             queueId;
    private long                      gameId;
    private List<ParticipantIdentity> participantIdentities;
    private String                    gameVersion;
    private LeagueShard               platformId;
    private GameModeType              gameMode;
    private MapType                   mapId;
    private GameType                  gameType;
    private List<TeamStats>           teams;
    private List<Participant>         participants;
    private long                      gameCreation;
    private long                      gameDuration;
    
    public static Match byId(LeagueShard platform, Long id)
    {
        return MatchAPI.getInstance().getMatch(platform, id);
    }
    
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
     * Time the game started
     *
     * @return long
     */
    public long getMatchCreation()
    {
        return this.gameCreation;
    }
    
    /**
     * A ZonedDateTime of {@code getMatchCreation()}
     *
     * @return ZonedDateTime
     */
    public ZonedDateTime getMatchCreationAsDate()
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
    
    public List<Participant> getParticipants(TeamType team)
    {
        return getParticipants().stream().filter(p -> p.getTeam() == team).collect(Collectors.toList());
    }
    
    public Optional<Participant> getParticipant(int participantId)
    {
        return participants.stream().filter(p -> p.getParticipantId() == participantId).findFirst();
    }
    
    public Optional<ParticipantIdentity> getParticipantIdentity(int participantId)
    {
        return participantIdentities.stream().filter(p -> p.getParticipantId() == participantId).findFirst();
    }
    
    /**
     * Returns the participantidentity that has this summonerid, or null if it cant be determined
     *
     * @param summonerId the id to check
     * @return ParticipantIdentity
     */
    public Optional<ParticipantIdentity> getParticipantIdentity(String summonerId)
    {
        return participantIdentities.stream()
                                    .filter(p -> p.getPlayer().getSummonerId().equals(summonerId))
                                    .findFirst();
    }
    
    /**
     * Returns the participantidentity that has this summonerid, or null if it cant be determined
     *
     * @param summoner the id to check
     * @return ParticipantIdentity
     */
    public Optional<ParticipantIdentity> getParticipantIdentity(Summoner summoner)
    {
        return getParticipantIdentity(summoner.getSummonerId());
    }
    
    
    /**
     * Returns the participant that has this summonerid, or null if it cant be determined
     *
     * @param summonerId the id to check
     * @return Participant
     */
    public Optional<Participant> getParticipant(String summonerId)
    {
        return getParticipantIdentity(summonerId)
                .flatMap(pid -> participants.stream()
                                            .filter(par -> pid.getParticipantId() == par.getParticipantId())
                                            .findFirst());
    }
    
    /**
     * Returns the participant that has this summonerid, or null if it cant be determined
     *
     * @param summoner the summoner to check
     * @return Participant
     */
    public Optional<Participant> getParticipant(Summoner summoner)
    {
        return getParticipant(summoner.getSummonerId());
    }
    
    
    public boolean didWin(Participant participant)
    {
        return participant.getStats().isWinner();
    }
    
    public boolean didWin(TeamType team)
    {
        return getTeamStats(team).get(0).isWinner();
    }
    
    /**
     * Returns your lane opponent based on role and lane, or null if it cant be determined
     *
     * @param self the participant to find the opponent of
     * @return ParticipantIdentity
     */
    public Optional<Participant> getLaneOpponent(Participant self)
    {
        return participants.stream()
                           .filter(p -> p.getParticipantId() != self.getParticipantId())
                           .filter(p -> p.getTeam() != self.getTeam())
                           .filter(p -> p.getTimeline().getLane() == self.getTimeline().getLane())
                           .filter(p -> p.getTimeline().getRole() == self.getTimeline().getRole())
                           .findFirst();
    }
    
    
    /**
     * Platform ID of the match
     *
     * @return String
     */
    public LeagueShard getPlatform()
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
        List<TeamType> types = Arrays.asList(team);
        
        return this.teams.stream()
                         .filter(t -> types.contains(t.getTeamType()))
                         .collect(Collectors.toList());
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
        if (!Objects.equals(participantIdentities, match.participantIdentities))
        {
            return false;
        }
        if (!Objects.equals(gameVersion, match.gameVersion))
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
        if (!Objects.equals(teams, match.teams))
        {
            return false;
        }
        return Objects.equals(participants, match.participants);
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
