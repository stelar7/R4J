[![](https://jitpack.io/v/stelar7/R4J.svg)](https://jitpack.io/#stelar7/R4J) [![Build](https://github.com/stelar7/R4J/actions/workflows/build.yml/badge.svg)](https://github.com/stelar7/R4J/actions/workflows/build.yml)

##### R4J isn't endorsed by Riot Games and doesn't reflect the views or opinions of Riot Games or anyone officially involved in producing or managing League of Legends. League of Legends and Riot Games are trademarks or registered trademarks of Riot Games, Inc. League of Legends © Riot Games, Inc.

## How do I use it?

```Java
R4J api = new R4J(new APICredentials("RGAPI-your-key"));

RiotAccount account  = api.getAccountAPI().getAccountByTag(RegionShard.EUROPE, "stelar7", "STL7");
Summoner    summoner = api.getLoLAPI().getSummonerAPI().getSummonerByPUUID(LeagueShard.EUW1, account.getPUUID());
```

Check out one of the many [tests](https://github.com/stelar7/R4J/tree/master/src/test/java/no/stelar7/api/r4j/tests), as they provide good examples.

##### What features does it have?

| Type           | Supported |
| :------------: | :-------: |
| V5 compatible? | Yes!      |
| Rate-limiting? | Set dynamically based on your key, and synced with the counts the api reports |
| RSO endpoints? | Yes, pass the access token of the player |

| Cache      | Supported |
| :--------: | :-------: |
| FileSystem | Yes! (the default) |
| Memory     | Yes!      |
| Tiered     | Yes! (ie. memory in front of filesystem) |
| SQL        | Not yet :( |

```Java
DataCall.setCacheProvider(new TieredCacheProvider(new MemoryCacheProvider(), new FileSystemCacheProvider()));
```

## How do I download?

For maven / gradle / sbt / leiningen:  [Jitpack](https://jitpack.io/#stelar7/R4J/)  
For a JAR file: [Releases](https://github.com/stelar7/R4J/releases/)

##### Javadoc?
[Click here](http://stelar7.no/l4j8)


## How do I async?
Wrap the method-call in `CompletableFuture`

```Java
CompletableFuture.supplyAsync(() -> api.getLoLAPI().getSummonerAPI().getSummonerByPUUID(LeagueShard.EUW1, puuid))
                 .thenAccept(this::handleSummonerCallback);

private void handleSummonerCallback(Summoner u)
{
    System.out.println(u);
}
```

To limit how many calls run at the same time (per platform and key), pass the limit when creating the api: `new R4J(creds, 4)`.

## How do I build and test?

```
mvn clean package         # builds, and runs the tests that need no api key
mvn test -Plive           # also runs the tests that call the real api (put your keys in SecretFile)
```

#### TODO:
* Add support for sql storage
