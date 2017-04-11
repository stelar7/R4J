package no.stelar7.api.l4j8.pojo.staticdata.mastery;

import no.stelar7.api.l4j8.pojo.staticdata.shared.*;

import java.util.*;

public class Mastery
{
    private List<String> description;
    private Integer      id;
    private Image        image;
    private String       masteryTree;
    private String       name;
    private String       prereq;
    private Integer      ranks;
    private List<String> sanitizedDescription;
    
    
    
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
        final Mastery other = (Mastery) obj;
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
        if (this.masteryTree == null)
        {
            if (other.masteryTree != null)
            {
                return false;
            }
        } else if (!this.masteryTree.equals(other.masteryTree))
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
        if (this.prereq == null)
        {
            if (other.prereq != null)
            {
                return false;
            }
        } else if (!this.prereq.equals(other.prereq))
        {
            return false;
        }
        if (this.ranks == null)
        {
            if (other.ranks != null)
            {
                return false;
            }
        } else if (!this.ranks.equals(other.ranks))
        {
            return false;
        }
        if (this.sanitizedDescription == null)
        {
            if (other.sanitizedDescription != null)
            {
                return false;
            }
        } else if (!this.sanitizedDescription.equals(other.sanitizedDescription))
        {
            return false;
        }
        return true;
    }
    
    /**
     * Gets the description.
     *
     * @return the description
     */
    public List<String> getDescription()
    {
        return this.description;
    }
    
    /**
     * Gets the id.
     *
     * @return the id
     */
    public Integer getId()
    {
        return this.id;
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
     * Gets the mastery tree.
     * <p>
     * Legal values: Defense, Offense, Utility
     *
     * @return the mastery tree
     */
    public String getMasteryTree()
    {
        return this.masteryTree;
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
     * Gets the prereq.
     *
     * @return the prereq
     */
    public String getPrereq()
    {
        return this.prereq;
    }
    
    /**
     * Gets the ranks.
     *
     * @return the ranks
     */
    public Integer getRanks()
    {
        return this.ranks;
    }
    
    /**
     * Gets the sanitized description.
     *
     * @return the sanitized description
     */
    public List<String> getSanitizedDescription()
    {
        return this.sanitizedDescription;
    }
    
    @Override
    public int hashCode()
    {
        final int prime  = 31;
        int       result = 1;
        result = (prime * result) + ((this.description == null) ? 0 : this.description.hashCode());
        result = (prime * result) + ((this.id == null) ? 0 : this.id.hashCode());
        result = (prime * result) + ((this.image == null) ? 0 : this.image.hashCode());
        result = (prime * result) + ((this.masteryTree == null) ? 0 : this.masteryTree.hashCode());
        result = (prime * result) + ((this.name == null) ? 0 : this.name.hashCode());
        result = (prime * result) + ((this.prereq == null) ? 0 : this.prereq.hashCode());
        result = (prime * result) + ((this.ranks == null) ? 0 : this.ranks.hashCode());
        result = (prime * result) + ((this.sanitizedDescription == null) ? 0 : this.sanitizedDescription.hashCode());
        return result;
    }
    
    @Override
    public String toString()
    {
        return "Mastery{" +
               "description=" + description +
               ", id=" + id +
               ", image=" + image +
               ", masteryTree='" + masteryTree + '\'' +
               ", name='" + name + '\'' +
               ", prereq='" + prereq + '\'' +
               ", ranks=" + ranks +
               ", sanitizedDescription=" + sanitizedDescription +
               '}';
    }
}
