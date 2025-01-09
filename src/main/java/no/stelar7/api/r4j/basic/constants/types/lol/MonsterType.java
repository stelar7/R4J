package no.stelar7.api.r4j.basic.constants.types.lol;

import no.stelar7.api.r4j.basic.constants.types.CodedEnum;

import java.util.*;
import java.util.stream.*;

public enum MonsterType implements CodedEnum
{
    ATAKHAN,
    BARON_NASHOR,
    BLUE_GOLEM,
    DRAGON,
    HORDE,
    RED_LIZARD,
    RIFTHERALD,
    VILEMAW,
    ;
    
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
    
    @Override
    public String prettyName()
    {
        switch (this)
        {
            case BARON_NASHOR:
                return "Baron Nashor";
            case BLUE_GOLEM:
                return "Blue Golem";
            case DRAGON:
                return "Dragon";
            case RED_LIZARD:
                return "Red Lizard";
            case RIFTHERALD:
                return "Rift Herald";
            case VILEMAW:
                return "Vilemaw";
            case HORDE:
                return "Voidgrub";
            case ATAKHAN:
                return "Atakhan";
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
