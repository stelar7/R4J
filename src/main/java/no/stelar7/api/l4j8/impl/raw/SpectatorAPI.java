package no.stelar7.api.l4j8.impl.raw;

import no.stelar7.api.l4j8.basic.calling.*;
import no.stelar7.api.l4j8.basic.constants.api.*;
import no.stelar7.api.l4j8.pojo.spectator.*;

import java.util.*;

@SuppressWarnings("unchecked")
public final class SpectatorAPI
{
    
    private static final SpectatorAPI INSTANCE = new SpectatorAPI();
    
    public static SpectatorAPI getInstance()
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
    public List<SpectatorGameInfo> getFeaturedGames(Platform server)
    {
        DataCallBuilder builder = new DataCallBuilder().withEndpoint(URLEndpoint.V3_SPECTATOR_FEATURED)
                                                       .withPlatform(server);
        
        Optional chl = DataCall.getCacheProvider().get(URLEndpoint.V3_SPECTATOR_FEATURED, server);
        if (chl.isPresent())
        {
            return (List<SpectatorGameInfo>) chl.get();
        }
        
        try
        {
            FeaturedGames fg = (FeaturedGames) builder.build();
            DataCall.getCacheProvider().store(URLEndpoint.V3_SPECTATOR_FEATURED, fg.getGameList(), server);
            return fg.getGameList();
        } catch (ClassCastException e)
        {
            
            return null;
        }
    }
    
    /**
     * The response object contains the CurrentGame of the summoner.
     * <p>
     * returns null if not in game
     *
     * @param server     the region to execute against
     * @param summonerId the summonerId
     * @return SpectatorGameInfo
     */
    public SpectatorGameInfo getCurrentGame(Platform server, long summonerId)
    {
        DataCallBuilder builder = new DataCallBuilder().withURLParameter(Constants.SUMMONER_ID_PLACEHOLDER, String.valueOf(summonerId))
                                                       .withEndpoint(URLEndpoint.V3_SPECTATOR_CURRENT)
                                                       .withPlatform(server);
        
        
        Optional chl = DataCall.getCacheProvider().get(URLEndpoint.V3_SPECTATOR_CURRENT, server);
        if (chl.isPresent())
        {
            return (SpectatorGameInfo) chl.get();
        }
        
        
        try
        {
            SpectatorGameInfo fg = (SpectatorGameInfo) builder.build();
            DataCall.getCacheProvider().store(URLEndpoint.V3_SPECTATOR_CURRENT, fg, server);
            return fg;
        } catch (ClassCastException e)
        {
            return null;
        }
    }
}
