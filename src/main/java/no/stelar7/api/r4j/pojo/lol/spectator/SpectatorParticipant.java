package no.stelar7.api.r4j.pojo.lol.spectator;

import no.stelar7.api.r4j.basic.constants.types.lol.*;

import java.io.Serializable;
import java.util.*;

public class SpectatorParticipant implements Serializable
{
    private static final long serialVersionUID = -4280093223886492229L;
    
    private int                                    championId;
    private long              profileIconId;
    private SummonerSpellType spell1Id;
    private SummonerSpellType spell2Id;
    private String                                 summonerName;
    private String            summonerId;
    private TeamType          teamId;
    private boolean           bot;
    private SpectatorPerks                         perks;
    private List<SpectatorGameCustomizationObject> gameCustomizationObjects;
    
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
     * the participants summonerid
     *
     * @return the summoner id
     */
    public String getSummonerId()
    {
        return summonerId;
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
     * no clue...
     *
     * @return stuff?
     */
    public List<SpectatorGameCustomizationObject> getGameCustomizationObjects()
    {
        return gameCustomizationObjects;
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
               ", summonerId='" + summonerId + '\'' +
               ", teamId=" + teamId +
               ", bot=" + bot +
               ", perks=" + perks +
               ", gameCustomizationObjects=" + gameCustomizationObjects +
               '}';
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
               Objects.equals(summonerId, that.summonerId) &&
               teamId == that.teamId &&
               Objects.equals(perks, that.perks) &&
               Objects.equals(gameCustomizationObjects, that.gameCustomizationObjects);
    }
    
    @Override
    public int hashCode()
    {
        return Objects.hash(championId, profileIconId, spell1Id, spell2Id, summonerName, summonerId, teamId, bot, perks, gameCustomizationObjects);
    }
}
