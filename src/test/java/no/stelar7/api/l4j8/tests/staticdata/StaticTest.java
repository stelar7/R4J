package no.stelar7.api.l4j8.tests.staticdata;

import no.stelar7.api.l4j8.basic.cache.*;
import no.stelar7.api.l4j8.basic.calling.DataCall;
import no.stelar7.api.l4j8.basic.constants.api.*;
import no.stelar7.api.l4j8.basic.constants.flags.*;
import no.stelar7.api.l4j8.impl.L4J8;
import no.stelar7.api.l4j8.impl.raw.StaticAPI;
import no.stelar7.api.l4j8.pojo.staticdata.champion.StaticChampion;
import no.stelar7.api.l4j8.pojo.staticdata.item.*;
import no.stelar7.api.l4j8.pojo.staticdata.map.MapDetails;
import no.stelar7.api.l4j8.pojo.staticdata.mastery.StaticMastery;
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
    StaticAPI api = l4j8.getStaticAPI();
    
    @Test
    public void testChampionList()
    {
        DataCall.setCacheProvider(new FileSystemCacheProvider());
        DataCall.setLogLevel(LogLevel.DEBUG);
        
        EnumSet<ChampDataFlags> dataFlags = EnumSet.of(ChampDataFlags.ALL, ChampDataFlags.IMAGE);
        
        Map<Integer, StaticChampion> list = api.getChampions(Platform.EUW1, dataFlags, null, null);
        Assert.assertTrue("less than 100?", list.size() > 100);
    }
    
    @Test
    public void testChampionSingle()
    {
        DataCall.setCacheProvider(new FileSystemCacheProvider());
        DataCall.setLogLevel(LogLevel.DEBUG);
        
        EnumSet<ChampDataFlags> dataFlags = EnumSet.of(ChampDataFlags.ALL, ChampDataFlags.IMAGE);
        
        StaticChampion list = api.getChampion(Platform.EUW1, Constants.TEST_CHAMPION_IDS[0], dataFlags, null, null);
        Assert.assertTrue("ok?", list.getId() == Constants.TEST_CHAMPION_IDS[0]);
    }
    
    @Test
    public void testItemList()
    {
        DataCall.setCacheProvider(new FileSystemCacheProvider());
        DataCall.setLogLevel(LogLevel.DEBUG);
        
        EnumSet<ItemDataFlags> dataFlags = EnumSet.of(ItemDataFlags.ALL, ItemDataFlags.IMAGE);
        
        ItemList list = api.getItems(Platform.EUW1, dataFlags, null, null);
        Assert.assertTrue("less than 100?", list.getData().size() > 100);
    }
    
    
    @Test
    public void testItemSingle()
    {
        DataCall.setCacheProvider(new FileSystemCacheProvider());
        DataCall.setLogLevel(LogLevel.DEBUG);
        
        EnumSet<ItemDataFlags> dataFlags = EnumSet.of(ItemDataFlags.ALL, ItemDataFlags.IMAGE);
        
        Item list = api.getItem(Platform.EUW1, 3147, dataFlags, null, null);
        Assert.assertTrue("ok?", list.getId() == 3147);
    }
    
    
    @Test
    public void testLanguageStrings()
    {
        DataCall.setCacheProvider(new FileSystemCacheProvider());
        DataCall.setLogLevel(LogLevel.DEBUG);
        
        Map<String, String> strings = api.getLanguageStrings(Platform.EUW1, null, null);
    }
    
    @Test
    public void testLanguages()
    {
        DataCall.setCacheProvider(new FileSystemCacheProvider());
        DataCall.setLogLevel(LogLevel.DEBUG);
        
        List<String> strings = api.getLanguages(Platform.EUW1);
    }
    
    @Test
    public void testMaps()
    {
        DataCall.setCacheProvider(new FileSystemCacheProvider());
        DataCall.setLogLevel(LogLevel.DEBUG);
        
        Map<String, MapDetails> data = api.getMaps(Platform.EUW1, null, null);
    }
    
    
    @Test
    public void testMasteryList()
    {
        DataCall.setCacheProvider(new FileSystemCacheProvider());
        DataCall.setLogLevel(LogLevel.DEBUG);
        
        EnumSet<MasteryDataFlags> dataFlags = EnumSet.of(MasteryDataFlags.ALL, MasteryDataFlags.IMAGE);
        
        Map<Integer, StaticMastery> list = api.getMasteries(Platform.EUW1, dataFlags, null, null);
    }
    
    
    @Test
    public void testMasterySingleMultipleFlags()
    {
        DataCall.setCacheProvider(new FileSystemCacheProvider());
        DataCall.setLogLevel(LogLevel.DEBUG);
        
        EnumSet<MasteryDataFlags> dataFlags = EnumSet.of(MasteryDataFlags.ALL, MasteryDataFlags.IMAGE);
        
        StaticMastery list = api.getMastery(Platform.EUW1, 6131, dataFlags, null, null);
        Assert.assertTrue("ok?", list.getId() == 6131);
    }
    
    @Test
    public void testMasterySingleOneFlag()
    {
        DataCall.setCacheProvider(new FileSystemCacheProvider());
        DataCall.setLogLevel(LogLevel.DEBUG);
        
        EnumSet<MasteryDataFlags> dataFlags = EnumSet.of(MasteryDataFlags.ALL);
        
        StaticMastery list = api.getMastery(Platform.EUW1, 6131, dataFlags, null, null);
        Assert.assertTrue("ok?", list.getId() == 6131);
    }
    
    @Test
    public void testMasterySingleNoFlags()
    {
        DataCall.setCacheProvider(new FileSystemCacheProvider());
        DataCall.setLogLevel(LogLevel.DEBUG);
        
        StaticMastery list = api.getMastery(Platform.EUW1, 6131, null, null, null);
        Assert.assertTrue("ok?", list.getId() == 6131);
    }
    
    @Test
    public void testProfileIcons()
    {
        DataCall.setCacheProvider(new FileSystemCacheProvider());
        DataCall.setLogLevel(LogLevel.DEBUG);
        
        Map<Long, ProfileIconDetails> data = api.getProfileIcons(Platform.EUW1, null, null);
    }
    
    @Test
    public void testRealms()
    {
        DataCall.setCacheProvider(new FileSystemCacheProvider());
        DataCall.setLogLevel(LogLevel.DEBUG);
        
        Realm data = api.getRealm(Platform.EUW1);
    }
    
    @Test
    public void testRuneList()
    {
        DataCall.setCacheProvider(new FileSystemCacheProvider());
        DataCall.setLogLevel(LogLevel.DEBUG);
        
        EnumSet<RuneDataFlags> dataFlags = EnumSet.of(RuneDataFlags.ALL, RuneDataFlags.IMAGE);
        
        Map<Integer, StaticRune> list = api.getRunes(Platform.EUW1, dataFlags, null, null);
    }
    
    
    @Test
    public void testRuneSingle()
    {
        DataCall.setCacheProvider(new FileSystemCacheProvider());
        DataCall.setLogLevel(LogLevel.DEBUG);
        
        EnumSet<RuneDataFlags> dataFlags = EnumSet.of(RuneDataFlags.ALL, RuneDataFlags.IMAGE);
        
        StaticRune rune = api.getRune(Platform.EUW1, 5023, dataFlags, null, null);
        
        Assert.assertTrue("missing id?", rune.getId() == 5023);
        Assert.assertTrue("missing stats?", rune.getStats() != null);
        Assert.assertTrue("missing desc?", rune.getDescription() != null);
        Assert.assertTrue("missing tags?", rune.getTags() != null);
        Assert.assertTrue("missing image?", rune.getImage() != null);
        Assert.assertTrue("missing sandesc?", rune.getSanitizedDescription() != null);
        Assert.assertTrue("missing rune?", rune.getRune() != null);
        Assert.assertTrue("missing name?", rune.getName() != null);
    }
    
    @Test
    public void testSummonerSpellList()
    {
        DataCall.setCacheProvider(new FileSystemCacheProvider());
        DataCall.setLogLevel(LogLevel.DEBUG);
        
        EnumSet<SpellDataFlags> dataFlags = EnumSet.of(SpellDataFlags.ALL, SpellDataFlags.IMAGE);
        
        Map<Integer, StaticSummonerSpell> list = api.getSummonerSpells(Platform.EUN1, dataFlags, null, null);
    }
    
    
    @Test
    public void testSummonerSpellSingle()
    {
        DataCall.setCacheProvider(new FileSystemCacheProvider());
        DataCall.setLogLevel(LogLevel.DEBUG);
        
        EnumSet<SpellDataFlags> dataFlags = EnumSet.of(SpellDataFlags.ALL, SpellDataFlags.IMAGE);
        
        StaticSummonerSpell list = api.getSummonerSpell(Platform.EUW1, 21, dataFlags, null, null);
        Assert.assertTrue("ok?", list.getId() == 21);
    }
    
    
    @Test
    public void testVersions()
    {
        DataCall.setCacheProvider(new FileSystemCacheProvider());
        DataCall.setLogLevel(LogLevel.DEBUG);
        
        List<String> data = api.getVersions(Platform.EUW1);
    }
    
    
}
