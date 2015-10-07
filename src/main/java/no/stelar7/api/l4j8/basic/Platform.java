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

    public static Platform getFromCode(String code)
    {
        return Stream.of(values()).filter(t -> t.name().equals(code)).findFirst().get();
    }
}
