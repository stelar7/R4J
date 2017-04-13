package no.stelar7.api.l4j8.basic.constants;

import java.util.*;
import java.util.stream.*;

public enum WardType
{
    BLUE_TRINKET,
    SIGHT_WARD,
    TEEMO_MUSHROOM,
    UNDEFINED,
    VISION_WARD,
    YELLOW_TRINKET,
    YELLOW_TRINKET_UPGRADE;
    
    /**
     * Returns an WardType from the provided value
     *
     * @return WardType
     */
    public static Optional<WardType> getFromCode(final String type)
    {
        return Stream.of(WardType.values()).filter(t -> t.name().equalsIgnoreCase(type)).findFirst();
    }
    
    /**
     * The value used to map strings to objects
     *
     * @return String
     */
    public String getCode()
    {
        return this.name();
    }
}
