package no.stelar7.api.l4j8.basic.constants;

import java.util.*;
import java.util.stream.*;

public enum BuildingType

{
    INHIBITOR_BUILDING,
    TOWER_BUILDING;
    
    /**
     * Returns an BuildingType from the provided value
     *
     * @return BuildingType
     */
    public static Optional<BuildingType> getFromCode(final String type)
    {
        return Stream.of(BuildingType.values()).filter(t -> t.name().equalsIgnoreCase(type)).findFirst();
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
