package no.stelar7.api.l4j8.basic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import no.stelar7.api.l4j8.dto.summoner.Summoner;
import no.stelar7.api.l4j8.dto.summoner.masteries.MasteryPage;
import no.stelar7.api.l4j8.dto.summoner.names.StringMap;
import no.stelar7.api.l4j8.dto.summoner.runes.RunePage;

public class APIObject
{
    Object createFromString(Class<?> requestedReturnType, String json) throws Exception
    {
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);

        JsonNode node = mapper.readTree(json);

        if (requestedReturnType.equals(Summoner.class))
        {
            Map<Object, Summoner> summoners = new HashMap<>();
            node.fields().forEachRemaining(m -> {
                try
                {
                    Summoner summoner = mapper.readValue(m.getValue().toString(), Summoner.class);
                    summoners.put(m.getKey(), summoner);
                } catch (Exception e)
                {
                    e.printStackTrace();
                }
            });
            return summoners;
        }

        if (requestedReturnType.equals(MasteryPage.class) || requestedReturnType.equals(RunePage.class))
        {
            Map<Object, List<Object>> masteries = new HashMap<>();
            node.fields().forEachRemaining(m -> {
                List<Object> pages = new ArrayList<>();

                m.getValue().get("pages").iterator().forEachRemaining(n -> {
                    try
                    {
                        Object page = mapper.readValue(n.toString(), requestedReturnType);
                        pages.add(page);
                    } catch (Exception e)
                    {
                        e.printStackTrace();
                    }
                });

                masteries.put(m.getKey(), pages);
            });
            return masteries;
        }
        
        if (requestedReturnType.equals(StringMap.class))
        {
            Map<String, String> data = new HashMap<>();
            node.fields().forEachRemaining(e -> data.put(e.getKey(), e.getValue().toString()));
            return data;
        }

        return null;
    }
}
