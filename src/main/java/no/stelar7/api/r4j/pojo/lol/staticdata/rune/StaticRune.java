package no.stelar7.api.r4j.pojo.lol.staticdata.rune;

import no.stelar7.api.r4j.pojo.lol.staticdata.shared.*;

import java.io.Serializable;
import java.util.*;

public class StaticRune implements Serializable
{
    private static final long serialVersionUID = -8678427377728607929L;
    
    private String               plaintext;
    private boolean              hideFromAll;
    private boolean              inStore;
    private List<String>         into;
    private int                  id;
    private RuneStats            stats;
    private String               colloq;
    private Map<String, Boolean> maps;
    private int                  specialRecipe;
    private Image                image;
    private String               description;
    private List<String>         tags;
    private String               requiredChampion;
    private List<String>         from;
    private String               group;
    private boolean              consumeOnFull;
    private String               name;
    private boolean              consumed;
    private int                  depth;
    private MetaData             rune;
    private int                  stacks;
    
    
    public String getPlaintext()
    {
        return plaintext;
    }
    
    public boolean getHideFromAll()
    {
        return hideFromAll;
    }
    
    public boolean getInStore()
    {
        return inStore;
    }
    
    public List<String> getInto()
    {
        return Collections.unmodifiableList(into);
    }
    
    public int getId()
    {
        return id;
    }
    
    public RuneStats getStats()
    {
        return stats;
    }
    
    public Map<String, Boolean> getMaps()
    {
        return maps;
    }
    
    public int getSpecialRecipe()
    {
        return specialRecipe;
    }
    
    public Image getImage()
    {
        return image;
    }
    
    public String getDescription()
    {
        return description;
    }
    
    public List<String> getTags()
    {
        return Collections.unmodifiableList(tags);
    }
    
    public String getRequiredChampion()
    {
        return requiredChampion;
    }
    
    public List<String> getFrom()
    {
        return Collections.unmodifiableList(from);
    }
    
    public String getGroup()
    {
        return group;
    }
    
    public boolean getConsumeOnFull()
    {
        return consumeOnFull;
    }
    
    public String getName()
    {
        return name;
    }
    
    public boolean getConsumed()
    {
        return consumed;
    }
    
    
    public int getDepth()
    {
        return depth;
    }
    
    public MetaData getRune()
    {
        return rune;
    }
    
    public int getStacks()
    {
        return stacks;
    }
    
    public String getColloq()
    {
        return colloq;
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
        StaticRune that = (StaticRune) o;
        return hideFromAll == that.hideFromAll &&
               inStore == that.inStore &&
               id == that.id &&
               specialRecipe == that.specialRecipe &&
               consumeOnFull == that.consumeOnFull &&
               consumed == that.consumed &&
               depth == that.depth &&
               stacks == that.stacks &&
               Objects.equals(plaintext, that.plaintext) &&
               Objects.equals(into, that.into) &&
               Objects.equals(stats, that.stats) &&
               Objects.equals(colloq, that.colloq) &&
               Objects.equals(maps, that.maps) &&
               Objects.equals(image, that.image) &&
               Objects.equals(description, that.description) &&
               Objects.equals(tags, that.tags) &&
               Objects.equals(requiredChampion, that.requiredChampion) &&
               Objects.equals(from, that.from) &&
               Objects.equals(group, that.group) &&
               Objects.equals(name, that.name) &&
               Objects.equals(rune, that.rune);
    }
    
    @Override
    public int hashCode()
    {
        return Objects.hash(plaintext, hideFromAll, inStore, into, id, stats, colloq, maps, specialRecipe, image, description, tags, requiredChampion, from, group, consumeOnFull, name, consumed, depth, rune, stacks);
    }
    
    @Override
    public String toString()
    {
        return "StaticRune{" +
               "plaintext='" + plaintext + '\'' +
               ", hideFromAll=" + hideFromAll +
               ", inStore=" + inStore +
               ", into=" + into +
               ", id=" + id +
               ", stats=" + stats +
               ", colloq='" + colloq + '\'' +
               ", maps=" + maps +
               ", specialRecipe=" + specialRecipe +
               ", image=" + image +
               ", description='" + description + '\'' +
               ", tags=" + tags +
               ", requiredChampion='" + requiredChampion + '\'' +
               ", from=" + from +
               ", group='" + group + '\'' +
               ", consumeOnFull=" + consumeOnFull +
               ", name='" + name + '\'' +
               ", consumed=" + consumed +
               ", depth=" + depth +
               ", rune=" + rune +
               ", stacks=" + stacks +
               '}';
    }
}
