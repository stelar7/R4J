package no.stelar7.api.l4j8.basic.constants.types;

import java.util.*;
import java.util.stream.*;

public enum TeamType implements CodedEnum
{
    
    BLUE(100),
    RED(200),
    AI(300);
    
    private final Integer code;
    
    TeamType(final int code)
    {
        this.code = code;
    }
    
    public Optional<TeamType> getFromCode(final String teamId)
    {
        return Stream.of(TeamType.values()).filter(t -> t.code.equals(Integer.valueOf(teamId))).findFirst();
    }
    
    @Override
    public String prettyName()
    {
        switch (this) {
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
