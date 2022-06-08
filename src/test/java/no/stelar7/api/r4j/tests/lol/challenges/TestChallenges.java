package no.stelar7.api.r4j.tests.lol.challenges;

import no.stelar7.api.r4j.basic.constants.api.regions.LeagueShard;
import no.stelar7.api.r4j.basic.constants.types.lol.ChallengeTierType;
import no.stelar7.api.r4j.impl.R4J;
import no.stelar7.api.r4j.impl.lol.raw.ChallengeAPI;
import no.stelar7.api.r4j.pojo.lol.challenges.*;
import no.stelar7.api.r4j.pojo.lol.summoner.Summoner;
import no.stelar7.api.r4j.tests.SecretFile;
import org.junit.jupiter.api.Test;

public class TestChallenges
{
    final R4J          r4J = new R4J(SecretFile.CREDS);
    final ChallengeAPI api = r4J.getLoLAPI().getChallengeAPI();
    
    @Test
    public void testGetChallengesList()
    {
        ChallengeConfigInfoList challengesList = api.getChallengesList(LeagueShard.EUW1);
    }
    
    @Test
    public void testGetAllChallengePercentiles()
    {
        MultiChallengePercentileMap allChallengePercentiles = api.getAllChallengePercentiles(LeagueShard.EUW1);
    }
    
    @Test
    public void testGetChallengeInfo()
    {
        ChallengeConfigInfo challengeInfo = api.getChallengeInfo(LeagueShard.EUW1, 1L);
    }
    
    @Test
    public void testGetChallengeLeaderboard()
    {
        ChallengeLeaderboardInfoList challengeLeaderboard = api.getChallengeLeaderboard(LeagueShard.EUW1, 1L, ChallengeTierType.MASTER, null);
    }
    
    @Test
    public void testGetChallengePercentiles()
    {
        MultiChallengePercentileMap challengePercentiles = api.getChallengePercentiles(LeagueShard.EUW1, 1L);
    }
    
    @Test
    public void testGetChallengePlayerInfo()
    {
        Summoner            summoner            = Summoner.byName(LeagueShard.EUW1, "stelar7");
        ChallengePlayerInfo challengePlayerInfo = api.getChallengePlayerInfo(LeagueShard.EUW1, summoner.getPUUID());
    }
}
