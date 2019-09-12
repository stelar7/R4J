package no.stelar7.api.l4j8.basic.constants.types;

import java.util.*;
import java.util.stream.*;

public enum WardType implements CodedEnum
{
    
    BLUE_TRINKET,
    CONTROL_WARD,
    SIGHT_WARD,
    TEEMO_MUSHROOM,
    UNDEFINED,
    VISION_WARD,
    YELLOW_TRINKET,
    YELLOW_TRINKET_UPGRADE;
    
    /**
     * Returns an WardType from the provided value
     *
     * @param type the type to check
     * @return WardType
     */
    public Optional<WardType> getFromCode(final String type)
    {
        return Stream.of(WardType.values()).filter(t -> t.name().equalsIgnoreCase(type)).findFirst();
    }
    
    @Override
    public String prettyName()
    {
        switch (this) {
            case BLUE_TRINKET:
                return "Blue Trinket";
            case CONTROL_WARD:
                return "Control Ward";
            case SIGHT_WARD:
                return "Sight Ward";
            case TEEMO_MUSHROOM:
                return "Teemo Mushroom";
            case UNDEFINED:
                return "Undefined";
            case VISION_WARD:
                return "Vision Ward";
            case YELLOW_TRINKET:
                return "Yellow Trinket";
            case YELLOW_TRINKET_UPGRADE:
                return "Yellow Trinket Upgrade";
            default:
                return "This enum does not have a pretty name";
        }
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
