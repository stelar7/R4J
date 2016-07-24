package no.stelar7.api.l4j8.tests.currentgame;

import java.util.*;

import org.junit.*;
import org.junit.runners.*;

import no.stelar7.api.l4j8.basic.*;
import no.stelar7.api.l4j8.basic.DataCall.*;
import no.stelar7.api.l4j8.basic.constants.api.*;
import no.stelar7.api.l4j8.pojo.currentgame.*;
import no.stelar7.api.l4j8.pojo.featuredgames.*;
import no.stelar7.api.l4j8.pojo.summoner.*;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CurrentGameTest
{

    DataCallBuilder featuredbuilder = DataCall.builder();
    DataCallBuilder currentbuilder  = DataCall.builder();
    DataCallBuilder idbuilder       = DataCall.builder();

    @Test
    public void doTest()
    {
        // Get a game in progess
        final FeaturedGameInfo game = ((FeaturedGames) this.featuredbuilder.build()).getGameList().get(0);

        // get a summoner from that game
        final String name = game.getParticipants().get(0).getSummonerName();

        final String parsedName = Utils.prepareForURL(name);
        this.idbuilder.withURLData("{summonerName}", parsedName);

        final Object data = this.idbuilder.build();
        final Map<String, Summoner> datamap = ((Map<String, Summoner>) data);

        // get the summoners id
        final Long id = datamap.get(parsedName).getId();

        this.currentbuilder.withURLData("{summonerId}", id.toString());
        this.currentbuilder.withURLData("{platformId}", game.getPlatform().get().getCode());

        // our ongoing game
        final CurrentGameInfo currentGame = (CurrentGameInfo) this.currentbuilder.build();

        Assert.assertNotNull("bannedchampion is null", currentGame.getBannedChampions());
        Assert.assertNotNull("gameid is null", currentGame.getGameId());
        Assert.assertNotNull("gamelength is null", currentGame.getGameLength());
        Assert.assertNotNull("gamemode is null", currentGame.getGameMode());
        Assert.assertNotNull("gamequeuecongifid is null", currentGame.getGameQueueConfigId());
        Assert.assertNotNull("gamestarttime is null", currentGame.getGameStartTime());
        Assert.assertNotNull("gametype is null", currentGame.getGameType());
        Assert.assertNotNull("mapid is null", currentGame.getMapId());
        Assert.assertNotNull("observers is null", currentGame.getObservers());
        Assert.assertNotNull("participantsis null", currentGame.getParticipants());

        Assert.assertNotNull("GAMEMODE is null", currentGame.getGameMode());
        Assert.assertNotNull("GAMEQUEUETYPE is null", currentGame.getGameQueueType());
        Assert.assertNotNull("GAMETYPE is null", currentGame.getGameType());
        Assert.assertNotNull("PLATFORM is null", currentGame.getPlatform());
        Assert.assertNotNull("GAMESTARTTIME is null", currentGame.getGameStartTimeAsDate());

        Assert.assertEquals("Timestamp doesnt match TIMESTAMP", currentGame.getGameStartTime(), (Long) currentGame.getGameStartTimeAsDate().toInstant().toEpochMilli());
        Assert.assertEquals("lane doesnt match LANE", currentGame.getGameModeId(), currentGame.getGameMode().get().getCode());
        Assert.assertEquals("queue doesnt match QUEUE", currentGame.getGameQueueConfigId(), currentGame.getGameQueueType().get().getCode());
        Assert.assertEquals("role doesnt match ROLE", currentGame.getGameTypeId(), currentGame.getGameType().get().getCode());
        Assert.assertEquals("season doesnt match SEASON", currentGame.getMapId(), currentGame.getMap().get().getCode());
        Assert.assertEquals("region doesnt match REGION", currentGame.getPlatformId(), currentGame.getPlatform().get().getCode());

    }

    @Before
    public void init()
    {
        this.currentbuilder.withServer(Server.EUW);
        this.featuredbuilder.withServer(Server.EUW);
        this.idbuilder.withServer(Server.EUW);

        this.currentbuilder.withRegion(Server.EUW);
        this.featuredbuilder.withRegion(Server.EUW);
        this.idbuilder.withRegion(Server.EUW);

        this.currentbuilder.withEndpoint(URLEndpoint.CURRENTGAME);
        this.featuredbuilder.withEndpoint(URLEndpoint.FEATUREDGAME);
        this.idbuilder.withEndpoint(URLEndpoint.SUMMONER_BY_NAME);

    }

}
