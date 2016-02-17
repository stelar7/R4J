package no.stelar7.api.l4j8.tests.staticdata;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import no.stelar7.api.l4j8.basic.constants.api.Server;
import no.stelar7.api.l4j8.basic.constants.api.URLEndpoint;
import no.stelar7.api.l4j8.tests.TestBase;

public class StaticVersionTest extends TestBase
{
    @Test
    public void doTest()
    {
        final List<String> data = (List<String>) TestBase.builder.build();

        Assert.assertFalse(data.isEmpty());

    }

    @Before
    public void initBeforeClass()
    {
        TestBase.builder.withServer(Server.GLOBAL);
        TestBase.builder.withEndpoint(URLEndpoint.STATIC_VERSIONS);
    }
}
