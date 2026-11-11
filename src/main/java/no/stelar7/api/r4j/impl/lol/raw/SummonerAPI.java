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
    
    /**
     * The summoner of a player that signed in with RSO
     *
     * @param server      the region to execute against
     * @param accessToken the access token of the player
     * @return the summoner, or null if it was not found
     */
    public Summoner getSummonerByAccessToken(final LeagueShard server, String accessToken)
    {
        // not cached, as the result depends on who the token belongs to
        return new no.stelar7.api.r4j.basic.calling.DataCallBuilder().withAccessToken(accessToken)
                                                                      .withEndpoint(no.stelar7.api.r4j.basic.constants.api.URLEndpoint.V4_SUMMONER_ME)
                                                                      .withPlatform(server)
                                                                      .buildAs(Summoner.class);
    }
}
