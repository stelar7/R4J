package no.stelar7.api.r4j.tests.lol.spectator;

import no.stelar7.api.r4j.basic.constants.api.regions.LeagueShard;
import no.stelar7.api.r4j.impl.R4J;
import no.stelar7.api.r4j.impl.lol.builders.spectator.SpectatorBuilder;
import no.stelar7.api.r4j.pojo.lol.spectator.SpectatorGameInfo;
import no.stelar7.api.r4j.tests.SecretFile;
import org.junit.jupiter.api.*;

import java.util.List;
import java.util.function.Consumer;

public class FeaturedGameTest
{
    Consumer<List<SpectatorGameInfo>> doAssertions = (final List<SpectatorGameInfo> currentGames) ->
    {
        for (SpectatorGameInfo featured : currentGames)
        {
            Assertions.assertNotNull(featured.getBannedChampions(), "bannedchampion is null");
            Assertions.assertNotNull(featured.getGameMode(), "gamemode is null");
            Assertions.assertNotNull(featured.getGameQueueConfig(), "gamequeuecongifid is null");
            Assertions.assertNotNull(featured.getGameStartAsDate(), "gamestarttime is null");
            Assertions.assertNotNull(featured.getGameType(), "gametype is null");
            Assertions.assertNotNull(featured.getMap(), "mapid is null");
            Assertions.assertNotNull(featured.getObservers(), "observers is null");
            Assertions.assertNotNull(featured.getParticipants(), "participants is null");
        }
    };
    
    @Test
    public void testCurrentGame()
    {
        final R4J        r4J = new R4J(SecretFile.CREDS);
        SpectatorBuilder sb  = new SpectatorBuilder().withPlatform(LeagueShard.EUW1);
        
        
        final List<SpectatorGameInfo> game = sb.getFeaturedGames();
        doAssertions.accept(game);
    }
    
}
