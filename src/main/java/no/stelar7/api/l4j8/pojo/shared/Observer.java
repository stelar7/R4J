package no.stelar7.api.l4j8.pojo.shared;

public class Observer
{
    private String encryptionKey;
    
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
        final Observer other = (Observer) obj;
        if (this.encryptionKey == null)
        {
            if (other.encryptionKey != null)
            {
                return false;
            }
        } else if (!this.encryptionKey.equals(other.encryptionKey))
        {
            return false;
        }
        return true;
    }
    
    /**
     * Key used to decrypt the spectator grid game data for playback
     *
     * @return String
     */
    public String getEncryptionKey()
    {
        return this.encryptionKey;
    }
    
    @Override
    public int hashCode()
    {
        final int prime  = 31;
        int       result = 1;
        result = (prime * result) + ((this.encryptionKey == null) ? 0 : this.encryptionKey.hashCode());
        return result;
    }
    
    @Override
    public String toString()
    {
        return "Observer{" +
               "encryptionKey='" + encryptionKey + '\'' +
               '}';
    }
}
