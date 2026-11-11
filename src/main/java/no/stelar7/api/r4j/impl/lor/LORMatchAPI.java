package no.stelar7.api.r4j.impl.lor;

import java.util.concurrent.CompletableFuture;
import org.jetbrains.annotations.Nullable;
import no.stelar7.api.r4j.basic.calling.*;
import no.stelar7.api.r4j.basic.constants.api.*;
import no.stelar7.api.r4j.basic.constants.api.regions.RuneterraShard;
import no.stelar7.api.r4j.pojo.lor.match.*;
import no.stelar7.api.r4j.pojo.shared.GAMHSMatch;

import java.util.*;

public class LORMatchAPI
{
    private static final LORMatchAPI INSTANCE = new LORMatchAPI(R4JContext.getDefault());
    
    /**
     * @param context the context to take the credentials, cache and limiters from
     * @return the api belonging to the context
     */
    public static LORMatchAPI getInstance(R4JContext context)
    {
        return context == null || context == R4JContext.getDefault() ? INSTANCE : context.getApi(LORMatchAPI.class, LORMatchAPI::new);
    }

    private final R4JContext context;

    private LORMatchAPI(R4JContext context)
    {
        this.context = context;
    }
    
    public static LORMatchAPI getInstance()
    {
        return LORMatchAPI.INSTANCE;
    }
    
    @Nullable
    public List<String> getMatchList(RuneterraShard server, String puuid)
    {
        DataCallBuilder builder = getMatchListCall(server, puuid);
        Map<String, Object> data = builder.getCacheKey();
        
        return builder.buildCached(data);
    }

    // the call getMatchList and its twin make
    private DataCallBuilder getMatchListCall(RuneterraShard server, String puuid)
    {
        DataCallBuilder builder = new DataCallBuilder(context)
                .withHeader(Constants.X_RIOT_TOKEN_HEADER_KEY, context.getCredentials().getLORAPIKey())
                .withEndpoint(URLEndpoint.V1_LOR_MATCHES_BY_PUUID)
                .withURLParameter(Constants.PUUID_ID_PLACEHOLDER, puuid)
                .withPlatform(server);
        
        Map<String, Object> data = new LinkedHashMap<>();
        data.put("platform", server);
        data.put("puuid", puuid);

        return builder.withCacheKey(data);
    }
    
    public LORMatch getMatch(RuneterraShard platform, String gameId)
    {
        GAMHSMatch raw = getMatchRAW(platform, gameId);
        return raw != null ? raw.asLORMatch() : null;
    }
    
    public LORMetadata getMetadata(RuneterraShard platform, String gameId)
    {
        GAMHSMatch raw = getMatchRAW(platform, gameId);
        return raw != null ? raw.asLORMetadata() : null;
    }
    
    @Nullable
    public GAMHSMatch getMatchRAW(RuneterraShard platform, String gameId)
    {
        DataCallBuilder builder = getMatchRAWCall(platform, gameId);
        Map<String, Object> data = builder.getCacheKey();
        
        return builder.buildCached(GAMHSMatch.class, data);
    }

    // the call getMatchRAW and its twin make
    private DataCallBuilder getMatchRAWCall(RuneterraShard platform, String gameId)
    {
        DataCallBuilder builder = new DataCallBuilder(context).withURLParameter(Constants.MATCH_ID_PLACEHOLDER, gameId)
                                                       .withHeader(Constants.X_RIOT_TOKEN_HEADER_KEY, context.getCredentials().getLORAPIKey())
                                                       .withEndpoint(URLEndpoint.V1_LOR_MATCH)
                                                       .withPlatform(platform);
        
        Map<String, Object> data = new LinkedHashMap<>();
        data.put("platform", platform);
        data.put("gameid", gameId);

        return builder.withCacheKey(data);
    }
    
    /**
     * Like {@link #getMatchList}, without a thread waiting for the answer: the ratelimit, the request and the retries are handled in the background.
     *
     * @return a future of what the blocking method returns
     */
    public CompletableFuture<List<String>> getMatchListAsync(RuneterraShard server, String puuid)
    {
        try
        {
            DataCallBuilder builder = getMatchListCall(server, puuid);
            Map<String, Object> data = builder.getCacheKey();
    
            return builder.<List<String>>buildCachedAsync(data);
        } catch (RuntimeException e)
        {
            // what the blocking method would have thrown before making the call (ie. a missing api key)
            return CompletableFuture.failedFuture(e);
        }
    }
    
    /**
     * Like {@link #getMatch}, without a thread waiting for the answer: the ratelimit, the request and the retries are handled in the background.
     *
     * @return a future of what the blocking method returns
     */
    public CompletableFuture<LORMatch> getMatchAsync(RuneterraShard platform, String gameId)
    {
        return getMatchRAWAsync(platform, gameId).thenApply(raw -> raw != null ? raw.asLORMatch() : null);
    }
    
    /**
     * Like {@link #getMetadata}, without a thread waiting for the answer: the ratelimit, the request and the retries are handled in the background.
     *
     * @return a future of what the blocking method returns
     */
    public CompletableFuture<LORMetadata> getMetadataAsync(RuneterraShard platform, String gameId)
    {
        return getMatchRAWAsync(platform, gameId).thenApply(raw -> raw != null ? raw.asLORMetadata() : null);
    }
    
    /**
     * Like {@link #getMatchRAW}, without a thread waiting for the answer: the ratelimit, the request and the retries are handled in the background.
     *
     * @return a future of what the blocking method returns
     */
    public CompletableFuture<GAMHSMatch> getMatchRAWAsync(RuneterraShard platform, String gameId)
    {
        try
        {
            DataCallBuilder builder = getMatchRAWCall(platform, gameId);
            Map<String, Object> data = builder.getCacheKey();
    
            return builder.buildCachedAsync(GAMHSMatch.class, data);
        } catch (RuntimeException e)
        {
            // what the blocking method would have thrown before making the call (ie. a missing api key)
            return CompletableFuture.failedFuture(e);
        }
    }
}
