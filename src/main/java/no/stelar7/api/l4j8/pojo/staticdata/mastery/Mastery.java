package no.stelar7.api.l4j8.pojo.staticdata.mastery;

import java.util.List;

import no.stelar7.api.l4j8.pojo.staticdata.shared.Image;

public class Mastery
{
    List<String> description;
    Integer      id;
    Image        image;
    String       masteryTree;
    String       name;
    String       prereq;
    Integer      ranks;
    List<String> sanitizedDescription;
}
