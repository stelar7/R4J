package no.stelar7.api.r4j.tests.offline;

import no.stelar7.api.r4j.basic.cache.CacheProvider;
import no.stelar7.api.r4j.basic.cache.impl.*;
import no.stelar7.api.r4j.basic.constants.api.URLEndpoint;
import no.stelar7.api.r4j.basic.constants.api.regions.LeagueShard;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.FileTime;
import java.util.*;
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
}
