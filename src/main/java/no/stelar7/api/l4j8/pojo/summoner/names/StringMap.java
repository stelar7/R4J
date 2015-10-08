package no.stelar7.api.l4j8.pojo.summoner.names;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import no.stelar7.api.l4j8.basic.APIObject;

public class StringMap implements APIObject
{
    public static Map<String, String> createFromString(String json) throws Exception
    {
        final ObjectMapper mapper = new ObjectMapper();
        mapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);

        final JsonNode node = mapper.readTree(json);
        final Map<String, String> data = new HashMap<>();
        node.fields().forEachRemaining(e -> data.put(e.getKey(), e.getValue().toString()));
        return data;
    }
}
