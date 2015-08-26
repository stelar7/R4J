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

    public boolean isActive()
    {
        return active;
    }

    public boolean isBotEnabled()
    {
        return botEnabled;
    }

    public boolean isBotMmEnabled()
    {
        return botMmEnabled;
    }

    public boolean isFreeToPlay()
    {
        return freeToPlay;
    }

    public int getId()
    {
        return id;
    }

    public boolean isRankedPlayEnabled()
    {
        return rankedPlayEnabled;
    }

    @Override
    public String toString()
    {
        return "Champion [active=" + active + ", botEnabled=" + botEnabled + ", botMmEnabled=" + botMmEnabled + ", freeToPlay=" + freeToPlay + ", id=" + id + ", rankedPlayEnabled=" + rankedPlayEnabled + "]";
    }
}
