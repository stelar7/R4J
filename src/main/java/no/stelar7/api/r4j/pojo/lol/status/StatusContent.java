package no.stelar7.api.r4j.pojo.lol.status;

import java.io.Serializable;
import java.util.Objects;

public class StatusContent implements Serializable
{
    private String locale;
    private String content;
    
    public String getLocale()
    {
        return locale;
    }
    
    public String getContent()
    {
        return content;
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
        StatusContent that = (StatusContent) o;
        return Objects.equals(locale, that.locale) &&
               Objects.equals(content, that.content);
    }
    
    @Override
    public int hashCode()
    {
        return Objects.hash(locale, content);
    }
    
    @Override
    public String toString()
    {
        return "StatusContent{" +
               "locale='" + locale + '\'' +
               ", content='" + content + '\'' +
               '}';
    }
}
