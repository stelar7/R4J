package no.stelar7.api.r4j.tests.val;

import ch.qos.logback.classic.*;
import com.google.gson.*;
import no.stelar7.api.r4j.basic.cache.impl.FileSystemCacheProvider;
import no.stelar7.api.r4j.basic.calling.DataCall;
import no.stelar7.api.r4j.basic.constants.api.URLEndpoint;
import no.stelar7.api.r4j.basic.constants.api.regions.*;
import no.stelar7.api.r4j.basic.constants.types.val.GameQueueType;
import no.stelar7.api.r4j.basic.utils.Utils;
import no.stelar7.api.r4j.impl.R4J;
import no.stelar7.api.r4j.impl.val.VALMatchAPI;
import no.stelar7.api.r4j.pojo.shared.RiotAccount;
import no.stelar7.api.r4j.pojo.val.match.*;
import no.stelar7.api.r4j.pojo.val.matchlist.*;
import no.stelar7.api.r4j.tests.SecretFile;
import org.junit.jupiter.api.*;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.*;
import java.util.stream.Collectors;

public class TestVALMatch
{
    @Test
    public void getRecent()
    {
        R4J         api      = new R4J(SecretFile.CREDS);
        VALMatchAPI matchAPI = api.getVALAPI().getMatchAPI();
        
        RecentMatchList recentMatches = matchAPI.getRecentMatches(ValorantShard.EU, GameQueueType.COMPETITIVE);
        System.out.println(recentMatches);
    }
    
    @Test
    public void getList()
    {
        DataCall.setCacheProvider(new FileSystemCacheProvider());
        DataCall.getCacheProvider().clear(URLEndpoint.V1_VAL_MATCHLIST_BY_PUUID, new HashMap<>());
        
        R4J         api      = new R4J(SecretFile.CREDS);
        VALMatchAPI matchAPI = api.getVALAPI().getMatchAPI();
        
        RiotAccount account = api.getAccountAPI().getAccountByTag(RegionShard.EUROPE, "stelar7", "stl7");
        
        List<MatchReference> matchlist = matchAPI.getMatchList(ValorantShard.EU, account.getPUUID());
        System.out.println(matchlist);
    }
    
    @Test
    public void getSingle() throws IOException
    {
        R4J         api      = new R4J(SecretFile.CREDS);
        VALMatchAPI matchAPI = api.getVALAPI().getMatchAPI();
        RiotAccount account  = api.getAccountAPI().getAccountByTag(RegionShard.EUROPE, "Coup De Graçe", "EUNE");
        
        List<MatchReference> matchlist = matchAPI.getMatchList(ValorantShard.EU, account.getPUUID());
        VALMatch             match     = matchAPI.getMatch(ValorantShard.EU, matchlist.get(0).getMatchId());
        Files.write(Paths.get(System.getProperty("user.home") + "\\Desktop\\game.json"), Utils.getGson().toJson(match).getBytes(StandardCharsets.UTF_8));
        System.out.println(match);
    }
    
    private static int scale(int x, int minTo, int maxTo, int minFrom, int maxFrom)
    {
        return ((maxFrom - minFrom) * (x - minTo)) / ((maxTo - minTo) + minFrom);
    }
    
    @Test
    public void getDeep()
    {
        R4J         api      = new R4J(SecretFile.CREDS);
        VALMatchAPI matchAPI = api.getVALAPI().getMatchAPI();
        RiotAccount account  = api.getAccountAPI().getAccountByTag(RegionShard.EUROPE, "Jbzz", "EUW");
        
        List<String> ids = new ArrayList<>();
        ids.add(account.getPUUID());
        
        List<MatchReference> matchlist = matchAPI.getMatchList(ValorantShard.EU, account.getPUUID());
        matchlist.forEach(m -> {
            VALMatch match = matchAPI.getMatch(ValorantShard.EU, m.getMatchId());
            
            match.getRoundResults().forEach(r -> {
                if (match.getPlayers().stream().anyMatch(p -> p.getCharacter() == null))
                {
                    System.out.println();
                }
                r.getPlayerStats().forEach(s -> {
                    if (s.getAbility().getGrenadeEffects() != null && !s.getAbility().getGrenadeEffects().equals("null"))
                    {
                        System.out.println();
                    }
                });
            });
            match.getPlayers().forEach(p -> ids.add(p.getPUUID()));
        });
        
        ids.forEach(puuid -> {
            List<MatchReference> matches = matchAPI.getMatchList(ValorantShard.EU, puuid);
            matches.forEach(m -> {
                VALMatch match = matchAPI.getMatch(ValorantShard.EU, m.getMatchId());
                match.getRoundResults().forEach(r -> {
                    r.getPlayerStats().forEach(s -> {
                        if (s.getAbility().getGrenadeEffects() != null && !s.getAbility().getGrenadeEffects().equals("null"))
                        {
                            System.out.println();
                        }
                    });
                });
            });
        });
    }
    
    @Test
    public void testGetSingleById()
    {
        R4J         api      = new R4J(SecretFile.CREDS);
        VALMatchAPI matchAPI = api.getVALAPI().getMatchAPI();
        VALMatch    match    = matchAPI.getMatch(ValorantShard.AP, "c4eb3e3d-d175-4b73-b7d4-279374d1b19b");
        System.out.println();
    }
    
    @Test
    @Disabled
    public void generateRecentGamesData() throws IOException
    {
        LoggerContext loggerContext = (LoggerContext) LoggerFactory.getILoggerFactory();
        loggerContext.getLogger("no.stelar7.api.r4j.basic.calling.DataCallBuilder").setLevel(Level.INFO);
        loggerContext.getLogger("no.stelar7.api.r4j.basic.ratelimiting.BurstRateLimiter").setLevel(Level.INFO);
        
        while (true)
        {
            DataCall.getCacheProvider().clear(URLEndpoint.V1_VAL_MATCH_BY_ID, new HashMap<>());
            Map<String, JsonElement> matches = new HashMap<>();
            Map<String, JsonElement> players = new HashMap<>();
            
            R4J             api           = new R4J(SecretFile.CREDS);
            VALMatchAPI     matchAPI      = api.getVALAPI().getMatchAPI();
            RecentMatchList recentMatches = matchAPI.getRecentMatches(ValorantShard.EU, GameQueueType.COMPETITIVE);
            
            List<String> matchIds = recentMatches.getMatchIds().stream().limit(100).collect(Collectors.toList());
            int          max      = matchIds.size();
            int          current  = 0;
            for (String matchId : matchIds)
            {
                System.out.println("Working on match " + (++current) + "/" + max);
                
                VALMatch    match     = matchAPI.getMatch(ValorantShard.EU, matchId);
                JsonElement matchJson = Utils.getGson().toJsonTree(match);
                matches.put(matchId, matchJson);
                
                for (Player player : match.getPlayers())
                {
                    RiotAccount account     = api.getAccountAPI().getAccountByPUUID(RegionShard.EUROPE, player.getPUUID());
                    JsonElement accountJson = Utils.getGson().toJsonTree(account);
                    players.put(account.getPUUID(), accountJson);
                }
            }
            
            JsonArray matchJson = new JsonArray();
            for (JsonElement value : matches.values())
            {
                matchJson.add(value);
            }
            
            JsonArray playerJson = new JsonArray();
            for (JsonElement value : players.values())
            {
                playerJson.add(value);
            }
            
            JsonObject obj = new JsonObject();
            obj.add("players", playerJson);
            obj.add("matches", matchJson);
            
            Path outputPath = Paths.get("C:\\cdragon\\valorant\\" + recentMatches.getGeneratedAt() + ".json");
            Files.createDirectories(outputPath.getParent());
            
            Files.write(outputPath, Utils.getGson().toJson(obj).getBytes(StandardCharsets.UTF_8));
        }
    }
}
