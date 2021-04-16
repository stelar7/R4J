package no.stelar7.api.r4j.impl.lor;

import com.google.gson.reflect.TypeToken;
import no.stelar7.api.r4j.basic.utils.Utils;
import no.stelar7.api.r4j.pojo.lor.offline.card.LoRFaction;
import no.stelar7.api.r4j.pojo.lor.staticdata.*;

import java.util.*;
import java.util.stream.Collectors;

public class LoRStaticAPI
{
    private LoRStaticAPI()
    {
    }
    
    public List<StaticLoRCard> cards = new ArrayList<>();
    public StaticLoRCoreInfo   core;
    
    private String set;
    private String language;
    private String version;
    
    public LoRStaticAPI(String set)
    {
        this(set, "en_us");
    }
    
    public LoRStaticAPI(String set, String language)
    {
        this(set, language, "latest");
    }
    
    public LoRStaticAPI(String set, String language, String version)
    {
        this.set = set;
        this.language = language;
        this.version = version;
        
        this.cards = loadCards();
        this.core = loadCore();
    }
    
    private String getSetPath()
    {
        return "https://dd.b.pvp.net/" + version + "/" + set + "/" + language + "/data/" + set + "-" + language + ".json";
    }
    
    private String getCorePath()
    {
        return "https://dd.b.pvp.net/" + version + "/core/" + language + "/data/globals-" + language + ".json";
    }
    
    private List<StaticLoRCard> loadCards()
    {
        return Utils.getGson().fromJson(Utils.getURLData(getSetPath()), new TypeToken<List<StaticLoRCard>>() {}.getType());
    }
    
    
    private StaticLoRCoreInfo loadCore()
    {
        return Utils.getGson().fromJson(Utils.getURLData(getCorePath()), StaticLoRCoreInfo.class);
    }
    
    public List<StaticLoRCard> getCards()
    {
        return cards;
    }
    
    public Optional<StaticLoRCard> getCard(String cardcode)
    {
        return cards
                .stream()
                .filter(c -> c.getCardCode().equalsIgnoreCase(cardcode))
                .findFirst();
    }
    
    public List<StaticLoRCard> getCards(LoRFaction faction)
    {
        return cards
                .stream()
                .filter(c -> c.getRegionRef().equalsIgnoreCase(faction.commonName()))
                .collect(Collectors.toList());
    }
    
    public List<StaticLoRKeyword> getKeywords()
    {
        return core.getKeywords();
    }
    
    public List<StaticLoRRegion> getRegions()
    {
        
        return core.getRegions();
    }
    
    public List<StaticLoRRarity> getRarities()
    {
        return core.getRarities();
    }
    
    public List<StaticLoRSpellSpeed> getSpellSpeeds()
    {
        return core.getSpellSpeeds();
    }
    
    
    public StaticLoRCoreInfo getCoreInfo()
    {
        return core;
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
        LoRStaticAPI that = (LoRStaticAPI) o;
        return Objects.equals(set, that.set) &&
               Objects.equals(language, that.language) &&
               Objects.equals(version, that.version);
    }
    
    @Override
    public int hashCode()
    {
        return Objects.hash(set, language, version);
    }
    
    @Override
    public String toString()
    {
        return "LoRStaticAPI{" +
               "set='" + set + '\'' +
               ", language='" + language + '\'' +
               ", version='" + version + '\'' +
               '}';
    }
}
