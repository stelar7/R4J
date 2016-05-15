package no.stelar7.api.l4j8.tests.tournament;

import java.util.*;

import no.stelar7.api.l4j8.basic.constants.*;
import no.stelar7.api.l4j8.basic.constants.api.*;
import no.stelar7.api.l4j8.impl.*;
import no.stelar7.api.l4j8.pojo.match.*;
import no.stelar7.api.l4j8.pojo.tournament.*;
import no.stelar7.api.l4j8.tests.*;

public class TournamentTest
{

    TournamentBuilder builder = new TournamentBuilder(SecretFile.TOURNAMENT_API_KEY);

    // @Test
    public void testAllRegistrations()
    {
        final ProviderRegistrationParameters params = new ProviderRegistrationParameters(Server.EUW, "http://stelar7.no/loltest/provider.php");
        final long providerId = this.builder.registerAsProvider(params);
        System.out.println(providerId);

        final TournamentRegistrationParameters trparams = new TournamentRegistrationParameters("THE BEST TOURNAMENT EVER", providerId);
        final long tournamentId = this.builder.registerTournament(trparams);
        System.out.println(tournamentId);

        final int teamSize = 5;

        final TournamentCodeUpdateParameters tcinner = new TournamentCodeUpdateParameters("0,1,2,3,4,5,6,7,8,9", TournamentMapType.SUMMONERS_RIFT, TournamentPickType.TOURNAMENT_DRAFT, TournamentSpectatorType.ALL);
        final TournamentCodeParameters tcparams = new TournamentCodeParameters(tcinner, "THIS IS METADATA YOOO", teamSize);

        final int actucalSize = tcparams.getAllowedSummonerIds().getParticipants().size();
        final int teamTimesTwo = teamSize * 2;

        if ((actucalSize != teamTimesTwo) || (teamSize < 1) || (teamSize > 10))
        {
            throw new RuntimeException("Cant create a match with unbalanced teams (note that teamsize / 2 must = allowedSummonerIds.size) (" + teamTimesTwo + " != " + actucalSize);
        }

        final List<String> codes = this.builder.generateTournamentCodes(tcparams, tournamentId, 1);

        System.out.println(codes);

        final TournamentCodeUpdateParameters tcuparams = new TournamentCodeUpdateParameters("10,20,30,40,50,60,70,80,90,100", TournamentMapType.TWISTED_TREELINE, TournamentPickType.TOURNAMENT_DRAFT, TournamentSpectatorType.ALL);
        this.builder.updateTournament(tcuparams, codes.get(0));

        System.out.println(codes);

        final TournamentCode id = this.builder.getTournamentInfo(codes.get(0));
        System.out.println(id);

        final LobbyEventWrapper eventWrapper = this.builder.getTournamentLobbyInfo(codes.get(0));
        eventWrapper.getEventList().forEach(System.out::println);

    }

    // @Test
    public void testPostGameData()
    {
        final String code = "EUW0418b-b9423c92-5733-4d1b-aff2-215229f96e8d";
        final List<Long> tournamentCodeMatchIds = this.builder.getMatchIds(code);
        tournamentCodeMatchIds.forEach(System.out::println);

        final MatchDetail matchDetail = this.builder.getMatchInfo(code, tournamentCodeMatchIds.get(0));
        System.out.println(matchDetail);
    }

    // @Test
    public void testTournamentCodeGeneration()
    {
        final int tournamentId = 3107;
        final int teamSize = 1;
        final TournamentCodeUpdateParameters inner = new TournamentCodeUpdateParameters("19613950,22291359", TournamentMapType.SUMMONERS_RIFT, TournamentPickType.TOURNAMENT_DRAFT, TournamentSpectatorType.ALL);
        final TournamentCodeParameters params = new TournamentCodeParameters(inner, "THIS IS METADATA YOOO", teamSize);
        if (params.getAllowedSummonerIds().getParticipants().size() != (teamSize * 2))
        {
            throw new RuntimeException("Cant create a match with unbalanced teams (note that teamsize / 2 must = allowedSummonerIds.size)");
        }
        final List<String> codes = this.builder.generateTournamentCodes(params, tournamentId, 2);
        System.out.println(codes);

    }

    // @Test
    public void testTournamentCodeGet()
    {
        final String tournamentCode = "EUW0418b-b9423c92-5733-4d1b-aff2-215229f96e8d";
        final TournamentCode id = this.builder.getTournamentInfo(tournamentCode);
        System.out.println(id);
    }

    // @Test
    public void testTournamentCodeUpdate()
    {
        final String tournamentCode = "EUW0418b-b9423c92-5733-4d1b-aff2-215229f96e8d";
        final TournamentCodeUpdateParameters params = new TournamentCodeUpdateParameters("19613950,22291359", TournamentMapType.SUMMONERS_RIFT, TournamentPickType.TOURNAMENT_DRAFT, TournamentSpectatorType.ALL);
        this.builder.updateTournament(params, tournamentCode);
    }

    // @Test
    public void testTournamentLobbyEvents()
    {
        final String tournamentCode = "EUW0418b-b9423c92-5733-4d1b-aff2-215229f96e8d";
        final LobbyEventWrapper eventWrapper = this.builder.getTournamentLobbyInfo(tournamentCode);
        eventWrapper.getEventList().forEach(System.out::println);
    }

    // @Test
    public void testTournamentMatchDetails()
    {
        final String tournamentCode = "EUW0418b-b9423c92-5733-4d1b-aff2-215229f96e8d";
        final MatchDetail eventWrapper = this.builder.getMatchInfo(tournamentCode, this.builder.getMatchIds(tournamentCode).get(0));
        System.out.println(eventWrapper);
    }

    // @Test
    public void testTournamentMatchIds()
    {
        final String tournamentCode = "EUW0418b-b9423c92-5733-4d1b-aff2-215229f96e8d";
        final List<Long> eventWrapper = this.builder.getMatchIds(tournamentCode);
        eventWrapper.forEach(System.out::println);
    }

    // @Test
    public void testTournamentProviderRegistration()
    {
        final ProviderRegistrationParameters params = new ProviderRegistrationParameters(Server.EUW, "http://stelar7.no/loltest/provider.php");
        final Long id = this.builder.registerAsProvider(params);
        System.out.println(id);
    }

    // @Test
    public void testTournamentRegistration()
    {
        final int providerId = 199;
        final TournamentRegistrationParameters params = new TournamentRegistrationParameters("THE BEST TOURNAMENT EVER", providerId);
        final Long id = this.builder.registerTournament(params);
        System.out.println(id);
    }

}
