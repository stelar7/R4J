package no.stelar7.api.l4j8.pojo.staticdata.champion;

import java.util.List;

import no.stelar7.api.l4j8.basic.APIObject;
import no.stelar7.api.l4j8.pojo.staticdata.shared.Image;

public class Champion implements APIObject
{
    List<String>        allytips;
    String              blurb;
    List<String>        enemytips;
    Integer             id;
    Image               image;
    Info                info;
    String              key;
    String              lore;
    String              name;
    String              partype;
    Passive             passive;
    List<Recommended>   recommended;
    List<Skin>          skins;
    List<ChampionSpell> spells;
    Stats               stats;
    List<String>        tags;
    String              title;

    /**
     * Gets the allytips.
     *
     * @return the allytips
     */
    public List<String> getAllytips()
    {
        return allytips;
    }

    /**
     * Gets the blurb.
     *
     * @return the blurb
     */
    public String getBlurb()
    {
        return blurb;
    }

    /**
     * Gets the enemytips.
     *
     * @return the enemytips
     */
    public List<String> getEnemytips()
    {
        return enemytips;
    }

    /**
     * Gets the id.
     *
     * @return the id
     */
    public Integer getId()
    {
        return id;
    }

    /**
     * Gets the image.
     *
     * @return the image
     */
    public Image getImage()
    {
        return image;
    }

    /**
     * Gets the info.
     *
     * @return the info
     */
    public Info getInfo()
    {
        return info;
    }

    /**
     * Gets the key.
     *
     * @return the key
     */
    public String getKey()
    {
        return key;
    }

    /**
     * Gets the lore.
     *
     * @return the lore
     */
    public String getLore()
    {
        return lore;
    }

    /**
     * Gets the name.
     *
     * @return the name
     */
    public String getName()
    {
        return name;
    }

    /**
     * Gets the partype.
     *
     * @return the partype
     */
    public String getPartype()
    {
        return partype;
    }

    /**
     * Gets the passive.
     *
     * @return the passive
     */
    public Passive getPassive()
    {
        return passive;
    }

    /**
     * Gets the recommended.
     *
     * @return the recommended
     */
    public List<Recommended> getRecommended()
    {
        return recommended;
    }

    /**
     * Gets the skins.
     *
     * @return the skins
     */
    public List<Skin> getSkins()
    {
        return skins;
    }

    /**
     * Gets the spells.
     *
     * @return the spells
     */
    public List<ChampionSpell> getSpells()
    {
        return spells;
    }

    /**
     * Gets the stats.
     *
     * @return the stats
     */
    public Stats getStats()
    {
        return stats;
    }

    /**
     * Gets the tags.
     *
     * @return the tags
     */
    public List<String> getTags()
    {
        return tags;
    }

    /**
     * Gets the title.
     *
     * @return the title
     */
    public String getTitle()
    {
        return title;
    }
}
