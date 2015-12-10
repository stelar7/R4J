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
     * Returns a ProviderId used to link the tournaments to your callback url
     */
    public int registerAsProvider(ProviderRegistrationParameters params)
    {
        DataCallBuilder builder = new DataCallBuilder();

        builder = builder.withPostData(new Gson().toJson(params)).withRequestMethod("POST").asVerbose(true);
        builder = builder.withRegion(Server.EUW).withServer(Server.EUW).withEndpoint(URLEndpoint.TOURNAMENT_PROVIDER);
        Integer providerId = (Integer) builder.build();

        return providerId;
    }

    /**
     * Returns a TournamentId used to refer to this tournament later on
     */
    public int registerTournament(TournamentRegistrationParameters params)
    {
        DataCallBuilder builder = new DataCallBuilder();

        builder = builder.withPostData(new Gson().toJson(params)).withRequestMethod("POST").asVerbose(true);
        builder = builder.withRegion(Server.EUW).withServer(Server.EUW).withEndpoint(URLEndpoint.TOURNAMENT_REGISTRATION);
        Integer tournamentId = (Integer) builder.build();

        return tournamentId;
    }

    /**
     * Generates a list of tournamentCodes to use for adding players to games ONE CODE PER GAME!!
     */
    public List<String> generateTournamentCodes(TournamentCodeParameters params, int tournamentId, int count)
    {
        DataCallBuilder builder = new DataCallBuilder();

        builder = builder.withPostData(new Gson().toJson(params)).withRequestMethod("POST").withURLData("{tournamentCode}", "").asVerbose(true);
        builder = builder.withRegion(Server.EUW).withServer(Server.EUW).withEndpoint(URLEndpoint.TOURNAMENT_CODE);
        List<String> tournamentCodes = (List<String>) builder.build();

        return tournamentCodes;
    }

    /**
     * Update the data for the tournamet code
     */
    public void updateTournament(TournamentCodeUpdateParameters params, String tournamentCode)
    {
        DataCallBuilder builder = new DataCallBuilder();

        builder = builder.withPostData(new Gson().toJson(params)).withRequestMethod("PUT").withURLData("{tournamentCode}", String.valueOf(tournamentCode)).asVerbose(true);
        builder = builder.withRegion(Server.EUW).withServer(Server.EUW).withEndpoint(URLEndpoint.TOURNAMENT_CODE);
        builder.build();
    }

    /**
     * Get info about a specified tournament
     */
    public TournamentCode getTournamentInfo(String tournamentCode)
    {
        DataCallBuilder builder = new DataCallBuilder();

        builder = builder.withURLData("{tournamentCode}", tournamentCode).asVerbose(true);
        builder = builder.withRegion(Server.EUW).withServer(Server.EUW).withEndpoint(URLEndpoint.TOURNAMENT_CODE);
        TournamentCode code = (TournamentCode) builder.build();

        return code;
    }

    /**
     * Get info about what happened in the lobby
     */
    public LobbyEventWrapper getTournamentLobbyInfo(String tournamentCode)
    {
        DataCallBuilder builder = new DataCallBuilder();

        builder = builder.withURLData("{tournamentCode}", tournamentCode).asVerbose(true);
        builder = builder.withRegion(Server.EUW).withServer(Server.EUW).withEndpoint(URLEndpoint.TOURNAMENT_LOBBY);
        LobbyEventWrapper code = (LobbyEventWrapper) builder.build();

        return code;
    }

    /**
     *  @deprecated  Use Match.getMatchInfo instead because this doesnt return the timeline
     */
    @Deprecated
    public MatchDetail getMatchInfo(String tournamentCode, Long matchId)
    {
        DataCallBuilder builder = new DataCallBuilder();

        builder = builder.withURLData("{matchId}", String.valueOf(matchId)).withURLParameter("tournamentCode", tournamentCode).withURLParameter("includeTimeline", "true").withAPIKey(API_KEY).asVerbose(true);
        builder = builder.withRegion(Server.EUW).withServer(Server.EUW).withEndpoint(URLEndpoint.TOURNAMENT_MATCH_BY_ID);
        MatchDetail code = (MatchDetail) builder.build();

        return code;
    }

    /**
     * A list of all matches played with this tournament code...
     */
    public List<Long> getMatchIds(String tournamentCode)
    {
        DataCallBuilder builder = new DataCallBuilder();

        builder = builder.withURLData("{tournamentCode}", tournamentCode).withAPIKey(API_KEY).asVerbose(true);
        builder = builder.withRegion(Server.EUW).withServer(Server.EUW).withEndpoint(URLEndpoint.TOURNAMENT_MATCH);
        List<Long> code = (List<Long>) builder.build();

        return code;
    }

}
