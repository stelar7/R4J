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
    
    

    /**
     * Gets the cooldown.
     *
     * @return the cooldown
     */
    public List<Double> getCooldown()
    {
        return cooldown;
    }

    /**
     * Gets the cooldown burn.
     *
     * @return the cooldown burn
     */
    public String getCooldownBurn()
    {
        return cooldownBurn;
    }

    /**
     * Gets the cost.
     *
     * @return the cost
     */
    public List<Integer> getCost()
    {
        return cost;
    }

    /**
     * Gets the cost burn.
     *
     * @return the cost burn
     */
    public String getCostBurn()
    {
        return costBurn;
    }

    /**
     * Gets the cost type.
     *
     * @return the cost type
     */
    public String getCostType()
    {
        return costType;
    }

    /**
     * Gets the description.
     *
     * @return the description
     */
    public String getDescription()
    {
        return description;
    }

    /**
     * Gets the effect.
     *
     * @return the effect
     */
    public List<List<Double>> getEffect()
    {
        return effect;
    }

    /**
     * Gets the effect burn.
     *
     * @return the effect burn
     */
    public List<String> getEffectBurn()
    {
        return effectBurn;
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
     * Gets the key.
     *
     * @return the key
     */
    public String getKey()
    {
        return key;
    }

    /**
     * Gets the leveltip.
     *
     * @return the leveltip
     */
    public LevelTip getLeveltip()
    {
        return leveltip;
    }

    /**
     * Gets the maxrank.
     *
     * @return the maxrank
     */
    public Integer getMaxrank()
    {
        return maxrank;
    }

    /**
     * Gets the modes.
     *
     * @return the modes
     */
    public List<String> getModes()
    {
        return modes;
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
     * Gets the range.
     *
     * @return the range
     */
    public List<Integer> getRange()
    {
        return range;
    }

    /**
     * Gets the range burn.
     *
     * @return the range burn
     */
    public String getRangeBurn()
    {
        return rangeBurn;
    }

    /**
     * Gets the resource.
     *
     * @return the resource
     */
    public String getResource()
    {
        return resource;
    }

    /**
     * Gets the sanitized description.
     *
     * @return the sanitized description
     */
    public String getSanitizedDescription()
    {
        return sanitizedDescription;
    }

    /**
     * Gets the sanitized tooltip.
     *
     * @return the sanitized tooltip
     */
    public String getSanitizedTooltip()
    {
        return sanitizedTooltip;
    }

    /**
     * Gets the summoner level.
     *
     * @return the summoner level
     */
    public Integer getSummonerLevel()
    {
        return summonerLevel;
    }

    /**
     * Gets the tooltip.
     *
     * @return the tooltip
     */
    public String getTooltip()
    {
        return tooltip;
    }

    /**
     * Gets the vars.
     *
     * @return the vars
     */
    public List<SpellVars> getVars()
    {
        return vars;
    }
}
