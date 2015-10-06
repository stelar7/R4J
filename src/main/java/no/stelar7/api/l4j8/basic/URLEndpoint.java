package no.stelar7.api.l4j8.basic;

import no.stelar7.api.l4j8.pojo.champion.Champion;
import no.stelar7.api.l4j8.pojo.currentgame.CurrentGameInfo;
import no.stelar7.api.l4j8.pojo.matchlist.MatchReference;
import no.stelar7.api.l4j8.pojo.summoner.Summoner;
import no.stelar7.api.l4j8.pojo.summoner.masteries.MasteryPage;
import no.stelar7.api.l4j8.pojo.summoner.names.StringMap;
import no.stelar7.api.l4j8.pojo.summoner.runes.RunePage;

public enum URLEndpoint
{
    SUMMONERS_BY_ID("/api/lol/{region}/{version}/summoner/{summonerId}", "v1.4", Summoner.class),
    SUMMONERS_BY_NAME("/api/lol/{region}/{version}/summoner/by-name/{summonerName}", "v1.4", Summoner.class),
    SUMMONER_MASTERIES_BY_ID("/api/lol/{region}/{version}/summoner/{summonerId}/masteries", "v1.4", MasteryPage.class),
    SUMMONER_RUNES_BY_ID("/api/lol/{region}/{version}/summoner/{summonerId}/runes", "v1.4", RunePage.class),
    SUMMONER_NAMES_BY_ID("/api/lol/{region}/{version}/summoner/{summonerId}/name", "v1.4", StringMap.class),
    CHAMPION_BY_ID("/api/lol/{region}/{version}/champion/{championId}", "v1.2", Champion.class),
    MATCHLIST("/api/lol/{region}/{version}/matchlist/by-summoner/{summonerId}", "v2.2", MatchReference.class),
    CURRENTGAME("/observer-mode/rest/consumer/getSpectatorGameInfo/{platformId}/{summonerId}", "", CurrentGameInfo.class),;

    String value;
    String version;
    Class<?> type;

    private URLEndpoint(final String value, final String version, final Class<?> type)
    {
        this.value = value;
        this.version = version;
        this.type = type;
    }

    public Object getType()
    {
        return this.type;
    }

    public String getValue()
    {
        return this.value;
    }

    public String getVersion()
    {
        return this.version;
    }
}
