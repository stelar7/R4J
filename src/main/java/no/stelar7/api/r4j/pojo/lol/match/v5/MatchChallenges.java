package no.stelar7.api.r4j.pojo.lol.match.v5;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.*;

/**
 * The challenges of a participant, with a field for every challenge in the api documentation.
 * <p>
 * Riot adds and removes challenges with patches and game modes, so most fields are null for any given match,
 * and a challenge that is not listed here is only in {@code MatchParticipant.getChallenges()}, which has everything the api sent.
 */
public class MatchChallenges implements Serializable
{
    private static final long serialVersionUID = 1L;

    @SerializedName("12AssistStreakCount")
    private Double        twelveAssistStreakCount;
    private Double        baronBuffGoldAdvantageOverThreshold;
    private Double        controlWardTimeCoverageInRiverOrEnemyHalf;
    private Double        earliestBaron;
    private Double        earliestDragonTakedown;
    private Double        earliestElderDragon;
    private Double        earlyLaningPhaseGoldExpAdvantage;
    private Double        fasterSupportQuestCompletion;
    private Double        fastestLegendary;
    private Double        hadAfkTeammate;
    private Double        highestChampionDamage;
    private Double        highestCrowdControlScore;
    private Double        highestWardKills;
    private Double        junglerKillsEarlyJungle;
    private Double        killsOnLanersEarlyJungleAsJungler;
    private Double        laningPhaseGoldExpAdvantage;
    private Double        legendaryCount;
    private Double        maxCsAdvantageOnLaneOpponent;
    private Double        maxLevelLeadLaneOpponent;
    private Double        mostWardsDestroyedOneSweeper;
    private Double        mythicItemUsed;
    private Double        playedChampSelectPosition;
    private Double        soloTurretsLategame;
    private Double        takedownsFirst25Minutes;
    private Double        teleportTakedowns;
    private Double        thirdInhibitorDestroyedTime;
    private Double        threeWardsOneSweeperCount;
    private Double        visionScoreAdvantageLaneOpponent;
    @SerializedName("InfernalScalePickup")
    private Double        infernalScalePickup;
    private Double        fistBumpParticipation;
    private Double        voidMonsterKill;
    private Double        abilityUses;
    private Double        acesBefore15Minutes;
    private Double        alliedJungleMonsterKills;
    private Double        baronTakedowns;
    private Double        blastConeOppositeOpponentCount;
    private Double        bountyGold;
    private Double        buffsStolen;
    private Double        completeSupportQuestInTime;
    private Double        controlWardsPlaced;
    private Double        damagePerMinute;
    private Double        damageTakenOnTeamPercentage;
    private Double        dancedWithRiftHerald;
    private Double        deathsByEnemyChamps;
    private Double        dodgeSkillShotsSmallWindow;
    private Double        doubleAces;
    private Double        dragonTakedowns;
    private List<Integer> legendaryItemUsed;
    private Double        effectiveHealAndShielding;
    private Double        elderDragonKillsWithOpposingSoul;
    private Double        elderDragonMultikills;
    private Double        enemyChampionImmobilizations;
    private Double        enemyJungleMonsterKills;
    private Double        epicMonsterKillsNearEnemyJungler;
    private Double        epicMonsterKillsWithin30SecondsOfSpawn;
    private Double        epicMonsterSteals;
    private Double        epicMonsterStolenWithoutSmite;
    private Double        firstTurretKilled;
    private Double        firstTurretKilledTime;
    private Double        flawlessAces;
    private Double        fullTeamTakedown;
    private Double        gameLength;
    private Double        getTakedownsInAllLanesEarlyJungleAsLaner;
    private Double        goldPerMinute;
    private Double        hadOpenNexus;
    private Double        immobilizeAndKillWithAlly;
    private Double        initialBuffCount;
    private Double        initialCrabCount;
    private Double        jungleCsBefore10Minutes;
    private Double        junglerTakedownsNearDamagedEpicMonster;
    private Double        kda;
    private Double        killAfterHiddenWithAlly;
    private Double        killedChampTookFullTeamDamageSurvived;
    private Double        killingSprees;
    private Double        killParticipation;
    private Double        killsNearEnemyTurret;
    private Double        killsOnOtherLanesEarlyJungleAsLaner;
    private Double        killsOnRecentlyHealedByAramPack;
    private Double        killsUnderOwnTurret;
    private Double        killsWithHelpFromEpicMonster;
    private Double        knockEnemyIntoTeamAndKill;
    private Double        kTurretsDestroyedBeforePlatesFall;
    private Double        landSkillShotsEarlyGame;
    private Double        laneMinionsFirst10Minutes;
    private Double        lostAnInhibitor;
    private Double        maxKillDeficit;
    private Double        mejaisFullStackInTime;
    private Double        moreEnemyJungleThanOpponent;
    private Double        multiKillOneSpell;
    private Double        multikills;
    private Double        multikillsAfterAggressiveFlash;
    private Double        multiTurretRiftHeraldCount;
    private Double        outerTurretExecutesBefore10Minutes;
    private Double        outnumberedKills;
    private Double        outnumberedNexusKill;
    private Double        perfectDragonSoulsTaken;
    private Double        perfectGame;
    private Double        pickKillWithAlly;
    private Double        poroExplosions;
    private Double        quickCleanse;
    private Double        quickFirstTurret;
    private Double        quickSoloKills;
    private Double        riftHeraldTakedowns;
    private Double        saveAllyFromDeath;
    private Double        scuttleCrabKills;
    private Double        shortestTimeToAceFromFirstTakedown;
    private Double        skillshotsDodged;
    private Double        skillshotsHit;
    private Double        snowballsHit;
    private Double        soloBaronKills;
    @SerializedName("SWARM_DefeatAatrox")
    private Double        swarmDefeatAatrox;
    @SerializedName("SWARM_DefeatBriar")
    private Double        swarmDefeatBriar;
    @SerializedName("SWARM_DefeatMiniBosses")
    private Double        swarmDefeatMiniBosses;
    @SerializedName("SWARM_EvolveWeapon")
    private Double        swarmEvolveWeapon;
    @SerializedName("SWARM_Have3Passives")
    private Double        swarmHave3Passives;
    @SerializedName("SWARM_KillEnemy")
    private Double        swarmKillEnemy;
    @SerializedName("SWARM_PickupGold")
    private Double        swarmPickupGold;
    @SerializedName("SWARM_ReachLevel50")
    private Double        swarmReachLevel50;
    @SerializedName("SWARM_Survive15Min")
    private Double        swarmSurvive15Min;
    @SerializedName("SWARM_WinWith5EvolvedWeapons")
    private Double        swarmWinWith5EvolvedWeapons;
    private Double        soloKills;
    private Double        stealthWardsPlaced;
    private Double        survivedSingleDigitHpCount;
    private Double        survivedThreeImmobilizesInFight;
    private Double        takedownOnFirstTurret;
    private Double        takedowns;
    private Double        takedownsAfterGainingLevelAdvantage;
    private Double        takedownsBeforeJungleMinionSpawn;
    private Double        takedownsFirstXMinutes;
    private Double        takedownsInAlcove;
    private Double        takedownsInEnemyFountain;
    private Double        teamBaronKills;
    private Double        teamDamagePercentage;
    private Double        teamElderDragonKills;
    private Double        teamRiftHeraldKills;
    private Double        tookLargeDamageSurvived;
    private Double        turretPlatesTaken;
    private Double        turretsTakenWithRiftHerald;
    private Double        turretTakedowns;
    private Double        twentyMinionsIn3SecondsCount;
    private Double        twoWardsOneSweeperCount;
    private Double        unseenRecalls;
    private Double        visionScorePerMinute;
    private Double        wardsGuarded;
    private Double        wardTakedowns;
    private Double        wardTakedownsBefore20M;

    public Double getTwelveAssistStreakCount()
    {
        return twelveAssistStreakCount;
    }
    
    public Double getBaronBuffGoldAdvantageOverThreshold()
    {
        return baronBuffGoldAdvantageOverThreshold;
    }
    
    public Double getControlWardTimeCoverageInRiverOrEnemyHalf()
    {
        return controlWardTimeCoverageInRiverOrEnemyHalf;
    }
    
    public Double getEarliestBaron()
    {
        return earliestBaron;
    }
    
    public Double getEarliestDragonTakedown()
    {
        return earliestDragonTakedown;
    }
    
    public Double getEarliestElderDragon()
    {
        return earliestElderDragon;
    }
    
    public Double getEarlyLaningPhaseGoldExpAdvantage()
    {
        return earlyLaningPhaseGoldExpAdvantage;
    }
    
    public Double getFasterSupportQuestCompletion()
    {
        return fasterSupportQuestCompletion;
    }
    
    public Double getFastestLegendary()
    {
        return fastestLegendary;
    }
    
    public Double getHadAfkTeammate()
    {
        return hadAfkTeammate;
    }
    
    public Double getHighestChampionDamage()
    {
        return highestChampionDamage;
    }
    
    public Double getHighestCrowdControlScore()
    {
        return highestCrowdControlScore;
    }
    
    public Double getHighestWardKills()
    {
        return highestWardKills;
    }
    
    public Double getJunglerKillsEarlyJungle()
    {
        return junglerKillsEarlyJungle;
    }
    
    public Double getKillsOnLanersEarlyJungleAsJungler()
    {
        return killsOnLanersEarlyJungleAsJungler;
    }
    
    public Double getLaningPhaseGoldExpAdvantage()
    {
        return laningPhaseGoldExpAdvantage;
    }
    
    public Double getLegendaryCount()
    {
        return legendaryCount;
    }
    
    public Double getMaxCsAdvantageOnLaneOpponent()
    {
        return maxCsAdvantageOnLaneOpponent;
    }
    
    public Double getMaxLevelLeadLaneOpponent()
    {
        return maxLevelLeadLaneOpponent;
    }
    
    public Double getMostWardsDestroyedOneSweeper()
    {
        return mostWardsDestroyedOneSweeper;
    }
    
    public Double getMythicItemUsed()
    {
        return mythicItemUsed;
    }
    
    public Double getPlayedChampSelectPosition()
    {
        return playedChampSelectPosition;
    }
    
    public Double getSoloTurretsLategame()
    {
        return soloTurretsLategame;
    }
    
    public Double getTakedownsFirst25Minutes()
    {
        return takedownsFirst25Minutes;
    }
    
    public Double getTeleportTakedowns()
    {
        return teleportTakedowns;
    }
    
    public Double getThirdInhibitorDestroyedTime()
    {
        return thirdInhibitorDestroyedTime;
    }
    
    public Double getThreeWardsOneSweeperCount()
    {
        return threeWardsOneSweeperCount;
    }
    
    public Double getVisionScoreAdvantageLaneOpponent()
    {
        return visionScoreAdvantageLaneOpponent;
    }
    
    public Double getInfernalScalePickup()
    {
        return infernalScalePickup;
    }
    
    public Double getFistBumpParticipation()
    {
        return fistBumpParticipation;
    }
    
    public Double getVoidMonsterKill()
    {
        return voidMonsterKill;
    }
    
    public Double getAbilityUses()
    {
        return abilityUses;
    }
    
    public Double getAcesBefore15Minutes()
    {
        return acesBefore15Minutes;
    }
    
    public Double getAlliedJungleMonsterKills()
    {
        return alliedJungleMonsterKills;
    }
    
    public Double getBaronTakedowns()
    {
        return baronTakedowns;
    }
    
    public Double getBlastConeOppositeOpponentCount()
    {
        return blastConeOppositeOpponentCount;
    }
    
    public Double getBountyGold()
    {
        return bountyGold;
    }
    
    public Double getBuffsStolen()
    {
        return buffsStolen;
    }
    
    public Double getCompleteSupportQuestInTime()
    {
        return completeSupportQuestInTime;
    }
    
    public Double getControlWardsPlaced()
    {
        return controlWardsPlaced;
    }
    
    public Double getDamagePerMinute()
    {
        return damagePerMinute;
    }
    
    public Double getDamageTakenOnTeamPercentage()
    {
        return damageTakenOnTeamPercentage;
    }
    
    public Double getDancedWithRiftHerald()
    {
        return dancedWithRiftHerald;
    }
    
    public Double getDeathsByEnemyChamps()
    {
        return deathsByEnemyChamps;
    }
    
    public Double getDodgeSkillShotsSmallWindow()
    {
        return dodgeSkillShotsSmallWindow;
    }
    
    public Double getDoubleAces()
    {
        return doubleAces;
    }
    
    public Double getDragonTakedowns()
    {
        return dragonTakedowns;
    }
    
    public List<Integer> getLegendaryItemUsed()
    {
        return legendaryItemUsed;
    }
    
    public Double getEffectiveHealAndShielding()
    {
        return effectiveHealAndShielding;
    }
    
    public Double getElderDragonKillsWithOpposingSoul()
    {
        return elderDragonKillsWithOpposingSoul;
    }
    
    public Double getElderDragonMultikills()
    {
        return elderDragonMultikills;
    }
    
    public Double getEnemyChampionImmobilizations()
    {
        return enemyChampionImmobilizations;
    }
    
    public Double getEnemyJungleMonsterKills()
    {
        return enemyJungleMonsterKills;
    }
    
    public Double getEpicMonsterKillsNearEnemyJungler()
    {
        return epicMonsterKillsNearEnemyJungler;
    }
    
    public Double getEpicMonsterKillsWithin30SecondsOfSpawn()
    {
        return epicMonsterKillsWithin30SecondsOfSpawn;
    }
    
    public Double getEpicMonsterSteals()
    {
        return epicMonsterSteals;
    }
    
    public Double getEpicMonsterStolenWithoutSmite()
    {
        return epicMonsterStolenWithoutSmite;
    }
    
    public Double getFirstTurretKilled()
    {
        return firstTurretKilled;
    }
    
    public Double getFirstTurretKilledTime()
    {
        return firstTurretKilledTime;
    }
    
    public Double getFlawlessAces()
    {
        return flawlessAces;
    }
    
    public Double getFullTeamTakedown()
    {
        return fullTeamTakedown;
    }
    
    public Double getGameLength()
    {
        return gameLength;
    }
    
    public Double getGetTakedownsInAllLanesEarlyJungleAsLaner()
    {
        return getTakedownsInAllLanesEarlyJungleAsLaner;
    }
    
    public Double getGoldPerMinute()
    {
        return goldPerMinute;
    }
    
    public Double getHadOpenNexus()
    {
        return hadOpenNexus;
    }
    
    public Double getImmobilizeAndKillWithAlly()
    {
        return immobilizeAndKillWithAlly;
    }
    
    public Double getInitialBuffCount()
    {
        return initialBuffCount;
    }
    
    public Double getInitialCrabCount()
    {
        return initialCrabCount;
    }
    
    public Double getJungleCsBefore10Minutes()
    {
        return jungleCsBefore10Minutes;
    }
    
    public Double getJunglerTakedownsNearDamagedEpicMonster()
    {
        return junglerTakedownsNearDamagedEpicMonster;
    }
    
    public Double getKda()
    {
        return kda;
    }
    
    public Double getKillAfterHiddenWithAlly()
    {
        return killAfterHiddenWithAlly;
    }
    
    public Double getKilledChampTookFullTeamDamageSurvived()
    {
        return killedChampTookFullTeamDamageSurvived;
    }
    
    public Double getKillingSprees()
    {
        return killingSprees;
    }
    
    public Double getKillParticipation()
    {
        return killParticipation;
    }
    
    public Double getKillsNearEnemyTurret()
    {
        return killsNearEnemyTurret;
    }
    
    public Double getKillsOnOtherLanesEarlyJungleAsLaner()
    {
        return killsOnOtherLanesEarlyJungleAsLaner;
    }
    
    public Double getKillsOnRecentlyHealedByAramPack()
    {
        return killsOnRecentlyHealedByAramPack;
    }
    
    public Double getKillsUnderOwnTurret()
    {
        return killsUnderOwnTurret;
    }
    
    public Double getKillsWithHelpFromEpicMonster()
    {
        return killsWithHelpFromEpicMonster;
    }
    
    public Double getKnockEnemyIntoTeamAndKill()
    {
        return knockEnemyIntoTeamAndKill;
    }
    
    public Double getKTurretsDestroyedBeforePlatesFall()
    {
        return kTurretsDestroyedBeforePlatesFall;
    }
    
    public Double getLandSkillShotsEarlyGame()
    {
        return landSkillShotsEarlyGame;
    }
    
    public Double getLaneMinionsFirst10Minutes()
    {
        return laneMinionsFirst10Minutes;
    }
    
    public Double getLostAnInhibitor()
    {
        return lostAnInhibitor;
    }
    
    public Double getMaxKillDeficit()
    {
        return maxKillDeficit;
    }
    
    public Double getMejaisFullStackInTime()
    {
        return mejaisFullStackInTime;
    }
    
    public Double getMoreEnemyJungleThanOpponent()
    {
        return moreEnemyJungleThanOpponent;
    }
    
    public Double getMultiKillOneSpell()
    {
        return multiKillOneSpell;
    }
    
    public Double getMultikills()
    {
        return multikills;
    }
    
    public Double getMultikillsAfterAggressiveFlash()
    {
        return multikillsAfterAggressiveFlash;
    }
    
    public Double getMultiTurretRiftHeraldCount()
    {
        return multiTurretRiftHeraldCount;
    }
    
    public Double getOuterTurretExecutesBefore10Minutes()
    {
        return outerTurretExecutesBefore10Minutes;
    }
    
    public Double getOutnumberedKills()
    {
        return outnumberedKills;
    }
    
    public Double getOutnumberedNexusKill()
    {
        return outnumberedNexusKill;
    }
    
    public Double getPerfectDragonSoulsTaken()
    {
        return perfectDragonSoulsTaken;
    }
    
    public Double getPerfectGame()
    {
        return perfectGame;
    }
    
    public Double getPickKillWithAlly()
    {
        return pickKillWithAlly;
    }
    
    public Double getPoroExplosions()
    {
        return poroExplosions;
    }
    
    public Double getQuickCleanse()
    {
        return quickCleanse;
    }
    
    public Double getQuickFirstTurret()
    {
        return quickFirstTurret;
    }
    
    public Double getQuickSoloKills()
    {
        return quickSoloKills;
    }
    
    public Double getRiftHeraldTakedowns()
    {
        return riftHeraldTakedowns;
    }
    
    public Double getSaveAllyFromDeath()
    {
        return saveAllyFromDeath;
    }
    
    public Double getScuttleCrabKills()
    {
        return scuttleCrabKills;
    }
    
    public Double getShortestTimeToAceFromFirstTakedown()
    {
        return shortestTimeToAceFromFirstTakedown;
    }
    
    public Double getSkillshotsDodged()
    {
        return skillshotsDodged;
    }
    
    public Double getSkillshotsHit()
    {
        return skillshotsHit;
    }
    
    public Double getSnowballsHit()
    {
        return snowballsHit;
    }
    
    public Double getSoloBaronKills()
    {
        return soloBaronKills;
    }
    
    public Double getSwarmDefeatAatrox()
    {
        return swarmDefeatAatrox;
    }
    
    public Double getSwarmDefeatBriar()
    {
        return swarmDefeatBriar;
    }
    
    public Double getSwarmDefeatMiniBosses()
    {
        return swarmDefeatMiniBosses;
    }
    
    public Double getSwarmEvolveWeapon()
    {
        return swarmEvolveWeapon;
    }
    
    public Double getSwarmHave3Passives()
    {
        return swarmHave3Passives;
    }
    
    public Double getSwarmKillEnemy()
    {
        return swarmKillEnemy;
    }
    
    public Double getSwarmPickupGold()
    {
        return swarmPickupGold;
    }
    
    public Double getSwarmReachLevel50()
    {
        return swarmReachLevel50;
    }
    
    public Double getSwarmSurvive15Min()
    {
        return swarmSurvive15Min;
    }
    
    public Double getSwarmWinWith5EvolvedWeapons()
    {
        return swarmWinWith5EvolvedWeapons;
    }
    
    public Double getSoloKills()
    {
        return soloKills;
    }
    
    public Double getStealthWardsPlaced()
    {
        return stealthWardsPlaced;
    }
    
    public Double getSurvivedSingleDigitHpCount()
    {
        return survivedSingleDigitHpCount;
    }
    
    public Double getSurvivedThreeImmobilizesInFight()
    {
        return survivedThreeImmobilizesInFight;
    }
    
    public Double getTakedownOnFirstTurret()
    {
        return takedownOnFirstTurret;
    }
    
    public Double getTakedowns()
    {
        return takedowns;
    }
    
    public Double getTakedownsAfterGainingLevelAdvantage()
    {
        return takedownsAfterGainingLevelAdvantage;
    }
    
    public Double getTakedownsBeforeJungleMinionSpawn()
    {
        return takedownsBeforeJungleMinionSpawn;
    }
    
    public Double getTakedownsFirstXMinutes()
    {
        return takedownsFirstXMinutes;
    }
    
    public Double getTakedownsInAlcove()
    {
        return takedownsInAlcove;
    }
    
    public Double getTakedownsInEnemyFountain()
    {
        return takedownsInEnemyFountain;
    }
    
    public Double getTeamBaronKills()
    {
        return teamBaronKills;
    }
    
    public Double getTeamDamagePercentage()
    {
        return teamDamagePercentage;
    }
    
    public Double getTeamElderDragonKills()
    {
        return teamElderDragonKills;
    }
    
    public Double getTeamRiftHeraldKills()
    {
        return teamRiftHeraldKills;
    }
    
    public Double getTookLargeDamageSurvived()
    {
        return tookLargeDamageSurvived;
    }
    
    public Double getTurretPlatesTaken()
    {
        return turretPlatesTaken;
    }
    
    public Double getTurretsTakenWithRiftHerald()
    {
        return turretsTakenWithRiftHerald;
    }
    
    public Double getTurretTakedowns()
    {
        return turretTakedowns;
    }
    
    public Double getTwentyMinionsIn3SecondsCount()
    {
        return twentyMinionsIn3SecondsCount;
    }
    
    public Double getTwoWardsOneSweeperCount()
    {
        return twoWardsOneSweeperCount;
    }
    
    public Double getUnseenRecalls()
    {
        return unseenRecalls;
    }
    
    public Double getVisionScorePerMinute()
    {
        return visionScorePerMinute;
    }
    
    public Double getWardsGuarded()
    {
        return wardsGuarded;
    }
    
    public Double getWardTakedowns()
    {
        return wardTakedowns;
    }
    
    public Double getWardTakedownsBefore20M()
    {
        return wardTakedownsBefore20M;
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
        MatchChallenges that = (MatchChallenges) o;
        return Objects.equals(twelveAssistStreakCount, that.twelveAssistStreakCount) &&
               Objects.equals(baronBuffGoldAdvantageOverThreshold, that.baronBuffGoldAdvantageOverThreshold) &&
               Objects.equals(controlWardTimeCoverageInRiverOrEnemyHalf, that.controlWardTimeCoverageInRiverOrEnemyHalf) &&
               Objects.equals(earliestBaron, that.earliestBaron) &&
               Objects.equals(earliestDragonTakedown, that.earliestDragonTakedown) &&
               Objects.equals(earliestElderDragon, that.earliestElderDragon) &&
               Objects.equals(earlyLaningPhaseGoldExpAdvantage, that.earlyLaningPhaseGoldExpAdvantage) &&
               Objects.equals(fasterSupportQuestCompletion, that.fasterSupportQuestCompletion) &&
               Objects.equals(fastestLegendary, that.fastestLegendary) &&
               Objects.equals(hadAfkTeammate, that.hadAfkTeammate) &&
               Objects.equals(highestChampionDamage, that.highestChampionDamage) &&
               Objects.equals(highestCrowdControlScore, that.highestCrowdControlScore) &&
               Objects.equals(highestWardKills, that.highestWardKills) &&
               Objects.equals(junglerKillsEarlyJungle, that.junglerKillsEarlyJungle) &&
               Objects.equals(killsOnLanersEarlyJungleAsJungler, that.killsOnLanersEarlyJungleAsJungler) &&
               Objects.equals(laningPhaseGoldExpAdvantage, that.laningPhaseGoldExpAdvantage) &&
               Objects.equals(legendaryCount, that.legendaryCount) &&
               Objects.equals(maxCsAdvantageOnLaneOpponent, that.maxCsAdvantageOnLaneOpponent) &&
               Objects.equals(maxLevelLeadLaneOpponent, that.maxLevelLeadLaneOpponent) &&
               Objects.equals(mostWardsDestroyedOneSweeper, that.mostWardsDestroyedOneSweeper) &&
               Objects.equals(mythicItemUsed, that.mythicItemUsed) &&
               Objects.equals(playedChampSelectPosition, that.playedChampSelectPosition) &&
               Objects.equals(soloTurretsLategame, that.soloTurretsLategame) &&
               Objects.equals(takedownsFirst25Minutes, that.takedownsFirst25Minutes) &&
               Objects.equals(teleportTakedowns, that.teleportTakedowns) &&
               Objects.equals(thirdInhibitorDestroyedTime, that.thirdInhibitorDestroyedTime) &&
               Objects.equals(threeWardsOneSweeperCount, that.threeWardsOneSweeperCount) &&
               Objects.equals(visionScoreAdvantageLaneOpponent, that.visionScoreAdvantageLaneOpponent) &&
               Objects.equals(infernalScalePickup, that.infernalScalePickup) &&
               Objects.equals(fistBumpParticipation, that.fistBumpParticipation) &&
               Objects.equals(voidMonsterKill, that.voidMonsterKill) &&
               Objects.equals(abilityUses, that.abilityUses) &&
               Objects.equals(acesBefore15Minutes, that.acesBefore15Minutes) &&
               Objects.equals(alliedJungleMonsterKills, that.alliedJungleMonsterKills) &&
               Objects.equals(baronTakedowns, that.baronTakedowns) &&
               Objects.equals(blastConeOppositeOpponentCount, that.blastConeOppositeOpponentCount) &&
               Objects.equals(bountyGold, that.bountyGold) &&
               Objects.equals(buffsStolen, that.buffsStolen) &&
               Objects.equals(completeSupportQuestInTime, that.completeSupportQuestInTime) &&
               Objects.equals(controlWardsPlaced, that.controlWardsPlaced) &&
               Objects.equals(damagePerMinute, that.damagePerMinute) &&
               Objects.equals(damageTakenOnTeamPercentage, that.damageTakenOnTeamPercentage) &&
               Objects.equals(dancedWithRiftHerald, that.dancedWithRiftHerald) &&
               Objects.equals(deathsByEnemyChamps, that.deathsByEnemyChamps) &&
               Objects.equals(dodgeSkillShotsSmallWindow, that.dodgeSkillShotsSmallWindow) &&
               Objects.equals(doubleAces, that.doubleAces) &&
               Objects.equals(dragonTakedowns, that.dragonTakedowns) &&
               Objects.equals(legendaryItemUsed, that.legendaryItemUsed) &&
               Objects.equals(effectiveHealAndShielding, that.effectiveHealAndShielding) &&
               Objects.equals(elderDragonKillsWithOpposingSoul, that.elderDragonKillsWithOpposingSoul) &&
               Objects.equals(elderDragonMultikills, that.elderDragonMultikills) &&
               Objects.equals(enemyChampionImmobilizations, that.enemyChampionImmobilizations) &&
               Objects.equals(enemyJungleMonsterKills, that.enemyJungleMonsterKills) &&
               Objects.equals(epicMonsterKillsNearEnemyJungler, that.epicMonsterKillsNearEnemyJungler) &&
               Objects.equals(epicMonsterKillsWithin30SecondsOfSpawn, that.epicMonsterKillsWithin30SecondsOfSpawn) &&
               Objects.equals(epicMonsterSteals, that.epicMonsterSteals) &&
               Objects.equals(epicMonsterStolenWithoutSmite, that.epicMonsterStolenWithoutSmite) &&
               Objects.equals(firstTurretKilled, that.firstTurretKilled) &&
               Objects.equals(firstTurretKilledTime, that.firstTurretKilledTime) &&
               Objects.equals(flawlessAces, that.flawlessAces) &&
               Objects.equals(fullTeamTakedown, that.fullTeamTakedown) &&
               Objects.equals(gameLength, that.gameLength) &&
               Objects.equals(getTakedownsInAllLanesEarlyJungleAsLaner, that.getTakedownsInAllLanesEarlyJungleAsLaner) &&
               Objects.equals(goldPerMinute, that.goldPerMinute) &&
               Objects.equals(hadOpenNexus, that.hadOpenNexus) &&
               Objects.equals(immobilizeAndKillWithAlly, that.immobilizeAndKillWithAlly) &&
               Objects.equals(initialBuffCount, that.initialBuffCount) &&
               Objects.equals(initialCrabCount, that.initialCrabCount) &&
               Objects.equals(jungleCsBefore10Minutes, that.jungleCsBefore10Minutes) &&
               Objects.equals(junglerTakedownsNearDamagedEpicMonster, that.junglerTakedownsNearDamagedEpicMonster) &&
               Objects.equals(kda, that.kda) &&
               Objects.equals(killAfterHiddenWithAlly, that.killAfterHiddenWithAlly) &&
               Objects.equals(killedChampTookFullTeamDamageSurvived, that.killedChampTookFullTeamDamageSurvived) &&
               Objects.equals(killingSprees, that.killingSprees) &&
               Objects.equals(killParticipation, that.killParticipation) &&
               Objects.equals(killsNearEnemyTurret, that.killsNearEnemyTurret) &&
               Objects.equals(killsOnOtherLanesEarlyJungleAsLaner, that.killsOnOtherLanesEarlyJungleAsLaner) &&
               Objects.equals(killsOnRecentlyHealedByAramPack, that.killsOnRecentlyHealedByAramPack) &&
               Objects.equals(killsUnderOwnTurret, that.killsUnderOwnTurret) &&
               Objects.equals(killsWithHelpFromEpicMonster, that.killsWithHelpFromEpicMonster) &&
               Objects.equals(knockEnemyIntoTeamAndKill, that.knockEnemyIntoTeamAndKill) &&
               Objects.equals(kTurretsDestroyedBeforePlatesFall, that.kTurretsDestroyedBeforePlatesFall) &&
               Objects.equals(landSkillShotsEarlyGame, that.landSkillShotsEarlyGame) &&
               Objects.equals(laneMinionsFirst10Minutes, that.laneMinionsFirst10Minutes) &&
               Objects.equals(lostAnInhibitor, that.lostAnInhibitor) &&
               Objects.equals(maxKillDeficit, that.maxKillDeficit) &&
               Objects.equals(mejaisFullStackInTime, that.mejaisFullStackInTime) &&
               Objects.equals(moreEnemyJungleThanOpponent, that.moreEnemyJungleThanOpponent) &&
               Objects.equals(multiKillOneSpell, that.multiKillOneSpell) &&
               Objects.equals(multikills, that.multikills) &&
               Objects.equals(multikillsAfterAggressiveFlash, that.multikillsAfterAggressiveFlash) &&
               Objects.equals(multiTurretRiftHeraldCount, that.multiTurretRiftHeraldCount) &&
               Objects.equals(outerTurretExecutesBefore10Minutes, that.outerTurretExecutesBefore10Minutes) &&
               Objects.equals(outnumberedKills, that.outnumberedKills) &&
               Objects.equals(outnumberedNexusKill, that.outnumberedNexusKill) &&
               Objects.equals(perfectDragonSoulsTaken, that.perfectDragonSoulsTaken) &&
               Objects.equals(perfectGame, that.perfectGame) &&
               Objects.equals(pickKillWithAlly, that.pickKillWithAlly) &&
               Objects.equals(poroExplosions, that.poroExplosions) &&
               Objects.equals(quickCleanse, that.quickCleanse) &&
               Objects.equals(quickFirstTurret, that.quickFirstTurret) &&
               Objects.equals(quickSoloKills, that.quickSoloKills) &&
               Objects.equals(riftHeraldTakedowns, that.riftHeraldTakedowns) &&
               Objects.equals(saveAllyFromDeath, that.saveAllyFromDeath) &&
               Objects.equals(scuttleCrabKills, that.scuttleCrabKills) &&
               Objects.equals(shortestTimeToAceFromFirstTakedown, that.shortestTimeToAceFromFirstTakedown) &&
               Objects.equals(skillshotsDodged, that.skillshotsDodged) &&
               Objects.equals(skillshotsHit, that.skillshotsHit) &&
               Objects.equals(snowballsHit, that.snowballsHit) &&
               Objects.equals(soloBaronKills, that.soloBaronKills) &&
               Objects.equals(swarmDefeatAatrox, that.swarmDefeatAatrox) &&
               Objects.equals(swarmDefeatBriar, that.swarmDefeatBriar) &&
               Objects.equals(swarmDefeatMiniBosses, that.swarmDefeatMiniBosses) &&
               Objects.equals(swarmEvolveWeapon, that.swarmEvolveWeapon) &&
               Objects.equals(swarmHave3Passives, that.swarmHave3Passives) &&
               Objects.equals(swarmKillEnemy, that.swarmKillEnemy) &&
               Objects.equals(swarmPickupGold, that.swarmPickupGold) &&
               Objects.equals(swarmReachLevel50, that.swarmReachLevel50) &&
               Objects.equals(swarmSurvive15Min, that.swarmSurvive15Min) &&
               Objects.equals(swarmWinWith5EvolvedWeapons, that.swarmWinWith5EvolvedWeapons) &&
               Objects.equals(soloKills, that.soloKills) &&
               Objects.equals(stealthWardsPlaced, that.stealthWardsPlaced) &&
               Objects.equals(survivedSingleDigitHpCount, that.survivedSingleDigitHpCount) &&
               Objects.equals(survivedThreeImmobilizesInFight, that.survivedThreeImmobilizesInFight) &&
               Objects.equals(takedownOnFirstTurret, that.takedownOnFirstTurret) &&
               Objects.equals(takedowns, that.takedowns) &&
               Objects.equals(takedownsAfterGainingLevelAdvantage, that.takedownsAfterGainingLevelAdvantage) &&
               Objects.equals(takedownsBeforeJungleMinionSpawn, that.takedownsBeforeJungleMinionSpawn) &&
               Objects.equals(takedownsFirstXMinutes, that.takedownsFirstXMinutes) &&
               Objects.equals(takedownsInAlcove, that.takedownsInAlcove) &&
               Objects.equals(takedownsInEnemyFountain, that.takedownsInEnemyFountain) &&
               Objects.equals(teamBaronKills, that.teamBaronKills) &&
               Objects.equals(teamDamagePercentage, that.teamDamagePercentage) &&
               Objects.equals(teamElderDragonKills, that.teamElderDragonKills) &&
               Objects.equals(teamRiftHeraldKills, that.teamRiftHeraldKills) &&
               Objects.equals(tookLargeDamageSurvived, that.tookLargeDamageSurvived) &&
               Objects.equals(turretPlatesTaken, that.turretPlatesTaken) &&
               Objects.equals(turretsTakenWithRiftHerald, that.turretsTakenWithRiftHerald) &&
               Objects.equals(turretTakedowns, that.turretTakedowns) &&
               Objects.equals(twentyMinionsIn3SecondsCount, that.twentyMinionsIn3SecondsCount) &&
               Objects.equals(twoWardsOneSweeperCount, that.twoWardsOneSweeperCount) &&
               Objects.equals(unseenRecalls, that.unseenRecalls) &&
               Objects.equals(visionScorePerMinute, that.visionScorePerMinute) &&
               Objects.equals(wardsGuarded, that.wardsGuarded) &&
               Objects.equals(wardTakedowns, that.wardTakedowns) &&
               Objects.equals(wardTakedownsBefore20M, that.wardTakedownsBefore20M);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(twelveAssistStreakCount, baronBuffGoldAdvantageOverThreshold, controlWardTimeCoverageInRiverOrEnemyHalf, earliestBaron, earliestDragonTakedown, earliestElderDragon, earlyLaningPhaseGoldExpAdvantage, fasterSupportQuestCompletion, fastestLegendary, hadAfkTeammate, highestChampionDamage, highestCrowdControlScore, highestWardKills, junglerKillsEarlyJungle, killsOnLanersEarlyJungleAsJungler, laningPhaseGoldExpAdvantage, legendaryCount, maxCsAdvantageOnLaneOpponent, maxLevelLeadLaneOpponent, mostWardsDestroyedOneSweeper, mythicItemUsed, playedChampSelectPosition, soloTurretsLategame, takedownsFirst25Minutes, teleportTakedowns, thirdInhibitorDestroyedTime, threeWardsOneSweeperCount, visionScoreAdvantageLaneOpponent, infernalScalePickup, fistBumpParticipation, voidMonsterKill, abilityUses, acesBefore15Minutes, alliedJungleMonsterKills, baronTakedowns, blastConeOppositeOpponentCount, bountyGold, buffsStolen, completeSupportQuestInTime, controlWardsPlaced, damagePerMinute, damageTakenOnTeamPercentage, dancedWithRiftHerald, deathsByEnemyChamps, dodgeSkillShotsSmallWindow, doubleAces, dragonTakedowns, legendaryItemUsed, effectiveHealAndShielding, elderDragonKillsWithOpposingSoul, elderDragonMultikills, enemyChampionImmobilizations, enemyJungleMonsterKills, epicMonsterKillsNearEnemyJungler, epicMonsterKillsWithin30SecondsOfSpawn, epicMonsterSteals, epicMonsterStolenWithoutSmite, firstTurretKilled, firstTurretKilledTime, flawlessAces, fullTeamTakedown, gameLength, getTakedownsInAllLanesEarlyJungleAsLaner, goldPerMinute, hadOpenNexus, immobilizeAndKillWithAlly, initialBuffCount, initialCrabCount, jungleCsBefore10Minutes, junglerTakedownsNearDamagedEpicMonster, kda, killAfterHiddenWithAlly, killedChampTookFullTeamDamageSurvived, killingSprees, killParticipation, killsNearEnemyTurret, killsOnOtherLanesEarlyJungleAsLaner, killsOnRecentlyHealedByAramPack, killsUnderOwnTurret, killsWithHelpFromEpicMonster, knockEnemyIntoTeamAndKill, kTurretsDestroyedBeforePlatesFall, landSkillShotsEarlyGame, laneMinionsFirst10Minutes, lostAnInhibitor, maxKillDeficit, mejaisFullStackInTime, moreEnemyJungleThanOpponent, multiKillOneSpell, multikills, multikillsAfterAggressiveFlash, multiTurretRiftHeraldCount, outerTurretExecutesBefore10Minutes, outnumberedKills, outnumberedNexusKill, perfectDragonSoulsTaken, perfectGame, pickKillWithAlly, poroExplosions, quickCleanse, quickFirstTurret, quickSoloKills, riftHeraldTakedowns, saveAllyFromDeath, scuttleCrabKills, shortestTimeToAceFromFirstTakedown, skillshotsDodged, skillshotsHit, snowballsHit, soloBaronKills, swarmDefeatAatrox, swarmDefeatBriar, swarmDefeatMiniBosses, swarmEvolveWeapon, swarmHave3Passives, swarmKillEnemy, swarmPickupGold, swarmReachLevel50, swarmSurvive15Min, swarmWinWith5EvolvedWeapons, soloKills, stealthWardsPlaced, survivedSingleDigitHpCount, survivedThreeImmobilizesInFight, takedownOnFirstTurret, takedowns, takedownsAfterGainingLevelAdvantage, takedownsBeforeJungleMinionSpawn, takedownsFirstXMinutes, takedownsInAlcove, takedownsInEnemyFountain, teamBaronKills, teamDamagePercentage, teamElderDragonKills, teamRiftHeraldKills, tookLargeDamageSurvived, turretPlatesTaken, turretsTakenWithRiftHerald, turretTakedowns, twentyMinionsIn3SecondsCount, twoWardsOneSweeperCount, unseenRecalls, visionScorePerMinute, wardsGuarded, wardTakedowns, wardTakedownsBefore20M);
    }

    @Override
    public String toString()
    {
        return "MatchChallenges{" +
               "twelveAssistStreakCount=" + twelveAssistStreakCount +
               ", baronBuffGoldAdvantageOverThreshold=" + baronBuffGoldAdvantageOverThreshold +
               ", controlWardTimeCoverageInRiverOrEnemyHalf=" + controlWardTimeCoverageInRiverOrEnemyHalf +
               ", earliestBaron=" + earliestBaron +
               ", earliestDragonTakedown=" + earliestDragonTakedown +
               ", earliestElderDragon=" + earliestElderDragon +
               ", earlyLaningPhaseGoldExpAdvantage=" + earlyLaningPhaseGoldExpAdvantage +
               ", fasterSupportQuestCompletion=" + fasterSupportQuestCompletion +
               ", fastestLegendary=" + fastestLegendary +
               ", hadAfkTeammate=" + hadAfkTeammate +
               ", highestChampionDamage=" + highestChampionDamage +
               ", highestCrowdControlScore=" + highestCrowdControlScore +
               ", highestWardKills=" + highestWardKills +
               ", junglerKillsEarlyJungle=" + junglerKillsEarlyJungle +
               ", killsOnLanersEarlyJungleAsJungler=" + killsOnLanersEarlyJungleAsJungler +
               ", laningPhaseGoldExpAdvantage=" + laningPhaseGoldExpAdvantage +
               ", legendaryCount=" + legendaryCount +
               ", maxCsAdvantageOnLaneOpponent=" + maxCsAdvantageOnLaneOpponent +
               ", maxLevelLeadLaneOpponent=" + maxLevelLeadLaneOpponent +
               ", mostWardsDestroyedOneSweeper=" + mostWardsDestroyedOneSweeper +
               ", mythicItemUsed=" + mythicItemUsed +
               ", playedChampSelectPosition=" + playedChampSelectPosition +
               ", soloTurretsLategame=" + soloTurretsLategame +
               ", takedownsFirst25Minutes=" + takedownsFirst25Minutes +
               ", teleportTakedowns=" + teleportTakedowns +
               ", thirdInhibitorDestroyedTime=" + thirdInhibitorDestroyedTime +
               ", threeWardsOneSweeperCount=" + threeWardsOneSweeperCount +
               ", visionScoreAdvantageLaneOpponent=" + visionScoreAdvantageLaneOpponent +
               ", infernalScalePickup=" + infernalScalePickup +
               ", fistBumpParticipation=" + fistBumpParticipation +
               ", voidMonsterKill=" + voidMonsterKill +
               ", abilityUses=" + abilityUses +
               ", acesBefore15Minutes=" + acesBefore15Minutes +
               ", alliedJungleMonsterKills=" + alliedJungleMonsterKills +
               ", baronTakedowns=" + baronTakedowns +
               ", blastConeOppositeOpponentCount=" + blastConeOppositeOpponentCount +
               ", bountyGold=" + bountyGold +
               ", buffsStolen=" + buffsStolen +
               ", completeSupportQuestInTime=" + completeSupportQuestInTime +
               ", controlWardsPlaced=" + controlWardsPlaced +
               ", damagePerMinute=" + damagePerMinute +
               ", damageTakenOnTeamPercentage=" + damageTakenOnTeamPercentage +
               ", dancedWithRiftHerald=" + dancedWithRiftHerald +
               ", deathsByEnemyChamps=" + deathsByEnemyChamps +
               ", dodgeSkillShotsSmallWindow=" + dodgeSkillShotsSmallWindow +
               ", doubleAces=" + doubleAces +
               ", dragonTakedowns=" + dragonTakedowns +
               ", legendaryItemUsed=" + legendaryItemUsed +
               ", effectiveHealAndShielding=" + effectiveHealAndShielding +
               ", elderDragonKillsWithOpposingSoul=" + elderDragonKillsWithOpposingSoul +
               ", elderDragonMultikills=" + elderDragonMultikills +
               ", enemyChampionImmobilizations=" + enemyChampionImmobilizations +
               ", enemyJungleMonsterKills=" + enemyJungleMonsterKills +
               ", epicMonsterKillsNearEnemyJungler=" + epicMonsterKillsNearEnemyJungler +
               ", epicMonsterKillsWithin30SecondsOfSpawn=" + epicMonsterKillsWithin30SecondsOfSpawn +
               ", epicMonsterSteals=" + epicMonsterSteals +
               ", epicMonsterStolenWithoutSmite=" + epicMonsterStolenWithoutSmite +
               ", firstTurretKilled=" + firstTurretKilled +
               ", firstTurretKilledTime=" + firstTurretKilledTime +
               ", flawlessAces=" + flawlessAces +
               ", fullTeamTakedown=" + fullTeamTakedown +
               ", gameLength=" + gameLength +
               ", getTakedownsInAllLanesEarlyJungleAsLaner=" + getTakedownsInAllLanesEarlyJungleAsLaner +
               ", goldPerMinute=" + goldPerMinute +
               ", hadOpenNexus=" + hadOpenNexus +
               ", immobilizeAndKillWithAlly=" + immobilizeAndKillWithAlly +
               ", initialBuffCount=" + initialBuffCount +
               ", initialCrabCount=" + initialCrabCount +
               ", jungleCsBefore10Minutes=" + jungleCsBefore10Minutes +
               ", junglerTakedownsNearDamagedEpicMonster=" + junglerTakedownsNearDamagedEpicMonster +
               ", kda=" + kda +
               ", killAfterHiddenWithAlly=" + killAfterHiddenWithAlly +
               ", killedChampTookFullTeamDamageSurvived=" + killedChampTookFullTeamDamageSurvived +
               ", killingSprees=" + killingSprees +
               ", killParticipation=" + killParticipation +
               ", killsNearEnemyTurret=" + killsNearEnemyTurret +
               ", killsOnOtherLanesEarlyJungleAsLaner=" + killsOnOtherLanesEarlyJungleAsLaner +
               ", killsOnRecentlyHealedByAramPack=" + killsOnRecentlyHealedByAramPack +
               ", killsUnderOwnTurret=" + killsUnderOwnTurret +
               ", killsWithHelpFromEpicMonster=" + killsWithHelpFromEpicMonster +
               ", knockEnemyIntoTeamAndKill=" + knockEnemyIntoTeamAndKill +
               ", kTurretsDestroyedBeforePlatesFall=" + kTurretsDestroyedBeforePlatesFall +
               ", landSkillShotsEarlyGame=" + landSkillShotsEarlyGame +
               ", laneMinionsFirst10Minutes=" + laneMinionsFirst10Minutes +
               ", lostAnInhibitor=" + lostAnInhibitor +
               ", maxKillDeficit=" + maxKillDeficit +
               ", mejaisFullStackInTime=" + mejaisFullStackInTime +
               ", moreEnemyJungleThanOpponent=" + moreEnemyJungleThanOpponent +
               ", multiKillOneSpell=" + multiKillOneSpell +
               ", multikills=" + multikills +
               ", multikillsAfterAggressiveFlash=" + multikillsAfterAggressiveFlash +
               ", multiTurretRiftHeraldCount=" + multiTurretRiftHeraldCount +
               ", outerTurretExecutesBefore10Minutes=" + outerTurretExecutesBefore10Minutes +
               ", outnumberedKills=" + outnumberedKills +
               ", outnumberedNexusKill=" + outnumberedNexusKill +
               ", perfectDragonSoulsTaken=" + perfectDragonSoulsTaken +
               ", perfectGame=" + perfectGame +
               ", pickKillWithAlly=" + pickKillWithAlly +
               ", poroExplosions=" + poroExplosions +
               ", quickCleanse=" + quickCleanse +
               ", quickFirstTurret=" + quickFirstTurret +
               ", quickSoloKills=" + quickSoloKills +
               ", riftHeraldTakedowns=" + riftHeraldTakedowns +
               ", saveAllyFromDeath=" + saveAllyFromDeath +
               ", scuttleCrabKills=" + scuttleCrabKills +
               ", shortestTimeToAceFromFirstTakedown=" + shortestTimeToAceFromFirstTakedown +
               ", skillshotsDodged=" + skillshotsDodged +
               ", skillshotsHit=" + skillshotsHit +
               ", snowballsHit=" + snowballsHit +
               ", soloBaronKills=" + soloBaronKills +
               ", swarmDefeatAatrox=" + swarmDefeatAatrox +
               ", swarmDefeatBriar=" + swarmDefeatBriar +
               ", swarmDefeatMiniBosses=" + swarmDefeatMiniBosses +
               ", swarmEvolveWeapon=" + swarmEvolveWeapon +
               ", swarmHave3Passives=" + swarmHave3Passives +
               ", swarmKillEnemy=" + swarmKillEnemy +
               ", swarmPickupGold=" + swarmPickupGold +
               ", swarmReachLevel50=" + swarmReachLevel50 +
               ", swarmSurvive15Min=" + swarmSurvive15Min +
               ", swarmWinWith5EvolvedWeapons=" + swarmWinWith5EvolvedWeapons +
               ", soloKills=" + soloKills +
               ", stealthWardsPlaced=" + stealthWardsPlaced +
               ", survivedSingleDigitHpCount=" + survivedSingleDigitHpCount +
               ", survivedThreeImmobilizesInFight=" + survivedThreeImmobilizesInFight +
               ", takedownOnFirstTurret=" + takedownOnFirstTurret +
               ", takedowns=" + takedowns +
               ", takedownsAfterGainingLevelAdvantage=" + takedownsAfterGainingLevelAdvantage +
               ", takedownsBeforeJungleMinionSpawn=" + takedownsBeforeJungleMinionSpawn +
               ", takedownsFirstXMinutes=" + takedownsFirstXMinutes +
               ", takedownsInAlcove=" + takedownsInAlcove +
               ", takedownsInEnemyFountain=" + takedownsInEnemyFountain +
               ", teamBaronKills=" + teamBaronKills +
               ", teamDamagePercentage=" + teamDamagePercentage +
               ", teamElderDragonKills=" + teamElderDragonKills +
               ", teamRiftHeraldKills=" + teamRiftHeraldKills +
               ", tookLargeDamageSurvived=" + tookLargeDamageSurvived +
               ", turretPlatesTaken=" + turretPlatesTaken +
               ", turretsTakenWithRiftHerald=" + turretsTakenWithRiftHerald +
               ", turretTakedowns=" + turretTakedowns +
               ", twentyMinionsIn3SecondsCount=" + twentyMinionsIn3SecondsCount +
               ", twoWardsOneSweeperCount=" + twoWardsOneSweeperCount +
               ", unseenRecalls=" + unseenRecalls +
               ", visionScorePerMinute=" + visionScorePerMinute +
               ", wardsGuarded=" + wardsGuarded +
               ", wardTakedowns=" + wardTakedowns +
               ", wardTakedownsBefore20M=" + wardTakedownsBefore20M +
               '}';
    }
}
