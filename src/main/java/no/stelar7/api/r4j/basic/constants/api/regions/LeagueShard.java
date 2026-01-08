package no.stelar7.api.r4j.basic.constants.api.regions;

import com.google.gson.JsonPrimitive;
import no.stelar7.api.r4j.basic.constants.types.*;
import no.stelar7.api.r4j.basic.exceptions.APIEnumNotUpToDateException;

import java.util.*;
import java.util.stream.Stream;

public enum LeagueShard implements CodedEnum, RealmSpesificEnum
{
    /**
     * Unknown platform, used for bots in participant identities
     */
    UNKNOWN("", "", "unknown"),
    /**
     * BRAZIL platform.
     */
    BR1("BR1", "br"),
    /**
     * Europe Nordic &amp; East platform.
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
    SG2("SG2", "sg"),
    /**
     * Philippines platform
     */
    PH2("PH2", "ph"),
    /**
     * Indonesia platform
     */
    ID1("ID1", "id"),
    /**
     * Vietnam platform
     */
    VN2("VN2", "vn"),
    /**
     * Thailand platform
     */
    TH2("TH2", "th"),
    /**
     * Taiwan platform
     */
    TW2("TW2", "tw"),
    /**
     * Middle East platform
     */
    ME1("ME1", "me");
    
    
    private String[] keys;
    
    LeagueShard(String... s)
    {
        this.keys = s;
    }
    
    /**
     * Returns a Platform from the provided code
     *
     * @param code the lookup key
     * @return Platform from code
     */
    public Optional<LeagueShard> getFromCode(final String code)
    {
        return fromString(code);
    }
    
    @Override
    public String prettyName()
    {
        switch (this)
        {
            case UNKNOWN:
                return "Unknown";
            case BR1:
                return "Brazil";
            case EUN1:
                return "Europe Nordic & East";
            case EUW1:
                return "Europe West";
            case JP1:
                return "Japan";
            case KR:
                return "Korea";
            case LA1:
                return "Latin America North";
            case LA2:
                return "Latin America South";
            case NA1:
                return "North America";
            case OC1:
                return "Oceania";
            case TR1:
                return "Turkey";
            case RU:
                return "Russia";
            case PBE1:
                return "Public Beta Environment";
            case SG2:
                return "Singapore";
            case PH2:
                return "Philippines";
            case ID1:
                return "Indonesia";
            case VN2:
                return "Vietnam";
            case TH2:
                return "Thailand";
            case TW2:
                return "Taiwan";
            case ME1:
                return "Middle East";
            default:
                return "This enum does not have a pretty name";
        }
    }
    
    public static Optional<LeagueShard> fromString(final String code)
    {
        return Stream.of(LeagueShard.values()).filter(t -> Stream.of(t.keys).anyMatch(s -> s.equalsIgnoreCase(code))).findFirst();
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
    
    public RegionShard toRegionShard()
    {
        switch (this)
        {
            
            case UNKNOWN:
                return RegionShard.UNKNOWN;
            case BR1:
            case NA1:
            case LA1:
            case LA2:
                return RegionShard.AMERICAS;
            case EUN1:
            case EUW1:
            case TR1:
            case RU:
            case ME1:
                return RegionShard.EUROPE;
            case JP1:
            case KR:
                return RegionShard.ASIA;
            case ID1:
            case SG2:
            case PH2:
            case VN2:
            case TH2:
            case TW2:
            case OC1:
                return RegionShard.SEA;
            case PBE1:
                return RegionShard.PBE;
            default:
                throw new APIEnumNotUpToDateException(RegionShard.class, new JsonPrimitive(this.toString()));
        }
    }

    public RegionShard toAccountRegionShard()
    {
        switch (this)
        {
            
            case UNKNOWN:
                return RegionShard.UNKNOWN;
            case BR1:
            case NA1:
            case LA1:
            case LA2:
                return RegionShard.AMERICAS;
            case EUN1:
            case EUW1:
            case TR1:
            case RU:
            case ME1:
                return RegionShard.EUROPE;
            case JP1:
            case KR:
            case ID1:
            case SG2:
            case PH2:
            case VN2:
            case TH2:
            case TW2:
            case OC1:
                return RegionShard.ASIA;
            case PBE1:
                return RegionShard.PBE;
            default:
                throw new APIEnumNotUpToDateException(RegionShard.class, new JsonPrimitive(this.toString()));
        }
    }
    
    @Override
    public String toString()
    {
        return this.getValue();
    }
    
    public static List<LeagueShard> getDefaultPlatforms()
    {
        return Arrays.asList(RU, KR, BR1, OC1, JP1, NA1, EUN1, EUW1, TR1, LA1, LA2);
    }
    
    public static List<LeagueShard> getSpectatorPlatforms()
    {
        return Arrays.asList(RU, KR, BR1, OC1, JP1, NA1, EUN1, EUW1, ME1);
    }
    
    public static List<LeagueShard> getGarenaPlatforms()
    {
        return Arrays.asList(ID1);
    }
    
    public static List<LeagueShard> getValidPlatforms()
    {
        return Arrays.asList(RU, KR, BR1, OC1, JP1, NA1, EUN1, EUW1, TR1, LA1, LA2, SG2, PH2, ID1, VN2, TH2, TW2, ME1);
    }
    
    public String[] getKeys()
    {
        return this.keys;
    }
}
