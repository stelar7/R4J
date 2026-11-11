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
}
