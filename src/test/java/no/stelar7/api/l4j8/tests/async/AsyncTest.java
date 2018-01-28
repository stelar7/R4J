package no.stelar7.api.l4j8.tests.async;

import no.stelar7.api.l4j8.basic.constants.api.*;
import no.stelar7.api.l4j8.impl.L4J8;
import no.stelar7.api.l4j8.impl.builders.match.MatchListBuilder;
import no.stelar7.api.l4j8.impl.builders.summoner.SummonerBuilder;
import no.stelar7.api.l4j8.pojo.match.MatchReference;
import no.stelar7.api.l4j8.pojo.summoner.Summoner;
import no.stelar7.api.l4j8.tests.SecretFile;
import org.junit.*;

import java.util.*;
import java.util.concurrent.CompletableFuture;

import static java.util.concurrent.CompletableFuture.*;


public class AsyncTest
{
    
    final L4J8 l4j8 = new L4J8(SecretFile.CREDS);
    
    @Test
    @Ignore
    public void testAsync()
    {
        List<CompletableFuture> futures = new ArrayList<>();
        
        for (int i = 0; i < 100; i++)
        {
            futures.add(
                    supplyAsync(() -> new SummonerBuilder().withPlatform(Platform.EUW1).withAccountId(Constants.TEST_ACCOUNT_IDS[1]).get())
                            .thenAccept(this::handleSummonerCallback)
                       );
        }
        
        futures.add(
                supplyAsync(() -> new MatchListBuilder().withPlatform(Platform.EUW1).withAccountId(Constants.TEST_ACCOUNT_IDS[1]).get())
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
