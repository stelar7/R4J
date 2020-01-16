package no.stelar7.api.r4j.pojo.lol.liveclient.events;

import java.util.stream.Stream;

public enum GameEventType
{
    UNKNOWN(""),
    
    GAME_START("GameStart"),
    GAME_END("GameEnd"),
    MINIONS_SPAWNING("MinionsSpawning"),
    CHAMPION_KILL("ChampionKill"),
    FIRST_BLOOD("FirstBlood"),
    FIRST_TOWER("FirstBrick"),
    TOWER_KILLED("TurretKilled"),
    INHIBITOR_KILLED("InhibKilled"),
    HERALD_KILLED("HeraldKill"),
    BARON_KILLED("BaronKill"),
    DRAGON_KILLED("DragonKill"),
    INHIBITOR_RESPAWNING_SOON("InhibRespawningSoon"),
    INHIBITOR_RESPAWNED("InhibRespawned"),
    MULTIKILL("Multikill"),
    TEAM_ACE("Ace"),
    ;
    
    private String eventName;
    
    GameEventType(String eventName)
    {
        this.eventName = eventName;
    }
    
    public String getEventName()
    {
        return this.eventName;
    }
    
    public static GameEventType getFromCode(final String compare)
    {
        return Stream.of(GameEventType.values()).filter(t -> t.eventName.equalsIgnoreCase(compare)).findFirst().orElse(UNKNOWN);
    }
}
