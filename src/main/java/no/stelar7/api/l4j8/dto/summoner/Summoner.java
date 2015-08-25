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
        return id;
    }

    public String getName()
    {
        return name;
    }

    public int getProfileIconId()
    {
        return profileIconId;
    }

    public Long getRevisionDate()
    {
        return revisionDate;
    }

    public int getSummonerLevel()
    {
        return summonerLevel;
    }
    
    @Override
    public String toString()
    {
        return "Summoner [id=" + id + ", name=" + name + ", profileIconId=" + profileIconId + ", revisionDate=" + revisionDate + ", summonerLevel=" + summonerLevel + "]";
    }
}