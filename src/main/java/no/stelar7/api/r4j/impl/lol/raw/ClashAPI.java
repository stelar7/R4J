package no.stelar7.api.r4j.impl.lol.raw;

import org.jetbrains.annotations.Nullable;
import no.stelar7.api.r4j.basic.calling.*;
import no.stelar7.api.r4j.basic.constants.api.*;
import no.stelar7.api.r4j.basic.constants.api.regions.LeagueShard;
import no.stelar7.api.r4j.pojo.lol.clash.*;

import java.util.*;

public class ClashAPI
{
    private static final ClashAPI INSTANCE = new ClashAPI(R4JContext.getDefault());
    
    public static ClashAPI getInstance()
    {
        return ClashAPI.INSTANCE;
    }
    
    /**
     * @param context the context to take the credentials, cache and limiters from
     * @return the api belonging to the context
     */
    public static ClashAPI getInstance(R4JContext context)
    {
        return context == null || context == R4JContext.getDefault() ? INSTANCE : context.getApi(ClashAPI.class, ClashAPI::new);
    }

    private final R4JContext context;

    private ClashAPI(R4JContext context)
    {
        this.context = context;
    }
    
    /**
     * Returns a list of player data for all active tournaments you are registered in
     * 
     * @deprecated use {@link #getPlayerInfoByPuuid(LeagueShard, String)} instead
     */
    @Deprecated(since = "3.0.0", forRemoval = true)
    public List<ClashPlayer> getPlayerInfo(final LeagueShard server, String summonerId)
    {
        DataCallBuilder builder = new DataCallBuilder(context).withEndpoint(URLEndpoint.V1_CLASH_PLAYER_BY_SUMMONER)
                                                       .withURLParameter(Constants.SUMMONER_ID_PLACEHOLDER, summonerId)
                                                       .withPlatform(server);
        
        Map<String, Object> data = new LinkedHashMap<>();
        data.put("platform", server);
        data.put("summonerId", summonerId);
        
        List<ClashPlayer> list = builder.buildCached(data);
        return list != null ? list : Collections.emptyList();
    }
    
    /**
     * Returns a list of player data for all active tournaments you are registered in
     */
    public List<ClashPlayer> getPlayerInfoByPuuid(final LeagueShard server, String puuid)
    {
        DataCallBuilder builder = new DataCallBuilder(context).withEndpoint(URLEndpoint.V1_CLASH_PLAYER_BY_PUUID)
                                                       .withURLParameter(Constants.PUUID_ID_PLACEHOLDER, puuid)
                                                       .withPlatform(server);
        
        Map<String, Object> data = new LinkedHashMap<>();
        data.put("platform", server);
        data.put("puuid", puuid);
        
        List<ClashPlayer> list = builder.buildCached(data);
        return list != null ? list : Collections.emptyList();
    }
    
    /**
     * Returns the team you played in
     */
    @Nullable
    public ClashTeam getTeam(final LeagueShard server, String teamId)
    {
        DataCallBuilder builder = new DataCallBuilder(context).withEndpoint(URLEndpoint.V1_CLASH_TEAM_BY_ID)
                                                       .withURLParameter(Constants.TEAM_ID_PLACEHOLDER, teamId)
                                                       .withPlatform(server);
        
        Map<String, Object> data = new LinkedHashMap<>();
        data.put("platform", server);
        data.put("teamId", teamId);
        
        return builder.buildCached(ClashTeam.class, data);
    }
    
    /**
     * Returns a list of active tournaments
     */
    public List<ClashTournament> getTournaments(final LeagueShard server)
    {
        DataCallBuilder builder = new DataCallBuilder(context).withEndpoint(URLEndpoint.V1_CLASH_TOURNAMENTS)
                                                       .withPlatform(server);
        
        Map<String, Object> data = new LinkedHashMap<>();
        data.put("platform", server);
        
        List<ClashTournament> list = builder.buildCached(data);
        return list != null ? list : Collections.emptyList();
    }
    
    /**
     * Returns a tournament
     */
    @Nullable
    public ClashTournament getTournamentByTeam(final LeagueShard server, String teamId)
    {
        DataCallBuilder builder = new DataCallBuilder(context).withEndpoint(URLEndpoint.V1_CLASH_TOURNAMENTS_BY_TEAM)
                                                       .withURLParameter(Constants.TEAM_ID_PLACEHOLDER, teamId)
                                                       .withPlatform(server);
        
        Map<String, Object> data = new LinkedHashMap<>();
        data.put("platform", server);
        data.put("teamId", teamId);
        
        return builder.buildCached(ClashTournament.class, data);
    }
    
    /**
     * Returns a tournament
     */
    @Nullable
    public ClashTournament getTournamentById(final LeagueShard server, int tournamentId)
    {
        DataCallBuilder builder = new DataCallBuilder(context).withEndpoint(URLEndpoint.V1_CLASH_TOURNAMENTS_BY_ID)
                                                       .withURLParameter(Constants.TOURNAMENT_ID_PLACEHOLDER, String.valueOf(tournamentId))
                                                       .withPlatform(server);
        
        Map<String, Object> data = new LinkedHashMap<>();
        data.put("platform", server);
        data.put("tournamentId", tournamentId);
        
        return builder.buildCached(ClashTournament.class, data);
    }
    
}
