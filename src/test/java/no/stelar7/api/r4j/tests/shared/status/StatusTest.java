package no.stelar7.api.r4j.tests.shared.status;

import no.stelar7.api.r4j.basic.constants.api.regions.*;
import no.stelar7.api.r4j.impl.R4J;
import no.stelar7.api.r4j.impl.shared.StatusAPI;
import no.stelar7.api.r4j.pojo.lol.status.PlatformData;
import no.stelar7.api.r4j.tests.SecretFile;
import org.junit.jupiter.api.Test;

public class StatusTest
{
    
    final R4J r4J = new R4J(SecretFile.CREDS);
    StatusAPI api = r4J.getStatusAPI();
    
    @Test
    public void testStatus()
    {
        PlatformData shardStatus = api.getShardStatus(LeagueShard.EUW1);
        PlatformData shardStatus1 = api.getShardStatus(RuneterraShard.EUROPE);
        PlatformData shardStatus2 = api.getShardStatus(ValorantShard.EU);
    }
    
    @Test
    public void testNullInit()
    {
        final R4J r4J = new R4J(null);
        r4J.getDDragonAPI().getRealm();
    }
}
