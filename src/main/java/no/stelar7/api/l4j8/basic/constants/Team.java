package no.stelar7.api.l4j8.basic.constants;

import java.util.stream.Stream;

public enum Team
{

    BLUE(100),
    PURPLE(200);

    public static Team getFromCode(final Number teamId)
    {
        return Stream.of(Team.values()).filter(t -> t.code.equals(teamId)).findFirst().get();
    }

    Integer code;

    Team(final int code)
    {
        this.code = code;
    }

    public Integer getCode()
    {
        return this.code;
    }
}
