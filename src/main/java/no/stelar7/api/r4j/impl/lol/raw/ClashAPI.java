package no.stelar7.api.r4j.impl.lol.raw;

import java.util.concurrent.CompletableFuture;
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
        DataCallBuilder builder = getPlayerInfoByPuuidCall(server, puuid);
        Map<String, Object> data = builder.getCacheKey();
        
        List<ClashPlayer> list = builder.buildCached(data);
        return list != null ? list : Collections.emptyList();
    }

    // the call getPlayerInfoByPuuid and its twin make
    private DataCallBuilder getPlayerInfoByPuuidCall(final LeagueShard server, String puuid)
    {
        DataCallBuilder builder = new DataCallBuilder(context).withEndpoint(URLEndpoint.V1_CLASH_PLAYER_BY_PUUID)
                                                       .withURLParameter(Constants.PUUID_ID_PLACEHOLDER, puuid)
                                                       .withPlatform(server);
        
        Map<String, Object> data = new LinkedHashMap<>();
        data.put("platform", server);
        data.put("puuid", puuid);

        return builder.withCacheKey(data);
    }
    
    /**
     * Returns the team you played in
     */
    @Nullable
    public ClashTeam getTeam(final LeagueShard server, String teamId)
    {
        DataCallBuilder builder = getTeamCall(server, teamId);
        Map<String, Object> data = builder.getCacheKey();
        
        return builder.buildCached(ClashTeam.class, data);
    }

    // the call getTeam and its twin make
    private DataCallBuilder getTeamCall(final LeagueShard server, String teamId)
    {
        DataCallBuilder builder = new DataCallBuilder(context).withEndpoint(URLEndpoint.V1_CLASH_TEAM_BY_ID)
                                                       .withURLParameter(Constants.TEAM_ID_PLACEHOLDER, teamId)
                                                       .withPlatform(server);
        
        Map<String, Object> data = new LinkedHashMap<>();
        data.put("platform", server);
        data.put("teamId", teamId);

        return builder.withCacheKey(data);
    }
    
    /**
     * Returns a list of active tournaments
     */
    public List<ClashTournament> getTournaments(final LeagueShard server)
    {
        DataCallBuilder builder = getTournamentsCall(server);
        Map<String, Object> data = builder.getCacheKey();
        
        List<ClashTournament> list = builder.buildCached(data);
        return list != null ? list : Collections.emptyList();
    }

    // the call getTournaments and its twin make
    private DataCallBuilder getTournamentsCall(final LeagueShard server)
    {
        DataCallBuilder builder = new DataCallBuilder(context).withEndpoint(URLEndpoint.V1_CLASH_TOURNAMENTS)
                                                       .withPlatform(server);
        
        Map<String, Object> data = new LinkedHashMap<>();
        data.put("platform", server);

        return builder.withCacheKey(data);
    }
    
    /**
     * Returns a tournament
     */
    @Nullable
    public ClashTournament getTournamentByTeam(final LeagueShard server, String teamId)
    {
        DataCallBuilder builder = getTournamentByTeamCall(server, teamId);
        Map<String, Object> data = builder.getCacheKey();
        
        return builder.buildCached(ClashTournament.class, data);
    }

    // the call getTournamentByTeam and its twin make
    private DataCallBuilder getTournamentByTeamCall(final LeagueShard server, String teamId)
    {
        DataCallBuilder builder = new DataCallBuilder(context).withEndpoint(URLEndpoint.V1_CLASH_TOURNAMENTS_BY_TEAM)
                                                       .withURLParameter(Constants.TEAM_ID_PLACEHOLDER, teamId)
                                                       .withPlatform(server);
        
        Map<String, Object> data = new LinkedHashMap<>();
        data.put("platform", server);
        data.put("teamId", teamId);

        return builder.withCacheKey(data);
    }
    
    /**
     * Returns a tournament
     */
    @Nullable
    public ClashTournament getTournamentById(final LeagueShard server, int tournamentId)
    {
        DataCallBuilder builder = getTournamentByIdCall(server, tournamentId);
        Map<String, Object> data = builder.getCacheKey();
        
        return builder.buildCached(ClashTournament.class, data);
    }

    // the call getTournamentById and its twin make
    private DataCallBuilder getTournamentByIdCall(final LeagueShard server, int tournamentId)
    {
        DataCallBuilder builder = new DataCallBuilder(context).withEndpoint(URLEndpoint.V1_CLASH_TOURNAMENTS_BY_ID)
                                                       .withURLParameter(Constants.TOURNAMENT_ID_PLACEHOLDER, String.valueOf(tournamentId))
                                                       .withPlatform(server);
        
        Map<String, Object> data = new LinkedHashMap<>();
        data.put("platform", server);
        data.put("tournamentId", tournamentId);

        return builder.withCacheKey(data);
    }
    
    /**
     * Like {@link #getPlayerInfoByPuuid}, without a thread waiting for the answer: the ratelimit, the request and the retries are handled in the background.
     *
     * @return a future of what the blocking method returns
     */
    public CompletableFuture<List<ClashPlayer>> getPlayerInfoByPuuidAsync(final LeagueShard server, String puuid)
    {
        try
        {
            DataCallBuilder builder = getPlayerInfoByPuuidCall(server, puuid);
            Map<String, Object> data = builder.getCacheKey();
    
            return builder.<List<ClashPlayer>>buildCachedAsync(data).thenApply(list -> list != null ? list : Collections.emptyList());
        } catch (RuntimeException e)
        {
            // what the blocking method would have thrown before making the call (ie. a missing api key)
            return CompletableFuture.failedFuture(e);
        }
    }
    
    /**
     * Like {@link #getTeam}, without a thread waiting for the answer: the ratelimit, the request and the retries are handled in the background.
     *
     * @return a future of what the blocking method returns
     */
    public CompletableFuture<ClashTeam> getTeamAsync(final LeagueShard server, String teamId)
    {
        try
        {
            DataCallBuilder builder = getTeamCall(server, teamId);
            Map<String, Object> data = builder.getCacheKey();
    
            return builder.buildCachedAsync(ClashTeam.class, data);
        } catch (RuntimeException e)
        {
            // what the blocking method would have thrown before making the call (ie. a missing api key)
            return CompletableFuture.failedFuture(e);
        }
    }
    
    /**
     * Like {@link #getTournaments}, without a thread waiting for the answer: the ratelimit, the request and the retries are handled in the background.
     *
     * @return a future of what the blocking method returns
     */
    public CompletableFuture<List<ClashTournament>> getTournamentsAsync(final LeagueShard server)
    {
        try
        {
            DataCallBuilder builder = getTournamentsCall(server);
            Map<String, Object> data = builder.getCacheKey();
    
            return builder.<List<ClashTournament>>buildCachedAsync(data).thenApply(list -> list != null ? list : Collections.emptyList());
        } catch (RuntimeException e)
        {
            // what the blocking method would have thrown before making the call (ie. a missing api key)
            return CompletableFuture.failedFuture(e);
        }
    }
    
    /**
     * Like {@link #getTournamentByTeam}, without a thread waiting for the answer: the ratelimit, the request and the retries are handled in the background.
     *
     * @return a future of what the blocking method returns
     */
    public CompletableFuture<ClashTournament> getTournamentByTeamAsync(final LeagueShard server, String teamId)
    {
        try
        {
            DataCallBuilder builder = getTournamentByTeamCall(server, teamId);
            Map<String, Object> data = builder.getCacheKey();
    
            return builder.buildCachedAsync(ClashTournament.class, data);
        } catch (RuntimeException e)
        {
            // what the blocking method would have thrown before making the call (ie. a missing api key)
            return CompletableFuture.failedFuture(e);
        }
    }
    
    /**
     * Like {@link #getTournamentById}, without a thread waiting for the answer: the ratelimit, the request and the retries are handled in the background.
     *
     * @return a future of what the blocking method returns
     */
    public CompletableFuture<ClashTournament> getTournamentByIdAsync(final LeagueShard server, int tournamentId)
    {
        try
        {
            DataCallBuilder builder = getTournamentByIdCall(server, tournamentId);
            Map<String, Object> data = builder.getCacheKey();
    
            return builder.buildCachedAsync(ClashTournament.class, data);
        } catch (RuntimeException e)
        {
            // what the blocking method would have thrown before making the call (ie. a missing api key)
            return CompletableFuture.failedFuture(e);
        }
    }
}
