package no.stelar7.api.l4j8.impl;

import no.stelar7.api.l4j8.basic.DataCall.DataCallBuilder;
import no.stelar7.api.l4j8.basic.constants.api.*;
import no.stelar7.api.l4j8.pojo.currentgame.CurrentGameInfo;
import no.stelar7.api.l4j8.pojo.featuredgames.FeaturedGames;

import java.util.Optional;

public final class SpectatorAPI
{
    
    private static final SpectatorAPI INSTANCE = new SpectatorAPI();
    
    static SpectatorAPI getInstance()
    {
        return SpectatorAPI.INSTANCE;
    }
    
    private SpectatorAPI()
    {
        // Hide public constructor
    }
    
    
    /**
     * The response object contains the FeaturedGames on the platform.
     *
     * @param server the region to execute against
     * @return Optional FeaturedGames
     */
    public Optional<FeaturedGames> getFeaturedGames(Platform server)
    {
        DataCallBuilder builder = new DataCallBuilder().asVerbose(true)
                                                       .withEndpoint(URLEndpoint.V3_SPECTATOR_FEATURED)
                                                       .withPlatform(server);
        
        return Optional.of((FeaturedGames) builder.build());
        
    }
    
    /**
     * The response object contains the CurrentGame of the summoner.
     *
     * @param server     the region to execute against
     * @param summonerId the summonerId
     * @return Optional FeaturedGames
     */
    public Optional<CurrentGameInfo> getCurrentGame(Platform server, long summonerId)
    {
        DataCallBuilder builder = new DataCallBuilder().asVerbose(true)
                                                       .withURLParameter(Constants.SUMMONER_ID_PLACEHOLDER, String.valueOf(summonerId))
                                                       .withEndpoint(URLEndpoint.V3_SPECTATOR_CURRENT)
                                                       .withPlatform(server);
        
        return Optional.of((CurrentGameInfo) builder.build());
    }
}
