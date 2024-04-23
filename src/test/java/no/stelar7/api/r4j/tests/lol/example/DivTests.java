package no.stelar7.api.r4j.tests.lol.example;

import no.stelar7.api.r4j.basic.constants.api.regions.LeagueShard;
import no.stelar7.api.r4j.impl.R4J;
import no.stelar7.api.r4j.impl.lol.raw.ImageAPI;
import no.stelar7.api.r4j.pojo.lol.match.v5.*;
import no.stelar7.api.r4j.pojo.lol.staticdata.champion.StaticChampion;
import no.stelar7.api.r4j.pojo.lol.summoner.Summoner;
import no.stelar7.api.r4j.pojo.shared.RiotAccount;
import no.stelar7.api.r4j.tests.SecretFile;
import org.junit.jupiter.api.Test;

import java.util.*;

public class DivTests
{
    @Test
    public void test()
    {
        R4J         api    = new R4J(SecretFile.CREDS);
        String      user   = "stelar7";
        LeagueShard region = LeagueShard.EUW1;
        
        RiotAccount                  account   = api.getAccountAPI().getAccountByTag(LeagueShard.EUW1.toRegionShard(), "stelar7", "STL7");
        Summoner                     summoner  = Summoner.byPUUID(LeagueShard.EUW1, account.getPUUID());
        Map<Integer, StaticChampion> champData = api.getDDragonAPI().getChampions();
        //pfp
        String pfp = ImageAPI.getInstance().getProfileIcon(region, user);
        //name and lv
        int    level = summoner.getSummonerLevel();
        String name  = summoner.getName();
        //most recent game
        List<String>     matches  = summoner.getLeagueGames().get();
        LOLMatch         match    = LOLMatch.get(region, matches.get(0));
        MatchParticipant self     = match.getParticipants().stream().filter(p -> p.getPuuid().equals(summoner.getPUUID())).findFirst().get();
        StaticChampion   champion = champData.get(self.getChampionId());
        MatchPerks       summs    = self.getPerks();
        boolean          won      = self.didWin();
        
        System.out.println("Profile icon: " + pfp);
        System.out.println(name + ", Level " + level);
        System.out.println();
        System.out.format(name + " %s their most recent game.", won ? "won" : "lost");
        System.out.println();
        System.out.println("They were playing " + self.getChampionSelectLane() + " " + champion.getName());
    }
}
