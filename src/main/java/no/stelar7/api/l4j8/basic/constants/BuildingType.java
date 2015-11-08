package no.stelar7.api.l4j8.basic.constants;

import java.util.stream.Stream;

public enum BuildingType

{
    INHIBITOR_BUILDING,
    TOWER_BUILDING;
    
    /**
     * Returns an BuildingType from the provided code
     *
     * @param code
     *            the lookup key
     * @return BuildingType
     */
    public static BuildingType getFromCode(final String type)
    {
        return Stream.of(BuildingType.values()).filter(t -> t.name().equals(type)).findFirst().get();
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
