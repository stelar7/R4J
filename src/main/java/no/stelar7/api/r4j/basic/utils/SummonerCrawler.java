package no.stelar7.api.r4j.basic.utils;

import no.stelar7.api.r4j.impl.lol.raw.LeagueAPI;
import no.stelar7.api.r4j.pojo.lol.match.ParticipantIdentity;
import no.stelar7.api.r4j.pojo.lol.summoner.Summoner;

import java.util.*;
import java.util.stream.Collectors;

public class SummonerCrawler
{
    private final Set<Summoner> results = new HashSet<>();
    
    public SummonerCrawler(Summoner start)
    {
        results.add(start);
    }
    
    public Set<Summoner> get()
    {
        return results;
    }
    
    public void crawlGames()
    {
        results.iterator().forEachRemaining(summoner -> {
            summoner.getLeagueGames().getMatchIterator().forEach(g -> results.addAll(g.getParticipantIdentities().stream().map(ParticipantIdentity::getCurrentSummoner).collect(Collectors.toSet())));
        });
    }
    
    public void crawlLeague()
    {
        results.iterator().forEachRemaining(summoner -> summoner.getLeagueEntry().forEach(l -> {
            LeagueAPI.getInstance().getLeague(summoner.getPlatform(), l.getLeagueId()).getEntries().forEach(e -> results.add(Summoner.bySummonerId(summoner.getPlatform(), e.getSummonerId())));
        }));
    }
    
}
