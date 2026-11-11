package no.stelar7.api.r4j.impl.lol.builders.thirdparty;

import no.stelar7.api.r4j.basic.calling.R4JContext;
import no.stelar7.api.r4j.basic.calling.DataCallBuilder;
import no.stelar7.api.r4j.basic.constants.api.*;
import no.stelar7.api.r4j.basic.constants.api.regions.LeagueShard;
import no.stelar7.api.r4j.basic.utils.Pair;

/**
 * @deprecated third-party-code is no longer listed in the Riot api documentation, see {@link no.stelar7.api.r4j.impl.lol.raw.ThirdPartyAPI}
 */
@Deprecated(since = "3.0.0", forRemoval = true)
@SuppressWarnings("unchecked")
public class ThirdPartyCodeBuilder
{
    private R4JContext context = R4JContext.getDefault();

    /**
     * Uses the credentials, cache and limiters of the context instead of the default ones
     *
     * @param context the context to use
     * @return this
     */
    public ThirdPartyCodeBuilder withContext(R4JContext context)
    {
        this.context = context == null ? R4JContext.getDefault() : context;
        return this;
    }

    
    private final LeagueShard platform;
    private final String      summonerId;
    
    private ThirdPartyCodeBuilder(LeagueShard platform, String summonerId)
    {
        this.platform = platform;
        this.summonerId = summonerId;
    }
    
    public ThirdPartyCodeBuilder()
    {
        this.platform = LeagueShard.UNKNOWN;
        this.summonerId = null;
    }
    
    public ThirdPartyCodeBuilder withPlatform(LeagueShard platform)
    {
        return new ThirdPartyCodeBuilder(platform, this.summonerId).withContext(this.context);
    }
    
    public ThirdPartyCodeBuilder withSummonerId(String id)
    {
        return new ThirdPartyCodeBuilder(this.platform, id).withContext(this.context);
    }
    
    
    public boolean checkCode()
    {
        DataCallBuilder builder = new DataCallBuilder(context).withURLParameter(Constants.SUMMONER_ID_PLACEHOLDER, this.summonerId)
                                                       .withEndpoint(URLEndpoint.V4_THIRD_PARTY_CODE)
                                                       .withPlatform(this.platform);
        
        try
        {
            Pair<String, String> data = (Pair<String, String>) builder.build();
            
            // this is just here to remove intellij warning
            assert data != null;
            
            return false;
        } catch (ClassCastException e)
        {
            
            return true;
        }
    }
    
    public String getCode()
    {
        DataCallBuilder builder = new DataCallBuilder(context).withURLParameter(Constants.SUMMONER_ID_PLACEHOLDER, this.summonerId)
                                                       .withEndpoint(URLEndpoint.V4_THIRD_PARTY_CODE)
                                                       .withPlatform(this.platform);
        
        try
        {
            Pair<String, String> data = (Pair<String, String>) builder.build();
            return data.getValue();
        } catch (ClassCastException e)
        {
            
            return (String) builder.build();
        }
    }
}
