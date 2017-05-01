package no.stelar7.api.l4j8.pojo.esports.marquee.article;

public class ArticleMedia
{
    private String          type;
    private String          original;
    private ArticleVariants variants;
    private String          mime;
    private String          uri;
    
    public String getType()
    {
        return type;
    }
    
    
    public String getOriginal()
    {
        return original;
    }
    
    
    public ArticleVariants getVariants()
    {
        return variants;
    }
    
    
    public String getMime()
    {
        return mime;
    }
    
    
    public String getUri()
    {
        return uri;
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
        
        ArticleMedia that = (ArticleMedia) o;
        
        if ((type != null) ? !type.equals(that.type) : (that.type != null))
        {
            return false;
        }
        if ((original != null) ? !original.equals(that.original) : (that.original != null))
        {
            return false;
        }
        if ((variants != null) ? !variants.equals(that.variants) : (that.variants != null))
        {
            return false;
        }
        if ((mime != null) ? !mime.equals(that.mime) : (that.mime != null))
        {
            return false;
        }
        return (uri != null) ? uri.equals(that.uri) : (that.uri == null);
    }
    
    @Override
    public int hashCode()
    {
        int result = type != null ? type.hashCode() : 0;
        result = 31 * result + (original != null ? original.hashCode() : 0);
        result = 31 * result + (variants != null ? variants.hashCode() : 0);
        result = 31 * result + (mime != null ? mime.hashCode() : 0);
        result = 31 * result + (uri != null ? uri.hashCode() : 0);
        return result;
    }
    
    @Override
    public String toString()
    {
        return "ArticleMedia{" +
               "type='" + type + '\'' +
               ", original='" + original + '\'' +
               ", variants=" + variants +
               ", mime='" + mime + '\'' +
               ", uri='" + uri + '\'' +
               '}';
    }
}
