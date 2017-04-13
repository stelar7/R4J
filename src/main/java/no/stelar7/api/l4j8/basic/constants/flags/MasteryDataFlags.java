package no.stelar7.api.l4j8.basic.constants.flags;

public enum MasteryDataFlags
{
    ALL("all"),
    IMAGE("image"),
    MASTERY_TREE("masteryTree"),
    PREREQ("prereq"),
    RANKS("ranks"),
    SANITIZED_DESCRIPTION("sanitizedDescription"),
    TREE("tree");
    
    private String value;
    
    MasteryDataFlags(final String value)
    {
        this.value = value;
    }
    
    public String getValue()
    {
        return value;
    }
}