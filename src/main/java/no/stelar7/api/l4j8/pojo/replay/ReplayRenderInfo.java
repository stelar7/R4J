package no.stelar7.api.l4j8.pojo.replay;

public class ReplayRenderInfo
{
    
    public enum CameraMode
    {
        TOP_DOWN("top"), FIRST_PERSON("fps"), THIRD_PERSON("tps"), FOCUS("focus"), PATH("path");
        String value;
        
        CameraMode(String value)
        {
            this.value = value;
        }
    }
    
    /**
     * 3D vector
     */
    public class Vector3f
    {
        float x;
        float y;
        float z;
    }
    
    /**
     * 4D vector
     */
    public class Vector4f
    {
        float a;
        float b;
        float g;
        float r;
    }
    
    /**
     * True if the camera is attached to an object in the game
     */
    boolean    cameraAttached;
    /**
     * Mouse look speed of the camera when in FIRST_PERSON mode (higher is faster)
     */
    float      cameraLookSpeed;
    /**
     * Camera movement mode such as first person or third person
     */
    CameraMode cameraMode;
    /**
     * Movement speed of the camera (higher is faster)
     */
    float      cameraMoveSpeed;
    /**
     * Position of the camera in world coordinates
     */
    Vector3f   cameraPosition;
    /**
     * Rotation of the camera in Euler degrees (yaw, pitch, roll)
     */
    Vector3f   cameraRotation;
    /**
     * Display champions and minions
     */
    boolean    characters;
    /**
     * Depth fog color specified in RGBA
     */
    Vector4f   depthFogColor;
    /**
     * Display depth based fog
     */
    boolean    depthFogEnabled;
    /**
     * Distance from the camera to the end of the fog
     */
    float      depthFogEnd;
    /**
     * Depth fog intensity (opacity from 0.0 to 1.0)
     */
    float      depthFogIntensity;
    /**
     * Distance from the camera to the start of the fog
     */
    float      depthFogStart;
    /**
     * Adjusts the shape and strength of the blur effect
     */
    float      depthOfFieldCircle;
    /**
     * Render a debug display to visualize depth of field distances
     */
    boolean    depthOfFieldDebug;
    /**
     * Display depth of field post processing
     */
    boolean    depthOfFieldEnabled;
    /**
     * Furthest distance from the camera in full blur
     */
    float      depthOfFieldFar;
    /**
     * Distance to the center of the depth of field effect, the point that will be the most in focus
     */
    float      depthOfFieldMid;
    /**
     * Closest distance from the camera in full blur
     */
    float      depthOfFieldNear;
    /**
     * Distance around the middle point that should be in focus
     */
    float      depthOfFieldWidth;
    /**
     * Display the level environment
     */
    boolean    environment;
    /**
     * Far camera clipping distance
     */
    float      farClip;
    /**
     * Camera field of view in degrees (default 45)
     */
    float      fieldOfView;
    /**
     * Display text notifications over the top of champions
     */
    boolean    floatingText;
    /**
     * Display fog of war
     */
    boolean    fogOfWar;
    /**
     * Display health bars on champions
     */
    boolean    healthBarChampions;
    /**
     * Display health bars on minions
     */
    boolean    healthBarMinions;
    /**
     * Display health bars on champion pets
     */
    boolean    healthBarPets;
    /**
     * Display health bars on structure and towers
     */
    boolean    healthBarStructures;
    /**
     * Display health bars on wards
     */
    boolean    healthBarWards;
    /**
     * Height fog color specified in RGBA
     */
    Vector4f   heightFogColor;
    /**
     * Display height based fog
     */
    boolean    heightFogEnabled;
    /**
     * Vertical height at the end of the fog
     */
    float      heightFogEnd;
    /**
     * "Height fog intensity (opacity from 0.0 to 1.0)
     */
    float      heightFogIntensity;
    /**
     * Vertical height at the start of the fog
     */
    float      heightFogStart;
    /**
     * Display all of the user interface
     */
    boolean    interfaceAll;
    /**
     * Display game announcements (center of the window)
     */
    boolean    interfaceAnnounce;
    /**
     * Display the chat window
     */
    boolean    interfaceChat;
    /**
     * Display the champion frames (sides of the window)
     */
    boolean    interfaceFrames;
    /**
     * Display the game minimap (bottom right corner)
     */
    boolean    interfaceMinimap;
    /**
     * Display quests
     */
    boolean    interfaceQuests;
    /**
     * Display the replay HUD with camera options
     */
    boolean    interfaceReplay;
    /**
     * Display the replay score interface (top of the window)
     */
    boolean    interfaceScore;
    /**
     * Display the replay scoreboard (bottom of the window)
     */
    boolean    interfaceScoreboard;
    /**
     * Display the target selection window
     */
    boolean    interfaceTarget;
    /**
     * Display the replay timeline (bottom of the window)
     */
    boolean    interfaceTimeline;
    /**
     * Adjusts the height that champions and minions walk over the environment
     */
    float      navGridOffset;
    /**
     * Near camera clipping distance
     */
    float      nearClip;
    /**
     * Display outlines on champions when the mouse is hovered over
     */
    float      outlineHover;
    /**
     * Display outlines on champions when selected
     */
    boolean    outlineSelect;
    /**
     * Display particles
     */
    boolean    particles;
    /**
     * Y-Axis offset of the skybox from the camera position
     */
    float      skyboxOffset;
    /**
     * Filepath for a cube mapped skybox in DDS format
     */
    String     skyboxPath;
    /**
     * Radius from the camera position to the edge of the skybox
     */
    float      skyboxRadius;
    /**
     * "Y-Axis rotation of the skybox in degrees
     */
    float      skyboxRotation;
    /**
     * Vector indicating the direction of the sun for shadows
     */
    Vector3f   sunDirection;
}
