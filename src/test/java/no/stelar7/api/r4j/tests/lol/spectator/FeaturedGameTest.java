package no.stelar7.api.r4j.tests.lol.spectator;

import no.stelar7.api.r4j.basic.constants.api.Platform;
import no.stelar7.api.r4j.impl.R4J;
import no.stelar7.api.r4j.impl.lol.builders.spectator.SpectatorBuilder;
import no.stelar7.api.r4j.pojo.lol.spectator.SpectatorGameInfo;
import no.stelar7.api.r4j.tests.SecretFile;
import org.junit.*;

import java.util.List;
import java.util.function.Consumer;

public class FeaturedGameTest
{
    Consumer<List<SpectatorGameInfo>> doAssertions = (final List<SpectatorGameInfo> currentGames) ->
    {
        for (SpectatorGameInfo featured : currentGames)
        {
            Assert.assertNotNull("bannedchampion is null", featured.getBannedChampions());
            Assert.assertNotNull("gamemode is null", featured.getGameMode());
            Assert.assertNotNull("gamequeuecongifid is null", featured.getGameQueueConfig());
            Assert.assertNotNull("gamestarttime is null", featured.getGameStartAsDate());
            Assert.assertNotNull("gametype is null", featured.getGameType());
            Assert.assertNotNull("mapid is null", featured.getMap());
            Assert.assertNotNull("observers is null", featured.getObservers());
            Assert.assertNotNull("participantsis null", featured.getParticipants());
        }
    };
    
    @Test
    public void testCurrentGame()
    {
        final R4J        r4J = new R4J(SecretFile.CREDS);
        SpectatorBuilder sb  = new SpectatorBuilder().withPlatform(Platform.EUW1);
        
        
        final List<SpectatorGameInfo> game = sb.getFeaturedGames();
        doAssertions.accept(game);
    }
    
}
