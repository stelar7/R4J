package no.stelar7.api.r4j.impl.val;

import java.util.concurrent.CompletableFuture;
import org.jetbrains.annotations.Nullable;
import no.stelar7.api.r4j.basic.calling.*;
import no.stelar7.api.r4j.basic.constants.api.*;
import no.stelar7.api.r4j.basic.constants.api.regions.ValorantShard;
import no.stelar7.api.r4j.pojo.val.content.Content;

import java.util.*;

public class VALContentAPI
{
    private static final VALContentAPI INSTANCE = new VALContentAPI(R4JContext.getDefault());
    
    public static VALContentAPI getInstance()
    {
        return VALContentAPI.INSTANCE;
    }
    
    /**
     * @param context the context to take the credentials, cache and limiters from
     * @return the api belonging to the context
     */
    public static VALContentAPI getInstance(R4JContext context)
    {
        return context == null || context == R4JContext.getDefault() ? INSTANCE : context.getApi(VALContentAPI.class, VALContentAPI::new);
    }

    private final R4JContext context;

    private VALContentAPI(R4JContext context)
    {
        this.context = context;
    }
    
    @Nullable
    public Content getContent(ValorantShard server, Optional<String> locale)
    {
        DataCallBuilder builder = getContentCall(server, locale);
        Map<String, Object> data = builder.getCacheKey();
        
        return builder.buildCached(Content.class, data);
    }

    // the call getContent and its twin make
    private DataCallBuilder getContentCall(ValorantShard server, Optional<String> locale)
    {
        DataCallBuilder builder = new DataCallBuilder(context).withURLParameter(Constants.REGION_PLACEHOLDER, server.name())
                                                       .withHeader(Constants.X_RIOT_TOKEN_HEADER_KEY, context.getCredentials().getVALAPIKey())
                                                       .withEndpoint(URLEndpoint.V1_VAL_CONTENT)
                                                       .withPlatform(server);
    
        locale.ifPresent(s -> builder.withQueryParameter(Constants.LOCALE_PLACEHOLDER_DATA, s));
        
        Map<String, Object> data = new LinkedHashMap<>();
        data.put("platform", server);
        data.put("locale", locale.orElse("null"));

        return builder.withCacheKey(data);
    }
    
    /**
     * Like {@link #getContent}, without a thread waiting for the answer: the ratelimit, the request and the retries are handled in the background.
     *
     * @return a future of what the blocking method returns
     */
    public CompletableFuture<Content> getContentAsync(ValorantShard server, Optional<String> locale)
    {
        try
        {
            DataCallBuilder builder = getContentCall(server, locale);
            Map<String, Object> data = builder.getCacheKey();
    
            return builder.buildCachedAsync(Content.class, data);
        } catch (RuntimeException e)
        {
            // what the blocking method would have thrown before making the call (ie. a missing api key)
            return CompletableFuture.failedFuture(e);
        }
    }
}
