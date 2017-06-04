package no.stelar7.api.l4j8.pojo.shared;

import no.stelar7.api.l4j8.pojo.staticdata.shared.*;

import java.util.*;

public class BaseSpellData
{
    private List<Double>       cooldown;
    private String             cooldownBurn;
    private List<Integer>      cost;
    private String             costBurn;
    private String             costType;
    private String             description;
    private List<List<Double>> effect;
    private List<String>       effectBurn;
    private Image              image;
    private String             key;
    private LevelTip           leveltip;
    private int                maxrank;
    private String             name;
    private Object             range;
    private String             rangeBurn;
    private String             resource;
    private String             sanitizedDescription;
    private String             sanitizedTooltip;
    private String             tooltip;
    private List<SpellVars>    vars;
    
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
    public int getMaxRank()
    {
        return this.maxrank;
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
    public List<Integer> getRangeAsList()
    {
        return (this.range instanceof List) ? (List<Integer>) this.range : null;
    }
    
    /**
     * Only valid for some spells
     *
     * @return the range
     */
    public String getRangeAsString()
    {
        return (this.range instanceof String) ? (String) this.range : null;
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
    
    public String getSanitizedTooltip(final int champLevel, final int spellLevel)
    {
        return this.replaceVariables(this.sanitizedTooltip, champLevel, spellLevel);
    }
    
    public String getTooltip(final int champLevel, final int spellLevel)
    {
        return this.replaceVariables(this.tooltip, champLevel, spellLevel);
    }
    
    private String replaceVariables(String text, final int clevel, final int slevel)
    {
        if ((clevel > 18) || (clevel < 1))
        {
            throw new IllegalArgumentException("Champion level out of bounds!");
        }
        if ((slevel > this.maxrank) || (slevel < 1))
        {
            throw new IllegalArgumentException("Spell level out of bounds!");
        }
        
        String data = text;
        
        if (this.effect != null)
        {
            int i = 0;
            for (final List<Double> e : this.effect)
            {
                if (e == null)
                {
                    continue;
                }
                
                String regex = String.format("\\{\\{ e%s }}", i);
                String value = e.get(slevel - 1).toString();
                data = data.replaceAll(regex, value);
                i++;
            }
        }
        if (this.vars != null)
        {
            for (final SpellVars var : this.vars)
            {
                if (var == null)
                {
                    continue;
                }
                Double val = var.getCoeff().get(0);
                if (var.getCoeff().size() == this.maxrank)
                {
                    val = var.getCoeff().get(slevel - 1);
                } else if (var.getCoeff().size() == 18)
                {
                    val = var.getCoeff().get(clevel - 1);
                }
                
                String regex = String.format("\\{\\{ %s }}", var.getKey());
                String value = val.toString();
                data = data.replaceAll(regex, value);
            }
        }
        return data;
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
        
        BaseSpellData that = (BaseSpellData) o;
        
        if (maxrank != that.maxrank)
        {
            return false;
        }
        if ((cooldown != null) ? !cooldown.equals(that.cooldown) : (that.cooldown != null))
        {
            return false;
        }
        if ((cooldownBurn != null) ? !cooldownBurn.equals(that.cooldownBurn) : (that.cooldownBurn != null))
        {
            return false;
        }
        if ((cost != null) ? !cost.equals(that.cost) : (that.cost != null))
        {
            return false;
        }
        if ((costBurn != null) ? !costBurn.equals(that.costBurn) : (that.costBurn != null))
        {
            return false;
        }
        if ((costType != null) ? !costType.equals(that.costType) : (that.costType != null))
        {
            return false;
        }
        if ((description != null) ? !description.equals(that.description) : (that.description != null))
        {
            return false;
        }
        if ((effect != null) ? !effect.equals(that.effect) : (that.effect != null))
        {
            return false;
        }
        if ((effectBurn != null) ? !effectBurn.equals(that.effectBurn) : (that.effectBurn != null))
        {
            return false;
        }
        if ((image != null) ? !image.equals(that.image) : (that.image != null))
        {
            return false;
        }
        if ((key != null) ? !key.equals(that.key) : (that.key != null))
        {
            return false;
        }
        if ((leveltip != null) ? !leveltip.equals(that.leveltip) : (that.leveltip != null))
        {
            return false;
        }
        if ((name != null) ? !name.equals(that.name) : (that.name != null))
        {
            return false;
        }
        if ((range != null) ? !range.equals(that.range) : (that.range != null))
        {
            return false;
        }
        if ((rangeBurn != null) ? !rangeBurn.equals(that.rangeBurn) : (that.rangeBurn != null))
        {
            return false;
        }
        if ((resource != null) ? !resource.equals(that.resource) : (that.resource != null))
        {
            return false;
        }
        if ((sanitizedDescription != null) ? !sanitizedDescription.equals(that.sanitizedDescription) : (that.sanitizedDescription != null))
        {
            return false;
        }
        if ((sanitizedTooltip != null) ? !sanitizedTooltip.equals(that.sanitizedTooltip) : (that.sanitizedTooltip != null))
        {
            return false;
        }
        if ((tooltip != null) ? !tooltip.equals(that.tooltip) : (that.tooltip != null))
        {
            return false;
        }
        return (vars != null) ? vars.equals(that.vars) : (that.vars == null);
    }
    
    @Override
    public int hashCode()
    {
        int result = cooldown != null ? cooldown.hashCode() : 0;
        result = 31 * result + (cooldownBurn != null ? cooldownBurn.hashCode() : 0);
        result = 31 * result + (cost != null ? cost.hashCode() : 0);
        result = 31 * result + (costBurn != null ? costBurn.hashCode() : 0);
        result = 31 * result + (costType != null ? costType.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (effect != null ? effect.hashCode() : 0);
        result = 31 * result + (effectBurn != null ? effectBurn.hashCode() : 0);
        result = 31 * result + (image != null ? image.hashCode() : 0);
        result = 31 * result + (key != null ? key.hashCode() : 0);
        result = 31 * result + (leveltip != null ? leveltip.hashCode() : 0);
        result = 31 * result + maxrank;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (range != null ? range.hashCode() : 0);
        result = 31 * result + (rangeBurn != null ? rangeBurn.hashCode() : 0);
        result = 31 * result + (resource != null ? resource.hashCode() : 0);
        result = 31 * result + (sanitizedDescription != null ? sanitizedDescription.hashCode() : 0);
        result = 31 * result + (sanitizedTooltip != null ? sanitizedTooltip.hashCode() : 0);
        result = 31 * result + (tooltip != null ? tooltip.hashCode() : 0);
        result = 31 * result + (vars != null ? vars.hashCode() : 0);
        return result;
    }
    
    @Override
    public String toString()
    {
        return "BaseSpellData{" +
               "cooldown=" + cooldown +
               ", cooldownBurn='" + cooldownBurn + '\'' +
               ", cost=" + cost +
               ", costBurn='" + costBurn + '\'' +
               ", costType='" + costType + '\'' +
               ", description='" + description + '\'' +
               ", effect=" + effect +
               ", effectBurn=" + effectBurn +
               ", image=" + image +
               ", key='" + key + '\'' +
               ", leveltip=" + leveltip +
               ", maxrank=" + maxrank +
               ", name='" + name + '\'' +
               ", range=" + range +
               ", rangeBurn='" + rangeBurn + '\'' +
               ", resource='" + resource + '\'' +
               ", sanitizedDescription='" + sanitizedDescription + '\'' +
               ", sanitizedTooltip='" + sanitizedTooltip + '\'' +
               ", tooltip='" + tooltip + '\'' +
               ", vars=" + vars +
               '}';
    }
}
