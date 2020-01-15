package no.stelar7.api.r4j.pojo.lol.replay;

import java.util.Objects;

public class ReplayPlaybackInfo
{
    /**
     * Total length of the replay in seconds
     */
    float   length;
    /**
     * True if the replay is paused
     */
    boolean paused;
    /**
     * True if the replay is fast forwarding or rewinding
     */
    boolean seeking;
    /**
     * Replay playback speed (0.5 is half speed, 2.0 is double speed etc)
     */
    float   speed;
    /**
     * Current time of the replay in seconds since the beginning of the game.
     */
    float   time;
    
    public float getLength()
    {
        return length;
    }
    
    public boolean isPaused()
    {
        return paused;
    }
    
    public boolean isSeeking()
    {
        return seeking;
    }
    
    public float getSpeed()
    {
        return speed;
    }
    
    public float getTime()
    {
        return time;
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
        ReplayPlaybackInfo that = (ReplayPlaybackInfo) o;
        return Float.compare(that.length, length) == 0 &&
               paused == that.paused &&
               seeking == that.seeking &&
               Float.compare(that.speed, speed) == 0 &&
               Float.compare(that.time, time) == 0;
    }
    
    @Override
    public int hashCode()
    {
        return Objects.hash(length, paused, seeking, speed, time);
    }
    
    @Override
    public String toString()
    {
        return "ReplayPlaybackInfo{" +
               "length=" + length +
               ", paused=" + paused +
               ", seeking=" + seeking +
               ", speed=" + speed +
               ", time=" + time +
               '}';
    }
}
