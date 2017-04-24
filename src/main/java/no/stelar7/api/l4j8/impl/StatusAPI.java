package no.stelar7.api.l4j8.impl;

import no.stelar7.api.l4j8.basic.DataCall.DataCallBuilder;
import no.stelar7.api.l4j8.basic.constants.api.*;
import no.stelar7.api.l4j8.pojo.status.ShardStatus;

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
        
        return (ShardStatus) builder.build();
    }
    
}
