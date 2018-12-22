package no.stelar7.api.l4j8.impl.lcu;

import no.stelar7.api.l4j8.basic.utils.Utils;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class LCUConnection
{
    public static String getConnectionString()
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
                return null;
            }
            
            return String.format("riot:%s@127.0.0.1:%s", password, port);
            
        } catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }
}
