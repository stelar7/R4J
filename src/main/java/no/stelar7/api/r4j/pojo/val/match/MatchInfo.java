package no.stelar7.api.r4j.pojo.val.match;

import java.io.Serializable;
import java.util.Objects;

public class MatchInfo implements Serializable
{
    private static final long serialVersionUID = -5536569302071536933L;
    
    private String matchId;
    private String  mapId;
    private Long    gameLengthMillis;
    private Long    gameStartMillis;
    private String  provisioningFlowId;
    private Boolean isCompleted;
    private String  customGameName;
    private String  queueId;
    private String  gameMode;
    private Boolean isRanked;
    private String  seasonId;
    
    public String getMatchId()
    {
        return matchId;
    }
    
    public String getMapId()
    {
        return mapId;
    }
    
    public Long getGameLengthMillis()
    {
        return gameLengthMillis;
    }
    
    public Long getGameStartMillis()
    {
        return gameStartMillis;
    }
    
    public String getProvisioningFlowId()
    {
        return provisioningFlowId;
    }
    
    public Boolean getCompleted()
    {
        return isCompleted;
    }
    
    public String getCustomGameName()
    {
        return customGameName;
    }
    
    public String getQueueId()
    {
        return queueId;
    }
    
    public String getGameMode()
    {
        return gameMode;
    }
    
    public Boolean getRanked()
    {
        return isRanked;
    }
    
    public String getSeasonId()
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
}
