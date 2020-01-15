package no.stelar7.api.r4j.pojo.lol.status;

import java.io.Serializable;
import java.util.List;

public class Incident implements Serializable
{
    private static final long serialVersionUID = -8823262961963183465L;
    
    private boolean       active;
    private String        created_at;
    private long          id;
    private List<Message> updates;
    
    
    /**
     * Gets the active.
     *
     * @return the active
     */
    public boolean getActive()
    {
        return this.active;
    }
    
    /**
     * Gets the created_at.
     *
     * @return the created_at
     */
    public String getCreatedAt()
    {
        return this.created_at;
    }
    
    /**
     * Gets the id.
     *
     * @return the id
     */
    public long getId()
    {
        return this.id;
    }
    
    /**
     * Gets the updates.
     *
     * @return the updates
     */
    public List<Message> getUpdates()
    {
        return this.updates;
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
        
        Incident incident = (Incident) o;
        
        if (active != incident.active)
        {
            return false;
        }
        if (id != incident.id)
        {
            return false;
        }
        if ((created_at != null) ? !created_at.equals(incident.created_at) : (incident.created_at != null))
        {
            return false;
        }
        return (updates != null) ? updates.equals(incident.updates) : (incident.updates == null);
    }
    
    @Override
    public int hashCode()
    {
        int result = active ? 1 : 0;
        result = 31 * result + (created_at != null ? created_at.hashCode() : 0);
        result = 31 * result + (int) (id ^ (id >>> 32));
        result = 31 * result + (updates != null ? updates.hashCode() : 0);
        return result;
    }
    
    @Override
    public String toString()
    {
        return "Incident{" +
               "active=" + active +
               ", created_at='" + created_at + '\'' +
               ", id=" + id +
               ", updates=" + updates +
               '}';
    }
}
