package no.stelar7.api.r4j.pojo.val.content;

import java.io.Serializable;
import java.util.*;

public class Act implements Serializable
{
    private static final long serialVersionUID = 1L;

    private String              id;
    private String              name;
    private Map<String, String> localizedNames;
    private boolean             isActive;

    public String getId()
    {
        return id;
    }
    
    public String getName()
    {
        return name;
    }
    
    public Map<String, String> getLocalizedNames()
    {
        return localizedNames;
    }
    
    public boolean isActive()
    {
        return isActive;
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
        Act that = (Act) o;
        return Objects.equals(id, that.id) &&
               Objects.equals(name, that.name) &&
               Objects.equals(localizedNames, that.localizedNames) &&
               isActive == that.isActive;
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(id, name, localizedNames, isActive);
    }

    @Override
    public String toString()
    {
        return "Act{" +
               "id=" + id +
               ", name=" + name +
               ", localizedNames=" + localizedNames +
               ", isActive=" + isActive +
               '}';
    }
}
