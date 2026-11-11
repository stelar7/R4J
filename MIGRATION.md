# Migrating from 2.x to 3.0.0

Most code keeps working unchanged: `new R4J(credentials)`, the static methods on `DataCall`, the `getInstance()` of the api classes and the builders all behave as before. These are the things that can affect you.

## You have to act if

* **You run on Java 8.** Java 11 is now the minimum.
* **You use the rate limiter classes directly.** `BurstRateLimiter` and `CounterRateLimiter` are gone, and `RateLimiter` has a new api (`tryAcquire`, `release`, `updateLimits`, `syncWithServerCounts`, `cooldown`, `getCallCounts`). `DataCallBuilder.getLock` and `getGlobalLock` are gone. If you only use R4J through its api classes, nothing changes for you.
* **You use `MemoryCacheProvider` with a time to live.** It is now in milliseconds, like the other caches (the old javadoc said seconds, but the cache did not work at all before).
* **You use `MySQLCacheProvider` or `MongoDBCacheProvider`.** Both are gone. Neither was finished (the MySQL one ended the JVM on every lookup), and `JdbcCacheProvider` caches in any SQL database, MySQL included. The MySQL driver and Morphia are no longer optional dependencies of R4J.
* **You use `LCUSocketReader`.** The websocket library it needs (`com.neovisionaries:nv-websocket-client:2.14`) is now an optional dependency, so it is no longer pulled in for everyone. Add it to your own project.
* **You relied on the Travis build uploading jars.** The Travis configuration is gone (the service was shut down), and nothing builds or uploads release jars automatically anymore.

## Behaviour that changed

* **Timeouts.** Calls used to wait forever by default. They now give up after 10 seconds connecting and 30 seconds reading, unless you set a timeout (`api.getContext().setGlobalTimeout(ms)`).
* **TLS.** Certificate checks used to be switched off for the whole JVM as a side effect of loading R4J. They are now only relaxed for connections to the local game client. If your own code talked to servers with invalid certificates and only worked because of R4J, it will now fail.
* **Unknown enum values.** A queue, map or similar that R4J does not know yet no longer throws `APIEnumNotUpToDateException`. It becomes the `UNKNOWN`/`UNDEFINED` constant of the enum (or `null` if there is none), and a warning is logged once. `GenericEnumSerializer.setStrict(true)` brings the exception back.
* **Query parameters are url-encoded.** If you built calls with `DataCallBuilder.withQueryParameter` and encoded the values yourself, remove your encoding.
* **`build()` on a 429.** After five consecutive application or method 429s a call now throws `APIResponseException` instead of retrying forever.
* **Console output.** Everything R4J printed to `System.out`/`System.err` now goes through slf4j.
* **File cache.** Items expire by age (time since they were stored) instead of by last access, and `TTL_INFINITY` now really means never (it used to delete everything). New files are json where possible; old cache folders can still be read.
* **Tiered cache.** A hit no longer refreshes the tier that had the data, so cached data expires even when it is read often.
* **Lazy lists.** `size()` and `isEmpty()` on a lazily loaded list (ie. from `getMatchList`) now load what they need to answer, instead of reporting what happened to be loaded. `size()` on a long match history therefore makes all the calls needed to count it; use `loadedSize()` for the old behaviour.
* **File cache cleanup.** Expired files and empty folders are now removed every hour (or more often with a short time to live), also with the default lifetimes.
* **Responses that can not be read.** Json of another shape than the endpoint returns (or no json at all) used to end in Gson's `JsonSyntaxException`, or a `ClassCastException` or `NullPointerException`. It is now an `APINoValidResponseException` with the original as its cause. If you caught the Gson exception, catch this one.
* **Nothing found.** Asking for a TFT or LoR match, or a master, grandmaster or challenger league, that does not exist returns null or an empty list instead of throwing a `NullPointerException`. The methods that can return null are marked `@Nullable`.
* **Data Dragon without a version.** When Data Dragon can not say which version is the newest, the `DDragonAPI` methods return an empty map (or null for one item) instead of throwing.
* **Compressed responses.** Every call asks for gzip. Set the header yourself on a call (`withHeader("Accept-Encoding", "identity")`) if something between you and Riot can not handle it.
* **Exceptions.** Every exception R4J throws on purpose now extends `R4JException` (a `RuntimeException`), so one catch covers them.
* **Tests.** `mvn test` only runs the tests that need no api key. Use `mvn test -Plive` for all of them.

## Removed

These are gone. Most were for endpoints Riot no longer documents, so they are not expected to have worked anymore.

| What | Why | Use instead |
| :--- | :-- | :---------- |
| `ThirdPartyAPI`, `ThirdPartyCodeBuilder`, `Summoner.getThirdPartyCode()` | third-party-code is no longer in the Riot api documentation | RSO, to prove an account belongs to a player |
| `SpectatorAPI.getFeaturedGames` | featured-games is no longer in the Riot api documentation | nothing |
| `MatchV5API.getMatchListReplays(server, puuid)`, `ReplayBuilder` | replaced by an endpoint that needs the access token of the player | `MatchV5API.getMatchReplaysByAccessToken` |
| `LeagueAPI.getLeague(server, leagueId)`, `TFTLeagueAPI.getLeague(server, leagueId)`, `LeagueBuilder.withLeagueId` / `getLeague()`, `SummonerCrawler.crawlLeague()` | Riot is retiring league ids | the league entries of a player, or the tier and division lists |
| `ClashAPI.getPlayerInfo(server, summonerId)` | summoner ids are no longer handed out | `ClashAPI.getPlayerInfoByPuuid` |
| `ImageAPI.getProfileIcon(region, summonerName)` | the service behind it no longer exists | `ImageAPI.getProfileIcon(iconId, version)` |
| `RealmSpesificEnum`, `RealmSpesificEnumSerializer` | the name was misspelled | `RealmSpecificEnum`, `RealmSpecificEnumSerializer` |

`RiotAccountShard.getShardAsEnum()` now returns a `RealmSpecificEnum`.

## Deprecated

* The static settings on `DataCall` (`setCacheProvider`, `setCredentials`, `setGlobalTimeout`, `setDefaultMaxSleep`, ...). They still configure the default context. Use the context itself: `api.getContext().setCacheProvider(...)`.
* The five and six argument constructors of `APICredentials`. Use `APICredentials.builder()`.

## Worth switching to

Nothing here is required, but these replace older ways of doing the same:

| Instead of | Use |
| :--------- | :-- |
| `new APICredentials(lol, tournament, tft, lor, val)` | `APICredentials.builder().lol(..).tft(..).build()` |
| `DataCall.setCacheProvider(..)` and the other static settings | `api.getContext().setCacheProvider(..)` |
| Your own thread pool around the api methods | The `...Async` twin of every api method, which returns a `CompletableFuture` without a thread waiting for the call |
| One set of keys per JVM | `R4J.isolated(credentials)`, an instance with its own keys, cache, settings and limiters |
| `MatchParticipant.getChallenges()` (a map) | `MatchParticipant.getTypedChallenges()` |
| Catching each exception type | `R4JException` |
