package no.stelar7.api.r4j.tests.lol.spectator;

import org.junit.jupiter.api.Test;

import no.stelar7.api.r4j.basic.constants.api.regions.LeagueShard;
import no.stelar7.api.r4j.basic.constants.api.regions.RegionShard;
import no.stelar7.api.r4j.basic.constants.types.ApiKeyType;
import no.stelar7.api.r4j.impl.R4J;
import no.stelar7.api.r4j.impl.lol.raw.SpectatorV5API;
import no.stelar7.api.r4j.pojo.lol.spectator.v5.SpectatorConnectionInformation;
import no.stelar7.api.r4j.pojo.shared.RiotAccount;
import no.stelar7.api.r4j.tests.SecretFile;

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
        RiotAccount    acc = r4J.getAccountAPI().getAccountByTag(RegionShard.EUROPE, "Incandescence33", "EUW", ApiKeyType.LOL);
        
        SpectatorConnectionInformation currentGame = api.getCurrentGame(LeagueShard.EUW1, acc.getPUUID());
        System.out.println(currentGame);
    }
}
