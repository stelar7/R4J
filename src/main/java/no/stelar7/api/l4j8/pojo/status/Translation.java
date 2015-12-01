package no.stelar7.api.l4j8.pojo.status;

public class Translation
{
    private String content;
    private String locale;
    private String updated_at;

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
        final Translation other = (Translation) obj;
        if (this.content == null)
        {
            if (other.content != null)
            {
                return false;
            }
        } else if (!this.content.equals(other.content))
        {
            return false;
        }
        if (this.locale == null)
        {
            if (other.locale != null)
            {
                return false;
            }
        } else if (!this.locale.equals(other.locale))
        {
            return false;
        }
        if (this.updated_at == null)
        {
            if (other.updated_at != null)
            {
                return false;
            }
        } else if (!this.updated_at.equals(other.updated_at))
        {
            return false;
        }
        return true;
    }

    /**
     * Gets the content.
     *
     * @return the content
     */
    public String getContent()
    {
        return this.content;
    }

    /**
     * Gets the locale.
     *
     * @return the locale
     */
    public String getLocale()
    {
        return this.locale;
    }

    /**
     * Gets the updated_at.
     *
     * @return the updated_at
     */
    public String getUpdated_at()
    {
        return this.updated_at;
    }

    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = (prime * result) + ((this.content == null) ? 0 : this.content.hashCode());
        result = (prime * result) + ((this.locale == null) ? 0 : this.locale.hashCode());
        result = (prime * result) + ((this.updated_at == null) ? 0 : this.updated_at.hashCode());
        return result;
    }

}
