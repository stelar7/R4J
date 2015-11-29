package no.stelar7.api.l4j8.tests;

import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;

import no.stelar7.api.l4j8.basic.DataCall;
import no.stelar7.api.l4j8.basic.DataCall.DataCallBuilder;
import no.stelar7.api.l4j8.basic.Server;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestBase
{
    protected static DataCallBuilder builder = DataCall.builder();

    @BeforeClass
    public static void init()
    {
        builder = DataCall.builder();
        builder.asVerbose(true);
        builder.withAPIKey(SecretFile.API_KEY);
        builder.withServer(Server.EUW);
        builder.withRegion(Server.EUW);
    }

}
