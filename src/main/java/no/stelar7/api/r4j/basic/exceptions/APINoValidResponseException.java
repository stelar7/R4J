package no.stelar7.api.r4j.basic.exceptions;

public class APINoValidResponseException extends RuntimeException
{
    
    public APINoValidResponseException(final String responseData)
    {
        super(responseData);
    }
    
}
