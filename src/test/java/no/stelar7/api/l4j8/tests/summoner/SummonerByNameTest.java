package no.stelar7.api.l4j8.tests.summoner;

import java.util.*;
import java.util.function.*;

import org.junit.*;

import no.stelar7.api.l4j8.basic.constants.api.*;
import no.stelar7.api.l4j8.impl.*;
import no.stelar7.api.l4j8.pojo.summoner.*;
import no.stelar7.api.l4j8.tests.*;

public class SummonerByNameTest
{

    private final BiConsumer<String, Optional<Summoner>> doAssertions = (final String key, final Optional<Summoner> optional) -> {
        if (optional.isPresent())
        {
            final Summoner value = optional.get();
            Assert.assertNotNull("Summoner name is NULL", value.getName());
            Assert.assertNotEquals("Summoner id is NULL", value.getId(), (Long) 0L);
            Assert.assertNotEquals("Summoner profile icon is NULL", value.getProfileIconId(), (Integer) 0);
            Assert.assertNotEquals("Summoner revision date is NULL", value.getRevisionDate(), (Long) 0L);
            Assert.assertNotEquals("Summoner level is NULL", value.getSummonerLevel(), (Integer) 0);
            Assert.assertNotNull("Summoner revison date DATE is NULL", value.getRevisionDateAsDate());
            Assert.assertEquals("Summoner revison date and DATE do not match", value.getRevisionDate(), (Long) value.getRevisionDateAsDate().toInstant().toEpochMilli());
            System.out.println(value);
        } else
        {
            System.out.println("Summoner " + key + " = " + optional);
        }
    };

    @Test
    public void doTest()
    {

        final L4J8 api = new L4J8(SecretFile.CREDS);

        // Generate list of summoner IDs
        final String[] values = { "stelar7", "henriko950", "vibbsen", "Tàylor Swíft" };

        api.getSummoner(Server.EUW, values).forEach(this.doAssertions);
        api.getSummoner(Server.LAN, values).forEach(this.doAssertions);

    }

}
