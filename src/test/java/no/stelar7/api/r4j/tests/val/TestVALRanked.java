package no.stelar7.api.r4j.tests.val;

import no.stelar7.api.r4j.basic.constants.api.regions.ValorantShard;
import no.stelar7.api.r4j.basic.constants.types.val.Season;
import no.stelar7.api.r4j.impl.R4J;
import no.stelar7.api.r4j.impl.val.VALRankedAPI;
import no.stelar7.api.r4j.pojo.val.ranked.Leaderboard;
import no.stelar7.api.r4j.tests.SecretFile;
import org.junit.jupiter.api.Test;

public class TestVALRanked
{
    @Test
    public void getLeaderboard()
    {
        R4J          api    = new R4J(SecretFile.CREDS);
        VALRankedAPI ranked = api.getVALAPI().getRankedAPI();
        
        Leaderboard leaderboard = ranked.getLeaderboard(ValorantShard.EU, Season.EPISODE_2_ACT_1.getActId(), 0, 200);
        System.out.println(leaderboard);
    }
}
