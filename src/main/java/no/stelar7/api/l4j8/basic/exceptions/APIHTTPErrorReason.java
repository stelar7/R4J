package no.stelar7.api.l4j8.basic.exceptions;

public enum APIHTTPErrorReason
{
    ERROR_400(400, "Generic Client Error"),
    ERROR_401(401, "Missing API key"),
    ERROR_403(403, "Invalid API key"),
    ERROR_404(404, "Not Found"),
    ERROR_429(429, "Rate Limit Exceeded"),
    ERROR_500(500, "Generic Server Error"),
    ERROR_599(599, "Read Timed Out");
    
    private int code;
    private String reason;
    
    APIHTTPErrorReason(int code, String reason)
    {
        this.code = code;
        this.reason = reason;
    }
    
    public int getCode()
    {
        return code;
    }
    
    public String getReason()
    {
        return reason;
    }
    
    
    @Override
    public String toString()
    {
        return "APIHTTPErrorReason{" +
               "code=" + code +
               ", reason='" + reason + '\'' +
               '}';
    }
}
