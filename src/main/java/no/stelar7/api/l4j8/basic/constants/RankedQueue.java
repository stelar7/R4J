package no.stelar7.api.l4j8.basic.constants;

import java.util.*;
import java.util.stream.*;

public enum RankedQueue
{
    RANKED_SOLO_5X5("RANKED_SOLO_5x5"),
    RANKED_TEAM_5X5("RANKED_TEAM_5x5"),
    RANKED_TEAM_3X3("RANKED_TEAM_3x3"),
    RANKED_FLEX_SR("RANKED_FLEX_SR"),
    TEAM_BUILDER_DRAFT_RANKED_5X5("TEAM_BUILDER_DRAFT_RANKED_5x5"),
    TEAM_BUILDER_RANKED_SOLO("TEAM_BUILDER_RANKED_SOLO");
    
    final String value;
    
    RankedQueue(String code)
    {
        this.value = code;
    }
    
    /**
     * Returns a RankedQueue from the provided value
     *
     * @param value the lookup key
     * @return RankedQueue
     */
    public static Optional<RankedQueue> getFromValue(final String value)
    {
        return Stream.of(RankedQueue.values()).filter(t -> t.name().equalsIgnoreCase(value)).findFirst();
    }
    
    /**
     * The value used to map strings to objects
     *
     * @return String
     */
    public String getValue()
    {
        return value;
    }
}
