package no.stelar7.api.r4j.impl.lol.builders.match;

import no.stelar7.api.r4j.basic.calling.*;
import no.stelar7.api.r4j.basic.constants.api.*;
import no.stelar7.api.r4j.basic.constants.api.regions.LeagueShard;
import no.stelar7.api.r4j.basic.utils.Pair;
import no.stelar7.api.r4j.pojo.lol.match.Match;

import java.util.*;

public class MatchBuilder
{
    
    private final long        id;
    private final LeagueShard platform;
    
    public MatchBuilder()
    {
        this.id = -1;
        this.platform = LeagueShard.UNKNOWN;
    }
    
    public MatchBuilder(LeagueShard platform, long id)
    {
        this.id = id;
        this.platform = platform;
    }
    
    public MatchBuilder withId(long id)
    {
        return new MatchBuilder(this.platform, id);
    }
    
    public MatchBuilder withPlatform(LeagueShard platform)
    {
        return new MatchBuilder(platform, this.id);
    }
    
    /**
     * Gets a match based on the parameters passed to the builder.
     * Matches older than 3 years will return null
     *
     * @return Match
     */
    public Match get()
    {
        if (this.id < 0 || this.platform == LeagueShard.UNKNOWN)
        {
            return null;
        }
        
        DataCallBuilder builder = new DataCallBuilder().withURLParameter(Constants.MATCH_ID_PLACEHOLDER, String.valueOf(this.id))
                                                       .withEndpoint(URLEndpoint.V4_MATCH)
                                                       .withPlatform(this.platform);
        
        Map<String, Object> data = new TreeMap<>();
        data.put("platform", this.platform);
        data.put("gameid", this.id);
        
        Optional<?> chl = DataCall.getCacheProvider().get(URLEndpoint.V4_MATCH, data);
        if (chl.isPresent())
        {
            return (Match) chl.get();
        }
        
        Object matchObj = builder.build();
        if (matchObj instanceof Pair)
        {
            return null;
        }
        
        Match match = (Match) matchObj;
        
        data.put("value", match);
        DataCall.getCacheProvider().store(URLEndpoint.V4_MATCH, data);
        
        return match;
    }
}
