package no.stelar7.api.r4j.basic.constants.types.val;

import com.google.gson.JsonPrimitive;
import no.stelar7.api.r4j.basic.constants.types.CodedEnum;
import no.stelar7.api.r4j.basic.exceptions.APIEnumNotUpToDateException;

import java.util.Optional;
import java.util.stream.Stream;

public enum Weapon implements CodedEnum<Weapon>
{
    NONE(""),
    SPIKE("0AFB2636-4093-C63B-4EF1-1E97966E2A3E"),
    CLASSIC("29A0CFAB-485B-F5D5-779A-B59F85E204A8"),
    ODIN("63E6C2B6-4A8E-869C-3D4C-E38355226584"),
    ARES("55D8A0F4-4274-CA67-FE2C-06AB45EFDF58"),
    VANDAL("9C82E19D-4575-0200-1A81-3EACF00CF872"),
    BULLDOG("AE3DE142-4D85-2547-DD26-4E90BED35CF7"),
    PHANTOM("EE8E8D15-496B-07AC-E5F6-8FAE5D4C7B1A"),
    JUDGE("EC845BF4-4F79-DDDA-A3DA-0DB3774B2794"),
    BUCKY("910BE174-449B-C412-AB22-D0873436B21B"),
    FRENZY("44D4E95C-4157-0037-81B2-17841BF2E8E3"),
    GHOST("1BAA85B4-4C70-1284-64BB-6481DFC3BB4E"),
    SHERIFF("E336C6B8-418D-9340-D77F-7A9E4CFE0702"),
    SHORTY("42DA8CCC-40D5-AFFC-BEEC-15AA47B42EDA"),
    OPERATOR("A03B24D3-4319-996D-0F8C-94BBFBA1DFC7"),
    GUARDIAN("4ADE7FAA-4CF1-8376-95EF-39884480959B"),
    MARSHAL("C4883E50-4494-202C-3EC3-6B8A9284F00B"),
    SPECTRE("462080D1-4035-2937-7C09-27AA2A5C27A7"),
    STINGER("F7E1B454-4AD4-1063-EC0A-159E56B58941"),
    TACTICAL_KNIFE("2F59173C-4BED-B6C3-2191-DEA9B58BE9C7"),
    ;
    
    
    private final String mode;
    
    /**
     * Constructor for MapType
     *
     * @param code the mapId
     */
    Weapon(final String code)
    {
        this.mode = code;
    }
    
    /**
     * Gets from code.
     *
     * @param mapId the map id
     * @return the from code
     */
    public Optional<Weapon> getFromCode(final String mapId)
    {
        return Stream.of(Weapon.values()).filter(t -> t.mode.equals(mapId)).findFirst();
    }
    
    public static Weapon fromCode(final String mapId)
    {
        return Stream.of(Weapon.values()).filter(t -> t.mode.equals(mapId)).findFirst().orElseThrow(() -> new APIEnumNotUpToDateException(Weapon.class, new JsonPrimitive(mapId)));
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
