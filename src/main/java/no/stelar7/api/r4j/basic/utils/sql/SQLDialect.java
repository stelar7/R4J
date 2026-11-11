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
    
    /**
     * Turns the value into a string literal, escaping the characters that could end the literal early
     */
    protected String quote(String value)
    {
        StringBuilder sb = new StringBuilder("'");
        for (char c : value.toCharArray())
        {
            switch (c)
            {
                case 0:
                    sb.append("\\0");
                    break;
                case 26:
                    sb.append("\\Z");
                    break;
                case '\'':
                    sb.append("''");
                    break;
                case '\\':
                    sb.append("\\\\");
                    break;
                default:
                    sb.append(c);
            }
        }

        return sb.append("'").toString();
    }

    public String convertForInsert(String type, Object value)
    {
        if (type == null && value != null)
        {
            throw new IllegalArgumentException("Unknown type for value " + value);
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
            return quote(value.toString());
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
