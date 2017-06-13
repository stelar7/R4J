package no.stelar7.api.l4j8.tests.tournament;

import no.stelar7.api.l4j8.basic.constants.api.*;
import no.stelar7.api.l4j8.basic.constants.types.*;
import no.stelar7.api.l4j8.basic.exceptions.APIUnsupportedAction;
import no.stelar7.api.l4j8.impl.*;
import no.stelar7.api.l4j8.pojo.match.Match;
import no.stelar7.api.l4j8.pojo.tournament.*;
import no.stelar7.api.l4j8.tests.SecretFile;
import org.junit.*;

import java.util.*;
import java.util.logging.*;

@Ignore
public class TournamentTest
{
    
    public static final Logger        LOGGER = Logger.getGlobal();
    final               TournamentAPI api    = new L4J8(SecretFile.CREDS).getTournamentAPI();
    
    
    @Test
    public void testAllRegistrations()
    {
        final ProviderRegistrationParameters params     = new ProviderRegistrationParameters(Platform.EUW1, "http://stelar7.no/loltest/provider.php");
        final long                           providerId = this.api.registerAsProvider(Platform.EUROPE, params);
        TournamentTest.LOGGER.log(Level.INFO, String.valueOf(providerId));
        
        final TournamentRegistrationParameters trparams     = new TournamentRegistrationParameters("THE BEST TOURNAMENT EVER", providerId);
        final long                             tournamentId = this.api.registerTournament(Platform.EUROPE, trparams);
        TournamentTest.LOGGER.log(Level.INFO, String.valueOf(tournamentId));
        
        final int teamSize = 5;
        
        final TournamentCodeUpdateParameters tcinner  = new TournamentCodeUpdateParameters(Arrays.asList(0L, 1L, 2L, 3L, 4L, 5L, 6L, 7L, 8L, 9L), TournamentMapType.SUMMONERS_RIFT, TournamentPickType.TOURNAMENT_DRAFT, TournamentSpectatorType.ALL);
        final TournamentCodeParameters       tcparams = new TournamentCodeParameters(tcinner, "THIS IS METADATA YOOO", teamSize);
        
        final int actucalSize  = tcparams.getAllowedSummonerIds().getParticipants().size();
        final int teamTimesTwo = teamSize * 2;
        
        if ((tcparams.getTeamSize() < 1) || (tcparams.getTeamSize() > 5) || tcparams.getTeamSize() % 2 != 0)
        {
            throw new APIUnsupportedAction("Cant create a match with unbalanced teams (note that teamsize / 2 must = allowedSummonerIds.size) (" + teamTimesTwo + " != " + actucalSize);
        }
        
        final List<String> codes = this.api.generateTournamentCodes(Platform.EUROPE, tcparams, tournamentId, 1);
        
        TournamentTest.LOGGER.log(Level.INFO, String.valueOf(codes));
        
        final TournamentCodeUpdateParameters tcuparams = new TournamentCodeUpdateParameters(Arrays.asList(10L, 20L, 30L, 40L, 50L, 60L, 70L, 80L, 90L, 100L), TournamentMapType.TWISTED_TREELINE, TournamentPickType.TOURNAMENT_DRAFT, TournamentSpectatorType.ALL);
        this.api.updateTournament(Platform.EUROPE, codes.get(0), tcuparams);
        
        TournamentTest.LOGGER.log(Level.INFO, String.valueOf(codes));
        
        final TournamentCode id = this.api.getTournamentInfo(Platform.EUROPE, codes.get(0));
        TournamentTest.LOGGER.log(Level.INFO, String.valueOf(id));
        
        final List<LobbyEvent> events = this.api.getTournamentLobbyInfo(Platform.EUROPE, codes.get(0));
        events.stream().map(String::valueOf).forEach(TournamentTest.LOGGER::info);
        
    }
    
    @Test
    public void testPostGameData()
    {
        final List<Long> tournamentCodeMatchIds = this.api.getMatchIds(Platform.EUROPE, Constants.TEST_TOURNAMENT_CODES[0]);
        tournamentCodeMatchIds.stream().map(String::valueOf).forEach(TournamentTest.LOGGER::info);
        
        final Match matchDetail = this.api.getMatchInfo(Platform.EUROPE, Constants.TEST_TOURNAMENT_CODES[0], tournamentCodeMatchIds.get(0));
        TournamentTest.LOGGER.log(Level.INFO, String.valueOf(matchDetail));
    }
    
    @Test
    public void testTournamentCodeGeneration()
    {
        final int                            tournamentId = 3107;
        final int                            teamSize     = 1;
        final TournamentCodeUpdateParameters inner        = new TournamentCodeUpdateParameters(Arrays.asList(19613950L, 22291359L), TournamentMapType.SUMMONERS_RIFT, TournamentPickType.TOURNAMENT_DRAFT, TournamentSpectatorType.ALL);
        final TournamentCodeParameters       params       = new TournamentCodeParameters(inner, "THIS IS METADATA YOOO", teamSize);
        if (params.getAllowedSummonerIds().getParticipants().size() != (teamSize * 2))
        {
            throw new APIUnsupportedAction("Cant create a match with unbalanced teams (note that teamsize / 2 must = allowedSummonerIds.size)");
        }
        final List<String> codes = this.api.generateTournamentCodes(Platform.EUROPE, params, tournamentId, 2);
        TournamentTest.LOGGER.log(Level.INFO, String.valueOf(codes));
        
    }
    
    @Test
    public void testTournamentCodeGet()
    {
        final TournamentCode id = this.api.getTournamentInfo(Platform.EUROPE, Constants.TEST_TOURNAMENT_CODES[0]);
        TournamentTest.LOGGER.log(Level.INFO, String.valueOf(id));
    }
    
    @Test
    public void testTournamentCodeUpdate()
    {
        final TournamentCodeUpdateParameters params = new TournamentCodeUpdateParameters(Arrays.asList(19613950L, 22291359L), TournamentMapType.SUMMONERS_RIFT, TournamentPickType.TOURNAMENT_DRAFT, TournamentSpectatorType.ALL);
        this.api.updateTournament(Platform.EUROPE, Constants.TEST_TOURNAMENT_CODES[0], params);
    }
    
    @Test
    public void testTournamentLobbyEvents()
    {
        final List<LobbyEvent> events = this.api.getTournamentLobbyInfo(Platform.EUROPE, Constants.TEST_TOURNAMENT_CODES[0]);
        events.stream().map(LobbyEvent::toString).forEach(TournamentTest.LOGGER::info);
    }
    
    @Test
    public void testTournamentMatchDetails()
    {
        final Match eventWrapper = this.api.getMatchInfo(Platform.EUROPE, Constants.TEST_TOURNAMENT_CODES[0], this.api.getMatchIds(Platform.EUROPE, Constants.TEST_TOURNAMENT_CODES[0]).get(0));
        TournamentTest.LOGGER.log(Level.INFO, String.valueOf(eventWrapper));
    }
    
    @Test
    public void testTournamentMatchIds()
    {
        final List<Long> eventWrapper = this.api.getMatchIds(Platform.EUROPE, Constants.TEST_TOURNAMENT_CODES[0]);
        eventWrapper.stream().map(String::valueOf).forEach(TournamentTest.LOGGER::info);
    }
    
    @Test
    public void testTournamentProviderRegistration()
    {
        final ProviderRegistrationParameters params = new ProviderRegistrationParameters(Platform.EUROPE, "http://stelar7.no/loltest/provider.php");
        final Long                           id     = this.api.registerAsProvider(Platform.EUROPE, params);
        TournamentTest.LOGGER.log(Level.INFO, String.valueOf(id));
    }
    
    @Test
    public void testTournamentRegistration()
    {
        final int                              providerId = 199;
        final TournamentRegistrationParameters params     = new TournamentRegistrationParameters("THE BEST TOURNAMENT EVER", providerId);
        final Long                             id         = this.api.registerTournament(Platform.EUROPE, params);
        TournamentTest.LOGGER.log(Level.INFO, String.valueOf(id));
    }
    
    
}
