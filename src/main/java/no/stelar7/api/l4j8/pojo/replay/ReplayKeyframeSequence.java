package no.stelar7.api.l4j8.pojo.replay;

import no.stelar7.api.l4j8.pojo.replay.ReplayRenderInfo.*;

import java.util.List;

public class ReplayKeyframeSequence
{
    /**
     * Blending options for interpolating time between keyframes
     */
    public enum KeyframeBlend
    {
        LINEAR("linear"),
        SNAP(" snap"),
        SMOOTH_STEP(" smoothstep"),
        SMOOTHER_STEP(" smootherstep"),
        QUADRATIC_EASE_IN(" quadraticeasein"),
        QUADRATIC_EASE_OUT(" quadraticeaseout"),
        QUADRATIC_EASE_IN_OUT(" quadraticeaseinout"),
        CUBIC_EASE_IN(" cubiceasein"),
        CUBIC_EASE_OUT(" cubiceaseout"),
        CUBIC_EASE_IN_OUT(" cubiceaseinout"),
        QUARTIC_EASE_IN(" quarticeasein"),
        QUARTIC_EASE_OUT(" quarticeaseout"),
        QUARTIC_EASE_IN_OUT(" quarticeaseinout"),
        QUINTIC_EASE_IN(" quinticeasein"),
        QUINTIC_EASE_OUT(" quinticeaseout"),
        QUINTIC_EASE_IN_OUT(" quinticeaseinout"),
        SINE_EASE_IN(" sineeasein"),
        SINE_EASE_OUT(" sineeaseout"),
        SINE_EASE_IN_OUT(" sineeaseinout"),
        CIRCULAR_EASE_IN(" circulareasein"),
        CIRCULAR_EASE_OUT(" circulareaseout"),
        CIRCULAR_EASE_IN_OUT(" circulareaseinout"),
        EXPONENTIAL_EASE_IN(" exponentialeasein"),
        EXPONENTIAL_EASE_OUT(" exponentialeaseout"),
        EXPONENTIAL_EASE_IN_OUT(" exponentialeaseinout"),
        ELASTIC_EASE_IN(" elasticeasein"),
        ELASTIC_EASE_OUT(" elasticeaseout"),
        ELASTIC_EASE_IN_OUT(" elasticeaseinout"),
        BACK_EASE_IN(" backeasein"),
        BACK_EASE_OUT(" backeaseout"),
        BACK_EASE_IN_OUT(" backeaseinout"),
        BOUNCE_EASE_IN(" bounceeasein"),
        BOUNCE_EASE_OUT(" bounceeaseout"),
        BOUNCE_EASE_IN_OUT("bounceEaseInOut");
        String value;
        
        KeyframeBlend(String value)
        {
            this.value = value;
        }
    }
    
    public class BooleanKeyframe
    {
        /**
         * Keyframe blending
         */
        KeyframeBlend blend;
        /**
         * Game time in seconds of keyframe
         */
        float         time;
        /**
         * Keyframe value
         */
        boolean       value;
    }
    
    public class FloatKeyframe
    {
        /**
         * Keyframe blending
         */
        KeyframeBlend blend;
        /**
         * Game time in seconds of keyframe
         */
        float         time;
        /**
         * Keyframe value
         */
        float         value;
    }
    
    public class Vector3fKeyframe
    {
        /**
         * Keyframe blending
         */
        KeyframeBlend blend;
        /**
         * Game time in seconds of keyframe
         */
        float         time;
        /**
         * Keyframe value
         */
        Vector3f      value;
    }
    
    public class Vector4fKeyframe
    {
        /**
         * Keyframe blending
         */
        KeyframeBlend blend;
        /**
         * Game time in seconds of keyframe
         */
        float         time;
        /**
         * Keyframe value
         */
        Vector4f      value;
    }
    
    /**
     * Keyframe track for Render.cameraPosition
     */
    List<Vector3fKeyframe> cameraPosition;
    /**
     * Keyframe track for Render.cameraRotation
     */
    List<Vector3fKeyframe> cameraRotation;
    /**
     * Keyframe track for Render.depthFogColor
     */
    List<Vector4fKeyframe> depthFogColor;
    /**
     * Keyframe track for Render.cameraPosition
     */
    List<BooleanKeyframe>  depthFogEnabled;
    /**
     * Keyframe track for Render.depthFogEnd
     */
    List<FloatKeyframe>    depthFogEnd;
    /**
     * Keyframe track for Render.depthFogIntensity
     */
    List<FloatKeyframe>    depthFogIntensity;
    /**
     * Keyframe track for Render.depthFogStart
     */
    List<FloatKeyframe>    depthFogStart;
    /**
     * Keyframe track for Render.depthOfFieldCircle
     */
    List<FloatKeyframe>    depthOfFieldCircle;
    /**
     * Keyframe track for Render.depthOfFieldEnabled
     */
    List<BooleanKeyframe>  depthOfFieldEnabled;
    /**
     * Keyframe track for Render.depthOfFieldFar
     */
    List<FloatKeyframe>    depthOfFieldFar;
    /**
     * Keyframe track for Render.depthOfFieldMid
     */
    List<FloatKeyframe>    depthOfFieldMid;
    /**
     * Keyframe track for Render.depthOfFieldNear
     */
    List<FloatKeyframe>    depthOfFieldNear;
    /**
     * Keyframe track for Render.depthOfFieldWidth
     */
    List<FloatKeyframe>    depthOfFieldWidth;
    /**
     * Keyframe track for Render.farClip
     */
    List<FloatKeyframe>    farClip;
    /**
     * Keyframe track for Render.fieldOfView
     */
    List<FloatKeyframe>    fieldOfView;
    /**
     * Keyframe track for Render.heightFogColor
     */
    List<Vector4fKeyframe> heightFogColor;
    /**
     * Keyframe track for Render.heightFogEnabled
     */
    List<BooleanKeyframe>  heightFogEnabled;
    /**
     * Keyframe track for Render.heightFogEnd
     */
    List<FloatKeyframe>    heightFogEnd;
    /**
     * Keyframe track for Render.heightFogIntensity
     */
    List<FloatKeyframe>    heightFogIntensity;
    /**
     * Keyframe track for Render.heightFogStart
     */
    List<FloatKeyframe>    heightFogStart;
    /**
     * Keyframe track for Render.navGridOffset
     */
    List<FloatKeyframe>    navGridOffset;
    /**
     * Keyframe track for Render.nearClip
     */
    List<FloatKeyframe>    nearClip;
    /**
     * Keyframe track for Render.speed
     */
    List<FloatKeyframe>    speed;
    /**
     * Keyframe track for Render.skyboxOffset
     */
    List<FloatKeyframe>    skyboxOffset;
    /**
     * Keyframe track for Render.skyboxRadius
     */
    List<FloatKeyframe>    skyboxRadius;
    /**
     * Keyframe track for Render.skyboxRotation
     */
    List<Vector3fKeyframe> skyboxRotation;
    /**
     * Keyframe track for Render.sunDirection
     */
    List<Vector3fKeyframe> sunDirection;
    
    
}
