package no.stelar7.api.r4j.basic.cache.impl;

import dev.morphia.*;
import dev.morphia.query.Query;
import dev.morphia.query.internal.MorphiaCursor;
import no.stelar7.api.r4j.basic.cache.*;
import no.stelar7.api.r4j.basic.calling.DataCall;
import no.stelar7.api.r4j.basic.constants.api.URLEndpoint;
import org.slf4j.*;

import java.util.*;

public class MongoDBCacheProvider implements CacheProvider
{
    private static final Logger logger = LoggerFactory.getLogger(MongoDBCacheProvider.class);
    
    Morphia                 morphia;
    com.mongodb.MongoClient morphiaClient;
    Datastore               morphiaStore;
    
    public MongoDBCacheProvider(String connectionString)
    {
        morphiaClient = new com.mongodb.MongoClient("localhost:27017");
        morphia = new Morphia();
        morphiaStore = morphia.createDatastore(morphiaClient, DataCall.getCredentials().getUniqueKeyCombination());
    }
    
    @Override
    public void store(URLEndpoint type, Map<String, Object> obj)
    {
        morphiaStore.save(obj.get("value"));
    }
    
    
    @Override
    public void update(URLEndpoint type, Map<String, Object> data)
    {
        Query<?> sum = morphiaStore.find(type.getType());
        data.forEach((k, v) -> sum.and(sum.criteria(k).equalIgnoreCase(v)));
        MorphiaCursor<?> morphiaCursor = sum.find();
        
        if (morphiaCursor.hasNext())
        {
            morphiaCursor.remove();
        }
        
        store(type, data);
    }
    
    @Override
    public Optional<?> get(URLEndpoint type, Map<String, Object> data)
    {
        Query<?> sum = morphiaStore.find(type.getType());
        data.forEach((k, v) -> sum.and(sum.criteria(k).equalIgnoreCase(v)));
        
        MorphiaCursor<?> cursor = sum.find();
        return cursor.hasNext() ? Optional.of(cursor.next()) : Optional.empty();
    }
    
    @Override
    public void clear(URLEndpoint type, Map<String, Object> filter)
    {
        Query<?> sum = morphiaStore.find(type.getType());
        
        filter.forEach((k, v) -> sum.and(sum.criteria(k).equalIgnoreCase(v)));
        
        MorphiaCursor<?> cursor = sum.find();
        while (cursor.hasNext())
        {
            cursor.remove();
        }
    }
    
    @Override
    public void clearOldCache()
    {
        // not implemented...
    }
    
    @Override
    public long getTimeToLive(URLEndpoint type)
    {
        return Long.MAX_VALUE;
    }
    
    @Override
    public long getSize(URLEndpoint type, Map<String, Object> filter)
    {
        return Long.MAX_VALUE;
    }
    
    @Override
    public void setTimeToLiveGlobal(long timeToLive)
    {
        // not implemented...
    }
    
    @Override
    public void setTimeToLive(CacheLifetimeHint hints)
    {
        // todo
    }
}
