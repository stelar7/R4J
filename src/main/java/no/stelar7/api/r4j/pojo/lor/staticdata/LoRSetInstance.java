package no.stelar7.api.r4j.pojo.lor.staticdata;

import java.nio.file.Path;
import java.util.Objects;

public class LoRSetInstance
{
    private String set;
    private String language;
    private Path   folderLocation;
    
    public LoRSetInstance(Path folderLocation, String set, String language)
    {
        this.set = set;
        this.language = language;
        this.folderLocation = folderLocation;
    }
    
    public String getSet()
    {
        return set;
    }
    
    public String getLanguage()
    {
        return language;
    }
    
    public Path getFolderLocation()
    {
        return folderLocation;
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
        LoRSetInstance that = (LoRSetInstance) o;
        return Objects.equals(set, that.set) &&
               Objects.equals(language, that.language) &&
               Objects.equals(folderLocation, that.folderLocation);
    }
    
    @Override
    public int hashCode()
    {
        return Objects.hash(set, language, folderLocation);
    }
    
    @Override
    public String toString()
    {
        return "LoRSetInstance{" +
               "set='" + set + '\'' +
               ", language='" + language + '\'' +
               ", folderLocation=" + folderLocation +
               '}';
    }
}
