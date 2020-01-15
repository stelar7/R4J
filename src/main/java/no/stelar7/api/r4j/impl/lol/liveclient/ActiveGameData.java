package no.stelar7.api.r4j.impl.lol.liveclient;

import java.util.List;

public class ActiveGameData
{
    ActiveGameClientPlayer activePlayer;
    List<ActiveGamePlayer> allPlayers;
    List<GameEvent>        events;
    ActiveGameState        gameData;
}
