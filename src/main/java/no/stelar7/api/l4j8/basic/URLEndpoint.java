package no.stelar7.api.l4j8.basic;

import java.util.List;
import java.util.Map;

import com.google.gson.reflect.TypeToken;

import no.stelar7.api.l4j8.pojo.currentgame.CurrentGameInfo;
import no.stelar7.api.l4j8.pojo.featuredgames.FeaturedGames;
import no.stelar7.api.l4j8.pojo.matchlist.MatchList;
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
import no.stelar7.api.l4j8.pojo.summoner.masteries.MasteryPages;
import no.stelar7.api.l4j8.pojo.summoner.runes.RunePages;

public enum URLEndpoint
{
    SUMMONER_BY_ID("/api/lol/{region}/{version}/summoner/{summonerId}", "v1.4", new TypeToken<Map<String, Summoner>>()
    {}.getType()),
    SUMMONER_BY_NAME("/api/lol/{region}/{version}/summoner/by-name/{summonerName}", "v1.4", new TypeToken<Map<String, Summoner>>()
    {}.getType()),
    SUMMONER_MASTERIES_BY_ID("/api/lol/{region}/{version}/summoner/{summonerId}/masteries", "v1.4", new TypeToken<Map<String, MasteryPages>>()
    {}.getType()),
    SUMMONER_RUNES_BY_ID("/api/lol/{region}/{version}/summoner/{summonerId}/runes", "v1.4", new TypeToken<Map<String, RunePages>>()
    {}.getType()),
    SUMMONER_NAME_BY_ID("/api/lol/{region}/{version}/summoner/{summonerId}/name", "v1.4", new TypeToken<Map<String, String>>()
    {}.getType()),
    CHAMPIONS("/api/lol/{region}/{version}/champion/{championId}", "v1.2", no.stelar7.api.l4j8.pojo.champion.ChampionList.class),
    CHAMPION_BY_ID("/api/lol/{region}/{version}/champion/{championId}", "v1.2", no.stelar7.api.l4j8.pojo.champion.Champion.class),
    MATCHLIST("/api/lol/{region}/{version}/matchlist/by-summoner/{summonerId}", "v2.2", MatchList.class),
    CURRENTGAME("/observer-mode/rest/consumer/getSpectatorGameInfo/{platformId}/{summonerId}", "", CurrentGameInfo.class),
    FEATUREDGAME("/observer-mode/rest/featured", "", FeaturedGames.class),
    STATIC_CHAMPION("/api/lol/static-data/{region}/{version}/champion", "v1.2", ChampionList.class),
    STATIC_CHAMPION_BY_ID("/api/lol/static-data/{region}/{version}/champion/{id}", "v1.2", no.stelar7.api.l4j8.pojo.staticdata.champion.Champion.class),
    STATIC_ITEM("/api/lol/static-data/{region}/{version}/item", "v1.2", ItemList.class),
    STATIC_ITEM_BY_ID("/api/lol/static-data/{region}/{version}/item/{id}", "v1.2", Item.class),
    STATIC_LANGUAGE_STRINGS("/api/lol/static-data/{region}/{version}/language-strings", "v1.2", LanguageStrings.class),
    STATIC_LANGUAGES("/api/lol/static-data/{region}/{version}/languages", "v1.2", new TypeToken<List<String>>()
    {}.getType()),
    STATIC_MAP("/api/lol/static-data/{region}/{version}/map", "v1.2", MapData.class),
    STATIC_MASTERY("/api/lol/static-data/{region}/{version}/mastery", "v1.2", MasteryList.class),
    STATIC_MASTERY_BY_ID("/api/lol/static-data/{region}/{version}/mastery", "v1.2", Mastery.class),
    STATIC_REALM("/api/lol/static-data/{region}/{version}/realm", "v1.2", Realm.class),
    STATIC_RUNE("/api/lol/static-data/{region}/{version}/rune", "v1.2", RuneList.class),
    STATIC_RUNE_BY_ID("/api/lol/static-data/{region}/{version}/rune/{id}", "v1.2", Rune.class),
    STATIC_SUMMONER_SPELL("/api/lol/static-data/{region}/{version}/summoner-spell", "v1.2", SummonerSpellList.class),
    STATIC_SUMMONER_SPELL_BY_ID("/api/lol/static-data/{region}/{version}/summoner-spell/{id}", "v1.2", SummonerSpell.class),
    STATIC_VERSIONS("/api/lol/static-data/{region}/{version}/versions", "v1.2", new TypeToken<List<String>>()
    {}.getType());

    private String value;
    private String version;
    private Object type;

    private URLEndpoint(final String value, final String version, final Object type)
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

    /**
     * TODO: add lol-staus-v1.0 Checks if the endpoint is avalible from the server
     *
     * @param server
     *            the server to check
     * @return true if it is avalible
     */
    public boolean isAvalibleFrom(final Server server)
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
                case STATIC_VERSIONS:
                    return true;
                default:
                    return false;
            }
        }
        return true;
    }
}
