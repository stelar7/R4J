package no.stelar7.api.l4j8.pojo.match;

import no.stelar7.api.l4j8.basic.constants.types.*;
import no.stelar7.api.l4j8.impl.StaticAPI;
import no.stelar7.api.l4j8.pojo.staticdata.champion.StaticChampion;

import java.util.*;

public class Participant
{
    private int                 championId;
    private TierType            highestAchievedSeasonTier;
    private List<MatchMastery>  masteries;
    private int                 participantId;
    private List<MatchRune>     runes;
    private SummonerSpellType   spell1Id;
    private SummonerSpellType   spell2Id;
    private ParticipantStats    stats;
    private TeamType            teamId;
    private ParticipantTimeline timeline;
    
    
    /**
     * StaticChampion
     *
     * @return StaticChampion
     */
    public StaticChampion getChampion()
    {
        return StaticAPI.getCache().getChampion(this.championId);
    }
    
    /**
     * Highest ranked tier achieved for the previous season, if any, otherwise null. Used to display border in game loading screen. (Legal values: CHALLENGER, MASTER, DIAMOND, PLATINUM, GOLD, SILVER, BRONZE, UNRANKED)
     *
     * @return String
     */
    public TierType getHighestAchievedSeasonTier()
    {
        return this.highestAchievedSeasonTier;
    }
    
    
    /**
     * List of mastery information
     *
     * @return {@code List<MatchMastery>}
     */
    public List<MatchMastery> getMasteries()
    {
        return this.masteries != null ? this.masteries : Collections.emptyList();
    }
    
    /**
     * Participant ID
     *
     * @return int
     */
    public int getParticipantId()
    {
        return this.participantId;
    }
    
    /**
     * List of rune information
     *
     * @return {@code List<MatchRune>}
     */
    public List<MatchRune> getRunes()
    {
        return this.runes != null ? this.runes : Collections.emptyList();
    }
    
    
    /**
     * First summoner spell ID
     *
     * @return long
     */
    public SummonerSpellType getSpell1()
    {
        return this.spell1Id;
    }
    
    
    /**
     * Second summoner spell ID
     *
     * @return long
     */
    public SummonerSpellType getSpell2()
    {
        return this.spell2Id;
    }
    
    /**
     * Participant statistics
     *
     * @return ParticipantStats
     */
    public ParticipantStats getStats()
    {
        return this.stats;
    }
    
    /**
     * TeamType ID
     *
     * @return int
     */
    public TeamType getTeam()
    {
        return this.teamId;
    }
    
    /**
     * MatchTimeline data.
     * Delta fields refer to values for the specified period (e.g., the gold per minute over the first 10 minutes of the game versus the second 20 minutes of the game. Diffs fields refer to the deltas versus the calculated lane opponent(s).
     *
     * @return ParticipantTimeline
     */
    public ParticipantTimeline getTimeline()
    {
        return this.timeline;
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
        
        Participant that = (Participant) o;
        
        if (participantId != that.participantId)
        {
            return false;
        }
        if (championId != that.championId)
        {
            return false;
        }
        if (highestAchievedSeasonTier != that.highestAchievedSeasonTier)
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
        if (spell1Id != that.spell1Id)
        {
            return false;
        }
        if (spell2Id != that.spell2Id)
        {
            return false;
        }
        if ((stats != null) ? !stats.equals(that.stats) : (that.stats != null))
        {
            return false;
        }
        if (teamId != that.teamId)
        {
            return false;
        }
        return (timeline != null) ? timeline.equals(that.timeline) : (that.timeline == null);
    }
    
    @Override
    public int hashCode()
    {
        int result = championId;
        result = 31 * result + (highestAchievedSeasonTier != null ? highestAchievedSeasonTier.hashCode() : 0);
        result = 31 * result + (masteries != null ? masteries.hashCode() : 0);
        result = 31 * result + participantId;
        result = 31 * result + (runes != null ? runes.hashCode() : 0);
        result = 31 * result + (spell1Id != null ? spell1Id.hashCode() : 0);
        result = 31 * result + (spell2Id != null ? spell2Id.hashCode() : 0);
        result = 31 * result + (stats != null ? stats.hashCode() : 0);
        result = 31 * result + (teamId != null ? teamId.hashCode() : 0);
        result = 31 * result + (timeline != null ? timeline.hashCode() : 0);
        return result;
    }
    
    @Override
    public String toString()
    {
        return "Participant{" +
               "championId=" + championId +
               ", highestAchievedSeasonTier='" + highestAchievedSeasonTier + '\'' +
               ", masteries=" + masteries +
               ", participantId=" + participantId +
               ", runes=" + runes +
               ", spell1Id=" + spell1Id +
               ", spell2Id=" + spell2Id +
               ", stats=" + stats +
               ", teamId=" + teamId +
               ", timeline=" + timeline +
               '}';
    }
}
