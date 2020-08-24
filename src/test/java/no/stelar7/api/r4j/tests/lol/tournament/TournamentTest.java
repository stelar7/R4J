package no.stelar7.api.r4j.tests.lol.tournament;

import no.stelar7.api.r4j.basic.constants.api.regions.LeagueShard;
import no.stelar7.api.r4j.basic.constants.types.lol.*;
import no.stelar7.api.r4j.impl.R4J;
import no.stelar7.api.r4j.impl.lol.builders.spectator.SpectatorBuilder;
import no.stelar7.api.r4j.impl.lol.builders.summoner.SummonerBuilder;
import no.stelar7.api.r4j.impl.lol.raw.TournamentAPI;
import no.stelar7.api.r4j.pojo.lol.match.Match;
import no.stelar7.api.r4j.pojo.lol.spectator.*;
import no.stelar7.api.r4j.pojo.lol.tournament.*;
import no.stelar7.api.r4j.tests.SecretFile;
import org.junit.jupiter.api.*;

import java.util.*;
import java.util.stream.Collectors;

public class TournamentTest
{
    
    final TournamentAPI api = new R4J(SecretFile.CREDS).getLoLAPI().getTournamentAPI(true);
    
    @Test
    @Disabled
    public void testAllRegistrations()
    {
        final ProviderRegistrationParameters params     = new ProviderRegistrationParameters(LeagueShard.EUW1, "http://stelar7.no/loltest/provider.php");
        final long                           providerId = this.api.registerAsProvider(params);
        
        final TournamentRegistrationParameters trparams     = new TournamentRegistrationParameters("THE BEST TOURNAMENT EVER", providerId);
        final long                             tournamentId = this.api.registerTournament(trparams);
        
        final int teamSize = 5;
        
        SpectatorBuilder        spectatorBuilder = new SpectatorBuilder().withPlatform(LeagueShard.EUW1);
        final SpectatorGameInfo game             = spectatorBuilder.getFeaturedGames().get(0);
        List<String>            names            = game.getParticipants().stream().map(SpectatorParticipant::getSummonerName).collect(Collectors.toList());
        List<String>            ids              = new ArrayList<>();
        SummonerBuilder         sb               = new SummonerBuilder().withPlatform(LeagueShard.EUW1);
        for (String name : names)
        {
            ids.add(sb.withName(name).get().getSummonerId());
        }
        
        final TournamentCodeUpdateParameters tcinner  = new TournamentCodeUpdateParameters(ids, TournamentMapType.SUMMONERS_RIFT, TournamentPickType.TOURNAMENT_DRAFT, TournamentSpectatorType.ALL);
        final TournamentCodeParameters       tcparams = new TournamentCodeParameters(tcinner, "THIS IS METADATA YOOO", teamSize);
        
        final List<String> codes = this.api.generateTournamentCodes(tcparams, tournamentId, 1);
        
        
        final TournamentCodeUpdateParameters tcuparams = new TournamentCodeUpdateParameters(ids, TournamentMapType.TWISTED_TREELINE, TournamentPickType.TOURNAMENT_DRAFT, TournamentSpectatorType.ALL);
        
        final List<LobbyEvent> events = this.api.getTournamentLobbyInfo(codes.get(0));
        
        if (!api.isStub())
        {
            this.api.updateTournament(codes.get(0), tcuparams);
            final TournamentCode id                     = this.api.getTournamentInfo(codes.get(0));
            final List<Long>     tournamentCodeMatchIds = this.api.getMatchIds(LeagueShard.EUW1, codes.get(0));
            final Match          matchDetail            = this.api.getMatchInfo(LeagueShard.EUW1, codes.get(0), tournamentCodeMatchIds.get(0));
        }
    }
}
