package no.stelar7.api.r4j.tests.lol.example;

import no.stelar7.api.r4j.basic.constants.api.Platform;
import no.stelar7.api.r4j.impl.R4J;
import no.stelar7.api.r4j.impl.lol.builders.summoner.SummonerBuilder;
import no.stelar7.api.r4j.impl.lol.raw.ImageAPI;
import no.stelar7.api.r4j.pojo.lol.match.*;
import no.stelar7.api.r4j.pojo.lol.staticdata.champion.StaticChampion;
import no.stelar7.api.r4j.pojo.lol.summoner.Summoner;
import no.stelar7.api.r4j.tests.SecretFile;
import org.junit.Test;

import java.util.*;

public class DivTests
{
    @Test
    public void test()
    {
        R4J    api  = new R4J(SecretFile.CREDS);
        String user = "Kalturi";
        Platform region = Platform.NA1;
        
        Summoner                     summoner  = new SummonerBuilder().withPlatform(region).withName(user).get();
        Map<Integer, StaticChampion> champData = api.getDDragonAPI().getChampions();
        //pfp
        String pfp = ImageAPI.getInstance().getProfileIcon(region, user);
        //name and lv
        Integer level = summoner.getSummonerLevel();
        String  name  = summoner.getName();
        //most recent game
        List<MatchReference> matches       = summoner.getLeagueGames().get();
        MatchReference recentGame = matches.stream().max(Comparator.comparing(MatchReference::getTimestamp)).get();
        Match          match      = recentGame.getFullMatch();
        Participant    self       = match.getParticipantFromSummonerId(summoner.getSummonerId()); //game data for user (summs, champ etc)
        StaticChampion champion   = champData.get(recentGame.getChampionId());
        MatchPerks     summs      = self.getPerks();
        boolean        won        = match.didWin(self);
        ParticipantIdentity  opponentId    = match.getLaneOpponentIdentity(self); //get lane opponent id
        Participant          opponent      = match.getParticipantFromParticipantId(opponentId.getParticipantId()); //summs, champ, etc for lane opponent
        StaticChampion       opponentChamp = champData.get(opponent.getChampionId());
        
        
        System.out.println("Profile icon: " + pfp);
        System.out.println(name + ", Level " + level);
        System.out.println();
        System.out.format(name + " %s their most recent game.", won ? "won" : "lost");
        System.out.println();
        System.out.println("They were playing " + self.getTimeline().getLane() + " " + champion.getName() + " against " + opponentChamp.getName() + ".");
    }
}
