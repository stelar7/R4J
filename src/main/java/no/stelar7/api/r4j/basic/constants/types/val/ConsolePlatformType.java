package no.stelar7.api.r4j.basic.constants.types.val;

/**
 * The console platforms the Valorant console endpoints can be queried for
 */
public enum ConsolePlatformType
{
    PLAYSTATION("playstation"),
    XBOX("xbox");

    private final String value;

    ConsolePlatformType(String value)
    {
        this.value = value;
    }

    /**
     * @return the value the api expects
     */
    public String getValue()
    {
        return value;
    }
}
