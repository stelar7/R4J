package no.stelar7.api.r4j.impl.lol.raw;

import no.stelar7.api.r4j.pojo.lol.status.ShardStatus;
import no.stelar7.api.r4j.basic.calling.*;
import no.stelar7.api.r4j.basic.constants.api.*;

import java.util.Optional;

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
    
    public ShardStatus getShardStatus(Platform server)
    {
        DataCallBuilder builder = new DataCallBuilder().withEndpoint(URLEndpoint.V3_SHARD_STATUS)
                                                       .withPlatform(server);
        
        Optional chl = DataCall.getCacheProvider().get(URLEndpoint.V3_SHARD_STATUS, server);
        if (chl.isPresent())
        {
            return (ShardStatus) chl.get();
        }
        
        try
        {
            ShardStatus list = (ShardStatus) builder.build();
            DataCall.getCacheProvider().store(URLEndpoint.V3_SHARD_STATUS, list, server);
            return list;
        } catch (ClassCastException e)
        {
            
            return null;
        }
    }
    
}
