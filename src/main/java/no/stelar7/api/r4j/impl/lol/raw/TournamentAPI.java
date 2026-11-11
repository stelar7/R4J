package no.stelar7.api.r4j.impl.lol.raw;

import java.util.concurrent.CompletableFuture;
import org.jetbrains.annotations.Nullable;
import no.stelar7.api.r4j.basic.calling.*;
import no.stelar7.api.r4j.basic.constants.api.*;
import no.stelar7.api.r4j.basic.constants.api.regions.RegionShard;
import no.stelar7.api.r4j.basic.utils.Utils;
import no.stelar7.api.r4j.pojo.lol.tournament.*;

import java.util.*;

@SuppressWarnings("unchecked")
public final class TournamentAPI
{
    
    private static final TournamentAPI INSTANCE      = new TournamentAPI(R4JContext.getDefault(), false);
    private static final TournamentAPI INSTANCE_STUB = new TournamentAPI(R4JContext.getDefault(), true);

    private final R4JContext context;
    private       boolean    useStub;

    public void useStub(boolean useStub)
    {
        this.useStub = useStub;
    }

    public static TournamentAPI getInstance(boolean useStub)
    {
        return useStub ? INSTANCE_STUB : INSTANCE;
    }

    /**
     * @param context the context to take the credentials and limiters from
     * @param useStub if true, returns the -stub instance
     * @return the api belonging to the context
     */
    public static TournamentAPI getInstance(R4JContext context, boolean useStub)
    {
        if (context == null || context == R4JContext.getDefault())
        {
            return getInstance(useStub);
        }

        // the two variants are separate objects, like the default ones
        return useStub ? context.getApi(StubHolder.class, c -> new StubHolder(new TournamentAPI(c, true))).api
                       : context.getApi(TournamentAPI.class, c -> new TournamentAPI(c, false));
    }

    private static final class StubHolder
    {
        private final TournamentAPI api;

        private StubHolder(TournamentAPI api)
        {
            this.api = api;
        }
    }

    private TournamentAPI(R4JContext context, boolean useStub)
    {
        this.context = context;
        this.useStub = useStub;
    }


    public boolean isStub()
    {
        return useStub;
    }
    
    /**
     * Generates a list of tournamentCodes to use for adding players to games.
     * You should only use a code per match.
     *
     * @param count        the amount of codes to generate (default: 1)
     * @param params       the TournamentCodeParameters for the games played with this code
     * @param tournamentId the tournamentId this game is played on
     * @return a list of tournamentcodes
     */
    @Nullable
    public List<String> generateTournamentCodes(final TournamentCodeParameters params, final long tournamentId, Integer count)
    {
        DataCallBuilder builder = new DataCallBuilder(context).withHeader(Constants.X_RIOT_TOKEN_HEADER_KEY, context.getCredentials().getTournamentAPIKey())
                                                       .withQueryParameter(Constants.URL_PARAM_TOURNAMENT_COUNT, String.valueOf(count != null ? count : 1))
                                                       .withQueryParameter(Constants.URL_PARAM_TOURNAMENT_ID, String.valueOf(tournamentId))
                                                       .withEndpoint(URLEndpoint.V5_TOURNAMENT_CODES)
                                                       .withPostData(Utils.getGson().toJson(params))
                                                       .withRequestMethod(Constants.METHOD_POST)
                                                       .withPlatform(RegionShard.AMERICAS);
        
        if (useStub)
        {
            builder.withEndpoint(URLEndpoint.V5_TOURNAMENT_STUB_CODES);
        }
        
        try
        {
            return (List<String>) builder.build();
        } catch (ClassCastException e)
        {
            
            return null;
        }
    }
    
    /**
     * Get info about a specified tournament code
     *
     * @param tournamentCode The tournament code of the match
     * @return TournamentCode
     */
    @Nullable
    public TournamentCode getTournamentInfo(final String tournamentCode)
    {
        DataCallBuilder builder = new DataCallBuilder(context).withHeader(Constants.X_RIOT_TOKEN_HEADER_KEY, context.getCredentials().getTournamentAPIKey())
                                                       .withURLParameter(Constants.TOURNAMENT_CODE_PLACEHOLDER, tournamentCode)
                                                       .withEndpoint(useStub ? URLEndpoint.V5_TOURNAMENT_STUB_CODES_BY_CODE : URLEndpoint.V5_TOURNAMENT_CODES_BY_CODE)
                                                       .withPlatform(RegionShard.AMERICAS);
        
        return builder.buildAs(TournamentCode.class);
    }
    
    /**
     * Get the games played with a tournament code
     *
     * @param tournamentCode The tournament code of the match
     * @return the games, or an empty list if there are none
     */
    public List<TournamentGame> getTournamentGames(final String tournamentCode)
    {
        if (useStub)
        {
            throw new IllegalArgumentException("This method is not useable with the stub API");
        }
        
        DataCallBuilder builder = new DataCallBuilder(context).withHeader(Constants.X_RIOT_TOKEN_HEADER_KEY, context.getCredentials().getTournamentAPIKey())
                                                       .withURLParameter(Constants.TOURNAMENT_CODE_PLACEHOLDER, tournamentCode)
                                                       .withEndpoint(URLEndpoint.V5_TOURNAMENT_GAMES_BY_CODE)
                                                       .withPlatform(RegionShard.AMERICAS);
        
        List<TournamentGame> games = builder.buildAs(TournamentGameList.class);
        return games != null ? games : Collections.emptyList();
    }
    
    /**
     * Get info about what happened in the lobby.
     *
     * @param tournamentCode The tournament code of the match
     * @return Lobby events
     */
    @Nullable
    public List<LobbyEvent> getTournamentLobbyInfo(final String tournamentCode)
    {
        DataCallBuilder builder = new DataCallBuilder(context).withHeader(Constants.X_RIOT_TOKEN_HEADER_KEY, context.getCredentials().getTournamentAPIKey())
                                                       .withURLParameter(Constants.TOURNAMENT_CODE_PLACEHOLDER, tournamentCode)
                                                       .withEndpoint(URLEndpoint.V5_TOURNAMENT_LOBBY_EVENTS)
                                                       .withPlatform(RegionShard.AMERICAS);
        
        if (useStub)
        {
            builder.withEndpoint(URLEndpoint.V5_TOURNAMENT_STUB_LOBBY_EVENTS);
        }
        
        try
        {
            LobbyEventWrapper lew = (LobbyEventWrapper) builder.build();
            return lew.getEventList();
        } catch (ClassCastException e)
        {
            
            return null;
        }
    }
    
    /**
     * Providers will need to call this endpoint first.
     * Registers their callback URL and their API key with the tournament system.
     * before this, other tournament endpoints will not work.
     * The id will always be the same for a given URL and Server.
     * I.E. Passing http://website.com and EUW will ALWAYS return the same code.
     *
     * @param params the provider definition
     * @return provider id
     */
    @Nullable
    public Long registerAsProvider(final ProviderRegistrationParameters params)
    {
        DataCallBuilder builder = new DataCallBuilder(context).withHeader(Constants.X_RIOT_TOKEN_HEADER_KEY, context.getCredentials().getTournamentAPIKey())
                                                       .withEndpoint(URLEndpoint.V5_TOURNAMENT_PROVIDER)
                                                       .withPostData(params.toJson())
                                                       .withRequestMethod(Constants.METHOD_POST)
                                                       .withPlatform(RegionShard.AMERICAS);
        
        
        if (useStub)
        {
            builder.withEndpoint(URLEndpoint.V5_TOURNAMENT_STUB_PROVIDER);
        }
        
        try
        {
            return Long.parseLong(builder.build().toString());
        } catch (NumberFormatException e)
        {
            return null;
        }
    }
    
    /**
     * Returns a TournamentId used to refer to this tournament later on.
     *
     * @param params the tournament definition
     * @return the id
     */
    @Nullable
    public Long registerTournament(final TournamentRegistrationParameters params)
    {
        DataCallBuilder builder = new DataCallBuilder(context).withHeader(Constants.X_RIOT_TOKEN_HEADER_KEY, context.getCredentials().getTournamentAPIKey())
                                                       .withEndpoint(URLEndpoint.V5_TOURNAMENT_TOURNAMENT)
                                                       .withPostData(Utils.getGson().toJson(params))
                                                       .withRequestMethod(Constants.METHOD_POST)
                                                       .withPlatform(RegionShard.AMERICAS);
        
        if (useStub)
        {
            builder.withEndpoint(URLEndpoint.V5_TOURNAMENT_STUB_TOURNAMENT);
        }
        
        try
        {
            return Long.parseLong(builder.build().toString());
        } catch (NumberFormatException e)
        {
            return null;
        }
    }
    
    /**
     * Update the data for the tournamet code
     *
     * @param params         the tournament definition
     * @param tournamentCode The tournament code of the match
     */
    public void updateTournament(final String tournamentCode, final TournamentCodeUpdateParameters params)
    {
        DataCallBuilder builder = new DataCallBuilder(context).withHeader(Constants.X_RIOT_TOKEN_HEADER_KEY, context.getCredentials().getTournamentAPIKey())
                                                       .withURLParameter(Constants.TOURNAMENT_CODE_PLACEHOLDER, tournamentCode)
                                                       .withEndpoint(URLEndpoint.V5_TOURNAMENT_CODES_BY_CODE)
                                                       .withPostData(Utils.getGson().toJson(params))
                                                       .withRequestMethod(Constants.METHOD_PUT)
                                                       .withPlatform(RegionShard.AMERICAS);
        
        if (useStub)
        {
            throw new IllegalArgumentException("This method is not useable with the stub API");
        }
        
        builder.build();
    }
    
    /**
     * Like {@link #generateTournamentCodes}, without a thread waiting for the answer: the ratelimit, the request and the retries are handled in the background.
     *
     * @return a future of what the blocking method returns
     */
    public CompletableFuture<List<String>> generateTournamentCodesAsync(final TournamentCodeParameters params, final long tournamentId, Integer count)
    {
        try
        {
            DataCallBuilder builder = new DataCallBuilder(context).withHeader(Constants.X_RIOT_TOKEN_HEADER_KEY, context.getCredentials().getTournamentAPIKey())
                                                           .withQueryParameter(Constants.URL_PARAM_TOURNAMENT_COUNT, String.valueOf(count != null ? count : 1))
                                                           .withQueryParameter(Constants.URL_PARAM_TOURNAMENT_ID, String.valueOf(tournamentId))
                                                           .withEndpoint(URLEndpoint.V5_TOURNAMENT_CODES)
                                                           .withPostData(Utils.getGson().toJson(params))
                                                           .withRequestMethod(Constants.METHOD_POST)
                                                           .withPlatform(RegionShard.AMERICAS);

            if (useStub)
            {
                builder.withEndpoint(URLEndpoint.V5_TOURNAMENT_STUB_CODES);
            }

            return builder.buildAsync().thenApply(result -> {
                try
                {
                    return (List<String>) result;
                } catch (ClassCastException e)
                {
                    return null;
                }
            });
        } catch (RuntimeException e)
        {
            // what the blocking method would have thrown before making the call (ie. a missing api key)
            return CompletableFuture.failedFuture(e);
        }
    }
    
    /**
     * Like {@link #getTournamentInfo}, without a thread waiting for the answer: the ratelimit, the request and the retries are handled in the background.
     *
     * @return a future of what the blocking method returns
     */
    public CompletableFuture<TournamentCode> getTournamentInfoAsync(final String tournamentCode)
    {
        try
        {
            DataCallBuilder builder = new DataCallBuilder(context).withHeader(Constants.X_RIOT_TOKEN_HEADER_KEY, context.getCredentials().getTournamentAPIKey())
                                                           .withURLParameter(Constants.TOURNAMENT_CODE_PLACEHOLDER, tournamentCode)
                                                           .withEndpoint(useStub ? URLEndpoint.V5_TOURNAMENT_STUB_CODES_BY_CODE : URLEndpoint.V5_TOURNAMENT_CODES_BY_CODE)
                                                           .withPlatform(RegionShard.AMERICAS);
        
            return builder.buildAsAsync(TournamentCode.class);
        } catch (RuntimeException e)
        {
            // what the blocking method would have thrown before making the call (ie. a missing api key)
            return CompletableFuture.failedFuture(e);
        }
    }
    
    /**
     * Like {@link #getTournamentGames}, without a thread waiting for the answer: the ratelimit, the request and the retries are handled in the background.
     *
     * @return a future of what the blocking method returns
     */
    public CompletableFuture<List<TournamentGame>> getTournamentGamesAsync(final String tournamentCode)
    {
        try
        {
            if (useStub)
            {
                throw new IllegalArgumentException("This method is not useable with the stub API");
            }

            DataCallBuilder builder = new DataCallBuilder(context).withHeader(Constants.X_RIOT_TOKEN_HEADER_KEY, context.getCredentials().getTournamentAPIKey())
                                                           .withURLParameter(Constants.TOURNAMENT_CODE_PLACEHOLDER, tournamentCode)
                                                           .withEndpoint(URLEndpoint.V5_TOURNAMENT_GAMES_BY_CODE)
                                                           .withPlatform(RegionShard.AMERICAS);

            return builder.buildAsAsync(TournamentGameList.class).<List<TournamentGame>>thenApply(games -> games != null ? games : Collections.emptyList());
        } catch (RuntimeException e)
        {
            // what the blocking method would have thrown before making the call (ie. a missing api key)
            return CompletableFuture.failedFuture(e);
        }
    }
    
    /**
     * Like {@link #getTournamentLobbyInfo}, without a thread waiting for the answer: the ratelimit, the request and the retries are handled in the background.
     *
     * @return a future of what the blocking method returns
     */
    public CompletableFuture<List<LobbyEvent>> getTournamentLobbyInfoAsync(final String tournamentCode)
    {
        try
        {
            DataCallBuilder builder = new DataCallBuilder(context).withHeader(Constants.X_RIOT_TOKEN_HEADER_KEY, context.getCredentials().getTournamentAPIKey())
                                                           .withURLParameter(Constants.TOURNAMENT_CODE_PLACEHOLDER, tournamentCode)
                                                           .withEndpoint(URLEndpoint.V5_TOURNAMENT_LOBBY_EVENTS)
                                                           .withPlatform(RegionShard.AMERICAS);

            if (useStub)
            {
                builder.withEndpoint(URLEndpoint.V5_TOURNAMENT_STUB_LOBBY_EVENTS);
            }

            return builder.buildAsync().thenApply(result -> {
                try
                {
                    LobbyEventWrapper lew = (LobbyEventWrapper) result;
                    return lew.getEventList();
                } catch (ClassCastException e)
                {
                    return null;
                }
            });
        } catch (RuntimeException e)
        {
            // what the blocking method would have thrown before making the call (ie. a missing api key)
            return CompletableFuture.failedFuture(e);
        }
    }
    
    /**
     * Like {@link #registerAsProvider}, without a thread waiting for the answer: the ratelimit, the request and the retries are handled in the background.
     *
     * @return a future of what the blocking method returns
     */
    public CompletableFuture<Long> registerAsProviderAsync(final ProviderRegistrationParameters params)
    {
        try
        {
            DataCallBuilder builder = new DataCallBuilder(context).withHeader(Constants.X_RIOT_TOKEN_HEADER_KEY, context.getCredentials().getTournamentAPIKey())
                                                           .withEndpoint(URLEndpoint.V5_TOURNAMENT_PROVIDER)
                                                           .withPostData(params.toJson())
                                                           .withRequestMethod(Constants.METHOD_POST)
                                                           .withPlatform(RegionShard.AMERICAS);

            if (useStub)
            {
                builder.withEndpoint(URLEndpoint.V5_TOURNAMENT_STUB_PROVIDER);
            }

            return builder.buildAsync().thenApply(result -> {
                try
                {
                    return Long.parseLong(result.toString());
                } catch (NumberFormatException e)
                {
                    return null;
                }
            });
        } catch (RuntimeException e)
        {
            // what the blocking method would have thrown before making the call (ie. a missing api key)
            return CompletableFuture.failedFuture(e);
        }
    }
    
    /**
     * Like {@link #registerTournament}, without a thread waiting for the answer: the ratelimit, the request and the retries are handled in the background.
     *
     * @return a future of what the blocking method returns
     */
    public CompletableFuture<Long> registerTournamentAsync(final TournamentRegistrationParameters params)
    {
        try
        {
            DataCallBuilder builder = new DataCallBuilder(context).withHeader(Constants.X_RIOT_TOKEN_HEADER_KEY, context.getCredentials().getTournamentAPIKey())
                                                           .withEndpoint(URLEndpoint.V5_TOURNAMENT_TOURNAMENT)
                                                           .withPostData(Utils.getGson().toJson(params))
                                                           .withRequestMethod(Constants.METHOD_POST)
                                                           .withPlatform(RegionShard.AMERICAS);

            if (useStub)
            {
                builder.withEndpoint(URLEndpoint.V5_TOURNAMENT_STUB_TOURNAMENT);
            }

            return builder.buildAsync().thenApply(result -> {
                try
                {
                    return Long.parseLong(result.toString());
                } catch (NumberFormatException e)
                {
                    return null;
                }
            });
        } catch (RuntimeException e)
        {
            // what the blocking method would have thrown before making the call (ie. a missing api key)
            return CompletableFuture.failedFuture(e);
        }
    }
}
