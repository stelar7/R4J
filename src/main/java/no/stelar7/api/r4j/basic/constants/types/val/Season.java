package no.stelar7.api.r4j.basic.constants.types.val;

import no.stelar7.api.r4j.basic.constants.types.CodedEnum;

import java.util.Optional;
import java.util.stream.Stream;

public enum Season implements CodedEnum<Season>
{
    NONE(""),
    CLOSED_BETA("0df5adb9-4dcb-6899-1306-3e9860661dd3"),
    EPISODE_1("fcf2c8f4-4324-e50b-2e23-718e4a3ab046"),
    EPISODE_1_ACT_1("3f61c772-4560-cd3f-5d3f-a7ab5abda6b3"),
    EPISODE_1_ACT_2("0530b9c4-4980-f2ee-df5d-09864cd00542"),
    EPISODE_1_ACT_3("46ea6166-4573-1128-9cea-60a15640059b"),
    EPISODE_2("71c81c67-4fae-ceb1-844c-aab2bb8710fa"),
    EPISODE_2_ACT_1("97b6e739-44cc-ffa7-49ad-398ba502ceb0"),
    EPISODE_2_ACT_2("ab57ef51-4e59-da91-cc8d-51a5a2b9b8ff"),
    EPISODE_2_ACT_3("52e9749a-429b-7060-99fe-4595426a0cf7"),
    EPISODE_3("97b39124-46ce-8b55-8fd1-7cbf7ffe173f"),
    EPISODE_3_ACT_1("2a27e5d2-4d30-c9e2-b15a-93b8909a442c"),
    EPISODE_3_ACT_2("4cb622e1-4244-6da3-7276-8daaf1c01be2"),
    EPISODE_3_ACT_3("a16955a5-4ad0-f761-5e9e-389df1c892fb"),
    ;
    
    
    private final String actId;
    
    /**
     * Constructor for MapType
     *
     * @param code the mapId
     */
    Season(final String code)
    {
        this.actId = code;
    }
    
    /**
     * Gets from code.
     *
     * @param mapId the map id
     * @return the from code
     */
    public Optional<Season> getFromCode(final String mapId)
    {
        return Stream.of(Season.values()).filter(t -> t.actId.equals(mapId)).findFirst();
    }
    
    @Override
    public String prettyName()
    {
        switch (this)
        {
            default:
                return "This enum does not have a pretty name";
        }
    }
    
    /**
     * Gets id.
     *
     * @return the id
     */
    public String getActId()
    {
        return this.actId;
    }
    
    
    /**
     * Used internaly in the api...
     *
     * @return the value
     */
    public String getValue()
    {
        return getActId();
    }
    
}
