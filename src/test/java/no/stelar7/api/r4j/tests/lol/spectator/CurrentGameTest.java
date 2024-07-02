package no.stelar7.api.r4j.tests.lol.spectator;

import no.stelar7.api.r4j.basic.constants.api.regions.LeagueShard;
import no.stelar7.api.r4j.basic.constants.types.ApiKeyType;
import no.stelar7.api.r4j.basic.utils.LazyList;
import no.stelar7.api.r4j.impl.R4J;
import no.stelar7.api.r4j.impl.lol.raw.SpectatorAPI;
import no.stelar7.api.r4j.pojo.lol.spectator.SpectatorGameInfo;
import no.stelar7.api.r4j.pojo.lol.summoner.Summoner;
import no.stelar7.api.r4j.pojo.shared.RiotAccount;
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
        final R4J r4J = new R4J(SecretFile.CREDS);
        
        // Get a game in progess
        final List<SpectatorGameInfo> game = SpectatorAPI.getInstance().getFeaturedGames(LeagueShard.ME1);
        
        // Get a summoner from that game
        final String name    = game.get(1).getParticipants().get(1).getPuuid();
        RiotAccount  account = r4J.getAccountAPI().getAccountByPUUID(LeagueShard.ME1.toRegionShard(), name, ApiKeyType.LOL);
        Summoner     sum     = Summoner.byPUUID(LeagueShard.ME1, account.getPUUID());
        
        // get match history
        LazyList<String> matchsIds = r4J.getLoLAPI().getMatchAPI().getMatchList(LeagueShard.ME1.toRegionShard(), sum.getPUUID());
        matchsIds.loadFully();
        
        // Get game info
        final SpectatorGameInfo currentGame = SpectatorAPI.getInstance().getCurrentGame(LeagueShard.ME1, sum.getPUUID());
        if (currentGame != null)
        {
            doAssertions.accept(currentGame);
        }
    }
    
    @Test
    public void testCurrentGameFame()
    {
        final R4J         r4J     = new R4J(SecretFile.CREDS);
        RiotAccount       account = r4J.getAccountAPI().getAccountByTag(LeagueShard.EUW1.toRegionShard(), "stelar7", "STL7");
        Summoner          s       = Summoner.byPUUID(LeagueShard.EUW1, account.getPUUID());
        SpectatorGameInfo game    = s.getCurrentGame();
        
        System.out.format("%s is %sin game%n", s.getName(), game != null ? "" : "not ");
    }
}
