package no.stelar7.api.r4j.tests.shared;

import no.stelar7.api.r4j.basic.constants.api.regions.*;
import no.stelar7.api.r4j.basic.constants.types.ApiKeyType;
import no.stelar7.api.r4j.impl.R4J;
import no.stelar7.api.r4j.pojo.shared.*;
import no.stelar7.api.r4j.tests.SecretFile;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.*;

@TestMethodOrder(OrderAnnotation.class)
public class TestSharedAccount
{
    @Test
    @Order(1)
    public void getByTag()
    {
        R4J         api     = new R4J(SecretFile.CREDS);
        RiotAccount account = api.getAccountAPI().getAccountByTag(RegionShard.EUROPE, "stelar7", "STL7");
        
        System.out.println(account);
    }
    
    @Test
    @Order(2)
    public void getByPUUID()
    {
        R4J         api        = new R4J(SecretFile.CREDS);
        RiotAccount accountTag = api.getAccountAPI().getAccountByTag(RegionShard.EUROPE, "stelar7", "STL7");
        RiotAccount account    = api.getAccountAPI().getAccountByPUUID(RegionShard.EUROPE, accountTag.getPUUID());
        
        System.out.println(account);
    }
    
    @Test
    @Order(3)
    public void getShards()
    {
        R4J              api     = new R4J(SecretFile.CREDS);
        RiotAccount      account = api.getAccountAPI().getAccountByTag(RegionShard.EUROPE, "stelar7", "STL7");
        RiotAccountShard shard   = api.getAccountAPI().getActiveShard(RegionShard.AMERICAS, ShardableGame.LOR, account.getPUUID());
        
        System.out.println(shard);
        System.out.println(shard.getShardAsEnum());
    }
    
    @Test
    @Order(4)
    public void getByTagDifferentKeyType()
    {
        R4J         api     = new R4J(SecretFile.CREDS);
        RiotAccount accountLoL = api.getAccountAPI().getAccountByTag(RegionShard.EUROPE, "stelar7", "STL7", ApiKeyType.LOL);
        RiotAccount accountTfT = api.getAccountAPI().getAccountByTag(RegionShard.EUROPE, "stelar7", "STL7", ApiKeyType.TFT);
        
        System.out.println(accountLoL);
        System.out.println(accountTfT);
    }
}
