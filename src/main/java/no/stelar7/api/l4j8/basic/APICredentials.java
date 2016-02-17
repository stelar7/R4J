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
     */
    public APICredentials(final APIType type, final String key)
    {
        switch (type)
        {
            case NORMAL:
                this.API_KEY = key;
                break;
            case TOURNAMENT:
                this.TOURNAMENT_KEY = key;
                break;
        }
    }

    /**
     * Instantiates new API credentials.
     *
     * @param api
     *            the api key
     * @param tournament
     *            the tournament key
     */
    public APICredentials(final String api, final String tournament)
    {
        this.API_KEY = api;
        this.TOURNAMENT_KEY = tournament;
    }

    /**
     * Gets the api key.
     *
     * @return the key
     */
    public String getAPI()
    {
        if (this.API_KEY == null)
        {
            throw new RuntimeException("API key not set!");
        }
        return this.API_KEY;
    }

    /**
     * Gets the tournament key.
     *
     * @return the key
     */
    public String getTournament()
    {
        if (this.TOURNAMENT_KEY == null)
        {
            throw new RuntimeException("TOURNAMENT key not set!");
        }
        return this.TOURNAMENT_KEY;
    }

}
