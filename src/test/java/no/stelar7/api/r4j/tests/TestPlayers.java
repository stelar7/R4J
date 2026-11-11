package no.stelar7.api.r4j.tests;

import no.stelar7.api.r4j.basic.constants.api.regions.LeagueShard;
import no.stelar7.api.r4j.basic.constants.types.lol.*;
import no.stelar7.api.r4j.impl.lol.raw.LeagueAPI;
import no.stelar7.api.r4j.pojo.lol.league.LeagueEntry;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Players for the tests that need someone to look up, no matter who: the challenger ladder of a platform.
 */
public final class TestPlayers
{
    private TestPlayers()
    {
        // Hide public constructor
    }

    public static List<String> puuids(LeagueShard platform)
    {
        return LeagueAPI.getInstance().getLeagueByTierDivision(platform, GameQueueType.RANKED_SOLO_5X5, TierDivisionType.CHALLENGER_I, 1)
                        .stream().map(LeagueEntry::getPuuid).collect(Collectors.toList());
    }

    public static String anyPuuid(LeagueShard platform)
    {
        return puuids(platform).get(0);
    }
}
