package no.stelar7.api.r4j.pojo.lol.match.v5;

import no.stelar7.api.r4j.basic.constants.api.regions.*;
import no.stelar7.api.r4j.basic.constants.types.lol.*;
import no.stelar7.api.r4j.impl.lol.raw.MatchV5API;

import java.io.Serializable;
import java.time.*;
import java.time.temporal.ChronoUnit;
import java.util.*;

public class LOLMatch implements Serializable
{
    private static final long serialVersionUID = 6308730651274668047L;
    
    private long gameCreation;
    private int                    gameDuration;
    private long                   gameId;
    private GameModeType           gameMode;
    private String                 gameName;
    private Long                   gameStartTimestamp;
    private Long                   gameEndTimestamp;
    private GameType               gameType;
    private String                 gameVersion;
    private MapType                mapId;
    private List<MatchParticipant> participants;
    private LeagueShard            platformId;
    private GameQueueType          queueId;
    private List<MatchTeam>        teams;
    private String                 tournamentCode;
    
    public static LOLMatch get(LeagueShard shard, String id)
    {
        return MatchV5API.getInstance().getMatch(shard.toRegionShard(), id);
    }
    
    public static LOLMatch get(RegionShard shard, String id)
    {
        return MatchV5API.getInstance().getMatch(shard, id);
    }
    
    public LOLTimeline getTimeline()
    {
        return MatchV5API.getInstance().getTimeline(platformId.toRegionShard(), platformId + "_" + gameId);
    }
    
    public ZonedDateTime getMatchCreationAsDate()
    {
        return ZonedDateTime.ofInstant(Instant.ofEpochMilli(this.gameCreation), ZoneOffset.UTC);
    }
    
    public long getGameCreation()
    {
        return gameCreation;
    }
    
    public int getGameDuration()
    {
        return gameDuration;
    }
    
    public Duration getGameDurationAsDuration()
    {
        if (this.gameEndTimestamp != null)
        {
            return Duration.of(this.gameDuration, ChronoUnit.SECONDS);
        }
        
        return Duration.of(this.gameDuration, ChronoUnit.MILLIS);
    }
    
    public long getGameId()
    {
        return gameId;
    }
    
    public GameModeType getGameMode()
    {
        return gameMode;
    }
    
    public String getGameName()
    {
        return gameName;
    }
    
    public long getGameStartTimestamp()
    {
        return gameStartTimestamp;
    }
    
    public ZonedDateTime getGameStartAsDate()
    {
        return ZonedDateTime.ofInstant(Instant.ofEpochMilli(this.gameStartTimestamp), ZoneOffset.UTC);
    }
    
    public long getGameEndTimestamp()
    {
        return gameEndTimestamp;
    }
    
    public ZonedDateTime getGameEndAsDate()
    {
        return ZonedDateTime.ofInstant(Instant.ofEpochMilli(this.gameEndTimestamp), ZoneOffset.UTC);
    }
    
    public GameType getGameType()
    {
        return gameType;
    }
    
    public String getGameVersion()
    {
        return gameVersion;
    }
    
    public MapType getMap()
    {
        return mapId;
    }
    
    public List<MatchParticipant> getParticipants()
    {
        return participants;
    }
    
    public LeagueShard getPlatform()
    {
        return platformId;
    }
    
    public GameQueueType getQueue()
    {
        return queueId;
    }
    
    public List<MatchTeam> getTeams()
    {
        return teams;
    }
    
    public String getTournamentCode()
    {
        return tournamentCode;
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
        LOLMatch lolMatch = (LOLMatch) o;
        return gameCreation == lolMatch.gameCreation && gameDuration == lolMatch.gameDuration && gameId == lolMatch.gameId && gameMode == lolMatch.gameMode && Objects.equals(gameName, lolMatch.gameName) && Objects.equals(gameStartTimestamp, lolMatch.gameStartTimestamp) && Objects.equals(gameEndTimestamp, lolMatch.gameEndTimestamp) && gameType == lolMatch.gameType && Objects.equals(gameVersion, lolMatch.gameVersion) && mapId == lolMatch.mapId && Objects.equals(participants, lolMatch.participants) && platformId == lolMatch.platformId && queueId == lolMatch.queueId && Objects.equals(teams, lolMatch.teams) && Objects.equals(tournamentCode, lolMatch.tournamentCode);
    }
    
    @Override
    public int hashCode()
    {
        return Objects.hash(gameCreation, gameDuration, gameId, gameMode, gameName, gameStartTimestamp, gameEndTimestamp, gameType, gameVersion, mapId, participants, platformId, queueId, teams, tournamentCode);
    }
    
    @Override
    public String toString()
    {
        return "LOLMatch{" +
               "gameCreation=" + gameCreation +
               ", gameDuration=" + gameDuration +
               ", gameId=" + gameId +
               ", gameMode=" + gameMode +
               ", gameName='" + gameName + '\'' +
               ", gameStartTimestamp=" + gameStartTimestamp +
               ", gameEndTimestamp=" + gameEndTimestamp +
               ", gameType=" + gameType +
               ", gameVersion='" + gameVersion + '\'' +
               ", mapId=" + mapId +
               ", participants=" + participants +
               ", platformId=" + platformId +
               ", queueId=" + queueId +
               ", teams=" + teams +
               ", tournamentCode='" + tournamentCode + '\'' +
               '}';
    }
}
