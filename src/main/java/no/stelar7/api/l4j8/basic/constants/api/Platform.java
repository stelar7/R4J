package no.stelar7.api.l4j8.basic.constants.api;

import no.stelar7.api.l4j8.basic.constants.types.CodedEnum;

import java.util.*;
import java.util.stream.Stream;

public enum Platform implements CodedEnum
{
    /**
     * Unknown platform, used for bots in participant identities
     */
    UNKNOWN(""),
    /**
     * BRAZIL platform.
     */
    BR1("BR1"),
    /**
     * Europe Nordic & East platform.
     */
    EUN1("EUN1"),
    /**
     * Europe West platform.
     */
    EUW1("EUW1"),
    /**
     * Japan platform.
     */
    JP1("JP1"),
    /**
     * Korea platform.
     */
    KR("KR"),
    /**
     * Latin America North platform.
     */
    LA1("LA1"),
    /**
     * Latin America South platform.
     */
    LA2("LA2"),
    /**
     * North America platform.
     */
    NA1("NA1", "NA"),
    /**
     * Oceania platform.
     */
    OC1("OC1"),
    /**
     * Turkey platform.
     */
    TR1("TR1"),
    /**
     * Russia platform.
     */
    RU("RU"),
    /**
     * Public Beta Environment platform.
     */
    PBE1("PBE1"),
    /**
     * Singapore platform
     */
    SG("SG"),
    /**
     * Philippines platform
     */
    PH("PH"),
    /**
     * Indonesia platform
     */
    ID1("ID1"),
    /**
     * Vietnam platform
     */
    VN("VN"),
    /**
     * Thailand platform
     */
    TH("TH"),
    /**
     * Taiwan platform
     */
    TW("TW");
    
    
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
    public Optional<Platform> getFromCode(final String code)
    {
        return fromString(code);
    }
    
    public static Optional<Platform> fromString(final String code)
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
