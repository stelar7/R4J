package no.stelar7.api.l4j8.basic.constants.flags;

public enum ChampDataFlags
{
    ALL("all"),
    ALLYTIPS("allytips"),
    //ALTIMAGES("altimages"),
    BLURB("blurb"),
    ENEMYTIPS("enemytips"),
    IMAGE("image"),
    INFO("info"),
    LORE("lore"),
    PARTYPE("partype"),
    PASSIVE("passive"),
    RECOMMENDED("recommended"),
    SKINS("skins"),
    SPELLS("spells"),
    STATS("stats"),
    TAGS("tags");
    
    private String value;
    
    ChampDataFlags(final String value)
    {
        this.value = value;
    }
    
    public String getValue()
    {
        return value;
    }
}