package no.stelar7.api.l4j8.pojo.summoner;

import java.time.Instant;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;

import no.stelar7.api.l4j8.basic.APIObject;

public class Summoner extends APIObject
{
    private Long    id;
    private String  name;
    private Integer profileIconId;
    private Long    revisionDate;
    private Integer summonerLevel;

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
     * ID of the summoner icon associated with the summoner * @return Integer
     */
    public Integer getProfileIconId()
    {
        return this.profileIconId;
    }

    /**
     * Date summoner was last modified specified as epoch milliseconds. The following events will update this timestamp: profile icon change, playing the tutorial or advanced tutorial, finishing a game, summoner name change
     *
     * * @return Long
     */
    public Long getRevisionDate()
    {
        return this.revisionDate;
    }

    /**
     * A ZonedDateTime of {@code getRevisionDate()}
     *
     * * @return ZonedDateTime
     */
    public ZonedDateTime getRevisionDateAsDate()
    {
        return ZonedDateTime.ofInstant(Instant.ofEpochMilli(this.revisionDate), ZoneOffset.UTC);
    }

    /**
     * Summoner level associated with the summoner * @return Integer
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