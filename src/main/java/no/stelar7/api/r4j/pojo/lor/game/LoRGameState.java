package no.stelar7.api.r4j.pojo.lor.game;

import no.stelar7.api.r4j.basic.constants.types.CodedEnum;

import java.util.Optional;
import java.util.stream.Stream;

public enum LoRGameState implements CodedEnum
{
    IN_PROGRESS("InProgress"),
    MENUS("Menus");
    
    private String key;
    
    LoRGameState(String key)
    {
        this.key = key;
    }
    
    public Optional<LoRGameState> getFromCode(final String type)
    {
        return Stream.of(LoRGameState.values()).filter(t -> t.key.equalsIgnoreCase(type)).findFirst();
    }
    
    @Override
    public String prettyName()
    {
        return this.key;
    }
}
