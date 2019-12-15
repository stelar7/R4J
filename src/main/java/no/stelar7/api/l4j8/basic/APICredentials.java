package no.stelar7.api.l4j8.basic;

import no.stelar7.api.l4j8.basic.exceptions.APIUnsupportedActionException;

public class APICredentials
{
    private String lolAPIKey;
    private String TFTAPIKey;
    private String tournamentApiKey;
    
    /**
     * Instantiates new API credentials.
     *
     * @param lol        the lol key
     * @param tft        the tft lol key
     * @param tournament the tournament key
     */
    public APICredentials(final String lol, final String tft, final String tournament)
    {
        this.lolAPIKey = lol;
        this.TFTAPIKey = tft;
        this.tournamentApiKey = tournament;
    }
    
    /**
     * Instantiates new API credentials.
     *
     * @param lol        the lol key
     * @param tournament the tournament key
     */
    public APICredentials(final String lol, final String tournament)
    {
        this.lolAPIKey = lol;
        this.TFTAPIKey = lol;
        this.tournamentApiKey = tournament;
    }
    
    /**
     * Instantiates new API credentials.
     *
     * @param api the api key
     */
    public APICredentials(final String api)
    {
        this.lolAPIKey = api;
        this.TFTAPIKey = api;
        this.tournamentApiKey = api;
    }
    
    /**
     * Gets the api key.
     *
     * @return the key
     */
    public String getLoLAPIKey()
    {
        if (this.lolAPIKey == null)
        {
            throw new APIUnsupportedActionException("API key not set!");
        }
        return this.lolAPIKey;
    }
    
    /**
     * Gets the tft key.
     *
     * @return the key
     */
    public String getTFTAPIKey()
    {
        if (this.TFTAPIKey == null)
        {
            throw new APIUnsupportedActionException("API key not set!");
        }
        return this.TFTAPIKey;
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
    
    public String getUniqueKeyCombination()
    {
        return lolAPIKey + TFTAPIKey + tournamentApiKey;
    }
    
}
