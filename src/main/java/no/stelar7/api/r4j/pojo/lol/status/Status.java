package no.stelar7.api.r4j.pojo.lol.status;

import java.io.Serializable;
import java.util.*;

public class Status implements Serializable
{
    private int                 id;
    private String              maintenance_status;
    private String              incident_severity;
    private List<StatusContent> titles;
    private List<StatusUpdate>  updates;
    private String              created_at;
    private String              archive_at;
    private String              updated_at;
    private List<String>        platforms;
    
    public int getId()
    {
        return id;
    }
    
    public String getMaintenance_status()
    {
        return maintenance_status;
    }
    
    public String getIncident_severity()
    {
        return incident_severity;
    }
    
    public List<StatusContent> getTitles()
    {
        return titles;
    }
    
    public List<StatusUpdate> getUpdates()
    {
        return updates;
    }
    
    public String getCreated_at()
    {
        return created_at;
    }
    
    public String getArchive_at()
    {
        return archive_at;
    }
    
    public String getUpdated_at()
    {
        return updated_at;
    }
    
    public List<String> getPlatforms()
    {
        return platforms;
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
        Status status = (Status) o;
        return id == status.id &&
               Objects.equals(maintenance_status, status.maintenance_status) &&
               Objects.equals(incident_severity, status.incident_severity) &&
               Objects.equals(titles, status.titles) &&
               Objects.equals(updates, status.updates) &&
               Objects.equals(created_at, status.created_at) &&
               Objects.equals(archive_at, status.archive_at) &&
               Objects.equals(updated_at, status.updated_at) &&
               Objects.equals(platforms, status.platforms);
    }
    
    @Override
    public int hashCode()
    {
        return Objects.hash(id, maintenance_status, incident_severity, titles, updates, created_at, archive_at, updated_at, platforms);
    }
    
    @Override
    public String toString()
    {
        return "Status{" +
               "id=" + id +
               ", maintenance_status='" + maintenance_status + '\'' +
               ", incident_severity='" + incident_severity + '\'' +
               ", titles=" + titles +
               ", updates=" + updates +
               ", created_at='" + created_at + '\'' +
               ", archive_at='" + archive_at + '\'' +
               ", updated_at='" + updated_at + '\'' +
               ", platforms=" + platforms +
               '}';
    }
}
