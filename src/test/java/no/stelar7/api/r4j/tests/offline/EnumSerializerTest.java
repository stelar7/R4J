package no.stelar7.api.r4j.tests.offline;

import com.google.gson.*;
import no.stelar7.api.r4j.basic.constants.api.regions.LeagueShard;
import no.stelar7.api.r4j.basic.constants.types.lol.*;
import no.stelar7.api.r4j.basic.constants.types.tft.TFTTier;
import no.stelar7.api.r4j.basic.exceptions.APIEnumNotUpToDateException;
import no.stelar7.api.r4j.basic.serializer.GenericEnumSerializer;
import no.stelar7.api.r4j.basic.utils.Utils;
import org.junit.jupiter.api.*;

import java.util.*;

public class EnumSerializerTest
{
    private static final Gson gson = Utils.getGson();

    private static final List<Class<? extends Enum<?>>> ENUMS = Arrays.asList(
            GameQueueType.class, GameModeType.class, GameType.class, MapType.class, TeamType.class, TierType.class,
            LaneType.class, RoleType.class, SummonerSpellType.class, LeagueShard.class, TFTTier.class,
            TournamentMapType.class, TournamentPickType.class, TournamentSpectatorType.class,
            no.stelar7.api.r4j.basic.constants.types.val.GameQueueType.class,
            no.stelar7.api.r4j.basic.constants.types.val.TeamType.class,
            no.stelar7.api.r4j.basic.constants.types.val.TierDivisionType.class,
            no.stelar7.api.r4j.basic.constants.types.val.RoundResultType.class);

    @AfterEach
    public void reset()
    {
        GenericEnumSerializer.setStrict(false);
    }

    @Test
    public void knownValuesStillParse()
    {
        Assertions.assertEquals(MapType.SUMMONERS_RIFT, gson.fromJson("11", MapType.class));
        Assertions.assertEquals(TierType.CHALLENGER, gson.fromJson("\"CHALLENGER\"", TierType.class));
    }

    @Test
    public void unknownValuesDoNotFailTheResponse()
    {
        // must not throw; the result is the UNKNOWN / UNDEFINED constant of the enum if it has one, otherwise null
        Assertions.assertDoesNotThrow(() -> gson.fromJson("987654", GameQueueType.class));
        Assertions.assertDoesNotThrow(() -> gson.fromJson("987654", MapType.class));
        Assertions.assertDoesNotThrow(() -> gson.fromJson("\"NOT_A_REAL_VALUE\"", GameModeType.class));
        Assertions.assertDoesNotThrow(() -> gson.fromJson("\"NOT_A_REAL_VALUE\"", TierType.class));
    }

    @Test
    public void strictModeThrowsOnUnknownValues()
    {
        GenericEnumSerializer.setStrict(true);
        Assertions.assertThrows(APIEnumNotUpToDateException.class, () -> gson.fromJson("987654", GameQueueType.class));
    }

    /**
     * Objects written by a cache contain the name of the constant, so that has to be readable again.
     */
    @Test
    public void everyConstantSurvivesAJsonRoundtrip()
    {
        GenericEnumSerializer.setStrict(true);

        List<String> broken = new ArrayList<>();
        for (Class<? extends Enum<?>> type : ENUMS)
        {
            for (Enum<?> constant : type.getEnumConstants())
            {
                Object back;
                try
                {
                    back = gson.fromJson(gson.toJson(constant), type);
                } catch (RuntimeException e)
                {
                    back = e;
                }

                if (back != constant)
                {
                    broken.add(type.getSimpleName() + "." + constant.name() + " -> " + back);
                }
            }
        }

        Assertions.assertTrue(broken.isEmpty(), "constants that do not survive a roundtrip: " + broken);
    }
}
