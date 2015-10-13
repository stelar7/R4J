package no.stelar7.api.l4j8.basic.constants;

import java.util.stream.Stream;

public enum Map
{

    /**
     * Summoner's Rift Original Summer Variant
     */
    SUMMONERS_RIFT_SUMMER_OLD(1L),
    /**
     * Summoner's Rift Original Autumn Variant
     */
    SUMMONERS_RIFT_AUTUMN_OLD(2L),
    /**
     * The Proving Grounds Tutorial Map
     */
    PROVING_GROUNDS(3L),
    /**
     * Twisted Treeline Original Version
     */
    TWISTED_TREELINE_OLD(4L),
    /**
     * The Crystal Scar Dominion Map
     */
    DOMINION(8L),
    /**
     * Twisted Treeline Current Version
     */
    TWISTED_TREELINE(10L),
    /**
     * Summoner's Rift Current Version
     */
    SUMMONERS_RIFT(11L),
    /**
     * Howling Abyss ARAM Map
     */
    HOWLING_ABYSS(12L),
    /**
     * Butcher's Bridge ARAM Map
     */
    BUTCHERS_BRIDGE(14L);

    public static Map getFromCode(final Number mapId)
    {
        return Stream.of(Map.values()).filter(t -> t.code == mapId).findFirst().get();
    }

    Long code;

    Map(final Long code)
    {
        this.code = code;
    }

    public Long getCode()
    {
        return this.code;
    }

}
