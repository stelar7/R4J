package no.stelar7.api.r4j.pojo.lol.liveclient;

import java.util.Objects;

public class ActiveGameItem
{
    private boolean canUse;
    private boolean consumable;
    private int     count;
    private String  displayName;
    private int     itemID;
    private int     price;
    private String  rawDescription;
    private String  rawDisplayName;
    private int     slot;
    
    public boolean isCanUse()
    {
        return canUse;
    }
    
    public boolean isConsumable()
    {
        return consumable;
    }
    
    public int getCount()
    {
        return count;
    }
    
    public String getDisplayName()
    {
        return displayName;
    }
    
    public int getItemID()
    {
        return itemID;
    }
    
    public int getPrice()
    {
        return price;
    }
    
    public String getRawDescription()
    {
        return rawDescription;
    }
    
    public String getRawDisplayName()
    {
        return rawDisplayName;
    }
    
    public int getSlot()
    {
        return slot;
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
        ActiveGameItem that = (ActiveGameItem) o;
        return canUse == that.canUse &&
               consumable == that.consumable &&
               count == that.count &&
               itemID == that.itemID &&
               price == that.price &&
               slot == that.slot &&
               Objects.equals(displayName, that.displayName) &&
               Objects.equals(rawDescription, that.rawDescription) &&
               Objects.equals(rawDisplayName, that.rawDisplayName);
    }
    
    @Override
    public int hashCode()
    {
        return Objects.hash(canUse, consumable, count, displayName, itemID, price, rawDescription, rawDisplayName, slot);
    }
    
    @Override
    public String toString()
    {
        return "ActiveGameItem{" +
               "canUse=" + canUse +
               ", consumable=" + consumable +
               ", count=" + count +
               ", displayName='" + displayName + '\'' +
               ", itemID=" + itemID +
               ", price=" + price +
               ", rawDescription='" + rawDescription + '\'' +
               ", rawDisplayName='" + rawDisplayName + '\'' +
               ", slot=" + slot +
               '}';
    }
}
