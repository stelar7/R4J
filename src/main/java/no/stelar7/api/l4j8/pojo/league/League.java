package no.stelar7.api.l4j8.pojo.league;

import java.util.List;

import no.stelar7.api.l4j8.basic.APIObject;
import no.stelar7.api.l4j8.basic.constants.RankedQueue;
import no.stelar7.api.l4j8.basic.constants.Tier;

public class League implements APIObject
{

    private List<LeagueEntry> entries;
    private String            name;
    private String            participantId;
    private String            queue;
    private String            tier;

    /**
     * The requested league entries.
     *
     * @return the entries
     */
    public List<LeagueEntry> getEntries()
    {
        return entries;
    }

    /**
     * This name is an internal place-holder name only. Display and localization of names in the game client are handled client-side.
     *
     * @return the name
     */
    public String getName()
    {
        return name;
    }

    /**
     * Specifies the relevant participant that is a member of this league (i.e., a requested summoner ID, a requested team ID, or the ID of a team to which one of the requested summoners belongs). Only present when full league is requested so that participant's entry can be identified. Not present when individual entry is requested.
     *
     * @return the participant id
     */
    public String getParticipantId()
    {
        return participantId;
    }

    /**
     * The league's queue type. (Legal values: RANKED_SOLO_5x5, RANKED_TEAM_3x3, RANKED_TEAM_5x5)
     *
     * @return the queue
     */
    public String getQueueString()
    {
        return queue;
    }

    /**
     * The league's queue type. (Legal values: RANKED_SOLO_5x5, RANKED_TEAM_3x3, RANKED_TEAM_5x5)
     *
     * @return the queue
     */
    public RankedQueue getQueue()
    {
        return RankedQueue.getFromCode(queue);
    }

    /**
     * The league's tier. (Legal values: CHALLENGER, MASTER, DIAMOND, PLATINUM, GOLD, SILVER, BRONZE)
     *
     * @return the tier
     */
    public String getTierString()
    {
        return tier;
    }

    /**
     * The league's tier. (Legal values: CHALLENGER, MASTER, DIAMOND, PLATINUM, GOLD, SILVER, BRONZE)
     *
     * @return the tier
     */
    public Tier getTier()
    {
        return Tier.getFromCode(tier);
    }
}
