package no.stelar7.api.l4j8.basic.constants.types;

import java.util.Optional;
import java.util.stream.Stream;

public enum LeaguePositionType implements CodedEnum
{
    /**
     * MASTER+ rank gets this for some reason?
     */
    APEX,
    /**
     * Top lane
     */
    TOP,
    /**
     * Jungle
     */
    JUNGLE,
    /**
     * Mid lane
     */
    MIDDLE,
    /**
     * Bottom lane
     */
    BOTTOM,
    /**
     * Support
     */
    UTILITY,
    /**
     * Not a positional queue
     */
    NONE;
    
    /**
     * Returns an LeaguePositionType from the provided value
     *
     * @param type the type to check
     * @return LeaguePositionType
     */
    public Optional<LeaguePositionType> getFromCode(final String type)
    {
        return Stream.of(LeaguePositionType.values()).filter(t -> t.name().equalsIgnoreCase(type)).findFirst();
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
