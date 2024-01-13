package no.stelar7.api.r4j.pojo.lol.spectator.v5;

import no.stelar7.api.r4j.basic.constants.types.lol.*;

import java.io.Serializable;
import java.util.Objects;

public class PlayerCredentials implements Serializable
{
    private static final long serialVersionUID = -6054152398828167734L;
    
    private long          gameId;
    private GameQueueType queueId;
    private String        playerId;
    private String        puuid;
    private String        encryptionKey;
    private String        handshakeToken;
    private long          championId;
    private String        summonerId;
    private boolean       observer;
    private GameModeType  gameMode;
    private String        observerEncryptionKey;
    private String        queueType;
    private long          gameCreateDate;
    
    public long getGameId()
    {
        return gameId;
    }
    
    public GameQueueType getQueueId()
    {
        return queueId;
    }
    
    public String getPlayerId()
    {
        return playerId;
    }
    
    public String getPuuid()
    {
        return puuid;
    }
    
    public String getEncryptionKey()
    {
        return encryptionKey;
    }
    
    public String getHandshakeToken()
    {
        return handshakeToken;
    }
    
    public long getChampionId()
    {
        return championId;
    }
    
    public String getSummonerId()
    {
        return summonerId;
    }
    
    public boolean isObserver()
    {
        return observer;
    }
    
    public GameModeType getGameMode()
    {
        return gameMode;
    }
    
    public String getObserverEncryptionKey()
    {
        return observerEncryptionKey;
    }
    
    public String getQueueType()
    {
        return queueType;
    }
    
    public long getGameCreateDate()
    {
        return gameCreateDate;
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
        PlayerCredentials that = (PlayerCredentials) o;
        return gameId == that.gameId
               && championId == that.championId
               && observer == that.observer
               && gameCreateDate == that.gameCreateDate
               && queueId == that.queueId
               && Objects.equals(playerId, that.playerId)
               && Objects.equals(puuid, that.puuid)
               && Objects.equals(encryptionKey, that.encryptionKey)
               && Objects.equals(handshakeToken, that.handshakeToken)
               && Objects.equals(summonerId, that.summonerId)
               && gameMode == that.gameMode
               && Objects.equals(observerEncryptionKey, that.observerEncryptionKey)
               && Objects.equals(queueType, that.queueType);
    }
    
    @Override
    public int hashCode()
    {
        return Objects.hash(gameId,
                            queueId,
                            playerId,
                            puuid,
                            encryptionKey,
                            handshakeToken,
                            championId,
                            summonerId,
                            observer,
                            gameMode,
                            observerEncryptionKey,
                            queueType,
                            gameCreateDate);
    }
    
    @Override
    public String toString()
    {
        return "PlayerCredentials{" +
               "gameId=" + gameId +
               ", queueId=" + queueId +
               ", playerId=" + playerId +
               ", puuid='" + puuid + '\'' +
               ", encryptionKey='" + encryptionKey + '\'' +
               ", handshakeToken='" + handshakeToken + '\'' +
               ", championId=" + championId +
               ", summonerId=" + summonerId +
               ", observer=" + observer +
               ", gameMode=" + gameMode +
               ", observerEncryptionKey='" + observerEncryptionKey + '\'' +
               ", queueType='" + queueType + '\'' +
               ", gameCreateDate=" + gameCreateDate +
               '}';
    }
}
