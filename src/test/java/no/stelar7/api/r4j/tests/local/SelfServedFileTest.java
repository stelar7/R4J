package no.stelar7.api.r4j.tests.local;

import com.google.gson.*;
import no.stelar7.api.r4j.basic.utils.Utils;
import no.stelar7.api.r4j.pojo.lol.staticdata.champion.*;
import org.junit.jupiter.api.*;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.*;
import java.util.Map.Entry;
import java.util.regex.*;

public class SelfServedFileTest
{
    @Test
    @Disabled
    public void doTest() throws IOException
    {
        Path filepath = Paths.get("src/test/java/no/stelar7/api/r4j/tests/local", "championFull.json");
        
        byte[]      content     = Files.readAllBytes(filepath);
        JsonElement contentJSON = Utils.getGson().toJsonTree(new String(content, StandardCharsets.UTF_8));
        String      finalJSON   = makeById(contentJSON);
        String      outputJSON  = Utils.getGson().toJson(new JsonParser().parse(finalJSON));
        
        StaticChampionList list = Utils.getGson().fromJson(finalJSON, StaticChampionList.class);
        
        //Files.write(Paths.get("championFull.json"), outputJSON.getBytes(StandardCharsets.UTF_8));
        
        testStaticDataIntegrety(list);
    }
    
    private String makeById(JsonElement json)
    {
        JsonObject jsonObject = new JsonParser().parse(json.getAsJsonPrimitive().getAsString()).getAsJsonObject();
        JsonObject data       = jsonObject.deepCopy().get("data").getAsJsonObject();
        JsonObject dataClone  = jsonObject.deepCopy().get("data").getAsJsonObject();
        
        for (String key : data.keySet())
        {
            JsonObject childObject = dataClone.get(key).getAsJsonObject();
            childObject.add("id", childObject.get("key"));
            dataClone.add(childObject.get("key").getAsString(), dataClone.get(key));
        }
        
        dataClone.deepCopy().keySet().stream().filter(Pattern.compile("-?[0-9]+").asPredicate().negate()).forEach(dataClone::remove);
        jsonObject.add("data", dataClone);
        
        return jsonObject.toString();
    }
    
    public void testStaticDataIntegrety(StaticChampionList clist)
    {
        Map<Integer, StaticChampion> list = clist.getData();
        Assertions.assertTrue(list.size() > 100, "less than 100?");
        
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
            if (s.getName().contains("Sorrow"))
            {
                System.out.println();
            }
            
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
        
        Assertions.assertFalse(errors.isEmpty(), "Static data is fixed!?");
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
