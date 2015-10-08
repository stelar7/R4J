package no.stelar7.api.l4j8.basic;

public interface APIObject
{
    static Class<?> createFromString(final String json) throws Exception
    {
        System.err.println("NOT A VALID TARGET FOR createFromString?");
        return null;
    }
}
