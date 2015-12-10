package no.stelar7.api.l4j8.basic.constants;

import java.util.Optional;
import java.util.stream.Stream;

public enum Season
{
    PRE_SEASON_2013("PRESEASON3"),
    PRE_SEASON_2014("PRESEASON2014"),
    PRE_SEASON_2015("PRESEASON2015"),
    PRE_SEASON_2016("PRESEASON2016"),

    SEASON_2013("SEASON3"),
    SEASON_2014("SEASON2014"),
    SEASON_2015("SEASON2015"),
    SEASON_2016("SEASON2016");

    public static Optional<Season> getFromCode(final String code)
    {
        return Stream.of(Season.values()).filter(t -> t.code.equals(code)).findFirst();
    }

    String code;

    Season(final String code)
    {
        this.code = code;
    }

    /**
     * The code used to map strings to objects
     *
     * @return String
     */
    public String getCode()
    {
        return this.code;
    }

}
