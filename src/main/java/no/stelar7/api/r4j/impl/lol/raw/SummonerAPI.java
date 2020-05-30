package no.stelar7.api.r4j.impl.lol.raw;

import no.stelar7.api.r4j.basic.constants.api.regions.LeagueShard;
import no.stelar7.api.r4j.impl.lol.builders.summoner.SummonerBuilder;
import no.stelar7.api.r4j.pojo.lol.summoner.Summoner;

@SuppressWarnings("unchecked")
public final class SummonerAPI
{
    
    
    private static final SummonerAPI INSTANCE = new SummonerAPI();
    
    public static SummonerAPI getInstance()
    {
        return SummonerAPI.INSTANCE;
    }
    
    private final SummonerBuilder builder = new SummonerBuilder();
    
    private SummonerAPI()
    {
        // Hide public constructor
    }
    
    
    /**
     * The response object contains the summoner objects mapped by their user id.
     *
     * @param server     the region to execute against
     * @param summonerId summonerId associated with summoners to retrieve.
     * @return Optional Summoner
     */
    public Summoner getSummonerById(final LeagueShard server, String summonerId)
    {
        return builder.withPlatform(server).withSummonerId(summonerId).get();
    }
    
    /**
     * The response object contains the summoner objects mapped by their username.
     *
     * @param server       the region to execute against
     * @param summonerName summoner name  associated with summoner to retrieve.
     * @return Optional Summoner
     */
    public Summoner getSummonerByName(final LeagueShard server, String summonerName)
    {
        return builder.withPlatform(server).withName(summonerName).get();
    }
    
    /**
     * The response object contains the summoner objects mapped by their username.
     *
     * @param server    the region to execute against
     * @param accountId accountId associated with summoner to retrieve.
     * @return Optional Summoner
     */
    public Summoner getSummonerByAccount(final LeagueShard server, String accountId)
    {
        return builder.withPlatform(server).withAccountId(accountId).get();
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
