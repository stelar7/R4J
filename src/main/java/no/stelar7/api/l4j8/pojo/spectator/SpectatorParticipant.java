package no.stelar7.api.l4j8.pojo.spectator;

import no.stelar7.api.l4j8.basic.constants.types.*;

import java.io.Serializable;
import java.util.List;

public class SpectatorParticipant implements Serializable
{
    private static final long serialVersionUID = -4280093223886492229L;
    
    private int                    championId;
    private long                   profileIconId;
    private SummonerSpellType      spell1Id;
    private SummonerSpellType      spell2Id;
    private String                 summonerName;
    private TeamType               teamId;
    private boolean                bot;
    private SpectatorPerks         perks;
    private List<SpectatorMastery> masteries;
    private List<SpectatorRune>    runes;
    private String                 summonerId;
    
    /**
     * The id of the champion
     *
     * @return int
     */
    public int getChampionId()
    {
        return championId;
    }
    
    /**
     * Gets profile icon id.
     *
     * @return the profile icon id
     */
    public long getProfileIconId()
    {
        return profileIconId;
    }
    
    /**
     * Gets the first summonerspell
     *
     * @return the spell 1
     */
    public SummonerSpellType getSpell1()
    {
        return spell1Id;
    }
    
    /**
     * Gets the second summonerspell
     *
     * @return the spell 2
     */
    public SummonerSpellType getSpell2()
    {
        return spell2Id;
    }
    
    /**
     * Gets summoner name
     *
     * @return the summoner name
     */
    public String getSummonerName()
    {
        return summonerName;
    }
    
    /**
     * Gets team id
     *
     * @return the team id
     */
    public TeamType getTeam()
    {
        return teamId;
    }
    
    /**
     * true if user is a bot
     *
     * @return the bot
     */
    public boolean isBot()
    {
        return bot;
    }
    
    
    /**
     * Gets perks, not present on older games
     *
     * @return the perks
     */
    public SpectatorPerks getPerks()
    {
        return perks;
    }
    
    /**
     * Gets summoner id (Not preset if gotten from the FeaturedGames method)
     *
     * @return the summoner id
     */
    public String getSummonerId()
    {
        return summonerId;
    }
    
    /**
     * Gets masteries, not present on newer games
     *
     * @return the masteries
     */
    public List<SpectatorMastery> getMasteries()
    {
        return masteries;
    }
    
    /**
     * Gets runes, not present on newer games
     *
     * @return the runes
     */
    public List<SpectatorRune> getRunes()
    {
        return runes;
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
        
        SpectatorParticipant that = (SpectatorParticipant) o;
        
        if (championId != that.championId)
        {
            return false;
        }
        if (profileIconId != that.profileIconId)
        {
            return false;
        }
        if (bot != that.bot)
        {
            return false;
        }
        if ((summonerId != null) ? !summonerId.equals(that.summonerId) : (that.summonerId != null))
        {
            return false;
        }
        if (spell1Id != that.spell1Id)
        {
            return false;
        }
        if (spell2Id != that.spell2Id)
        {
            return false;
        }
        if (teamId != that.teamId)
        {
            return false;
        }
        if ((summonerName != null) ? !summonerName.equals(that.summonerName) : (that.summonerName != null))
        {
            return false;
        }
        if ((perks != null) ? !perks.equals(that.perks) : (that.perks != null))
        {
            return false;
        }
        if ((masteries != null) ? !masteries.equals(that.masteries) : (that.masteries != null))
        {
            return false;
        }
        return (runes != null) ? runes.equals(that.runes) : (that.runes == null);
    }
    
    @Override
    public int hashCode()
    {
        int result = championId;
        result = 31 * result + (int) (profileIconId ^ (profileIconId >>> 32));
        result = 31 * result + (spell1Id != null ? spell1Id.hashCode() : 0);
        result = 31 * result + (spell2Id != null ? spell2Id.hashCode() : 0);
        result = 31 * result + (summonerName != null ? summonerName.hashCode() : 0);
        result = 31 * result + (teamId != null ? teamId.hashCode() : 0);
        result = 31 * result + (bot ? 1 : 0);
        result = 31 * result + (perks != null ? perks.hashCode() : 0);
        result = 31 * result + (masteries != null ? masteries.hashCode() : 0);
        result = 31 * result + (runes != null ? runes.hashCode() : 0);
        result = 31 * result + (summonerId != null ? summonerId.hashCode() : 0);
        return result;
    }
    
    @Override
    public String toString()
    {
        return "SpectatorParticipant{" +
               "championId=" + championId +
               ", profileIconId=" + profileIconId +
               ", spell1Id=" + spell1Id +
               ", spell2Id=" + spell2Id +
               ", summonerName='" + summonerName + '\'' +
               ", teamId=" + teamId +
               ", bot=" + bot +
               ", perks=" + perks +
               ", masteries=" + masteries +
               ", runes=" + runes +
               ", summonerId=" + summonerId +
               '}';
    }
}
