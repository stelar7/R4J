package no.stelar7.api.l4j8.pojo.staticdata.item;

import java.util.Map;

import no.stelar7.api.l4j8.basic.APIObject;
import no.stelar7.api.l4j8.pojo.staticdata.shared.BasicData;

public class Item extends BasicData implements APIObject
{
    Map<String, String> effect;

    /**
     * Gets the effect.
     *
     * @return the effect
     */
    public Map<String, String> getEffect()
    {
        return effect;
    }

}
