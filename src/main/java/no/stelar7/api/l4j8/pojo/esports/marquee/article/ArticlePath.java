package no.stelar7.api.l4j8.pojo.esports.marquee.article;

import java.util.List;

public class ArticlePath
{
    private String       canonical;
    private List<String> alternate;
    
    public String getCanonical()
    {
        return canonical;
    }
    
    
    public List<String> getAlternate()
    {
        return alternate;
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
        
        ArticlePath that = (ArticlePath) o;
        
        if ((canonical != null) ? !canonical.equals(that.canonical) : (that.canonical != null))
        {
            return false;
        }
        return (alternate != null) ? alternate.equals(that.alternate) : (that.alternate == null);
    }
    
    @Override
    public int hashCode()
    {
        int result = canonical != null ? canonical.hashCode() : 0;
        result = 31 * result + (alternate != null ? alternate.hashCode() : 0);
        return result;
    }
    
    
    @Override
    public String toString()
    {
        return "ArticlePath{" +
               "canonical='" + canonical + '\'' +
               ", alternate=" + alternate +
               '}';
    }
}
