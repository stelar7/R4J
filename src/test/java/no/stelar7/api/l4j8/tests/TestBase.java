package no.stelar7.api.l4j8.tests;

import org.junit.*;
import org.junit.runners.*;

import no.stelar7.api.l4j8.basic.*;
import no.stelar7.api.l4j8.basic.DataCall.*;
import no.stelar7.api.l4j8.basic.constants.api.*;
import no.stelar7.api.l4j8.impl.*;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestBase
{
    L4J8                             l4j8    = new L4J8(SecretFile.CREDS);
    protected static DataCallBuilder builder = DataCall.builder();

    public TestBase()
    {
        // Empty public constructor
    }

    @BeforeClass
    public static void init()
    {
        TestBase.builder = DataCall.builder();
        TestBase.builder.asVerbose(true);
        TestBase.builder.withServer(Server.EUW);
        TestBase.builder.withRegion(Server.EUW);
    }

}
