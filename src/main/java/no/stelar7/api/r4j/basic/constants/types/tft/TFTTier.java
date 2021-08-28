package no.stelar7.api.r4j.basic.constants.types.tft;

import no.stelar7.api.r4j.basic.constants.types.CodedEnum;

import java.util.Optional;
import java.util.stream.Stream;

public enum TFTTier implements CodedEnum<TFTTier>
{
    ORANGE,
    PURPLE,
    BLUE,
    GREEN,
    GRAY,
    ;
    
    @Override
    public Optional<TFTTier> getFromCode(String code)
    {
        return Stream.of(TFTTier.values()).filter(t -> t.name().equalsIgnoreCase(code)).findFirst();
    }
    
    @Override
    public String prettyName()
    {
        switch (this) {
            case ORANGE:
                return "Orange";
            case PURPLE:
                return "Purple";
            case BLUE:
                return "Blue";
            case GREEN:
                return "Green";
            case GRAY:
                return "Gray";
            default:
                return "This enum does not have a pretty name";
        }
    }
    
    @Override
    public String commonName()
    {
        return this.prettyName();
    }
}
