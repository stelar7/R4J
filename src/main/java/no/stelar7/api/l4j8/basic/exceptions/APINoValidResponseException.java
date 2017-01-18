package no.stelar7.api.l4j8.basic.exceptions;

public class APINoValidResponseException extends RuntimeException
{
    
    public APINoValidResponseException(final String responseData)
    {
        super(responseData);
    }
    
}
