package no.stelar7.api.r4j.tests.lol.spectator;

import no.stelar7.api.r4j.basic.constants.api.regions.LeagueShard;
import no.stelar7.api.r4j.impl.R4J;
import no.stelar7.api.r4j.impl.lol.raw.SpectatorV5API;
import no.stelar7.api.r4j.pojo.lol.spectator.v5.SpectatorConnectionInformation;
import no.stelar7.api.r4j.pojo.lol.summoner.Summoner;
import no.stelar7.api.r4j.tests.SecretFile;
import org.junit.jupiter.api.Test;

public class SpectatorV5Test
{
    @Test
    public void testFeaturedGame()
    {
        final R4J      r4J = new R4J(SecretFile.CREDS);
        SpectatorV5API api = r4J.getLoLAPI().getSpectatorV5API();
        
        api.getFeaturedGames(LeagueShard.EUW1).forEach(System.out::println);
    }
    
    @Test
    public void testCurrentGame()
    {
        final R4J      r4J = new R4J(SecretFile.CREDS);
        SpectatorV5API api = r4J.getLoLAPI().getSpectatorV5API();
        Summoner       sum = Summoner.byName(LeagueShard.EUW1, "stelar7");
        
        SpectatorConnectionInformation currentGame = api.getCurrentGame(LeagueShard.EUW1, sum.getPUUID());
        System.out.println(currentGame);
    }
}
