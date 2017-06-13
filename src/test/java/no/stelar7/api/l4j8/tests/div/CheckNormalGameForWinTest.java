package no.stelar7.api.l4j8.tests.div;

import no.stelar7.api.l4j8.basic.constants.api.Platform;
import no.stelar7.api.l4j8.impl.L4J8;
import no.stelar7.api.l4j8.pojo.match.*;
import no.stelar7.api.l4j8.pojo.summoner.Summoner;
import no.stelar7.api.l4j8.tests.SecretFile;
import org.junit.Test;

import java.util.*;

public class CheckNormalGameForWinTest
{
    
    final L4J8 l4j8 = new L4J8(SecretFile.CREDS);
    
    @Test
    public void testFindSelfInNormalGame()
    {
        Summoner             dev  = l4j8.getSummonerAPI().getSummonerByName(Platform.NA1, "devitgg");
        List<MatchReference> refs = l4j8.getMatchAPI().getRecentMatches(Platform.NA1, dev.getAccountId());
        
        for (MatchReference current : refs)
        {
            Match fullGame = current.getFullMatch();
            
            List<Participant> candidates = new ArrayList<>();
            
            for (Participant participant : fullGame.getParticipants())
            {
                if (participant.getChampion().getId() == current.getChampion().getId())
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
    public void testFindSelfAfterNormalGame()
    {
        Summoner dev = l4j8.getSummonerAPI().getSummonerByName(Platform.NA1, "devitgg");
        
        while (l4j8.getSpectatorAPI().getCurrentGame(Platform.NA1, dev.getSummonerId()) == null)
        {
            try
            {
                Thread.sleep(30000);
            } catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        }
        
        List<MatchReference> refs = l4j8.getMatchAPI().getRecentMatches(Platform.NA1, dev.getAccountId());
        
        for (MatchReference current : refs)
        {
            Match fullGame = current.getFullMatch();
            
            List<Participant> candidates = new ArrayList<>();
            
            for (Participant participant : fullGame.getParticipants())
            {
                if (participant.getChampion().getId() == current.getChampion().getId())
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
