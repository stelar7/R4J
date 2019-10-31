package no.stelar7.api.l4j8.tests.lol.div;

import no.stelar7.api.l4j8.basic.constants.api.Platform;
import no.stelar7.api.l4j8.impl.L4J8;
import no.stelar7.api.l4j8.impl.lol.builders.match.MatchListBuilder;
import no.stelar7.api.l4j8.impl.lol.builders.spectator.SpectatorBuilder;
import no.stelar7.api.l4j8.impl.lol.builders.summoner.SummonerBuilder;
import no.stelar7.api.l4j8.pojo.match.lol.*;
import no.stelar7.api.l4j8.pojo.summoner.Summoner;
import no.stelar7.api.l4j8.tests.SecretFile;
import org.junit.*;

import java.util.*;

public class CheckNormalGameForWinTest
{
    
    final L4J8 l4j8 = new L4J8(SecretFile.CREDS);
    
    @Test
    @Ignore
    public void testFindSelfInNormalGame()
    {
        Summoner             dev  = new SummonerBuilder().withPlatform(Platform.NA1).withName("devitgg").get();
        List<MatchReference> refs = new MatchListBuilder().withPlatform(Platform.NA1).withAccountId(dev.getAccountId()).get();
        
        for (MatchReference current : refs)
        {
            Match fullGame = current.getFullMatch();
            
            List<Participant> candidates = new ArrayList<>();
            
            for (Participant participant : fullGame.getParticipants())
            {
                if (participant.getChampionId() == current.getChampionId())
                {
                    candidates.add(participant);
                }
            }
            
            candidates.removeIf(candidate -> candidate.getTimeline().getLane() != current.getLane());
            candidates.removeIf(candidate -> candidate.getTimeline().getRole() != current.getRole());
            
            if (candidates.size() == 1)
            {
                Participant self = candidates.get(0);
                System.out.println("I " + (self.getStats().isWinner() ? "won!" : "lost :("));
            } else
            {
                System.out.println("Unable to find self!");
            }
        }
    }
    
    
    @Test
    @Ignore
    public void testFindSelfAfterNormalGame()
    {
        Summoner dev = new SummonerBuilder().withPlatform(Platform.NA1).withName("devitgg").get();
        
        while (new SpectatorBuilder().withPlatform(Platform.NA1).withSummonerId(dev.getSummonerId()).getCurrentGame() != null)
        {
            try
            {
                Thread.sleep(30000);
            } catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        }
        
        List<MatchReference> refs = new MatchListBuilder().withPlatform(Platform.NA1).withAccountId(dev.getAccountId()).get();
        
        for (MatchReference current : refs)
        {
            Match fullGame = current.getFullMatch();
            
            List<Participant> candidates = new ArrayList<>();
            
            for (Participant participant : fullGame.getParticipants())
            {
                if (participant.getChampionId() == current.getChampionId())
                {
                    candidates.add(participant);
                }
            }
            
            candidates.removeIf(candidate -> candidate.getTimeline().getLane() != current.getLane());
            candidates.removeIf(candidate -> candidate.getTimeline().getRole() != current.getRole());
            
            if (candidates.size() == 1)
            {
                Participant self = candidates.get(0);
                System.out.println("I " + (self.getStats().isWinner() ? "won!" : "lost :("));
            } else
            {
                System.out.println("Unable to find self!");
            }
        }
    }
}
