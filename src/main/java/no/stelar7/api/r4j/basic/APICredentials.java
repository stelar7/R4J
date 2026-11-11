package no.stelar7.api.r4j.basic;

import no.stelar7.api.r4j.basic.constants.types.ApiKeyType;
import no.stelar7.api.r4j.basic.exceptions.APIUnsupportedActionException;
import no.stelar7.api.r4j.basic.utils.Base32;

import java.security.*;
import org.slf4j.*;

public class APICredentials
{
    private static final Logger logger = LoggerFactory.getLogger(APICredentials.class);
    
    private final String lolAPIKey;
    private final String TFTAPIKey;
    private final String LORAPIKey;
    private final String VALAPIKey;
    private final String tournamentApiKey;
    private final String riftboundAPIKey;
    
    /**
     * Starts building credentials, naming each key instead of relying on its position:
     * <pre>{@code
     * APICredentials credentials = APICredentials.builder()
     *                                            .lol("RGAPI-...")
     *                                            .tft("RGAPI-...")
     *                                            .build();
     * }</pre>
     * A key that is not set is null; using an api that needs it throws an APIUnsupportedActionException.
     *
     * @return a builder
     */
    public static Builder builder()
    {
        return new Builder();
    }

    public static final class Builder
    {
        private String lol;
        private String tournament;
        private String tft;
        private String lor;
        private String val;
        private String riftbound;

        private Builder()
        {
        }

        /**
         * Uses one key for every product, like {@code new APICredentials(key)}. Keys set after this replace it for their product.
         *
         * @param key the key (ie. a development key)
         * @return this
         */
        public Builder all(String key)
        {
            this.lol = key;
            this.tournament = key;
            this.tft = key;
            this.lor = key;
            this.val = key;
            this.riftbound = key;
            return this;
        }

        /**
         * @param key the League of Legends key
         * @return this
         */
        public Builder lol(String key)
        {
            this.lol = key;
            return this;
        }

        /**
         * @param key the tournament key
         * @return this
         */
        public Builder tournament(String key)
        {
            this.tournament = key;
            return this;
        }

        /**
         * @param key the Teamfight Tactics key
         * @return this
         */
        public Builder tft(String key)
        {
            this.tft = key;
            return this;
        }

        /**
         * @param key the Legends of Runeterra key
         * @return this
         */
        public Builder lor(String key)
        {
            this.lor = key;
            return this;
        }

        /**
         * @param key the VALORANT key
         * @return this
         */
        public Builder val(String key)
        {
            this.val = key;
            return this;
        }

        /**
         * @param key the Riftbound key
         * @return this
         */
        public Builder riftbound(String key)
        {
            this.riftbound = key;
            return this;
        }

        public APICredentials build()
        {
            return new APICredentials(lol, tournament, tft, lor, val, riftbound, true);
        }
    }

    /**
     * Instantiates new API credentials.
     *
     * @param lol        the lol key
     * @param tournament the tournament key
     * @param tft        the tft lol key
     * @param lor        the lor lol key
     * @param val        the val lol key
     * @deprecated five keys of the same type in a fixed order are easy to mix up; use {@link #builder()}
     */
    @Deprecated
    public APICredentials(final String lol, final String tournament, final String tft, final String lor, final String val)
    {
        this(lol, tournament, tft, lor, val, null);
    }
    
    /**
     * Instantiates new API credentials.
     *
     * @param lol        the lol key
     * @param tournament the tournament key
     * @param tft        the tft key
     * @param lor        the lor key
     * @param val        the val key
     * @param riftbound  the riftbound key
     * @deprecated six keys of the same type in a fixed order are easy to mix up; use {@link #builder()}
     */
    @Deprecated
    public APICredentials(final String lol, final String tournament, final String tft, final String lor, final String val, final String riftbound)
    {
        this(lol, tournament, tft, lor, val, riftbound, true);
    }

    // the extra parameter only tells this constructor apart from the deprecated public one
    private APICredentials(final String lol, final String tournament, final String tft, final String lor, final String val, final String riftbound, boolean internal)
    {
        this.riftboundAPIKey = riftbound;
        this.lolAPIKey = lol;
        this.tournamentApiKey = tournament;
        this.TFTAPIKey = tft;
        this.LORAPIKey = lor;
        this.VALAPIKey = val;
        
        if (lol != null && tournament != null && !lol.equals(tournament)) {
            logger.warn("Using different keys for league and tournament might lead to decryption exceptions");
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
        this(key, tournament, key, key, key, null, true);
    }
    
    /**
     * Instantiates new API credentials.
     *
     * @param api the api key
     */
    public APICredentials(final String api)
    {
        this(api, api, api, api, api, api, true);
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
    
    /**
     * Gets the riftbound key.
     *
     * @return the key
     */
    public String getRiftboundAPIKey()
    {
        if (this.riftboundAPIKey == null)
        {
            throw new APIUnsupportedActionException("RIFTBOUND key not set!");
        }
        return this.riftboundAPIKey;
    }
    
    /**
     * @param type the product
     * @return true if a key is set for the product
     */
    public boolean hasKey(ApiKeyType type)
    {
        return getRawKey(type) != null;
    }
    
    private String getRawKey(ApiKeyType type)
    {
        switch (type)
        {
            case LOL:
                return lolAPIKey;
            case TOURNAMENT:
                return tournamentApiKey;
            case TFT:
                return TFTAPIKey;
            case VAL:
                return VALAPIKey;
            case LOR:
                return LORAPIKey;
            case RIFTBOUND:
                return riftboundAPIKey;
            default:
                return null;
        }
    }
    
    /**
     * Finds which product a key belongs to. If the same key is used for several products, the first one in the order
     * LOL, TFT, VAL, LOR, TOURNAMENT, RIFTBOUND is returned.
     *
     * @param key the key
     * @return the product, or UNKNOWN if the key is not one of the keys in these credentials
     */
    public ApiKeyType getKeyType(String key)
    {
        for (ApiKeyType type : new ApiKeyType[]{ApiKeyType.LOL, ApiKeyType.TFT, ApiKeyType.VAL, ApiKeyType.LOR, ApiKeyType.TOURNAMENT, ApiKeyType.RIFTBOUND})
        {
            if (key.equals(getRawKey(type)))
            {
                return type;
            }
        }
        
        return ApiKeyType.UNKNOWN;
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
            case RIFTBOUND:
                return getRiftboundAPIKey();
            default:
                throw new APIUnsupportedActionException("API key not set!");
        }
    }
    
    public String getUniqueKeyCombination()
    {
        String unique = lolAPIKey + "separator" + tournamentApiKey + "separator" + TFTAPIKey + "separator" + LORAPIKey + "separator" + VALAPIKey;
        if (riftboundAPIKey != null && !riftboundAPIKey.equals(lolAPIKey))
        {
            // only added when it is a key of its own, so the cache location of existing credentials does not change
            unique += "separator" + riftboundAPIKey;
        }
        try
        {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            messageDigest.update(unique.getBytes());
            return Base32.encode(messageDigest.digest());
        } catch (NoSuchAlgorithmException e)
        {
            logger.warn("SHA-256 is not available", e);
            return unique;
        }
    }
}
