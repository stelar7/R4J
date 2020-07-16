package no.stelar7.api.r4j.tests.lor;

import no.stelar7.api.r4j.impl.lor.LoRStaticAPI;
import no.stelar7.api.r4j.pojo.lor.offline.card.LoRFaction;
import org.junit.jupiter.api.Test;

public class TestStaticLoRData
{
    @Test
    public void testStatic()
    {
        LoRStaticAPI api = new LoRStaticAPI("set1", "en_us", "latest");
    
        System.out.println(api.getCards(LoRFaction.SHADOW_ILES).size());
        System.out.println(api.getRarities());
    }
}
