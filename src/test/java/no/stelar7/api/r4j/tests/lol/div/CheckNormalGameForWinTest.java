package no.stelar7.api.r4j.tests.lol.div;

import no.stelar7.api.r4j.basic.constants.api.regions.LeagueShard;
import no.stelar7.api.r4j.impl.R4J;
import no.stelar7.api.r4j.impl.lol.builders.match.MatchListBuilder;
import no.stelar7.api.r4j.impl.lol.builders.spectator.SpectatorBuilder;
import no.stelar7.api.r4j.impl.lol.builders.summoner.SummonerBuilder;
import no.stelar7.api.r4j.pojo.lol.match.v4.*;
import no.stelar7.api.r4j.pojo.lol.summoner.Summoner;
import no.stelar7.api.r4j.tests.SecretFile;
import org.junit.jupiter.api.*;

import java.util.*;

public class CheckNormalGameForWinTest
{
    
    final R4J r4J = new R4J(SecretFile.CREDS);
    
    @Test
    @Disabled
    public void testFindSelfInNormalGame()
    {
        Summoner             dev  = new SummonerBuilder().withPlatform(LeagueShard.NA1).withName("devitgg").get();
        List<MatchReference> refs = new MatchListBuilder().withPlatform(LeagueShard.NA1).withAccountId(dev.getAccountId()).get();
        
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
    @Disabled
    public void testFindSelfAfterNormalGame()
    {
        Summoner dev = new SummonerBuilder().withPlatform(LeagueShard.NA1).withName("devitgg").get();
        
        while (new SpectatorBuilder().withPlatform(LeagueShard.NA1).withSummonerId(dev.getSummonerId()).getCurrentGame() != null)
        {
            try
            {
                Thread.sleep(30000);
            } catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        }
        
        List<MatchReference> refs = new MatchListBuilder().withPlatform(LeagueShard.NA1).withAccountId(dev.getAccountId()).get();
        
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
