package no.stelar7.api.r4j.pojo.val.match;

import no.stelar7.api.r4j.basic.utils.sql.*;

import java.io.Serializable;
import java.util.*;

public class Location implements Serializable
{
    private static final long serialVersionUID = -2841290000843430590L;
    
    
    private int x;
    private int y;
    
    public int getX()
    {
        return x;
    }
    
    public int getY()
    {
        return y;
    }
    
    @Override
    public boolean equals(Object o)
    {
        if (this == o)
        {
            return true;
        }
        if (o == null || getClass() != o.getClass())
        {
            return false;
        }
        Location location = (Location) o;
        return x == location.x &&
               y == location.y;
    }
    
    @Override
    public int hashCode()
    {
        return Objects.hash(x, y);
    }
    
    @Override
    public String toString()
    {
        return "Location{" +
               "x=" + x +
               ", y=" + y +
               '}';
    }
    
    @SQLTypeMap
    private static Map<String, String> getTypeMap()
    {
        Map<String, String> returnMap = new HashMap<>();
        
        returnMap.put("id", "long");
        returnMap.put("x", "int");
        returnMap.put("y", "int");
        
        return returnMap;
    }
    
    @SQLForeignMap
    private static Map<Class<?>, String> getForeignKeyMap()
    {
        Map<Class<?>, String> returnMap = new HashMap<>();
        
        returnMap.put(VALMatch.class, "id");
        returnMap.put(RoundResult.class, "roundNum");
        returnMap.put(PlayerLocation.class, "puuid");
        returnMap.put(PlayerRoundStats.class, "puuid");
        
        return returnMap;
    }
    
    @SQLExtraMap
    private static Map<String, String> getExtraMap()
    {
        Map<String, String> returnMap = new HashMap<>();
        
        returnMap.put("id", "primary key auto_increment");
        
        return returnMap;
    }
}
