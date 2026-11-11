package no.stelar7.api.r4j.basic.calling;

/**
 * Implemented by the returned objects that can fetch more data themselves (ie. a Summoner fetching its matches).
 * <p>
 * Such an object remembers the context it was fetched with, so what it fetches later uses the same credentials, cache and ratelimiters.
 * The context is not part of the data: it is not stored by a cache, and not compared by equals.
 */
public interface ContextAware
{
    /**
     * Set by the library when the object is returned from a call
     *
     * @param context the context the object was fetched with
     */
    void setContext(R4JContext context);

    /**
     * @return the context the object was fetched with (the default context if it was not fetched through one)
     */
    R4JContext getContext();
}
