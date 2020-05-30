package no.stelar7.api.r4j.tests.lol.status;

import no.stelar7.api.r4j.basic.constants.api.regions.LeagueShard;
import no.stelar7.api.r4j.impl.*;
import no.stelar7.api.r4j.impl.lol.raw.StatusAPI;
import no.stelar7.api.r4j.pojo.lol.status.ShardStatus;
import no.stelar7.api.r4j.tests.SecretFile;
import org.junit.Test;

public class StatusTest
{
    
    final R4J r4J = new R4J(SecretFile.CREDS);
    StatusAPI api = r4J.getStatusAPI();
    
    @Test
    public void testStats()
    {
        ShardStatus status = api.getShardStatus(LeagueShard.EUW1);
    }
    
    @Test
    public void testNullInit()
    {
        final R4J r4J = new R4J(null);
        r4J.getDDragonAPI().getRealm();
    }
}
