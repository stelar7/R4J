package no.stelar7.api.l4j8.basic.constants.api;

import com.google.gson.reflect.TypeToken;
import no.stelar7.api.l4j8.pojo.champion.*;
import no.stelar7.api.l4j8.pojo.championmastery.ChampionMastery;
import no.stelar7.api.l4j8.pojo.league.*;
import no.stelar7.api.l4j8.pojo.match.*;
import no.stelar7.api.l4j8.pojo.spectator.*;
import no.stelar7.api.l4j8.pojo.staticdata.champion.*;
import no.stelar7.api.l4j8.pojo.staticdata.item.*;
import no.stelar7.api.l4j8.pojo.staticdata.language.LanguageStrings;
import no.stelar7.api.l4j8.pojo.staticdata.map.MapData;
import no.stelar7.api.l4j8.pojo.staticdata.mastery.*;
import no.stelar7.api.l4j8.pojo.staticdata.perk.*;
import no.stelar7.api.l4j8.pojo.staticdata.profileicon.ProfileIconData;
import no.stelar7.api.l4j8.pojo.staticdata.realm.Realm;
import no.stelar7.api.l4j8.pojo.staticdata.rune.*;
import no.stelar7.api.l4j8.pojo.staticdata.summonerspell.*;
import no.stelar7.api.l4j8.pojo.status.ShardStatus;
import no.stelar7.api.l4j8.pojo.summoner.Summoner;
import no.stelar7.api.l4j8.pojo.summoner.masteries.MasteryPages;
import no.stelar7.api.l4j8.pojo.summoner.runes.RunePages;
import no.stelar7.api.l4j8.pojo.tournament.*;

import java.util.List;

public enum URLEndpoint
{
    // lol/summoner/v3/summoners/by-account/{accountId}
    // lol/summoner/v3/summoners/by-name/{summonerName}
    // lol/summoner/v3/summoners/{summonerId}
    V3_SUMMONER_BY_ACCOUNT("lol", "summoner", "v3", "summoners/by-account/" + Constants.ACCOUNT_ID_PLACEHOLDER, Summoner.class),
    V3_SUMMONER_BY_NAME("lol", "summoner", "v3", "summoners/by-name/" + Constants.SUMMONER_NAME_PLACEHOLDER, Summoner.class),
    V3_SUMMONER_BY_ID("lol", "summoner", "v3", "summoners/" + Constants.SUMMONER_ID_PLACEHOLDER, Summoner.class),
    
    // lol/platform/v3/runes/by-summoner/{summonerId}
    // lol/platform/v3/masteries/by-summoner/{summonerId}
    // typetoken should be removed once riot removes the map structure from the JSON
    V3_RUNES_BY_ID("lol", "platform", "v3", "runes/by-summoner/" + Constants.SUMMONER_ID_PLACEHOLDER, RunePages.class),
    V3_MASTERIES_BY_ID("lol", "platform", "v3", "masteries/by-summoner/" + Constants.SUMMONER_ID_PLACEHOLDER, MasteryPages.class),
    
    
    // lol/spectator/v3/featured-games
    // lol/spectator/v3/active-games/by-summoner/{summonerId}
    V3_SPECTATOR_FEATURED("lol", "spectator", "v3", "featured-games", FeaturedGames.class),
    V3_SPECTATOR_CURRENT("lol", "spectator", "v3", "active-games/by-summoner/" + Constants.SUMMONER_ID_PLACEHOLDER, SpectatorGameInfo.class),
    
    // lol/champion-mastery/v3/champion-masteries/by-summoner/{summonerId}
    // lol/champion-mastery/v3/champion-masteries/by-summoner/{summonerId}/by-champion/{championId}
    // lol/champion-mastery/v3/scores/by-summoner/{summonerId}
    V3_MASTERY_BY_ID("lol", "champion-mastery", "v3", "champion-masteries/by-summoner/" + Constants.SUMMONER_ID_PLACEHOLDER, new TypeToken<List<ChampionMastery>>() {}.getType()),
    V3_MASTERY_BY_CHAMPION("lol", "champion-mastery", "v3", "champion-masteries/by-summoner/" + Constants.SUMMONER_ID_PLACEHOLDER + "/by-champion/" + Constants.CHAMPION_ID_PLACEHOLDER, ChampionMastery.class),
    V3_MASTERY_SCORE("lol", "champion-mastery", "v3", "scores/by-summoner/" + Constants.SUMMONER_ID_PLACEHOLDER, Integer.class),
    
    // lol/platform/v3/champions
    // lol/platform/v3/champions/{id}
    V3_CHAMPIONS("lol", "platform", "v3", "champions", ChampionList.class),
    V3_CHAMPIONS_BY_ID("lol", "platform", "v3", "champions/" + Constants.ID_PLACEHOLDER, Champion.class),
    
    // lol/status/v3/shard-data
    V3_SHARD_STATUS("lol", "status", "v3", "shard-data", ShardStatus.class),
    
    // lol/static-data/v3/champions
    // lol/static-data/v3/champions/{id}
    // lol/static-data/v3/items
    // lol/static-data/v3/items/{id}
    // lol/static-data/v3/language-strings Retrieve language strings data.
    // lol/static-data/v3/languages Retrieve supported languages data.
    // lol/static-data/v3/maps Retrieve map data.
    // lol/static-data/v3/masteries Retrieves mastery list.
    // lol/static-data/v3/masteries/{id} Retrieves mastery item by its unique id.
    // lol/static-data/v3/profile-icons Retrieve profile icons.
    // lol/static-data/v3/realms Retrieve realm data.
    // lol/static-data/v3/runes Retrieves rune list.
    // lol/static-data/v3/runes/{id} Retrieves rune by its unique id.
    // lol/static-data/v3/summoner-spells Retrieves summoner spell list.
    // lol/static-data/v3/summoner-spells/{id} Retrieves summoner spell by its unique id.
    // lol/static-data/v3/versions Retrieve version data
    // lol/static-data/v3/reforged-runes Retrieves reforged rune list
    // lol/static-data/v3/reforged-runes/{id} Retrieves reforged rune by ID
    // lol/static-data/v3/reforged-rune-paths Retrieves reforged rune path list
    // lol/static-data/v3/reforged-rune-paths/{id} Retrieves reforged rune path by ID
    // lol/static-data/v3/tarball-links Retrieves full tarball link
    V3_STATIC_CHAMPIONS("lol", "static-data", "v3", "champions", StaticChampionList.class),
    V3_STATIC_CHAMPION_BY_ID("lol", "static-data", "v3", "champions/" + Constants.ID_PLACEHOLDER, StaticChampion.class),
    V3_STATIC_ITEMS("lol", "static-data", "v3", "items", ItemList.class),
    V3_STATIC_ITEM_BY_ID("lol", "static-data", "v3", "items/" + Constants.ID_PLACEHOLDER, Item.class),
    V3_STATIC_LANGUAGE_STRINGS("lol", "static-data", "v3", "language-strings", LanguageStrings.class),
    V3_STATIC_LANGUAGES("lol", "static-data", "v3", "languages", new TypeToken<List<String>>() {}.getType()),
    V3_STATIC_MAPS("lol", "static-data", "v3", "maps", MapData.class),
    V3_STATIC_MASTERIES("lol", "static-data", "v3", "masteries", StaticMasteryList.class),
    V3_STATIC_MASTERY_BY_ID("lol", "static-data", "v3", "masteries/" + Constants.ID_PLACEHOLDER, StaticMastery.class),
    V3_STATIC_PROFILEICONS("lol", "static-data", "v3", "profile-icons", ProfileIconData.class),
    V3_STATIC_REALMS("lol", "static-data", "v3", "realms", Realm.class),
    V3_STATIC_RUNES("lol", "static-data", "v3", "runes", StaticRuneList.class),
    V3_STATIC_RUNE_BY_ID("lol", "static-data", "v3", "runes/" + Constants.ID_PLACEHOLDER, StaticRune.class),
    V3_STATIC_SUMMONER_SPELLS("lol", "static-data", "v3", "summoner-spells", StaticSummonerSpellList.class),
    V3_STATIC_SUMMONER_SPELL_BY_ID("lol", "static-data", "v3", "summoner-spells/" + Constants.ID_PLACEHOLDER, StaticSummonerSpell.class),
    V3_STATIC_VERSIONS("lol", "static-data", "v3", "versions", new TypeToken<List<String>>() {}.getType()),
    V3_STATIC_PERK_BY_ID("lol", "static-data", "v3", "reforged-runes/" + Constants.ID_PLACEHOLDER, StaticPerk.class),
    V3_STATIC_PERKS("lol", "static-data", "v3", "reforged-runes", new TypeToken<List<StaticPerk>>() {}.getType()),
    V3_STATIC_PERKPATH_BY_ID("lol", "static-data", "v3", "reforged-rune-paths/" + Constants.ID_PLACEHOLDER, PerkPath.class),
    V3_STATIC_PERKPATHS("lol", "static-data", "v3", "reforged-rune-paths", new TypeToken<List<PerkPath>>() {}.getType()),
    V3_STATIC_TARBALL("lol", "static-data", "v3", "tarball-links", String.class),
    
    DDRAGON_CHAMPION_MANY("cdn/", "", Constants.VERSION_PLACEHOLDER, "/data/" + Constants.LOCALE_PLACEHOLDER + "/championFull.json", StaticChampionList.class),
    DDRAGON_ITEMS("cdn/", "", Constants.VERSION_PLACEHOLDER, "/data/" + Constants.LOCALE_PLACEHOLDER + "/item.json", ItemList.class),
    DDRAGON_LANGUAGE_STRINGS("cdn/", "", Constants.VERSION_PLACEHOLDER, "/data/" + Constants.LOCALE_PLACEHOLDER + "/language.json", LanguageStrings.class),
    DDRAGON_LANGUAGES("cdn/", "", "", "languages.json", new TypeToken<List<String>>() {}.getType()),
    DDRAGON_MAPS("cdn/", "", Constants.VERSION_PLACEHOLDER, "/data/" + Constants.LOCALE_PLACEHOLDER + "/map.json", MapData.class),
    DDRAGON_MASTERIES("cdn/", "", Constants.VERSION_PLACEHOLDER, "/data/" + Constants.LOCALE_PLACEHOLDER + "/mastery.json", StaticMasteryList.class),
    DDRAGON_PROFILEICONS("cdn/", "", Constants.VERSION_PLACEHOLDER, "/data/" + Constants.LOCALE_PLACEHOLDER + "/profileicon.json", ProfileIconData.class),
    DDRAGON_RUNES("cdn/", "", Constants.VERSION_PLACEHOLDER, "/data/" + Constants.LOCALE_PLACEHOLDER + "/rune.json", StaticRuneList.class),
    DDRAGON_PERKS("cdn/", "", Constants.VERSION_PLACEHOLDER, "/data/" + Constants.LOCALE_PLACEHOLDER + "/runesReforged.json", new TypeToken<List<StaticPerk>>() {}.getType()),
    DDRAGON_PERKPATHS("cdn/", "", Constants.VERSION_PLACEHOLDER, "/data/" + Constants.LOCALE_PLACEHOLDER + "/runesReforged.json", new TypeToken<List<PerkPath>>() {}.getType()),
    DDRAGON_SUMMONER_SPELLS("cdn/", "", Constants.VERSION_PLACEHOLDER, "/data/" + Constants.LOCALE_PLACEHOLDER + "/summoner.json", StaticSummonerSpellList.class),
    DDRAGON_VERSIONS("api/", "", "", "versions.json", new TypeToken<List<String>>() {}.getType()),
    DDRAGON_REALMS("realms/", "", Constants.REGION_PLACEHOLDER, ".json", Realm.class),
    DDRAGON_TARBALL("", "", "", "", String.class),
    
    //    lol/match/v3/matches/{matchId}
    //    lol/match/v3/matchlists/by-account/{accountId}
    //    lol/match/v3/matchlists/by-account/{accountId}/recent
    //    lol/match/v3/timelines/by-match/{matchId}
    V3_MATCH("lol", "match", "v3", "matches/" + Constants.MATCH_ID_PLACEHOLDER, Match.class),
    V3_MATCHLIST("lol", "match", "v3", "matchlists/by-account/" + Constants.ACCOUNT_ID_PLACEHOLDER, MatchList.class),
    V3_TIMELINE("lol", "match", "v3", "timelines/by-match/" + Constants.MATCH_ID_PLACEHOLDER, MatchTimeline.class),
    
    // api/lol/{region}/v2.5/league/by-summoner/{summonerIds}
    // api/lol/{region}/v2.5/league/by-summoner/{summonerIds}/entry
    // api/lol/{region}/v2.5/league/challenger
    // api/lol/{region}/v2.5/league/master
    V3_LEAGUE("lol", "league", "v3", "leagues/" + Constants.LEAGUE_ID_PLACEHOLDER, LeagueList.class),
    V3_LEAGUE_ENTRY("lol", "league", "v3", "positions/by-summoner/" + Constants.SUMMONER_ID_PLACEHOLDER, new TypeToken<List<LeaguePosition>>() {}.getType()),
    V3_LEAGUE_CHALLENGER("lol", "league", "v3", "challengerleagues/by-queue/" + Constants.QUEUE_PLACEHOLDER, LeagueList.class),
    V3_LEAGUE_MASTER("lol", "league", "v3", "masterleagues/by-queue/" + Constants.QUEUE_PLACEHOLDER, LeagueList.class),
    
    // lol/platform/v3/third-party-code/by-summoner/{summonerId}
    V3_THIRD_PARTY_CODE("lol", "platform", "v3", "third-party-code/by-summoner/" + Constants.SUMMONER_ID_PLACEHOLDER, String.class),
    
    
    // POST lol/tournament-stub/v3/codes Create a mock tournament code for the given tournament.
    // GET  lol/tournament-stub/v3/lobby-events/by-code/{tournamentCode} Gets a mock list of lobby events by tournament code.
    // POST lol/tournament-stub/v3/providers Creates a mock tournament provider and returns its ID.
    // POST lol/tournament-stub/v3/tournaments Creates a mock tournament and returns its ID.
    V3_STUB_TOURNAMENT_CODES("lol", "tournament-stub", "v3", "codes", new TypeToken<List<String>>() {}.getType()),
    V3_STUB_TOURNAMENT_LOBBY_EVENTS("lol", "tournament-stub", "v3", "lobby-events/by-code/" + Constants.TOURNAMENT_CODE_PLACEHOLDER, LobbyEventWrapper.class),
    V3_STUB_TOURNAMENT_PROVIDER("lol", "tournament-stub", "v3", "providers", Integer.class),
    V3_STUB_TOURNAMENT_TOURNAMENT("lol", "tournament-stub", "v3", "tournaments", Integer.class),
    
    
    // GET  lol/match/v3/matches/{matchId}/by-tournament-code/{tournamentCode}
    // GET  lol/match/v3/matches/by-tournament-code/{tournamentCode}/ids
    V3_TOURNAMENT_MATCH("lol", "match", "v3", "matches/" + Constants.MATCH_ID_PLACEHOLDER + "/by-tournament-code/" + Constants.TOURNAMENT_CODE_PLACEHOLDER, Match.class),
    V3_TOURNAMENT_MATCHLIST("lol", "match", "v3", "matches/by-tournament-code/" + Constants.TOURNAMENT_CODE_PLACEHOLDER + "/ids", new TypeToken<List<Long>>() {}.getType()),
    
    // POST lol/tournament/v3/codes Create a tournament code for the given tournament.
    // PUT  lol/tournament/v3/codes/{tournamentCode} Update the pick type, map, spectator type, or allowed summoners for a code.
    // GET  lol/tournament/v3/codes/{tournamentCode} Returns the tournament code DTO associated with a tournament code string.
    // GET  lol/tournament/v3/lobby-events/by-code/{tournamentCode} Gets a list of lobby events by tournament code.
    // POST lol/tournament/v3/providers Creates a tournament provider and returns its ID.
    // POST lol/tournament/v3/tournaments Creates a tournament and returns its ID.
    V3_TOURNAMENT_CODES("lol", "tournament", "v3", "codes", new TypeToken<List<String>>() {}.getType()),
    V3_TOURNAMENT_CODES_BY_CODE("lol", "tournament", "v3", "codes/" + Constants.TOURNAMENT_CODE_PLACEHOLDER, TournamentCode.class),
    V3_TOURNAMENT_LOBBY_EVENTS("lol", "tournament", "v3", "lobby-events/by-code/" + Constants.TOURNAMENT_CODE_PLACEHOLDER, LobbyEventWrapper.class),
    V3_TOURNAMENT_PROVIDER("lol", "tournament", "v3", "providers", Integer.class),
    V3_TOURNAMENT_TOURNAMENT("lol", "tournament", "v3", "tournaments", Integer.class),
    
    
    // POST lol/tournament/v3/codes Create a tournament code for the given tournament.
    // GET  lol/tournament/v3/lobby-events/by-code/{tournamentCode} Gets a list of lobby events by tournament code.
    // POST lol/tournament/v3/providers Creates a tournament provider and returns its ID.
    // POST lol/tournament/v3/tournaments Creates a tournament and returns its ID.
    V3_TOURNAMENT_STUB_CODES("lol", "tournament-stub", "v3", "codes", new TypeToken<List<String>>() {}.getType()),
    V3_TOURNAMENT_STUB_LOBBY_EVENTS("lol", "tournament-stub", "v3", "lobby-events/by-code/" + Constants.TOURNAMENT_CODE_PLACEHOLDER, LobbyEventWrapper.class),
    V3_TOURNAMENT_STUB_PROVIDER("lol", "tournament-stub", "v3", "providers", Integer.class),
    V3_TOURNAMENT_STUB_TOURNAMENT("lol", "tournament-stub", "v3", "tournaments", Integer.class);
    
    
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
    
    public boolean isDDragon()
    {
        return this.name().startsWith("DDRAGON");
    }
}
