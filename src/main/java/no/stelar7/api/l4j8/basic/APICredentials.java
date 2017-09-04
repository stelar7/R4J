package no.stelar7.api.l4j8.basic;

import no.stelar7.api.l4j8.basic.exceptions.APIUnsupportedActionException;

public class APICredentials
{
    private String baseApiKey;
    private String tournamentApiKey;
    
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
            throw new APIUnsupportedActionException("API key not set!");
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
            throw new APIUnsupportedActionException("TOURNAMENT key not set!");
        }
        return this.tournamentApiKey;
    }
    
}
