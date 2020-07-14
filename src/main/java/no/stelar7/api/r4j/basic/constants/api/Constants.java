package no.stelar7.api.r4j.basic.constants.api;


/**
 * This class lists all the String constants in use in the project
 */
public final class Constants
{
    public static final String API_KEY_PLACEHOLDER_DATA      = "api_key";
    public static final String FREE_TO_PLAY_PLACEHOLDER_DATA = "freeToPlay";
    
    public static final String CHAMPDATA_PLACEHOLDER_DATA         = "tags";
    public static final String CHAMPLISTDATA_PLACEHOLDER_DATA     = "tags";
    public static final String ITEMDATA_PLACEHOLDER_DATA          = "tags";
    public static final String ITEMLISTDATA_PLACEHOLDER_DATA      = "tags";
    public static final String MASTERYLISTDATA_PLACEHOLDER_DATA   = "tags";
    public static final String MASTERYDATA_PLACEHOLDER_DATA       = "tags";
    public static final String RUNEDATA_PLACEHOLDER_DATA          = "tags";
    public static final String RUNELISTDATA_PLACEHOLDER_DATA      = "tags";
    public static final String SUMMONERSPELL_PLACEHOLDER_DATA     = "tags";
    public static final String SUMMONERSPELLLIST_PLACEHOLDER_DATA = "tags";
    
    public static final String VERSION_PLACEHOLDER_DATA    = "version";
    public static final String LOCALE_PLACEHOLDER_DATA     = "locale";
    public static final String QUEUE_PLACEHOLDER_DATA      = "queue";
    public static final String SEASON_PLACEHOLDER_DATA     = "season";
    public static final String ENDTIME_PLACEHOLDER_DATA    = "endTime";
    public static final String ENDINDEX_PLACEHOLDER_DATA   = "endIndex";
    public static final String BEGINTIME_PLACEHOLDER_DATA  = "beginTime";
    public static final String BEGININDEX_PLACEHOLDER_DATA = "beginIndex";
    public static final String CHAMPION_PLACEHOLDER_DATA   = "champion";
    public static final String TIMELINE_PLACEHOLDER_DATA   = "includeTimeline";
    public static final String COUNT_PLACEHOLDER_DATA      = "count";
    public static final String TYPE_PLACEHOLDER_DATA       = "type";
    public static final String PAGE_PLACEHOLDER_DATA       = "page";
    
    public static final String TOURNAMENT_ID_PLACEHOLDER    = "{tournamentId}";
    public static final String TEAM_ID_PLACEHOLDER          = "{teamId}";
    public static final String TOURNAMENT_CODE_PLACEHOLDER  = "{tournamentCode}";
    public static final String PLATFORM_ID_PLACEHOLDER      = "{platformId}";
    public static final String SUMMONER_ID_PLACEHOLDER      = "{summonerId}";
    public static final String SUMMONER_IDS_PLACEHOLDER     = "{summonerIds}";
    public static final String LEAGUE_ID_PLACEHOLDER        = "{leagueId}";
    public static final String ACCOUNT_ID_PLACEHOLDER       = "{accountId}";
    public static final String PUUID_ID_PLACEHOLDER         = "{PUUID}";
    public static final String SUMMONER_NAME_PLACEHOLDER    = "{summonerName}";
    public static final String CHAMPION_ID_PLACEHOLDER      = "{championId}";
    public static final String MATCH_ID_PLACEHOLDER         = "{matchId}";
    public static final String ID_PLACEHOLDER               = "{id}";
    public static final String PLATFORM_PLACEHOLDER         = "{platform}";
    public static final String GAME_PLACEHOLDER             = "{game}";
    public static final String GAMEID_PLACEHOLDER           = "{gameId}";
    public static final String SERVICE_PLACEHOLDER          = "{service}";
    public static final String VERSION_PLACEHOLDER          = "{version}";
    public static final String LOCALE_PLACEHOLDER           = "{locale}";
    public static final String RESOURCE_PLACEHOLDER         = "{resource}";
    public static final String REGION_PLACEHOLDER           = "{region}";
    public static final String SERVER_PLACEHOLDER           = "{server}";
    public static final String QUEUE_PLACEHOLDER            = "{queue}";
    public static final String POSITIONAL_QUEUE_PLACEHOLDER = "{positionalQueue}";
    public static final String TIER_PLACEHOLDER             = "{tier}";
    public static final String DIVISION_PLACEHOLDER         = "{division}";
    public static final String LANE_PLACEHOLDER             = "{lane}";
    public static final String PAGE_PLACEHOLDER             = "{page}";
    public static final String GAME_NAME_PLACEHOLDER        = "{gameName}";
    public static final String TAG_LINE_PLACEHOLDER         = "{tagLine}";
    
    
    public static final String URL_PARAM_TOURNAMENT_ID       = "tournamentId";
    public static final String URL_PARAM_TOURNAMENT_CODE     = "tournamentCode";
    public static final String URL_PARAM_TOURNAMENT_TIMELINE = "includeTimeline";
    public static final String URL_PARAM_TOURNAMENT_COUNT    = "count";
    public static final String URL_PARAM_CHAMPDATA           = "champData";
    public static final String URL_PARAM_DATA_BY_ID          = "dataById";
    public static final String URL_PARAM_VERSION             = "version";
    public static final String URL_PARAM_LOCALE              = "locale";
    public static final String URL_PARAM_FOR_ACCOUNT_ID      = "forAccountId";
    
    public static final String X_RIOT_TOKEN_HEADER_KEY = "X-Riot-Token";
    public static final String AUTHORIZATION           = "Authorization";
    
    public static final String VERBOSE_STRING_FORMAT          = "%1$-35s: %2$-40s";
    public static final String TABBED_VERBOSE_STRING_FORMAT   = "\t%1$-35s: %2$-40s";
    public static final String TABBED2X_VERBOSE_STRING_FORMAT = "\t\t%1$-35s: %2$-40s";
    
    
    public static final String SEPARATOR        = "/";
    public static final String METHOD_PUT       = "PUT";
    public static final String METHOD_POST      = "POST";
    public static final String PROTOCOL         = "https://";
    public static final String HOST_PLACEHOLDER = ".api.riotgames.com";
    public static final String GSVR             = "{game}{service}{version}{resource}";
    public static final String DDRAGON_PROXY    = "http://ddragon.leagueoflegends.com/" + GSVR;
    public static final String CLIENT_PROXY     = "https://127.0.0.1:2999/" + GSVR;
    
    public static final String BASE_URL = PROTOCOL + PLATFORM_PLACEHOLDER + HOST_PLACEHOLDER + SEPARATOR;
    public static final String URL_PATH = GAME_PLACEHOLDER + SEPARATOR + SERVICE_PLACEHOLDER + SEPARATOR + VERSION_PLACEHOLDER + SEPARATOR + RESOURCE_PLACEHOLDER;
    
    public static final String REQUEST_URL = BASE_URL + URL_PATH;
    
    private Constants()
    {
    }
}
