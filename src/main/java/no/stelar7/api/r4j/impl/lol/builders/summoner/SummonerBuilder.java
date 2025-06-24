package no.stelar7.api.r4j.impl.lol.builders.summoner;

import no.stelar7.api.r4j.basic.calling.*;
import no.stelar7.api.r4j.basic.constants.api.*;
import no.stelar7.api.r4j.basic.constants.api.regions.LeagueShard;
import no.stelar7.api.r4j.basic.utils.Pair;
import no.stelar7.api.r4j.pojo.lol.summoner.Summoner;

import java.util.*;

public class SummonerBuilder
{

    private String      puuid;
    private LeagueShard platform;

    public SummonerBuilder()
    {
    }

    private SummonerBuilder(String puuid, LeagueShard platform)
    {
        this.puuid = puuid;
        this.platform = platform;
    }

    public SummonerBuilder withPUUID(String puuid)
    {
        return new SummonerBuilder(puuid, this.platform);
    }

    public SummonerBuilder withPlatform(LeagueShard platform)
    {
        return new SummonerBuilder(this.puuid, platform);
    }

    public SummonerBuilder setPUUID(String puuid)
    {
        this.puuid = puuid;
        return this;
    }

    public SummonerBuilder setPlatform(LeagueShard platform)
    {
        this.platform = platform;
        return this;
    }


    /**
     * gets a summoner based on the parameters passed to the builder
     * <p>
     * If variables are set using a setXXX method, they are parsed with name having the highest priority, and accountid the lowest. as shown in the "list below"
     * name > id > puuid > accountid
     *
     * @return Summoner
     */
    public Summoner get()
    {
        DataCallBuilder builder  = new DataCallBuilder().withPlatform(this.platform);
        URLEndpoint     endpoint = null;


        Map<String, Object> data = new LinkedHashMap<>();
        data.put("platform", platform);

        builder.withURLParameter(Constants.PUUID_ID_PLACEHOLDER, this.puuid);
        endpoint = URLEndpoint.V4_SUMMONER_BY_PUUID;
        data.put("puuid", this.puuid);

        builder.withEndpoint(endpoint);

        Optional<?> chl = DataCall.getCacheProvider().get(endpoint, data);
        if (chl.isPresent())
        {
            return (Summoner) chl.get();
        }


        Object sob = builder.build();

        if (sob instanceof Pair)
        {
            return null;
        }

        data.put("value", sob);
        DataCall.getCacheProvider().store(endpoint, data);

        return (Summoner) sob;
    }
}
