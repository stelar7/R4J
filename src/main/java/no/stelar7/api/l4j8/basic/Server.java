package no.stelar7.api.l4j8.basic;

import java.util.Locale;
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
