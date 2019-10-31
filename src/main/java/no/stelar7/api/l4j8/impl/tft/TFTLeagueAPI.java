package no.stelar7.api.l4j8.impl.tft;

import no.stelar7.api.l4j8.basic.calling.*;
import no.stelar7.api.l4j8.basic.constants.api.*;
import no.stelar7.api.l4j8.basic.constants.types.*;
import no.stelar7.api.l4j8.basic.utils.*;
import no.stelar7.api.l4j8.pojo.league.*;

import java.util.*;

public class TFTLeagueAPI
{
    private static final TFTLeagueAPI INSTANCE = new TFTLeagueAPI();
    
    public static TFTLeagueAPI getInstance()
    {
        return TFTLeagueAPI.INSTANCE;
    }
    
    private TFTLeagueAPI()
    {
        // Hide public constructor
    }
    
    /**
     * Get master tier leagues.
     * Valid queues are: RANKED_SOLO_5x5, RANKED_FLEX_SR, RANKED_FLEX_TT
     *
     * @param server region to get data from
     * @return LeagueList
     */
    private LeagueList getMasterLeague(Platform server)
    {
        
        DataCallBuilder builder = new DataCallBuilder().withURLParameter(Constants.REGION_PLACEHOLDER, server.name())
                                                       .withEndpoint(URLEndpoint.V1_TFT_LEAGUE_MASTER)
                                                       .withPlatform(server);
        
        Optional chl = DataCall.getCacheProvider().get(URLEndpoint.V1_TFT_LEAGUE_MASTER, server);
        if (chl.isPresent())
        {
            return (LeagueList) chl.get();
        }
        
        try
        {
            LeagueList list = (LeagueList) builder.build();
            DataCall.getCacheProvider().store(URLEndpoint.V1_TFT_LEAGUE_MASTER, list, server);
            return list;
        } catch (ClassCastException e)
        {
            
            return null;
        }
    }
    
    /**
     * Get grandmaster tier leagues.
     * Valid queues are: RANKED_SOLO_5x5, RANKED_FLEX_SR, RANKED_FLEX_TT
     *
     * @param server region to get data from
     * @return LeagueList
     */
    private LeagueList getGrandmasterLeague(Platform server)
    {
        
        DataCallBuilder builder = new DataCallBuilder().withURLParameter(Constants.REGION_PLACEHOLDER, server.name())
                                                       .withEndpoint(URLEndpoint.V1_TFT_LEAGUE_GRANDMASTER)
                                                       .withPlatform(server);
        
        Optional chl = DataCall.getCacheProvider().get(URLEndpoint.V1_TFT_LEAGUE_GRANDMASTER, server);
        if (chl.isPresent())
        {
            return (LeagueList) chl.get();
        }
        
        try
        {
            LeagueList list = (LeagueList) builder.build();
            DataCall.getCacheProvider().store(URLEndpoint.V1_TFT_LEAGUE_GRANDMASTER, list, server);
            return list;
        } catch (ClassCastException e)
        {
            
            return null;
        }
    }
    
    
    /**
     * Get challenger tier leagues.
     * Valid queues are: RANKED_SOLO_5x5, RANKED_FLEX_SR, RANKED_FLEX_TT
     *
     * @param server region to get data from
     * @return LeagueList
     */
    private LeagueList getChallengerLeague(Platform server)
    {
        DataCallBuilder builder = new DataCallBuilder().withURLParameter(Constants.REGION_PLACEHOLDER, server.name())
                                                       .withEndpoint(URLEndpoint.V1_TFT_LEAGUE_CHALLENGER)
                                                       .withPlatform(server);
        
        
        Optional chl = DataCall.getCacheProvider().get(URLEndpoint.V1_TFT_LEAGUE_CHALLENGER, server);
        if (chl.isPresent())
        {
            return (LeagueList) chl.get();
        }
        
        try
        {
            LeagueList list = (LeagueList) builder.build();
            DataCall.getCacheProvider().store(URLEndpoint.V1_TFT_LEAGUE_CHALLENGER, list, server);
            return list;
        } catch (ClassCastException e)
        {
            
            return null;
        }
    }
    
    
    /**
     * Get league from its ID.
     *
     * @param server   region to get data from
     * @param leagueId league to get data for
     * @return LeagueList
     */
    public LeagueList getLeague(Platform server, String leagueId)
    {
        DataCallBuilder builder = new DataCallBuilder().withURLParameter(Constants.REGION_PLACEHOLDER, server.name())
                                                       .withURLParameter(Constants.LEAGUE_ID_PLACEHOLDER, String.valueOf(leagueId))
                                                       .withEndpoint(URLEndpoint.V1_TFT_LEAGUE)
                                                       .withPlatform(server);
        
        Optional chl = DataCall.getCacheProvider().get(URLEndpoint.V1_TFT_LEAGUE, server, leagueId);
        if (chl.isPresent())
        {
            return (LeagueList) chl.get();
        }
        
        try
        {
            LeagueList list = (LeagueList) builder.build();
            DataCall.getCacheProvider().store(URLEndpoint.V1_TFT_LEAGUE, list, server, leagueId);
            return list;
        } catch (ClassCastException e)
        {
            
            return null;
        }
    }
    
    /**
     * Get league entries for a summonerId
     * Empty if unranked
     *
     * @param server     region to get data from
     * @param summonerId summoner to get data for
     * @return List of LeagueEntry
     */
    public List<LeagueEntry> getLeagueEntries(Platform server, String summonerId)
    {
        DataCallBuilder builder = new DataCallBuilder().withURLParameter(Constants.REGION_PLACEHOLDER, server.name())
                                                       .withURLParameter(Constants.SUMMONER_ID_PLACEHOLDER, summonerId)
                                                       .withEndpoint(URLEndpoint.V1_TFT_LEAGUE_ENTRY)
                                                       .withPlatform(server);
        
        Optional chl = DataCall.getCacheProvider().get(URLEndpoint.V1_TFT_LEAGUE_ENTRY, server, summonerId);
        if (chl.isPresent())
        {
            return (List<LeagueEntry>) chl.get();
        }
        
        try
        {
            Object data = builder.build();
            if (data instanceof Pair)
            {
                return Collections.emptyList();
            }
            
            List<LeagueEntry> list = (List<LeagueEntry>) data;
            DataCall.getCacheProvider().store(URLEndpoint.V1_TFT_LEAGUE_ENTRY, list, server, summonerId);
            return list;
        } catch (ClassCastException e)
        {
            return Collections.emptyList();
        }
    }
    
    /**
     * @param server  the region to query
     * @param tierdiv the tier and division to query
     * @param page    the page to query (first page is 1)
     * @return LeagueList
     */
    public List<? extends LeagueItem> getLeagueByTierDivision(Platform server, TierDivisionType tierdiv, int page)
    {
        DataCallBuilder builder = new DataCallBuilder().withURLParameter(Constants.REGION_PLACEHOLDER, server.name())
                                                       .withURLParameter(Constants.TIER_PLACEHOLDER, tierdiv.getTier())
                                                       .withURLParameter(Constants.DIVISION_PLACEHOLDER, tierdiv.getDivision())
                                                       .withURLData(Constants.PAGE_PLACEHOLDER_DATA, String.valueOf(page))
                                                       .withEndpoint(URLEndpoint.V1_TFT_LEAGUE_RANK)
                                                       .withPlatform(server);
        
        if (Arrays.asList(TierDivisionType.MASTER_I, TierDivisionType.GRANDMASTER_I, TierDivisionType.CHALLENGER_I).contains(tierdiv))
        {
            if (page > 1)
            {
                return Collections.emptyList();
            }
            
            switch (tierdiv)
            {
                case MASTER_I:
                    return getMasterLeague(server).getEntries();
                case GRANDMASTER_I:
                    return getGrandmasterLeague(server).getEntries();
                case CHALLENGER_I:
                    return getChallengerLeague(server).getEntries();
            }
        }
        
        
        Optional chl = DataCall.getCacheProvider().get(URLEndpoint.V1_TFT_LEAGUE_RANK, server, tierdiv, page);
        if (chl.isPresent())
        {
            return (List<LeagueEntry>) chl.get();
        }
        
        try
        {
            Object data = builder.build();
            if (data instanceof Pair)
            {
                return Collections.emptyList();
            }
            
            List<LeagueEntry> list = (List<LeagueEntry>) data;
            DataCall.getCacheProvider().store(URLEndpoint.V1_TFT_LEAGUE_RANK, list, server, tierdiv, page);
            return list;
        } catch (ClassCastException e)
        {
            return Collections.emptyList();
        }
    }
    
    /**
     * @param server  the region to query
     * @param tierdiv the tier and division to query
     * @return LeagueList
     */
    public LazyList<? extends LeagueItem> getLeagueByTierDivisionLazy(Platform server, TierDivisionType tierdiv)
    {
        return new LazyList<>(1, prevValue -> getLeagueByTierDivision(server, tierdiv, prevValue + 1));
    }
    
    
}
