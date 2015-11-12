package no.stelar7.api.l4j8.basic;

import java.util.stream.Stream;

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
     * @param code
     *            the lookup key
     * @return Platform
     */
    public static Platform getFromCode(final String code)
    {
        return Stream.of(Platform.values()).filter(t -> t.name().equals(code)).findFirst().get();
    }

    /**
     * The code used to map strings to objects
     *
     * @return String
     */
    public String getCode()
    {
        return this.name();
    }
}
