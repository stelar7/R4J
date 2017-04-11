package no.stelar7.api.l4j8.pojo.status;

import java.util.*;

public class Service
{
    private String         name;
    private String         slug;
    private String         status;
    private List<Incident> incidents;
    
    
    
    @Override
    public boolean equals(final Object obj)
    {
        if (this == obj)
        {
            return true;
        }
        if (obj == null)
        {
            return false;
        }
        if (this.getClass() != obj.getClass())
        {
            return false;
        }
        final Service other = (Service) obj;
        if (this.incidents == null)
        {
            if (other.incidents != null)
            {
                return false;
            }
        } else if (!this.incidents.equals(other.incidents))
        {
            return false;
        }
        if (this.name == null)
        {
            if (other.name != null)
            {
                return false;
            }
        } else if (!this.name.equals(other.name))
        {
            return false;
        }
        if (this.slug == null)
        {
            if (other.slug != null)
            {
                return false;
            }
        } else if (!this.slug.equals(other.slug))
        {
            return false;
        }
        if (this.status == null)
        {
            if (other.status != null)
            {
                return false;
            }
        } else if (!this.status.equals(other.status))
        {
            return false;
        }
        return true;
    }
    
    /**
     * Gets the incidents.
     *
     * @return the incidents
     */
    public List<Incident> getIncidents()
    {
        return this.incidents;
    }
    
    /**
     * Gets the name.
     *
     * @return the name
     */
    public String getName()
    {
        return this.name;
    }
    
    /**
     * Gets the slug.
     *
     * @return the slug
     */
    public String getSlug()
    {
        return this.slug;
    }
    
    /**
     * Gets the status. Legal values: Online, Alert, Offline, Deploying
     *
     * @return the status
     */
    public String getStatus()
    {
        return this.status;
    }
    
    @Override
    public int hashCode()
    {
        final int prime  = 31;
        int       result = 1;
        result = (prime * result) + ((this.incidents == null) ? 0 : this.incidents.hashCode());
        result = (prime * result) + ((this.name == null) ? 0 : this.name.hashCode());
        result = (prime * result) + ((this.slug == null) ? 0 : this.slug.hashCode());
        result = (prime * result) + ((this.status == null) ? 0 : this.status.hashCode());
        return result;
    }
    
    @Override
    public String toString()
    {
        return "Service{" +
               "name='" + name + '\'' +
               ", slug='" + slug + '\'' +
               ", status='" + status + '\'' +
               ", incidents=" + incidents +
               '}';
    }
}
