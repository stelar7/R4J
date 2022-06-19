[![](https://jitpack.io/v/stelar7/R4J.svg)](https://jitpack.io/#stelar7/R4J) [![Build Status](https://travis-ci.org/stelar7/R4J.svg?branch=master)](https://travis-ci.org/stelar7/R4J)

##### R4J isn't endorsed by Riot Games and doesn't reflect the views or opinions of Riot Games or anyone officially involved in producing or managing League of Legends. League of Legends and Riot Games are trademarks or registered trademarks of Riot Games, Inc. League of Legends © Riot Games, Inc.

## How do I use it?

Check out one of the many [tests](https://github.com/stelar7/R4J/tree/master/src/test/java/no/stelar7/api/r4j/tests), as they provide good examples.

##### What features does it have?

| Type           | Supported |
| :------------: | :-------: |
| V5 compatible?            | Yes!      |
| Rate-limiting? | Set dynamically based on your key!      |

| Cache           | Supported |
| :------------: | :-------: |
| FileSystem            | Yes!      |
| Memory            | Partly...     |
| SQL         | Not yet :(     |


## How do I download?

For maven / gradle / sbt / leiningen:  [Jitpack](https://jitpack.io/#stelar7/R4J/)  
For a JAR file: [Releases](https://github.com/stelar7/R4J/releases/)

##### Javadoc?
[Click here](http://stelar7.no/l4j8)
 
 
## How do I async?
Wrap the method-call in `CompletableFuture`

```Java 
CompletableFuture.supplyAsync(() -> api.getSummonerAPI().getSummonerByAccount(Platform.EUW1, Constants.TEST_ACCOUNT_IDS[0])).thenAccept(this::handleSummonerCallback);

private void handleSummonerCallback(Summoner u)
{
    System.out.println(u);
}
```

[more examples here](https://github.com/stelar7/R4J/tree/master/src/test/java/no/stelar7/api/r4j/tests/async/AsyncTest.java)


#### TODO:
* Add ALL methods to MemoryCacheProvider
* Add support for sql storage (hibernate?)  
* Add ddragon as an alternative to static-data
