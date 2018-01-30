package no.stelar7.api.l4j8.basic.cache.impl;

import no.stelar7.api.l4j8.basic.cache.*;
import no.stelar7.api.l4j8.basic.constants.api.URLEndpoint;
import no.stelar7.api.l4j8.basic.utils.MySQL;

import java.util.Optional;

public class MySQLCacheProvider implements CacheProvider
{
    
    private       long              timeToLive;
    private       CacheLifetimeHint hints;
    private final MySQL             sql;
    
    public MySQLCacheProvider(String host, int port, String database, String username, String password)
    {
        sql = new MySQL(host, port, database, username, password);
    }
    
    @Override
    public void setTimeToLive(CacheLifetimeHint timeToLive)
    {
        this.hints = timeToLive;
    }
    
    @Override
    public void setTimeToLiveGlobal(long timeToLive)
    {
        this.timeToLive = timeToLive;
    }
    
    @Override
    public void store(URLEndpoint type, Object... obj)
    {
    
    }
    
    @Override
    public void update(URLEndpoint type, Object... obj)
    {
    
    }
    
    @Override
    public Optional<?> get(URLEndpoint type, Object... data)
    {
        return Optional.empty();
    }
    
    @Override
    public void clear(URLEndpoint type, Object... filter)
    {
    
    }
    
    @Override
    public void clearOldCache()
    {
    
    }
    
    @Override
    public long getTimeToLive(URLEndpoint type)
    {
        return 0;
    }
    
    @Override
    public long getSize(URLEndpoint type)
    {
        return 0;
    }
    
}
