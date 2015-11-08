package no.stelar7.api.l4j8.pojo.match;

import java.util.List;

public class Timeline
{
    private Long        frameInterval;
    private List<Frame> frames;

    /**
     * Gets the frame interval.
     *
     * @return the frame interval
     */
    public Long getFrameInterval()
    {
        return frameInterval;
    }

    /**
     * Gets the frames.
     *
     * @return the frames
     */
    public List<Frame> getFrames()
    {
        return frames;
    }

}
