package no.stelar7.api.r4j.pojo.lol.tournament;

import no.stelar7.api.r4j.basic.constants.api.regions.LeagueShard;
import no.stelar7.api.r4j.basic.constants.types.lol.*;

import java.io.Serializable;
import java.util.*;

public class TournamentCode implements Serializable
{
    private static final long serialVersionUID = 6929086319264520944L;
    
    
    private String                  code;
    private long                    id;
    private String                  lobbyName;
    private String                  map;
    private String                  metaData;
    private Set<String>             participants;
    private String                  password;
    private TournamentPickType      pickType;
    private int                     providerId;
    private LeagueShard             region;
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
    public Set<String> getParticipants()
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
    public LeagueShard getRegion()
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
        return id == that.id && providerId == that.providerId && teamSize == that.teamSize && tournamentId == that.tournamentId && Objects.equals(code, that.code) && Objects.equals(lobbyName, that.lobbyName) && Objects.equals(map, that.map) && Objects.equals(metaData, that.metaData) && Objects.equals(participants, that.participants) && Objects.equals(password, that.password) && pickType == that.pickType && region == that.region && spectators == that.spectators;
    }
    
    @Override
    public int hashCode()
    {
        return Objects.hash(code, id, lobbyName, map, metaData, participants, password, pickType, providerId, region, spectators, teamSize, tournamentId);
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
               ", pickType=" + pickType +
               ", providerId=" + providerId +
               ", region=" + region +
               ", spectators=" + spectators +
               ", teamSize=" + teamSize +
               ", tournamentId=" + tournamentId +
               '}';
    }
}
