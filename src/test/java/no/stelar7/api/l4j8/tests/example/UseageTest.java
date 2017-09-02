package no.stelar7.api.l4j8.tests.example;

import no.stelar7.api.l4j8.basic.cache.FileSystemCacheProvider;
import no.stelar7.api.l4j8.basic.calling.DataCall;
import no.stelar7.api.l4j8.basic.constants.api.*;
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
        DataCall.setLogLevel(LogLevel.INFO);
        DataCall.setCacheProvider(new FileSystemCacheProvider(null, -1));
        
        
        Summoner             stelar7        = api.getSummonerAPI().getSummonerByName(Platform.EUW1, "stelar7");
        List<MatchReference> some           = stelar7.getGames(null, null, null, null, null, null, null);
        MatchReference       mostRecentGame = some.stream().sorted(Comparator.comparing(MatchReference::getTimestamp).reversed()).findFirst().get();
        
        Match       match = mostRecentGame.getFullMatch();
        Participant self  = match.getParticipantFromSummonerId(stelar7.getSummonerId());
        
        List<MatchRune>    runes      = self.getRunes();
        List<MatchMastery> masteries  = self.getMasteries();
        List<LeagueList>   fullLeague = stelar7.getFullLeague();
        ChampionMastery    mastery    = stelar7.getChampionMastery(mostRecentGame.getChampion().getId());
        
        boolean didWin = match.didWin(self);
        
        ParticipantIdentity opponentIdentity = match.getLaneOpponentIdentity(self);
        Participant         opponent         = match.getParticipantFromParticipantId(opponentIdentity.getParticipantId());
        
        Map<Integer, StaticRune>    runeData      = api.getStaticAPI().getRunes(Platform.EUW1, EnumSet.of(RuneDataFlags.ALL), null, null);
        Map<Integer, StaticMastery> masteriesData = api.getStaticAPI().getMasteries(Platform.EUW1, EnumSet.of(MasteryDataFlags.ALL), null, null);
        
        
        System.out.format("Player '%s' played their latest game as '%s'%n", stelar7.getName(), mostRecentGame.getChampion().getName());
        
        System.out.format("They have a masteryscore of %s on that champion%n", mastery.getChampionPoints());
        
        System.out.format("They played in %s, their role was: %s%n", self.getTimeline().getLane(), self.getTimeline().getRole());
        
        System.out.format("They %s that game%n", didWin ? "won" : "lost");
        
        System.out.format("They laned against '%s' as '%s'%n", opponentIdentity.getPlayer().getSummonerName(), opponent.getChampion().getName());
        
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

    Poppy's Templars (RANKED_SOLO_5X5):
    There are 202 players in the league
    They currently in GOLD_II
    They are not in a promo to GOLD_I
    They have 34LP

    Soraka's Sentinels (RANKED_FLEX_SR):
    There are 200 players in the league
    They currently in SILVER_V
    They are not in a promo to SILVER_IV
    They have 0LP
     
     *
     */
}


