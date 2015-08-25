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

    APIErrorType error;

    public APIError(Exception e)
    {
        e.printStackTrace();
        error = APIErrorType.API_ERROR;
    }

    public APIError(int responseCode)
    {
        switch (responseCode)
        {
            case 400:
                error = APIErrorType.BAD_REQUEST;
                break;
            default:
                error = APIErrorType.SERVER_ERROR;
                break;
        }
    }

    @Override
    public String toString()
    {
        return error.toString();
    }

}
