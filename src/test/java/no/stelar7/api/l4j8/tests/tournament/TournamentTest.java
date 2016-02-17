package no.stelar7.api.l4j8.tests.tournament;

import java.util.List;

import no.stelar7.api.l4j8.basic.constants.TournamentMapType;
import no.stelar7.api.l4j8.basic.constants.TournamentPickType;
import no.stelar7.api.l4j8.basic.constants.TournamentSpectatorType;
import no.stelar7.api.l4j8.basic.constants.api.Server;
import no.stelar7.api.l4j8.impl.TournamentBuilder;
import no.stelar7.api.l4j8.pojo.match.MatchDetail;
import no.stelar7.api.l4j8.pojo.tournament.LobbyEventWrapper;
import no.stelar7.api.l4j8.pojo.tournament.ProviderRegistrationParameters;
import no.stelar7.api.l4j8.pojo.tournament.TournamentCode;
import no.stelar7.api.l4j8.pojo.tournament.TournamentCodeParameters;
import no.stelar7.api.l4j8.pojo.tournament.TournamentCodeUpdateParameters;
import no.stelar7.api.l4j8.pojo.tournament.TournamentRegistrationParameters;
import no.stelar7.api.l4j8.tests.SecretFile;

public class TournamentTest
{

    TournamentBuilder builder = new TournamentBuilder(SecretFile.TOURNAMENT_KEY);

//    @Test
    public void testAllRegistrations()
    {
        ProviderRegistrationParameters params = new ProviderRegistrationParameters(Server.EUW, "http://stelar7.no/loltest/provider.php");
        long providerId = builder.registerAsProvider(params);
        System.out.println(providerId);

        TournamentRegistrationParameters trparams = new TournamentRegistrationParameters("THE BEST TOURNAMENT EVER", providerId);
        long tournamentId = builder.registerTournament(trparams);
        System.out.println(tournamentId);

        int teamSize = 5;

        TournamentCodeUpdateParameters tcinner = new TournamentCodeUpdateParameters("0,1,2,3,4,5,6,7,8,9", TournamentMapType.SUMMONERS_RIFT, TournamentPickType.TOURNAMENT_DRAFT, TournamentSpectatorType.ALL);
        TournamentCodeParameters tcparams = new TournamentCodeParameters(tcinner, "THIS IS METADATA YOOO", teamSize);

        int actucalSize = tcparams.getAllowedSummonerIds().getParticipants().size();
        int teamTimesTwo = teamSize * 2;

        if (actucalSize != teamTimesTwo || teamSize < 1 || teamSize > 10)
        {
            throw new RuntimeException("Cant create a match with unbalanced teams (note that teamsize / 2 must = allowedSummonerIds.size) (" + teamTimesTwo + " != " + actucalSize);
        }

        List<String> codes = builder.generateTournamentCodes(tcparams, tournamentId, 1);

        System.out.println(codes);

        TournamentCodeUpdateParameters tcuparams = new TournamentCodeUpdateParameters("10,20,30,40,50,60,70,80,90,100", TournamentMapType.TWISTED_TREELINE, TournamentPickType.TOURNAMENT_DRAFT, TournamentSpectatorType.ALL);
        builder.updateTournament(tcuparams, codes.get(0));

        System.out.println(codes);

        TournamentCode id = builder.getTournamentInfo(codes.get(0));
        System.out.println(id);

        LobbyEventWrapper eventWrapper = builder.getTournamentLobbyInfo(codes.get(0));
        eventWrapper.getEventList().forEach(System.out::println);

    }

    // @Test
    public void testPostGameData()
    {
        String code = "EUW0418b-b9423c92-5733-4d1b-aff2-215229f96e8d";
        List<Long> tournamentCodeMatchIds = builder.getMatchIds(code);
        tournamentCodeMatchIds.forEach(System.out::println);

        MatchDetail matchDetail = builder.getMatchInfo(code, tournamentCodeMatchIds.get(0));
        System.out.println(matchDetail);
    }

//    @Test
    public void testTournamentRegistration()
    {
        int providerId = 199;
        TournamentRegistrationParameters params = new TournamentRegistrationParameters("THE BEST TOURNAMENT EVER", providerId);
        Long id = builder.registerTournament(params);
        System.out.println(id);
    }

//    @Test
    public void testTournamentProviderRegistration()
    {
        ProviderRegistrationParameters params = new ProviderRegistrationParameters(Server.EUW, "http://stelar7.no/loltest/provider.php");
        Long id = builder.registerAsProvider(params);
        System.out.println(id);
    }

//    @Test
    public void testTournamentCodeGeneration()
    {
        int tournamentId = 3107;
        int teamSize = 1;
        TournamentCodeUpdateParameters inner = new TournamentCodeUpdateParameters("19613950,22291359", TournamentMapType.SUMMONERS_RIFT, TournamentPickType.TOURNAMENT_DRAFT, TournamentSpectatorType.ALL);
        TournamentCodeParameters params = new TournamentCodeParameters(inner, "THIS IS METADATA YOOO", teamSize);
        if (params.getAllowedSummonerIds().getParticipants().size() != (teamSize * 2))
        {
            throw new RuntimeException("Cant create a match with unbalanced teams (note that teamsize / 2 must = allowedSummonerIds.size)");
        }
        List<String> codes = builder.generateTournamentCodes(params, tournamentId, 2);
        System.out.println(codes);

    }

//    @Test
    public void testTournamentCodeUpdate()
    {
        String tournamentCode = "EUW0418b-b9423c92-5733-4d1b-aff2-215229f96e8d";
        TournamentCodeUpdateParameters params = new TournamentCodeUpdateParameters("19613950,22291359", TournamentMapType.SUMMONERS_RIFT, TournamentPickType.TOURNAMENT_DRAFT, TournamentSpectatorType.ALL);
        builder.updateTournament(params, tournamentCode);
    }

//    @Test
    public void testTournamentCodeGet()
    {
        String tournamentCode = "EUW0418b-b9423c92-5733-4d1b-aff2-215229f96e8d";
        TournamentCode id = builder.getTournamentInfo(tournamentCode);
        System.out.println(id);
    }

//    @Test
    public void testTournamentLobbyEvents()
    {
        String tournamentCode = "EUW0418b-b9423c92-5733-4d1b-aff2-215229f96e8d";
        LobbyEventWrapper eventWrapper = builder.getTournamentLobbyInfo(tournamentCode);
        eventWrapper.getEventList().forEach(System.out::println);
    }

//    @Test
    public void testTournamentMatchIds()
    {
        String tournamentCode = "EUW0418b-b9423c92-5733-4d1b-aff2-215229f96e8d";
        List<Long> eventWrapper = builder.getMatchIds(tournamentCode);
        eventWrapper.forEach(System.out::println);
    }

//    @Test
    public void testTournamentMatchDetails()
    {
        String tournamentCode = "EUW0418b-b9423c92-5733-4d1b-aff2-215229f96e8d";
        MatchDetail eventWrapper = builder.getMatchInfo(tournamentCode, builder.getMatchIds(tournamentCode).get(0));
        System.out.println(eventWrapper);
    }

}
