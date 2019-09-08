package no.stelar7.api.l4j8.tests.spectator;

import no.stelar7.api.l4j8.basic.constants.api.Platform;
import no.stelar7.api.l4j8.impl.L4J8;
import no.stelar7.api.l4j8.impl.builders.spectator.SpectatorBuilder;
import no.stelar7.api.l4j8.impl.builders.summoner.SummonerBuilder;
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
        Assert.assertNotNull("gamemode is null", currentGame.getGameMode());
        Assert.assertNotNull("gamequeuecongifid is null", currentGame.getGameQueueConfig());
        Assert.assertNotNull("gamestarttime is null", currentGame.getGameStartAsDate());
        Assert.assertNotNull("gametype is null", currentGame.getGameType());
        Assert.assertNotNull("mapid is null", currentGame.getMap());
        Assert.assertNotNull("observers is null", currentGame.getObservers());
        Assert.assertNotNull("participants is null", currentGame.getParticipants());
        
        // this was an issue somehow??
        Assert.assertNotNull("participants has null id", currentGame.getParticipants().get(0).getSummonerId());
    };
    
    @Test
    public void testCurrentGame()
    {
        final L4J8       l4j8 = new L4J8(SecretFile.CREDS);
        SpectatorBuilder sb   = new SpectatorBuilder().withPlatform(Platform.EUW1);
        
        // Get a game in progess
        final List<SpectatorGameInfo> game = sb.getFeaturedGames();
        
        // Get a summoner from that game
        final String   name = game.get(0).getParticipants().get(0).getSummonerName();
        final Summoner sum  = new SummonerBuilder().withPlatform(game.get(0).getPlatform()).withName(name).get();
        
        // Get game info
        final SpectatorGameInfo currentGame = sb.withSummonerId(sum.getSummonerId()).getCurrentGame();
        if (currentGame != null)
        {
            doAssertions.accept(currentGame);
        }
    }
    
    @Test
    public void testCurrentGameFame()
    {
        final L4J8        l4j8 = new L4J8(SecretFile.CREDS);
        Summoner          s    = Summoner.byName(Platform.EUW1, "Klospülautomat");
        SpectatorGameInfo game = s.getCurrentGame();
        
        System.out.format("%s is %sin game%n", s.getName(), game != null ? "" : "not ");
    }
}
