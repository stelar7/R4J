package no.stelar7.api.r4j.basic.utils.sql;

import java.util.*;
import java.util.stream.Collectors;

public enum SQLDialect
{
    MYSQL
            {
                final Map<String, String> types = new HashMap<>();
                
                public Map<String, String> getTypeMap()
                {
                    if (!types.isEmpty())
                    {
                        return types;
                    }
                    
                    types.put("int", "int");
                    types.put("long", "bigint");
                    types.put("string", "varchar(255)");
                    types.put("float", "float");
                    types.put("double", "double");
                    types.put("boolean", "tinyint(1)");
                    
                    return types;
                }
            };
    
    
    public abstract Map<String, String> getTypeMap();
    
    public Map<String, String> translate(Collection<String> types)
    {
        return types.stream().distinct().collect(Collectors.toMap(a -> a, a -> getTypeMap().getOrDefault(a, a)));
    }
    
    public String convertForInsert(String type, Object value)
    {
        if (type == null) {
            System.out.println();
        }
        
        if (value == null)
        {
            return "null";
        }
        
        if (type.equals("boolean"))
        {
            return ((boolean) value) ? "1" : "0";
        }
        
        if (type.contains("string"))
        {
            return "\"" + value.toString() + "\"";
        }
    
        if (type.contains("int"))
        {
            return value.toString();
        }
    
        if (type.contains("long"))
        {
            return value.toString();
        }
    
        if (type.contains("float"))
        {
            return value.toString();
        }
    
        if (type.contains("double"))
        {
            return value.toString();
        }
    
        throw new RuntimeException("Unconvertable type " + type + "... value=" + value.toString());
    }
}
