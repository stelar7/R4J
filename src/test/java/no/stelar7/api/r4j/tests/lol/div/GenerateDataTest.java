package no.stelar7.api.r4j.tests.lol.div;

import no.stelar7.api.r4j.basic.constants.api.regions.LeagueShard;
import no.stelar7.api.r4j.basic.utils.Utils;
import no.stelar7.api.r4j.impl.R4J;
import no.stelar7.api.r4j.pojo.lol.match.v5.*;
import no.stelar7.api.r4j.pojo.lol.summoner.Summoner;
import no.stelar7.api.r4j.tests.SecretFile;
import org.junit.jupiter.api.*;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.*;

public class GenerateDataTest
{
    @Test
    @Disabled
    public void generateStuff() throws IOException
    {
        R4J           api      = new R4J(SecretFile.CREDS);
        Summoner      self     = Summoner.byName(LeagueShard.EUW1, "stelar7");
        MatchIterator iterator = self.getLeagueGames().getMatchIterator();
        
        int                     i         = 0;
        List<InternalMatchData> matchData = new ArrayList<>();
        for (LOLMatch m : iterator)
        {
            MatchParticipant  lookup = m.getParticipants().stream().filter(p -> p.getPuuid().equals(self.getPUUID())).findFirst().get();
            InternalMatchData data   = new InternalMatchData();
            data.queue = m.getQueue().commonName();
            data.win = lookup.didWin();
            data.startTime = m.getMatchCreationAsDate().toInstant().toEpochMilli();
            
            Map<String, InternalMatchSummoner> sums = new HashMap<>();
            for (MatchParticipant p : m.getParticipants())
            {
                InternalMatchSummoner sum = new InternalMatchSummoner();
                sum.name = p.getSummonerName();
                sum.champion = p.getChampionId();
                sum.summoner1 = p.getSummoner1Id();
                sum.summoner2 = p.getSummoner2Id();
                sum.perks = generatePerkList(p.getPerks());
                sum.kills = p.getKills();
                sum.deaths = p.getDeaths();
                sum.assists = p.getAssists();
                sum.level = p.getChampionLevel();
                sum.cs = p.getTotalMinionsKilled();
                List<Integer> items = Arrays.asList(p.getItem0(), p.getItem1(), p.getItem2(), p.getItem3(), p.getItem4(), p.getItem5(), p.getItem6());
                Collections.sort(items);
                sum.items = items;
                
                if (lookup.getTeam() == p.getTeam())
                {
                    data.team.add(sum);
                } else
                {
                    data.enemy.add(sum);
                }
                
                if (p.equals(lookup))
                {
                    data.lookup = sum;
                }
            }
            
            matchData.add(data);
            
            if (i++ > 10)
            {
                break;
            }
        }
        
        Files.write(Paths.get("C:\\Users\\Steffen\\Desktop\\output.json"), Utils.getGson().toJson(matchData).getBytes(StandardCharsets.UTF_8));
        
    }
    
    private List<Integer> generatePerkList(MatchPerks perks)
    {
        List<Integer> data = new ArrayList<>();
        perks.getPerkStyles().stream().flatMap(p -> p.getSelections().stream()).map(PerkSelection::getPerk).forEach(data::add);
        data.add(perks.getStatPerks().getOffense());
        data.add(perks.getStatPerks().getFlex());
        data.add(perks.getStatPerks().getDefense());
        Collections.sort(data);
        return data;
    }
    
    static class InternalMatchData
    {
        String                      queue;
        boolean                     win;
        long                        startTime;
        InternalMatchSummoner       lookup;
        List<InternalMatchSummoner> team  = new ArrayList<>();
        List<InternalMatchSummoner> enemy = new ArrayList<>();
    }
    
    static class InternalMatchSummoner
    {
        String        name;
        int           champion;
        int           summoner1;
        int           summoner2;
        List<Integer> perks;
        long          kills;
        long          deaths;
        long          assists;
        long          level;
        long          cs;
        List<Integer> items;
    }
    
    
}
