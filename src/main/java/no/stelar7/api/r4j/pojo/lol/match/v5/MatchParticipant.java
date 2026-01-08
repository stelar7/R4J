package no.stelar7.api.r4j.pojo.lol.match.v5;

import no.stelar7.api.r4j.basic.constants.types.lol.*;

import java.io.Serializable;
import java.util.*;

public class MatchParticipant implements Serializable
{
    private static final long serialVersionUID = -747077395713063069L;
    
    private int                 allInPings;
    private int                 assistMePings;
    private int                 assists;
    private int                 baitPings;
    private int                 baronKills;
    private int                 basicPings;
    private int                 bountyLevel;
    private Map<String, Object> challenges;
    private int                 champExperience;
    private int                 champLevel;
    private int                 championId;
    private String              championName;
    private int                 championTransform;
    private int                 commandPings;
    private int                 consumablesPurchased;
    private int                 damageDealtToBuildings;
    private int                 damageDealtToObjectives;
    private int                 damageDealtToTurrets;
    private int                 damageSelfMitigated;
    private int                 dangerPings;
    private int                 deaths;
    private int                 detectorWardsPlaced;
    private int                 doubleKills;
    private int                 dragonKills;
    private boolean             eligibleForProgression;
    private int                 enemyMissingPings;
    private int                 enemyVisionPings;
    private boolean             firstBloodAssist;
    private boolean             firstBloodKill;
    private boolean             firstTowerAssist;
    private boolean             firstTowerKill;
    private boolean             gameEndedInEarlySurrender;
    private boolean             gameEndedInSurrender;
    private int                 getBackPings;
    private int                 goldEarned;
    private int                 goldSpent;
    private int                 holdPings;
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
    private int                 needVisionPings;
    private int                 neutralMinionsKilled;
    private int                 nexusKills;
    private int                 nexusLost;
    private int                 nexusTakedowns;
    private int                 objectivesStolen;
    private int                 objectivesStolenAssists;
    private int                 onMyWayPings;
    private int                 participantId;
    private int                 pentaKills;
    private MatchPerks          perks;
    private int                 physicalDamageDealt;
    private int                 physicalDamageDealtToChampions;
    private int                 physicalDamageTaken;
    private int                 placement;
    private int                 playerAugment1;
    private int                 playerAugment2;
    private int                 playerAugment3;
    private int                 playerAugment4;
    private int                 playerSubteamId;
    private int                 profileIcon;
    private int                 pushPings;
    private String              puuid;
    private int                 quadraKills;
    private String              riotIdName;
    private String              riotIdGameName;
    private String              riotIdTagline;
    private RoleType            role;
    private int                 roleBoundItem;
    private int                 sightWardsBoughtInGame;
    private int                 spell1Casts;
    private int                 spell2Casts;
    private int                 spell3Casts;
    private int                 spell4Casts;
    private int                 subteamPlacement;
    private int                 summoner1Casts;
    private int                 summoner1Id;
    private int                 summoner2Casts;
    private int                 summoner2Id;
    private String              summonerId;
    private int                 summonerLevel;
    private boolean             teamEarlySurrendered;
    private TeamType            teamId;
    private LaneType            teamPosition;
    private int                 timeCCingOthers;
    private int                 timePlayed;
    private int                 totalAllyJungleMinionsKilled;
    private int                 totalDamageDealt;
    private int                 totalDamageDealtToChampions;
    private int                 totalDamageShieldedOnTeammates;
    private int                 totalDamageTaken;
    private int                 totalEnemyJungleMinionsKilled;
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
    private int                 visionClearedPings;
    private int                 visionScore;
    private int                 visionWardsBoughtInGame;
    private int                 wardsKilled;
    private int                 wardsPlaced;
    private boolean             win;
    
    public int getAllInPings()
    {
        return allInPings;
    }
    
    public int getAssistMePings()
    {
        return assistMePings;
    }
    
    public int getAssists()
    {
        return assists;
    }
    
    public int getBaitPings()
    {
        return baitPings;
    }
    
    public int getBaronKills()
    {
        return baronKills;
    }
    
    public int getBasicPings()
    {
        return basicPings;
    }
    
    public int getBountyLevel()
    {
        return bountyLevel;
    }
    
    public Map<String, Object> getChallenges()
    {
        return challenges;
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
    
    public int getCommandPings()
    {
        return commandPings;
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
    
    public int getDangerPings()
    {
        return dangerPings;
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
    
    public boolean isEligibleForProgression()
    {
        return eligibleForProgression;
    }
    
    public int getEnemyMissingPings()
    {
        return enemyMissingPings;
    }
    
    public int getEnemyVisionPings()
    {
        return enemyVisionPings;
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
    
    public int getGetBackPings()
    {
        return getBackPings;
    }
    
    public int getGoldEarned()
    {
        return goldEarned;
    }
    
    public int getGoldSpent()
    {
        return goldSpent;
    }
    
    public int getHoldPings()
    {
        return holdPings;
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
    
    public int getNeedVisionPings()
    {
        return needVisionPings;
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
    
    public int getOnMyWayPings()
    {
        return onMyWayPings;
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
    
    public int getPlacement()
    {
        return placement;
    }
    
    public int getPlayerAugment1()
    {
        return playerAugment1;
    }
    
    public int getPlayerAugment2()
    {
        return playerAugment2;
    }
    
    public int getPlayerAugment3()
    {
        return playerAugment3;
    }
    
    public int getPlayerAugment4()
    {
        return playerAugment4;
    }
    
    public int getPlayerSubteamId()
    {
        return playerSubteamId;
    }
    
    public int getProfileIcon()
    {
        return profileIcon;
    }
    
    public int getPushPings()
    {
        return pushPings;
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
        if (riotIdName == null)
        {
            return riotIdGameName;
        }
        
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

    public int getRoleBoundItem()
    {
        return roleBoundItem;
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
    
    public int getSubteamPlacement()
    {
        return subteamPlacement;
    }
    
    public int getSummoner1Casts()
    {
        return summoner1Casts;
    }
    
    public int getSummoner1Id()
    {
        return summoner1Id;
    }
    
    public int getSummoner2Casts()
    {
        return summoner2Casts;
    }
    
    public int getSummoner2Id()
    {
        return summoner2Id;
    }
    
    public String getSummonerId()
    {
        return summonerId;
    }
    
    public int getSummonerLevel()
    {
        return summonerLevel;
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
    
    public int getTotalAllyJungleMinionsKilled()
    {
        return totalAllyJungleMinionsKilled;
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
    
    public int getTotalEnemyJungleMinionsKilled()
    {
        return totalEnemyJungleMinionsKilled;
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
    
    public int getTurretTakedowns()
    {
        return turretTakedowns;
    }
    
    public int getTurretsLost()
    {
        return turretsLost;
    }
    
    public int getUnrealKills()
    {
        return unrealKills;
    }
    
    public int getVisionClearedPings()
    {
        return visionClearedPings;
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
        return allInPings == that.allInPings
               && assistMePings == that.assistMePings
               && assists == that.assists
               && baitPings == that.baitPings
               && baronKills == that.baronKills
               && basicPings == that.basicPings
               && bountyLevel == that.bountyLevel
               && champExperience == that.champExperience
               && champLevel == that.champLevel
               && championId == that.championId
               && championTransform == that.championTransform
               && commandPings == that.commandPings
               && consumablesPurchased == that.consumablesPurchased
               && damageDealtToBuildings == that.damageDealtToBuildings
               && damageDealtToObjectives == that.damageDealtToObjectives
               && damageDealtToTurrets == that.damageDealtToTurrets
               && damageSelfMitigated == that.damageSelfMitigated
               && dangerPings == that.dangerPings
               && deaths == that.deaths
               && detectorWardsPlaced == that.detectorWardsPlaced
               && doubleKills == that.doubleKills
               && dragonKills == that.dragonKills
               && eligibleForProgression == that.eligibleForProgression
               && enemyMissingPings == that.enemyMissingPings
               && enemyVisionPings == that.enemyVisionPings
               && firstBloodAssist == that.firstBloodAssist
               && firstBloodKill == that.firstBloodKill
               && firstTowerAssist == that.firstTowerAssist
               && firstTowerKill == that.firstTowerKill
               && gameEndedInEarlySurrender == that.gameEndedInEarlySurrender
               && gameEndedInSurrender == that.gameEndedInSurrender
               && getBackPings == that.getBackPings
               && goldEarned == that.goldEarned
               && goldSpent == that.goldSpent
               && holdPings == that.holdPings
               && inhibitorKills == that.inhibitorKills
               && inhibitorTakedowns == that.inhibitorTakedowns
               && inhibitorsLost == that.inhibitorsLost
               && item0 == that.item0
               && item1 == that.item1
               && item2 == that.item2
               && item3 == that.item3
               && item4 == that.item4
               && item5 == that.item5
               && item6 == that.item6
               && itemsPurchased == that.itemsPurchased
               && killingSprees == that.killingSprees
               && kills == that.kills
               && largestCriticalStrike == that.largestCriticalStrike
               && largestKillingSpree == that.largestKillingSpree
               && largestMultiKill == that.largestMultiKill
               && longestTimeSpentLiving == that.longestTimeSpentLiving
               && magicDamageDealt == that.magicDamageDealt
               && magicDamageDealtToChampions == that.magicDamageDealtToChampions
               && magicDamageTaken == that.magicDamageTaken
               && needVisionPings == that.needVisionPings
               && neutralMinionsKilled == that.neutralMinionsKilled
               && nexusKills == that.nexusKills
               && nexusLost == that.nexusLost
               && nexusTakedowns == that.nexusTakedowns
               && objectivesStolen == that.objectivesStolen
               && objectivesStolenAssists == that.objectivesStolenAssists
               && onMyWayPings == that.onMyWayPings
               && participantId == that.participantId
               && pentaKills == that.pentaKills
               && physicalDamageDealt == that.physicalDamageDealt
               && physicalDamageDealtToChampions == that.physicalDamageDealtToChampions
               && physicalDamageTaken == that.physicalDamageTaken
               && placement == that.placement
               && playerAugment1 == that.playerAugment1
               && playerAugment2 == that.playerAugment2
               && playerAugment3 == that.playerAugment3
               && playerAugment4 == that.playerAugment4
               && playerSubteamId == that.playerSubteamId
               && profileIcon == that.profileIcon
               && pushPings == that.pushPings
               && quadraKills == that.quadraKills
               && sightWardsBoughtInGame == that.sightWardsBoughtInGame
               && spell1Casts == that.spell1Casts
               && spell2Casts == that.spell2Casts
               && spell3Casts == that.spell3Casts
               && spell4Casts == that.spell4Casts
               && subteamPlacement == that.subteamPlacement
               && summoner1Casts == that.summoner1Casts
               && summoner1Id == that.summoner1Id
               && summoner2Casts == that.summoner2Casts
               && summoner2Id == that.summoner2Id
               && summonerLevel == that.summonerLevel
               && teamEarlySurrendered == that.teamEarlySurrendered
               && timeCCingOthers == that.timeCCingOthers
               && timePlayed == that.timePlayed
               && totalAllyJungleMinionsKilled == that.totalAllyJungleMinionsKilled
               && totalDamageDealt == that.totalDamageDealt
               && totalDamageDealtToChampions == that.totalDamageDealtToChampions
               && totalDamageShieldedOnTeammates == that.totalDamageShieldedOnTeammates
               && totalDamageTaken == that.totalDamageTaken
               && totalEnemyJungleMinionsKilled == that.totalEnemyJungleMinionsKilled
               && totalHeal == that.totalHeal
               && totalHealsOnTeammates == that.totalHealsOnTeammates
               && totalMinionsKilled == that.totalMinionsKilled
               && totalTimeCCDealt == that.totalTimeCCDealt
               && totalTimeSpentDead == that.totalTimeSpentDead
               && totalUnitsHealed == that.totalUnitsHealed
               && tripleKills == that.tripleKills
               && trueDamageDealt == that.trueDamageDealt
               && trueDamageDealtToChampions == that.trueDamageDealtToChampions
               && trueDamageTaken == that.trueDamageTaken
               && turretKills == that.turretKills
               && turretTakedowns == that.turretTakedowns
               && turretsLost == that.turretsLost
               && unrealKills == that.unrealKills
               && visionClearedPings == that.visionClearedPings
               && visionScore == that.visionScore
               && visionWardsBoughtInGame == that.visionWardsBoughtInGame
               && wardsKilled == that.wardsKilled
               && wardsPlaced == that.wardsPlaced
               && win == that.win
               && Objects.equals(challenges, that.challenges)
               && Objects.equals(championName, that.championName)
               && individualPosition == that.individualPosition
               && lane == that.lane
               && Objects.equals(perks, that.perks)
               && Objects.equals(puuid, that.puuid)
               && Objects.equals(riotIdName, that.riotIdName)
               && Objects.equals(riotIdGameName, that.riotIdGameName)
               && Objects.equals(riotIdTagline, that.riotIdTagline)
               && role == that.role
               && roleBoundItem == that.roleBoundItem
               && Objects.equals(summonerId, that.summonerId)
               && teamId == that.teamId
               && teamPosition == that.teamPosition;
    }
    
    @Override
    public int hashCode()
    {
        return Objects.hash(allInPings,
                            assistMePings,
                            assists,
                            baitPings,
                            baronKills,
                            basicPings,
                            bountyLevel,
                            challenges,
                            champExperience,
                            champLevel,
                            championId,
                            championName,
                            championTransform,
                            commandPings,
                            consumablesPurchased,
                            damageDealtToBuildings,
                            damageDealtToObjectives,
                            damageDealtToTurrets,
                            damageSelfMitigated,
                            dangerPings,
                            deaths,
                            detectorWardsPlaced,
                            doubleKills,
                            dragonKills,
                            eligibleForProgression,
                            enemyMissingPings,
                            enemyVisionPings,
                            firstBloodAssist,
                            firstBloodKill,
                            firstTowerAssist,
                            firstTowerKill,
                            gameEndedInEarlySurrender,
                            gameEndedInSurrender,
                            getBackPings,
                            goldEarned,
                            goldSpent,
                            holdPings,
                            individualPosition,
                            inhibitorKills,
                            inhibitorTakedowns,
                            inhibitorsLost,
                            item0,
                            item1,
                            item2,
                            item3,
                            item4,
                            item5,
                            item6,
                            itemsPurchased,
                            killingSprees,
                            kills,
                            lane,
                            largestCriticalStrike,
                            largestKillingSpree,
                            largestMultiKill,
                            longestTimeSpentLiving,
                            magicDamageDealt,
                            magicDamageDealtToChampions,
                            magicDamageTaken,
                            needVisionPings,
                            neutralMinionsKilled,
                            nexusKills,
                            nexusLost,
                            nexusTakedowns,
                            objectivesStolen,
                            objectivesStolenAssists,
                            onMyWayPings,
                            participantId,
                            pentaKills,
                            perks,
                            physicalDamageDealt,
                            physicalDamageDealtToChampions,
                            physicalDamageTaken,
                            placement,
                            playerAugment1,
                            playerAugment2,
                            playerAugment3,
                            playerAugment4,
                            playerSubteamId,
                            profileIcon,
                            pushPings,
                            puuid,
                            quadraKills,
                            riotIdName,
                            riotIdGameName,
                            riotIdTagline,
                            role,
                            roleBoundItem,
                            sightWardsBoughtInGame,
                            spell1Casts,
                            spell2Casts,
                            spell3Casts,
                            spell4Casts,
                            subteamPlacement,
                            summoner1Casts,
                            summoner1Id,
                            summoner2Casts,
                            summoner2Id,
                            summonerId,
                            summonerLevel,
                            teamEarlySurrendered,
                            teamId,
                            teamPosition,
                            timeCCingOthers,
                            timePlayed,
                            totalAllyJungleMinionsKilled,
                            totalDamageDealt,
                            totalDamageDealtToChampions,
                            totalDamageShieldedOnTeammates,
                            totalDamageTaken,
                            totalEnemyJungleMinionsKilled,
                            totalHeal,
                            totalHealsOnTeammates,
                            totalMinionsKilled,
                            totalTimeCCDealt,
                            totalTimeSpentDead,
                            totalUnitsHealed,
                            tripleKills,
                            trueDamageDealt,
                            trueDamageDealtToChampions,
                            trueDamageTaken,
                            turretKills,
                            turretTakedowns,
                            turretsLost,
                            unrealKills,
                            visionClearedPings,
                            visionScore,
                            visionWardsBoughtInGame,
                            wardsKilled,
                            wardsPlaced,
                            win);
    }
    
    @Override
    public String toString()
    {
        return "MatchParticipant{" +
               "allInPings=" + allInPings +
               ", assistMePings=" + assistMePings +
               ", assists=" + assists +
               ", baitPings=" + baitPings +
               ", baronKills=" + baronKills +
               ", basicPings=" + basicPings +
               ", bountyLevel=" + bountyLevel +
               ", challenges=" + challenges +
               ", champExperience=" + champExperience +
               ", champLevel=" + champLevel +
               ", championId=" + championId +
               ", championName='" + championName + '\'' +
               ", championTransform=" + championTransform +
               ", commandPings=" + commandPings +
               ", consumablesPurchased=" + consumablesPurchased +
               ", damageDealtToBuildings=" + damageDealtToBuildings +
               ", damageDealtToObjectives=" + damageDealtToObjectives +
               ", damageDealtToTurrets=" + damageDealtToTurrets +
               ", damageSelfMitigated=" + damageSelfMitigated +
               ", dangerPings=" + dangerPings +
               ", deaths=" + deaths +
               ", detectorWardsPlaced=" + detectorWardsPlaced +
               ", doubleKills=" + doubleKills +
               ", dragonKills=" + dragonKills +
               ", eligibleForProgression=" + eligibleForProgression +
               ", enemyMissingPings=" + enemyMissingPings +
               ", enemyVisionPings=" + enemyVisionPings +
               ", firstBloodAssist=" + firstBloodAssist +
               ", firstBloodKill=" + firstBloodKill +
               ", firstTowerAssist=" + firstTowerAssist +
               ", firstTowerKill=" + firstTowerKill +
               ", gameEndedInEarlySurrender=" + gameEndedInEarlySurrender +
               ", gameEndedInSurrender=" + gameEndedInSurrender +
               ", getBackPings=" + getBackPings +
               ", goldEarned=" + goldEarned +
               ", goldSpent=" + goldSpent +
               ", holdPings=" + holdPings +
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
               ", needVisionPings=" + needVisionPings +
               ", neutralMinionsKilled=" + neutralMinionsKilled +
               ", nexusKills=" + nexusKills +
               ", nexusLost=" + nexusLost +
               ", nexusTakedowns=" + nexusTakedowns +
               ", objectivesStolen=" + objectivesStolen +
               ", objectivesStolenAssists=" + objectivesStolenAssists +
               ", onMyWayPings=" + onMyWayPings +
               ", participantId=" + participantId +
               ", pentaKills=" + pentaKills +
               ", perks=" + perks +
               ", physicalDamageDealt=" + physicalDamageDealt +
               ", physicalDamageDealtToChampions=" + physicalDamageDealtToChampions +
               ", physicalDamageTaken=" + physicalDamageTaken +
               ", placement=" + placement +
               ", playerAugment1=" + playerAugment1 +
               ", playerAugment2=" + playerAugment2 +
               ", playerAugment3=" + playerAugment3 +
               ", playerAugment4=" + playerAugment4 +
               ", playerSubteamId=" + playerSubteamId +
               ", profileIcon=" + profileIcon +
               ", pushPings=" + pushPings +
               ", puuid='" + puuid + '\'' +
               ", quadraKills=" + quadraKills +
               ", riotIdName='" + riotIdName + '\'' +
               ", riotIdGameName='" + riotIdGameName + '\'' +
               ", riotIdTagline='" + riotIdTagline + '\'' +
               ", role=" + role +
               ", roleBoundItem=" + roleBoundItem + 
               ", sightWardsBoughtInGame=" + sightWardsBoughtInGame +
               ", spell1Casts=" + spell1Casts +
               ", spell2Casts=" + spell2Casts +
               ", spell3Casts=" + spell3Casts +
               ", spell4Casts=" + spell4Casts +
               ", subteamPlacement=" + subteamPlacement +
               ", summoner1Casts=" + summoner1Casts +
               ", summoner1Id=" + summoner1Id +
               ", summoner2Casts=" + summoner2Casts +
               ", summoner2Id=" + summoner2Id +
               ", summonerId='" + summonerId + '\'' +
               ", summonerLevel=" + summonerLevel +
               ", teamEarlySurrendered=" + teamEarlySurrendered +
               ", teamId=" + teamId +
               ", teamPosition=" + teamPosition +
               ", timeCCingOthers=" + timeCCingOthers +
               ", timePlayed=" + timePlayed +
               ", totalAllyJungleMinionsKilled=" + totalAllyJungleMinionsKilled +
               ", totalDamageDealt=" + totalDamageDealt +
               ", totalDamageDealtToChampions=" + totalDamageDealtToChampions +
               ", totalDamageShieldedOnTeammates=" + totalDamageShieldedOnTeammates +
               ", totalDamageTaken=" + totalDamageTaken +
               ", totalEnemyJungleMinionsKilled=" + totalEnemyJungleMinionsKilled +
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
               ", visionClearedPings=" + visionClearedPings +
               ", visionScore=" + visionScore +
               ", visionWardsBoughtInGame=" + visionWardsBoughtInGame +
               ", wardsKilled=" + wardsKilled +
               ", wardsPlaced=" + wardsPlaced +
               ", win=" + win +
               '}';
    }
}
