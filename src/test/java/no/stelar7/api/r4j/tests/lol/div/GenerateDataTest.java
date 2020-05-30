package no.stelar7.api.r4j.tests.lol.div;

import no.stelar7.api.r4j.basic.constants.api.regions.LeagueShard;
import no.stelar7.api.r4j.basic.utils.Utils;
import no.stelar7.api.r4j.impl.R4J;
import no.stelar7.api.r4j.pojo.lol.match.*;
import no.stelar7.api.r4j.pojo.lol.summoner.Summoner;
import no.stelar7.api.r4j.tests.SecretFile;
import org.junit.*;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.*;

public class GenerateDataTest
{
    @Test
    @Ignore
    public void generateStuff() throws IOException
    {
        R4J           api      = new R4J(SecretFile.CREDS);
        Summoner      self     = Summoner.byName(LeagueShard.EUW1, "stelar7");
        MatchIterator iterator = self.getLeagueGames().getMatchIterator();
        
        int                     i         = 0;
        List<InternalMatchData> matchData = new ArrayList<>();
        for (Match m : iterator)
        {
            Participant       lookup = m.getParticipant(self.getSummonerId()).get();
            InternalMatchData data   = new InternalMatchData();
            data.queue = m.getGameQueueType().commonName();
            data.win = m.didWin(lookup);
            data.startTime = m.getMatchCreationAsDate().toInstant().toEpochMilli();
            
            Map<String, InternalMatchSummoner> sums = new HashMap<>();
            for (Participant p : m.getParticipants())
            {
                InternalMatchSummoner sum = new InternalMatchSummoner();
                sum.name = m.getParticipantIdentity(p.getParticipantId()).get().getSummonerName();
                sum.champion = p.getChampionId();
                sum.summoner1 = p.getSpell1().getValue();
                sum.summoner2 = p.getSpell2().getValue();
                sum.perks = generatePerkList(p.getPerks());
                sum.kills = p.getStats().getKills();
                sum.deaths = p.getStats().getDeaths();
                sum.assists = p.getStats().getAssists();
                sum.level = p.getStats().getChampLevel();
                sum.cs = p.getStats().getTotalMinionsKilled();
                List<Long> items = Arrays.asList(p.getStats().getItem0(), p.getStats().getItem1(), p.getStats().getItem2(), p.getStats().getItem3(), p.getStats().getItem4(), p.getStats().getItem5(), p.getStats().getItem6());
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
        perks.getPerkList().stream().map(MatchPerk::getPerkId).forEach(data::add);
        data.add(perks.getStatPerk0());
        data.add(perks.getStatPerk1());
        data.add(perks.getStatPerk2());
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
        List<Long>    items;
    }
    
    
}
