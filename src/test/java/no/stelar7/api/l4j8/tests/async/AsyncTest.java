package no.stelar7.api.l4j8.tests.async;

import no.stelar7.api.l4j8.basic.constants.api.*;
import no.stelar7.api.l4j8.impl.*;
import no.stelar7.api.l4j8.pojo.match.MatchReference;
import no.stelar7.api.l4j8.pojo.summoner.Summoner;
import no.stelar7.api.l4j8.tests.SecretFile;
import org.junit.Test;

import java.util.*;
import java.util.concurrent.CompletableFuture;

import static java.util.concurrent.CompletableFuture.*;


public class AsyncTest
{
    
    final L4J8 l4j8 = new L4J8(SecretFile.CREDS);
    
    @Test
    public void testAsync() throws InterruptedException
    {
        SummonerAPI sapi = l4j8.getSummonerAPI();
        MatchAPI    mapi = l4j8.getMatchAPI();
        
        List<CompletableFuture> futures = new ArrayList<>();
        
        for (int i = 0; i < 100; i++)
        {
            futures.add(
                    supplyAsync(() -> sapi.getSummonerByAccount(Platform.EUW1, Constants.TEST_ACCOUNT_IDS[1]))
                            .thenAccept(this::handleSummonerCallback)
                       );
        }
        
        futures.add(
                supplyAsync(() -> mapi.getMatchList(Platform.EUW1, Constants.TEST_ACCOUNT_IDS[1], null, null, null, null, null, null, null))
                        .thenAccept(this::handleMatchCallback)
                   );
        CompletableFuture spinner = CompletableFuture.allOf(futures.toArray(new CompletableFuture[futures.size()]));
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
