package no.stelar7.api.l4j8.basic.constants;

import java.util.stream.Stream;

public enum GameMode
{
    /**
     * Classic Summoner's Rift and Twisted Treeline games
     */
    CLASSIC,
    /**
     * Dominion/Crystal Scar games
     */
    ODIN,
    /**
     * ARAM games
     */
    ARAM,
    /**
     * Tutorial games
     */
    TUTORIAL,
    /**
     * One for All games
     */
    ONEFORALL,
    /**
     * Ascension games
     */
    ASCENSION,
    /**
     * Snowdown Showdown games
     */
    FIRSTBLOOD,
    /**
     * King Poro games
     */
    KINGPORO;

    /**
     * Returns a GameMode from the provided code
     *
     * @param code
     *            the lookup key
     * @return GameMode
     */
    public static GameMode getFromCode(final String gameMode)
    {
        return Stream.of(GameMode.values()).filter(t -> t.name().equals(gameMode)).findFirst().get();
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
