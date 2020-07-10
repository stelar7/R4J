package no.stelar7.api.r4j.pojo.val.match;

import java.util.*;

public class Kill
{
    private int                  gameTime;
    private int                  roundTime;
    private String               killer;
    private String               victim;
    private Location             victimLocation;
    private List<String>         assistants;
    private List<PlayerLocation> playerLocations;
    private FinishingDamage      finishingDamage;
    
    public int getGameTime()
    {
        return gameTime;
    }
    
    public int getRoundTime()
    {
        return roundTime;
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
        return gameTime == kill.gameTime &&
               roundTime == kill.roundTime &&
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
        return Objects.hash(gameTime, roundTime, killer, victim, victimLocation, assistants, playerLocations, finishingDamage);
    }
    
    @Override
    public String toString()
    {
        return "Kill{" +
               "gameTime=" + gameTime +
               ", roundTime=" + roundTime +
               ", killer='" + killer + '\'' +
               ", victim='" + victim + '\'' +
               ", victimLocation=" + victimLocation +
               ", assistants=" + assistants +
               ", playerLocations=" + playerLocations +
               ", finishingDamage=" + finishingDamage +
               '}';
    }
}
