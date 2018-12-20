package no.stelar7.api.l4j8.pojo.spectator;

import no.stelar7.api.l4j8.basic.constants.types.*;

import java.io.Serializable;
import java.util.*;

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
        return championId == that.championId &&
               profileIconId == that.profileIconId &&
               bot == that.bot &&
               spell1Id == that.spell1Id &&
               spell2Id == that.spell2Id &&
               Objects.equals(summonerName, that.summonerName) &&
               teamId == that.teamId &&
               Objects.equals(perks, that.perks);
    }
    
    @Override
    public int hashCode()
    {
        return Objects.hash(championId, profileIconId, spell1Id, spell2Id, summonerName, teamId, bot, perks);
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
               '}';
    }
}
