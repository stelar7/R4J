package no.stelar7.api.l4j8.basic.constants.api;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import no.stelar7.api.l4j8.impl.lor.*;
import no.stelar7.api.l4j8.pojo.champion.*;
import no.stelar7.api.l4j8.pojo.championmastery.ChampionMastery;
import no.stelar7.api.l4j8.pojo.league.*;
import no.stelar7.api.l4j8.pojo.match.*;
import no.stelar7.api.l4j8.pojo.replay.*;
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
import no.stelar7.api.l4j8.pojo.tournament.*;

import java.util.List;

public enum URLEndpoint
{
    // lol/summoner/v3/summoners/by-account/{accountId}
    // lol/summoner/v3/summoners/by-name/{summonerName}
    // lol/summoner/v3/summoners/{summonerId}
    V3_SUMMONER_BY_ACCOUNT("lol", "summoner", "v4", "summoners/by-account/" + Constants.ACCOUNT_ID_PLACEHOLDER, Summoner.class),
    V3_SUMMONER_BY_PUUID("lol", "summoner", "v4", "summoners/by-puuid/" + Constants.PUUID_ID_PLACEHOLDER, Summoner.class),
    V3_SUMMONER_BY_NAME("lol", "summoner", "v4", "summoners/by-name/" + Constants.SUMMONER_NAME_PLACEHOLDER, Summoner.class),
    V3_SUMMONER_BY_ID("lol", "summoner", "v4", "summoners/" + Constants.SUMMONER_ID_PLACEHOLDER, Summoner.class),
    
    // lol/spectator/v3/featured-games
    // lol/spectator/v3/active-games/by-summoner/{summonerId}
    V3_SPECTATOR_FEATURED("lol", "spectator", "v4", "featured-games", FeaturedGames.class),
    V3_SPECTATOR_CURRENT("lol", "spectator", "v4", "active-games/by-summoner/" + Constants.SUMMONER_ID_PLACEHOLDER, SpectatorGameInfo.class),
    
    // lol/champion-mastery/v3/champion-masteries/by-summoner/{summonerId}
    // lol/champion-mastery/v3/champion-masteries/by-summoner/{summonerId}/by-champion/{championId}
    // lol/champion-mastery/v3/scores/by-summoner/{summonerId}
    V3_MASTERY_BY_ID("lol", "champion-mastery", "v4", "champion-masteries/by-summoner/" + Constants.SUMMONER_ID_PLACEHOLDER, new TypeToken<List<ChampionMastery>>() {}.getType()),
    V3_MASTERY_BY_CHAMPION("lol", "champion-mastery", "v4", "champion-masteries/by-summoner/" + Constants.SUMMONER_ID_PLACEHOLDER + "/by-champion/" + Constants.CHAMPION_ID_PLACEHOLDER, ChampionMastery.class),
    V3_MASTERY_SCORE("lol", "champion-mastery", "v4", "scores/by-summoner/" + Constants.SUMMONER_ID_PLACEHOLDER, Integer.class),
    
    // lol/platform/v3/champion-rotations
    V3_CHAMPION_ROTATIONS("lol", "platform", "v3", "champion-rotations", ChampionRotationInfo.class),
    
    // lol/status/v3/shard-data
    V3_SHARD_STATUS("lol", "status", "v3", "shard-data", ShardStatus.class),
    
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
    //    lol/match/v3/timelines/by-match/{matchId}
    V3_MATCH("lol", "match", "v4", "matches/" + Constants.MATCH_ID_PLACEHOLDER, Match.class),
    V3_MATCHLIST("lol", "match", "v4", "matchlists/by-account/" + Constants.ACCOUNT_ID_PLACEHOLDER, MatchList.class),
    V3_TIMELINE("lol", "match", "v4", "timelines/by-match/" + Constants.MATCH_ID_PLACEHOLDER, MatchTimeline.class),
    
    // TODO
    V4_GAMHS_MATCH("lol", "match", "v4", "match/" + Constants.MATCH_ID_PLACEHOLDER, GAMHSMatch.class),
    V4_GAMHS_MATCHLIST("lol", "match", "v4", "matchlists/by-puuid/" + Constants.PUUID_ID_PLACEHOLDER, new TypeToken<List<String>>() {}.getType()),
    
    // api/lol/{region}/v2.5/league/by-summoner/{summonerIds}
    // api/lol/{region}/v2.5/league/by-summoner/{summonerIds}/entry
    // api/lol/{region}/v2.5/league/challenger
    // api/lol/{region}/v2.5/league/master
    V3_LEAGUE("lol", "league", "v4", "leagues/" + Constants.LEAGUE_ID_PLACEHOLDER, LeagueList.class),
    V3_LEAGUE_ENTRY("lol", "league", "v4", "entries/by-summoner/" + Constants.SUMMONER_ID_PLACEHOLDER, new TypeToken<List<LeagueEntry>>() {}.getType()),
    //V3_LEAGUE_RANK("lol", "league", "v4", "entries/" + Constants.POSITIONAL_QUEUE_PLACEHOLDER + "/" + Constants.TIER_PLACEHOLDER + "/" + Constants.DIVISION_PLACEHOLDER, new TypeToken<List<LeagueEntry>>() {}.getType()),
    V3_LEAGUE_RANK("lol", "league-exp", "v4", "entries/" + Constants.POSITIONAL_QUEUE_PLACEHOLDER + "/" + Constants.TIER_PLACEHOLDER + "/" + Constants.DIVISION_PLACEHOLDER, new TypeToken<List<LeagueEntry>>() {}.getType()),
    //V3_LEAGUE_MASTER("lol", "league", "v4", "masterleagues/by-queue/" + Constants.QUEUE_PLACEHOLDER, LeagueList.class),
    //V3_LEAGUE_GRANDMASTER("lol", "league", "v4", "grandmasterleagues/by-queue/" + Constants.QUEUE_PLACEHOLDER, LeagueList.class),
    //V3_LEAGUE_CHALLENGER("lol", "league", "v4", "challengerleagues/by-queue/" + Constants.QUEUE_PLACEHOLDER, LeagueList.class),
    
    // lol/platform/v3/third-party-code/by-summoner/{summonerId}
    V3_THIRD_PARTY_CODE("lol", "platform", "v4", "third-party-code/by-summoner/" + Constants.SUMMONER_ID_PLACEHOLDER, String.class),
    
    
    // POST lol/tournament-stub/v3/codes Create a mock tournament code for the given tournament.
    // GET  lol/tournament-stub/v3/lobby-events/by-code/{tournamentCode} Gets a mock list of lobby events by tournament code.
    // POST lol/tournament-stub/v3/providers Creates a mock tournament provider and returns its ID.
    // POST lol/tournament-stub/v3/tournaments Creates a mock tournament and returns its ID.
    V3_STUB_TOURNAMENT_CODES("lol", "tournament-stub", "v4", "codes", new TypeToken<List<String>>() {}.getType()),
    V3_STUB_TOURNAMENT_LOBBY_EVENTS("lol", "tournament-stub", "v4", "lobby-events/by-code/" + Constants.TOURNAMENT_CODE_PLACEHOLDER, LobbyEventWrapper.class),
    V3_STUB_TOURNAMENT_PROVIDER("lol", "tournament-stub", "v4", "providers", Integer.class),
    V3_STUB_TOURNAMENT_TOURNAMENT("lol", "tournament-stub", "v4", "tournaments", Integer.class),
    
    
    // GET  lol/match/v3/matches/{matchId}/by-tournament-code/{tournamentCode}
    // GET  lol/match/v3/matches/by-tournament-code/{tournamentCode}/ids
    V3_TOURNAMENT_MATCH("lol", "match", "v4", "matches/" + Constants.MATCH_ID_PLACEHOLDER + "/by-tournament-code/" + Constants.TOURNAMENT_CODE_PLACEHOLDER, Match.class),
    V3_TOURNAMENT_MATCHLIST("lol", "match", "v4", "matches/by-tournament-code/" + Constants.TOURNAMENT_CODE_PLACEHOLDER + "/ids", new TypeToken<List<Long>>() {}.getType()),
    
    // POST lol/tournament/v3/codes Create a tournament code for the given tournament.
    // PUT  lol/tournament/v3/codes/{tournamentCode} Update the pick type, map, spectator type, or allowed summoners for a code.
    // GET  lol/tournament/v3/codes/{tournamentCode} Returns the tournament code DTO associated with a tournament code string.
    // GET  lol/tournament/v3/lobby-events/by-code/{tournamentCode} Gets a list of lobby events by tournament code.
    // POST lol/tournament/v3/providers Creates a tournament provider and returns its ID.
    // POST lol/tournament/v3/tournaments Creates a tournament and returns its ID.
    V3_TOURNAMENT_CODES("lol", "tournament", "v4", "codes", new TypeToken<List<String>>() {}.getType()),
    V3_TOURNAMENT_CODES_BY_CODE("lol", "tournament", "v4", "codes/" + Constants.TOURNAMENT_CODE_PLACEHOLDER, TournamentCode.class),
    V3_TOURNAMENT_LOBBY_EVENTS("lol", "tournament", "v4", "lobby-events/by-code/" + Constants.TOURNAMENT_CODE_PLACEHOLDER, LobbyEventWrapper.class),
    V3_TOURNAMENT_PROVIDER("lol", "tournament", "v4", "providers", Integer.class),
    V3_TOURNAMENT_TOURNAMENT("lol", "tournament", "v4", "tournaments", Integer.class),
    
    
    // POST lol/tournament/v3/codes Create a tournament code for the given tournament.
    // GET  lol/tournament/v3/lobby-events/by-code/{tournamentCode} Gets a list of lobby events by tournament code.
    // POST lol/tournament/v3/providers Creates a tournament provider and returns its ID.
    // POST lol/tournament/v3/tournaments Creates a tournament and returns its ID.
    V3_TOURNAMENT_STUB_CODES("lol", "tournament-stub", "v4", "codes", new TypeToken<List<String>>() {}.getType()),
    V3_TOURNAMENT_STUB_LOBBY_EVENTS("lol", "tournament-stub", "v4", "lobby-events/by-code/" + Constants.TOURNAMENT_CODE_PLACEHOLDER, LobbyEventWrapper.class),
    V3_TOURNAMENT_STUB_PROVIDER("lol", "tournament-stub", "v4", "providers", Integer.class),
    V3_TOURNAMENT_STUB_TOURNAMENT("lol", "tournament-stub", "v4", "tournaments", Integer.class),
    
    // LCU
    LCU_LOBBY("lol-lobby/v2/lobby", "", "", "", JsonObject.class),
    LCU_LOBBY_JOIN("lol-lobby/v2/party/{id}/join", "", "", "", Void.class),
    LCU_LOBBY_INVITE("lol-lobby/v2/lobby/invitations", "", "", "", JsonArray.class),
    LCU_LOBBY_POSITION("lol-lobby/v2/lobby/members/localMember/position-preferences", "", "", "", Void.class),
    LCU_LOBBY_MATCHMAKING("lol-lobby/v2/lobby/matchmaking/search", "", "", "", Void.class),
    
    LCU_RESTART("process-control/v1/process/restart", "", "", "", Void.class),
    
    LCU_SUMMONER_BY_NAME("lol-summoner/v2/summoners", "", "", "", JsonArray.class),
    
    LCU_LOGIN("lol-login/v1/session", "", "", "", JsonObject.class),
    
    LCU_CREATE_NOTIFICATION("player-notifications/v1/notifications", "", "", "", JsonObject.class),
    
    LCU_REPLAY_DOWNLOAD("lol-replays/v1/rofls/{gameId}/download", "", "", "", Void.class),
    LCU_REPLAY_DOWNLOAD_PATH("lol-replays/v1/rofls/path", "", "", "", String.class),
    LCU_REPLAY_WATCH("lol-replays/v1/rofls/{gameId}/watch", "", "", "", String.class),
    
    // REPLAY
    REPLAY_GAME("replay/game", "", "", "", ReplayGameClientProcessInfo.class),
    REPLAY_PLAYBACK("replay/playback", "", "", "", ReplayPlaybackInfo.class),
    REPLAY_RECORDING("replay/recording", "", "", "", ReplayRecordingInfo.class),
    REPLAY_RENDER("replay/render", "", "", "", ReplayRenderInfo.class),
    REPLAY_SEQUENCE("replay/sequence", "", "", "", ReplayKeyframeSequence.class),
    
    
    LOR_STATIC_ACTIVE_DECK("static-decklist", "", "", "", JsonObject.class),
    LOR_CARD_POSITIONS("positional-rectangles", "", "", "", LoRGameInfo.class),
    LOR_GAME_RESULT("game-result", "", "", "", LoRGameResult.class);
    
    
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
