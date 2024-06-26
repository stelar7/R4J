package no.stelar7.api.r4j.tests.shared;

import no.stelar7.api.r4j.basic.calling.DataCall;
import no.stelar7.api.r4j.basic.constants.api.regions.LeagueShard;
import no.stelar7.api.r4j.basic.exceptions.APIResponseException;
import no.stelar7.api.r4j.impl.R4J;
import no.stelar7.api.r4j.pojo.shared.RiotAccount;
import no.stelar7.api.r4j.tests.SecretFile;
import org.junit.jupiter.api.*;

public class TestTimeout
{
    @Test
    public void getShards()
    {
        R4J api = new R4J(SecretFile.CREDS);
        DataCall.setCacheProvider(null);
        DataCall.setGlobalTimeout(1);
        Assertions.assertThrows(APIResponseException.class, () -> {
            RiotAccount account = api.getAccountAPI().getAccountByTag(LeagueShard.EUW1.toRegionShard(), "stelar7", "STL7");
        });
        DataCall.setGlobalTimeout(0);
    }
}
