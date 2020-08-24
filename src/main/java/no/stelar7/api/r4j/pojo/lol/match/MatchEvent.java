package no.stelar7.api.r4j.pojo.lol.match;

import no.stelar7.api.r4j.basic.constants.types.lol.*;

import java.io.Serializable;
import java.util.List;

public class MatchEvent implements Serializable
{
    private static final long serialVersionUID = 5385878555903938057L;
    
    private int            afterId;
    private int           beforeId;
    private AscencionType ascendedType;
    private List<Integer> assistingParticipantIds;
    private BuildingType  buildingType;
    private int           creatorId;
    private EventType      type;
    private EventType      eventType;
    private int            itemId;
    private int            killerId;
    private LaneType       laneType;
    private LevelUpType    levelUpType;
    private MonsterType    monsterType;
    private MonsterSubType monsterSubType;
    private int            participantId;
    private PointType      pointCaptured;
    private MatchPosition  position;
    private int            skillSlot;
    private TeamType       teamId;
    private long           timestamp;
    private TowerType      towerType;
    private int            victimId;
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
     * @return {@code List<int>}
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
     * @return int
     */
    public int getCreatorId()
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
        return this.type != null ? this.type : this.eventType;
    }
    
    /**
     * The ending item ID of the event. Only present if relevant.
     *
     * @return int
     */
    public int getItemAfter()
    {
        return this.afterId;
    }
    
    /**
     * The starting item ID of the event. Only present if relevant.
     *
     * @return int
     */
    public int getItemBefore()
    {
        return this.beforeId;
    }
    
    /**
     * The item ID of the event. Only present if relevant.
     *
     * @return int
     */
    public int getItemId()
    {
        return this.itemId;
    }
    
    /**
     * The killer ID of the event. Only present if relevant. Killer ID 0 indicates a minion.
     *
     * @return int
     */
    public int getKillerId()
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
     * @return MonsterType
     */
    public MonsterType getMonsterType()
    {
        return this.monsterType;
    }
    
    /**
     * The monster subtype of the event. Only present if relevant. (Legal values: EARTH_DRAGON, FIRE_DRAGON, WATER_DRAGON, AIR_DRAGON, ELDER_DRAGON)
     *
     * @return MonsterSubType
     */
    public MonsterSubType getMonsterSubType()
    {
        return this.monsterSubType;
    }
    
    /**
     * The participant ID of the event. Only present if relevant.
     *
     * @return int
     */
    public int getParticipantId()
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
     * @return int
     */
    public int getSkillSlot()
    {
        return this.skillSlot;
    }
    
    /**
     * The team ID of the event. Only present if relevant.
     *
     * @return int
     */
    public TeamType getTeam()
    {
        return this.teamId;
    }
    
    /**
     * Represents how many milliseconds into the game the event occurred.
     *
     * @return long
     */
    public long getTimestamp()
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
     * @return int
     */
    public int getVictimId()
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
        
        if (afterId != that.afterId)
        {
            return false;
        }
        if (beforeId != that.beforeId)
        {
            return false;
        }
        if (creatorId != that.creatorId)
        {
            return false;
        }
        if (itemId != that.itemId)
        {
            return false;
        }
        if (killerId != that.killerId)
        {
            return false;
        }
        if (participantId != that.participantId)
        {
            return false;
        }
        if (skillSlot != that.skillSlot)
        {
            return false;
        }
        if (timestamp != that.timestamp)
        {
            return false;
        }
        if (victimId != that.victimId)
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
        if (type != that.type)
        {
            return false;
        }
        if (eventType != that.eventType)
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
        if (pointCaptured != that.pointCaptured)
        {
            return false;
        }
        if ((position != null) ? !position.equals(that.position) : (that.position != null))
        {
            return false;
        }
        if (teamId != that.teamId)
        {
            return false;
        }
        if (towerType != that.towerType)
        {
            return false;
        }
        return wardType == that.wardType;
    }
    
    @Override
    public int hashCode()
    {
        int result = afterId;
        result = 31 * result + beforeId;
        result = 31 * result + (ascendedType != null ? ascendedType.hashCode() : 0);
        result = 31 * result + (assistingParticipantIds != null ? assistingParticipantIds.hashCode() : 0);
        result = 31 * result + (buildingType != null ? buildingType.hashCode() : 0);
        result = 31 * result + creatorId;
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (eventType != null ? eventType.hashCode() : 0);
        result = 31 * result + itemId;
        result = 31 * result + killerId;
        result = 31 * result + (laneType != null ? laneType.hashCode() : 0);
        result = 31 * result + (levelUpType != null ? levelUpType.hashCode() : 0);
        result = 31 * result + (monsterType != null ? monsterType.hashCode() : 0);
        result = 31 * result + (monsterSubType != null ? monsterSubType.hashCode() : 0);
        result = 31 * result + participantId;
        result = 31 * result + (pointCaptured != null ? pointCaptured.hashCode() : 0);
        result = 31 * result + (position != null ? position.hashCode() : 0);
        result = 31 * result + skillSlot;
        result = 31 * result + (teamId != null ? teamId.hashCode() : 0);
        result = 31 * result + (int) (timestamp ^ (timestamp >>> 32));
        result = 31 * result + (towerType != null ? towerType.hashCode() : 0);
        result = 31 * result + victimId;
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
