package no.stelar7.api.r4j.impl;

import no.stelar7.api.r4j.basic.APICredentials;
import no.stelar7.api.r4j.basic.cache.impl.FileSystemCacheProvider;
import no.stelar7.api.r4j.basic.calling.DataCall;
import no.stelar7.api.r4j.impl.lol.raw.*;
import no.stelar7.api.r4j.impl.lor.*;
import no.stelar7.api.r4j.impl.shared.*;
import no.stelar7.api.r4j.impl.tft.*;
import no.stelar7.api.r4j.impl.val.*;

/**
 * The base class for calling anything from this api wrapper
 */
public class R4J
{
    
    /**
     * Constructor for the R4J class.
     *
     * @param creds the API credentials used for the API (your token)
     */
    public R4J(final APICredentials creds)
    {
        DataCall.setCredentials(creds);
        DataCall.setCacheProvider(new FileSystemCacheProvider());
    }
    
    public LOLAPI getLoLAPI()
    {
        return LOLAPI.INSTANCE;
    }
    
    public static final class LOLAPI
    {
        private LOLAPI()
        {
        }
        
        private static final LOLAPI INSTANCE = new LOLAPI();
        
        /**
         * Gets mastery api.
         *
         * @return the mastery api
         */
        public MasteryAPI getMasteryAPI()
        {
            return MasteryAPI.getInstance();
        }
        
        /**
         * Gets spectator api.
         *
         * @return the spectator api
         */
        public SpectatorAPI getSpectatorAPI()
        {
            return SpectatorAPI.getInstance();
        }
        
        /**
         * Gets spectator api.
         *
         * @return the spectator api
         */
        public SpectatorV5API getSpectatorV5API()
        {
            return SpectatorV5API.getInstance();
        }
        
        /**
         * Gets summoner api.
         *
         * @return the summoner api
         */
        public SummonerAPI getSummonerAPI()
        {
            return SummonerAPI.getInstance();
        }
        
        /**
         * Gets tournament api.
         *
         * @param useStub if true, returns the -stub instance
         * @return the tournament api
         */
        public TournamentAPI getTournamentAPI(boolean useStub)
        {
            return TournamentAPI.getInstance(useStub);
        }
        
        /**
         * Gets champion api.
         *
         * @return the champion api
         */
        public ChampionAPI getChampionAPI()
        {
            return ChampionAPI.getInstance();
        }
        
        /**
         * Gets match api.
         *
         * @return the match api
         */
        public MatchV5API getMatchAPI()
        {
            return MatchV5API.getInstance();
        }
        
        /**
         * Gets league api.
         *
         * @return the league api
         */
        public LeagueAPI getLeagueAPI()
        {
            return LeagueAPI.getInstance();
        }
        
        /**
         * Gets clash api.
         *
         * @return the clash api
         */
        public ClashAPI getClashAPI()
        {
            return ClashAPI.getInstance();
        }
        
        /**
         * Gets challenge api
         *
         * @return the challenge api
         */
        public ChallengeAPI getChallengeAPI()
        {
            return ChallengeAPI.getInstance();
        }
    }
    
    public LORAPI getLORAPI()
    {
        return LORAPI.INSTANCE;
    }
    
    public static final class LORAPI
    {
        private LORAPI()
        {
        }
        
        private static final R4J.LORAPI INSTANCE = new R4J.LORAPI();
        
        /**
         * Gets ranked api.
         *
         * @return the ranked api
         */
        public LORRankedAPI getRankedAPI()
        {
            return LORRankedAPI.getInstance();
        }
        
        /**
         * Gets client api.
         *
         * @return the client api
         */
        public LoRClientAPI getClientAPI()
        {
            return LoRClientAPI.getInstance();
        }
        
        /**
         * Gets match api.
         *
         * @return the match api
         */
        public LORMatchAPI getMatchAPI()
        {
            return LORMatchAPI.getInstance();
        }
        
    }
    
    public TFTAPI getTFTAPI()
    {
        return TFTAPI.INSTANCE;
    }
    
    public static final class TFTAPI
    {
        private TFTAPI()
        {
        }
        
        private static final TFTAPI INSTANCE = new TFTAPI();
        
        /**
         * Gets summoner api.
         *
         * @return the summoner api
         */
        public TFTSummonerAPI getSummonerAPI()
        {
            return TFTSummonerAPI.getInstance();
        }
        
        /**
         * Gets match api.
         *
         * @return the match api
         */
        public TFTMatchAPI getMatchAPI()
        {
            return TFTMatchAPI.getInstance();
        }
        
        /**
         * Gets league api.
         *
         * @return the league api
         */
        public TFTLeagueAPI getLeagueAPI()
        {
            return TFTLeagueAPI.getInstance();
        }
        
    }
    
    public VALAPI getVALAPI()
    {
        return VALAPI.INSTANCE;
    }
    
    public static final class VALAPI
    {
        private VALAPI()
        {
        }
        
        private static final VALAPI INSTANCE = new VALAPI();
        
        /**
         * Gets summoner api.
         *
         * @return the summoner api
         */
        public VALContentAPI getContentAPI()
        {
            return VALContentAPI.getInstance();
        }
        
        /**
         * Gets match api.
         *
         * @return the match api
         */
        public VALMatchAPI getMatchAPI()
        {
            return VALMatchAPI.getInstance();
        }
        
        public VALRankedAPI getRankedAPI()
        {
            return VALRankedAPI.getInstance();
        }
    }
    
    /**
     * Gets image api.
     *
     * @return the image api
     */
    public ImageAPI getImageAPI()
    {
        return ImageAPI.getInstance();
    }
    
    
    /**
     * Gets account api.
     *
     * @return the account api
     */
    public AccountAPI getAccountAPI()
    {
        return AccountAPI.getInstance();
    }
    
    
    /**
     * Gets status api.
     *
     * @return the status api
     */
    public StatusAPI getStatusAPI()
    {
        return StatusAPI.getInstance();
    }
    
    /**
     * Gets static api.
     *
     * @return the static api
     */
    public DDragonAPI getDDragonAPI()
    {
        return DDragonAPI.getInstance();
    }
    
}
