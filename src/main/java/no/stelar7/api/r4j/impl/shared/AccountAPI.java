package no.stelar7.api.r4j.impl.shared;

import java.util.concurrent.CompletableFuture;
import org.jetbrains.annotations.Nullable;
import no.stelar7.api.r4j.basic.calling.*;
import no.stelar7.api.r4j.basic.constants.api.*;
import no.stelar7.api.r4j.basic.constants.api.regions.*;
import no.stelar7.api.r4j.basic.constants.types.ApiKeyType;
import no.stelar7.api.r4j.basic.utils.Utils;
import no.stelar7.api.r4j.pojo.shared.*;

import java.util.*;

public class AccountAPI
{
    
    private static final AccountAPI INSTANCE = new AccountAPI(R4JContext.getDefault());
    
    public static AccountAPI getInstance()
    {
        return AccountAPI.INSTANCE;
    }
    
    /**
     * @param context the context to take the credentials, cache and limiters from
     * @return the api belonging to the context
     */
    public static AccountAPI getInstance(R4JContext context)
    {
        return context == null || context == R4JContext.getDefault() ? INSTANCE : context.getApi(AccountAPI.class, AccountAPI::new);
    }

    private final R4JContext context;

    private AccountAPI(R4JContext context)
    {
        this.context = context;
    }
    
    @Nullable
    public RiotAccount getAccountByPUUID(RegionShard server, String puuid)
    {
        return getAccountByPUUID(server, puuid, ApiKeyType.VAL);
    }
    
    @Nullable
    public RiotAccount getAccountByPUUID(RegionShard server, String puuid, ApiKeyType keyType)
    {   
        
        DataCallBuilder builder = new DataCallBuilder(context).withURLParameter(Constants.PUUID_ID_PLACEHOLDER, puuid)
                                                       .withHeader(Constants.X_RIOT_TOKEN_HEADER_KEY, context.getCredentials().getKey(keyType))
                                                       .withEndpoint(URLEndpoint.V1_SHARED_ACCOUNT_BY_PUUID)
                                                       .withPlatform(server);
        Map<String, Object> data = new LinkedHashMap<>();
        data.put("platform", server);
        data.put("puuid", puuid);
        
        return builder.buildCached(RiotAccount.class, data);
    }
    
    @Nullable
    public RiotAccount getAccountByTag(RegionShard server, String name, String tag)
    {
        return getAccountByTag(server, name, tag, ApiKeyType.VAL);
    }
    
    @Nullable
    public RiotAccount getAccountByTag(RegionShard server, String name, String tag, ApiKeyType keyType)
    {
        DataCallBuilder builder = new DataCallBuilder(context).withURLParameter(Constants.GAME_NAME_PLACEHOLDER, Utils.normalizeString(name))
                                                       .withURLParameter(Constants.TAG_LINE_PLACEHOLDER, Utils.normalizeString(tag))
                                                       .withHeader(Constants.X_RIOT_TOKEN_HEADER_KEY, context.getCredentials().getKey(keyType))
                                                       .withEndpoint(URLEndpoint.V1_SHARED_ACCOUNT_BY_TAG)
                                                       .withPlatform(server);
        Map<String, Object> data = new LinkedHashMap<>();
        data.put("platform", server);
        data.put("name", Utils.normalizeString(name));
        data.put("tag", Utils.normalizeString(tag));
        data.put("keytype", keyType);
        
        return builder.buildCached(RiotAccount.class, data);
    }
    
    @Nullable
    public RiotAccountShard getActiveShard(RegionShard server, ShardableGame game, String puuid)
    {
        return getActiveShard(server, game, puuid, ApiKeyType.VAL);
    }
    
    @Nullable
    public RiotAccountShard getActiveShard(RegionShard server, ShardableGame game, String puuid, ApiKeyType keyType)
    {
        DataCallBuilder builder = new DataCallBuilder(context).withURLParameter(Constants.GAME_PLACEHOLDER, game.getRealmValue())
                                                       .withURLParameter(Constants.PUUID_ID_PLACEHOLDER, puuid)
                                                       .withHeader(Constants.X_RIOT_TOKEN_HEADER_KEY, context.getCredentials().getKey(keyType))
                                                       .withEndpoint(URLEndpoint.V1_SHARED_SHARD_BY_PUUID)
                                                       .withPlatform(server);
        Map<String, Object> data = new LinkedHashMap<>();
        data.put("platform", server);
        data.put("game", game);
        data.put("puuid", puuid);
        
        return builder.buildCached(RiotAccountShard.class, data);
    }
    
    /**
     * The platform the player is active on, for {@link ShardableGame#LOL} or {@link ShardableGame#TFT}
     */
    @Nullable
    public RiotAccountRegion getActiveRegion(RegionShard server, ShardableGame game, String puuid)
    {
        return getActiveRegion(server, game, puuid, ApiKeyType.LOL);
    }
    
    @Nullable
    public RiotAccountRegion getActiveRegion(RegionShard server, ShardableGame game, String puuid, ApiKeyType keyType)
    {
        DataCallBuilder builder = new DataCallBuilder(context).withURLParameter(Constants.GAME_PLACEHOLDER, game.getRealmValue())
                                                       .withURLParameter(Constants.PUUID_ID_PLACEHOLDER, puuid)
                                                       .withHeader(Constants.X_RIOT_TOKEN_HEADER_KEY, context.getCredentials().getKey(keyType))
                                                       .withEndpoint(URLEndpoint.V1_SHARED_REGION_BY_PUUID)
                                                       .withPlatform(server);
        Map<String, Object> data = new LinkedHashMap<>();
        data.put("platform", server);
        data.put("game", game);
        data.put("puuid", puuid);
        data.put("keytype", keyType);
        
        return builder.buildCached(RiotAccountRegion.class, data);
    }
    
    /**
     * The account of a player that signed in with RSO
     *
     * @param server      the region to query
     * @param accessToken the access token of the player
     * @param keyType     the api key of the product the RSO client belongs to
     * @return the account, or null if it was not found
     */
    @Nullable
    public RiotAccount getAccountByAccessToken(RegionShard server, String accessToken, ApiKeyType keyType)
    {
        DataCallBuilder builder = new DataCallBuilder(context).withHeader(Constants.X_RIOT_TOKEN_HEADER_KEY, context.getCredentials().getKey(keyType))
                                                       .withAccessToken(accessToken)
                                                       .withEndpoint(URLEndpoint.V1_SHARED_ACCOUNT_ME)
                                                       .withPlatform(server);

        // not cached, as the result depends on who the token belongs to
        return builder.buildAs(RiotAccount.class);
    }
    
    // ---- Generated: the ...Async twin of every method above. Do not edit by hand; they follow the blocking methods.
    
    /**
     * Runs {@link #getAccountByPUUID} on a background thread.
     *
     * @return a future of what the blocking method returns
     */
    public CompletableFuture<RiotAccount> getAccountByPUUIDAsync(RegionShard server, String puuid)
    {
        return context.callAsync(() -> getAccountByPUUID(server, puuid));
    }
    
    /**
     * Like {@link #getAccountByPUUID}, without a thread waiting for the answer: the ratelimit, the request and the retries are handled in the background.
     *
     * @return a future of what the blocking method returns
     */
    public CompletableFuture<RiotAccount> getAccountByPUUIDAsync(RegionShard server, String puuid, ApiKeyType keyType)
    {
        try
        {
        
            DataCallBuilder builder = new DataCallBuilder(context).withURLParameter(Constants.PUUID_ID_PLACEHOLDER, puuid)
                                                           .withHeader(Constants.X_RIOT_TOKEN_HEADER_KEY, context.getCredentials().getKey(keyType))
                                                           .withEndpoint(URLEndpoint.V1_SHARED_ACCOUNT_BY_PUUID)
                                                           .withPlatform(server);
            Map<String, Object> data = new LinkedHashMap<>();
            data.put("platform", server);
            data.put("puuid", puuid);
        
            return builder.buildCachedAsync(RiotAccount.class, data);
        } catch (RuntimeException e)
        {
            // what the blocking method would have thrown before making the call (ie. a missing api key)
            return CompletableFuture.failedFuture(e);
        }
    }
    
    /**
     * Runs {@link #getAccountByTag} on a background thread.
     *
     * @return a future of what the blocking method returns
     */
    public CompletableFuture<RiotAccount> getAccountByTagAsync(RegionShard server, String name, String tag)
    {
        return context.callAsync(() -> getAccountByTag(server, name, tag));
    }
    
    /**
     * Like {@link #getAccountByTag}, without a thread waiting for the answer: the ratelimit, the request and the retries are handled in the background.
     *
     * @return a future of what the blocking method returns
     */
    public CompletableFuture<RiotAccount> getAccountByTagAsync(RegionShard server, String name, String tag, ApiKeyType keyType)
    {
        try
        {
            DataCallBuilder builder = new DataCallBuilder(context).withURLParameter(Constants.GAME_NAME_PLACEHOLDER, Utils.normalizeString(name))
                                                           .withURLParameter(Constants.TAG_LINE_PLACEHOLDER, Utils.normalizeString(tag))
                                                           .withHeader(Constants.X_RIOT_TOKEN_HEADER_KEY, context.getCredentials().getKey(keyType))
                                                           .withEndpoint(URLEndpoint.V1_SHARED_ACCOUNT_BY_TAG)
                                                           .withPlatform(server);
            Map<String, Object> data = new LinkedHashMap<>();
            data.put("platform", server);
            data.put("name", Utils.normalizeString(name));
            data.put("tag", Utils.normalizeString(tag));
            data.put("keytype", keyType);
        
            return builder.buildCachedAsync(RiotAccount.class, data);
        } catch (RuntimeException e)
        {
            // what the blocking method would have thrown before making the call (ie. a missing api key)
            return CompletableFuture.failedFuture(e);
        }
    }
    
    /**
     * Runs {@link #getActiveShard} on a background thread.
     *
     * @return a future of what the blocking method returns
     */
    public CompletableFuture<RiotAccountShard> getActiveShardAsync(RegionShard server, ShardableGame game, String puuid)
    {
        return context.callAsync(() -> getActiveShard(server, game, puuid));
    }
    
    /**
     * Like {@link #getActiveShard}, without a thread waiting for the answer: the ratelimit, the request and the retries are handled in the background.
     *
     * @return a future of what the blocking method returns
     */
    public CompletableFuture<RiotAccountShard> getActiveShardAsync(RegionShard server, ShardableGame game, String puuid, ApiKeyType keyType)
    {
        try
        {
            DataCallBuilder builder = new DataCallBuilder(context).withURLParameter(Constants.GAME_PLACEHOLDER, game.getRealmValue())
                                                           .withURLParameter(Constants.PUUID_ID_PLACEHOLDER, puuid)
                                                           .withHeader(Constants.X_RIOT_TOKEN_HEADER_KEY, context.getCredentials().getKey(keyType))
                                                           .withEndpoint(URLEndpoint.V1_SHARED_SHARD_BY_PUUID)
                                                           .withPlatform(server);
            Map<String, Object> data = new LinkedHashMap<>();
            data.put("platform", server);
            data.put("game", game);
            data.put("puuid", puuid);
        
            return builder.buildCachedAsync(RiotAccountShard.class, data);
        } catch (RuntimeException e)
        {
            // what the blocking method would have thrown before making the call (ie. a missing api key)
            return CompletableFuture.failedFuture(e);
        }
    }
    
    /**
     * Runs {@link #getActiveRegion} on a background thread.
     *
     * @return a future of what the blocking method returns
     */
    public CompletableFuture<RiotAccountRegion> getActiveRegionAsync(RegionShard server, ShardableGame game, String puuid)
    {
        return context.callAsync(() -> getActiveRegion(server, game, puuid));
    }
    
    /**
     * Like {@link #getActiveRegion}, without a thread waiting for the answer: the ratelimit, the request and the retries are handled in the background.
     *
     * @return a future of what the blocking method returns
     */
    public CompletableFuture<RiotAccountRegion> getActiveRegionAsync(RegionShard server, ShardableGame game, String puuid, ApiKeyType keyType)
    {
        try
        {
            DataCallBuilder builder = new DataCallBuilder(context).withURLParameter(Constants.GAME_PLACEHOLDER, game.getRealmValue())
                                                           .withURLParameter(Constants.PUUID_ID_PLACEHOLDER, puuid)
                                                           .withHeader(Constants.X_RIOT_TOKEN_HEADER_KEY, context.getCredentials().getKey(keyType))
                                                           .withEndpoint(URLEndpoint.V1_SHARED_REGION_BY_PUUID)
                                                           .withPlatform(server);
            Map<String, Object> data = new LinkedHashMap<>();
            data.put("platform", server);
            data.put("game", game);
            data.put("puuid", puuid);
            data.put("keytype", keyType);
        
            return builder.buildCachedAsync(RiotAccountRegion.class, data);
        } catch (RuntimeException e)
        {
            // what the blocking method would have thrown before making the call (ie. a missing api key)
            return CompletableFuture.failedFuture(e);
        }
    }
    
    /**
     * Like {@link #getAccountByAccessToken}, without a thread waiting for the answer: the ratelimit, the request and the retries are handled in the background.
     *
     * @return a future of what the blocking method returns
     */
    public CompletableFuture<RiotAccount> getAccountByAccessTokenAsync(RegionShard server, String accessToken, ApiKeyType keyType)
    {
        try
        {
            DataCallBuilder builder = new DataCallBuilder(context).withHeader(Constants.X_RIOT_TOKEN_HEADER_KEY, context.getCredentials().getKey(keyType))
                                                           .withAccessToken(accessToken)
                                                           .withEndpoint(URLEndpoint.V1_SHARED_ACCOUNT_ME)
                                                           .withPlatform(server);

            // not cached, as the result depends on who the token belongs to
            return builder.buildAsAsync(RiotAccount.class);
        } catch (RuntimeException e)
        {
            // what the blocking method would have thrown before making the call (ie. a missing api key)
            return CompletableFuture.failedFuture(e);
        }
    }
}
