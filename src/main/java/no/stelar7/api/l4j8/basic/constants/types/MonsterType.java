package no.stelar7.api.l4j8.basic.constants.types;

import java.util.*;
import java.util.stream.*;

public enum MonsterType implements CodedEnum
{
    BARON_NASHOR,
    BLUE_GOLEM,
    DRAGON,
    RED_LIZARD,
    RIFTHERALD,
    VILEMAW;
    
    /**
     * Returns an MonsterType from the provided value
     *
     * @param type the type to check
     * @return MonsterType
     */
    public Optional<MonsterType> getFromCode(final String type)
    {
        return Stream.of(MonsterType.values()).filter(t -> t.name().equalsIgnoreCase(type)).findFirst();
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
