package no.stelar7.api.l4j8.impl;

import java.util.List;

import com.google.gson.Gson;

import no.stelar7.api.l4j8.basic.DataCall.DataCallBuilder;
import no.stelar7.api.l4j8.basic.Server;
import no.stelar7.api.l4j8.basic.URLEndpoint;
import no.stelar7.api.l4j8.pojo.match.MatchDetail;
import no.stelar7.api.l4j8.pojo.tournament.LobbyEventWrapper;
import no.stelar7.api.l4j8.pojo.tournament.ProviderRegistrationParameters;
import no.stelar7.api.l4j8.pojo.tournament.TournamentCode;
import no.stelar7.api.l4j8.pojo.tournament.TournamentCodeParameters;
import no.stelar7.api.l4j8.pojo.tournament.TournamentCodeUpdateParameters;
import no.stelar7.api.l4j8.pojo.tournament.TournamentRegistrationParameters;

public class TournamentBuilder
{

    String API_KEY;

    public TournamentBuilder(String API_KEY)
    {
        this.API_KEY = API_KEY;
    }

    /**
     * Returns a ProviderId used to link the tournaments to your callback url. The id will always be the same for a given URL and Server. I.E. Passing http://website.com and EUW will ALWAYS return the same code.
     */
    public long registerAsProvider(ProviderRegistrationParameters params)
    {
        DataCallBuilder builder = new DataCallBuilder();

        builder = builder.withHeader("X-Riot-Token", API_KEY);
        builder = builder.withRequestMethod("POST");
        builder = builder.withPostData(new Gson().toJson(params));
        builder = builder.withEndpoint(URLEndpoint.TOURNAMENT_PROVIDER);
        builder = builder.withServer(Server.GLOBAL);
        builder = builder.asVerbose(true);

        Long providerId = (Long) builder.build();
        return providerId;
    }

    /**
     * Returns a TournamentId used to refer to this tournament later on
     */
    public long registerTournament(TournamentRegistrationParameters params)
    {
        DataCallBuilder builder = new DataCallBuilder();

        builder = builder.withHeader("X-Riot-Token", API_KEY);
        builder = builder.withRequestMethod("POST");
        builder = builder.withPostData(new Gson().toJson(params));
        builder = builder.withEndpoint(URLEndpoint.TOURNAMENT_REGISTRATION);
        builder = builder.withServer(Server.GLOBAL);
        builder = builder.asVerbose(true);

        Long tournamentId = (Long) builder.build();
        return tournamentId;
    }

    /**
     * Generates a list of tournamentCodes to use for adding players to games. You should only use a code one time.
     */
    public List<String> generateTournamentCodes(TournamentCodeParameters params, long tournamentId, int count)
    {
        DataCallBuilder builder = new DataCallBuilder();

        builder = builder.withHeader("X-Riot-Token", API_KEY);
        builder = builder.withRequestMethod("POST");
        builder = builder.withPostData(new Gson().toJson(params));
        builder = builder.withEndpoint(URLEndpoint.TOURNAMENT_CODE_CODES);
        builder = builder.withURLData("{tournamentCode}", "");
        builder = builder.withURLParameter("tournamentId", String.valueOf(tournamentId));
        builder = builder.withURLParameter("count", String.valueOf(count));
        builder = builder.withServer(Server.GLOBAL);
        builder = builder.asVerbose(true);

        List<String> tournamentCodes = (List<String>) builder.build();
        return tournamentCodes;
    }

    /**
     * Update the data for the tournamet code
     */
    public void updateTournament(TournamentCodeUpdateParameters params, String tournamentCode)
    {
        DataCallBuilder builder = new DataCallBuilder();

        builder = builder.withHeader("X-Riot-Token", API_KEY);
        builder = builder.withRequestMethod("PUT");
        builder = builder.withPostData(new Gson().toJson(params));
        builder = builder.withEndpoint(URLEndpoint.TOURNAMENT_CODE_CODES);
        builder = builder.withURLData("{tournamentCode}", tournamentCode);
        builder = builder.withServer(Server.GLOBAL);
        builder = builder.asVerbose(true);

        builder.build();
    }

    /**
     * Get info about a specified tournament
     */
    public TournamentCode getTournamentInfo(String tournamentCode)
    {
        DataCallBuilder builder = new DataCallBuilder();

        builder = builder.withHeader("X-Riot-Token", API_KEY);
        builder = builder.withEndpoint(URLEndpoint.TOURNAMENT_CODE);
        builder = builder.withURLData("{tournamentCode}", tournamentCode);
        builder = builder.withServer(Server.GLOBAL);
        builder = builder.asVerbose(true);

        TournamentCode code = (TournamentCode) builder.build();
        return code;
    }

    /**
     * Get info about what happened in the lobby
     */
    public LobbyEventWrapper getTournamentLobbyInfo(String tournamentCode)
    {
        DataCallBuilder builder = new DataCallBuilder();

        builder = builder.withHeader("X-Riot-Token", API_KEY);
        builder = builder.withEndpoint(URLEndpoint.TOURNAMENT_LOBBY);
        builder = builder.withURLData("{tournamentCode}", tournamentCode);
        builder = builder.withServer(Server.GLOBAL);
        builder = builder.asVerbose(true);

        LobbyEventWrapper code = (LobbyEventWrapper) builder.build();
        return code;
    }

    /**
     * @deprecated Use Match.getMatchInfo instead because this doesnt return the timeline
     */
    @Deprecated
    public MatchDetail getMatchInfo(String tournamentCode, Long matchId)
    {
        DataCallBuilder builder = new DataCallBuilder();

        builder = builder.withAPIKey(API_KEY);
        builder = builder.withHeader("X-Riot-Token", API_KEY);
        builder = builder.withEndpoint(URLEndpoint.TOURNAMENT_MATCH_BY_ID);
        builder = builder.withURLData("{matchId}", String.valueOf(matchId));
        builder = builder.withURLParameter("tournamentCode", tournamentCode);
        builder = builder.withURLParameter("includeTimeline", "true");
        builder = builder.withServer(Server.GLOBAL);
        builder = builder.asVerbose(true);

        MatchDetail code = (MatchDetail) builder.build();
        return code;
    }

    /**
     * A list of all matches played with this tournament code...
     */
    public List<Long> getMatchIds(String tournamentCode)
    {
        DataCallBuilder builder = new DataCallBuilder();

        builder = builder.withAPIKey(API_KEY);
        builder = builder.withHeader("X-Riot-Token", API_KEY);
        builder = builder.withEndpoint(URLEndpoint.TOURNAMENT_MATCH);
        builder = builder.withURLData("{tournamentCode}", tournamentCode);
        builder = builder.withServer(Server.EUW);
        builder = builder.asVerbose(true);

        List<Long> code = (List<Long>) builder.build();
        return code;
    }

}
