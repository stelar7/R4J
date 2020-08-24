package no.stelar7.api.r4j.basic.constants.types.val;

import no.stelar7.api.r4j.basic.constants.types.CodedEnum;

import java.util.Optional;
import java.util.stream.Stream;

public enum GameModeType implements CodedEnum<GameModeType>
{
    DEFAULT("/Game/GameModes/Bomb/BombGameMode.BombGameMode_C"),
    DEATHMATCH("/Game/GameModes/Deathmatch/DeathmatchGameMode.DeathmatchGameMode_C"),
    SPIKE_RUSH("/Game/GameModes/QuickBomb/QuickBombGameMode.QuickBombGameMode_C"),
    ;
    
    
    private final String mode;
    
    /**
     * Constructor for MapType
     *
     * @param code the mapId
     */
    GameModeType(final String code)
    {
        this.mode = code;
    }
    
    /**
     * Gets from code.
     *
     * @param mapId the map id
     * @return the from code
     */
    public Optional<GameModeType> getFromCode(final String mapId)
    {
        return Stream.of(GameModeType.values()).filter(t -> t.mode.equals(mapId)).findFirst();
    }
    
    @Override
    public String prettyName()
    {
        switch (this)
        {
            default:
                return "This enum does not have a pretty name";
        }
    }
    
    /**
     * Gets id.
     *
     * @return the id
     */
    public String getId()
    {
        return this.mode;
    }
    
    
    /**
     * Used internaly in the api...
     *
     * @return the value
     */
    public String getValue()
    {
        return getId();
    }
    
}
