package no.stelar7.api.l4j8.basic.constants.api;

import java.util.*;
import java.util.stream.*;

public enum Server
{
    BR("br.api.pvp.net", true),
    EUNE("eune.api.pvp.net", true),
    EUW("euw.api.pvp.net", true),
    KR("kr.api.pvp.net", true),
    JP("jp.api.pvp.net", true),
    LAN("lan.api.pvp.net", true),
    LAS("las.api.pvp.net", true),
    NA("na.api.pvp.net", true),
    OCE("oce.api.pvp.net", true),
    TR("tr.api.pvp.net", true),
    RU("ru.api.pvp.net", true),
    PBE("pbe.api.pvp.net", true),
    GLOBAL("global.api.pvp.net", false);

    public static Optional<Server> getFromCode(final String code)
    {
        return Stream.of(Server.values()).filter(t -> t.name().equals(code)).findFirst();
    }

    private final String  server;

    private final Boolean limited;

    Server(final String endpoint, final boolean limited)
    {
        this.server = endpoint;
        this.limited = limited;
    }

    /**
     * The "server" as the URL expects it (euw, br, na, etc...)
     *
     * @return String
     */
    public String asURLFormat()
    {
        return this.name().toLowerCase(Locale.ENGLISH);
    }

    /**
     * The code used to map strings to objects
     *
     * @return String
     */
    public String getCode()
    {
        return this.name();
    }

    /**
     * The URL base for calling the API on that server
     *
     * @return String
     */
    public String getURL()
    {
        return this.server;
    }

    /**
     * Whether or not this "server" has a rate-limit
     *
     * @return Boolean
     */
    public Boolean isLimited()
    {
        return this.limited;
    }

}
