package no.stelar7.api.r4j.pojo.lol.match.v4;

import no.stelar7.api.r4j.basic.constants.types.lol.*;
import no.stelar7.api.r4j.impl.lol.raw.DDragonAPI;
import no.stelar7.api.r4j.pojo.lol.staticdata.champion.StaticChampion;

import java.io.Serializable;
import java.util.*;

public class Participant implements Serializable
{
    
    private static final long serialVersionUID = -5435750033777444439L;
    
    private int                 participantId;
    private int                 championId;
    private List<MatchRune>     runes;
    private List<MatchMastery>  masteries;
    private MatchPerks          perks;
    private SummonerSpellType   spell1Id;
    private SummonerSpellType   spell2Id;
    private ParticipantStats    stats;
    private TeamType            teamId;
    private ParticipantTimeline timeline;
    
    
    /**
     * Gets the match perks, returns null on older games
     *
     * @return {@code List<MatchPerk>}
     */
    public MatchPerks getPerks()
    {
        return perks;
    }
    
    
    /**
     * returns the id of the champion played by this participant
     *
     * @return int
     */
    public int getChampionId()
    {
        return championId;
    }
    
    
    public StaticChampion getChampion()
    {
        return DDragonAPI.getInstance().getChampion(championId);
    }
    
    
    /**
     * List of mastery information, returns null on newer games
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
     * List of rune information, returns null on newer games
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
        return participantId == that.participantId &&
               championId == that.championId &&
               Objects.equals(runes, that.runes) &&
               Objects.equals(masteries, that.masteries) &&
               Objects.equals(perks, that.perks) &&
               spell1Id == that.spell1Id &&
               spell2Id == that.spell2Id &&
               Objects.equals(stats, that.stats) &&
               teamId == that.teamId &&
               Objects.equals(timeline, that.timeline);
    }
    
    @Override
    public int hashCode()
    {
        return Objects.hash(participantId, championId, runes, masteries, perks, spell1Id, spell2Id, stats, teamId, timeline);
    }
    
    @Override
    public String toString()
    {
        return "Participant{" +
               "participantId=" + participantId +
               ", championId=" + championId +
               ", runes=" + runes +
               ", masteries=" + masteries +
               ", perks=" + perks +
               ", spell1Id=" + spell1Id +
               ", spell2Id=" + spell2Id +
               ", stats=" + stats +
               ", teamId=" + teamId +
               ", timeline=" + timeline +
               '}';
    }
}
