package no.stelar7.api.l4j8.basic.constants;

import java.util.stream.Stream;

public enum Season
{
    PRE_SEASON_2013("PRESEASON3"),
    PRE_SEASON_2014("PRESEASON2014"),
    PRE_SEASON_2015("PRESEASON2015"),

    SEASON_2013("SEASON3"),
    SEASON_2014("SEASON2014"),
    SEASON_2015("SEASON2015");

    public static Season getFromCode(final String code)
    {
        return Stream.of(Season.values()).filter(t -> t.code.equals(code)).findFirst().get();
    }

    String code;

    Season(final String code)
    {
        this.code = code;
    }

    public String getCode()
    {
        return this.code;
    }

}
