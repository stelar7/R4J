package no.stelar7.api.l4j8.tests.summoner;

import no.stelar7.api.l4j8.basic.constants.api.Constants;
import no.stelar7.api.l4j8.basic.constants.api.Server;
import no.stelar7.api.l4j8.impl.L4J8;
import no.stelar7.api.l4j8.tests.SecretFile;
import no.stelar7.api.l4j8.tests.TestBase;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;
import java.util.Optional;
import java.util.function.BiConsumer;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@SuppressWarnings("ALL")
public class SummonerNameById extends TestBase
{

    public static final Logger LOGGER = Logger.getGlobal();

    private final BiConsumer<Long, Optional<String>> doAssertions = (final Long key, final Optional<String> optional) ->
    {
        if (optional.isPresent())
        {
            final String value = optional.get();
            Assert.assertNotNull("No returned value for id ", value);
            SummonerNameById.LOGGER.log(Level.INFO, String.format("%s: %s", key, value));
        } else
        {
            SummonerNameById.LOGGER.log(Level.INFO, "Summoner " + key + " = " + optional);
        }
    };

    @Test
    public void doTest()
    {

        final L4J8 api = new L4J8(SecretFile.CREDS);

        final List<Long> values = Stream.of(Constants.TEST_SUMMONER_IDS).collect(Collectors.toList());

        api.getSummonerNames(Server.EUW, values).forEach(this.doAssertions);
    }

}
