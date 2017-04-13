package no.stelar7.api.l4j8.basic.exceptions;

public class APIDataNotParseableException extends RuntimeException
{
    public APIDataNotParseableException(String data)
    {
        super("The api returned a wierd response.. " + data);
    }
}
