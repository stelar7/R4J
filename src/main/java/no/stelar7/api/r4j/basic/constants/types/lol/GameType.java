package no.stelar7.api.r4j.basic.constants.types.lol;

import no.stelar7.api.r4j.basic.constants.types.CodedEnum;

import java.util.*;
import java.util.stream.Stream;

public enum GameType implements CodedEnum
{
    /**
     * Custom games
     */
    CUSTOM_GAME("CUSTOM_GAME", "CUSTOM"),
    /**
     * Tutorial games
     */
    TUTORIAL_GAME("TUTORIAL_GAME", "TUTORIAL"),
    /**
     * All other games
     */
    MATCHED_GAME("MATCHED_GAME", "MATCHED"),
    
    UNKNOWN("UNKNOWN");
    
    private final List<String> codes;
    
    GameType(String... codes)
    {
        this.codes = Arrays.asList(codes);
    }
    
    /**
     * Returns a GameType from the provided value
     *
     * @param gameType the type to check
     * @return GameType
     */
    public Optional<GameType> getFromCode(final String gameType)
    {
        if (gameType.isEmpty())
        {
            return Optional.of(UNKNOWN);
        }
        
        return Stream.of(GameType.values()).filter(t -> t.codes.contains(gameType.toUpperCase())).findFirst();
    }
    
    @Override
    public String prettyName()
    {
        switch (this)
        {
            case CUSTOM_GAME:
                return "Custom Game";
            case TUTORIAL_GAME:
                return "Tutorial Game";
            case MATCHED_GAME:
                return "Matched Game";
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
