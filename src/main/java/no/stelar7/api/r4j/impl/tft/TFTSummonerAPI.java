package no.stelar7.api.r4j.impl.tft;

import org.jetbrains.annotations.Nullable;
import no.stelar7.api.r4j.basic.calling.*;
import no.stelar7.api.r4j.basic.constants.api.*;
import no.stelar7.api.r4j.basic.constants.api.regions.LeagueShard;
import no.stelar7.api.r4j.pojo.lol.summoner.Summoner;

import java.util.*;

public class TFTSummonerAPI
{
    
    
    private static final TFTSummonerAPI INSTANCE = new TFTSummonerAPI(R4JContext.getDefault());
    
    public static TFTSummonerAPI getInstance()
    {
        return TFTSummonerAPI.INSTANCE;
    }
    
    /**
     * @param context the context to take the credentials, cache and limiters from
     * @return the api belonging to the context
     */
    public static TFTSummonerAPI getInstance(R4JContext context)
    {
        return context == null || context == R4JContext.getDefault() ? INSTANCE : context.getApi(TFTSummonerAPI.class, TFTSummonerAPI::new);
    }

    private final R4JContext context;

    private TFTSummonerAPI(R4JContext context)
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
        DataCallBuilder builder = new DataCallBuilder(context)
                .withHeader(Constants.X_RIOT_TOKEN_HEADER_KEY, context.getCredentials().getTFTAPIKey())
                .withURLParameter(Constants.PUUID_ID_PLACEHOLDER, PUUID)
                .withEndpoint(URLEndpoint.V1_TFT_SUMMONER_BY_PUUID)
                .withPlatform(server);
        
        Map<String, Object> data = new LinkedHashMap<>();
        data.put("platform", server);
        data.put("puuid", PUUID);
        
        return builder.buildCached(Summoner.class, data);
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
        return new DataCallBuilder(context).withHeader(Constants.X_RIOT_TOKEN_HEADER_KEY, context.getCredentials().getTFTAPIKey())
                                    .withAccessToken(accessToken)
                                    .withEndpoint(URLEndpoint.V1_TFT_SUMMONER_ME)
                                    .withPlatform(server)
                                    .buildAs(Summoner.class);
    }
}
