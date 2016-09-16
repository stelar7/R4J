package no.stelar7.api.l4j8.basic.constants;

import java.awt.*;
import java.util.Optional;
import java.util.stream.Stream;

public enum Map
{

    /**
     * Summoner's Rift Original Summer Variant
     */
    SUMMONERS_RIFT_SUMMER_OLD(1, new Rectangle(-269, -83, 14276, 14522)),
    /**
     * Summoner's Rift Original Autumn Variant
     */
    SUMMONERS_RIFT_AUTUMN_OLD(2, new Rectangle(-269, -83, 14276, 14522)),
    /**
     * The Proving Grounds Tutorial Map
     */
    PROVING_GROUNDS(3, new Rectangle(-500, -500, 15000, 15000)),
    /**
     * Twisted Treeline Original Version
     */
    TWISTED_TREELINE_OLD(4, new Rectangle(0, 0, 15398, 15398)),
    /**
     * The Crystal Scar Dominion Map
     */
    CRYSTAL_SCAR(8, new Rectangle(0, 0, 13987, 13987)),
    /**
     * Twisted Treeline Current Version
     */
    TWISTED_TREELINE(10, new Rectangle(0, 0, 15398, 15398)),
    /**
     * Summoner's Rift Current Version
     */
    SUMMONERS_RIFT(11, new Rectangle(-269, -83, 14276, 14522)),
    /**
     * Howling Abyss ARAM Map
     */
    HOWLING_ABYSS(12, new Rectangle(-28, -19, 12849, 12858)),
    /**
     * Butcher's Bridge ARAM Map
     */
    BUTCHERS_BRIDGE(14, new Rectangle(-28, -19, 12849, 12858));

    public static Optional<Map> getFromCode(final Integer mapId)
    {
        return Stream.of(Map.values()).filter(t -> t.code.equals(mapId)).findFirst();
    }

    Integer code;

    Rectangle bounds;

    Map(final Integer code, final Rectangle bounds)
    {
        this.code = code;
        this.bounds = bounds;
    }

    public Rectangle getBounds()
    {
        return this.bounds;
    }

    public Integer getCode()
    {
        return this.code;
    }

}
