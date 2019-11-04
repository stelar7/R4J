package no.stelar7.api.l4j8.pojo.replay;

public enum ReplayTeamType
{
    ALL("ALL"),
    UNKNOWN("UNKNOWN"),
    ORDER("ORDER"),
    CHAOS("CHAOS"),
    NEUTRAL("NEUTRAL");
    
    private String value;
    
    ReplayTeamType(String value)
    {
        this.value = value;
    }
    
    public String getValue()
    {
        return value;
    }
}
