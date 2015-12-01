package no.stelar7.api.l4j8.basic.constants;

import java.util.stream.Stream;

public enum LevelUpType
{
    EVOLVE,
    NORMAL;

    /**
     * Returns an LevelUpType from the provided code
     *
     * @param code
     *            the lookup key
     * @return LevelUpType
     */
    public static LevelUpType getFromCode(final String type)
    {
        return Stream.of(LevelUpType.values()).filter(t -> t.name().equals(type)).findFirst().get();
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
