package no.stelar7.api.l4j8.basic;

import no.stelar7.api.l4j8.pojo.champion.Champion;
import no.stelar7.api.l4j8.pojo.currentgame.CurrentGameInfo;
import no.stelar7.api.l4j8.pojo.featuredgames.FeaturedGameInfo;
import no.stelar7.api.l4j8.pojo.matchlist.MatchReference;
import no.stelar7.api.l4j8.pojo.shared.StringList;
import no.stelar7.api.l4j8.pojo.shared.StringMap;
import no.stelar7.api.l4j8.pojo.staticdata.champion.ChampionList;
import no.stelar7.api.l4j8.pojo.staticdata.item.Item;
import no.stelar7.api.l4j8.pojo.staticdata.item.ItemList;
import no.stelar7.api.l4j8.pojo.staticdata.language.LanguageStrings;
import no.stelar7.api.l4j8.pojo.staticdata.map.MapData;
import no.stelar7.api.l4j8.pojo.staticdata.mastery.Mastery;
import no.stelar7.api.l4j8.pojo.staticdata.mastery.MasteryList;
import no.stelar7.api.l4j8.pojo.staticdata.realm.Realm;
import no.stelar7.api.l4j8.pojo.staticdata.rune.Rune;
import no.stelar7.api.l4j8.pojo.staticdata.rune.RuneList;
import no.stelar7.api.l4j8.pojo.staticdata.summonerspell.SummonerSpell;
import no.stelar7.api.l4j8.pojo.staticdata.summonerspell.SummonerSpellList;
import no.stelar7.api.l4j8.pojo.summoner.Summoner;
import no.stelar7.api.l4j8.pojo.summoner.masteries.MasteryPage;
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
    CURRENTGAME("/observer-mode/rest/consumer/getSpectatorGameInfo/{platformId}/{summonerId}", "", CurrentGameInfo.class),
    FEATUREDGAME("/observer-mode/rest/featured", "", FeaturedGameInfo.class),
    STATIC_CHAMPION("/api/lol/static-data/{region}/{version}/champion", "v1.2", ChampionList.class),
    STATIC_CHAMPION_BY_ID("/api/lol/static-data/{region}/{version}/champion/{id}", "v1.2", no.stelar7.api.l4j8.pojo.staticdata.champion.Champion.class),
    STATIC_ITEM("/api/lol/static-data/{region}/{version}/item", "v1.2", ItemList.class),
    STATIC_ITEM_BY_ID("/api/lol/static-data/{region}/{version}/item/{id}", "v1.2", Item.class),
    STATIC_LANGUAGE_STRINGS("/api/lol/static-data/{region}/{version}/language-strings", "v1.2", LanguageStrings.class),
    STATIC_LANGUAGES("/api/lol/static-data/{region}/{version}/languages", "v1.2", StringList.class),
    STATIC_MAP("/api/lol/static-data/{region}/{version}/map", "v1.2", MapData.class),
    STATIC_MASTERY("/api/lol/static-data/{region}/{version}/mastery", "v1.2", MasteryList.class),
    STATIC_MASTERY_BY_ID("/api/lol/static-data/{region}/{version}/mastery", "v1.2", Mastery.class),
    STATIC_REALM("/api/lol/static-data/{region}/{version}/realm", "v1.2", Realm.class),
    STATIC_RUNE("/api/lol/static-data/{region}/{version}/rune", "v1.2", RuneList.class),
    STATIC_RUNE_BY_ID("/api/lol/static-data/{region}/{version}/rune/{id}", "v1.2", Rune.class),
    STATIC_SUMMONER_SPELL("/api/lol/static-data/{region}/{version}/summoner-spell", "v1.2", SummonerSpellList.class),
    STATIC_SUMMONER_SPELL_BY_ID("/api/lol/static-data/{region}/{version}/summoner-spell/{id}", "v1.2", SummonerSpell.class),
    STATIC_SUMMONER_VERSIONS("/api/lol/static-data/{region}/{version}/versions", "v1.2", StringList.class);

    private String value;
    private String version;
    private Class<? extends APIObject> type;

    private URLEndpoint(final String value, final String version, final Class<? extends APIObject> type)
    {
        this.value = value;
        this.version = version;
        this.type = type;
    }

    public Class<? extends APIObject> getType()
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

    /**
     * TODO: add lol-staus-v1.0
     * Checks if the endpoint is avalible from the server
     * 
     * @param server
     *            the server to check
     * @return true if it is avalible
     */
    public boolean isAvalibleFrom(Server server)
    {
        if (server == Server.PBE)
        {
            switch (this)
            {
                case CURRENTGAME:
                case FEATUREDGAME:
                case STATIC_CHAMPION:
                case STATIC_CHAMPION_BY_ID:
                case STATIC_ITEM:
                case STATIC_ITEM_BY_ID:
                case STATIC_LANGUAGE_STRINGS:
                case STATIC_LANGUAGES:
                case STATIC_MAP:
                case STATIC_MASTERY:
                case STATIC_MASTERY_BY_ID:
                case STATIC_REALM:
                case STATIC_RUNE:
                case STATIC_RUNE_BY_ID:
                case STATIC_SUMMONER_SPELL:
                case STATIC_SUMMONER_SPELL_BY_ID:
                case STATIC_SUMMONER_VERSIONS:
                    return true;
                default:
                    return false;
            }
        }
        return true;
    }
}
