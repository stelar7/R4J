package no.stelar7.api.l4j8.basic.cache;

import no.stelar7.api.l4j8.basic.constants.api.URLEndpoint;

public final class CacheDataKeys
{
    private CacheDataKeys()
    {
        // hide public constructor
    }
    
    
    private static String[] getKeysForType(URLEndpoint type)
    {
        switch (type)
        {
            case V3_MATCH:
            {
                return new String[]{"platformId", "gameId"};
            }
            case V3_TIMELINE:
            {
                return new String[]{"platformId", "gameId"};
            }
            case V3_CHAMPIONS_BY_ID:
            {
                return new String[]{"platformId", "id"};
            }
            case V3_CHAMPIONS:
            {
                return new String[]{"platformId", "freeToPlay"};
            }
            case V3_MASTERY_BY_ID:
            {
                return new String[]{"platformId", "playerId"};
            }
            case V3_MASTERY_BY_CHAMPION:
            {
                return new String[]{"platformId", "playerId", "championId"};
            }
            case V3_MASTERY_SCORE:
            {
                return new String[]{"platformId", "playerId"};
            }
            case V3_LEAGUE_CHALLENGER:
            {
                return new String[]{"platformId", "queue"};
            }
            case V3_LEAGUE_MASTER:
            {
                return new String[]{"platformId", "queue"};
            }
            case V3_LEAGUE:
            {
                return new String[]{"platformId", "leagueId"};
            }
            case V3_LEAGUE_ENTRY:
            {
                return new String[]{"platformId", "playerOrTeamId"};
            }
            case V3_SPECTATOR_CURRENT:
            {
                return new String[]{"platformId", "playerId"};
            }
            case V3_SPECTATOR_FEATURED:
            {
                return new String[]{"platformId"};
            }
            case V3_SUMMONER_BY_ACCOUNT:
            {
                return new String[]{"platformId", "accountId"};
            }
            case V3_SUMMONER_BY_ID:
            {
                return new String[]{"platformId", "1", "id"};
            }
            case V3_SUMMONER_BY_NAME:
            {
                return new String[]{"platformId", "1", "1", "name"};
            }
            case V3_SHARD_STATUS:
            {
                return new String[]{"platformId"};
            }
            default:
            {
                throw new UnsupportedOperationException(type.toString() + " is not added to the cache");
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
                String key   = keys[i];
                Object value = filters[i];
                
                if ("1".equals(key))
                {
                    continue;
                }
                
                start.append(key).append(" = ").append(value);
                if (i < keys.length - 1)
                {
                    start.append(" AND ");
                }
            }
        }
        
        return start.toString();
    }
}
