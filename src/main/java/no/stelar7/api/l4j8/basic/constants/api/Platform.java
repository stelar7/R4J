package no.stelar7.api.l4j8.basic.constants.api;

import no.stelar7.api.l4j8.basic.constants.types.*;

import java.util.*;
import java.util.stream.Stream;

public enum Platform implements CodedEnum, RealmSpesificEnum
{
    /**
     * Unknown platform, used for bots in participant identities
     */
    UNKNOWN("", ""),
    /**
     * BRAZIL platform.
     */
    BR1("BR1", "br"),
    /**
     * Europe Nordic & East platform.
     */
    EUN1("EUN1", "eune"),
    /**
     * Europe West platform.
     */
    EUW1("EUW1", "euw"),
    /**
     * Japan platform.
     */
    JP1("JP1", "jp"),
    /**
     * Korea platform.
     */
    KR("KR", "kr"),
    /**
     * Latin America North platform.
     */
    LA1("LA1", "lan", "WAS_LA1"),
    /**
     * Latin America South platform.
     */
    LA2("LA2", "las"),
    /**
     * North America platform.
     */
    NA1("NA1", "na"),
    /**
     * Oceania platform.
     */
    OC1("OC1", "oc"),
    /**
     * Turkey platform.
     */
    TR1("TR1", "tr"),
    /**
     * Russia platform.
     */
    RU("RU", "ru"),
    /**
     * Public Beta Environment platform.
     */
    PBE1("PBE1", "pbe"),
    /**
     * Singapore platform
     */
    SG("SG", "sg"),
    /**
     * Philippines platform
     */
    PH("PH", "ph"),
    /**
     * Indonesia platform
     */
    ID1("ID1", "id"),
    /**
     * Vietnam platform
     */
    VN("VN", "vn"),
    /**
     * Thailand platform
     */
    TH("TH", "th"),
    /**
     * Taiwan platform
     */
    TW("TW", "tw");
    
    
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
    
    /**
     * Used internaly in the api...
     *
     * @return the value
     */
    @Override
    public String getRealmValue()
    {
        return this.keys[1];
    }
    
    @Override
    public String toString()
    {
        return this.getValue();
    }
    
    public static List<Platform> getDefaultPlatforms()
    {
        return Arrays.asList(RU, KR, BR1, OC1, JP1, NA1, EUN1, EUW1, TR1, LA1, LA2);
    }
    
    public static List<Platform> getSpectatorPlatforms()
    {
        return Arrays.asList(RU, KR, BR1, OC1, JP1, NA1, EUN1, EUW1);
    }
    
    public static List<Platform> getGarenaPlatforms()
    {
        return Arrays.asList(SG, PH, ID1, VN, TH, TW);
    }
    
    public static List<Platform> getValidPlatforms()
    {
        return Arrays.asList(RU, KR, BR1, OC1, JP1, NA1, EUN1, EUW1, TR1, LA1, LA2, SG, PH, ID1, VN, TH, TW);
    }
    
}
