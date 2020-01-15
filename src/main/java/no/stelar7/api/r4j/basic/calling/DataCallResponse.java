package no.stelar7.api.r4j.basic.calling;


public class DataCallResponse
{
    private final int    responseCode;
    private final String responseData;
    
    DataCallResponse(final int a, final String b)
    {
        this.responseCode = a;
        this.responseData = b;
    }
    
    /**
     * Response code
     */
    int getResponseCode()
    {
        return this.responseCode;
    }
    
    /**
     * Response data
     */
    String getResponseData()
    {
        return this.responseData;
    }
    
    @Override
    public String toString()
    {
        return "DataCallResponse" +
               "\n{" +
               "\n\tresponseCode=" + responseCode +
               "\n\tresponseData=\n\t" + responseData + "\n\n}";
    }
}
