package no.stelar7.api.l4j8.pojo.status;

import java.util.List;

public class Service
{
    private String         name;
    private String         slug;
    private String         status;
    private List<Incident> incidents;

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
     * Gets the slug.
     *
     * @return the slug
     */
    public String getSlug()
    {
        return slug;
    }

    /**
     * Gets the status. Legal values: Online, Alert, Offline, Deploying
     *
     * @return the status
     */
    public String getStatus()
    {
        return status;
    }

    /**
     * Gets the incidents.
     *
     * @return the incidents
     */
    public List<Incident> getIncidents()
    {
        return incidents;
    }

}
