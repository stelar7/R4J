package no.stelar7.api.l4j8.pojo.esports.navitems;

public class NavItem
{
    private Integer id;
    private String  link;
    private String  slug;
    private String  label;
    private Object  imageUrl;
    private Integer order;
    private Boolean external;
    private Integer parentNavItem;
    private String  createdAt;
    private String  updatedAt;
    private Integer league;
    
    public int getId()
    {
        return id;
    }
    
    
    public String getLink()
    {
        return link;
    }
    
    
    public String getSlug()
    {
        return slug;
    }
    
    
    public String getLabel()
    {
        return label;
    }
    
    
    public Object getImageUrl()
    {
        return imageUrl;
    }
    
    
    public int getOrder()
    {
        return order;
    }
    
    
    public boolean isExternal()
    {
        return external;
    }
    
    
    public int getParentNavItem()
    {
        return parentNavItem;
    }
    
    
    public String getCreatedAt()
    {
        return createdAt;
    }
    
    
    public String getUpdatedAt()
    {
        return updatedAt;
    }
    
    
    public int getLeague()
    {
        return league;
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
        
        NavItem navItem = (NavItem) o;
        
        if ((id != null) ? !id.equals(navItem.id) : (navItem.id != null))
        {
            return false;
        }
        if ((link != null) ? !link.equals(navItem.link) : (navItem.link != null))
        {
            return false;
        }
        if ((slug != null) ? !slug.equals(navItem.slug) : (navItem.slug != null))
        {
            return false;
        }
        if ((label != null) ? !label.equals(navItem.label) : (navItem.label != null))
        {
            return false;
        }
        if ((imageUrl != null) ? !imageUrl.equals(navItem.imageUrl) : (navItem.imageUrl != null))
        {
            return false;
        }
        if ((order != null) ? !order.equals(navItem.order) : (navItem.order != null))
        {
            return false;
        }
        if ((external != null) ? !external.equals(navItem.external) : (navItem.external != null))
        {
            return false;
        }
        if ((parentNavItem != null) ? !parentNavItem.equals(navItem.parentNavItem) : (navItem.parentNavItem != null))
        {
            return false;
        }
        if ((createdAt != null) ? !createdAt.equals(navItem.createdAt) : (navItem.createdAt != null))
        {
            return false;
        }
        if ((updatedAt != null) ? !updatedAt.equals(navItem.updatedAt) : (navItem.updatedAt != null))
        {
            return false;
        }
        return (league != null) ? league.equals(navItem.league) : (navItem.league == null);
    }
    
    @Override
    public int hashCode()
    {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (link != null ? link.hashCode() : 0);
        result = 31 * result + (slug != null ? slug.hashCode() : 0);
        result = 31 * result + (label != null ? label.hashCode() : 0);
        result = 31 * result + (imageUrl != null ? imageUrl.hashCode() : 0);
        result = 31 * result + (order != null ? order.hashCode() : 0);
        result = 31 * result + (external != null ? external.hashCode() : 0);
        result = 31 * result + (parentNavItem != null ? parentNavItem.hashCode() : 0);
        result = 31 * result + (createdAt != null ? createdAt.hashCode() : 0);
        result = 31 * result + (updatedAt != null ? updatedAt.hashCode() : 0);
        result = 31 * result + (league != null ? league.hashCode() : 0);
        return result;
    }
    
    
    @Override
    public String toString()
    {
        return "NavItem{" +
               "id=" + id +
               ", link='" + link + '\'' +
               ", slug='" + slug + '\'' +
               ", label='" + label + '\'' +
               ", imageUrl=" + imageUrl +
               ", order=" + order +
               ", external=" + external +
               ", parentNavItem=" + parentNavItem +
               ", createdAt='" + createdAt + '\'' +
               ", updatedAt='" + updatedAt + '\'' +
               ", league=" + league +
               '}';
    }
}
