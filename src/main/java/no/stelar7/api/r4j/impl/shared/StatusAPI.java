package no.stelar7.api.r4j.impl.shared;

import no.stelar7.api.r4j.basic.calling.*;
import no.stelar7.api.r4j.basic.constants.api.URLEndpoint;
import no.stelar7.api.r4j.basic.constants.api.regions.*;
import no.stelar7.api.r4j.pojo.lol.status.PlatformData;

import java.util.*;

public final class StatusAPI
{
    private static final StatusAPI INSTANCE = new StatusAPI();
    
    public static StatusAPI getInstance()
    {
        return StatusAPI.INSTANCE;
    }
    
    private StatusAPI()
    {
        // Hide public constructor
    }
    
    public PlatformData getShardStatus(LeagueShard server)
    {
        DataCallBuilder builder = new DataCallBuilder().withEndpoint(URLEndpoint.V4_STATUS_LOL)
                                                       .withPlatform(server);
        
        Map<String, Object> data = new TreeMap<>();
        data.put("platform", server);
        
        Optional<?> chl = DataCall.getCacheProvider().get(URLEndpoint.V4_STATUS_LOL, data);
        if (chl.isPresent())
        {
            return (PlatformData) chl.get();
        }
        
        try
        {
            PlatformData list = (PlatformData) builder.build();
            
            data.put("value", list);
            DataCall.getCacheProvider().store(URLEndpoint.V4_STATUS_LOL, data);
            
            return list;
        } catch (ClassCastException e)
        {
            
            return null;
        }
    }
    
    public PlatformData getShardStatus(RuneterraShard server)
    {
        DataCallBuilder builder = new DataCallBuilder().withEndpoint(URLEndpoint.V1_STATUS_LOR)
                                                       .withPlatform(server);
        
        Map<String, Object> data = new TreeMap<>();
        data.put("platform", server);
        
        Optional<?> chl = DataCall.getCacheProvider().get(URLEndpoint.V1_STATUS_LOR, data);
        if (chl.isPresent())
        {
            return (PlatformData) chl.get();
        }
        
        try
        {
            PlatformData list = (PlatformData) builder.build();
            
            data.put("value", list);
            DataCall.getCacheProvider().store(URLEndpoint.V1_STATUS_LOR, data);
            
            return list;
        } catch (ClassCastException e)
        {
            
            return null;
        }
    }
    
    public PlatformData getShardStatus(ValorantShard server)
    {
        DataCallBuilder builder = new DataCallBuilder().withEndpoint(URLEndpoint.V1_STATUS_VAL)
                                                       .withPlatform(server);
        
        Map<String, Object> data = new TreeMap<>();
        data.put("platform", server);
        
        Optional<?> chl = DataCall.getCacheProvider().get(URLEndpoint.V1_STATUS_VAL, data);
        if (chl.isPresent())
        {
            return (PlatformData) chl.get();
        }
        
        try
        {
            PlatformData list = (PlatformData) builder.build();
            
            data.put("value", list);
            DataCall.getCacheProvider().store(URLEndpoint.V1_STATUS_VAL, data);
            
            return list;
        } catch (ClassCastException e)
        {
            
            return null;
        }
    }
    
}
