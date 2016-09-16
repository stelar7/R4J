package no.stelar7.api.l4j8.basic.constants;

import java.util.Optional;
import java.util.stream.Stream;

public enum RankedQueue
{
    RANKED_SOLO_5X5("RANKED_SOLO_5x5"),
    RANKED_TEAM_5X5("RANKED_TEAM_5x5"),
    RANKED_TEAM_3X3("RANKED_TEAM_3x3");

    String code;

    RankedQueue(String code)
    {
        this.code = code;
    }

    /**
     * Returns a RankedQueue from the provided code
     *
     * @param code the lookup key
     * @return RankedQueue
     */
    public static Optional<RankedQueue> getFromCode(final String code)
    {
        return Stream.of(RankedQueue.values()).filter(t -> t.name().equalsIgnoreCase(code)).findFirst();
    }

    /**
     * The code used to map strings to objects
     *
     * @return String
     */
    public String getCode()
    {
        return code;
    }
}
