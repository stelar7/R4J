package no.stelar7.api.r4j.basic.constants.types;

import java.util.Optional;
import java.util.stream.Stream;

public enum TransformType implements CodedEnum<TransformType>
{
    ASSASSIN,
    ;
    
    @Override
    public Optional<TransformType> getFromCode(String type)
    {
        return Stream.of(TransformType.values()).filter(t -> t.name().equals(type)).findFirst();
    }
    
    @Override
    public String prettyName()
    {
        return this.name();
    }
}
