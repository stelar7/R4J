package no.stelar7.api.r4j.pojo.lol.status;

import java.io.Serializable;
import java.util.*;

public class PlatformData implements Serializable
{
    private String       id;
    private String       name;
    private List<String> locales;
    private List<Status> maintenances;
    private List<Status> incidents;
    
    public String getId()
    {
        return id;
    }
    
    public String getName()
    {
        return name;
    }
    
    public List<String> getLocales()
    {
        return locales;
    }
    
    public List<Status> getMaintenances()
    {
        return maintenances;
    }
    
    public List<Status> getIncidents()
    {
        return incidents;
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
        PlatformData that = (PlatformData) o;
        return Objects.equals(id, that.id) &&
               Objects.equals(name, that.name) &&
               Objects.equals(locales, that.locales) &&
               Objects.equals(maintenances, that.maintenances) &&
               Objects.equals(incidents, that.incidents);
    }
    
    @Override
    public int hashCode()
    {
        return Objects.hash(id, name, locales, maintenances, incidents);
    }
    
    @Override
    public String toString()
    {
        return "PlatformData{" +
               "id='" + id + '\'' +
               ", name='" + name + '\'' +
               ", locales=" + locales +
               ", maintenances=" + maintenances +
               ", incidents=" + incidents +
               '}';
    }
}
