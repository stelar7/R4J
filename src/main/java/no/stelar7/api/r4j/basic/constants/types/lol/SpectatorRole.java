package no.stelar7.api.r4j.basic.constants.types.lol;

import no.stelar7.api.r4j.basic.constants.types.CodedEnum;

import java.util.Optional;
import java.util.stream.Stream;

public enum SpectatorRole implements CodedEnum<SpectatorRole>
{
    BOTTOM_PRIMARY_BOTTOM_FILL("BOTTOM.PRIMARY.BOTTOM.FILL"),
    BOTTOM_PRIMARY_BOTTOM_JUNGLE("BOTTOM.PRIMARY.BOTTOM.JUNGLE"),
    BOTTOM_PRIMARY_BOTTOM_MIDDLE("BOTTOM.PRIMARY.BOTTOM.MIDDLE"),
    BOTTOM_PRIMARY_BOTTOM_UTILITY("BOTTOM.PRIMARY.BOTTOM.UTILITY"),
    BOTTOM_SECONDARY_MIDDLE_BOTTOM("BOTTOM.SECONDARY.MIDDLE.BOTTOM"),
    JUNGLE_PRIMARY_JUNGLE_BOTTOM("JUNGLE.PRIMARY.JUNGLE.BOTTOM"),
    JUNGLE_PRIMARY_JUNGLE_MIDDLE("JUNGLE.PRIMARY.JUNGLE.MIDDLE"),
    JUNGLE_PRIMARY_JUNGLE_TOP("JUNGLE.PRIMARY.JUNGLE.TOP"),
    JUNGLE_PRIMARY_JUNGLE_UTILITY("JUNGLE.PRIMARY.JUNGLE.UTILITY"),
    MIDDLE_PRIMARY_MIDDLE_BOTTOM("MIDDLE.PRIMARY.MIDDLE.BOTTOM"),
    MIDDLE_PRIMARY_MIDDLE_JUNGLE("MIDDLE.PRIMARY.MIDDLE.JUNGLE"),
    MIDDLE_PRIMARY_MIDDLE_UTILITY("MIDDLE.PRIMARY.MIDDLE.UTILITY"),
    TOP_PRIMARY_TOP_BOTTOM("TOP.PRIMARY.TOP.BOTTOM"),
    TOP_PRIMARY_TOP_MIDDLE("TOP.PRIMARY.TOP.MIDDLE"),
    TOP_PRIMARY_TOP_UTILITY("TOP.PRIMARY.TOP.UTILITY"),
    TOP_SECONDARY_JUNGLE_TOP("TOP.SECONDARY.JUNGLE.TOP"),
    UTILITY_PRIMARY_UTILITY_JUNGLE("UTILITY.PRIMARY.UTILITY.JUNGLE"),
    UTILITY_PRIMARY_UTILITY_MIDDLE("UTILITY.PRIMARY.UTILITY.MIDDLE"),
    UTILITY_PRIMARY_UTILITY_TOP("UTILITY.PRIMARY.UTILITY.TOP"),
    ;
    
    private final String value;
    
    SpectatorRole(final String value)
    {
        this.value = value;
    }
    
    public Optional<SpectatorRole> getFromCode(final String value)
    {
        return getFromString(value);
    }
    
    @Override
    public String prettyName()
    {
        return this.commonName();
    }
    
    @Override
    public String commonName()
    {
        return this.name();
    }
    
    public static Optional<SpectatorRole> getFromString(final String value)
    {
        return Stream.of(SpectatorRole.values()).filter(t -> t.value.equalsIgnoreCase(value)).findFirst();
    }
    
}
