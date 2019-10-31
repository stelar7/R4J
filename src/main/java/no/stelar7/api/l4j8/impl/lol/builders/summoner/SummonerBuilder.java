package no.stelar7.api.l4j8.impl.lol.builders.summoner;

import no.stelar7.api.l4j8.basic.calling.*;
import no.stelar7.api.l4j8.basic.constants.api.*;
import no.stelar7.api.l4j8.basic.utils.*;
import no.stelar7.api.l4j8.pojo.summoner.Summoner;

import java.util.Optional;

public class SummonerBuilder
{
    
    private final String   name;
    private final String   sumId;
    private final String   accId;
    private final String   puuid;
    private final Platform platform;
    
    public SummonerBuilder()
    {
        this.name = "";
        this.sumId = "";
        this.accId = "";
        this.puuid = "";
        this.platform = Platform.UNKNOWN;
    }
    
    private SummonerBuilder(String name, String sumId, String accId, String puuid, Platform platform)
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
    
    public SummonerBuilder withPlatform(Platform platform)
    {
        return new SummonerBuilder(this.name, this.sumId, this.accId, this.puuid, platform);
    }
    
    /**
     * gets a summoner based on the parameters passed to the builder
     *
     * @return Summoner
     */
    public Summoner get()
    {
        DataCallBuilder builder  = new DataCallBuilder().withPlatform(this.platform);
        URLEndpoint     endpoint = null;
        Object          index    = "";
        
        
        if (accId.length() > 0)
        {
            builder.withURLParameter(Constants.ACCOUNT_ID_PLACEHOLDER, String.valueOf(this.accId));
            endpoint = URLEndpoint.V3_SUMMONER_BY_ACCOUNT;
            index = this.accId;
        }
        
        if (puuid.length() > 0)
        {
            builder.withURLParameter(Constants.PUUID_ID_PLACEHOLDER, String.valueOf(this.puuid));
            endpoint = URLEndpoint.V3_SUMMONER_BY_PUUID;
            index = this.puuid;
        }
        
        if (sumId.length() > 0)
        {
            builder.withURLParameter(Constants.SUMMONER_ID_PLACEHOLDER, String.valueOf(this.sumId));
            endpoint = URLEndpoint.V3_SUMMONER_BY_ID;
            index = this.sumId;
        }
        
        if (name.length() > 0)
        {
            builder.withURLParameter(Constants.SUMMONER_NAME_PLACEHOLDER, Utils.normalizeString(this.name));
            endpoint = URLEndpoint.V3_SUMMONER_BY_NAME;
            index = this.name;
        }
        
        builder.withEndpoint(endpoint);
        
        
        Optional chl = DataCall.getCacheProvider().get(endpoint, this.platform, index);
        if (chl.isPresent())
        {
            return (Summoner) chl.get();
        }
        
        
        Object sob = builder.build();
        
        if (sob instanceof Pair)
        {
            return null;
        }
        
        DataCall.getCacheProvider().store(endpoint, sob, this.platform, index);
        return (Summoner) sob;
    }
}
