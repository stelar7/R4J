package no.stelar7.api.l4j8.pojo.esports.marquee.article;

public class ArticleComments
{
    private String discussionId;
    private String applicationId;
    private String serviceUrl;
    
    public String getDiscussionId()
    {
        return discussionId;
    }
    
    public String getApplicationId()
    {
        return applicationId;
    }
    
    public String getServiceUrl()
    {
        return serviceUrl;
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
        
        ArticleComments that = (ArticleComments) o;
        
        if ((discussionId != null) ? !discussionId.equals(that.discussionId) : (that.discussionId != null))
        {
            return false;
        }
        if ((applicationId != null) ? !applicationId.equals(that.applicationId) : (that.applicationId != null))
        {
            return false;
        }
        return (serviceUrl != null) ? serviceUrl.equals(that.serviceUrl) : (that.serviceUrl == null);
    }
    
    @Override
    public int hashCode()
    {
        int result = discussionId != null ? discussionId.hashCode() : 0;
        result = 31 * result + (applicationId != null ? applicationId.hashCode() : 0);
        result = 31 * result + (serviceUrl != null ? serviceUrl.hashCode() : 0);
        return result;
    }
    
    @Override
    public String toString()
    {
        return "ArticleComments{" +
               "discussionId='" + discussionId + '\'' +
               ", applicationId='" + applicationId + '\'' +
               ", serviceUrl='" + serviceUrl + '\'' +
               '}';
    }
}
