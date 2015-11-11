package no.stelar7.api.l4j8.pojo.staticdata.champion;

import java.util.List;

import no.stelar7.api.l4j8.pojo.staticdata.shared.Image;
import no.stelar7.api.l4j8.pojo.staticdata.shared.LevelTip;
import no.stelar7.api.l4j8.pojo.staticdata.shared.SpellVars;

public class ChampionSpell
{
    List<Image>        altimages;
    List<Double>       cooldown;
    String             cooldownBurn;
    List<Integer>      cost;
    String             costBurn;
    String             costType;
    String             description;
    List<List<Double>> effect;
    List<String>       effectBurn;
    Image              image;
    String             key;
    LevelTip           leveltip;
    Integer            maxrank;
    String             name;
    List<Integer>      range;
    String             rangeBurn;
    String             resource;
    String             sanitizedDescription;
    String             sanitizedTooltip;
    String             tooltip;
    List<SpellVars>    vars;

}
