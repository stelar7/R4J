package no.stelar7.api.l4j8.tests.summoner;

import no.stelar7.api.l4j8.basic.constants.api.*;
import no.stelar7.api.l4j8.impl.*;
import no.stelar7.api.l4j8.pojo.summoner.*;
import no.stelar7.api.l4j8.tests.*;
import org.junit.*;

import java.util.*;
import java.util.function.*;
import java.util.logging.*;
import java.util.stream.*;

public class SummonerByNameTest
{
    
    public static final Logger LOGGER = Logger.getGlobal();
    
    private final BiConsumer<String, Optional<Summoner>> doAssertions = (final String key, final Optional<Summoner> optional) ->
    {
        if (optional.isPresent())
        {
            final Summoner value = optional.get();
            Assert.assertNotNull("Summoner name is NULL", value.getName());
            Assert.assertNotEquals("Summoner id is NULL", value.getId(), (Long) 0L);
            Assert.assertNotEquals("Summoner profile icon is NULL", value.getProfileIconId(), (Integer) 0);
            Assert.assertNotEquals("Summoner revision date is NULL", value.getRevisionDate(), (Long) 0L);
            Assert.assertNotEquals("Summoner level is NULL", value.getSummonerLevel(), (Integer) 0);
            Assert.assertNotNull("Summoner revison date DATE is NULL", value.getRevisionDateAsDate());
            Assert.assertEquals("Summoner revison date and DATE do not match", value.getRevisionDate(), (Long) value.getRevisionDateAsDate()
                                                                                                                    .toInstant()
                                                                                                                    .toEpochMilli());
            SummonerByNameTest.LOGGER.log(Level.INFO, String.valueOf(value));
        } else
        {
            SummonerByNameTest.LOGGER.log(Level.INFO, "Summoner " + key + " = " + optional);
        }
    };
    
    @Test
    public void doTest()
    {
        
        final L4J8 api = new L4J8(SecretFile.CREDS);
        
        // Generate list of summoner IDs
        final List<String> values = Stream.of(Constants.TEST_SUMMONER_NAMES).collect(Collectors.toList());
        
        api.getSummonerByName(Server.EUW, values).forEach(this.doAssertions);
        api.getSummonerByName(Server.LAN, values).forEach(this.doAssertions);
        
    }
    
}
