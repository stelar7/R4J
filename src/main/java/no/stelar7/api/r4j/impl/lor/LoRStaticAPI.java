package no.stelar7.api.r4j.impl.lor;

import com.google.gson.reflect.TypeToken;
import no.stelar7.api.r4j.basic.utils.Utils;
import no.stelar7.api.r4j.pojo.lor.offline.card.LoRFaction;
import no.stelar7.api.r4j.pojo.lor.staticdata.*;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.*;
import java.util.stream.Collectors;

public class LoRStaticAPI
{
    private LoRStaticAPI()
    {
    }
    
    private static Map<LoRSetInstance, List<StaticLoRCard>> setCards = new HashMap<>();
    private static LoRSetInstance                           activeInstance;
    
    public static LoRSetInstance getActiveInstance()
    {
        return activeInstance;
    }
    
    public static void setActiveInstance(LoRSetInstance activeInstance)
    {
        LoRStaticAPI.activeInstance = activeInstance;
        loadCards();
    }
    
    private static void loadCards()
    {
        try
        {
            String              basePath    = activeInstance.getLanguage() + File.separator + "data" + File.separator;
            String              filename    = basePath + activeInstance.getSet() + "-" + activeInstance.getLanguage() + ".json";
            Path                setFile     = activeInstance.getFolderLocation().resolve(filename);
            byte[]              content     = Files.readAllBytes(setFile);
            String              contentJSON = new String(content, StandardCharsets.UTF_8);
            List<StaticLoRCard> cards       = Utils.getGson().fromJson(contentJSON, new TypeToken<List<StaticLoRCard>>() {}.getType());
            setCards.put(activeInstance, cards);
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }
    
    public static List<StaticLoRCard> getCards()
    {
        if (activeInstance == null)
        {
            throw new RuntimeException("No active instance set for this data, please set it and try again!");
        }
        
        return setCards.get(activeInstance);
    }
    
    public static Optional<StaticLoRCard> getCard(String cardcode)
    {
        if (activeInstance == null)
        {
            throw new RuntimeException("No active instance set for this data, please set it and try again!");
        }
        
        return setCards.get(activeInstance)
                       .stream()
                       .filter(c -> c.getCardCode().equalsIgnoreCase(cardcode))
                       .findFirst();
    }
    
    public static List<StaticLoRCard> getCards(LoRFaction faction)
    {
        if (activeInstance == null)
        {
            throw new RuntimeException("No active instance set for this data, please set it and try again!");
        }
        
        return setCards.get(activeInstance)
                       .stream()
                       .filter(c -> c.getRegionRef().equalsIgnoreCase(faction.commonName()))
                       .collect(Collectors.toList());
    }
}
