package no.stelar7.api.r4j.basic.constants.types.lol;

import no.stelar7.api.r4j.basic.constants.types.CodedEnum;

import java.util.*;
import java.util.stream.*;

public enum SeasonType implements CodedEnum
{
    PRE_SEASON_2013("PRESEASON3", 0),
    SEASON_2013("SEASON3", 1),
    PRE_SEASON_2014("PRESEASON2014", 2),
    SEASON_2014("SEASON2014", 3),
    PRE_SEASON_2015("PRESEASON2015", 4),
    SEASON_2015("SEASON2015", 5),
    PRE_SEASON_2016("PRESEASON2016", 6),
    SEASON_2016("SEASON2016", 7),
    PRE_SEASON_2017("PRESEASON2017", 8),
    SEASON_2017("SEASON2017", 9),
    PRE_SEASON_2018("PRESEASON2018", 10),
    SEASON_2018("SEASON2018", 11),
    PRE_SEASON_2019("PRESEASON2019", 12),
    SEASON_2019("SEASON2019", 13),
    PRE_SEASON_2020("PRESEASON2020", 14),
    SEASON_2020("SEASON2020", 15),
    PRE_SEASON_2021("PRESEASON2021", 16),
    ;
    
    private final String  value;
    private final Integer seasonId;
    
    SeasonType(final String value, final int seasonId)
    {
        this.value = value;
        this.seasonId = seasonId;
    }
    
    public Optional<SeasonType> getFromCode(final String value)
    {
        try
        {
            return getFromId(Integer.parseInt(value));
        } catch (NumberFormatException e)
        {
            return getFromString(value);
        }
    }
    
    @Override
    public String prettyName()
    {
        switch (this)
        {
            case PRE_SEASON_2013:
                return "Preseason 2013";
            case SEASON_2013:
                return "Season 2013";
            case PRE_SEASON_2014:
                return "Preseason 2014";
            case SEASON_2014:
                return "Season 2014";
            case PRE_SEASON_2015:
                return "Preseason 2015";
            case SEASON_2015:
                return "Season 2015";
            case PRE_SEASON_2016:
                return "Preseason 2016";
            case SEASON_2016:
                return "Season 2016";
            case PRE_SEASON_2017:
                return "Preseason 2017";
            case SEASON_2017:
                return "Season 2017";
            case PRE_SEASON_2018:
                return "Preseason 2018";
            case SEASON_2018:
                return "Season 2018";
            case PRE_SEASON_2019:
                return "Preseason 2019";
            case SEASON_2019:
                return "Season 2019";
            case PRE_SEASON_2020:
                return "Preseason 2020";
            case SEASON_2020:
                return "Season 2020";
            default:
                return "This enum does not have a pretty name";
        }
    }
    
    public static Optional<SeasonType> getFromString(final String value)
    {
        return Stream.of(SeasonType.values()).filter(t -> t.value.equalsIgnoreCase(value)).findFirst();
    }
    
    public static Optional<SeasonType> getFromId(final Integer value)
    {
        return Stream.of(SeasonType.values()).filter(t -> t.seasonId.equals(value)).findFirst();
    }
    
    /**
     * The value used to map strings to objects
     *
     * @return String
     */
    public Integer getValue()
    {
        return this.seasonId;
    }
    
}
