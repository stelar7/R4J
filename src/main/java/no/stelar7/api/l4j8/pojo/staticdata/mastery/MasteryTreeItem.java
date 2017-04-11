package no.stelar7.api.l4j8.pojo.staticdata.mastery;

public class MasteryTreeItem
{
    private Integer masteryId;
    private String  prereq;
    
    
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
        final MasteryTreeItem other = (MasteryTreeItem) obj;
        if (this.masteryId == null)
        {
            if (other.masteryId != null)
            {
                return false;
            }
        } else if (!this.masteryId.equals(other.masteryId))
        {
            return false;
        }
        if (this.prereq == null)
        {
            if (other.prereq != null)
            {
                return false;
            }
        } else if (!this.prereq.equals(other.prereq))
        {
            return false;
        }
        return true;
    }
    
    /**
     * Gets the mastery id.
     *
     * @return the mastery id
     */
    public Integer getMasteryId()
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
    public int hashCode()
    {
        final int prime  = 31;
        int       result = 1;
        result = (prime * result) + ((this.masteryId == null) ? 0 : this.masteryId.hashCode());
        result = (prime * result) + ((this.prereq == null) ? 0 : this.prereq.hashCode());
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
