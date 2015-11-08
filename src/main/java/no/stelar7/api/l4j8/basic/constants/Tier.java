package no.stelar7.api.l4j8.basic.constants;

import java.util.stream.Stream;

public enum Tier
{
    CHALLENGER,
    MASTER,
    DIAMOND,
    PLATINUM,
    GOLD,
    SILVER,
    BRONZE,
    UNRANKED;

    /**
     * Returns an Tier from the provided code
     *
     * @param code
     *            the lookup key
     * @return Tier
     */
    public static Tier getFromCode(final String type)
    {
        return Stream.of(Tier.values()).filter(t -> t.name().equals(type)).findFirst().get();
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
