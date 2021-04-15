package no.stelar7.api.r4j.pojo.lol.match.v5;

import java.io.Serializable;
import java.util.Objects;

public class PerkSelection implements Serializable
{
    private static final long serialVersionUID = 2538970980109396608L;
    
    private int perk;
    private int var1;
    private int var2;
    private int var3;
    
    public int getPerk()
    {
        return perk;
    }
    
    public int getVar1()
    {
        return var1;
    }
    
    public int getVar2()
    {
        return var2;
    }
    
    public int getVar3()
    {
        return var3;
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
        PerkSelection that = (PerkSelection) o;
        return perk == that.perk && var1 == that.var1 && var2 == that.var2 && var3 == that.var3;
    }
    
    @Override
    public int hashCode()
    {
        return Objects.hash(perk, var1, var2, var3);
    }
    
    @Override
    public String toString()
    {
        return "PerkSelection{" +
               "perk=" + perk +
               ", var1=" + var1 +
               ", var2=" + var2 +
               ", var3=" + var3 +
               '}';
    }
}
