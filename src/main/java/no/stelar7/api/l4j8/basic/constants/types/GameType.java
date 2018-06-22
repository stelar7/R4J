package no.stelar7.api.l4j8.basic.constants.types;

import java.util.*;
import java.util.stream.*;

public enum GameType implements CodedEnum
{
    /**
     * Custom games
     */
    CUSTOM_GAME,
    /**
     * Tutorial games
     */
    TUTORIAL_GAME,
    /**
     * All other games
     */
    MATCHED_GAME;
    
    /**
     * Returns a GameType from the provided value
     *
     * @param gameType the type to check
     * @return GameType
     */
    public Optional<GameType> getFromCode(final String gameType)
    {
        return Stream.of(GameType.values()).filter(t -> t.name().equalsIgnoreCase(gameType)).findFirst();
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
