package no.stelar7.api.r4j.pojo.lol.cdragon;

import java.io.Serializable;
import java.util.*;

public class CDragonChampionTagInfo implements Serializable
{
    private static final long serialVersionUID = 1L;

    private String championTagPrimary;
    private String championTagSecondary;

    public String getChampionTagPrimary()
    {
        return championTagPrimary;
    }
    
    public String getChampionTagSecondary()
    {
        return championTagSecondary;
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
        CDragonChampionTagInfo that = (CDragonChampionTagInfo) o;
        return Objects.equals(championTagPrimary, that.championTagPrimary) &&
               Objects.equals(championTagSecondary, that.championTagSecondary);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(championTagPrimary, championTagSecondary);
    }

    @Override
    public String toString()
    {
        return "CDragonChampionTagInfo{" +
               "championTagPrimary=" + championTagPrimary +
               ", championTagSecondary=" + championTagSecondary +
               '}';
    }
}
