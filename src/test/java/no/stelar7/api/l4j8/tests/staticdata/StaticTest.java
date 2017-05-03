package no.stelar7.api.l4j8.tests.staticdata;

import no.stelar7.api.l4j8.basic.constants.api.*;
import no.stelar7.api.l4j8.basic.constants.flags.*;
import no.stelar7.api.l4j8.impl.*;
import no.stelar7.api.l4j8.pojo.staticdata.champion.*;
import no.stelar7.api.l4j8.pojo.staticdata.item.*;
import no.stelar7.api.l4j8.pojo.staticdata.language.LanguageStrings;
import no.stelar7.api.l4j8.pojo.staticdata.map.MapData;
import no.stelar7.api.l4j8.pojo.staticdata.mastery.*;
import no.stelar7.api.l4j8.pojo.staticdata.profileicon.ProfileIconData;
import no.stelar7.api.l4j8.pojo.staticdata.realm.Realm;
import no.stelar7.api.l4j8.pojo.staticdata.rune.*;
import no.stelar7.api.l4j8.pojo.staticdata.summonerspell.*;
import no.stelar7.api.l4j8.tests.SecretFile;
import org.junit.*;

import java.util.*;

public class StaticTest
{
    
    final L4J8 l4j8 = new L4J8(SecretFile.CREDS);
    StaticAPI        api     = l4j8.getStaticAPI();
    Optional<String> version = Optional.empty();
    Optional<String> locale  = Optional.empty();
    
    @Test
    public void testChampionList()
    {
        Optional<EnumSet<ChampDataFlags>> dataFlags = Optional.of(EnumSet.of(ChampDataFlags.ALL));
        
        StaticChampionList list = api.getChampions(Platform.EUW1, dataFlags, version, locale);
        Assert.assertTrue("less than 100?", list.getData().size() > 100);
        
        //list.getData().values().stream().sorted(Comparator.comparing(StaticChampion::getName)).forEach(c -> System.out.format("%s(%s, \"%s\"),%n", c.getName().toUpperCase(Locale.ENGLISH), c.getSummonerId(), c.getName()));
    }
    
    
    @Test
    public void testChampionSingle()
    {
        Optional<EnumSet<ChampDataFlags>> dataFlags = Optional.of(EnumSet.of(ChampDataFlags.ALL));
        
        StaticChampion list = api.getChampion(Platform.EUW1, Constants.TEST_CHAMPION_IDS[0], dataFlags, version, locale);
        Assert.assertTrue("ok?", list.getId() == Constants.TEST_CHAMPION_IDS[0]);
    }
    
    @Test
    public void testItemList()
    {
        Optional<EnumSet<ItemDataFlags>> dataFlags = Optional.of(EnumSet.of(ItemDataFlags.ALL));
        
        ItemList list = api.getItems(Platform.EUW1, dataFlags, version, locale);
        Assert.assertTrue("less than 100?", list.getData().size() > 100);
    }
    
    
    @Test
    public void testItemSingle()
    {
        Optional<EnumSet<ItemDataFlags>> dataFlags = Optional.of(EnumSet.of(ItemDataFlags.ALL));
        
        Item list = api.getItem(Platform.EUW1, 1018, dataFlags, version, locale);
        Assert.assertTrue("ok?", list.getId() == 1018);
    }
    
    
    @Test
    public void testLanguageStrings()
    {
        LanguageStrings strings = api.getLanguageStrings(Platform.EUW1, version, locale);
    }
    
    @Test
    public void testLanguages()
    {
        List<String> strings = api.getLanguages(Platform.EUW1);
    }
    
    @Test
    public void testMaps()
    {
        MapData data = api.getMaps(Platform.EUW1, version, locale);
    }
    
    
    @Test
    public void testMasteryList()
    {
        Optional<EnumSet<MasteryDataFlags>> dataFlags = Optional.of(EnumSet.of(MasteryDataFlags.ALL));
        
        MasteryList list = api.getMasteries(Platform.EUW1, dataFlags, version, locale);
    }
    
    
    @Test
    public void testMasterySingle()
    {
        Optional<EnumSet<MasteryDataFlags>> dataFlags = Optional.of(EnumSet.of(MasteryDataFlags.ALL));
        
        StaticMastery list = api.getMastery(Platform.EUW1, 6131, dataFlags, version, locale);
        
        Assert.assertTrue("ok?", list.getId() == 6131);
    }
    
    @Test
    public void testProfileIcons()
    {
        ProfileIconData data = api.getProfileIcons(Platform.EUW1, Optional.empty(), Optional.empty());
    }
    
    @Test
    public void testRealms()
    {
        Realm data = api.getRealm(Platform.EUW1);
    }
    
    @Test
    public void testRuneList()
    {
        Optional<EnumSet<RuneDataFlags>> dataFlags = Optional.of(EnumSet.of(RuneDataFlags.ALL));
        
        StaticRuneList list = api.getRunes(Platform.EUW1, dataFlags, version, locale);
    }
    
    
    @Test
    public void testRuneSingle()
    {
        Optional<EnumSet<RuneDataFlags>> dataFlags = Optional.of(EnumSet.of(RuneDataFlags.ALL));
        
        StaticRune rune = api.getRune(Platform.EUW1, 5023, dataFlags, version, locale);
        
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
        Optional<EnumSet<SpellDataFlags>> dataFlags = Optional.of(EnumSet.of(SpellDataFlags.ALL));
        
        StaticSummonerSpellList list = api.getSummonerSpells(Platform.EUW1, dataFlags, version, locale);
    }
    
    
    @Test
    public void testSummonerSpellSingle()
    {
        Optional<EnumSet<SpellDataFlags>> dataFlags = Optional.of(EnumSet.of(SpellDataFlags.ALL));
        
        StaticSummonerSpell list = api.getSummonerSpell(Platform.EUW1, 21, dataFlags, version, locale);
        
        Assert.assertTrue("ok?", list.getId() == 21);
    }
    
    
    @Test
    public void testVersions()
    {
        List<String> data = api.getVersions(Platform.EUW1);
    }
}
