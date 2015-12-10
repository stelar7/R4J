package no.stelar7.api.l4j8.basic.constants;

import java.util.Optional;
import java.util.stream.Stream;

public enum Lane
{
    /**
     * The middle lane
     */
    MID("MID", "MIDDLE", "MID_LANE"),

    /**
     * the top lane
     */
    TOP("TOP", "TOP_LANE"),

    /**
     * the jungle
     */
    JUNGLE,

    /**
     * The bottom lane
     */
    BOT("BOT", "BOTTOM", "BOT_LANE");

    public static Optional<Lane> getFromCode(final String code)
    {
        return Stream.of(Lane.values()).filter(t -> Stream.of(t.keys).anyMatch(s -> s.equalsIgnoreCase(code))).findFirst();
    }

    /**
     * Returns a Lane from the provided code
     *
     * @param code
     *            the lookup key
     * @return Lane
     */

    String[] keys;

    Lane(final String... keys)
    {
        this.keys = keys;
    }

    /**
     * The code used to map strings to objects
     *
     * @return String
     */
    public String[] getCodes()
    {
        return this.keys;
    }
}
