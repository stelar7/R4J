package no.stelar7.api.l4j8.pojo.champion;

import no.stelar7.api.l4j8.basic.constants.types.ChampionType;

public class Champion
{
    
    private boolean      active;
    private boolean      botEnabled;
    private boolean      botMmEnabled;
    private boolean      freeToPlay;
    private boolean      rankedPlayEnabled;
    private ChampionType id;
    
    public int getId()
    {
        return this.id.getId();
    }
    
    public ChampionType getChampionType()
    {
        return this.id;
    }
    
    public boolean isActive()
    {
        return this.active;
    }
    
    public boolean isBotEnabled()
    {
        return this.botEnabled;
    }
    
    public boolean isBotMmEnabled()
    {
        return this.botMmEnabled;
    }
    
    public boolean isFreeToPlay()
    {
        return this.freeToPlay;
    }
    
    public boolean isRankedPlayEnabled()
    {
        return this.rankedPlayEnabled;
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
        
        Champion champion = (Champion) o;
        
        if (active != champion.active)
        {
            return false;
        }
        if (botEnabled != champion.botEnabled)
        {
            return false;
        }
        if (botMmEnabled != champion.botMmEnabled)
        {
            return false;
        }
        if (freeToPlay != champion.freeToPlay)
        {
            return false;
        }
        if (rankedPlayEnabled != champion.rankedPlayEnabled)
        {
            return false;
        }
        return id == champion.id;
    }
    
    @Override
    public int hashCode()
    {
        int result = (active ? 1 : 0);
        result = 31 * result + (botEnabled ? 1 : 0);
        result = 31 * result + (botMmEnabled ? 1 : 0);
        result = 31 * result + (freeToPlay ? 1 : 0);
        result = 31 * result + (rankedPlayEnabled ? 1 : 0);
        result = 31 * result + (id != null ? id.hashCode() : 0);
        return result;
    }
    
    @Override
    public String toString()
    {
        return "ChampionType{" +
               "active=" + active +
               ", botEnabled=" + botEnabled +
               ", botMmEnabled=" + botMmEnabled +
               ", freeToPlay=" + freeToPlay +
               ", rankedPlayEnabled=" + rankedPlayEnabled +
               ", id=" + id +
               '}';
    }
}
