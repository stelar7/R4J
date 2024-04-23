package no.stelar7.api.r4j.tests.lol.spectator;

import no.stelar7.api.r4j.basic.constants.api.regions.*;
import no.stelar7.api.r4j.impl.R4J;
import no.stelar7.api.r4j.impl.lol.raw.SpectatorAPI;
import no.stelar7.api.r4j.pojo.lol.spectator.SpectatorGameInfo;
import no.stelar7.api.r4j.pojo.shared.RiotAccount;
import no.stelar7.api.r4j.tests.SecretFile;
import org.junit.jupiter.api.Test;

public class SpectatorV5Test
{
    @Test
    public void testFeaturedGame()
    {
        final R4J    r4J = new R4J(SecretFile.CREDS);
        SpectatorAPI api = r4J.getLoLAPI().getSpectatorAPI();
        
        api.getFeaturedGames(LeagueShard.EUW1).forEach(System.out::println);
    }
    
    @Test
    public void testCurrentGame()
    {
        final R4J    r4J = new R4J(SecretFile.CREDS);
        SpectatorAPI api = r4J.getLoLAPI().getSpectatorAPI();
        RiotAccount  acc = r4J.getAccountAPI().getAccountByPUUID(RegionShard.EUROPE, "3p_vqgvTRkkynHiEG4Hcy1elPOpyo5x6kFqvTMOobIdwXcJFgSwSYcIL27m036TKAa1GBzzB-6CNpg");
        
        SpectatorGameInfo currentGame = api.getCurrentGame(LeagueShard.EUW1, acc.getPUUID());
        System.out.println(currentGame);
    }
}
