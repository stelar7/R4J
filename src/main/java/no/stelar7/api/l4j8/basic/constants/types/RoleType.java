package no.stelar7.api.l4j8.basic.constants.types;

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
     * When there are 2 participants in a lane, this person farmed
     */
    DUO_CARRY,
    /**
     * When there are 2 participants in a lane, this parson did not farm
     */
    DUO_SUPPORT;
    
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
