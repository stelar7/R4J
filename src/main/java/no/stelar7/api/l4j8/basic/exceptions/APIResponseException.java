package no.stelar7.api.l4j8.basic.exceptions;

import no.stelar7.api.l4j8.basic.constants.api.*;

public class APIResponseException extends RuntimeException
{

    APIHTTPErrorReason reason;
    String             data;

    public APIResponseException(APIHTTPErrorReason errorCode, String response)
    {
        this.reason = errorCode;
        this.data = response;
    }

    @Override
    public String getMessage()
    {
        switch (reason)
        {
            case ERROR429:
                return String.format("HTTP 429 hit. Reason for hit: %s", data);
            default:
                return String.format("%s: %s", reason, data);
        }
    }
}
