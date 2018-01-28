package no.stelar7.api.l4j8.pojo.staticdata.champion;

import no.stelar7.api.l4j8.pojo.staticdata.shared.Image;

import java.io.Serializable;

public class Passive implements Serializable
{
    private static final long serialVersionUID = -1431514724519691319L;
    
    private String description;
    private Image  image;
    private String name;
    private String sanitizedDescription;
    
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
        final Passive other = (Passive) obj;
        if (this.description == null)
        {
            if (other.description != null)
            {
                return false;
            }
        } else if (!this.description.equals(other.description))
        {
            return false;
        }
        if (this.image == null)
        {
            if (other.image != null)
            {
                return false;
            }
        } else if (!this.image.equals(other.image))
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
        if (this.sanitizedDescription == null)
        {
            return other.sanitizedDescription == null;
        } else
        {
            return this.sanitizedDescription.equals(other.sanitizedDescription);
        }
    }
    
    /**
     * Gets the description.
     *
     * @return the description
     */
    public String getDescription()
    {
        return this.description;
    }
    
    /**
     * Gets the image.
     *
     * @return the image
     */
    public Image getImage()
    {
        return this.image;
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
     * Gets the sanitized description.
     *
     * @return the sanitized description
     */
    public String getSanitizedDescription()
    {
        return this.sanitizedDescription;
    }
    
    @Override
    public int hashCode()
    {
        final int prime  = 31;
        int       result = 1;
        result = (prime * result) + ((this.description == null) ? 0 : this.description.hashCode());
        result = (prime * result) + ((this.image == null) ? 0 : this.image.hashCode());
        result = (prime * result) + ((this.name == null) ? 0 : this.name.hashCode());
        result = (prime * result) + ((this.sanitizedDescription == null) ? 0 : this.sanitizedDescription.hashCode());
        return result;
    }
    
    @Override
    public String toString()
    {
        return "Passive{" +
               "description='" + description + '\'' +
               ", image=" + image +
               ", name='" + name + '\'' +
               ", sanitizedDescription='" + sanitizedDescription + '\'' +
               '}';
    }
}
