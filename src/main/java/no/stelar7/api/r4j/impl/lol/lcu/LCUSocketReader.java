package no.stelar7.api.r4j.impl.lol.lcu;

import com.google.gson.*;
import com.google.gson.stream.JsonWriter;
import com.neovisionaries.ws.client.*;
import no.stelar7.api.r4j.basic.utils.Pair;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.security.NoSuchAlgorithmException;
import java.util.*;
import java.util.function.Consumer;

public class LCUSocketReader
{
    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(LCUSocketReader.class);
    
    private WebSocket                           socket;
    private Map<String, List<Consumer<String>>> headerHandlers = new HashMap<>();
    private Map<String, List<Consumer<String>>> rawHandlers    = new HashMap<>();
    private boolean                             connected;
    
    private Thread ping = new Thread(() -> {
        while (true)
        {
            try
            {
                Thread.sleep(500);
                if (!socket.isOpen())
                {
                    disconnect();
                    break;
                }
            } catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        }
    }, "LCU Ping Thread");
    
    public LCUSocketReader()
    {
        Pair<String, String> connection = LCUConnection.getConnectionData();
        if (connection != null)
        {
            setupConnection(connection.getKey(), connection.getValue());
        }
    }
    
    public LCUSocketReader(String password, String port)
    {
        setupConnection(password, port);
    }
    
    private void setupConnection(String password, String port)
    {
        try
        {
            String           url     = String.format("wss://localhost:%s/", port);
            WebSocketFactory factory = new WebSocketFactory();
            factory.setSSLContext(SimpleSSLContext.getInstance("TLS"));
            factory.setVerifyHostname(false);
            
            socket = factory.createSocket(url);
            socket.setUserInfo("riot", password);
            socket.addListener(new WebSocketAdapter()
            {
                @Override
                public void onTextMessage(WebSocket websocket, String text)
                {
                    JsonElement elem = new JsonParser().parse(text);
                    JsonArray   data = elem.getAsJsonArray();
                    
                    String id      = data.get(0).toString();
                    String event   = data.get(1).getAsString();
                    String content = data.get(2).getAsJsonObject().toString();
                    
                    List<Consumer<String>> headerConsumers = headerHandlers.getOrDefault(event, Collections.emptyList());
                    List<Consumer<String>> rawConsumers    = rawHandlers.getOrDefault(event, Collections.emptyList());
                    try
                    {
                        StringWriter holder = new StringWriter();
                        JsonWriter   jw     = new JsonWriter(holder);
                        jw.beginObject();
                        jw.name(event).jsonValue(content);
                        jw.endObject();
                        jw.flush();
                        jw.close();
                        
                        headerConsumers.forEach(c -> c.accept(holder.toString()));
                        rawConsumers.forEach(c -> c.accept(content));
                    } catch (IOException e)
                    {
                        e.printStackTrace();
                    }
                }
            });
        } catch (IOException | NoSuchAlgorithmException e)
        {
            e.printStackTrace();
        }
    }
    
    
    public void connect()
    {
        try
        {
            if (socket == null)
            {
                logger.error("Failed to initialize reader, this method will not work...");
                return;
            }
            
            socket.connect();
            ping.start();
            connected = true;
            logger.debug("Connected!");
            
        } catch (WebSocketException e)
        {
            e.printStackTrace();
        }
    }
    
    public void disconnect()
    {
        ping.interrupt();
        socket.disconnect();
        connected = false;
        logger.debug("Disconnected!");
    }
    
    private void sendMessage(int eventCode, String data)
    {
        socket.sendText(String.format("[%s, \"%s\"]", eventCode, data));
    }
    
    public void subscribe(String event, Consumer<String> consumer)
    {
        sendMessage(5, event);
        headerHandlers.computeIfAbsent(event, (key) -> new ArrayList<>()).add(consumer);
        logger.info("Subscribed to " + event);
    }
    
    public void subscribeRaw(String event, Consumer<String> consumer)
    {
        sendMessage(5, event);
        rawHandlers.computeIfAbsent(event, (key) -> new ArrayList<>()).add(consumer);
        logger.info("Subscribed to " + event);
    }
    
    
    public void unsubscribe(String event)
    {
        sendMessage(6, event);
        headerHandlers.remove(event);
        logger.info("Unsubscribed to " + event);
    }
    
    public void unsubscribeRaw(String event)
    {
        sendMessage(6, event);
        rawHandlers.remove(event);
        logger.info("Unsubscribed to " + event);
    }
    
    public boolean isConnected()
    {
        return connected;
    }
}
