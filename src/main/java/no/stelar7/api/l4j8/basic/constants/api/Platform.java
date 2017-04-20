package no.stelar7.api.l4j8.basic.constants.api;

import java.util.*;
import java.util.stream.*;

public enum Platform
{
    BR1,
    EUN1,
    EUW1,
    KR,
    LA1,
    LA2,
    NA1,
    OC1,
    TR1,
    RU,
    PBE1;
    
    /**
     * Returns a Platform from the provided code
     *
     * @param code the lookup key
     * @return Platform
     */
    public static Optional<Platform> getFromCode(final String code)
    {
        return Stream.of(Platform.values()).filter(t -> t.name().equals(code)).findFirst();
    }
    
    
    @Override
    public String toString()
    {
        return this.name().toLowerCase(Locale.ENGLISH);
    }
}
