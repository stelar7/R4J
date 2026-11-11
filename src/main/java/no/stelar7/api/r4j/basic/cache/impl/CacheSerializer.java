package no.stelar7.api.r4j.basic.cache.impl;

import com.google.gson.stream.JsonWriter;
import no.stelar7.api.r4j.basic.constants.api.URLEndpoint;
import no.stelar7.api.r4j.basic.utils.Utils;
import org.slf4j.*;

import java.io.*;
import java.nio.charset.StandardCharsets;

/**
 * Turns cached items into bytes and back, for the caches that keep their data outside of the JVM.
 * <p>
 * An item is stored as json when it is what the endpoint returns and reading the json back gives an equal object.
 * Json does not break when a field is added to a class, and reading it can not run code.
 * Everything else (ie. the lists and maps built from the static data) is stored with java serialization.
 */
public final class CacheSerializer
{
    private static final Logger logger = LoggerFactory.getLogger(CacheSerializer.class);

    /**
     * Only classes of this library and the JDK can be read from data in the java serialization format
     */
    private static final ObjectInputFilter SERIALIZATION_FILTER = ObjectInputFilter.Config.createFilter("no.stelar7.api.r4j.**;java.**;com.google.gson.**;!*");

    private CacheSerializer()
    {
        // Hide public constructor
    }

    /**
     * @param type the endpoint the item belongs to
     * @param obj  the item
     * @return json (as UTF-8) when that is lossless, otherwise the java serialization of the item
     * @throws IOException if the item can not be serialized at all
     */
    public static byte[] serialize(URLEndpoint type, Object obj) throws IOException
    {
        if (obj.getClass() == type.getType())
        {
            try
            {
                String json = toJson(obj);
                if (obj.equals(Utils.getGson().fromJson(json, type.getType())))
                {
                    return json.getBytes(StandardCharsets.UTF_8);
                }
            } catch (RuntimeException e)
            {
                logger.debug("{} can not be stored as json, using java serialization", type, e);
            }
        }

        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try (ObjectOutputStream out = new ObjectOutputStream(bos))
        {
            out.writeObject(obj);
        }

        return bos.toByteArray();
    }

    private static String toJson(Object obj)
    {
        // written without the pretty printing of the shared gson instance, to keep the data small
        StringWriter writer = new StringWriter();
        Utils.getGson().toJson(obj, obj.getClass(), new JsonWriter(writer));
        return writer.toString();
    }

    /**
     * @param data what {@link #serialize(URLEndpoint, Object)} returned
     * @return true if the data is java serialization, false if it is json
     */
    public static boolean isJavaSerialization(byte[] data)
    {
        // java serialization always starts with the bytes AC ED, which is never the start of json
        return data.length >= 2 && (data[0] & 0xFF) == 0xAC && (data[1] & 0xFF) == 0xED;
    }

    /**
     * @param type the endpoint the item belongs to
     * @param data what {@link #serialize(URLEndpoint, Object)} returned
     * @return the item
     * @throws IOException            if the data can not be read
     * @throws ClassNotFoundException if the data refers to a class that does not exist (anymore)
     */
    public static Object deserialize(URLEndpoint type, byte[] data) throws IOException, ClassNotFoundException
    {
        if (!isJavaSerialization(data))
        {
            try
            {
                return Utils.getGson().fromJson(new String(data, StandardCharsets.UTF_8), type.getType());
            } catch (RuntimeException e)
            {
                throw new IOException("Unreadable json in the cache", e);
            }
        }

        try (ObjectInputStream in = new ObjectInputStream(new ByteArrayInputStream(data)))
        {
            in.setObjectInputFilter(SERIALIZATION_FILTER);
            return in.readObject();
        }
    }
}
