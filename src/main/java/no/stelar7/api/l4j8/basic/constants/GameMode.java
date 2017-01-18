package no.stelar7.api.l4j8.basic.constants;

import java.util.*;
import java.util.stream.*;

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
    KINGPORO,
    /**
     * Nexus Siege games
     */
    SIEGE,;
    
    /**
     * Returns a GameMode from the provided code
     *
     * @return GameMode
     */
    public static Optional<GameMode> getFromCode(final String gameMode)
    {
        return Stream.of(GameMode.values()).filter(t -> t.name().equalsIgnoreCase(gameMode)).findFirst();
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
