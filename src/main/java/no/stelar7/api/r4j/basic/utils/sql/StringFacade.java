package no.stelar7.api.r4j.basic.utils.sql;

import no.stelar7.api.r4j.pojo.val.match.*;

import java.util.*;

public class StringFacade
{
    private String value;
    
    public StringFacade(String value)
    {
        this.value = value;
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
        StringFacade that = (StringFacade) o;
        return Objects.equals(value, that.value);
    }
    
    @Override
    public int hashCode()
    {
        return Objects.hash(value);
    }
    
    @Override
    public String toString()
    {
        return "StringFacade{" +
               "value='" + value + '\'' +
               '}';
    }
    
    @SQLForeignMap
    private static Map<Class<?>, String> getForeignKeyMap()
    {
        Map<Class<?>, String> returnMap = new HashMap<>();
        
        returnMap.put(Match.class, "id");
        returnMap.put(RoundResult.class, "roundNum");
        returnMap.put(PlayerRoundStats.class, "puuid");
        
        return returnMap;
    }
}
