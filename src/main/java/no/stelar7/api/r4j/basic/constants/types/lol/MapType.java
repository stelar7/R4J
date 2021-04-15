package no.stelar7.api.r4j.basic.constants.types.lol;

import no.stelar7.api.r4j.basic.constants.types.CodedEnum;
import no.stelar7.api.r4j.basic.utils.Rectangle;

import java.util.Optional;
import java.util.stream.Stream;

public enum MapType implements CodedEnum
{
    
    /**
     * Summoner's Rift Original Summer Variant
     */
    // -650, -83, 14076, 14522 ??
    SUMMONERS_RIFT_SUMMER_OLD(1, new Rectangle(-269, -83, 14276, 14522)),
    /**
     * Summoner's Rift Original Autumn Variant
     */
    // -650, -83, 14076, 14522 ??
    SUMMONERS_RIFT_AUTUMN_OLD(2, new Rectangle(-269, -83, 14276, 14522)),
    /**
     * The Proving Grounds Tutorial map
     */
    PROVING_GROUNDS(3, new Rectangle(-500, -500, 15000, 15000)),
    /**
     * Twisted Treeline Original Version
     */
    TWISTED_TREELINE_OLD(4, new Rectangle(0, 0, 15398, 15398)),
    /**
     * The Crystal Scar Dominion map
     */
    CRYSTAL_SCAR(8, new Rectangle(0, 0, 13987, 13987)),
    /**
     * Twisted Treeline Current Version
     */
    TWISTED_TREELINE(10, new Rectangle(0, 0, 15398, 15398)),
    /**
     * Summoner's Rift Current Version
     */
    // 0, 0, 14820, 148841 ??
    SUMMONERS_RIFT(11, new Rectangle(-269, -83, 14276, 14522)),
    /**
     * Howling Abyss ARAM map
     */
    HOWLING_ABYSS(12, new Rectangle(-28, -19, 12849, 12858)),
    /**
     * Butcher's Bridge ARAM map
     */
    BUTCHERS_BRIDGE(14, new Rectangle(-28, -19, 12849, 12858)),
    /**
     * Darkstar Map
     */
    //-650, -83, 14076, 14522 ??
    COSMIC_RUINS(16, new Rectangle(20, 20, 6970, 6970)),
    /**
     * Invasion map
     */
    //16975, 18675, 23025, 24725 ??
    VALORAN_CITY_PARK(18, new Rectangle(0, 0, 28767, 28767)),
    /**
     * Invasion map
     */
    SUBSTRUCTURE_43(19, new Rectangle(0, 0, 12056, 12056)),
    /**
     * Crash Site map
     */
    CRASH_SITE(20, new Rectangle(0, 0, 12056, 12056)),
    /**
     * Nexus Blitz map
     */
    NEXUS_BLITZ(21, new Rectangle(0, 0, 12056, 12056)),
    /**
     * TFT map
     */
    TEAMFIGHT_TACTICS(22, new Rectangle(0, 0, 12056, 12056)),
    ;
    
    
    private final Integer   mapId;
    private final Rectangle bounds;
    
    
    /**
     * Constructor for MapType
     *
     * @param code   the mapId
     * @param bounds adjusted bounds where avaliable, otherwise mapbounds
     */
    MapType(final Integer code, final Rectangle bounds)
    {
        this.mapId = code;
        this.bounds = bounds;
    }
    
    /**
     * Gets from code.
     *
     * @param mapId the map id
     * @return the from code
     */
    public Optional<MapType> getFromCode(String value)
    {
        if (value.contains("."))
        {
            value = value.substring(0, value.indexOf("."));
        }
        
        return getFromId(Integer.parseInt(value));
    }
    
    public Optional<MapType> getFromId(final int mapId)
    {
        return Stream.of(MapType.values()).filter(t -> t.mapId.equals(mapId)).findFirst();
    }
    
    @Override
    public String prettyName()
    {
        switch (this)
        {
            case SUMMONERS_RIFT_SUMMER_OLD:
                return "Summoners Rift Old Skin";
            case SUMMONERS_RIFT_AUTUMN_OLD:
                return "Summoners Rift Autumn Skin";
            case PROVING_GROUNDS:
                return "Proving Grounds";
            case TWISTED_TREELINE_OLD:
                return "Twisted Treeline Old Skin";
            case CRYSTAL_SCAR:
                return "Crystal Scar";
            case TWISTED_TREELINE:
                return "Twisted Treeline";
            case SUMMONERS_RIFT:
                return "Summoners Rift";
            case HOWLING_ABYSS:
                return "Howling Abyss";
            case BUTCHERS_BRIDGE:
                return "Butchers Bridge";
            case COSMIC_RUINS:
                return "Cosmic Ruins";
            case VALORAN_CITY_PARK:
                return "Valoran City Park";
            case SUBSTRUCTURE_43:
                return "Substructure 43";
            case CRASH_SITE:
                return "Crash Site";
            case NEXUS_BLITZ:
                return "Nexus Blitz";
            case TEAMFIGHT_TACTICS:
                return "Teamfight Tactics";
            default:
                return "This enum does not have a pretty name";
        }
    }
    
    /**
     * Gets bounds.
     *
     * @return the bounds
     */
    public Rectangle getBounds()
    {
        return this.bounds;
    }
    
    /**
     * Gets id.
     *
     * @return the id
     */
    public Integer getId()
    {
        return this.mapId;
    }
    
    
    /**
     * Used internaly in the api...
     *
     * @return the value
     */
    public int getValue()
    {
        return getId();
    }
    
}
