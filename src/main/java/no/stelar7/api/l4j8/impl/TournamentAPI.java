package no.stelar7.api.l4j8.impl;

import java.util.*;

import com.google.gson.*;

import no.stelar7.api.l4j8.basic.DataCall.*;
import no.stelar7.api.l4j8.basic.constants.api.*;
import no.stelar7.api.l4j8.pojo.match.*;
import no.stelar7.api.l4j8.pojo.tournament.*;

public class TournamentAPI
{

    private static final TournamentAPI INSTANCE = new TournamentAPI();

    /**
     * Generates a list of tournamentCodes to use for adding players to games. You should only use a code one time.
     */
    public List<String> generateTournamentCodes(final TournamentCodeParameters params, final long tournamentId, final int count)
    {
        DataCallBuilder builder = new DataCallBuilder();

        builder = builder.withHeader(Constants.X_RIOT_TOKEN, DataCallBuilder.defaultCredentials().getTournamentAPIKey());
        builder = builder.withRequestMethod("POST");
        builder = builder.withPostData(new Gson().toJson(params));
        builder = builder.withEndpoint(URLEndpoint.TOURNAMENT_CODE_CODES);
        builder = builder.withURLData(Constants.TOURNAMENT_CODE, "");
        builder = builder.withURLParameter("tournamentId", String.valueOf(tournamentId));
        builder = builder.withURLParameter("count", String.valueOf(count));
        builder = builder.withServer(Server.GLOBAL);
        builder = builder.asVerbose(true);

        return (List<String>) builder.build();
    }

    /**
     * A list of all matches played with this tournament code...
     */
    public List<Long> getMatchIds(final String tournamentCode, Server server)
    {
        DataCallBuilder builder = new DataCallBuilder();

        builder = builder.withHeader(Constants.X_RIOT_TOKEN, DataCallBuilder.defaultCredentials().getTournamentAPIKey());
        builder = builder.withEndpoint(URLEndpoint.TOURNAMENT_MATCH);
        builder = builder.withURLData(Constants.TOURNAMENT_CODE, tournamentCode);
        builder = builder.withServer(server);
        builder = builder.asVerbose(true);

        return (List<Long>) builder.build();
    }

    /**
     * Get details about a match from this tournament, differs from Match.getMatchInfo because this returns participants
     */
    public MatchDetail getMatchInfo(final String tournamentCode, final Long matchId)
    {
        DataCallBuilder builder = new DataCallBuilder();

        builder = builder.withHeader(Constants.X_RIOT_TOKEN, DataCallBuilder.defaultCredentials().getTournamentAPIKey());
        builder = builder.withEndpoint(URLEndpoint.TOURNAMENT_MATCH_BY_ID);
        builder = builder.withURLData("{matchId}", String.valueOf(matchId));
        builder = builder.withURLParameter("tournamentCode", tournamentCode);
        builder = builder.withURLParameter("includeTimeline", "true");
        builder = builder.withServer(Server.GLOBAL);
        builder = builder.asVerbose(true);

        return (MatchDetail) builder.build();
    }

    /**
     * Get info about a specified tournament
     */
    public TournamentCode getTournamentInfo(final String tournamentCode)
    {
        DataCallBuilder builder = new DataCallBuilder();

        builder = builder.withHeader(Constants.X_RIOT_TOKEN, DataCallBuilder.defaultCredentials().getTournamentAPIKey());
        builder = builder.withEndpoint(URLEndpoint.TOURNAMENT_CODE);
        builder = builder.withURLData(Constants.TOURNAMENT_CODE, tournamentCode);
        builder = builder.withServer(Server.GLOBAL);
        builder = builder.asVerbose(true);

        return (TournamentCode) builder.build();
    }

    /**
     * Get info about what happened in the lobby
     */
    public LobbyEventWrapper getTournamentLobbyInfo(final String tournamentCode)
    {
        DataCallBuilder builder = new DataCallBuilder();

        builder = builder.withHeader(Constants.X_RIOT_TOKEN, DataCallBuilder.defaultCredentials().getTournamentAPIKey());
        builder = builder.withEndpoint(URLEndpoint.TOURNAMENT_LOBBY);
        builder = builder.withURLData(Constants.TOURNAMENT_CODE, tournamentCode);
        builder = builder.withServer(Server.GLOBAL);
        builder = builder.asVerbose(true);

        return (LobbyEventWrapper) builder.build();
    }

    /**
     * Returns a ProviderId used to link the tournaments to your callback url. The id will always be the same for a given URL and Server. I.E. Passing http://website.com and EUW will ALWAYS return the same code.
     */
    public long registerAsProvider(final ProviderRegistrationParameters params)
    {
        DataCallBuilder builder = new DataCallBuilder();

        builder = builder.withHeader(Constants.X_RIOT_TOKEN, DataCallBuilder.defaultCredentials().getTournamentAPIKey());
        builder = builder.withRequestMethod("POST");
        builder = builder.withPostData(new Gson().toJson(params));
        builder = builder.withEndpoint(URLEndpoint.TOURNAMENT_PROVIDER);
        builder = builder.withServer(Server.GLOBAL);
        builder = builder.asVerbose(true);

        return (Long) builder.build();
    }

    /**
     * Returns a TournamentId used to refer to this tournament later on
     */
    public long registerTournament(final TournamentRegistrationParameters params)
    {
        DataCallBuilder builder = new DataCallBuilder();

        builder = builder.withHeader(Constants.X_RIOT_TOKEN, DataCallBuilder.defaultCredentials().getTournamentAPIKey());
        builder = builder.withRequestMethod("POST");
        builder = builder.withPostData(new Gson().toJson(params));
        builder = builder.withEndpoint(URLEndpoint.TOURNAMENT_REGISTRATION);
        builder = builder.withServer(Server.GLOBAL);
        builder = builder.asVerbose(true);

        return (Long) builder.build();
    }

    /**
     * Update the data for the tournamet code
     */
    public void updateTournament(final TournamentCodeUpdateParameters params, final String tournamentCode)
    {
        DataCallBuilder builder = new DataCallBuilder();

        builder = builder.withHeader(Constants.X_RIOT_TOKEN, DataCallBuilder.defaultCredentials().getTournamentAPIKey());
        builder = builder.withRequestMethod("PUT");
        builder = builder.withPostData(new Gson().toJson(params));
        builder = builder.withEndpoint(URLEndpoint.TOURNAMENT_CODE_CODES);
        builder = builder.withURLData(Constants.TOURNAMENT_CODE, tournamentCode);
        builder = builder.withServer(Server.GLOBAL);
        builder = builder.asVerbose(true);

        builder.build();
    }

    static TournamentAPI getInstance()
    {
        return INSTANCE;
    }

}
