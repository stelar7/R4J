package no.stelar7.api.l4j8.basic.constants.types;

import java.util.Optional;
import java.util.stream.Stream;

public enum MonsterSubType implements CodedEnum
{
    EARTH_DRAGON, FIRE_DRAGON, WATER_DRAGON, AIR_DRAGON, ELDER_DRAGON;
    
    public Optional<MonsterSubType> getFromCode(String id)
    {
        return Stream.of(MonsterSubType.values()).filter(t -> t.name().equalsIgnoreCase(id)).findFirst();
    }
}
