package no.stelar7.api.l4j8.pojo.game;

import no.stelar7.api.l4j8.basic.constants.types.SummonerSpellType;
import no.stelar7.api.l4j8.basic.constants.types.*;

import java.time.*;
import java.util.List;

public class Game
{
    private ChampionType      championId;
    private Long              createDate;
    private List<Player>      fellowPlayers;
    private Long              gameId;
    private GameModeType      gameMode;
    private GameType          gameType;
    private Boolean           invalid;
    private Integer           ipEarned;
    private Integer           level;
    private MapType           mapId;
    private SummonerSpellType spell1;
    private SummonerSpellType spell2;
    private RawStats          stats;
    private GameSubType       subType;
    private TeamType          teamId;
    
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
        final Game other = (Game) obj;
        if (this.championId == null)
        {
            if (other.championId != null)
            {
                return false;
            }
        } else if (!this.championId.equals(other.championId))
        {
            return false;
        }
        if (this.createDate == null)
        {
            if (other.createDate != null)
            {
                return false;
            }
        } else if (!this.createDate.equals(other.createDate))
        {
            return false;
        }
        if (this.fellowPlayers == null)
        {
            if (other.fellowPlayers != null)
            {
                return false;
            }
        } else if (!this.fellowPlayers.equals(other.fellowPlayers))
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
        if (this.invalid == null)
        {
            if (other.invalid != null)
            {
                return false;
            }
        } else if (!this.invalid.equals(other.invalid))
        {
            return false;
        }
        if (this.ipEarned == null)
        {
            if (other.ipEarned != null)
            {
                return false;
            }
        } else if (!this.ipEarned.equals(other.ipEarned))
        {
            return false;
        }
        if (this.level == null)
        {
            if (other.level != null)
            {
                return false;
            }
        } else if (!this.level.equals(other.level))
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
        if (this.spell1 == null)
        {
            if (other.spell1 != null)
            {
                return false;
            }
        } else if (!this.spell1.equals(other.spell1))
        {
            return false;
        }
        if (this.spell2 == null)
        {
            if (other.spell2 != null)
            {
                return false;
            }
        } else if (!this.spell2.equals(other.spell2))
        {
            return false;
        }
        if (this.stats == null)
        {
            if (other.stats != null)
            {
                return false;
            }
        } else if (!this.stats.equals(other.stats))
        {
            return false;
        }
        if (this.subType == null)
        {
            if (other.subType != null)
            {
                return false;
            }
        } else if (!this.subType.equals(other.subType))
        {
            return false;
        }
        if (this.teamId == null)
        {
            if (other.teamId != null)
            {
                return false;
            }
        } else if (!this.teamId.equals(other.teamId))
        {
            return false;
        }
        return true;
    }
    
    
    /**
     * ChampionType ID associated with game.
     *
     * @return Integer
     */
    public ChampionType getChampion()
    {
        return this.championId;
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
     * <p>
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
    public GameModeType getGameMode()
    {
        return this.gameMode;
    }
    
    
    /**
     * Game type
     *
     * @return String
     */
    public GameType getGameType()
    {
        return this.gameType;
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
     * MapType ID.
     *
     * @return Integer.
     */
    public MapType getMap()
    {
        return this.mapId;
    }
    
    /**
     * ID of first summoner spell.
     *
     * @return Integer
     */
    public SummonerSpellType getSpell1()
    {
        return this.spell1;
    }
    
    /**
     * ID of second summoner spell.
     *
     * @return Integer
     */
    public SummonerSpellType getSpell2()
    {
        return this.spell2;
    }
    
    /**
     * Statistics associated with the game for this summoner.
     *
     * @return stats
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
    public GameSubType getSubType()
    {
        return this.subType;
    }
    
    /**
     * TeamType ID associated with game.
     *
     * @return Integer
     */
    public TeamType getTeam()
    {
        return this.teamId;
    }
    
    @Override
    public int hashCode()
    {
        final int prime  = 31;
        int       result = 1;
        result = (prime * result) + ((this.championId == null) ? 0 : this.championId.hashCode());
        result = (prime * result) + ((this.createDate == null) ? 0 : this.createDate.hashCode());
        result = (prime * result) + ((this.fellowPlayers == null) ? 0 : this.fellowPlayers.hashCode());
        result = (prime * result) + ((this.gameId == null) ? 0 : this.gameId.hashCode());
        result = (prime * result) + ((this.gameMode == null) ? 0 : this.gameMode.hashCode());
        result = (prime * result) + ((this.gameType == null) ? 0 : this.gameType.hashCode());
        result = (prime * result) + ((this.invalid == null) ? 0 : this.invalid.hashCode());
        result = (prime * result) + ((this.ipEarned == null) ? 0 : this.ipEarned.hashCode());
        result = (prime * result) + ((this.level == null) ? 0 : this.level.hashCode());
        result = (prime * result) + ((this.mapId == null) ? 0 : this.mapId.hashCode());
        result = (prime * result) + ((this.spell1 == null) ? 0 : this.spell1.hashCode());
        result = (prime * result) + ((this.spell2 == null) ? 0 : this.spell2.hashCode());
        result = (prime * result) + ((this.stats == null) ? 0 : this.stats.hashCode());
        result = (prime * result) + ((this.subType == null) ? 0 : this.subType.hashCode());
        result = (prime * result) + ((this.teamId == null) ? 0 : this.teamId.hashCode());
        return result;
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
    
    @Override
    public String toString()
    {
        return "Game{" +
               "championId=" + championId +
               ", createDate=" + createDate +
               ", fellowPlayers=" + fellowPlayers +
               ", gameId=" + gameId +
               ", gameMode='" + gameMode + '\'' +
               ", gameType='" + gameType + '\'' +
               ", invalid=" + invalid +
               ", ipEarned=" + ipEarned +
               ", level=" + level +
               ", mapId=" + mapId +
               ", spell1=" + spell1 +
               ", spell2=" + spell2 +
               ", stats=" + stats +
               ", subType='" + subType + '\'' +
               ", teamId=" + teamId +
               '}';
    }
}
