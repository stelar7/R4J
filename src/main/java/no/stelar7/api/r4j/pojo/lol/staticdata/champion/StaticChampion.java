package no.stelar7.api.r4j.pojo.lol.staticdata.champion;

import no.stelar7.api.r4j.pojo.lol.staticdata.shared.Image;

import java.io.Serializable;
import java.util.*;

public class StaticChampion implements Serializable
{
    private static final long serialVersionUID = -5036593383951582883L;
    
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
    private StaticChampionStats       stats;
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
    public StaticChampionStats getStats()
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
        return id == that.id &&
               Objects.equals(allytips, that.allytips) &&
               Objects.equals(blurb, that.blurb) &&
               Objects.equals(enemytips, that.enemytips) &&
               Objects.equals(image, that.image) &&
               Objects.equals(info, that.info) &&
               Objects.equals(key, that.key) &&
               Objects.equals(lore, that.lore) &&
               Objects.equals(name, that.name) &&
               Objects.equals(partype, that.partype) &&
               Objects.equals(passive, that.passive) &&
               Objects.equals(recommended, that.recommended) &&
               Objects.equals(skins, that.skins) &&
               Objects.equals(spells, that.spells) &&
               Objects.equals(stats, that.stats) &&
               Objects.equals(tags, that.tags) &&
               Objects.equals(title, that.title);
    }
    
    @Override
    public int hashCode()
    {
        return Objects.hash(allytips, blurb, enemytips, id, image, info, key, lore, name, partype, passive, recommended, skins, spells, stats, tags, title);
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
