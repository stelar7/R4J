package no.stelar7.api.r4j.impl.val;

import no.stelar7.api.r4j.basic.calling.*;
import no.stelar7.api.r4j.basic.constants.api.*;
import no.stelar7.api.r4j.basic.constants.api.regions.ValorantShard;
import no.stelar7.api.r4j.pojo.val.ranked.Leaderboard;

import java.util.*;

public class VALRankedAPI
{
    private static final VALRankedAPI INSTANCE = new VALRankedAPI();
    
    public static VALRankedAPI getInstance()
    {
        return VALRankedAPI.INSTANCE;
    }
    
    private VALRankedAPI()
    {
        // Hide public constructor
    }
    
    public Leaderboard getLeaderboard(ValorantShard platform, String actId, int startIndex, int size)
    {
        DataCallBuilder builder = new DataCallBuilder().withURLParameter(Constants.ACT_ID_PLACEHOLDER, actId)
                                                       .withQueryParameter(Constants.STARTINDEX_PLACEHOLDER_DATA, String.valueOf(startIndex))
                                                       .withQueryParameter(Constants.SIZE_PLACEHOLDER_DATA, String.valueOf(size))
                                                       .withHeader(Constants.X_RIOT_TOKEN_HEADER_KEY, DataCall.getCredentials().getVALAPIKey())
                                                       .withEndpoint(URLEndpoint.V1_VAL_LEADERBOARD_BY_ACT)
                                                       .withPlatform(platform);
        
        Map<String, Object> data = new LinkedHashMap<>();
        data.put("platform", platform);
        data.put("actId", actId);
        data.put("startIndex", startIndex);
        data.put("size", size);
        
        Optional<?> chl = DataCall.getCacheProvider().get(URLEndpoint.V1_VAL_LEADERBOARD_BY_ACT, data);
        if (chl.isPresent())
        {
            return (Leaderboard) chl.get();
        }
        
        try
        {
            Leaderboard match = (Leaderboard) builder.build();
            
            data.put("value", match);
            DataCall.getCacheProvider().store(URLEndpoint.V1_VAL_LEADERBOARD_BY_ACT, data);
            
            return match;
        } catch (ClassCastException e)
        {
            return null;
        }
    }
}
