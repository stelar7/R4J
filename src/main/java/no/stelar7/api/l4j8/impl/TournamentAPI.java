package no.stelar7.api.l4j8.impl;

import no.stelar7.api.l4j8.basic.DataCall.*;
import no.stelar7.api.l4j8.basic.*;
import no.stelar7.api.l4j8.basic.constants.api.*;
import no.stelar7.api.l4j8.pojo.match.*;
import no.stelar7.api.l4j8.pojo.tournament.*;

import java.util.*;

public class TournamentAPI
{
	
	private static final TournamentAPI INSTANCE = new TournamentAPI();
	
	static TournamentAPI getInstance()
	{
		return TournamentAPI.INSTANCE;
	}
	
	/**
	 * Generates a list of tournamentCodes to use for adding players to games. You should only use a code one time.
	 */
	@SuppressWarnings("unchecked")
	public List<String> generateTournamentCodes(final TournamentCodeParameters params, final long tournamentId, final int count)
	{
		DataCallBuilder builder = new DataCallBuilder();
		
		builder = builder.withHeader(Constants.X_RIOT_TOKEN_HEADER_KEY, DataCallBuilder.getCredentials().getTournamentAPIKey());
		builder = builder.withRequestMethod(Constants.REQUEST_METHOD_POST);
		builder = builder.withPostData(Utils.toJson(params));
		builder = builder.withEndpoint(URLEndpoint.TOURNAMENT_CODE_CODES);
		builder = builder.withURLData(Constants.TOURNAMENT_CODE_PLACEHOLDER, "");
		builder = builder.withURLParameter(Constants.URL_PARAM_TOURNAMENT_ID, String.valueOf(tournamentId));
		builder = builder.withURLParameter(Constants.URL_PARAM_TOURNAMENT_COUNT, String.valueOf(count));
		builder = builder.withServer(Server.GLOBAL);
		
		return (List<String>) builder.build();
	}
	
	/**
	 * A list of all matches played with this tournament code...
	 */
	public List<Long> getMatchIds(final String tournamentCode, final Server server)
	{
		DataCallBuilder builder = new DataCallBuilder();
		
		builder = builder.withHeader(Constants.X_RIOT_TOKEN_HEADER_KEY, DataCallBuilder.getCredentials().getTournamentAPIKey());
		builder = builder.withEndpoint(URLEndpoint.TOURNAMENT_MATCH);
		builder = builder.withURLData(Constants.TOURNAMENT_CODE_PLACEHOLDER, tournamentCode);
		builder = builder.withServer(server);
		
		@SuppressWarnings("unchecked")
		List<Long> ids = (List<Long>) builder.build();
		
		return ids;
	}
	
	/**
	 * Get details about a match from this tournament, differs from Match.getMatchInfo because this returns participants
	 */
	public MatchDetail getMatchInfo(final String tournamentCode, final Long matchId)
	{
		DataCallBuilder builder = new DataCallBuilder();
		
		builder = builder.withHeader(Constants.X_RIOT_TOKEN_HEADER_KEY, DataCallBuilder.getCredentials().getTournamentAPIKey());
		builder = builder.withEndpoint(URLEndpoint.TOURNAMENT_MATCH_BY_ID);
		builder = builder.withURLData(Constants.MATCH_ID_PLACEHOLDER, String.valueOf(matchId));
		builder = builder.withURLParameter(Constants.URL_PARAM_TOURNAMENT_CODE, tournamentCode);
		builder = builder.withURLParameter(Constants.URL_PARAM_TOURNAMENT_TIMELINE, String.valueOf(true));
		builder = builder.withServer(Server.GLOBAL);
		
		return (MatchDetail) builder.build();
	}
	
	/**
	 * Get info about a specified tournament
	 */
	public TournamentCode getTournamentInfo(final String tournamentCode)
	{
		DataCallBuilder builder = new DataCallBuilder();
		
		builder = builder.withHeader(Constants.X_RIOT_TOKEN_HEADER_KEY, DataCallBuilder.getCredentials().getTournamentAPIKey());
		builder = builder.withEndpoint(URLEndpoint.TOURNAMENT_CODE);
		builder = builder.withURLData(Constants.TOURNAMENT_CODE_PLACEHOLDER, tournamentCode);
		builder = builder.withServer(Server.GLOBAL);
		
		return (TournamentCode) builder.build();
	}
	
	/**
	 * Get info about what happened in the lobby
	 */
	public LobbyEventWrapper getTournamentLobbyInfo(final String tournamentCode)
	{
		DataCallBuilder builder = new DataCallBuilder();
		
		builder = builder.withHeader(Constants.X_RIOT_TOKEN_HEADER_KEY, DataCallBuilder.getCredentials().getTournamentAPIKey());
		builder = builder.withEndpoint(URLEndpoint.TOURNAMENT_LOBBY);
		builder = builder.withURLData(Constants.TOURNAMENT_CODE_PLACEHOLDER, tournamentCode);
		builder = builder.withServer(Server.GLOBAL);
		
		return (LobbyEventWrapper) builder.build();
	}
	
	/**
	 * Returns a ProviderId used to link the tournaments to your callback url. The id will always be the same for a given URL and Server. I.E. Passing http://website.com and EUW will ALWAYS return the same code.
	 */
	public long registerAsProvider(final ProviderRegistrationParameters params)
	{
		DataCallBuilder builder = new DataCallBuilder();
		
		builder = builder.withHeader(Constants.X_RIOT_TOKEN_HEADER_KEY, DataCallBuilder.getCredentials().getTournamentAPIKey());
		builder = builder.withRequestMethod(Constants.REQUEST_METHOD_POST);
		builder = builder.withPostData(Utils.toJson(params));
		builder = builder.withEndpoint(URLEndpoint.TOURNAMENT_PROVIDER);
		builder = builder.withServer(Server.GLOBAL);
		
		return (Long) builder.build();
	}
	
	/**
	 * Returns a TournamentId used to refer to this tournament later on
	 */
	public long registerTournament(final TournamentRegistrationParameters params)
	{
		DataCallBuilder builder = new DataCallBuilder();
		
		builder = builder.withHeader(Constants.X_RIOT_TOKEN_HEADER_KEY, DataCallBuilder.getCredentials().getTournamentAPIKey());
		builder = builder.withRequestMethod(Constants.REQUEST_METHOD_POST);
		builder = builder.withPostData(Utils.toJson(params));
		builder = builder.withEndpoint(URLEndpoint.TOURNAMENT_REGISTRATION);
		builder = builder.withServer(Server.GLOBAL);
		
		return (Long) builder.build();
	}
	
	/**
	 * Update the data for the tournamet code
	 */
	public void updateTournament(final TournamentCodeUpdateParameters params, final String tournamentCode)
	{
		DataCallBuilder builder = new DataCallBuilder();
		
		builder = builder.withHeader(Constants.X_RIOT_TOKEN_HEADER_KEY, DataCallBuilder.getCredentials().getTournamentAPIKey());
		builder = builder.withRequestMethod(Constants.REQUEST_METHOD_PUT);
		builder = builder.withPostData(Utils.toJson(params));
		builder = builder.withEndpoint(URLEndpoint.TOURNAMENT_CODE_CODES);
		builder = builder.withURLData(Constants.TOURNAMENT_CODE_PLACEHOLDER, tournamentCode);
		builder = builder.withServer(Server.GLOBAL);
		
		builder.build();
	}
	
}
