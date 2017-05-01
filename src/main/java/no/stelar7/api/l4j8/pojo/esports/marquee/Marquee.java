package no.stelar7.api.l4j8.pojo.esports.marquee;

import java.util.List;

public class Marquee
{
    
    private int           id;
    private String        slug;
    private String        locale;
    private String        publishDate;
    private String        createdAt;
    private String        updatedAt;
    private List<Integer> marqueeItems;
    
    public int getId()
    {
        return id;
    }
    
    
    public String getSlug()
    {
        return slug;
    }
    
    
    public String getLocale()
    {
        return locale;
    }
    
    
    public String getPublishDate()
    {
        return publishDate;
    }
    
    
    public String getCreatedAt()
    {
        return createdAt;
    }
    
    
    public String getUpdatedAt()
    {
        return updatedAt;
    }
    
    
    public List<Integer> getMarqueeItems()
    {
        return marqueeItems;
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
        
        Marquee marquee = (Marquee) o;
        
        if (id != marquee.id)
        {
            return false;
        }
        if ((slug != null) ? !slug.equals(marquee.slug) : (marquee.slug != null))
        {
            return false;
        }
        if ((locale != null) ? !locale.equals(marquee.locale) : (marquee.locale != null))
        {
            return false;
        }
        if ((publishDate != null) ? !publishDate.equals(marquee.publishDate) : (marquee.publishDate != null))
        {
            return false;
        }
        if ((createdAt != null) ? !createdAt.equals(marquee.createdAt) : (marquee.createdAt != null))
        {
            return false;
        }
        if ((updatedAt != null) ? !updatedAt.equals(marquee.updatedAt) : (marquee.updatedAt != null))
        {
            return false;
        }
        return (marqueeItems != null) ? marqueeItems.equals(marquee.marqueeItems) : (marquee.marqueeItems == null);
    }
    
    @Override
    public int hashCode()
    {
        int result = id;
        result = 31 * result + (slug != null ? slug.hashCode() : 0);
        result = 31 * result + (locale != null ? locale.hashCode() : 0);
        result = 31 * result + (publishDate != null ? publishDate.hashCode() : 0);
        result = 31 * result + (createdAt != null ? createdAt.hashCode() : 0);
        result = 31 * result + (updatedAt != null ? updatedAt.hashCode() : 0);
        result = 31 * result + (marqueeItems != null ? marqueeItems.hashCode() : 0);
        return result;
    }
    
    
    @Override
    public String toString()
    {
        return "Marquee{" +
               "id=" + id +
               ", slug='" + slug + '\'' +
               ", locale='" + locale + '\'' +
               ", publishDate='" + publishDate + '\'' +
               ", createdAt='" + createdAt + '\'' +
               ", updatedAt='" + updatedAt + '\'' +
               ", marqueeItems=" + marqueeItems +
               '}';
    }
}
