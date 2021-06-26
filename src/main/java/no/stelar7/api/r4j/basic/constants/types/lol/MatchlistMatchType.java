package no.stelar7.api.r4j.basic.constants.types.lol;

import no.stelar7.api.r4j.basic.constants.types.CodedEnum;

import java.util.Optional;
import java.util.stream.Stream;

public enum MatchlistMatchType implements CodedEnum<MatchlistMatchType>
{
    RANKED("ranked"), NORMAL("normal"), TOURNAMENT("tourney"), TUTORIAL("tutorial");
    
    private final String apiname;
    
    MatchlistMatchType(final String apiName)
    {
        this.apiname = apiName;
    }
    
    public Optional<MatchlistMatchType> getFromCode(final String type)
    {
        return Stream.of(MatchlistMatchType.values()).filter(t -> t.apiname.equalsIgnoreCase(type)).findFirst();
    }
    
    @Override
    public String prettyName()
    {
        switch (this)
        {
            case RANKED:
                return "Ranked";
            case NORMAL:
                return "Normal";
            case TOURNAMENT:
                return "Tournament";
            case TUTORIAL:
                return "Tutorial";
            default:
                return "This enum does not have a pretty name";
        }
    }
    
    @Override
    public String commonName()
    {
        return this.prettyName();
    }
    
    public String getValue()
    {
        return this.apiname;
    }
}
