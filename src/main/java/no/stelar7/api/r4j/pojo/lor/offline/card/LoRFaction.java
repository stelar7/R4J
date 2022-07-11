package no.stelar7.api.r4j.pojo.lor.offline.card;

import no.stelar7.api.r4j.basic.constants.types.CodedEnum;

import java.util.*;

public enum LoRFaction implements CodedEnum<LoRFaction>
{
    DEMACIA(1, "DE", 0),
    FRELJORD(1, "FR", 1),
    IONIA(1, "IO", 2),
    NOXUS(1, "NX", 3),
    PILTOVER_AND_ZAUN(1, "PZ", 4),
    SHADOW_ILES(1, "SI", 5),
    BILGEWATER(2, "BW", 6),
    MOUNT_TARGON(2, "MT", 9),
    SHURIMA(3, "SH", 7),
    BANDLE_CITY(4, "BC", 10),
    RUNETERRA(5, "RU", 12),
    ;
    
    private final int    version;
    private final String shortCode;
    private final int    id;
    
    LoRFaction(int version, String shortCode, int id)
    {
        this.version = version;
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
    
    public int getVersion()
    {
        return version;
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
            case SHURIMA:
                return "Shurima";
            case BANDLE_CITY:
                return "Bandle City";
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
                return "MountTargon";
            case SHURIMA:
                return "Shurima";
            case BANDLE_CITY:
                return "BandleCity";
            default:
                throw new RuntimeException("Unknown region; please alert the API maintainer!");
        }
    }
}
