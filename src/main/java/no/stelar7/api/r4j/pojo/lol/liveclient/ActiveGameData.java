package no.stelar7.api.r4j.pojo.lol.liveclient;

import no.stelar7.api.r4j.pojo.lol.liveclient.events.GameEvent;

import java.util.*;

public class ActiveGameData
{
    private ActiveGameClientPlayer activePlayer;
    private List<ActiveGamePlayer> allPlayers;
    private List<GameEvent>        events;
    private ActiveGameState        gameData;
    
    public ActiveGameData(ActiveGameClientPlayer activePlayer, List<ActiveGamePlayer> allPlayers, List<GameEvent> events, ActiveGameState gameData)
    {
        this.activePlayer = activePlayer;
        this.allPlayers = allPlayers;
        this.events = events;
        this.gameData = gameData;
    }
    
    public ActiveGameClientPlayer getActivePlayer()
    {
        return activePlayer;
    }
    
    public List<ActiveGamePlayer> getAllPlayers()
    {
        return allPlayers;
    }
    
    public List<GameEvent> getEvents()
    {
        return events;
    }
    
    public ActiveGameState getGameData()
    {
        return gameData;
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
        ActiveGameData that = (ActiveGameData) o;
        return Objects.equals(activePlayer, that.activePlayer) &&
               Objects.equals(allPlayers, that.allPlayers) &&
               Objects.equals(events, that.events) &&
               Objects.equals(gameData, that.gameData);
    }
    
    @Override
    public int hashCode()
    {
        return Objects.hash(activePlayer, allPlayers, events, gameData);
    }
    
    @Override
    public String toString()
    {
        return "ActiveGameData{" +
               "activePlayer=" + activePlayer +
               ", allPlayers=" + allPlayers +
               ", events=" + events +
               ", gameData=" + gameData +
               '}';
    }
}
