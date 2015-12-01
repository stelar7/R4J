package no.stelar7.api.l4j8.pojo.staticdata.summonerspell;

import java.util.List;

import no.stelar7.api.l4j8.basic.APIObject;
import no.stelar7.api.l4j8.pojo.staticdata.shared.Image;
import no.stelar7.api.l4j8.pojo.staticdata.shared.LevelTip;
import no.stelar7.api.l4j8.pojo.staticdata.shared.SpellVars;

public class SummonerSpell implements APIObject
{
    List<Double>       cooldown;
    String             cooldownBurn;
    List<Integer>      cost;
    String             costBurn;
    String             costType;
    String             description;
    List<List<Double>> effect;
    List<String>       effectBurn;
    Integer            id;
    Image              image;
    String             key;
    LevelTip           leveltip;
    Integer            maxrank;
    List<String>       modes;
    String             name;
    List<Integer>      range;
    String             rangeBurn;
    String             resource;
    String             sanitizedDescription;
    String             sanitizedTooltip;
    Integer            summonerLevel;
    String             tooltip;
    List<SpellVars>    vars;

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
        final SummonerSpell other = (SummonerSpell) obj;
        if (this.cooldown == null)
        {
            if (other.cooldown != null)
            {
                return false;
            }
        } else if (!this.cooldown.equals(other.cooldown))
        {
            return false;
        }
        if (this.cooldownBurn == null)
        {
            if (other.cooldownBurn != null)
            {
                return false;
            }
        } else if (!this.cooldownBurn.equals(other.cooldownBurn))
        {
            return false;
        }
        if (this.cost == null)
        {
            if (other.cost != null)
            {
                return false;
            }
        } else if (!this.cost.equals(other.cost))
        {
            return false;
        }
        if (this.costBurn == null)
        {
            if (other.costBurn != null)
            {
                return false;
            }
        } else if (!this.costBurn.equals(other.costBurn))
        {
            return false;
        }
        if (this.costType == null)
        {
            if (other.costType != null)
            {
                return false;
            }
        } else if (!this.costType.equals(other.costType))
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
        if (this.effect == null)
        {
            if (other.effect != null)
            {
                return false;
            }
        } else if (!this.effect.equals(other.effect))
        {
            return false;
        }
        if (this.effectBurn == null)
        {
            if (other.effectBurn != null)
            {
                return false;
            }
        } else if (!this.effectBurn.equals(other.effectBurn))
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
        if (this.leveltip == null)
        {
            if (other.leveltip != null)
            {
                return false;
            }
        } else if (!this.leveltip.equals(other.leveltip))
        {
            return false;
        }
        if (this.maxrank == null)
        {
            if (other.maxrank != null)
            {
                return false;
            }
        } else if (!this.maxrank.equals(other.maxrank))
        {
            return false;
        }
        if (this.modes == null)
        {
            if (other.modes != null)
            {
                return false;
            }
        } else if (!this.modes.equals(other.modes))
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
        if (this.range == null)
        {
            if (other.range != null)
            {
                return false;
            }
        } else if (!this.range.equals(other.range))
        {
            return false;
        }
        if (this.rangeBurn == null)
        {
            if (other.rangeBurn != null)
            {
                return false;
            }
        } else if (!this.rangeBurn.equals(other.rangeBurn))
        {
            return false;
        }
        if (this.resource == null)
        {
            if (other.resource != null)
            {
                return false;
            }
        } else if (!this.resource.equals(other.resource))
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
        if (this.sanitizedTooltip == null)
        {
            if (other.sanitizedTooltip != null)
            {
                return false;
            }
        } else if (!this.sanitizedTooltip.equals(other.sanitizedTooltip))
        {
            return false;
        }
        if (this.summonerLevel == null)
        {
            if (other.summonerLevel != null)
            {
                return false;
            }
        } else if (!this.summonerLevel.equals(other.summonerLevel))
        {
            return false;
        }
        if (this.tooltip == null)
        {
            if (other.tooltip != null)
            {
                return false;
            }
        } else if (!this.tooltip.equals(other.tooltip))
        {
            return false;
        }
        if (this.vars == null)
        {
            if (other.vars != null)
            {
                return false;
            }
        } else if (!this.vars.equals(other.vars))
        {
            return false;
        }
        return true;
    }

    /**
     * Gets the cooldown.
     *
     * @return the cooldown
     */
    public List<Double> getCooldown()
    {
        return this.cooldown;
    }

    /**
     * Gets the cooldown burn.
     *
     * @return the cooldown burn
     */
    public String getCooldownBurn()
    {
        return this.cooldownBurn;
    }

    /**
     * Gets the cost.
     *
     * @return the cost
     */
    public List<Integer> getCost()
    {
        return this.cost;
    }

    /**
     * Gets the cost burn.
     *
     * @return the cost burn
     */
    public String getCostBurn()
    {
        return this.costBurn;
    }

    /**
     * Gets the cost type.
     *
     * @return the cost type
     */
    public String getCostType()
    {
        return this.costType;
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
     * Gets the effect.
     *
     * @return the effect
     */
    public List<List<Double>> getEffect()
    {
        return this.effect;
    }

    /**
     * Gets the effect burn.
     *
     * @return the effect burn
     */
    public List<String> getEffectBurn()
    {
        return this.effectBurn;
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
     * Gets the key.
     *
     * @return the key
     */
    public String getKey()
    {
        return this.key;
    }

    /**
     * Gets the leveltip.
     *
     * @return the leveltip
     */
    public LevelTip getLeveltip()
    {
        return this.leveltip;
    }

    /**
     * Gets the maxrank.
     *
     * @return the maxrank
     */
    public Integer getMaxrank()
    {
        return this.maxrank;
    }

    /**
     * Gets the modes.
     *
     * @return the modes
     */
    public List<String> getModes()
    {
        return this.modes;
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
     * Gets the range.
     *
     * @return the range
     */
    public List<Integer> getRange()
    {
        return this.range;
    }

    /**
     * Gets the range burn.
     *
     * @return the range burn
     */
    public String getRangeBurn()
    {
        return this.rangeBurn;
    }

    /**
     * Gets the resource.
     *
     * @return the resource
     */
    public String getResource()
    {
        return this.resource;
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
     * Gets the sanitized tooltip.
     *
     * @return the sanitized tooltip
     */
    public String getSanitizedTooltip()
    {
        return this.sanitizedTooltip;
    }

    /**
     * Gets the summoner level.
     *
     * @return the summoner level
     */
    public Integer getSummonerLevel()
    {
        return this.summonerLevel;
    }

    /**
     * Gets the tooltip.
     *
     * @return the tooltip
     */
    public String getTooltip()
    {
        return this.tooltip;
    }

    /**
     * Gets the vars.
     *
     * @return the vars
     */
    public List<SpellVars> getVars()
    {
        return this.vars;
    }

    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = (prime * result) + ((this.cooldown == null) ? 0 : this.cooldown.hashCode());
        result = (prime * result) + ((this.cooldownBurn == null) ? 0 : this.cooldownBurn.hashCode());
        result = (prime * result) + ((this.cost == null) ? 0 : this.cost.hashCode());
        result = (prime * result) + ((this.costBurn == null) ? 0 : this.costBurn.hashCode());
        result = (prime * result) + ((this.costType == null) ? 0 : this.costType.hashCode());
        result = (prime * result) + ((this.description == null) ? 0 : this.description.hashCode());
        result = (prime * result) + ((this.effect == null) ? 0 : this.effect.hashCode());
        result = (prime * result) + ((this.effectBurn == null) ? 0 : this.effectBurn.hashCode());
        result = (prime * result) + ((this.id == null) ? 0 : this.id.hashCode());
        result = (prime * result) + ((this.image == null) ? 0 : this.image.hashCode());
        result = (prime * result) + ((this.key == null) ? 0 : this.key.hashCode());
        result = (prime * result) + ((this.leveltip == null) ? 0 : this.leveltip.hashCode());
        result = (prime * result) + ((this.maxrank == null) ? 0 : this.maxrank.hashCode());
        result = (prime * result) + ((this.modes == null) ? 0 : this.modes.hashCode());
        result = (prime * result) + ((this.name == null) ? 0 : this.name.hashCode());
        result = (prime * result) + ((this.range == null) ? 0 : this.range.hashCode());
        result = (prime * result) + ((this.rangeBurn == null) ? 0 : this.rangeBurn.hashCode());
        result = (prime * result) + ((this.resource == null) ? 0 : this.resource.hashCode());
        result = (prime * result) + ((this.sanitizedDescription == null) ? 0 : this.sanitizedDescription.hashCode());
        result = (prime * result) + ((this.sanitizedTooltip == null) ? 0 : this.sanitizedTooltip.hashCode());
        result = (prime * result) + ((this.summonerLevel == null) ? 0 : this.summonerLevel.hashCode());
        result = (prime * result) + ((this.tooltip == null) ? 0 : this.tooltip.hashCode());
        result = (prime * result) + ((this.vars == null) ? 0 : this.vars.hashCode());
        return result;
    }
}
