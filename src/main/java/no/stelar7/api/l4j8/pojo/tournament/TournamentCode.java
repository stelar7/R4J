package no.stelar7.api.l4j8.pojo.tournament;

import no.stelar7.api.l4j8.basic.constants.api.Platform;
import no.stelar7.api.l4j8.basic.constants.types.*;

import java.io.Serializable;
import java.util.Set;

public class TournamentCode implements Serializable
{
    private static final long serialVersionUID = 5463341008783194897L;
    
    private String                  code;
    private long                    id;
    private String                  lobbyName;
    private String                  map;
    private String                  metaData;
    private Set<Long>               participants;
    private String                  password;
    private TournamentPickType      pickType;
    private int                     providerId;
    private Platform                region;
    private TournamentSpectatorType spectators;
    private int                     teamSize;
    private int                     tournamentId;
    
    
    /**
     * The tournament code.
     *
     * @return string
     */
    public String getCode()
    {
        return this.code;
    }
    
    /**
     * The tournament code's ID.
     *
     * @return long
     */
    public long getId()
    {
        return this.id;
    }
    
    /**
     * The lobby name for the tournament code game.
     *
     * @return string
     */
    public String getLobbyName()
    {
        return this.lobbyName;
    }
    
    /**
     * The game map for the tournament code game
     *
     * @return map?
     */
    public String getMap()
    {
        return this.map;
    }
    
    /**
     * The metadata for tournament code.
     *
     * @return string
     */
    public String getMetaData()
    {
        return this.metaData;
    }
    
    /**
     * The participants for tournament code.
     *
     * @return set
     */
    public Set<Long> getParticipants()
    {
        return this.participants;
    }
    
    /**
     * The password for the tournament code game.
     *
     * @return string
     */
    public String getPassword()
    {
        return this.password;
    }
    
    /**
     * The pick mode for tournament code game.
     *
     * @return TournamentPickType
     */
    public TournamentPickType getPickType()
    {
        return this.pickType;
    }
    
    /**
     * The provider's ID.
     *
     * @return int
     */
    public int getProviderId()
    {
        return this.providerId;
    }
    
    /**
     * The tournament code's region.
     *
     * @return platform
     */
    public Platform getRegion()
    {
        return this.region;
    }
    
    /**
     * The spectator mode for the tournament code game.
     *
     * @return TournamentSpectatorType
     */
    public TournamentSpectatorType getSpectators()
    {
        return this.spectators;
    }
    
    /**
     * The team size for the tournament code game.
     *
     * @return int
     */
    public int getTeamSize()
    {
        return this.teamSize;
    }
    
    /**
     * The tournament's ID.
     *
     * @return int
     */
    public int getTournamentId()
    {
        return this.tournamentId;
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
        
        TournamentCode that = (TournamentCode) o;
        
        if (id != that.id)
        {
            return false;
        }
        if (providerId != that.providerId)
        {
            return false;
        }
        if (teamSize != that.teamSize)
        {
            return false;
        }
        if (tournamentId != that.tournamentId)
        {
            return false;
        }
        if ((code != null) ? !code.equals(that.code) : (that.code != null))
        {
            return false;
        }
        if ((lobbyName != null) ? !lobbyName.equals(that.lobbyName) : (that.lobbyName != null))
        {
            return false;
        }
        if ((map != null) ? !map.equals(that.map) : (that.map != null))
        {
            return false;
        }
        if ((metaData != null) ? !metaData.equals(that.metaData) : (that.metaData != null))
        {
            return false;
        }
        if ((participants != null) ? !participants.equals(that.participants) : (that.participants != null))
        {
            return false;
        }
        if ((password != null) ? !password.equals(that.password) : (that.password != null))
        {
            return false;
        }
        if (pickType != that.pickType)
        {
            return false;
        }
        if (region != that.region)
        {
            return false;
        }
        return spectators == that.spectators;
    }
    
    @Override
    public int hashCode()
    {
        int result = code != null ? code.hashCode() : 0;
        result = 31 * result + (int) (id ^ (id >>> 32));
        result = 31 * result + (lobbyName != null ? lobbyName.hashCode() : 0);
        result = 31 * result + (map != null ? map.hashCode() : 0);
        result = 31 * result + (metaData != null ? metaData.hashCode() : 0);
        result = 31 * result + (participants != null ? participants.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (pickType != null ? pickType.hashCode() : 0);
        result = 31 * result + providerId;
        result = 31 * result + (region != null ? region.hashCode() : 0);
        result = 31 * result + (spectators != null ? spectators.hashCode() : 0);
        result = 31 * result + teamSize;
        result = 31 * result + tournamentId;
        return result;
    }
    
    @Override
    public String toString()
    {
        return "TournamentCode{" +
               "code='" + code + '\'' +
               ", id=" + id +
               ", lobbyName='" + lobbyName + '\'' +
               ", map='" + map + '\'' +
               ", metaData='" + metaData + '\'' +
               ", participants=" + participants +
               ", password='" + password + '\'' +
               ", pickType='" + pickType + '\'' +
               ", providerId=" + providerId +
               ", region='" + region + '\'' +
               ", spectators='" + spectators + '\'' +
               ", teamSize=" + teamSize +
               ", tournamentId=" + tournamentId +
               '}';
    }
}
