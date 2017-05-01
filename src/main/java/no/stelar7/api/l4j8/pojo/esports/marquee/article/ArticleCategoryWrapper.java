package no.stelar7.api.l4j8.pojo.esports.marquee.article;

public class ArticleCategoryWrapper
{
    
    private ArticleCategory category;
    
    public ArticleCategory getCategory()
    {
        return category;
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
        
        ArticleCategoryWrapper that = (ArticleCategoryWrapper) o;
        
        return (category != null) ? category.equals(that.category) : (that.category == null);
    }
    
    @Override
    public int hashCode()
    {
        return category != null ? category.hashCode() : 0;
    }
    
    
    @Override
    public String toString()
    {
        return "ArticleCategoryWrapper{" +
               "category=" + category +
               '}';
    }
}
