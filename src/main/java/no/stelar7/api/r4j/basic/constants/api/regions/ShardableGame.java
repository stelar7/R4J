package no.stelar7.api.r4j.basic.constants.api.regions;

import java.util.Optional;
import java.util.stream.Stream;

public enum ShardableGame
{
    /**
     * Unknown game
     */
    UNKNOWN("", ""),
    /**
     * Legends of runeterra
     */
    LOR("LOR", "lor"),
    /**
     * Valorant
     */
    VAL("VAL", "val"),
    
    ;
    
    private String[] key;
    
    ShardableGame(String... s)
    {
        this.key = s;
    }
    
    /**
     * Returns a Platform from the provided code
     *
     * @param code the lookup key
     * @return Platform from code
     */
    public static Optional<ShardableGame> getFromCode(final String code)
    {
        return Stream.of(ShardableGame.values()).filter(t -> t.key[0].equalsIgnoreCase(code)).findFirst();
    }
    
    
    /**
     * Used internaly in the api...
     *
     * @return the value
     */
    public String getValue()
    {
        return this.key[0];
    }
    
    public String getRealmValue()
    {
        return this.key[1];
    }
    
    @Override
    public String toString()
    {
        return this.getValue();
    }
}
