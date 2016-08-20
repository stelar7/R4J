package no.stelar7.api.l4j8.tests;

import no.stelar7.api.l4j8.basic.DataCall;
import no.stelar7.api.l4j8.basic.DataCall.DataCallBuilder;
import no.stelar7.api.l4j8.basic.constants.api.Server;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestBase
{
    protected static DataCallBuilder builder = DataCall.builder();

    @BeforeClass
    public static void init()
    {
        TestBase.builder = DataCall.builder();
        TestBase.builder.asVerbose(true);
        TestBase.builder.withServer(Server.EUW);
        TestBase.builder.withRegion(Server.EUW);
    }


    protected TestBase()
    {
        // Empty public constructor
    }

}
