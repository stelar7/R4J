package no.stelar7.api.l4j8.basic;

import no.stelar7.api.l4j8.basic.constants.api.APIType;
import no.stelar7.api.l4j8.basic.exceptions.APIUnsupportedAction;

public class APICredentials
{
    String baseApiKey;
    String tournamentApiKey;

    /**
     * Instantiates the APICredentials
     *
     * @param key  the key to use
     * @param type the type of key
     */

    public APICredentials(final String key, final APIType type)
    {
        switch (type)
        {
        case NORMAL:
            this.baseApiKey = key;
            break;
        case TOURNAMENT:
            this.tournamentApiKey = key;
            break;
        default:
            throw new APIUnsupportedAction(String.format("%s is not a valid choice for APICredentials", type));
        }
    }

    /**
     * Instantiates new API credentials.
     *
     * @param api        the api key
     * @param tournament the tournament key
     */
    public APICredentials(final String api, final String tournament)
    {
        this.baseApiKey = api;
        this.tournamentApiKey = tournament;
    }

    /**
     * Gets the api key.
     *
     * @return the key
     */
    public String getBaseAPIKey()
    {
        if (this.baseApiKey == null)
        {
            throw new APIUnsupportedAction("API key not set!");
        }
        return this.baseApiKey;
    }

    /**
     * Gets the tournament key.
     *
     * @return the key
     */
    public String getTournamentAPIKey()
    {
        if (this.tournamentApiKey == null)
        {
            throw new APIUnsupportedAction("TOURNAMENT key not set!");
        }
        return this.tournamentApiKey;
    }

}
