package no.stelar7.api.l4j8.pojo.esports.marquee;

import no.stelar7.api.l4j8.pojo.esports.marquee.article.Article;

import java.util.List;

public class MarqueeWapper
{
    
    private List<Marquee>     marquees;
    private List<MarqueeItem> marqueeItems;
    private List<Article>     articles;
    
    public List<Marquee> getMarquees()
    {
        return marquees;
    }
    
    public List<MarqueeItem> getMarqueeItems()
    {
        return marqueeItems;
    }
    
    public List<Article> getArticles()
    {
        return articles;
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
        
        MarqueeWapper that = (MarqueeWapper) o;
        
        if ((marquees != null) ? !marquees.equals(that.marquees) : (that.marquees != null))
        {
            return false;
        }
        if ((marqueeItems != null) ? !marqueeItems.equals(that.marqueeItems) : (that.marqueeItems != null))
        {
            return false;
        }
        return (articles != null) ? articles.equals(that.articles) : (that.articles == null);
    }
    
    @Override
    public int hashCode()
    {
        int result = marquees != null ? marquees.hashCode() : 0;
        result = 31 * result + (marqueeItems != null ? marqueeItems.hashCode() : 0);
        result = 31 * result + (articles != null ? articles.hashCode() : 0);
        return result;
    }
    
    
    @Override
    public String toString()
    {
        return "MarqueeWapper{" +
               "marquees=" + marquees +
               ", marqueeItems=" + marqueeItems +
               ", articles=" + articles +
               '}';
    }
}

