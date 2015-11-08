package no.stelar7.api.l4j8.pojo.match;

public class BannedChampion
{
    
    private Integer championId;
    private     Integer pickTurn;
    
    /**
     * Banned champion ID
     *
     * @return Integer
     */
    public Integer getChampionId()
    {
        return championId;
    }
    
    /**
     * Turn during which the champion was banned
     *
     * @return Integer
     */
    public Integer getPickTurn()
    {
        return pickTurn;
    }
}
