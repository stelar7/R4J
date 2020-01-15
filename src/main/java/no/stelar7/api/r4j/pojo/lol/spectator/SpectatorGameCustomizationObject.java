package no.stelar7.api.r4j.pojo.lol.spectator;

import java.util.Objects;

public class SpectatorGameCustomizationObject
{
    private String category;
    private String content;
    
    public String getCategory()
    {
        return category;
    }
    
    public String getContent()
    {
        return content;
    }
    
    @Override
    public String toString()
    {
        return "SpectatorGameCustomizationObject{" +
               "category='" + category + '\'' +
               ", content='" + content + '\'' +
               '}';
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
        SpectatorGameCustomizationObject that = (SpectatorGameCustomizationObject) o;
        return Objects.equals(category, that.category) &&
               Objects.equals(content, that.content);
    }
    
    @Override
    public int hashCode()
    {
        return Objects.hash(category, content);
    }
}
