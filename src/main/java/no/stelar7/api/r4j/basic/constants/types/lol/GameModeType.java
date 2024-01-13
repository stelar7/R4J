package no.stelar7.api.r4j.basic.constants.types.lol;

import no.stelar7.api.r4j.basic.constants.types.CodedEnum;

import java.util.*;
import java.util.stream.*;

public enum GameModeType implements CodedEnum
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
    TUTORIAL_MODULE_1,
    TUTORIAL_MODULE_2,
    TUTORIAL_MODULE_3,
    /**
     * Practice tool games
     */
    PRACTICETOOL,
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
     * Overcharge mode
     */
    PROJECT,
    /**
     * Snow Battle ARURF mode
     */
    SNOWURF,
    /**
     * Nexus Blitz mode
     */
    GAMEMODEX,
    NEXUSBLITZ,
    /**
     * Odyssey: Extraction mode
     */
    ODYSSEY,
    /**
     * Ultbook
     */
    ULTBOOK,
    UNKNOWN,
    
    /**
     * 2v2v2v2 ARENA
     */
    CHERRY,
    
    /**
     * TFT
     */
    TFT,
    ;
    
    /**
     * Returns a GameModeType from the provided value
     *
     * @param gameMode the mode to check
     * @return GameModeType
     */
    public Optional<GameModeType> getFromCode(final String gameMode)
    {
        if (gameMode.isEmpty()) {
            return Optional.of(UNKNOWN);
        }
        
        return Stream.of(GameModeType.values()).filter(t -> t.name().equalsIgnoreCase(gameMode)).findFirst();
    }
    
    @Override
    public String prettyName()
    {
        switch (this)
        {
            case CLASSIC:
                return "Classic";
            case ODIN:
                return "Dominion";
            case ARAM:
                return "ARAM";
            case TUTORIAL:
            case TUTORIAL_MODULE_1:
            case TUTORIAL_MODULE_2:
            case TUTORIAL_MODULE_3:
                return "Tutorial";
            case ONEFORALL:
                return "One for All";
            case ASCENSION:
                return "Ascension";
            case FIRSTBLOOD:
                return "Snowdown Showdown";
            case KINGPORO:
                return "King Poro";
            case SIEGE:
                return "Nexus Siege";
            case ASSASSINATE:
                return "Blood Hunt Assassin";
            case ARSR:
                return "All Random Summoners Rift";
            case DARKSTAR:
                return "Darkstar";
            case STARGUARDIAN:
                return "Invasion";
            case URF:
                return "URF";
            case DOOMBOTSTEEMO:
                return "Doombots";
            case PROJECT:
                return "Overcharge";
            case SNOWURF:
                return "Snow Battle ARURF";
            case GAMEMODEX:
                return "Nexus Blitz";
            case ODYSSEY:
                return "Odyssey";
            default:
                return "This enum does not have a pretty name";
        }
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
