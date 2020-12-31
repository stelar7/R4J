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
                    
                    types.put("int8", "tinyint");
                    types.put("int16", "smallint");
                    types.put("int32", "int");
                    types.put("int64", "bigint");
                    types.put("tinytext", "varchar(10)");
                    types.put("text", "varchar(100)");
                    types.put("bigtext", "varchar(1000)");
                    types.put("binary", "varbinary");
                    types.put("date", "datetime");
                    types.put("double", "float");
                    types.put("float", "real");
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
        if (value == null) {
            return "null";
        }
        
        if (type.equals("boolean"))
        {
            return ((boolean) value) ? "1" : "0";
        }
        
        if (type.contains("text"))
        {
            return "\"" + value.toString() + "\"";
        }
        
        if (type.contains("int"))
        {
            return value.toString();
        }
        
        throw new RuntimeException("Unconvertable type " + type + "... value=" + value.toString());
    }
}
