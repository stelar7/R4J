package no.stelar7.api.l4j8.pojo.staticdata.champion;

import java.util.List;

public class Recommended
{
    List<Block> blocks;
    String      champion;
    String      map;
    String      mode;
    Boolean     priority;
    String      title;
    String      type;

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
        final Recommended other = (Recommended) obj;
        if (this.blocks == null)
        {
            if (other.blocks != null)
            {
                return false;
            }
        } else if (!this.blocks.equals(other.blocks))
        {
            return false;
        }
        if (this.champion == null)
        {
            if (other.champion != null)
            {
                return false;
            }
        } else if (!this.champion.equals(other.champion))
        {
            return false;
        }
        if (this.map == null)
        {
            if (other.map != null)
            {
                return false;
            }
        } else if (!this.map.equals(other.map))
        {
            return false;
        }
        if (this.mode == null)
        {
            if (other.mode != null)
            {
                return false;
            }
        } else if (!this.mode.equals(other.mode))
        {
            return false;
        }
        if (this.priority == null)
        {
            if (other.priority != null)
            {
                return false;
            }
        } else if (!this.priority.equals(other.priority))
        {
            return false;
        }
        if (this.title == null)
        {
            if (other.title != null)
            {
                return false;
            }
        } else if (!this.title.equals(other.title))
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
     * Gets the blocks.
     *
     * @return the blocks
     */
    public List<Block> getBlocks()
    {
        return this.blocks;
    }

    /**
     * Gets the champion.
     *
     * @return the champion
     */
    public String getChampion()
    {
        return this.champion;
    }

    /**
     * Gets the map.
     *
     * @return the map
     */
    public String getMap()
    {
        return this.map;
    }

    /**
     * Gets the mode.
     *
     * @return the mode
     */
    public String getMode()
    {
        return this.mode;
    }

    /**
     * Gets the priority.
     *
     * @return the priority
     */
    public Boolean getPriority()
    {
        return this.priority;
    }

    /**
     * Gets the title.
     *
     * @return the title
     */
    public String getTitle()
    {
        return this.title;
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
        final int prime = 31;
        int result = 1;
        result = (prime * result) + ((this.blocks == null) ? 0 : this.blocks.hashCode());
        result = (prime * result) + ((this.champion == null) ? 0 : this.champion.hashCode());
        result = (prime * result) + ((this.map == null) ? 0 : this.map.hashCode());
        result = (prime * result) + ((this.mode == null) ? 0 : this.mode.hashCode());
        result = (prime * result) + ((this.priority == null) ? 0 : this.priority.hashCode());
        result = (prime * result) + ((this.title == null) ? 0 : this.title.hashCode());
        result = (prime * result) + ((this.type == null) ? 0 : this.type.hashCode());
        return result;
    }
}
