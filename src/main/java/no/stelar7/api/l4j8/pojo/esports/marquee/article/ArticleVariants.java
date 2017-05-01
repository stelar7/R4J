package no.stelar7.api.l4j8.pojo.esports.marquee.article;

public class ArticleVariants
{
    
    private String thumbnail;
    private String article_thumbnail;
    private String article_image;
    private String marquee;
    private String hero;
    private String grid_1st_spot;
    
    public String getArticleThumbnail()
    {
        return article_thumbnail;
    }
    
    
    public String getArticleImage()
    {
        return article_image;
    }
    
    public String getThumbnail()
    {
        return thumbnail;
    }
    
    public String getGridFirstSpot()
    {
        return grid_1st_spot;
    }
    
    public String getMarquee()
    {
        return marquee;
    }
    
    
    public String getHero()
    {
        return hero;
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
        
        ArticleVariants that = (ArticleVariants) o;
        
        if ((thumbnail != null) ? !thumbnail.equals(that.thumbnail) : (that.thumbnail != null))
        {
            return false;
        }
        if ((article_thumbnail != null) ? !article_thumbnail.equals(that.article_thumbnail) : (that.article_thumbnail != null))
        {
            return false;
        }
        if ((article_image != null) ? !article_image.equals(that.article_image) : (that.article_image != null))
        {
            return false;
        }
        if ((marquee != null) ? !marquee.equals(that.marquee) : (that.marquee != null))
        {
            return false;
        }
        if ((hero != null) ? !hero.equals(that.hero) : (that.hero != null))
        {
            return false;
        }
        return (grid_1st_spot != null) ? grid_1st_spot.equals(that.grid_1st_spot) : (that.grid_1st_spot == null);
    }
    
    @Override
    public int hashCode()
    {
        int result = thumbnail != null ? thumbnail.hashCode() : 0;
        result = 31 * result + (article_thumbnail != null ? article_thumbnail.hashCode() : 0);
        result = 31 * result + (article_image != null ? article_image.hashCode() : 0);
        result = 31 * result + (marquee != null ? marquee.hashCode() : 0);
        result = 31 * result + (hero != null ? hero.hashCode() : 0);
        result = 31 * result + (grid_1st_spot != null ? grid_1st_spot.hashCode() : 0);
        return result;
    }
    
    @Override
    public String toString()
    {
        return "ArticleVariants{" +
               "thumbnail='" + thumbnail + '\'' +
               ", article_thumbnail='" + article_thumbnail + '\'' +
               ", article_image='" + article_image + '\'' +
               ", marquee='" + marquee + '\'' +
               ", hero='" + hero + '\'' +
               ", grid_1st_spot='" + grid_1st_spot + '\'' +
               '}';
    }
}
