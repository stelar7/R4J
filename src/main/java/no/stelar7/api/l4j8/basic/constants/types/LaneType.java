package no.stelar7.api.l4j8.basic.constants.types;

import java.util.*;
import java.util.stream.*;

public enum LaneType
{
    /**
     * The middle lane
     */
    MID("MID", "MIDDLE", "MID_LANE"),
    
    /**
     * the top lane
     */
    TOP("TOP", "TOP_LANE"),
    
    /**
     * the jungle
     */
    JUNGLE("JUNGLE"),
    
    /**
     * The bottom lane
     */
    BOT("BOT", "BOTTOM", "BOT_LANE");
    
    final String[] keys;
    
    LaneType(final String... keys)
    {
        this.keys = keys;
    }
    
    /**
     * Returns a LaneType from the provided value
     *
     * @param code the lookup key
     * @return LaneType
     */
    public static Optional<LaneType> getFromCode(final String code)
    {
        return Stream.of(LaneType.values()).filter(t -> Stream.of(t.keys).anyMatch(s -> s.equalsIgnoreCase(code))).findFirst();
    }
    
    /**
     * The value used to map strings to objects
     *
     * @return String
     */
    public String[] getCodes()
    {
        return this.keys;
    }
}
