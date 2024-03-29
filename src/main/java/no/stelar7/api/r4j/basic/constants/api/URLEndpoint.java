package no.stelar7.api.r4j.basic.constants.api;

import com.google.gson.*;
import no.stelar7.api.r4j.basic.utils.*;
import no.stelar7.api.r4j.pojo.lol.challenges.*;
import no.stelar7.api.r4j.pojo.lol.champion.ChampionRotationInfo;
import no.stelar7.api.r4j.pojo.lol.championmastery.*;
import no.stelar7.api.r4j.pojo.lol.clash.*;
import no.stelar7.api.r4j.pojo.lol.league.*;
import no.stelar7.api.r4j.pojo.lol.liveclient.*;
import no.stelar7.api.r4j.pojo.lol.replay.*;
import no.stelar7.api.r4j.pojo.lol.spectator.*;
import no.stelar7.api.r4j.pojo.lol.spectator.v5.SpectatorConnectionInformation;
import no.stelar7.api.r4j.pojo.lol.staticdata.champion.StaticChampionList;
import no.stelar7.api.r4j.pojo.lol.staticdata.item.ItemList;
import no.stelar7.api.r4j.pojo.lol.staticdata.language.LanguageStrings;
import no.stelar7.api.r4j.pojo.lol.staticdata.map.MapData;
import no.stelar7.api.r4j.pojo.lol.staticdata.mastery.StaticMasteryList;
import no.stelar7.api.r4j.pojo.lol.staticdata.perk.*;
import no.stelar7.api.r4j.pojo.lol.staticdata.profileicon.ProfileIconData;
import no.stelar7.api.r4j.pojo.lol.staticdata.realm.Realm;
import no.stelar7.api.r4j.pojo.lol.staticdata.rune.StaticRuneList;
import no.stelar7.api.r4j.pojo.lol.staticdata.summonerspell.StaticSummonerSpellList;
import no.stelar7.api.r4j.pojo.lol.status.PlatformData;
import no.stelar7.api.r4j.pojo.lol.summoner.Summoner;
import no.stelar7.api.r4j.pojo.lol.tournament.*;
import no.stelar7.api.r4j.pojo.lor.oauth.*;
import no.stelar7.api.r4j.pojo.lor.offline.expedition.LoRExpeditionInfo;
import no.stelar7.api.r4j.pojo.lor.offline.game.*;
import no.stelar7.api.r4j.pojo.lor.ranked.LoRRankedPlayerList;
import no.stelar7.api.r4j.pojo.shared.*;
import no.stelar7.api.r4j.pojo.tft.league.*;
import no.stelar7.api.r4j.pojo.val.content.Content;
import no.stelar7.api.r4j.pojo.val.match.VALMatch;
import no.stelar7.api.r4j.pojo.val.matchlist.RecentMatchList;
import no.stelar7.api.r4j.pojo.val.ranked.Leaderboard;

public enum URLEndpoint
{
    // lol/summoner/v3/summoners/by-account/{accountId}
    // lol/summoner/v3/summoners/by-name/{summonerName}
    // lol/summoner/v3/summoners/{summonerId}
    V4_SUMMONER_BY_ACCOUNT("lol", "summoner", "v4", "summoners/by-account/" + Constants.ACCOUNT_ID_PLACEHOLDER, Summoner.class),
    V4_SUMMONER_BY_PUUID("lol", "summoner", "v4", "summoners/by-puuid/" + Constants.PUUID_ID_PLACEHOLDER, Summoner.class),
    V4_SUMMONER_BY_NAME("lol", "summoner", "v4", "summoners/by-name/" + Constants.SUMMONER_NAME_PLACEHOLDER, Summoner.class),
    V4_SUMMONER_BY_ID("lol", "summoner", "v4", "summoners/" + Constants.SUMMONER_ID_PLACEHOLDER, Summoner.class),
    
    // lol/spectator/v3/featured-games
    // lol/spectator/v3/active-games/by-summoner/{summonerId}
    V4_SPECTATOR_FEATURED("lol", "spectator", "v4", "featured-games", FeaturedGames.class),
    V4_SPECTATOR_CURRENT("lol", "spectator", "v4", "active-games/by-summoner/" + Constants.SUMMONER_ID_PLACEHOLDER, SpectatorGameInfo.class),
    
    V5_SPECTATOR_FEATURED("lol", "spectator", "v5", "featured-games", FeaturedGames.class),
    V5_SPECTATOR_CURRENT("lol", "spectator", "v5", "active-games/by-summoner/" + Constants.PUUID_ID_PLACEHOLDER, SpectatorGameInfo.class),
    
    // lol/champion-mastery/v4/champion-masteries/by-puuid/{encryptedPUUID}
    // lol/champion-mastery/v4/champion-masteries/by-puuid/{encryptedPUUID}/by-champion/{championId}
    // lol/champion-mastery/v4/champion-masteries/by-puuid/{encryptedPUUID}/top
    // lol/champion-mastery/v4/scores/by-puuid/{encryptedPUUID}
    V4_MASTERY_BY_PUUID("lol", "champion-mastery", "v4", "champion-masteries/by-puuid/" + Constants.PUUID_ID_PLACEHOLDER, ChampionMasteryList.class),
    V4_MASTERY_BY_CHAMPION("lol", "champion-mastery", "v4", "champion-masteries/by-puuid/" + Constants.PUUID_ID_PLACEHOLDER + "/by-champion/" + Constants.CHAMPION_ID_PLACEHOLDER, ChampionMastery.class),
    V4_MASTERY_TOP("lol", "champion-mastery", "v4", "champion-masteries/by-puuid/" + Constants.PUUID_ID_PLACEHOLDER + "/top", ChampionMasteryList.class),
    V4_MASTERY_SCORE("lol", "champion-mastery", "v4", "scores/by-puuid/" + Constants.PUUID_ID_PLACEHOLDER, Integer.class),
    
    // lol/platform/v3/champion-rotations
    V3_CHAMPION_ROTATIONS("lol", "platform", "v3", "champion-rotations", ChampionRotationInfo.class),
    
    DDRAGON_CHAMPION_MANY("cdn/", "", Constants.VERSION_PLACEHOLDER, "/data/" + Constants.LOCALE_PLACEHOLDER + "/championFull.json", StaticChampionList.class),
    DDRAGON_ITEMS("cdn/", "", Constants.VERSION_PLACEHOLDER, "/data/" + Constants.LOCALE_PLACEHOLDER + "/item.json", ItemList.class),
    DDRAGON_LANGUAGE_STRINGS("cdn/", "", Constants.VERSION_PLACEHOLDER, "/data/" + Constants.LOCALE_PLACEHOLDER + "/language.json", LanguageStrings.class),
    DDRAGON_LANGUAGES("cdn/", "", "", "languages.json", StringList.class),
    DDRAGON_MAPS("cdn/", "", Constants.VERSION_PLACEHOLDER, "/data/" + Constants.LOCALE_PLACEHOLDER + "/map.json", MapData.class),
    DDRAGON_MASTERIES("cdn/", "", Constants.VERSION_PLACEHOLDER, "/data/" + Constants.LOCALE_PLACEHOLDER + "/mastery.json", StaticMasteryList.class),
    DDRAGON_PROFILEICONS("cdn/", "", Constants.VERSION_PLACEHOLDER, "/data/" + Constants.LOCALE_PLACEHOLDER + "/profileicon.json", ProfileIconData.class),
    DDRAGON_RUNES("cdn/", "", Constants.VERSION_PLACEHOLDER, "/data/" + Constants.LOCALE_PLACEHOLDER + "/rune.json", StaticRuneList.class),
    DDRAGON_PERKS("cdn/", "", Constants.VERSION_PLACEHOLDER, "/data/" + Constants.LOCALE_PLACEHOLDER + "/runesReforged.json", StaticPerkList.class),
    DDRAGON_PERKPATHS("cdn/", "", Constants.VERSION_PLACEHOLDER, "/data/" + Constants.LOCALE_PLACEHOLDER + "/runesReforged.json", PerkPathList.class),
    DDRAGON_SUMMONER_SPELLS("cdn/", "", Constants.VERSION_PLACEHOLDER, "/data/" + Constants.LOCALE_PLACEHOLDER + "/summoner.json", StaticSummonerSpellList.class),
    DDRAGON_VERSIONS("api/", "", "", "versions.json", StringList.class),
    DDRAGON_REALMS("realms/", "", Constants.REGION_PLACEHOLDER, ".json", Realm.class),
    DDRAGON_TARBALL("", "", "", "", String.class),
    
    // api/lol/{region}/v2.5/league/by-summoner/{summonerIds}
    // api/lol/{region}/v2.5/league/by-summoner/{summonerIds}/entry
    // api/lol/{region}/v2.5/league/challenger
    // api/lol/{region}/v2.5/league/master
    V4_LEAGUE("lol", "league", "v4", "leagues/" + Constants.LEAGUE_ID_PLACEHOLDER, LeagueList.class),
    V4_LEAGUE_ENTRY("lol", "league", "v4", "entries/by-summoner/" + Constants.SUMMONER_ID_PLACEHOLDER, LeagueEntryList.class),
    //V3_LEAGUE_RANK("lol", "league", "v4", "entries/" + Constants.POSITIONAL_QUEUE_PLACEHOLDER + "/" + Constants.TIER_PLACEHOLDER + "/" + Constants.DIVISION_PLACEHOLDER, LeagueEntryList.class),
    V4_LEAGUE_RANK("lol", "league", "v4", "entries/" + Constants.POSITIONAL_QUEUE_PLACEHOLDER + "/" + Constants.TIER_PLACEHOLDER + "/" + Constants.DIVISION_PLACEHOLDER, LeagueEntryList.class),
    V4_LEAGUE_MASTER("lol", "league", "v4", "masterleagues/by-queue/" + Constants.QUEUE_PLACEHOLDER, LeagueList.class),
    V4_LEAGUE_GRANDMASTER("lol", "league", "v4", "grandmasterleagues/by-queue/" + Constants.QUEUE_PLACEHOLDER, LeagueList.class),
    V4_LEAGUE_CHALLENGER("lol", "league", "v4", "challengerleagues/by-queue/" + Constants.QUEUE_PLACEHOLDER, LeagueList.class),
    
    // lol/platform/v3/third-party-code/by-summoner/{summonerId}
    V4_THIRD_PARTY_CODE("lol", "platform", "v4", "third-party-code/by-summoner/" + Constants.SUMMONER_ID_PLACEHOLDER, String.class),
    
    
    // POST lol/tournament/v3/codes Create a tournament code for the given tournament.
    // PUT  lol/tournament/v3/codes/{tournamentCode} Update the pick type, map, spectator type, or allowed summoners for a code.
    // GET  lol/tournament/v3/codes/{tournamentCode} Returns the tournament code DTO associated with a tournament code string.
    // GET  lol/tournament/v3/lobby-events/by-code/{tournamentCode} Gets a list of lobby events by tournament code.
    // POST lol/tournament/v3/providers Creates a tournament provider and returns its ID.
    // POST lol/tournament/v3/tournaments Creates a tournament and returns its ID.
    V5_TOURNAMENT_CODES("lol", "tournament", "v5", "codes", StringList.class),
    V5_TOURNAMENT_CODES_BY_CODE("lol", "tournament", "v5", "codes/" + Constants.TOURNAMENT_CODE_PLACEHOLDER, TournamentCode.class),
    V5_TOURNAMENT_LOBBY_EVENTS("lol", "tournament", "v5", "lobby-events/by-code/" + Constants.TOURNAMENT_CODE_PLACEHOLDER, LobbyEventWrapper.class),
    V5_TOURNAMENT_PROVIDER("lol", "tournament", "v5", "providers", Integer.class),
    V5_TOURNAMENT_TOURNAMENT("lol", "tournament", "v5", "tournaments", Integer.class),
    
    
    // POST lol/tournament/v3/codes Create a tournament code for the given tournament.
    // GET  lol/tournament/v3/lobby-events/by-code/{tournamentCode} Gets a list of lobby events by tournament code.
    // POST lol/tournament/v3/providers Creates a tournament provider and returns its ID.
    // POST lol/tournament/v3/tournaments Creates a tournament and returns its ID.
    V5_TOURNAMENT_STUB_CODES("lol", "tournament-stub", "v5", "codes", StringList.class),
    V5_TOURNAMENT_STUB_CODES_BY_CODE("lol", "tournament", "v5", "codes/" + Constants.TOURNAMENT_CODE_PLACEHOLDER, TournamentCode.class),
    V5_TOURNAMENT_STUB_LOBBY_EVENTS("lol", "tournament-stub", "v5", "lobby-events/by-code/" + Constants.TOURNAMENT_CODE_PLACEHOLDER, LobbyEventWrapper.class),
    V5_TOURNAMENT_STUB_PROVIDER("lol", "tournament-stub", "v5", "providers", Integer.class),
    V5_TOURNAMENT_STUB_TOURNAMENT("lol", "tournament-stub", "v5", "tournaments", Integer.class),
    
    // LCU
    LCU_LOBBY("lol-lobby/v2/lobby", "", "", "", JsonObject.class),
    LCU_LOBBY_JOIN("lol-lobby/v2/party/{id}/join", "", "", "", Void.class),
    LCU_LOBBY_INVITE("lol-lobby/v2/lobby/invitations", "", "", "", JsonArray.class),
    LCU_LOBBY_POSITION("lol-lobby/v2/lobby/members/localMember/position-preferences", "", "", "", Void.class),
    LCU_LOBBY_MATCHMAKING("lol-lobby/v2/lobby/matchmaking/search", "", "", "", Void.class),
    
    LCU_RESTART("process-control/v1/process/restart", "", "", "", Void.class),
    
    LCU_SUMMONER_BY_NAME("lol-summoner/v2/summoners", "", "", "", JsonArray.class),
    LCU_SUMMONER_BY_ID("lol-summoner/v1/summoners/" + Constants.ID_PLACEHOLDER, "", "", "", JsonObject.class),
    
    LCU_LOGIN("lol-login/v1/session", "", "", "", JsonObject.class),
    
    LCU_CREATE_NOTIFICATION("player-notifications/v1/notifications", "", "", "", JsonObject.class),
    
    LCU_REPLAY_DOWNLOAD("lol-replays/v1/rofls/{gameId}/download", "", "", "", Void.class),
    LCU_REPLAY_DOWNLOAD_PATH("lol-replays/v1/rofls/path", "", "", "", String.class),
    LCU_REPLAY_WATCH("lol-replays/v1/rofls/{gameId}/watch", "", "", "", String.class),
    
    // REPLAY
    REPLAY_GAME("replay/game", "", "", "", ReplayGameClientProcessInfo.class),
    REPLAY_PARTICLES("replay/particles", "", "", "", BooleanMap.class),
    REPLAY_PLAYBACK("replay/playback", "", "", "", ReplayPlaybackInfo.class),
    REPLAY_RECORDING("replay/recording", "", "", "", ReplayRecordingInfo.class),
    REPLAY_RENDER("replay/render", "", "", "", ReplayRenderInfo.class),
    REPLAY_SEQUENCE("replay/sequence", "", "", "", ReplayKeyframeSequence.class),
    
    LIVECLIENT_ACTIVE_PLAYER("liveclientdata/activeplayer", "", "", "", ActiveGameClientPlayer.class),
    LIVECLIENT_ACTIVE_PLAYER_ABILITIES("liveclientdata/activeplayerabilities", "", "", "", ActiveGameClientPlayerAbilities.class),
    LIVECLIENT_ACTIVE_PLAYER_NAME("liveclientdata/activeplayername", "", "", "", String.class),
    LIVECLIENT_ACTIVE_PLAYER_RUNES("liveclientdata/activeplayerrunes", "", "", "", ActiveGameClientPlayerPerks.class),
    LIVECLIENT_PLAYER_LIST("liveclientdata/playerlist", "", "", "", ActiveGamePlayerList.class),
    LIVECLIENT_EVENT_DATA("liveclientdata/eventdata", "", "", "", JsonElement.class),
    LIVECLIENT_GAME_DATA("liveclientdata/allgamedata", "", "", "", JsonElement.class),
    LIVECLIENT_GAME_STATS("liveclientdata/gamestats", "", "", "", ActiveGameState.class),
    LIVECLIENT_PLAYER_RUNES("liveclientdata/playermainrunes", "", "", "", ActiveGamePlayerPerks.class),
    LIVECLIENT_PLAYER_ITEMS("liveclientdata/playeritems", "", "", "", ActiveGameItemList.class),
    LIVECLIENT_PLAYER_SCORES("liveclientdata/playerscores", "", "", "", ActiveGamePlayerScores.class),
    LIVECLIENT_PLAYER_SUMMONERS("liveclientdata/playersummonerspells", "", "", "", ActiveGamePlayerSummonerSpells.class),
    
    
    LOR_STATIC_ACTIVE_DECK("static-decklist", "", "", "", JsonObject.class),
    LOR_CARD_POSITIONS("positional-rectangles", "", "", "", LoRGameInfo.class),
    LOR_GAME_RESULT("game-result", "", "", "", LoRGameResult.class),
    LOR_EXPEDITIONS_STATE("expeditions-state", "", "", "", LoRExpeditionInfo.class),
    
    
    V1_TFT_SUMMONER_BY_ACCOUNT("tft", "summoner", "v1", "summoners/by-account/" + Constants.ACCOUNT_ID_PLACEHOLDER, Summoner.class),
    V1_TFT_SUMMONER_BY_PUUID("tft", "summoner", "v1", "summoners/by-puuid/" + Constants.PUUID_ID_PLACEHOLDER, Summoner.class),
    V1_TFT_SUMMONER_BY_NAME("tft", "summoner", "v1", "summoners/by-name/" + Constants.SUMMONER_NAME_PLACEHOLDER, Summoner.class),
    V1_TFT_SUMMONER_BY_ID("tft", "summoner", "v1", "summoners/" + Constants.SUMMONER_ID_PLACEHOLDER, Summoner.class),
    
    V1_TFT_MATCHLIST("tft", "match", "v1", "matches/by-puuid/" + Constants.PUUID_ID_PLACEHOLDER + "/ids", StringList.class),
    V1_TFT_MATCH("tft", "match", "v1", "matches/" + Constants.MATCH_ID_PLACEHOLDER, GAMHSMatch.class),
    
    
    V1_TFT_LEAGUE("tft", "league", "v1", "leagues/" + Constants.LEAGUE_ID_PLACEHOLDER, TFTLeagueList.class),
    V1_TFT_LEAGUE_ENTRY("tft", "league", "v1", "entries/by-summoner/" + Constants.SUMMONER_ID_PLACEHOLDER, TFTLeagueEntryList.class),
    V1_TFT_LEAGUE_RANK("tft", "league", "v1", "entries/" + Constants.TIER_PLACEHOLDER + "/" + Constants.DIVISION_PLACEHOLDER, TFTLeagueEntryList.class),
    V1_TFT_LEAGUE_MASTER("tft", "league", "v1", "master", TFTLeagueList.class),
    V1_TFT_LEAGUE_GRANDMASTER("tft", "league", "v1", "grandmaster", TFTLeagueList.class),
    V1_TFT_LEAGUE_CHALLENGER("tft", "league", "v1", "challenger", TFTLeagueList.class),
    
    
    V1_LOR_RANKED_LEADERBOARD("lor", "ranked", "v1", "leaderboards", LoRRankedPlayerList.class),
    V1_LOR_INVENTORY_CARDS_ME("lor", "inventory", "v1", "cards/me", LoRCardList.class),
    V1_LOR_DECKS_ME_GET("lor", "deck", "v1", "decks/me", LoRDeckList.class),
    V1_LOR_DECKS_ME_POST("lor", "deck", "v1", "decks/me", String.class),
    
    V1_CLASH_PLAYER_BY_SUMMONER("lol", "clash", "v1", "players/by-summoner/" + Constants.SUMMONER_ID_PLACEHOLDER, ClashPlayerList.class),
    V1_CLASH_TEAM_BY_ID("lol", "clash", "v1", "teams/" + Constants.TEAM_ID_PLACEHOLDER, ClashTeam.class),
    V1_CLASH_TOURNAMENTS("lol", "clash", "v1", "tournaments", ClashTournamentList.class),
    V1_CLASH_TOURNAMENTS_BY_TEAM("lol", "clash", "v1", "tournaments/by-team/" + Constants.TEAM_ID_PLACEHOLDER, ClashTournament.class),
    V1_CLASH_TOURNAMENTS_BY_ID("lol", "clash", "v1", "tournaments/" + Constants.TOURNAMENT_ID_PLACEHOLDER, ClashTournament.class),
    
    V1_VAL_CONTENT("val", "content", "v1", "contents", Content.class),
    V1_VAL_MATCH_BY_ID("val", "match", "v1", "matches/" + Constants.MATCH_ID_PLACEHOLDER, VALMatch.class),
    V1_VAL_MATCHLIST_BY_PUUID("val", "match", "v1", "matchlists/by-puuid/" + Constants.PUUID_ID_PLACEHOLDER, no.stelar7.api.r4j.pojo.val.matchlist.MatchList.class),
    V1_VAL_RECENT_MATCH_BY_QUEUE("val", "match", "v1", "recent-matches/by-queue/" + Constants.QUEUE_PLACEHOLDER, RecentMatchList.class),
    V1_VAL_LEADERBOARD_BY_ACT("val", "ranked", "v1", "leaderboards/by-act/" + Constants.ACT_ID_PLACEHOLDER, Leaderboard.class),
    
    V1_SHARED_ACCOUNT_BY_PUUID("riot", "account", "v1", "accounts/by-puuid/" + Constants.PUUID_ID_PLACEHOLDER, RiotAccount.class),
    V1_SHARED_ACCOUNT_BY_TAG("riot", "account", "v1", "accounts/by-riot-id/" + Constants.GAME_NAME_PLACEHOLDER + "/" + Constants.TAG_LINE_PLACEHOLDER, RiotAccount.class),
    V1_SHARED_SHARD_BY_PUUID("riot", "account", "v1", "active-shards/by-game/" + Constants.GAME_PLACEHOLDER + "/by-puuid/" + Constants.PUUID_ID_PLACEHOLDER, RiotAccountShard.class),
    
    V1_LOR_MATCHES_BY_PUUID("lor", "match", "v1", "matches/by-puuid/" + Constants.PUUID_ID_PLACEHOLDER + "/ids", StringList.class),
    V1_LOR_MATCH("lor", "match", "v1", "matches/" + Constants.MATCH_ID_PLACEHOLDER, GAMHSMatch.class),
    
    V4_STATUS_LOL("lol", "status", "v4", "platform-data", PlatformData.class),
    V1_STATUS_LOR("lor", "status", "v1", "platform-data", PlatformData.class),
    V1_STATUS_VAL("val", "status", "v1", "platform-data", PlatformData.class),
    
    V5_MATCHLIST("lol", "match", "v5", "matches/by-puuid/" + Constants.PUUID_ID_PLACEHOLDER + "/ids", StringList.class),
    V5_MATCH("lol", "match", "v5", "matches/" + Constants.MATCH_ID_PLACEHOLDER, GAMHSMatch.class),
    V5_TIMELINE("lol", "match", "v5", "matches/" + Constants.MATCH_ID_PLACEHOLDER + "/timeline", GAMHSMatch.class),
    
    V1_CHALLENGES_PERCENTILES("lol", "challenges", "v1", "challenges/percentiles", MultiChallengePercentileMap.class),
    V1_CHALLENGES_PERCENTILES_BY_ID("lol", "challenges", "v1", "challenges/" + Constants.CHALLENGE_ID_PLACEHOLDER + "/percentiles", ChallengePercentileMap.class),
    V1_CHALLENGES_CONFIG("lol", "challenges", "v1", "challenges/config", ChallengeConfigInfoList.class),
    V1_CHALLENGES_CONFIG_BY_ID("lol", "challenges", "v1", "challenges/" + Constants.CHALLENGE_ID_PLACEHOLDER + "/config", ChallengeConfigInfo.class),
    V1_CHALLENGES_LEADERBOARD_BY_LEVEL("lol", "challenges", "v1", "challenges/" + Constants.CHALLENGE_ID_PLACEHOLDER + "/leaderboards/by-level/" + Constants.LEVEL_PLACEHOLDER, ChallengeLeaderboardInfoList.class),
    V1_CHALLENGES_BY_PLAYER("lol", "challenges", "v1", "player-data/" + Constants.PUUID_ID_PLACEHOLDER, ChallengePlayerInfo.class),
    
    ;
    
    
    private final String   game;
    private final String   service;
    private final String   version;
    private final String   resource;
    private final Class<?> type;
    
    URLEndpoint(String game, String service, String version, String resource, Class<?> type)
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
    
    public Class<?> getType()
    {
        return type;
    }
}
