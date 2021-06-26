package no.stelar7.api.r4j.pojo.lol.match.v5;

import no.stelar7.api.r4j.basic.constants.api.regions.LeagueShard;
import no.stelar7.api.r4j.basic.constants.types.lol.*;

import java.io.Serializable;
import java.time.*;
import java.time.temporal.ChronoUnit;
import java.util.*;

public class LOLMatch implements Serializable
{
    private static final long serialVersionUID = -2786000037824449302L;
    
    private long                   gameCreation;
    private int                    gameDuration;
    private long                   gameId;
    private GameModeType           gameMode;
    private String                 gameName;
    private long                   gameStartTimestamp;
    private GameType               gameType;
    private String                 gameVersion;
    private MapType                mapId;
    private List<MatchParticipant> participants;
    private LeagueShard            platformId;
    private GameQueueType          queueId;
    private List<MatchTeam>        teams;
    private String                 tournamentCode;
    
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
        return Duration.of(this.gameDuration, ChronoUnit.SECONDS);
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
    
    public GameType getGameType()
    {
        return gameType;
    }
    
    public String getGameVersion()
    {
        return gameVersion;
    }
    
    public MapType getMapId()
    {
        return mapId;
    }
    
    public List<MatchParticipant> getParticipants()
    {
        return participants;
    }
    
    public LeagueShard getPlatformId()
    {
        return platformId;
    }
    
    public GameQueueType getQueueId()
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
        return gameCreation == lolMatch.gameCreation && gameDuration == lolMatch.gameDuration && gameId == lolMatch.gameId && gameStartTimestamp == lolMatch.gameStartTimestamp && gameMode == lolMatch.gameMode && Objects.equals(gameName, lolMatch.gameName) && gameType == lolMatch.gameType && Objects.equals(gameVersion, lolMatch.gameVersion) && mapId == lolMatch.mapId && Objects.equals(participants, lolMatch.participants) && platformId == lolMatch.platformId && queueId == lolMatch.queueId && Objects.equals(teams, lolMatch.teams) && Objects.equals(tournamentCode, lolMatch.tournamentCode);
    }
    
    @Override
    public int hashCode()
    {
        return Objects.hash(gameCreation, gameDuration, gameId, gameMode, gameName, gameStartTimestamp, gameType, gameVersion, mapId, participants, platformId, queueId, teams, tournamentCode);
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
