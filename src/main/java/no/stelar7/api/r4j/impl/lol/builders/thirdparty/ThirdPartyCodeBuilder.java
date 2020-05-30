package no.stelar7.api.r4j.impl.lol.builders.thirdparty;

import no.stelar7.api.r4j.basic.calling.DataCallBuilder;
import no.stelar7.api.r4j.basic.constants.api.*;
import no.stelar7.api.r4j.basic.constants.api.regions.LeagueShard;
import no.stelar7.api.r4j.basic.utils.Pair;

@SuppressWarnings("unchecked")
public class ThirdPartyCodeBuilder
{
    
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
        return new ThirdPartyCodeBuilder(platform, this.summonerId);
    }
    
    public ThirdPartyCodeBuilder withSummonerId(String id)
    {
        return new ThirdPartyCodeBuilder(this.platform, id);
    }
    
    
    public boolean checkCode()
    {
        DataCallBuilder builder = new DataCallBuilder().withURLParameter(Constants.SUMMONER_ID_PLACEHOLDER, this.summonerId)
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
        DataCallBuilder builder = new DataCallBuilder().withURLParameter(Constants.SUMMONER_ID_PLACEHOLDER, this.summonerId)
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
