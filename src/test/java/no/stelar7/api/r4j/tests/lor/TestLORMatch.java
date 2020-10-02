package no.stelar7.api.r4j.tests.lor;

import no.stelar7.api.r4j.basic.constants.api.regions.*;
import no.stelar7.api.r4j.impl.R4J;
import no.stelar7.api.r4j.impl.lor.LORMatchAPI;
import no.stelar7.api.r4j.pojo.lol.summoner.Summoner;
import no.stelar7.api.r4j.pojo.lor.match.*;
import no.stelar7.api.r4j.pojo.shared.GAMHSMatch;
import no.stelar7.api.r4j.tests.SecretFile;
import org.junit.jupiter.api.Test;

import java.util.List;

public class TestLORMatch
{
    final R4J         l4j8 = new R4J(SecretFile.CREDS);
    final LORMatchAPI api  = l4j8.getLORAPI().getMatchAPI();
    
    
    @Test
    public void testFetchList()
    {
        List<String> stelar7 = api.getMatchList(RuneterraShard.EUROPE, Summoner.byName(LeagueShard.EUW1, "stelar7").getPUUID());
        System.out.println();
    }
    
    @Test
    public void testFetchMatch()
    {
        LORMatch    match    = api.getMatch(RuneterraShard.EUROPE, "6f70a089-0bb6-4ad8-ab16-9979562aaa7d");
        LORMetadata metadata = api.getMetadata(RuneterraShard.EUROPE, "6f70a089-0bb6-4ad8-ab16-9979562aaa7d");
        System.out.println();
    }
    
    @Test
    public void testFetchMatchRAW()
    {
        GAMHSMatch stelar7 = api.getMatchRAW(RuneterraShard.EUROPE, "6f70a089-0bb6-4ad8-ab16-9979562aaa7d");
        System.out.println();
    }
    
    
    @Test
    public void testFetchMany()
    {
        List<String> list = api.getMatchList(RuneterraShard.EUROPE, Summoner.byName(LeagueShard.EUW1, "stelar7").getPUUID());
        list.forEach(id -> {
            LORMatch match = api.getMatch(RuneterraShard.EUROPE, id);
            System.out.println(match.getGameStartTimeUTC());
            System.out.println(match.getGameStartAsDate());
            System.out.println();
        });
    }
}
