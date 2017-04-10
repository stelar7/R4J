package no.stelar7.api.l4j8.basic.constants.api;

import com.google.gson.reflect.TypeToken;
import no.stelar7.api.l4j8.pojo.summoner.Summoner;
import no.stelar7.api.l4j8.pojo.summoner.masteries.MasteryPages;
import no.stelar7.api.l4j8.pojo.summoner.runes.RunePages;

import java.util.Map;

public enum URLEndpoint
{
    
    // lol/summoner/v3/summoners/{summonerId}
    V3_SUMMONER_BY_ACCOUNT("lol", "summoner", "v3", "summoners/by-account/" + Constants.ACCOUNT_ID_PLACEHOLDER, Summoner.class),
    V3_SUMMONER_BY_NAME("lol", "summoner", "v3", "summoners/by-name/" + Constants.SUMMONER_NAME_PLACEHOLDER, Summoner.class),
    V3_SUMMONER_BY_ID("lol", "summoner", "v3", "summoners/" + Constants.SUMMONER_ID_PLACEHOLDER, Summoner.class),
    
    // lol/platform/v3/runes/by-summoner/{summonerId}
    V3_RUNES_BY_ID("lol", "platform", "v3", "runes/by-summoner/" + Constants.SUMMONER_ID_PLACEHOLDER, new TypeToken<Map<Long, RunePages>>()
    {}.getType()),
    
    // lol/platform/v3/masteries/by-summoner/{summonerId}
    V3_MASTERIES_BY_ID("lol", "platform", "v3", "masteries/by-summoner/" + Constants.SUMMONER_ID_PLACEHOLDER, new TypeToken<Map<Long, MasteryPages>>()
    {}.getType()),;
    
    private final String game;
    private final String service;
    private final String version;
    private final String resource;
    private final Object type;
    
    
    URLEndpoint(String game, String service, String version, String resource, Object type)
    {
        this.game = game;
        this.service = service;
        this.version = version;
        this.resource = resource;
        this.type = type;
    }
    
    public String getGame()
    {
        return game;
    }
    
    public String getService()
    {
        return service;
    }
    
    public String getVersion()
    {
        return version;
    }
    
    public String getResource()
    {
        return resource;
    }
    
    public Object getType()
    {
        return type;
    }
}
