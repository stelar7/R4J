package no.stelar7.api.l4j8.pojo.staticdata.mastery;

import java.util.List;

import no.stelar7.api.l4j8.basic.APIObject;
import no.stelar7.api.l4j8.pojo.staticdata.shared.Image;

public class Mastery implements APIObject
{
    List<String> description;
    Integer      id;
    Image        image;
    String       masteryTree;
    String       name;
    String       prereq;
    Integer      ranks;
    List<String> sanitizedDescription;

    /**
     * Gets the description.
     *
     * @return the description
     */
    public List<String> getDescription()
    {
        return description;
    }

    /**
     * Gets the id.
     *
     * @return the id
     */
    public Integer getId()
    {
        return id;
    }

    /**
     * Gets the image.
     *
     * @return the image
     */
    public Image getImage()
    {
        return image;
    }

    /**
     * Gets the mastery tree. 
     * 
     * Legal values: Defense, Offense, Utility
     *
     * @return the mastery tree
     */
    public String getMasteryTree()
    {
        return masteryTree;
    }

    /**
     * Gets the name.
     *
     * @return the name
     */
    public String getName()
    {
        return name;
    }

    /**
     * Gets the prereq.
     *
     * @return the prereq
     */
    public String getPrereq()
    {
        return prereq;
    }

    /**
     * Gets the ranks.
     *
     * @return the ranks
     */
    public Integer getRanks()
    {
        return ranks;
    }

    /**
     * Gets the sanitized description.
     *
     * @return the sanitized description
     */
    public List<String> getSanitizedDescription()
    {
        return sanitizedDescription;
    }
}
