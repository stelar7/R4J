package no.stelar7.api.l4j8.basic.constants.types;

import java.util.*;
import java.util.stream.*;

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
     * Returns an EventType from the provided value
     *
     * @param type the type to check
     * @return EventType
     */
    public static Optional<EventType> getFromCode(final String type)
    {
        return Stream.of(EventType.values()).filter(t -> t.name().equalsIgnoreCase(type)).findFirst();
    }
    
    /**
     * The value used to map strings to objects
     *
     * @return String
     */
    public String getCode()
    {
        return this.name();
    }
}
