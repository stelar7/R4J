package no.stelar7.api.r4j.pojo.lol.cdragon;

import java.io.Serializable;
import java.util.*;

public class CDragonPassive implements Serializable
{
    private static final long serialVersionUID = 1L;

    private String name;
    private String abilityIconPath;
    private String abilityVideoPath;
    private String abilityVideoImagePath;
    private String description;

    public String getName()
    {
        return name;
    }
    
    public String getAbilityIconPath()
    {
        return abilityIconPath;
    }
    
    public String getAbilityVideoPath()
    {
        return abilityVideoPath;
    }
    
    public String getAbilityVideoImagePath()
    {
        return abilityVideoImagePath;
    }
    
    public String getDescription()
    {
        return description;
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
        CDragonPassive that = (CDragonPassive) o;
        return Objects.equals(name, that.name) &&
               Objects.equals(abilityIconPath, that.abilityIconPath) &&
               Objects.equals(abilityVideoPath, that.abilityVideoPath) &&
               Objects.equals(abilityVideoImagePath, that.abilityVideoImagePath) &&
               Objects.equals(description, that.description);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(name, abilityIconPath, abilityVideoPath, abilityVideoImagePath, description);
    }

    @Override
    public String toString()
    {
        return "CDragonPassive{" +
               "name=" + name +
               ", abilityIconPath=" + abilityIconPath +
               ", abilityVideoPath=" + abilityVideoPath +
               ", abilityVideoImagePath=" + abilityVideoImagePath +
               ", description=" + description +
               '}';
    }
}
