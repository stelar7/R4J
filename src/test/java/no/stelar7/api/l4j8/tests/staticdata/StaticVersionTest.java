package no.stelar7.api.l4j8.tests.staticdata;

import no.stelar7.api.l4j8.basic.constants.api.Server;
import no.stelar7.api.l4j8.basic.constants.api.URLEndpoint;
import no.stelar7.api.l4j8.tests.TestBase;
import org.junit.*;

import java.util.List;

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
