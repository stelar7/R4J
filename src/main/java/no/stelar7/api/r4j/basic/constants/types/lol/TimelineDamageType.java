package no.stelar7.api.r4j.basic.constants.types.lol;

import no.stelar7.api.r4j.basic.constants.types.CodedEnum;

import java.util.Optional;
import java.util.stream.Stream;

public enum TimelineDamageType implements CodedEnum<TimelineDamageType>
{
    MINION, MONSTER, TOWER, OTHER;
    
    @Override
    public Optional<TimelineDamageType> getFromCode(String type)
    {
        return Stream.of(TimelineDamageType.values()).filter(t -> t.name().equals(type)).findFirst();
    }
    
    @Override
    public String prettyName()
    {
        return this.name();
    }
    
    @Override
    public String commonName()
    {
        return this.prettyName();
    }
}
