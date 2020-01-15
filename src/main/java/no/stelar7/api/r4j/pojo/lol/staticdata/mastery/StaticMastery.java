package no.stelar7.api.r4j.pojo.lol.staticdata.mastery;

import no.stelar7.api.r4j.pojo.lol.staticdata.shared.Image;

import java.io.Serializable;
import java.util.*;

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
    
    
    /**
     * Gets the description.
     *
     * @return the description
     */
    public String getDescription()
    {
        return String.join("\n", this.description);
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
    public String getSanitizedDescription()
    {
        return sanitize(this.getDescription());
    }
    
    private String sanitize(String inData)
    {
        String outData = inData;
        outData = outData.replaceAll("<br>", "\n");
        outData = outData.replaceAll("<.+?>", "");
        return outData;
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
        return id == that.id &&
               ranks == that.ranks &&
               Objects.equals(description, that.description) &&
               Objects.equals(image, that.image) &&
               Objects.equals(masteryTree, that.masteryTree) &&
               Objects.equals(name, that.name) &&
               Objects.equals(prereq, that.prereq);
    }
    
    @Override
    public int hashCode()
    {
        return Objects.hash(description, id, image, masteryTree, name, prereq, ranks);
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
               '}';
    }
}
