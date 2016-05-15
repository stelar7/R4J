package no.stelar7.api.l4j8.pojo.stats;

import java.time.*;
import java.util.*;

public class RankedStats
{
    private List<ChampionStats> champions;
    private Long                modifyDate;
    private Long                summonerId;

    @Override
    public boolean equals(final Object obj)
    {
        if (this == obj)
        {
            return true;
        }
        if (obj == null)
        {
            return false;
        }
        if (this.getClass() != obj.getClass())
        {
            return false;
        }
        final RankedStats other = (RankedStats) obj;
        if (this.champions == null)
        {
            if (other.champions != null)
            {
                return false;
            }
        } else if (!this.champions.equals(other.champions))
        {
            return false;
        }
        if (this.modifyDate == null)
        {
            if (other.modifyDate != null)
            {
                return false;
            }
        } else if (!this.modifyDate.equals(other.modifyDate))
        {
            return false;
        }
        if (this.summonerId == null)
        {
            if (other.summonerId != null)
            {
                return false;
            }
        } else if (!this.summonerId.equals(other.summonerId))
        {
            return false;
        }
        return true;
    }

    /**
     * Collection of aggregated stats summarized by champion.
     *
     * @return the champions
     */
    public List<ChampionStats> getChampions()
    {
        return this.champions;
    }

    /**
     * Date stats were last modified specified as epoch milliseconds.
     *
     * @return the modify date
     */
    public Long getModifyDate()
    {
        return this.modifyDate;
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
        return this.summonerId;
    }

    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = (prime * result) + ((this.champions == null) ? 0 : this.champions.hashCode());
        result = (prime * result) + ((this.modifyDate == null) ? 0 : this.modifyDate.hashCode());
        result = (prime * result) + ((this.summonerId == null) ? 0 : this.summonerId.hashCode());
        return result;
    }

}
