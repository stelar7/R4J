package no.stelar7.api.l4j8.pojo.staticdata.champion;

import no.stelar7.api.l4j8.pojo.staticdata.shared.*;

import java.util.*;

public class StaticChampion
{
    private List<String>              allytips;
    private String                    blurb;
    private List<String>              enemytips;
    private int                       id;
    private Image                     image;
    private Info                      info;
    private String                    key;
    private String                    lore;
    private String                    name;
    private String                    partype;
    private Passive                   passive;
    private List<Recommended>         recommended;
    private List<Skin>                skins;
    private List<StaticChampionSpell> spells;
    private Stats                     stats;
    private List<String>              tags;
    private String                    title;
    
    
    /**
     * Gets the allytips.
     *
     * @return the allytips
     */
    public List<String> getAllytips()
    {
        return this.allytips;
    }
    
    /**
     * Gets the blurb.
     *
     * @return the blurb
     */
    public String getBlurb()
    {
        return this.blurb;
    }
    
    /**
     * Gets the enemytips.
     *
     * @return the enemytips
     */
    public List<String> getEnemytips()
    {
        return this.enemytips;
    }
    
    /**
     * Gets the id.
     *
     * @return the id
     */
    public int getId()
    {
        return this.id;
    }
    
    /**
     * Gets the image.
     *
     * @return the image
     */
    public Image getImage()
    {
        return this.image;
    }
    
    /**
     * Gets the info.
     *
     * @return the info
     */
    public Info getInfo()
    {
        return this.info;
    }
    
    /**
     * Gets the key.
     *
     * @return the key
     */
    public String getKey()
    {
        return this.key;
    }
    
    /**
     * Gets the lore.
     *
     * @return the lore
     */
    public String getLore()
    {
        return this.lore;
    }
    
    /**
     * Gets the name.
     *
     * @return the name
     */
    public String getName()
    {
        return this.name;
    }
    
    /**
     * Gets the partype.
     *
     * @return the partype
     */
    public String getPartype()
    {
        return this.partype;
    }
    
    /**
     * Gets the passive.
     *
     * @return the passive
     */
    public Passive getPassive()
    {
        return this.passive;
    }
    
    /**
     * Gets the recommended.
     *
     * @return the recommended
     */
    public List<Recommended> getRecommended()
    {
        return this.recommended;
    }
    
    /**
     * Gets the skins.
     *
     * @return the skins
     */
    public List<Skin> getSkins()
    {
        return this.skins;
    }
    
    /**
     * Gets the spells.
     *
     * @return the spells
     */
    public List<StaticChampionSpell> getSpells()
    {
        return this.spells;
    }
    
    /**
     * Gets the stats.
     *
     * @return the stats
     */
    public Stats getStats()
    {
        return this.stats;
    }
    
    /**
     * Gets the tags.
     *
     * @return the tags
     */
    public List<String> getTags()
    {
        return this.tags;
    }
    
    /**
     * Gets the title.
     *
     * @return the title
     */
    public String getTitle()
    {
        return this.title;
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
        
        StaticChampion that = (StaticChampion) o;
        
        if (id != that.id)
        {
            return false;
        }
        if ((allytips != null) ? !allytips.equals(that.allytips) : (that.allytips != null))
        {
            return false;
        }
        if ((blurb != null) ? !blurb.equals(that.blurb) : (that.blurb != null))
        {
            return false;
        }
        if ((enemytips != null) ? !enemytips.equals(that.enemytips) : (that.enemytips != null))
        {
            return false;
        }
        if ((image != null) ? !image.equals(that.image) : (that.image != null))
        {
            return false;
        }
        if ((info != null) ? !info.equals(that.info) : (that.info != null))
        {
            return false;
        }
        if ((key != null) ? !key.equals(that.key) : (that.key != null))
        {
            return false;
        }
        if ((lore != null) ? !lore.equals(that.lore) : (that.lore != null))
        {
            return false;
        }
        if ((name != null) ? !name.equals(that.name) : (that.name != null))
        {
            return false;
        }
        if ((partype != null) ? !partype.equals(that.partype) : (that.partype != null))
        {
            return false;
        }
        if ((passive != null) ? !passive.equals(that.passive) : (that.passive != null))
        {
            return false;
        }
        if ((recommended != null) ? !recommended.equals(that.recommended) : (that.recommended != null))
        {
            return false;
        }
        if ((skins != null) ? !skins.equals(that.skins) : (that.skins != null))
        {
            return false;
        }
        if ((spells != null) ? !spells.equals(that.spells) : (that.spells != null))
        {
            return false;
        }
        if ((stats != null) ? !stats.equals(that.stats) : (that.stats != null))
        {
            return false;
        }
        if ((tags != null) ? !tags.equals(that.tags) : (that.tags != null))
        {
            return false;
        }
        return (title != null) ? title.equals(that.title) : (that.title == null);
    }
    
    @Override
    public int hashCode()
    {
        int result = allytips != null ? allytips.hashCode() : 0;
        result = 31 * result + (blurb != null ? blurb.hashCode() : 0);
        result = 31 * result + (enemytips != null ? enemytips.hashCode() : 0);
        result = 31 * result + id;
        result = 31 * result + (image != null ? image.hashCode() : 0);
        result = 31 * result + (info != null ? info.hashCode() : 0);
        result = 31 * result + (key != null ? key.hashCode() : 0);
        result = 31 * result + (lore != null ? lore.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (partype != null ? partype.hashCode() : 0);
        result = 31 * result + (passive != null ? passive.hashCode() : 0);
        result = 31 * result + (recommended != null ? recommended.hashCode() : 0);
        result = 31 * result + (skins != null ? skins.hashCode() : 0);
        result = 31 * result + (spells != null ? spells.hashCode() : 0);
        result = 31 * result + (stats != null ? stats.hashCode() : 0);
        result = 31 * result + (tags != null ? tags.hashCode() : 0);
        result = 31 * result + (title != null ? title.hashCode() : 0);
        return result;
    }
    
    @Override
    public String toString()
    {
        return "StaticChampion{" +
               "allytips=" + allytips +
               ", blurb='" + blurb + '\'' +
               ", enemytips=" + enemytips +
               ", id=" + id +
               ", image=" + image +
               ", info=" + info +
               ", key='" + key + '\'' +
               ", lore='" + lore + '\'' +
               ", name='" + name + '\'' +
               ", partype='" + partype + '\'' +
               ", passive=" + passive +
               ", recommended=" + recommended +
               ", skins=" + skins +
               ", spells=" + spells +
               ", stats=" + stats +
               ", tags=" + tags +
               ", title='" + title + '\'' +
               '}';
    }
}
