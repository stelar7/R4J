package no.stelar7.api.r4j.impl;

import no.stelar7.api.r4j.basic.APICredentials;
import no.stelar7.api.r4j.basic.cache.impl.FileSystemCacheProvider;
import no.stelar7.api.r4j.basic.calling.R4JContext;
import no.stelar7.api.r4j.impl.lol.raw.*;
import no.stelar7.api.r4j.impl.lor.*;
import no.stelar7.api.r4j.impl.riftbound.RiftboundContentAPI;
import no.stelar7.api.r4j.impl.shared.*;
import no.stelar7.api.r4j.impl.tft.*;
import no.stelar7.api.r4j.impl.val.*;

/**
 * The base class for calling anything from this api wrapper
 */
public class R4J
{
    
    private final R4JContext context;
    private final LOLAPI     lolAPI;
    private final LORAPI     lorAPI;
    private final TFTAPI     tftAPI;
    private final VALAPI     valAPI;

    /**
     * Constructor for the R4J class.
     * <p>
     * This configures the default context, which is shared by every R4J created with this constructor, the static methods on DataCall,
     * and the getInstance() method of the api classes. Use {@link #isolated(APICredentials)} for an instance that shares nothing.
     *
     * @param creds the API credentials used for the API (your token)
     */
    public R4J(final APICredentials creds)
    {
        this(R4JContext.getDefault().setCredentials(creds).setCacheProvider(new FileSystemCacheProvider()));
    }

    public R4J(final APICredentials creds, final int numberOfCallsAllowedInParallel)
    {
        this(R4JContext.getDefault().setCredentials(creds).setCacheProvider(new FileSystemCacheProvider()).setNumberOfCallsAllowedInParallel(numberOfCallsAllowedInParallel));
    }

    /**
     * An R4J using the credentials, cache, settings and ratelimiters of the context.
     *
     * @param context the context to use
     * @return an R4J for the context
     */
    public static R4J withContext(final R4JContext context)
    {
        return new R4J(context);
    }
    
    // not public, so "new R4J(null)" is not ambiguous with the credentials constructor
    private R4J(final R4JContext context)
    {
        this.context = context;
        this.lolAPI = new LOLAPI(context);
        this.lorAPI = new LORAPI(context);
        this.tftAPI = new TFTAPI(context);
        this.valAPI = new VALAPI(context);
    }

    /**
     * Creates an R4J with its own context: its own credentials, ratelimiters, settings and (file system) cache.
     * Nothing is shared with the default context or with other isolated instances, so several sets of api keys can be used side by side.
     * <p>
     * Note that the convenience methods on the returned objects that fetch more data (ie. from a Summoner) always use the default context.
     *
     * @param creds the API credentials used for the API (your token)
     * @return a new, independent R4J
     */
    public static R4J isolated(final APICredentials creds)
    {
        R4JContext context = new R4JContext(creds);
        context.setCacheProvider(new FileSystemCacheProvider());
        return new R4J(context);
    }

    /**
     * @return the context of this instance; use it to change the cache, timeouts and other settings
     */
    public R4JContext getContext()
    {
        return context;
    }

    public LOLAPI getLoLAPI()
    {
        return lolAPI;
    }
    
    public static final class LOLAPI
    {
        private final R4JContext context;

        private LOLAPI(R4JContext context)
        {
            this.context = context;
        }
        
        /**
         * Gets mastery api.
         *
         * @return the mastery api
         */
        public MasteryAPI getMasteryAPI()
        {
            return MasteryAPI.getInstance(context);
        }
        
        /**
         * Gets spectator api.
         *
         * @return the spectator api
         */
        public SpectatorAPI getSpectatorAPI()
        {
            return SpectatorAPI.getInstance(context);
        }
        
        /**
         * Gets summoner api.
         *
         * @return the summoner api
         */
        public SummonerAPI getSummonerAPI()
        {
            return SummonerAPI.getInstance(context);
        }
        
        /**
         * Gets tournament api.
         *
         * @param useStub if true, returns the -stub instance
         * @return the tournament api
         */
        public TournamentAPI getTournamentAPI(boolean useStub)
        {
            return TournamentAPI.getInstance(context, useStub);
        }
        
        /**
         * Gets champion api.
         *
         * @return the champion api
         */
        public ChampionAPI getChampionAPI()
        {
            return ChampionAPI.getInstance(context);
        }
        
        /**
         * Gets match api.
         *
         * @return the match api
         */
        public MatchV5API getMatchAPI()
        {
            return MatchV5API.getInstance(context);
        }
        
        /**
         * Gets league api.
         *
         * @return the league api
         */
        public LeagueAPI getLeagueAPI()
        {
            return LeagueAPI.getInstance(context);
        }
        
        /**
         * Gets clash api.
         *
         * @return the clash api
         */
        public ClashAPI getClashAPI()
        {
            return ClashAPI.getInstance(context);
        }
        
        /**
         * Gets challenge api
         *
         * @return the challenge api
         */
        public ChallengeAPI getChallengeAPI()
        {
            return ChallengeAPI.getInstance(context);
        }
    }
    
    public LORAPI getLORAPI()
    {
        return lorAPI;
    }
    
    public static final class LORAPI
    {
        private final R4JContext context;

        private LORAPI(R4JContext context)
        {
            this.context = context;
        }
        
        /**
         * Gets ranked api.
         *
         * @return the ranked api
         */
        public LORRankedAPI getRankedAPI()
        {
            return LORRankedAPI.getInstance(context);
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
            return LORMatchAPI.getInstance(context);
        }
        
    }
    
    public TFTAPI getTFTAPI()
    {
        return tftAPI;
    }
    
    public static final class TFTAPI
    {
        private final R4JContext context;

        private TFTAPI(R4JContext context)
        {
            this.context = context;
        }
        
        /**
         * Gets summoner api.
         *
         * @return the summoner api
         */
        public TFTSummonerAPI getSummonerAPI()
        {
            return TFTSummonerAPI.getInstance(context);
        }
        
        /**
         * Gets match api.
         *
         * @return the match api
         */
        public TFTMatchAPI getMatchAPI()
        {
            return TFTMatchAPI.getInstance(context);
        }
        
        /**
         * Gets league api.
         *
         * @return the league api
         */
        public TFTLeagueAPI getLeagueAPI()
        {
            return TFTLeagueAPI.getInstance(context);
        }

        /**
         * Gets spectator api.
         *
         * @return the spectator api
         */
        public TFTSpectatorAPI getSpectatorAPI()
        {
            return TFTSpectatorAPI.getInstance(context);
        }
        
    }
    
    public VALAPI getVALAPI()
    {
        return valAPI;
    }
    
    public static final class VALAPI
    {
        private final R4JContext context;

        private VALAPI(R4JContext context)
        {
            this.context = context;
        }
        
        /**
         * Gets summoner api.
         *
         * @return the summoner api
         */
        public VALContentAPI getContentAPI()
        {
            return VALContentAPI.getInstance(context);
        }
        
        /**
         * Gets match api.
         *
         * @return the match api
         */
        public VALMatchAPI getMatchAPI()
        {
            return VALMatchAPI.getInstance(context);
        }
        
        public VALRankedAPI getRankedAPI()
        {
            return VALRankedAPI.getInstance(context);
        }

        /**
         * Gets accolades api.
         *
         * @return the accolades api
         */
        public VALAccoladesAPI getAccoladesAPI()
        {
            return VALAccoladesAPI.getInstance(context);
        }

        /**
         * Gets the api for the console versions of the match and ranked endpoints.
         *
         * @return the console api
         */
        public VALConsoleAPI getConsoleAPI()
        {
            return VALConsoleAPI.getInstance(context);
        }
    }
    
    /**
     * Gets the Riftbound content api.
     *
     * @return the riftbound content api
     */
    public RiftboundContentAPI getRiftboundContentAPI()
    {
        return RiftboundContentAPI.getInstance(context);
    }

    /**
     * Gets image api.
     *
     * @return the image api
     */
    public ImageAPI getImageAPI()
    {
        return ImageAPI.getInstance(context);
    }
    
    
    /**
     * Gets account api.
     *
     * @return the account api
     */
    public AccountAPI getAccountAPI()
    {
        return AccountAPI.getInstance(context);
    }
    
    
    /**
     * Gets status api.
     *
     * @return the status api
     */
    public StatusAPI getStatusAPI()
    {
        return StatusAPI.getInstance(context);
    }
    
    /**
     * Gets static api.
     *
     * @return the static api
     */
    public DDragonAPI getDDragonAPI()
    {
        return DDragonAPI.getInstance(context);
    }
    
}
