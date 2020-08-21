package no.stelar7.api.r4j.tests.val;

import no.stelar7.api.r4j.basic.constants.api.regions.*;
import no.stelar7.api.r4j.impl.R4J;
import no.stelar7.api.r4j.impl.val.VALMatchAPI;
import no.stelar7.api.r4j.pojo.shared.RiotAccount;
import no.stelar7.api.r4j.pojo.val.match.Match;
import no.stelar7.api.r4j.pojo.val.matchlist.MatchReference;
import no.stelar7.api.r4j.tests.SecretFile;
import org.junit.jupiter.api.Test;

import java.util.List;

public class TestVALMatch
{
    @Test
    public void getGetList()
    {
        R4J           api     = new R4J(SecretFile.CREDS);
        VALMatchAPI   matchAPI = api.getVALAPI().getMatchAPI();
    
        RiotAccount account = api.getAccountAPI().getAccountByTag(RegionShard.EUROPE, "stelar7", "stl7");
    
        List<MatchReference> matchlist = matchAPI.getMatchList(ValorantShard.EU, account.getPUUID());
        System.out.println(matchlist);
    }
    
    @Test
    public void getGetSingle()
    {
        R4J           api     = new R4J(SecretFile.CREDS);
        VALMatchAPI          matchAPI  = api.getVALAPI().getMatchAPI();
        RiotAccount account = api.getAccountAPI().getAccountByTag(RegionShard.EUROPE, "stelar7", "stl7");
    
        List<MatchReference> matchlist = matchAPI.getMatchList(ValorantShard.EU, account.getPUUID());
        Match match = matchAPI.getMatch(ValorantShard.EU, matchlist.get(0).getMatchId());
        System.out.println(match);
    }
}
