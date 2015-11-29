package no.stelar7.api.l4j8.pojo.game;

import java.time.Instant;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.List;

import no.stelar7.api.l4j8.basic.APIObject;
import no.stelar7.api.l4j8.basic.constants.Champion;
import no.stelar7.api.l4j8.basic.constants.GameMode;
import no.stelar7.api.l4j8.basic.constants.GameSubType;
import no.stelar7.api.l4j8.basic.constants.GameType;
import no.stelar7.api.l4j8.basic.constants.Map;
import no.stelar7.api.l4j8.basic.constants.Team;

public class Game implements APIObject
{
    private Long         championId;
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

    private Integer teamId;

    /**
     * Champion ID associated with game.
     *
     * @return Integer
     */
    public Long getChampionId()
    {
        return this.championId;
    }

    /**
     * The champion as a Champion
     * 
     * @return Champion
     */
    public Champion getChampion()
    {
        return Champion.getFromId(championId);
    }

    /**
     * Date that end game data was recorded, specified as epoch milliseconds.
     *
     * @return Long
     */
    public Long getCreateDate()
    {
        return this.createDate;
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
     * Other players associated with the game.
     *
     * @return {@code List<Player>}
     */
    public List<Player> getFellowPlayers()
    {
        return this.fellowPlayers;
    }

    /**
     * Game ID.
     *
     * @return Long
     */
    public Long getGameId()
    {
        return this.gameId;
    }

    /**
     * Game mode.
     *
     * @return String
     */
    public String getGameModeId()
    {
        return this.gameMode;
    }

    /**
     * a GameMode representing the gameMode.
     *
     * @return GameMode
     */
    public GameMode getGameMode()
    {
        return GameMode.getFromCode(this.gameMode);
    }

    /**
     * Game type
     *
     * @return String
     */
    public String getGameTypeId()
    {
        return this.gameType;
    }

    /**
     * a GameType representing the GameType.
     *
     * @return GameType
     */
    public GameType getGameType()
    {
        return GameType.getFromCode(this.gameType);
    }

    /**
     * Invalid flag
     *
     * @return Boolean
     */
    public Boolean isInvalid()
    {
        return this.invalid;
    }

    /**
     * IP Earned
     *
     * @return Integer
     */
    public Integer getIpEarned()
    {
        return this.ipEarned;
    }

    /**
     * Level.
     *
     * @return Integer
     */
    public Integer getLevel()
    {
        return this.level;
    }

    /**
     * a Map representing the Map.
     *
     * @return Map
     */
    public Map getMap()
    {
        return Map.getFromCode(this.mapId);
    }

    /**
     * Map ID.
     *
     * @return Integer.
     */
    public Integer getMapId()
    {
        return this.mapId;
    }

    /**
     * ID of first summoner spell.
     *
     * @return Integer
     */
    public Integer getSpell1()
    {
        return this.spell1;
    }

    /**
     * ID of second summoner spell.
     *
     * @return Integer
     */
    public Integer getSpell2()
    {
        return this.spell2;
    }

    /**
     * Statistics associated with the game for this summoner.
     *
     * @return
     */
    public RawStats getStats()
    {
        return this.stats;
    }

    /**
     * Game sub-type.
     *
     * @return String
     */
    public String getSubTypeId()
    {
        return this.subType;
    }

    /**
     * a GameSubType representing the GameSubType.
     *
     * @return GameSubType
     */
    public GameSubType getSubType()
    {
        return GameSubType.getFromCode(this.subType);
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

    /**
     * Team ID associated with game.
     *
     * @return Integer
     */
    public Integer getTeamId()
    {
        return this.teamId;
    }

    @Override
    public String toString()
    {
        return "Game [championId=" + this.championId + ", createDate=" + this.createDate + ", fellowPlayers=" + this.fellowPlayers + ", gameId=" + this.gameId + ", gameMode=" + this.gameMode + ", gameType=" + this.gameType + ", invalid=" + this.invalid + ", ipEarned=" + this.ipEarned + ", level=" + this.level + ", mapId=" + this.mapId + ", spell1=" + this.spell1 + ", spell2=" + this.spell2 + ", stats=" + this.stats + ", subType=" + this.subType + ", teamId=" + this.teamId + "]";
    }
}
