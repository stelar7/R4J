package no.stelar7.api.r4j.pojo.lol.status;

import java.io.Serializable;
import java.util.*;

public class StatusUpdate implements Serializable
{
    private int          id;
    private String       author;
    private boolean      publish;
    private List<String> publish_locations;
    private String       created_at;
    private String       updated_at;
    
    public int getId()
    {
        return id;
    }
    
    public String getAuthor()
    {
        return author;
    }
    
    public boolean isPublish()
    {
        return publish;
    }
    
    public List<String> getPublish_locations()
    {
        return publish_locations;
    }
    
    public String getCreated_at()
    {
        return created_at;
    }
    
    public String getUpdated_at()
    {
        return updated_at;
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
        StatusUpdate that = (StatusUpdate) o;
        return id == that.id &&
               publish == that.publish &&
               Objects.equals(author, that.author) &&
               Objects.equals(publish_locations, that.publish_locations) &&
               Objects.equals(created_at, that.created_at) &&
               Objects.equals(updated_at, that.updated_at);
    }
    
    @Override
    public int hashCode()
    {
        return Objects.hash(id, author, publish, publish_locations, created_at, updated_at);
    }
    
    @Override
    public String toString()
    {
        return "StatusUpdate{" +
               "id=" + id +
               ", author='" + author + '\'' +
               ", publish=" + publish +
               ", publish_locations=" + publish_locations +
               ", created_at='" + created_at + '\'' +
               ", updated_at='" + updated_at + '\'' +
               '}';
    }
}
