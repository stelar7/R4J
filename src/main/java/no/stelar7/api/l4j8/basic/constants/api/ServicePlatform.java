package no.stelar7.api.l4j8.basic.constants.api;

import java.util.*;
import java.util.stream.Stream;

public enum ServicePlatform
{
    /**
     * Unknown platform, used for bots in participant identities
     */
    UNKNOWN(""),
    /**
     * Americas platform.
     */
    AMERICAS("AMERICAS"),
    /**
     * Europe platform.
     */
    EUROPE("EUROPE"),
    /**
     * Asia platform.
     */
    ASIA("ASIA");
    
    private String key;
    
    ServicePlatform(String s)
    {
        this.key = s;
    }
    
    /**
     * Returns a Platform from the provided code
     *
     * @param code the lookup key
     * @return Platform from code
     */
    public static Optional<ServicePlatform> getFromCode(final String code)
    {
        return Stream.of(ServicePlatform.values()).filter(t -> t.key.equalsIgnoreCase(code)).findFirst();
    }
    
    
    /**
     * Used internaly in the api...
     *
     * @return the value
     */
    public String getValue()
    {
        return this.key;
    }
    
    @Override
    public String toString()
    {
        return this.key.toLowerCase(Locale.ENGLISH);
    }
}
