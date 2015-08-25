package no.stelar7.api.l4j8.basic;

import no.stelar7.api.l4j8.dto.summoner.Summoner;
import no.stelar7.api.l4j8.dto.summoner.masteries.MasteryPage;
import no.stelar7.api.l4j8.dto.summoner.names.StringMap;
import no.stelar7.api.l4j8.dto.summoner.runes.RunePage;

public enum URLEndpoint
{
    SUMMONERS_BY_ID("/api/lol/{server}/{version}/summoner/{summonerId}", "v1.4", Summoner.class),
    SUMMONERS_BY_NAME("/api/lol/{server}/{version}/summoner/by-name/{summonerName}", "v1.4", Summoner.class),
    SUMMONER_MASTERIES_BY_ID("/api/lol/{server}/{version}/summoner/{summonerId}/masteries", "v1.4", MasteryPage.class),
    SUMMONER_RUNES_BY_ID("/api/lol/{server}/{version}/summoner/{summonerId}/runes", "v1.4", RunePage.class),
    SUMMONER_NAMES_BY_ID("/api/lol/{server}/{version}/summoner/{summonerId}/name", "v1.4", StringMap.class),;

    String value;
    String version;
    Class<?> type;

    private URLEndpoint(final String value, final String version, Class<?> type)
    {
        this.value = value;
        this.version = version;
        this.type = type;
    }

    public String getVersion()
    {
        return version;
    }

    public String getValue()
    {
        return value;
    }

    public Object getType()
    {
        return type;
    }
}
