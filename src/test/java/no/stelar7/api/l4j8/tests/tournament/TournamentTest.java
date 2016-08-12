package no.stelar7.api.l4j8.tests.tournament;

import java.util.*;
import java.util.logging.*;

import no.stelar7.api.l4j8.basic.constants.*;
import no.stelar7.api.l4j8.basic.constants.api.*;
import no.stelar7.api.l4j8.basic.exceptions.*;
import no.stelar7.api.l4j8.impl.*;
import no.stelar7.api.l4j8.pojo.match.*;
import no.stelar7.api.l4j8.pojo.tournament.*;
import no.stelar7.api.l4j8.tests.*;

public class TournamentTest
{

    public static final Logger LOGGER  = Logger.getGlobal();
    TournamentAPI              builder = new L4J8(SecretFile.CREDS).getTournamentAPI();

    // @Test
    public void testAllRegistrations()
    {
        final ProviderRegistrationParameters params = new ProviderRegistrationParameters(Server.EUW, "http://stelar7.no/loltest/provider.php");
        final long providerId = this.builder.registerAsProvider(params);
        TournamentTest.LOGGER.log(Level.INFO, String.valueOf(providerId));

        final TournamentRegistrationParameters trparams = new TournamentRegistrationParameters("THE BEST TOURNAMENT EVER", providerId);
        final long tournamentId = this.builder.registerTournament(trparams);
        TournamentTest.LOGGER.log(Level.INFO, String.valueOf(tournamentId));

        final int teamSize = 5;

        final TournamentCodeUpdateParameters tcinner = new TournamentCodeUpdateParameters("0,1,2,3,4,5,6,7,8,9", TournamentMapType.SUMMONERS_RIFT, TournamentPickType.TOURNAMENT_DRAFT, TournamentSpectatorType.ALL);
        final TournamentCodeParameters tcparams = new TournamentCodeParameters(tcinner, "THIS IS METADATA YOOO", teamSize);

        final int actucalSize = tcparams.getAllowedSummonerIds().getParticipants().size();
        final int teamTimesTwo = teamSize * 2;

        if ((actucalSize != teamTimesTwo) || (teamSize < 1) || (teamSize > 10))
        {
            throw new APIUnsupportedAction("Cant create a match with unbalanced teams (note that teamsize / 2 must = allowedSummonerIds.size) (" + teamTimesTwo + " != " + actucalSize);
        }

        final List<String> codes = this.builder.generateTournamentCodes(tcparams, tournamentId, 1);

        TournamentTest.LOGGER.log(Level.INFO, String.valueOf(codes));

        final TournamentCodeUpdateParameters tcuparams = new TournamentCodeUpdateParameters("10,20,30,40,50,60,70,80,90,100", TournamentMapType.TWISTED_TREELINE, TournamentPickType.TOURNAMENT_DRAFT, TournamentSpectatorType.ALL);
        this.builder.updateTournament(tcuparams, codes.get(0));

        TournamentTest.LOGGER.log(Level.INFO, String.valueOf(codes));

        final TournamentCode id = this.builder.getTournamentInfo(codes.get(0));
        TournamentTest.LOGGER.log(Level.INFO, String.valueOf(id));

        final LobbyEventWrapper eventWrapper = this.builder.getTournamentLobbyInfo(codes.get(0));
        eventWrapper.getEventList().stream().map(String::valueOf).forEach(TournamentTest.LOGGER::info);

    }

    // @Test
    public void testPostGameData()
    {
        final List<Long> tournamentCodeMatchIds = this.builder.getMatchIds(Constants.TEST_TOURNAMENT_CODE, Server.EUW);
        tournamentCodeMatchIds.stream().map(String::valueOf).forEach(TournamentTest.LOGGER::info);

        final MatchDetail matchDetail = this.builder.getMatchInfo(Constants.TEST_TOURNAMENT_CODE, tournamentCodeMatchIds.get(0));
        TournamentTest.LOGGER.log(Level.INFO, String.valueOf(matchDetail));
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
            throw new APIUnsupportedAction("Cant create a match with unbalanced teams (note that teamsize / 2 must = allowedSummonerIds.size)");
        }
        final List<String> codes = this.builder.generateTournamentCodes(params, tournamentId, 2);
        TournamentTest.LOGGER.log(Level.INFO, String.valueOf(codes));

    }

    // @Test
    public void testTournamentCodeGet()
    {
        final TournamentCode id = this.builder.getTournamentInfo(Constants.TEST_TOURNAMENT_CODE);
        TournamentTest.LOGGER.log(Level.INFO, String.valueOf(id));
    }

    // @Test
    public void testTournamentCodeUpdate()
    {
        final TournamentCodeUpdateParameters params = new TournamentCodeUpdateParameters("19613950,22291359", TournamentMapType.SUMMONERS_RIFT, TournamentPickType.TOURNAMENT_DRAFT, TournamentSpectatorType.ALL);
        this.builder.updateTournament(params, Constants.TEST_TOURNAMENT_CODE);
    }

    // @Test
    public void testTournamentLobbyEvents()
    {
        final LobbyEventWrapper eventWrapper = this.builder.getTournamentLobbyInfo(Constants.TEST_TOURNAMENT_CODE);
        eventWrapper.getEventList().stream().map(lew -> lew.toString()).forEach(TournamentTest.LOGGER::info);
    }

    // @Test
    public void testTournamentMatchDetails()
    {
        final MatchDetail eventWrapper = this.builder.getMatchInfo(Constants.TEST_TOURNAMENT_CODE, this.builder.getMatchIds(Constants.TEST_TOURNAMENT_CODE, Server.EUW).get(0));
        TournamentTest.LOGGER.log(Level.INFO, String.valueOf(eventWrapper));
    }

    // @Test
    public void testTournamentMatchIds()
    {
        final List<Long> eventWrapper = this.builder.getMatchIds(Constants.TEST_TOURNAMENT_CODE, Server.EUW);
        eventWrapper.stream().map(String::valueOf).forEach(TournamentTest.LOGGER::info);
    }

    // @Test
    public void testTournamentProviderRegistration()
    {
        final ProviderRegistrationParameters params = new ProviderRegistrationParameters(Server.EUW, "http://stelar7.no/loltest/provider.php");
        final Long id = this.builder.registerAsProvider(params);
        TournamentTest.LOGGER.log(Level.INFO, String.valueOf(id));
    }

    // @Test
    public void testTournamentRegistration()
    {
        final int providerId = 199;
        final TournamentRegistrationParameters params = new TournamentRegistrationParameters("THE BEST TOURNAMENT EVER", providerId);
        final Long id = this.builder.registerTournament(params);
        TournamentTest.LOGGER.log(Level.INFO, String.valueOf(id));
    }

}
