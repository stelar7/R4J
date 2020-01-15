package no.stelar7.api.r4j.pojo.lor.card;

import java.util.*;

public enum LoRFaction
{
    DEMACIA("DE", 0),
    FRELJORD("FR", 1),
    IONIA("IO", 2),
    NOXUS("NX", 3),
    PILTOVER_AND_ZAUN("PZ", 4),
    SHADOW_ILES("SI", 5);
    
    private String shortCode;
    private int    id;
    
    LoRFaction(String shortCode, int id)
    {
        this.shortCode = shortCode;
        this.id = id;
    }
    
    public String getShortCode()
    {
        return shortCode;
    }
    
    public int getId()
    {
        return id;
    }
    
    public static LoRFaction fromID(int id)
    {
        return Arrays.stream(values()).filter(a -> a.id == id).findFirst().orElseThrow(NoSuchElementException::new);
    }
    
    public static LoRFaction fromCode(String code)
    {
        return Arrays.stream(values()).filter(a -> a.shortCode.equalsIgnoreCase(code)).findFirst().orElseThrow(NoSuchElementException::new);
    }
}
