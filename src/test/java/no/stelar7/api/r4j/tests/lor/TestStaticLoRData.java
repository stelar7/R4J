package no.stelar7.api.r4j.tests.lor;

import no.stelar7.api.r4j.impl.lor.LoRStaticAPI;
import no.stelar7.api.r4j.pojo.lor.offline.card.LoRFaction;
import no.stelar7.api.r4j.pojo.lor.staticdata.*;
import org.junit.Test;

import java.nio.file.*;

public class TestStaticLoRData
{
    @Test
    public void testStatic()
    {
        Path setZip = Paths.get("C:\\Users\\Steffen\\Downloads\\datadragon-set1-lite-en_us");
        LoRStaticAPI.setSetInstance(new LoRSetInstance(setZip, "set1", "en_us"));
        
        Path coreZip = Paths.get("C:\\Users\\Steffen\\Downloads\\datadragon-core-en_us");
        LoRStaticAPI.setCoreInstance(new LoRCoreInstance(coreZip, "en_us"));
        
        System.out.println(LoRStaticAPI.getCards(LoRFaction.SHADOW_ILES).size());
        System.out.println(LoRStaticAPI.getRarities());
    }
}
