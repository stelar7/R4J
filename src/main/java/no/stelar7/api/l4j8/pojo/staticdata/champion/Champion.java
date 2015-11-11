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
}
