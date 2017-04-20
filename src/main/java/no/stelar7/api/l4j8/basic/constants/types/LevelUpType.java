package no.stelar7.api.l4j8.basic.constants.types;

import java.util.*;
import java.util.stream.*;

public enum LevelUpType
{
    EVOLVE,
    NORMAL;
    
    /**
     * Returns an LevelUpType from the provided value
     *
     * @param type the type to check
     * @return LevelUpType
     */
    public static Optional<LevelUpType> getFromCode(final String type)
    {
        return Stream.of(LevelUpType.values()).filter(t -> t.name().equalsIgnoreCase(type)).findFirst();
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
