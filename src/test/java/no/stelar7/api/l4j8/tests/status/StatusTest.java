package no.stelar7.api.l4j8.tests.status;

import no.stelar7.api.l4j8.basic.constants.api.Platform;
import no.stelar7.api.l4j8.impl.*;
import no.stelar7.api.l4j8.pojo.status.ShardStatus;
import no.stelar7.api.l4j8.tests.SecretFile;
import org.junit.*;

import java.util.Optional;

public class StatusTest
{
    
    @Test
    public void testStats()
    {
        final L4J8 l4j8 = new L4J8(SecretFile.CREDS);
        StatusAPI  api  = l4j8.getStatusAPI();
        
        Optional<ShardStatus> status = api.getShardStatus(Platform.EUW1);
        Assert.assertTrue("no data?", status.isPresent());
    
        status.ifPresent(System.out::println);
    }
}
