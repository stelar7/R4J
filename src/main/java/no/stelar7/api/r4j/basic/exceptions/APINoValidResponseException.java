package no.stelar7.api.r4j.basic.exceptions;

public class APINoValidResponseException extends R4JException
{
    
    public APINoValidResponseException(final String responseData)
    {
        super(responseData);
    }
    
}
