package no.stelar7.api.l4j8.basic.constants;

import java.util.stream.Stream;

public enum TowerType
{
    BASE_TURRET,
    FOUNTAIN_TURRET,
    INNER_TURRET,
    NEXUS_TURRET,
    OUTER_TURRET,
    UNDEFINED_TURRET;

    /**
     * Returns an TowerType from the provided code
     *
     * @param code
     *            the lookup key
     * @return TowerType
     */
    public static TowerType getFromCode(final String type)
    {
        return Stream.of(TowerType.values()).filter(t -> t.name().equals(type)).findFirst().get();
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
