package no.stelar7.api.l4j8.basic.constants;

import java.util.stream.Stream;

public enum GameType
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

    public static GameType getFromCode(String gameType)
    {
        return Stream.of(values()).filter(t -> t.name().equals(gameType)).findFirst().get();
    }
}
