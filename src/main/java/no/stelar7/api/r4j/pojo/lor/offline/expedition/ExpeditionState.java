package no.stelar7.api.r4j.pojo.lor.offline.expedition;

import no.stelar7.api.r4j.basic.constants.types.CodedEnum;

import java.util.Optional;
import java.util.stream.Stream;

public enum ExpeditionState implements CodedEnum<ExpeditionState>
{
    INACTIVE("Inactive"), OFFSCREEN("Offscreen"), PICKING("Picking"), SWAPPING("Swapping"), OTHER("Other");
    
    private String key;
    
    ExpeditionState(String key)
    {
        this.key = key;
    }
    
    public String getKey()
    {
        return key;
    }
    
    @Override
    public Optional<ExpeditionState> getFromCode(String code)
    {
        return Stream.of(ExpeditionState.values()).filter(v -> v.key.equalsIgnoreCase(code)).findFirst();
    }
    
    @Override
    public String prettyName()
    {
        return this.key;
    }
}
