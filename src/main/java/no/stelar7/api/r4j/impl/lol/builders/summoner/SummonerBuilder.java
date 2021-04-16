package no.stelar7.api.r4j.impl.lol.builders.summoner;

import no.stelar7.api.r4j.basic.calling.*;
import no.stelar7.api.r4j.basic.constants.api.*;
import no.stelar7.api.r4j.basic.constants.api.regions.LeagueShard;
import no.stelar7.api.r4j.basic.utils.*;
import no.stelar7.api.r4j.pojo.lol.summoner.Summoner;

import java.util.*;

public class SummonerBuilder
{
    
    private String   name;
    private String   sumId;
    private String   accId;
    private String      puuid;
    private LeagueShard platform;
    
    public SummonerBuilder()
    {
        this.name = "";
        this.sumId = "";
        this.accId = "";
        this.puuid = "";
        this.platform = LeagueShard.UNKNOWN;
    }
    
    private SummonerBuilder(String name, String sumId, String accId, String puuid, LeagueShard platform)
    {
        this.name = name;
        this.sumId = sumId;
        this.accId = accId;
        this.puuid = puuid;
        this.platform = platform;
    }
    
    public SummonerBuilder withSummonerId(String id)
    {
        return new SummonerBuilder("", id, "", "", this.platform);
    }
    
    public SummonerBuilder withAccountId(String id)
    {
        return new SummonerBuilder("", "", id, "", this.platform);
    }
    
    public SummonerBuilder withName(String name)
    {
        return new SummonerBuilder(name, "", "", "", this.platform);
    }
    
    public SummonerBuilder withPUUID(String puuid)
    {
        return new SummonerBuilder(name, "", "", puuid, this.platform);
    }
    
    public SummonerBuilder withPlatform(LeagueShard platform)
    {
        return new SummonerBuilder(this.name, this.sumId, this.accId, this.puuid, platform);
    }
    
    public SummonerBuilder setSummonerId(String id)
    {
        this.sumId = id;
        return this;
    }
    
    public SummonerBuilder setAccountId(String id)
    {
        this.accId = id;
        return this;
    }
    
    public SummonerBuilder setName(String name)
    {
        this.name = name;
        return this;
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
        
        
        Map<String, Object> data = new TreeMap<>();
        data.put("platform", platform);
        
        if (accId.length() > 0)
        {
            builder.withURLParameter(Constants.ACCOUNT_ID_PLACEHOLDER, this.accId);
            endpoint = URLEndpoint.V4_SUMMONER_BY_ACCOUNT;
            data.put("accountid", this.accId);
        }
        
        if (puuid.length() > 0)
        {
            builder.withURLParameter(Constants.PUUID_ID_PLACEHOLDER, this.puuid);
            endpoint = URLEndpoint.V4_SUMMONER_BY_PUUID;
            data.put("puuid", this.puuid);
        }
        
        if (sumId.length() > 0)
        {
            builder.withURLParameter(Constants.SUMMONER_ID_PLACEHOLDER, this.sumId);
            endpoint = URLEndpoint.V4_SUMMONER_BY_ID;
            data.put("id", this.sumId);
        }
        
        if (name.length() > 0)
        {
            builder.withURLParameter(Constants.SUMMONER_NAME_PLACEHOLDER, Utils.normalizeString(this.name));
            endpoint = URLEndpoint.V4_SUMMONER_BY_NAME;
            data.put("name", this.name);
        }
        
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
