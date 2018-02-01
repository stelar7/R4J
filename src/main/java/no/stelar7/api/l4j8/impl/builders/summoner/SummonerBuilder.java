package no.stelar7.api.l4j8.impl.builders.summoner;

import no.stelar7.api.l4j8.basic.calling.*;
import no.stelar7.api.l4j8.basic.constants.api.*;
import no.stelar7.api.l4j8.basic.utils.*;
import no.stelar7.api.l4j8.pojo.summoner.Summoner;

import java.util.Optional;

public class SummonerBuilder
{
    
    private final String   name;
    private final long     sumId;
    private final long     accId;
    private final Platform platform;
    
    public SummonerBuilder()
    {
        this.name = "";
        this.sumId = -1;
        this.accId = -1;
        this.platform = Platform.UNKNOWN;
    }
    
    private SummonerBuilder(String name, long sumId, long accId, Platform platform)
    {
        this.name = name;
        this.sumId = sumId;
        this.accId = accId;
        this.platform = platform;
    }
    
    public SummonerBuilder withSummonerId(Long id)
    {
        return new SummonerBuilder("", id, -1, this.platform);
    }
    
    public SummonerBuilder withAccountId(Long id)
    {
        return new SummonerBuilder("", -1, id, this.platform);
    }
    
    public SummonerBuilder withName(String name)
    {
        return new SummonerBuilder(name, -1, -1, this.platform);
    }
    
    public SummonerBuilder withPlatform(Platform platform)
    {
        return new SummonerBuilder(this.name, this.sumId, this.accId, platform);
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
        
        
        if (accId > 0)
        {
            builder.withURLParameter(Constants.ACCOUNT_ID_PLACEHOLDER, String.valueOf(this.accId));
            endpoint = URLEndpoint.V3_SUMMONER_BY_ACCOUNT;
        }
        
        if (sumId > 0)
        {
            builder.withURLParameter(Constants.SUMMONER_ID_PLACEHOLDER, String.valueOf(this.sumId));
            endpoint = URLEndpoint.V3_SUMMONER_BY_ID;
        }
        
        if (name.length() > 0)
        {
            builder.withURLParameter(Constants.SUMMONER_NAME_PLACEHOLDER, Utils.normalizeSummonerName(this.name));
            endpoint = URLEndpoint.V3_SUMMONER_BY_NAME;
        }
        
        builder.withEndpoint(endpoint);
        
        
        Optional chl = DataCall.getCacheProvider().get(endpoint, this.platform, this.accId, this.sumId, this.name);
        if (chl.isPresent())
        {
            return (Summoner) chl.get();
        }
        
        
        Object sob = builder.build();
        
        if (sob instanceof Pair)
        {
            return null;
        }
        
        DataCall.getCacheProvider().store(endpoint, sob, this.platform, this.accId, this.sumId, this.name);
        return (Summoner) sob;
    }
}
