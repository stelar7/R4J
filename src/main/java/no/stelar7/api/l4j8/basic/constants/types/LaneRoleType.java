package no.stelar7.api.l4j8.basic.constants.types;

import java.util.Optional;
import java.util.stream.Stream;

public enum LaneRoleType
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
     * The bottom lane adc
     */
    DUO_CARRY("DUO_CARRY"),
    /**
     * The bottom lane support
     */
    DUO_SUPPORT("DUO_SUPPORT");
    
    private final String[] keys;
    
    LaneRoleType(final String... keys)
    {
        this.keys = keys.clone();
    }
    
    /**
     * Returns a LaneRoleType from the provided value
     *
     * @param code the lookup key
     * @return LaneType
     */
    public static Optional<LaneRoleType> getFromCode(final String code)
    {
        return Stream.of(LaneRoleType.values()).filter(t -> Stream.of(t.keys).anyMatch(s -> s.equalsIgnoreCase(code))).findFirst();
    }
    
    public String getValue()
    {
        return this.name();
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
