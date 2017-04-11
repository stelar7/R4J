package no.stelar7.api.l4j8.basic.constants.api;


/**
 * This class lists all the String constants in use in the project
 */
public class Constants
{
    public static final String API_KEY_PLACEHOLDER_DATA      = "api_key";
    public static final String FREE_TO_PLAY_PLACEHOLDER_DATA = "freeToPlay";
    public static final String CHAMPDATA_PLACEHOLDER_DATA    = "champData";
    public static final String VERSION_PLACEHOLDER_DATA      = "version";
    public static final String LOCALE_PLACEHOLDER_DATA       = "locale";
    
    public static final String TOURNAMENT_CODE_PLACEHOLDER = "{tournamentCode}";
    public static final String PLATFORM_ID_PLACEHOLDER     = "{platformId}";
    public static final String SUMMONER_ID_PLACEHOLDER     = "{summonerId}";
    public static final String ACCOUNT_ID_PLACEHOLDER      = "{accountId}";
    public static final String SUMMONER_NAME_PLACEHOLDER   = "{summonerName}";
    public static final String CHAMPION_ID_PLACEHOLDER     = "{championId}";
    public static final String MATCH_ID_PLACEHOLDER        = "{matchId}";
    public static final String ID_PLACEHOLDER              = "{id}";
    public static final String PLATFORM_PLACEHOLDER        = "{platform}";
    public static final String GAME_PLACEHOLDER            = "{game}";
    public static final String SERVICE_PLACEHOLDER         = "{service}";
    public static final String VERSION_PLACEHOLDER         = "{version}";
    public static final String RESOURCE_PLACEHOLDER        = "{resource}";
    
    public static final String URL_PARAM_TOURNAMENT_ID       = "tournamentId";
    public static final String URL_PARAM_TOURNAMENT_CODE     = "tournamentCode";
    public static final String URL_PARAM_TOURNAMENT_TIMELINE = "includeTimeline";
    public static final String URL_PARAM_TOURNAMENT_COUNT    = "count";
    public static final String URL_PARAM_CHAMPDATA           = "champData";
    public static final String URL_PARAM_DATA_BY_ID          = "dataById";
    public static final String URL_PARAM_VERSION             = "version";
    public static final String URL_PARAM_LOCALE              = "locale";
    
    public static final String X_RIOT_TOKEN_HEADER_KEY = "X-Riot-Token";
    
    public static final String VERBOSE_STRING_FORMAT        = "%1$-20s: %2$-40s%n";
    public static final String TABBED_VERBOSE_STRING_FORMAT = "\t%1$-20s: %2$-40s%n";
    
    
    public static final String SEPARATOR   = "/";
    public static final String METHOD_PUT  = "PUT";
    public static final String METHOD_POST = "POST";
    public static final String PROTOCOL    = "HTTPS://";
    public static final String HOST        = ".api.riotgames.com";
    
    public static final String REQUEST_URL = PROTOCOL + PLATFORM_PLACEHOLDER + HOST + SEPARATOR + GAME_PLACEHOLDER + SEPARATOR + SERVICE_PLACEHOLDER + SEPARATOR + VERSION_PLACEHOLDER + SEPARATOR + RESOURCE_PLACEHOLDER;
    
    public static final String[] TEST_TOURNAMENT_CODES = {"EUW0418b-b9423c92-5733-4d1b-aff2-215229f96e8d"};
    public static final String[] TEST_SUMMONER_NAMES   = {"stelar7"};
    public static final Long[]   TEST_SUMMONER_IDS     = {19613950L};
    public static final Long[]   TEST_ACCOUNT_IDS      = {22401330L};
    public static final int[]    TEST_CHAMPION_IDS     = {89};
    
    private Constants()
    {
    }
}
