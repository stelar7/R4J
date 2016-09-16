package no.stelar7.api.l4j8.basic.constants.api;

/**
 * This class lists all the String constants in use in the project
 */
public class Constants
{
    public static final String TOURNAMENT_CODE_PLACEHOLDER   = "{tournamentCode}";
    public static final String PLATFORM_ID_PLACEHOLDER       = "{platformId}";
    public static final String SUMMONER_ID_PLACEHOLDER       = "{summonerId}";
    public static final String SUMMONER_NAME_PLACEHOLDER     = "{summonerName}";
    public static final String CHAMPION_ID_PLACEHOLDER       = "{championId}";
    public static final String MATCH_ID_PLACEHOLDER          = "{matchId}";
    public static final String TOURNAMENT_ID_URL_PARAM       = "tournamentId";
    public static final String TOURNAMENT_CODE_URL_PARAM     = "tournamentCode";
    public static final String TOURNAMENT_TIMELINE_URL_PARAM = "includeTimeline";
    public static final String TOURNAMENT_COUNT_URL_PARAM    = "count";
    public static final String X_RIOT_TOKEN_HEADER_KEY       = "X-Riot-Token";
    public static final String VERBOSE_STRING_FORMAT         = "%1$-20s: %2$-40s%n";
    public static final String TABBED_VERBOSE_STRING_FORMAT  = "\t%1$-20s: %2$-40s%n";
    public static final String TEST_TOURNAMENT_CODE          = "EUW0418b-b9423c92-5733-4d1b-aff2-215229f96e8d";
    public static final String REQUEST_METHOD_POST           = "POST";
    public static final String REQUEST_METHOD_PUT            = "PUT";

    public static final Long[]   TEST_SUMMONER_IDS   = {19613950L, 22291359L, 33540589L};
    public static final String[] TEST_SUMMONER_NAMES = {"stelar7", "henriko950", "vibbsen", "Tàylor Swíft"};

    public static final Integer SUMMONER_ENDPOINT_MAX_IDS = 40;
    public static final Integer TEAM_ENDPOINT_MAX_IDS     = 10;
    public static final Integer LEAGUE_ENDPOINT_MAX_IDS   = 10;

    private Constants()
    {
    }
}
