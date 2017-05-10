package no.stelar7.api.l4j8.tests.staticdata;

import no.stelar7.api.l4j8.basic.constants.api.*;
import no.stelar7.api.l4j8.basic.constants.flags.*;
import no.stelar7.api.l4j8.basic.exceptions.APIDataNotParseableException;
import no.stelar7.api.l4j8.impl.*;
import no.stelar7.api.l4j8.pojo.staticdata.champion.StaticChampion;
import no.stelar7.api.l4j8.pojo.staticdata.item.*;
import no.stelar7.api.l4j8.pojo.staticdata.map.MapDetails;
import no.stelar7.api.l4j8.pojo.staticdata.mastery.*;
import no.stelar7.api.l4j8.pojo.staticdata.profileicon.ProfileIconDetails;
import no.stelar7.api.l4j8.pojo.staticdata.realm.Realm;
import no.stelar7.api.l4j8.pojo.staticdata.rune.StaticRune;
import no.stelar7.api.l4j8.pojo.staticdata.summonerspell.StaticSummonerSpell;
import no.stelar7.api.l4j8.pojo.summoner.masteries.*;
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
        EnumSet<ChampDataFlags> dataFlags = EnumSet.of(ChampDataFlags.ALL);
        
        Map<Long, StaticChampion> list = api.getChampions(Platform.EUW1, dataFlags, null, null);
        Assert.assertTrue("less than 100?", list.size() > 100);
        
    }
    
    
    @Test
    public void testChampionSingle()
    {
        EnumSet<ChampDataFlags> dataFlags = EnumSet.of(ChampDataFlags.ALL);
        
        StaticChampion list = api.getChampion(Platform.EUW1, Constants.TEST_CHAMPION_IDS[0], dataFlags, null, null);
        Assert.assertTrue("ok?", list.getId().equals(Constants.TEST_CHAMPION_IDS[0]));
    }
    
    @Test
    public void testItemList()
    {
        EnumSet<ItemDataFlags> dataFlags = EnumSet.of(ItemDataFlags.ALL);
        
        ItemList list = api.getItems(Platform.EUW1, dataFlags, null, null);
        Assert.assertTrue("less than 100?", list.getData().size() > 100);
    }
    
    
    @Test
    public void testItemSingle()
    {
        EnumSet<ItemDataFlags> dataFlags = EnumSet.of(ItemDataFlags.ALL);
        
        Item list = api.getItem(Platform.EUW1, 1018, dataFlags, null, null);
        Assert.assertTrue("ok?", list.getId() == 1018);
    }
    
    
    @Test
    public void testLanguageStrings()
    {
        Map<String, String> strings = api.getLanguageStrings(Platform.EUW1, null, null);
    }
    
    @Test
    public void testLanguages()
    {
        List<String> strings = api.getLanguages(Platform.EUW1);
    }
    
    @Test
    public void testMaps()
    {
        Map<String, MapDetails> data = api.getMaps(Platform.EUW1, null, null);
        System.out.println();
    }
    
    
    @Test
    public void testMasteryList()
    {
        EnumSet<MasteryDataFlags> dataFlags = EnumSet.of(MasteryDataFlags.ALL);
        
        Map<Integer, StaticMastery> list = api.getMasteries(Platform.EUW1, dataFlags, null, null);
    }
    
    @Test
    public void testMasteryTreeish()
    {
        EnumSet<MasteryDataFlags> dataFlags = EnumSet.of(MasteryDataFlags.ALL);
        
        Map<Integer, StaticMastery>        data  = api.getMasteries(Platform.EUW1, dataFlags, null, null);
        List<MasteryPage>                  pages = SummonerAPI.getInstance().getMasteries(Platform.EUW1, Constants.TEST_SUMMONER_IDS[0]);
        
        for (MasteryPage page : pages)
        {
            int[] score = {0, 0, 0};
            for (Mastery mastery : page.getMasteries())
            {
                StaticMastery staticMastery = data.get(mastery.getId());
                switch (staticMastery.getMasteryTree())
                {
                    case "Ferocity":
                        score[0] += mastery.getRank();
                        break;
                    case "Cunning":
                        score[1] += mastery.getRank();
                        break;
                    case "Resolve":
                        score[2] += mastery.getRank();
                        break;
                    default:
                        throw new APIDataNotParseableException("new mastery names?");
                }
            }
            
            System.out.format("%s: %s/%s/%s%n", page.getName(), score[0], score[1], score[2]);
        }
    }
    
    
    @Test
    public void testMasterySingle()
    {
        EnumSet<MasteryDataFlags> dataFlags = EnumSet.of(MasteryDataFlags.ALL);
        
        StaticMastery list = api.getMastery(Platform.EUW1, 6131, dataFlags, null, null);
        
        Assert.assertTrue("ok?", list.getId() == 6131);
    }
    
    @Test
    public void testProfileIcons()
    {
        Map<Long, ProfileIconDetails> data = api.getProfileIcons(Platform.EUW1, null, null);
    }
    
    @Test
    public void testRealms()
    {
        Realm data = api.getRealm(Platform.EUW1);
    }
    
    @Test
    public void testRuneList()
    {
        EnumSet<RuneDataFlags> dataFlags = EnumSet.of(RuneDataFlags.ALL);
        
        Map<Integer, StaticRune> list = api.getRunes(Platform.EUW1, dataFlags, null, null);
    }
    
    
    @Test
    public void testRuneSingle()
    {
        EnumSet<RuneDataFlags> dataFlags = EnumSet.of(RuneDataFlags.ALL);
        
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
        EnumSet<SpellDataFlags> dataFlags = EnumSet.of(SpellDataFlags.ALL);
        
        Map<String, StaticSummonerSpell> list = api.getSummonerSpells(Platform.EUW1, dataFlags, null, null);
    }
    
    
    @Test
    public void testSummonerSpellSingle()
    {
        EnumSet<SpellDataFlags> dataFlags = EnumSet.of(SpellDataFlags.ALL);
        
        StaticSummonerSpell list = api.getSummonerSpell(Platform.EUW1, 21, dataFlags, null, null);
        
        Assert.assertTrue("ok?", list.getId() == 21);
    }
    
    
    @Test
    public void testVersions()
    {
        List<String> data = api.getVersions(Platform.EUW1);
    }
}
