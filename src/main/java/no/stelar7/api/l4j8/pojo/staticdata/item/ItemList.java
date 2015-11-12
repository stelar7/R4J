package no.stelar7.api.l4j8.pojo.staticdata.item;

import java.util.List;
import java.util.Map;

import no.stelar7.api.l4j8.basic.APIObject;
import no.stelar7.api.l4j8.pojo.staticdata.shared.BasicData;

public class ItemList implements APIObject
{
    BasicData         basic;
    Map<String, Item> data;
    List<Group>       groups;
    List<ItemTree>    tree;
    String            type;
    String            version;

    /**
     * Gets the basic.
     *
     * @return the basic
     */
    public BasicData getBasic()
    {
        return basic;
    }

    /**
     * Gets the data.
     *
     * @return the data
     */
    public Map<String, Item> getData()
    {
        return data;
    }

    /**
     * Gets the groups.
     *
     * @return the groups
     */
    public List<Group> getGroups()
    {
        return groups;
    }

    /**
     * Gets the tree.
     *
     * @return the tree
     */
    public List<ItemTree> getTree()
    {
        return tree;
    }

    /**
     * Gets the type.
     *
     * @return the type
     */
    public String getType()
    {
        return type;
    }

    /**
     * Gets the version.
     *
     * @return the version
     */
    public String getVersion()
    {
        return version;
    }
}
