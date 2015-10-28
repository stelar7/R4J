package no.stelar7.api.l4j8.basic.constants;

import java.util.stream.Stream;

public enum Role
{

    /**
     * Duo queue?
     */
    DUO,
    /**
     * Unknown?
     */
    NONE,
    /**
     * Solo queue?
     */
    SOLO,
    /**
     * Duo queue marksman
     */
    DUO_CARRY,
    /**
     * Duo queue support
     */
    DUO_SUPPORT;

    /**
     * Returns a Role from the provided code
     *
     * @param code
     *            the lookup key
     * @return Role
     */
    public static Role getFromCode(final String code)
    {
        return Stream.of(Role.values()).filter(t -> t.name().equals(code)).findFirst().get();
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
}
