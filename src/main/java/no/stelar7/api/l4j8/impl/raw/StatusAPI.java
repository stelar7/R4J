package no.stelar7.api.l4j8.impl.raw;

import no.stelar7.api.l4j8.basic.calling.*;
import no.stelar7.api.l4j8.basic.constants.api.*;
import no.stelar7.api.l4j8.pojo.status.ShardStatus;

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
