package no.stelar7.api.r4j.basic.exceptions;

/**
 * The parent of every exception this library throws on purpose, so "anything that went wrong in R4J" can be caught in one place:
 * <pre>{@code
 * try
 * {
 *     api.getLoLAPI().getMatchAPI().getMatch(RegionShard.EUROPE, matchId);
 * } catch (APIResponseException e)
 * {
 *     // the api answered with an error (see e.getReason())
 * } catch (R4JException e)
 * {
 *     // anything else: no valid response in time, missing credentials, an endpoint in cooldown, ...
 * }
 * }</pre>
 * It is unchecked, like the exceptions were before they had a common parent.
 */
public class R4JException extends RuntimeException
{
    public R4JException()
    {
        super();
    }

    public R4JException(String message)
    {
        super(message);
    }

    public R4JException(Throwable cause)
    {
        super(cause);
    }

    public R4JException(String message, Throwable cause)
    {
        super(message, cause);
    }
}
