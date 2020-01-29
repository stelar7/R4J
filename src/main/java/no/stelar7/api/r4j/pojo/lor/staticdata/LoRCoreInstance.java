package no.stelar7.api.r4j.pojo.lor.staticdata;

import no.stelar7.api.r4j.basic.utils.Utils;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.*;

public class LoRCoreInstance
{
    private String language;
    private Path   folderLocation;
    
    public LoRCoreInstance(Path folderLocation, String language)
    {
        this.language = language;
        this.folderLocation = folderLocation;
    }
    
    public String getLanguage()
    {
        return language;
    }
    
    public Path getFolderLocation()
    {
        return folderLocation;
    }
    
    public Path getSourceFileLocation()
    {
        String basePath = getLanguage() + File.separator + "data" + File.separator;
        String filename = basePath + "globals-" + getLanguage() + ".json";
        return getFolderLocation().resolve(filename);
    }
    
    public boolean isValid()
    {
        return Files.exists(getSourceFileLocation());
    }
    
    
    public StaticLoRCoreInfo loadData()
    {
        try
        {
            byte[]            content     = Files.readAllBytes(getSourceFileLocation());
            String            contentJSON = new String(content, StandardCharsets.UTF_8);
            StaticLoRCoreInfo cards       = Utils.getGson().fromJson(contentJSON, StaticLoRCoreInfo.class);
            return cards;
        } catch (IOException e)
        {
            e.printStackTrace();
            return null;
        }
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
        LoRCoreInstance that = (LoRCoreInstance) o;
        return Objects.equals(language, that.language) &&
               Objects.equals(folderLocation, that.folderLocation);
    }
    
    @Override
    public int hashCode()
    {
        return Objects.hash(language, folderLocation);
    }
    
    @Override
    public String toString()
    {
        return "LoRCoreInstance{" +
               ", language='" + language + '\'' +
               ", folderLocation=" + folderLocation +
               '}';
    }
}
