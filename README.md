##### L4J8 isn't endorsed by Riot Games and doesn't reflect the views or opinions of Riot Games or anyone officially involved in producing or managing League of Legends. League of Legends and Riot Games are trademarks or registered trademarks of Riot Games, Inc. League of Legends © Riot Games, Inc.

## TODO:
* Implement smooth ratelimiter
* Test to make sure that Tournament-V3 works as expected (need key for this...)
* Add remainder of E-sports API

http://api.lolesports.com/api/v1/navItems  
http://api.lolesports.com/api/v2/featuredTournamentOptions/en_US  
http://api.lolesports.com/api/v1/marquees?locale=en_US  
http://api.lolesports.com/api/v1/scheduleItems?leagueId={id}  
http://api.lolesports.com/api/v1/leagues/{id}  
http://api.lolesports.com/api/v2/highlanderMatchDetails?tournamentId={tournamentId}&matchId={matchid}  
http://api.lolesports.com/api/v2/tournamentPlayerStats?groupName={group}&tournamentId={tournamentid}  
http://api.lolesports.com/api/v1/teams?slug={slug}&tournament={tournamentid}  
http://api.lolesports.com/api/v1/players?slug={slug}&tournament={tournamentid}  




## Usage


```java 
L4J8 api = new L4J8(new APICredentials("apikey", APIType.NORMAL));
        
        
// First API call
Summoner stelar7 = api.getSummonerAPI().getSummonerByName(Platform.EUW1, "stelar7");
        
// Second API call
List<MatchReference> games = stelar7.getRankedGames();
        
MatchReference mostRecentGame = games.get(0);
        
// Third and fourth API call
Match         match    = mostRecentGame.getFullMatch();
MatchTimeline timeline = mostRecentGame.getTimeline();
        
System.out.format("Player '%s' played a game as '%s'%n", stelar7.getName(), mostRecentGame.getChampion().getFormattedName());
        
// Fifth API call
ChampionMastery mastery = stelar7.getChampionMastery(mostRecentGame.getChampion());
System.out.format("They have a masteryscore of %s%n", mastery.getChampionPoints());
        
Participant self   = match.getParticipantFromSummonerId(stelar7.getSummonerId());
boolean     didWin = match.didWin(self);
System.out.format("They %s the game%n%n", didWin ? "won" : "lost");
        
        
ParticipantIdentity opponentIdentity = match.getLaneOpponentIdentity(stelar7.getSummonerId());
Participant         opponent         = match.getParticipant(opponentIdentity.getParticipantId());
        
System.out.format("They laned against '%s' as '%s'%n", opponentIdentity.getPlayer().getSummonerName(), opponent.getChampion().getFormattedName());
        
        
List<MatchRune>    runes     = self.getRunes();
List<MatchMastery> masteries = self.getMasteries();
        
// Calls done with the static api does not count towards your ratelimit
StaticRuneList    runeData      = api.getStaticAPI().getRunes(Platform.EUW1, Optional.of(EnumSet.of(RuneDataFlags.ALL)), Optional.empty(), Optional.empty());
StaticMasteryList masteriesData = api.getStaticAPI().getMasteries(Platform.EUW1, Optional.of(EnumSet.of(MasteryDataFlags.ALL)), Optional.empty(), Optional.empty());
        
System.out.format("%nThey used the following runes:");
        
for (MatchRune rune : runes)
{
    String name = runeData.getData().get(rune.getRuneId()).getName();
    
    System.out.format("Name: '%s' Count: %s%n", name, rune.getRank());
}
        
System.out.format("%nThey used the following masteries:");
        
for (MatchMastery matchMastery : masteries)
{
    String name = masteriesData.getData().get(matchMastery.getMasteryId()).getName();
            
    System.out.format("Name: '%s' Level: %s%n", name, matchMastery.getRank());
}
        
        
// Sixth API call
List<LeaguePosition> leagueEntry = stelar7.getLeagueEntry();
        
for (LeaguePosition position : leagueEntry)
{
    System.out.format("%n%s (%s):%n", position.getLeagueName(), position.getQueueType());
    System.out.format("They are%sin a promo to %s%n", position.isInPromos() ? "" : " not ", position.getTierDivisionType().getNext());
    System.out.format("They have %sLP%n", position.getLeaguePoints());
}
        
// Seventh API call
List<LeagueList> fullLeague = stelar7.getFullLeague();
        
for (LeagueList leagueList : fullLeague)
{
    System.out.format("%n%s (%s):%n", leagueList.getLeagueName(), leagueList.getQueueType());
    System.out.format("There are %s players in the league%n", leagueList.getEntries().size());
}

```