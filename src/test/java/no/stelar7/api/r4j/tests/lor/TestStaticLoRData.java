package no.stelar7.api.r4j.tests.lor;

import no.stelar7.api.r4j.impl.lor.LoRStaticAPI;
import no.stelar7.api.r4j.pojo.lor.offline.card.LoRFaction;
import no.stelar7.api.r4j.pojo.lor.staticdata.LoRSetInstance;
import org.junit.Test;

import java.nio.file.*;

public class TestStaticLoRData
{
    @Test
    public void testStatic()
    {
        Path           zipOutputPath = Paths.get("C:\\Users\\Steffen\\Downloads\\datadragon-set1-lite-en_us (1)");
        LoRSetInstance instance      = new LoRSetInstance(zipOutputPath, "set1", "en_us");
        LoRStaticAPI.setActiveInstance(instance);
        
        System.out.println(LoRStaticAPI.getCards(LoRFaction.SHADOW_ILES).size());
    }
}
