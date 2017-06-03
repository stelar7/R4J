package no.stelar7.api.l4j8.basic.constants.types;

import java.util.*;
import java.util.stream.*;

public enum RoleType
{
    
    /**
     * Duo queue?
     */
    DUO,
    /**
     * Unknown?
     */
    NONE,
    /**
     * Solo queue?
     */
    SOLO,
    /**
     * Duo queue marksman
     */
    DUO_CARRY,
    /**
     * Duo queue support
     */
    DUO_SUPPORT;
    
    /**
     * Returns a RoleType from the provided value
     *
     * @param code the lookup key
     * @return RoleType
     */
    public static Optional<RoleType> getFromCode(final String code)
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
