package no.stelar7.api.r4j.pojo.shared;

import com.google.gson.JsonPrimitive;
import no.stelar7.api.r4j.basic.constants.api.regions.*;
import no.stelar7.api.r4j.basic.constants.types.RealmSpesificEnum;
import no.stelar7.api.r4j.basic.exceptions.APIEnumNotUpToDateException;

import java.io.Serializable;
import java.util.Objects;

public class RiotAccountShard implements Serializable
{
    private String puuid;
    private String game;
    private String activeShard;
    
    public String getPuuid()
    {
        return puuid;
    }
    
    public String getGame()
    {
        return game;
    }
    
    public String getActiveShard()
    {
        return activeShard;
    }
    
    public RealmSpesificEnum getShardAsEnum()
    {
        if (game.equalsIgnoreCase("val"))
        {
            return ValorantShard.getFromCode(this.activeShard).orElseThrow(() -> new APIEnumNotUpToDateException(ValorantShard.class, new JsonPrimitive(this.activeShard)));
        }
        if (game.equalsIgnoreCase("lor"))
        {
            return RuneterraShard.getFromCode(this.activeShard).orElseThrow(() -> new APIEnumNotUpToDateException(RuneterraShard.class, new JsonPrimitive(this.activeShard)));
        }
        
        return null;
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
        RiotAccountShard that = (RiotAccountShard) o;
        return Objects.equals(puuid, that.puuid) &&
               Objects.equals(game, that.game) &&
               Objects.equals(activeShard, that.activeShard);
    }
    
    @Override
    public int hashCode()
    {
        return Objects.hash(puuid, game, activeShard);
    }
    
    @Override
    public String toString()
    {
        return "RiotAccountShard{" +
               "puuid='" + puuid + '\'' +
               ", game='" + game + '\'' +
               ", activeShard='" + activeShard + '\'' +
               '}';
    }
}
