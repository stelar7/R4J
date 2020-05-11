package no.stelar7.api.r4j.tests.LCUTest;

import com.google.gson.JsonObject;
import no.stelar7.api.r4j.impl.lol.lcu.*;
import org.junit.*;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;

public class LCURunningTest
{
    @Test
    @Ignore
    public void testRunning()
    {
        Assert.assertNotNull("Unable to fecth connection string from league client... Is it running?", LCUConnection.getConnectionString());
    }
    
    
    @Test
    @Ignore
    public void testLogin()
    {
        LCUApi.login("myusername", "mypassword");
    }
    
    @Test
    @Ignore
    public void testReplay()
    {
        LCUApi.downloadReplay(3042295790L);
        LCUApi.spectateGame(3042295790L);
        System.out.println(LCUApi.getReplaySavePath());
    }
    
    @Test
    @Ignore
    public void testbyId()
    {
        JsonObject summoner = LCUApi.getSummoner(19613950);
        System.out.println();
    }
    
    @Test
    @Ignore
    public void testCustomURL()
    {
        Object a = LCUApi.customUrl("​entitlements​/v1​/token", null, "GET");
        System.out.println(a);
        //String json = "{\"actorCellId\":0,\"championId\":51,\"completed\":false,\"id\":1,\"isAllyAction\":true,\"isInProgress\":true,\"pickTurn\":1,\"type\":\"pick\"}";
        
        //Object obj = LCUApi.customUrl("lol-champ-select/v1/session/", null, "GET");
        //LCUApi.customUrl("lol-champ-select/v1/session/actions/1/complete", null, "POST");
    }
    
    @Test
    @Ignore
    public void testStartInfiniteChampSelect()
    {
        //LCUApi.createLobby()
    }
    
    @Test
    @Ignore
    public void testLogAllEvents() throws InterruptedException
    {
        Thread th = new Thread(() -> {
            try
            {
                LCUSocketReader socket = LCUApi.createWebSocket();
                socket.connect();
                
                for (String key : LCUApi.getWebsocketEvents())
                {
                    // ignore the "catch-all" event
                    if (key.equalsIgnoreCase("OnJsonApiEvent"))
                    {
                        continue;
                    }
                    
                    socket.subscribe(key, k -> {
                        try
                        {
                            Files.createDirectories(Paths.get("D:\\lcu"));
                            Files.write(Paths.get("D:\\lcu", key + ".json"), k.getBytes(StandardCharsets.UTF_8), StandardOpenOption.APPEND, StandardOpenOption.CREATE);
                        } catch (IOException e)
                        {
                            e.printStackTrace();
                        }
                    });
                }
                
                while (socket.isConnected())
                {
                    Thread.sleep(1000);
                }
            } catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        }, "LCU Socket thread");
        
        th.start();
        th.join();
    }
    
    
    @Test
    @Ignore
    public void testCreateSocket() throws InterruptedException
    {
        Thread th = new Thread(() -> {
            try
            {
                LCUSocketReader socket = LCUApi.createWebSocket();
                socket.connect();
                socket.subscribe("OnJsonApiEvent", System.out::println);
                while (socket.isConnected())
                {
                    Thread.sleep(1000);
                }
            } catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        }, "LCU Socket thread");
        
        th.start();
        th.join();
    }
    
    
    @Test
    @Ignore
    public void testLogChampselectSession() throws InterruptedException
    {
        Thread th = new Thread(() -> {
            try
            {
                LCUSocketReader socket = LCUApi.createWebSocket();
                socket.connect();
                socket.subscribe("OnJsonApiEvent_lol-champ-select_v1_session", v -> {
                    try
                    {
                        Files.write(Paths.get("D:\\lcu", "session.json"), v.getBytes(StandardCharsets.UTF_8), StandardOpenOption.APPEND, StandardOpenOption.CREATE);
                    } catch (IOException e)
                    {
                        e.printStackTrace();
                    }
                });
                
                while (socket.isConnected())
                {
                    Thread.sleep(1000);
                }
            } catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        }, "LCU Socket thread");
        
        th.start();
        th.join();
    }
    
}
