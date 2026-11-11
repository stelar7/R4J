package no.stelar7.api.r4j.pojo.riftbound;

import java.io.Serializable;
import java.util.*;

public class RiftboundCardArt implements Serializable
{
    private static final long serialVersionUID = 1L;

    private String thumbnailURL;
    private String fullURL;
    private String artist;

    public String getThumbnailURL()
    {
        return thumbnailURL;
    }
    
    public String getFullURL()
    {
        return fullURL;
    }
    
    public String getArtist()
    {
        return artist;
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
        RiftboundCardArt that = (RiftboundCardArt) o;
        return Objects.equals(thumbnailURL, that.thumbnailURL) &&
               Objects.equals(fullURL, that.fullURL) &&
               Objects.equals(artist, that.artist);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(thumbnailURL, fullURL, artist);
    }

    @Override
    public String toString()
    {
        return "RiftboundCardArt{" +
               "thumbnailURL=" + thumbnailURL +
               ", fullURL=" + fullURL +
               ", artist=" + artist +
               '}';
    }
}
