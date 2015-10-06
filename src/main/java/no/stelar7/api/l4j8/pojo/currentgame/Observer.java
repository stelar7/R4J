package no.stelar7.api.l4j8.pojo.currentgame;

import no.stelar7.api.l4j8.basic.APIObject;

public class Observer extends APIObject
{
    private String encryptionKey;

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
    public String toString()
    {
        return "Observer [encryptionKey=" + this.encryptionKey + "]";
    }
}
