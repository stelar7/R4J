package no.stelar7.api.r4j.pojo.lol.match.v5;

import java.io.Serializable;
import java.util.Objects;

public class DamageStats implements Serializable
{
    private static final long serialVersionUID = -1051562859422423330L;
    
    private int magicDamageDone;
    private int magicDamageDoneToChampions;
    private int magicDamageTaken;
    private int physicalDamageDone;
    private int physicalDamageDoneToChampions;
    private int physicalDamageTaken;
    private int totalDamageDone;
    private int totalDamageDoneToChampions;
    private int totalDamageTaken;
    private int trueDamageDone;
    private int trueDamageDoneToChampions;
    private int trueDamageTaken;
    
    public static long getSerialVersionUID()
    {
        return serialVersionUID;
    }
    
    public int getMagicDamageDone()
    {
        return magicDamageDone;
    }
    
    public int getMagicDamageDoneToChampions()
    {
        return magicDamageDoneToChampions;
    }
    
    public int getMagicDamageTaken()
    {
        return magicDamageTaken;
    }
    
    public int getPhysicalDamageDone()
    {
        return physicalDamageDone;
    }
    
    public int getPhysicalDamageDoneToChampions()
    {
        return physicalDamageDoneToChampions;
    }
    
    public int getPhysicalDamageTaken()
    {
        return physicalDamageTaken;
    }
    
    public int getTotalDamageDone()
    {
        return totalDamageDone;
    }
    
    public int getTotalDamageDoneToChampions()
    {
        return totalDamageDoneToChampions;
    }
    
    public int getTotalDamageTaken()
    {
        return totalDamageTaken;
    }
    
    public int getTrueDamageDone()
    {
        return trueDamageDone;
    }
    
    public int getTrueDamageDoneToChampions()
    {
        return trueDamageDoneToChampions;
    }
    
    public int getTrueDamageTaken()
    {
        return trueDamageTaken;
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
        DamageStats that = (DamageStats) o;
        return magicDamageDone == that.magicDamageDone && magicDamageDoneToChampions == that.magicDamageDoneToChampions && magicDamageTaken == that.magicDamageTaken && physicalDamageDone == that.physicalDamageDone && physicalDamageDoneToChampions == that.physicalDamageDoneToChampions && physicalDamageTaken == that.physicalDamageTaken && totalDamageDone == that.totalDamageDone && totalDamageDoneToChampions == that.totalDamageDoneToChampions && totalDamageTaken == that.totalDamageTaken && trueDamageDone == that.trueDamageDone && trueDamageDoneToChampions == that.trueDamageDoneToChampions && trueDamageTaken == that.trueDamageTaken;
    }
    
    @Override
    public int hashCode()
    {
        return Objects.hash(magicDamageDone, magicDamageDoneToChampions, magicDamageTaken, physicalDamageDone, physicalDamageDoneToChampions, physicalDamageTaken, totalDamageDone, totalDamageDoneToChampions, totalDamageTaken, trueDamageDone, trueDamageDoneToChampions, trueDamageTaken);
    }
    
    @Override
    public String toString()
    {
        return "DamageStats{" +
               "magicDamageDone=" + magicDamageDone +
               ", magicDamageDoneToChampions=" + magicDamageDoneToChampions +
               ", magicDamageTaken=" + magicDamageTaken +
               ", physicalDamageDone=" + physicalDamageDone +
               ", physicalDamageDoneToChampions=" + physicalDamageDoneToChampions +
               ", physicalDamageTaken=" + physicalDamageTaken +
               ", totalDamageDone=" + totalDamageDone +
               ", totalDamageDoneToChampions=" + totalDamageDoneToChampions +
               ", totalDamageTaken=" + totalDamageTaken +
               ", trueDamageDone=" + trueDamageDone +
               ", trueDamageDoneToChampions=" + trueDamageDoneToChampions +
               ", trueDamageTaken=" + trueDamageTaken +
               '}';
    }
}
