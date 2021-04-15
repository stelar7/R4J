package no.stelar7.api.r4j.tests.lol.match;

import com.google.gson.JsonParser;
import com.google.gson.stream.JsonWriter;
import no.stelar7.api.r4j.basic.APICredentials;
import no.stelar7.api.r4j.basic.cache.impl.FileSystemCacheProvider;
import no.stelar7.api.r4j.basic.calling.DataCall;
import no.stelar7.api.r4j.basic.constants.api.regions.LeagueShard;
import no.stelar7.api.r4j.basic.constants.types.lol.*;
import no.stelar7.api.r4j.basic.utils.*;
import no.stelar7.api.r4j.impl.R4J;
import no.stelar7.api.r4j.impl.lol.builders.matchv5.match.*;
import no.stelar7.api.r4j.pojo.lol.match.v5.*;
import no.stelar7.api.r4j.pojo.lol.summoner.Summoner;
import org.junit.jupiter.api.*;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.*;

public class MatchListV5Test
{
    final R4J r4J = new R4J(new APICredentials("RGAPI-2364dba0-bd9a-48d7-94a1-cd7969ec3f3e"));
    
    
    @Test
    @Disabled
    public void testMatchInvalidSpellSlots() throws IOException
    {
        DataCall.setCacheProvider(new FileSystemCacheProvider());
        
        MatchListBuilder builder = new MatchListBuilder();
        Summoner         sum     = Summoner.byName(LeagueShard.EUW1, "stelar7");
        
        LazyList<String> all = sum.getLeagueGamesV5().getLazy();
        MatchBuilder     mb  = new MatchBuilder(sum.getPlatform());
        TimelineBuilder  tb  = new TimelineBuilder(sum.getPlatform());
        
        StringWriter sw = new StringWriter();
        JsonWriter   sb = new JsonWriter(sw);
        sb.beginObject();
        
        for (String matchid : all)
        {
            tb = tb.withId(matchid);
            mb = mb.withId(matchid);
            
            LOLMatch    match       = mb.getMatch();
            LOLTimeline lolTimeline = tb.getTimeline();
            
            List<TimelineDamageData> wierdEntries = new ArrayList<>();
            lolTimeline.getFrames().forEach(frame -> {
                frame.getEvents().forEach(event -> {
                    if (event.getType() == EventType.CHAMPION_KILL)
                    {
                        event.getVictimDamageReceived().forEach(d -> {
                            if (d.getSpellSlot() == SpellSlotType.INVALID || d.getSpellSlot() == SpellSlotType.UNDOCUMENTED || d.getSpellSlot() == SpellSlotType.UNUSED || d.getSpellSlot() == SpellSlotType.OUT_OF_BOUNDS)
                            {
                                wierdEntries.add(d);
                            }
                        });
                        
                        event.getVictimDamageDealt().forEach(d -> {
                            if (d.getSpellSlot() == SpellSlotType.INVALID || d.getSpellSlot() == SpellSlotType.UNDOCUMENTED || d.getSpellSlot() == SpellSlotType.UNUSED || d.getSpellSlot() == SpellSlotType.OUT_OF_BOUNDS)
                            {
                                wierdEntries.add(d);
                            }
                        });
                    }
                });
            });
            if (wierdEntries.size() > 0)
            {
                sb.name(tb.getID());
                sb.beginArray();
                for (TimelineDamageData wierdEntry : wierdEntries)
                {
                    sb.jsonValue(Utils.getGson().toJson(wierdEntry));
                }
                sb.endArray();
            }
        }
        sb.endObject();
        sb.flush();
        
        String output = Utils.getGson().toJson(new JsonParser().parse(sw.toString()));
        Files.write(Paths.get("C:\\Users\\Steffen\\Desktop\\Ny mappe\\errors.json"), output.getBytes(StandardCharsets.UTF_8));
    }
    
    @Test
    @Disabled
    public void testMatchParticipantIds()
    {
        DataCall.setCacheProvider(new FileSystemCacheProvider());
        
        MatchListBuilder builder = new MatchListBuilder();
        Summoner         sum     = Summoner.byName(LeagueShard.EUW1, "stelar7");
        
        LazyList<String> all = sum.getLeagueGamesV5().getLazy();
        
        MatchBuilder    mb = new MatchBuilder(sum.getPlatform());
        TimelineBuilder tb = new TimelineBuilder(sum.getPlatform());
        
        for (String matchid : all)
        {
            tb = tb.withId(matchid);
            mb = mb.withId(matchid);
            
            LOLMatch    match       = mb.getMatch();
            LOLTimeline lolTimeline = tb.getTimeline();
            
            List<TimelineDamageData> wierdEntries = new ArrayList<>();
            lolTimeline.getFrames().forEach(frame -> {
                
                frame.getParticipantFrames().forEach((k, v) -> {
                    if (!k.equals(String.valueOf(v.getParticipantId())))
                    {
                        System.out.println(matchid + " has mismatched participantid and key");
                    }
                });
            });
        }
    }
}


