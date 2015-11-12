package no.stelar7.api.l4j8.tests;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;

import no.stelar7.api.l4j8.basic.DataCall;
import no.stelar7.api.l4j8.basic.DataCall.DataCallBuilder;
import no.stelar7.api.l4j8.basic.Server;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestBase
{
    protected DataCallBuilder builder = DataCall.builder();

    @Before
    public void init()
    {
        this.builder.withAPIKey(SecretFile.API_KEY);
        this.builder.withServer(Server.EUW);
    }

}
