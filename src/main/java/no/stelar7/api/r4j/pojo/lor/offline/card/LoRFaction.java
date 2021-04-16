package no.stelar7.api.r4j.pojo.lor.offline.card;

import no.stelar7.api.r4j.basic.constants.types.CodedEnum;

import java.util.*;

public enum LoRFaction implements CodedEnum<LoRFaction>
{
    DEMACIA("DE", 0),
    FRELJORD("FR", 1),
    IONIA("IO", 2),
    NOXUS("NX", 3),
    PILTOVER_AND_ZAUN("PZ", 4),
    SHADOW_ILES("SI", 5),
    BILGEWATER("BW", 6),
    SHURIMA("SH", 7),
    MOUNT_TARGON("MT", 9);
    
    private final String shortCode;
    private final int    id;
    
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
    
    @Override
    public Optional<LoRFaction> getFromCode(String code)
    {
        return Arrays.stream(values()).filter(a -> a.shortCode.equalsIgnoreCase(code)).findFirst();
    }
    
    @Override
    public String prettyName()
    {
        switch (this)
        {
            case DEMACIA:
                return "Demacia";
            case FRELJORD:
                return "Freljord";
            case IONIA:
                return "Ionia";
            case NOXUS:
                return "Noxus";
            case PILTOVER_AND_ZAUN:
                return "Piltover & Zaun";
            case SHADOW_ILES:
                return "Shadow Isles";
            case BILGEWATER:
                return "Bilgewater";
            case MOUNT_TARGON:
                return "Mount Targon";
            default:
                throw new RuntimeException("Unknown region; please alert the API maintainer!");
        }
    }
    
    @Override
    public String commonName()
    {
        switch (this)
        {
            case DEMACIA:
                return "Demacia";
            case FRELJORD:
                return "Freljord";
            case IONIA:
                return "Ionia";
            case NOXUS:
                return "Noxus";
            case PILTOVER_AND_ZAUN:
                return "PiltoverZaun";
            case SHADOW_ILES:
                return "ShadowIsles";
            case BILGEWATER:
                return "Bilgewater";
            case MOUNT_TARGON:
                return "Mount Targon";
            default:
                throw new RuntimeException("Unknown region; please alert the API maintainer!");
        }
    }
}
