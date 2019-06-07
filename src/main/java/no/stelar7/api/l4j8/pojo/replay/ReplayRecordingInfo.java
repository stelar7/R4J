package no.stelar7.api.l4j8.pojo.replay;

import java.util.Objects;

public class ReplayRecordingInfo
{
    public enum AVContainer
    {
        WEBM("webm"), PNG("png"), PNG_AND_DEPTH("pngAndDepth ");
        String value;
        
        AVContainer(String value)
        {
            this.value = value;
        }
    }
    
    /**
     * Indicates the output format of the recording (for example webm or png)
     */
    AVContainer codec;
    /**
     * Current time of the recording, indicating progress of the render
     */
    float       currentTime;
    /**
     * Game time in seconds where the recording ends
     */
    float       endTime;
    /**
     * True if the recording should match the target frames per second exactly by slowing down the recording if required
     */
    boolean     enforceFrameRate;
    /**
     * Target number of frames per second to record in the video
     */
    int         framesPerSecond;
    /**
     * Height of the output video in pixels (same as the game window size)
     */
    int         height;
    /**
     * True if the recording is being output in a lossless codec (no compression)
     */
    boolean     lossless;
    /**
     * File path or directory where the recording should be saved
     */
    String      path;
    /**
     * True if we are currently recording a replay
     */
    boolean     recording;
    /**
     * Playback speed used when recording
     */
    float       replaySpeed;
    /**
     * Game time in seconds where the recording starts
     */
    float       startTime;
    /**
     * Width of the output video in pixels (same as the game window size)
     */
    int         width;
    
    public AVContainer getCodec()
    {
        return codec;
    }
    
    public float getCurrentTime()
    {
        return currentTime;
    }
    
    public float getEndTime()
    {
        return endTime;
    }
    
    public boolean isEnforceFrameRate()
    {
        return enforceFrameRate;
    }
    
    public int getFramesPerSecond()
    {
        return framesPerSecond;
    }
    
    public int getHeight()
    {
        return height;
    }
    
    public boolean isLossless()
    {
        return lossless;
    }
    
    public String getPath()
    {
        return path;
    }
    
    public boolean isRecording()
    {
        return recording;
    }
    
    public float getReplaySpeed()
    {
        return replaySpeed;
    }
    
    public float getStartTime()
    {
        return startTime;
    }
    
    public int getWidth()
    {
        return width;
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
        ReplayRecordingInfo that = (ReplayRecordingInfo) o;
        return Float.compare(that.currentTime, currentTime) == 0 &&
               Float.compare(that.endTime, endTime) == 0 &&
               enforceFrameRate == that.enforceFrameRate &&
               framesPerSecond == that.framesPerSecond &&
               height == that.height &&
               lossless == that.lossless &&
               recording == that.recording &&
               Float.compare(that.replaySpeed, replaySpeed) == 0 &&
               Float.compare(that.startTime, startTime) == 0 &&
               width == that.width &&
               codec == that.codec &&
               Objects.equals(path, that.path);
    }
    
    @Override
    public int hashCode()
    {
        return Objects.hash(codec, currentTime, endTime, enforceFrameRate, framesPerSecond, height, lossless, path, recording, replaySpeed, startTime, width);
    }
    
    @Override
    public String toString()
    {
        return "ReplayRecordingInfo{" +
               "codec=" + codec +
               ", currentTime=" + currentTime +
               ", endTime=" + endTime +
               ", enforceFrameRate=" + enforceFrameRate +
               ", framesPerSecond=" + framesPerSecond +
               ", height=" + height +
               ", lossless=" + lossless +
               ", path='" + path + '\'' +
               ", recording=" + recording +
               ", replaySpeed=" + replaySpeed +
               ", startTime=" + startTime +
               ", width=" + width +
               '}';
    }
}
