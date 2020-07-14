package no.stelar7.api.r4j.impl.shared;

import no.stelar7.api.r4j.basic.calling.*;
import no.stelar7.api.r4j.basic.constants.api.*;
import no.stelar7.api.r4j.basic.constants.api.regions.*;
import no.stelar7.api.r4j.basic.constants.types.RealmSpesificEnum;
import no.stelar7.api.r4j.pojo.shared.RiotAccount;

import java.util.*;

public class AccountAPI
{
    
    private static final AccountAPI INSTANCE = new AccountAPI();
    
    public static AccountAPI getInstance()
    {
        return AccountAPI.INSTANCE;
    }
    
    private AccountAPI()
    {
        // Hide public constructor
    }
    
    public RiotAccount getAccountByPUUID(RegionShard server, String puuid)
    {
        DataCallBuilder builder = new DataCallBuilder().withURLParameter(Constants.PUUID_ID_PLACEHOLDER, puuid)
                                                       .withHeader(Constants.X_RIOT_TOKEN_HEADER_KEY, DataCall.getCredentials().getTFTAPIKey())
                                                       .withEndpoint(URLEndpoint.V1_SHARED_ACCOUNT_BY_PUUID)
                                                       .withPlatform(server);
        Map<String, Object> data = new TreeMap<>();
        data.put("platform", server);
        
        Optional<?> chl = DataCall.getCacheProvider().get(URLEndpoint.V1_SHARED_ACCOUNT_BY_PUUID, data);
        if (chl.isPresent())
        {
            return (RiotAccount) chl.get();
        }
        
        try
        {
            RiotAccount list = (RiotAccount) builder.build();
            
            data.put("value", list);
            DataCall.getCacheProvider().store(URLEndpoint.V1_SHARED_ACCOUNT_BY_PUUID, data);
            
            return list;
        } catch (ClassCastException e)
        {
            return null;
        }
    }
    
    public RiotAccount getAccountByTag(RegionShard server, String name, String tag)
    {
        DataCallBuilder builder = new DataCallBuilder().withURLParameter(Constants.GAME_NAME_PLACEHOLDER, name)
                                                       .withURLParameter(Constants.TAG_LINE_PLACEHOLDER, tag)
                                                       .withHeader(Constants.X_RIOT_TOKEN_HEADER_KEY, DataCall.getCredentials().getTFTAPIKey())
                                                       .withEndpoint(URLEndpoint.V1_SHARED_ACCOUNT_BY_TAG)
                                                       .withPlatform(server);
        Map<String, Object> data = new TreeMap<>();
        data.put("platform", server);
        
        Optional<?> chl = DataCall.getCacheProvider().get(URLEndpoint.V1_SHARED_ACCOUNT_BY_TAG, data);
        if (chl.isPresent())
        {
            return (RiotAccount) chl.get();
        }
        
        try
        {
            RiotAccount list = (RiotAccount) builder.build();
            
            data.put("value", list);
            DataCall.getCacheProvider().store(URLEndpoint.V1_SHARED_ACCOUNT_BY_TAG, data);
            
            return list;
        } catch (ClassCastException e)
        {
            return null;
        }
    }
    
    public RealmSpesificEnum getActiveShard(RegionShard server, ShardableGame game, String puuid)
    {
        DataCallBuilder builder = new DataCallBuilder().withURLParameter(Constants.GAME_PLACEHOLDER, game.getRealmValue())
                                                       .withURLParameter(Constants.PUUID_ID_PLACEHOLDER, puuid)
                                                       .withHeader(Constants.X_RIOT_TOKEN_HEADER_KEY, DataCall.getCredentials().getTFTAPIKey())
                                                       .withEndpoint(URLEndpoint.V1_SHARED_SHARD_BY_PUUID)
                                                       .withPlatform(server);
        Map<String, Object> data = new TreeMap<>();
        data.put("platform", server);
        
        Optional<?> chl = DataCall.getCacheProvider().get(URLEndpoint.V1_SHARED_SHARD_BY_PUUID, data);
        if (chl.isPresent())
        {
            return (RealmSpesificEnum) chl.get();
        }
        
        try
        {
            RealmSpesificEnum list = (RealmSpesificEnum) builder.build();
            
            data.put("value", list);
            DataCall.getCacheProvider().store(URLEndpoint.V1_SHARED_SHARD_BY_PUUID, data);
            
            return list;
        } catch (ClassCastException e)
        {
            return null;
        }
    }
    
    
}
