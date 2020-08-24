package no.stelar7.api.r4j.basic.constants.types.lol;

import no.stelar7.api.r4j.basic.constants.types.CodedEnum;

import java.util.*;
import java.util.stream.*;

public enum LevelUpType implements CodedEnum
{
    EVOLVE,
    NORMAL;
    
    /**
     * Returns an LevelUpType from the provided value
     *
     * @param type the type to check
     * @return LevelUpType
     */
    public Optional<LevelUpType> getFromCode(final String type)
    {
        return Stream.of(LevelUpType.values()).filter(t -> t.name().equalsIgnoreCase(type)).findFirst();
    }
    
    @Override
    public String prettyName()
    {
        switch (this) {
            case EVOLVE:
                return "Evolve";
            case NORMAL:
                return "Normal";
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
