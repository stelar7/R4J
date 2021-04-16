package no.stelar7.api.r4j.basic.utils;

import no.stelar7.api.r4j.basic.utils.sql.*;

import java.lang.reflect.*;
import java.util.*;
import java.util.Map.Entry;

public class ObjectMapper<T>
{
    private final Class<T>           clazz;
    private final Map<String, Field> fields          = new LinkedHashMap<>();
    private final Map<String, Field> oneToOneFields  = new LinkedHashMap<>();
    private final Map<String, Field> oneToManyFields = new LinkedHashMap<>();
    private       Method             extrasMethod;
    private       Method             typesMethod;
    private       Method             foreignsMethod;
    private       Field              pkField;
    
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
            if (Modifier.isStatic(field.getModifiers()))
            {
                continue;
            }
            
            Class<?> returntype = field.getType();
            if (returntype.isEnum() || returntype.isPrimitive() || returntype.isAssignableFrom(String.class) || returntype.isAssignableFrom(List.class))
            {
                continue;
            }
            
            field.setAccessible(true);
            oneToOneFields.put(field.getName(), field);
        }
        
        for (Field field : clazz.getDeclaredFields())
        {
            if (Modifier.isStatic(field.getModifiers()))
            {
                continue;
            }
            
            Class<?> returntype = field.getType();
            if (!returntype.isAssignableFrom(List.class))
            {
                continue;
            }
            
            field.setAccessible(true);
            oneToManyFields.put(field.getName(), field);
        }
        
        for (Method method : clazz.getDeclaredMethods())
        {
            if (method.isAnnotationPresent(SQLExtraMap.class))
            {
                method.setAccessible(true);
                extrasMethod = method;
            }
            
            if (method.isAnnotationPresent(SQLTypeMap.class))
            {
                method.setAccessible(true);
                typesMethod = method;
            }
            
            if (method.isAnnotationPresent(SQLForeignMap.class))
            {
                method.setAccessible(true);
                foreignsMethod = method;
            }
        }
        
        for (Field field : clazz.getDeclaredFields())
        {
            if (field.isAnnotationPresent(SQLReturnKey.class))
            {
                field.setAccessible(true);
                pkField = field;
            }
        }
    }
    
    public Set<String> getFieldNames()
    {
        return new HashSet<>(fields.keySet());
    }
    
    public Map<String, Field> getPropertyFields()
    {
        return new HashMap<>(fields);
    }
    
    public HashMap<String, Field> getOneToOneFields()
    {
        return new HashMap<>(oneToOneFields);
    }
    
    public HashMap<String, Field> getOneToManyFields()
    {
        return new HashMap<>(oneToManyFields);
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
    
    public Map<String, String> getExtraMap()
    {
        if (this.extrasMethod == null)
        {
            return Collections.emptyMap();
        }
        
        try
        {
            Map<String, String> preTrans = (Map<String, String>) this.extrasMethod.invoke(null);
            return preTrans;
        } catch (Exception e)
        {
            e.printStackTrace();
        }
        
        return Collections.emptyMap();
    }
    
    public Map<String, String> getUntranslatedTypeMap()
    {
        if (this.typesMethod != null)
        {
            try
            {
                Map<String, String> preTrans = (Map<String, String>) this.typesMethod.invoke(null);
                return preTrans;
            } catch (Exception e)
            {
                e.printStackTrace();
            }
        }
        
        Map<String, String> types = new HashMap<>();
        for (Entry<String, Field> entry : getPropertyFields().entrySet())
        {
            Class<?> returnType = entry.getValue().getType();
            if (returnType.isAssignableFrom(String.class))
            {
                types.put(entry.getKey(), "string");
            }
            
            if (returnType.isAssignableFrom(int.class) || returnType.isAssignableFrom(Integer.class))
            {
                types.put(entry.getKey(), "int");
            }
            
            if (returnType.isAssignableFrom(long.class) || returnType.isAssignableFrom(Long.class))
            {
                types.put(entry.getKey(), "long");
            }
            
            if (returnType.isAssignableFrom(float.class) || returnType.isAssignableFrom(Float.class))
            {
                types.put(entry.getKey(), "float");
            }
            
            if (returnType.isAssignableFrom(double.class) || returnType.isAssignableFrom(Double.class))
            {
                types.put(entry.getKey(), "float");
            }
            
            if (returnType.isAssignableFrom(boolean.class) || returnType.isAssignableFrom(Boolean.class))
            {
                types.put(entry.getKey(), "boolean");
            }
            
            if (returnType.isEnum())
            {
                types.put(entry.getKey(), "string");
            }
        }
        
        return types;
    }
    
    public Map<Class<?>, String> getForeignMap()
    {
        if (this.foreignsMethod == null)
        {
            return Collections.emptyMap();
        }
        
        try
        {
            Map<Class<?>, String> preTrans = (Map<Class<?>, String>) this.foreignsMethod.invoke(null);
            return preTrans;
        } catch (Exception e)
        {
            e.printStackTrace();
        }
        
        return Collections.emptyMap();
    }
    
    public Pair<Class<?>, String> getReturnedKey(Object obj)
    {
        if (this.pkField == null)
        {
            return null;
        }
        
        try
        {
            Object value = this.pkField.get(obj);
            return new Pair<>(this.clazz, value.toString());
        } catch (Exception e)
        {
            e.printStackTrace();
        }
        
        return null;
    }
}
