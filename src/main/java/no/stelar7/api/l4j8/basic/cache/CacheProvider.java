package no.stelar7.api.l4j8.basic.cache;

import no.stelar7.api.l4j8.basic.constants.api.URLEndpoint;

import java.nio.file.Path;
import java.util.Optional;

public interface CacheProvider
{
    
    int  TTL_INFINITY     = -1;
    Path LOCATION_DEFAULT = null;
    int  TTL_USE_HINTS    = -2;
    
    /**
     * Stores the obj in the type cache
     *
     * @param type the endpoint to store the cache in
     * @param obj  the object to store
     */
    void store(URLEndpoint type, Object... obj);
    
    
    /**
     * Updates the TTL for this object
     *
     * @param type the endpoint to store the cache in
     * @param obj  the object to store
     */
    void update(URLEndpoint type, Object... obj);
    
    /**
     * Returns data from the cache if found, otherwise Optional.empty();
     *
     * @param type the endpoint to get data from
     * @param data the data to look for
     * @return return type depends on the endpoint called
     */
    Optional<?> get(URLEndpoint type, Object... data);
    
    /**
     * Empties the entire cache for that endpoint
     *
     * @param type   the endpoint
     * @param filter optional filter to clear specific data
     */
    void clear(URLEndpoint type, Object... filter);
    
    /**
     * Removes any old items frcm the cache
     */
    void clearOldCache();
    
    /**
     * Returns the time in seconds the items are alloweed to live in the cache
     *
     * @return long
     */
    long getTimeToLive(URLEndpoint type);
    
    /**
     * Returns the count of items in the cache (or the size in bytes)
     *
     * @return long
     */
    long getSize(URLEndpoint type);
    
    
    /**
     * Sets the timeout for caches
     *
     * @param timeToLive how long they should live
     */
    void setTimeToLiveGlobal(long timeToLive);
    
    /**
     * Sets the timeout for caches
     *
     * @param hints how long they should live
     */
    void setTimeToLive(CacheLifetimeHint hints);
    
}
