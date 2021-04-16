package no.stelar7.api.r4j.impl.val;

import no.stelar7.api.r4j.basic.calling.*;
import no.stelar7.api.r4j.basic.constants.api.*;
import no.stelar7.api.r4j.basic.constants.api.regions.ValorantShard;
import no.stelar7.api.r4j.pojo.val.content.Content;

import java.util.*;

public class VALContentAPI
{
    private static final VALContentAPI INSTANCE = new VALContentAPI();
    
    public static VALContentAPI getInstance()
    {
        return VALContentAPI.INSTANCE;
    }
    
    private VALContentAPI()
    {
        // Hide public constructor
    }
    
    public Content getContent(ValorantShard server, Optional<String> locale)
    {
        DataCallBuilder builder = new DataCallBuilder().withURLParameter(Constants.REGION_PLACEHOLDER, server.name())
                                                       .withHeader(Constants.X_RIOT_TOKEN_HEADER_KEY, DataCall.getCredentials().getVALAPIKey())
                                                       .withEndpoint(URLEndpoint.V1_VAL_CONTENT)
                                                       .withPlatform(server);
    
        locale.ifPresent(s -> builder.withQueryParameter(Constants.LOCALE_PLACEHOLDER_DATA, s));
        
        Map<String, Object> data = new TreeMap<>();
        data.put("platform", server);
        data.put("locale", locale.orElse("null"));
        
        Optional<?> chl = DataCall.getCacheProvider().get(URLEndpoint.V1_VAL_CONTENT, data);
        if (chl.isPresent())
        {
            return (Content) chl.get();
        }
        
        try
        {
            Object ret = builder.build();
            Content cont = (Content) ret;
            
            data.put("value", cont);
            DataCall.getCacheProvider().store(URLEndpoint.V1_VAL_CONTENT, data);
            
            return cont;
        } catch (ClassCastException e)
        {
            return null;
        }
    }
}
