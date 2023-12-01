package no.stelar7.api.r4j.basic;

import no.stelar7.api.r4j.basic.constants.types.ApiKeyType;
import no.stelar7.api.r4j.basic.exceptions.APIUnsupportedActionException;
import no.stelar7.api.r4j.basic.utils.Base32;

import java.security.*;
import java.util.logging.Logger;

public class APICredentials
{
    private final String lolAPIKey;
    private final String TFTAPIKey;
    private final String LORAPIKey;
    private final String VALAPIKey;
    private final String tournamentApiKey;
    
    /**
     * Instantiates new API credentials.
     *
     * @param lol        the lol key
     * @param tournament the tournament key
     * @param tft        the tft lol key
     * @param lor        the lor lol key
     * @param val        the val lol key
     */
    public APICredentials(final String lol, final String tournament, final String tft, final String lor, final String val)
    {
        this.lolAPIKey = lol;
        this.tournamentApiKey = tournament;
        this.TFTAPIKey = tft;
        this.LORAPIKey = lor;
        this.VALAPIKey = val;
        
        if (lol != null && tournament != null && !lol.equals(tournament)) {
            Logger.getGlobal().warning("Using different keys for league and tournament might lead to decryption exceptions");
        }
    }
    
    
    /**
     * Instantiates new API credentials.
     *
     * @param key        the lol key
     * @param tournament the tournament key
     */
    public APICredentials(final String key, final String tournament)
    {
        this(key, tournament, key, key, key);
    }
    
    /**
     * Instantiates new API credentials.
     *
     * @param api the api key
     */
    public APICredentials(final String api)
    {
        this(api, api, api, api, api);
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
     * Gets the lor key.
     *
     * @return the key
     */
    public String getLORAPIKey()
    {
        if (this.LORAPIKey == null)
        {
            throw new APIUnsupportedActionException("API key not set!");
        }
        return this.LORAPIKey;
    }
    
    /**
     * Gets the val key.
     *
     * @return the key
     */
    public String getVALAPIKey()
    {
        if (this.VALAPIKey == null)
        {
            throw new APIUnsupportedActionException("API key not set!");
        }
        return this.VALAPIKey;
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
    
    public String getKey(ApiKeyType type) {
        switch (type) {
            case LOL:
                return getLoLAPIKey();
            case TOURNAMENT:
                return getTournamentAPIKey();
            case TFT:
                return getTFTAPIKey();
            case VAL:
                return getVALAPIKey();
            case LOR:
                return getLORAPIKey();
            default:
                throw new APIUnsupportedActionException("API key not set!");
        }
    }
    
    public String getUniqueKeyCombination()
    {
        String unique = lolAPIKey + "separator" + tournamentApiKey + "separator" + TFTAPIKey + "separator" + LORAPIKey + "separator" + VALAPIKey;
        try
        {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            messageDigest.update(unique.getBytes());
            return Base32.encode(messageDigest.digest());
        } catch (NoSuchAlgorithmException e)
        {
            e.printStackTrace();
            return unique;
        }
    }
}
