package no.stelar7.api.l4j8.pojo.staticdata.champion;

import java.util.List;

import no.stelar7.api.l4j8.pojo.staticdata.shared.Image;

public class Champion
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

    @Override
    public boolean equals(final Object obj)
    {
        if (this == obj)
        {
            return true;
        }
        if (obj == null)
        {
            return false;
        }
        if (this.getClass() != obj.getClass())
        {
            return false;
        }
        final Champion other = (Champion) obj;
        if (this.allytips == null)
        {
            if (other.allytips != null)
            {
                return false;
            }
        } else if (!this.allytips.equals(other.allytips))
        {
            return false;
        }
        if (this.blurb == null)
        {
            if (other.blurb != null)
            {
                return false;
            }
        } else if (!this.blurb.equals(other.blurb))
        {
            return false;
        }
        if (this.enemytips == null)
        {
            if (other.enemytips != null)
            {
                return false;
            }
        } else if (!this.enemytips.equals(other.enemytips))
        {
            return false;
        }
        if (this.id == null)
        {
            if (other.id != null)
            {
                return false;
            }
        } else if (!this.id.equals(other.id))
        {
            return false;
        }
        if (this.image == null)
        {
            if (other.image != null)
            {
                return false;
            }
        } else if (!this.image.equals(other.image))
        {
            return false;
        }
        if (this.info == null)
        {
            if (other.info != null)
            {
                return false;
            }
        } else if (!this.info.equals(other.info))
        {
            return false;
        }
        if (this.key == null)
        {
            if (other.key != null)
            {
                return false;
            }
        } else if (!this.key.equals(other.key))
        {
            return false;
        }
        if (this.lore == null)
        {
            if (other.lore != null)
            {
                return false;
            }
        } else if (!this.lore.equals(other.lore))
        {
            return false;
        }
        if (this.name == null)
        {
            if (other.name != null)
            {
                return false;
            }
        } else if (!this.name.equals(other.name))
        {
            return false;
        }
        if (this.partype == null)
        {
            if (other.partype != null)
            {
                return false;
            }
        } else if (!this.partype.equals(other.partype))
        {
            return false;
        }
        if (this.passive == null)
        {
            if (other.passive != null)
            {
                return false;
            }
        } else if (!this.passive.equals(other.passive))
        {
            return false;
        }
        if (this.recommended == null)
        {
            if (other.recommended != null)
            {
                return false;
            }
        } else if (!this.recommended.equals(other.recommended))
        {
            return false;
        }
        if (this.skins == null)
        {
            if (other.skins != null)
            {
                return false;
            }
        } else if (!this.skins.equals(other.skins))
        {
            return false;
        }
        if (this.spells == null)
        {
            if (other.spells != null)
            {
                return false;
            }
        } else if (!this.spells.equals(other.spells))
        {
            return false;
        }
        if (this.stats == null)
        {
            if (other.stats != null)
            {
                return false;
            }
        } else if (!this.stats.equals(other.stats))
        {
            return false;
        }
        if (this.tags == null)
        {
            if (other.tags != null)
            {
                return false;
            }
        } else if (!this.tags.equals(other.tags))
        {
            return false;
        }
        if (this.title == null)
        {
            if (other.title != null)
            {
                return false;
            }
        } else if (!this.title.equals(other.title))
        {
            return false;
        }
        return true;
    }

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
    public Integer getId()
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
    public List<ChampionSpell> getSpells()
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
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = (prime * result) + ((this.allytips == null) ? 0 : this.allytips.hashCode());
        result = (prime * result) + ((this.blurb == null) ? 0 : this.blurb.hashCode());
        result = (prime * result) + ((this.enemytips == null) ? 0 : this.enemytips.hashCode());
        result = (prime * result) + ((this.id == null) ? 0 : this.id.hashCode());
        result = (prime * result) + ((this.image == null) ? 0 : this.image.hashCode());
        result = (prime * result) + ((this.info == null) ? 0 : this.info.hashCode());
        result = (prime * result) + ((this.key == null) ? 0 : this.key.hashCode());
        result = (prime * result) + ((this.lore == null) ? 0 : this.lore.hashCode());
        result = (prime * result) + ((this.name == null) ? 0 : this.name.hashCode());
        result = (prime * result) + ((this.partype == null) ? 0 : this.partype.hashCode());
        result = (prime * result) + ((this.passive == null) ? 0 : this.passive.hashCode());
        result = (prime * result) + ((this.recommended == null) ? 0 : this.recommended.hashCode());
        result = (prime * result) + ((this.skins == null) ? 0 : this.skins.hashCode());
        result = (prime * result) + ((this.spells == null) ? 0 : this.spells.hashCode());
        result = (prime * result) + ((this.stats == null) ? 0 : this.stats.hashCode());
        result = (prime * result) + ((this.tags == null) ? 0 : this.tags.hashCode());
        result = (prime * result) + ((this.title == null) ? 0 : this.title.hashCode());
        return result;
    }
}
