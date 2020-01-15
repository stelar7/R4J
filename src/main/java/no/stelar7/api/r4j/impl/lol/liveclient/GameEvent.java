package no.stelar7.api.r4j.impl.lol.liveclient;

import java.util.List;

public class GameEvent
{
    int    EventID;
    String EventName;
    float  EventTime;
    
    public static class ChampionKillEvent extends KillEvent
    {
        String VictimName;
    }
    
    public static class KillEvent extends GameEvent
    {
        List<String> Assisters;
        String       KillerName;
    }
    
    public static class RewardEvent extends GameEvent
    {
        String Recipient;
    }
    
    public static class DragonKillEvent extends EpicUnitKillEvent
    {
        String DragonType;
    }
    
    public static class TurretKillEvent extends KillEvent
    {
        String TurretKilled;
    }
    
    public static class InhibKillEvent extends KillEvent
    {
        String InhibKilled;
    }
    
    public static class InhibRespawnedEvent extends GameEvent
    {
        String InhibRespawned;
    }
    
    
    public static class InhibRespawningSoonEvent extends GameEvent
    {
        String InhibRespawningSoon;
    }
    
    public static class EpicUnitKillEvent extends KillEvent
    {
        boolean Stolen;
    }
}
