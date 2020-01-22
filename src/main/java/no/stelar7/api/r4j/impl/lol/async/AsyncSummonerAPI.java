package no.stelar7.api.r4j.impl.lol.async;

import no.stelar7.api.r4j.basic.calling.*;
import no.stelar7.api.r4j.basic.constants.api.*;
import no.stelar7.api.r4j.basic.utils.Utils;
import no.stelar7.api.r4j.pojo.lol.summoner.Summoner;

import java.util.*;
import java.util.concurrent.*;

public class AsyncSummonerAPI
{
    
    private static final AsyncSummonerAPI INSTANCE = new AsyncSummonerAPI();
    
    public static AsyncSummonerAPI getInstance()
    {
        return AsyncSummonerAPI.INSTANCE;
    }
    
    private static final Map<Platform, ExecutorService> threadPool = new EnumMap<Platform, ExecutorService>(Platform.class)
    {{
        for (Platform platform : Platform.values())
        {
            put(platform, Executors.newFixedThreadPool(1));
        }
    }};
    
    private AsyncSummonerAPI()
    {
        // Hide public constructor
    }
    
    
    /**
     * The response object contains the summoner objects mapped by their user id.
     *
     * @param server     the region to execute against
     * @param summonerId summonerId associated with summoners to retrieve.
     * @return Optional Summoner
     */
    public CompletableFuture<Summoner> getSummonerById(final Platform server, String summonerId)
    {
        DataCallBuilder builder = new DataCallBuilder()
                .withURLParameter(Constants.SUMMONER_ID_PLACEHOLDER, summonerId)
                .withEndpoint(URLEndpoint.V4_SUMMONER_BY_ID)
                .withPlatform(server);
        
        Map<String, Object> data = new TreeMap<>();
        data.put("platform", server);
        data.put("is", summonerId);
        
        return DataCall.getCacheProvider()
                       .get(URLEndpoint.V4_SUMMONER_BY_ID, data)
                       .map(o -> CompletableFuture.completedFuture((Summoner) o))
                       .orElseGet(() -> CompletableFuture.supplyAsync(() -> {
                           try
                           {
                               Summoner summoner = (Summoner) builder.build();
                
                               data.put("value", summoner);
                               DataCall.getCacheProvider().store(URLEndpoint.V4_SUMMONER_BY_ID, data);
                
                               return summoner;
                           } catch (ClassCastException e)
                           {
                               return null;
                           }
                       }, threadPool.get(server)));
        
    }
    
    /**
     * The response object contains the summoner objects mapped by their username.
     *
     * @param server       the region to execute against
     * @param summonerName summoner name  associated with summoner to retrieve.
     * @return Optional Summoner
     */
    public CompletableFuture<Summoner> getSummonerByName(final Platform server, String summonerName)
    {
        DataCallBuilder builder = new DataCallBuilder()
                .withURLParameter(Constants.SUMMONER_NAME_PLACEHOLDER, Utils.normalizeString(summonerName))
                .withEndpoint(URLEndpoint.V4_SUMMONER_BY_NAME)
                .withPlatform(server);
        
        Map<String, Object> data = new TreeMap<>();
        data.put("platform", server);
        data.put("name", summonerName);
        
        
        return DataCall.getCacheProvider()
                       .get(URLEndpoint.V4_SUMMONER_BY_NAME, data)
                       .map(o -> CompletableFuture.completedFuture((Summoner) o))
                       .orElseGet(() -> CompletableFuture.supplyAsync(() -> {
                           try
                           {
                               Summoner summoner = (Summoner) builder.build();
                
                               data.put("value", summoner);
                               DataCall.getCacheProvider().store(URLEndpoint.V4_SUMMONER_BY_NAME, data);
                
                               return summoner;
                           } catch (ClassCastException e)
                           {
                               return null;
                           }
                       }, threadPool.get(server)));
        
    }
    
    /**
     * The response object contains the summoner objects mapped by their username.
     *
     * @param server    the region to execute against
     * @param accountId accountId associated with summoner to retrieve.
     * @return Optional Summoner
     */
    public CompletableFuture<Summoner> getSummonerByAccount(final Platform server, String accountId)
    {
        DataCallBuilder builder = new DataCallBuilder()
                .withURLParameter(Constants.ACCOUNT_ID_PLACEHOLDER, accountId)
                .withEndpoint(URLEndpoint.V4_SUMMONER_BY_ACCOUNT)
                .withPlatform(server);
        
        Map<String, Object> data = new TreeMap<>();
        data.put("platform", server);
        data.put("accountid", accountId);
        
        return DataCall.getCacheProvider()
                       .get(URLEndpoint.V4_SUMMONER_BY_ACCOUNT, data)
                       .map(o -> CompletableFuture.completedFuture((Summoner) o))
                       .orElseGet(() -> CompletableFuture.supplyAsync(() -> {
                           try
                           {
                               Summoner summoner = (Summoner) builder.build();
                
                               data.put("value", summoner);
                               DataCall.getCacheProvider().store(URLEndpoint.V4_SUMMONER_BY_ACCOUNT, data);
                
                               return summoner;
                           } catch (ClassCastException e)
                           {
                               return null;
                           }
                       }, threadPool.get(server)));
        
    }
    
    
    /**
     * The response object contains the summoner objects mapped by their username.
     *
     * @param server the region to execute against
     * @param PUUID  puuid associated with summoner to retrieve.
     * @return Optional Summoner
     */
    public CompletableFuture<Summoner> getSummonerByPUUID(final Platform server, String PUUID)
    {
        DataCallBuilder builder = new DataCallBuilder()
                .withURLParameter(Constants.PUUID_ID_PLACEHOLDER, PUUID)
                .withEndpoint(URLEndpoint.V4_SUMMONER_BY_PUUID)
                .withPlatform(server);
        
        Map<String, Object> data = new TreeMap<>();
        data.put("platform", server);
        data.put("puuid", PUUID);
        
        return DataCall.getCacheProvider()
                       .get(URLEndpoint.V4_SUMMONER_BY_PUUID, data)
                       .map(o -> CompletableFuture.completedFuture((Summoner) o))
                       .orElseGet(() -> CompletableFuture.supplyAsync(() -> {
                           try
                           {
                               Summoner summoner = (Summoner) builder.build();
                
                               data.put("value", summoner);
                               DataCall.getCacheProvider().store(URLEndpoint.V4_SUMMONER_BY_PUUID, data);
                
                               return summoner;
                           } catch (ClassCastException e)
                           {
                               return null;
                           }
                       }, threadPool.get(server)));
        
    }
}
