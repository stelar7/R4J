package no.stelar7.api.l4j8.tests.example;

import no.stelar7.api.l4j8.basic.constants.api.Platform;
import no.stelar7.api.l4j8.basic.constants.flags.*;
import no.stelar7.api.l4j8.basic.constants.types.TierDivisionType;
import no.stelar7.api.l4j8.impl.L4J8;
import no.stelar7.api.l4j8.pojo.championmastery.ChampionMastery;
import no.stelar7.api.l4j8.pojo.league.*;
import no.stelar7.api.l4j8.pojo.match.*;
import no.stelar7.api.l4j8.pojo.staticdata.mastery.StaticMastery;
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
        
        Summoner             stelar7        = api.getSummonerAPI().getSummonerByName(Platform.EUW1, "stelar7");
        List<MatchReference> games          = stelar7.getRankedGames();
        MatchReference       mostRecentGame = games.get(0);
        
        Match         match    = mostRecentGame.getFullMatch();
        MatchTimeline timeline = mostRecentGame.getTimeline();
        
        System.out.format("Player '%s' played their latest game as '%s'%n", stelar7.getName(), mostRecentGame.getChampion().getName());
        
        ChampionMastery mastery = stelar7.getChampionMastery(mostRecentGame.getChampion().getId());
        System.out.format("They have a masteryscore of %s on that champion%n", mastery.getChampionPoints());
        
        Participant self   = match.getParticipantFromSummonerId(stelar7.getSummonerId());
        boolean     didWin = match.didWin(self);
        System.out.format("They %s that game%n", didWin ? "won" : "lost");
        
        
        ParticipantIdentity opponentIdentity = match.getLaneOpponentIdentity(self);
        Participant         opponent         = match.getParticipantFromParticipantId(opponentIdentity.getParticipantId());
        
        System.out.format("They laned against '%s' as '%s'%n", opponentIdentity.getPlayer().getSummonerName(), opponent.getChampion().getName());
        
        
        List<MatchRune>    runes     = self.getRunes();
        List<MatchMastery> masteries = self.getMasteries();
        
        Map<Integer, StaticRune>    runeData      = api.getStaticAPI().getRunes(Platform.EUW1, EnumSet.of(RuneDataFlags.ALL), null, null);
        Map<Integer, StaticMastery> masteriesData = api.getStaticAPI().getMasteries(Platform.EUW1, EnumSet.of(MasteryDataFlags.ALL), null, null);
        
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
        
        
        List<LeagueList> fullLeague = stelar7.getFullLeague();
        for (LeagueList leagueList : fullLeague)
        {
            LeagueItem       position     = leagueList.getLeagueItem(stelar7.getSummonerId());
            TierDivisionType tierDivision = leagueList.getTierDivisionType(stelar7.getSummonerId());
            
            System.out.format("%n%s (%s):%n", leagueList.getLeagueName(), leagueList.getQueueType());
            System.out.format("There are %s players in the league%n", leagueList.getEntries().size());
            
            System.out.format("They currently in %s%n", tierDivision);
            System.out.format("They are%sin a promo to %s%n", position.isInPromos() ? "" : " not ", tierDivision.getNext());
            System.out.format("They have %sLP%n", position.getLeaguePoints());
        }
    }
    
    /*
     *
     
     Player 'stelar7' played their latest game as 'Warwick'
     They have a masteryscore of 83079 on that champion
     They won that game
     They laned against 'Dynastro' as 'Shaco'
 
     They used the following runes:
     Name: 'Greater Mark of Attack Speed                 ' Count: 9
     Name: 'Greater Glyph of Scaling Magic Resist        ' Count: 3
     Name: 'Greater Glyph of Scaling Cooldown Reduction  ' Count: 6
     Name: 'Greater Seal of Scaling Health               ' Count: 9
     Name: 'Greater Quintessence of Attack Speed         ' Count: 3
 
     They used the following masteries:
     Name: 'Fury                                         ' Level: 5
     Name: 'Fresh Blood                                  ' Level: 1
     Name: 'Vampirism                                    ' Level: 1
     Name: 'Natural Talent                               ' Level: 4
     Name: 'Battle Trance                                ' Level: 1
     Name: 'Piercing Thoughts                            ' Level: 5
     Name: 'Fervor of Battle                             ' Level: 1
     Name: 'Recovery                                     ' Level: 5
     Name: 'Explorer                                     ' Level: 1
     Name: 'Runic Armor                                  ' Level: 5
     Name: 'Insight                                      ' Level: 1
 
     Poppy's Templars (RANKED_SOLO_5X5):
     There are 200 players in the league
     They currently in GOLD_III
     They are not in a promo to GOLD_II
     They have 34LP
 
     Soraka's Sentinels (RANKED_FLEX_SR):
     There are 200 players in the league
     They currently in SILVER_V
     They are not in a promo to SILVER_IV
     They have 26LP
     
     *
     */
}


