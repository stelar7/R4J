package no.stelar7.api.l4j8.pojo.staticdata.item;

import java.util.List;
import java.util.Map;

import no.stelar7.api.l4j8.pojo.staticdata.shared.BasicData;

public class ItemList
{
    BasicData         basic;
    Map<String, Item> data;
    List<Group>       groups;
    List<ItemTree>    tree;
    String            type;
    String            version;
}
