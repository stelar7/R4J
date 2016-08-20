package no.stelar7.api.l4j8.basic.constants.api;

import java.util.stream.Stream;

public enum RateLimitType
{
    LIMIT_USER("user", "User ratelimit reached"),
    LIMIT_SERVICE("service", "Service ratelimit reached"),
    LIMIT_UNDERLYING("", "Underlying service limit reached");

    private final String value;
    private final String reason;

    RateLimitType(String value, String reason)
    {
        this.value = value;
        this.reason = reason;
    }

    private String getValue()
    {
        return value;
    }

    public String getReason()
    {
        return reason;
    }

    public static RateLimitType getBestMatch(String data)
    {
        if (data == null)
        {
            return LIMIT_UNDERLYING;
        }
        return Stream.of(values()).filter(s -> s.getValue().equalsIgnoreCase(data)).findFirst().orElse(LIMIT_UNDERLYING);
    }

}
