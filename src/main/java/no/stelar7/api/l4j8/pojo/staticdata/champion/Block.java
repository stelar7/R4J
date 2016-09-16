package no.stelar7.api.l4j8.pojo.staticdata.champion;

import java.util.List;

class Block
{
    private List<BlockItem> items;
    private Boolean recMath;
    private String type;

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
        final Block other = (Block) obj;
        if (this.items == null)
        {
            if (other.items != null)
            {
                return false;
            }
        } else if (!this.items.equals(other.items))
        {
            return false;
        }
        if (this.recMath == null)
        {
            if (other.recMath != null)
            {
                return false;
            }
        } else if (!this.recMath.equals(other.recMath))
        {
            return false;
        }
        if (this.type == null)
        {
            if (other.type != null)
            {
                return false;
            }
        } else if (!this.type.equals(other.type))
        {
            return false;
        }
        return true;
    }

    /**
     * Gets the items.
     *
     * @return the items
     */
    public List<BlockItem> getItems()
    {
        return this.items;
    }

    /**
     * Gets the rec math.
     *
     * @return the rec math
     */
    public Boolean getRecMath()
    {
        return this.recMath;
    }

    /**
     * Gets the type.
     *
     * @return the type
     */
    public String getType()
    {
        return this.type;
    }

    @Override
    public int hashCode()
    {
        final int prime  = 31;
        int       result = 1;
        result = (prime * result) + ((this.items == null) ? 0 : this.items.hashCode());
        result = (prime * result) + ((this.recMath == null) ? 0 : this.recMath.hashCode());
        result = (prime * result) + ((this.type == null) ? 0 : this.type.hashCode());
        return result;
    }
}