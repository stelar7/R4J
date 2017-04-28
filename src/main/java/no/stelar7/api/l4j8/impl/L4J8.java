package no.stelar7.api.l4j8.impl;

import no.stelar7.api.l4j8.basic.*;
import no.stelar7.api.l4j8.basic.ratelimiting.RateLimiter;

/**
 * The base class for calling anything from this api wrapper
 */
public class L4J8
{
    
    /**
     * Constructor for the L4J8 class.
     *
     * @param creds   the API credentials used for the API (your token)
     * @param limiter the limiter
     */
    public L4J8(final APICredentials creds, RateLimiter... limiter)
    {
        DataCall.setCredentials(creds);
        DataCall.setRatelimiter(limiter);
    }
    
    /**
     * Gets mastery api.
     *
     * @return the mastery api
     */
    public MasteryAPI getMasteryAPI()
    {
        return MasteryAPI.getInstance();
    }
    
    /**
     * Gets spectator api.
     *
     * @return the spectator api
     */
    public SpectatorAPI getSpectatorAPI()
    {
        return SpectatorAPI.getInstance();
    }
    
    /**
     * Gets summoner api.
     *
     * @return the summoner api
     */
    public SummonerAPI getSummonerAPI()
    {
        return SummonerAPI.getInstance();
    }
    
    /**
     * Gets tournament api.
     *
     * @return the tournament api
     */
    public TournamentAPI getTournamentAPI()
    {
        return TournamentAPI.getInstance();
    }
    
    /**
     * Gets champion api.
     *
     * @return the champion api
     */
    public ChampionAPI getChampionAPI()
    {
        return ChampionAPI.getInstance();
    }
    
    /**
     * Gets status api.
     *
     * @return the status api
     */
    public StatusAPI getStatusAPI()
    {
        return StatusAPI.getInstance();
    }
    
    /**
     * Gets static api.
     *
     * @return the static api
     */
    public StaticAPI getStaticAPI()
    {
        return StaticAPI.getInstance();
    }
    
    /**
     * Gets match api.
     *
     * @return the match api
     */
    public MatchAPI getMatchAPI()
    {
        return MatchAPI.getInstance();
    }
    
    /**
     * Gets league api.
     *
     * @return the league api
     */
    public LeagueAPI getLeagueAPI()
    {
        return LeagueAPI.getInstance();
    }
    
    /**
     * Gets image api.
     *
     * @return the image api
     */
    public ImageAPI getImageAPI()
    {
        return ImageAPI.getInstance();
    }
}
