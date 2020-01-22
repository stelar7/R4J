package no.stelar7.api.r4j.impl.tft;

import no.stelar7.api.r4j.basic.calling.*;
import no.stelar7.api.r4j.basic.constants.api.*;
import no.stelar7.api.r4j.basic.constants.types.TierDivisionType;
import no.stelar7.api.r4j.basic.utils.*;
import no.stelar7.api.r4j.pojo.lol.league.*;

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
                                                       .withHeader(Constants.X_RIOT_TOKEN_HEADER_KEY, DataCall.getCredentials().getTFTAPIKey())
                                                       .withEndpoint(URLEndpoint.V1_TFT_LEAGUE_MASTER)
                                                       .withPlatform(server);
        
        Map<String, Object> data = new TreeMap<>();
        data.put("platform", server);
        
        Optional<?> chl = DataCall.getCacheProvider().get(URLEndpoint.V1_TFT_LEAGUE_MASTER, data);
        if (chl.isPresent())
        {
            return (LeagueList) chl.get();
        }
        
        try
        {
            LeagueList list = (LeagueList) builder.build();
            
            data.put("value", list);
            DataCall.getCacheProvider().store(URLEndpoint.V1_TFT_LEAGUE_MASTER, data);
            
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
                                                       .withHeader(Constants.X_RIOT_TOKEN_HEADER_KEY, DataCall.getCredentials().getTFTAPIKey())
                                                       .withEndpoint(URLEndpoint.V1_TFT_LEAGUE_GRANDMASTER)
                                                       .withPlatform(server);
        Map<String, Object> data = new TreeMap<>();
        data.put("platform", server);
        
        Optional<?> chl = DataCall.getCacheProvider().get(URLEndpoint.V1_TFT_LEAGUE_GRANDMASTER, data);
        if (chl.isPresent())
        {
            return (LeagueList) chl.get();
        }
        
        try
        {
            LeagueList list = (LeagueList) builder.build();
            
            data.put("value", list);
            DataCall.getCacheProvider().store(URLEndpoint.V1_TFT_LEAGUE_GRANDMASTER, data);
            
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
                                                       .withHeader(Constants.X_RIOT_TOKEN_HEADER_KEY, DataCall.getCredentials().getTFTAPIKey())
                                                       .withEndpoint(URLEndpoint.V1_TFT_LEAGUE_CHALLENGER)
                                                       .withPlatform(server);
        
        Map<String, Object> data = new TreeMap<>();
        data.put("platform", server);
        
        Optional<?> chl = DataCall.getCacheProvider().get(URLEndpoint.V1_TFT_LEAGUE_CHALLENGER, data);
        if (chl.isPresent())
        {
            return (LeagueList) chl.get();
        }
        
        try
        {
            LeagueList list = (LeagueList) builder.build();
            
            data.put("value", list);
            DataCall.getCacheProvider().store(URLEndpoint.V1_TFT_LEAGUE_CHALLENGER, data);
            
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
                                                       .withHeader(Constants.X_RIOT_TOKEN_HEADER_KEY, DataCall.getCredentials().getTFTAPIKey())
                                                       .withURLParameter(Constants.LEAGUE_ID_PLACEHOLDER, String.valueOf(leagueId))
                                                       .withEndpoint(URLEndpoint.V1_TFT_LEAGUE)
                                                       .withPlatform(server);
        
        Map<String, Object> data = new TreeMap<>();
        data.put("platform", server);
        data.put("leagueid", leagueId);
        
        Optional<?> chl = DataCall.getCacheProvider().get(URLEndpoint.V1_TFT_LEAGUE, data);
        if (chl.isPresent())
        {
            return (LeagueList) chl.get();
        }
        
        try
        {
            LeagueList list = (LeagueList) builder.build();
            
            data.put("value", list);
            DataCall.getCacheProvider().store(URLEndpoint.V1_TFT_LEAGUE, data);
            
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
                                                       .withHeader(Constants.X_RIOT_TOKEN_HEADER_KEY, DataCall.getCredentials().getTFTAPIKey())
                                                       .withURLParameter(Constants.SUMMONER_ID_PLACEHOLDER, summonerId)
                                                       .withEndpoint(URLEndpoint.V1_TFT_LEAGUE_ENTRY)
                                                       .withPlatform(server);
        
        Map<String, Object> data = new TreeMap<>();
        data.put("platform", server);
        data.put("id", summonerId);
        
        Optional<?> chl = DataCall.getCacheProvider().get(URLEndpoint.V1_TFT_LEAGUE_ENTRY, data);
        if (chl.isPresent())
        {
            return (List<LeagueEntry>) chl.get();
        }
        
        try
        {
            Object ret = builder.build();
            if (ret instanceof Pair)
            {
                return Collections.emptyList();
            }
            
            List<LeagueEntry> list = (List<LeagueEntry>) ret;
            
            data.put("value", list);
            DataCall.getCacheProvider().store(URLEndpoint.V1_TFT_LEAGUE_ENTRY, data);
            
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
    public List<LeagueEntry> getLeagueByTierDivision(Platform server, TierDivisionType tierdiv, int page)
    {
        DataCallBuilder builder = new DataCallBuilder().withURLParameter(Constants.REGION_PLACEHOLDER, server.name())
                                                       .withURLParameter(Constants.TIER_PLACEHOLDER, tierdiv.getTier())
                                                       .withURLParameter(Constants.DIVISION_PLACEHOLDER, tierdiv.getDivision())
                                                       .withHeader(Constants.X_RIOT_TOKEN_HEADER_KEY, DataCall.getCredentials().getTFTAPIKey())
                                                       .withQueryParameter(Constants.PAGE_PLACEHOLDER_DATA, String.valueOf(page))
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
        
        Map<String, Object> data = new TreeMap<>();
        data.put("platform", server);
        data.put("tierdiv", tierdiv);
        data.put("page", page);
        
        Optional<?> chl = DataCall.getCacheProvider().get(URLEndpoint.V1_TFT_LEAGUE_RANK, data);
        if (chl.isPresent())
        {
            return (List<LeagueEntry>) chl.get();
        }
        
        try
        {
            Object ret = builder.build();
            if (ret instanceof Pair)
            {
                return Collections.emptyList();
            }
            
            List<LeagueEntry> list = (List<LeagueEntry>) ret;
            
            data.put("value", list);
            DataCall.getCacheProvider().store(URLEndpoint.V1_TFT_LEAGUE_RANK, data);
            
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
    public LazyList<LeagueEntry> getLeagueByTierDivisionLazy(Platform server, TierDivisionType tierdiv)
    {
        return new LazyList<>(1, prevValue -> getLeagueByTierDivision(server, tierdiv, prevValue + 1));
    }
    
    
}
