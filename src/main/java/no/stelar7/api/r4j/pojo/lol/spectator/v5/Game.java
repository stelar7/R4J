package no.stelar7.api.r4j.pojo.lol.spectator.v5;

import no.stelar7.api.r4j.basic.constants.types.lol.*;

import java.io.Serializable;
import java.util.*;

public class Game implements Serializable
{
    private static final long serialVersionUID = 6832252021485876503L;
    
    private long                           id;
    private String                         gameState;
    private GameQueueType                  queueTypeName;
    private String                         roomName;
    private String                         roomPassword;
    private String                         name;
    private long                           pickTurn;
    private MapType                        mapId;
    private GameModeType                   gameMode;
    private long                           maxNumPlayers;
    private String                         gameType;
    private GameQueueType                  gameQueueConfigId;
    private long                           spectatorDelay;
    private String                         gameVersion;
    private List<Team>                     teamOne;
    private List<Team>                     teamTwo;
    private List<PlayerChampionSelections> playerChampionSelections;
    private List<BannedChampions>          bannedChampions;
    private List<Observer>                 observers;
    
    public long getId()
    {
        return id;
    }
    
    public String getGameState()
    {
        return gameState;
    }
    
    public GameQueueType getQueueTypeName()
    {
        return queueTypeName;
    }
    
    public String getRoomName()
    {
        return roomName;
    }
    
    public String getRoomPassword()
    {
        return roomPassword;
    }
    
    public String getName()
    {
        return name;
    }
    
    public long getPickTurn()
    {
        return pickTurn;
    }
    
    public MapType getMapId()
    {
        return mapId;
    }
    
    public GameModeType getGameMode()
    {
        return gameMode;
    }
    
    public long getMaxNumPlayers()
    {
        return maxNumPlayers;
    }
    
    public String getGameType()
    {
        return gameType;
    }
    
    public GameQueueType getGameQueueConfigId()
    {
        return gameQueueConfigId;
    }
    
    public long getSpectatorDelay()
    {
        return spectatorDelay;
    }
    
    public String getGameVersion()
    {
        return gameVersion;
    }
    
    public List<Team> getTeamOne()
    {
        return teamOne;
    }
    
    public List<Team> getTeamTwo()
    {
        return teamTwo;
    }
    
    public List<PlayerChampionSelections> getPlayerChampionSelections()
    {
        return playerChampionSelections;
    }
    
    public List<BannedChampions> getBannedChampions()
    {
        return bannedChampions;
    }
    
    public List<Observer> getObservers()
    {
        return observers;
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
        Game game = (Game) o;
        return id == game.id
               && pickTurn == game.pickTurn
               && maxNumPlayers == game.maxNumPlayers
               && spectatorDelay == game.spectatorDelay
               && Objects.equals(gameState,
                                 game.gameState)
               && Objects.equals(queueTypeName, game.queueTypeName)
               && Objects.equals(roomName, game.roomName)
               && Objects.equals(roomPassword, game.roomPassword)
               && Objects.equals(name, game.name)
               && mapId == game.mapId
               && gameMode == game.gameMode
               && Objects.equals(gameType, game.gameType)
               && gameQueueConfigId == game.gameQueueConfigId
               && Objects.equals(gameVersion, game.gameVersion)
               && Objects.equals(teamOne, game.teamOne)
               && Objects.equals(teamTwo, game.teamTwo)
               && Objects.equals(playerChampionSelections, game.playerChampionSelections)
               && Objects.equals(bannedChampions, game.bannedChampions)
               && Objects.equals(observers, game.observers);
    }
    
    @Override
    public int hashCode()
    {
        return Objects.hash(id,
                            gameState,
                            queueTypeName,
                            roomName,
                            roomPassword,
                            name,
                            pickTurn,
                            mapId,
                            gameMode,
                            maxNumPlayers,
                            gameType,
                            gameQueueConfigId,
                            spectatorDelay,
                            gameVersion,
                            teamOne,
                            teamTwo,
                            playerChampionSelections,
                            bannedChampions,
                            observers);
    }
    
    @Override
    public String toString()
    {
        return "Game{" +
               "id=" + id +
               ", gameState='" + gameState + '\'' +
               ", queueTypeName='" + queueTypeName + '\'' +
               ", roomName='" + roomName + '\'' +
               ", roomPassword='" + roomPassword + '\'' +
               ", name='" + name + '\'' +
               ", pickTurn=" + pickTurn +
               ", mapId=" + mapId +
               ", gameMode=" + gameMode +
               ", maxNumPlayers=" + maxNumPlayers +
               ", gameType='" + gameType + '\'' +
               ", gameQueueConfigId=" + gameQueueConfigId +
               ", spectatorDelay=" + spectatorDelay +
               ", gameVersion='" + gameVersion + '\'' +
               ", teamOne=" + teamOne +
               ", teamTwo=" + teamTwo +
               ", playerChampionSelections=" + playerChampionSelections +
               ", bannedChampions=" + bannedChampions +
               ", observers=" + observers +
               '}';
    }
}
