package no.stelar7.api.l4j8.pojo.champion;

import no.stelar7.api.l4j8.basic.APIObject;

public class Champion extends APIObject
{
    Boolean active;
    Boolean botEnabled;
    Boolean botMmEnabled;
    Boolean freeToPlay;
    Integer id;
    Boolean rankedPlayEnabled;

    public Integer getId()
    {
        return this.id;
    }

    public Boolean isActive()
    {
        return this.active;
    }

    public Boolean isBotEnabled()
    {
        return this.botEnabled;
    }

    public Boolean isBotMmEnabled()
    {
        return this.botMmEnabled;
    }

    public Boolean isFreeToPlay()
    {
        return this.freeToPlay;
    }

    public Boolean isRankedPlayEnabled()
    {
        return this.rankedPlayEnabled;
    }

    @Override
    public String toString()
    {
        return "Champion [active=" + this.active + ", botEnabled=" + this.botEnabled + ", botMmEnabled=" + this.botMmEnabled + ", freeToPlay=" + this.freeToPlay + ", id=" + this.id + ", rankedPlayEnabled=" + this.rankedPlayEnabled + "]";
    }
}
