package no.stelar7.api.l4j8.pojo.replay;

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
}
