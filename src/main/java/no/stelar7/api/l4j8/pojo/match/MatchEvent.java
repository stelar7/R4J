package no.stelar7.api.l4j8.pojo.match;

import no.stelar7.api.l4j8.basic.constants.types.*;

import java.util.List;

public class MatchEvent
{
    private Integer        afterId;
    private Integer        beforeId;
    private AscencionType  ascendedType;
    private List<Integer>  assistingParticipantIds;
    private BuildingType   buildingType;
    private Integer        creatorId;
    private EventType      type;
    private Integer        itemId;
    private Integer        killerId;
    private LaneType       laneType;
    private LevelUpType    levelUpType;
    private MonsterType    monsterType;
    private MonsterSubType monsterSubType;
    private Integer        participantId;
    private PointType      pointCaptured;
    private MatchPosition  position;
    private Integer        skillSlot;
    private TeamType       teamId;
    private Long           timestamp;
    private TowerType      towerType;
    private Integer        victimId;
    private WardType       wardType;
    
    
    /**
     * The ascended type of the event as AscencionType
     *
     * @return AscencionType
     */
    public AscencionType getAscendedType()
    {
        return ascendedType;
    }
    
    /**
     * The assisting participant IDs of the event. Only present if relevant.
     *
     * @return {@code List<Integer>}
     */
    public List<Integer> getAssistingParticipantIds()
    {
        return this.assistingParticipantIds;
    }
    
    
    /**
     * The building type of the event. Only present if relevant. (Legal values: INHIBITOR_BUILDING, TOWER_BUILDING)
     *
     * @return the building type
     */
    public BuildingType getBuildingType()
    {
        return this.buildingType;
    }
    
    /**
     * The creator ID of the event. Only present if relevant.
     *
     * @return Integer
     */
    public Integer getCreatorId()
    {
        return this.creatorId;
    }
    
    
    /**
     * MatchEvent type. (Legal values: ASCENDED_EVENT, BUILDING_KILL, CAPTURE_POINT, CHAMPION_KILL, ELITE_MONSTER_KILL, ITEM_DESTROYED, ITEM_PURCHASED, ITEM_SOLD, ITEM_UNDO, PORO_KING_SUMMON, SKILL_LEVEL_UP, WARD_KILL, WARD_PLACED)
     *
     * @return String
     */
    public EventType getEventType()
    {
        return this.type;
    }
    
    /**
     * The ending item ID of the event. Only present if relevant.
     *
     * @return Integer
     */
    public Integer getItemAfter()
    {
        return this.afterId;
    }
    
    /**
     * The starting item ID of the event. Only present if relevant.
     *
     * @return Integer
     */
    public Integer getItemBefore()
    {
        return this.beforeId;
    }
    
    /**
     * The item ID of the event. Only present if relevant.
     *
     * @return Integer
     */
    public Integer getItemId()
    {
        return this.itemId;
    }
    
    /**
     * The killer ID of the event. Only present if relevant. Killer ID 0 indicates a minion.
     *
     * @return Integer
     */
    public Integer getKillerId()
    {
        return this.killerId;
    }
    
    /**
     * The lane type of the event. Only present if relevant. (Legal values: BOT_LANE, MID_LANE, TOP_LANE)
     *
     * @return the lane type
     */
    public LaneType getLaneType()
    {
        return this.laneType;
    }
    
    /**
     * The level up type of the event. Only present if relevant. (Legal values: EVOLVE, NORMAL)
     *
     * @return String
     */
    public LevelUpType getLevelUpType()
    {
        return this.levelUpType;
    }
    
    /**
     * The monster type of the event. Only present if relevant. (Legal values: BARON_NASHOR, BLUE_GOLEM, DRAGON, RED_LIZARD, VILEMAW)
     *
     * @return String
     */
    public MonsterType getMonsterType()
    {
        return this.monsterType;
    }
    
    /**
     * The participant ID of the event. Only present if relevant.
     *
     * @return Integer
     */
    public Integer getParticipantId()
    {
        return this.participantId;
    }
    
    /**
     * The point captured in the event. Only present if relevant. (Legal values: POINT_A, POINT_B, POINT_C, POINT_D, POINT_E)
     *
     * @return String
     */
    public PointType getPointCaptured()
    {
        return this.pointCaptured;
    }
    
    /**
     * The position of the event. Only present if relevant.
     *
     * @return MatchPosition
     */
    public MatchPosition getPosition()
    {
        return this.position;
    }
    
    /**
     * The skill slot of the event. Only present if relevant.
     *
     * @return Integer
     */
    public Integer getSkillSlot()
    {
        return this.skillSlot;
    }
    
    /**
     * The team ID of the event. Only present if relevant.
     *
     * @return Integer
     */
    public TeamType getTeam()
    {
        return this.teamId;
    }
    
    /**
     * Represents how many milliseconds into the game the event occurred.
     *
     * @return Long
     */
    public Long getTimestamp()
    {
        return this.timestamp;
    }
    
    /**
     * The tower type of the event. Only present if relevant. (Legal values: BASE_TURRET, FOUNTAIN_TURRET, INNER_TURRET, NEXUS_TURRET, OUTER_TURRET, UNDEFINED_TURRET)
     *
     * @return String
     */
    public TowerType getTowerType()
    {
        return this.towerType;
    }
    
    /**
     * The victim ID of the event. Only present if relevant.
     *
     * @return Integer
     */
    public Integer getVictimId()
    {
        return this.victimId;
    }
    
    /**
     * The ward type of the event. Only present if relevant. (Legal values: SIGHT_WARD, TEEMO_MUSHROOM, UNDEFINED, VISION_WARD, YELLOW_TRINKET, YELLOW_TRINKET_UPGRADE)
     *
     * @return String
     */
    public WardType getWardType()
    {
        return this.wardType;
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
        
        MatchEvent that = (MatchEvent) o;
        
        if ((afterId != null) ? !afterId.equals(that.afterId) : (that.afterId != null))
        {
            return false;
        }
        if ((beforeId != null) ? !beforeId.equals(that.beforeId) : (that.beforeId != null))
        {
            return false;
        }
        if (ascendedType != that.ascendedType)
        {
            return false;
        }
        if ((assistingParticipantIds != null) ? !assistingParticipantIds.equals(that.assistingParticipantIds) : (that.assistingParticipantIds != null))
        {
            return false;
        }
        if (buildingType != that.buildingType)
        {
            return false;
        }
        if ((creatorId != null) ? !creatorId.equals(that.creatorId) : (that.creatorId != null))
        {
            return false;
        }
        if (type != that.type)
        {
            return false;
        }
        if ((itemId != null) ? !itemId.equals(that.itemId) : (that.itemId != null))
        {
            return false;
        }
        if ((killerId != null) ? !killerId.equals(that.killerId) : (that.killerId != null))
        {
            return false;
        }
        if (laneType != that.laneType)
        {
            return false;
        }
        if (levelUpType != that.levelUpType)
        {
            return false;
        }
        if (monsterType != that.monsterType)
        {
            return false;
        }
        if (monsterSubType != that.monsterSubType)
        {
            return false;
        }
        if ((participantId != null) ? !participantId.equals(that.participantId) : (that.participantId != null))
        {
            return false;
        }
        if (pointCaptured != that.pointCaptured)
        {
            return false;
        }
        if ((position != null) ? !position.equals(that.position) : (that.position != null))
        {
            return false;
        }
        if ((skillSlot != null) ? !skillSlot.equals(that.skillSlot) : (that.skillSlot != null))
        {
            return false;
        }
        if (teamId != that.teamId)
        {
            return false;
        }
        if ((timestamp != null) ? !timestamp.equals(that.timestamp) : (that.timestamp != null))
        {
            return false;
        }
        if (towerType != that.towerType)
        {
            return false;
        }
        if ((victimId != null) ? !victimId.equals(that.victimId) : (that.victimId != null))
        {
            return false;
        }
        return wardType == that.wardType;
    }
    
    @Override
    public int hashCode()
    {
        int result = afterId != null ? afterId.hashCode() : 0;
        result = 31 * result + (beforeId != null ? beforeId.hashCode() : 0);
        result = 31 * result + (ascendedType != null ? ascendedType.hashCode() : 0);
        result = 31 * result + (assistingParticipantIds != null ? assistingParticipantIds.hashCode() : 0);
        result = 31 * result + (buildingType != null ? buildingType.hashCode() : 0);
        result = 31 * result + (creatorId != null ? creatorId.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (itemId != null ? itemId.hashCode() : 0);
        result = 31 * result + (killerId != null ? killerId.hashCode() : 0);
        result = 31 * result + (laneType != null ? laneType.hashCode() : 0);
        result = 31 * result + (levelUpType != null ? levelUpType.hashCode() : 0);
        result = 31 * result + (monsterType != null ? monsterType.hashCode() : 0);
        result = 31 * result + (monsterSubType != null ? monsterSubType.hashCode() : 0);
        result = 31 * result + (participantId != null ? participantId.hashCode() : 0);
        result = 31 * result + (pointCaptured != null ? pointCaptured.hashCode() : 0);
        result = 31 * result + (position != null ? position.hashCode() : 0);
        result = 31 * result + (skillSlot != null ? skillSlot.hashCode() : 0);
        result = 31 * result + (teamId != null ? teamId.hashCode() : 0);
        result = 31 * result + (timestamp != null ? timestamp.hashCode() : 0);
        result = 31 * result + (towerType != null ? towerType.hashCode() : 0);
        result = 31 * result + (victimId != null ? victimId.hashCode() : 0);
        result = 31 * result + (wardType != null ? wardType.hashCode() : 0);
        return result;
    }
    
    @Override
    public String toString()
    {
        return "MatchEvent{" +
               "afterId=" + afterId +
               ", beforeId=" + beforeId +
               ", ascendedType=" + ascendedType +
               ", assistingParticipantIds=" + assistingParticipantIds +
               ", buildingType=" + buildingType +
               ", creatorId=" + creatorId +
               ", type=" + type +
               ", itemId=" + itemId +
               ", killerId=" + killerId +
               ", laneType=" + laneType +
               ", levelUpType=" + levelUpType +
               ", monsterType=" + monsterType +
               ", monsterSubType=" + monsterSubType +
               ", participantId=" + participantId +
               ", pointCaptured=" + pointCaptured +
               ", position=" + position +
               ", skillSlot=" + skillSlot +
               ", teamId=" + teamId +
               ", timestamp=" + timestamp +
               ", towerType=" + towerType +
               ", victimId=" + victimId +
               ", wardType=" + wardType +
               '}';
    }
}
