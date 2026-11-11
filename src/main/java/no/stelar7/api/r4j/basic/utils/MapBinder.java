package no.stelar7.api.r4j.basic.utils;

import com.google.gson.annotations.SerializedName;

import java.lang.reflect.*;
import java.util.*;

/**
 * Fills an object from a map of loosely typed values (what gson makes of a json object when it is read as a Map).
 */
public final class MapBinder
{
    private MapBinder()
    {
        // Hide public constructor
    }

    /**
     * Creates an object of the type and sets every field that has a usable value in the map.
     * The key of a field is its {@link SerializedName}, or its name. Only Double and {@code List<Integer>} fields are filled.
     * A value of another kind than the field expects is skipped, so one unexpected value does not lose the rest.
     *
     * @param values the values, may be null
     * @param type   a class with a no-argument constructor
     * @param <T>    the class
     * @return the object, or null if there is no map
     */
    public static <T> T bind(Map<String, ?> values, Class<T> type)
    {
        if (values == null)
        {
            return null;
        }

        try
        {
            Constructor<T> constructor = type.getDeclaredConstructor();
            constructor.setAccessible(true);
            T result = constructor.newInstance();

            for (Field field : type.getDeclaredFields())
            {
                if (Modifier.isStatic(field.getModifiers()) || Modifier.isTransient(field.getModifiers()))
                {
                    continue;
                }

                SerializedName name  = field.getAnnotation(SerializedName.class);
                Object         value = values.get(name != null ? name.value() : field.getName());

                Object converted = null;
                if (field.getType() == Double.class && value instanceof Number)
                {
                    converted = ((Number) value).doubleValue();
                } else if (field.getType() == List.class && value instanceof Collection)
                {
                    List<Integer> numbers = new ArrayList<>();
                    for (Object element : (Collection<?>) value)
                    {
                        if (element instanceof Number)
                        {
                            numbers.add(((Number) element).intValue());
                        }
                    }
                    converted = numbers;
                }

                if (converted != null)
                {
                    field.setAccessible(true);
                    field.set(result, converted);
                }
            }

            return result;
        } catch (ReflectiveOperationException e)
        {
            throw new IllegalStateException("Unable to create a " + type.getName(), e);
        }
    }
}
