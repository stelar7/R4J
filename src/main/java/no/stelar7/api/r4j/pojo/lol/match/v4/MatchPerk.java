package no.stelar7.api.r4j.pojo.lol.match.v4;

import java.io.Serializable;

public class MatchPerk implements Serializable
{
    private static final long serialVersionUID = 2661863541123170867L;
    
    private int perkId;
    private int perkVar1;
    private int perkVar2;
    private int perkVar3;
    
    public int getPerkVar1()
    {
        return perkVar1;
    }
    
    public int getPerkVar2()
    {
        return perkVar2;
    }
    
    public int getPerkVar3()
    {
        return perkVar3;
    }
    
    public int getPerkId()
    {
        
        return perkId;
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
        
        MatchPerk matchPerk = (MatchPerk) o;
        
        if (perkId != matchPerk.perkId)
        {
            return false;
        }
        if (perkVar1 != matchPerk.perkVar1)
        {
            return false;
        }
        if (perkVar2 != matchPerk.perkVar2)
        {
            return false;
        }
        return perkVar3 == matchPerk.perkVar3;
    }
    
    @Override
    public int hashCode()
    {
        int result = perkId;
        result = 31 * result + perkVar1;
        result = 31 * result + perkVar2;
        result = 31 * result + perkVar3;
        return result;
    }
    
    @Override
    public String toString()
    {
        return "MatchPerk{" +
               "perkId=" + perkId +
               ", perkVar1=" + perkVar1 +
               ", perkVar2=" + perkVar2 +
               ", perkVar3=" + perkVar3 +
               '}';
    }
}
