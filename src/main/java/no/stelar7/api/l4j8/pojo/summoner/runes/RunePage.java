package no.stelar7.api.l4j8.pojo.summoner.runes;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.JsonNode;

import no.stelar7.api.l4j8.basic.APIObject;

public class RunePage implements APIObject
{

    public static Map<Object, List<Object>> createFromString(final String json) throws Exception
    {
        final JsonNode node = APIObject.getDefaultMapper().readTree(json);
        final Map<Object, List<Object>> data = new HashMap<>();
        node.fields().forEachRemaining(m -> {
            final List<Object> pages = new ArrayList<>();

            m.getValue().get("pages").iterator().forEachRemaining(n -> {
                try
                {
                    final Object page = APIObject.getDefaultMapper().readValue(n.toString(), RunePage.class);
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

    private Boolean        current;
    private Long           id;
    private List<RuneSlot> slots;

    private String name;

    /**
     * The rune page ID
     *
     * @return Long
     */
    public Long getId()
    {
        return this.id;
    }

    /**
     * The rune page name
     *
     * @return String
     */
    public String getName()
    {
        return this.name;
    }

    /**
     * Collection of rune slots associated with the rune page, if there are no pages, it returns an empty list
     *
     * @return {@code List<RuneSlot>}
     */
    public List<RuneSlot> getSlots()
    {
        return (this.slots == null) ? Collections.EMPTY_LIST : Collections.unmodifiableList(this.slots);
    }

    /**
     * Indicates if the page is the current page.
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
        return "RunePage [current=" + this.current + ", id=" + this.id + ", slots=" + this.slots + ", name=" + this.name + "]";
    }
}
