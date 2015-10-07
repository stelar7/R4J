package no.stelar7.api.l4j8.basic.constants;

import java.util.stream.Stream;

public enum Team
{

    BLUE(100),
    PURPLE(200);

    Integer code;

    Team(int code)
    {
        this.code = code;
    }

    public Integer getCode()
    {
        return code;
    }

    public static Team getFromCode(Number teamId)
    {
        return Stream.of(values()).filter(t -> t.code == teamId).findFirst().get();
    }
}
