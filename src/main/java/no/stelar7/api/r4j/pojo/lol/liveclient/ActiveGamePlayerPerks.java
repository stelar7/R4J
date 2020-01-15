package no.stelar7.api.r4j.pojo.lol.liveclient;

import java.util.Objects;

public class ActiveGamePlayerPerks
{
    private ActiveGamePlayerPerk keystone;
    private ActiveGamePlayerPerk primaryRuneTree;
    private ActiveGamePlayerPerk secondaryRuneTree;
    
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
        ActiveGamePlayerPerks that = (ActiveGamePlayerPerks) o;
        return Objects.equals(keystone, that.keystone) &&
               Objects.equals(primaryRuneTree, that.primaryRuneTree) &&
               Objects.equals(secondaryRuneTree, that.secondaryRuneTree);
    }
    
    @Override
    public int hashCode()
    {
        return Objects.hash(keystone, primaryRuneTree, secondaryRuneTree);
    }
    
    @Override
    public String toString()
    {
        return "ActiveGamePlayerPerks{" +
               "keystone=" + keystone +
               ", primaryRuneTree=" + primaryRuneTree +
               ", secondaryRuneTree=" + secondaryRuneTree +
               '}';
    }
}
