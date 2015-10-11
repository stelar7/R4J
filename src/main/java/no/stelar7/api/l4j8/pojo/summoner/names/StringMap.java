package no.stelar7.api.l4j8.pojo.summoner.names;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.databind.JsonNode;

import no.stelar7.api.l4j8.basic.APIObject;

public class StringMap implements APIObject
{
    public static Map<String, String> createFromString(final String json) throws Exception
    {

        final JsonNode node = APIObject.getDefaultMapper().readTree(json);
        final Map<String, String> data = new HashMap<>();
        node.fields().forEachRemaining(e -> data.put(e.getKey(), e.getValue().toString()));
        return data;
    }
}
