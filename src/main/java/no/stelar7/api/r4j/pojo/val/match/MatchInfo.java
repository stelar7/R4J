package no.stelar7.api.r4j.pojo.val.match;

import no.stelar7.api.r4j.basic.constants.api.regions.ValorantShard;
import no.stelar7.api.r4j.basic.constants.types.val.*;
import no.stelar7.api.r4j.basic.utils.sql.*;
import no.stelar7.api.r4j.impl.val.VALContentAPI;
import no.stelar7.api.r4j.pojo.val.content.ContentItem;

import java.io.Serializable;
import java.util.*;

public class MatchInfo implements Serializable
{
    private static final long serialVersionUID = -5536569302071536933L;
    
    private String               matchId;
    private String               mapId;
    private long                 gameLengthMillis;
    private long                 gameStartMillis;
    private ProvisioningFlowType provisioningFlowId;
    private boolean              isCompleted;
    private String               customGameName;
    private GameQueueType        queueId;
    private String               gameMode;
    private boolean              isRanked;
    private Season               seasonId;
    
    public String getMatchId()
    {
        return matchId;
    }
    
    public String getMap()
    {
        return mapId;
    }
    
    public Optional<ContentItem> getMapAsContent()
    {
        return VALContentAPI.getInstance()
                            .getContent(ValorantShard.EU, Optional.empty())
                            .getMaps()
                            .stream()
                            .filter(c -> c.getAssetPath().equalsIgnoreCase(this.mapId))
                            .findFirst();
    }
    
    public long getGameLengthMillis()
    {
        return gameLengthMillis;
    }
    
    public long getGameStartMillis()
    {
        return gameStartMillis;
    }
    
    public ProvisioningFlowType getProvisioningFlow()
    {
        return provisioningFlowId;
    }
    
    public boolean getCompleted()
    {
        return isCompleted;
    }
    
    public String getCustomGameName()
    {
        return customGameName;
    }
    
    public GameQueueType getGameQueue()
    {
        return queueId;
    }
    
    public String getGameMode()
    {
        return gameMode;
    }
    
    public Optional<ContentItem> getGameModeAsContent()
    {
        return VALContentAPI.getInstance()
                            .getContent(ValorantShard.EU, Optional.empty())
                            .getGameModes()
                            .stream()
                            .filter(c -> c.getAssetPath().equalsIgnoreCase(this.gameMode))
                            .findFirst();
    }
    
    public boolean getRanked()
    {
        return isRanked;
    }
    
    public Season getSeason()
    {
        return seasonId;
    }
    
    @Override
    public boolean equals(Object o)
    {
        if (this == o)
        {
            return true;
        }
        if (o == null || getClass() != o.getClass())
        {
            return false;
        }
        MatchInfo matchInfo = (MatchInfo) o;
        return Objects.equals(matchId, matchInfo.matchId) &&
               Objects.equals(mapId, matchInfo.mapId) &&
               Objects.equals(gameLengthMillis, matchInfo.gameLengthMillis) &&
               Objects.equals(gameStartMillis, matchInfo.gameStartMillis) &&
               Objects.equals(provisioningFlowId, matchInfo.provisioningFlowId) &&
               Objects.equals(isCompleted, matchInfo.isCompleted) &&
               Objects.equals(customGameName, matchInfo.customGameName) &&
               Objects.equals(queueId, matchInfo.queueId) &&
               Objects.equals(gameMode, matchInfo.gameMode) &&
               Objects.equals(isRanked, matchInfo.isRanked) &&
               Objects.equals(seasonId, matchInfo.seasonId);
    }
    
    @Override
    public int hashCode()
    {
        return Objects.hash(matchId, mapId, gameLengthMillis, gameStartMillis, provisioningFlowId, isCompleted, customGameName, queueId, gameMode, isRanked, seasonId);
    }
    
    @Override
    public String toString()
    {
        return "MatchInfo{" +
               "matchId='" + matchId + '\'' +
               ", mapId='" + mapId + '\'' +
               ", gameLengthMillis=" + gameLengthMillis +
               ", gameStartMillis=" + gameStartMillis +
               ", provisioningFlowId='" + provisioningFlowId + '\'' +
               ", isCompleted=" + isCompleted +
               ", customGameName='" + customGameName + '\'' +
               ", queueId='" + queueId + '\'' +
               ", gameMode='" + gameMode + '\'' +
               ", isRanked=" + isRanked +
               ", seasonId='" + seasonId + '\'' +
               '}';
    }
    
    @SQLForeignMap
    private static Map<Class<?>, String> getForeignKeyMap()
    {
        Map<Class<?>, String> returnMap = new HashMap<>();
        
        returnMap.put(Match.class, "id");
        
        return returnMap;
    }
}
