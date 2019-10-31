package no.stelar7.api.l4j8.tests.staticdata;

import no.stelar7.api.l4j8.basic.cache.impl.FileSystemCacheProvider;
import no.stelar7.api.l4j8.basic.calling.DataCall;
import no.stelar7.api.l4j8.impl.L4J8;
import no.stelar7.api.l4j8.impl.lol.raw.DDragonAPI;
import no.stelar7.api.l4j8.pojo.staticdata.champion.StaticChampion;
import no.stelar7.api.l4j8.pojo.staticdata.item.Item;
import no.stelar7.api.l4j8.pojo.staticdata.map.MapDetails;
import no.stelar7.api.l4j8.pojo.staticdata.mastery.StaticMastery;
import no.stelar7.api.l4j8.pojo.staticdata.perk.*;
import no.stelar7.api.l4j8.pojo.staticdata.profileicon.ProfileIconDetails;
import no.stelar7.api.l4j8.pojo.staticdata.realm.Realm;
import no.stelar7.api.l4j8.pojo.staticdata.rune.StaticRune;
import no.stelar7.api.l4j8.pojo.staticdata.summonerspell.StaticSummonerSpell;
import no.stelar7.api.l4j8.tests.SecretFile;
import org.junit.*;

import java.util.*;

public class StaticTest
{
    
    final L4J8 l4j8 = new L4J8(SecretFile.CREDS);
    DDragonAPI api = l4j8.getDDragonAPI();
    
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
        double r = list.getSpells().get(0).getRange().get(0);
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
