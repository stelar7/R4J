package no.stelar7.api.l4j8.impl.lol.lcu;

import no.stelar7.api.l4j8.basic.utils.*;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.stream.Collectors;

public class LCUConnection
{
    public static Pair<String, String> getConnectionData()
    {
        boolean      isWindows      = System.getProperty("os.name").toLowerCase(Locale.ENGLISH).contains("win");
        List<String> windowsCommand = Arrays.asList("WMIC", "process", "where", "name='LeagueClientUx.exe'", "get", "commandLine");
        List<String> macCommand     = Arrays.asList("ps", "x", "|", "grep", "'LeagueClientUx.exe'");
        
        String passwordRegex = "--remoting-auth-token=(?<password>[^ \\\"]+)";
        String portRegex     = "--app-port=(?<port>\\d+)";
        
        try
        {
            ProcessBuilder builder = new ProcessBuilder(isWindows ? windowsCommand : macCommand);
            Process        process = builder.start();
            BufferedReader br      = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String         line    = br.lines().collect(Collectors.joining());
            
            String password = Utils.extractRegex(line, passwordRegex, "password");
            String port     = Utils.extractRegex(line, portRegex, "port");
            
            if (password == null || port == null)
            {
                System.err.println("The league client does not appear to be running!");
                return null;
            }
            
            return new Pair<>(password, port);
            
        } catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }
    
    
    private static String urlString;
    
    public static String getConnectionString()
    {
        if (urlString != null)
        {
            return urlString;
        }
        
        Pair<String, String> info = getConnectionData();
        return (urlString = String.format("https://127.0.0.1:%s/", info.getValue()));
    }
    
    private static Pair<String, String> headerMap;
    
    public static Pair<String, String> getAuthorizationHeader()
    {
        if (headerMap != null)
        {
            return headerMap;
        }
        
        Pair<String, String> info = getConnectionData();
        
        byte[] login   = ("riot:" + info.getKey()).getBytes(StandardCharsets.UTF_8);
        byte[] base64  = Base64.getEncoder().encode(login);
        String encoded = new String(base64, StandardCharsets.UTF_8);
        
        return (headerMap = new Pair<>("Authorization", "Basic " + encoded));
    }
    
    public static void refetchConnection()
    {
        headerMap = null;
        urlString = null;
    }
}
