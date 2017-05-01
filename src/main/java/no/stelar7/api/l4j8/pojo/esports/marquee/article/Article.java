package no.stelar7.api.l4j8.pojo.esports.marquee.article;

import java.util.*;

public class Article
{
    private String                    nid;
    private String                    uuid;
    private String                    tuuid;
    private String                    type;
    private String                    title;
    private String                    published;
    private String                    created;
    private String                    changed;
    private int                       status;
    private String                    region;
    private String                    locale;
    private String                    language;
    private String                    scheduled;
    private String                    shortTitle;
    private ArticlePath               path;
    private Object                    showIn;
    private ArticleCategoryWrapper    category;
    private String                    customTemplate;
    private String                    author;
    private Object                    bodySmall;
    private Object                    bodyMedium;
    private String                    bodyFull;
    private Map<String, ArticleMedia> media;
    private String                    redirect;
    private List<Object>              tags;
    private List<Object>              externalScripts;
    private List<Object>              backdrop;
    private List<String>              leagues;
    
    public ArticleComments getComments()
    {
        return comments;
    }
    
    private ArticleComments comments;
    
    public String getNid()
    {
        return nid;
    }
    
    
    public String getUuid()
    {
        return uuid;
    }
    
    
    public String getTuuid()
    {
        return tuuid;
    }
    
    
    public String getType()
    {
        return type;
    }
    
    
    public String getTitle()
    {
        return title;
    }
    
    
    public String getPublished()
    {
        return published;
    }
    
    
    public String getCreated()
    {
        return created;
    }
    
    
    public String getChanged()
    {
        return changed;
    }
    
    
    public int getStatus()
    {
        return status;
    }
    
    
    public String getRegion()
    {
        return region;
    }
    
    
    public String getLocale()
    {
        return locale;
    }
    
    
    public String getLanguage()
    {
        return language;
    }
    
    
    public String getScheduled()
    {
        return scheduled;
    }
    
    
    public String getShortTitle()
    {
        return shortTitle;
    }
    
    
    public ArticlePath getPath()
    {
        return path;
    }
    
    
    public Object getShowIn()
    {
        return showIn;
    }
    
    
    public ArticleCategoryWrapper getCategory()
    {
        return category;
    }
    
    
    public String getCustomTemplate()
    {
        return customTemplate;
    }
    
    
    public String getAuthor()
    {
        return author;
    }
    
    
    public Object getBodySmall()
    {
        return bodySmall;
    }
    
    
    public Object getBodyMedium()
    {
        return bodyMedium;
    }
    
    
    public String getBodyFull()
    {
        return bodyFull;
    }
    
    
    public Map<String, ArticleMedia> getMedia()
    {
        return media;
    }
    
    public String getRedirect()
    {
        return redirect;
    }
    
    
    public List<Object> getTags()
    {
        return tags;
    }
    
    
    public List<Object> getExternalScripts()
    {
        return externalScripts;
    }
    
    
    public List<Object> getBackdrop()
    {
        return backdrop;
    }
    
    
    public List<String> getLeagues()
    {
        return leagues;
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
        
        Article article = (Article) o;
        
        if (status != article.status)
        {
            return false;
        }
        if ((nid != null) ? !nid.equals(article.nid) : (article.nid != null))
        {
            return false;
        }
        if ((uuid != null) ? !uuid.equals(article.uuid) : (article.uuid != null))
        {
            return false;
        }
        if ((tuuid != null) ? !tuuid.equals(article.tuuid) : (article.tuuid != null))
        {
            return false;
        }
        if ((type != null) ? !type.equals(article.type) : (article.type != null))
        {
            return false;
        }
        if ((title != null) ? !title.equals(article.title) : (article.title != null))
        {
            return false;
        }
        if ((published != null) ? !published.equals(article.published) : (article.published != null))
        {
            return false;
        }
        if ((created != null) ? !created.equals(article.created) : (article.created != null))
        {
            return false;
        }
        if ((changed != null) ? !changed.equals(article.changed) : (article.changed != null))
        {
            return false;
        }
        if ((region != null) ? !region.equals(article.region) : (article.region != null))
        {
            return false;
        }
        if ((locale != null) ? !locale.equals(article.locale) : (article.locale != null))
        {
            return false;
        }
        if ((language != null) ? !language.equals(article.language) : (article.language != null))
        {
            return false;
        }
        if ((scheduled != null) ? !scheduled.equals(article.scheduled) : (article.scheduled != null))
        {
            return false;
        }
        if ((shortTitle != null) ? !shortTitle.equals(article.shortTitle) : (article.shortTitle != null))
        {
            return false;
        }
        if ((path != null) ? !path.equals(article.path) : (article.path != null))
        {
            return false;
        }
        if ((showIn != null) ? !showIn.equals(article.showIn) : (article.showIn != null))
        {
            return false;
        }
        if ((category != null) ? !category.equals(article.category) : (article.category != null))
        {
            return false;
        }
        if ((customTemplate != null) ? !customTemplate.equals(article.customTemplate) : (article.customTemplate != null))
        {
            return false;
        }
        if ((author != null) ? !author.equals(article.author) : (article.author != null))
        {
            return false;
        }
        if ((bodySmall != null) ? !bodySmall.equals(article.bodySmall) : (article.bodySmall != null))
        {
            return false;
        }
        if ((bodyMedium != null) ? !bodyMedium.equals(article.bodyMedium) : (article.bodyMedium != null))
        {
            return false;
        }
        if ((bodyFull != null) ? !bodyFull.equals(article.bodyFull) : (article.bodyFull != null))
        {
            return false;
        }
        if ((media != null) ? !media.equals(article.media) : (article.media != null))
        {
            return false;
        }
        if ((redirect != null) ? !redirect.equals(article.redirect) : (article.redirect != null))
        {
            return false;
        }
        if ((tags != null) ? !tags.equals(article.tags) : (article.tags != null))
        {
            return false;
        }
        if ((externalScripts != null) ? !externalScripts.equals(article.externalScripts) : (article.externalScripts != null))
        {
            return false;
        }
        if ((backdrop != null) ? !backdrop.equals(article.backdrop) : (article.backdrop != null))
        {
            return false;
        }
        if ((leagues != null) ? !leagues.equals(article.leagues) : (article.leagues != null))
        {
            return false;
        }
        return (comments != null) ? comments.equals(article.comments) : (article.comments == null);
    }
    
    @Override
    public int hashCode()
    {
        int result = nid != null ? nid.hashCode() : 0;
        result = 31 * result + (uuid != null ? uuid.hashCode() : 0);
        result = 31 * result + (tuuid != null ? tuuid.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (published != null ? published.hashCode() : 0);
        result = 31 * result + (created != null ? created.hashCode() : 0);
        result = 31 * result + (changed != null ? changed.hashCode() : 0);
        result = 31 * result + status;
        result = 31 * result + (region != null ? region.hashCode() : 0);
        result = 31 * result + (locale != null ? locale.hashCode() : 0);
        result = 31 * result + (language != null ? language.hashCode() : 0);
        result = 31 * result + (scheduled != null ? scheduled.hashCode() : 0);
        result = 31 * result + (shortTitle != null ? shortTitle.hashCode() : 0);
        result = 31 * result + (path != null ? path.hashCode() : 0);
        result = 31 * result + (showIn != null ? showIn.hashCode() : 0);
        result = 31 * result + (category != null ? category.hashCode() : 0);
        result = 31 * result + (customTemplate != null ? customTemplate.hashCode() : 0);
        result = 31 * result + (author != null ? author.hashCode() : 0);
        result = 31 * result + (bodySmall != null ? bodySmall.hashCode() : 0);
        result = 31 * result + (bodyMedium != null ? bodyMedium.hashCode() : 0);
        result = 31 * result + (bodyFull != null ? bodyFull.hashCode() : 0);
        result = 31 * result + (media != null ? media.hashCode() : 0);
        result = 31 * result + (redirect != null ? redirect.hashCode() : 0);
        result = 31 * result + (tags != null ? tags.hashCode() : 0);
        result = 31 * result + (externalScripts != null ? externalScripts.hashCode() : 0);
        result = 31 * result + (backdrop != null ? backdrop.hashCode() : 0);
        result = 31 * result + (leagues != null ? leagues.hashCode() : 0);
        result = 31 * result + (comments != null ? comments.hashCode() : 0);
        return result;
    }
    
    @Override
    public String toString()
    {
        return "Article{" +
               "nid='" + nid + '\'' +
               ", uuid='" + uuid + '\'' +
               ", tuuid='" + tuuid + '\'' +
               ", type='" + type + '\'' +
               ", title='" + title + '\'' +
               ", published='" + published + '\'' +
               ", created='" + created + '\'' +
               ", changed='" + changed + '\'' +
               ", status=" + status +
               ", region='" + region + '\'' +
               ", locale='" + locale + '\'' +
               ", language='" + language + '\'' +
               ", scheduled='" + scheduled + '\'' +
               ", shortTitle='" + shortTitle + '\'' +
               ", path=" + path +
               ", showIn=" + showIn +
               ", category=" + category +
               ", customTemplate='" + customTemplate + '\'' +
               ", author='" + author + '\'' +
               ", bodySmall=" + bodySmall +
               ", bodyMedium=" + bodyMedium +
               ", bodyFull='" + bodyFull + '\'' +
               ", media=" + media +
               ", redirect='" + redirect + '\'' +
               ", tags=" + tags +
               ", externalScripts=" + externalScripts +
               ", backdrop=" + backdrop +
               ", leagues=" + leagues +
               ", comments=" + comments +
               '}';
    }
}
