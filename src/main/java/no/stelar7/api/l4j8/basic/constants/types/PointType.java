package no.stelar7.api.l4j8.basic.constants.types;

import java.util.*;
import java.util.stream.*;

public enum PointType
{
    POINT_A,
    POINT_B,
    POINT_C,
    POINT_D,
    POINT_E;
    
    /**
     * Returns an PointType from the provided value
     *
     * @param type the type to check
     * @return PointType
     */
    public static Optional<PointType> getFromCode(final String type)
    {
        return Stream.of(PointType.values()).filter(t -> t.name().equalsIgnoreCase(type)).findFirst();
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
