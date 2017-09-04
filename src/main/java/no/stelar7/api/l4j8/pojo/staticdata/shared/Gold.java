package no.stelar7.api.l4j8.pojo.staticdata.shared;

import java.io.Serializable;

public class Gold implements Serializable
{
    private static final long serialVersionUID = -7870414403107823868L;
    
    private int     base;
    private boolean purchasable;
    private int     sell;
    private int     total;
    
    
    /**
     * Gets the base.
     *
     * @return the base
     */
    public int getBase()
    {
        return this.base;
    }
    
    /**
     * Gets the purchasable.
     *
     * @return the purchasable
     */
    public boolean getPurchasable()
    {
        return this.purchasable;
    }
    
    /**
     * Gets the sell.
     *
     * @return the sell
     */
    public int getSell()
    {
        return this.sell;
    }
    
    /**
     * Gets the total.
     *
     * @return the total
     */
    public int getTotal()
    {
        return this.total;
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
        
        Gold gold = (Gold) o;
        
        if (base != gold.base)
        {
            return false;
        }
        if (purchasable != gold.purchasable)
        {
            return false;
        }
        if (sell != gold.sell)
        {
            return false;
        }
        return total == gold.total;
    }
    
    @Override
    public int hashCode()
    {
        int result = base;
        result = 31 * result + (purchasable ? 1 : 0);
        result = 31 * result + sell;
        result = 31 * result + total;
        return result;
    }
    
    @Override
    public String toString()
    {
        return "Gold{" +
               "base=" + base +
               ", purchasable=" + purchasable +
               ", sell=" + sell +
               ", total=" + total +
               '}';
    }
}
