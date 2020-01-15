package no.stelar7.api.r4j.pojo.lol.staticdata.mastery;

import java.io.Serializable;

public class MasteryTreeItem implements Serializable
{
    private static final long serialVersionUID = 3955045796477384883L;
    
    private int    masteryId;
    private String prereq;
    
    
    /**
     * Gets the mastery id.
     *
     * @return the mastery id
     */
    public int getMasteryId()
    {
        return this.masteryId;
    }
    
    /**
     * Gets the prereq.
     *
     * @return the prereq
     */
    public String getPrereq()
    {
        return this.prereq;
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
        
        MasteryTreeItem that = (MasteryTreeItem) o;
        
        if (masteryId != that.masteryId)
        {
            return false;
        }
        return (prereq != null) ? prereq.equals(that.prereq) : (that.prereq == null);
    }
    
    @Override
    public int hashCode()
    {
        int result = masteryId;
        result = 31 * result + (prereq != null ? prereq.hashCode() : 0);
        return result;
    }
    
    @Override
    public String toString()
    {
        return "MasteryTreeItem{" +
               "masteryId=" + masteryId +
               ", prereq='" + prereq + '\'' +
               '}';
    }
}
