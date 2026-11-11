package no.stelar7.api.r4j.basic.serializer;

import com.google.gson.*;
import no.stelar7.api.r4j.basic.constants.types.CodedEnum;
import no.stelar7.api.r4j.basic.exceptions.APIEnumNotUpToDateException;
import org.slf4j.*;

import java.lang.reflect.Type;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class GenericEnumSerializer<T> implements JsonDeserializer<T>
{
    private static final Logger logger = LoggerFactory.getLogger(GenericEnumSerializer.class);

    /**
     * the values that have already been warned about, so a new queue does not flood the log
     */
    private static final Set<String> warned = ConcurrentHashMap.newKeySet();

    private static volatile boolean strict = false;

    /**
     * By default a value the enum does not know about (ie. a queue Riot just added) is turned into the UNKNOWN / UNDEFINED constant of the enum
     * (or null if the enum has neither), and a warning is logged once.
     * In strict mode an {@link APIEnumNotUpToDateException} is thrown instead, which makes the whole response fail to parse.
     *
     * @param strict true to throw on unknown values
     */
    public static void setStrict(boolean strict)
    {
        GenericEnumSerializer.strict = strict;
    }

    public static boolean isStrict()
    {
        return strict;
    }

    @Override
    public T deserialize(JsonElement json, Type t, JsonDeserializationContext context)
    {
        if (json.isJsonNull())
        {
            return null;
        }

        String   id        = json.getAsString();
        Object[] constants = ((Class<?>) t).getEnumConstants();

        // the value used by the api
        for (Object constant : constants)
        {
            try
            {
                Optional<?> match = ((CodedEnum<?>) constant).getFromCode(id);
                if (match.isPresent())
                {
                    return (T) match.get();
                }
            } catch (RuntimeException e)
            {
                // ie. a NumberFormatException from an enum that only knows numeric codes
            }
        }

        // the name of the constant, which is what gson writes when an object is serialized (ie. by a cache)
        for (Object constant : constants)
        {
            if (((Enum<?>) constant).name().equals(id))
            {
                return (T) constant;
            }
        }

        if (strict)
        {
            throw new APIEnumNotUpToDateException(t, json);
        }

        Object fallback = null;
        for (Object constant : constants)
        {
            String name = ((Enum<?>) constant).name();
            if (name.equals("UNKNOWN") || (fallback == null && name.equals("UNDEFINED")))
            {
                fallback = constant;
            }
        }

        if (warned.add(t.getTypeName() + "/" + id))
        {
            logger.warn("The enum {} is missing the value {}, using {} instead. Please make sure you have the latest version of the library; if you do, report this to the maintainer.", t.getTypeName(), json, fallback);
        }

        return (T) fallback;
    }
}
