package no.stelar7.api.l4j8.basic.constants.types;

import java.util.*;
import java.util.stream.*;

public enum GameModeType
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
    SIEGE,
    /**
     * Blood Hunt Assassin games
     */
    ASSASSINATE,
    /**
     * All Random Summoner's Rift games
     */
    ARSR,
    /**
     * Darkstar games
     */
    DARKSTAR,
    /**
     * Invasion mode
     */
    STARGUARDIAN,
    /**
     * URF mode
     */
    URF,
    /**
     * Doombots mode
     */
    DOOMBOTSTEEMO,
    /**
     * Overcharge mode?
     */
    OVERCHARGE;
    
    /**
     * Returns a GameModeType from the provided value
     *
     * @param gameMode the mode to check
     * @return GameModeType
     */
    public static Optional<GameModeType> getFromCode(final String gameMode)
    {
        return Stream.of(GameModeType.values()).filter(t -> t.name().equalsIgnoreCase(gameMode)).findFirst();
    }
    
    
    /**
     * Used internaly in the api...
     *
     * @return the value
     */
    public String getValue()
    {
        return this.name();
    }
    
    /**
     * The value used to map strings to objects
     *
     * @return String
     */
    public String getCode()
    {
        return this.name();
    }
}
