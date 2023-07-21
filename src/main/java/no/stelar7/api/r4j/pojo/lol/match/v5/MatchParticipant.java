package no.stelar7.api.r4j.pojo.lol.match.v5;

import no.stelar7.api.r4j.basic.constants.types.lol.*;

import java.io.Serializable;
import java.util.*;

public class MatchParticipant implements Serializable
{
    private static final long serialVersionUID = 921950638612403583L;
    
    private int                 assists;
    private int                 baronKills;
    private int                 bountyLevel;
    private Map<String, Object> challenges;
    private int                 champExperience;
    private int                 champLevel;
    private int                 championId;
    private String              championName;
    private int                 championTransform;
    private int                 consumablesPurchased;
    private int                 damageDealtToBuildings;
    private int                 damageDealtToObjectives;
    private int                 damageDealtToTurrets;
    private int                 damageSelfMitigated;
    private int                 deaths;
    private int                 detectorWardsPlaced;
    private int                 doubleKills;
    private int                 dragonKills;
    private boolean             firstBloodAssist;
    private boolean             firstBloodKill;
    private boolean             firstTowerAssist;
    private boolean             firstTowerKill;
    private boolean             gameEndedInEarlySurrender;
    private boolean             gameEndedInSurrender;
    private int                 goldEarned;
    private int                 goldSpent;
    private LaneType            individualPosition;
    private int                 inhibitorKills;
    private int                 inhibitorTakedowns;
    private int                 inhibitorsLost;
    private int                 item0;
    private int                 item1;
    private int                 item2;
    private int                 item3;
    private int                 item4;
    private int                 item5;
    private int                 item6;
    private int                 itemsPurchased;
    private int                 killingSprees;
    private int                 kills;
    private LaneType            lane;
    private int                 largestCriticalStrike;
    private int                 largestKillingSpree;
    private int                 largestMultiKill;
    private int                 longestTimeSpentLiving;
    private int                 magicDamageDealt;
    private int                 magicDamageDealtToChampions;
    private int                 magicDamageTaken;
    private int                 neutralMinionsKilled;
    private int                 nexusKills;
    private int                 nexusLost;
    private int                 nexusTakedowns;
    private int                 objectivesStolen;
    private int                 objectivesStolenAssists;
    private int                 participantId;
    private int                 pentaKills;
    private MatchPerks          perks;
    private int                 physicalDamageDealt;
    private int                 physicalDamageDealtToChampions;
    private int                 physicalDamageTaken;
    private int                 profileIcon;
    private String              puuid;
    private int                 quadraKills;
    private String              riotIdName;
    private String              riotIdTagline;
    private RoleType            role;
    private int                 sightWardsBoughtInGame;
    private int                 spell1Casts;
    private int                 summoner1Id;
    private int                 spell2Casts;
    private int                 summoner2Id;
    private int                 spell3Casts;
    private int                 spell4Casts;
    private int                 summoner1Casts;
    private int                 summoner2Casts;
    private String              summonerId;
    private int                 summonerLevel;
    private String              summonerName;
    private boolean             teamEarlySurrendered;
    private TeamType            teamId;
    private LaneType            teamPosition;
    private int                 timeCCingOthers;
    private int                 timePlayed;
    private int                 totalDamageDealt;
    private int                 totalDamageDealtToChampions;
    private int                 totalDamageShieldedOnTeammates;
    private int                 totalDamageTaken;
    private int                 totalHeal;
    private int                 totalHealsOnTeammates;
    private int                 totalMinionsKilled;
    private int                 totalTimeCCDealt;
    private int                 totalTimeSpentDead;
    private int                 totalUnitsHealed;
    private int                 tripleKills;
    private int                 trueDamageDealt;
    private int                 trueDamageDealtToChampions;
    private int                 trueDamageTaken;
    private int                 turretKills;
    private int                 turretTakedowns;
    private int                 turretsLost;
    private int                 unrealKills;
    private int                 visionScore;
    private int                 visionWardsBoughtInGame;
    private int                 wardsKilled;
    private int                 wardsPlaced;
    private boolean             win;
    
    //Arena Game Mode field
    
    private int playerSubteamId;
    
    public int getAssists()
    {
        return assists;
    }
    
    public int getBaronKills()
    {
        return baronKills;
    }
    
    public int getBountyLevel()
    {
        return bountyLevel;
    }
    
    public int getChampionExperience()
    {
        return champExperience;
    }
    
    public int getChampionLevel()
    {
        return champLevel;
    }
    
    public int getChampionId()
    {
        return championId;
    }
    
    public String getChampionName()
    {
        return championName;
    }
    
    public int getChampionTransform()
    {
        return championTransform;
    }
    
    public int getConsumablesPurchased()
    {
        return consumablesPurchased;
    }
    
    public int getDamageDealtToBuildings()
    {
        return damageDealtToBuildings;
    }
    
    public int getDamageDealtToObjectives()
    {
        return damageDealtToObjectives;
    }
    
    public int getDamageDealtToTurrets()
    {
        return damageDealtToTurrets;
    }
    
    public int getDamageSelfMitigated()
    {
        return damageSelfMitigated;
    }
    
    public int getDeaths()
    {
        return deaths;
    }
    
    public int getDetectorWardsPlaced()
    {
        return detectorWardsPlaced;
    }
    
    public int getDoubleKills()
    {
        return doubleKills;
    }
    
    public int getDragonKills()
    {
        return dragonKills;
    }
    
    public boolean isFirstBloodAssist()
    {
        return firstBloodAssist;
    }
    
    public boolean isFirstBloodKill()
    {
        return firstBloodKill;
    }
    
    public boolean isFirstTowerAssist()
    {
        return firstTowerAssist;
    }
    
    public boolean isFirstTowerKill()
    {
        return firstTowerKill;
    }
    
    public boolean isGameEndedInEarlySurrender()
    {
        return gameEndedInEarlySurrender;
    }
    
    public boolean isGameEndedInSurrender()
    {
        return gameEndedInSurrender;
    }
    
    public int getGoldEarned()
    {
        return goldEarned;
    }
    
    public int getGoldSpent()
    {
        return goldSpent;
    }
    
    public LaneType getGameDeterminedPosition()
    {
        return individualPosition;
    }
    
    public int getInhibitorKills()
    {
        return inhibitorKills;
    }
    
    public int getInhibitorTakedowns()
    {
        return inhibitorTakedowns;
    }
    
    public int getInhibitorsLost()
    {
        return inhibitorsLost;
    }
    
    public int getItem0()
    {
        return item0;
    }
    
    public int getItem1()
    {
        return item1;
    }
    
    public int getItem2()
    {
        return item2;
    }
    
    public int getItem3()
    {
        return item3;
    }
    
    public int getItem4()
    {
        return item4;
    }
    
    public int getItem5()
    {
        return item5;
    }
    
    public int getItem6()
    {
        return item6;
    }
    
    public int getItemsPurchased()
    {
        return itemsPurchased;
    }
    
    public int getKillingSprees()
    {
        return killingSprees;
    }
    
    public int getKills()
    {
        return kills;
    }
    
    public LaneType getLane()
    {
        return lane;
    }
    
    public int getLargestCriticalStrike()
    {
        return largestCriticalStrike;
    }
    
    public int getLargestKillingSpree()
    {
        return largestKillingSpree;
    }
    
    public int getLargestMultiKill()
    {
        return largestMultiKill;
    }
    
    public int getLongestTimeSpentLiving()
    {
        return longestTimeSpentLiving;
    }
    
    public int getMagicDamageDealt()
    {
        return magicDamageDealt;
    }
    
    public int getMagicDamageDealtToChampions()
    {
        return magicDamageDealtToChampions;
    }
    
    public int getMagicDamageTaken()
    {
        return magicDamageTaken;
    }
    
    public int getNeutralMinionsKilled()
    {
        return neutralMinionsKilled;
    }
    
    public int getNexusKills()
    {
        return nexusKills;
    }
    
    public int getNexusLost()
    {
        return nexusLost;
    }
    
    public int getNexusTakedowns()
    {
        return nexusTakedowns;
    }
    
    public int getObjectivesStolen()
    {
        return objectivesStolen;
    }
    
    public int getObjectivesStolenAssists()
    {
        return objectivesStolenAssists;
    }
    
    public int getParticipantId()
    {
        return participantId;
    }
    
    public int getPentaKills()
    {
        return pentaKills;
    }
    
    public MatchPerks getPerks()
    {
        return perks;
    }
    
    public int getPhysicalDamageDealt()
    {
        return physicalDamageDealt;
    }
    
    public int getPhysicalDamageDealtToChampions()
    {
        return physicalDamageDealtToChampions;
    }
    
    public int getPhysicalDamageTaken()
    {
        return physicalDamageTaken;
    }
    
    public int getProfileIcon()
    {
        return profileIcon;
    }
    
    public String getPuuid()
    {
        return puuid;
    }
    
    public int getQuadraKills()
    {
        return quadraKills;
    }
    
    public String getRiotIdName()
    {
        return riotIdName;
    }
    
    public String getRiotIdTagline()
    {
        return riotIdTagline;
    }
    
    public RoleType getRole()
    {
        return role;
    }
    
    public int getSightWardsBoughtInGame()
    {
        return sightWardsBoughtInGame;
    }
    
    public int getSpell1Casts()
    {
        return spell1Casts;
    }
    
    public int getSpell2Casts()
    {
        return spell2Casts;
    }
    
    public int getSpell3Casts()
    {
        return spell3Casts;
    }
    
    public int getSpell4Casts()
    {
        return spell4Casts;
    }
    
    public int getSummoner1Id()
    {
        return summoner1Id;
    }
    
    public int getSummoner1Casts()
    {
        return summoner1Casts;
    }
    
    public int getSummoner2Id()
    {
        return summoner2Id;
    }
    
    public int getSummoner2Casts()
    {
        return summoner2Casts;
    }
    
    public String getSummonerId()
    {
        return summonerId;
    }
    
    public int getSummonerLevel()
    {
        return summonerLevel;
    }
    
    public String getSummonerName()
    {
        return summonerName;
    }
    
    public boolean isTeamEarlySurrendered()
    {
        return teamEarlySurrendered;
    }
    
    public TeamType getTeam()
    {
        return teamId;
    }
    
    public LaneType getChampionSelectLane()
    {
        return teamPosition;
    }
    
    public int getTimeCCingOthers()
    {
        return timeCCingOthers;
    }
    
    public int getTimePlayed()
    {
        return timePlayed;
    }
    
    public int getTotalDamageDealt()
    {
        return totalDamageDealt;
    }
    
    public int getTotalDamageDealtToChampions()
    {
        return totalDamageDealtToChampions;
    }
    
    public int getTotalDamageShieldedOnTeammates()
    {
        return totalDamageShieldedOnTeammates;
    }
    
    public int getTotalDamageTaken()
    {
        return totalDamageTaken;
    }
    
    public int getTotalHeal()
    {
        return totalHeal;
    }
    
    public int getTotalHealsOnTeammates()
    {
        return totalHealsOnTeammates;
    }
    
    public int getTotalMinionsKilled()
    {
        return totalMinionsKilled;
    }
    
    public int getTotalTimeCCDealt()
    {
        return totalTimeCCDealt;
    }
    
    public int getTotalTimeSpentDead()
    {
        return totalTimeSpentDead;
    }
    
    public int getTotalUnitsHealed()
    {
        return totalUnitsHealed;
    }
    
    public int getTripleKills()
    {
        return tripleKills;
    }
    
    public int getTrueDamageDealt()
    {
        return trueDamageDealt;
    }
    
    public int getTrueDamageDealtToChampions()
    {
        return trueDamageDealtToChampions;
    }
    
    public int getTrueDamageTaken()
    {
        return trueDamageTaken;
    }
    
    public int getTurretKills()
    {
        return turretKills;
    }
    
    public int getTurretsLost()
    {
        return turretsLost;
    }
    
    public int getTurretTakedowns()
    {
        return turretTakedowns;
    }
    
    public int getUnrealKills()
    {
        return unrealKills;
    }
    
    public int getVisionScore()
    {
        return visionScore;
    }
    
    public int getVisionWardsBoughtInGame()
    {
        return visionWardsBoughtInGame;
    }
    
    public int getWardsKilled()
    {
        return wardsKilled;
    }
    
    public int getWardsPlaced()
    {
        return wardsPlaced;
    }
    
    public boolean didWin()
    {
        return win;
    }
    
    /**
     * Used to determine team in Arena Game Mode
     * @return team id. Can be 1-4. Return 0 if the game mode is not arena (cherry).
     */
    public int getArenaTeamId() {
		return playerSubteamId;
	}

	public Map<String, Object> getChallenges()
    {
        return challenges;
    }
    
    @Override
    public boolean equals(Object o)
    {
        if (this == o)
        {
            return true;
        }
        if (o == null || getClass() != o.getClass())
        {
            return false;
        }
        MatchParticipant that = (MatchParticipant) o;
        return assists == that.assists && baronKills == that.baronKills && bountyLevel == that.bountyLevel && champExperience == that.champExperience && champLevel == that.champLevel && championId == that.championId && championTransform == that.championTransform && consumablesPurchased == that.consumablesPurchased && damageDealtToBuildings == that.damageDealtToBuildings && damageDealtToObjectives == that.damageDealtToObjectives && damageDealtToTurrets == that.damageDealtToTurrets && damageSelfMitigated == that.damageSelfMitigated && deaths == that.deaths && detectorWardsPlaced == that.detectorWardsPlaced && doubleKills == that.doubleKills && dragonKills == that.dragonKills && firstBloodAssist == that.firstBloodAssist && firstBloodKill == that.firstBloodKill && firstTowerAssist == that.firstTowerAssist && firstTowerKill == that.firstTowerKill && gameEndedInEarlySurrender == that.gameEndedInEarlySurrender && gameEndedInSurrender == that.gameEndedInSurrender && goldEarned == that.goldEarned && goldSpent == that.goldSpent && inhibitorKills == that.inhibitorKills && inhibitorTakedowns == that.inhibitorTakedowns && inhibitorsLost == that.inhibitorsLost && item0 == that.item0 && item1 == that.item1 && item2 == that.item2 && item3 == that.item3 && item4 == that.item4 && item5 == that.item5 && item6 == that.item6 && itemsPurchased == that.itemsPurchased && killingSprees == that.killingSprees && kills == that.kills && largestCriticalStrike == that.largestCriticalStrike && largestKillingSpree == that.largestKillingSpree && largestMultiKill == that.largestMultiKill && longestTimeSpentLiving == that.longestTimeSpentLiving && magicDamageDealt == that.magicDamageDealt && magicDamageDealtToChampions == that.magicDamageDealtToChampions && magicDamageTaken == that.magicDamageTaken && neutralMinionsKilled == that.neutralMinionsKilled && nexusKills == that.nexusKills && nexusLost == that.nexusLost && nexusTakedowns == that.nexusTakedowns && objectivesStolen == that.objectivesStolen && objectivesStolenAssists == that.objectivesStolenAssists && participantId == that.participantId && pentaKills == that.pentaKills && physicalDamageDealt == that.physicalDamageDealt && physicalDamageDealtToChampions == that.physicalDamageDealtToChampions && physicalDamageTaken == that.physicalDamageTaken && profileIcon == that.profileIcon && quadraKills == that.quadraKills && sightWardsBoughtInGame == that.sightWardsBoughtInGame && spell1Casts == that.spell1Casts && summoner1Id == that.summoner1Id && spell2Casts == that.spell2Casts && summoner2Id == that.summoner2Id && spell3Casts == that.spell3Casts && spell4Casts == that.spell4Casts && summoner1Casts == that.summoner1Casts && summoner2Casts == that.summoner2Casts && summonerLevel == that.summonerLevel && teamEarlySurrendered == that.teamEarlySurrendered && timeCCingOthers == that.timeCCingOthers && timePlayed == that.timePlayed && totalDamageDealt == that.totalDamageDealt && totalDamageDealtToChampions == that.totalDamageDealtToChampions && totalDamageShieldedOnTeammates == that.totalDamageShieldedOnTeammates && totalDamageTaken == that.totalDamageTaken && totalHeal == that.totalHeal && totalHealsOnTeammates == that.totalHealsOnTeammates && totalMinionsKilled == that.totalMinionsKilled && totalTimeCCDealt == that.totalTimeCCDealt && totalTimeSpentDead == that.totalTimeSpentDead && totalUnitsHealed == that.totalUnitsHealed && tripleKills == that.tripleKills && trueDamageDealt == that.trueDamageDealt && trueDamageDealtToChampions == that.trueDamageDealtToChampions && trueDamageTaken == that.trueDamageTaken && turretKills == that.turretKills && turretTakedowns == that.turretTakedowns && turretsLost == that.turretsLost && unrealKills == that.unrealKills && visionScore == that.visionScore && visionWardsBoughtInGame == that.visionWardsBoughtInGame && wardsKilled == that.wardsKilled && wardsPlaced == that.wardsPlaced && win == that.win && Objects.equals(challenges, that.challenges) && Objects.equals(championName, that.championName) && individualPosition == that.individualPosition && lane == that.lane && Objects.equals(perks, that.perks) && Objects.equals(puuid, that.puuid) && Objects.equals(riotIdName, that.riotIdName) && Objects.equals(riotIdTagline, that.riotIdTagline) && role == that.role && Objects.equals(summonerId, that.summonerId) && Objects.equals(summonerName, that.summonerName) && teamId == that.teamId && teamPosition == that.teamPosition;
    }
    
    @Override
    public int hashCode()
    {
        return Objects.hash(assists, baronKills, bountyLevel, challenges, champExperience, champLevel, championId, championName, championTransform, consumablesPurchased, damageDealtToBuildings, damageDealtToObjectives, damageDealtToTurrets, damageSelfMitigated, deaths, detectorWardsPlaced, doubleKills, dragonKills, firstBloodAssist, firstBloodKill, firstTowerAssist, firstTowerKill, gameEndedInEarlySurrender, gameEndedInSurrender, goldEarned, goldSpent, individualPosition, inhibitorKills, inhibitorTakedowns, inhibitorsLost, item0, item1, item2, item3, item4, item5, item6, itemsPurchased, killingSprees, kills, lane, largestCriticalStrike, largestKillingSpree, largestMultiKill, longestTimeSpentLiving, magicDamageDealt, magicDamageDealtToChampions, magicDamageTaken, neutralMinionsKilled, nexusKills, nexusLost, nexusTakedowns, objectivesStolen, objectivesStolenAssists, participantId, pentaKills, perks, physicalDamageDealt, physicalDamageDealtToChampions, physicalDamageTaken, profileIcon, puuid, quadraKills, riotIdName, riotIdTagline, role, sightWardsBoughtInGame, spell1Casts, summoner1Id, spell2Casts, summoner2Id, spell3Casts, spell4Casts, summoner1Casts, summoner2Casts, summonerId, summonerLevel, summonerName, teamEarlySurrendered, teamId, teamPosition, timeCCingOthers, timePlayed, totalDamageDealt, totalDamageDealtToChampions, totalDamageShieldedOnTeammates, totalDamageTaken, totalHeal, totalHealsOnTeammates, totalMinionsKilled, totalTimeCCDealt, totalTimeSpentDead, totalUnitsHealed, tripleKills, trueDamageDealt, trueDamageDealtToChampions, trueDamageTaken, turretKills, turretTakedowns, turretsLost, unrealKills, visionScore, visionWardsBoughtInGame, wardsKilled, wardsPlaced, win);
    }
    
    @Override
    public String toString()
    {
        return "MatchParticipant{" +
               "assists=" + assists +
               ", baronKills=" + baronKills +
               ", bountyLevel=" + bountyLevel +
               ", challenges=" + challenges +
               ", champExperience=" + champExperience +
               ", champLevel=" + champLevel +
               ", championId=" + championId +
               ", championName='" + championName + '\'' +
               ", championTransform=" + championTransform +
               ", consumablesPurchased=" + consumablesPurchased +
               ", damageDealtToBuildings=" + damageDealtToBuildings +
               ", damageDealtToObjectives=" + damageDealtToObjectives +
               ", damageDealtToTurrets=" + damageDealtToTurrets +
               ", damageSelfMitigated=" + damageSelfMitigated +
               ", deaths=" + deaths +
               ", detectorWardsPlaced=" + detectorWardsPlaced +
               ", doubleKills=" + doubleKills +
               ", dragonKills=" + dragonKills +
               ", firstBloodAssist=" + firstBloodAssist +
               ", firstBloodKill=" + firstBloodKill +
               ", firstTowerAssist=" + firstTowerAssist +
               ", firstTowerKill=" + firstTowerKill +
               ", gameEndedInEarlySurrender=" + gameEndedInEarlySurrender +
               ", gameEndedInSurrender=" + gameEndedInSurrender +
               ", goldEarned=" + goldEarned +
               ", goldSpent=" + goldSpent +
               ", individualPosition=" + individualPosition +
               ", inhibitorKills=" + inhibitorKills +
               ", inhibitorTakedowns=" + inhibitorTakedowns +
               ", inhibitorsLost=" + inhibitorsLost +
               ", item0=" + item0 +
               ", item1=" + item1 +
               ", item2=" + item2 +
               ", item3=" + item3 +
               ", item4=" + item4 +
               ", item5=" + item5 +
               ", item6=" + item6 +
               ", itemsPurchased=" + itemsPurchased +
               ", killingSprees=" + killingSprees +
               ", kills=" + kills +
               ", lane=" + lane +
               ", largestCriticalStrike=" + largestCriticalStrike +
               ", largestKillingSpree=" + largestKillingSpree +
               ", largestMultiKill=" + largestMultiKill +
               ", longestTimeSpentLiving=" + longestTimeSpentLiving +
               ", magicDamageDealt=" + magicDamageDealt +
               ", magicDamageDealtToChampions=" + magicDamageDealtToChampions +
               ", magicDamageTaken=" + magicDamageTaken +
               ", neutralMinionsKilled=" + neutralMinionsKilled +
               ", nexusKills=" + nexusKills +
               ", nexusLost=" + nexusLost +
               ", nexusTakedowns=" + nexusTakedowns +
               ", objectivesStolen=" + objectivesStolen +
               ", objectivesStolenAssists=" + objectivesStolenAssists +
               ", participantId=" + participantId +
               ", pentaKills=" + pentaKills +
               ", perks=" + perks +
               ", physicalDamageDealt=" + physicalDamageDealt +
               ", physicalDamageDealtToChampions=" + physicalDamageDealtToChampions +
               ", physicalDamageTaken=" + physicalDamageTaken +
               ", profileIcon=" + profileIcon +
               ", puuid='" + puuid + '\'' +
               ", quadraKills=" + quadraKills +
               ", riotIdName='" + riotIdName + '\'' +
               ", riotIdTagline='" + riotIdTagline + '\'' +
               ", role=" + role +
               ", sightWardsBoughtInGame=" + sightWardsBoughtInGame +
               ", spell1Casts=" + spell1Casts +
               ", summoner1Id=" + summoner1Id +
               ", spell2Casts=" + spell2Casts +
               ", summoner2Id=" + summoner2Id +
               ", spell3Casts=" + spell3Casts +
               ", spell4Casts=" + spell4Casts +
               ", summoner1Casts=" + summoner1Casts +
               ", summoner2Casts=" + summoner2Casts +
               ", summonerId='" + summonerId + '\'' +
               ", summonerLevel=" + summonerLevel +
               ", summonerName='" + summonerName + '\'' +
               ", teamEarlySurrendered=" + teamEarlySurrendered +
               ", teamId=" + teamId +
               ", teamPosition=" + teamPosition +
               ", timeCCingOthers=" + timeCCingOthers +
               ", timePlayed=" + timePlayed +
               ", totalDamageDealt=" + totalDamageDealt +
               ", totalDamageDealtToChampions=" + totalDamageDealtToChampions +
               ", totalDamageShieldedOnTeammates=" + totalDamageShieldedOnTeammates +
               ", totalDamageTaken=" + totalDamageTaken +
               ", totalHeal=" + totalHeal +
               ", totalHealsOnTeammates=" + totalHealsOnTeammates +
               ", totalMinionsKilled=" + totalMinionsKilled +
               ", totalTimeCCDealt=" + totalTimeCCDealt +
               ", totalTimeSpentDead=" + totalTimeSpentDead +
               ", totalUnitsHealed=" + totalUnitsHealed +
               ", tripleKills=" + tripleKills +
               ", trueDamageDealt=" + trueDamageDealt +
               ", trueDamageDealtToChampions=" + trueDamageDealtToChampions +
               ", trueDamageTaken=" + trueDamageTaken +
               ", turretKills=" + turretKills +
               ", turretTakedowns=" + turretTakedowns +
               ", turretsLost=" + turretsLost +
               ", unrealKills=" + unrealKills +
               ", visionScore=" + visionScore +
               ", visionWardsBoughtInGame=" + visionWardsBoughtInGame +
               ", wardsKilled=" + wardsKilled +
               ", wardsPlaced=" + wardsPlaced +
               ", win=" + win +
               '}';
    }
}
