package no.stelar7.api.l4j8.pojo.staticdata.summonerspell;

import java.util.List;

import no.stelar7.api.l4j8.pojo.staticdata.shared.Image;
import no.stelar7.api.l4j8.pojo.staticdata.shared.LevelTip;
import no.stelar7.api.l4j8.pojo.staticdata.shared.SpellVars;

public class SummonerSpell
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
}
