package no.stelar7.api.l4j8.basic.constants.api;

import java.util.*;

import com.google.gson.reflect.*;

import no.stelar7.api.l4j8.pojo.championmastery.*;
import no.stelar7.api.l4j8.pojo.currentgame.*;
import no.stelar7.api.l4j8.pojo.featuredgames.*;
import no.stelar7.api.l4j8.pojo.match.*;
import no.stelar7.api.l4j8.pojo.matchlist.*;
import no.stelar7.api.l4j8.pojo.staticdata.champion.*;
import no.stelar7.api.l4j8.pojo.staticdata.item.*;
import no.stelar7.api.l4j8.pojo.staticdata.language.*;
import no.stelar7.api.l4j8.pojo.staticdata.map.*;
import no.stelar7.api.l4j8.pojo.staticdata.mastery.*;
import no.stelar7.api.l4j8.pojo.staticdata.mastery.Mastery;
import no.stelar7.api.l4j8.pojo.staticdata.realm.*;
import no.stelar7.api.l4j8.pojo.staticdata.rune.*;
import no.stelar7.api.l4j8.pojo.staticdata.rune.Rune;
import no.stelar7.api.l4j8.pojo.staticdata.summonerspell.*;
import no.stelar7.api.l4j8.pojo.status.*;
import no.stelar7.api.l4j8.pojo.summoner.*;
import no.stelar7.api.l4j8.pojo.summoner.masteries.*;
import no.stelar7.api.l4j8.pojo.summoner.runes.*;
import no.stelar7.api.l4j8.pojo.tournament.*;

public enum URLEndpoint
{
    SUMMONER_BY_ID("/api/lol/{region}/{version}/summoner/{summonerId}", "v1.4", new TypeToken<Map<Long, Summoner>>()
    {}.getType()),
    SUMMONER_BY_NAME("/api/lol/{region}/{version}/summoner/by-name/{summonerName}", "v1.4", new TypeToken<Map<String, Summoner>>()
    {}.getType()),
    SUMMONER_MASTERIES_BY_ID("/api/lol/{region}/{version}/summoner/{summonerId}/masteries", "v1.4", new TypeToken<Map<Long, MasteryPages>>()
    {}.getType()),
    SUMMONER_RUNES_BY_ID("/api/lol/{region}/{version}/summoner/{summonerId}/runes", "v1.4", new TypeToken<Map<Long, RunePages>>()
    {}.getType()),
    SUMMONER_NAME_BY_ID("/api/lol/{region}/{version}/summoner/{summonerId}/name", "v1.4", new TypeToken<Map<Long, String>>()
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
    {}.getType()),

    TOURNAMENT_PROVIDER("/tournament/public/{version}/provider", "v1", Long.class),
    TOURNAMENT_REGISTRATION("/tournament/public/{version}/tournament", "v1", Long.class),
    TOURNAMENT_CODE_CODES("/tournament/public/{version}/code/{tournamentCode}", "v1", new TypeToken<List<String>>()
    {}.getType()),
    TOURNAMENT_CODE("/tournament/public/{version}/code/{tournamentCode}", "v1", TournamentCode.class),
    TOURNAMENT_LOBBY("/tournament/public/{version}/lobby/events/by-code/{tournamentCode}", "v1", LobbyEventWrapper.class),
    TOURNAMENT_MATCH("/api/lol/{region}/{version}/match/by-tournament/{tournamentCode}/ids", "v2.2", new TypeToken<List<Long>>()
    {}.getType()),
    TOURNAMENT_MATCH_BY_ID("/api/lol/{region}/{version}/match/for-tournament/{matchId}", "v2.2", MatchDetail.class),

    CHAMPIONMASTERY_BY_ID("/championmastery/location/{platformId}/player/{summonerId}/champion/{championId}", "", ChampionMastery.class),
    CHAMPIONMASTERY("/championmastery/location/{platformId}/player/{summonerId}/champions", "", new TypeToken<List<ChampionMastery>>()
    {}.getType()),
    CHAMPIONMASTERY_SCORE("/championmastery/location/{platformId}/player/{summonerId}/score", "", Integer.class),
    CHAMPIONMASTERY_TOP("/championmastery/location/{platformId}/player/{summonerId}/topchampions", "", new TypeToken<List<ChampionMastery>>()
    {}.getType()),

    STATUS("/shards", "", new TypeToken<List<Shard>>()
    {}.getType()),
    STATUS_BY_SHARD("/shards/{shard}", "", ShardStatus.class);

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
     * Checks if the endpoint is avalible from the server
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
                case STATUS:
                case STATUS_BY_SHARD:
                    return true;
                default:
                    return false;
            }
        }
        return true;
    }
}
