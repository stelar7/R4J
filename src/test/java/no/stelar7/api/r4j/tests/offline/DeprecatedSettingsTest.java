package no.stelar7.api.r4j.tests.offline;

import no.stelar7.api.r4j.basic.APICredentials;
import no.stelar7.api.r4j.basic.cache.CacheProvider;
import no.stelar7.api.r4j.basic.cache.impl.MemoryCacheProvider;
import no.stelar7.api.r4j.basic.calling.*;
import org.junit.jupiter.api.*;

/**
 * The static settings on DataCall are deprecated, but have to keep working: they read and change the default context.
 */
@SuppressWarnings("deprecation")
public class DeprecatedSettingsTest
{
    @Test
    public void theStaticSettingsAreThoseOfTheDefaultContext()
    {
        R4JContext     context     = R4JContext.getDefault();
        APICredentials credentials = context.getCredentials();
        CacheProvider  cache       = context.getCacheProvider();
        int            timeout     = context.getGlobalTimeout();
        int            parallel    = context.getNumberOfCallsAllowedInParallel();

        try
        {
            APICredentials other = new APICredentials("deprecated-settings-test-key");
            DataCall.setCredentials(other);
            Assertions.assertSame(other, context.getCredentials());
            Assertions.assertSame(other, DataCall.getCredentials());

            CacheProvider memory = new MemoryCacheProvider();
            DataCall.setCacheProvider(memory);
            Assertions.assertSame(memory, context.getCacheProvider());
            Assertions.assertSame(memory, DataCall.getCacheProvider());

            DataCall.setGlobalTimeout(1234);
            Assertions.assertEquals(1234, context.getGlobalTimeout());
            Assertions.assertEquals(1234, DataCall.getGlobalTimeout());

            DataCall.setNumberOfCallsAllowedInParallel(7);
            Assertions.assertEquals(7, context.getNumberOfCallsAllowedInParallel());
            Assertions.assertEquals(7, DataCall.getNumberOfCallsAllowedInParallel());
        } finally
        {
            context.setCredentials(credentials);
            context.setCacheProvider(cache);
            context.setGlobalTimeout(timeout);
            context.setNumberOfCallsAllowedInParallel(parallel);
        }
    }
}
