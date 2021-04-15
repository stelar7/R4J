package no.stelar7.api.r4j.basic.constants.types.lol;

import no.stelar7.api.r4j.basic.constants.types.CodedEnum;

import java.util.*;
import java.util.stream.Stream;

public enum SpellSlotType implements CodedEnum<SpellSlotType>
{
    INVALID(new Integer[]{-1}),
    Q(new Integer[]{0}),
    W(new Integer[]{1}),
    E(new Integer[]{2}),
    R(new Integer[]{3}),
    SUMMONER_SPELL(new Integer[]{4, 5}),
    ITEM(new Integer[]{6, 7, 8, 9, 10, 11}),
    TRINKET(new Integer[]{12}),
    RECALL(new Integer[]{13}),
    ELIXIR_AUTO_USE(new Integer[]{14}),
    UNUSED(new Integer[]{15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44}),
    HELPER_SPELLS(new Integer[]{45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 60}),
    RESPAWN(new Integer[]{61}),
    UNDOCUMENTED(new Integer[]{62}),
    PASSIVE(new Integer[]{63}),
    BASIC_ATTACK(new Integer[]{64, 65, 66, 67, 68, 69, 70, 71, 72}),
    CRITICAL_ATTACK(new Integer[]{73, 74, 75, 76, 77, 78, 79, 80, 81}),
    OUT_OF_BOUNDS(new Integer[]{}),
    ;
    
    Integer[] slotIds;
    
    SpellSlotType(Integer[] slotIds)
    {
        this.slotIds = slotIds;
    }
    
    public Optional<SpellSlotType> getFromId(int value)
    {
        if (value > 81)
        {
            return Optional.of(OUT_OF_BOUNDS);
        }
        
        return Stream.of(SpellSlotType.values()).filter(t -> Arrays.asList(t.slotIds).contains(value)).findFirst();
    }
    
    @Override
    public Optional<SpellSlotType> getFromCode(String value)
    {
        if (value.contains("."))
        {
            value = value.substring(0, value.indexOf("."));
        }
        
        return getFromId(Integer.parseInt(value));
    }
    
    @Override
    public String prettyName()
    {
        return this.name();
    }
}
