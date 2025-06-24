package no.stelar7.api.r4j.impl.lol.raw;

import no.stelar7.api.r4j.basic.constants.api.regions.LeagueShard;
import no.stelar7.api.r4j.impl.lol.builders.summoner.SummonerBuilder;
import no.stelar7.api.r4j.pojo.lol.summoner.Summoner;

public final class SummonerAPI
{
    private static final SummonerAPI INSTANCE = new SummonerAPI();
    private final SummonerBuilder builder = new SummonerBuilder();

    public static SummonerAPI getInstance()
    {
        return SummonerAPI.INSTANCE;
    }

    private SummonerAPI()
    {
        // Hide public constructor
    }
    
    /**
     * The response object contains the summoner objects mapped by their username.
     *
     * @param server the region to execute against
     * @param PUUID  puuid associated with summoner to retrieve.
     * @return Optional Summoner
     */
    public Summoner getSummonerByPUUID(final LeagueShard server, String PUUID)
    {
        return builder.withPlatform(server).withPUUID(PUUID).get();
    }
}
