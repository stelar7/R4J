package no.stelar7.api.l4j8.basic;

import java.util.stream.Stream;

public enum Server
{
    GLOBAL("global.api.pvp.net", false),
    EUW("euw.api.pvp.net", true);

    public static Server getFromCode(final String code)
    {
        return Stream.of(Server.values()).filter(t -> t.name().equals(code)).findFirst().get();
    }

    private final String server;

    private final Boolean limited;

    Server(final String endpoint, final boolean limited)
    {
        this.server = endpoint;
        this.limited = limited;
    }

    public String getURL()
    {
        return this.server;
    }

    public boolean isLimited()
    {
        return this.limited;
    }

    public String toReadable()
    {
        return this.name().toLowerCase();
    }

}
