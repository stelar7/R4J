[![](https://jitpack.io/v/stelar7/R4J.svg)](https://jitpack.io/#stelar7/R4J)

##### R4J isn't endorsed by Riot Games and doesn't reflect the views or opinions of Riot Games or anyone officially involved in producing or managing League of Legends. League of Legends and Riot Games are trademarks or registered trademarks of Riot Games, Inc. League of Legends © Riot Games, Inc.

## How do I use it?

```Java
R4J api = new R4J(new APICredentials("RGAPI-your-key"));

RiotAccount account  = api.getAccountAPI().getAccountByTag(RegionShard.EUROPE, "stelar7", "STL7");
Summoner    summoner = api.getLoLAPI().getSummonerAPI().getSummonerByPUUID(LeagueShard.EUW1, account.getPUUID());
```

Check out one of the many [tests](https://github.com/stelar7/R4J/tree/master/src/test/java/no/stelar7/api/r4j/tests), as they provide good examples.

##### What does it cover?

| API | What | Where |
| :-- | :--- | :---- |
| Riot API | League of Legends, Teamfight Tactics, VALORANT, Legends of Runeterra, Riftbound, accounts and status: every endpoint on the [developer portal](https://developer.riotgames.com/apis), including tournaments and the RSO (signed-in player) endpoints | `api.getLoLAPI()`, `getTFTAPI()`, `getVALAPI()`, `getLORAPI()`, `getAccountAPI()`, ... |
| Data Dragon | Static data (champions, items, runes, ...) and images | `api.getDDragonAPI()`, `api.getImageAPI()` |
| CommunityDragon | Champion data and assets from the community-run CDN (the only source here not run by Riot; only used if you call it) | `api.getCDragonAPI()` |
| League client (LCU) | Calls to the running client, and its websocket events | `LCUApi`, `LCUSocketReader` |
| Live client data | Data about the game being played on this machine | `LiveClientDataAPI` |
| Replay API | Controlling the replay viewer | `ReplayAPI` |
| LoR client | The Legends of Runeterra game client, and deck codes | `LoRClientAPI`, `LoRDeckCode` |
| Tournament callbacks | A listener for the result Riot posts after a tournament game | `TournamentCallbackListener` |

##### What features does it have?

| Type           | Supported |
| :------------: | :-------: |
| V5 compatible? | Yes!      |
| Rate-limiting? | Set dynamically based on your key, and synced with the counts the api reports |
| Shared keys?   | Yes, across processes; optionally exact with Redis or a database |
| RSO endpoints? | Yes, pass the access token of the player |

| Cache      | Supported |
| :--------: | :-------: |
| FileSystem | Yes! (the default) |
| Memory     | Yes!      |
| Tiered     | Yes! (ie. memory in front of filesystem) |
| SQL        | Yes! (any database with a JDBC driver, `JdbcCacheProvider`) |

```Java
api.getContext().setCacheProvider(new TieredCacheProvider(new MemoryCacheProvider(), new FileSystemCacheProvider()));
```

## How do I download?

For maven / gradle / sbt / leiningen:  [Jitpack](https://jitpack.io/#stelar7/R4J/)  
For a JAR file: [Releases](https://github.com/stelar7/R4J/releases/)

##### Javadoc?
[Click here](https://stelar7.github.io/R4J/) (updated with every release)


## How do I async?

Every method of the api classes has a twin ending in `Async` that returns a `CompletableFuture`:

```Java
api.getStatusAPI().getShardStatusAsync(LeagueShard.EUW1)
   .thenAccept(status -> System.out.println(status));
```

No thread waits while the call is being made: the rate limit, the request and the retries are handled in the background, also for the methods that make more than one call (ie. Data Dragon looking up the newest version first). The cache is read and written directly, so a slow cache (ie. a database) is waited for.

The builders have the same: `SummonerBuilder.getAsync()`, `MatchListBuilder.getAsync()`, `MatchBuilder.getMatchAsync()` and `TimelineBuilder.getTimelineAsync()`.

`api.async(() -> ...)` runs anything else in the background, and `DataCallBuilder.buildAsync()` is the non-blocking way to make a call of your own.

To limit how many calls run at the same time (per platform and key), pass the limit when creating the api: `new R4J(creds, 4)`.

## Several keys, several processes

`new R4J(creds)` configures one shared default context. For an instance that shares nothing with it (its own keys, cache, settings and ratelimiters), use an isolated one:

```Java
R4J other = R4J.isolated(new APICredentials("RGAPI-another-key"));
other.getContext().setGlobalTimeout(5000);
```

Several processes (also on different machines) can use the same api key:

* Without any setup, each process follows the call counts the api reports, which include the calls of the others. That stays close to the limits, but calls that are in flight in different processes at the same time can not see each other, so an occasional 429 is possible (and handled).
* For exact limits, keep the counters in a shared store. A Redis implementation is included; it only needs a way to run a script, so R4J does not depend on a Redis client:

```Java
JedisPooled jedis = new JedisPooled("localhost", 6379);
R4JContext.getDefault().setRateLimitStore(new RedisRateLimitStore((script, keys, args) -> jedis.eval(script, keys, args)));
```

  A SQL database works too. It uses one small table, created on first use, and the JDBC driver of your database:

```Java
R4JContext.getDefault().setRateLimitStore(new JdbcRateLimitStore(dataSource));
```

  Redis is the faster of the two. The database store measures expiry with the clock of each process, so keep the clocks of the machines in sync.

Implement `SharedRateLimitStore` to use something else. The file system cache can be shared between processes as well.

## Upgrading

See the [changelog](CHANGELOG.md) for what changed in 3.0.0 and what to look out for when coming from 2.x.

## Requirements

Java 11 or newer.

## How do I build and test?

```
mvn clean package         # builds, and runs the tests that need no api key
mvn test -Plive           # also runs the tests that call the real api (put your keys in SecretFile)
R4J_TEST_REDIS=localhost:6379 mvn test   # also runs the rate limit store tests against a real Redis
R4J_TEST_JDBC="jdbc:postgresql://localhost/db?user=..&password=.." mvn test   # and against a real database
```

#### TODO:
* Nothing right now
