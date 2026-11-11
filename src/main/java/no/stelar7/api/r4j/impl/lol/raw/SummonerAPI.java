package no.stelar7.api.r4j.impl.lol.raw;

import java.util.concurrent.CompletableFuture;
import org.jetbrains.annotations.Nullable;
import no.stelar7.api.r4j.basic.calling.R4JContext;
import no.stelar7.api.r4j.basic.constants.api.regions.LeagueShard;
import no.stelar7.api.r4j.impl.lol.builders.summoner.SummonerBuilder;
import no.stelar7.api.r4j.pojo.lol.summoner.Summoner;

public final class SummonerAPI
{
    private static final SummonerAPI INSTANCE = new SummonerAPI(R4JContext.getDefault());

    public static SummonerAPI getInstance()
    {
        return SummonerAPI.INSTANCE;
    }

    /**
     * @param context the context to take the credentials, cache and limiters from
     * @return the api belonging to the context
     */
    public static SummonerAPI getInstance(R4JContext context)
    {
        return context == null || context == R4JContext.getDefault() ? INSTANCE : context.getApi(SummonerAPI.class, SummonerAPI::new);
    }

    private final R4JContext context;

    private SummonerAPI(R4JContext context)
    {
        this.context = context;
    }
    
    /**
     * The response object contains the summoner objects mapped by their username.
     *
     * @param server the region to execute against
     * @param PUUID  puuid associated with summoner to retrieve.
     * @return Optional Summoner
     */
    @Nullable
    public Summoner getSummonerByPUUID(final LeagueShard server, String PUUID)
    {
        return new SummonerBuilder().withContext(context).withPlatform(server).withPUUID(PUUID).get();
    }
    
    /**
     * The summoner of a player that signed in with RSO
     *
     * @param server      the region to execute against
     * @param accessToken the access token of the player
     * @return the summoner, or null if it was not found
     */
    @Nullable
    public Summoner getSummonerByAccessToken(final LeagueShard server, String accessToken)
    {
        // not cached, as the result depends on who the token belongs to
        return new no.stelar7.api.r4j.basic.calling.DataCallBuilder(context).withAccessToken(accessToken)
                                                                      .withEndpoint(no.stelar7.api.r4j.basic.constants.api.URLEndpoint.V4_SUMMONER_ME)
                                                                      .withPlatform(server)
                                                                      .buildAs(Summoner.class);
    }
    
    /**
     * Runs {@link #getSummonerByPUUID} on a background thread.
     *
     * @return a future of what the blocking method returns
     */
    public CompletableFuture<Summoner> getSummonerByPUUIDAsync(final LeagueShard server, String PUUID)
    {
        return context.callAsync(() -> getSummonerByPUUID(server, PUUID));
    }
    
    /**
     * Like {@link #getSummonerByAccessToken}, without a thread waiting for the answer: the ratelimit, the request and the retries are handled in the background.
     *
     * @return a future of what the blocking method returns
     */
    public CompletableFuture<Summoner> getSummonerByAccessTokenAsync(final LeagueShard server, String accessToken)
    {
        try
        {
            // not cached, as the result depends on who the token belongs to
            return new no.stelar7.api.r4j.basic.calling.DataCallBuilder(context).withAccessToken(accessToken)
                                                                          .withEndpoint(no.stelar7.api.r4j.basic.constants.api.URLEndpoint.V4_SUMMONER_ME)
                                                                          .withPlatform(server)
                                                                          .buildAsAsync(Summoner.class);
        } catch (RuntimeException e)
        {
            // what the blocking method would have thrown before making the call (ie. a missing api key)
            return CompletableFuture.failedFuture(e);
        }
    }
}
