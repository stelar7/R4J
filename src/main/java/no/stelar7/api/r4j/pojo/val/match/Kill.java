package no.stelar7.api.r4j.pojo.val.match;

import no.stelar7.api.r4j.basic.utils.sql.SQLForeignMap;

import java.io.Serializable;
import java.util.*;

public class Kill implements Serializable
{
    private static final long serialVersionUID = 5537015544246838162L;
    
    private int                  timeSinceGameStartMillis;
    private int                  timeSinceRoundStartMillis;
    private String               killer;
    private String               victim;
    private Location             victimLocation;
    private List<String>         assistants;
    private List<PlayerLocation> playerLocations;
    private FinishingDamage      finishingDamage;
    
    public int getTimeSinceGameStartMillis()
    {
        return timeSinceGameStartMillis;
    }
    
    public int getTimeSinceRoundStartMillis()
    {
        return timeSinceRoundStartMillis;
    }
    
    public String getKiller()
    {
        return killer;
    }
    
    public String getVictim()
    {
        return victim;
    }
    
    public Location getVictimLocation()
    {
        return victimLocation;
    }
    
    public List<String> getAssistants()
    {
        return assistants;
    }
    
    public List<PlayerLocation> getPlayerLocations()
    {
        return playerLocations;
    }
    
    public FinishingDamage getFinishingDamage()
    {
        return finishingDamage;
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
        Kill kill = (Kill) o;
        return timeSinceGameStartMillis == kill.timeSinceGameStartMillis &&
               timeSinceRoundStartMillis == kill.timeSinceRoundStartMillis &&
               Objects.equals(killer, kill.killer) &&
               Objects.equals(victim, kill.victim) &&
               Objects.equals(victimLocation, kill.victimLocation) &&
               Objects.equals(assistants, kill.assistants) &&
               Objects.equals(playerLocations, kill.playerLocations) &&
               Objects.equals(finishingDamage, kill.finishingDamage);
    }
    
    @Override
    public int hashCode()
    {
        return Objects.hash(timeSinceGameStartMillis, timeSinceRoundStartMillis, killer, victim, victimLocation, assistants, playerLocations, finishingDamage);
    }
    
    @Override
    public String toString()
    {
        return "Kill{" +
               "timeSinceGameStartMillis=" + timeSinceGameStartMillis +
               ", timeSinceRoundStartMillis=" + timeSinceRoundStartMillis +
               ", killer='" + killer + '\'' +
               ", victim='" + victim + '\'' +
               ", victimLocation=" + victimLocation +
               ", assistants=" + assistants +
               ", playerLocations=" + playerLocations +
               ", finishingDamage=" + finishingDamage +
               '}';
    }
    
    
    @SQLForeignMap
    private static Map<Class<?>, String> getForeignKeyMap()
    {
        Map<Class<?>, String> returnMap = new HashMap<>();
        
        returnMap.put(Match.class, "id");
        returnMap.put(RoundResult.class, "roundNum");
        returnMap.put(PlayerRoundStats.class, "puuid");
        
        return returnMap;
    }
}
