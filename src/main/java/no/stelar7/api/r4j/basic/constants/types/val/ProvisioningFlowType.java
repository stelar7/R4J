package no.stelar7.api.r4j.basic.constants.types.val;

import no.stelar7.api.r4j.basic.constants.types.CodedEnum;

import java.util.Optional;
import java.util.stream.Stream;

public enum ProvisioningFlowType implements CodedEnum<ProvisioningFlowType>
{
    
    MATCHMAKING("Matchmaking"),
    CUSTOM_GAME("CustomGame");
    
    
    private final String flow;
    
    /**
     * Constructor for MapType
     *
     * @param code the mapId
     */
    ProvisioningFlowType(final String code)
    {
        this.flow = code;
    }
    
    /**
     * Gets from code.
     *
     * @param mapId the map id
     * @return the from code
     */
    public Optional<ProvisioningFlowType> getFromCode(final String mapId)
    {
        return Stream.of(ProvisioningFlowType.values()).filter(t -> t.flow.equals(mapId)).findFirst();
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
        return this.flow;
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
