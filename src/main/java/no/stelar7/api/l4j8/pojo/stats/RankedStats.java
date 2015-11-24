package no.stelar7.api.l4j8.pojo.stats;

import java.time.Instant;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.List;

import no.stelar7.api.l4j8.basic.APIObject;

public class RankedStats implements APIObject
{
    private List<ChampionStats> champions;
    private Long                modifyDate;
    private Long                summonerId;

    /**
     * Collection of aggregated stats summarized by champion.
     *
     * @return the champions
     */
    public List<ChampionStats> getChampions()
    {
        return champions;
    }

    /**
     * Date stats were last modified specified as epoch milliseconds.
     *
     * @return the modify date
     */
    public Long getModifyDate()
    {
        return modifyDate;
    }

    /**
     * A ZonedDateTime of {@code getModifyDate()}
     *
     * * @return ZonedDateTime
     */
    public ZonedDateTime getModifyDateAsDate()
    {
        return ZonedDateTime.ofInstant(Instant.ofEpochMilli(this.modifyDate), ZoneOffset.UTC);
    }

    /**
     * Gets the summoner id.
     *
     * @return the summoner id
     */
    public Long getSummonerId()
    {
        return summonerId;
    }

}
