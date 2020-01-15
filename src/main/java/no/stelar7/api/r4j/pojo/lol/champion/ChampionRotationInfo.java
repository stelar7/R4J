package no.stelar7.api.r4j.pojo.lol.champion;

import no.stelar7.api.r4j.impl.lol.raw.DDragonAPI;
import no.stelar7.api.r4j.pojo.lol.staticdata.champion.StaticChampion;

import java.io.Serializable;
import java.util.*;

public class ChampionRotationInfo implements Serializable
{
    private List<Integer> freeChampionIds;
    private List<Integer> freeChampionIdsForNewPlayers;
    private int           maxNewPlayerLevel;
    
    /**
     * List of championIds that players over {maxNewPlayerLevel} players get to play for free
     */
    public List<Integer> getFreeChampionIds()
    {
        return freeChampionIds;
    }
    
    /**
     * List of championIds that new players get to play for free
     */
    public List<Integer> getFreeChampionIdsForNewPlayers()
    {
        return freeChampionIdsForNewPlayers;
    }
    
    /**
     * Above this level you will get the champions from {freeChampionIds} instead of {freeChampionIdsForNewPlayers}
     */
    public int getMaxNewPlayerLevel()
    {
        return maxNewPlayerLevel;
    }
    
    /**
     * List of champions that players over {maxNewPlayerLevel} get to play for free
     */
    public List<StaticChampion> getFreeChampions()
    {
        return DDragonAPI.getInstance().getChampionsFromId(freeChampionIds);
    }
    
    /**
     * List of champions that players below {maxNewPlayerLevel} get to play for free
     */
    public List<StaticChampion> getFreeChampionsForNewPlayers()
    {
        return DDragonAPI.getInstance().getChampionsFromId(freeChampionIdsForNewPlayers);
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
        ChampionRotationInfo that = (ChampionRotationInfo) o;
        return maxNewPlayerLevel == that.maxNewPlayerLevel &&
               Objects.equals(freeChampionIds, that.freeChampionIds) &&
               Objects.equals(freeChampionIdsForNewPlayers, that.freeChampionIdsForNewPlayers);
    }
    
    @Override
    public int hashCode()
    {
        return Objects.hash(freeChampionIds, freeChampionIdsForNewPlayers, maxNewPlayerLevel);
    }
    
    @Override
    public String toString()
    {
        return "ChampionRotationInfo{" +
               "freeChampionIds=" + freeChampionIds +
               ", freeChampionIdsForNewPlayers=" + freeChampionIdsForNewPlayers +
               ", maxNewPlayerLevel=" + maxNewPlayerLevel +
               '}';
    }
}
