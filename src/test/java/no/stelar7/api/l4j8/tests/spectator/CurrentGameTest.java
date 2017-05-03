package no.stelar7.api.l4j8.tests.spectator;

import no.stelar7.api.l4j8.basic.constants.api.Platform;
import no.stelar7.api.l4j8.impl.*;
import no.stelar7.api.l4j8.pojo.spectator.SpectatorGameInfo;
import no.stelar7.api.l4j8.pojo.summoner.Summoner;
import no.stelar7.api.l4j8.tests.SecretFile;
import org.junit.*;

import java.util.List;
import java.util.function.Consumer;

public class CurrentGameTest
{
    
    Consumer<SpectatorGameInfo> doAssertions = (final SpectatorGameInfo currentGame) ->
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
        final List<SpectatorGameInfo> game = api.getFeaturedGames(Platform.EUW1);
        
        // Get a summoner from that game
        final String   name = game.get(0).getParticipants().get(0).getSummonerName();
        final Summoner sum  = l4j8.getSummonerAPI().getSummonerByName(Platform.EUW1, name);
        
        // Get game info
        final SpectatorGameInfo currentGame = api.getCurrentGame(Platform.EUW1, sum.getSummonerId());
        doAssertions.accept(currentGame);
    }
}
