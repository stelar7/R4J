package no.stelar7.api.l4j8.pojo.status;

import java.util.List;

public class ShardStatus
{
    private String        hostname;
    private List<String>  locales;
    private String        name;
    private String        region_tag;
    private List<Service> services;
    private String        slug;

    /**
     * Gets the hostname.
     *
     * @return the hostname
     */
    public String getHostname()
    {
        return hostname;
    }

    /**
     * Gets the locales.
     *
     * @return the locales
     */
    public List<String> getLocales()
    {
        return locales;
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
     * Gets the region_tag.
     *
     * @return the region_tag
     */
    public String getRegion_tag()
    {
        return region_tag;
    }

    /**
     * Gets the services.
     *
     * @return the services
     */
    public List<Service> getServices()
    {
        return services;
    }

    /**
     * Gets the slug.
     *
     * @return the slug
     */
    public String getSlug()
    {
        return slug;
    }

}
