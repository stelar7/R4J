package no.stelar7.api.l4j8.pojo.esports.navitems;

import java.util.Map;

public class EsportsLeague
{
    private int                 id;
    private String              slug;
    private String              name;
    private String              guid;
    private Object              region;
    private int                 drupalId;
    private String              logoUrl;
    private String              createdAt;
    private String              updatedAt;
    private Map<String, String> abouts;
    private Map<String, String> names;
    
    public int getId()
    {
        return id;
    }
    
    
    public String getSlug()
    {
        return slug;
    }
    
    
    public String getName()
    {
        return name;
    }
    
    
    public String getGuid()
    {
        return guid;
    }
    
    
    public Object getRegion()
    {
        return region;
    }
    
    
    public int getDrupalId()
    {
        return drupalId;
    }
    
    
    public String getLogoUrl()
    {
        return logoUrl;
    }
    
    
    public String getCreatedAt()
    {
        return createdAt;
    }
    
    
    public String getUpdatedAt()
    {
        return updatedAt;
    }
    
    
    public Map<String, String> getAbouts()
    {
        return abouts;
    }
    
    
    public Map<String, String> getNames()
    {
        return names;
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
        
        EsportsLeague that = (EsportsLeague) o;
        
        if (id != that.id)
        {
            return false;
        }
        if (drupalId != that.drupalId)
        {
            return false;
        }
        if ((slug != null) ? !slug.equals(that.slug) : (that.slug != null))
        {
            return false;
        }
        if ((name != null) ? !name.equals(that.name) : (that.name != null))
        {
            return false;
        }
        if ((guid != null) ? !guid.equals(that.guid) : (that.guid != null))
        {
            return false;
        }
        if ((region != null) ? !region.equals(that.region) : (that.region != null))
        {
            return false;
        }
        if ((logoUrl != null) ? !logoUrl.equals(that.logoUrl) : (that.logoUrl != null))
        {
            return false;
        }
        if ((createdAt != null) ? !createdAt.equals(that.createdAt) : (that.createdAt != null))
        {
            return false;
        }
        if ((updatedAt != null) ? !updatedAt.equals(that.updatedAt) : (that.updatedAt != null))
        {
            return false;
        }
        if ((abouts != null) ? !abouts.equals(that.abouts) : (that.abouts != null))
        {
            return false;
        }
        return (names != null) ? names.equals(that.names) : (that.names == null);
    }
    
    @Override
    public int hashCode()
    {
        int result = id;
        result = 31 * result + (slug != null ? slug.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (guid != null ? guid.hashCode() : 0);
        result = 31 * result + (region != null ? region.hashCode() : 0);
        result = 31 * result + drupalId;
        result = 31 * result + (logoUrl != null ? logoUrl.hashCode() : 0);
        result = 31 * result + (createdAt != null ? createdAt.hashCode() : 0);
        result = 31 * result + (updatedAt != null ? updatedAt.hashCode() : 0);
        result = 31 * result + (abouts != null ? abouts.hashCode() : 0);
        result = 31 * result + (names != null ? names.hashCode() : 0);
        return result;
    }
    
    @Override
    public String toString()
    {
        return "EsportsLeague{" +
               "id=" + id +
               ", slug='" + slug + '\'' +
               ", name='" + name + '\'' +
               ", guid='" + guid + '\'' +
               ", region=" + region +
               ", drupalId=" + drupalId +
               ", logoUrl='" + logoUrl + '\'' +
               ", createdAt='" + createdAt + '\'' +
               ", updatedAt='" + updatedAt + '\'' +
               ", abouts=" + abouts +
               ", names=" + names +
               '}';
    }
}
