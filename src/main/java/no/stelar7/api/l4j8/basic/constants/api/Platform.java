package no.stelar7.api.l4j8.basic.constants.api;

import java.util.*;
import java.util.stream.Stream;

public enum Platform
{
    /**
     * Unknown platform, used for bots in participant identities
     */
    UNKNOWN(""),
    /**
     * BR platform.
     */
    BR1("BR1"),
    /**
     * EUNE platform.
     */
    EUN1("EUN1"),
    /**
     * EUW platform.
     */
    EUW1("EUW1"),
    /**
     * JP platform.
     */
    JP1("JP1"),
    /**
     * KR platform.
     */
    KR("KR"),
    /**
     * LAN platform.
     */
    LA1("LA1"),
    /**
     * LAS platform.
     */
    LA2("LA2"),
    /**
     * NA platform.
     */
    NA1("NA1", "NA"),
    /**
     * OC platform.
     */
    OC1("OC1"),
    /**
     * TR platform.
     */
    TR1("TR1"),
    /**
     * RU platform.
     */
    RU("RU"),
    /**
     * PBE platform.
     */
    PBE1("PBE1");
    
    
    private String[] keys;
    
    Platform(String... s)
    {
        this.keys = s;
    }
    
    /**
     * Returns a Platform from the provided code
     *
     * @param code the lookup key
     * @return Platform from code
     */
    public static Optional<Platform> getFromCode(final String code)
    {
        return Stream.of(Platform.values()).filter(t -> Stream.of(t.keys).anyMatch(s -> s.equalsIgnoreCase(code))).findFirst();
    }
    
    
    /**
     * Used internaly in the api...
     *
     * @return the value
     */
    public String getValue()
    {
        return this.keys[0];
    }
    
    @Override
    public String toString()
    {
        return this.keys[0].toLowerCase(Locale.ENGLISH);
    }
}
