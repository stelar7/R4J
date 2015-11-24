package no.stelar7.api.l4j8.pojo.status;

import java.util.List;

public class Message
{
    private String            author;
    private String            content;
    private String            created_at;
    private Long              id;
    private String            severity;
    private String            updated_at;
    private List<Translation> translations;

    /**
     * Gets the author.
     *
     * @return the author
     */
    public String getAuthor()
    {
        return author;
    }

    /**
     * Gets the content.
     *
     * @return the content
     */
    public String getContent()
    {
        return content;
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
     * Gets the severity. Legal values: Info, Alert, Error
     *
     * @return the severity
     */
    public String getSeverity()
    {
        return severity;
    }

    /**
     * Gets the updated_at.
     *
     * @return the updated_at
     */
    public String getUpdated_at()
    {
        return updated_at;
    }

    /**
     * Gets the translations.
     *
     * @return the translations
     */
    public List<Translation> getTranslations()
    {
        return translations;
    }

}
