package no.stelar7.api.r4j.basic.constants.api.regions;

import no.stelar7.api.r4j.basic.constants.types.RealmSpesificEnum;

import java.util.Optional;
import java.util.stream.Stream;

public enum ValorantShard implements RealmSpesificEnum
{
    /**
     * Unknown platform
     */
    UNKNOWN("", ""),
    /**
     * North america
     */
    NA("NA", "na"),
    /**
     * North america
     */
    PBE1("PBE1", "pbe1"),
    /**
     * Europe
     */
    EU("EU", "eu"),
    /**
     * Brazil
     */
    BR("BR", "br"),
    /**
     * Korea
     */
    KR("KR", "kr"),
    /**
     * Latin america
     */
    LATAM("LATAM", "latam"),
    /**
     * Asia southpasific
     */
    APAC("APAC", "apac");
    
    private final String[] key;
    
    ValorantShard(String... s)
    {
        this.key = s;
    }
    
    /**
     * Returns a Platform from the provided code
     *
     * @param code the lookup key
     * @return Platform from code
     */
    public static Optional<ValorantShard> getFromCode(final String code)
    {
        return Stream.of(ValorantShard.values()).filter(t -> t.key[0].equalsIgnoreCase(code)).findFirst();
    }
    
    
    /**
     * Used internaly in the api...
     *
     * @return the value
     */
    public String getValue()
    {
        return this.key[0];
    }
    
    /**
     * Used internaly in the api...
     *
     * @return the value
     */
    @Override
    public String getRealmValue()
    {
        return this.key[1];
    }
    
    @Override
    public String toString()
    {
        return this.getValue();
    }
}
