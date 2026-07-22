package no.stelar7.api.r4j.basic.exceptions;

/**
 * Thrown before any HTTP call is made when the target endpoint is in a server-side
 * ratelimit cooldown (not linked to the API Key rate limit, rate limit on the Riot server itself) 
 * and that the "DataCall.setFailFastOnServiceRatelimit()" flag is true.
 * 
 * Callers may treat this as "data unavailable right now" and retry later.
 */
public class APIEndpointCooldownException extends RuntimeException
{
    private final long remainingCooldownMs;

    public APIEndpointCooldownException(String endpointName, long remainingCooldownMs)
    {
        super("Endpoint " + endpointName + " is in a server-side ratelimit cooldown for another " + remainingCooldownMs + " ms");
        this.remainingCooldownMs = remainingCooldownMs;
    }

    public long getRemainingCooldownMs()
    {
        return remainingCooldownMs;
    }
}
