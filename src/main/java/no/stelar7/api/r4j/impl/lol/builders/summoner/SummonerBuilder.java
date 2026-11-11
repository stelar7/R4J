package no.stelar7.api.r4j.impl.lol.builders.summoner;

import java.util.concurrent.CompletableFuture;
import org.jetbrains.annotations.Nullable;
import no.stelar7.api.r4j.basic.calling.*;
import no.stelar7.api.r4j.basic.constants.api.*;
import no.stelar7.api.r4j.basic.constants.api.regions.LeagueShard;
import no.stelar7.api.r4j.basic.utils.Pair;
import no.stelar7.api.r4j.pojo.lol.summoner.Summoner;

import java.util.*;

public class SummonerBuilder
{
    private R4JContext context = R4JContext.getDefault();

    /**
     * Uses the credentials, cache and limiters of the context instead of the default ones
     *
     * @param context the context to use
     * @return this
     */
    public SummonerBuilder withContext(R4JContext context)
    {
        this.context = context == null ? R4JContext.getDefault() : context;
        return this;
    }


    private String      puuid;
    private LeagueShard platform;

    public SummonerBuilder()
    {
    }

    private SummonerBuilder(String puuid, LeagueShard platform)
    {
        this.puuid = puuid;
        this.platform = platform;
    }

    public SummonerBuilder withPUUID(String puuid)
    {
        return new SummonerBuilder(puuid, this.platform).withContext(this.context);
    }

    public SummonerBuilder withPlatform(LeagueShard platform)
    {
        return new SummonerBuilder(this.puuid, platform).withContext(this.context);
    }

    public SummonerBuilder setPUUID(String puuid)
    {
        this.puuid = puuid;
        return this;
    }

    public SummonerBuilder setPlatform(LeagueShard platform)
    {
        this.platform = platform;
        return this;
    }


    /**
     * gets a summoner based on the parameters passed to the builder
     * <p>
     * If variables are set using a setXXX method, they are parsed with name having the highest priority, and accountid the lowest. as shown in the "list below"
     * name > id > puuid > accountid
     *
     * @return Summoner
     */
    @Nullable
    public Summoner get()
    {
        DataCallBuilder builder  = new DataCallBuilder(context).withPlatform(this.platform);
        URLEndpoint     endpoint = null;


        Map<String, Object> data = new LinkedHashMap<>();
        data.put("platform", platform);

        builder.withURLParameter(Constants.PUUID_ID_PLACEHOLDER, this.puuid);
        endpoint = URLEndpoint.V4_SUMMONER_BY_PUUID;
        data.put("puuid", this.puuid);

        builder.withEndpoint(endpoint);

        return builder.buildCached(Summoner.class, data);
    }
    
    /**
     * Like {@link #get}, without a thread waiting for the answer: the ratelimit, the request and the retries are handled in the background.
     *
     * @return a future of what the blocking method returns
     */
    public CompletableFuture<Summoner> getAsync()
    {
        try
        {
            DataCallBuilder builder = new DataCallBuilder(context).withPlatform(this.platform)
                                                           .withURLParameter(Constants.PUUID_ID_PLACEHOLDER, this.puuid)
                                                           .withEndpoint(URLEndpoint.V4_SUMMONER_BY_PUUID);

            Map<String, Object> data = new LinkedHashMap<>();
            data.put("platform", platform);
            data.put("puuid", this.puuid);

            return builder.buildCachedAsync(Summoner.class, data);
        } catch (RuntimeException e)
        {
            // what the blocking method would have thrown before making the call (ie. a missing api key)
            return CompletableFuture.failedFuture(e);
        }
    }
}
