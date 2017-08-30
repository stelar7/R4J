package no.stelar7.api.l4j8.pojo.match;

import java.io.Serializable;
import java.util.List;

public class MatchTimeline implements Serializable
{
    private long             frameInterval;
    private List<MatchFrame> frames;
    
    
    /**
     * Gets the frame interval.
     *
     * @return frame interval
     */
    public long getFrameInterval()
    {
        return this.frameInterval;
    }
    
    /**
     * Gets the frames.
     *
     * @return frames
     */
    public List<MatchFrame> getFrames()
    {
        return this.frames;
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
        
        MatchTimeline that = (MatchTimeline) o;
        
        if (frameInterval != that.frameInterval)
        {
            return false;
        }
        return (frames != null) ? frames.equals(that.frames) : (that.frames == null);
    }
    
    @Override
    public int hashCode()
    {
        int result = (int) (frameInterval ^ (frameInterval >>> 32));
        result = 31 * result + (frames != null ? frames.hashCode() : 0);
        return result;
    }
    
    @Override
    public String toString()
    {
        return "MatchTimeline{" +
               "frameInterval=" + frameInterval +
               ", frames=" + frames +
               '}';
    }
}
