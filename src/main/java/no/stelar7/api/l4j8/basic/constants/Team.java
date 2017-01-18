package no.stelar7.api.l4j8.basic.constants;

import java.util.*;
import java.util.stream.*;

public enum Team
{
    
    BLUE(100),
    PURPLE(200);
    
    final Integer code;
    
    Team(final int code)
    {
        this.code = code;
    }
    
    public static Optional<Team> getFromCode(final Number teamId)
    {
        return Stream.of(Team.values()).filter(t -> t.code.equals(teamId)).findFirst();
    }
    
    public Integer getCode()
    {
        return this.code;
    }
}
