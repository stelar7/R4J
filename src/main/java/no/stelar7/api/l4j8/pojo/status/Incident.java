package no.stelar7.api.l4j8.pojo.status;

import java.util.List;

public class Incident
{
    private Boolean       active;
    private String        created_at;
    private Long          id;
    private List<Message> updates;

    /**
     * Gets the active.
     *
     * @return the active
     */
    public Boolean getActive()
    {
        return active;
    }

    /**
     * Gets the created_at.
     *
     * @return the created_at
     */
    public String getCreated_at()
    {
        return created_at;
    }

    /**
     * Gets the id.
     *
     * @return the id
     */
    public Long getId()
    {
        return id;
    }

    /**
     * Gets the updates.
     *
     * @return the updates
     */
    public List<Message> getUpdates()
    {
        return updates;
    }

}
