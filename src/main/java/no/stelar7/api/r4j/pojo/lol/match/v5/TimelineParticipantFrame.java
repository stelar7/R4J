package no.stelar7.api.r4j.pojo.lol.match.v5;

import java.io.Serializable;
import java.util.Objects;

public class TimelineParticipantFrame implements Serializable
{
    private static final long serialVersionUID = 8518386569606056255L;
    
    private ChampionStats    championStats;
    private int              currentGold;
    private DamageStats      damageStats;
    private int              goldPerSecond;
    private int              jungleMinionsKilled;
    private int              level;
    private int              minionsKilled;
    private int              participantId;
    private TimelinePosition position;
    private int              timeEnemySpentControlled;
    private int              totalGold;
    private int              xp;
    
    public static long getSerialVersionUID()
    {
        return serialVersionUID;
    }
    
    public ChampionStats getChampionStats()
    {
        return championStats;
    }
    
    public int getCurrentGold()
    {
        return currentGold;
    }
    
    public DamageStats getDamageStats()
    {
        return damageStats;
    }
    
    public int getGoldPerSecond()
    {
        return goldPerSecond;
    }
    
    public int getJungleMinionsKilled()
    {
        return jungleMinionsKilled;
    }
    
    public int getLevel()
    {
        return level;
    }
    
    public int getMinionsKilled()
    {
        return minionsKilled;
    }
    
    public int getParticipantId()
    {
        return participantId;
    }
    
    public TimelinePosition getPosition()
    {
        return position;
    }
    
    public int getTimeEnemySpentControlled()
    {
        return timeEnemySpentControlled;
    }
    
    public int getTotalGold()
    {
        return totalGold;
    }
    
    public int getXp()
    {
        return xp;
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
        TimelineParticipantFrame that = (TimelineParticipantFrame) o;
        return currentGold == that.currentGold && goldPerSecond == that.goldPerSecond && jungleMinionsKilled == that.jungleMinionsKilled && level == that.level && minionsKilled == that.minionsKilled && participantId == that.participantId && timeEnemySpentControlled == that.timeEnemySpentControlled && totalGold == that.totalGold && xp == that.xp && Objects.equals(championStats, that.championStats) && Objects.equals(damageStats, that.damageStats) && Objects.equals(position, that.position);
    }
    
    @Override
    public int hashCode()
    {
        return Objects.hash(championStats, currentGold, damageStats, goldPerSecond, jungleMinionsKilled, level, minionsKilled, participantId, position, timeEnemySpentControlled, totalGold, xp);
    }
    
    @Override
    public String toString()
    {
        return "TimelineParticipantFrame{" +
               "championStats=" + championStats +
               ", currentGold=" + currentGold +
               ", damageStats=" + damageStats +
               ", goldPerSecond=" + goldPerSecond +
               ", jungleMinionsKilled=" + jungleMinionsKilled +
               ", level=" + level +
               ", minionsKilled=" + minionsKilled +
               ", participantId=" + participantId +
               ", position=" + position +
               ", timeEnemySpentControlled=" + timeEnemySpentControlled +
               ", totalGold=" + totalGold +
               ", xp=" + xp +
               '}';
    }
}
