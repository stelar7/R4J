package no.stelar7.api.r4j.tests.async;

import no.stelar7.api.r4j.basic.constants.api.Platform;
import no.stelar7.api.r4j.impl.R4J;
import no.stelar7.api.r4j.impl.lol.async.*;
import no.stelar7.api.r4j.impl.lol.raw.SpectatorAPI;
import no.stelar7.api.r4j.pojo.lol.match.MatchReference;
import no.stelar7.api.r4j.pojo.lol.spectator.*;
import no.stelar7.api.r4j.pojo.lol.summoner.Summoner;
import no.stelar7.api.r4j.tests.SecretFile;
import org.junit.*;

import java.util.*;
import java.util.concurrent.CompletableFuture;


public class AsyncTest
{
    
    final R4J r4J = new R4J(SecretFile.CREDS);
    
    @Test
    @Ignore
    public void testAsync()
    {
        AsyncSummonerAPI        as      = AsyncSummonerAPI.getInstance();
        AsyncMatchAPI           am      = AsyncMatchAPI.getInstance();
        List<CompletableFuture> futures = new ArrayList<>();
        
        List<Summoner> summoners = new ArrayList<>();
        for (Platform p : Platform.getSpectatorPlatforms())
        {
            List<SpectatorGameInfo>    games        = SpectatorAPI.getInstance().getFeaturedGames(p);
            SpectatorGameInfo          gameInfo     = games.get(0);
            List<SpectatorParticipant> participants = gameInfo.getParticipants();
            SpectatorParticipant       participant  = participants.get(0);
            
            String   id = participant.getSummonerName();
            Platform pl = gameInfo.getPlatform();
            
            Summoner s = Summoner.byName(pl, id);
            summoners.add(s);
        }
        
        for (Summoner s : summoners)
        {
            for (int i = 0; i < 100; i++)
            {
                futures.add(as.getSummonerByAccount(s.getPlatform(), s.getAccountId()).thenAccept(this::handleSummonerCallback));
                futures.add(am.getMatchList(s.getPlatform(), s.getAccountId()).thenAccept(this::handleMatchCallback));
            }
        }
        
        CompletableFuture spinner = CompletableFuture.allOf(futures.toArray(new CompletableFuture[0]));
        spinner.join();
    }
    
    private void handleMatchCallback(List<MatchReference> matchReferences)
    {
        System.out.println(matchReferences);
    }
    
    private void handleSummonerCallback(Summoner u)
    {
        System.out.println(u);
    }
}
