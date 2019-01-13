package no.stelar7.api.l4j8.tests.example;

import no.stelar7.api.l4j8.basic.constants.api.Platform;
import no.stelar7.api.l4j8.impl.L4J8;
import no.stelar7.api.l4j8.impl.builders.summoner.SummonerBuilder;
import no.stelar7.api.l4j8.impl.raw.ImageAPI;
import no.stelar7.api.l4j8.pojo.match.*;
import no.stelar7.api.l4j8.pojo.staticdata.champion.StaticChampion;
import no.stelar7.api.l4j8.pojo.summoner.Summoner;
import no.stelar7.api.l4j8.tests.SecretFile;
import org.junit.Test;

import java.util.*;

public class DivTests
{
    @Test
    public void test()
    {
        L4J8     api    = new L4J8(SecretFile.CREDS);
        String   user   = "Kalturi";
        Platform region = Platform.NA1;
        
        Summoner                     summoner  = new SummonerBuilder().withPlatform(region).withName(user).get();
        Map<Integer, StaticChampion> champData = api.getDDragonAPI().getChampions();
        //pfp
        String pfp = ImageAPI.getInstance().getProfileIcon(region, user);
        //name and lv
        Integer level = summoner.getSummonerLevel();
        String  name  = summoner.getName();
        //most recent game
        List<MatchReference> matches       = summoner.getGames().get();
        MatchReference       recentGame    = matches.stream().max(Comparator.comparing(MatchReference::getTimestamp)).get();
        Match                match         = recentGame.getFullMatch();
        Participant          self          = match.getParticipantFromSummonerId(summoner.getSummonerId()); //game data for user (summs, champ etc)
        StaticChampion       champion      = champData.get(recentGame.getChampionId());
        MatchPerks           summs         = self.getPerks();
        boolean              won           = match.didWin(self);
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
