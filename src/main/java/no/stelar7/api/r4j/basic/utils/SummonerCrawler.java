package no.stelar7.api.r4j.basic.utils;

import no.stelar7.api.r4j.impl.lol.raw.LeagueAPI;
import no.stelar7.api.r4j.pojo.lol.match.v5.MatchParticipant;
import no.stelar7.api.r4j.pojo.lol.summoner.Summoner;

import java.util.*;
import java.util.stream.Collectors;

public class SummonerCrawler
{
    private final Set<Summoner> results = new HashSet<>();
    private       int           limit   = -1;
    
    public SummonerCrawler(Summoner start)
    {
        this(start, -1);
    }
    
    public SummonerCrawler(Summoner start, int limit)
    {
        results.add(start);
        this.limit = limit;
    }
    
    public Set<Summoner> get()
    {
        return results;
    }
    
    public void crawlGames()
    {
        results.iterator().forEachRemaining(summoner -> {
            if (limit > 0 && results.size() > limit)
            {
                return;
            }
            
            summoner.getLeagueGames().getMatchIterator().forEach(g -> {
                if (limit > 0 && results.size() > limit)
                {
                    return;
                }
                
                results.addAll(
                        g.getParticipants().stream()
                         .map(MatchParticipant::getPuuid)
                         .map(p -> Summoner.byPUUID(g.getPlatform(), p))
                         .collect(Collectors.toSet()));
            });
        });
    }
    
    public void crawlLeague()
    {
        results.iterator().forEachRemaining(summoner -> summoner.getLeagueEntry().forEach(l -> {
            if (limit > 0 && results.size() > limit)
            {
                return;
            }
            
            LeagueAPI.getInstance().getLeague(summoner.getPlatform(), l.getLeagueId()).getEntries().forEach(e -> {
                if (limit > 0 && results.size() > limit)
                {
                    return;
                }
                
                results.add(Summoner.bySummonerId(summoner.getPlatform(), e.getSummonerId()));
            });
        }));
    }
    
}
