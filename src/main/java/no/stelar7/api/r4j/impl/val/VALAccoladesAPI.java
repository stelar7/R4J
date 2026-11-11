package no.stelar7.api.r4j.impl.val;

import java.util.concurrent.CompletableFuture;
import org.jetbrains.annotations.Nullable;
import com.google.gson.JsonElement;
import no.stelar7.api.r4j.basic.calling.*;
import no.stelar7.api.r4j.basic.constants.api.*;
import no.stelar7.api.r4j.basic.constants.api.regions.ValorantShard;

/**
 * val-accolades-v1.
 * The api documentation does not describe what these endpoints return, so the json is returned as is. Nothing is cached.
 */
public final class VALAccoladesAPI
{
    private static final VALAccoladesAPI INSTANCE = new VALAccoladesAPI(R4JContext.getDefault());

    public static VALAccoladesAPI getInstance()
    {
        return VALAccoladesAPI.INSTANCE;
    }

    /**
     * @param context the context to take the credentials, cache and limiters from
     * @return the api belonging to the context
     */
    public static VALAccoladesAPI getInstance(R4JContext context)
    {
        return context == null || context == R4JContext.getDefault() ? INSTANCE : context.getApi(VALAccoladesAPI.class, VALAccoladesAPI::new);
    }

    private final R4JContext context;

    private VALAccoladesAPI(R4JContext context)
    {
        this.context = context;
    }

    /**
     * @param platform    the shard to query
     * @param enabledOnly only return the accolades that are enabled (null to use the api default)
     * @return the json returned by the api, or null if nothing was found
     */
    @Nullable
    public JsonElement getAccolades(ValorantShard platform, Boolean enabledOnly)
    {
        DataCallBuilder builder = new DataCallBuilder(context).withHeader(Constants.X_RIOT_TOKEN_HEADER_KEY, context.getCredentials().getVALAPIKey())
                                                       .withEndpoint(URLEndpoint.V1_VAL_ACCOLADES)
                                                       .withPlatform(platform);

        if (enabledOnly != null)
        {
            builder.withQueryParameter(Constants.ENABLED_ONLY_PLACEHOLDER_DATA, String.valueOf(enabledOnly));
        }

        return builder.buildAs(JsonElement.class);
    }

    /**
     * @param platform the shard the player plays on
     * @param puuid    the puuid of the player
     * @return the json returned by the api, or null if nothing was found
     */
    @Nullable
    public JsonElement getPlayerAccolades(ValorantShard platform, String puuid)
    {
        DataCallBuilder builder = new DataCallBuilder(context).withURLParameter(Constants.PUUID_ID_PLACEHOLDER, puuid)
                                                       .withHeader(Constants.X_RIOT_TOKEN_HEADER_KEY, context.getCredentials().getVALAPIKey())
                                                       .withEndpoint(URLEndpoint.V1_VAL_ACCOLADES_BY_PUUID)
                                                       .withPlatform(platform);

        return builder.buildAs(JsonElement.class);
    }
    
    /**
     * Like {@link #getAccolades}, without a thread waiting for the answer: the ratelimit, the request and the retries are handled in the background.
     *
     * @return a future of what the blocking method returns
     */
    public CompletableFuture<JsonElement> getAccoladesAsync(ValorantShard platform, Boolean enabledOnly)
    {
        try
        {
            DataCallBuilder builder = new DataCallBuilder(context).withHeader(Constants.X_RIOT_TOKEN_HEADER_KEY, context.getCredentials().getVALAPIKey())
                                                           .withEndpoint(URLEndpoint.V1_VAL_ACCOLADES)
                                                           .withPlatform(platform);

            if (enabledOnly != null)
            {
                builder.withQueryParameter(Constants.ENABLED_ONLY_PLACEHOLDER_DATA, String.valueOf(enabledOnly));
            }

            return builder.buildAsAsync(JsonElement.class);
        } catch (RuntimeException e)
        {
            // what the blocking method would have thrown before making the call (ie. a missing api key)
            return CompletableFuture.failedFuture(e);
        }
    }
    
    /**
     * Like {@link #getPlayerAccolades}, without a thread waiting for the answer: the ratelimit, the request and the retries are handled in the background.
     *
     * @return a future of what the blocking method returns
     */
    public CompletableFuture<JsonElement> getPlayerAccoladesAsync(ValorantShard platform, String puuid)
    {
        try
        {
            DataCallBuilder builder = new DataCallBuilder(context).withURLParameter(Constants.PUUID_ID_PLACEHOLDER, puuid)
                                                           .withHeader(Constants.X_RIOT_TOKEN_HEADER_KEY, context.getCredentials().getVALAPIKey())
                                                           .withEndpoint(URLEndpoint.V1_VAL_ACCOLADES_BY_PUUID)
                                                           .withPlatform(platform);

            return builder.buildAsAsync(JsonElement.class);
        } catch (RuntimeException e)
        {
            // what the blocking method would have thrown before making the call (ie. a missing api key)
            return CompletableFuture.failedFuture(e);
        }
    }
}
