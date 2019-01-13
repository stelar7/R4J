package no.stelar7.api.l4j8.tests.example;

import no.stelar7.api.l4j8.basic.cache.impl.FileSystemCacheProvider;
import no.stelar7.api.l4j8.basic.calling.DataCall;
import no.stelar7.api.l4j8.basic.constants.api.*;
import no.stelar7.api.l4j8.impl.L4J8;
import no.stelar7.api.l4j8.impl.builders.summoner.SummonerBuilder;
import no.stelar7.api.l4j8.pojo.championmastery.ChampionMastery;
import no.stelar7.api.l4j8.pojo.match.*;
import no.stelar7.api.l4j8.pojo.staticdata.champion.StaticChampion;
import no.stelar7.api.l4j8.pojo.staticdata.mastery.StaticMastery;
import no.stelar7.api.l4j8.pojo.staticdata.perk.*;
import no.stelar7.api.l4j8.pojo.staticdata.rune.StaticRune;
import no.stelar7.api.l4j8.pojo.summoner.Summoner;
import no.stelar7.api.l4j8.tests.SecretFile;
import org.junit.Test;

import java.util.*;

public class UseageTest
{
    @Test
    public void testUseage()
    {
        L4J8 api = new L4J8(SecretFile.CREDS);
        DataCall.setLogLevel(LogLevel.NONE);
        DataCall.setCacheProvider(new FileSystemCacheProvider());
        
        Map<Integer, StaticRune>     runeData      = api.getDDragonAPI().getRunes();
        Map<Integer, StaticMastery>  masteriesData = api.getDDragonAPI().getMasteries();
        Map<Integer, StaticChampion> championData  = api.getDDragonAPI().getChampions();
        Map<Integer, StaticPerk>     perkData      = api.getDDragonAPI().getPerks();
        
        Summoner             stelar7        = new SummonerBuilder().withPlatform(Platform.EUW1).withName("stelar7").get();
        List<MatchReference> some           = stelar7.getGames().get();
        MatchReference       mostRecentGame = some.stream().max(Comparator.comparing(MatchReference::getTimestamp)).get();
        
        Match       match = mostRecentGame.getFullMatch();
        Participant self  = match.getParticipantFromSummonerId(stelar7.getSummonerId());
        
        List<MatchRune>    runes     = self.getRunes();
        List<MatchMastery> masteries = self.getMasteries();
        MatchPerks         perks     = self.getPerks();
        StaticChampion     champion  = championData.get(mostRecentGame.getChampionId());
        
        ChampionMastery mastery = stelar7.getChampionMastery(champion.getId());
        
        boolean didWin = match.didWin(self);
        
        ParticipantIdentity opponentIdentity = match.getLaneOpponentIdentity(self);
        Participant         opponent         = match.getParticipantFromParticipantId(opponentIdentity.getParticipantId());
        StaticChampion      opponentChampion = championData.get(opponent.getChampionId());
        
        System.out.format("Player '%s' played their latest game as '%s'%n", stelar7.getName(), champion.getName());
        
        System.out.format("They have a masteryscore of %s on that champion%n", mastery.getChampionPoints());
        
        System.out.format("They played in %s, their role was: %s%n", self.getTimeline().getLane(), self.getTimeline().getRole());
        
        System.out.format("They %s that game%n", didWin ? "won" : "lost");
        
        System.out.format("They laned against '%s' as '%s'%n", opponentIdentity.getSummonerName(), opponentChampion.getName());
        
        if (!runes.isEmpty() && !masteries.isEmpty())
        {
            System.out.format("%nThey used the following runes:%n");
            for (MatchRune rune : runes)
            {
                String name = runeData.get(rune.getRuneId()).getName();
                System.out.format("Name: '%-45s' Count: %s%n", name, rune.getRank());
            }
            
            System.out.format("%nThey used the following masteries:%n");
            for (MatchMastery matchMastery : masteries)
            {
                String name = masteriesData.get(matchMastery.getMasteryId()).getName();
                System.out.format("Name: '%-45s' Level: %s%n", name, matchMastery.getRank());
            }
        } else
        {
            System.out.format("%nThey used the following perks:%n");
            for (MatchPerk perk : perks.getPerkList())
            {
                StaticPerk perkInfo = perkData.get(perk.getPerkId());
                String     name     = perkInfo.getName();
                System.out.format("Name: '%-20s' variables: %-5s, %-5s, %-5s%n", name, perk.getPerkVar1(), perk.getPerkVar2(), perk.getPerkVar3());
            }
        }
    }
    
    /*
     *
     
    Player 'stelar7' played their latest game as 'Brand'
    They have a masteryscore of 23129 on that champion
    They played in BOT, their role was: DUO_SUPPORT
    They lost that game
    They laned against 'zanram' as 'Janna'

    They used the following runes:
    Name: 'Greater Mark of Magic Penetration            ' Count: 9
    Name: 'Greater Glyph of Ability Power               ' Count: 9
    Name: 'Greater Seal of Armor                        ' Count: 9
    Name: 'Greater Quintessence of Ability Power        ' Count: 3

    They used the following masteries:
    Name: 'Sorcery                                      ' Level: 5
    Name: 'Expose Weakness                              ' Level: 1
    Name: 'Natural Talent                               ' Level: 5
    Name: 'Double Edged Sword                           ' Level: 1
    Name: 'Piercing Thoughts                            ' Level: 5
    Name: 'Deathfire Touch                              ' Level: 1
    Name: 'Wanderer                                     ' Level: 5
    Name: 'Secret Stash                                 ' Level: 1
    Name: 'Merciless                                    ' Level: 5
    Name: 'Dangerous Game                               ' Level: 1
    
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


