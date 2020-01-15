package no.stelar7.api.r4j.basic.constants.types;

import java.util.*;
import java.util.stream.*;

public enum TowerType implements CodedEnum
{
    BASE_TURRET,
    FOUNTAIN_TURRET,
    INNER_TURRET,
    NEXUS_TURRET,
    OUTER_TURRET,
    UNDEFINED_TURRET;
    
    /**
     * Returns an TowerType from the provided value
     *
     * @param type the type to check
     * @return TowerType
     */
    public Optional<TowerType> getFromCode(final String type)
    {
        return Stream.of(TowerType.values()).filter(t -> t.name().equalsIgnoreCase(type)).findFirst();
    }
    
    @Override
    public String prettyName()
    {
        switch (this)
        {
            case BASE_TURRET:
                return "Base Turret";
            case FOUNTAIN_TURRET:
                return "Fountain Turret";
            case INNER_TURRET:
                return "Inner Turret";
            case NEXUS_TURRET:
                return "Nexus Turret";
            case OUTER_TURRET:
                return "Outer Turret";
            case UNDEFINED_TURRET:
                return "Undefined Turret";
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
