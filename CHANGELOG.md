# Changelog

## 3.0.0

### Upgrading from 2.x

Most code keeps working unchanged: `new R4J(credentials)`, the static methods on `DataCall`, the `getInstance()` of the api classes and the builders all behave as before. These are the things that can affect you.

**You have to act if:**

* **You run on Java 8.** Java 11 is now the minimum.
* **You use the rate limiter classes directly.** `BurstRateLimiter` and `CounterRateLimiter` are gone, and `RateLimiter` has a new api (`tryAcquire`, `release`, `updateLimits`, `syncWithServerCounts`, `cooldown`, `getCallCounts`). `DataCallBuilder.getLock` and `getGlobalLock` are gone. If you only use R4J through its api classes, nothing changes for you.
* **You use `MemoryCacheProvider` with a time to live.** It is now in milliseconds, like the other caches (the old javadoc said seconds, but the cache did not work at all before).
* **You use `MySQLCacheProvider`.** Its databases are now named `r4j_<hash>` instead of after the raw api key, and existing ones are not migrated. It is also deprecated, see below.
* **You use `LCUSocketReader`.** The websocket library it needs (`com.neovisionaries:nv-websocket-client:2.14`) is now an optional dependency, so it is no longer pulled in for everyone. Add it to your own project.
* **You relied on the Travis build uploading jars.** The Travis configuration is gone (the service was shut down), and nothing builds or uploads release jars automatically anymore.

**Behaviour that changed:**

* **Timeouts.** Calls used to wait forever by default. They now give up after 10 seconds connecting and 30 seconds reading, unless you set a timeout (`DataCall.setGlobalTimeout`).
* **TLS.** Certificate checks used to be switched off for the whole JVM as a side effect of loading R4J. They are now only relaxed for connections to the local game client. If your own code talked to servers with invalid certificates and only worked because of R4J, it will now fail.
* **Unknown enum values.** A queue, map or similar that R4J does not know yet no longer throws `APIEnumNotUpToDateException`. It becomes the `UNKNOWN`/`UNDEFINED` constant of the enum (or `null` if there is none), and a warning is logged once. `GenericEnumSerializer.setStrict(true)` brings the exception back.
* **Query parameters are url-encoded.** If you built calls with `DataCallBuilder.withQueryParameter` and encoded the values yourself, remove your encoding.
* **`build()` on a 429.** After five consecutive application or method 429s a call now throws `APIResponseException` instead of retrying forever.
* **Console output.** Everything R4J printed to `System.out`/`System.err` now goes through slf4j.
* **File cache.** Items expire by age (time since they were stored) instead of by last access, and `TTL_INFINITY` now really means never (it used to delete everything). New files are json where possible; old cache folders can still be read.
* **Tiered cache.** A hit no longer refreshes the tier that had the data, so cached data expires even when it is read often.
* **Lazy lists.** `size()` and `isEmpty()` on a lazily loaded list (ie. from `getMatchList`) now load what they need to answer, instead of reporting what happened to be loaded. `size()` on a long match history therefore makes all the calls needed to count it; use `loadedSize()` for the old behaviour.
* **File cache cleanup.** Expired files and empty folders are now removed every hour (or more often with a short time to live), also with the default lifetimes.
* **Tests.** `mvn test` only runs the tests that need no api key. Use `mvn test -Plive` for all of them.

**Deprecated, and scheduled for removal in 4.0.0:**

Everything below still works as it did (as far as Riot still answers), but is marked `@Deprecated(since = "3.0.0", forRemoval = true)`, so your compiler warns where you use it.

| What | Why | Use instead |
| :--- | :-- | :---------- |
| `ThirdPartyAPI`, `ThirdPartyCodeBuilder`, `Summoner.getThirdPartyCode()` | third-party-code is no longer in the Riot api documentation | RSO, to prove an account belongs to a player |
| `SpectatorAPI.getFeaturedGames` | featured-games is no longer in the Riot api documentation | nothing |
| `MatchV5API.getMatchListReplays(server, puuid)`, `ReplayBuilder` | replaced by an endpoint that needs the access token of the player | `MatchV5API.getMatchReplaysByAccessToken` |
| `LeagueAPI.getLeague(server, leagueId)`, `TFTLeagueAPI.getLeague(server, leagueId)` | Riot is retiring league ids | the league entries of a player, or the tier and division lists |
| `ClashAPI.getPlayerInfo(server, summonerId)` | summoner ids are no longer handed out | `ClashAPI.getPlayerInfoByPuuid` |
| `ImageAPI.getProfileIcon(region, summonerName)` | the service behind it no longer exists | `ImageAPI.getProfileIcon(iconId, version)` |
| `MySQLCacheProvider`, `MongoDBCacheProvider` | never finished | `JdbcCacheProvider` |
| `RealmSpesificEnum`, `RealmSpesificEnumSerializer` | the name was misspelled | `RealmSpecificEnum`, `RealmSpecificEnumSerializer` |

**Deprecated, without a removal date:**

* The static settings on `DataCall` (`setCacheProvider`, `setCredentials`, `setGlobalTimeout`, `setDefaultMaxSleep`, ...). They still configure the default context. Use the context itself: `api.getContext().setCacheProvider(...)`.
* The five and six argument constructors of `APICredentials`. Use `APICredentials.builder()`.

### New

* **Endpoints:** TFT status, TFT spectator, TFT rated ladder and the queue filter on TFT leagues; `league-exp-v4`; tournament games by code; Valorant console match and ranked; Valorant accolades; Riftbound content; the RSO endpoints (`accounts/me`, `summoners/me`, `lol-rso-match-v1`, `matches/replays`).
* **Fields:** the responses of champion mastery, challenges, status, LoR match, match-v5, TFT match and the Valorant match, leaderboard and content apis were brought in line with the documentation.
* **Several instances:** `R4J.isolated(credentials)` gives an instance with its own keys, cache, settings and limiters. Objects it returns (a `Summoner`, a match) keep using it when they fetch more data.
* **Sharing an api key between processes:** works without setup by following the call counts the api reports. For exact limits, keep the counters in a shared store with `R4JContext.setRateLimitStore`: `RedisRateLimitStore`, `JdbcRateLimitStore`, or your own `SharedRateLimitStore`.
* **CommunityDragon:** `CDragonAPI` reads champion data and builds asset urls (splash art, tiles, portraits, ability icons, champion select sounds, wards, profile icons) from the CommunityDragon CDN. It is community-run, not Riot, and is only contacted when you call it.
* **Tournament callbacks:** `TournamentCallbackListener` receives the result Riot posts after a tournament game.
* **Caches:** `MemoryCacheProvider` works; `JdbcCacheProvider` caches in any SQL database; the file cache can be shared by several processes.
* **Riftbound key** in `APICredentials`, and `APICredentials.builder()` to name each key instead of passing five or six strings in the right order (those constructors are deprecated).
* **Non-blocking calls:** `DataCallBuilder.buildAsync`, `buildAsAsync` and `buildCachedAsync` make a call without a thread waiting for the rate limit, the response or the retries. `R4J.async(...)` runs any of the (blocking) api methods in the background.
* **One exception parent:** every exception R4J throws on purpose now extends `R4JException` (itself a `RuntimeException`), so they can be caught together.
* **Module name:** the jars declare `Automatic-Module-Name: no.stelar7.r4j`.
* **Typed match challenges:** `MatchParticipant.getTypedChallenges()` and `getTypedMissions()` give the documented challenges and mission scores as objects with a field each. The maps (`getChallenges()`, `getMissions()`) are unchanged and still hold everything the api sent.
* **Proxy support:** `R4JContext.setRequestUrl` sends every call to the Riot api through a url of your choice.
* **Nullability:** the api methods that return null when nothing is found are marked `@Nullable` (JetBrains annotations), so IntelliJ warns about unchecked use and Kotlin sees them as nullable types. The methods that return a list return an empty list instead, as before.
* **Typed calls:** `DataCallBuilder.buildAs` and `buildCached`, and `withAccessToken` for RSO.

### Fixed

* Retries could deadlock when a limit on parallel calls was set, and held their permit while sleeping.
* Waiting on a rate limit on one platform blocked the same endpoint on every other platform.
* A call waiting for a rate limit kept other endpoints of its platform from being called when a limit on parallel calls was set.
* A burst of calls made before the first response arrived was not rate limited.
* Request bodies with characters outside ASCII were corrupted.
* Tournament code lookup and update always called a url that does not exist.
* A 405 was reported as a 403, connection errors lost their cause, and a 429 on a call without limiters threw a `NullPointerException`.
* The api key was written to the log at trace level.
* The connection was not reused after a 429.
* With a shared rate limit store, calls to the same endpoint waited for each other while the store was being asked.
* `MySQLCacheProvider` terminated the JVM on every lookup.
* `ImageAPI` built loading screen art urls that do not exist, and rune and mastery icon urls that only work for an old patch. `ImageAPI.getProfileIcon(region, summonerName)` is deprecated, as the service behind it is gone.
* A lazily loaded list (ie. from `getMatchList`) claimed to be empty until it was iterated (#112).
* The file cache kept growing, because expired files were only removed when asked for again and folders were never removed (#61).
