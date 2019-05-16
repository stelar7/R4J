package no.stelar7.api.l4j8.tests.LCUTest;

import no.stelar7.api.l4j8.impl.lcu.*;
import org.junit.*;

public class LCURunningTest
{
    @Test
    public void testRunning()
    {
        Assert.assertNotNull("Unable to fecth connection string from league client... Is it running?", LCUConnection.getConnectionString());
    }
    
    
    @Test
    public void testLogin()
    {
        LCUApi.login("myusername", "mypassword");
    }
    
    @Test
    public void testReplay()
    {
        LCUApi.downloadReplay(3042295790L);
        LCUApi.spectateGame(3042295790L);
        System.out.println(LCUApi.getReplaySavePath());
    }
    
    
    @Test
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
}
