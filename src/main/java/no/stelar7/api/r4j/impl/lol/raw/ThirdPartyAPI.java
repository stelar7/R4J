package no.stelar7.api.r4j.impl.lol.raw;

import no.stelar7.api.r4j.basic.calling.R4JContext;
import no.stelar7.api.r4j.basic.calling.DataCallBuilder;
import no.stelar7.api.r4j.basic.constants.api.*;
import no.stelar7.api.r4j.basic.constants.api.regions.LeagueShard;
import no.stelar7.api.r4j.basic.utils.Pair;

@SuppressWarnings("unchecked")
/**
 * @deprecated third-party-code is no longer listed in the Riot api documentation, and it is looked up by summoner id, which the api no longer hands out
 */
@Deprecated(since = "3.0.0", forRemoval = true)
public final class ThirdPartyAPI
{
    private static final ThirdPartyAPI INSTANCE = new ThirdPartyAPI(R4JContext.getDefault());
    
    public static ThirdPartyAPI getInstance()
    {
        return ThirdPartyAPI.INSTANCE;
    }
    
    /**
     * @param context the context to take the credentials, cache and limiters from
     * @return the api belonging to the context
     */
    public static ThirdPartyAPI getInstance(R4JContext context)
    {
        return context == null || context == R4JContext.getDefault() ? INSTANCE : context.getApi(ThirdPartyAPI.class, ThirdPartyAPI::new);
    }

    private final R4JContext context;

    private ThirdPartyAPI(R4JContext context)
    {
        this.context = context;
    }
    
    public boolean checkCode(LeagueShard platform, String summonerId)
    {
        DataCallBuilder builder = new DataCallBuilder(context).withURLParameter(Constants.SUMMONER_ID_PLACEHOLDER, summonerId)
                                                       .withEndpoint(URLEndpoint.V4_THIRD_PARTY_CODE)
                                                       .withPlatform(platform);
        
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
    
    public String getCode(LeagueShard platform, String summonerId)
    {
        DataCallBuilder builder = new DataCallBuilder(context).withURLParameter(Constants.SUMMONER_ID_PLACEHOLDER, summonerId)
                                                       .withEndpoint(URLEndpoint.V4_THIRD_PARTY_CODE)
                                                       .withPlatform(platform);
        
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
