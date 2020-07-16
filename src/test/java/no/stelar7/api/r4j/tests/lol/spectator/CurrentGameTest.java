package no.stelar7.api.r4j.tests.lol.spectator;

import no.stelar7.api.r4j.basic.constants.api.regions.LeagueShard;
import no.stelar7.api.r4j.impl.R4J;
import no.stelar7.api.r4j.impl.lol.builders.spectator.SpectatorBuilder;
import no.stelar7.api.r4j.impl.lol.builders.summoner.SummonerBuilder;
import no.stelar7.api.r4j.pojo.lol.spectator.SpectatorGameInfo;
import no.stelar7.api.r4j.pojo.lol.summoner.Summoner;
import no.stelar7.api.r4j.tests.SecretFile;
import org.junit.jupiter.api.*;

import java.util.List;
import java.util.function.Consumer;

public class CurrentGameTest
{
    
    Consumer<SpectatorGameInfo> doAssertions = (final SpectatorGameInfo currentGame) ->
    {
        
        Assertions.assertNotNull(currentGame.getBannedChampions(), "bannedchampion is null");
        Assertions.assertNotNull(currentGame.getGameMode(), "gamemode is null");
        Assertions.assertNotNull(currentGame.getGameQueueConfig(), "gamequeuecongifid is null");
        Assertions.assertNotNull(currentGame.getGameStartAsDate(), "gamestarttime is null");
        Assertions.assertNotNull(currentGame.getGameType(), "gametype is null");
        Assertions.assertNotNull(currentGame.getMap(), "mapid is null");
        Assertions.assertNotNull(currentGame.getObservers(), "observers is null");
        Assertions.assertNotNull(currentGame.getParticipants(), "participants is null");
        
        // this was an issue somehow??
        Assertions.assertNotNull(currentGame.getParticipants().get(0).getSummonerId(), "participants has null id");
    };
    
    @Test
    public void testCurrentGame()
    {
        final R4J        r4J = new R4J(SecretFile.CREDS);
        SpectatorBuilder sb  = new SpectatorBuilder().withPlatform(LeagueShard.EUW1);
        
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
        final R4J         r4J  = new R4J(SecretFile.CREDS);
        Summoner          s    = Summoner.byName(LeagueShard.EUW1, "Klospülautomat");
        SpectatorGameInfo game = s.getCurrentGame();
        
        System.out.format("%s is %sin game%n", s.getName(), game != null ? "" : "not ");
    }
}
