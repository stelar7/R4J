package no.stelar7.api.r4j.basic.utils;

import java.lang.reflect.*;
import java.util.*;
import java.util.Map.Entry;

public class ObjectMapper<T>
{
    private final Class<T>           clazz;
    private final Map<String, Field> fields             = new LinkedHashMap<>();
    private final Map<String, Field> nonprimitiveFields = new LinkedHashMap<>();
    
    public ObjectMapper(Class<T> clazz)
    {
        this.clazz = clazz;
        
        for (Field field : clazz.getDeclaredFields())
        {
            if (Modifier.isStatic(field.getModifiers()))
            {
                continue;
            }
            
            Class<?> returntype = field.getType();
            if (!returntype.isEnum() && !returntype.isPrimitive() && !returntype.isAssignableFrom(String.class))
            {
                continue;
            }
            
            field.setAccessible(true);
            fields.put(field.getName(), field);
        }
        
        for (Field field : clazz.getDeclaredFields())
        {
            Class<?> returntype = field.getType();
            if (!(!returntype.isEnum() && !returntype.isPrimitive() && !returntype.isAssignableFrom(String.class)))
            {
                continue;
            }
            
            field.setAccessible(true);
            nonprimitiveFields.put(field.getName(), field);
        }
    }
    
    public Set<String> getFieldNames()
    {
        return new HashSet<>(fields.keySet());
    }
    
    public Map<String, Object> unmap(T object)
    {
        Map<String, Object> data = new HashMap<>();
        
        for (Entry<String, Field> entry : fields.entrySet())
        {
            try
            {
                data.put(entry.getKey(), entry.getValue().get(object));
            } catch (IllegalAccessException e)
            {
                e.printStackTrace();
            }
        }
        
        return data;
    }
    
    public T map(Map<String, Object> row)
    {
        try
        {
            T dto = clazz.getConstructor().newInstance();
            for (Entry<String, Object> entry : row.entrySet())
            {
                T value = convertToTyped(entry.getValue());
                fields.get(entry.getKey()).set(dto, value);
            }
            
            return dto;
        } catch (Exception e)
        {
            e.printStackTrace();
            throw new RuntimeException("Failed to parse database object to type " + row + "..." + clazz.getName());
        }
    }
    
    public List<T> map(List<Map<String, Object>> rows)
    {
        List<T> list = new ArrayList<>();
        
        for (Map<String, Object> row : rows)
        {
            list.add(map(row));
        }
        
        return list;
    }
    
    private T convertToTyped(Object o)
    {
        return (T) o;
    }
}
