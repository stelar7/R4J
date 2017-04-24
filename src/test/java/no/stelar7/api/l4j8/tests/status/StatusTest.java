package no.stelar7.api.l4j8.tests.status;

import no.stelar7.api.l4j8.basic.constants.api.Platform;
import no.stelar7.api.l4j8.impl.*;
import no.stelar7.api.l4j8.pojo.status.ShardStatus;
import no.stelar7.api.l4j8.tests.SecretFile;
import org.junit.Test;

public class StatusTest
{
    
    final L4J8 l4j8 = new L4J8(SecretFile.CREDS);
    StatusAPI api = l4j8.getStatusAPI();
    
    @Test
    public void testStats()
    {
        ShardStatus status = api.getShardStatus(Platform.EUW1);
    }
}
