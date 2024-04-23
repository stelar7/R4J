package no.stelar7.api.r4j.tests.lol.div;

import no.stelar7.api.r4j.basic.constants.api.regions.LeagueShard;
import no.stelar7.api.r4j.impl.R4J;
import no.stelar7.api.r4j.impl.lol.builders.matchv5.match.MatchListBuilder;
import no.stelar7.api.r4j.pojo.lol.match.v5.*;
import no.stelar7.api.r4j.pojo.lol.summoner.Summoner;
import no.stelar7.api.r4j.pojo.shared.RiotAccount;
import no.stelar7.api.r4j.tests.SecretFile;
import org.junit.jupiter.api.*;

import java.util.List;

public class CheckNormalGameForWinTest
{
    
    final R4J r4j = new R4J(SecretFile.CREDS);
    
    @Test
    @Disabled
    public void testFindSelfInNormalGame()
    {
        RiotAccount  account  = r4j.getAccountAPI().getAccountByTag(LeagueShard.EUW1.toRegionShard(), "stelar7", "STL7");
        Summoner     summoner = Summoner.byPUUID(LeagueShard.EUW1, account.getPUUID());
        List<String> refs     = new MatchListBuilder().withPlatform(LeagueShard.NA1).withPuuid(summoner.getPUUID()).get();
        
        for (String current : refs)
        {
            LOLMatch         match = LOLMatch.get(LeagueShard.NA1, current);
            MatchParticipant self  = match.getParticipants().stream().filter(p -> p.getPuuid().equals(summoner.getPUUID())).findFirst().get();
            
            System.out.println("I " + (self.didWin() ? "won!" : "lost :("));
        }
    }
}
