package no.stelar7.api.l4j8.pojo.match;

import java.util.List;

import no.stelar7.api.l4j8.basic.constants.SummonerSpell;
import no.stelar7.api.l4j8.basic.constants.Tier;

public class Participant
{
    private Integer             championId;
    private String              highestAchievedSeasonTier;
    private List<Mastery>       masteries;
    private Integer             participantId;
    private List<Rune>          runes;
    private Long                spell1Id;
    private Long                spell2Id;
    private ParticipantStats    stats;
    private Integer             teamId;
    private ParticipantTimeline timeline;

    /**
     * Champion ID
     *
     * @return Integer
     */
    public Integer getChampionId()
    {
        return championId;
    }

    /**
     * Highest ranked tier achieved for the previous season, if any, otherwise null. Used to display border in game loading screen. (Legal values: CHALLENGER, MASTER, DIAMOND, PLATINUM, GOLD, SILVER, BRONZE, UNRANKED)
     *
     * @return String
     */
    public String getHighestAchievedSeasonTierId()
    {
        return highestAchievedSeasonTier;
    }

    /**
     * Highest ranked tier achieved for the previous season (inferred from the id)
     * 
     * @return Tier
     */
    public Tier getHighestAchivedSeasonTier()
    {
        return Tier.getFromCode(highestAchievedSeasonTier);
    }

    /**
     * List of mastery information
     *
     * @return {@code List<Mastery>}
     */
    public List<Mastery> getMasteries()
    {
        return masteries;
    }

    /**
     * Participant ID
     *
     * @return Integer
     */
    public Integer getParticipantId()
    {
        return participantId;
    }

    /**
     * List of rune information
     *
     * @return {@code List<Rune>}
     */
    public List<Rune> getRunes()
    {
        return runes;
    }

    /**
     * First summoner spell ID
     *
     * @return Long
     */
    public Long getSpell1Id()
    {
        return spell1Id;
    }

    /**
     * First summoner spell
     * 
     * @return SummonerSpell
     */
    public SummonerSpell getSpell1()
    {
        return SummonerSpell.getFromCode(spell1Id);
    }

    /**
     * Second summoner spell ID
     *
     * @return Long
     */
    public Long getSpell2Id()
    {
        return spell2Id;
    }

    /**
     * Second summoner spell
     * 
     * @return SummonerSpell
     */
    public SummonerSpell getSpell2()
    {
        return SummonerSpell.getFromCode(spell2Id);
    }

    /**
     * Participant statistics
     *
     * @return ParticipantStats
     */
    public ParticipantStats getStats()
    {
        return stats;
    }

    /**
     * Team ID
     *
     * @return Integer
     */
    public Integer getTeamId()
    {
        return teamId;
    }

    /**
     * Team inferred from the team id
     * 
     * @return Team
     */
    public no.stelar7.api.l4j8.basic.constants.Team getTeam()
    {
        return no.stelar7.api.l4j8.basic.constants.Team.getFromCode(teamId);
    }

    /**
     * Timeline data. Delta fields refer to values for the specified period (e.g., the gold per minute over the first 10 minutes of the game versus the second 20 minutes of the game. Diffs fields refer to the deltas versus the calculated lane opponent(s).
     *
     * @return ParticipantTimeline
     */
    public ParticipantTimeline getTimeline()
    {
        return timeline;
    }

}
