package no.stelar7.api.r4j.tests.val;

import no.stelar7.api.r4j.basic.cache.impl.FileSystemCacheProvider;
import no.stelar7.api.r4j.basic.calling.DataCall;
import no.stelar7.api.r4j.basic.constants.api.URLEndpoint;
import no.stelar7.api.r4j.basic.constants.api.regions.*;
import no.stelar7.api.r4j.basic.constants.types.val.GameQueueType;
import no.stelar7.api.r4j.impl.R4J;
import no.stelar7.api.r4j.impl.val.VALMatchAPI;
import no.stelar7.api.r4j.pojo.shared.RiotAccount;
import no.stelar7.api.r4j.pojo.val.match.*;
import no.stelar7.api.r4j.pojo.val.matchlist.*;
import no.stelar7.api.r4j.tests.SecretFile;
import org.junit.jupiter.api.Test;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.List;
import java.util.*;

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
    public void getSingle()
    {
        R4J         api      = new R4J(SecretFile.CREDS);
        VALMatchAPI matchAPI = api.getVALAPI().getMatchAPI();
        RiotAccount account  = api.getAccountAPI().getAccountByTag(RegionShard.EUROPE, "Coup De Graçe", "EUNE");
        
        List<MatchReference> matchlist = matchAPI.getMatchList(ValorantShard.EU, account.getPUUID());
        Match                match     = matchAPI.getMatch(ValorantShard.EU, matchlist.get(0).getMatchId());
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
}
