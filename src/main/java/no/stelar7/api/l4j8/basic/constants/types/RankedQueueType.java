package no.stelar7.api.l4j8.basic.constants.types;

import java.util.*;
import java.util.stream.*;

public enum RankedQueueType
{
    RANKED_SOLO_5X5("RANKED_SOLO_5x5"),
    RANKED_TEAM_5X5("RANKED_TEAM_5x5"),
    RANKED_TEAM_3X3("RANKED_TEAM_3x3"),
    RANKED_FLEX_SR("RANKED_FLEX_SR"),
    TEAM_BUILDER_DRAFT_RANKED_5X5("TEAM_BUILDER_DRAFT_RANKED_5x5"),
    TEAM_BUILDER_RANKED_SOLO("TEAM_BUILDER_RANKED_SOLO");
    
    private final String value;
    
    RankedQueueType(String code)
    {
        this.value = code;
    }
    
    /**
     * Returns a RankedQueueType from the provided value
     *
     * @param value the lookup key
     * @return RankedQueueType
     */
    public static Optional<RankedQueueType> getFromCode(final String value)
    {
        return Stream.of(RankedQueueType.values()).filter(t -> t.name().equalsIgnoreCase(value)).findFirst();
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
