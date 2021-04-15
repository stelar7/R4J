package no.stelar7.api.r4j.basic.constants.types.lol;

import no.stelar7.api.r4j.basic.constants.types.CodedEnum;

import java.util.*;
import java.util.stream.*;

public enum RoleType implements CodedEnum
{
    
    /**
     * When there are 2 participants in a lane, both farming
     */
    DUO,
    /**
     * No set role (Jungler)
     */
    NONE,
    /**
     * Someone alone in a lane
     */
    SOLO,
    /**
     * When there are 2 participants in a lane, this character farmed
     */
    DUO_CARRY,
    /**
     * When there are 2 participants in a lane, this character did not farm
     */
    DUO_SUPPORT,
    /**
     * This character farmed
     */
    CARRY,
    /**
     * This character did not farm
     */
    SUPPORT,
    ;
    
    /**
     * Returns a RoleType from the provided value
     *
     * @param code the lookup key
     * @return RoleType
     */
    public Optional<RoleType> getFromCode(final String code)
    {
        return Stream.of(RoleType.values()).filter(t -> t.name().equalsIgnoreCase(code)).findFirst();
    }
    
    @Override
    public String prettyName()
    {
        switch (this)
        {
            case DUO:
                return "Duo";
            case NONE:
                return "None";
            case SOLO:
                return "Solo";
            case DUO_CARRY:
                return "Duo Carry";
            case DUO_SUPPORT:
                return "Duo Support";
            default:
                return "This enum does not have a pretty name";
        }
    }
    
    /**
     * The value used to map strings to objects
     *
     * @return String
     */
    public String getValue()
    {
        return this.name();
    }
}
