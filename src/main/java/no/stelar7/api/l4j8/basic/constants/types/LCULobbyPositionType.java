package no.stelar7.api.l4j8.basic.constants.types;

import java.util.Optional;
import java.util.stream.Stream;

public enum LCULobbyPositionType implements CodedEnum
{
    /**
     * No role selected
     */
    UNSELECTED,
    /**
     * Top lane
     */
    TOP,
    /**
     * Jungle
     */
    JUNGLE,
    /**
     * Mid lane
     */
    MIDDLE,
    /**
     * Bottom lane
     */
    BOTTOM,
    /**
     * Support
     */
    UTILITY,
    /**
     * Fill
     */
    FILL;
    
    /**
     * Returns a LCULobbyPositionType from the provided value
     *
     * @param code the lookup key
     * @return LCULobbyPositionType
     */
    public Optional<LCULobbyPositionType> getFromCode(final String code)
    {
        return Stream.of(LCULobbyPositionType.values()).filter(t -> Stream.of(t.name()).anyMatch(s -> s.equalsIgnoreCase(code))).findFirst();
    }
    
    
    @Override
    public String prettyName()
    {
        switch (this) {
            case UNSELECTED:
                return "Unselected";
            case TOP:
                return "Top";
            case JUNGLE:
                return "Jungle";
            case MIDDLE:
                return "Middle";
            case BOTTOM:
                return "Bottom";
            case UTILITY:
                return "Utility";
            case FILL:
                return "Fill";
            default:
                return "This enum does not have a pretty name";
        }
    }
}
