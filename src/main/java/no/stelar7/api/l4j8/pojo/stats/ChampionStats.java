package no.stelar7.api.l4j8.pojo.stats;

import no.stelar7.api.l4j8.basic.constants.Champion;

public class ChampionStats
{
    private Long            id;
    private AggregatedStats stats;

    /**
     * Champion ID. Note that champion ID 0 represents the combined stats for all champions. For static information correlating to champion IDs, please refer to the LoL Static Data API.
     *
     * @return the id
     */
    public Long getId()
    {
        return id;
    }

    /**
     * Returns the Champion representing this id
     * 
     * @return the champion
     */
    public Champion getChampion()
    {
        return Champion.getFromId(id);
    }

    /**
     * Aggregated stats associated with the champion.
     *
     * @return the stats
     */
    public AggregatedStats getStats()
    {
        return stats;
    }

}
