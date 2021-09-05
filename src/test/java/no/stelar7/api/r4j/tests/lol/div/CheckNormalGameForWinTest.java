package no.stelar7.api.r4j.tests.lol.div;

import no.stelar7.api.r4j.basic.constants.api.regions.LeagueShard;
import no.stelar7.api.r4j.impl.R4J;
import no.stelar7.api.r4j.impl.lol.builders.matchv5.match.MatchListBuilder;
import no.stelar7.api.r4j.impl.lol.builders.summoner.SummonerBuilder;
import no.stelar7.api.r4j.pojo.lol.match.v5.*;
import no.stelar7.api.r4j.pojo.lol.summoner.Summoner;
import no.stelar7.api.r4j.tests.SecretFile;
import org.junit.jupiter.api.*;

import java.util.List;

public class CheckNormalGameForWinTest
{
    
    final R4J r4J = new R4J(SecretFile.CREDS);
    
    @Test
    @Disabled
    public void testFindSelfInNormalGame()
    {
        Summoner     summoner = new SummonerBuilder().withPlatform(LeagueShard.NA1).withName("devitgg").get();
        List<String> refs     = new MatchListBuilder().withPlatform(LeagueShard.NA1).withPuuid(summoner.getPUUID()).get();
        
        for (String current : refs)
        {
            LOLMatch         match = LOLMatch.get(LeagueShard.NA1, current);
            MatchParticipant self  = match.getParticipants().stream().filter(p -> p.getPuuid().equals(summoner.getPUUID())).findFirst().get();
            
            System.out.println("I " + (self.didWin() ? "won!" : "lost :("));
        }
    }
}
