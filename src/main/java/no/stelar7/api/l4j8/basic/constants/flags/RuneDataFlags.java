package no.stelar7.api.l4j8.basic.constants.flags;

public enum RuneDataFlags
{
    ALL("all"),
    COLLOQ("colloq"),
    CONSUME_ON_FULL("consumeOnFull"),
    CONSUMED("consumed"),
    DEPTH("depth"),
    EFFECT("effect"),
    FROM("from"),
    GOLD("gold"),
    HIDE_FROM_ALL("hideFromAll"),
    IMAGE("image"),
    IN_STORE("inStore"),
    INTO("into"),
    MAPS("maps"),
    REQUIRED_CHAMPION("requiredChampion"),
    SANITIZED_DESCRIPTION("sanitizedDescription"),
    SPECIAL_RECIPE("specialRecipe"),
    STACKS("stacks"),
    STATS("stats"),
    TAGS("tags");
    
    private String value;
    
    RuneDataFlags(final String value)
    {
        this.value = value;
    }
    
    public String getValue()
    {
        return value;
    }
}