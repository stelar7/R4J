package no.stelar7.api.r4j.basic.ratelimiting;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.stream.Stream;

public enum RateLimitType
{
    LIMIT_USER("application", "Application ratelimit reached"),
    LIMIT_SERVICE("service", "Service ratelimit reached"),
    LIMIT_METHOD("method", "Method ratelimit reached"),
    LIMIT_UNDERLYING("", "Underlying service limit reached");
    
    private final String value;
    private final String reason;
    
    RateLimitType(String value, String reason)
    {
        this.value = value;
        this.reason = reason;
    }
    
    private static final Logger logger = LoggerFactory.getLogger(RateLimitType.class);

    public static RateLimitType getBestMatch(String data)
    {
        if (data == null)
        {
            return LIMIT_UNDERLYING;
        }
        return Stream.of(values()).filter(s -> s.getValue().equalsIgnoreCase(data)).findFirst().orElseGet(() -> {
            logger.warn("Unknown X-Rate-Limit-Type header value '{}', treating it as an underlying service limit", data);
            return LIMIT_UNDERLYING;
        });
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
