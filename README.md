[![](https://jitpack.io/v/stelar7/L4J8.svg)](https://jitpack.io/#stelar7/L4J8)

##### L4J8 isn't endorsed by Riot Games and doesn't reflect the views or opinions of Riot Games or anyone officially involved in producing or managing League of Legends. League of Legends and Riot Games are trademarks or registered trademarks of Riot Games, Inc. League of Legends © Riot Games, Inc.

## How do I use it?

Check out one of the many [tests](https://github.com/stelar7/L4J8/tree/master/src/test/java/no/stelar7/api/l4j8/tests), as they provide good examples.

##### What features does it have?

| Type           | Supported |
| :------------: | :-------: |
| V3 compatible?            | Yes!      |
| Rate-limiting? | Set dynamically based on your key!      |
| Cache?         | Working on it!     |


## How do I download?

For maven / gradle / sbt / leiningen:  [Jitpack](https://jitpack.io/#stelar7/L4J8/)
 
 
## How do I async?
Wrap the method-call in `CompletableFuture`

```Java 
CompletableFuture.supplyAsync(() -> l4j8.getSummonerAPI().getSummonerByAccount(Platform.EUW1, Constants.TEST_ACCOUNT_IDS[0])).thenAccept(this::handleSummonerCallback);

private void handleSummonerCallback(Summoner u)
{
    System.out.println(u);
}
```

[A more concrete example here](https://github.com/stelar7/L4J8/blob/master/src/test/java/no/stelar7/api/l4j8/tests/async/AsyncTest.java)
