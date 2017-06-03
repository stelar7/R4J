package no.stelar7.api.l4j8.tests.cache;

import no.stelar7.api.l4j8.basic.DataCall;
import no.stelar7.api.l4j8.basic.cache.MySQLCache;
import no.stelar7.api.l4j8.basic.constants.api.*;
import no.stelar7.api.l4j8.impl.*;
import no.stelar7.api.l4j8.tests.SecretFile;
import org.junit.Test;

public class CacheTest
{
    
    final L4J8 l4j8 = new L4J8(SecretFile.CREDS);
    
    @Test
    public void doCacheTest()
    {
        DataCall.setCacheProvider(MySQLCache.create("", 3306, "l4j8test", "root", ""));
        l4j8.getMatchAPI().getRecentMatches(Platform.EUW1, Constants.TEST_ACCOUNT_IDS[0]).get(0).getFullMatch();
        System.out.println();
    }
    
}
