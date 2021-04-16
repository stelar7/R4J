package no.stelar7.api.r4j.basic.constants.types;

import java.util.Optional;
import java.util.stream.Stream;

public enum KillType implements CodedEnum<KillType>
{
    KILL_ACE,
    KILL_FIRST_BLOOD,
    KILL_MULTI,
    UNDEFINED,
    ;
    
    @Override
    public Optional<KillType> getFromCode(final String code)
    {
        return Stream.of(KillType.values()).filter(t -> t.name().equalsIgnoreCase(code)).findFirst();
    }
    
    @Override
    public String prettyName()
    {
        return this.name();
    }
}
