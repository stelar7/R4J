package no.stelar7.api.l4j8.impl.lol.liveclient;

import com.google.gson.JsonElement;
import no.stelar7.api.l4j8.basic.calling.DataCallBuilder;
import no.stelar7.api.l4j8.basic.constants.api.*;
import no.stelar7.api.l4j8.basic.exceptions.APIResponseException;
import no.stelar7.api.l4j8.basic.utils.Pair;
import no.stelar7.api.l4j8.pojo.replay.ReplayTeamType;

public class LiveClientDataAPI
{
    public static JsonElement getGameData()
    {
        try
        {
            Object data = new DataCallBuilder()
                    .withLimiters(false)
                    .withProxy(Constants.CLIENT_PROXY)
                    .withEndpoint(URLEndpoint.LIVECLIENT_GAME_DATA)
                    .withRequestMethod("GET")
                    .build();
            
            if (data instanceof Pair)
            {
                return null;
            }
            
            return (JsonElement) data;
            
        } catch (APIResponseException e)
        {
            return null;
        }
    }
    
    public static JsonElement getEventData()
    {
        try
        {
            Object data = new DataCallBuilder()
                    .withLimiters(false)
                    .withProxy(Constants.CLIENT_PROXY)
                    .withEndpoint(URLEndpoint.LIVECLIENT_EVENT_DATA)
                    .withRequestMethod("GET")
                    .build();
            
            if (data instanceof Pair)
            {
                return null;
            }
            
            return (JsonElement) data;
        } catch (APIResponseException e)
        {
            return null;
        }
    }
    
    public static JsonElement getGameStats()
    {
        try
        {
            Object data = new DataCallBuilder()
                    .withLimiters(false)
                    .withProxy(Constants.CLIENT_PROXY)
                    .withEndpoint(URLEndpoint.LIVECLIENT_GAME_STATS)
                    .withRequestMethod("GET")
                    .build();
            
            
            if (data instanceof Pair)
            {
                return null;
            }
            
            return (JsonElement) data;
            
        } catch (APIResponseException e)
        {
            return null;
        }
    }
    
    
    public static JsonElement getActivePlayer()
    {
        try
        {
            Object data = new DataCallBuilder()
                    .withLimiters(false)
                    .withProxy(Constants.CLIENT_PROXY)
                    .withEndpoint(URLEndpoint.LIVECLIENT_ACTIVE_PLAYER)
                    .withRequestMethod("GET")
                    .build();
            
            if (data instanceof Pair)
            {
                return null;
            }
            
            return (JsonElement) data;
            
        } catch (APIResponseException e)
        {
            return null;
        }
    }
    
    public static JsonElement getActivePlayerAbilities()
    {
        try
        {
            Object data = new DataCallBuilder()
                    .withLimiters(false)
                    .withProxy(Constants.CLIENT_PROXY)
                    .withEndpoint(URLEndpoint.LIVECLIENT_ACTIVE_PLAYER_ABILITIES)
                    .withRequestMethod("GET")
                    .build();
            
            if (data instanceof Pair)
            {
                return null;
            }
            
            return (JsonElement) data;
            
        } catch (APIResponseException e)
        {
            return null;
        }
    }
    
    public static JsonElement getActivePlayerRunes()
    {
        try
        {
            Object data = new DataCallBuilder()
                    .withLimiters(false)
                    .withProxy(Constants.CLIENT_PROXY)
                    .withEndpoint(URLEndpoint.LIVECLIENT_ACTIVE_PLAYER_RUNES)
                    .withRequestMethod("GET")
                    .build();
            
            if (data instanceof Pair)
            {
                return null;
            }
            
            return (JsonElement) data;
            
        } catch (APIResponseException e)
        {
            return null;
        }
    }
    
    public static JsonElement getActivePlayerName()
    {
        try
        {
            Object data = new DataCallBuilder()
                    .withLimiters(false)
                    .withProxy(Constants.CLIENT_PROXY)
                    .withEndpoint(URLEndpoint.LIVECLIENT_ACTIVE_PLAYER_NAME)
                    .withRequestMethod("GET")
                    .build();
            
            if (data instanceof Pair)
            {
                return null;
            }
            
            return (JsonElement) data;
            
        } catch (APIResponseException e)
        {
            return null;
        }
    }
    
    public static JsonElement getPlayerList(ReplayTeamType team)
    {
        try
        {
            Object data = new DataCallBuilder()
                    .withLimiters(false)
                    .withProxy(Constants.CLIENT_PROXY)
                    .withEndpoint(URLEndpoint.LIVECLIENT_PLAYER_LIST)
                    .withQueryParameter("teamID", team.getValue())
                    .withRequestMethod("GET")
                    .build();
            
            if (data instanceof Pair)
            {
                return null;
            }
            
            return (JsonElement) data;
            
        } catch (APIResponseException e)
        {
            return null;
        }
    }
    
    public static JsonElement getPlayerMainRunes(String summoner)
    {
        try
        {
            Object data = new DataCallBuilder()
                    .withLimiters(false)
                    .withProxy(Constants.CLIENT_PROXY)
                    .withEndpoint(URLEndpoint.LIVECLIENT_PLAYER_RUNES)
                    .withQueryParameter("summonerName", summoner)
                    .withRequestMethod("GET")
                    .build();
            
            if (data instanceof Pair)
            {
                return null;
            }
            
            return (JsonElement) data;
        } catch (APIResponseException e)
        {
            return null;
        }
    }
    
    public static JsonElement getPlayerItems(String summoner)
    {
        try
        {
            Object data = new DataCallBuilder()
                    .withLimiters(false)
                    .withProxy(Constants.CLIENT_PROXY)
                    .withEndpoint(URLEndpoint.LIVECLIENT_PLAYER_ITEMS)
                    .withQueryParameter("summonerName", summoner)
                    .withRequestMethod("GET")
                    .build();
            
            if (data instanceof Pair)
            {
                return null;
            }
            
            return (JsonElement) data;
            
        } catch (APIResponseException e)
        {
            return null;
        }
    }
    
    public static JsonElement getPlayerScores(String summoner)
    {
        try
        {
            Object data = new DataCallBuilder()
                    .withLimiters(false)
                    .withProxy(Constants.CLIENT_PROXY)
                    .withEndpoint(URLEndpoint.LIVECLIENT_PLAYER_SCORES)
                    .withQueryParameter("summonerName", summoner)
                    .withRequestMethod("GET")
                    .build();
            
            if (data instanceof Pair)
            {
                return null;
            }
            
            return (JsonElement) data;
        } catch (APIResponseException e)
        {
            return null;
        }
    }
    
    public static JsonElement getPlayerSummonerSpells(String summoner)
    {
        try
        {
            Object data = new DataCallBuilder()
                    .withLimiters(false)
                    .withProxy(Constants.CLIENT_PROXY)
                    .withEndpoint(URLEndpoint.LIVECLIENT_PLAYER_SUMMONERS)
                    .withQueryParameter("summonerName", summoner)
                    .withRequestMethod("GET")
                    .build();
            
            if (data instanceof Pair)
            {
                return null;
            }
            
            return (JsonElement) data;
        } catch (APIResponseException e)
        {
            return null;
        }
    }
}
