package no.stelar7.api.r4j.tests.lor;

import no.stelar7.api.r4j.basic.utils.Base32;
import org.junit.jupiter.api.*;

import java.nio.charset.StandardCharsets;

public class TestBase32
{
    @Test
    public void encodeDecodeReturnsSame()
    {
        String test = "THIS IS MY TEST";
        byte[] data = test.getBytes(StandardCharsets.UTF_8);
        
        String encoded = Base32.encode(data);
        byte[] decoded = Base32.decode(encoded);
        
        String original = new String(decoded, StandardCharsets.UTF_8);
        
        Assertions.assertEquals(test, original);
    }
}
