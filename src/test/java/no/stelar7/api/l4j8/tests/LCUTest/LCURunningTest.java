package no.stelar7.api.l4j8.tests.LCUTest;

import no.stelar7.api.l4j8.impl.lcu.LCUConnection;
import org.junit.*;

public class LCURunningTest
{
    @Test
    public void testRunning()
    {
        Assert.assertNotNull("Unable to fecth connection string from league client... Is it running?", LCUConnection.getConnectionString());
    }
}
