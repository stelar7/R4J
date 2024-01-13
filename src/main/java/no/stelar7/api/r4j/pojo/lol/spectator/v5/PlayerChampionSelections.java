package no.stelar7.api.r4j.pojo.lol.spectator.v5;

import no.stelar7.api.r4j.basic.constants.types.lol.SummonerSpellType;

import java.io.Serializable;
import java.util.Objects;

public class PlayerChampionSelections implements Serializable
{
    private static final long serialVersionUID = -7792195971965081473L;
    
    private String            summonerInternalName;
    private long              championId;
    private SummonerSpellType spell1Id;
    private SummonerSpellType spell2Id;
    
    public String getSummonerInternalName()
    {
        return summonerInternalName;
    }
    
    public long getChampionId()
    {
        return championId;
    }
    
    public SummonerSpellType getSpell1Id()
    {
        return spell1Id;
    }
    
    public SummonerSpellType getSpell2Id()
    {
        return spell2Id;
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
        PlayerChampionSelections that = (PlayerChampionSelections) o;
        return championId == that.championId && Objects.equals(summonerInternalName, that.summonerInternalName) && spell1Id == that.spell1Id && spell2Id == that.spell2Id;
    }
    
    @Override
    public int hashCode()
    {
        return Objects.hash(summonerInternalName, championId, spell1Id, spell2Id);
    }
    
    @Override
    public String toString()
    {
        return "PlayerChampionSelections{" +
               "summonerInternalName='" + summonerInternalName + '\'' +
               ", championId=" + championId +
               ", spell1Id=" + spell1Id +
               ", spell2Id=" + spell2Id +
               '}';
    }
}
