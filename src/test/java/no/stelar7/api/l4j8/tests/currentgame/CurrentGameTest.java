package no.stelar7.api.l4j8.tests.currentgame;

import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import no.stelar7.api.l4j8.basic.DataCall;
import no.stelar7.api.l4j8.basic.DataCall.DataCallBuilder;
import no.stelar7.api.l4j8.basic.Server;
import no.stelar7.api.l4j8.basic.URLEndpoint;
import no.stelar7.api.l4j8.basic.Utils;
import no.stelar7.api.l4j8.pojo.currentgame.CurrentGameInfo;
import no.stelar7.api.l4j8.pojo.featuredgames.FeaturedGameInfo;
import no.stelar7.api.l4j8.pojo.summoner.Summoner;
import no.stelar7.api.l4j8.tests.SecretFile;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CurrentGameTest
{

    DataCallBuilder featuredbuilder = DataCall.builder();
    DataCallBuilder currentbuilder  = DataCall.builder();
    DataCallBuilder idbuilder       = DataCall.builder();

    @Before
    public void init()
    {
        this.currentbuilder.withAPIKey(SecretFile.API_KEY);
        this.featuredbuilder.withAPIKey(SecretFile.API_KEY);
        this.idbuilder.withAPIKey(SecretFile.API_KEY);

        this.currentbuilder.withServer(Server.EUW);
        this.featuredbuilder.withServer(Server.EUW);
        this.idbuilder.withServer(Server.EUW);

        this.currentbuilder.withEndpoint(URLEndpoint.CURRENTGAME);
        this.featuredbuilder.withEndpoint(URLEndpoint.FEATUREDGAME);
        this.idbuilder.withEndpoint(URLEndpoint.SUMMONER_BY_NAME);

    }

    @Test
    public void doTest()
    {
        // Get a game in progess
        FeaturedGameInfo game = ((List<FeaturedGameInfo>) featuredbuilder.build().getValue()).get(0);

        // get a summoner from that game
        String name = game.getParticipants().get(0).getSummonerName();
        
        String parsedName = Utils.prepareForURL(name);
        idbuilder.withURLData("{summonerName}", parsedName);

        Object data = idbuilder.build().getValue();
        Map<String, Summoner> datamap = ((Map<String, Summoner>) data);

        // get the summoners id
        Long id = datamap.get(parsedName).getId();

        this.currentbuilder.withURLData("{summonerId}", id.toString());
        this.currentbuilder.withURLData("{platformId}", game.getPlatform().getCode());

        // our ongoing game
        CurrentGameInfo currentGame = (CurrentGameInfo) currentbuilder.build().getValue();

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
        Assert.assertEquals("lane doesnt match LANE", currentGame.getGameModeId(), currentGame.getGameMode().getCode());
        Assert.assertEquals("queue doesnt match QUEUE", currentGame.getGameQueueConfigId(), currentGame.getGameQueueType().getCode());
        Assert.assertEquals("role doesnt match ROLE", currentGame.getGameTypeId(), currentGame.getGameType().getCode());
        Assert.assertEquals("season doesnt match SEASON", currentGame.getMapId(), currentGame.getMap().getCode());
        Assert.assertEquals("region doesnt match REGION", currentGame.getPlatformId(), currentGame.getPlatform().getCode());

    }

}
