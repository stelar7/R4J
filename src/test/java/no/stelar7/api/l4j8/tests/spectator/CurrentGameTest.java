package no.stelar7.api.l4j8.tests.spectator;

import no.stelar7.api.l4j8.basic.constants.api.Platform;
import no.stelar7.api.l4j8.impl.*;
import no.stelar7.api.l4j8.pojo.currentgame.CurrentGameInfo;
import no.stelar7.api.l4j8.pojo.featuredgames.FeaturedGames;
import no.stelar7.api.l4j8.pojo.summoner.Summoner;
import no.stelar7.api.l4j8.tests.SecretFile;
import org.junit.*;

import java.util.Optional;
import java.util.function.Consumer;

public class CurrentGameTest
{
    
    Consumer<CurrentGameInfo> doAssertions = (final CurrentGameInfo currentGame) ->
    {
        
        Assert.assertNotNull("bannedchampion is null", currentGame.getBannedChampions());
        Assert.assertNotNull("gameid is null", currentGame.getGameId());
        Assert.assertNotNull("gamelength is null", currentGame.getGameLength());
        Assert.assertNotNull("gamemode is null", currentGame.getGameMode());
        Assert.assertNotNull("gamequeuecongifid is null", currentGame.getGameQueueConfig());
        Assert.assertNotNull("gamestarttime is null", currentGame.getGameStartTimeAsDate());
        Assert.assertNotNull("gametype is null", currentGame.getGameType());
        Assert.assertNotNull("mapid is null", currentGame.getMap());
        Assert.assertNotNull("observers is null", currentGame.getObservers());
        Assert.assertNotNull("participantsis null", currentGame.getParticipants());
        
    };
    
    @Test
    public void testCurrentGame()
    {
        final L4J8   l4j8 = new L4J8(SecretFile.CREDS);
        SpectatorAPI api  = l4j8.getSpectatorAPI();
        
        // Get a game in progess
        final Optional<FeaturedGames> game = api.getFeaturedGames(Platform.EUW1);
        Assert.assertTrue("No data returned", game.isPresent());
        
        // Get a summoner from that game
        final String             name = game.get().getGameList().get(0).getParticipants().get(0).getSummonerName();
        final Optional<Summoner> sum  = l4j8.getSummonerAPI().getSummonerByName(Platform.EUW1, name);
        Assert.assertTrue("No data returned", sum.isPresent());
        
        // Get game info
        final Optional<CurrentGameInfo> currentGame = api.getCurrentGame(Platform.EUW1, sum.get().getId());
        Assert.assertTrue("No data returned", currentGame.isPresent());
        currentGame.ifPresent(doAssertions);
    }
}
