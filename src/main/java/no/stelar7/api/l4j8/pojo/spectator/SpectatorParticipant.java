package no.stelar7.api.l4j8.pojo.spectator;

import no.stelar7.api.l4j8.basic.constants.types.*;

import java.util.List;

public class SpectatorParticipant
{
    private ChampionType           championId;
    private Long                   profileIconId;
    private SummonerSpellType      spell1Id;
    private SummonerSpellType      spell2Id;
    private String                 summonerName;
    private TeamType               teamId;
    private Boolean                bot;
    private List<SpectatorMastery> masteries;
    private List<SpectatorRune>    runes;
    private Long                   summonerId;
    
    /**
     * Gets champion
     *
     * @return the champion
     */
    public ChampionType getChampion()
    {
        return championId;
    }
    
    /**
     * Gets profile icon id.
     *
     * @return the profile icon id
     */
    public Long getProfileIconId()
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
    public Boolean isBot()
    {
        return bot;
    }
    
    /**
     * Gets masterie (Not preset if gotten from the FeaturedGames method)
     *
     * @return the masteries
     */
    public List<SpectatorMastery> getMasteries()
    {
        return masteries;
    }
    
    /**
     * Gets runes (Not preset if gotten from the FeaturedGames method)
     *
     * @return the runes
     */
    public List<SpectatorRune> getRunes()
    {
        return runes;
    }
    
    /**
     * Gets summoner id (Not preset if gotten from the FeaturedGames method)
     *
     * @return the summoner id
     */
    public Long getSummonerId()
    {
        return summonerId;
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
        if ((profileIconId != null) ? !profileIconId.equals(that.profileIconId) : (that.profileIconId != null))
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
        if ((summonerName != null) ? !summonerName.equals(that.summonerName) : (that.summonerName != null))
        {
            return false;
        }
        if (teamId != that.teamId)
        {
            return false;
        }
        if ((bot != null) ? !bot.equals(that.bot) : (that.bot != null))
        {
            return false;
        }
        if ((masteries != null) ? !masteries.equals(that.masteries) : (that.masteries != null))
        {
            return false;
        }
        if ((runes != null) ? !runes.equals(that.runes) : (that.runes != null))
        {
            return false;
        }
        return (summonerId != null) ? summonerId.equals(that.summonerId) : (that.summonerId == null);
    }
    
    @Override
    public int hashCode()
    {
        int result = championId != null ? championId.hashCode() : 0;
        result = 31 * result + (profileIconId != null ? profileIconId.hashCode() : 0);
        result = 31 * result + (spell1Id != null ? spell1Id.hashCode() : 0);
        result = 31 * result + (spell2Id != null ? spell2Id.hashCode() : 0);
        result = 31 * result + (summonerName != null ? summonerName.hashCode() : 0);
        result = 31 * result + (teamId != null ? teamId.hashCode() : 0);
        result = 31 * result + (bot != null ? bot.hashCode() : 0);
        result = 31 * result + (masteries != null ? masteries.hashCode() : 0);
        result = 31 * result + (runes != null ? runes.hashCode() : 0);
        result = 31 * result + (summonerId != null ? summonerId.hashCode() : 0);
        return result;
    }
}