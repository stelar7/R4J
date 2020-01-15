package no.stelar7.api.r4j.pojo.lol.replay;

import java.util.Objects;

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
    
    public boolean isCameraAttached()
    {
        return cameraAttached;
    }
    
    public float getCameraLookSpeed()
    {
        return cameraLookSpeed;
    }
    
    public CameraMode getCameraMode()
    {
        return cameraMode;
    }
    
    public float getCameraMoveSpeed()
    {
        return cameraMoveSpeed;
    }
    
    public Vector3f getCameraPosition()
    {
        return cameraPosition;
    }
    
    public Vector3f getCameraRotation()
    {
        return cameraRotation;
    }
    
    public boolean isCharacters()
    {
        return characters;
    }
    
    public Vector4f getDepthFogColor()
    {
        return depthFogColor;
    }
    
    public boolean isDepthFogEnabled()
    {
        return depthFogEnabled;
    }
    
    public float getDepthFogEnd()
    {
        return depthFogEnd;
    }
    
    public float getDepthFogIntensity()
    {
        return depthFogIntensity;
    }
    
    public float getDepthFogStart()
    {
        return depthFogStart;
    }
    
    public float getDepthOfFieldCircle()
    {
        return depthOfFieldCircle;
    }
    
    public boolean isDepthOfFieldDebug()
    {
        return depthOfFieldDebug;
    }
    
    public boolean isDepthOfFieldEnabled()
    {
        return depthOfFieldEnabled;
    }
    
    public float getDepthOfFieldFar()
    {
        return depthOfFieldFar;
    }
    
    public float getDepthOfFieldMid()
    {
        return depthOfFieldMid;
    }
    
    public float getDepthOfFieldNear()
    {
        return depthOfFieldNear;
    }
    
    public float getDepthOfFieldWidth()
    {
        return depthOfFieldWidth;
    }
    
    public boolean isEnvironment()
    {
        return environment;
    }
    
    public float getFarClip()
    {
        return farClip;
    }
    
    public float getFieldOfView()
    {
        return fieldOfView;
    }
    
    public boolean isFloatingText()
    {
        return floatingText;
    }
    
    public boolean isFogOfWar()
    {
        return fogOfWar;
    }
    
    public boolean isHealthBarChampions()
    {
        return healthBarChampions;
    }
    
    public boolean isHealthBarMinions()
    {
        return healthBarMinions;
    }
    
    public boolean isHealthBarPets()
    {
        return healthBarPets;
    }
    
    public boolean isHealthBarStructures()
    {
        return healthBarStructures;
    }
    
    public boolean isHealthBarWards()
    {
        return healthBarWards;
    }
    
    public Vector4f getHeightFogColor()
    {
        return heightFogColor;
    }
    
    public boolean isHeightFogEnabled()
    {
        return heightFogEnabled;
    }
    
    public float getHeightFogEnd()
    {
        return heightFogEnd;
    }
    
    public float getHeightFogIntensity()
    {
        return heightFogIntensity;
    }
    
    public float getHeightFogStart()
    {
        return heightFogStart;
    }
    
    public boolean isInterfaceAll()
    {
        return interfaceAll;
    }
    
    public boolean isInterfaceAnnounce()
    {
        return interfaceAnnounce;
    }
    
    public boolean isInterfaceChat()
    {
        return interfaceChat;
    }
    
    public boolean isInterfaceFrames()
    {
        return interfaceFrames;
    }
    
    public boolean isInterfaceMinimap()
    {
        return interfaceMinimap;
    }
    
    public boolean isInterfaceQuests()
    {
        return interfaceQuests;
    }
    
    public boolean isInterfaceReplay()
    {
        return interfaceReplay;
    }
    
    public boolean isInterfaceScore()
    {
        return interfaceScore;
    }
    
    public boolean isInterfaceScoreboard()
    {
        return interfaceScoreboard;
    }
    
    public boolean isInterfaceTarget()
    {
        return interfaceTarget;
    }
    
    public boolean isInterfaceTimeline()
    {
        return interfaceTimeline;
    }
    
    public float getNavGridOffset()
    {
        return navGridOffset;
    }
    
    public float getNearClip()
    {
        return nearClip;
    }
    
    public float getOutlineHover()
    {
        return outlineHover;
    }
    
    public boolean isOutlineSelect()
    {
        return outlineSelect;
    }
    
    public boolean isParticles()
    {
        return particles;
    }
    
    public float getSkyboxOffset()
    {
        return skyboxOffset;
    }
    
    public String getSkyboxPath()
    {
        return skyboxPath;
    }
    
    public float getSkyboxRadius()
    {
        return skyboxRadius;
    }
    
    public float getSkyboxRotation()
    {
        return skyboxRotation;
    }
    
    public Vector3f getSunDirection()
    {
        return sunDirection;
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
        ReplayRenderInfo that = (ReplayRenderInfo) o;
        return cameraAttached == that.cameraAttached &&
               Float.compare(that.cameraLookSpeed, cameraLookSpeed) == 0 &&
               Float.compare(that.cameraMoveSpeed, cameraMoveSpeed) == 0 &&
               characters == that.characters &&
               depthFogEnabled == that.depthFogEnabled &&
               Float.compare(that.depthFogEnd, depthFogEnd) == 0 &&
               Float.compare(that.depthFogIntensity, depthFogIntensity) == 0 &&
               Float.compare(that.depthFogStart, depthFogStart) == 0 &&
               Float.compare(that.depthOfFieldCircle, depthOfFieldCircle) == 0 &&
               depthOfFieldDebug == that.depthOfFieldDebug &&
               depthOfFieldEnabled == that.depthOfFieldEnabled &&
               Float.compare(that.depthOfFieldFar, depthOfFieldFar) == 0 &&
               Float.compare(that.depthOfFieldMid, depthOfFieldMid) == 0 &&
               Float.compare(that.depthOfFieldNear, depthOfFieldNear) == 0 &&
               Float.compare(that.depthOfFieldWidth, depthOfFieldWidth) == 0 &&
               environment == that.environment &&
               Float.compare(that.farClip, farClip) == 0 &&
               Float.compare(that.fieldOfView, fieldOfView) == 0 &&
               floatingText == that.floatingText &&
               fogOfWar == that.fogOfWar &&
               healthBarChampions == that.healthBarChampions &&
               healthBarMinions == that.healthBarMinions &&
               healthBarPets == that.healthBarPets &&
               healthBarStructures == that.healthBarStructures &&
               healthBarWards == that.healthBarWards &&
               heightFogEnabled == that.heightFogEnabled &&
               Float.compare(that.heightFogEnd, heightFogEnd) == 0 &&
               Float.compare(that.heightFogIntensity, heightFogIntensity) == 0 &&
               Float.compare(that.heightFogStart, heightFogStart) == 0 &&
               interfaceAll == that.interfaceAll &&
               interfaceAnnounce == that.interfaceAnnounce &&
               interfaceChat == that.interfaceChat &&
               interfaceFrames == that.interfaceFrames &&
               interfaceMinimap == that.interfaceMinimap &&
               interfaceQuests == that.interfaceQuests &&
               interfaceReplay == that.interfaceReplay &&
               interfaceScore == that.interfaceScore &&
               interfaceScoreboard == that.interfaceScoreboard &&
               interfaceTarget == that.interfaceTarget &&
               interfaceTimeline == that.interfaceTimeline &&
               Float.compare(that.navGridOffset, navGridOffset) == 0 &&
               Float.compare(that.nearClip, nearClip) == 0 &&
               Float.compare(that.outlineHover, outlineHover) == 0 &&
               outlineSelect == that.outlineSelect &&
               particles == that.particles &&
               Float.compare(that.skyboxOffset, skyboxOffset) == 0 &&
               Float.compare(that.skyboxRadius, skyboxRadius) == 0 &&
               Float.compare(that.skyboxRotation, skyboxRotation) == 0 &&
               cameraMode == that.cameraMode &&
               Objects.equals(cameraPosition, that.cameraPosition) &&
               Objects.equals(cameraRotation, that.cameraRotation) &&
               Objects.equals(depthFogColor, that.depthFogColor) &&
               Objects.equals(heightFogColor, that.heightFogColor) &&
               Objects.equals(skyboxPath, that.skyboxPath) &&
               Objects.equals(sunDirection, that.sunDirection);
    }
    
    @Override
    public int hashCode()
    {
        return Objects.hash(cameraAttached, cameraLookSpeed, cameraMode, cameraMoveSpeed, cameraPosition, cameraRotation, characters, depthFogColor, depthFogEnabled, depthFogEnd, depthFogIntensity, depthFogStart, depthOfFieldCircle, depthOfFieldDebug, depthOfFieldEnabled, depthOfFieldFar, depthOfFieldMid, depthOfFieldNear, depthOfFieldWidth, environment, farClip, fieldOfView, floatingText, fogOfWar, healthBarChampions, healthBarMinions, healthBarPets, healthBarStructures, healthBarWards, heightFogColor, heightFogEnabled, heightFogEnd, heightFogIntensity, heightFogStart, interfaceAll, interfaceAnnounce, interfaceChat, interfaceFrames, interfaceMinimap, interfaceQuests, interfaceReplay, interfaceScore, interfaceScoreboard, interfaceTarget, interfaceTimeline, navGridOffset, nearClip, outlineHover, outlineSelect, particles, skyboxOffset, skyboxPath, skyboxRadius, skyboxRotation, sunDirection);
    }
    
    @Override
    public String toString()
    {
        return "ReplayRenderInfo{" +
               "cameraAttached=" + cameraAttached +
               ", cameraLookSpeed=" + cameraLookSpeed +
               ", cameraMode=" + cameraMode +
               ", cameraMoveSpeed=" + cameraMoveSpeed +
               ", cameraPosition=" + cameraPosition +
               ", cameraRotation=" + cameraRotation +
               ", characters=" + characters +
               ", depthFogColor=" + depthFogColor +
               ", depthFogEnabled=" + depthFogEnabled +
               ", depthFogEnd=" + depthFogEnd +
               ", depthFogIntensity=" + depthFogIntensity +
               ", depthFogStart=" + depthFogStart +
               ", depthOfFieldCircle=" + depthOfFieldCircle +
               ", depthOfFieldDebug=" + depthOfFieldDebug +
               ", depthOfFieldEnabled=" + depthOfFieldEnabled +
               ", depthOfFieldFar=" + depthOfFieldFar +
               ", depthOfFieldMid=" + depthOfFieldMid +
               ", depthOfFieldNear=" + depthOfFieldNear +
               ", depthOfFieldWidth=" + depthOfFieldWidth +
               ", environment=" + environment +
               ", farClip=" + farClip +
               ", fieldOfView=" + fieldOfView +
               ", floatingText=" + floatingText +
               ", fogOfWar=" + fogOfWar +
               ", healthBarChampions=" + healthBarChampions +
               ", healthBarMinions=" + healthBarMinions +
               ", healthBarPets=" + healthBarPets +
               ", healthBarStructures=" + healthBarStructures +
               ", healthBarWards=" + healthBarWards +
               ", heightFogColor=" + heightFogColor +
               ", heightFogEnabled=" + heightFogEnabled +
               ", heightFogEnd=" + heightFogEnd +
               ", heightFogIntensity=" + heightFogIntensity +
               ", heightFogStart=" + heightFogStart +
               ", interfaceAll=" + interfaceAll +
               ", interfaceAnnounce=" + interfaceAnnounce +
               ", interfaceChat=" + interfaceChat +
               ", interfaceFrames=" + interfaceFrames +
               ", interfaceMinimap=" + interfaceMinimap +
               ", interfaceQuests=" + interfaceQuests +
               ", interfaceReplay=" + interfaceReplay +
               ", interfaceScore=" + interfaceScore +
               ", interfaceScoreboard=" + interfaceScoreboard +
               ", interfaceTarget=" + interfaceTarget +
               ", interfaceTimeline=" + interfaceTimeline +
               ", navGridOffset=" + navGridOffset +
               ", nearClip=" + nearClip +
               ", outlineHover=" + outlineHover +
               ", outlineSelect=" + outlineSelect +
               ", particles=" + particles +
               ", skyboxOffset=" + skyboxOffset +
               ", skyboxPath='" + skyboxPath + '\'' +
               ", skyboxRadius=" + skyboxRadius +
               ", skyboxRotation=" + skyboxRotation +
               ", sunDirection=" + sunDirection +
               '}';
    }
}
