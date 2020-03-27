package no.stelar7.api.r4j.pojo.lol.clash;

import no.stelar7.api.r4j.basic.constants.types.CodedEnum;

import java.util.Optional;
import java.util.stream.Stream;

public enum ClashPosition implements CodedEnum<ClashPosition>
{
    UNSELECTED, FILL, TOP, JUNGLE, MIDDLE, BOTTOM, UTILITY;
    
    @Override
    public Optional<ClashPosition> getFromCode(String code)
    {
        return Stream.of(ClashPosition.values()).filter(t -> t.name().equalsIgnoreCase(code)).findFirst();
    }
    
    @Override
    public String prettyName()
    {
        return this.name();
    }
}
