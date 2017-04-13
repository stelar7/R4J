package no.stelar7.api.l4j8.tests.spectator;

import no.stelar7.api.l4j8.basic.constants.api.Platform;
import no.stelar7.api.l4j8.impl.*;
import no.stelar7.api.l4j8.pojo.featuredgames.*;
import no.stelar7.api.l4j8.tests.SecretFile;
import org.junit.*;

import java.util.Optional;
import java.util.function.Consumer;

public class FeaturedGameTest
{
    Consumer<FeaturedGames> doAssertions = (final FeaturedGames currentGames) ->
    {
        for (FeaturedGameInfo featured : currentGames.getGameList())
        {
            Assert.assertNotNull("bannedchampion is null", featured.getBannedChampions());
            Assert.assertNotNull("gameid is null", featured.getGameId());
            Assert.assertNotNull("gamelength is null", featured.getGameLength());
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
        final L4J8   l4j8 = new L4J8(SecretFile.CREDS);
        SpectatorAPI api  = l4j8.getSpectatorAPI();
        
        final Optional<FeaturedGames> game = api.getFeaturedGames(Platform.EUW1);
        Assert.assertTrue("No data returned", game.isPresent());
        game.ifPresent(doAssertions);
    }
    
}
