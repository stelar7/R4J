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

    private final Boolean limit;

    Server(final String endpoint, final boolean limit)
    {
        this.server = endpoint;
        this.limit = limit;
    }

    public String getURL()
    {
        return this.server;
    }

    public boolean hasLimit()
    {
        return this.limit;
    }

    public String toReadable()
    {
        return this.name().toLowerCase();
    }

}
