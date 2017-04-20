package no.stelar7.api.l4j8.pojo.staticdata.profileicon;

import no.stelar7.api.l4j8.pojo.staticdata.shared.Image;

public class ProfileIconDetails
{
    private Image  image;
    private String id;
    
    public Image getImage()
    {
        return image;
    }
    
    public String getId()
    {
        return id;
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
        
        ProfileIconDetails that = (ProfileIconDetails) o;
        
        if (image != null ? !image.equals(that.image) : that.image != null)
        {
            return false;
        }
        return id != null ? id.equals(that.id) : that.id == null;
    }
    
    @Override
    public int hashCode()
    {
        int result = image != null ? image.hashCode() : 0;
        result = 31 * result + (id != null ? id.hashCode() : 0);
        return result;
    }
    
    @Override
    public String toString()
    {
        return "ProfileIcon{" +
               "image=" + image +
               ", id='" + id + '\'' +
               '}';
    }
}
