package no.stelar7.api.l4j8.tests.staticdata;

import no.stelar7.api.l4j8.basic.constants.*;
import no.stelar7.api.l4j8.basic.constants.api.*;
import no.stelar7.api.l4j8.pojo.staticdata.champion.Champion;
import no.stelar7.api.l4j8.pojo.staticdata.champion.*;
import no.stelar7.api.l4j8.tests.*;
import org.junit.*;

public class StaticChampionTest extends TestBase
{
	
	@Before
	public void initForTest()
	{
		TestBase.init();
		TestBase.builder.withEndpoint(URLEndpoint.STATIC_CHAMPION);
		TestBase.builder.withServer(Server.GLOBAL);
		TestBase.builder.withRegion(Server.EUW);
	}
	
	@Test
	public void printAllCds()
	{
		TestBase.builder.withURLParameter(Constants.URL_PARAM_CHAMPDATA, ChampDataFlags.ALL.getValue());
		ChampionList list = (ChampionList) TestBase.builder.build();
		for (Champion c : list.getData().values())
		{
			for (ChampionSpell s : c.getSpells())
			{
				System.out.println(s.getCooldownBurn());
			}
		}
	}
	
}
