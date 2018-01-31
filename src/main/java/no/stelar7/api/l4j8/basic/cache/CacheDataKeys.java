package no.stelar7.api.l4j8.basic.cache;

import no.stelar7.api.l4j8.basic.constants.api.URLEndpoint;

public final class CacheDataKeys
{
    private CacheDataKeys()
    {
        // hide public constructor
    }
    
    private static String[] matchKeys = {"gameId", "platformId"};
    
    private static String[] getKeysForType(URLEndpoint type)
    {
        switch (type)
        {
            case V3_MATCH:
            {
                return matchKeys;
            }
            default:
            {
                throw new UnsupportedOperationException(type.toString() + " is not registered with the cache");
            }
        }
    }
    
    public static String createUpdateStatement(URLEndpoint type, Object[] obj)
    {
        Object[] filters = new Object[obj.length - 1];
        System.arraycopy(obj, 1, filters, 0, obj.length - 1);
        
        StringBuilder start = new StringBuilder("UPDATE ? SET ? = ?");
        String[]      keys  = getKeysForType(type);
        
        if (keys.length > 0)
        {
            start.append(" WHERE ");
            for (int i = 0; i < keys.length; i++)
            {
                start.append(keys[i]).append(" = ").append(filters[i]);
                if (i < keys.length - 1)
                {
                    start.append(" AND ");
                }
            }
        }
        
        return start.toString();
    }
}
