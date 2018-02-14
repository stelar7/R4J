package no.stelar7.api.l4j8.basic.utils;

import no.stelar7.api.l4j8.basic.constants.types.LaneRoleType;

import java.util.Map;

public class QuadObject
{
    
    private Map<LaneRoleType, Integer> first;
    private float                      second;
    private float                      third;
    private Map<LaneRoleType, Integer> fourth;
    
    public QuadObject(Map<LaneRoleType, Integer> first, float second, float third, Map<LaneRoleType, Integer> fourth)
    {
        this.first = first;
        this.second = second;
        this.third = third;
        this.fourth = fourth;
    }
    
    public Map<LaneRoleType, Integer> getRoles()
    {
        return first;
    }
    
    public float getProbability()
    {
        return second;
    }
    
    public float getConfidence()
    {
        return third;
    }
    
    public Map<LaneRoleType, Integer> getSecondary()
    {
        return fourth;
    }
}
