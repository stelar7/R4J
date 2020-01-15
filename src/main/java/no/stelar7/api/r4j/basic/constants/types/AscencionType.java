package no.stelar7.api.r4j.basic.constants.types;

import java.util.*;
import java.util.stream.*;

public enum AscencionType implements CodedEnum
{
    CHAMPION_ASCENDED,
    CLEAR_ASCENDED,
    MINION_ASCENDED;
    
    public Optional<AscencionType> getFromCode(final String type)
    {
        return Stream.of(AscencionType.values()).filter(t -> t.name().equalsIgnoreCase(type)).findFirst();
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
    
    @Override
    public String prettyName()
    {
        switch (this)
        {
            case CHAMPION_ASCENDED:
                return "Champion Ascended";
            case CLEAR_ASCENDED:
                return "Clear Ascended";
            case MINION_ASCENDED:
                return "Minion Ascended";
            default:
                return "This enum does not have a pretty name";
        }
    }
}
