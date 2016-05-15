package no.stelar7.api.l4j8.basic.constants;

import java.util.*;
import java.util.stream.*;

public enum Point
{
    POINT_A,
    POINT_B,
    POINT_C,
    POINT_D,
    POINT_E;

    /**
     * Returns an Point from the provided code
     *
     * @param code
     *            the lookup key
     * @return Point
     */
    public static Optional<Point> getFromCode(final String type)
    {
        return Stream.of(Point.values()).filter(t -> t.name().equals(type)).findFirst();
    }

    /**
     * The code used to map strings to objects
     *
     * @return String
     */
    public String getCode()
    {
        return this.name();
    }
}
