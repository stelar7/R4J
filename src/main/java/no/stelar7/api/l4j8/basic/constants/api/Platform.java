package no.stelar7.api.l4j8.basic.constants.api;

import java.util.*;
import java.util.stream.*;

public enum Platform
{
    /**
     * BR platform.
     */
    BR1,
    /**
     * EUNE platform.
     */
    EUN1,
    /**
     * EUW platform.
     */
    EUW1,
    /**
     * JP platform.
     */
    JP1,
    /**
     * KR platform.
     */
    KR,
    /**
     * LAN platform.
     */
    LA1,
    /**
     * LAS platform.
     */
    LA2,
    /**
     * NA platform.
     */
    NA1,
    /**
     * OC platform.
     */
    OC1,
    /**
     * TR platform.
     */
    TR1,
    /**
     * RU platform.
     */
    RU,
    /**
     * PBE platform.
     */
    PBE1,
    /**
     * Americas platform.
     */
    AMERICAS,
    /**
     * Europe platform.
     */
    EUROPE,
    /**
     * Asia platform.
     */
    ASIA;
    
    /**
     * Returns a Platform from the provided code
     *
     * @param code the lookup key
     * @return Platform from code
     */
    public static Optional<Platform> getFromCode(final String code)
    {
        return Stream.of(Platform.values()).filter(t -> t.name().equals(code)).findFirst();
    }
    
    
    /**
     * Used internaly in the api...
     *
     * @return the value
     */
    public String getValue()
    {
        return this.name();
    }
    
    @Override
    public String toString()
    {
        return this.name().toLowerCase(Locale.ENGLISH);
    }
}
