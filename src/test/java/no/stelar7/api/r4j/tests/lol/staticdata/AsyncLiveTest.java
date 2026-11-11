package no.stelar7.api.r4j.tests.lol.staticdata;

import no.stelar7.api.r4j.basic.calling.DataCallBuilder;
import no.stelar7.api.r4j.basic.constants.api.*;
import no.stelar7.api.r4j.basic.utils.StringList;
import no.stelar7.api.r4j.pojo.lol.staticdata.realm.Realm;
import org.junit.jupiter.api.*;

import java.util.*;
import java.util.concurrent.*;

/**
 * The non-blocking transport against a real https server (Data Dragon).
 * Needs the network, but no api key. Runs with -Plive.
 */
public class AsyncLiveTest
{
    @Test
    public void nonBlockingCallsWorkOverHttps() throws Exception
    {
        List<String> versions = new DataCallBuilder().withLimiters(false)
                                                     .withProxy(Constants.DDRAGON_PROXY)
                                                     .withEndpoint(URLEndpoint.DDRAGON_VERSIONS)
                                                     .buildAsAsync(StringList.class)
                                                     .get(30, TimeUnit.SECONDS);

        Assertions.assertFalse(versions.isEmpty());
        Assertions.assertTrue(versions.get(0).matches("\\d+\\.\\d+\\.\\d+"), versions.get(0));

        // the same answer as the blocking transport gives
        Object blocking = new DataCallBuilder().withLimiters(false)
                                               .withProxy(Constants.DDRAGON_PROXY)
                                               .withEndpoint(URLEndpoint.DDRAGON_VERSIONS)
                                               .build();
        Assertions.assertEquals(blocking, versions);

        // several at once
        List<CompletableFuture<Realm>> calls = new ArrayList<>();
        for (String region : Arrays.asList("euw", "na", "kr", "br"))
        {
            calls.add(new DataCallBuilder().withLimiters(false)
                                           .withProxy(Constants.DDRAGON_PROXY)
                                           .withEndpoint(URLEndpoint.DDRAGON_REALMS)
                                           .withURLParameter(Constants.REGION_PLACEHOLDER, region)
                                           .buildAsAsync(Realm.class));
        }

        for (CompletableFuture<Realm> call : calls)
        {
            Assertions.assertTrue(call.get(30, TimeUnit.SECONDS).getCDN().startsWith("https://"));
        }
    }
}
