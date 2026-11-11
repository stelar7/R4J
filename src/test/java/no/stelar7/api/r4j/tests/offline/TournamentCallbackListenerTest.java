package no.stelar7.api.r4j.tests.offline;

import no.stelar7.api.r4j.impl.lol.tournament.TournamentCallbackListener;
import no.stelar7.api.r4j.pojo.lol.tournament.TournamentGame;
import org.junit.jupiter.api.*;

import java.io.*;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

public class TournamentCallbackListenerTest
{
    // the shape of the callback in the tournament api documentation
    private static final String CALLBACK = "{\"startTime\":1234567890000,\"shortCode\":\"NA1234a-1a23b456-a1b2-1abc-ab12-1234567890ab\"," +
                                           "\"metaData\":\"{\\\"title\\\":\\\"Game 42 - Finals\\\"}\",\"gameId\":1234567890,\"gameName\":\"a123bc45-ab1c-1a23-ab12-12345a67b89c\"," +
                                           "\"gameType\":\"Practice\",\"gameMap\":11,\"gameMode\":\"CLASSIC\",\"region\":\"NA1\"," +
                                           "\"winningTeam\":[{\"puuid\":\"winner-1\"},{\"puuid\":\"winner-2\"}],\"losingTeam\":[{\"puuid\":\"loser-1\"},{\"puuid\":\"loser-2\"}]}";

    private final List<TournamentGame> received = new CopyOnWriteArrayList<>();
    private       TournamentCallbackListener listener;

    @BeforeEach
    public void start() throws IOException
    {
        listener = new TournamentCallbackListener(0, "/callback", game -> {
            if ("fail".equals(game.getShortCode()))
            {
                throw new IllegalStateException("handler failed");
            }
            received.add(game);
        });
        listener.start();
    }

    @AfterEach
    public void stop()
    {
        listener.stop();
    }

    private int send(String method, String path, String body) throws IOException
    {
        HttpURLConnection con = (HttpURLConnection) new URL("http://127.0.0.1:" + listener.getPort() + path).openConnection();
        con.setRequestMethod(method);
        if (body != null)
        {
            con.setDoOutput(true);
            try (OutputStream out = con.getOutputStream())
            {
                out.write(body.getBytes(StandardCharsets.UTF_8));
            }
        }

        return con.getResponseCode();
    }

    @Test
    public void aCallbackIsHandedToTheHandler() throws IOException
    {
        Assertions.assertEquals(200, send("POST", "/callback", CALLBACK));

        Assertions.assertEquals(1, received.size());
        TournamentGame game = received.get(0);
        Assertions.assertEquals("NA1234a-1a23b456-a1b2-1abc-ab12-1234567890ab", game.getShortCode());
        Assertions.assertEquals(1234567890L, game.getGameId());
        Assertions.assertEquals(1234567890000L, game.getStartTime());
        Assertions.assertEquals(11, game.getGameMap());
        Assertions.assertEquals("NA1", game.getRegion());
        Assertions.assertEquals("{\"title\":\"Game 42 - Finals\"}", game.getMetaData());
        Assertions.assertEquals(2, game.getWinningTeam().size());
        Assertions.assertEquals("winner-1", game.getWinningTeam().get(0).getPuuid());
        Assertions.assertEquals("loser-2", game.getLosingTeam().get(1).getPuuid());
    }

    @Test
    public void anythingElseIsRejected() throws IOException
    {
        Assertions.assertEquals(405, send("GET", "/callback", null));
        Assertions.assertEquals(400, send("POST", "/callback", "this is not json {"));
        Assertions.assertEquals(404, send("POST", "/callback/other", CALLBACK));
        Assertions.assertEquals(404, send("POST", "/somewhere-else", CALLBACK));
        Assertions.assertTrue(received.isEmpty());
    }

    @Test
    public void aFailingHandlerAnswers500() throws IOException
    {
        Assertions.assertEquals(500, send("POST", "/callback", "{\"shortCode\":\"fail\"}"));
        Assertions.assertTrue(received.isEmpty());

        // and the listener keeps working
        Assertions.assertEquals(200, send("POST", "/callback", CALLBACK));
        Assertions.assertEquals(1, received.size());
    }

    @Test
    public void parseWorksWithoutTheListener()
    {
        Assertions.assertEquals("NA1", TournamentCallbackListener.parse(CALLBACK).getRegion());
    }

    @Test
    public void stoppingFreesThePort() throws IOException
    {
        int port = listener.getPort();
        listener.stop();
        Assertions.assertEquals(-1, listener.getPort());

        try (ServerSocket socket = new ServerSocket(port))
        {
            Assertions.assertEquals(port, socket.getLocalPort());
        }
    }
}
