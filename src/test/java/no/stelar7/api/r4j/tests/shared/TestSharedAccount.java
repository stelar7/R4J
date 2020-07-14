package no.stelar7.api.r4j.tests.shared;

import no.stelar7.api.r4j.basic.constants.api.regions.*;
import no.stelar7.api.r4j.basic.constants.types.RealmSpesificEnum;
import no.stelar7.api.r4j.impl.R4J;
import no.stelar7.api.r4j.pojo.lol.summoner.Summoner;
import no.stelar7.api.r4j.pojo.shared.RiotAccount;
import no.stelar7.api.r4j.tests.SecretFile;
import org.junit.Test;

public class TestSharedAccount
{
    @Test
    public void getByPUUID()
    {
        R4J         api     = new R4J(SecretFile.CREDS);
        Summoner    stelar7 = Summoner.byName(LeagueShard.EUW1, "stelar7");
        RiotAccount account = api.getAccountAPI().getAccountByPUUID(RegionShard.EUROPE, stelar7.getPUUID());
        
        System.out.println(account);
    }
    
    @Test
    public void getByTag()
    {
        R4J         api     = new R4J(SecretFile.CREDS);
        RiotAccount account = api.getAccountAPI().getAccountByTag(RegionShard.EUROPE, "stelar7", "STL7");
        
        System.out.println(account);
    }
    
    @Test
    public void getShards()
    {
        R4J               api     = new R4J(SecretFile.CREDS);
        RiotAccount       account = api.getAccountAPI().getAccountByTag(RegionShard.EUROPE, "stelar7", "STL7");
        RealmSpesificEnum shard   = api.getAccountAPI().getActiveShard(RegionShard.EUROPE, ShardableGame.LOR, account.getPUUID());
        
        System.out.println(shard);
    }
}
