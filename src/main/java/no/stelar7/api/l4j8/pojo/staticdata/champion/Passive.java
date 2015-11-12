package no.stelar7.api.l4j8.pojo.staticdata.champion;

import no.stelar7.api.l4j8.pojo.staticdata.shared.Image;

public class Passive
{
    String description;
    Image  image;
    String name;
    String sanitizedDescription;

    /**
     * Gets the description.
     *
     * @return the description
     */
    public String getDescription()
    {
        return description;
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
     * Gets the name.
     *
     * @return the name
     */
    public String getName()
    {
        return name;
    }

    /**
     * Gets the sanitized description.
     *
     * @return the sanitized description
     */
    public String getSanitizedDescription()
    {
        return sanitizedDescription;
    }
}
