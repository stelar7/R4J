package no.stelar7.api.l4j8.basic;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

public interface APIObject
{
    static Class<?> createFromString(final String json, final Class<?> target)
    {
        System.err.println("NOT A VALID TARGET FOR createFromString?");
        return null;
    }

    static ObjectMapper getDefaultMapper()
    {
        final ObjectMapper mapper = new ObjectMapper();
        mapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        return mapper;
    }
}
