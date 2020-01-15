package no.stelar7.api.r4j.pojo.lol.liveclient;

import java.util.Objects;

public class ActiveGameClientPlayer
{
    private ActiveGameClientPlayerAbilities abilities;
    private ActiveGameClientPlayerStats     championStats;
    private float                           currentGold;
    private ActiveGameClientPlayerPerks     fullRunes;
    private int                             level;
    private String                          summonerName;
    
    public ActiveGameClientPlayerAbilities getAbilities()
    {
        return abilities;
    }
    
    public ActiveGameClientPlayerStats getChampionStats()
    {
        return championStats;
    }
    
    public float getCurrentGold()
    {
        return currentGold;
    }
    
    public ActiveGameClientPlayerPerks getFullRunes()
    {
        return fullRunes;
    }
    
    public int getLevel()
    {
        return level;
    }
    
    public String getSummonerName()
    {
        return summonerName;
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
        ActiveGameClientPlayer that = (ActiveGameClientPlayer) o;
        return Float.compare(that.currentGold, currentGold) == 0 &&
               level == that.level &&
               Objects.equals(abilities, that.abilities) &&
               Objects.equals(championStats, that.championStats) &&
               Objects.equals(fullRunes, that.fullRunes) &&
               Objects.equals(summonerName, that.summonerName);
    }
    
    @Override
    public int hashCode()
    {
        return Objects.hash(abilities, championStats, currentGold, fullRunes, level, summonerName);
    }
    
    @Override
    public String toString()
    {
        return "ActiveGameClientPlayer{" +
               "abilities=" + abilities +
               ", championStats=" + championStats +
               ", currentGold=" + currentGold +
               ", fullRunes=" + fullRunes +
               ", level=" + level +
               ", summonerName='" + summonerName + '\'' +
               '}';
    }
}
