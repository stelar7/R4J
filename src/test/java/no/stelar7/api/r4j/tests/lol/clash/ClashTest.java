package no.stelar7.api.r4j.tests.lol.clash;

import no.stelar7.api.r4j.basic.constants.api.regions.LeagueShard;
import no.stelar7.api.r4j.impl.R4J;
import no.stelar7.api.r4j.impl.lol.raw.ClashAPI;
import no.stelar7.api.r4j.pojo.lol.clash.*;
import no.stelar7.api.r4j.pojo.lol.summoner.Summoner;
import no.stelar7.api.r4j.tests.SecretFile;
import org.junit.Test;

import java.util.List;

public class ClashTest
{
    final R4J      R4J = new R4J(SecretFile.CREDS);
    final ClashAPI api = R4J.getLoLAPI().getClashAPI();
    
    @Test
    public void testGetPlayerData()
    {
        List<ClashPlayer> stelar7 = api.getPlayerInfo(LeagueShard.EUW1, Summoner.byName(LeagueShard.EUW1, "stelar7").getSummonerId());
        System.out.println(stelar7);
    }
    
    @Test
    public void getTeams()
    {
        List<ClashPlayer> stelar7 = api.getPlayerInfo(LeagueShard.EUW1, Summoner.byName(LeagueShard.EUW1, "stelar7").getSummonerId());
        for (ClashPlayer clashPlayer : stelar7)
        {
            ClashTeam team = api.getTeam(LeagueShard.EUW1, clashPlayer.getTeamId());
            System.out.println(team);
        }
    }
    
    @Test
    public void testGetTournaments()
    {
        List<ClashTournament> tournaments = api.getTournaments(LeagueShard.EUW1);
        System.out.println(tournaments);
    }
    
    @Test
    public void testGetTournamentsByTeam()
    {
        List<ClashPlayer> stelar7 = api.getPlayerInfo(LeagueShard.EUW1, Summoner.byName(LeagueShard.EUW1, "stelar7").getSummonerId());
        for (ClashPlayer clashPlayer : stelar7)
        {
            ClashTeam       team             = api.getTeam(LeagueShard.EUW1, clashPlayer.getTeamId());
            ClashTournament tournamentByTeam = api.getTournamentByTeam(LeagueShard.EUW1, team.getId());
            System.out.println(tournamentByTeam);
        }
    }
    
    @Test
    public void testGetTournamentsById()
    {
        List<ClashPlayer> stelar7 = api.getPlayerInfo(LeagueShard.EUW1, Summoner.byName(LeagueShard.EUW1, "stelar7").getSummonerId());
        for (ClashPlayer clashPlayer : stelar7)
        {
            ClashTeam       team           = api.getTeam(LeagueShard.EUW1, clashPlayer.getTeamId());
            ClashTournament tournamentById = api.getTournamentById(LeagueShard.EUW1, team.getTournamentId());
            System.out.println(tournamentById);
        }
    }
    
    
}
