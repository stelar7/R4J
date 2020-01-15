package no.stelar7.api.r4j.tests.replay;

import no.stelar7.api.r4j.impl.lol.liveclient.LiveClientDataAPI;
import no.stelar7.api.r4j.impl.lol.replay.ReplayAPI;
import no.stelar7.api.r4j.pojo.lol.replay.ReplayTeamType;
import org.junit.*;

public class ReplayAPITest
{
    @Test
    @Ignore
    public void testReplay()
    {
        System.out.println(ReplayAPI.getParticleVisibility());
    }
    
    
    @Test
    @Ignore
    public void testLiveClient()
    {
        System.out.println(LiveClientDataAPI.getActivePlayer());
        System.out.println(LiveClientDataAPI.getActivePlayerAbilities());
        System.out.println(LiveClientDataAPI.getActivePlayerName());
        System.out.println(LiveClientDataAPI.getActivePlayerRunes());
        System.out.println(LiveClientDataAPI.getGameData());
        System.out.println(LiveClientDataAPI.getEventData());
        System.out.println(LiveClientDataAPI.getGameStats());
        System.out.println(LiveClientDataAPI.getPlayerList(ReplayTeamType.ALL));
        System.out.println(LiveClientDataAPI.getPlayerItems("stelar7"));
        System.out.println(LiveClientDataAPI.getPlayerMainRunes("stelar7"));
        System.out.println(LiveClientDataAPI.getPlayerScores("stelar7"));
        System.out.println(LiveClientDataAPI.getPlayerSummonerSpells("stelar7"));
    }
    
}
