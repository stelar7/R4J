package no.stelar7.api.r4j.pojo.tft;

import java.util.Objects;

public class TFTCompanion
{
    private int    skin_ID;
    private String content_ID;
    private String species;
    
    public int getSkin_ID()
    {
        return skin_ID;
    }
    
    public String getContent_ID()
    {
        return content_ID;
    }
    
    public String getSpecies()
    {
        return species;
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
        TFTCompanion that = (TFTCompanion) o;
        return skin_ID == that.skin_ID &&
               Objects.equals(content_ID, that.content_ID) &&
               Objects.equals(species, that.species);
    }
    
    @Override
    public int hashCode()
    {
        return Objects.hash(skin_ID, content_ID, species);
    }
    
    @Override
    public String toString()
    {
        return "TFTCompanion{" +
               "skin_ID=" + skin_ID +
               ", content_ID='" + content_ID + '\'' +
               ", species='" + species + '\'' +
               '}';
    }
}
