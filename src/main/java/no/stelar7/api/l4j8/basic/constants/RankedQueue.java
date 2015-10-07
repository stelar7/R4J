package no.stelar7.api.l4j8.basic.constants;

import java.util.stream.Stream;

public enum RankedQueue
{
    RANKED_SOLO_5X5,
    RANKED_TEAM_5X5,
    RANKED_TEAM_3X3;

    public static RankedQueue getFromCode(String code)
    {
        return Stream.of(values()).filter(t -> t.name().equals(code)).findFirst().get();
    }

    public String getCode()
    {
        return this.name();
    }
}
