package no.stelar7.api.r4j.pojo.lol.clash;

import java.io.Serializable;
import java.util.*;

public class ClashTeam implements Serializable
{
    private static final long serialVersionUID = -6888186255786520524L;
    private String id;
    private int               tournamentId;
    private String            name;
    private int               iconId;
    private int               tier;
    private String            captain;
    private String            abbreviation;
    private List<ClashPlayer> players;
    
    public String getId()
    {
        return id;
    }
    
    public int getTournamentId()
    {
        return tournamentId;
    }
    
    public String getName()
    {
        return name;
    }
    
    public int getIconId()
    {
        return iconId;
    }
    
    public int getTier()
    {
        return tier;
    }
    
    /**
     * @return The puuid of the captain of this team
     */
    public String getCaptain()
    {
        return captain;
    }
    
    public String getAbbreviation()
    {
        return abbreviation;
    }
    
    public List<ClashPlayer> getPlayers()
    {
        return players;
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
        ClashTeam clashTeam = (ClashTeam) o;
        return tournamentId == clashTeam.tournamentId &&
               iconId == clashTeam.iconId &&
               tier == clashTeam.tier &&
               Objects.equals(id, clashTeam.id) &&
               Objects.equals(name, clashTeam.name) &&
               Objects.equals(captain, clashTeam.captain) &&
               Objects.equals(abbreviation, clashTeam.abbreviation) &&
               Objects.equals(players, clashTeam.players);
    }
    
    @Override
    public int hashCode()
    {
        return Objects.hash(id, tournamentId, name, iconId, tier, captain, abbreviation, players);
    }
    
    @Override
    public String toString()
    {
        return "ClashTeam{" +
               "id='" + id + '\'' +
               ", tournamentId=" + tournamentId +
               ", name='" + name + '\'' +
               ", iconId=" + iconId +
               ", tier=" + tier +
               ", captain='" + captain + '\'' +
               ", abbreviation='" + abbreviation + '\'' +
               ", players=" + players +
               '}';
    }
}
