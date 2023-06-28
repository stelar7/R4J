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
    EPISODE_4("808202d6-4f2b-a8ff-1feb-b3a0590ad79f"),
    EPISODE_4_ACT_1("573f53ac-41a5-3a7d-d9ce-d6a6298e5704"),
    EPISODE_4_ACT_2("d929bc38-4ab6-7da4-94f0-ee84f8ac141e"),
    EPISODE_4_ACT_3("3e47230a-463c-a301-eb7d-67bb60357d4f"),
    EPISODE_5("79f9d00f-433a-85d6-dfc3-60aef115e699"),
    EPISODE_5_ACT_1("67e373c7-48f7-b422-641b-079ace30b427"),
    EPISODE_5_ACT_2("7a85de9a-4032-61a9-61d8-f4aa2b4a84b6"),
    EPISODE_5_ACT_3("aca29595-40e4-01f5-3f35-b1b3d304c96e"),
    EPISODE_6("3ec8084a-4e45-4d22-d801-f8a63e5a208b"),
    EPISODE_6_ACT_1("9c91a445-4f78-1baa-a3ea-8f8aadf4914d"),
    EPISODE_6_ACT_2("34093c29-4306-43de-452f-3f944bde22be"),
    EPISODE_6_ACT_3("2de5423b-4aad-02ad-8d9b-c0a931958861"),
    EPISODE_7_ACT_1("0981a882-4e7d-371a-70c4-c3b4f46c504a"),
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
