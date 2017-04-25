package no.stelar7.api.l4j8.basic.constants.api;

import no.stelar7.api.l4j8.basic.exceptions.*;

import java.util.stream.*;

public enum RateLimitType
{
    LIMIT_USER("application", "Application ratelimit reached"),
    LIMIT_SERVICE("service", "Service ratelimit reached"),
    LIMIT_METHOD("method", "Service ratelimit reached"),
    LIMIT_UNDERLYING("", "Underlying service limit reached");
    
    private final String value;
    private final String reason;
    
    RateLimitType(String value, String reason)
    {
        this.value = value;
        this.reason = reason;
    }
    
    public static RateLimitType getBestMatch(String data)
    {
        if (data == null)
        {
            return LIMIT_UNDERLYING;
        }
        return Stream.of(values()).filter(s -> s.getValue().equalsIgnoreCase(data)).findFirst().orElseThrow(() -> new APIDataNotParseableException(data));
    }
    
    public String getValue()
    {
        return value;
    }
    
    public String getReason()
    {
        return reason;
    }
    
}
