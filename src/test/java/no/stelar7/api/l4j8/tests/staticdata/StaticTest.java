package no.stelar7.api.l4j8.tests.staticdata;

import no.stelar7.api.l4j8.basic.constants.api.*;
import no.stelar7.api.l4j8.basic.constants.flags.*;
import no.stelar7.api.l4j8.basic.exceptions.APIDataNotParseableException;
import no.stelar7.api.l4j8.impl.*;
import no.stelar7.api.l4j8.pojo.staticdata.champion.*;
import no.stelar7.api.l4j8.pojo.staticdata.item.*;
import no.stelar7.api.l4j8.pojo.staticdata.map.MapDetails;
import no.stelar7.api.l4j8.pojo.staticdata.mastery.StaticMastery;
import no.stelar7.api.l4j8.pojo.staticdata.profileicon.ProfileIconDetails;
import no.stelar7.api.l4j8.pojo.staticdata.realm.Realm;
import no.stelar7.api.l4j8.pojo.staticdata.rune.StaticRune;
import no.stelar7.api.l4j8.pojo.staticdata.summonerspell.StaticSummonerSpell;
import no.stelar7.api.l4j8.pojo.summoner.masteries.*;
import no.stelar7.api.l4j8.tests.SecretFile;
import org.junit.*;

import java.util.*;
import java.util.Map.Entry;
import java.util.regex.*;

public class StaticTest
{
    
    final L4J8 l4j8 = new L4J8(SecretFile.CREDS);
    StaticAPI api = l4j8.getStaticAPI();
    
    @Test
    public void testChampionList()
    {
        EnumSet<ChampDataFlags> dataFlags = EnumSet.of(ChampDataFlags.ALL, ChampDataFlags.IMAGE);
        
        Map<Integer, StaticChampion> list = api.getChampions(Platform.EUW1, dataFlags, null, null);
        Assert.assertTrue("less than 100?", list.size() > 100);
    }
    
    @Test
    public void testChampionSingle()
    {
        EnumSet<ChampDataFlags> dataFlags = EnumSet.of(ChampDataFlags.ALL, ChampDataFlags.IMAGE);
        
        StaticChampion list = api.getChampion(Platform.EUW1, Constants.TEST_CHAMPION_IDS[0], dataFlags, null, null);
        Assert.assertTrue("ok?", list.getId() == Constants.TEST_CHAMPION_IDS[0]);
    }
    
    @Test
    public void testItemList()
    {
        EnumSet<ItemDataFlags> dataFlags = EnumSet.of(ItemDataFlags.ALL, ItemDataFlags.IMAGE);
        
        ItemList list = api.getItems(Platform.EUW1, dataFlags, null, null);
        Assert.assertTrue("less than 100?", list.getData().size() > 100);
    }
    
    
    @Test
    public void testItemSingle()
    {
        EnumSet<ItemDataFlags> dataFlags = EnumSet.of(ItemDataFlags.ALL, ItemDataFlags.IMAGE);
        
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
        EnumSet<MasteryDataFlags> dataFlags = EnumSet.of(MasteryDataFlags.ALL, MasteryDataFlags.IMAGE);
        
        Map<Integer, StaticMastery> list = api.getMasteries(Platform.EUW1, dataFlags, null, null);
    }
    
    @Test
    public void testMasteryTreeish()
    {
        EnumSet<MasteryDataFlags> dataFlags = EnumSet.of(MasteryDataFlags.ALL, MasteryDataFlags.IMAGE);
        
        Map<Integer, StaticMastery> data  = api.getMasteries(Platform.EUW1, dataFlags, null, null);
        List<MasteryPage>           pages = SummonerAPI.getInstance().getMasteries(Platform.EUW1, Constants.TEST_SUMMONER_IDS[0]);
        
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
    public void testMasterySingleMultipleFlags()
    {
        EnumSet<MasteryDataFlags> dataFlags = EnumSet.of(MasteryDataFlags.ALL, MasteryDataFlags.IMAGE);
        
        StaticMastery list = api.getMastery(Platform.EUW1, 6131, dataFlags, null, null);
        
        Assert.assertTrue("ok?", list.getId() == 6131);
    }
    
    @Test
    public void testMasterySingleOneFlag()
    {
        EnumSet<MasteryDataFlags> dataFlags = EnumSet.of(MasteryDataFlags.ALL);
        
        StaticMastery list = api.getMastery(Platform.EUW1, 6131, dataFlags, null, null);
        
        Assert.assertTrue("ok?", list.getId() == 6131);
    }
    
    @Test
    public void testMasterySingleNoFlags()
    {
        StaticMastery list = api.getMastery(Platform.EUW1, 6131, null, null, null);
        
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
        EnumSet<RuneDataFlags> dataFlags = EnumSet.of(RuneDataFlags.ALL, RuneDataFlags.IMAGE);
        
        Map<Integer, StaticRune> list = api.getRunes(Platform.EUW1, dataFlags, null, null);
    }
    
    
    @Test
    public void testRuneSingle()
    {
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
        EnumSet<SpellDataFlags> dataFlags = EnumSet.of(SpellDataFlags.ALL, SpellDataFlags.IMAGE);
        
        Map<String, StaticSummonerSpell> list = api.getSummonerSpells(Platform.EUW1, dataFlags, null, null);
    }
    
    
    @Test
    public void testSummonerSpellSingle()
    {
        EnumSet<SpellDataFlags> dataFlags = EnumSet.of(SpellDataFlags.ALL, SpellDataFlags.IMAGE);
        
        StaticSummonerSpell list = api.getSummonerSpell(Platform.EUW1, 21, dataFlags, null, null);
        
        Assert.assertTrue("ok?", list.getId() == 21);
    }
    
    
    @Test
    public void testVersions()
    {
        List<String> data = api.getVersions(Platform.EUW1);
    }
    
    @Test
    public void testStaticDataIntegrety()
    {
        EnumSet<ChampDataFlags> dataFlags = EnumSet.of(ChampDataFlags.ALL, ChampDataFlags.IMAGE);
        
        Map<Integer, StaticChampion> list = api.getChampions(Platform.EUW1, dataFlags, null, null);
        Assert.assertTrue("less than 100?", list.size() > 100);
        
        Pattern pat = Pattern.compile("(\\{\\{ .*? }})");
        
        Map<String, List<StaticChampionSpell>> container = new HashMap<>();
        List<StaticChampionSpell>              spells    = new ArrayList<>();
        list.forEach((k, v) -> container.put(v.getName(), v.getSpells()));
        list.forEach((k, v) -> spells.addAll(v.getSpells()));
        
        List<String> errors = new ArrayList<>();
        
        errors.add("Missing Tooltip:");
        for (Iterator<StaticChampionSpell> iter = spells.iterator(); iter.hasNext(); )
        {
            StaticChampionSpell s = iter.next();
            
            if (s.getSanitizedTooltip() == null || s.getSanitizedTooltip().length() < 50)
            {
                errors.add(String.format("%s - %s", getFromSublist(s, container), s.getName()));
                iter.remove();
            }
        }
        errors.add("");
        errors.add("Missing Variables: ");
        for (StaticChampionSpell s : spells)
        {
            Set<String>  vars   = new HashSet<>();
            StringJoiner sb     = new StringJoiner(", ");
            String       better = s.getSanitizedTooltip(18, s.getMaxRank());
            Matcher      m      = pat.matcher(better);
            
            if (better.contains("{"))
            {
                while (m.find())
                {
                    vars.add(m.group().replace("{{ ", "").replace(" }}", ""));
                }
                
                List<String> varSort = new ArrayList<>(vars);
                varSort.sort(String::compareTo);
                varSort.forEach(sb::add);
                errors.add(String.format("%s - %s: %s", getFromSublist(s, container), s.getName(), sb.toString()));
            }
        }
        errors.forEach(System.out::println);
        
        Assert.assertFalse("Static data is fixed!?", errors.isEmpty());
    }
    
    private String getFromSublist(StaticChampionSpell find, Map<String, List<StaticChampionSpell>> data)
    {
        for (Entry<String, List<StaticChampionSpell>> entry : data.entrySet())
        {
            if (entry.getValue().contains(find))
            {
                return entry.getKey();
            }
        }
        return "no data found?????";
    }
    
}
