package no.stelar7.api.r4j.impl.lol.replay;

import no.stelar7.api.r4j.basic.calling.DataCallBuilder;
import no.stelar7.api.r4j.basic.constants.api.*;
import no.stelar7.api.r4j.basic.exceptions.APIResponseException;
import no.stelar7.api.r4j.basic.utils.Utils;
import no.stelar7.api.r4j.pojo.lol.replay.*;

import java.util.Map;

public class ReplayAPI
{
    public static ReplayGameClientProcessInfo getProcessInfo()
    {
        try
        {
            return (ReplayGameClientProcessInfo) new DataCallBuilder()
                    .withLimiters(false)
                    .withProxy(Constants.CLIENT_PROXY)
                    .withEndpoint(URLEndpoint.REPLAY_GAME)
                    .withRequestMethod("GET")
                    .build();
        } catch (APIResponseException e)
        {
            return null;
        }
    }
    
    public static void setParticleVisibility(Map<String, Boolean> particles)
    {
        try
        {
            new DataCallBuilder()
                    .withLimiters(false)
                    .withProxy(Constants.CLIENT_PROXY)
                    .withEndpoint(URLEndpoint.REPLAY_PARTICLES)
                    .withRequestMethod("POST")
                    .withPostData(Utils.getGson().toJson(particles))
                    .build();
            
        } catch (APIResponseException e)
        {
            e.printStackTrace();
        }
    }
    
    public static Map<String, Boolean> getParticleVisibility()
    {
        try
        {
            return (Map<String, Boolean>) new DataCallBuilder()
                    .withLimiters(false)
                    .withProxy(Constants.CLIENT_PROXY)
                    .withEndpoint(URLEndpoint.REPLAY_PARTICLES)
                    .withRequestMethod("GET")
                    .build();
        } catch (APIResponseException e)
        {
            return null;
        }
    }
    
    public static void setPlaybackParameters(ReplayPlaybackInfo info)
    {
        try
        {
            new DataCallBuilder()
                    .withLimiters(false)
                    .withProxy(Constants.CLIENT_PROXY)
                    .withEndpoint(URLEndpoint.REPLAY_PLAYBACK)
                    .withRequestMethod("POST")
                    .withPostData(Utils.getGson().toJson(info))
                    .build();
            
        } catch (APIResponseException e)
        {
            e.printStackTrace();
        }
    }
    
    public static ReplayPlaybackInfo getPlaybackParameters()
    {
        try
        {
            return (ReplayPlaybackInfo) new DataCallBuilder()
                    .withLimiters(false)
                    .withProxy(Constants.CLIENT_PROXY)
                    .withEndpoint(URLEndpoint.REPLAY_PLAYBACK)
                    .withRequestMethod("GET")
                    .build();
        } catch (APIResponseException e)
        {
            return null;
        }
    }
    
    public static void setRecordingParameters(ReplayRecordingInfo info)
    {
        try
        {
            new DataCallBuilder()
                    .withLimiters(false)
                    .withProxy(Constants.CLIENT_PROXY)
                    .withEndpoint(URLEndpoint.REPLAY_RECORDING)
                    .withRequestMethod("POST")
                    .withPostData(Utils.getGson().toJson(info))
                    .build();
            
        } catch (APIResponseException e)
        {
            e.printStackTrace();
        }
    }
    
    public static ReplayRecordingInfo getRecordingParameters()
    {
        try
        {
            return (ReplayRecordingInfo) new DataCallBuilder()
                    .withLimiters(false)
                    .withProxy(Constants.CLIENT_PROXY)
                    .withEndpoint(URLEndpoint.REPLAY_RECORDING)
                    .withRequestMethod("GET")
                    .build();
        } catch (APIResponseException e)
        {
            return null;
        }
    }
    
    public static void setRenderParameters(ReplayRenderInfo info)
    {
        try
        {
            new DataCallBuilder()
                    .withLimiters(false)
                    .withProxy(Constants.CLIENT_PROXY)
                    .withEndpoint(URLEndpoint.REPLAY_RENDER)
                    .withRequestMethod("POST")
                    .withPostData(Utils.getGson().toJson(info))
                    .build();
            
        } catch (APIResponseException e)
        {
            e.printStackTrace();
        }
    }
    
    public static ReplayRenderInfo getRenderParameters()
    {
        try
        {
            return (ReplayRenderInfo) new DataCallBuilder()
                    .withLimiters(false)
                    .withProxy(Constants.CLIENT_PROXY)
                    .withEndpoint(URLEndpoint.REPLAY_RENDER)
                    .withRequestMethod("GET")
                    .build();
        } catch (APIResponseException e)
        {
            return null;
        }
    }
    
    public static void setSequenceParameters(ReplayKeyframeSequence info)
    {
        try
        {
            new DataCallBuilder()
                    .withLimiters(false)
                    .withProxy(Constants.CLIENT_PROXY)
                    .withEndpoint(URLEndpoint.REPLAY_SEQUENCE)
                    .withRequestMethod("POST")
                    .withPostData(Utils.getGson().toJson(info))
                    .build();
            
        } catch (APIResponseException e)
        {
            e.printStackTrace();
        }
    }
    
    public static ReplayKeyframeSequence getSequenceParameters()
    {
        try
        {
            return (ReplayKeyframeSequence) new DataCallBuilder()
                    .withLimiters(false)
                    .withProxy(Constants.CLIENT_PROXY)
                    .withEndpoint(URLEndpoint.REPLAY_SEQUENCE)
                    .withRequestMethod("GET")
                    .build();
        } catch (APIResponseException e)
        {
            return null;
        }
    }
}
