package no.stelar7.api.l4j8.impl;

import no.stelar7.api.l4j8.basic.*;
import no.stelar7.api.l4j8.basic.DataCall.DataCallBuilder;
import no.stelar7.api.l4j8.basic.constants.api.*;
import no.stelar7.api.l4j8.pojo.match.Match;
import no.stelar7.api.l4j8.pojo.tournament.*;

import java.util.*;

public final class TournamentAPI
{
    
    private static final TournamentAPI INSTANCE = new TournamentAPI();
    
    public static TournamentAPI getInstance()
    {
        return TournamentAPI.INSTANCE;
    }
    
    private TournamentAPI()
    {
        // Hide public constructor
    }
    
    
    /**
     * Generates a list of tournamentCodes to use for adding players to games.
     * You should only use a code per match.
     *
     * @param server       the server the games are played on
     * @param count        the amount of codes to generate (default: 1)
     * @param params       the TournamentCodeParameters for the games played with this code
     * @param tournamentId the tournamentId this game is played on
     * @return a list of tournamentcodes
     */
    public List<String> generateTournamentCodes(Platform server, final TournamentCodeParameters params, final long tournamentId, Optional<Integer> count)
    {
        DataCallBuilder builder = new DataCallBuilder().withHeader(Constants.X_RIOT_TOKEN_HEADER_KEY, DataCall.getCredentials().getTournamentAPIKey())
                                                       .withURLParameter(Constants.URL_PARAM_TOURNAMENT_COUNT, String.valueOf(count.orElseGet(() -> 1)))
                                                       .withURLParameter(Constants.URL_PARAM_TOURNAMENT_ID, String.valueOf(tournamentId))
                                                       .withURLData(Constants.TOURNAMENT_CODE_PLACEHOLDER, "")
                                                       .withEndpoint(URLEndpoint.V3_TOURNAMENT_CODES)
                                                       .withPostData(Utils.getGson().toJson(params))
                                                       .withRequestMethod(Constants.METHOD_POST)
                                                       .withPlatform(server);
        
        return (List<String>) builder.build();
    }
    
    /**
     * A list of all games played with this tournament code.
     *
     * @param server         the server the games are played on
     * @param tournamentCode The tournament code of the match
     * @return a list of matchIds played with this code
     */
    public List<Long> getMatchIds(final Platform server, final String tournamentCode)
    {
        DataCallBuilder builder = new DataCallBuilder().withHeader(Constants.X_RIOT_TOKEN_HEADER_KEY, DataCall.getCredentials().getTournamentAPIKey())
                                                       .withURLData(Constants.TOURNAMENT_CODE_PLACEHOLDER, tournamentCode)
                                                       .withEndpoint(URLEndpoint.V3_TOURNAMENT_MATCHLIST)
                                                       .withPlatform(server);
        
        
        return (List<Long>) builder.build();
    }
    
    /**
     * Get details about a match from this tournament.
     * Differs from Match.getMatchInfo because this returns participants
     *
     * @param server         the server the games are played on
     * @param tournamentCode The tournament code of the match
     * @param matchId        the ID of the match.
     * @return Match
     */
    public Match getMatchInfo(final Platform server, final String tournamentCode, final Long matchId)
    {
        DataCallBuilder builder = new DataCallBuilder().withHeader(Constants.X_RIOT_TOKEN_HEADER_KEY, DataCall.getCredentials().getTournamentAPIKey())
                                                       .withURLData(Constants.URL_PARAM_TOURNAMENT_CODE, tournamentCode)
                                                       .withURLData(Constants.MATCH_ID_PLACEHOLDER, String.valueOf(matchId))
                                                       .withEndpoint(URLEndpoint.V3_TOURNAMENT_MATCH)
                                                       .withPlatform(server);
        
        return (Match) builder.build();
    }
    
    /**
     * Get info about a specified tournament
     *
     * @param server         the server the games are played on
     * @param tournamentCode The tournament code of the match
     * @return TournamentCode
     */
    public TournamentCode getTournamentInfo(Platform server, final String tournamentCode)
    {
        DataCallBuilder builder = new DataCallBuilder().withHeader(Constants.X_RIOT_TOKEN_HEADER_KEY, DataCall.getCredentials().getTournamentAPIKey())
                                                       .withURLData(Constants.TOURNAMENT_CODE_PLACEHOLDER, tournamentCode)
                                                       .withEndpoint(URLEndpoint.V3_TOURNAMENT_CODES_BY_CODE)
                                                       .withPlatform(server);
        
        return (TournamentCode) builder.build();
    }
    
    /**
     * Get info about what happened in the lobby.
     *
     * @param server         the server the games are played on
     * @param tournamentCode The tournament code of the match
     * @return Lobby events
     */
    public List<LobbyEvent> getTournamentLobbyInfo(final Platform server, final String tournamentCode)
    {
        DataCallBuilder builder = new DataCallBuilder().withHeader(Constants.X_RIOT_TOKEN_HEADER_KEY, DataCall.getCredentials().getTournamentAPIKey())
                                                       .withURLData(Constants.TOURNAMENT_CODE_PLACEHOLDER, tournamentCode)
                                                       .withEndpoint(URLEndpoint.V3_TOURNAMENT_LOBBY_EVENTS)
                                                       .withPlatform(server);
        
        LobbyEventWrapper lew = (LobbyEventWrapper) builder.build();
        return lew.getEventList();
    }
    
    /**
     * Providers will need to call this endpoint first.
     * Registers their callback URL and their API key with the tournament system.
     * before this, other tournament endpoints will not work.
     * The id will always be the same for a given URL and Server.
     * I.E. Passing http://website.com and EUW will ALWAYS return the same code.
     *
     * @param server the server the games are played on
     * @param params the provider definition
     * @return provider id
     */
    public long registerAsProvider(final Platform server, final ProviderRegistrationParameters params)
    {
        DataCallBuilder builder = new DataCallBuilder().withHeader(Constants.X_RIOT_TOKEN_HEADER_KEY, DataCall.getCredentials().getTournamentAPIKey())
                                                       .withEndpoint(URLEndpoint.V3_TOURNAMENT_PROVIDER)
                                                       .withPostData(Utils.getGson().toJson(params))
                                                       .withRequestMethod(Constants.METHOD_POST)
                                                       .withPlatform(server);
        
        return (Long) builder.build();
    }
    
    /**
     * Returns a TournamentId used to refer to this tournament later on.
     *
     * @param server the server the games are played on
     * @param params the tournament definition
     * @return the id
     */
    public long registerTournament(final Platform server, final TournamentRegistrationParameters params)
    {
        DataCallBuilder builder = new DataCallBuilder().withHeader(Constants.X_RIOT_TOKEN_HEADER_KEY, DataCall.getCredentials().getTournamentAPIKey())
                                                       .withEndpoint(URLEndpoint.V3_TOURNAMENT_TOURNAMENT)
                                                       .withPostData(Utils.getGson().toJson(params))
                                                       .withRequestMethod(Constants.METHOD_POST)
                                                       .withPlatform(server);
        
        return (Long) builder.build();
    }
    
    /**
     * Update the data for the tournamet code
     *
     * @param server         the server the games are played on
     * @param params         the tournament definition
     * @param tournamentCode The tournament code of the match
     */
    public void updateTournament(final Platform server, final String tournamentCode, final TournamentCodeUpdateParameters params)
    {
        DataCallBuilder builder = new DataCallBuilder().withHeader(Constants.X_RIOT_TOKEN_HEADER_KEY, DataCall.getCredentials().getTournamentAPIKey())
                                                       .withURLData(Constants.TOURNAMENT_CODE_PLACEHOLDER, tournamentCode)
                                                       .withEndpoint(URLEndpoint.V3_TOURNAMENT_CODES)
                                                       .withPostData(Utils.getGson().toJson(params))
                                                       .withRequestMethod(Constants.METHOD_PUT)
                                                       .withPlatform(server);
        
        builder.build();
    }
}
