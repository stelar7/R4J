package no.stelar7.api.r4j.tests.offline;

import no.stelar7.api.r4j.basic.cache.CacheProvider;
import no.stelar7.api.r4j.basic.cache.impl.*;
import no.stelar7.api.r4j.basic.constants.api.URLEndpoint;
import no.stelar7.api.r4j.basic.constants.api.regions.LeagueShard;
import no.stelar7.api.r4j.basic.utils.Utils;
import no.stelar7.api.r4j.pojo.shared.RiotAccount;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.sql.DriverManager;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.nio.file.attribute.FileTime;
import java.util.*;
import java.util.concurrent.*;
import java.util.stream.*;

/**
 * Tests the cache providers that need no external service
 */
public class CacheProviderTest
{
    private static final URLEndpoint ENDPOINT = URLEndpoint.V4_SUMMONER_BY_PUUID;

    private Map<String, Object> key(LeagueShard platform, String puuid)
    {
        Map<String, Object> data = new LinkedHashMap<>();
        data.put("platform", platform);
        data.put("puuid", puuid);
        return data;
    }

    private Map<String, Object> entry(LeagueShard platform, String puuid, Object value)
    {
        Map<String, Object> data = key(platform, puuid);
        data.put("value", value);
        return data;
    }

    private void storesAndReturns(CacheProvider cache)
    {
        Assertions.assertFalse(cache.get(ENDPOINT, key(LeagueShard.EUW1, "a")).isPresent());

        cache.store(ENDPOINT, entry(LeagueShard.EUW1, "a", "value-a"));
        cache.store(ENDPOINT, entry(LeagueShard.EUW1, "b", "value-b"));
        cache.store(ENDPOINT, entry(LeagueShard.NA1, "a", "value-na"));

        Assertions.assertEquals("value-a", cache.get(ENDPOINT, key(LeagueShard.EUW1, "a")).orElse(null));
        Assertions.assertEquals("value-b", cache.get(ENDPOINT, key(LeagueShard.EUW1, "b")).orElse(null));
        Assertions.assertEquals("value-na", cache.get(ENDPOINT, key(LeagueShard.NA1, "a")).orElse(null));
        Assertions.assertFalse(cache.get(ENDPOINT, key(LeagueShard.NA1, "b")).isPresent());

        // other endpoints are not affected
        Assertions.assertFalse(cache.get(URLEndpoint.V4_STATUS_LOL, key(LeagueShard.EUW1, "a")).isPresent());

        // null values are not stored
        cache.store(ENDPOINT, entry(LeagueShard.KR, "a", null));
        Assertions.assertFalse(cache.get(ENDPOINT, key(LeagueShard.KR, "a")).isPresent());

        // the filter is a prefix of the key
        Map<String, Object> euw = new LinkedHashMap<>();
        euw.put("platform", LeagueShard.EUW1);
        Assertions.assertEquals(2, cache.getSize(ENDPOINT, euw));
        Assertions.assertEquals(3, cache.getSize(ENDPOINT, Collections.emptyMap()));

        cache.clear(ENDPOINT, euw);
        Assertions.assertFalse(cache.get(ENDPOINT, key(LeagueShard.EUW1, "a")).isPresent());
        Assertions.assertEquals("value-na", cache.get(ENDPOINT, key(LeagueShard.NA1, "a")).orElse(null));
    }

    @Test
    public void memoryCacheStoresAndReturns()
    {
        storesAndReturns(new MemoryCacheProvider(CacheProvider.TTL_INFINITY));
    }

    @Test
    public void memoryCacheExpires() throws InterruptedException
    {
        MemoryCacheProvider cache = new MemoryCacheProvider(200);
        cache.store(ENDPOINT, entry(LeagueShard.EUW1, "a", "value-a"));
        Assertions.assertTrue(cache.get(ENDPOINT, key(LeagueShard.EUW1, "a")).isPresent());

        Thread.sleep(400);
        Assertions.assertFalse(cache.get(ENDPOINT, key(LeagueShard.EUW1, "a")).isPresent());
    }

    @Test
    public void fileCacheStoresAndReturns(@TempDir Path dir)
    {
        storesAndReturns(new FileSystemCacheProvider(dir, CacheProvider.TTL_INFINITY));
    }

    @Test
    public void fileCacheExpiresOnAgeNotOnAccess(@TempDir Path dir) throws IOException
    {
        FileSystemCacheProvider cache = new FileSystemCacheProvider(dir, 60_000);
        cache.store(ENDPOINT, entry(LeagueShard.EUW1, "a", "value-a"));

        // reading it does not make it younger
        Assertions.assertTrue(cache.get(ENDPOINT, key(LeagueShard.EUW1, "a")).isPresent());

        // pretend it was stored two minutes ago
        List<Path> files;
        try (Stream<Path> walk = Files.walk(dir))
        {
            files = walk.filter(Files::isRegularFile).collect(Collectors.toList());
        }
        Assertions.assertEquals(1, files.size());
        Files.setLastModifiedTime(files.get(0), FileTime.fromMillis(System.currentTimeMillis() - 120_000));

        Assertions.assertFalse(cache.get(ENDPOINT, key(LeagueShard.EUW1, "a")).isPresent());
        Assertions.assertFalse(Files.exists(files.get(0)));
    }

    @Test
    public void tieredCacheFillsTheTiersInFront(@TempDir Path dir)
    {
        MemoryCacheProvider     memory = new MemoryCacheProvider(CacheProvider.TTL_INFINITY);
        FileSystemCacheProvider file   = new FileSystemCacheProvider(dir, CacheProvider.TTL_INFINITY);
        TieredCacheProvider     tiered = new TieredCacheProvider(memory, file);

        file.store(ENDPOINT, entry(LeagueShard.EUW1, "a", "value-a"));
        Assertions.assertFalse(memory.get(ENDPOINT, key(LeagueShard.EUW1, "a")).isPresent());

        Assertions.assertEquals("value-a", tiered.get(ENDPOINT, key(LeagueShard.EUW1, "a")).orElse(null));
        Assertions.assertEquals("value-a", memory.get(ENDPOINT, key(LeagueShard.EUW1, "a")).orElse(null));
    }
    
    private List<Path> files(Path dir) throws IOException
    {
        try (Stream<Path> walk = Files.walk(dir))
        {
            return walk.filter(Files::isRegularFile).collect(Collectors.toList());
        }
    }
    
    @Test
    public void fileCacheStoresApiObjectsAsJson(@TempDir Path dir) throws IOException
    {
        FileSystemCacheProvider cache    = new FileSystemCacheProvider(dir, CacheProvider.TTL_INFINITY);
        URLEndpoint             endpoint = URLEndpoint.V1_SHARED_ACCOUNT_BY_PUUID;
        
        RiotAccount account = Utils.getGson().fromJson("{\"puuid\":\"abc\",\"gameName\":\"Ærlig\",\"tagLine\":\"EUW\"}", RiotAccount.class);
        cache.store(endpoint, entry(LeagueShard.EUW1, "abc", account));
        
        // what is on disk is plain json
        List<Path> files = files(dir);
        Assertions.assertEquals(1, files.size());
        String content = new String(Files.readAllBytes(files.get(0)), StandardCharsets.UTF_8);
        Assertions.assertTrue(content.startsWith("{") && content.contains("\"gameName\""), content);
        
        Object cached = cache.get(endpoint, key(LeagueShard.EUW1, "abc")).orElse(null);
        Assertions.assertEquals(account, cached);
        Assertions.assertNotSame(account, cached);
    }
    
    @Test
    public void fileCacheStillReadsJavaSerializedFiles(@TempDir Path dir) throws IOException
    {
        FileSystemCacheProvider cache = new FileSystemCacheProvider(dir, CacheProvider.TTL_INFINITY);
        
        // a String is not what this endpoint returns, so it is stored the old way (which is also what older versions wrote)
        cache.store(ENDPOINT, entry(LeagueShard.EUW1, "a", "value-a"));
        byte[] content = Files.readAllBytes(files(dir).get(0));
        Assertions.assertEquals(0xAC, content[0] & 0xFF);
        Assertions.assertEquals(0xED, content[1] & 0xFF);
        
        Assertions.assertEquals("value-a", cache.get(ENDPOINT, key(LeagueShard.EUW1, "a")).orElse(null));
    }
    
    @Test
    public void fileCacheRemovesUnreadableFiles(@TempDir Path dir) throws IOException
    {
        FileSystemCacheProvider cache    = new FileSystemCacheProvider(dir, CacheProvider.TTL_INFINITY);
        URLEndpoint             endpoint = URLEndpoint.V1_SHARED_ACCOUNT_BY_PUUID;
        
        RiotAccount account = Utils.getGson().fromJson("{\"puuid\":\"abc\"}", RiotAccount.class);
        cache.store(endpoint, entry(LeagueShard.EUW1, "abc", account));
        
        Path file = files(dir).get(0);
        Files.write(file, "{ this is not json".getBytes(StandardCharsets.UTF_8));
        
        Assertions.assertFalse(cache.get(endpoint, key(LeagueShard.EUW1, "abc")).isPresent());
        Assertions.assertFalse(Files.exists(file));
    }
    
    /**
     * Several caches on the same folder, like several processes sharing a cache directory
     */
    @Test
    public void fileCacheCanBeSharedByConcurrentWriters(@TempDir Path dir) throws Exception
    {
        URLEndpoint endpoint = URLEndpoint.V1_SHARED_ACCOUNT_BY_PUUID;
        RiotAccount account  = Utils.getGson().fromJson("{\"puuid\":\"abc\",\"gameName\":\"name\",\"tagLine\":\"EUW\"}", RiotAccount.class);
        
        ExecutorService      pool = Executors.newFixedThreadPool(6);
        List<Future<String>> jobs = new ArrayList<>();
        for (int t = 0; t < 6; t++)
        {
            jobs.add(pool.submit(() -> {
                FileSystemCacheProvider cache = new FileSystemCacheProvider(dir, CacheProvider.TTL_INFINITY);
                for (int i = 0; i < 150; i++)
                {
                    cache.store(endpoint, entry(LeagueShard.EUW1, "abc", account));
                    Optional<?> cached = cache.get(endpoint, key(LeagueShard.EUW1, "abc"));
                    
                    // a reader must never see half a file: the value is either complete or (on a platform that can not replace atomically) absent
                    if (cached.isPresent() && !account.equals(cached.get()))
                    {
                        return "read a damaged value: " + cached.get();
                    }
                }
                return null;
            }));
        }
        
        for (Future<String> job : jobs)
        {
            Assertions.assertNull(job.get(60, TimeUnit.SECONDS));
        }
        pool.shutdown();
        
        // no temporary files are left behind
        Assertions.assertEquals(1, files(dir).size());
    }

    @Test
    public void fileCacheCleanupRemovesExpiredFilesAndEmptyFolders(@TempDir Path dir) throws IOException
    {
        FileSystemCacheProvider cache = new FileSystemCacheProvider(dir, 60_000);
        cache.store(ENDPOINT, entry(LeagueShard.EUW1, "old", "value-old"));
        cache.store(ENDPOINT, entry(LeagueShard.NA1, "fresh", "value-fresh"));

        // the first one was stored two minutes ago
        Path old = files(dir).stream().filter(p -> p.toString().contains("old")).findFirst().orElseThrow(AssertionError::new);
        Files.setLastModifiedTime(old, FileTime.fromMillis(System.currentTimeMillis() - 120_000));

        // nobody asks for it again; the cleanup has to remove it, and the folders that only existed for it
        cache.clearOldCache();

        Assertions.assertFalse(Files.exists(old));
        Assertions.assertFalse(Files.exists(old.getParent()));
        Assertions.assertTrue(Files.exists(dir));

        // what has not expired is untouched
        List<Path> left = files(dir);
        Assertions.assertEquals(1, left.size());
        Assertions.assertTrue(left.get(0).toString().contains("fresh"));
        Assertions.assertEquals("value-fresh", cache.get(ENDPOINT, key(LeagueShard.NA1, "fresh")).orElse(null));

        // and storing into a folder that was removed works
        cache.store(ENDPOINT, entry(LeagueShard.EUW1, "old", "value-new"));
        Assertions.assertEquals("value-new", cache.get(ENDPOINT, key(LeagueShard.EUW1, "old")).orElse(null));
    }

    private JdbcCacheProvider h2Cache(long ttl)
    {
        // a table per test, so the tests do not see each others data
        String table = "cache_" + UUID.randomUUID().toString().replace("-", "");
        return new JdbcCacheProvider(() -> DriverManager.getConnection("jdbc:h2:mem:r4jcache;DB_CLOSE_DELAY=-1"), table, ttl);
    }

    @Test
    public void databaseCacheStoresAndReturns()
    {
        storesAndReturns(h2Cache(CacheProvider.TTL_INFINITY));
    }

    @Test
    public void databaseCacheExpires() throws InterruptedException
    {
        JdbcCacheProvider cache = h2Cache(200);
        cache.store(ENDPOINT, entry(LeagueShard.EUW1, "a", "value-a"));
        Assertions.assertTrue(cache.get(ENDPOINT, key(LeagueShard.EUW1, "a")).isPresent());

        Thread.sleep(400);
        Assertions.assertFalse(cache.get(ENDPOINT, key(LeagueShard.EUW1, "a")).isPresent());
        Assertions.assertEquals(0, cache.getSize(ENDPOINT, Collections.emptyMap()));

        // clearOldCache removes what nobody asked for again
        cache.store(ENDPOINT, entry(LeagueShard.EUW1, "b", "value-b"));
        Thread.sleep(400);
        cache.clearOldCache();
        Assertions.assertEquals(0, cache.getSize(ENDPOINT, Collections.emptyMap()));
    }

    @Test
    public void databaseCacheKeepsApiObjectsAndReplacesThem()
    {
        JdbcCacheProvider cache    = h2Cache(CacheProvider.TTL_INFINITY);
        URLEndpoint       endpoint = URLEndpoint.V1_SHARED_ACCOUNT_BY_PUUID;

        RiotAccount first  = Utils.getGson().fromJson("{\"puuid\":\"abc\",\"gameName\":\"\u00c6rlig\",\"tagLine\":\"EUW\"}", RiotAccount.class);
        RiotAccount second = Utils.getGson().fromJson("{\"puuid\":\"abc\",\"gameName\":\"renamed\",\"tagLine\":\"EUW\"}", RiotAccount.class);

        cache.store(endpoint, entry(LeagueShard.EUW1, "abc", first));
        Assertions.assertEquals(first, cache.get(endpoint, key(LeagueShard.EUW1, "abc")).orElse(null));

        // storing again replaces the item instead of adding a second one
        cache.store(endpoint, entry(LeagueShard.EUW1, "abc", second));
        Assertions.assertEquals(second, cache.get(endpoint, key(LeagueShard.EUW1, "abc")).orElse(null));
        Assertions.assertEquals(1, cache.getSize(endpoint, Collections.emptyMap()));
    }

    /**
     * A real database, when R4J_TEST_JDBC is set to its jdbc url (with the user and password in the url)
     */
    @Test
    public void realDatabaseCacheStoresAndReturns()
    {
        String url = System.getProperty("R4J_TEST_JDBC") != null ? System.getProperty("R4J_TEST_JDBC") : System.getenv("R4J_TEST_JDBC");
        Assumptions.assumeTrue(url != null && !url.isEmpty(), "R4J_TEST_JDBC is not set, skipping the database test");

        String table = "cache_" + UUID.randomUUID().toString().replace("-", "");
        JdbcCacheProvider cache = new JdbcCacheProvider(() -> DriverManager.getConnection(url), table, CacheProvider.TTL_INFINITY);
        storesAndReturns(cache);

        // something larger than 64KB, which is where the smaller text types of some databases end
        StringBuilder name = new StringBuilder();
        for (int i = 0; i < 20_000; i++)
        {
            name.append("æøå ");
        }
        RiotAccount big = Utils.getGson().fromJson("{\"puuid\":\"big\",\"gameName\":\"" + name + "\"}", RiotAccount.class);
        cache.store(URLEndpoint.V1_SHARED_ACCOUNT_BY_PUUID, entry(LeagueShard.EUW1, "big", big));
        Assertions.assertEquals(big, cache.get(URLEndpoint.V1_SHARED_ACCOUNT_BY_PUUID, key(LeagueShard.EUW1, "big")).orElse(null));
    }
}
