package no.stelar7.api.r4j.basic.constants.types.lol;

import no.stelar7.api.r4j.basic.constants.types.CodedEnum;

import java.util.Optional;
import java.util.stream.Stream;

public enum MonsterSubType implements CodedEnum
{
    EARTH_DRAGON, FIRE_DRAGON, WATER_DRAGON, AIR_DRAGON, ELDER_DRAGON;
    
    public Optional<MonsterSubType> getFromCode(String id)
    {
        return Stream.of(MonsterSubType.values()).filter(t -> t.name().equalsIgnoreCase(id)).findFirst();
    }
    
    @Override
    public String prettyName()
    {
        switch (this)
        {
            case EARTH_DRAGON:
                return "Earth Dragon";
            case FIRE_DRAGON:
                return "Fire Dragon";
            case WATER_DRAGON:
                return "Water Dragon";
            case AIR_DRAGON:
                return "Air Dragon";
            case ELDER_DRAGON:
                return "Elder Dragon";
            default:
                return "This enum does not have a pretty name";
        }
    }
}
