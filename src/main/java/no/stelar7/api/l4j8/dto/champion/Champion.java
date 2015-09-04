package no.stelar7.api.l4j8.dto.champion;

import no.stelar7.api.l4j8.basic.APIObject;

public class Champion extends APIObject
{
    boolean active;
    boolean botEnabled;
    boolean botMmEnabled;
    boolean freeToPlay;
    boolean rankedPlayEnabled;
    int     id;

    public int getId()
    {
        return this.id;
    }

    public boolean isActive()
    {
        return this.active;
    }

    public boolean isBotEnabled()
    {
        return this.botEnabled;
    }

    public boolean isBotMmEnabled()
    {
        return this.botMmEnabled;
    }

    public boolean isFreeToPlay()
    {
        return this.freeToPlay;
    }

    public boolean isRankedPlayEnabled()
    {
        return this.rankedPlayEnabled;
    }

    @Override
    public String toString()
    {
        return "Champion [active=" + this.active + ", botEnabled=" + this.botEnabled + ", botMmEnabled=" + this.botMmEnabled + ", freeToPlay=" + this.freeToPlay + ", id=" + this.id + ", rankedPlayEnabled=" + this.rankedPlayEnabled + "]";
    }
}
