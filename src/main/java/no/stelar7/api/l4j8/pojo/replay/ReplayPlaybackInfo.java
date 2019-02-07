package no.stelar7.api.l4j8.pojo.replay;

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
}
