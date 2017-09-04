package no.stelar7.api.l4j8.pojo.status;

import java.io.Serializable;

public class Translation implements Serializable
{
    private static final long serialVersionUID = 3394393594073368289L;
    
    private String content;
    private String locale;
    private String heading;
    
    
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
        if (this.heading == null)
        {
            if (other.heading != null)
            {
                return false;
            }
        } else if (!this.heading.equals(other.heading))
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
     * Gets the heading.
     *
     * @return the heading
     */
    public String getUpdatedAt()
    {
        return this.heading;
    }
    
    @Override
    public int hashCode()
    {
        final int prime  = 31;
        int       result = 1;
        result = (prime * result) + ((this.content == null) ? 0 : this.content.hashCode());
        result = (prime * result) + ((this.locale == null) ? 0 : this.locale.hashCode());
        result = (prime * result) + ((this.heading == null) ? 0 : this.heading.hashCode());
        return result;
    }
    
    @Override
    public String toString()
    {
        return "Translation{" +
               "content='" + content + '\'' +
               ", locale='" + locale + '\'' +
               ", heading='" + heading + '\'' +
               '}';
    }
}
