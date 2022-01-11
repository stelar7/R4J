package no.stelar7.api.r4j.basic.constants.api.regions;

import no.stelar7.api.r4j.basic.constants.types.RealmSpesificEnum;

import java.util.Optional;
import java.util.stream.Stream;

public enum RuneterraShard implements RealmSpesificEnum
{
    /**
     * Unknown platform
     */
    UNKNOWN("", ""),
    /**
     * North america
     */
    AMERICAS("AMERICAS", "americas"),
    /**
     * Europe
     */
    EUROPE("EUROPE", "europe"),
    /**
     * Asia
     */
    @Deprecated
    ASIA("ASIA", "asia"),
    /**
     * SEA
     */
    @Deprecated
    SEA("SEA", "sea"),
    /**
     * Asia-Pacific
     */
    APAC("SEA", "sea"),;
    
    private final String[] key;
    
    RuneterraShard(String... s)
    {
        this.key = s;
    }
    
    /**
     * Returns a Platform from the provided code
     *
     * @param code the lookup key
     * @return Platform from code
     */
    public static Optional<RuneterraShard> getFromCode(final String code)
    {
        return Stream.of(RuneterraShard.values()).filter(t -> t.key[0].equalsIgnoreCase(code)).findFirst();
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
