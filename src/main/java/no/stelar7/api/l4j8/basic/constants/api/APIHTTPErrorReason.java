package no.stelar7.api.l4j8.basic.constants.api;

public enum APIHTTPErrorReason
{
    ERROR_404(404, "Not Found"),
    ERROR_429(429, "Rate Limit Exceeded"),
    ERROR_400(400, "Generic Client Error"),
    ERROR_500(500, "Generic Server Error"),
    ERROR_599(599, "Read Timed Out");
    
    int    code;
    String reason;
    
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
