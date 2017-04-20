package no.stelar7.api.l4j8.tests.staticdata;

import no.stelar7.api.l4j8.basic.constants.api.*;
import no.stelar7.api.l4j8.basic.constants.flags.*;
import no.stelar7.api.l4j8.basic.constants.flags.ChampDataFlags;
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
        
        Optional<StaticChampionList> list = api.getChampions(Platform.EUW1, dataFlags, version, locale);
        
        Assert.assertTrue("no data?", list.isPresent());
        Assert.assertTrue("less than 100?", list.get().getData().size() > 100);
    }
    
    
    @Test
    public void testChampionSingle()
    {
        Optional<EnumSet<ChampDataFlags>> dataFlags = Optional.of(EnumSet.of(ChampDataFlags.ALL));
        
        Optional<StaticChampion> list = api.getChampion(Platform.EUW1, Constants.TEST_CHAMPION_IDS[0], dataFlags, version, locale);
        
        Assert.assertTrue("no data?", list.isPresent());
        Assert.assertTrue("ok?", list.get().getId() == Constants.TEST_CHAMPION_IDS[0]);
    }
    
    @Test
    public void testItemList()
    {
        Optional<EnumSet<ItemDataFlags>> dataFlags = Optional.of(EnumSet.of(ItemDataFlags.ALL));
        
        Optional<ItemList> list = api.getItems(Platform.EUW1, dataFlags, version, locale);
        
        Assert.assertTrue("no data?", list.isPresent());
        Assert.assertTrue("less than 100?", list.get().getData().size() > 100);
    }
    
    
    @Test
    public void testItemSingle()
    {
        Optional<EnumSet<ItemDataFlags>> dataFlags = Optional.of(EnumSet.of(ItemDataFlags.ALL));
        
        Optional<Item> list = api.getItem(Platform.EUW1, 1018, dataFlags, version, locale);
        
        Assert.assertTrue("no data?", list.isPresent());
        Assert.assertTrue("ok?", list.get().getId() == 1018);
    }
    
    
    @Test
    public void testLanguageStrings()
    {
        Optional<LanguageStrings> strings = api.getLanguageStrings(Platform.EUW1, version, locale);
        Assert.assertTrue("no data?", strings.isPresent());
    }
    
    @Test
    public void testLanguages()
    {
        Optional<List<String>> strings = api.getLanguages(Platform.EUW1);
        Assert.assertTrue("no data?", strings.isPresent());
    }
    
    @Test
    public void testMaps()
    {
        Optional<MapData> data = api.getMaps(Platform.EUW1, version, locale);
        Assert.assertTrue("no data?", data.isPresent());
    }
    
    
    @Test
    public void testMasteryList()
    {
        Optional<EnumSet<MasteryDataFlags>> dataFlags = Optional.of(EnumSet.of(MasteryDataFlags.ALL));
        
        Optional<MasteryList> list = api.getMasteries(Platform.EUW1, dataFlags, version, locale);
        
        
        Assert.assertTrue("no data?", list.isPresent());
    }
    
    
    @Test
    public void testMasterySingle()
    {
        Optional<EnumSet<MasteryDataFlags>> dataFlags = Optional.of(EnumSet.of(MasteryDataFlags.ALL));
        
        Optional<Mastery> list = api.getMastery(Platform.EUW1, 6131, dataFlags, version, locale);
        
        Assert.assertTrue("no data?", list.isPresent());
        Assert.assertTrue("ok?", list.get().getId() == 6131);
    }
    
    @Test
    public void testProfileIcons()
    {
        Optional<ProfileIconData> data = api.getProfileIcons(Platform.EUW1, Optional.empty(), Optional.empty());
        Assert.assertTrue("no data?", data.isPresent());
    }
    
    @Test
    public void testRealms()
    {
        Optional<Realm> data = api.getRealm(Platform.EUW1);
        Assert.assertTrue("no data?", data.isPresent());
    }
    
    @Test
    public void testRuneList()
    {
        Optional<EnumSet<RuneDataFlags>> dataFlags = Optional.of(EnumSet.of(RuneDataFlags.ALL));
        
        Optional<StaticRuneList> list = api.getRunes(Platform.EUW1, dataFlags, version, locale);
        Assert.assertTrue("no data?", list.isPresent());
    }
    
    
    @Test
    public void testRuneSingle()
    {
        Optional<EnumSet<RuneDataFlags>> dataFlags = Optional.of(EnumSet.of(RuneDataFlags.ALL));
        
        Optional<StaticRune> rune = api.getRune(Platform.EUW1, 5023, dataFlags, version, locale);
        
        Assert.assertTrue("no data?", rune.isPresent());
        
        Assert.assertTrue("missing id?", rune.get().getId() == 5023);
        Assert.assertTrue("missing stats?", rune.get().getStats() != null);
        Assert.assertTrue("missing desc?", rune.get().getDescription() != null);
        Assert.assertTrue("missing tags?", rune.get().getTags() != null);
        Assert.assertTrue("missing image?", rune.get().getImage() != null);
        Assert.assertTrue("missing sandesc?", rune.get().getSanitizedDescription() != null);
        Assert.assertTrue("missing rune?", rune.get().getRune() != null);
        Assert.assertTrue("missing name?", rune.get().getName() != null);
    }
    
    @Test
    public void testSummonerSpellList()
    {
        Optional<EnumSet<SpellDataFlags>> dataFlags = Optional.of(EnumSet.of(SpellDataFlags.ALL));
        
        Optional<StaticSummonerSpellList> list = api.getSummonerSpells(Platform.EUW1, dataFlags, version, locale);
        
        Assert.assertTrue("no data?", list.isPresent());
    }
    
    
    @Test
    public void testSummonerSpellSingle()
    {
        Optional<EnumSet<SpellDataFlags>> dataFlags = Optional.of(EnumSet.of(SpellDataFlags.ALL));
        
        Optional<StaticSummonerSpell> list = api.getSummonerSpell(Platform.EUW1, 21, dataFlags, version, locale);
        
        Assert.assertTrue("no data?", list.isPresent());
        Assert.assertTrue("ok?", list.get().getId() == 21);
    }
    
    
    @Test
    public void testVersions()
    {
        Optional<List<String>> data = api.getVersions(Platform.EUW1);
        Assert.assertTrue("no data?", data.isPresent());
        
    }
}
