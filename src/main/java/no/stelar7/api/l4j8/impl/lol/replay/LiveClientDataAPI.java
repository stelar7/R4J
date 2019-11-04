package no.stelar7.api.l4j8.impl.lol.replay;

import com.google.gson.JsonElement;
import no.stelar7.api.l4j8.basic.calling.DataCallBuilder;
import no.stelar7.api.l4j8.basic.constants.api.*;
import no.stelar7.api.l4j8.basic.exceptions.APIResponseException;
import no.stelar7.api.l4j8.pojo.replay.ReplayTeamType;

public class LiveClientDataAPI
{
    public static JsonElement getEventData()
    {
        try
        {
            return (JsonElement) new DataCallBuilder()
                    .withLimiters(false)
                    .withProxy(Constants.RECORDING_PROXY)
                    .withEndpoint(URLEndpoint.REPLAY_EVENT_DATA)
                    .withRequestMethod("GET")
                    .build();
        } catch (APIResponseException e)
        {
            return null;
        }
    }
    
    public static JsonElement getGameStats()
    {
        try
        {
            return (JsonElement) new DataCallBuilder()
                    .withLimiters(false)
                    .withProxy(Constants.RECORDING_PROXY)
                    .withEndpoint(URLEndpoint.REPLAY_GAME_STATS)
                    .withRequestMethod("GET")
                    .build();
        } catch (APIResponseException e)
        {
            return null;
        }
    }
    
    
    public static JsonElement getActivePlayer()
    {
        try
        {
            return (JsonElement) new DataCallBuilder()
                    .withLimiters(false)
                    .withProxy(Constants.RECORDING_PROXY)
                    .withEndpoint(URLEndpoint.REPLAY_ACTIVE_PLAYER)
                    .withRequestMethod("GET")
                    .build();
        } catch (APIResponseException e)
        {
            return null;
        }
    }
    
    public static JsonElement getActivePlayerAbilities()
    {
        try
        {
            return (JsonElement) new DataCallBuilder()
                    .withLimiters(false)
                    .withProxy(Constants.RECORDING_PROXY)
                    .withEndpoint(URLEndpoint.REPLAY_ACTIVE_PLAYER_ABILITIES)
                    .withRequestMethod("GET")
                    .build();
        } catch (APIResponseException e)
        {
            return null;
        }
    }
    
    public static JsonElement getActivePlayerRunes()
    {
        try
        {
            return (JsonElement) new DataCallBuilder()
                    .withLimiters(false)
                    .withProxy(Constants.RECORDING_PROXY)
                    .withEndpoint(URLEndpoint.REPLAY_ACTIVE_PLAYER_RUNES)
                    .withRequestMethod("GET")
                    .build();
        } catch (APIResponseException e)
        {
            return null;
        }
    }
    
    public static JsonElement getActivePlayerName()
    {
        try
        {
            return (JsonElement) new DataCallBuilder()
                    .withLimiters(false)
                    .withProxy(Constants.RECORDING_PROXY)
                    .withEndpoint(URLEndpoint.REPLAY_ACTIVE_PLAYER_NAME)
                    .withRequestMethod("GET")
                    .build();
        } catch (APIResponseException e)
        {
            return null;
        }
    }
    
    public static JsonElement getPlayerList(ReplayTeamType team)
    {
        try
        {
            return (JsonElement) new DataCallBuilder()
                    .withLimiters(false)
                    .withProxy(Constants.RECORDING_PROXY)
                    .withEndpoint(URLEndpoint.REPLAY_PLAYER_LIST)
                    .withURLData("teamID", team.getValue())
                    .withRequestMethod("GET")
                    .build();
        } catch (APIResponseException e)
        {
            return null;
        }
    }
    
    public static JsonElement getPlayerMainRunes(String summoner)
    {
        try
        {
            return (JsonElement) new DataCallBuilder()
                    .withLimiters(false)
                    .withProxy(Constants.RECORDING_PROXY)
                    .withEndpoint(URLEndpoint.REPLAY_PLAYER_RUNES)
                    .withURLData("summonerName", summoner)
                    .withRequestMethod("GET")
                    .build();
        } catch (APIResponseException e)
        {
            return null;
        }
    }
    
    public static JsonElement getPlayerItems(String summoner)
    {
        try
        {
            return (JsonElement) new DataCallBuilder()
                    .withLimiters(false)
                    .withProxy(Constants.RECORDING_PROXY)
                    .withEndpoint(URLEndpoint.REPLAY_PLAYER_ITEMS)
                    .withURLData("summonerName", summoner)
                    .withRequestMethod("GET")
                    .build();
        } catch (APIResponseException e)
        {
            return null;
        }
    }
    
    public static JsonElement getPlayerScores(String summoner)
    {
        try
        {
            return (JsonElement) new DataCallBuilder()
                    .withLimiters(false)
                    .withProxy(Constants.RECORDING_PROXY)
                    .withEndpoint(URLEndpoint.REPLAY_PLAYER_SCORES)
                    .withURLData("summonerName", summoner)
                    .withRequestMethod("GET")
                    .build();
        } catch (APIResponseException e)
        {
            return null;
        }
    }
    
    public static JsonElement getPlayerSummonerSpells(String summoner)
    {
        try
        {
            return (JsonElement) new DataCallBuilder()
                    .withLimiters(false)
                    .withProxy(Constants.RECORDING_PROXY)
                    .withEndpoint(URLEndpoint.REPLAY_PLAYER_SUMMONERS)
                    .withURLData("summonerName", summoner)
                    .withRequestMethod("GET")
                    .build();
        } catch (APIResponseException e)
        {
            return null;
        }
    }
}
