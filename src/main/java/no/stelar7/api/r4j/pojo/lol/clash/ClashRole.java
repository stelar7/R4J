package no.stelar7.api.r4j.pojo.lol.clash;

import no.stelar7.api.r4j.basic.constants.types.CodedEnum;

import java.util.Optional;
import java.util.stream.Stream;

public enum ClashRole implements CodedEnum<ClashRole>
{
    CAPTAIN, MEMBER;
    
    @Override
    public Optional<ClashRole> getFromCode(String code)
    {
        return Stream.of(ClashRole.values()).filter(t -> t.name().equalsIgnoreCase(code)).findFirst();
    }
    
    @Override
    public String prettyName()
    {
        return this.name();
    }
}
