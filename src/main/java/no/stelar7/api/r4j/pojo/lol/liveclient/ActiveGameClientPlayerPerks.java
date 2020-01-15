package no.stelar7.api.r4j.pojo.lol.liveclient;

import java.util.*;

public class ActiveGameClientPlayerPerks
{
    private List<ActiveGamePlayerPerk> generalRunes;
    private ActiveGamePlayerPerk       keystone;
    private ActiveGamePlayerPerk       primaryRuneTree;
    private ActiveGamePlayerPerk       secondaryRuneTree;
    private List<ActiveGamePlayerPerk> statRunes;
    
    public List<ActiveGamePlayerPerk> getGeneralRunes()
    {
        return generalRunes;
    }
    
    public ActiveGamePlayerPerk getKeystone()
    {
        return keystone;
    }
    
    public ActiveGamePlayerPerk getPrimaryRuneTree()
    {
        return primaryRuneTree;
    }
    
    public ActiveGamePlayerPerk getSecondaryRuneTree()
    {
        return secondaryRuneTree;
    }
    
    public List<ActiveGamePlayerPerk> getStatRunes()
    {
        return statRunes;
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
        ActiveGameClientPlayerPerks that = (ActiveGameClientPlayerPerks) o;
        return Objects.equals(generalRunes, that.generalRunes) &&
               Objects.equals(keystone, that.keystone) &&
               Objects.equals(primaryRuneTree, that.primaryRuneTree) &&
               Objects.equals(secondaryRuneTree, that.secondaryRuneTree) &&
               Objects.equals(statRunes, that.statRunes);
    }
    
    @Override
    public int hashCode()
    {
        return Objects.hash(generalRunes, keystone, primaryRuneTree, secondaryRuneTree, statRunes);
    }
    
    @Override
    public String toString()
    {
        return "ActiveGameClientPlayerPerks{" +
               "generalRunes=" + generalRunes +
               ", keystone=" + keystone +
               ", primaryRuneTree=" + primaryRuneTree +
               ", secondaryRuneTree=" + secondaryRuneTree +
               ", statRunes=" + statRunes +
               '}';
    }
}
