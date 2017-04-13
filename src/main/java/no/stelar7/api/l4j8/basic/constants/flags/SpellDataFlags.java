package no.stelar7.api.l4j8.basic.constants.flags;

public enum SpellDataFlags
{
    ALL("all"),
    COOLDOWN("cooldown"),
    COOLDOWN_BURN("cooldownBurn"),
    COST("cost"),
    COST_BURN("costBurn"),
    COST_TYPE("costType"),
    EFFECT("effect"),
    EFFECT_BURN("effectBurn"),
    IMAGE("image"),
    KEY("key"),
    LEVEL_TIP("leveltip"),
    MAX_RANK("maxrank"),
    MODES("modes"),
    RANGE("range"),
    RANGE_BURN("rangeBurn"),
    SANITIZED_DESCRIPTION("sanitizedDescription"),
    SANITIZED_TOOLTIP("sanitizedTooltip"),
    TOOLTIP("tooltip"),
    VARS("vars");
    
    private String value;
    
    SpellDataFlags(final String value)
    {
        this.value = value;
    }
    
    public String getValue()
    {
        return value;
    }
}