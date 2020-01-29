package no.stelar7.api.r4j.impl.lor;

import no.stelar7.api.r4j.pojo.lor.offline.card.LoRFaction;
import no.stelar7.api.r4j.pojo.lor.staticdata.*;

import java.util.*;
import java.util.stream.Collectors;

public class LoRStaticAPI
{
    private LoRStaticAPI()
    {
    }
    
    private static Map<LoRSetInstance, List<StaticLoRCard>> setCards = new HashMap<>();
    private static Map<LoRCoreInstance, StaticLoRCoreInfo>  coreInfo = new HashMap<>();
    
    private static LoRSetInstance  setInstance;
    private static LoRCoreInstance coreInstance;
    
    public static LoRSetInstance getSetInstance()
    {
        return setInstance;
    }
    
    public static void setSetInstance(LoRSetInstance setInstance)
    {
        LoRStaticAPI.setInstance = setInstance;
        setCards.put(setInstance, setInstance.loadData());
    }
    
    public static LoRCoreInstance getCoreInstance()
    {
        return coreInstance;
    }
    
    public static void setCoreInstance(LoRCoreInstance coreInstance)
    {
        LoRStaticAPI.coreInstance = coreInstance;
        coreInfo.put(coreInstance, coreInstance.loadData());
    }
    
    public static List<StaticLoRCard> getCards()
    {
        if (setInstance == null || !setInstance.isValid())
        {
            throw new RuntimeException("No active instance set for this data, please set it and try again!");
        }
        
        return setCards.get(setInstance);
    }
    
    public static Optional<StaticLoRCard> getCard(String cardcode)
    {
        if (setInstance == null || !setInstance.isValid())
        {
            throw new RuntimeException("No active instance set for this data, please set it and try again!");
        }
        
        return setCards.get(setInstance)
                       .stream()
                       .filter(c -> c.getCardCode().equalsIgnoreCase(cardcode))
                       .findFirst();
    }
    
    public static List<StaticLoRCard> getCards(LoRFaction faction)
    {
        if (setInstance == null || !setInstance.isValid())
        {
            throw new RuntimeException("No active instance set for this data, please set it and try again!");
        }
        
        return setCards.get(setInstance)
                       .stream()
                       .filter(c -> c.getRegionRef().equalsIgnoreCase(faction.commonName()))
                       .collect(Collectors.toList());
    }
    
    public static List<StaticLoRKeyword> getKeywords()
    {
        if (coreInstance == null || !coreInstance.isValid())
        {
            throw new RuntimeException("No active instance set for core data, please set it and try again!");
        }
        
        return coreInfo.get(coreInstance).getKeywords();
    }
    
    public static List<StaticLoRRegion> getRegions()
    {
        if (coreInstance == null || !coreInstance.isValid())
        {
            throw new RuntimeException("No active instance set for core data, please set it and try again!");
        }
        
        return coreInfo.get(coreInstance).getRegions();
    }
    
    public static List<StaticLoRRarity> getRarities()
    {
        if (coreInstance == null || !coreInstance.isValid())
        {
            throw new RuntimeException("No active instance set for core data, please set it and try again!");
        }
        
        return coreInfo.get(coreInstance).getRarities();
    }
    
    public static List<StaticLoRSpellSpeed> getSpellSpeeds()
    {
        if (coreInstance == null || !coreInstance.isValid())
        {
            throw new RuntimeException("No active instance set for core data, please set it and try again!");
        }
        
        return coreInfo.get(coreInstance).getSpellSpeeds();
    }
    
    
    public static StaticLoRCoreInfo getCoreInfo()
    {
        if (coreInstance == null || !coreInstance.isValid())
        {
            throw new RuntimeException("No active instance set for core data, please set it and try again!");
        }
        
        return coreInfo.get(coreInstance);
    }
}
