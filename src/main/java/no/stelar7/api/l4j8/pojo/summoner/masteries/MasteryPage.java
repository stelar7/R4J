package no.stelar7.api.l4j8.pojo.summoner.masteries;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import no.stelar7.api.l4j8.basic.APIObject;
import no.stelar7.api.l4j8.pojo.summoner.runes.RunePage;

public class MasteryPage implements APIObject
{
    private Boolean       current;
    private Long          id;
    private List<Mastery> masteries;
    private String        name;

    /**
     * The mastery page ID
     *
     * @return Long
     */
    public Long getId()
    {
        return this.id;
    }

    /**
     * Collection of masteries associated with the mastery page
     *
     * @return {@code List<Mastery>}
     */
    public List<Mastery> getMasteries()
    {
        return Collections.unmodifiableList(this.masteries);
    }

    /**
     * The mastery page name
     *
     * @return String
     */
    public String getName()
    {
        return this.name;
    }

    /**
     * Indicates if the mastery page is the current mastery page
     *
     * @return Boolean
     */
    public Boolean isCurrent()
    {
        return this.current;
    }

    @Override
    public String toString()
    {
        return "MasteryPage [current=" + this.current + ", id=" + this.id + ", masteries=" + this.masteries + ", name=" + this.name + "]";
    }

    public static Map<Object, List<Object>> createFromString(final String json) throws Exception
    {
        final ObjectMapper mapper = new ObjectMapper();
        mapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);

        final JsonNode node = mapper.readTree(json);
        final Map<Object, List<Object>> data = new HashMap<>();
        node.fields().forEachRemaining(m -> {
            final List<Object> pages = new ArrayList<>();

            m.getValue().get("pages").iterator().forEachRemaining(n -> {
                try
                {
                    final Object page = mapper.readValue(n.toString(), RunePage.class);
                    pages.add(page);
                } catch (final Exception e)
                {
                    e.printStackTrace();
                }
            });

            data.put(m.getKey(), pages);
        });
        return data;
    }

}
