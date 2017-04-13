package no.stelar7.api.l4j8.basic.constants.types;

import java.util.*;
import java.util.stream.*;

public enum SeasonType
{
    PRE_SEASON_2013("PRESEASON3"),
    PRE_SEASON_2014("PRESEASON2014"),
    PRE_SEASON_2015("PRESEASON2015"),
    PRE_SEASON_2016("PRESEASON2016"),
    PRE_SEASON_2017("PRESEASON2017"),
    
    SEASON_2013("SEASON3"),
    SEASON_2014("SEASON2014"),
    SEASON_2015("SEASON2015"),
    SEASON_2016("SEASON2016"),
    SEASON_2017("SEASON2017");
    
    private final String value;
    
    SeasonType(final String value)
    {
        this.value = value;
    }
    
    public static Optional<SeasonType> getFromCode(final String value)
    {
        return Stream.of(SeasonType.values()).filter(t -> t.value.equalsIgnoreCase(value)).findFirst();
    }
    
    /**
     * The value used to map strings to objects
     *
     * @return String
     */
    public String getValue()
    {
        return this.value;
    }
    
}
