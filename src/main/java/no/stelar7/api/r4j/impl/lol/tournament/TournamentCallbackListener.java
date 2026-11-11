package no.stelar7.api.r4j.impl.lol.tournament;

import com.google.gson.JsonParseException;
import com.sun.net.httpserver.*;
import no.stelar7.api.r4j.basic.utils.Utils;
import no.stelar7.api.r4j.pojo.lol.tournament.TournamentGame;
import org.slf4j.*;

import java.io.*;
import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;
import java.util.Objects;
import java.util.concurrent.*;
import java.util.function.Consumer;

/**
 * Receives the callback Riot sends when a game played with a tournament code has ended.
 * <p>
 * When you register as a tournament provider ({@code TournamentAPI.registerAsProvider}) you give Riot a url. After every game played with one of
 * your tournament codes, Riot POSTs the result to that url. This class is a small http server that accepts those requests and hands you the result:
 * <pre>{@code
 * TournamentCallbackListener listener = new TournamentCallbackListener(8080, "/callback", game -> {
 *     System.out.println(game.getShortCode() + " was won by " + game.getWinningTeam());
 * });
 * listener.start();
 * // ...
 * listener.stop();
 * }</pre>
 * Things to know:
 * <ul>
 * <li>Riot only calls urls on port 80 (http) or 443 (https). Listen on any port and put a reverse proxy in front of it, or forward the port.</li>
 * <li>The listener answers 200 once your handler has returned. If the handler throws, it answers 500.</li>
 * <li>Anyone who knows the url can post to it. The callback has no signature, so treat it as a hint and confirm the result with
 * {@code TournamentAPI.getTournamentGames(code)} or the match api before acting on it.</li>
 * <li>If you already run a web server, you do not need this class: give the body of the request to {@link #parse(String)}.</li>
 * </ul>
 */
public class TournamentCallbackListener
{
    private static final Logger logger = LoggerFactory.getLogger(TournamentCallbackListener.class);

    /**
     * A callback is a few hundred bytes; anything much larger is not from Riot
     */
    private static final int MAX_BODY_BYTES = 1024 * 1024;

    private final int                      port;
    private final String                   path;
    private final Consumer<TournamentGame> handler;

    private HttpServer      server;
    private ExecutorService executor;

    /**
     * @param port    the port to listen on, 0 to let the system pick one (see {@link #getPort()})
     * @param path    the path of the callback url (ie. "/callback")
     * @param handler called with the result of every game that is reported
     */
    public TournamentCallbackListener(int port, String path, Consumer<TournamentGame> handler)
    {
        this.port = port;
        this.path = path.startsWith("/") ? path : "/" + path;
        this.handler = Objects.requireNonNull(handler);
    }

    /**
     * Turns the body of a callback into an object
     *
     * @param json the body Riot posted
     * @return the result of the game
     * @throws JsonParseException if the body is not a callback
     */
    public static TournamentGame parse(String json)
    {
        TournamentGame game = Utils.getGson().fromJson(json, TournamentGame.class);
        if (game == null)
        {
            throw new JsonParseException("The callback is empty");
        }

        return game;
    }

    /**
     * Starts listening. Does nothing if it is already listening.
     *
     * @throws IOException if the port can not be used
     */
    public synchronized void start() throws IOException
    {
        if (server != null)
        {
            return;
        }

        executor = Executors.newCachedThreadPool(r -> {
            Thread t = new Thread(r, "R4J-TournamentCallback");
            t.setDaemon(true);
            return t;
        });

        server = HttpServer.create(new InetSocketAddress(port), 0);
        server.createContext(path, this::handle);
        server.setExecutor(executor);
        server.start();

        logger.info("Listening for tournament callbacks on port {} at {}", getPort(), path);
    }

    /**
     * Stops listening. Does nothing if it is not listening.
     */
    public synchronized void stop()
    {
        if (server == null)
        {
            return;
        }

        server.stop(0);
        executor.shutdownNow();
        server = null;
        executor = null;
    }

    /**
     * @return the port that is being listened on (useful when the listener was created with port 0), or -1 if it is not listening
     */
    public synchronized int getPort()
    {
        return server == null ? -1 : server.getAddress().getPort();
    }

    private void handle(HttpExchange exchange) throws IOException
    {
        try
        {
            if (!"POST".equalsIgnoreCase(exchange.getRequestMethod()))
            {
                respond(exchange, 405);
                return;
            }

            // the path has to match exactly; the server would also hand us everything below it
            if (!path.equals(exchange.getRequestURI().getPath()))
            {
                respond(exchange, 404);
                return;
            }

            ByteArrayOutputStream body   = new ByteArrayOutputStream();
            byte[]                buffer = new byte[4096];
            int                   read;
            try (InputStream in = exchange.getRequestBody())
            {
                while ((read = in.read(buffer)) != -1)
                {
                    body.write(buffer, 0, read);
                    if (body.size() > MAX_BODY_BYTES)
                    {
                        respond(exchange, 413);
                        return;
                    }
                }
            }

            TournamentGame game;
            try
            {
                game = parse(new String(body.toByteArray(), StandardCharsets.UTF_8));
            } catch (RuntimeException e)
            {
                logger.warn("Received something that is not a tournament callback from {}", exchange.getRemoteAddress());
                respond(exchange, 400);
                return;
            }

            try
            {
                handler.accept(game);
            } catch (RuntimeException e)
            {
                logger.error("The tournament callback handler failed", e);
                respond(exchange, 500);
                return;
            }

            respond(exchange, 200);
        } finally
        {
            exchange.close();
        }
    }

    private void respond(HttpExchange exchange, int code) throws IOException
    {
        exchange.sendResponseHeaders(code, -1);
    }
}
