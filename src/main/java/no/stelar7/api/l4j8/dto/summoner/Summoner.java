package no.stelar7.api.l4j8.dto.summoner;

import no.stelar7.api.l4j8.basic.APIObject;

public class Summoner extends APIObject
{
    private long   id;
    private String name;
    private int    profileIconId;
    private Long   revisionDate;
    private int    summonerLevel;

    public Long getId()
    {
        return this.id;
    }

    public String getName()
    {
        return this.name;
    }

    public int getProfileIconId()
    {
        return this.profileIconId;
    }

    public Long getRevisionDate()
    {
        return this.revisionDate;
    }

    public int getSummonerLevel()
    {
        return this.summonerLevel;
    }

    @Override
    public String toString()
    {
        return "Summoner [id=" + this.id + ", name=" + this.name + ", profileIconId=" + this.profileIconId + ", revisionDate=" + this.revisionDate + ", summonerLevel=" + this.summonerLevel + "]";
    }
}