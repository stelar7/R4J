package no.stelar7.api.r4j.basic.exceptions;

public class APIResponseException extends R4JException
{
    
    private final APIHTTPErrorReason reason;
    private final String             data;
    
    public APIResponseException(APIHTTPErrorReason errorCode, String response)
    {
        this.reason = errorCode;
        this.data = response;
    }
    
    public APIResponseException(APIHTTPErrorReason errorCode, String response, Throwable cause)
    {
        super(cause);
        this.reason = errorCode;
        this.data = response;
    }
    
    @Override
    public String getMessage()
    {
        return data;
    }
    
    public APIHTTPErrorReason getReason()
    {
        return reason;
    }
}
