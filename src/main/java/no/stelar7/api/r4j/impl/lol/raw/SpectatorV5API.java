package no.stelar7.api.r4j.impl.lol.raw;

import no.stelar7.api.r4j.basic.calling.*;
import no.stelar7.api.r4j.basic.constants.api.*;
import no.stelar7.api.r4j.basic.constants.api.regions.LeagueShard;
import no.stelar7.api.r4j.basic.utils.Pair;
import no.stelar7.api.r4j.pojo.lol.spectator.*;
import java.util.*;

public final class SpectatorV5API
{
    
    private static final SpectatorV5API INSTANCE = new SpectatorV5API();
    
    public static SpectatorV5API getInstance()
    {
        return SpectatorV5API.INSTANCE;
    }
    
    private SpectatorV5API()
    {
        // Hide public constructor
    }
    
    
    /**
     * The response object contains the FeaturedGames on the platform.
     *
     * @param server the region to execute against
     * @return Optional FeaturedGames
     */
    public List<SpectatorGameInfo> getFeaturedGames(LeagueShard server)
    {
        DataCallBuilder builder = new DataCallBuilder().withEndpoint(URLEndpoint.V5_SPECTATOR_FEATURED)
                                                       .withPlatform(server);
        
        Map<String, Object> data = new HashMap<>();
        data.put("platform", server);
        
        Optional<?> chl = DataCall.getCacheProvider().get(URLEndpoint.V5_SPECTATOR_FEATURED, data);
        if (chl.isPresent())
        {
            return ((FeaturedGames) chl.get()).getGameList();
        }
        
        try
        {
            Object ret = builder.build();
            if (ret instanceof Pair)
            {
                return Collections.emptyList();
            }
            
            FeaturedGames fg = (FeaturedGames) data;
            
            data.put("value", fg);
            DataCall.getCacheProvider().store(URLEndpoint.V5_SPECTATOR_FEATURED, data);
            
            return fg.getGameList();
        } catch (ClassCastException e)
        {
            return Collections.emptyList();
        }
    }
    
    /**
     * The response object contains the CurrentGame of the summoner.
     * <p>
     * returns null if not in game
     *
     * @param server the region to execute against
     * @param puuid  the puuid
     * @return SpectatorGameInfo
     */
    public SpectatorGameInfo getCurrentGame(LeagueShard server, String puuid)
    {
        DataCallBuilder builder = new DataCallBuilder().withURLParameter(Constants.PUUID_ID_PLACEHOLDER, puuid)
                                                       .withEndpoint(URLEndpoint.V5_SPECTATOR_CURRENT)
                                                       .withPlatform(server);
        
        Map<String, Object> data = new LinkedHashMap<>();
        data.put("platform", server);
        data.put("summoner", puuid);
        
        Optional<?> chl = DataCall.getCacheProvider().get(URLEndpoint.V5_SPECTATOR_CURRENT, data);
        if (chl.isPresent())
        {
            return (SpectatorGameInfo) chl.get();
        }
        
        try
        {
          SpectatorGameInfo fg = (SpectatorGameInfo) builder.build();
            
            data.put("value", fg);
            DataCall.getCacheProvider().store(URLEndpoint.V5_SPECTATOR_CURRENT, data);
            
            return fg;
        } catch (ClassCastException e)
        {
            return null;
        }
    }
}
