package no.stelar7.api.l4j8.impl.lol.raw;

import no.stelar7.api.l4j8.basic.calling.DataCallBuilder;
import no.stelar7.api.l4j8.basic.constants.api.*;
import no.stelar7.api.l4j8.basic.utils.Pair;

@SuppressWarnings("unchecked")
public final class ThirdPartyAPI
{
    private static final ThirdPartyAPI INSTANCE = new ThirdPartyAPI();
    
    public static ThirdPartyAPI getInstance()
    {
        return ThirdPartyAPI.INSTANCE;
    }
    
    private ThirdPartyAPI()
    {
        // Hide public constructor
    }
    
    public boolean checkCode(Platform platform, String summonerId)
    {
        DataCallBuilder builder = new DataCallBuilder().withURLParameter(Constants.SUMMONER_ID_PLACEHOLDER, summonerId)
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
    
    public String getCode(Platform platform, String summonerId)
    {
        DataCallBuilder builder = new DataCallBuilder().withURLParameter(Constants.SUMMONER_ID_PLACEHOLDER, summonerId)
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
