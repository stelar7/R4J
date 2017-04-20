package no.stelar7.api.l4j8.impl;

import no.stelar7.api.l4j8.basic.APICredentials;
import no.stelar7.api.l4j8.basic.DataCall.DataCallBuilder;

/**
 * The base class for calling anything from this api wrapper
 */
public class L4J8
{
    
    /**
     * Constructor for the L4J8 class.
     *
     * @param creds the API credentials used for the API (your token)
     */
    public L4J8(final APICredentials creds)
    {
        DataCallBuilder.setCredentials(creds);
    }
    
    public MasteryAPI getMasteryAPI()
    {
        return MasteryAPI.getInstance();
    }
    
    public SpectatorAPI getSpectatorAPI()
    {
        return SpectatorAPI.getInstance();
    }
    
    public SummonerAPI getSummonerAPI()
    {
        return SummonerAPI.getInstance();
    }
    
    public TournamentAPI getTournamentAPI()
    {
        return TournamentAPI.getInstance();
    }
    
    public ChampionAPI getChampionAPI()
    {
        return ChampionAPI.getInstance();
    }
    
    public StatusAPI getStatusAPI()
    {
        return StatusAPI.getInstance();
    }
    
    public StaticAPI getStaticAPI()
    {
        return StaticAPI.getInstance();
    }
    
    public MatchAPI getMatchAPI()
    {
        return MatchAPI.getInstance();
    }
}
