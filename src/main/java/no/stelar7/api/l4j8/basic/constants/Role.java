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

    public static Role getFromCode(String code)
    {
        return Stream.of(values()).filter(t -> t.name().equals(code)).findFirst().get();
    }
}
