package no.stelar7.api.r4j.tests.offline;

import no.stelar7.api.r4j.basic.utils.Utils;
import no.stelar7.api.r4j.pojo.lol.match.v5.*;
import org.junit.jupiter.api.*;

import java.util.*;

public class MatchChallengesTest
{
    // a participant the way the api sends it, trimmed to a few challenges
    private static final String PARTICIPANT =
            "{\"puuid\":\"abc\",\"challenges\":{\"kda\":3.5,\"killParticipation\":0.6153846153846154,\"soloKills\":2,\"12AssistStreakCount\":1," +
            "\"SWARM_DefeatAatrox\":0,\"InfernalScalePickup\":4,\"legendaryItemUsed\":[3078,6610],\"earliestBaron\":1321.7," +
            "\"aChallengeAddedNextPatch\":7,\"damagePerMinute\":\"not a number\"}," +
            "\"missions\":{\"playerScore0\":12,\"playerScore1\":0.5,\"playerScore11\":3}}";

    private final MatchParticipant participant = Utils.getGson().fromJson(PARTICIPANT, MatchParticipant.class);

    @Test
    public void challengesAreAvailableAsFields()
    {
        MatchChallenges challenges = participant.getTypedChallenges();

        Assertions.assertEquals(3.5, challenges.getKda());
        Assertions.assertEquals(0.6153846153846154, challenges.getKillParticipation());
        Assertions.assertEquals(2.0, challenges.getSoloKills());
        // documented as an integer, sent as a decimal
        Assertions.assertEquals(1321.7, challenges.getEarliestBaron());
        Assertions.assertEquals(Arrays.asList(3078, 6610), challenges.getLegendaryItemUsed());

        // names that are not valid java names
        Assertions.assertEquals(1.0, challenges.getTwelveAssistStreakCount());
        Assertions.assertEquals(0.0, challenges.getSwarmDefeatAatrox());
        Assertions.assertEquals(4.0, challenges.getInfernalScalePickup());
    }

    @Test
    public void whatIsMissingOrUnexpectedDoesNotBreakTheRest()
    {
        MatchChallenges challenges = participant.getTypedChallenges();

        // not sent for this match
        Assertions.assertNull(challenges.getTurretPlatesTaken());
        // sent as something that is not a number
        Assertions.assertNull(challenges.getDamagePerMinute());

        // a challenge the class does not know yet is still in the map
        Assertions.assertEquals(7.0, participant.getChallenges().get("aChallengeAddedNextPatch"));
        Assertions.assertEquals(10, participant.getChallenges().size());
    }

    @Test
    public void missionsAreAvailableAsFields()
    {
        MatchMissions missions = participant.getTypedMissions();

        Assertions.assertEquals(12.0, missions.getPlayerScore0());
        Assertions.assertEquals(0.5, missions.getPlayerScore(1));
        Assertions.assertEquals(3.0, missions.getPlayerScore(11));
        Assertions.assertNull(missions.getPlayerScore(5));
    }

    @Test
    public void aParticipantWithoutChallengesHasNone()
    {
        MatchParticipant bare = Utils.getGson().fromJson("{\"puuid\":\"abc\"}", MatchParticipant.class);

        Assertions.assertNull(bare.getTypedChallenges());
        Assertions.assertNull(bare.getTypedMissions());
    }
}
