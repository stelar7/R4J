package no.stelar7.api.r4j.basic.constants.types.lol;

import no.stelar7.api.r4j.basic.constants.types.CodedEnum;

import java.util.Optional;
import java.util.stream.Stream;

public enum TeamType implements CodedEnum
{
    SUBTEAM(0),
    BLUE(100),
    RED(200),
    AI(300);
    
    private final Integer code;
    
    TeamType(final int code)
    {
        this.code = code;
    }
    
    public Optional<TeamType> getFromCode(String value)
    {
        if (value.contains("."))
        {
            value = value.substring(0, value.indexOf("."));
        }
        
        return getFromId(Integer.parseInt(value));
    }
    
    public Optional<TeamType> getFromId(final int teamId)
    {
        return Stream.of(TeamType.values()).filter(t -> t.code.equals(teamId)).findFirst();
    }
    
    public TeamType opposite()
    {
        switch (this)
        {
            case RED:
                return BLUE;
            case BLUE:
                return RED;
            case AI:
                return AI;
            default:
                return null;
        }
    }
    
    @Override
    public String prettyName()
    {
        switch (this)
        {
            case BLUE:
                return "Blue";
            case RED:
                return "Red";
            case AI:
                return "AI";
            default:
                return "This enum does not have a pretty name";
        }
    }
    
    public Integer getValue()
    {
        return this.code;
    }
}
