package no.stelar7.api.l4j8.basic;

import java.util.stream.Stream;

public enum Platform
{
    NA1,
    BR1,
    LA1,
    LA2,
    OC1,
    EUN1,
    TR1,
    RU,
    EUW1,
    KR;

    /**
     * Returns a Platform from the provided code
     * 
     * @param code
     *            the lookup key
     * @return Platform
     */
    public static Platform getFromCode(final String code)
    {
        return Stream.of(Platform.values()).filter(t -> t.name().equals(code)).findFirst().get();
    }
}
