package no.stelar7.api.r4j.pojo.lor;

public class LoRConnection
{
    private static int port = 21337;
    
    public static int getPort()
    {
        return port;
    }
    
    public static void setPort(int port)
    {
        LoRConnection.port = port;
    }
    
    public static String getConnectionString()
    {
        return "http://127.0.0.1:" + port + "/";
    }
}
