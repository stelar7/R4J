package no.stelar7.api.l4j8.pojo.staticdata.mastery;

import no.stelar7.api.l4j8.pojo.staticdata.shared.Image;

import java.io.Serializable;
import java.util.List;

public class StaticMastery implements Serializable
{
    private static final long serialVersionUID = -2913618253144483204L;
    
    private List<String> description;
    private int          id;
    private Image        image;
    private String       masteryTree;
    private String       name;
    private String       prereq;
    private int          ranks;
    private List<String> sanitizedDescription;
    
    
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
    public int getId()
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
    public int getRanks()
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
        
        StaticMastery that = (StaticMastery) o;
        
        if (id != that.id)
        {
            return false;
        }
        if (ranks != that.ranks)
        {
            return false;
        }
        if ((description != null) ? !description.equals(that.description) : (that.description != null))
        {
            return false;
        }
        if ((image != null) ? !image.equals(that.image) : (that.image != null))
        {
            return false;
        }
        if ((masteryTree != null) ? !masteryTree.equals(that.masteryTree) : (that.masteryTree != null))
        {
            return false;
        }
        if ((name != null) ? !name.equals(that.name) : (that.name != null))
        {
            return false;
        }
        if ((prereq != null) ? !prereq.equals(that.prereq) : (that.prereq != null))
        {
            return false;
        }
        return (sanitizedDescription != null) ? sanitizedDescription.equals(that.sanitizedDescription) : (that.sanitizedDescription == null);
    }
    
    @Override
    public int hashCode()
    {
        int result = description != null ? description.hashCode() : 0;
        result = 31 * result + id;
        result = 31 * result + (image != null ? image.hashCode() : 0);
        result = 31 * result + (masteryTree != null ? masteryTree.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (prereq != null ? prereq.hashCode() : 0);
        result = 31 * result + ranks;
        result = 31 * result + (sanitizedDescription != null ? sanitizedDescription.hashCode() : 0);
        return result;
    }
    
    @Override
    public String toString()
    {
        return "StaticMastery{" +
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
