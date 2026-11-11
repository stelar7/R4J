package no.stelar7.api.r4j.pojo.riftbound;

import java.io.Serializable;
import java.util.*;

public class RiftboundSet implements Serializable
{
    private static final long serialVersionUID = 1L;

    private String              id;
    private String              name;
    private List<RiftboundCard> cards;

    public String getId()
    {
        return id;
    }
    
    public String getName()
    {
        return name;
    }
    
    public List<RiftboundCard> getCards()
    {
        return cards;
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
        RiftboundSet that = (RiftboundSet) o;
        return Objects.equals(id, that.id) &&
               Objects.equals(name, that.name) &&
               Objects.equals(cards, that.cards);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(id, name, cards);
    }

    @Override
    public String toString()
    {
        return "RiftboundSet{" +
               "id=" + id +
               ", name=" + name +
               ", cards=" + cards +
               '}';
    }
}
