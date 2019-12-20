package no.stelar7.api.l4j8.tests.replay;

import no.stelar7.api.l4j8.impl.lol.liveclient.LiveClientDataAPI;
import no.stelar7.api.l4j8.impl.lol.replay.*;
import no.stelar7.api.l4j8.pojo.replay.ReplayTeamType;
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
        System.out.println(LiveClientDataAPI.getEventData());
        System.out.println(LiveClientDataAPI.getGameStats());
        System.out.println(LiveClientDataAPI.getPlayerList(ReplayTeamType.ALL));
        System.out.println(LiveClientDataAPI.getPlayerItems("stelar7"));
        System.out.println(LiveClientDataAPI.getPlayerMainRunes("stelar7"));
        System.out.println(LiveClientDataAPI.getPlayerScores("stelar7"));
        System.out.println(LiveClientDataAPI.getPlayerSummonerSpells("stelar7"));
    }
    
}
