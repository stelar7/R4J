package no.stelar7.api.l4j8.basic.cache;

import no.stelar7.api.l4j8.basic.constants.api.URLEndpoint;

import java.util.Optional;
import java.util.concurrent.*;

public abstract class CacheProvider
{
    
    /**
     * Stores the obj in the type cache
     *
     * @param type the endpoint to store the cache in
     * @param obj  the object to store
     */
    public abstract void store(URLEndpoint type, Object... obj);
    
    
    /**
     * Updates the TTL for this object
     *
     * @param type the endpoint to store the cache in
     * @param obj  the object to store
     */
    public abstract void update(URLEndpoint type, Object... obj);
    
    /**
     * Returns data from the cache if found, otherwise Optional.empty();
     *
     * @param type the endpoint to get data from
     * @param data the data to look for
     * @return return type depends on the endpoint called
     */
    public abstract Optional<?> get(URLEndpoint type, Object... data);
    
    /**
     * Empties the entire cache for that endpoint
     *
     * @param type the endpoint
     */
    public abstract void clear(URLEndpoint type);
    
    /**
     * Removes any old items frcm the cache
     */
    public abstract void clearOldCache();
    
    private ScheduledExecutorService clearService = Executors.newScheduledThreadPool(1);
    private   ScheduledFuture<?> clearTask;
    protected long               timeToLive;
    
    /**
     * Returns the time in seconds the items are alloweed to live in the cache
     *
     * @return long
     */
    public abstract long getTimeToLive();
    
    /**
     * Returns the count of items in the cache
     *
     * @return long
     */
    public abstract long getSize();
    
    
    /**
     * Sets the timeout for caches
     *
     * @param timeToLive how long they should live
     */
    public void setTimeToLive(long timeToLive)
    {
        this.timeToLive = timeToLive;
        
        if (timeToLive > 0)
        {
            clearTask = clearService.scheduleAtFixedRate(this::clearOldCache, timeToLive, timeToLive, TimeUnit.SECONDS);
        } else
        {
            if (clearTask != null)
            {
                clearTask.cancel(false);
            }
        }
    }
    
    public static final class EmptyProvider extends CacheProvider
    {
        public static final EmptyProvider INSTANCE = new EmptyProvider();
        
        private EmptyProvider()
        {
            // Hide public constructor
        }
        
        @Override
        public void store(URLEndpoint clazz, Object... obj) {/*void cache*/}
        
        @Override
        public void update(URLEndpoint type, Object... obj)
        {/*void cache*/}
        
        @Override
        public Optional<?> get(URLEndpoint type, Object... data)
        {
            return Optional.empty();
        }
        
        @Override
        public void clear(URLEndpoint type)
        {/*void cache*/}
        
        @Override
        public void clearOldCache()
        {/*void cache*/}
    
        @Override
        public long getTimeToLive()
        {
            return 0;
        }
    
        @Override
        public long getSize()
        {
            return 0;
        }
    }
}
