package no.stelar7.api.l4j8.basic.constants;

import java.util.stream.Stream;

public enum Lane
{
    /**
     * Short for MIDDLE
     */
    MID,
    /**
     * The middle lane
     */
    MIDDLE,
    /**
     * the top lane
     */
    TOP,
    /**
     * the jungle
     */
    JUNGLE,
    /**
     * short for BOTTOM
     */
    BOT,
    /**
     * the bottom lane
     */
    BOTTOM;

    public static Lane getFromCode(final String code)
    {
        return Stream.of(Lane.values()).filter(t -> t.name().equals(code)).findFirst().get();
    }
}
