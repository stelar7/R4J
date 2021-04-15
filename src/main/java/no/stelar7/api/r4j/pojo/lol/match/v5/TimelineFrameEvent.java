package no.stelar7.api.r4j.pojo.lol.match.v5;

import no.stelar7.api.r4j.basic.constants.types.TransformType;
import no.stelar7.api.r4j.basic.constants.types.lol.*;

import java.io.Serializable;
import java.time.*;
import java.util.*;

public class TimelineFrameEvent implements Serializable
{
    private static final long serialVersionUID = 8170189893605146893L;
    
    private int                      creatorId;
    private long                     realTimestamp;
    private long                     timestamp;
    private EventType                type;
    private int                      participantId;
    private int                      skillSlot;
    private LevelUpType              levelUpType;
    private int                      itemId;
    private int                      beforeId;
    private int                      afterId;
    private int                      goldGain;
    private WardType                 wardType;
    private int                      bounty;
    private int                      killStreakLength;
    private int                      killerId;
    private TimelinePosition         position;
    private List<TimelineDamageData> victimDamageDealt;
    private List<TimelineDamageData> victimDamageReceived;
    private int                      victimId;
    private BuildingType             buildingType;
    private TowerType                towerType;
    private LaneType                 laneType;
    private List<Integer>            assistingParticipantIds;
    private MonsterType              monsterType;
    private MonsterSubType           monsterSubType;
    private TeamType                 teamId;
    private TeamType                 killerTeamId;
    private TransformType            transformType;
    
    public long getRealTimestamp()
    {
        return realTimestamp;
    }
    
    public ZonedDateTime getTimestampAsDate()
    {
        return ZonedDateTime.ofInstant(Instant.ofEpochMilli(this.realTimestamp), ZoneOffset.UTC);
    }
    
    public long getTimestamp()
    {
        return timestamp;
    }
    
    public EventType getType()
    {
        return type;
    }
    
    public int getCreatorId()
    {
        return creatorId;
    }
    
    public int getParticipantId()
    {
        return participantId;
    }
    
    public int getSkillSlot()
    {
        return skillSlot;
    }
    
    public LevelUpType getLevelUpType()
    {
        return levelUpType;
    }
    
    public int getItemId()
    {
        return itemId;
    }
    
    public int getBeforeId()
    {
        return beforeId;
    }
    
    public int getAfterId()
    {
        return afterId;
    }
    
    public int getGoldGain()
    {
        return goldGain;
    }
    
    public WardType getWardType()
    {
        return wardType;
    }
    
    public int getBounty()
    {
        return bounty;
    }
    
    public int getKillStreakLength()
    {
        return killStreakLength;
    }
    
    public int getKillerId()
    {
        return killerId;
    }
    
    public TimelinePosition getPosition()
    {
        return position;
    }
    
    public List<TimelineDamageData> getVictimDamageDealt()
    {
        return victimDamageDealt == null ? Collections.emptyList() : victimDamageDealt;
    }
    
    public List<TimelineDamageData> getVictimDamageReceived()
    {
        return victimDamageReceived == null ? Collections.emptyList() : victimDamageReceived;
    }
    
    public int getVictimId()
    {
        return victimId;
    }
    
    public BuildingType getBuildingType()
    {
        return buildingType;
    }
    
    public TowerType getTowerType()
    {
        return towerType;
    }
    
    public LaneType getLaneType()
    {
        return laneType;
    }
    
    public List<Integer> getAssistingParticipantIds()
    {
        return assistingParticipantIds;
    }
    
    public MonsterType getMonsterType()
    {
        return monsterType;
    }
    
    public MonsterSubType getMonsterSubType()
    {
        return monsterSubType;
    }
    
    public TeamType getTeamId()
    {
        return teamId;
    }
    
    public TeamType getKillerTeamId()
    {
        return killerTeamId;
    }
    
    public TransformType getTransformType()
    {
        return transformType;
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
        TimelineFrameEvent that = (TimelineFrameEvent) o;
        return creatorId == that.creatorId && realTimestamp == that.realTimestamp && timestamp == that.timestamp && participantId == that.participantId && skillSlot == that.skillSlot && itemId == that.itemId && beforeId == that.beforeId && afterId == that.afterId && goldGain == that.goldGain && bounty == that.bounty && killStreakLength == that.killStreakLength && killerId == that.killerId && victimId == that.victimId && type == that.type && levelUpType == that.levelUpType && wardType == that.wardType && Objects.equals(position, that.position) && Objects.equals(victimDamageDealt, that.victimDamageDealt) && Objects.equals(victimDamageReceived, that.victimDamageReceived) && buildingType == that.buildingType && towerType == that.towerType && laneType == that.laneType && Objects.equals(assistingParticipantIds, that.assistingParticipantIds) && monsterType == that.monsterType && monsterSubType == that.monsterSubType && teamId == that.teamId && killerTeamId == that.killerTeamId && transformType == that.transformType;
    }
    
    @Override
    public int hashCode()
    {
        return Objects.hash(creatorId, realTimestamp, timestamp, type, participantId, skillSlot, levelUpType, itemId, beforeId, afterId, goldGain, wardType, bounty, killStreakLength, killerId, position, victimDamageDealt, victimDamageReceived, victimId, buildingType, towerType, laneType, assistingParticipantIds, monsterType, monsterSubType, teamId, killerTeamId, transformType);
    }
    
    @Override
    public String toString()
    {
        return "TimelineFrameEvent{" +
               "creatorId=" + creatorId +
               ", realTimestamp=" + realTimestamp +
               ", timestamp=" + timestamp +
               ", type=" + type +
               ", participantId=" + participantId +
               ", skillSlot=" + skillSlot +
               ", levelUpType=" + levelUpType +
               ", itemId=" + itemId +
               ", beforeId=" + beforeId +
               ", afterId=" + afterId +
               ", goldGain=" + goldGain +
               ", wardType=" + wardType +
               ", bounty=" + bounty +
               ", killStreakLength=" + killStreakLength +
               ", killerId=" + killerId +
               ", position=" + position +
               ", victimDamageDealt=" + victimDamageDealt +
               ", victimDamageReceived=" + victimDamageReceived +
               ", victimId=" + victimId +
               ", buildingType=" + buildingType +
               ", towerType=" + towerType +
               ", laneType=" + laneType +
               ", assistingParticipantIds=" + assistingParticipantIds +
               ", monsterType=" + monsterType +
               ", monsterSubType=" + monsterSubType +
               ", teamId=" + teamId +
               ", killerTeamId=" + killerTeamId +
               ", transformType=" + transformType +
               '}';
    }
}
