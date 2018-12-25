package no.stelar7.api.l4j8.pojo.league;

import no.stelar7.api.l4j8.basic.constants.types.*;

import java.io.Serializable;
import java.util.List;

public class LeagueList implements Serializable
{
    private static final long serialVersionUID = -5998948570024210475L;
    
    private List<LeagueItem> entries;
    private String           name;
    private GameQueueType    queue;
    private TierType         tier;
    private String           leagueId;
    
    
    /**
     * Takes in a summonerId, and returns the TierDivisionType created from it.
     * Returns UNRANKED if the id is not found
     *
     * @param id the id to use for construction
     * @return TierDivisionType
     */
    public TierDivisionType getTierDivisionType(String id)
    {
        LeagueItem pos = getLeagueItem(id);
        
        return pos == null ? TierDivisionType.UNRANKED : pos.getTierDivisionType(tier);
    }
    
    /**
     * Takes in a summonerId, and returns the LeagueItem found in the list.
     * Returns null if not found
     *
     * @param id the Tier to use for construction
     * @return LeagueItem
     */
    public LeagueItem getLeagueItem(String id)
    {
        return entries.stream().filter(s -> s.getSummonerId().equals(id)).findFirst().orElse(null);
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
        
        LeagueList that = (LeagueList) o;
        
        if ((entries != null) ? !entries.equals(that.entries) : (that.entries != null))
        {
            return false;
        }
        if ((name != null) ? !name.equals(that.name) : (that.name != null))
        {
            return false;
        }
        if (queue != that.queue)
        {
            return false;
        }
        if (tier != that.tier)
        {
            return false;
        }
        return (leagueId != null) ? leagueId.equals(that.leagueId) : (that.leagueId == null);
    }
    
    @Override
    public int hashCode()
    {
        int result = entries != null ? entries.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (queue != null ? queue.hashCode() : 0);
        result = 31 * result + (tier != null ? tier.hashCode() : 0);
        result = 31 * result + (leagueId != null ? leagueId.hashCode() : 0);
        return result;
    }
    
    /**
     * The requested league entries.
     *
     * @return the entries
     */
    public List<LeagueItem> getEntries()
    {
        return this.entries;
    }
    
    /**
     * This name is an internal place-holder name only. Display and localization of names in the game client are handled client-side.
     *
     * @return the name
     */
    public String getLeagueName()
    {
        return this.name;
    }
    
    /**
     * ID of the league
     *
     * @return String
     */
    public String getLeagueId()
    {
        return leagueId;
    }
    
    
    /**
     * The league's queue type. (Legal values: RANKED_SOLO_5x5, RANKED_TEAM_3x3, RANKED_TEAM_5x5)
     *
     * @return the queue
     */
    public GameQueueType getQueueType()
    {
        return this.queue;
    }
    
    /**
     * The league's tier. (Legal values: CHALLENGER, MASTER, DIAMOND, PLATINUM, GOLD, SILVER, BRONZE)
     *
     * @return the tier
     */
    public TierType getTier()
    {
        return this.tier;
    }
    
    @Override
    public String toString()
    {
        return "LeagueList{" +
               "entries=" + entries +
               ", name='" + name + '\'' +
               ", queue='" + queue + '\'' +
               ", tier='" + tier + '\'' +
               '}';
    }
}
