package no.stelar7.api.r4j.tests.lol.staticdata;

import no.stelar7.api.r4j.basic.cache.impl.FileSystemCacheProvider;
import no.stelar7.api.r4j.basic.calling.DataCall;
import no.stelar7.api.r4j.basic.utils.Utils;
import no.stelar7.api.r4j.impl.R4J;
import no.stelar7.api.r4j.impl.lol.raw.*;
import no.stelar7.api.r4j.pojo.lol.staticdata.champion.*;
import no.stelar7.api.r4j.pojo.lol.staticdata.item.Item;
import no.stelar7.api.r4j.pojo.lol.staticdata.map.MapDetails;
import no.stelar7.api.r4j.pojo.lol.staticdata.mastery.StaticMastery;
import no.stelar7.api.r4j.pojo.lol.staticdata.perk.*;
import no.stelar7.api.r4j.pojo.lol.staticdata.profileicon.ProfileIconDetails;
import no.stelar7.api.r4j.pojo.lol.staticdata.realm.Realm;
import no.stelar7.api.r4j.pojo.lol.staticdata.rune.StaticRune;
import no.stelar7.api.r4j.pojo.lol.staticdata.summonerspell.StaticSummonerSpell;
import no.stelar7.api.r4j.tests.SecretFile;
import org.junit.*;

import javax.net.ssl.SSLException;
import java.io.*;
import java.net.URL;
import java.nio.channels.*;
import java.nio.file.*;
import java.util.*;

public class StaticTest
{
    
    final R4J r4J = new R4J(SecretFile.CREDS);
    DDragonAPI api = r4J.getDDragonAPI();
    
    @Test
    public void fetchAllImagesWithIdName()
    {
        Path outputFolder = Paths.get("C:\\Users\\Steffen\\Desktop\\cs\\src\\assets\\splash");
        
        api.getChampions()
           .entrySet()
           .stream()
           .parallel()
           .forEach(e -> {
               for (Skin skin : e.getValue().getSkins())
               {
                   String url  = "https://cdn.communitydragon.org/latest/champion/" + e.getKey() + "/splash-art/centered/skin/" + skin.getNum();
                   Path   file = outputFolder.resolve(Utils.padLeft(String.valueOf(skin.getId()), "0", 6) + ".png");
                
                   downloadFile(url, file);
                   System.out.println();
               }
           });
    }
    
    private void downloadFile(String url, Path outputFile)
    {
        try
        {
            Files.createDirectories(outputFile.getParent());
            URL u = new URL(url);
            try (InputStream is = u.openStream();
                 ReadableByteChannel rbc = Channels.newChannel(is);
                 FileOutputStream fos = new FileOutputStream(outputFile.toFile()))
            {
                fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
            }
        } catch (SSLException e)
        {
            downloadFile(url, outputFile);
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }
    
    @Test
    public void testChampionList()
    {
        DataCall.setCacheProvider(new FileSystemCacheProvider());
        
        Map<Integer, StaticChampion> list = api.getChampions();
        Assert.assertTrue("less than 100?", list.size() > 100);
    }
    
    @Test
    public void testChampionSingle()
    {
        DataCall.setCacheProvider(new FileSystemCacheProvider());
        
        StaticChampion list = api.getChampion(1);
        double         r    = list.getSpells().get(0).getRange().get(0);
        System.out.println(r);
        Assert.assertEquals("ok?", list.getId(), 1);
    }
    
    @Test
    public void testItemList()
    {
        DataCall.setCacheProvider(new FileSystemCacheProvider());
        
        Map<Integer, Item> list = api.getItems();
        Assert.assertTrue("less than 100?", list.size() > 100);
    }
    
    
    @Test
    public void testItemSingle()
    {
        DataCall.setCacheProvider(new FileSystemCacheProvider());
        
        
        Item list = api.getItem(3147);
        Assert.assertEquals("ok?", 3147, list.getId());
    }
    
    
    @Test
    public void testLanguageStrings()
    {
        DataCall.setCacheProvider(new FileSystemCacheProvider());
        
        
        Map<String, String> strings = api.getLanguageStrings();
    }
    
    @Test
    public void testLanguages()
    {
        DataCall.setCacheProvider(new FileSystemCacheProvider());
        
        
        List<String> strings = api.getLanguages();
    }
    
    @Test
    public void testMaps()
    {
        DataCall.setCacheProvider(new FileSystemCacheProvider());
        
        
        Map<String, MapDetails> data = api.getMaps();
    }
    
    
    @Test
    public void testMasteryList()
    {
        DataCall.setCacheProvider(new FileSystemCacheProvider());
        
        
        Map<Integer, StaticMastery> list = api.getMasteries();
    }
    
    
    @Test
    public void testMasterySingle()
    {
        DataCall.setCacheProvider(new FileSystemCacheProvider());
        
        
        StaticMastery list = api.getMastery(6131);
        Assert.assertEquals("ok?", 6131, list.getId());
    }
    
    @Test
    public void testProfileIcons()
    {
        DataCall.setCacheProvider(new FileSystemCacheProvider());
        
        
        Map<Long, ProfileIconDetails> data = api.getProfileIcons();
    }
    
    @Test
    public void testRealms()
    {
        DataCall.setCacheProvider(new FileSystemCacheProvider());
        
        
        Realm data = api.getRealm();
    }
    
    @Test
    public void testRuneList()
    {
        DataCall.setCacheProvider(new FileSystemCacheProvider());
        
        
        Map<Integer, StaticRune> list = api.getRunes();
    }
    
    
    @Test
    public void testRuneSingle()
    {
        DataCall.setCacheProvider(new FileSystemCacheProvider());
        
        
        StaticRune rune = api.getRune(5023);
        
        Assert.assertEquals("missing id?", 5023, rune.getId());
        Assert.assertNotNull("missing stats?", rune.getStats());
        Assert.assertNotNull("missing desc?", rune.getDescription());
        Assert.assertNotNull("missing tags?", rune.getTags());
        Assert.assertNotNull("missing image?", rune.getImage());
        Assert.assertNotNull("missing rune?", rune.getRune());
        Assert.assertNotNull("missing name?", rune.getName());
    }
    
    @Test
    public void testSummonerSpellList()
    {
        DataCall.setCacheProvider(new FileSystemCacheProvider());
        
        
        Map<Integer, StaticSummonerSpell> list = api.getSummonerSpells();
    }
    
    
    @Test
    public void testSummonerSpellSingle()
    {
        DataCall.setCacheProvider(new FileSystemCacheProvider());
        
        
        StaticSummonerSpell list = api.getSummonerSpell(21);
        Assert.assertEquals("ok?", 21, list.getId());
    }
    
    
    @Test
    public void testVersions()
    {
        DataCall.setCacheProvider(new FileSystemCacheProvider());
        
        
        List<String> data = api.getVersions();
    }
    
    @Test
    public void testPerkPath()
    {
        DataCall.setCacheProvider(new FileSystemCacheProvider());
        
        
        Map<Integer, PerkPath> paths = api.getPerkPaths();
    }
    
    @Test
    public void testPerkPathId()
    {
        DataCall.setCacheProvider(new FileSystemCacheProvider());
        
        
        PerkPath paths = api.getPerkPath(8000);
    }
    
    @Test
    public void testPerk()
    {
        DataCall.setCacheProvider(new FileSystemCacheProvider());
        
        
        Map<Integer, StaticPerk> paths = api.getPerks();
        System.out.println();
    }
    
    @Test
    public void testPerkId()
    {
        DataCall.setCacheProvider(new FileSystemCacheProvider());
        
        
        StaticPerk paths = api.getPerk(8005);
        System.out.println();
    }
    
    @Test
    public void testTarball()
    {
        DataCall.setCacheProvider(new FileSystemCacheProvider());
        
        
        String paths = api.getTarball();
        System.out.println();
    }
}
