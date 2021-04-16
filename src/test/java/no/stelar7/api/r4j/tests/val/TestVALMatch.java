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
import org.junit.jupiter.api.Test;
import org.slf4j.LoggerFactory;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.List;
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
        Match                match     = matchAPI.getMatch(ValorantShard.EU, matchlist.get(0).getMatchId());
        Files.write(Paths.get("C:\\Users\\Steffen\\Desktop\\game.json"), Utils.getGson().toJson(match).getBytes(StandardCharsets.UTF_8));
        System.out.println(match);
    }
    
    private static int scale(int x, int minTo, int maxTo, int minFrom, int maxFrom)
    {
        return ((maxFrom - minFrom) * (x - minTo)) / ((maxTo - minTo) + minFrom);
    }
    
    //@Test
    public static void main(String[] args) throws IOException
    {
        R4J         api      = new R4J(SecretFile.CREDS);
        VALMatchAPI matchAPI = api.getVALAPI().getMatchAPI();
        RiotAccount account  = api.getAccountAPI().getAccountByTag(RegionShard.EUROPE, "stelar7", "stl7");
        Match       match    = matchAPI.getMatch(ValorantShard.EU, "c31d95db-8431-40e0-8e1d-a90295d4a0a6");
        
        List<Location> myDeaths = new ArrayList<>();
        
        match.getRoundResults().forEach(r -> {
            r.getPlayerStats().forEach(p -> {
                p.getKills().forEach(k -> {
                    if (!k.getVictim().equalsIgnoreCase(account.getPUUID()))
                    {
                        return;
                    }
                    myDeaths.add(k.getVictimLocation());
                    System.out.println(k.getVictimLocation());
                });
            });
        });
        
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setSize(600, 600);
        Image   img     = ImageIO.read(new File("C:\\Users\\Steffen\\Desktop\\Bind.png"));
        JSlider sliderX = new JSlider(-10000, 10000);
        JSlider sliderY = new JSlider(-10000, 10000);
        sliderX.setValue(200);
        sliderY.setValue(-7000);
        JPanel image = new JPanel(new GridBagLayout())
        {
            @Override
            protected void paintComponent(Graphics g)
            {
                super.paintComponent(g);
                g.setColor(Color.BLACK);
                g.fillRect(0, 0, 512, 512);
                g.drawImage(img, 0, 0, 512, 512, null);
                g.setColor(Color.RED);
                for (Location d : myDeaths)
                {
                    int x = scale(sliderX.getValue() + d.getX(), 0, 16500, 0, 512);
                    int y = scale(sliderY.getValue() + d.getY(), -16500, 0, 0, 512);
                    System.out.println(d.getX());
                    System.out.println(sliderX.getValue());
                    System.out.println(d.getY());
                    System.out.println(sliderY.getValue());
                    g.fillOval(x, y, 10, 10);
                    // break;
                }
            }
        };
        sliderX.addChangeListener((e) -> image.repaint());
        sliderY.addChangeListener((e) -> image.repaint());
        image.setSize(512, 600);
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.anchor = GridBagConstraints.PAGE_END;
        //image.add(sliderX, c);
        c.gridy = 1;
        //image.add(sliderY, c);
        c.gridy = 2;
        frame.add(image);
        frame.setVisible(true);
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
            Match match = matchAPI.getMatch(ValorantShard.EU, m.getMatchId());
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
                Match match = matchAPI.getMatch(ValorantShard.EU, m.getMatchId());
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
        Match       match    = matchAPI.getMatch(ValorantShard.AP, "c4eb3e3d-d175-4b73-b7d4-279374d1b19b");
        System.out.println();
    }
    
    @Test
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
                
                Match       match     = matchAPI.getMatch(ValorantShard.EU, matchId);
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
            
            Path outputPath = Paths.get("D:\\valorant_dump\\" + recentMatches.getGeneratedAt() + ".json");
            Files.createDirectories(outputPath.getParent());
            
            Files.write(outputPath, Utils.getGson().toJson(obj).getBytes(StandardCharsets.UTF_8));
        }
    }
}
