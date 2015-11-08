package no.stelar7.api.l4j8.basic.constants;

import java.util.stream.Stream;

public enum EventType
{
    ASCENDED_EVENT,
    BUILDING_KILL,
    CAPTURE_POINT,
    CHAMPION_KILL,
    ELITE_MONSTER_KILL,
    ITEM_DESTROYED,
    ITEM_PURCHASED,
    ITEM_SOLD,
    ITEM_UNDO,
    PORO_KING_SUMMON,
    SKILL_LEVEL_UP,
    WARD_KILL,
    WARD_PLACED;
    
    /**
     * Returns an EventType from the provided code
     *
     * @param code
     *            the lookup key
     * @return EventType
     */
    public static EventType getFromCode(final String type)
    {
        return Stream.of(EventType.values()).filter(t -> t.name().equals(type)).findFirst().get();
    }

    /**
     * The code used to map strings to objects
     *
     * @return String
     */
    public String getCode()
    {
        return this.name();
    }
}
