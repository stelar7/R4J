package no.stelar7.api.r4j.basic.constants.types.lol;

import no.stelar7.api.r4j.basic.constants.types.CodedEnum;

import java.util.*;
import java.util.stream.*;

public enum EventType implements CodedEnum
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
    public Optional<EventType> getFromCode(final String type)
    {
        return Stream.of(EventType.values()).filter(t -> t.name().equalsIgnoreCase(type)).findFirst();
    }
    
    @Override
    public String prettyName()
    {
        switch (this)
        {
            
            case ASCENDED_EVENT:
                return "Ascended";
            case BUILDING_KILL:
                return "Building Killed";
            case CAPTURE_POINT:
                return "Point Captured";
            case CHAMPION_KILL:
                return "Champion Killed";
            case ELITE_MONSTER_KILL:
                return "Elite Monster Killed";
            case ITEM_DESTROYED:
                return "Item Destroyed";
            case ITEM_PURCHASED:
                return "item Purchased";
            case ITEM_SOLD:
                return "Item Sold";
            case ITEM_UNDO:
                return "Item Undone";
            case PORO_KING_SUMMON:
                return "Poro King Summoned";
            case SKILL_LEVEL_UP:
                return "Skill Leveled";
            case WARD_KILL:
                return "Ward Killed";
            case WARD_PLACED:
                return "Ward Placed";
            default:
                return "This enum does not have a pretty name";
        }
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
