package no.stelar7.api.l4j8.basic.constants.types;

import java.util.*;
import java.util.stream.*;

public enum PointType implements CodedEnum
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
    public Optional<PointType> getFromCode(final String type)
    {
        return Stream.of(PointType.values()).filter(t -> t.name().equalsIgnoreCase(type)).findFirst();
    }
    
    @Override
    public String prettyName()
    {
        switch (this) {
            case POINT_A:
                return "Point A";
            case POINT_B:
                return "Point B";
            case POINT_C:
                return "Point C";
            case POINT_D:
                return "Point D";
            case POINT_E:
                return "Point E";
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
