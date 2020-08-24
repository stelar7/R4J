package no.stelar7.api.r4j.tests.val;

import no.stelar7.api.r4j.basic.constants.api.regions.*;
import no.stelar7.api.r4j.basic.constants.types.val.FinishingDamageType;
import no.stelar7.api.r4j.impl.R4J;
import no.stelar7.api.r4j.impl.val.VALMatchAPI;
import no.stelar7.api.r4j.pojo.shared.RiotAccount;
import no.stelar7.api.r4j.pojo.val.match.*;
import no.stelar7.api.r4j.pojo.val.matchlist.MatchReference;
import no.stelar7.api.r4j.tests.SecretFile;
import org.junit.jupiter.api.Test;

import java.util.*;

public class TestVALMatch
{
    @Test
    public void getList()
    {
        R4J         api      = new R4J(SecretFile.CREDS);
        VALMatchAPI matchAPI = api.getVALAPI().getMatchAPI();
        
        RiotAccount account = api.getAccountAPI().getAccountByTag(RegionShard.EUROPE, "stelar7", "stl7");
        
        List<MatchReference> matchlist = matchAPI.getMatchList(ValorantShard.EU, account.getPUUID());
        System.out.println(matchlist);
    }
    
    @Test
    public void getSingle()
    {
        R4J         api      = new R4J(SecretFile.CREDS);
        VALMatchAPI matchAPI = api.getVALAPI().getMatchAPI();
        RiotAccount account  = api.getAccountAPI().getAccountByTag(RegionShard.EUROPE, "stelar7", "stl7");
        
        List<MatchReference> matchlist = matchAPI.getMatchList(ValorantShard.EU, account.getPUUID());
        Match                match     = matchAPI.getMatch(ValorantShard.EU, matchlist.get(0).getMatchId());
        System.out.println(match);
    }
    
    @Test
    public void getDeep()
    {
        //DataCall.setCacheProvider(new FileSystemCacheProvider(1));
        
        R4J         api      = new R4J(SecretFile.CREDS);
        VALMatchAPI matchAPI = api.getVALAPI().getMatchAPI();
        RiotAccount account  = api.getAccountAPI().getAccountByTag(RegionShard.EUROPE, "Jbzz", "EUW");
        
        List<String> ids = new ArrayList<>();
        ids.add(account.getPUUID());
        
        List<MatchReference> matchlist = matchAPI.getMatchList(ValorantShard.EU, account.getPUUID());
        matchlist.forEach(m -> {
            Match match = matchAPI.getMatch(ValorantShard.EU, m.getMatchId());
            match.getPlayers().forEach(p -> ids.add(p.getPUUID()));
        });
        
        ids.forEach(puuid -> {
            List<MatchReference> matches = matchAPI.getMatchList(ValorantShard.EU, puuid);
            matches.forEach(m -> {
                Match match = matchAPI.getMatch(ValorantShard.EU, m.getMatchId());
                match.getRoundResults().forEach(r -> {
                    r.getPlayerStats().forEach(s -> {
                        for (Kill kill : s.getKills())
                        {
                            FinishingDamage dmg = kill.getFinishingDamage();
                            if (dmg.getDamageType() == FinishingDamageType.ABILITY)
                            {
                                dmg.getDamageItemAsSkill();
                            }
                        }
                    });
                });
            });
        });
    }
}
