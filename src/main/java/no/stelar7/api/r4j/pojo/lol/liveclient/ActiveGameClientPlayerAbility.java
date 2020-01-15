package no.stelar7.api.r4j.pojo.lol.liveclient;

import java.util.Objects;

public class ActiveGameClientPlayerAbility
{
    private  int    abilityLevel;
    private String displayName;
    private String id;
    private String rawDescription;
    private String rawDisplayName;
    
    public int getAbilityLevel()
    {
        return abilityLevel;
    }
    
    public String getDisplayName()
    {
        return displayName;
    }
    
    public String getId()
    {
        return id;
    }
    
    public String getRawDescription()
    {
        return rawDescription;
    }
    
    public String getRawDisplayName()
    {
        return rawDisplayName;
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
        ActiveGameClientPlayerAbility that = (ActiveGameClientPlayerAbility) o;
        return abilityLevel == that.abilityLevel &&
               Objects.equals(displayName, that.displayName) &&
               Objects.equals(id, that.id) &&
               Objects.equals(rawDescription, that.rawDescription) &&
               Objects.equals(rawDisplayName, that.rawDisplayName);
    }
    
    @Override
    public int hashCode()
    {
        return Objects.hash(abilityLevel, displayName, id, rawDescription, rawDisplayName);
    }
    
    @Override
    public String toString()
    {
        return "ActiveGameClientPlayerAbility{" +
               "abilityLevel=" + abilityLevel +
               ", displayName='" + displayName + '\'' +
               ", id='" + id + '\'' +
               ", rawDescription='" + rawDescription + '\'' +
               ", rawDisplayName='" + rawDisplayName + '\'' +
               '}';
    }
}
