package no.stelar7.api.l4j8.basic;

import no.stelar7.api.l4j8.basic.constants.api.APIType;

public class APICredentials
{
    String API_KEY;
    String TOURNAMENT_KEY;

    /**
     * Instantiates new API credentials.
     *
     * @param api
     *            the api key
     * @param tournament
     *            the tournament key
     */
    public APICredentials(String api, String tournament)
    {
        API_KEY = api;
        TOURNAMENT_KEY = tournament;
    }

    /**
     * Instantiates new API credentials.
     *
     * @param api
     *            the api key
     */
    public APICredentials(APIType type, String key)
    {
        switch (type)
        {
            case NORMAL:
                API_KEY = key;
                break;
            case TOURNAMENT:
                TOURNAMENT_KEY = key;
                break;
        }
    }

    /**
     * Gets the api key.
     *
     * @return the key
     */
    public String getAPI()
    {
        if (API_KEY == null)
        {
            throw new RuntimeException("API key not set!");
        }
        return API_KEY;
    }

    /**
     * Gets the tournament key.
     *
     * @return the key
     */
    public String getTournament()
    {
        if (TOURNAMENT_KEY == null)
        {
            throw new RuntimeException("TOURNAMENT key not set!");
        }
        return TOURNAMENT_KEY;
    }

}
