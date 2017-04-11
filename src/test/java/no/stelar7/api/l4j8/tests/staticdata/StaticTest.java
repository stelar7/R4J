package no.stelar7.api.l4j8.tests.staticdata;

import no.stelar7.api.l4j8.basic.constants.*;
import no.stelar7.api.l4j8.basic.constants.api.Platform;
import no.stelar7.api.l4j8.impl.*;
import no.stelar7.api.l4j8.pojo.staticdata.champion.*;
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
        Optional<EnumSet<ChampDataFlags>> dataFlags = Optional.of(EnumSet.of(ChampDataFlags.ALL));
        Optional<String>                  version   = Optional.empty();
        Optional<String>                  locale    = Optional.empty();
        
        Optional<StaticChampionList> list = api.getChampions(Platform.EUW1, dataFlags, version, locale);
        
        Assert.assertTrue("no data?", list.isPresent());
        Assert.assertTrue("less than 100?", list.get().getData().size() > 100);
    }
    
    
    @Test
    public void testChampionSingle()
    {
        Optional<EnumSet<ChampDataFlags>> dataFlags = Optional.of(EnumSet.of(ChampDataFlags.ALL));
        Optional<String>                  version   = Optional.empty();
        Optional<String>                  locale    = Optional.empty();
        
        Optional<StaticChampion> list = api.getChampion(Platform.EUW1, Champions.LEONA.getId(), dataFlags, version, locale);
        
        Assert.assertTrue("no data?", list.isPresent());
        list.ifPresent(System.out::println);
        Assert.assertTrue("ok?", list.get().getId().intValue() == Champions.LEONA.getId());
    }
    
}
