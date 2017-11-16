package no.stelar7.api.l4j8.tests.spectator;

import no.stelar7.api.l4j8.basic.constants.api.Platform;
import no.stelar7.api.l4j8.impl.L4J8;
import no.stelar7.api.l4j8.impl.builders.spectator.SpectatorBuilder;
import no.stelar7.api.l4j8.pojo.spectator.SpectatorGameInfo;
import no.stelar7.api.l4j8.tests.SecretFile;
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
            Assert.assertNotNull("gamestarttime is null", featured.getGameStartTimeAsDate());
            Assert.assertNotNull("gametype is null", featured.getGameType());
            Assert.assertNotNull("mapid is null", featured.getMap());
            Assert.assertNotNull("observers is null", featured.getObservers());
            Assert.assertNotNull("participantsis null", featured.getParticipants());
        }
    };
    
    @Test
    public void testCurrentGame()
    {
        final L4J8       l4j8 = new L4J8(SecretFile.CREDS);
        SpectatorBuilder sb   = new SpectatorBuilder().withPlatform(Platform.EUW1);
        
        
        final List<SpectatorGameInfo> game = sb.getFeaturedGames();
        doAssertions.accept(game);
    }
    
}
