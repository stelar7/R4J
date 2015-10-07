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

    public static GameMode getFromCode(String gameMode)
    {
        return Stream.of(values()).filter(t -> t.name().equals(gameMode)).findFirst().get();
    }
}
