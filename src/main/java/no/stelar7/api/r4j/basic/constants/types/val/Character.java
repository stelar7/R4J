package no.stelar7.api.r4j.basic.constants.types.val;

import no.stelar7.api.r4j.basic.constants.types.CodedEnum;

import java.util.Optional;
import java.util.stream.Stream;

public enum Character implements CodedEnum<Character>
{
    BREACH("5f8d3a7f-467b-97f3-062c-13acf203c006"),
    BRIMSTONE("9f0d8ba9-4140-b941-57d3-a7ad57c6b417"),
    CYPHER("117ed9e3-49f3-6512-3ccf-0cada7e3823b"),
    JETT("add6443a-41bd-e414-f6ad-e58d267f4e95"),
    KILLJOY("1e58de9c-4950-5125-93e9-a0aee9f98746"),
    OMEN("8e253930-4c05-31dd-1b6c-968525494517"),
    PHOENIX("eb93336a-449b-9c1b-0a54-a891f7921d69"),
    RAZE("f94c3b30-42be-e959-889c-5aa313dba261"),
    REYNA("a3bfb853-43b2-7238-a4f1-ad90e9e46bcc"),
    SAGE("569fdd95-4d10-43ab-ca70-79becc718b46"),
    SOVA("320b2a48-4d9b-a075-30f1-1f93a9b638fa"),
    SOVA_NPC("ded3520f-4264-bfed-162d-b080e2abccf9"),
    VIPER("707eab51-4836-f488-046a-cda6bf494859"),
    ;
    
    
    private final String mode;
    
    /**
     * Constructor for MapType
     *
     * @param code the mapId
     */
    Character(final String code)
    {
        this.mode = code;
    }
    
    /**
     * Gets from code.
     *
     * @param mapId the map id
     * @return the from code
     */
    public Optional<Character> getFromCode(final String mapId)
    {
        return Stream.of(Character.values()).filter(t -> t.mode.equals(mapId)).findFirst();
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
    public String getId()
    {
        return this.mode;
    }
    
    
    /**
     * Used internaly in the api...
     *
     * @return the value
     */
    public String getValue()
    {
        return getId();
    }
    
}
