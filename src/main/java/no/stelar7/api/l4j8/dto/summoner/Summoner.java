package no.stelar7.api.l4j8.dto.summoner;

import java.time.Instant;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;

import no.stelar7.api.l4j8.basic.APIObject;

public class Summoner extends APIObject
{
    private long   id;
    private String name;
    private int    profileIconId;
    private Long   revisionDate;
    private int    summonerLevel;

    /**
     * The Summoners ID * @return Long
     */
    public Long getId()
    {
        return this.id;
    }

    /**
     * The Summoners name * @return String
     */
    public String getName()
    {
        return this.name;
    }

    /**
     * The Summoners profile icon ID * @return Integer
     */
    public Integer getProfileIconId()
    {
        return this.profileIconId;
    }

    /**
     * A Long representing the last time the summoner did one of the following: 1. logged in. 2. logged out. 3. changed profile picture. 4. started a match 5. propably more, but i cant remember them at this point
     *
     * * @return Long
     */
    public Long getRevisionDate()
    {
        return this.revisionDate;
    }

    /**
     * A ZonedDateTime representing the last time the summoner did one of the following: 1. logged in. 2. logged out. 3. changed profile picture. 4. started a match 5. propably more, but i cant remember them at this point
     *
     * * @return ZonedDateTime
     */
    public ZonedDateTime getRevisionDateAsDate()
    {
        return ZonedDateTime.ofInstant(Instant.ofEpochMilli(this.revisionDate), ZoneOffset.UTC);
    }

    /**
     * The Summoners level * @return Integer
     */
    public Integer getSummonerLevel()
    {
        return this.summonerLevel;
    }

    @Override
    public String toString()
    {
        return "Summoner [id=" + this.id + ", name=" + this.name + ", profileIconId=" + this.profileIconId + ", revisionDate=" + this.revisionDate + ", summonerLevel=" + this.summonerLevel + "]";
    }
}