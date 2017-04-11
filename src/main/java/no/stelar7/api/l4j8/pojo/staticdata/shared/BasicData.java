package no.stelar7.api.l4j8.pojo.staticdata.shared;

import java.util.*;

public class BasicData
{
    private String               colloq;
    private Boolean              consumeOnFull;
    private Boolean              consumed;
    private Integer              depth;
    private String               description;
    private List<String>         from;
    private Gold                 gold;
    private String               group;
    private Boolean              hideFromAll;
    private Integer              id;
    private Image                image;
    private Boolean              inStore;
    private List<String>         into;
    private Map<String, Boolean> maps;
    private String               name;
    private String               plaintext;
    private String               requiredChampion;
    private MetaData             rune;
    private String               sanitizedDescription;
    private Integer              specialRecipe;
    private Integer              stacks;
    private BasicDataStats       stats;
    private List<String>         tags;
    
    
    
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
        final BasicData other = (BasicData) obj;
        if (this.colloq == null)
        {
            if (other.colloq != null)
            {
                return false;
            }
        } else if (!this.colloq.equals(other.colloq))
        {
            return false;
        }
        if (this.consumeOnFull == null)
        {
            if (other.consumeOnFull != null)
            {
                return false;
            }
        } else if (!this.consumeOnFull.equals(other.consumeOnFull))
        {
            return false;
        }
        if (this.consumed == null)
        {
            if (other.consumed != null)
            {
                return false;
            }
        } else if (!this.consumed.equals(other.consumed))
        {
            return false;
        }
        if (this.depth == null)
        {
            if (other.depth != null)
            {
                return false;
            }
        } else if (!this.depth.equals(other.depth))
        {
            return false;
        }
        if (this.description == null)
        {
            if (other.description != null)
            {
                return false;
            }
        } else if (!this.description.equals(other.description))
        {
            return false;
        }
        if (this.from == null)
        {
            if (other.from != null)
            {
                return false;
            }
        } else if (!this.from.equals(other.from))
        {
            return false;
        }
        if (this.gold == null)
        {
            if (other.gold != null)
            {
                return false;
            }
        } else if (!this.gold.equals(other.gold))
        {
            return false;
        }
        if (this.group == null)
        {
            if (other.group != null)
            {
                return false;
            }
        } else if (!this.group.equals(other.group))
        {
            return false;
        }
        if (this.hideFromAll == null)
        {
            if (other.hideFromAll != null)
            {
                return false;
            }
        } else if (!this.hideFromAll.equals(other.hideFromAll))
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
        if (this.inStore == null)
        {
            if (other.inStore != null)
            {
                return false;
            }
        } else if (!this.inStore.equals(other.inStore))
        {
            return false;
        }
        if (this.into == null)
        {
            if (other.into != null)
            {
                return false;
            }
        } else if (!this.into.equals(other.into))
        {
            return false;
        }
        if (this.maps == null)
        {
            if (other.maps != null)
            {
                return false;
            }
        } else if (!this.maps.equals(other.maps))
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
        if (this.plaintext == null)
        {
            if (other.plaintext != null)
            {
                return false;
            }
        } else if (!this.plaintext.equals(other.plaintext))
        {
            return false;
        }
        if (this.requiredChampion == null)
        {
            if (other.requiredChampion != null)
            {
                return false;
            }
        } else if (!this.requiredChampion.equals(other.requiredChampion))
        {
            return false;
        }
        if (this.rune == null)
        {
            if (other.rune != null)
            {
                return false;
            }
        } else if (!this.rune.equals(other.rune))
        {
            return false;
        }
        if (this.sanitizedDescription == null)
        {
            if (other.sanitizedDescription != null)
            {
                return false;
            }
        } else if (!this.sanitizedDescription.equals(other.sanitizedDescription))
        {
            return false;
        }
        if (this.specialRecipe == null)
        {
            if (other.specialRecipe != null)
            {
                return false;
            }
        } else if (!this.specialRecipe.equals(other.specialRecipe))
        {
            return false;
        }
        if (this.stacks == null)
        {
            if (other.stacks != null)
            {
                return false;
            }
        } else if (!this.stacks.equals(other.stacks))
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
        return true;
    }
    
    /**
     * Gets the colloq.
     *
     * @return the colloq
     */
    public String getColloq()
    {
        return this.colloq;
    }
    
    /**
     * Gets the consumed.
     *
     * @return the consumed
     */
    public Boolean getConsumed()
    {
        return this.consumed;
    }
    
    /**
     * Gets the consume on full.
     *
     * @return the consume on full
     */
    public Boolean getConsumeOnFull()
    {
        return this.consumeOnFull;
    }
    
    /**
     * Gets the depth.
     *
     * @return the depth
     */
    public Integer getDepth()
    {
        return this.depth;
    }
    
    /**
     * Gets the description.
     *
     * @return the description
     */
    public String getDescription()
    {
        return this.description;
    }
    
    /**
     * Gets the from.
     *
     * @return the from
     */
    public List<String> getFrom()
    {
        return this.from;
    }
    
    /**
     * Gets the gold.
     *
     * @return the gold
     */
    public Gold getGold()
    {
        return this.gold;
    }
    
    /**
     * Gets the group.
     *
     * @return the group
     */
    public String getGroup()
    {
        return this.group;
    }
    
    /**
     * Gets the hide from all.
     *
     * @return the hide from all
     */
    public Boolean getHideFromAll()
    {
        return this.hideFromAll;
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
     * Gets the in store.
     *
     * @return the in store
     */
    public Boolean getInStore()
    {
        return this.inStore;
    }
    
    /**
     * Gets the into.
     *
     * @return the into
     */
    public List<String> getInto()
    {
        return this.into;
    }
    
    /**
     * Gets the maps.
     *
     * @return the maps
     */
    public Map<String, Boolean> getMaps()
    {
        return this.maps;
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
     * Gets the plaintext.
     *
     * @return the plaintext
     */
    public String getPlaintext()
    {
        return this.plaintext;
    }
    
    /**
     * Gets the required champion.
     *
     * @return the required champion
     */
    public String getRequiredChampion()
    {
        return this.requiredChampion;
    }
    
    /**
     * Gets the rune.
     *
     * @return the rune
     */
    public MetaData getRune()
    {
        return this.rune;
    }
    
    /**
     * Gets the sanitized description.
     *
     * @return the sanitized description
     */
    public String getSanitizedDescription()
    {
        return this.sanitizedDescription;
    }
    
    /**
     * Gets the special recipe.
     *
     * @return the special recipe
     */
    public Integer getSpecialRecipe()
    {
        return this.specialRecipe;
    }
    
    /**
     * Gets the stacks.
     *
     * @return the stacks
     */
    public Integer getStacks()
    {
        return this.stacks;
    }
    
    /**
     * Gets the stats.
     *
     * @return the stats
     */
    public BasicDataStats getStats()
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
    
    @Override
    public int hashCode()
    {
        final int prime  = 31;
        int       result = 1;
        result = (prime * result) + ((this.colloq == null) ? 0 : this.colloq.hashCode());
        result = (prime * result) + ((this.consumeOnFull == null) ? 0 : this.consumeOnFull.hashCode());
        result = (prime * result) + ((this.consumed == null) ? 0 : this.consumed.hashCode());
        result = (prime * result) + ((this.depth == null) ? 0 : this.depth.hashCode());
        result = (prime * result) + ((this.description == null) ? 0 : this.description.hashCode());
        result = (prime * result) + ((this.from == null) ? 0 : this.from.hashCode());
        result = (prime * result) + ((this.gold == null) ? 0 : this.gold.hashCode());
        result = (prime * result) + ((this.group == null) ? 0 : this.group.hashCode());
        result = (prime * result) + ((this.hideFromAll == null) ? 0 : this.hideFromAll.hashCode());
        result = (prime * result) + ((this.id == null) ? 0 : this.id.hashCode());
        result = (prime * result) + ((this.image == null) ? 0 : this.image.hashCode());
        result = (prime * result) + ((this.inStore == null) ? 0 : this.inStore.hashCode());
        result = (prime * result) + ((this.into == null) ? 0 : this.into.hashCode());
        result = (prime * result) + ((this.maps == null) ? 0 : this.maps.hashCode());
        result = (prime * result) + ((this.name == null) ? 0 : this.name.hashCode());
        result = (prime * result) + ((this.plaintext == null) ? 0 : this.plaintext.hashCode());
        result = (prime * result) + ((this.requiredChampion == null) ? 0 : this.requiredChampion.hashCode());
        result = (prime * result) + ((this.rune == null) ? 0 : this.rune.hashCode());
        result = (prime * result) + ((this.sanitizedDescription == null) ? 0 : this.sanitizedDescription.hashCode());
        result = (prime * result) + ((this.specialRecipe == null) ? 0 : this.specialRecipe.hashCode());
        result = (prime * result) + ((this.stacks == null) ? 0 : this.stacks.hashCode());
        result = (prime * result) + ((this.stats == null) ? 0 : this.stats.hashCode());
        result = (prime * result) + ((this.tags == null) ? 0 : this.tags.hashCode());
        return result;
    }
    
    @Override
    public String toString()
    {
        return "BasicData{" +
               "colloq='" + colloq + '\'' +
               ", consumeOnFull=" + consumeOnFull +
               ", consumed=" + consumed +
               ", depth=" + depth +
               ", description='" + description + '\'' +
               ", from=" + from +
               ", gold=" + gold +
               ", group='" + group + '\'' +
               ", hideFromAll=" + hideFromAll +
               ", id=" + id +
               ", image=" + image +
               ", inStore=" + inStore +
               ", into=" + into +
               ", maps=" + maps +
               ", name='" + name + '\'' +
               ", plaintext='" + plaintext + '\'' +
               ", requiredChampion='" + requiredChampion + '\'' +
               ", rune=" + rune +
               ", sanitizedDescription='" + sanitizedDescription + '\'' +
               ", specialRecipe=" + specialRecipe +
               ", stacks=" + stacks +
               ", stats=" + stats +
               ", tags=" + tags +
               '}';
    }
}
