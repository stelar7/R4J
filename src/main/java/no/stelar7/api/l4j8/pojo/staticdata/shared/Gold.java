package no.stelar7.api.l4j8.pojo.staticdata.shared;

public class Gold
{
    Integer base;
    Boolean purchasable;
    Integer sell;
    Integer total;

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
        final Gold other = (Gold) obj;
        if (this.base == null)
        {
            if (other.base != null)
            {
                return false;
            }
        } else if (!this.base.equals(other.base))
        {
            return false;
        }
        if (this.purchasable == null)
        {
            if (other.purchasable != null)
            {
                return false;
            }
        } else if (!this.purchasable.equals(other.purchasable))
        {
            return false;
        }
        if (this.sell == null)
        {
            if (other.sell != null)
            {
                return false;
            }
        } else if (!this.sell.equals(other.sell))
        {
            return false;
        }
        if (this.total == null)
        {
            if (other.total != null)
            {
                return false;
            }
        } else if (!this.total.equals(other.total))
        {
            return false;
        }
        return true;
    }

    /**
     * Gets the base.
     *
     * @return the base
     */
    public Integer getBase()
    {
        return this.base;
    }

    /**
     * Gets the purchasable.
     *
     * @return the purchasable
     */
    public Boolean getPurchasable()
    {
        return this.purchasable;
    }

    /**
     * Gets the sell.
     *
     * @return the sell
     */
    public Integer getSell()
    {
        return this.sell;
    }

    /**
     * Gets the total.
     *
     * @return the total
     */
    public Integer getTotal()
    {
        return this.total;
    }

    @Override
    public int hashCode()
    {
        final int prime  = 31;
        int       result = 1;
        result = (prime * result) + ((this.base == null) ? 0 : this.base.hashCode());
        result = (prime * result) + ((this.purchasable == null) ? 0 : this.purchasable.hashCode());
        result = (prime * result) + ((this.sell == null) ? 0 : this.sell.hashCode());
        result = (prime * result) + ((this.total == null) ? 0 : this.total.hashCode());
        return result;
    }
}
