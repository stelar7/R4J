package no.stelar7.api.l4j8.pojo.esports.marquee;

public class MarqueeItem
{
    private int    id;
    private int    marqueeId;
    private String article;
    private int    tier;
    private int    priority;
    private String createdAt;
    private String updatedAt;
    
    public int getId()
    {
        return id;
    }
    
    
    public int getMarqueeId()
    {
        return marqueeId;
    }
    
    
    public String getArticle()
    {
        return article;
    }
    
    
    public int getTier()
    {
        return tier;
    }
    
    
    public int getPriority()
    {
        return priority;
    }
    
    
    public String getCreatedAt()
    {
        return createdAt;
    }
    
    
    public String getUpdatedAt()
    {
        return updatedAt;
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
        
        MarqueeItem that = (MarqueeItem) o;
        
        if (id != that.id)
        {
            return false;
        }
        if (marqueeId != that.marqueeId)
        {
            return false;
        }
        if (tier != that.tier)
        {
            return false;
        }
        if (priority != that.priority)
        {
            return false;
        }
        if ((article != null) ? !article.equals(that.article) : (that.article != null))
        {
            return false;
        }
        if ((createdAt != null) ? !createdAt.equals(that.createdAt) : (that.createdAt != null))
        {
            return false;
        }
        return (updatedAt != null) ? updatedAt.equals(that.updatedAt) : (that.updatedAt == null);
    }
    
    @Override
    public int hashCode()
    {
        int result = id;
        result = 31 * result + marqueeId;
        result = 31 * result + (article != null ? article.hashCode() : 0);
        result = 31 * result + tier;
        result = 31 * result + priority;
        result = 31 * result + (createdAt != null ? createdAt.hashCode() : 0);
        result = 31 * result + (updatedAt != null ? updatedAt.hashCode() : 0);
        return result;
    }
    
    @Override
    public String toString()
    {
        return "MarqueeItem{" +
               "id=" + id +
               ", marqueeId=" + marqueeId +
               ", article='" + article + '\'' +
               ", tier=" + tier +
               ", priority=" + priority +
               ", createdAt='" + createdAt + '\'' +
               ", updatedAt='" + updatedAt + '\'' +
               '}';
    }
}
