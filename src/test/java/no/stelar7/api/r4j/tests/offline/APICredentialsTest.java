package no.stelar7.api.r4j.tests.offline;

import no.stelar7.api.r4j.basic.APICredentials;
import no.stelar7.api.r4j.basic.constants.types.ApiKeyType;
import no.stelar7.api.r4j.basic.exceptions.APIUnsupportedActionException;
import org.junit.jupiter.api.*;

public class APICredentialsTest
{
    @Test
    public void theBuilderPutsEveryKeyWhereItBelongs()
    {
        APICredentials credentials = APICredentials.builder()
                                                   .lol("lol-key")
                                                   .tournament("tournament-key")
                                                   .tft("tft-key")
                                                   .lor("lor-key")
                                                   .val("val-key")
                                                   .riftbound("riftbound-key")
                                                   .build();

        Assertions.assertEquals("lol-key", credentials.getLoLAPIKey());
        Assertions.assertEquals("tournament-key", credentials.getTournamentAPIKey());
        Assertions.assertEquals("tft-key", credentials.getTFTAPIKey());
        Assertions.assertEquals("lor-key", credentials.getLORAPIKey());
        Assertions.assertEquals("val-key", credentials.getVALAPIKey());
        Assertions.assertEquals("riftbound-key", credentials.getRiftboundAPIKey());
        Assertions.assertEquals(ApiKeyType.TFT, credentials.getKeyType("tft-key"));
    }

    @Test
    public void keysThatAreNotSetAreMissing()
    {
        APICredentials credentials = APICredentials.builder().tft("tft-key").build();

        Assertions.assertTrue(credentials.hasKey(ApiKeyType.TFT));
        Assertions.assertFalse(credentials.hasKey(ApiKeyType.LOL));
        Assertions.assertThrows(APIUnsupportedActionException.class, credentials::getLoLAPIKey);
    }

    @Test
    public void allSetsEveryKeyAndCanBeOverridden()
    {
        APICredentials credentials = APICredentials.builder().all("dev-key").val("val-key").build();

        Assertions.assertEquals("dev-key", credentials.getLoLAPIKey());
        Assertions.assertEquals("dev-key", credentials.getRiftboundAPIKey());
        Assertions.assertEquals("val-key", credentials.getVALAPIKey());

        // the same as the single key constructor, so both find the same cached data
        Assertions.assertEquals(new APICredentials("dev-key").getUniqueKeyCombination(), APICredentials.builder().all("dev-key").build().getUniqueKeyCombination());
    }
}
