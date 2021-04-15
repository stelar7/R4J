package no.stelar7.api.r4j.pojo.lol.match.v4;

import no.stelar7.api.r4j.basic.constants.api.regions.LeagueShard;
import no.stelar7.api.r4j.impl.lol.raw.MatchAPI;

import java.io.Serializable;
import java.util.*;

public class MatchTimeline implements Serializable
{
    private static final long serialVersionUID = -5614060612188013016L;
    
    private long             frameInterval;
    private List<MatchFrame> frames;
    
    public static MatchTimeline byId(Long id, LeagueShard platform)
    {
        return MatchAPI.getInstance().getTimeline(platform, id);
    }
    
    
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
        return frameInterval == that.frameInterval &&
               Objects.equals(frames, that.frames);
    }
    
    @Override
    public int hashCode()
    {
        return Objects.hash(frameInterval, frames);
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
