package no.stelar7.api.r4j.impl.tft;

import java.util.concurrent.CompletableFuture;
import org.jetbrains.annotations.Nullable;
import no.stelar7.api.r4j.basic.constants.api.regions.RegionShard;
import no.stelar7.api.r4j.basic.utils.Pair;
import no.stelar7.api.r4j.basic.calling.*;
import no.stelar7.api.r4j.basic.constants.api.*;
import no.stelar7.api.r4j.pojo.shared.GAMHSMatch;
import no.stelar7.api.r4j.pojo.tft.*;

import java.util.*;

public class TFTMatchAPI
{
    private static final TFTMatchAPI INSTANCE = new TFTMatchAPI(R4JContext.getDefault());
    
    public static TFTMatchAPI getInstance()
    {
        return TFTMatchAPI.INSTANCE;
    }
    
    /**
     * @param context the context to take the credentials, cache and limiters from
     * @return the api belonging to the context
     */
    public static TFTMatchAPI getInstance(R4JContext context)
    {
        return context == null || context == R4JContext.getDefault() ? INSTANCE : context.getApi(TFTMatchAPI.class, TFTMatchAPI::new);
    }

    private final R4JContext context;

    private TFTMatchAPI(R4JContext context)
    {
        this.context = context;
    }
    
    public List<String> getMatchList(RegionShard server, String PUUID, Integer start, Integer count, Long startTime, Long endTime)
    {
        DataCallBuilder builder = new DataCallBuilder(context).withURLParameter(Constants.PUUID_ID_PLACEHOLDER, PUUID)
                                                       .withHeader(Constants.X_RIOT_TOKEN_HEADER_KEY, context.getCredentials().getTFTAPIKey())
                                                       .withEndpoint(URLEndpoint.V1_TFT_MATCHLIST)
                                                       .withQueryParameter(Constants.START_PLACEHOLDER_DATA, String.valueOf(start))
                                                       .withQueryParameter(Constants.COUNT_PLACEHOLDER_DATA, String.valueOf(count))
                                                       .withPlatform(server);
    
        if (startTime != null)
        {
            builder.withQueryParameter(Constants.STARTTIME_PLACEHOLDER_DATA, String.valueOf(startTime));
        }
    
        if (endTime != null)
        {
            builder.withQueryParameter(Constants.ENDTIME_PLACEHOLDER_DATA, String.valueOf(endTime));
        }
        
        Map<String, Object> data = new LinkedHashMap<>();
        data.put("platform", server);
        data.put("puuid", PUUID);
        data.put("start", start);
        data.put("count", count);
        data.put("starttime", startTime);
        data.put("endtime", endTime);
        
        List<String> matchList = builder.buildCached(data);
        return matchList != null ? matchList : Collections.emptyList();
    }
    
    
    /**
     * Returns an iterator that transforms all {@code MatchReference} to {@code Match}
     *
     * @param server the platform the account is on
     * @param puuid  the account to check
     * @param count  the amount of games to fetch
     * @return {@code MatchIterator}
     */
    public MatchIterator getMatchIterator(RegionShard server, String puuid, Integer start, Integer count, Long startTime, Long endTime)
    {
        return new MatchIterator(getMatchList(server, puuid, start, count, startTime, endTime), context);
    }
    
    public TFTMatch getMatch(RegionShard platform, String gameId)
    {
        GAMHSMatch raw = getMatchRAW(platform, gameId);
        return raw != null ? raw.asTFTMatch() : null;
    }
    
    public TFTMetadata getMetadata(RegionShard platform, String gameId)
    {
        GAMHSMatch raw = getMatchRAW(platform, gameId);
        return raw != null ? raw.asTFTMetadata() : null;
    }
    
    @Nullable
    public GAMHSMatch getMatchRAW(RegionShard platform, String gameId)
    {
        DataCallBuilder builder = new DataCallBuilder(context).withURLParameter(Constants.MATCH_ID_PLACEHOLDER, gameId)
                                                       .withHeader(Constants.X_RIOT_TOKEN_HEADER_KEY, context.getCredentials().getTFTAPIKey())
                                                       .withEndpoint(URLEndpoint.V1_TFT_MATCH)
                                                       .withPlatform(platform);
        
        Map<String, Object> data = new LinkedHashMap<>();
        data.put("platform", platform);
        data.put("gameid", gameId);
        
        return builder.buildCached(GAMHSMatch.class, data);
    }
    
    // ---- Generated: the ...Async twin of every method above. Do not edit by hand; they follow the blocking methods.
    
    /**
     * Like {@link #getMatchList}, without a thread waiting for the answer: the ratelimit, the request and the retries are handled in the background.
     *
     * @return a future of what the blocking method returns
     */
    public CompletableFuture<List<String>> getMatchListAsync(RegionShard server, String PUUID, Integer start, Integer count, Long startTime, Long endTime)
    {
        try
        {
            DataCallBuilder builder = new DataCallBuilder(context).withURLParameter(Constants.PUUID_ID_PLACEHOLDER, PUUID)
                                                           .withHeader(Constants.X_RIOT_TOKEN_HEADER_KEY, context.getCredentials().getTFTAPIKey())
                                                           .withEndpoint(URLEndpoint.V1_TFT_MATCHLIST)
                                                           .withQueryParameter(Constants.START_PLACEHOLDER_DATA, String.valueOf(start))
                                                           .withQueryParameter(Constants.COUNT_PLACEHOLDER_DATA, String.valueOf(count))
                                                           .withPlatform(server);
    
            if (startTime != null)
            {
                builder.withQueryParameter(Constants.STARTTIME_PLACEHOLDER_DATA, String.valueOf(startTime));
            }
    
            if (endTime != null)
            {
                builder.withQueryParameter(Constants.ENDTIME_PLACEHOLDER_DATA, String.valueOf(endTime));
            }
        
            Map<String, Object> data = new LinkedHashMap<>();
            data.put("platform", server);
            data.put("puuid", PUUID);
            data.put("start", start);
            data.put("count", count);
            data.put("starttime", startTime);
            data.put("endtime", endTime);
        
            return builder.<List<String>>buildCachedAsync(data).thenApply(matchList -> matchList != null ? matchList : Collections.emptyList());
        } catch (RuntimeException e)
        {
            // what the blocking method would have thrown before making the call (ie. a missing api key)
            return CompletableFuture.failedFuture(e);
        }
    }
    
    /**
     * Runs {@link #getMatch} on a background thread.
     *
     * @return a future of what the blocking method returns
     */
    public CompletableFuture<TFTMatch> getMatchAsync(RegionShard platform, String gameId)
    {
        return context.callAsync(() -> getMatch(platform, gameId));
    }
    
    /**
     * Runs {@link #getMetadata} on a background thread.
     *
     * @return a future of what the blocking method returns
     */
    public CompletableFuture<TFTMetadata> getMetadataAsync(RegionShard platform, String gameId)
    {
        return context.callAsync(() -> getMetadata(platform, gameId));
    }
    
    /**
     * Like {@link #getMatchRAW}, without a thread waiting for the answer: the ratelimit, the request and the retries are handled in the background.
     *
     * @return a future of what the blocking method returns
     */
    public CompletableFuture<GAMHSMatch> getMatchRAWAsync(RegionShard platform, String gameId)
    {
        try
        {
            DataCallBuilder builder = new DataCallBuilder(context).withURLParameter(Constants.MATCH_ID_PLACEHOLDER, gameId)
                                                           .withHeader(Constants.X_RIOT_TOKEN_HEADER_KEY, context.getCredentials().getTFTAPIKey())
                                                           .withEndpoint(URLEndpoint.V1_TFT_MATCH)
                                                           .withPlatform(platform);
        
            Map<String, Object> data = new LinkedHashMap<>();
            data.put("platform", platform);
            data.put("gameid", gameId);
        
            return builder.buildCachedAsync(GAMHSMatch.class, data);
        } catch (RuntimeException e)
        {
            // what the blocking method would have thrown before making the call (ie. a missing api key)
            return CompletableFuture.failedFuture(e);
        }
    }
}
