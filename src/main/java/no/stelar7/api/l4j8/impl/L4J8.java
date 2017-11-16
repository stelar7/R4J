package no.stelar7.api.l4j8.impl;

import no.stelar7.api.l4j8.basic.APICredentials;
import no.stelar7.api.l4j8.basic.calling.DataCall;
import no.stelar7.api.l4j8.impl.builders.league.LeagueBuilder;
import no.stelar7.api.l4j8.impl.builders.champion.ChampionBuilder;
import no.stelar7.api.l4j8.impl.builders.championmastery.ChampionMasteryBuilder;
import no.stelar7.api.l4j8.impl.builders.spectator.SpectatorBuilder;
import no.stelar7.api.l4j8.impl.builders.summoner.SummonerBuilder;
import no.stelar7.api.l4j8.impl.builders.match.*;

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
        DataCall.setCredentials(creds);
    }
    
    /**
     * Gets mastery api.
     *
     * @return the mastery api
     */
    public ChampionMasteryBuilder getMastery()
    {
        return new ChampionMasteryBuilder();
    }
    
    /**
     * Gets spectator api.
     *
     * @return the spectator api
     */
    public SpectatorBuilder getSpectator()
    {
        return new SpectatorBuilder();
    }
    
    /**
     * Gets summoner api.
     *
     * @return the summoner api
     */
    public SummonerBuilder getSummoner()
    {
        return new SummonerBuilder();
    }
    
    /**
     * Gets tournament api.
     *
     * @param useStub if true, returns the -stub instance
     * @return the tournament api
     */
    public TournamentAPI getTournamentAPI(boolean useStub)
    {
        return TournamentAPI.getInstance(useStub);
    }
    
    /**
     * Gets champion api.
     *
     * @return the champion api
     */
    public ChampionBuilder getChampion()
    {
        return new ChampionBuilder();
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
     * Gets match .
     *
     * @return the match api
     */
    public MatchBuilder getMatch()
    {
        return new MatchBuilder();
    }
    
    /**
     * Gets match timeline.
     *
     * @return matchtimeline
     */
    public TimelineBuilder getMatchTimeline()
    {
        return new TimelineBuilder();
    }
    
    
    /**
     * Gets matchlist.
     *
     * @return matchlist
     */
    public MatchListBuilder getMatchList()
    {
        return new MatchListBuilder();
    }
    
    /**
     * Gets league api.
     *
     * @return the league api
     */
    public LeagueBuilder getLeague()
    {
        return new LeagueBuilder();
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
