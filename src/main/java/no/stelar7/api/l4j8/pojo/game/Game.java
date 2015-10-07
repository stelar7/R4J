package no.stelar7.api.l4j8.pojo.game;

import java.time.Instant;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.List;

import no.stelar7.api.l4j8.basic.constants.GameMode;
import no.stelar7.api.l4j8.basic.constants.GameSubType;
import no.stelar7.api.l4j8.basic.constants.GameType;
import no.stelar7.api.l4j8.basic.constants.Map;
import no.stelar7.api.l4j8.basic.constants.Team;

public class Game
{
    private Integer      championId;
    private Long         createDate;
    private List<Player> fellowPlayers;
    private Long         gameId;
    private String       gameMode;
    private String       gameType;
    private Boolean      invalid;
    private Integer      ipEarned;
    private Integer      level;
    private Integer      mapId;
    private Integer      spell1;
    private Integer      spell2;
    private RawStats     stats;
    private String       subType;
    private Integer      teamId;

    /**
     * Champion ID associated with game.
     * 
     * @return Integer
     */
    public Integer getChampionId()
    {
        return championId;
    }

    /**
     * A ZonedDateTime of {@code getCreateDate()}
     *
     * * @return ZonedDateTime
     */
    public ZonedDateTime getCreateDateAsDate()
    {
        return ZonedDateTime.ofInstant(Instant.ofEpochMilli(this.createDate), ZoneOffset.UTC);
    }

    /**
     * Date that end game data was recorded, specified as epoch milliseconds.
     * 
     * @return Long
     */
    public Long getCreateDate()
    {
        return createDate;
    }

    /**
     * Other players associated with the game.
     * 
     * @return {@code List<Player>}
     */
    public List<Player> getFellowPlayers()
    {
        return fellowPlayers;
    }

    /**
     * Game ID.
     * 
     * @return Long
     */
    public Long getGameId()
    {
        return gameId;
    }

    /**
     * Game mode.
     * 
     * @return String
     */
    public String getGameMode()
    {
        return gameMode;
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
     * Game type
     * 
     * @return String
     */
    public String getGameType()
    {
        return gameType;
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
     * Invalid flag
     * 
     * @return Boolean
     */
    public Boolean getInvalid()
    {
        return invalid;
    }

    /**
     * IP Earned
     * 
     * @return Integer
     */
    public Integer getIpEarned()
    {
        return ipEarned;
    }

    /**
     * Level.
     * 
     * @return Integer
     */
    public Integer getLevel()
    {
        return level;
    }

    /**
     * Map ID.
     * 
     * @return Integer.
     */
    public Integer getMapId()
    {
        return mapId;
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
     * ID of first summoner spell.
     * 
     * @return Integer
     */
    public Integer getSpell1()
    {
        return spell1;
    }

    /**
     * ID of second summoner spell.
     * 
     * @return Integer
     */
    public Integer getSpell2()
    {
        return spell2;
    }

    /**
     * Statistics associated with the game for this summoner.
     * 
     * @return
     */
    public RawStats getStats()
    {
        return stats;
    }

    /**
     * Game sub-type.
     * 
     * @return String
     */
    public String getSubType()
    {
        return subType;
    }

    /**
     * a GameSubType representing the GameSubType.
     *
     * @return GameSubType
     */
    public GameSubType getSubTypeAsGameSubType()
    {
        return GameSubType.getFromCode(this.subType);
    }

    /**
     * Team ID associated with game. 
     * 
     * @return Integer
     */
    public Integer getTeamId()
    {
        return teamId;
    }
    
    /**
     * a Team representing the team associated with game.
     *
     * @return Team
     */
    public Team getTeam()
    {
        return Team.getFromCode(this.teamId);
    }


    @Override
    public String toString()
    {
        return "Game [championId=" + championId + ", createDate=" + createDate + ", fellowPlayers=" + fellowPlayers + ", gameId=" + gameId + ", gameMode=" + gameMode + ", gameType=" + gameType + ", invalid=" + invalid + ", ipEarned=" + ipEarned + ", level=" + level + ", mapId=" + mapId + ", spell1=" + spell1 + ", spell2=" + spell2 + ", stats=" + stats + ", subType=" + subType + ", teamId=" + teamId + "]";
    }
}
