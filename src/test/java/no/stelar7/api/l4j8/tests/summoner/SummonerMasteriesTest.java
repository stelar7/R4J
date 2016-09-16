package no.stelar7.api.l4j8.tests.summoner;

import no.stelar7.api.l4j8.basic.constants.api.Constants;
import no.stelar7.api.l4j8.basic.constants.api.Server;
import no.stelar7.api.l4j8.impl.L4J8;
import no.stelar7.api.l4j8.pojo.summoner.masteries.Mastery;
import no.stelar7.api.l4j8.pojo.summoner.masteries.MasteryPages;
import no.stelar7.api.l4j8.tests.SecretFile;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;
import java.util.Optional;
import java.util.function.BiConsumer;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SummonerMasteriesTest
{

    public static final Logger LOGGER = Logger.getGlobal();

    private final BiConsumer<Long, Optional<MasteryPages>> doAssertions = (final Long key, final Optional<MasteryPages> optional) ->
    {
        if (optional.isPresent())
        {
            final MasteryPages pages = optional.get();

            pages.getPages().forEach(page ->
            {

                Assert.assertNotNull("Mastery Page does not have an id", page.getId());
                // Assert.assertNotNull("Mastery Page does not have a name",
                // page.getName()); Names can be empty, so ignore this...
                Assert.assertNotNull("Mastery Page does not contain any masteries", page.getMasteries());
                Assert.assertNotNull("Unable to determine current Mastery page", page.isCurrent());

                page.getMasteries().forEach((final Mastery mastery) ->
                {
                    Assert.assertNotNull("Mastery does not have an id", mastery.getId());
                    Assert.assertNotNull("Mastery does not have a rank", mastery.getRank());

                    Assert.assertNotEquals("Mastery does not have a valid id", mastery.getId(), (Integer) 0);
                    Assert.assertNotEquals("Mastery does not have a valid rank", mastery.getRank(), (Integer) 0);
                });

            });
            SummonerMasteriesTest.LOGGER.log(Level.INFO, "MasteryPages " + key + " = OK");
        } else
        {
            SummonerMasteriesTest.LOGGER.log(Level.INFO, "MasteryPages " + key + " = BAD");
        }
    };

    @Test
    public void doTest()
    {
        final L4J8 api = new L4J8(SecretFile.CREDS);

        // Generate list of summoner IDs
        final List<Long> values = Stream.of(Constants.TEST_SUMMONER_IDS).collect(Collectors.toList());
        api.getMasteries(Server.EUW, values).forEach(this.doAssertions);
    }

}
