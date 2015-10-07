package no.stelar7.api.l4j8.basic;

public class APIError extends APIObject
{

    enum APIErrorType
    {
        BAD_REQUEST,
        RATE_LIMIT,
        API_ERROR,
        SERVER_ERROR;
    }

    private APIErrorType error;
    private Integer      code;

    public APIError(final Exception e)
    {
        this.error = APIErrorType.API_ERROR;
        e.printStackTrace();
    }

    public APIError(final int responseCode)
    {
        this.code = responseCode;
        switch (responseCode)
        {
            case 400:
                this.error = APIErrorType.BAD_REQUEST;
                break;
            default:
                this.error = APIErrorType.SERVER_ERROR;
                break;
        }
    }

    @Override
    public String toString()
    {
        return "APIError [code=" + code + ", error=" + this.error + "]";
    }

}
