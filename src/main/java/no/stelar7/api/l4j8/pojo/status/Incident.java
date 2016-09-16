package no.stelar7.api.l4j8.pojo.status;

import java.util.List;

public class Incident
{
    private Boolean       active;
    private String        created_at;
    private Long          id;
    private List<Message> updates;

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
        final Incident other = (Incident) obj;
        if (this.active == null)
        {
            if (other.active != null)
            {
                return false;
            }
        } else if (!this.active.equals(other.active))
        {
            return false;
        }
        if (this.created_at == null)
        {
            if (other.created_at != null)
            {
                return false;
            }
        } else if (!this.created_at.equals(other.created_at))
        {
            return false;
        }
        if (this.id == null)
        {
            if (other.id != null)
            {
                return false;
            }
        } else if (!this.id.equals(other.id))
        {
            return false;
        }
        if (this.updates == null)
        {
            if (other.updates != null)
            {
                return false;
            }
        } else if (!this.updates.equals(other.updates))
        {
            return false;
        }
        return true;
    }

    /**
     * Gets the active.
     *
     * @return the active
     */
    public Boolean getActive()
    {
        return this.active;
    }

    /**
     * Gets the created_at.
     *
     * @return the created_at
     */
    public String getCreated_at()
    {
        return this.created_at;
    }

    /**
     * Gets the id.
     *
     * @return the id
     */
    public Long getId()
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
    public int hashCode()
    {
        final int prime  = 31;
        int       result = 1;
        result = (prime * result) + ((this.active == null) ? 0 : this.active.hashCode());
        result = (prime * result) + ((this.created_at == null) ? 0 : this.created_at.hashCode());
        result = (prime * result) + ((this.id == null) ? 0 : this.id.hashCode());
        result = (prime * result) + ((this.updates == null) ? 0 : this.updates.hashCode());
        return result;
    }

}
