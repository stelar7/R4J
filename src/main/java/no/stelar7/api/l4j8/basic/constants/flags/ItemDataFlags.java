package no.stelar7.api.l4j8.basic.constants.flags;

public enum ItemDataFlags
{
    ALL("all"),
    COLLOQ("colloq"),
    CONSUME_ON_FULL("consumeOnFull"),
    CONSUMED("consumed"),
    DEPTH("depth"),
    EFFECT("effect"),
    FROM("from"),
    GOLD("gold"),
    GROUPS("groups"),
    HIDE_FROM_ALL("hideFromAll"),
    IMAGE("image"),
    IN_STORE("inStore"),
    INTO("into"),
    MAPS("maps"),
    REQUIRED_CHAMPION("requiredChampion"),
    SANITIZED_DESCRIPTION("sanitizedDescription"),
    SPECIAL_RECIPE("specialRecipe"),
    STATS("stats"),
    TAGS("tags"),
    TREE("tree");
    
    private String value;
    
    ItemDataFlags(final String value)
    {
        this.value = value;
    }
    
    public String getValue()
    {
        return value;
    }
}