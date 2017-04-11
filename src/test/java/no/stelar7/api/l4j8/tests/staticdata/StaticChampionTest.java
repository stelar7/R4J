package no.stelar7.api.l4j8.tests.staticdata;

import no.stelar7.api.l4j8.basic.constants.*;
import no.stelar7.api.l4j8.basic.constants.api.*;
import no.stelar7.api.l4j8.pojo.staticdata.champion.StaticChampion;
import no.stelar7.api.l4j8.pojo.staticdata.champion.*;
import no.stelar7.api.l4j8.tests.*;
import org.junit.*;

public class StaticChampionTest extends TestBase
{
    
    
    @Test
    public void printAllCds()
    {
        TestBase.builder.withURLParameter(Constants.URL_PARAM_CHAMPDATA, ChampDataFlags.ALL.getValue());
        StaticChampionList list = (StaticChampionList) TestBase.builder.build();
        for (StaticChampion c : list.getData().values())
        {
            for (StaticChampionSpell s : c.getSpells())
            {
                System.out.println(s.getCooldownBurn());
            }
        }
    }
    
}
