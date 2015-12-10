package no.stelar7.api.l4j8.basic.constants;

import java.util.Optional;
import java.util.stream.Stream;

public enum MonsterType
{
    BARON_NASHOR,
    BLUE_GOLEM,
    DRAGON,
    RED_LIZARD,
    RIFTHERALD,
    VILEMAW;

    /**
     * Returns an MonsterType from the provided code
     *
     * @param code
     *            the lookup key
     * @return MonsterType
     */
    public static Optional<MonsterType> getFromCode(final String type)
    {
        return Stream.of(MonsterType.values()).filter(t -> t.name().equals(type)).findFirst();
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
