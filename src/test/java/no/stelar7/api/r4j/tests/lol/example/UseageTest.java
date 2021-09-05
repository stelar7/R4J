package no.stelar7.api.r4j.tests.lol.example;

import no.stelar7.api.r4j.basic.cache.impl.FileSystemCacheProvider;
import no.stelar7.api.r4j.basic.calling.DataCall;
import no.stelar7.api.r4j.basic.constants.api.regions.LeagueShard;
import no.stelar7.api.r4j.impl.R4J;
import no.stelar7.api.r4j.pojo.lol.championmastery.ChampionMastery;
import no.stelar7.api.r4j.pojo.lol.match.v5.*;
import no.stelar7.api.r4j.pojo.lol.staticdata.champion.StaticChampion;
import no.stelar7.api.r4j.pojo.lol.staticdata.mastery.StaticMastery;
import no.stelar7.api.r4j.pojo.lol.staticdata.perk.StaticPerk;
import no.stelar7.api.r4j.pojo.lol.staticdata.rune.StaticRune;
import no.stelar7.api.r4j.pojo.lol.summoner.Summoner;
import no.stelar7.api.r4j.tests.SecretFile;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Collectors;

public class UseageTest
{
    @Test
    public void testUseage()
    {
        R4J api = new R4J(SecretFile.CREDS);
        DataCall.setCacheProvider(new FileSystemCacheProvider());
        
        Map<Integer, StaticRune>     runeData      = api.getDDragonAPI().getRunes();
        Map<Integer, StaticMastery>  masteriesData = api.getDDragonAPI().getMasteries();
        Map<Integer, StaticChampion> championData  = api.getDDragonAPI().getChampions();
        Map<Integer, StaticPerk>     perkData      = api.getDDragonAPI().getPerks();
        
        Summoner     summoner = Summoner.byName(LeagueShard.EUW1, "stelar7");
        List<String> some     = summoner.getLeagueGames().get();
        
        LOLMatch         match = LOLMatch.get(summoner.getPlatform(), some.get(0));
        MatchParticipant self  = match.getParticipants().stream().filter(p -> p.getPuuid().equals(summoner.getPUUID())).findFirst().get();
        
        StaticChampion champion = championData.get(self.getChampionId());
        
        ChampionMastery mastery = summoner.getChampionMastery(champion.getId());
        boolean         didWin  = self.didWin();
        
        System.out.format("Player '%s' played their latest game as '%s'%n", summoner.getName(), champion.getName());
        System.out.format("They have a masteryscore of %s on that champion%n", mastery.getChampionPoints());
        System.out.format("They played in %s, their role was: %s%n", self.getChampionSelectLane(), self.getRole());
        System.out.format("They %s that game%n", didWin ? "won" : "lost");
        
        
        System.out.format("%nThey used the following perks:%n");
        List<PerkSelection> perks = self.getPerks().getPerkStyles().stream().flatMap(s -> s.getSelections().stream()).collect(Collectors.toList());
        for (PerkSelection perk : perks)
        {
            StaticPerk perkInfo = perkData.get(perk.getPerk());
            String     name     = perkInfo.getName();
            System.out.format("Name: '%-20s' variables: %-5s, %-5s, %-5s%n", name, perk.getVar1(), perk.getVar2(), perk.getVar3());
        }
    }
    
    /*
     *
     
    Player 'stelar7' played their latest game as 'Brand'
    They have a masteryscore of 23129 on that champion
    They played in BOT, their role was: DUO_SUPPORT
    They lost that game
    
    They used the following perks:
    Name: 'Fleet Footwork           ' variables: 725  , 0    , 0
    Name: 'Triumph                  ' variables: 223  , 100  , 0
    Name: 'Legend: Alacrity         ' variables: 24   , 0    , 0
    Name: 'Coup de Grace            ' variables: 82   , 0    , 0
    Name: 'Gathering Storm          ' variables: 14   , 0    , 0
    Name: 'Nullifying Orb           ' variables: 510  , 0    , 0

     
     *
     */
}


