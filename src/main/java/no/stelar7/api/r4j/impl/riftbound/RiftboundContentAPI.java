package no.stelar7.api.r4j.impl.riftbound;

import java.util.concurrent.CompletableFuture;
import org.jetbrains.annotations.Nullable;
import no.stelar7.api.r4j.basic.calling.*;
import no.stelar7.api.r4j.basic.constants.api.*;
import no.stelar7.api.r4j.basic.constants.api.regions.RegionShard;
import no.stelar7.api.r4j.basic.constants.types.ApiKeyType;
import no.stelar7.api.r4j.pojo.riftbound.RiftboundContent;

import java.util.*;

/**
 * riftbound-content-v1
 */
public final class RiftboundContentAPI
{
    private static final RiftboundContentAPI INSTANCE = new RiftboundContentAPI(R4JContext.getDefault());

    public static RiftboundContentAPI getInstance()
    {
        return RiftboundContentAPI.INSTANCE;
    }

    /**
     * @param context the context to take the credentials, cache and limiters from
     * @return the api belonging to the context
     */
    public static RiftboundContentAPI getInstance(R4JContext context)
    {
        return context == null || context == R4JContext.getDefault() ? INSTANCE : context.getApi(RiftboundContentAPI.class, RiftboundContentAPI::new);
    }

    private final R4JContext context;

    private RiftboundContentAPI(R4JContext context)
    {
        this.context = context;
    }

    /**
     * All the sets and cards in the game, using the Riftbound api key (or the LoL key, if no Riftbound key is set).
     *
     * @param server the region to query (AMERICAS, ASIA or EUROPE)
     * @param locale the locale to get the content in (ie. "en"), or null to use the api default
     * @return the content
     */
    public RiftboundContent getContent(RegionShard server, String locale)
    {
        return getContent(server, locale, context.getCredentials().hasKey(ApiKeyType.RIFTBOUND) ? ApiKeyType.RIFTBOUND : ApiKeyType.LOL);
    }

    /**
     * All the sets and cards in the game.
     *
     * @param server  the region to query (AMERICAS, ASIA or EUROPE)
     * @param locale  the locale to get the content in (ie. "en"), or null to use the api default
     * @param keyType which of the keys in the APICredentials has access to the Riftbound api
     * @return the content
     */
    @Nullable
    public RiftboundContent getContent(RegionShard server, String locale, ApiKeyType keyType)
    {
        DataCallBuilder builder = new DataCallBuilder(context).withHeader(Constants.X_RIOT_TOKEN_HEADER_KEY, context.getCredentials().getKey(keyType))
                                                       .withEndpoint(URLEndpoint.V1_RIFTBOUND_CONTENT)
                                                       .withPlatform(server);

        if (locale != null)
        {
            builder.withQueryParameter(Constants.LOCALE_PLACEHOLDER_DATA, locale);
        }

        Map<String, Object> data = new LinkedHashMap<>();
        data.put("platform", server);
        data.put("locale", locale);

        return builder.buildCached(RiftboundContent.class, data);
    }
    
    /**
     * Runs {@link #getContent} on a background thread.
     *
     * @return a future of what the blocking method returns
     */
    public CompletableFuture<RiftboundContent> getContentAsync(RegionShard server, String locale)
    {
        return context.callAsync(() -> getContent(server, locale));
    }
    
    /**
     * Like {@link #getContent}, without a thread waiting for the answer: the ratelimit, the request and the retries are handled in the background.
     *
     * @return a future of what the blocking method returns
     */
    public CompletableFuture<RiftboundContent> getContentAsync(RegionShard server, String locale, ApiKeyType keyType)
    {
        try
        {
            DataCallBuilder builder = new DataCallBuilder(context).withHeader(Constants.X_RIOT_TOKEN_HEADER_KEY, context.getCredentials().getKey(keyType))
                                                           .withEndpoint(URLEndpoint.V1_RIFTBOUND_CONTENT)
                                                           .withPlatform(server);

            if (locale != null)
            {
                builder.withQueryParameter(Constants.LOCALE_PLACEHOLDER_DATA, locale);
            }

            Map<String, Object> data = new LinkedHashMap<>();
            data.put("platform", server);
            data.put("locale", locale);

            return builder.buildCachedAsync(RiftboundContent.class, data);
        } catch (RuntimeException e)
        {
            // what the blocking method would have thrown before making the call (ie. a missing api key)
            return CompletableFuture.failedFuture(e);
        }
    }
}
