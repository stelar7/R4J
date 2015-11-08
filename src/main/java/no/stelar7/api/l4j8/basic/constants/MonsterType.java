package no.stelar7.api.l4j8.basic.constants;

import java.util.stream.Stream;

public enum MonsterType
{
    BARON_NASHOR,
    BLUE_GOLEM,
    DRAGON,
    RED_LIZARD,
    VILEMAW;

    /**
     * Returns an MonsterType from the provided code
     *
     * @param code
     *            the lookup key
     * @return MonsterType
     */
    public static MonsterType getFromCode(final String type)
    {
        return Stream.of(MonsterType.values()).filter(t -> t.name().equals(type)).findFirst().get();
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
