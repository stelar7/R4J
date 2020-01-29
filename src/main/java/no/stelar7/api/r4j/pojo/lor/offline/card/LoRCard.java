package no.stelar7.api.r4j.pojo.lor.offline.card;

import no.stelar7.api.r4j.basic.utils.Utils;
import no.stelar7.api.r4j.impl.lor.LoRStaticAPI;
import no.stelar7.api.r4j.pojo.lor.staticdata.StaticLoRCard;

import java.util.Objects;

public class LoRCard
{
    private final int        set;
    private final LoRFaction faction;
    private final int        id;
    
    public static LoRCard create(String cardCode)
    {
        if (cardCode.length() < 7)
        {
            return null;
        }
        
        return create(cardCode.substring(0, 2), cardCode.substring(2, 4), cardCode.substring(4, 7));
        
    }
    
    public static LoRCard create(String setString, String factionString, String numberString)
    {
        try
        {
            int        set     = Integer.parseInt(setString);
            LoRFaction faction = LoRFaction.fromCode(factionString);
            int        id      = Integer.parseInt(numberString);
            
            return new LoRCard(set, faction, id);
        } catch (Exception e)
        {
            return null;
        }
    }
    
    private LoRCard(int set, LoRFaction faction, int id)
    {
        this.set = set;
        this.faction = faction;
        this.id = id;
    }
    
    public StaticLoRCard getAsStaticCard()
    {
        return LoRStaticAPI.getCard(this.getCardCode()).orElseThrow(() -> new RuntimeException("The LOR static data source youve specified does not contain any info about this card!"));
    }
    
    public String getCardCode()
    {
        return Utils.padLeft(String.valueOf(set), "0", 2) + faction.getShortCode() + Utils.padLeft(String.valueOf(id), "0", 3);
    }
    
    public int getSet()
    {
        return set;
    }
    
    public LoRFaction getFaction()
    {
        return faction;
    }
    
    public int getId()
    {
        return id;
    }
    
    @Override
    public boolean equals(Object o)
    {
        if (this == o)
        {
            return true;
        }
        if (o == null || getClass() != o.getClass())
        {
            return false;
        }
        
        LoRCard loRCard = (LoRCard) o;
        return set == loRCard.set &&
               id == loRCard.id &&
               faction == loRCard.faction;
    }
    
    @Override
    public int hashCode()
    {
        return Objects.hash(set, faction, id);
    }
    
    @Override
    public String toString()
    {
        return "LoRCard{" +
               "set=" + set +
               ", faction=" + faction +
               ", id=" + id +
               '}';
    }
}
